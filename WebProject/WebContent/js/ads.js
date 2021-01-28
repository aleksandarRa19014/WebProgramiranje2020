$(document).ready(function() {
	
	
	var role;
	
	var images = []; 
	
	var ads = [];
	
	var tempAds = [];
	
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
	
	
	$.ajax({
	  	url: "/WebProject/rest/AdminService/getAmenities",
		type: "GET",
		contentType: "application/json",
		dataType: "JSON"
	  
	}).done(function( data, textStatus, jqXHR ) {
		
		$.each(data,function(i,item){
			
			$("#multiselect").append(
					"<option id='" +item.id+ "' value='"+item.name +"' >" + item.name + "</option>"
			
			);
			
			
		});

	}).fail(function() {
	    
	});
		
	

	function generatePage(role,ads){
		
		if (ads.length > 0){
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
				
				
		
				
				
				console.log(role);
			}else if(role == "guest"){
				console.log(role);
				$.each(ads,function(i,apart){
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
									+       	"<button  class='btn btn-primary btn-res'>Rezervisi apartman</button>"
								    +       "</div>"
								    +"</div>"	
							);				
						
					}
				});
			}else if(role == "none"){
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
							
						    +       "</div>"
						    +"</div>"	
					);
				
				});
				
				
				console.log(role);
			}
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
	
	function hasAmenity(arr, arr2){
		  return arr.every(i => arr2.includes(i));
	}
	
	$("#filterButton").click(function(e) {
			
		filterAds = [];
		
		tempAds = [];
		
		typeApart = $( "#filterType option:selected" ).val();
		
		statusApart = $( "#filterStatus option:selected" ).val();
		
		if (typeof statusApart === 'undefined'){
			statusApart = "none";
		}
		
		$("#sortPrce").val("none").change();
		
		selectedAmanties = [];
		
		$('#multiselect :selected').each(function(i, sel){  
		    selectedAmanties.push($(sel).val());

		});

		$.each(ads,function(i,apart){
    		apartAmant = [];
    		
    		$.each(apart.amenites,function(i,amnt){
    			apartAmant.push(amnt.name);
    			
    		});
    		
			if ( (hasAmenity(selectedAmanties,apartAmant)  || selectedAmanties.length == 0) && (typeApart.trim() == apart.typeApart || typeApart.trim() == 'none') && (statusApart.trim() == apart.status || statusApart.trim() == 'none') ){
				
				console.log("IMA")
				
				filterAds.push(apart);
			}
			
    	});
		
		tempAds = filterAds;
	  $('.card').empty();
	  
	  generatePage(role, filterAds);
	  
	  $('.card').show();
	  $('.card-body').show();
		
		
	});
	
	$("#resetButton").click(function(e) {
		
		  $('.card').empty();
		  
		  generatePage(role, ads);
		 
		  tempAds = ads;
		  
		  $("#multiselect")[0].selectedIndex = -1;
		  
		  $("#sortPrce").val("none").change();
		  
		  $('.card').show();
		  $('.card-body').show();
	});
	
	$( "#sortPrce" ).change(function() {
		  sortAds = [];
		  
		  if(tempAds.length != 0) {
			  sortAds = tempAds;
		  }else{
			  sortAds = ads;
		  }
			  
		  
		  console.log($( "#sortPrce option:selected" ).text());
		  if ($( "#sortPrce option:selected" ).val() == 'asc')
		  {
			  sortAds.sort(sortByPriceAsc);
			  
			  $('.card').empty();
			  
			  generatePage(role, sortAds);
			  
			  $('.card').show();
			  $('.card-body').show();
		  }else if($( "#sortPrce option:selected" ).val() == 'desc'){
			  
			  sortAds.sort(sortByPriceDesc);
			  
			  $('.card').empty();
			  
			  generatePage(role, sortAds);
			  
			  $('.card').show();
			  $('.card-body').show();
		  }
		  
		});
	
	
	$( "#sortPrceInactive" ).change(function() {
		
		 if(tempAds.length != 0) {
			  sortAds = tempAds;
		  }else{
			  sortAds = ads;
		  }
		  
		  console.log($( "#sortPrceInactive option:selected" ).val());
		  if ($( "#sortPrceInactive option:selected" ).val() == 'asc')
		  {
			  sortAds.sort(sortByPriceAsc);
			  
			  $('.card').empty();
			  
			  generatePage(role, sortAds);
			  
			  $('.card').show();
			  $('.card-body').show();
			  
		  }else if($( "#sortPrceInactive option:selected" ).val() == 'desc'){
			  
			  sortAds.sort(sortByPriceDesc);
			  
			  $('.card').empty();
			  
			  generatePage(role, sortAds);
			  
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
	
	
	
	$("body").on("click", ".btn-res", function(){
    	
    	var idApart = $(this).parents(".card").attr('id');
    	
    	window.location = "apartmentRes.html?id=" +idApart;
    	

    });
	
	
	class SearchDto {
	    constructor(startDate, endDate, minPrice, maxPrice, minRoomNo, maxRoomNo, personNo, place) {
	        this.startDate = startDate;
	        this.endDate = endDate;
	        this.minPrice = minPrice;
	        this.maxPrice = maxPrice;
	        this.minRoomNo = minRoomNo;
	        this.maxRoomNo = maxRoomNo;
	        this.personNo = personNo;
	        this.place = place;

	    }
	}
	
	 $("#searchButton").click(function() {

	        startD = ""
	        if ($("#startdate").val() != "") {
	            startDate = new Date($("#startdate").val())
	            var tzoffset = (new Date()).getTimezoneOffset() * 60000;
	            var localISOTime = (new Date(startDate - tzoffset)).toISOString()
	            startD = localISOTime.split("T")[0]
	        }

	        endD = ""
	        if ($("#endDate").val() != "") {
	            endDate = new Date($("#endDate").val())
	            var tzoffset = (new Date()).getTimezoneOffset() * 60000;
	            var localISOTime = (new Date(endDate - tzoffset)).toISOString()
	            endD = localISOTime.split("T")[0]
	        }

	        searchObject = new SearchDto(
	            startD,
	            endD,
	            $("#minPrice").val(),
	            $("#maxPrice").val(),
	            $("#minRoomNumb").val(),
	            $("#maxRoomNumb").val(),
	            $("#guestNumber").val(),
	            $("#dest").val()
	        )
	        console.log(searchObject)
	        $.ajax({
	            method: 'POST',
	            url: '/WebProject/rest/apartmentService/search',
	            contentType: 'application/json',
	            data: JSON.stringify(searchObject),
	        }).done(function( data, textStatus, jqXHR) {
	        	
	        	
	        	$.each(data,function(i,item){
	    			
	    	  		console.log(item.nameApartment);
	    	  		
	    		});
	        	 
				  
				  $('.card').empty();
				  
				  generatePage(role, data);
				  
				  $('.card').show();
				  $('.card-body').show();
			}).fail(function( data, textStatus, jqXHR) {
				
				alert(data.responseText);
				
			});
	    })
	
	
	
	
});


