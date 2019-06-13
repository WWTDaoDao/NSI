package com.nsi.okexsay.Beans;

public class BiBiBean {

    /**
     * price : 5808
     * total : 0
     * coinCount1 : 4
     * coinCount2 : 4
     * rose : 0
     * symbol : BTC
     * exchangeRate : 6.8997
     * symbolid : 8
     */

    private int price;
    private int total;
    private int coinCount1;
    private int coinCount2;
    private int rose;
    private String symbol;
    private double exchangeRate;
    private String symbolid;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCoinCount1() {
        return coinCount1;
    }

    public void setCoinCount1(int coinCount1) {
        this.coinCount1 = coinCount1;
    }

    public int getCoinCount2() {
        return coinCount2;
    }

    public void setCoinCount2(int coinCount2) {
        this.coinCount2 = coinCount2;
    }

    public int getRose() {
        return rose;
    }

    public void setRose(int rose) {
        this.rose = rose;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getSymbolid() {
        return symbolid;
    }

    public void setSymbolid(String symbolid) {
        this.symbolid = symbolid;
    }
}
