package vn.htc.monitor.service;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

@SuppressWarnings("restriction")
public class ServerService {

    private static final String CONTEXT_PATH = "/";

    public void startServer(int port) {
        try {
            Server server = new Server(port);
            Context context
                    = new Context(server, CONTEXT_PATH, Context.SESSIONS);

            ServletHolder servletHolder
                    = new ServletHolder(new ServletContainer());
            servletHolder.setInitParameter(
                    "com.sun.jersey.config.property.resourceConfigClass",
                    "com.sun.jersey.api.core.PackagesResourceConfig");
            servletHolder.setInitParameter(
                    "com.sun.jersey.config.property.packages",
                    "com.develperstack.controller");

            servletHolder.setInitOrder(1);
            context.addServlet(servletHolder, "/*");
//            System.out.println("Start Restful Web Server: contextPath: " + CONTEXT_PATH + " | Port: " + port);
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(ServerService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
