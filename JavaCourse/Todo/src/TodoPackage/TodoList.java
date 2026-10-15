package TodoPackage;
import java.util.ArrayList;
import java.util.Scanner;
public class TodoList {


    ArrayList<Item> todoItems = new ArrayList<>(); //Dynamically allocating list that will hold the items as they grow and shrink
    Scanner sc = new Scanner(System.in); //Takes input


    //public
    public void showList(){
        System.out.println("Total: " + todoItems.size());
        if(todoItems.isEmpty())
            return;
        for (int i = 1; i - 1 < todoItems.size(); i++){
            System.out.println(i + ". " + returnItemData(i - 1) );
        }
    }

    public void addItem(){
        Item newItem = new Item();

        System.out.print("Add text: ");
        newItem.text = sc.nextLine();

        System.out.print("Add Duedate(dd/mm/year): ");
        newItem.duedate = sc.nextLine();

        todoItems.add(newItem);
    }
    public void removeItem(int index){
        if(isValidIndex(index))
            todoItems.remove(index);
        else
            System.out.println("Invalid index");
    }
    public void changeState(){
        System.out.println("Index: ");
        int index = sc.nextInt();
        --index;
        if(isValidIndex(index))
        {
            System.out.println("true/false");
            Boolean newState = sc.nextBoolean();
            todoItems.get(index).state = newState;
        }

    }

    public int totalItems(){
        return todoItems.size();
    }
    //private
    private String returnItemData(int index){
        return  todoItems.get(index).text + " | " +
                todoItems.get(index).duedate + " | " +
                todoItems.get(index).state;
    }

    private boolean isValidIndex(int index)
    {
        return index >= 0 && index <= todoItems.size();
    }

}
