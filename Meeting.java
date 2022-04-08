import java.util.ArrayList;
import java.util.Scanner;
public class Meeting {
    /*
    § date: the date/time of appointment, can be changed only to a later date. The type
of date is MDate
§ attendees: list of Person instances invited to the Meeting instance.
§ host: a Person instance, the owner of the Meeting instance, must be set by the
constructor, cannot be changed.
§ isOnline: if a meeting is online, it returns true; otherwise false. It must be set by
the constructor, can be changed later.
§ url: if meeting is online this will be a URL to the meeting.
§ location: if meeting is not online (face-to-face), this will keep the location as a
string such as “AMF-318”
     */
    public static Scanner scanner;
    private String name;
    private MDate date;
    private ArrayList<Person> attendees;
    private Person host;
    private boolean isOnline;
    private String url;
    private String location;
    /*
    § Constructor(s); requires the date, the host, online or not, url or location, and at least
one attendee to construct the Meeting instance. The current Meeting instance
should be added to the host’s, and the attendees’ lists as well.
§ Required accessors/mutators
§ equals(): in order for the two meeting instances to be equal, the date fields, the
hosts and the list of attendees must be the same.
§ addAttendee(): takes a Person instance, if the person is not in the list, then the
person’s addMeeting() method is invoked. If true is returned, then the person is
added to attendees.
§ removeAttendee(): takes a Person instance. If (s)he is in the attendees,
removes him/her, invokes the person’s removeMeeting(), and returns true.
§ removeAllAttendees(): removes all attendees of the event. Required if meeting
needs to be cancelled.
§ toString(): returns host, date, online or not, location, and list of attendees as
String instance.
     */
    /*
    § Constructor(s); requires the date, the host, online or not, url or location, and at least
one attendee to construct the Meeting instance. The current Meeting instance
should be added to the host’s, and the attendees’ lists as well.
     */
    public Person findAttendeeWithGivenId(int id) {
        Person p = null;
        for(int i = 0; i < getAttendee().size(); i++) {
            Person temp = getAttendee().get(i);
            if(p.getId() == id) {
                p = temp;
            }
        }
        return p;
    }
    @Override
    public String toString() {
        String str = "";
        str = "Name of the meeting = " + this.name + " Date = " + this.getDate().toString() + " Host = " + this.getHost().getName() + " is it online ? " + this.isOnline() +
                " url = " + this.getUrl() + " location = " + this.getLocation() + " attendees = " + this.showAttendee();
        return str;
    }

    public void removeAllAttendees(ArrayList<Person> attendees) {
        attendees.removeAll(attendees);
    }
    public boolean removeAttendee(Person p) {
        for(int i = 0; i < attendees.size(); i++) {
            Person temp = attendees.get(i);
            if(temp == p) {
                attendees.remove(p);
                p.removeMeeting(this);
                return true;
            }
        }
        return false;
    }
    public boolean addAttendee(Person p) {
        Person attendee = null;
        for(int i =0; i < attendees.size(); i++) {
            attendee = attendees.get(i);
            if(attendee != null) {
                if(p != attendee) {
                    attendees.add(p);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean equals(Meeting m) {
        if(this.getDate().compareTo(m.getDate())) {
            if(this.getHost() == m.getHost()) {
                return true;
            }
        }
        return false;
    }
    public Meeting(int day,int month,int year,
                   String timeZone,int hour,int minute,Person host,boolean isOnline,Person attendee,String name) {
        this.name = name;
        attendees = new ArrayList<Person>();
        attendees.add(attendee);
        this.date = new MDate(day,month,year,timeZone,hour,minute);
        scanner = new Scanner(System.in);
        this.host = host;
        this.isOnline = isOnline;
        host.organizeMeeting(this);
        for(int i = 0; i < attendees.size(); i++) {
            attendees.get(i).addMeeting(this);
        }
        if(this.isOnline) {
            System.out.println("Enter the url");
            this.url = scanner.nextLine();
            this.location = "Online";
        }
        else {
            this.url = "Face to face";
            System.out.println("What is the location of the meeting : ");
             location = scanner.nextLine();
            this.location = location;
        }
    }
    public String showAttendee() {
        String str = "";
        for(int i = 0; i < this.getAttendee().size(); i++) {
            str += (this.getAttendee().get(i).getName());
        }
        return str;
    }
    public MDate getDate() {
        return date;
    }


    public Person getHost() {
        return host;
    }

    public void setHost(Person host) {
        this.host = host;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public ArrayList<Person> getAttendee() {
        return attendees;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
