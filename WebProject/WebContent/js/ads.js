$(document).ready(function() {
	
	
	var role;
	
	var images = []; 
	
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
	
	
	$("#getApartments").one( "click", function() {

		$.ajax({
		  	url: "/WebProject/rest/apartmentService/allApts",
			type: "GET",
			contentType: "application/json",
			dataType: "JSON"
		  
		}).done(function( data, textStatus, jqXHR ) {
			
			$.each(data,function(i,item){
				
		  		console.log(item.nameApartment);
		  		
			});
			
			generatePage(role, data);
		
		}).fail(function( data, textStatus, jqXHR) {
			
			alert(data.responseText);
			
		});
	});

	function generatePage(role,ads){
		if(role == "admin"){
			
			$.each(ads,function(i,apart){
				
						$("#apartments").append(
						"<div class='col-xs-12 col-sm-6 col-md-3 col-lg-3 apartment_box' id = 'crtApartments' data-apartment_price=' "+apart.price + " '  data-person_number='' data-room_number=' '>"
							+"<div class='thumbnail-box' >"
							+		"<div class='col-xs-5 col-sm-5 col-md-12 col-lg-12 no-padding-right no-padding-left'>"
							+				   "<a class='' href=''>"
							+                   "<img class='img-responsive' src='"+apart.pathToImgs[0] + "' > </a>"
							+		"</div>"
							+	  	"<div  class='col-xs-7 col-sm-7 col-md-12 col-lg-12 no-padding-right no-padding-left caption'>"
							+		    "<a href='https://noviapartmani.com/apartmani-beograd/strogi-centar/apartman-kolarac'>"
							+				"<div class='col-xs-7 col-sm-7 col-md-8 col-lg-9 no-padding-left no-padding-right'>"
							+					"<h3 class='apartment-name'>apartman Kolarac</h3>"
							+				"</div>"
							+			"</a>"
							+			"<div class='col-xs-5 col-sm-5'>"
							+				"<span class='apartment-price'> "+apart.price + " </span>"
							+			"</div>"
							+         "</div>"
							+  "</div>"
							+	"<ul class='clear-both apartment-details-list'>"
							+		"<li> <span>" + apart.nameApartment + "</span></li>"
							+		"<li><span>" + apart.typeApart + "</span></li>"
							+		"<li><span>" + apart.numRoom + "</li>"
							+		"<li><span>" + apart.numOfGuests + "</li>"		
							+		"<li><span>" + apart.location.address.street + "</span></li>"
							+	"</ul>"
					    	+ "</div>");
		  		
			});
			
			
			$("#apartments").append("<br>");
		}else if(role == "host"){
			console.log(role);
		}else if(role == "guest"){
			console.log(role);
		}else if(role == "none"){
			console.log(role);
		}
		
		
	}
	
	
});