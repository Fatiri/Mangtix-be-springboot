package com.enigma.entity;

public enum StatusTicketOut {
    WAITING("waiting"),
    ON_SALE("onSale"),
    FREE("free");

    private final String whetherSold;

    StatusTicketOut(String whetherSold) {
        this.whetherSold=whetherSold;
    }
}
