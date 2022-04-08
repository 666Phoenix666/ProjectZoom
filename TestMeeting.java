import java.util.Scanner;
import java.util.*;
public class TestMeeting {
    public static Scanner scn;
    public static ArrayList<Person> allPeople;
    public static ArrayList<Meeting> allMeetings = new ArrayList<Meeting>();
    public static Person currentPerson = null;
    /*
    Write a test class. This test program will first display a main menu like the following (an output file
has been attached to this document):
a. Create and host a new meeting (you must know at least one friend who will attend your
event)
b. Cancel a meeting:
c. Attend an existing meeting
d. Leave a meeting
e. Display my Meetings
f. Display Meetings organized by me
g. Logout
h. Exit: quits the app.
     */

    /*
    a. Create and host a new meeting (you must know at least one friend who will attend your event):
createMeeting() is invoked. It asks the user the date, and the name of the meeting. The user who
has logged in will be the host of the meeting. Then, the Meeting instance is created, added to the
current user’s iOrganize list by invoking organizeMeeting(), and added to the allMeetings
list of TestClass.
Returns to the main menu.
     */
    /*
    Cancel a meeting: cancelMeeting() is invoked. Only hosts are allowed to cancel meetings, which
are created by them. So, all meetings hosted by the current user are displayed (menu item f), and
asked the name of which is to be cancelled. The meeting, say cancelMe, is fetched from allMeetings list of TestClass. Current user’s (supposed to be the host) cancelMeeting()
method is invoked by passing cancelMe meeting instance as an argument.
Returns to the main menu
     */
    public static void logOut() {
        if(currentPerson == null) {
            System.out.println("You have already logged out");
        }
        else {
            currentPerson = null;
            System.out.println("Logged out");
        }
    }
    public static void displayAllMeeting() {
        if(allMeetings.size() == 0) {
            System.out.println("The list is empty");
        }
        else {


        for(int i = 0; i < allMeetings.size(); i++) {
            System.out.println(allMeetings.toString());
        }
        }
    }
    public static void cancelMeeting() {
        if(currentPerson == null) {
            System.out.println("You need to login first");
            return;
        }
        else {
        currentPerson.displayMyOrganizations();
        System.out.println("Which meeting do you want to cancel ? :");
        String name = scn.nextLine();
        Meeting m = null;
        for(int i = 0; i < currentPerson.getiOrganize().size(); i++) {
            m = currentPerson.getiOrganize().get(i);
            if(m.getName().equals(name) && m.getHost() == currentPerson) {
                currentPerson.cancelMeeting(m);
                allMeetings.remove(m);
                System.out.println("Meeting " + m.getName() + " cancelled");
                break;
            }
        }
    }
    }
    /*
    c. Attend an existing meeting: attendMeeting () will be invoked. First the list of meetings is
displayed. Then the user is asked if s/he would like to attend any of them. If so, s/he is asked which
meeting to attend, and then s/he will be added to the attendee list of the chosen meeting.
     */
    public static void leaveMeeting() {
        if (currentPerson == null) {
            System.out.println("You need to login first");
            return;
        } else {


            displayAllMeeting();
            System.out.println("Which meeting do you want to leave : ");
            String name = scn.nextLine();
            for (int i = 0; i < allMeetings.size(); i++) {
                if (name.equals(allMeetings.get(i).getName())) {
                    currentPerson.removeMeeting(allMeetings.get(i));
                    break;
                }
            }
        }
    }
    public static void attendMeeting() {
        if (currentPerson == null) {
            System.out.println("You have to login first");
            return;
        } else {


            displayAllMeeting();
            System.out.println("Which meeting do you want to attend : ");
            String name = scn.nextLine();
            Meeting m = null;
            for (int i = 0; i < allMeetings.size(); i++) {
                m = allMeetings.get(i);
                if (name.equals(m.getName()) && !(allMeetings.get(i).equals(currentPerson.getMymeetings().get(i)))
                        && !(allMeetings.get(i).equals(currentPerson.getiOrganize().get(i)))) {
                    currentPerson.addMeeting(allMeetings.get(i));
                    System.out.println("you have attended to meeting " + m.getName());
                    break;
                }
            }
        }
    }
    public static void createMeeting() {
        if (currentPerson == null) {
            System.out.println("You have to login first");
            return;
        }
        else {

            allMeetings = new ArrayList<Meeting>();
            System.out.println("Enter the day");
            int day = scn.nextInt();
            System.out.println("Enter the month");
            int month = scn.nextInt();
            System.out.println("Enter the year");
            int year = scn.nextInt();
            scn.nextLine();
            System.out.println("Enter the time zone");
            String timeZone = scn.nextLine();
            System.out.println("Enter the hour");
            int hour = scn.nextInt();
            System.out.println("Enter the minute");
            int minute = scn.nextInt();
            scn.nextLine();
            System.out.println("What is the name of the meeting ?");
            String name = scn.nextLine();
            System.out.println("Is it online :");
            String online = scn.nextLine();
            boolean Isonline;
            if (online.equals("yes")) {
                Isonline = true;
            } else {
                Isonline = false;
            }
            System.out.println("Enter at least one attendee id");
            int id = scn.nextInt();
            scn.nextLine();
            for (int i = 0; i < allPeople.size(); i++) {
                Person attendee = null;
                Person p = allPeople.get(i);
                if (p != null) {
                    if (p.getId() == id) {
                        attendee = p;
                        Meeting m = new Meeting(day, month, year, timeZone, hour, minute, currentPerson, Isonline, p, name);
                        allMeetings.add(m);
                        System.out.println("Meeting " + m.getName() + " organized");
                        break;
                    }
                }
            }
        }
    }
    public static void login() {
        if(currentPerson !=null) {
            System.out.println("You have already logged in");
            return;
        }
        else {
            System.out.println("İd of the account you want to login");
            int id = scn.nextInt();
            scn.nextLine();
            for(int i = 0; i < allPeople.size(); i++) {
                Person p = allPeople.get(i);
                if(p.getId() == id) {
                    currentPerson = p;
                }
            }
        }
    }
    public static void displayMeetingsOrganizedByMe() {
        if (currentPerson == null) {
            System.out.println("You need to login first");
            return;
        }
        else {

            if (allMeetings.size() == 0) {
                System.out.println("List is empty!");
            } else {
                currentPerson.displayMyOrganizations();
            }
        }
    }
    public static void displayMyMeetings() {
        if (currentPerson == null) {
            System.out.println("You need to login first");
            return;
        } else {
            if (currentPerson.getMymeetings().size() == 0 && currentPerson.getiOrganize().size() != 0) {
                System.out.println("List is empty(meetings)");
                currentPerson.displayMyOrganizations();
            }
            if (currentPerson.getiOrganize().size() == 0 && currentPerson.getMymeetings().size() != 0) {
                System.out.println("List is empty(organized)");
                currentPerson.displayMyOrganizations();
            } else {

                currentPerson.displayMyMeetings();

            }
        }
    }
    public static void main(String[] args) {
        scn = new Scanner(System.in);
        allPeople = new ArrayList<Person>();

        System.out.println("Create list of users enter -1 to continue with menu");
        String username;
        do {
            System.out.println("Enter username : ");
             username = scn.nextLine();
            Person p = new Person(username);
            System.out.println("your id is : " + p.getId() + " do not forget");
            allPeople.add(p);
        }while(!username.equals("-1"));
        boolean work = true;
        while (work) {
            String menu = "0-Login\n"
                            +"a-Host a new meeting\n"
                            +"b-Cancel meeting\n"
                            +"c-Attend an existing meeting\n"
                            +"d-Leave meeting\n"
                            +"e-Display my meeting\n"
                            +"f-display meetings organized by me\n"
                            +"g-Logout\n"
                            +"h-quit app";
            System.out.println(menu);
            System.out.println("Enter operation : ");
            String operation = scn.nextLine();
            switch (operation) {
                case "0":
                    login();
                    break;
                case "a":
                    createMeeting();
                    break;
                case "b":
                    cancelMeeting();
                    break;
                case "c":
                    attendMeeting();
                    break;
                case "d":
                    leaveMeeting();
                    break;
                    case "e" :
                        displayMyMeetings();
                        break;
                     case "f":
                    displayMeetingsOrganizedByMe();
                    break;
                case "g":
                    logOut();
                    break;
                case "h":
                    work = false;
                    break;
                default:
                    System.out.println("Invalid operation");
                }
        }
        /*
    a. Create and host a new meeting (you must know at least one friend who will attend your event):
createMeeting() is invoked. It asks the user the date, and the name of the meeting. The user who
has logged in will be the host of the meeting. Then, the Meeting instance is created, added to the
current user’s iOrganize list by invoking organizeMeeting(), and added to the allMeetings
list of TestClass.
Returns to the main menu.
     */
    }
}