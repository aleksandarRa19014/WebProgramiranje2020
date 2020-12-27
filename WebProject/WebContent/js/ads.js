$(document).ready(function() {
	
	
	var role;
	
	var images = []; 
	
	var ads = [];
	
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
			
			ads = data;
			
			generatePage(role, data);
			
			
		
		}).fail(function( data, textStatus, jqXHR) {
			
			alert(data.responseText);
			
		});
	});

	function generatePage(role,ads){
		if(role == "admin"){
		
			
			$.each(ads,function(i,apart){
				
						$("#apartments").append(
								    "<div class='card mb-3' >"
								    +		"<img class='card-img-top' src=' " + apart.pathToImgs[0] + " ' alt=''>"
								    +		"<div class='card-body'>"
								    +			"<h5 class= 'card-title'>" + apart.nameApartment + "</h5>"
								    +			"<span class='apartment-price'> "+apart.price + " </span>"
								    +			"<ul id='aprtspc' class='clear-both apartment-details-list'>"
									+				"<li class='card-text' > <span>" + apart.nameApartment + "</span></li>"
									+				"<li class='card-text'><span>" + apart.typeApart + "</span></li>"
									+				"<li class='card-text'><span>" + apart.numRoom + "</li>"
									+				"<li class='card-text'><span>" + apart.numOfGuests + "</li>"		
									+				"<li class='card-text'><span>" + apart.location.address.street + "</span></li>"
									+			"</ul>"
								    +       	"<a href='' class='btn btn-primary'>Go somewhere</a>"
								    +       	"<a href='' class='btn btn-primary'>Go somewhere</a>"
								    +       "</div>"
								    +"</div>"	
					    );
						
						
			});
			
			$('.card').show();
			$('.card-body').show();
			
			$("#apartments").append("<br>");
		}else if(role == "host"){
			
			$.each(ads,function(i,apart){
				
				$("#apartments").append(
						    "<div class='card mb-3' >"
						    +		"<img class='card-img-top' src=' " + apart.pathToImgs[0] + " ' alt=''>"
						    +		"<div class='card-body'>"
						    +			"<h5 class= 'card-title'>" + apart.nameApartment + "</h5>"
						    +			"<span class='apartment-price'> "+apart.price + " </span>"
						    +			"<ul id='aprtspc' class='clear-both apartment-details-list'>"
							+				"<li class='card-text' > <span>" + apart.nameApartment + "</span></li>"
							+				"<li class='card-text'><span>" + apart.typeApart + "</span></li>"
							+				"<li class='card-text'><span>" + apart.numRoom + "</li>"
							+				"<li class='card-text'><span>" + apart.numOfGuests + "</li>"		
							+				"<li class='card-text'><span>" + apart.location.address.street + "</span></li>"
							+			"</ul>"
						    +       	"<a href='' class='btn btn-primary'>Go somewhere</a>"
						    +       	"<a href='' class='btn btn-primary'>Go somewhere</a>"
						    +       "</div>"
						    +"</div>"	
			    );
						
						
			});
			
			$('.card').show();
			$('.card-body').show();
			
			$("#apartments").append("<br>");
			
			console.log(role);
		}else if(role == "guest"){
			console.log(role);
		}else if(role == "none"){
			console.log(role);
		}
		
		
	}
	
	
	function sortByPriceAsc(a, b) {
	    var aPrice = a.price;
	    var bPrice = b.price;
	    return ((aPrice < bPrice) ? -1 : ((aPrice > bPrice) ? 1 : 0));
	}

	function sortByPriceDesc(a, b) {
	    var aPrice = a.price;
	    var bPrice = b.price;
	    return ((aPrice > bPrice) ? -1 : ((aPrice < bPrice) ? 1 : 0));
	}
	
	
	
	$( "#sortPrce" ).change(function() {
		  
		  console.log($( "#sortPrce option:selected" ).text());
		  if ($( "#sortPrce option:selected" ).val() == 'asc')
		  {
			  ads.sort(sortByPriceAsc);
			  
			  $('.card').empty();
			  
			  generatePage(role, ads);
		  }else if($( "#sortPrce option:selected" ).val() == 'desc'){
			  
			  ads.sort(sortByPriceDesc);
			  
			  $('.card').empty();
			  
			  generatePage(role, ads);
		  }
		  
		});
	
	
	
	
});


