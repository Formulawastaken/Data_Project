import java.util.Random;

public class User {
	private String name;
	private String email;
	private String type;
	private String numEst;//i changed these to strings (they were int) since were not gonna be doing any operations with them<--
	private String numTel;//i changed these to strings (they were int) since were not gonna be doing any operations with them<--
    private int id; //i think it should'nt be static, just make a getter<--
    private int balance;//to keep track of each users balance 
	
	public User(String name,String email,String type,String numEst,String numTel,int balance) {
        Random random = new Random();
		this.name = name;
		this.email = email;
		this.type = type;
		this.numEst = numEst;
		this.numTel = numTel;
        //setting a random integer between 0-1000 for unique id
        this.id = random.nextInt(1000);
        this.balance = balance;
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
}