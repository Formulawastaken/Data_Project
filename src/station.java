import java.time.*;
import java.util.*;
//should everything be static? i think only the user list should be static bc everything else is proprietary to each station no? 
public class station {
    public static Set<User> users = new HashSet<>(); //current registered users
    public static Set<Vehicle> availableVehicles = new HashSet<>(); //available vehicles for clients
    public static Map<User,Vehicle> reservations = new HashMap<>(); //pairs clients with the veichle they reserved 
    public static Queue<User> waitingList = new LinkedList<>();//waiting queue for clients when no vehicles are available

    //user cmds
    public static void addUser(String name,String email,String type,String numEst,String numTel){
        User newUser = new User(name, email, type, numEst, numTel);
        users.add(newUser);
    }
    public static void removeUser(int id){
        if(!users.isEmpty()){ //checks if there are any users registered
        for(User user:users){
            if (user.getId()==id){
                users.remove(user);
                break;
            }
        }
        }
        else{
            System.out.println("There are currently no users to remove, please add one first.");
        }
    }
    public static void modifyUser(int id, String command){ //the command is what part of the user they would like to change
        User user = null;
        Scanner scanner = new Scanner(System.in);
        if(!users.isEmpty()){ //checks if there are any users registered
            for(User s:users){
                if (s.getId()==id){
                    user=s;
                    break;
                }
            }
        }
        else{
            System.out.println("There are currently no users to modify, please add one first.");
            scanner.close();
            return;
        }
        if(command.equals("Name")){
            System.out.println("Input the new name");
            user.setName(scanner.nextLine());  // Note: Sometimes nextLine() can cause troubles.
        }
        else if(command.equals("Email")){
            System.out.println("Input the new Email");//Note: Is it neccesary to check if its a upr email?
            user.setEmail(scanner.nextLine());
        }
        else if(command.equals("Type")){
            System.out.println("Input the new user type");
            user.setType(scanner.nextLine());
        }
        else if(command.equals("Student number")){
            System.out.println("Input new student number for the user");
            user.setStudentNumber(scanner.nextLine());
        }
        else if(command.equals("Telephone number")){
            System.out.println("Input the new telephone number for the user");
        }
        scanner.close();
    }

    //vehicle cmds
    public static void addVehicle(String type, String description, String startTime, String endTime, String place ){
        availableVehicles.add(new Vehicle(type,description,startTime,endTime, place));
    }
    public static void removeVehicle(int id){
        if(!availableVehicles.isEmpty()){
            Vehicle vehicle=null;
            for(Vehicle v:availableVehicles){
                if(v.getId()==id){
                    vehicle=v;
                    break;
                } 
            }
            availableVehicles.remove(vehicle);
        }
        else{
            System.out.println("There are currently no users to modify, please add one first.");
        }

    }
    public static void modifyVehicle(int id, String command){
        if(!availableVehicles.isEmpty()){
            Vehicle vehicle=null;
            Scanner scanner=new Scanner(System.in);
            for(Vehicle v:availableVehicles){
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
