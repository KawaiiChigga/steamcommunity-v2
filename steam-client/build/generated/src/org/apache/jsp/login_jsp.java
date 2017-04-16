package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "head.jsp", out, true);
      out.write("\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <div class=\"container\">\r\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, true);
      out.write("\r\n");
      out.write("            <div class=\"content\">\r\n");
      out.write("                <div class=\"highlight\">\r\n");
      out.write("                    <div class=\"highlight_left\">\r\n");
      out.write("                        <h3>Sign In</h3>\r\n");
      out.write("                        <p>To an existing Steam account</p>\r\n");
      out.write("                        <form action=\"login\" method=\"POST\">\r\n");
      out.write("                            Steam username <br/>\r\n");
      out.write("                            <input type=\"text\" name=\"txtUsername\" /> <br/>\r\n");
      out.write("                            Password <br/>\r\n");
      out.write("                            <input type=\"password\" name=\"txtPassword\" /> <br/>\r\n");
      out.write("                            <input type=\"checkbox\" name=\"checkRemember\" /> Remember me on this computer <br/><br/>\r\n");
      out.write("                            <input type=\"submit\" name=\"btnSubmit\" value=\"Sign in\"/>\r\n");
      out.write("                        </form>\r\n");
      out.write("                        <a href=\"#\">Forgot your password?</a>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"highlight_right\">\r\n");
      out.write("                        <h3>Create</h3>\r\n");
      out.write("                        <p>A new free account</p>\r\n");
      out.write("                        <p>It's free to join and easy to use. Continue on to create your Steam account \r\n");
      out.write("                            and get Steam, the leading digital solution for PC, Mac, and Linux games and Software.</p>\r\n");
      out.write("                        <a href=\"join.jsp\"><input type=\"button\" value=\"Join Steam\" /></a>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div style=\"clear: both;\"></div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div id=\"\" style=\"text-align: center;\">\r\n");
      out.write("                    <img src=\"image/about_steam_preview.png\" alt=\"\"/>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, true);
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
