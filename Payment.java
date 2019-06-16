import java.util.Scanner;

public class Payment {
    BkashServer bkashServer = new BkashServer();
    Scanner console = new Scanner(System.in);

    public boolean checkIfValidAmountForPaymentToMerchant(double amount){
      return  (amount >= bkashServer.getMinAmountPaymentToMerchant() );

    }

    public void wholePaymentForPayment(int secretIndex){

        int merchantNumber = 0;
        double amountPaidToMerchant = 0;
        int referenceForMerchant = 0;
        int counterNo = 0;
        int PINInputForPaymentToMerchant = 0;


        System.out.println("Payment\nEnter Merchant No: ");
        merchantNumber = console.nextInt();
        bkashServer.checkIfValidNumber(merchantNumber);

        System.out.println("Payment\nEnter Amount: ");
        amountPaidToMerchant = console.nextDouble();
        if (!checkIfValidAmountForPaymentToMerchant(amountPaidToMerchant)) {
            System.out.println(""+ amountPaidToMerchant);
            System.out.println("The amount entered is Invalid");
            System.exit(-1);
        }

        System.out.println("Payment\nEnter Reference No: ");
        referenceForMerchant = console.nextInt();

        System.out.println("Payment\nEnter Counter No: ");
        counterNo = console.nextInt();

        System.out.println("Payment\nTo: " + merchantNumber + "\nAmount: " + amountPaidToMerchant +
                "\nReference No: " + referenceForMerchant + "\nCounter No: " + counterNo + "\nEnter Menu PIN: ");

        PINInputForPaymentToMerchant = console.nextInt();
        if (PINInputForPaymentToMerchant != bkashServer.getPinForThatNumber(secretIndex)){
            System.out.println("Invalid Combination of Account and PIN. Please Try Again");
            System.exit(1);

        }else if(bkashServer.checkForSufficientBalanceForPayment(amountPaidToMerchant, secretIndex)) {
            System.out.println("Insufficient Funds.Please Try Again.");
            System.exit(0);
        }else{
            bkashServer.setBalanceForPaymentToMerchant(amountPaidToMerchant, secretIndex);
            System.out.print("Paid  " + amountPaidToMerchant + " to 0" + merchantNumber + ". "
                    +  "Fee Tk 0.00. Balance Tk " +  bkashServer.getBalanceForThatNumber(secretIndex) + " TrxID " +
                    (amountPaidToMerchant * amountPaidToMerchant) + " Counter No: " + counterNo + " Pls wait for confirmation");
        }


    }
}
