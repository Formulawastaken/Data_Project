import java.util.*;

public class User {
    public static Set<User> users = new HashSet<>(); //current registered users
	private String name;
	private String email;
	private String type;
	private String numEst;//i changed these to strings (they were int) since were not gonna be doing any operations with them<--
	private String numTel;//i changed these to strings (they were int) since were not gonna be doing any operations with them<--
    private int id; //i think it should'nt be static, just make a getter
    private static int lastID; //<-- Stores the last used id value to keep incrementing 
    private int balance;//to keep track of each users balance
    public static boolean hasReservation = false; //Used to check if the user has a reservation when trying to make one.
	
	public User(String name,String email,String type,String numEst,String numTel,int balance) {
		this.name = name;
		this.email = email;
		this.type = type;
		this.numEst = numEst;
		this.numTel = numTel;
        this.id = lastID++; //Assigns the id incrementing from the last assigned id, should keep everyones id unique even if a user is deleted.
        this.balance = balance;
        users.add(this);
	}
	
	//setters
    public void setName(String name){
        this.name = name;
    }
	public void setEmail(String email){
        this.email = email;
    }
    public void setType(String type){
        if(type == "Owner" || type == "Client" || type == "Both"){
            this.type = type;
        }
        else{
            System.out.println("Not a valid type for a user");//made an error message <--
        }
    }
    public void setStudentNumber(String numEst){
        this.numEst = numEst;
    }
    public void setPhoneNumber(String numTel){
        this.numTel = numTel;
    }
    public void setBalance(int balance){
        if(balance > 0){
            this.balance = balance;
        } else {
            System.out.println("Balance cannot be less than 0");
        }
    }
    //getters
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getType(){
        return this.type;
    }
    public String getStudentNumber(){
        return this.numEst;
    }
    public String getPhoneNumber(){
        return this.numTel;
    }
    public int getId(){ //made a getter for id's :) <--
        return this.id;
    }
    public int getBalance(){
        return this.balance;
    }

    //methods
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
    public boolean hasReservation(){//returns true or false if the user has a reservation.
        return hasReservation;
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
}