package http;


import model.Envelope;
import service.CustomParserXMLtoObj;
import service.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@WebServlet("/")
public class XmlParserServlet extends HttpServlet {
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
            out.println("There is no content at all!");
        } else {
            try {
                envelope = CustomParserXMLtoObj.convertXMLtoObject(xml, Envelope.class);
            } catch (JAXBException e) {
                out.println("There is an exception while parsing Your XML to Object, <br> ");
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String sStackTrace = sw.toString();
                out.println(sStackTrace);
            }
            if (envelope != null) {
                jsonString = new StringBuilder(JsonUtil.convertObjectToJSON(envelope));
            }
        }

    }

//    convertXMLToJSON(Str)


}
