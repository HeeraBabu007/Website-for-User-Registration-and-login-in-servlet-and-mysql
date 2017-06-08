import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Student_Registration extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
    
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            
            String n=request.getParameter("Name");
            int r=Integer.parseInt(request.getParameter("Rollno"));
            String e=request.getParameter("Email");
            String p=request.getParameter("password");
          
       
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ims2", "root", "root");
            
            PreparedStatement ps =con.prepareStatement("insert into student values(?,?,?,?)");
            
            ps.setInt(1, r);
            ps.setString(2, n);
            ps.setString(3, e);
            ps.setString(4, p);
            
            int check=ps.executeUpdate();
            if(check>0)
            {
                out.println("<h1>Student registration successfull.</h>");
            }
            
        } 
        catch (ClassNotFoundException ex) 
        {
            
         out.println("<h1>Driver class not found</h1>");
        } 
        catch (SQLException ex) 
        {
            out.println("<h1>Connection object not created.</h>");
        }
  
    }

}
