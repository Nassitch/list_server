package com.list.server.models.responses;

public class StatisticResponse {

    private String[] months;
    private Long[] shops;
    private Long[] invoices;

    public StatisticResponse(String[] months, Long[] shops, Long[] invoices) {
        this.months = months;
        this.shops = shops;
        this.invoices = invoices;
    }

    public String[] getMonths() {
        return this.months;
    }

    public Long[] getShops() {
        return this.shops;
    }

    public Long[] getInvoices() {
        return this.invoices;
    }

    public void setMonths(String[] months) {
        this.months = months;
    }

    public void setShops(Long[] shops) {
        this.shops = shops;
    }

    public void setInvoices(Long[] invoices) {
        this.invoices = invoices;
    }
}
