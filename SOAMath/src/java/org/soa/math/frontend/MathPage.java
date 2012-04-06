/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.frontend;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.soa.math.decomposer.MathParser;

/**
 *
 * @author Basem
 */
@WebServlet(name = "MathPage", urlPatterns = "/math")
public class MathPage extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        processCalculationRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
         processCalculationRequest(req, resp);
    }

    protected void processCalculationRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        String expression = req.getParameter("math_expression");
        String result = MathParser.processEquation(expression);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().printf("<h1>operation result for %s = %s </h1>", expression, result);
    }
}
