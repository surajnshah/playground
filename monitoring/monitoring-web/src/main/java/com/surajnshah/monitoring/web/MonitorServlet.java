package com.surajnshah.monitoring.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author surajshah on 03/08/2018
 * @project surajnshah.com
 */

@WebServlet(
        name = "MonitorServlet",
        urlPatterns = {"/monitor"}
)

public class MonitorServlet extends HttpServlet {

    MonitorService monitorService = new MonitorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Monitor monitor = monitorService.getMonitor();
        forwardMonitor(req, resp, monitor);

    }

    private void forwardMonitor(HttpServletRequest req, HttpServletResponse resp, Monitor monitor) throws ServletException, IOException {

        String nextJSP = "/jsp/monitor.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);

    }

}
