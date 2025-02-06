// BankDatabase.java
// Represents the bank account information database 

public class BankDatabase
{  
   private SavingAccount accounts[]; // new edit by Simon // array of SavingAccounts
   private ChequeAccount cAccount[]; // new edit by Simon // array of ChequeAccounts
   
   // no-argument BankDatabase constructor initializes accounts
   public BankDatabase()
   {  
      accounts = new SavingAccount[ 3 ]; // new edit by Simon // just 2 accounts for testing
      cAccount = new ChequeAccount[ 3 ]; // new edit by Simon
      
      // Saving Account
      accounts[ 0 ] = new SavingAccount( 12345, 54321, 1000.0, 1200.0, 0.01 );   // new edit by Simon
      accounts[ 1 ] = new SavingAccount( 98765, 56789, 200.0, 200.0, 0.1 );      // new edit by Simon
      accounts[ 2 ] = new SavingAccount( 56789, 98765, 2000.0, 2000.0, 1 );      // new edit by Simon
      
      // Cheque Account
      // new edit by Simon add 1 Cheque Account
      cAccount[ 0 ] = new ChequeAccount(34567, 76543, 30000.0, 30000.0, 10000);   // new edit by Simon
      cAccount[ 1 ] = new ChequeAccount(45678, 87654, 70000.0, 71000.0, 6000);
      cAccount[ 2 ] = new ChequeAccount(23456, 65432, 20000.0, 21000.0);                // new edit by Simon
   } // end no-argument BankDatabase constructor
   
   // retrieve Account object containing specified account number
   public Account getAccount( int accountNumber )
   {
      // loop through accounts searching for matching account number
      for ( Account currentAccount : accounts )
      {
         // return current account if match found
         if ( currentAccount.getAccountNumber() == accountNumber )
            return currentAccount;
      } // end for
      // new edit by Simon
      for ( Account currentAccount : cAccount ) // read the cheque account from the cAccount array in bankdatabase
      {
         // return current account if match found
         if ( currentAccount.getAccountNumber() == accountNumber )
            return currentAccount;
      } // end for

      return null; // if no matching account was found, return null
   } // end method getAccount

   // new edit by Simon
   // retrieve SavingAccount objedct
   public SavingAccount getSavingAccount( int accountNumber )
   {
      // loop through accounts searching for matching account number
      for ( SavingAccount currentAccount : accounts )
      {
         // return current account if match found
         if ( currentAccount.getAccountNumber() == accountNumber )
            return currentAccount;
      } // end for

      return null; // if no matching account was found, return null
   } // end method getSavingAccount

   // new edit Simon
   // retrieve ChequeAccount object
   public ChequeAccount getChequeAccount( int accountNumber )
   {
      // loop through accounts searching for matching account number
      for ( ChequeAccount currentAccount : cAccount )
      {
         // return current account if match found
         if ( currentAccount.getAccountNumber() == accountNumber )
            return currentAccount;
      } // end for

      return null; // if no matching account was found, return null
   } // end method getChequeAccount

   // determine whether user-specified account number and PIN match
   // those of an account in the database
   public boolean authenticateUser( int userAccountNumber, int userPIN )
   {
      // attempt to retrieve the account with the account number
      Account userAccount = getAccount( userAccountNumber );

      // if account exists, return result of Account method validatePIN
      if ( userAccount != null )
         return userAccount.validatePIN( userPIN );
      else
         return false; // account number not found, so return false
   } // end method authenticateUser

   // return available balance of Account with specified account number
   public double getAvailableBalance( int userAccountNumber )
   {
      return getAccount( userAccountNumber ).getAvailableBalance();
   } // end method getAvailableBalance

   // return total balance of Account with specified account number
   public double getTotalBalance( int userAccountNumber )
   {
      return getAccount( userAccountNumber ).getTotalBalance();
   } // end method getTotalBalance

   // new edit by Simon
   // return interest rate of Saving Account with specified account number
   public double getInterest( int userAccountNumber )
   {
      return getSavingAccount( userAccountNumber ).getInterestRate();
   } // end method getInterest

   // new edit by Simon
   public int getChequeLimit( int userAccountNumber ) // return Cheque Limit for the specific account
   {
      return getChequeAccount( userAccountNumber ).getLimit();
   } // end method getChequeLimit

   // credit an amount to Account with specified account number
   public void credit( int userAccountNumber, double amount )
   {
      getAccount( userAccountNumber ).credit( amount );
   } // end method credit

   // debit an amount from of Account with specified account number
   public void debit( int userAccountNumber, double amount )
   {
      getAccount( userAccountNumber ).debit( amount );
   } // end method debit

   // new edit by Simon
   public int flagSavingAccount( int accountNumber)   // flag to determine whether the specific account is a saving account
    {
        for ( SavingAccount currentAccount : accounts )
      {
         // return 1 if match found
         if ( currentAccount.getAccountNumber() == accountNumber )
            return 1;
      } // end for
      
         return 0; // return 0 if match not found
    } // end method flagSavingAccount

   // new edit by Simon
   public int flagChequeAccount( int accountNumber)   // flag to determine whether the specific account is a cheque account
    {
        for ( ChequeAccount currentAccount : cAccount )
      {
         // return 1 if match found
         if ( currentAccount.getAccountNumber() == accountNumber )
            return 1;
      } // end for
      
         return 0; // return 0 if match not found
    } // end method flagChequeAccount

    // new edit by Don
    public boolean isAccountExists(int accountNumber) {
      for (Account account : accounts) {
          if (account.getAccountNumber() == accountNumber) {
              return true; // Account exists
          }
      }
      // new edit by Don
      for (Account account : cAccount) {
          if (account.getAccountNumber() == accountNumber) {
              return true; // Account exists
          }
      }
      return false; // Account not found
  }// end method isAccountExists
} // end class BankDatabase



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