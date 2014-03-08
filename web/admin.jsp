<%@page import="com.classes.Product"%>
<%@page import="java.util.Vector"%>
<%@page import="com.classes.DBAccess"%>
<html>
    <head>
                     <title>Admin Page</title>
        <link rel="stylesheet" href="stylesheets/spstyle.css" type="text/css" media="all" />
    </head>
    <body>
        <script>
                            function usersShow(){
                            document.getElementById("manageproducts").setAttribute("hidden",true);
                            document.getElementById("userview").removeAttribute("hidden");
                            document.getElementById("userBtn").setAttribute("class","active");
                            document.getElementById("productBtn").setAttribute("class","#");
                        }
                         function productShow(){
                            document.getElementById("manageproducts").removeAttribute("hidden");
                            document.getElementById("userview").setAttribute("hidden",true);
                             document.getElementById("userBtn").setAttribute("class","#");
                            document.getElementById("productBtn").setAttribute("class","active");
                        }
                        </script>
                        
          
     
                 
        <!-- Header -->
        <div id="header">
            <div class="shell">
                <!-- Logo + Top Nav -->
                <div id="top">
                    <h1><a href="#">SpringTime</a></h1>
                    <div id="top-navigation">
                        Welcome <a href="#"><strong>Administrator</strong></a>
                        <span>|</span>
                        <a href="#">Help</a>
                        <span>|</span>
                        <a href="#">Profile Settings</a>
                        <span>|</span>
                        <a href="#">Log out</a>
                    </div>
                </div>
                <!-- End Logo + Top Nav -->

                <!-- Main Nav -->
                <div id="navigation">
                    <ul>
                               <li><a href="#" class="active" onclick="productShow();" id="productBtn"><span>Manage Products</span></a></li>
                        <li><a href="#" onclick="usersShow();" id="userBtn"><span>Users</span></a></li>

                    </ul>
                </div>
                <!-- End Main Nav -->
            </div>
        </div>
        <!-- End Header -->

        <!-- Container -->
        <div id="container">
            <div class="shell">

                <!-- Small Nav -->
                <div class="small-nav">
                    <a href="#">Dashboard</a>
                    <span>&gt;</span>
                    Current Articles
                </div>
                <!-- End Small Nav -->

                <!-- Message OK -->		
                <div class="msg msg-ok">
                    <p><strong>Your file was uploaded successfully!</strong></p>
                    <a href="#" class="close">close</a>
                </div>
                <!-- End Message OK -->		

                <!-- Message Error -->
                <div class="msg msg-error">
                    <p><strong>You must select a file to upload first!</strong></p>
                    <a href="#" class="close">close</a>
                </div>
                <!-- End Message Error -->
                <br />
                <!-- Main -->
                <div id="main">
                    <div class="cl">&nbsp;</div>

                    <!-- Content -->
                    <div id="content">

                        <!-- Box -->
                        <div class="box" id="manageproducts">
                            <!-- Box Head -->
                            <div class="box-head">
                                <h2 class="left">Current Articles</h2>
                                <div class="right">
                                    <label>search articles</label>
                                    <input type="text" class="field small-field" />
                                    <input type="submit" class="button" value="search" />
                                </div>
                            </div>
                            <!-- End Box Head -->	

                            <!-- Table -->
                            <div class="table">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <th width="13"><input type="checkbox" class="checkbox" /></th>
                                        <th>Title</th>
                                        <th>Date</th>
                                        <th>Added by</th>
                                        <th width="110" class="ac">Content Control</th>
                                    </tr>
                                      <%
            try{DBAccess db = new DBAccess();
            Vector<Product> products = new Vector();
            products = db.getAllProducts();
            for (int i = 0; i < products.size(); i++) {
                %>
                <% boolean isSet = (request.getParameter("edit") == null);  %>
                <% if(!isSet) { %>
                <%if(products.get(i).getId()==Integer.parseInt(request.getParameter("edit"))){%>
                 <td><input type="checkbox" class="checkbox" /></td>
                 <form action="query?save=1" method="post">
                                        <td><h3><input size="10" type="text" value="<% out.print( products.get(i).getName()) ; %>" name="name"></h3></td>
                                         <td><h3><input  type="text" value="<% out.print( products.get(i).getDesc()) ; %>" name="desc"></h3></td>
                                          <td><h3><input size="3" type="text" value="<% out.print( products.get(i).getPrice()) ; %>" name="price"></h3></td>
                                           <td><h3><input size="3" type="text" value="<% out.print( products.get(i).getQuantity()) ; %>" name="quantity"></a></h3></td>
                                           <td><h3><input size="3" type="hidden" value="<% out.print( products.get(i).getId()) ; %>" name="id"></a></h3></td>
                                           </br><td><input type="submit" value="save"></td>
                 
                <%}}else{ %>
                
                 <tr <% if(i%2==0){%>class="odd"<%}%>>
                                        <td><input type="checkbox" class="checkbox" /></td>
                                        <td><h3><a href="#"><% out.print( products.get(i).getName()) ; %></a></h3></td>
                                         <td><h3><a href="#"><% out.print( products.get(i).getDesc()) ; %></a></h3></td>
                                          <td><h3><a href="#"><% out.print( products.get(i).getPrice()) ; %></a></h3></td>
                                           <td><h3><a href="#"><% out.print( products.get(i).getQuantity()) ; %></a></h3></td>
                                            <td><h3><img src="<% out.print("/home/ashraf/images/"+products.get(i).getImage()) ; %>"></h3></td>
                         
                                           </br>
                                           <td><a href="<% out.print("query?id="+ products.get(i).getId()+"&del=1") ; %>" class="ico del">Delete</a><a href="<% out.print("query?edit="+ products.get(i).getId()); %>" class="ico edit" role="button" data-toggle="modal">Edit</a></td>
                                    </tr>
          
                    <%}
            }
            }catch(Exception ex){}
        %>
        
                                                         </table>


                                <!-- Pagging -->
                                <div class="pagging">
                                    <div class="left">Showing 1-12 of 44</div>
                                    <div class="right">
                                        <a href="#">Previous</a>
                                        <a href="#">1</a>
                                        <a href="#">2</a>
                                        <a href="#">3</a>
                                        <a href="#">4</a>
                                        <a href="#">245</a>
                                        <span>...</span>
                                        <a href="#">Next</a>
                                        <a href="#">View all</a>
                                    </div>
                                </div>
                                <!-- End Pagging -->

                            </div>
                            <!-- Table -->

                        </div>
                        <!-- End Box -->

                        <!-- Box -->
                        <div class="box" id="userview" hidden>
                            <!-- Box Head -->
                                <input type="text" name="search key">
                                <a class="button" href="admin.jsp?search=1"  />Search</a>
                                <div id="searchz">
                                    <% if(request.getParameter("search")!=null){%>
                                    <p>Content Goes Here</p>
                                      <script>
                               usersShow();
                           </script>
       
                                    <% } %>
                                </div>
                                <!-- End Form Buttons -->
                            
                        </div>
                        <!-- End Box -->

                    </div>
                    <!-- End Content -->
               
                    <!-- Sidebar -->
                    <div id="sidebar">

                        <!-- Box -->
                        <div class="box">

                            <!-- Box Head -->
                            <div class="box-head">
                                <h2>Management</h2>
                            </div>
                            <!-- End Box Head-->

                            <div class="box-content">
                                <a href="#" class="add-button"><span>Add new Article</span></a>
                                <div class="cl">&nbsp;</div>

                                <p class="select-all"><input type="checkbox" class="checkbox" /><label>select all</label></p>
                                <p><a href="#">Delete Selected</a></p>

                                <!-- Sort -->
                                <div class="sort">
                                    <label>Add New Product</label>
                                    <form action="query" method="post" enctype="multipart/form-data">
                                       Name: <input type="text" name="name" class="field">
                                       Description :     <input type="text" name="desc" class="field">
                                           Price :     <input type="text" name="price" class="field">
                                            Quantity :        <input type="text" name="quantity" class="field">
                                             photo :        <input type="file" name="pic" class="field">
                                                    <input type="submit" class="button" value="submit" />
                                                    </form>
                                   
                                </div>
                                <!-- End Sort -->

                            </div>
                        </div>
                        <!-- End Box -->
                    </div>
                    <!-- End Sidebar -->

                    <div class="cl">&nbsp;</div>			
                </div>
                <!-- Main -->
            </div>
        </div>
        <!-- End Container -->

        <!-- Footer -->
        <div id="footer">
            <div class="shell">
                <span class="left">&copy; 2010 - CompanyName</span>
                <span class="right">
                    Design by <a href="http://chocotemplates.com" target="_blank" title="The Sweetest CSS Templates WorldWide">Chocotemplates.com</a>
                </span>
            </div>
        </div>
        <!-- End Footer -->
<!--    this is edit    -->

  
    </body>
</html>
