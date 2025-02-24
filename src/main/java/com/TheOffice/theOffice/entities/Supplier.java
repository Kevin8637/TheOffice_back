package com.TheOffice.theOffice.entities;

import java.math.BigDecimal;

public class Supplier {
    private int id;
    private String name;
    private BigDecimal price;
    private String quality;
    private int companyId;

    public Supplier(String name, BigDecimal price, String quality, int companyId) {
        this.name = name;
        this.price = price;
        this.quality = quality;
        this.companyId = companyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
