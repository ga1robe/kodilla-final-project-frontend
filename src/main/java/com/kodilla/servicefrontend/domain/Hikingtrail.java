package com.kodilla.servicefrontend.domain;

import java.util.Objects;

public class Hikingtrail {

    private String name;
    private String begin;
    private String end;
    private String totalDistance;
    private HikingtrailsType type;

    public Hikingtrail() {
    }

    public Hikingtrail(String name, String begin, String end, String totalDistance, HikingtrailsType type) {
        this.name = name;
        this.begin = begin;
        this.end = end;
        this.totalDistance = totalDistance;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public HikingtrailsType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hikingtrail hikingtrail = (Hikingtrail) o;

        if (!totalDistance.equals(hikingtrail.totalDistance)) return false;
        if (!Objects.equals(name, hikingtrail.name)) return false;
        if (!Objects.equals(begin, hikingtrail.begin)) return false;
        if (!Objects.equals(end, hikingtrail.end)) return false;
        return Objects.equals(type, hikingtrail.type);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (begin != null ? begin.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (totalDistance != null ? totalDistance.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public void setEnd(String end) { this.end = end; }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void setType(HikingtrailsType type) {
        this.type = type;
    }
}
