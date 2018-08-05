package com.surajnshah.monitoring.web;

import javax.ws.rs.core.Response;

/**
 * @author surajshah on 03/08/2018
 * @project surajnshah.com
 */
public class Monitor {

    private double systemLoadAverage;
    private int availableProcessors;
    private long freeMemory;
    private long maxMemory;
    private long totalMemory;
    private double usedMemory;

    public Monitor(double systemLoadAverage, int availableProcessors, long freeMemory, long maxMemory, long totalMemory, double usedMemory) {

        this.systemLoadAverage = systemLoadAverage;
        this.availableProcessors = availableProcessors;
        this.freeMemory = freeMemory;
        this.maxMemory = maxMemory;
        this.totalMemory = totalMemory;
        this.usedMemory = usedMemory;

    }

    public double getSystemLoadAverage() {
        return systemLoadAverage;
    }

    public void setSystemLoadAverage(double systemLoadAverage) {
        this.systemLoadAverage = systemLoadAverage;
    }

    public int getAvailableProcessors() {
        return availableProcessors;
    }

    public void setAvailableProcessors(int availableProcessors) {
        this.availableProcessors = availableProcessors;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(long freeMemory) {
        this.freeMemory = freeMemory;
    }

    public long getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public double getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(double usedMemory) {
        this.usedMemory = usedMemory;
    }

    @Override
    public String toString() {

        return new StringBuffer(" System Load Average : ").append(this.systemLoadAverage)
                .append(" Available Processors : ").append(this.availableProcessors)
                .append(" Free Memory : ").append(this.freeMemory)
                .append(" Max Memory : ").append(this.maxMemory)
                .append(" Total Memory : ").append(this.totalMemory)
                .append(" Used Memory : ").append(this.usedMemory).toString();

    }

}
