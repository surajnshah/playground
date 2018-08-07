package com.surajnshah.monitoring.web;

import javax.management.MBeanServerConnection;
import java.io.IOException;
import java.lang.management.ManagementFactory;

import static java.lang.Math.toIntExact;

/**
 * @author surajshah on 03/08/2018
 * @project surajnshah.com
 */
public class MonitorService {

    public Monitor getMonitor() throws IOException {

        MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();

        com.sun.management.OperatingSystemMXBean osMBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        double cpuLoadAverage = osMBean.getSystemLoadAverage();

        int availableProcessors = Runtime.getRuntime().availableProcessors();

        long freeMemory = Runtime.getRuntime().freeMemory();

        long maxMemory = Runtime.getRuntime().maxMemory();

        long totalMemory = Runtime.getRuntime().totalMemory();

        long usedMemory = totalMemory - freeMemory;

        double usedMemAsPercentage = toIntExact(usedMemory) / (double) toIntExact(totalMemory) * 100;

        double systemCpuLoad = osMBean.getSystemCpuLoad();

        /*
        System.out.println("Total memory: " + totalMemory);
        System.out.println("Free memory: " + freeMemory);
        System.out.println("Used memory: " + usedMemory);
        System.out.println("Used memory (%): " + usedMemAsPercentage);
        */

        Monitor monitor = new Monitor(cpuLoadAverage, availableProcessors, freeMemory, maxMemory, totalMemory, usedMemAsPercentage, systemCpuLoad);

        //System.out.println("About to provide : " + cpuLoadAverage + " to client.");

        return monitor;

    }

}
