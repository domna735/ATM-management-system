// BalanceInquiry.java
// Represents a balance inquiry ATM transaction

public class BalanceInquiry extends Transaction
{  
   private double totalInterest; // new edit by Simon // instance variable for interest rate
   private int chequeLimit;   // new edit by Simon // instance variable for cheque limit
   
   // BalanceInquiry constructor
   public BalanceInquiry( int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase )
   {
      super( userAccountNumber, atmScreen, atmBankDatabase );
   } // end BalanceInquiry constructor

   // performs the transaction
   public void execute()
   {
      // get references to bank database and screen
      BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();

      // get the available balance for the account involved
      double availableBalance = 
         bankDatabase.getAvailableBalance( getAccountNumber() );

      // get the total balance for the account involved
      double totalBalance = 
         bankDatabase.getTotalBalance( getAccountNumber() );

      // new edit by Simon
      if (bankDatabase.flagSavingAccount(getAccountNumber()) == 1)   // check if the specific account is a Saving Account
      {
         totalInterest = 
            bankDatabase.getInterest( getAccountNumber() );    // get the interest rate for the account involved
      
      } // end if Saving

      // new edit by Simon
      if (bankDatabase.flagChequeAccount(getAccountNumber()) == 1)   // check if the specific account is a Cheque Account
      {
         chequeLimit = bankDatabase.getChequeLimit( getAccountNumber());   // get the Cheque limit
      } // end if Cheque
      
      // display the balance information on the screen
      screen.displayMessageLine( "\nBalance Information:" );
      screen.displayMessage( " - Available balance: " ); 
      screen.displayDollarAmount( availableBalance );
      screen.displayMessage( "\n - Total balance:     " );
      screen.displayDollarAmount( totalBalance );

      //new edit by Simon
      if (bankDatabase.flagSavingAccount(getAccountNumber()) == 1)   // check if the specific account is a Saving Account
      {
         screen.displayMessage( "\n - Interest Rate:     " );  // new edit by Simon
         screen.displayInterestRate( totalInterest );                  // new edit by Simon
      }  // end if

      // new edit by Simon
      if (bankDatabase.flagChequeAccount(getAccountNumber()) == 1)   // check if the specific account is a Cheque Account
      {
         screen.displayMessage( "\n - Cheque Limitation: " );  // new edit by Simon
         screen.displayChequeLimit( chequeLimit );                  // new edit by Simon
      }  // end if

      screen.displayMessageLine( "" );
   } // end method execute
} // end class BalanceInquiry



/**************************************************************************
 * (C) Copyright 1992-2007 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/