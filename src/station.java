import java.time.*;
import java.util.*;

//should everything be static? i think only the user list should be static bc everything else is proprietary to each station no? 
// ^ yesss, sorry i tought the sation class managed all the info from the programm
public class station {
    public static boolean isStefaniFull = false;//max is 40
    public static int stefaniCapacity = 0;
    public static boolean isCentroEstudiantesFull = false; //max is 90
    public static int centroEstudiantesCapacity = 0;
    public static boolean isBiolFull = false;//max is 35
    public static int biolCapacity = 0;
    public static boolean isQuimFull = false;//max is 45
    public static int quimCapacity = 0;
    public static boolean isAdemFull = false;//max is 45
    public static int ademCapacity = 0;
    public static Map<User,Vehicle> reservations = new HashMap<>(); //pairs clients with the veichle they reserved 
    public static Map<Vehicle, LocalTime> reservationtimes = new HashMap<>(); // pairs the user to the time its reserved the vehicles
    public static Queue<User> waitingList = new LinkedList<>();//waiting queue for clients when no vehicles are available

    
//reservation cmds(TODO)
    public static void addReservation(User user){
        if(user.hasReservation()){
            System.out.println("You have a current reservation!");
            return;
        }
        else if(Vehicle.availableVehicles.isEmpty() && !user.hasReservation()){ //if there are no available vehicles offer a wating list.
            Scanner scanner = new Scanner(System.in);
            System.out.println("There are currently no available vehicles. \nWould you like to enter a waiting queue? Type [Yes] or [No]");
            String command = scanner.nextLine();
            if(command.equals("Yes")){
                waitingList.add(user);
                System.out.println("You have entered a queue.");
            }
            else if(command.equals("No")){
                System.out.println("You have not entered a queue.");
            }
            scanner.close();
            return;
        }
        removeiftimePassed();
        Map<Integer,Vehicle> vehicleMap = new HashMap<>(); //makes a list of the vehicles and gives them a number
        for(Vehicle vehicle:Vehicle.availableVehicles){
            int vehicleNum=1;
            vehicle.showVehicle(vehicleNum);
            vehicleMap.put(vehicleNum, vehicle);
            vehicleNum++;
        }
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of the vehicle you would like to choose:");
        int selected = scanner.nextInt();


        LocalTime endTime = null;
        while(true){
            System.out.println("From 1-6 hours, how many hours would you like to rent the vehcile? \nType a number:");
            Integer hours = scanner.nextInt();
            LocalTime time = LocalTime.now().plusHours(hours);
            if(vehicleMap.get(selected).getEndTime().isBefore(time)){
                System.out.println("The vehicle is not available for that long, please lower the hours.");
                continue;
            }
            endTime = time;
            break;
        }
        long hoursRented = Duration.between(LocalTime.now(),endTime).toHours();


        reservations.put(user, vehicleMap.get(selected));
        System.out.println("You have reserved this vehicle!");
        System.out.println("Your total is: $"+((hoursRented-1)*(vehicleMap.get(selected).getFracTime()))+vehicleMap.get(selected).getPrice());
        reservationtimes.put(vehicleMap.get(selected),endTime); //map keeping the users with reservations and whow many hours they rented for 
        Vehicle.availableVehicles.remove(vehicleMap.get(selected));
        scanner.close();


    }
    public static void removeReservation(){


    }
    public static void modifyReservation(){
        
    }
    public static void removeiftimePassed(){//Checks if the end time is different to the current time and removes it from available vehicles if so
        LocalTime now = LocalTime.now();
        for(Vehicle vehicle : Vehicle.availableVehicles){
            if(now.isBefore(vehicle.getStartTime()) ||now.isAfter(vehicle.getEndTime())){
                Vehicle.availableVehicles.remove(vehicle); //what if it has to come back the next day
            }
        }
    }
    public static void returnVehicle(){ //checks if the reservation is over then returns to available vehicles
        for(Map.Entry<Vehicle, LocalTime> entry : reservationtimes.entrySet()){
            if(LocalTime.now().isAfter(entry.getValue())){
                reservationtimes.remove(entry.getKey()); //removes from reservatino times
                for(Map.Entry<User, Vehicle> entry2 : reservations.entrySet()){
                    if(entry2.getValue().equals(entry.getKey())){
                        Vehicle.availableVehicles.add(entry2.getValue()); // puts back in available vehicles
                        reservations.remove(entry2.getKey()); //removes from reservations
                    }
                }
            }
        }
    }

}
