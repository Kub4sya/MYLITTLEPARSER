package com.fuckingcheese;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class ReactorType {
    @SerializedName("class")
    private String name;
    private double burnup;
    private double kpd;
    private double enrichment;
    private int termal_capacity;
    private double electrical_capacity;
    private int life_time;
    private double first_load;
    private String from;

    public ReactorType(String name, double burnup, double kpd, double enrichment, int termal_capacity, double electrical_capacity, int life_time, double first_load) {
        this.name = name;
        this.burnup = burnup;
        this.kpd = kpd;
        this.enrichment = enrichment;
        this.termal_capacity = termal_capacity;
        this.electrical_capacity = electrical_capacity;
        this.life_time = life_time;
        this.first_load = first_load;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReactorType that = (ReactorType) o;
        return Double.compare(that.burnup, burnup) == 0 && Double.compare(that.kpd, kpd) == 0 && Double.compare(that.enrichment, enrichment) == 0 && termal_capacity == that.termal_capacity && Double.compare(that.electrical_capacity, electrical_capacity) == 0 && life_time == that.life_time && Double.compare(that.first_load, first_load) == 0 && Objects.equals(name, that.name) && Objects.equals(from, that.from);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, burnup, kpd, enrichment, termal_capacity, electrical_capacity, life_time, first_load, from);
    }

    public String getName() {
        return name;
    }

    public double getBurnup() {
        return burnup;
    }

    public double getKpd() {
        return kpd;
    }

    public double getEnrichment() {
        return enrichment;
    }

    public int getTermal_capacity() {
        return termal_capacity;
    }

    public double getElectrical_capacity() {
        return electrical_capacity;
    }

    public int getLife_time() {
        return life_time;
    }

    public double getFirst_load() {
        return first_load;
    }

    public String getFrom() {
        return from;
    }
    
    

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "ReactorType{" +
                "name='" + name + '\'' +
                ", burnup=" + burnup +
                ", kpd=" + kpd +
                ", enrichment=" + enrichment +
                ", termal_capacity=" + termal_capacity +
                ", electrical_capacity=" + electrical_capacity +
                ", life_time=" + life_time +
                ", first_load=" + first_load +
                ", from=" + from +
                '}';
    }
}
