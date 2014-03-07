
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
 import org.apache.commons.fileupload.FileItem;
    import org.apache.commons.fileupload.FileUploadException;
   import org.apache.commons.fileupload.disk.DiskFileItemFactory;
   import org.apache.commons.fileupload.servlet.ServletFileUpload;
     import org.apache.commons.io.IOUtils;

public class query extends HttpServlet {

    String databaseName = "commerce";
    String databaseUsername = "root";
    String databasePassword = "1234";

    // hellow
    Connection con = null;
    ResultSet rst = null;
      String name ;
        String desc ;
        String price ;
        String quan ;

    public Connection StartConnection() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, databaseUsername, databasePassword);
        } catch (Exception ex) {
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
        String filename="";
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
     
          try{
         con = StartConnection();
              if (con != null) {
            
                  DiskFileItemFactory factory = new DiskFileItemFactory();
  // maximum size that will be stored in memory
   //  factory.setSizeThreshold(maxMemSize);
  // Location to save data that is larger than maxMemSize.
     factory.setRepository(new File("/home/ashraf/images"));  

  // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
  // maximum file size to be uploaded.
   //  upload.setSizeMax( maxFileSize );

     
  // Parse the request to get file items.
    PrintWriter s = response.getWriter();
     List<FileItem> fileItems = upload.parseRequest(request);
      // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
               // String fieldname = item.getFieldName();
                 
        name=fileItems.get(0).getString();
        desc=fileItems.get(1).getString();
        price=fileItems.get(2).getString().toString();
        quan=fileItems.get(3).getString().toString();
      //  s.print("name"+name);
              
       
       
            
     
    //   s.print(request.getParameter("name"));

  // Process the uploaded file items
      Iterator i = fileItems.iterator();

    
     while ( i.hasNext () ) 
     {
       FileItem fi = (FileItem)i.next();
     if ( !fi.isFormField () )  
     {
        // Get the uploaded file parameters
        String fieldName = fi.getFieldName();
        String fileName = fi.getName();
        String contentType = fi.getContentType();
        boolean isInMemory = fi.isInMemory();
        long sizeInBytes = fi.getSize();
        // Write the file
        File file;
         
        if( fileName.lastIndexOf("\\") >= 0 ){
            file = new File( "/home/ashraf/images/"+  fileName.substring( fileName.lastIndexOf("/"))) ;
            filename = "/home/ashraf/images/"+fileName;
        }else{
           file = new File( "/home/ashraf/images/"+  fileName.substring(fileName.lastIndexOf("/")+1)) ;
            filename = "/home/ashraf/images/"+fileName;
        }
         s.println("abl write");
        fi.write( file );
        
     }}
         s.println("ehhhhh");
       s.println(name+desc+price+quan+filename);
   
                PreparedStatement pst = con.prepareCall("insert into product values(default,?,?,?,?,1,?)");
                s.println("ehhhhh"+name+desc+price+quan+filename);
                pst.setString(1, name);
                 s.println("1"+name);
               
                pst.setString(2, desc);
                s.println("2"+desc);
               
                pst.setFloat(3, Float.parseFloat(price));
                s.println("3"+price);
               
                pst.setInt(4, Integer.parseInt(quan));
                s.println("4"+quan);
               
                pst.setString(5, filename);
                s.println("5"+filename);
               
                pst.execute();
                 s.println("ashroooooooooooooooooof"+name+desc+price+quan+filename);
        

                con.close();
                
            }
            response.sendRedirect("admin.jsp");

        } catch (Exception e) {
           
            e.printStackTrace();
        }
    
         } }     
}
