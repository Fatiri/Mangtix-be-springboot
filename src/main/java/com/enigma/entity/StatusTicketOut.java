package com.enigma.entity;

public enum StatusTicketOut {
    ON_SALE("onSale"),
    FREE("free");

    private final String whetherSold;

    StatusTicketOut(String whetherSold) {
        this.whetherSold=whetherSold;
    }
}
