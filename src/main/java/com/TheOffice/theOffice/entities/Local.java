package com.TheOffice.theOffice.entities;

import java.math.BigDecimal;

public class Local {
    private Long id;
    private LocalLevel level;
    private Integer size;
    private BigDecimal rent;
    private Integer maxEmployees;
    private Integer maxMachines;
    private Long companyId;

    public Local() {
    }

    public Local(Long id, LocalLevel level, Integer size, BigDecimal rent, Integer maxEmployees, Integer maxMachines, Long companyId) {
        this.id = id;
        this.level = level;
        this.size = size;
        this.rent = rent;
        this.maxEmployees = maxEmployees;
        this.maxMachines = maxMachines;
        this.companyId = companyId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalLevel getLevel() { return level; }
    public void setLevel(LocalLevel level) { this.level = level; }

    public Integer getSize() { return size; }
    public void setSize(Integer size) { this.size = size; }

    public BigDecimal getRent() { return rent; }
    public void setRent(BigDecimal rent) { this.rent = rent; }

    public Integer getMaxEmployees() { return maxEmployees; }
    public void setMaxEmployees(Integer maxEmployees) { this.maxEmployees = maxEmployees; }

    public Integer getMaxMachines() { return maxMachines; }
    public void setMaxMachines(Integer maxMachines) { this.maxMachines = maxMachines; }

    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
}

// Définition de l'ENUM pour le niveau du local
enum LocalLevel {
    PETIT_LOCAL, MOYEN_LOCAL, GRAND_LOCAL;
}
