/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Basem
 */
@WebServlet(name="MathPage", urlPatterns="/math")
public class MathPage extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        
    }
    
    protected String processCalculationRequest(String calculationRequest)
    {
        
        return "";
    }
}
