package com.surajnshah.sandbox.monitoring;

import org.apache.commons.io.FileSystemUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author surajshah on 17/07/2018
 * @project surajnshah.com
 */
public class DiskSpaceMonitor {

    public static void main(String[] args) throws Exception {

        long freeSpaceKb = getFreeSpaceKb();
        System.out.println("Free Space : " + freeSpaceKb);

    }

    private static long getFreeSpaceKb() {
        try {
            return FileSystemUtils.freeSpaceKb(new File(".").getAbsolutePath());

        } catch (IOException e) {
            return 0;
        }
    }

}
