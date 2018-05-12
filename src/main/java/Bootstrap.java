import http.XmlParserServlet;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;


public class Bootstrap {
    private static final Logger LOG = LogManager.getLogger("ServerLogger");

    private static Properties prop;
    private static String rootPath;
    private static int httpPort;
    private static int tcpDestPort;
    private static String tcpDestAddress;

    static {
        if (Objects.nonNull(rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath())) {
            LOG.info(rootPath);
            String configPath = rootPath + "config";
            LOG.info(configPath);
            prop = new Properties();
            try {
                prop.load(new FileInputStream(configPath));
                LOG.info("Success reading prop file");
            } catch (IOException e) {
                LOG.error("!!!   CAN'T ACCESS FILE PROPERTIES   !!! ", e);
            }
            httpPort = Integer.parseInt(prop.getProperty("http.port"));
            tcpDestPort = Integer.parseInt(prop.getProperty("tcp.dest.port"));
            tcpDestAddress = prop.getProperty("tcp.dest.addr");
        } else LOG.info("!!!  CANT GET PATH TO THE CONFIG FILE   !!!");
    }


    public static void main(String[] args) throws Exception {
        // не работает при таком запуске, так и не успел разобраться почему
//        Server server = new Server(httpPort);
//        ServletHandler handler = new ServletHandler();
//        handler.addServletWithMapping(XmlParserServlet.class, "/");
//        server.setHandler(handler);
//        System.out.println("before join");
//        server.start();
//        server.join();
        // нагуглил другой вариант запуска
        Server server = new Server(httpPort);
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");
        server.setHandler(contextHandler);
        ServletHolder sh = new ServletHolder(new XmlParserServlet(tcpDestPort, tcpDestAddress));
        contextHandler.addServlet(sh, "/");
        server.start();


    }

}
