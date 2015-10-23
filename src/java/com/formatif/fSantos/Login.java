/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.formatif.fSantos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Génie Franklin
 */
public class Login extends HttpServlet {
    
    Connection con = null;
    Statement stm = null;
    String msg;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
         try {
            Class.forName("com.mysql.jdbc.Driver");            
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());           
        }
        
         try {
             
            con = DriverManager.getConnection("jdbc:mysql://localhost/ecole?user=root");
            stm = con.createStatement();
           if(isLogin(username,password)){ 
             HttpSession s = request.getSession(true);  
             s.setAttribute("username", username); 
            // s.setAttribute("password", password);
              RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
            r.forward(request, response);
            } else {
            msg = "Message de la servlet Login : 'Nom d'utilisateur et/ou mot de passe incorrect.'";
            request.setAttribute("message", msg);
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
            r.forward(request, response);
            }  
             
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
            
        } finally {
            out.close();
        }
    }
    //cette fonction permet de se connecter
     private boolean isLogin(String un, String pass) throws SQLException {
        ResultSet resultats = null;
        try {
            String req = "SELECT * FROM user WHERE numid ='" + un + "' AND mdp='" + pass + "'";
            resultats = stm.executeQuery(req);
        } catch (SQLException ex) {
         
        }
            return resultats.next();
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
