
public class Bit {

    private boolean value;
    
    public Bit(boolean value){
        //Task 3.1
    	//takes the argument that we got and saves its value
    	this.value = value;
    }

    public int toInt(){ 
        int ans = 0;
        //Task 3.2
        //returns the numbers 1,0 based on the value of the argument
        if(value == true) ans=1;
        return ans;

    }

    public String toString(){
        String ans = "";
        //Task 3.3
        //returns the numbers 1,0 as a String based on the value of the argument
        ans = toInt()+"";
        return ans;
    }
}


