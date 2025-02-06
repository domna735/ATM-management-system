public class Transfer extends Transaction {
    // create by Don 
    private Keypad keypad;
    private int sourceAccountNumber;
    private int targetAccountNumber;
    private double amount;

    public Transfer(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
    }

    @Override
    public void execute() {
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        sourceAccountNumber = getAccountNumber();
        boolean accountok = false;
        boolean amountok = false;

        do{
         // Prompt the user about the source account number
        screen.displayMessage("\nThe source account number: "+ getAccountNumber() );// cout the source account number

        // Prompt the user to enter the target account number
        screen.displayMessage("\nEnter the target account number: ");// ask for the target account number massage
        targetAccountNumber = keypad.getInput(); // int input value
        
        // Check if the target account number is same as the source account number
        if (targetAccountNumber==sourceAccountNumber) {
            screen.displayMessage("You can not transfer to same account");
        }
         // Check if both target accounts exist
        else if (!bankDatabase.isAccountExists(targetAccountNumber)) {
            screen.displayMessageLine("Target account does not exist.");
        }
        else{
            accountok=true;
        }
        }while(!accountok);

        do {
            // Prompt the user to enter the transfer amount
            double availableBalance = bankDatabase.getAvailableBalance(sourceAccountNumber);
            screen.displayMessage("\nTransfer to Account Number: " + targetAccountNumber + ":");
            screen.displayMessage("\n - Available balance: HK");
            screen.displayDollarAmount(availableBalance);
            // Ask for the transfer amount
            screen.displayMessage("\n - Enter the transfer amount: HK$");
            amount = keypad.getInput_2(); // Get the transfer amount
        
            if (amount <= 0) {
                screen.displayMessageLine("Invalid transfer amount. Please enter a valid amount.");
            } else if (bankDatabase.flagChequeAccount(sourceAccountNumber) == 1) {
                // Check if it's a cheque account and within the limit
                int chequeLimit = bankDatabase.getChequeLimit(sourceAccountNumber);
                if (amount > chequeLimit) {
                    screen.displayMessageLine("Transfer amount exceeds cheque limit.");
                } else {
                    amountok = true;
                }
            } else if (availableBalance < amount) {
                // Check if there's not enough money in the source account
                screen.displayMessageLine("Insufficient funds in the source account.");
            } else if (bankDatabase.flagSavingAccount(sourceAccountNumber) == 1) {
                // Check if it's a saving account and has sufficient balance
                if (amount > availableBalance) {
                    screen.displayMessageLine("Insufficient funds in the source account.");
                } else {
                    amountok = true;
                }
            } else {
                // If none of the conditions apply, consider the amount as valid
                amountok = true;
            }
        } while (!amountok);

        // ask to confirm the transfer
        screen.displayMessageLine("\nConfirmation:");
        screen.displayMessage("Confirm to transfer HK");
        screen.displayDollarAmount(amount);
        screen.displayMessage(" to Account: [" + targetAccountNumber + "]? (Yes[1]/No[0])");
        int confirm = keypad.getInput();

        // check user confirm or not 
        while (confirm != 0 && confirm != 1) {
            screen.displayMessageLine("Invalid selection. Try again.");
            screen.displayMessage("Confirm to transfer HK");
            screen.displayDollarAmount(amount);
            screen.displayMessage(" to Account: [" + targetAccountNumber + "]? (Yes[1]/No[0])");
            confirm = keypad.getInput();
        }
        // cancel transaction because user not confirm transfer
        if (confirm == 0) {
            screen.displayMessageLine("Transaction canceled.");
            return;
        }

        // Perform the transfer by debiting the source account and crediting the target account
        bankDatabase.debit(sourceAccountNumber, amount);
        bankDatabase.credit(targetAccountNumber, amount);

        // Display a successful transfer message
        screen.displayMessageLine("Transfer of HKD $" + amount + " from account " + sourceAccountNumber +
                " to account " + targetAccountNumber + " is successful.");
    
    }
}

