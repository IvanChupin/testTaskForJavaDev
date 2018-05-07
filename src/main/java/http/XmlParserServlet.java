package http;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        String xml = req.getParameter("xml");
        if (xml == null) {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("There is no content at all!");
        } else {

        }

    }

//    convertXMLToJSON(Str)


}
