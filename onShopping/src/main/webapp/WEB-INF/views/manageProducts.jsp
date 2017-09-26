<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<div class="container">

   <div class="row"> 
   
   <c:if test="${not empty msg }" >
     
     <div class="col-xs-12">
     
        <div class="alert alert-success alert-dismissible">
        
            <button type="button" class="close" data-dismiss="alert">&times; </button>
            
                 ${msg}
        
        </div>
     
     
     </div>
       
   
   </c:if>
   
      <div class="col-md-offset-2 col-md-8 ">
      
         <div class="panel panel-primary">
            
             <div class="panel-heading">
             
                 <h4>Product Management</h4>
             
             </div>
             
             <div class="panel-body">
               
                 <!-- Form Elements -->
                 <f:form class="form-horizontal" modelAttribute="product" 
                    action="${contextRoot}/manage/products" 
					method="POST" enctype="multipart/form-data">
                     
                     <div class="form-group">
                     
                        <label class="control-label col-md-4" for="name">Enter Product Name</label>
                      
                        <div class="col-md-8">
                        
                           <f:input class="form-control" type="text" path="name" id="name" placeholder="Product name" />
                           
                           <!--<em class="help-block">Please Enter Product Name</em>  -->
                           <f:errors path="name" cssClass="help-block" element="em"></f:errors>
                           
                        </div>
                                                
                     </div>
                     
                     <div class="form-group">
                     
                        <label class="control-label col-md-4" for="brand">Enter Brand Name</label>
                      
                        <div class="col-md-8">
                        
                           <f:input class="form-control" type="text" path="brand" id="brand" placeholder="Brand name" />
                           
                           <!-- <em class="help-block">Please Enter Brand Name</em> -->
                           <f:errors path="brand" cssClass="help-block" element="em"></f:errors>
                           
                        </div>
                                                
                     </div>
                     
                     <div class="form-group">
							<label class="control-label col-md-4" for="description">Product Description</label>
							<div class="col-md-8">
								<f:textarea path="description" id="description" rows="4" class="form-control" placeholder="Enter your description here!" /> 
								<f:errors path="description" cssClass="help-block" element="em"></f:errors>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="unitPrice">Unit Price</label>
							<div class="col-md-8">
								<f:input type="number" path="unitPrice" id="unitPrice" class="form-control" placeholder="Unit Price in &#8377;" />
							     <f:errors path="unitPrice" cssClass="help-block" element="em"></f:errors>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Quantity Available</label>
							<div class="col-md-8">
								<f:input type="number" path="quantity" id="quantity" class="form-control" placeholder="Quantity Avialable" />
							
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="file">Select an Image: </label>
							<div class="col-md-8">
								<f:input type="file" path="file" id="file"  class="form-control"/>
								<f:errors path="file" cssClass="help-block" element="em"/> 
							</div>
						</div>

                     
                     <div class="form-group">
							<label class="control-label col-md-4" for="categoryId">Select Category :</label>
							<div class="col-md-8">
								<f:select class="form-control" path="categoryId" id="categoryId" 
								   items="${categories}"
								   itemLabel="name"
								   itemValue="id"
					/>
							<c:if test="${product.id == 0}" >
								<div class="text-right">
								  <br/>
								  <button type="button" data-toggle="modal" data-target="#myCategoryModal" class="btn btn-warning btn-xs">Add Category</button>
								</div>
								 </c:if> 
							</div>
						</div>
                     
                     <div class="form-group">
                    
                       <div class="col-md-offset-4 col-md-8">
                        
                           <input type="submit" name="submit" id="submit" value="Submit" class="btn btn-primary"  />
                           
                           
                           <!-- hidden fields for products -->
                           <f:hidden path="id" />
                           <f:hidden path="code" />
                           <f:hidden path="supplierId" />
                           <f:hidden path="views" />
                           <f:hidden path="purchases"/> 
                           <f:hidden path="views" />
                                                  
                       </div>
                                                
                       </div>
                                      
                 </f:form>
           
             </div>
         
         
         </div>
      
      
      </div> 
    
   
   </div>
 
 
 <hr/>
 
 <div class="row"> 
   
      <div class="col-xs-12">
         <h1>Available Products</h1>
         <hr/>
      </div>
 
      <div class="col-xs-12">
           <div style="overflow: auto;">
           
              <!-- product table for admin -->
         
			<table id="adminproductsTable" class="table table-condensed table-bordered">
							
				<thead>					
					<tr>					
						<th>Id</th>
						<th>&#160;</th>
						<th>Name</th>
						<th>Brand</thBrand>
						<th>Qty. Avail</th>
						<th>Unit Price</th>
						<th>Activate</th>				
						<th>Edit</th>
					</tr>				
				</thead>
				
				
				
				<tfoot>
					<tr>					
						<th>Id</th>
						<th>&#160;</th>
						<th>Name</th>
						
						<th>Qty. Avail</th>
						<th>Unit Price</th>
						<th>Activate</th>				
						<th>Edit</th>
					</tr>									
				</tfoot>
				
							
			</table>
           
   
            </div>
 
      </div>
 
 </div>

  <div class="modal fade" id="myCategoryModal" role="dialog" tabindex="-1">
      <div class="modal-dialog" role="document">
          <div class="modal-content">
          <!-- modal header -->
             <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal">
                    <span>&times; </span>
                 </button>
                 <h4 class="modal-title">Add new Catgeory</h4>
             </div>
             <div class="modal-body">
               <!-- catgeory form -->
                <f:form id="categoryForm" modelAttribute="category" action="${contextRoot}/manage/category" method="POST" class="form-horizontal">
                   
                    <div class="form-group">
                       <label for="name" class="control-label col-md-4">Catgeory name</label> 
                        <div class="col-md-8">
                          <f:input path="name" type="text" id="name" class="form-control"/>                       
                        </div>
                    </div>     
                    
                    <div class="form-group">
                       <label for="description" class="control-label col-md-4">Catgeory Description</label> 
                        <div class="col-md-8">
                          <f:textarea  rows="4" path="description"  class="form-control"/>                       
                        </div>
                    </div>    
                    
                    <div class="form-group">
                       <div class="col-md-offset-4  col-md-8">
                          <input type="submit" value="Add Category" class="btn btn-primary" />
                       </div>
                    </div>
                                
                </f:form>
             
             </div>
          </div>
      </div>
  </div>
</div>