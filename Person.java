import java.util.ArrayList;
import java.util.*;
public class Person {
    /*
    Attributes:
§ name: set during construction,
§ id: must be unique
§ myMeetings: the list of meetings of the person that s/he plans to attend
§ iOrganize: the list of meetings organized by a person
     */
    private static Scanner scn;
    private String name;
    private int id = 0;
    private ArrayList<Meeting> mymeetings;
    private ArrayList<Meeting> iOrganize;
    private static int count;
    private ArrayList <Person> users;
    /*
    § Constructor(s): requires name to be constructed.
§ Required accessor/mutators
§ equals(): override this method to check whether two Person instances are the
same or not, i.e., same persons must have the name and id fields..
§ addMeeting ( ): takes a Meeting instance, adds it to the myMeetings list, only if
the person doesn’t have another meeting at the same time. If the meeting instance is
successfully added to the list, it returns true.
§ removeMeeting (): takes a Meeting instance, removes the instance from
myMeetings list.
§ organizeMeeting(): takes a Meeting instance, adds the instance to the
iOrganize list.
§ cancelMeeting(): used to cancel the Meeting instance which is taken as
argument, organized by the Person under inspection. The meeting to be cancelled
should be removed from all its participants’ lists.
§ displayMyMeetings(): displays the whole list of meetings’ dates, the host of the
meeting.
§ displayMyOrganizations(): displays the Meetings organized by the Person
under inspection.
§ toString(): returns important info of Person instance as String. These
information is name, id, number of meetings that a person plans to attend and
number of meetings organized by a person. It will return the string in the following
format; eg. User Ayse with ID 454 has 5 meetings to attend and 3 meetings
organized.
     */
    public Person findPerson(int id) {
        Person p = null;
        for (int i = 0; i < this.getUsers().size(); i++) {
            Person temp = this.getUsers().get(i);
            if(temp.getId() == id) {
                p = temp;
            }
        }
        return p;
    }
    public void cancelMeeting(Meeting m) {

        if(m.getHost() == this) {
            this.removeMeeting(m);
            for(int i = 0; i < m.getAttendee().size(); i++) {
                m.getAttendee().get(i).removeMeeting(m);
            }
        }
    }
    public boolean addMeeting(Meeting m) {
        for(int i = 0; i < mymeetings.size(); i++) {
            if(mymeetings.get(i).getDate().compareTo(m.getDate())) {
                return false;
            }
        }
        this.mymeetings.add(m);
        return true;
    }
    public void displayMyOrganizations() {
        for(int i = 0; i < this.getiOrganize().size(); i++) {
            Meeting m = this.getiOrganize().get(i);
            if(m != null) {
                System.out.println(m.toString());
            }
        }
    }
    public void displayMyMeetings() {
        for(int i = 0; i < this.getMymeetings().size(); i++) {
            Meeting m = this.getMymeetings().get(i);
            if(m != null) {
                System.out.println(m.toString());
            }
        }
    }
    public void organizeMeeting(Meeting m) {
        this.getiOrganize().add(m);
    }
    public void removeMeeting(Meeting m) {
        this.getMymeetings().remove(m);
    }
    public Person(String name) {
        this.name = name;
        this.id += count;

        this.mymeetings = new ArrayList<Meeting>();
        this.iOrganize = new ArrayList<Meeting>();
        this.users = new ArrayList<Person>();
        users.add(this);
        Person.count++;
    }
    public static int getCount() {
        return count;
    }
    public  int getId() {
        return this.id;
    }
    public String getName() {
        return name;
    }
    public ArrayList<Meeting> getMymeetings() {
        return mymeetings;
    }
    public ArrayList<Meeting> getiOrganize() {
        return iOrganize;
    }
    public boolean equals(Person p) {
        if(this.name.equals(p.getName())) {
            if(this.id == p.getId()) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Person> getUsers() {
        return users;
    }
}
