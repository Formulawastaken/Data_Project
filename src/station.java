import java.time.LocalTime;
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
    public static Map<User, LocalTime> reservationtimes = new HashMap<>(); // pairs the user to the time its reserved the vehicles
    public static Queue<User> waitingList = new LinkedList<>();//waiting queue for clients when no vehicles are available

    
//reservation cmds(TODO)
    public static void addReservation(User user){
        if(user.hasReservation()){
            System.out.println("You have a current reservation!");
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
        reservations.put(user, vehicleMap.get(selected));
        reservationtimes.put(user,LocalTime.now()); //map keeping the users with reservations and when they reserved a vehicle 
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
                Vehicle.availableVehicles.remove(vehicle);
            }
        }
    }

}
