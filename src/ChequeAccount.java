// new edit by Simon
public class ChequeAccount extends Account
{
    private int limitPerCheque = 10000; // instance variable for Cheque limit

    public ChequeAccount ( int accountNo, int pin,                  // SavingAccount constructor
    double availableBalance, double totalBalance ) 
    {
        
        super(accountNo, pin, availableBalance, totalBalance);      // calling the superclass constructor
        
        setLimit( limitPerCheque );  // calling setInterestRate method within this class to initialize/modify the interestRate value
    
    }
    
    public ChequeAccount ( int accountNo, int pin,                  // SavingAccount constructor
    double availableBalance, double totalBalance, int limit ) 
    {
        
        super(accountNo, pin, availableBalance, totalBalance);      // calling the superclass constructor
        
        setLimit( limit );  // calling setInterestRate method within this class to initialize/modify the interestRate value
    
    }   // end of SavingAccpunt Constructor

    public void setLimit( int limit )   // set the value of cheque limit and throw an exception if the limit is less than $5000
    {
        if ( limit > 0)
            limitPerCheque = limit;
        else
            throw new IllegalArgumentException( "The Cheque limit should not be zero or negative.\n");
    } // end of method setLimit

    public int getLimit()   // return the limitPerCheque
    {
        return limitPerCheque;
    } // end of method getLimit
} // end class ChequeAccount