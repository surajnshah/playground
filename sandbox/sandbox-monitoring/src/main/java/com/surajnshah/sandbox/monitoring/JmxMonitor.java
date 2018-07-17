package com.surajnshah.sandbox.monitoring;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @author surajshah on 17/07/2018
 * @project surajnshah.com
 */
public class JmxMonitor {

    public static void main(String[] args) throws Exception {

        double processCpuLoad = getCpuLoad("process");
        System.out.println("Process CPU Load: " + processCpuLoad);
        double systemCpuLoad = getCpuLoad("system");
        System.out.println("System CPU Load: " + systemCpuLoad);

    }

    public static double getCpuLoad(String type) throws Exception {

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = ObjectName.getInstance("java.lang:type=OperatingSystem");
        AttributeList attributeList;
        if (type == "process") {
            attributeList = mBeanServer.getAttributes(objectName, new String[]{"ProcessCpuLoad"});

        } else if (type == "system") {
            attributeList = mBeanServer.getAttributes(objectName, new String[]{"SystemCpuLoad"});
        } else {
            attributeList = null;
        }

        if (attributeList.isEmpty()) return Double.NaN;

        Attribute attribute = (Attribute) attributeList.get(0);
        Double value = (Double) attribute.getValue();

        // Usually takes a couple of seconds before we get real values
        if (value == -1.0) return Double.NaN;

        // Returns a % value with 1 decimal point precision
        return ((int) (value * 1000) / 10.0);

    }

}
