$(function() {
	// solving the active menu problem
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	case 'User cart':
		$('#userCart').addClass('active');
		break;
	default:
		if (menu == "Home")
			break;
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;

	}
	
	// for handling CSRF token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	
	if(token.length > 0 && header.length > 0)
		{
		// set the token header for the ajax request
		$(document).ajaxSend(function(e, xhr, options) {			
			xhr.setRequestHeader(header,token);			
		});				
		  
		}
	

//code for jquery datatable

     var $table = $('#productListTable');
 
     //execute the below code only where we have this table
     
     if ($table.length) {
 		// console.log('Inside the table!');

 		var jsonUrl = '';
 		if (window.categoryId == '') 
 		{
 			jsonUrl = window.contextRoot + '/json/data/all/products';
 		} else 
 		{
 			jsonUrl = window.contextRoot + '/json/data/category/'+ window.categoryId + '/products';
 		}

    	 
    	 $table.DataTable ({
    		 
    		 lengthMenu: [[3,5,10,-1], ['3 records','5 records' ,'10 records ','all ']],
    		 pageLength : 5,
    		 ajax : {
					url : jsonUrl,
					dataSrc : ''
				},
    			 columns: [
    				 {
    					data : 'code',
    					mRender : function(data,type,row)
    					{
    						return '<img src="'+window.contextRoot +'/resources/images/'+data+'.jpg" class="dataTableImg" />' ;
    					}
    				 },
    				 {
    				   data : 'name'
    				 },
    				 {
      				   data : 'brand'
      				 },
      				 {
      				   data : 'unitPrice',
      				   mRender :  function(data,type,row)
      				   {
      					   return '&#8377; ' + data; 
      				   }
      				 },
      				 {
      				   data : 'quantity',
      				   mRender : function(data,type,row)
      				   {
      					   if(data <1)
      						   {
      						      return '<span style="color:red">Out OF Stock! </span> ';
      						   }
      					   return data;
      					   
      				   }
      					   
      				 },
      				 {
      					 data : 'id',
      					 bSortable : false,
      					 mRender : function(data,type,row)
      					 {
      						 var str = '';
      						str +='<a href="'+window.contextRoot+'/show/ '+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160; ';
      						
      						if(userRole == 'ADMIN')
      							{
      							 str +='<a href="'+window.contextRoot+'/manage/ '+data+'/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>' ;
      							}
      						else
      							{
      						if(row.quantity < 1)
      							{
      							str +='<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
      							}
      						else
      							{
      							
   								 str +='<a href="'+window.contextRoot+'/cart/add/ '+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>' ;
      								         						    							
      							}
      						
      							}
      						
      						return str;
      						 
      					 }
      				 }
    			       
    				 ]
    		 
    		 
    	 });
    	 }

     
     //dismssing alert after 3 seconds
     
     var $alert= $('.alert');
     
     if($alert.length)
    	 {
    	     setTimeout(function()
    	    		 {
    	    	 
    	    	          $alert.fadeOut('slow');
    	    	 
    	    		 },3000)
    	   
    	 }
     
     // toggle button 
   
     // data table for admin
     //--------------------------------------------------------------------------------------------------------------------
     var $adminProductstable = $('#adminproductsTable');
     
     //execute the below code only where we have this table
     
     if ($adminProductstable.length) {
 		// console.log('Inside the table!');

 		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
 		
    	 
 		$adminProductstable.DataTable ({
    		 
    		 lengthMenu: [[10,30,50,-1], ['10 records','30 records' ,'50 records ','all ']],
    		 pageLength : 30,
    		 ajax : {
					url : jsonUrl,
					dataSrc : ''
				},
    			 columns: [
    				 {
    					data: 'id', 
    				 },
    				 {
    					data : 'code',
    					mRender : function(data,type,row)
    					{
    						return '<img src="'+window.contextRoot +'/resources/images/'+data+'.jpg" class="adminDataTableImg" />' ;
    					}
    				 },
    				 {
    				   data : 'name'
    				 },
    				 {
      				   data : 'brand'
      				 },
      				
      				 {
      				   data : 'quantity',
      				   mRender : function(data,type,row)
      				   {
      					   if(data <1)
      						   {
      						      return '<span style="color:red">Out OF Stock! </span> ';
      						   }
      					   return data;
      					   
      				   }
      					   
      				 },
      				 {
        				   data : 'unitPrice',
        				   mRender :  function(data,type,row)
        				   {
        					   return '&#8377; ' + data; 
        				   }
        				 },
      				 {
      					 data : 'active',
      					 bSortable:false,
      					 mRender:function(data,type,row)
      					 {
      						 var str = '';
      						 
      						 str += '<label class="switch">' ;
      						 if(data)
      							 {
      							    str += '<input type="checkbox" checked="checked" value="'+row.id+'" />' ;
      							 }
      						 else
      							 {
      							 str += '<input type="checkbox"  value="'+row.id+'" />' ;
      							 }
      						 
   				            
   				              
				             str += '<div class="slider" ></div> </label> ' ;
				          
      						 return str;
      					 }
      						
      				 },
      				 {
      					 data: 'id',
      					bSortable:false,
      					mRender: function(data,type,row)
      					{
      						var str = '';
      						str += '<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning"> ';
   				            str +=  '<span class="glyphicon glyphicon-pencil"></span> </a>'  
   				                     
      						return str;
      						
      					}
      					 
      				 }
    			       
    				 ],
    				 
    				 initComplete: function()
    				 {
    					 var api =this.api();
    					 api.$('.switch input[type="checkbox"]').on('change',function ()
    				    		 {
    		    	           var checkbox= $(this);
    		    	           var checked= checkbox.prop('checked');
    		    	           var dmsg = (checked)? 'you want to activate your product?' : 
    		    	        	                      'you want to deactivate?' ;
    		    	           var value= checkbox.prop('value');
    		    	           
    		    	           
    		    	           bootbox.confirm({
    		    	        	   size: 'medium' ,
    		    	        	   title: 'Product Activation and deactivation' ,
    		    	        	   message: dmsg ,
    		    	        	   callback : function (confirmed)
    		    	        	       {
    		    	        		      if(confirmed)
    		    	        		    	  {
    		    	        		    	     console.log(value);
    		    	        		    	     
    		    	        		    	     var activationUrl=window.contextRoot + '/manage/product/' +value + '/activation' ;
    		    	        		    	     
    		    	        		    	     $.post(activationUrl,function(data)
    		    	        		    	    		 {
    		  	        		    	    
        		    	        		    	     bootbox.alert({
        		    	        		    	    	size: 'medium',
        		    	        		    	    	title: 'information',
        		    	        		    	    	 message: data
        		    	        		    	    	 
        		    	        		    	     });
                                        	 });
	    	       		    	        		    	    
    		    	        		    	  }
    		    	        		      else
    		    	        		    	  {
    		    	        		    	  
    		    	        		    	     checkbox.prop('checked',!checked);
    		    	        		    	  }
    		    	        		     
    		    	        	       }
    		    	        	   
    		    	        	   
    		    	           });
    		    	        
    		    		 });
    		     
    					 
    				 }
    		 
    		 
    	 });
    	 }
     
     
    //--------------------------------------------------------------------------------------------------------------------

   //validation code for category

     var $categoryForm = $('#categoryForm');

   if($categoryForm.length)
     {
	   $categoryForm.validate({
		  
		   rules: {
			   
			   name: {
				   
				   required: true,
				   minlength: 2				   
			   },
			   
			   description: {
				   
				   required: true
			   }
			   
		   },
		   
		   messages: {
			   
			 name: {
				 
				 required: 'Please add the category name!' ,
				 minlength: 'catgeory name should not be less than 2 characters'
			 },
            description: {
				 
				 required: 'Please add the category description!!' ,
				
			 } 
			   
		   },
		   errorElement: 'em',
		   errorPlacement: function(error,element){
			   //add the class of help-block
			   error.addClass('help-block');
			   //add the error element after the input eleemnt
			   error.insertAfter(element);
			   
		   }
		   
	   });
	   
     }
//-----------------------------------------------------------------------------------------------
   //validation code for login form

   var $loginForm = $('#loginForm');

 if($loginForm.length)
   {
	   $loginForm.validate({
		  
		   rules: {
			   
			   username: {
				   
				   required: true,
				   email: true			   
			   },
			   
			   password: {
				   
				   required: true
			   }
			   
		   },
		   
		   messages: {
			   
			 username: {
				 
				 required: 'Please enter the  username!' ,
				 email: 'enter valid email'
			 },
          password: {
				 
				 required: 'Please enter password!!' ,
				
			 } 
			   
		   },
		   errorElement: 'em',
		   errorPlacement: function(error,element){
			   //add the class of help-block
			   error.addClass('help-block');
			   //add the error element after the input eleemnt
			   error.insertAfter(element);
			   
		   }
		   
	   });
	   
   }
   
   
 //*************************************************************************************************
 
 //handling the click event of refresh cart button
 /* handle refresh cart*/	
 
 
	$('button[name="refreshCart"]').click(function(){
		var cartLineId = $(this).attr('value');
		var countField = $('#count_' + cartLineId);
		var originalCount = countField.attr('value');
		
		// do the checking only the count has changed
		if(countField.val() !== originalCount) {	
			// check if the quantity is within the specified range
			if(countField.val() < 1 || countField.val() > 3) {
				// set the field back to the original field
				countField.val(originalCount);
				
				bootbox.alert({
					size: 'medium',
			    	title: 'Error',
			    	message: 'Product Count should be minimum 1 and maximum 3!'
				});
			}
			else {
				// use the window.location.href property to send the request to the server
				var updateUrl = window.contextRoot + '/cart/' + cartLineId + '/update?count=' + countField.val();
				window.location.href = updateUrl;
			}
		}
	});			

 //*************************************************************************************************

});
