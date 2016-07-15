$(document).ready(function() {
	
	$("#productlist").show();
	 $("#productdetails").hide();
	
	$('.addCart').click(function (event) {
	
		 var idItem = $(this).attr('id');
		 var id = idItem.substring(9);
		 var dataString ='prodId='+ id;
		 $.ajax({  
			    type: "POST",  
			    url: "AddToCart",
           data: dataString,
           success: function(data){
        	   alert("Add to cart successfully!");
               window.location = 'http://localhost:8080/MinionMart/Shopping.jsp';
             }                
			  });
		
	 
	 
});            
	$('.addWish').click(function (event) {
		 var idItem = $(this).attr('id');
		 var id = idItem.substring(9);
		 var dataString ='prodId='+ id;
		 
		 $.ajax({  
			    type: "POST",  
			    url: "AddToWish",
          data: dataString,
          success: function(data){
        	  alert("Add to Wish List successfully!");
              window.location = 'http://localhost:8080/MinionMart/Shopping.jsp';
            }                
			  });
	}); 
		 
		
	 
	             
		  
	
});