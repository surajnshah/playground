package com.surajnshah.monitoring.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * @author surajshah on 03/08/2018
 * @project surajnshah.com
 */


@Path("/monitor")
public class MonitorRestService {

    MonitorService monitorService = new MonitorService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Monitor getMonitor() throws IOException {

        Monitor monitor = monitorService.getMonitor();

        return monitor;

    }

}
