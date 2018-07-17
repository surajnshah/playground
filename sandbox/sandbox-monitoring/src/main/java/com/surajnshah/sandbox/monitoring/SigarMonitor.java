package com.surajnshah.sandbox.monitoring;

import org.hyperic.sigar.ProcCpu;
import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.ptql.ProcessFinder;

/**
 * @author surajshah on 17/07/2018
 * @project surajnshah.com
 */
public class SigarMonitor {

    public static void main(String[] args) throws SigarException, InterruptedException {

        // Create a Sigar object
        Sigar sigar = new Sigar();

        for (int i = 0; i < 100; i++) {

            ProcessFinder find = new ProcessFinder(sigar);

            // Get the list of current Java processes, and optionally query the list to choose which processes to monitor
            try {
                long[] pidList = sigar.getProcList();
            } catch (SigarException e) {
                e.printStackTrace();
            }

            // Assuming we know the process id, we may query the process finder
            long pid = find.findSingleProcess("Pid.Pid.eq=54730");

            // Get memory info for the process id
            ProcMem memory = new ProcMem();
            memory.gather(sigar, pid);

            // Get CPU info for the process id
            ProcCpu cpu = new ProcCpu();
            cpu.gather(sigar, pid);

            // Print the memory used by the process id
            System.out.println("Current memory used: " + Long.toString(memory.getSize()));

            // Print all memory info
            System.out.println(memory.toMap());

            // Print all CPU info
            System.out.println(cpu.toMap());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
