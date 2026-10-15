package TodoPackage;

public class Item {
    String text;
    String duedate;
    Boolean state = false;


    public void setText(String newText){text = newText;}
    public void setState(Boolean newState){state = newState;}
    public void setDuedate(String newDuedate){ duedate = newDuedate;}
}
