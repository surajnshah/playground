package com.surajnshah.monitoring.web;

import javax.management.MBeanServerConnection;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.NaN;
import static java.lang.Math.toIntExact;

/**
 * @author surajshah on 03/08/2018
 * @project surajnshah.com
 */
public class MonitorService {

    public Monitor getMonitor() throws IOException, InterruptedException {

        MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();

        com.sun.management.OperatingSystemMXBean osMBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        double cpuLoadAverage = osMBean.getSystemLoadAverage();

        int availableProcessors = Runtime.getRuntime().availableProcessors();

        long freeMemory = Runtime.getRuntime().freeMemory();

        long maxMemory = Runtime.getRuntime().maxMemory();

        long totalMemory = Runtime.getRuntime().totalMemory();

        long usedMemory = totalMemory - freeMemory;

        double usedMemAsPercentage = toIntExact(usedMemory) / (double) toIntExact(totalMemory) * 100;

        long t = System.currentTimeMillis();
        long end = t+2000;

        List<Double> x = new ArrayList<>();

        while(System.currentTimeMillis() < end) {
            x.add(osMBean.getSystemCpuLoad());
            Thread.sleep(100);
        }

        Double systemCpuLoad = new MonitorService().calculateAverage(x) * 100;

        Monitor monitor = new Monitor(cpuLoadAverage, availableProcessors, freeMemory, maxMemory, totalMemory, usedMemAsPercentage, systemCpuLoad);

        return monitor;

    }

    public double calculateAverage(List <Double> lists) {

        Double sum = 0.0;
        if (!lists.isEmpty()) {
            for (Double list : lists) {
                if (!list.equals(NaN)) {
                    sum += list;
                }
            }
            return sum.doubleValue() / lists.size();
        }

        return sum;

    }

}
