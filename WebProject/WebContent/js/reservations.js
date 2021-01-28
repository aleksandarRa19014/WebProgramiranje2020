$(document).ready(function(){
	
	var reservations = [];
	
	var role;
	
	$.ajax({
	  	url: "/WebProject/rest/userService/currentUser",
		type: "GET",
		contentType: "application/json",
		dataType: "JSON"
	  
	}).done(function( data, textStatus, jqXHR ) {
		if (data != undefined)
           role = data.role;
        else { 
            role = "none";
        }
		

	}).fail(function() {
	    
	});
	
	$.ajax({
	  	url: "/WebProject/rest/reservation/getReservations",
		type: "GET",
		contentType: "application/json",
		dataType: "JSON"
	  
	}).done(function( data, textStatus, jqXHR ) {
		
		$.each(data,function(i,item){
			
	  		console.log(item.nameApartment);
	  		
		});
		
		reservations = data;
		
		generatePage(role, data);
		
		
	
	}).fail(function( data, textStatus, jqXHR) {
		
		alert(data.responseText);
		
	});
	
	
	function generatePage(role, reservs){
		if(reservs.length>0){
			$.each(reservs,function(i,res){
				
				$("#reservations").append(
						    "<div class='card mb-3' id='"+ res.idRes +" '> "
						    +		"<img class='card-img-top' src=' " + res.reservedApart.pathToImgs[0] + " ' alt=''>"
						    +		"<div class='card-body'>"
						    +			"<h5 class= 'card-title'>" + res.reservedApart.nameApartment + "</h5>"
						    +			"<span class='apartment-price'> "+res.price + " </span>"
						   
							
						    +       "</div>"
						    +"</div>"	
			    );
				
				
				});
		}
	}
	
	
});