import java.util.Scanner;
import java.lang.Math;

public class MyBkash {
    Scanner console = new Scanner(System.in);
    BkashServer bkashServer = new BkashServer();

    public void printListForMyBkash(){
        System.out.println("bKash\n1 Check Balance\n2 Request Statement\n3 Change Mobile Menu PIN\n4 Manage Beneficiary" +
                "\n5 Update MNP Info\n0 Main Menu");
    }

    public void wholeProcedureForMyBkash(int secretIndex, int inputChoice){
        if (inputChoice == 1){
            int PINWhichWasInput = 0;
            System.out.println("Enter Menu Pin");
            PINWhichWasInput = console.nextInt();
            if (bkashServer.getPinForThatNumber(secretIndex) != PINWhichWasInput){
                System.out.println("Invalid Combination of Account No and PIN");
            }else{
                System.out.println("Your current bKash Account balance is Tk " + bkashServer.getBalanceForThatNumber(secretIndex)
                        +". Your available bKash Account balance is Tk "+ bkashServer.getBalanceForThatNumber(secretIndex)+
                        ". bKash App diye balance check ekdom simple");
                System.exit(0);
            }


        }
        else if (inputChoice == 2){
            int PINWhichWasInput = 0;
            System.out.println("Enter Menu PIN");
            PINWhichWasInput = console.nextInt();
            if (bkashServer.getPinForThatNumber(secretIndex) != PINWhichWasInput){
                System.out.println("Invalid Combination of Account No and PIN");
            }else{
                System.out.println("Statement List: ");
                System.out.println("1 12/06/19 Mobile Recharge-Tk-10.00");
                System.out.println("2 11/06/19 Mobile Recharge-Tk-10.00");
                System.out.println("1 12/06/19 Mobile Recharge Reversal-Tk-10.00");
                System.out.println("1 12/06/19 Mobile Recharge Reversal-Tk-10.00");
                System.exit(0);
            }



        }else if (inputChoice == 3){
            // Sorry, Sequential or same digits PIN is not acceptable
            int PINWhichWasInput = 0;
            System.out.println("Enter Old Menu PIN");
            PINWhichWasInput = console.nextInt();
            if (bkashServer.getPinForThatNumber(secretIndex) != PINWhichWasInput){

                System.out.println("Invalid Combination of Account No and PIN");
                System.exit(0);
            }
            int PINWhichWasInputNew = 0;
            System.out.println("Enter New PIN");
            PINWhichWasInput = console.nextInt();
            checkIfValidNewPIN(PINWhichWasInput);

            System.out.println("Please enter PIN again to confirm");
            int PINWhichWasInputAgain = console.nextInt();
            if(PINWhichWasInput != PINWhichWasInputAgain){
                System.out.println("PIN Input was not equal. Please try again.");
                System.exit(0);
            }else{
                bkashServer.changePIN(secretIndex, PINWhichWasInputAgain);
            }


        }else if (inputChoice ==4){
            System.out.println("Still under development.");
        }else if (inputChoice == 5){
            System.out.println("Still under development.");

        }

    }

    public void checkIfValidNewPIN(int pin){

        if (pin < 10000) {
            System.out.println("PIN too small. PIN must be of 5 digits and cannot start with 0.");
            System.exit(0);
        }else if (pin > 99999){
            System.out.println("PIN too large. PIN must be of 5 digits");
            System.exit(0);
        }
        int[] PINAsArray={0,0,0,0,0};
        PINAsArray[0] = pin/10000;
        PINAsArray[1] = (pin%10000)/1000;
        PINAsArray[2] = (pin%1000)/100;
        PINAsArray[3] = (pin%100)/10;
        PINAsArray[4] = (pin%10)/1;
        int flagToDetectRepitionOfSequence = 0;
        for (int i = 0; i < 4; i++){

            if (PINAsArray[i] == PINAsArray[i+1]){
                flagToDetectRepitionOfSequence += 1;

            }
            if (flagToDetectRepitionOfSequence == 2){
                System.out.println("Please try another password with less sequential digits");
                System.exit(0);
            }
        }
        if (((PINAsArray[0] == PINAsArray[1]+1) && (PINAsArray[1] == PINAsArray[2]+1) && (PINAsArray[2] == PINAsArray[3]+1)
        && (PINAsArray[3] == PINAsArray[4]+1)) ||
                ((PINAsArray[0] == PINAsArray[1]-1) && (PINAsArray[1] == PINAsArray[2]-1) && (PINAsArray[2] == PINAsArray[3]-1)
                        && (PINAsArray[3] == PINAsArray[4]-1))){
            System.out.println("The PIN is sequential. Please try another one");
            System.exit(0);
        }

    }
}
