/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.formatif.fSantos;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
public class calculateur extends HttpServlet {

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
          DecimalFormat df = new DecimalFormat(".##");
          String d = request.getParameter("designation");
          String p = request.getParameter("prixUnitaire");
          String q = request.getParameter("quantite");
          double prix,
          qte,
          mHT,
          mTPS,
          mTVQ,
          mTot=0;
          if(d.equals("") || p.equals("") || q.equals(""))
           {
            String message = "veuillez remplir tous les champs";
            request.setAttribute("msg", message);
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
            r.forward(request, response);
           }
            
            try{
             prix = Double.parseDouble(p);
             qte = Double.parseDouble(q);
            }
            catch(Exception e)
            {
            String message = "Le calcul: "+p+" avec "+q+" est invalide";
            request.setAttribute("msg", message);
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
            r.forward(request, response);
            return;
            }
             
        
        
      
           if (d!=null)
        {
             
             mHT = prix*qte;
             mTPS = mHT*0.05;
             mTVQ = mHT*0.0975;
             mTot = mHT+mTPS+mTVQ;  
           
            
        }
           
         HttpSession session = request.getSession(true);
                ArrayList calc = (ArrayList)session.getAttribute("calculs");
                String result =""+d+": "+mTot+"$";
                if (calc==null)
                {
                    calc = new ArrayList();
                    calc.add(result);
                    session.setAttribute("calculs", calc); 
                }
                else
                {
                    calc.add(result);
                    session.setAttribute("calculs", calc);                    
                } 
               
            request.setAttribute("resultat", mTot);
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
            r.forward(request, response); 
            
            
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
