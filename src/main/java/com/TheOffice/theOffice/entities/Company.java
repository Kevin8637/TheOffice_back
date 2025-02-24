package com.TheOffice.theOffice.entities;

import java.util.Date;

public class Company {
    Long id;
    String sector;
    String name;
    Date creation_date;

    public Company (){
    }

    public Company(Long id, String sector, String name, Date creation_date) {
        this.id = id;
        this.sector = sector;
        this.name = name;
        this.creation_date = creation_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }
}
