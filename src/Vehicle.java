import java.time.*;
import java.util.*;
public class Vehicle {
    public static Set<Vehicle> vehicles = new HashSet<>(); //list of all vehicles
    public static Set<Vehicle> toReturn = new HashSet<>();
    private int id;
    private static int lastID=0;
    private String type;
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private String place;
    private User owner; //TODO maybe?
    private double price; //Added a way to give vehicles a price (in credits).
    private double fracTime;//Price for time after first hour;
    private boolean isAvailable; // to check if a vehicle is available in the availableVehicles list or not. kemu note:Why was it static
    //constructor
    public Vehicle(String type, String description, String startTime, String endTime, String place, User owner){
        this.id = lastID++; //Assigns the id incrementing from the last assigned id, should keep everyones id unique even if a user is deleted.
        this.type=type; 
        this.description=description;
        String[] startTimeSplit=startTime.split(":"); //splits the start time into hour and minutes
        String[] endTimeSplit=endTime.split(":"); //splits the end time into hour and minutes
        this.startTime = LocalTime.of(Integer.parseInt(startTimeSplit[0]), Integer.parseInt(startTimeSplit[1]));  
        this.endTime = LocalTime.of(Integer.parseInt(endTimeSplit[0]), Integer.parseInt(endTimeSplit[1]));
        this.place=place;
        this.owner=owner;
        this.isAvailable = true;
        switch(type){
            case "Scooter":
            this.price=2;
            this.fracTime=1;
            case "scooter":
            this.price=2;
            this.fracTime=1;
            case "Bike":
            this.price=3;
            this.fracTime=2;
            case "bike":
            this.price=3;
            this.fracTime=2;
            case "Skateboard":
            this.price=1;
            this.fracTime=0.5;
            case "skateboard":
            this.price=1;
            this.fracTime=0.5;
        }
    }
    //setters 
    public void setDescription(String description){
        this.description=description;
    }
    public void setType(String type){
        this.type=type;
    }
    public void setStartTime(String startTime){
        String[] startTimeSplit=startTime.split(":");
        this.startTime = LocalTime.of(Integer.parseInt(startTimeSplit[0]), Integer.parseInt(startTimeSplit[1]));
    }
    public void setEndTime(String endTime){
        String[] endTimeSplit=endTime.split(":");
        this.endTime = LocalTime.of(Integer.parseInt(endTimeSplit[0]), Integer.parseInt(endTimeSplit[1]));
    }
    public void setPlace(String place){
        this.place=place;
    }
    public void setAvailable(boolean availability){
        if(availability){
           this.isAvailable = false;
        }else{ 
            this.isAvailable = true;
        }
    }

    
    //getters
    public String getDescription(){
        return this.description;
    }
    public int getId(){
        return this.id;
    }
    public LocalTime getStartTime(){
        return this.startTime;
    }
    public LocalTime getEndTime(){
        return this.endTime;
    }
    public String getPlace(){
        return this.place;
    }
    public double getPrice(){
        return this.price;
    }
    public double getFracTime(){
        return this.fracTime;
    }
    public boolean isAvailable(){
        return this.isAvailable;
    }
    public User getOwner(){
        return this.owner;
    }

    //methods
    

    public void showVehicle (int num){
        System.out.println("Vehicle ["+num+"]");
        System.out.println("Type: "+type);
        System.out.println("Description: "+description);
        System.out.println("Cost: "+price);
    }

    public static void addVehicle(String type, String description, String startTime, String endTime, String place, User owner ){
        vehicles.add(new Vehicle(type,description,startTime,endTime,place,owner ));
    }
    public static void removeVehicle(int id){
        if(!vehicles.isEmpty()){
            Vehicle vehicle=null;
            for(Vehicle v:vehicles){
                if(v.getId()==id){
                    vehicle=v;
                    break;
                } 
            }
            vehicles.remove(vehicle);
        }
        else{
            System.out.println("There are currently no users to modify, please add one first.");
        }

    }

    public static void modifyVehicle(int id, String command){
        if(!vehicles.isEmpty()){
            Vehicle vehicle=null;
            Scanner scanner=new Scanner(System.in);
            for(Vehicle v:vehicles){
                if(v.getId()==id){
                    vehicle=v;
                    break;
                }
            }
            switch(command){

                case "Type":
                System.out.println("Input new type for the vehicle");
                vehicle.setType(scanner.nextLine());
                scanner.close();

                case "Description":

                System.out.println("Input new description for the vehicle");
                vehicle.setDescription(scanner.nextLine());
                scanner.close();

                case "Place":

                System.out.println("Input new place for the vehicle");
                vehicle.setDescription(scanner.nextLine());
                scanner.close();
                //TODO start and end time modifiers, should owner be able to be modified?
            }
        }
        else{
            System.out.println("There are currently no vehicles to modify, please add one first.");
        }
    }
}
