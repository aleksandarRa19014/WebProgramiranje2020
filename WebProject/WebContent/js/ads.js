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
 
		
	

	function generatePage(role,ads){
		if(role == "admin"){
		
			
			$.each(ads,function(i,apart){
				
						$("#apartments").append(
								    "<div class='card mb-3' id='"+ apart.id +" '> "
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
									+       	"<button  class='btn btn-primary btn-edit'>Izmeni podatke</button>"
								    +       	"<button  class='btn btn-danger btn-delete'>Obrisi apartman</button>"
								    +       "</div>"
								    +"</div>"	
					    );
						
						
			});
			
			$('.card').show();
			$('.card-body').show();
			
			$("#apartments").append("<br>");
		}else if(role == "host"){
			
			$.each(ads,function(i,apart){
				
				console.log(apart);
				
				if (apart.status == 'active'){
					
					$("#apartments").append(
							"<div class='card mb-3' id='"+ apart.id +" '> "
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
							+       	"<button  class='btn btn-primary btn-edit'>Izmeni podatke</button>"
						    +       	"<button  class='btn btn-danger btn-delete'>Obrisi apartman</button>"
						    +       "</div>"
						    +"</div>"	
					);
					
					
				}else{
					$("#inactiveApartments").append(
							"<div class='card mb-3' id='"+ apart.id +" '> "
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
						    +       	"<button  class='btn btn-primary btn-edit'>Izmeni podatke</button>"
						    +       	"<button  class='btn btn-danger btn-delete'>Obrisi apartman</button>"
						    +       "</div>"
						    +"</div>"	
					);
					
					
				}	
						
			});
			
			
			
			$("#apartments").append("<br>");
			$("#inactiveApartments").append("<br>");
			
			
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
			  
			  $('.card').show();
			  $('.card-body').show();
		  }else if($( "#sortPrce option:selected" ).val() == 'desc'){
			  
			  ads.sort(sortByPriceDesc);
			  
			  $('.card').empty();
			  
			  generatePage(role, ads);
			  
			  $('.card').show();
			  $('.card-body').show();
		  }
		  
		});
	
	
	$( "#sortPrceInactive" ).change(function() {
		  
		  console.log($( "#sortPrceInactive option:selected" ).val());
		  if ($( "#sortPrceInactive option:selected" ).val() == 'asc')
		  {
			  ads.sort(sortByPriceAsc);
			  
			  $('.card').empty();
			  
			  generatePage(role, ads);
			  
			  $('.card').show();
			  $('.card-body').show();
			  
		  }else if($( "#sortPrceInactive option:selected" ).val() == 'desc'){
			  
			  ads.sort(sortByPriceDesc);
			  
			  $('.card').empty();
			  
			  generatePage(role, ads);
			  
			  $('.card').show();
			  $('.card-body').show();
		  }
		  
		});
	
	
	$("body").on("click", ".btn-delete", function(){
    	
    	var idApart = $(this).parents(".card").attr('id');
    	
    	$.ajax({
            url: '/WebProject/rest/apartmentService/?id=' + idApart,
            type: 'DELETE',
            contentType: 'application/json',
           
        }).done(function( data, textStatus, jqXHR) {
       	 	alert(data.responseText);
		}).fail(function( data, textStatus, jqXHR) {
			
			alert(data.responseText);
			
		});
    	
    	$(this).parents(".card").remove();
    	console.log(idApart);
    	

    });
	
	
	$("body").on("click", ".btn-edit", function(){
    	
    	var idApart = $(this).parents(".card").attr('id');
    	
    	var apartForChange;
    	$.each(ads,function(i,apart){
    		
    		if(apart.id = idApart){
    			apartForChange = apart;
    		}
    		
    	});
    	
    	window.location = "changeApartment.html?id=" +idApart;
    	

    });
	
	
	
	
	
});


