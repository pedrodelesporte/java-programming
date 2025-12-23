import java.util.Scanner;

public class Lab9a {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String symbol, name;

        while (true) {
            System.out.print("Enter stock Symbol (or 'q' to exit): ");
            symbol = input.nextLine();

            if (symbol.equalsIgnoreCase("q")) {
                System.out.println("Goodbye!");
                break;
            }

            System.out.print("Enter company name: ");
            name = input.nextLine();

            System.out.print("Enter previous closing price: ");
            double previousPrice = Double.parseDouble(input.nextLine());

            System.out.print("Enter current price: ");
            double currentPrice = Double.parseDouble(input.nextLine());

            // Create the stock object and set prices
            Stock stock = new Stock(symbol, name);
            stock.setPreviousClosingPrice(previousPrice);
            stock.setCurrentPrice(currentPrice);

            double percentChange = stock.getChangePercent();

            System.out.printf(
                    "SYMBOL=%s, NAME=%s, PreviousPrice=%.2f, CurrentPrice=%.2f,%n",
                    stock.getSymbol(),
                    stock.getName(),
                    stock.getPreviousClosingPrice(),
                    stock.getCurrentPrice()
            );
            System.out.printf("PercentChange=%+,.2f %%\n\n", percentChange);
        }

        input.close();
    }
}


class Stock {
    private String symbol;
    private String name;
    private double previousClosingPrice;
    private double currentPrice;

    // Constructor
    public Stock(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    // Getters and Setters
    public String getSymbol() {
        return symbol;
    }



    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public double getPreviousClosingPrice() {
        return previousClosingPrice;
    }

    public void setPreviousClosingPrice(double previousClosingPrice) {
        this.previousClosingPrice = previousClosingPrice;
    }


    public double getCurrentPrice() {
        return currentPrice;
    }


    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }


    public double getChangePercent() {
        if (previousClosingPrice == 0) {
            return 0; // to avoid the division by zero
        }
        return ((currentPrice - previousClosingPrice) / previousClosingPrice) * 100;
    }
}

