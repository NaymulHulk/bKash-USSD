
public class BkashServer {

        private int[] userAllNumbers = new int[]{1834567890, 1234567891, 1234567892, 1234567893, 1234567894, 1234567895, 1834567896,
                1234567897, 1234567898, 1234567899};
        private double[] userAllBalances = new double[]{1000.10, 500, 10, 0, 9.95, 10000, 45.45, 8, 112, 10.10};
        private final int[] userAllPins = {11111, 12345, 23456, 34567, 45678, 56789, 67890, 78901, 89012, 90123};
        private final int customerCareNumber = 16247;
        private final int maxCashIn = 10000;
        private final int maxCashOut = 10000;
        private final int maxSend = 10000;
        private final int minReceive = 10;
        private final int minSend = 10;
        private final int maxReceive = 10000;
        private final int minCashIn = 10;
        private final int minCashOut = 10;
        private final double minAmountPaymentToMerchant = 0.01;
        //



        public String getTwoFourSeven() {
                return twoFourSeven;
        }

        private final String twoFourSeven = "*247#";


        // Getter Methods
        public int[] getUserAllNumbers(){
                return userAllNumbers.clone();
        }
        public int getPinForThatNumber(int index){
                return this.userAllPins[index];
        }
        public double getBalanceForThatNumber(int index){
                return this.userAllBalances[index];
        }
        public double getMinAmountPaymentToMerchant() {
                return minAmountPaymentToMerchant;
        }

        // Setter Methods
        public void setBalanceSend(double amount, int index){
                this.userAllBalances[index] -= amount -5;
        }

        public double calcFeeForCashOutFromAgent(int amount){
                double amountInDouble = (double) amount;
                return (185.00 * amountInDouble / 10000.00);
        }

        public void setBalanceCashOut(int amount,int indexUser, int indexAgent){
                double amountInDouble = (double) amount;
                this.userAllBalances[indexUser] -= (calcFeeForCashOutFromAgent(amount) + amountInDouble);
                this.userAllBalances[indexAgent] += amountInDouble;


        }
        public void setBalanceReceive(double amount, int index){
                this.userAllBalances[index] += amount;
        }
        public void setBalanceRecharge(double amount, int index){
                this.userAllBalances[index] -= amount;
        }

        public void getMainMenu(){
                System.out.println("bKash");
                System.out.println("1 Send Money");
                System.out.println("2 Mobile Recharge");
                System.out.println("3 Payment");
                System.out.println("4 Cash Out");
                System.out.println("5 Pay Bill");
                System.out.println("6 Remittance");
                System.out.println("7 My bKash");
                System.out.println("8 Helpline");
                System.out.println("9 Dismiss");
        }
        public void getHelpLine(){
                System.out.println("bKash");
                System.out.println("1 call 16247 or Visit www.bkash.com for more info.");
                System.out.println("0 Main Menu");
                System.out.println("9 Dismiss");
        }
        public void checkIfValidNumber(int num){
                if ((num > 1999999999) || (num < 1300000000) || ((num >= 1400000000) && (num <= 1499999999))){
                        System.out.println("Number is Invalid. Please Try Again.");
                        System.exit(-1);
                }
        }
        public void setBalanceForPaymentToMerchant(double amount, int index){
                userAllBalances[index] -= amount;
        }

        public boolean checkForSufficientBalanceForPayment(double amount, int index){
                return (userAllBalances[index] - amount < 0.00);
        }
        public void changePIN(int index, int pin){
                userAllPins[index] = pin;
                System.out.println("Your new PIN is "+userAllPins[index]);
                System.exit(0);

        }





}

