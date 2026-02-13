/*
 * CSC-239 Project 2: Credit Card Verification Application
 * Student: Pedro Delesporte
 * Due Date: 11 19 2025
 * Description: This program processes credit card transactions,
 * saving the results in a text file.
 */


public class CreditAccount {


    // credit card issuer symbols
    public static final String ISSUER_AMER_EXPRESS = "AE";   // American Express
    public static final String ISSUER_VISA         = "V";    // Visa
    public static final String ISSUER_MASTER_CARD  = "MC";   // MasterCard
    public static final String ISSUER_DISCOVER     = "DIS";  // Discover
    public static final String ISSUER_DINERS_CLUB  = "DINE"; // Diners Club

    // issuer codes = first digit of account number
    public static final int ISSUER_CODE_AE   = 3;
    public static final int ISSUER_CODE_V    = 4;
    public static final int ISSUER_CODE_MC   = 5;
    public static final int ISSUER_CODE_DIS  = 6;
    public static final int ISSUER_CODE_DINE = 7;

    // the data, one object = one account
    private String accountNum;   // 16-digit account number
    private double available;    // current available credit
    private double maxLimit;     // maximum credit limit
    private String issuerSymbol; // AE, V, MC, DIS, DINE
    private boolean accountValid;

    //TODO: constructor for creating accounts
    public CreditAccount(String issuerSymbol) {
        this.issuerSymbol = issuerSymbol.toUpperCase();
        int issuerCode = getIssuerCode(this.issuerSymbol);

        if (issuerCode == -1) {
            accountValid = false;
            return;
        }

        // create the 16 random digits for the account number
        // starting with the first number (issuerCode) from above
        accountNum = "" + issuerCode; // start string with first digit
        for (int i = 0; i < 15; i++) {
            int digit = (int) (Math.random() * 10);
            accountNum = accountNum + digit;
        }

        // check last digit to pick the credit limit
        char lastChar = accountNum.charAt(accountNum.length() - 1);
        int lastDigit = lastChar - '0';

        if (lastDigit >= 0 && lastDigit <= 4) {
            maxLimit = 1000.0;
        } else {
            maxLimit = 500.0;
        }

        available = maxLimit;    // at the beginning you have full limit
        accountValid = true;
    }

    // TODO: constructor to load data from the file
    public CreditAccount(String recordText, int recordLength) {
        // format  number| available| limit
        String[] parts = recordText.split("\\|");
        if (parts.length < 3) {
            accountValid = false;
            return;
        }

        accountNum = parts[0];
        available = Double.parseDouble(parts[1]);
        maxLimit  = Double.parseDouble(parts[2]);



        // get issuer symbol from first digit
        char first = accountNum.charAt(0);
        int issuerCode = first - '0';
        issuerSymbol = getIssuerSymbol(issuerCode);

        accountValid = true;
    }

    // create the line for writing to dataFile.txt
    public String assembleRecordText() {
        return accountNum + "| " +
                String.format("%.2f", available) + "| " +
                String.format("%.2f", maxLimit);
    }

    // convert symbol to issuer code
    public static int getIssuerCode(String issuerSymbol) {
        if (issuerSymbol == null) {
            return -1;
        }

        String s = issuerSymbol.toUpperCase();

        if (s.equals(ISSUER_AMER_EXPRESS)) return ISSUER_CODE_AE;
        if (s.equals(ISSUER_VISA))         return ISSUER_CODE_V;
        if (s.equals(ISSUER_MASTER_CARD))  return ISSUER_CODE_MC;
        if (s.equals(ISSUER_DISCOVER))     return ISSUER_CODE_DIS;
        if (s.equals(ISSUER_DINERS_CLUB))  return ISSUER_CODE_DINE;

        return -1; // invalid
    }

    // issuer code to symbol
    public static String getIssuerSymbol(int issuerCode) {
        if (issuerCode == ISSUER_CODE_AE)   return ISSUER_AMER_EXPRESS;
        if (issuerCode == ISSUER_CODE_V)    return ISSUER_VISA;
        if (issuerCode == ISSUER_CODE_MC)   return ISSUER_MASTER_CARD;
        if (issuerCode == ISSUER_CODE_DIS)  return ISSUER_DISCOVER;
        if (issuerCode == ISSUER_CODE_DINE) return ISSUER_DINERS_CLUB;

        return "";
    }

    // TODO:GETTERS / SETTERS
    public String getAccountNum() {

        return accountNum;

    }

    public double getAvailable() {

        return available;

    }

    public void setAvailable(double available) {

        this.available = available;

    }

    public double getMaxLimit() {

        return maxLimit;

    }

    public String getIssuerSymbol() {

        return issuerSymbol;

    }

    public boolean isAccountValid() {

        return accountValid;

    }
}
