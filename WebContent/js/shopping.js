$(document).ready(function() {
	$('.addCart').click(function (event) {
		 var idItem = $(this).attr('id');
		 var id = idItem.substring(9);
		 var dataString ='prodId='+ id;
		 
		 $.ajax({  
			    type: "POST",  
			    url: "AddToCart",
           data: dataString,
           success: function(data){
               window.location = 'http://localhost:8081/MinionMart/Shopping.jsp';
             }                
			  });
		
	 
	 
});            
	$('.addCart').click(function (event) {
		 var idItem = $(this).attr('id');
		 var id = idItem.substring(9);
		 var dataString ='prodId='+ id;
		 
		 $.ajax({  
			    type: "POST",  
			    url: "AddToCart",
          data: dataString,
          success: function(data){
              window.location = 'http://localhost:8081/MinionMart/Shopping.jsp';
            }                
			  });
		
	 
	 
});            
		  
	
})