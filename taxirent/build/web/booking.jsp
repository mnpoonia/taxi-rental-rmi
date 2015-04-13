<%-- 
    Document   : booking
    Created on : Mar 12, 2015, 8:03:09 PM
    Author     : mnpoonia
--%>

<%@page import="cli.mnp.RMIClient"%>
<%@page import="java.sql.Date"%>
<%@page import="com.mnp.BookingInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            String mob = request.getParameter("mob");
            String email = request.getParameter("email");
            String from = request.getParameter("from");
            String to = request.getParameter("to");
            String date = request.getParameter("day");
            String mon = request.getParameter("month");
            String year = request.getParameter("year");
            //Registry myRegistry = LocateRegistry.getRegistry("$OPENSHIFT_JBOSSEWS_IP", 16099);
            //Registry myRegistry = LocateRegistry.getRegistry("localhost", 16099);
            // search for myMessage service
            //book b = (book) myRegistry.lookup("book");
            RMIClient cli = new RMIClient();

            BookingInfo info = new BookingInfo();
            Date d = Date.valueOf(year + "-" + mon + "-" + date);
            info.setMob_no(mob);
            info.setEmail(email);
            info.setFrom_Addr(from);
            info.setTo_addr(to);
            info.setDate(d);
            int id = cli.bookCab(info);
            if (id >= 0) {
                out.println("booing successful");
            } else {
                out.println("booking unsuccessful");
            }

        %>
    </body>
</html>
