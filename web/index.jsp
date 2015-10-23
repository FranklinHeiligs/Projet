<%-- 
    Document   : index
    Created on : 2015-10-19, 21:56:28
    Author     : Génie Franklin
--%>

<%@page import="java.util.ListIterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style type="text/css">
            .errorMessage {color : red;}
            .resultat {font-weight: bold;}
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
         HttpSession s = request.getSession();
         Object login = null;
        if (request.getAttribute("message")!=null)
        {
            out.println("<p class=\"errorMessage\">"+request.getAttribute("message")+"</p>");
        }
        
        login = s.getAttribute("username");
         if (login !=null){%>
         <h1>Bienvenue <% out.println(request.getParameter("username")); %></h1>
        
       
         
        <%  out.println("<a href=\"./Logout?action=Logout\">déconnexion</a>"); %>
        
        <h1>Calcul Épicerie</h1>
       
        
        <form action="./calculateur" method="post"> <%-- 
        Lorsqu'on utilise un bouton il faut l'accompagner d'un form,
        l'action du "form" indique vers où on transfère les paramètres.        
        --%>           
        <table border="0">
         
            <tbody>
                <tr>
                    <td>Designation: <input type="text" name="designation" value="" /></td>
                </tr>
                <tr>
                    <td>Prix Unitaire:<input type="text" name="prixUnitaire" value="" /></td>
                </tr>
                <tr>
                    <td>Quantité: <input type="text" name="quantite" value="" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="calculer" /></td>
                </tr>
            </tbody>
        </table>
       </form>
       
        <% 
        if (request.getAttribute("msg")!=null)
        {
            out.println("<p class=\"errorMessage\">"+request.getAttribute("msg")+"</p>");
        }
        
        %>
        <%
        if (session!=null && request.getParameter("action") != null && request.getParameter("action").equals("efface"))
            {
                session.removeAttribute("calculs");
               // session = null;
            }
        if (session != null)
                {
                    ArrayList calc = (ArrayList)session.getAttribute("calculs");
                    if (calc!=null)
                    {
                        ListIterator it = calc.listIterator();
                        out.println("<h3>Historique des achats :</h3>");
                        out.println("(<a href=\"./index.jsp?action=efface\">Effacer l'historique</a>)<br />");
                        while (it.hasNext())
                            out.println(it.next()+"<br />");
                    }
                }
        
        
        %>
        <% }else{%>
             <form action="./Login" method="POST">
              username: <input type="text" name="username" value="" />
              password: <input type="password" name="password" value="" />
              <input type="submit" value="Se connecter" />
             
             </form>
 <%}%>
        <%-- <%
        if (request.getAttribute("resultat")!=null)
        { 
        
            //double x = ((Double)request.getAttribute("resultat")).doubleValue();
            out.println("<span class=\"designation\">"+request.getParameter("designation")+"</span>"+": "); 
            out.println("<span class=\"prix Unitaire\">"+request.getParameter("prixUnitaire")+"</span>");
            out.println(" * ");
            out.println("<span class=\"quantité\">"+request.getParameter("quantite")+"</span>");
            out.println(" = ");
            out.println("<span class=\"resultat\">"+request.getAttribute("resultat")+"$ </span>"+" taxes incluses");
        }
%> --%>
    </body>
</html>
