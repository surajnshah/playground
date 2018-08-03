package com.surajnshah.monitoring.web;

import javax.management.MBeanServerConnection;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 * @author surajshah on 03/08/2018
 * @project surajnshah.com
 */
public class MonitorService {

    public Monitor getMonitor() throws IOException {

        MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();

        OperatingSystemMXBean osMBean = ManagementFactory.newPlatformMXBeanProxy(mbsc, ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);

        double cpuLoadAverage = osMBean.getSystemLoadAverage();

        int availableProcessors = Runtime.getRuntime().availableProcessors();

        long freeMemory = Runtime.getRuntime().freeMemory();

        long maxMemory = Runtime.getRuntime().maxMemory();

        long totalMemory = Runtime.getRuntime().totalMemory();

        Monitor monitor = new Monitor (cpuLoadAverage, availableProcessors, freeMemory, maxMemory, totalMemory);

        //System.out.println("About to provide : " + cpuLoadAverage + " to client.");

        return monitor;

    }

}
