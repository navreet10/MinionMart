/**
 * 
 */
$(document).ready(function() {
	
	
	$('.helpful').click(function (event) {
	
		 var idItem = $(this).attr('id');
		 var id = idItem.substring(7);
		 var dataString ='reviewid='+ id;
		 $.ajax({  
			    type: "POST",  
			    url: "Helpful",
           data: dataString,
           success: function(data){
        	   $('.nothelpful').hide();
               window.location = 'http://localhost:8080/MinionMart/productdetails.jsp';
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
