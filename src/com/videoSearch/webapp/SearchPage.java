package com.videoSearch.webapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
final public class SearchPage extends HttpServlet {
	
	
	
	   /**
     * Respond to a GET request for the content produced by
     * this servlet.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are producing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
	@Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
      throws IOException, ServletException {
		
		response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Video Search'o'matic</title>");
        writer.println("</head>");
        writer.println("<body bgcolor=white>");
        writer.println("<table border=\"0\">");
        writer.println("<tr>");
        writer.println("<td>");
        writer.println("<img src=\"images/tomcat.gif\">");
        writer.println("</td>");
        writer.println("<td>");
        writer.println("<h1>Video Search Tool Thingie</h1>");
        
        writer.println("What are you in the mood for today?");
        writer.println("</td>");
        writer.println("</tr>");
        
        writer.println("<form method=\"post\" action =\""+ request.getContextPath() +
        			   "/search\" >");
        writer.println("<input type=\"text\" name=\"moodQuery\" size=\"30\">");
        writer.println("<input type=\"submit\" value=\"Go!\">");
        writer.println("</form></table>");

        writer.println("</body>");
        writer.println("</html>");
    
    }
}
