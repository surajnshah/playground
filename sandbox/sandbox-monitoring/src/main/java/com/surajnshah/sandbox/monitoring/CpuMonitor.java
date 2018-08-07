package com.surajnshah.sandbox.monitoring;

import com.surajnshah.example.processbuilder.ProcessBuilderExample;

import javax.management.MBeanServerConnection;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.NaN;

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

        /*
        for (int i = 0; i < 1000; i++) {

            Thread.sleep(100);
            System.out.println("System CPU Load: " + osMBean.getSystemCpuLoad() * 100);

        }
        */

        long t = System.currentTimeMillis();
        long end = t+5000;
        /*while(System.currentTimeMillis() < end) {
            System.out.println("System CPU Load: " + osMBean.getSystemCpuLoad() * 100);
            Thread.sleep(100);
        }*/

        List<Double> x = new ArrayList<Double>();

        while(System.currentTimeMillis() < end) {
            x.add(osMBean.getSystemCpuLoad());
            Thread.sleep(100);
        }

        Double sysCpu = new CpuMonitor().calculateAverage(x);

        System.out.println("Average System CPU Load: " + sysCpu * 100);

        System.out.println("Free Physical Memory Size: " + osMBean.getFreePhysicalMemorySize());
        System.out.println("Total Physical Memory Size: " + osMBean.getTotalPhysicalMemorySize());

        //ProcessBuilder pb = new ProcessBuilder("ps", "-A", "-o", "%cpu", "|", "awk", "'{s+=$1}", "END", "{print", "s", "\"%\"}'");
        ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "ps -A -o %cpu | awk '{s+=$1} END {print s \"%\"}'");
        Process process = pb.start();
        int errCode = process.waitFor();
        System.out.println("CPU Utilization: " + ProcessBuilderExample.output(process.getInputStream()));

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
