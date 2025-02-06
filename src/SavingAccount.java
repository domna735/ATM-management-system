//new edit by Simon
public class SavingAccount extends Account 
{
    private double interestRate = 0.001;    // instance variable for the anum interest rate with default value 0.1%

    public SavingAccount ( int accountNo, int pin,                              // SavingAccount constructor
    double availableBalance, double totalBalance, double intrate ) 
    {
        
        super(accountNo, pin, availableBalance, totalBalance);      // calling the superclass constructor
        
        setInterestRate(intrate);   // calling setInterestRate method within this class to initialize/modify the interestRate value
   
    }   // end of SavingAccpunt Constructor

    public SavingAccount ( int accountNo, int pin,                              // SavingAccount constructor
    double availableBalance, double totalBalance ) 
    {
        
        super(accountNo, pin, availableBalance, totalBalance);      // calling the superclass constructor
        
        setInterestRate(interestRate);   // calling setInterestRate method within this class to initialize/modify the interestRate value

    
    }

    public double getInterestRate()     // return interestRate
    {
        return interestRate;
    } // end of method getInterestRate

    public void setInterestRate(double rate)     // set InterestRate
    {
        interestRate = rate;
    } // end of method setInterestRate

} // end class SavingAccount
