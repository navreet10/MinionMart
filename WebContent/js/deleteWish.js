/**
 * 
 */
$(document).ready(function() {
	
	$('.delete').click(function (event) {
		     
		 var idItem = $(this).attr('id');
		 var id = idItem.substring(6);
		 var dataString ='wishid='+ id;
		 $.ajax({  
			    type: "POST",  
			    url: "DeletefromWish",
           data: dataString,      
           success: function(data){
        	   alert("Delete from wish list successfully!");
               window.location = 'http://localhost:8080/MinionMart/wishList.jsp';
             }    
		 
			  });
		
	 
	 
});            
	
		
	 
	             
		  
	
});
