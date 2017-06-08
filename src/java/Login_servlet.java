/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HeeraBabu
 */
public class Login_servlet extends HttpServlet 
{


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            String n =request.getParameter("name");     
            String p=request.getParameter("password");
            
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims2","root","root");
            PreparedStatement ps=con.prepareStatement("select * from student where name=? and password=? ");  
 
            
            ps.setString(1,n);
            ps.setString(2,p);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next())
            {
                out.print("<h1>Thank for Login :) your record is there in our system.</h1>");
                RequestDispatcher rd=request.getRequestDispatcher("Welcome.html");  
                rd.forward(request,response);  
            }
            
            else
            { 
                out.print("<html><body>");
                out.print("</body></html>");
                out.print("<br>");
                out.print("<h3>Incorrect information or you are not registered.");
                out.print("<a href='Login.html'> Try again </a></h3>");
                out.print("<br>");
                out.print("<h3>Not registered ? Please ! Sign up <a href='Signup.html'>Click here to Signup</h3></a>");
         } 
            
        } 
        catch (ClassNotFoundException ex) 
        {
             out.println("<h1>Driver Not found</h1>");
            
        }
        catch (SQLException ex) 
        {
             out.println("<h1>Connection object Not found</h1>");
        }
    
    
    }


}
