import java.util.Random;

public class User {
	private String name;
	private String email;
	private String type;
	private int numEst;
	private int numTel;
    private static int id;
	
	public User(String name,String email,String type,int numEst,int numTel) {
        Random random = new Random();
		this.name = name;
		this.email = email;
		this.type = type;
		this.numEst = numEst;
		this.numTel = numTel;
        //setting a random integer between 0-1000 for unique id
        this.id = random.nextInt(1000);
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
    }
    public void setStudentNumber(int numEst){
        this.numEst = numEst;
    }
    public void setPhoneNumber(int numTel){
        this.numTel = numTel;
    }
    //getters
}