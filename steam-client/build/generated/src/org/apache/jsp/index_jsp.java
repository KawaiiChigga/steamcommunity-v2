package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import client.JerseyClient;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
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
      out.write("                <h3 style=\"color: #66C0F4\">Welcome to the Steam Discussions</h3>\r\n");
      out.write("                <p><b>Everyone is invited!</b> The Steam discussions are for everyone, new and \r\n");
      out.write("                    advanced user alike! </p>\r\n");
      out.write("                <p><b>Searching is key!</b> Before you post a question, use the forum search feature \r\n");
      out.write("                    to determine whether your topic has already been covered. <br/> </p>\r\n");
      out.write("                <p><b>Do not start flame wars!</b> If someone has engaged in behavior that is a detriment \r\n");
      out.write("                    to the message board -- spamming, flaming people, etc -- contact one of the forum moderators \r\n");
      out.write("                    or report the post. Flaming the offensive user will only increase the problem. \r\n");
      out.write("                    Harassment is not tolerated. </p>\r\n");
      out.write("\r\n");
      out.write("                <form action=\"search.jsp\" method=\"get\">\r\n");
      out.write("                    <input type=\"text\" name=\"Searching\" placeholder=\"Search discussions\" />\r\n");
      out.write("                    <input type=\"submit\" value=\"Search\" />\r\n");
      out.write("                </form>\r\n");
      out.write("\r\n");
      out.write("                <br/>\r\n");
      out.write("                <h3 style=\"color: #66C0F4\">Game Forums</h3>\r\n");
      out.write("                <div class=\"line\"></div><br>\r\n");
      out.write("                <div class=\"contenthomeleft\">\r\n");
      out.write("                    ");

                        JerseyClient jc = new JerseyClient();
                        JSONArray data = (JSONArray) JSONValue.parse(jc.getAllDiscussion());
                        for (int i = 0; i < data.size(); i++) {
                            JSONObject temp = (JSONObject) data.get(i);
                            Long id = (Long) temp.get("discussionid");
                            String gamename = (String) temp.get("gamename");
                            String imgurl = (String) temp.get("imgurl");
                            
      out.write("\r\n");
      out.write("     \r\n");
      out.write("                            <div class=\"discussionbar\">\r\n");
      out.write("                                <div class=\"discussionbarlogo\" style=\"\r\n");
      out.write("                                    background-image: url('image/games/");
      out.print(imgurl);
      out.write("');\r\n");
      out.write("                                    background-repeat: no-repeat;\r\n");
      out.write("                                    background-size: contain;\">\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <a href=\"thread.jsp?id=");
      out.print(id);
      out.write("\" class=\"discussiontitle\">");
      out.print(gamename);
      out.write("</a>\r\n");
      out.write("                                <a href=\"thread.jsp?id=");
      out.print(id);
      out.write("\" class=\"viewall\">VIEW ALL</a>\r\n");
      out.write("                            </div>\r\n");
      out.write("                    ");

                        }
                    
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("                    ");

                        String ses = (String) request.getSession().getAttribute("currentsession");
                        
                        
                                //CtrlAccount.getUser((Integer) session.getAttribute("currentsession"));
                        if (ses != null) {
                            JSONObject u = (JSONObject) JSONValue.parse(jc.getUser(ses));
                            if (u.get("userid").toString().equals("1")) {
                    
      out.write("\r\n");
      out.write("                            <div class=\"contenthomeright\">\r\n");
      out.write("                                <a href=\"addDiscussion.jsp\"><input type=\"submit\" class=\"btn btn-default\" style=\"background-color:rgb(27,40,56); margin-top:5px;color:white; float:right; font-family: lato; font-size: 16px;\" value=\"Create New Discussion\"></a>\r\n");
      out.write("                            </div>\r\n");
      out.write("                    ");

                            }
                        }
                    
      out.write("\r\n");
      out.write("                \r\n");
      out.write("            </div>\r\n");
      out.write("            \r\n");
      out.write("        </div>\r\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, true);
      out.write("\r\n");
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
