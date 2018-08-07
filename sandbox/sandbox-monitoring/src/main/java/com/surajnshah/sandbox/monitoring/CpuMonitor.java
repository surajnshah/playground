package com.surajnshah.sandbox.monitoring;

import com.surajnshah.example.processbuilder.ProcessBuilderExample;

import javax.management.MBeanServerConnection;
import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * @author surajshah on 03/08/2018
 * @project surajnshah.com
 */
public class CpuMonitor {

    public static void main(String[] args) throws IOException, InterruptedException {

        MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();

        //com.sun.management.OperatingSystemMXBean osMBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.newPlatformMXBeanProxy(mbsc, ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);

        com.sun.management.OperatingSystemMXBean osMBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        double cpuLoadAverage = osMBean.getSystemLoadAverage();

        System.out.println("System Load Average : " + cpuLoadAverage);
        System.out.println("Process CPU Load: " + osMBean.getProcessCpuLoad());


        for (int i = 0; i < 10000; i++) {

            Thread.sleep(100);
            System.out.println("System CPU Load: " + osMBean.getSystemCpuLoad() * 100);

        }


        System.out.println("System CPU Load: " + osMBean.getSystemCpuLoad());

        System.out.println("Free Physical Memory Size: " + osMBean.getFreePhysicalMemorySize());
        System.out.println("Total Physical Memory Size: " + osMBean.getTotalPhysicalMemorySize());

        //ProcessBuilder pb = new ProcessBuilder("ps", "-A", "-o", "%cpu", "|", "awk", "'{s+=$1}", "END", "{print", "s", "\"%\"}'");
        ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "ps -A -o %cpu | awk '{s+=$1} END {print s \"%\"}'");
        Process process = pb.start();
        int errCode = process.waitFor();
        System.out.println("CPU Utilization: " + ProcessBuilderExample.output(process.getInputStream()));

    }

}
