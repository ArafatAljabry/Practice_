package setter;

public class Car {
    private Specification specs;

    // Setter injection uses default constructor
    /*public Car(Specification specs){
        this.specs = specs;
    }*/
    public void displayDetails(){
        System.out.println("Car details: " + specs.toString());
    }

    public void setSpecs(Specification specs) {
        this.specs = specs;
    }
}
