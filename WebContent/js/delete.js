/**
 * 
 */
$(document).ready(function() {
	
	$('.delete').click(function (event) {
		     
		 var idItem = $(this).attr('id');
		 var id = idItem.substring(6);
		 var dataString ='cartid='+ id;
		 $.ajax({  
			    type: "POST",  
			    url: "DeletefromCart",
           data: dataString,      
           success: function(data){
        	   alert("Delete from cart successfully!");
               window.location = 'http://localhost:8080/MinionMart/viewCart.jsp';
             }    
		 
			  });
		
	 
	 
});            
	
		
	 
	             
		  
	
});
