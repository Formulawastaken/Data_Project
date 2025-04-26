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
    public static ArrayList<Double> transactions = new ArrayList<>();//keeps track of total transactions in the programm
    public static Queue<User> waitingList = new LinkedList<>();//waiting queue for clients when no vehicles are available
    public static boolean hasAvailable = false;

    
//reservation cmds(TODO)
    public static void addReservation(User user){
        for (Vehicle v : Vehicle.vehicles){
            if(v.isAvailable()){// checks if the list has atleast one available vehicle;
                hasAvailable = true;
                break;
            }
        }
        if(user.hasReservation()){
            System.out.println("You have a current reservation!");
            return;
        }
        if(!hasAvailable && !user.hasReservation()){ //if there are no available vehicles offer a wating list.
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
        }                      // v this should be run in the app no? v
        modifyiftimePassed(); // changes the availability for all vehicles whos time frame for renting has expired or hasnt been met yet 
        Map<Integer,Vehicle> availablevehiclesMap = new HashMap<>(); //makes a list of the vehicles and gives them a number if they are available
        for(Vehicle vehicle: Vehicle.vehicles){
            if(vehicle.isAvailable()){ //only adds if the vehicle is available;
                int vehicleNum=1;
                vehicle.showVehicle(vehicleNum);
                availablevehiclesMap.put(vehicleNum, vehicle);
                vehicleNum++;
            }
        }
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of the vehicle you would like to choose:");
        int selected = scanner.nextInt();


        LocalTime endTime = null;
        while(true){
            System.out.println("From 1-6 hours, how many hours would you like to rent the vehcile? \nType a number:");
            Integer hours = scanner.nextInt();
            LocalTime time = LocalTime.now().plusHours(hours);
            if(availablevehiclesMap.get(selected).getEndTime().isBefore(time)){
                System.out.println("The vehicle is not available for that long, please lower the hours.");
                continue;
            }
            endTime = time;
            break;
        }
        long hoursRented = Duration.between(LocalTime.now(),endTime).toHours();
        Double transaction = ((hoursRented-1)*(availablevehiclesMap.get(selected).getFracTime()))+availablevehiclesMap.get(selected).getPrice();//Note: Made by kemu
        transactions.add(transaction);//adds transaction Note: Made by kemu
        user.addBalance(-transaction);//takes away from the balance of the user what he payed for the reservation of the vehicle
        availablevehiclesMap.get(selected).getOwner().addBalance(transaction);// adds the income made by the owner of the vehicle

        reservations.put(user, availablevehiclesMap.get(selected));
        System.out.println("You have reserved this vehicle!");
        System.out.println("Your total is: $"+transaction); //Note: Made by kemu
        reservationtimes.put(availablevehiclesMap.get(selected),endTime); //map keeping the users with reservations and whow many hours they rented for 
        Vehicle.vehicles.remove(availablevehiclesMap.get(selected));
        scanner.close();


    }
    public static void removeReservation(){


    }
    public static void modifyReservation(){
        
    }
    public static void modifyiftimePassed(){//Checks if the end time is different to the current time and removes it from available vehicles if so
        LocalTime now = LocalTime.now();
        for(Vehicle vehicle : Vehicle.vehicles){
            if(now.isBefore(vehicle.getStartTime()) ||now.isAfter(vehicle.getEndTime())){
                vehicle.setAvailable(false); //Changes the isAvailable Value to False
            }
        }
    }
    public static void returnVehicle(){ //checks if the reservation is over then returns to available vehicles
        for(Map.Entry<Vehicle, LocalTime> entry : reservationtimes.entrySet()){
            if(LocalTime.now().isAfter(entry.getValue())){
                reservationtimes.remove(entry.getKey()); //removes from reservatin times
                for(Map.Entry<User, Vehicle> entry2 : reservations.entrySet()){
                    if(entry2.getValue().equals(entry.getKey())){
                        entry2.getValue().setAvailable(true); // puts back in available vehicles ***Lorenzo note: this should now instead of returning to the vehicles list modify is Available to True*** !!!FIXED!!!** 
                        reservations.remove(entry2.getKey()); //removes from reservations
                    }
                }
            }
        }
    }

}
