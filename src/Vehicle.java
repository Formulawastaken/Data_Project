import java.time.*;
import java.util.Random;
public class Vehicle {
    private int id;
    private String type;
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private String place;
    private User owner; //TODO
    private double price; //Added a way to give vehicles a price (in credits).
    private double fracTime;//Price for time after first hour;
    //constructor
    public Vehicle(String type, String description, String startTime, String endTime, String place){
        Random random = new Random();
        this.id = random.nextInt(1000); //setting a random integer between 0-1000 for unique id
        this.type=type;
        this.description=description;
        String[] startTimeSplit=startTime.split(":"); //splits the start time into hour and minutes
        String[] endTimeSplit=endTime.split(":"); //splits the end time into hour and minutes
        this.startTime = LocalTime.of(Integer.parseInt(startTimeSplit[0]), Integer.parseInt(startTimeSplit[1]));  
        this.endTime = LocalTime.of(Integer.parseInt(endTimeSplit[0]), Integer.parseInt(endTimeSplit[1]));
        this.place=place;
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

    //methods
    
}
