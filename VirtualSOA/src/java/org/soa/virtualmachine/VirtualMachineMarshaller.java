/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.virtualmachine;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Basem
 */
@WebServlet(name = "VMControls", urlPatterns = "/vmcontrol")
public class VirtualMachineMarshaller extends HttpServlet
{

    public String executeControl(String request, String parameter)
    {
        Method m;
        String result = "";
        try
        {
            m = VirtualMachineClient.getInstance().getClass().getDeclaredMethod(request, String.class);
            try
            {
                result = (String) m.invoke(VirtualMachineClient.getInstance(), parameter);
            } catch (IllegalAccessException ex)
            {
                Logger.getLogger(VirtualMachineMarshaller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex)
            {
                Logger.getLogger(VirtualMachineMarshaller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex)
            {
                Logger.getLogger(VirtualMachineMarshaller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchMethodException ex)
        {
            Logger.getLogger(VirtualMachineMarshaller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex)
        {
            Logger.getLogger(VirtualMachineMarshaller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public void parseAction(HttpServletRequest req, HttpServletResponse resp)
    {
        PrintWriter out = null;
        try
        {
            out = resp.getWriter();
//            out.println("<p>");
            Enumeration<String> att = req.getParameterNames();
            while (att.hasMoreElements())
            {
                String el = att.nextElement();
                String param = req.getParameter(el);
                if (!param.isEmpty())
                {
                    out.println(executeControl(el, param));
                }
            }

//            out.println("</p>");

        } catch (IOException ex)
        {
            Logger.getLogger(VirtualMachineMarshaller.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            out.close();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        handle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        handle(req, resp);
    }
    
    public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html;charset=UTF-8");
        parseAction(req, resp);
    }
}
