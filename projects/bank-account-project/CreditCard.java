/*
 * CSC-239 Project 2: Credit Card Verification Application
 * Student: Pedro Delesporte
 * Due Date: 11 19 2025
 * Description: This program processes credit card transactions,
 * saving the results in a text file.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CreditCard {

    private static final String DATA_FILE_NAME = "dataFile.txt";
    private static final int MAX_ACCOUNTS = 1000;

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        printHelp();

        while (true) {
            System.out.print("\nEnter a command: ");
            if (!console.hasNextLine()) {
                break;
            }
            String line = console.nextLine();

            line = line.trim();
            if (line.length() == 0) {
                continue;
            }

            String[] parts = line.split("\\s+");
            String command = parts[0].toLowerCase();

            if (command.equals("q")) {
                System.out.println("Goodbye!");
                break;
            }

            else if (command.equals("help")) {
                printHelp();
            }

            else if (command.equals("create")) {

                if (parts.length < 2) {
                    System.out.println("Missing the issuer symbol of your card!");

                }


                else {
                    String issuerSymbol = parts[1];
                    createAccount(issuerSymbol);
                }

            } else if (command.equals("verify")) {

                if (parts.length < 3) {
                    System.out.println("Missing your account number and amount!");

                } else {

                    String accountNum = parts[1];

                    try {

                        double amount = Double.parseDouble(parts[2]);
                        authorizeTransaction(accountNum, amount);

                    } catch (NumberFormatException e) {

                        System.out.println("Invalid amount!");

                    }
                }
            } else {
                System.out.println("Invalid command. Type 'help' for help.\\n");
            }
        }

        console.close();
    }

    private static void printHelp() {
        System.out.println("USAGE:   command parameter(s)\n");
        System.out.println("    COMMAND        PARAMETER(S)\n");
        System.out.println("    help                                Output help text.");
        System.out.println("    create         issuerSymbol         CREATE new account.");
        System.out.println("    verify         accountNum amount    VERIFY a purchase or credit.");
        System.out.println("    q                                   exit the program.\n");
        System.out.println("The command and issuerSymbol values are NOT case sensitive.");
        System.out.println("The following credit cards are supported:\n");
        System.out.println("     CARD                    SYMBOL");
        System.out.println("     American Express        AE");
        System.out.println("     Visa                    V");
        System.out.println("     MasterCard              MC");
        System.out.println("     Discover                DIS");
        System.out.println("     Diners Club             DINE\n");
    }

    // TODO: create command
    private static void createAccount(String issuerSymbol) {
        CreditAccount[] accounts = new CreditAccount[MAX_ACCOUNTS];

        // load data from existing file
        int count = loadData(accounts);

        issuerSymbol = issuerSymbol.toUpperCase();
        int issuerCode = CreditAccount.getIssuerCode(issuerSymbol);
        if (issuerCode == -1) {
            System.out.println("Invalid issuer symbol '" + issuerSymbol + "'.");
            writeData(accounts, count);
            return;
        }

        if (count >= MAX_ACCOUNTS) {
            System.out.println("You've reached the maximum number of accounts");
            writeData(accounts, count);
            return;
        }

        CreditAccount newAcct = new CreditAccount(issuerSymbol);
        if (!newAcct.isAccountValid()) {
            System.out.println("Error, could not create your account.");
            writeData(accounts, count);
            return;
        }

        accounts[count] = newAcct;
        count++;

        System.out.println();
        System.out.println("New account created for your  credit card symbol " + issuerSymbol +
                ":  account number is " + newAcct.getAccountNum() + ",");
        System.out.println("credit limit is $" + String.format("%.2f", newAcct.getMaxLimit()));

        writeData(accounts, count);
    }

    // TODO: verify command
    private static void authorizeTransaction(String accountNum, double amount) {
        CreditAccount[] accounts = new CreditAccount[MAX_ACCOUNTS];

        // load existing accounts
        int count = loadData(accounts);

        if (count == 0) {
            System.out.println("This account is not in the file (accountNum=" + accountNum + ")");
            writeData(accounts, count);
            return;
        }

        int index = findAccount(accounts, count, accountNum);

        if (index == -1) {
            System.out.println("This account is not in the file  (accountNum=" + accountNum + ")");
            writeData(accounts, count);
            return;
        }

        CreditAccount acct = accounts[index];
        double available = acct.getAvailable();
        double maxLimit  = acct.getMaxLimit();

        if (amount > 0) {
            // TODO: purchase  (decreases available credit)
            if (amount <= available) {
                double newAvail = available - amount;
                acct.setAvailable(newAvail);
                System.out.println("Purchase granted for (account number: " + acct.getAccountNum() +
                        ", transaction amount: $" + String.format("%.2f", amount) +
                        ", available credit now: $" + String.format("%.2f", newAvail) + ")");
            } else {
                System.out.println("Purchase denied for (account number: " + acct.getAccountNum() +
                        ", transaction amount: $" + String.format("%.2f", amount) +
                        ", available credit is: $" + String.format("%.2f", available) + ")");
            }
        } else if (amount < 0) {
            // TODO: return/credit increases available credit
            double increase = -amount;
            double newAvail = available + increase;

            if (newAvail <= maxLimit) {
                acct.setAvailable(newAvail);
                System.out.println("AUTHORIZATION GRANTED (accountNum=" + acct.getAccountNum() +
                        ", transactionAmount=$" + String.format("%.2f", amount) +
                        ", available credit=$" + String.format("%.2f", newAvail) + ")");
            } else {
                acct.setAvailable(maxLimit);
                System.out.println("AUTHORIZATION GRANTED with CAUTION -- current transaction attempted to raise");
                System.out.println("available credit above MAX=$" + String.format("%.2f", maxLimit) +
                        " (accountNum=" + acct.getAccountNum() +
                        ", transactionAmount=$" + String.format("%.2f", amount) +
                        ", NEW available credit =$" + String.format("%.2f", maxLimit) + ")");
            }
        } else {
            // amount == 0
            System.out.println("Zero amount transaction, no change! ");
        }

        writeData(accounts, count);
    }

    // search for account in a array
    private static int findAccount(CreditAccount[] accounts, int count, String accountNum) {
        for (int i = 0; i < count; i++) {
            if (accounts[i] != null &&
                    accounts[i].getAccountNum().equals(accountNum)) {
                return i;
            }
        }
        return -1;
    }

    // TODO: archive old dataFile.txt and read accounts into array
    private static int loadData(CreditAccount[] accounts) {
        int count = 0;
        File dataFile = new File(DATA_FILE_NAME);

        // if no data file nothing to load
        if (!dataFile.exists()) {
            return 0;
        }

        // TODO: rename old dataFile.txt to dataFile_YYYYMMDD_HHMMSS.txt
        String archivedName = renameWithTimestamp(dataFile);
        if (archivedName == null) {
            // if rename fail, we use original name
            archivedName = DATA_FILE_NAME;
        }

        File inputFile = new File(archivedName);
        Scanner fileScanner = null;

        try {
            fileScanner = new Scanner(inputFile);
            while (fileScanner.hasNextLine() && count < MAX_ACCOUNTS) {
                String line = fileScanner.nextLine().trim();
                if (line.length() == 0) {
                    continue;
                }
                CreditAccount acct = new CreditAccount(line, line.length());
                if (acct.isAccountValid()) {
                    accounts[count] = acct;
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            count = 0;
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
        }

        System.out.println(count + " records loaded from the input file.");
        return count;
    }

    // TODO: write array back out to dataFile.txt
    private static void writeData(CreditAccount[] accounts, int count) {
        PrintWriter out = null;

        try {
            out = new PrintWriter(DATA_FILE_NAME);
            for (int i = 0; i < count; i++) {
                if (accounts[i] != null) {
                    out.println(accounts[i].assembleRecordText());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error, unable to write data file.");
        } finally {
            if (out != null) {
                out.close();
            }
        }

        System.out.println(count + " records written to output file.");
    }

    // TODO: rename with the timestamp
    private static String renameWithTimestamp(File dataFile) {
        if (dataFile == null || !dataFile.exists()) {
            return null;
        }

        // build a string like dataFile_20251119_134512.txt
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        String newName = "dataFile_" + timestamp + ".txt";

        File newFile = new File(newName);
        boolean ok = dataFile.renameTo(newFile);

        if (ok) {
            return newName;
        } else {
            return null; // rename failed
        }
    }
}

