import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
            String custRepModPath = rootPath + "config";
            prop = new Properties();
            try {
                prop.load(new FileInputStream(custRepModPath));
            } catch (IOException e) {
                LOG.error("!!!   CAN'T ACCESS FILE PROPERTIES   !!! ",e);
            }
            httpPort = Integer.parseInt(prop.getProperty("http.port"));
            tcpDestPort = Integer.parseInt(prop.getProperty("tcp.dest.port"));
            tcpDestAddress = prop.getProperty("tcp.dest.addr");
        }else LOG.info("!!!  CANT GET PATH TO THE CONFIG FILE   !!!");
    }

}
