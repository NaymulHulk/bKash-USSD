import java.util.Scanner;
public class Recharge {
    Scanner console = new Scanner(System.in);
    BkashServer bkashServer = new BkashServer();
    private final int[] codeToDetectSim = {8, 6, 9, 7, 5, 3};
    private final String[] codeToDetectSimsName = {"Robi",  "Airtel", "Banglalink", "Grameenphone", "Teletalk", "skitto"};



    public void printSimList() {
        System.out.println("bKash");
        System.out.println("1 Robi");
        System.out.println("2 Airtel");
        System.out.println("3 Banglalink");
        System.out.println("4 Grameenphone");
        System.out.println("5 Teletalk");
        System.out.println("0 Main Menu");
        System.out.println("9 Dismiss");
    }

    public void printRobiAirtelBanglalinkTeletalkMenu() {
        System.out.println("bKash");
        System.out.println("1 Prepaid");
        System.out.println("2 Postpaid");

    }

    public int checkDigitToDetectSim(int number) {
        return ((number % 1000000000) / 100000000);
    }

    public void wholePaymentProcedureForRecharging(int secretIndex, int simChoiceInput) {
        int input = 0;
        boolean simMatchedFlag = false;

        System.out.println("Enter Amount: ");
        int rechargingAmount = console.nextInt();

        System.out.println("Enter " +codeToDetectSimsName[simChoiceInput - 1] + " Mobile No: ");
        input = console.nextInt();
        bkashServer.checkIfValidNumber(input);

        if (checkDigitToDetectSim(input) == codeToDetectSim[simChoiceInput - 1]) {
            simMatchedFlag = true;
            System.out.println("Enter bKash PIN");
            int inputPIN = console.nextInt();
            int secretPIN = bkashServer.getPinForThatNumber(secretIndex);
            if (rechargingAmount < 10){
                System.out.println("Amount too low to recharge.");
                System.exit(1);
            }else if (rechargingAmount > 1000){
                System.out.println("Amount too high to recharge.");
                System.exit(1);
            }else if (secretPIN != inputPIN){
                System.out.println("Invalid bKash Account No and PIN combination. ");
                System.exit(1);
            }else if ((bkashServer.getBalanceForThatNumber(secretIndex) - rechargingAmount)< 0) {
                System.out.println("Insufficient funds.");
                System.exit(1);
            }else{
                double rechargingAmountInDouble = rechargingAmount;
                bkashServer.setBalanceRecharge(rechargingAmountInDouble, secretIndex);
                System.out.print("Received Mobile Recharge request of Tk " + rechargingAmountInDouble + " for 0" + input + ". "
                        +  "Fee Tk 5.00. Balance Tk " +  bkashServer.getBalanceForThatNumber(secretIndex) + " TrxID " + "" +
                        "Pls wait for confirmation");

                System.exit(0);

            }


        }else{
            System.out.println("This is not a " + codeToDetectSimsName[simChoiceInput - 1] + " Number. Please try again with a number having the third digit as " +
                    codeToDetectSim[simChoiceInput - 1] + " and make sure it is 11 digits.");

        }
    }
}