<%@ include file="../shared/flows-header.jsp" %>


			<div class="container">
			  
			   <div class="row">
	<!-- column to display the personal details!! -->
		<div class="col-sm-6">
	
			<div class="panel panel-primary">
				
				<div class="panel-heading">
					<h4>Personal Details</h4>
				</div>
			
				<div class="panel-body">
					<!-- code to display personal details -->
					<div class="text-center">
					
					    <h4>${registerModel.user.firstName}  ${registerModel.user.lastName}</h4>
					    
					    <h5>Email: ${registerModel.user.email} </h5>
					    <h4>Contact Number:  ${registerModel.user.contactNumber} </h4>
					    
					    <h4>Role: ${registerModel.user.role} </h4>
					
					</div>
					
					
				</div>
				<div class="panel-footer">
				   <!-- code to display the edit of personal details -->
				   <a href="${flowExecutionUrl }&_eventId_personal" class="btn btn-success">Edit</a>
				</div>
			
			</div>
					
		
		</div>
		
		<!-- column to display the address -->
		<div class="col-sm-6">
		
			<div class="panel panel-primary">
				
				<div class="panel-heading">
					<h4>Billing Address</h4>
				</div>
				<div class="panel-body">
					<!-- code to display communictaion address -->
					
					  <h4>${registerModel.billing.addressLineOne} </h4>
					  <h4>${registerModel.billing.addressLineTwo}</h4>
					    
					    <h5>Email: ${registerModel.billing.city} </h5>
					    <h4>Contact Number:  ${registerModel.billing.state} </h4>
					    
					    <h4>Role: ${registerModel.billing.country} </h4>
					     <h4>Role: ${registerModel.billing.postalCode} </h4>
					
				</div>
				<div class="panel-footer">
				   <!-- code to display the edit of address -->
				   <a href="${flowExecutionUrl }&_eventId_billing" class="btn btn-success">Edit</a>
				</div>
			
			</div>
		
		</div>
	
	</div>
	
	
	<!-- to provide the confirm button after displaying the address -->
	<div class="row">
		
		<div class="col-sm-4 col-sm-offset-4">
			
			<div class="text-center">
				
				<!-- anchor to move to the success page -->
				
				<a href="${flowExecutionUrl }&_eventId_submit" class="btn btn-success">Confirm</a>
			</div>
			
		</div>
		
	</div>
			 
			</div>					
		
<%@ include file="../shared/flows-footer.jsp" %>	
	