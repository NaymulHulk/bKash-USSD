import java.util.Scanner;


public class Interface {


    public static void main(String args[]) {



        Recharge recharge = new Recharge();
        BkashServer bkashServer = new BkashServer();
        Wallet wallet = new Wallet();
        Payment payment = new Payment();
        CashOut cashOut = new CashOut();
        Remittance remittance = new Remittance();
        MyBkash myBkash = new MyBkash();
        Scanner console = new Scanner(System.in);

        int senderNumber = 0;

        int secretIndexSender = 0;
        int secretIndexReceiver = 0;
        while (true) {

            System.out.println("Please type in your bKash Wallet Number: ");
            senderNumber = console.nextInt();
            int[] userNumbersArray = bkashServer.getUserAllNumbers();
            boolean numberExistsInServerSender = false;
            for (int i = 0; i < 10; i++) {
                if (userNumbersArray[i] == senderNumber) {
                    numberExistsInServerSender = true;
                    secretIndexSender = i;
                    break;
                }
            }
            if (!numberExistsInServerSender) {
                System.out.println("Wallet Number: (" + senderNumber + ") does not exist");
                continue;
            } else {
                break;
            }
        }


        String s = console.nextLine();
        String bKashMenuNumber = bkashServer.getTwoFourSeven();
        while ((!s.equals(bKashMenuNumber))) {
            System.out.println("Please type *247# for bKash Menu");
            s = console.nextLine();
        }

        int input = 0;
        while (input != 9) {
            bkashServer.getMainMenu();
            input = console.nextInt();

            if (input == 8) {
                bkashServer.getHelpLine();
                input = console.nextInt();
                if (input == 1) {
                    while (input != 0) {
                        System.out.println("bkash");
                        System.out.println("0 Main Menu");
                        input = console.nextInt();
                    }
                    continue;
                } else if (input == 0) {
                    continue;
                } else if (input == 9) {
                    System.exit(0);
                } else {
                    continue;
                }
            }
            else if (input == 1) {
                System.out.println("Enter Receiver bKash Account No: ");
                input = console.nextInt();
                int receiverNumber = input;
                System.out.println("Enter Amount: ");

                double sendingAmount = console.nextDouble();
                System.out.println("Enter Reference: ");
                input = console.nextInt();
                int referenceNumber = input;

                System.out.println("Send Money");
                System.out.println("Amount: Tk " + sendingAmount);
                System.out.println("Reference:" + receiverNumber);
                System.out.println("Enter Menu PIN to confirm: ");
                input = console.nextInt();
                int PINWhichWasInput = input;
                input = 0;

                int[] userNumbersArray = bkashServer.getUserAllNumbers();
                boolean numberExistsInServerReceiver = false;
                for (int j = 0; j < 10; j++) {
                    if (userNumbersArray[j] == receiverNumber) {
                        numberExistsInServerReceiver = true;
                        secretIndexReceiver = j;
                        break;
                    }
                }


                if (senderNumber == receiverNumber) {
                    System.out.println("Cannot pay self.");
                    System.exit(1);
                } else if (sendingAmount < 10) {
                    System.out.println("Amount too low to transact.");
                    System.exit(1);
                } else if (sendingAmount > 10000) {
                    System.out.println("Amount too high to transact.");
                    System.exit(1);
                } else if (wallet.validateWithPIN(secretIndexSender) != PINWhichWasInput) {
                    System.out.println("Invalid bKash Account No and PIN combination. ");
                    System.exit(1);
                } else if ((wallet.validateWithBalance(secretIndexSender) - sendingAmount - 5) < 0) {
                    System.out.println("Insufficient funds.");
                    System.exit(1);
                } else if (!numberExistsInServerReceiver) {
                    System.out.println("Receiver account (" + receiverNumber + ") is invalid.");
                    System.exit(1);
                } else {
                    bkashServer.setBalanceSend(sendingAmount, secretIndexSender);
                    bkashServer.setBalanceReceive(sendingAmount, secretIndexReceiver);

                    System.out.println("Send Money Tk " + sendingAmount + " to 0" + receiverNumber + " successful. "
                            + "Ref" + referenceNumber + ". " + "Fee Tk 5.00. Balance Tk " +
                            bkashServer.getBalanceForThatNumber(secretIndexSender) + " TrxID " + (referenceNumber * referenceNumber));

                    System.exit(0);
                }
            }
            else if (input == 2) {

                boolean prepOrPost = true; //true means prepaid, false means postpaid
                recharge.printSimList();
                int simChoiceInput = console.nextInt();
                if (simChoiceInput == 9) {
                    System.exit(0);
                } else if (simChoiceInput == 0) {
                    continue;
                } else if (simChoiceInput != 4) {
                    while (true) {
                        recharge.printRobiAirtelBanglalinkTeletalkMenu();
                        System.out.println("0 Main Menu");
                        input = console.nextInt();
                        if (input == 9) {
                            System.exit(0);
                        } else if (input == 0) {
                            break;
                        } else if (input == 2) {
                            prepOrPost = false;
                            //insert fn from recharge

                        } else if (input == 1) {
                            recharge.wholePaymentProcedureForRecharging(secretIndexSender, simChoiceInput);
                            System.out.println(" to make sure the transaction is valid for your prepaid number.");
                            System.exit(0);

                        } else if (input == 1) {
                            recharge.wholePaymentProcedureForRecharging(secretIndexSender, simChoiceInput);
                            System.out.println(" to make sure the transaction is valid for your postpaid number.");
                            System.exit(0);

                        }
                    }
                } else if (simChoiceInput == 4) {
                    while (true) {
                        recharge.printRobiAirtelBanglalinkTeletalkMenu();
                        System.out.println("3 skitto");
                        System.out.println("0 Main Menu");
                        input = console.nextInt();
                        if (input == 9) {
                            System.exit(0);
                        } else if (input == 0) {
                            break;
                        } else if (input == 2) {
                            prepOrPost = false;
                            //insert fn from recharge

                        } else if (input == 1) {
                            recharge.wholePaymentProcedureForRecharging(secretIndexSender, simChoiceInput);
                            System.out.println(" to make sure the transaction is valid for your prepaid number.");
                            System.exit(0);

                        } else if (input == 2) {
                            recharge.wholePaymentProcedureForRecharging(secretIndexSender, simChoiceInput);
                            System.out.println(" to make sure the transaction is valid for your postpaid number.");
                            System.exit(0);

                        } else if (input == 3) {
                            recharge.wholePaymentProcedureForRecharging(secretIndexSender, simChoiceInput + 2);
                            System.out.println(" to make sure the transaction is valid for your skitto number.");
                            System.exit(0);

                        } else {
                            continue;
                        }
                    }
                }
            }
            else if (input == 3) {
                payment.wholePaymentForPayment(secretIndexSender);
                break;

            }
            else if (input == 4){
                cashOut.printCashOutFirstMenu();
                cashOut.cashOutAllProcedure(secretIndexSender, senderNumber );
                System.exit(0);

            }
            else if (input == 5) {
                System.out.println("Still under development");
                break;
            }
            else if (input == 6){
                remittance.firstPageMenuOfRemittance();
                remittance.wholeProcedureForRemittance(secretIndexSender);


            }
            else if (input == 7){
                myBkash.printListForMyBkash();
                input = console.nextInt();
                if (input == 0){
                    continue;
                }else{
                    myBkash.wholeProcedureForMyBkash(secretIndexSender, input);
                }

            }
        }
    }
}


