
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class query extends HttpServlet {

    String databaseName = "commerce";
    String databaseUsername = "root";
    String databasePassword = "1234";

    Connection con = null;
    ResultSet rst = null;

    public Connection StartConnection() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, databaseUsername, databasePassword);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return con;
    }

    public void doGet(HttpServletRequest Request, HttpServletResponse response) throws ServletException, IOException {
        try {
           
if(Request.getParameter("edit")==null){
            con = StartConnection();
            boolean is_deleted = true;
            if (con != null) {

                PreparedStatement pst = con.prepareCall("Delete from product  where id = ?");
                pst.setInt(1, Integer.parseInt(Request.getParameter("id")));
                is_deleted = pst.execute();

                con.close();
            }

            response.sendRedirect("admin.jsp");
}else{response.sendRedirect("admin.jsp?edit="+Request.getParameter("edit"));}
        } catch (Exception e) {
           
            e.printStackTrace();
        }
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException , IOException
    {
         if(request.getParameter("save")!=null)
            {
                try{
                 con = StartConnection(); 
                 PreparedStatement pst = con.prepareCall("update  product set name=?,  price=?, quantity=?   where id = ?");
                pst.setString(1, request.getParameter("name"));
                pst.setString(2, request.getParameter("price"));
                pst.setString(3, request.getParameter("quantity"));
                pst.setInt(4, Integer.parseInt(request.getParameter("id")));
                 pst.execute();

                con.close();
            

            response.sendRedirect("admin.jsp");
                }catch(Exception e){}
            }else{
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        String price = request.getParameter("price");
        String quan = request.getParameter("quantity");
        try{
         con = StartConnection();
              if (con != null) {

                PreparedStatement pst = con.prepareCall("insert into product values(default,?,?,?,?,1,?)");
                pst.setString(1, name);
                pst.setString(2, desc);
                pst.setString(3, price);
                pst.setString(4, quan);
                pst.setString(5, "");
                pst.execute();

                con.close();
            }

            response.sendRedirect("admin.jsp");

        } catch (Exception e) {
           
            e.printStackTrace();
        }
    
    }}
}
