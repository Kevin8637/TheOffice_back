package com.TheOffice.theOffice.entities.Machine;

import java.math.BigDecimal;

public class Machine {
    private Long id;
    private String name;
    private ProductionQuality productionQuality;
    private BigDecimal price;
    private BigDecimal maintenanceCost;

    public Machine() {
    }

    public Machine(Long id, String name, ProductionQuality productionQuality, BigDecimal price, BigDecimal maintenanceCost) {
        this.id = id;
        this.name = name;
        this.productionQuality = productionQuality;
        this.price = price;
        this.maintenanceCost = maintenanceCost;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public ProductionQuality getProductionQuality() { return productionQuality; }
    public void setProductionQuality(ProductionQuality productionQuality) { this.productionQuality = productionQuality; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public BigDecimal getMaintenanceCost() { return maintenanceCost; }
    public void setMaintenanceCost(BigDecimal maintenanceCost) { this.maintenanceCost = maintenanceCost; }
}

