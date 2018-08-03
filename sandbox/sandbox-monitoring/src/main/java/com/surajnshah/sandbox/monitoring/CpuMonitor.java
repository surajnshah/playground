package com.surajnshah.sandbox.monitoring;

import com.surajnshah.example.processbuilder.ProcessBuilderExample;

import javax.management.MBeanServerConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 * @author surajshah on 03/08/2018
 * @project surajnshah.com
 */
public class CpuMonitor {

    public static void main(String[] args) throws IOException, InterruptedException {

        MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();

        OperatingSystemMXBean osMBean = ManagementFactory.newPlatformMXBeanProxy(mbsc, ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);

        double cpuLoadAverage = osMBean.getSystemLoadAverage();

        System.out.println("System Load Average : " + cpuLoadAverage);

        //ProcessBuilder pb = new ProcessBuilder("ps", "-A", "-o", "%cpu", "|", "awk", "'{s+=$1}", "END", "{print", "s", "\"%\"}'");
        ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "ps -A -o %cpu | awk '{s+=$1} END {print s \"%\"}'");
        Process process = pb.start();
        int errCode = process.waitFor();
        System.out.println("CPU Utilization: " + ProcessBuilderExample.output(process.getInputStream()));

    }

}
