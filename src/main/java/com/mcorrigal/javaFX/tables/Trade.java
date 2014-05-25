package com.mcorrigal.javaFX.tables;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Trade {
    private SimpleBooleanProperty isActive;
    private SimpleStringProperty instrumentType;
    private SimpleStringProperty member;
    private SimpleStringProperty counterparty;
    private SimpleStringProperty side;
    private SimpleStringProperty symbol;
    private SimpleStringProperty lots;
    private SimpleStringProperty price;

    public Trade(
            boolean isActive,
            String instrumentType,
            String member,
            String counterparty,
            String side,
            String symbol,
            String lots,
            String price) {

        this.isActive = new SimpleBooleanProperty(isActive);
        this.instrumentType = new SimpleStringProperty(instrumentType);
        this.member = new SimpleStringProperty(member);
        this.counterparty = new SimpleStringProperty(counterparty);
        this.side = new SimpleStringProperty(side);
        this.symbol = new SimpleStringProperty(symbol);
        this.lots = new SimpleStringProperty(lots);
        this.price = new SimpleStringProperty(price);
    }

    public boolean getIsActive() {
        return isActive.get();
    }

    public SimpleBooleanProperty isActiveProperty() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive.set(isActive);
    }

    public String getInstrumentType() {
        return instrumentType.get();
    }

    public SimpleStringProperty instrumentTypeProperty() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType.set(instrumentType);
    }

    public String getMember() {
        return member.get();
    }

    public SimpleStringProperty memberProperty() {
        return member;
    }

    public void setMember(String member) {
        this.member.set(member);
    }

    public String getCounterparty() {
        return counterparty.get();
    }

    public SimpleStringProperty counterpartyProperty() {
        return counterparty;
    }

    public void setCounterparty(String counterparty) {
        this.counterparty.set(counterparty);
    }

    public String getSide() {
        return side.get();
    }

    public SimpleStringProperty sideProperty() {
        return side;
    }

    public void setSide(String side) {
        this.side.set(side);
    }

    public String getSymbol() {
        return symbol.get();
    }

    public SimpleStringProperty symbolProperty() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol.set(symbol);
    }

    public String getLots() {
        return lots.get();
    }

    public SimpleStringProperty lotsProperty() {
        return lots;
    }

    public void setLots(String lots) {
        this.lots.set(lots);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }
}

