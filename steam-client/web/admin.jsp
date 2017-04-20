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
                <div class="contentthread">
                    <div class="contentthreadleft" style="opacity:0.8; background-color: #FFFFFF; color:black; min-height: 700px">
                        Report at the number of threads per discussion : 
                        <br/><br/>
                        <img src="/steam-client/BarChartServlet" width="100%" >
                    </div>
                    <div class="contentthreadright">
                    </div>
                </div><div style="clear:both;"></div>
            </div>
            <jsp:include page="footer.jsp" flush="true" />
        </div>
    </body>
</html>
