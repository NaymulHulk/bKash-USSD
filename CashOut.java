import java.util.Scanner;

public class CashOut {
    Scanner console = new Scanner(System.in);
    BkashServer bkashServer = new BkashServer();

    public void printCashOutFirstMenu(){
        System.out.println("bkash\n1 From Agent\n2 From ATM\n0 Main Menu");
    }
    public void checkCashOutAmountInvalidFromAgent(int amount){
        if (amount < 50){
            System.out.println("Cash Out From Agent is too low.");
            System.exit(0);
        }
        else if (amount > 25000){
            System.out.println("Cash Out From Agent is too high");
            System.exit(0);
        }
    }
    public void cashOutAllProcedure(int secretIndex, int userNumber){

        int cashOutChoice = console.nextInt();
        int PINWhichWasInputForCashOut = 0;
        if (cashOutChoice == 2){
            System.out.println("Enter Menu PIN to request ATM Cash Out: ");
            PINWhichWasInputForCashOut = console.nextInt();
            if (bkashServer.getPinForThatNumber(secretIndex) !=  PINWhichWasInputForCashOut){
                System.out.println("Invalid Combination of Account and PIN. Please Try Again");
                System.exit(0);
            }else {
                System.out.println("Your Security Code is " + (PINWhichWasInputForCashOut*PINWhichWasInputForCashOut)
                + ". Go to a bKash enabled ATM booth within 5 minutes. Use this code on ATM screen.");
            }
        }
        else if (cashOutChoice == 1){
            boolean checkForValidAgentNumber = false;
            int cashOutAmountFromAgent = 0;
            int agentNumberForCashOut = 0;
            System.out.println("Enter Agent bKash number:  ");
            agentNumberForCashOut = console.nextInt();
            bkashServer.checkIfValidNumber(agentNumberForCashOut);
            System.out.println("Enter Amount: ");
            cashOutAmountFromAgent = console.nextInt();
            checkCashOutAmountInvalidFromAgent(cashOutAmountFromAgent);
            System.out.println("Cash Out.\nTo: " + agentNumberForCashOut + "\nAmount: Tk " + cashOutAmountFromAgent + "\nEnter Menu PIN to confirm: ");
            PINWhichWasInputForCashOut = console.nextInt();
            int[] allNumbers= bkashServer.getUserAllNumbers();
            int indexNumberOfAgent = 0;
            for (int i =0; i < 10; i++){
                if (allNumbers[i] ==  agentNumberForCashOut){
                    checkForValidAgentNumber =  true;
                    indexNumberOfAgent = i;
                    break;
                }
            }
            if ((!checkForValidAgentNumber) || (userNumber == agentNumberForCashOut))  {
                System.out.println("The feature is not supported for this product.");
                System.exit(1);
            }else if (bkashServer.getPinForThatNumber(secretIndex) != PINWhichWasInputForCashOut){
                System.out.println("Invalid Combination of Account and PIN. Please Try Again");
                System.exit(1);
            }else{
                bkashServer.setBalanceCashOut(cashOutAmountFromAgent, secretIndex, indexNumberOfAgent);
                System.out.println("Cashed Out Tk " + cashOutAmountFromAgent + " from 0" + agentNumberForCashOut + " successfully. "
                        + "Ref cshoUt" + (cashOutAmountFromAgent+6425231) + "EfG. Fee Tk " + (bkashServer.calcFeeForCashOutFromAgent(cashOutAmountFromAgent)) + ". Balance Tk " +
                        bkashServer.getBalanceForThatNumber(secretIndex) + " TrxID paoA123456EaDw");

                System.exit(0);


            }
        }else{
            cashOutAllProcedure(secretIndex, userNumber);
        }

    }
}







