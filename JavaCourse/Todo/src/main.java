import TodoPackage.*;
import java.util.Scanner;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        TodoList list = new TodoList();

        while(isRunning){
            System.out.println("Todolist: 1.ShowList - 2.Add - 3.Remove - 4.Complete item - 5.Exit ");
            list.showList();
            char input = sc.next().charAt(0);
            switch(input)
            {
                case '1':
                    list.showList();
                    break;
                case '2':
                    list.addItem();
                    break;
                case '3':
                    System.out.print("Insert index 0-" + list.totalItems());
                    int index = sc.nextInt();
                    list.removeItem(-- index);
                    break;
                case('4'):
                    list.changeState();
                    break;
                case('5'):
                    isRunning = false;
                default:
                    break;
            }
        }
    }
