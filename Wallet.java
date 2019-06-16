public class Wallet {

    BkashServer bkashServer = new BkashServer();

    public int validateWithPIN(int index){
    int userSecretPIN = bkashServer.getPinForThatNumber(index);
    return userSecretPIN;
    }
    public double validateWithBalance(int index){
        double userSecretBalance = bkashServer.getBalanceForThatNumber(index);
        return userSecretBalance;
    }

}
