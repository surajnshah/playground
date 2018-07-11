package com.surajnshah.example.employees;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.Service;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

/**
 * @author surajshah on 10/07/2018
 * @project surajnshah.com
 */
public class Main {

    public static void main(String[] args) throws LifecycleException, InterruptedException, ServletException {

        Tomcat tomcat = new Tomcat();

        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8081";
        }

        tomcat.setPort(Integer.valueOf(webPort));

        String webappDirLocation = "example/example-employees-app/src/main/webapp/";
        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File(webappDirLocation).getAbsolutePath());

        File additionWebInfClasses = new File("example/example-employees-app/target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        Service service = tomcat.getService();
        service.addConnector(httpConnector());

        tomcat.start();
        tomcat.getServer().await();

    }

    public static Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8081);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }
}
