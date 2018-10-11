package Java8;

public class Transcation {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transcation(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{" + this.trader + "," + "year: " + year + "value:" + value + "}";
    }
}
