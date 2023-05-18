package com.fuckingcheese;

public class Reactor {
    private int id;
    private String code;
    private String unit_name;
    private int site;
    private String status;
    private String type;
    private String model;
    private ReactorType clASS; // make like ReactorType
    private Boolean ru_design;
    private int thermal_capacity;
    private int gross_capacity;
    private int net_capacity;
    private String construction_start;
    private String commercial_operation;
    private String date_shutdown;
    private double enrichment;
    private int load_factor;
    private double fuel_сonsumption;

    public Reactor(int id, String code, String unit_name, int site, String status, String type, String model, ReactorType clASS, Boolean ru_design, int thermmal_capacity, int gross_capacity, int net_capacity, String construction_start, String commercial_operation, String date_shutdown, double enrichment, int load_factor) {
        this.id = id;
        this.code = code;
        this.unit_name = unit_name;
        this.site = site;
        this.status = status;
        this.type = type;
        this.model = model;
        this.clASS = clASS;
        this.ru_design = ru_design;
        this.thermal_capacity = thermmal_capacity;
        this.gross_capacity = gross_capacity;
        this.net_capacity = net_capacity;
        this.construction_start = construction_start;
        this.commercial_operation = commercial_operation;
        this.date_shutdown = date_shutdown;
        this.enrichment = enrichment;
        this.load_factor = load_factor;
        this.fuel_сonsumption = calculateFuelConsumption(thermal_capacity, load_factor, clASS.getBurnup());
        

    }
    
    public double calculateFuelConsumption(int thermalCapacity, int loadFactor, double burnup)
    {
        if(getYearOperation(commercial_operation)==2023)
        {
            System.out.println("i am exist");
            return burnup;
        }
        else
        {        
            return (thermalCapacity/burnup)*((loadFactor/100)+(loadFactor%100))*365;
        }
    }
    
    public int getYearOperation(String comOpr)
    {
        String year = comOpr.split("-")[0];
        return Integer.parseInt(year);
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public int getSite() {
        return site;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public ReactorType getClASS() {
        return clASS;
    }

    public Boolean getRu_design() {
        return ru_design;
    }

    public int getThermmal_capacity() {
        return thermal_capacity;
    }

    public int getGross_capacity() {
        return gross_capacity;
    }

    public int getNet_capacity() {
        return net_capacity;
    }

    public String getConstruction_start() {
        return construction_start;
    }

    public String getCommercial_operation() {
        return commercial_operation;
    }

    public String getDate_shutdown() {
        return date_shutdown;
    }

    public double getEnrichment() {
        return enrichment;
    }

    public int getLoad_factor() {
        return load_factor;
    }

    public double getFuel_сonsumption() {
        return fuel_сonsumption;
    }

    @Override
    public String toString() {
        return "Reactor{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", unit_name='" + unit_name + '\'' +
                ", site=" + site +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", clASS='" + clASS + '\'' +
                ", ru_design=" + ru_design +
                ", thermmal_capacity=" + thermal_capacity +
                ", gross_capacity=" + gross_capacity +
                ", net_capacity=" + net_capacity +
                ", construction_start='" + construction_start + '\'' +
                ", commercial_operation='" + commercial_operation + '\'' +
                ", date_shutdown='" + date_shutdown + '\'' +
                ", enrichment=" + enrichment +
                ", load_factor=" + load_factor +
                ", fuel_сonsumption=" + fuel_сonsumption +
                '}';
    }
}
