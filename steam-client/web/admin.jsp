<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="head.jsp" flush="true" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="header.jsp" flush="true" />
            <div class="content">
                <h3>Statistic Report</h3>
                <div class="contentthread" style="padding:15px; opacity:0.8; background-color: #FFFFFF; color:black;">
                        Report at the number of threads per discussion : 
                        <br/><br/>
                        <img src="/steam-client/BarChartServlet" width="100%" >
                    </div>
            </div>
            <jsp:include page="footer.jsp" flush="true" />
        </div>
    </body>
</html>
