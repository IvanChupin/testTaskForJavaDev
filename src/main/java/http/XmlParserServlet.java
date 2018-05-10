package http;


import jdk.nashorn.internal.runtime.linker.Bootstrap;
import model.Envelope;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import service.CustomParserXMLtoObj;
import service.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.net.UnknownHostException;

@WebServlet("/")
public class XmlParserServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger("HttpServletLogger");
    private String hostName;
    private int portNumber;
    private final String fixedMagic = "FFBBCCDD";

    public XmlParserServlet(int tcpDestPort, String tcpDestAddress) {
        portNumber = tcpDestPort;
        hostName = tcpDestAddress;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t\t<title>Test Task</title>\n" +
                "\n" +
                "\t</head>\n" +
                "<body>\n" +
                "\n" +
                "\t<h2>Insert your xml and press click button</h2>\n" +
                "\n" +
                "\t\t<form id=\"myForm\" name=\"xml\" method=\"post\">\n" +
                "\t\t  Paste your xml right here:<br>\n" +
                "\t\t <textarea rows=\"10\" cols=\"80\" ></textarea>\n" +
                "\t\t  <br><br>\n" +
                "\t\t  <input type=\"submit\" value=\"click!\">\n" +
                "\t\t</form> \n" +
                "\n" +
                "</body>\n" +
                "</html>\n");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Envelope envelope = null;
        StringBuilder jsonString;
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        String xml = req.getParameter("xml");
        if (xml == null) {
            LOG.info("There is no content at all!");
            out.println("There is no content at all!");
        } else {
            try {
                envelope = CustomParserXMLtoObj.convertXMLtoObject(xml, Envelope.class);
                LOG.info("Converting to XML successful");
            } catch (JAXBException e) {
                out.println("There is an exception while parsing your XML to Object <br> ");
                LOG.error("There is an exception while parsing your XML to Object", e);
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String sStackTrace = sw.toString();
                out.println(sStackTrace);
            }
            if (envelope != null) {
                try {
                    jsonString = new StringBuilder(JsonUtil.convertObjectToJSON(envelope));
                    out.println("JSON successfully created" + jsonString);
                    LOG.info(jsonString);
                    createAndSendThroughTheSocket(jsonString.toString(), hostName, portNumber, out);
                } catch (IOException e) {
                    out.println(e.getMessage());
                    LOG.error("!!!   There is an exception while converting Object to JSON   !!!", e);
                }

            }
        }

    }

    private void createAndSendThroughTheSocket(String msg, String hostName, int portNumber, PrintWriter pout) {
        try (
                Socket socket = new Socket(hostName, portNumber);
                BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream(), 2048);
        ) {
            out.write(fixedMagic.getBytes());
            out.write(msg.getBytes("UTF-16LE").length);
            out.write(msg.getBytes("UTF-16LE"));
            pout.println("Data has been send though the socket");
            LOG.info("Data has been send though the socket");
        } catch (UnknownHostException e) {
            LOG.error("There is a problem with end-point socket", e);
            pout.println("There is a problem with end-point socket" + e.getMessage());
        } catch (IOException e) {
            LOG.error(e);
            pout.println(e.getMessage());
        }
    }

}
