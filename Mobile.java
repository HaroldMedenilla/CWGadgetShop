     
public class Mobile extends Gadget
{
    private int callCredit;
    
    public Mobile(String model, double price, int weight, String size, int callCredit)
    {
       super(model, price, weight, size);
       this.callCredit=callCredit;
    }
    
    public int getCallCredit()
    {
        
        return callCredit;
    }
    
    public void addCredit(int amount)
    {
        if(amount > 0)
        {
            callCredit += amount;
        }
        else
        {
            System.out.println("Must add sufficient amount of credit.");  
        }    
    }
    
    public void makeCall(String number, int duration)
    {
        if(duration <= callCredit)
        {
            System.out.println("You are now calling: " + number + " for  " + duration + "minutes.");
            callCredit = callCredit - duration;
        }
        
        else
        {
            System.out.println("You do not have enough credit to call this number. Please add a sufficient amount of credit if you would like to make this call.");
        }
    }
    
    public void display()
    {
        super.display();
        System.out.println("Call Credits left: " +callCredit);
    }
    
}
