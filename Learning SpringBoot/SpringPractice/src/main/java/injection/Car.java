package injection;

public class Car {
    private Specification specs;

    public Car(Specification specs){
        this.specs = specs;
    }
    public void displayDetails(){
        System.out.println("Car details: " + specs.toString());
    }
}
