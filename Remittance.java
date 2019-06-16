import java.util.Scanner;

public class Remittance {
    Scanner console = new Scanner(System.in);
    BkashServer bkashServer = new BkashServer();

    public void firstPageMenuOfRemittance(){
        System.out.println("1 Western Union\n0 Main menu");
    }
    public void wholeProcedureForRemittance(int secretIndex){
        int choiceInput= 0;
        choiceInput = console.nextInt();
        if (choiceInput == 0){
            System.out.print("");
        }else if (choiceInput == 1){
            String inputMTCNNumber;
            int amountForRemittance = 0;
            int PINWhichWasInput = 0;

            System.out.println("Enter Western Union MTCN Number: ");
            console.nextLine();
            inputMTCNNumber = console.nextLine();
            if (inputMTCNNumber.length() != 10){
                System.out.println("MTCN number must be of 10 Digits. Please ask your nearest Western Union and try again later.");
                System.exit(0);
            }
            System.out.println("Enter Amount: ");
            amountForRemittance = console.nextInt();
            if (amountForRemittance <50){
                System.out.println("Amount too low to transact. Should be at least 50.");
                System.exit(0);
            }else if (amountForRemittance > 25000){
                System.out.println("Amount too high to transact. Should be at most 25000.");
                System.exit(0);
            }
            System.out.println("Enter Menu PIN Please: ");
            PINWhichWasInput = console.nextInt();
            if (bkashServer.getPinForThatNumber(secretIndex)!= PINWhichWasInput){
                System.out.println("Invalid combination of Account No and PIN. Please try again");
                System.exit(0);
            }else{
                System.out.println("Wait for confirmation SMS");
                System.exit(0);
            }



        }
    }
}
