$(document).ready(function(){
	
	params = new URLSearchParams(window.location.search);

	console.log(params.get('id'));
	
	var idApart = params.get('id');
	
	var availableDates = [];
	
	$("#nameApart").text("lesa");
	
	$.ajax({
	  	url: '/WebProject/rest/apartmentService/getApart?id=' + idApart,
		type: "GET",
		contentType: "application/json",
		
	  
	}).done(function( data, textStatus, jqXHR ) {
	
		    console.log(data.nameApartment)
		     
		    $("#nameApart").text(data.nameApartment);
		    
		   
		    
		    var dateStr; 
		    $.each(data.datesAvailable,function(i,item){
		    	
		    	d = item.dayOfMonth;
			    m = item.monthValue;
			    y = item.year;
		    	
			    
			    
			    dateStr =  y + "-" + m + "-" + d; 
			    
			    availableDates.push(dateStr);
		    	console.log(item);
			
			
			});
		    
		    
		    
		   
		    
		    /*  $("#typeApart").val(data.typeApart.trim()).change();
		    
		    $("#statusApart").val(data.status.trim()).change();
		    
		    $("#numbRoom").attr("value",data.numRoom); 
		    $("#numbGuests").attr("value",data.numOfGuests);
		    $("#price").attr("value",data.price);
		    
		    
		    
		    $("#address").attr("value",data.location.address.street); 
		    $("#place").attr("value",data.location.address.place);
		    $("#zipCode").attr("value",data.location.address.zipCode);
		    
		    $("#geoWid").attr("value",data.location.latitude.toString());
		    $("#geoLen").attr("value",data.location.longitude.toString());
		    
		   
		    
		    
		    console.log(data.datesForRent[0].year);
		    console.log(data.datesForRent[data.datesForRent.length-1]);
		    
		    d = data.datesForRent[0].dayOfMonth;
		    m = data.datesForRent[0].monthValue;
		    y = data.datesForRent[0].year;
		    
		    
		    
		    
		    
		    var dateString1 = y + "-" + (m <= 9 ? '0' + m : m) + "-" + (d <= 9 ? '0' + d : d); 
		   
		   
		   
		    console.log(data.datesForRent[data.datesForRent.length-1]);
		    
		    d = data.datesForRent[data.datesForRent.length-1].dayOfMonth;
		    m = data.datesForRent[data.datesForRent.length-1].monthValue;
		    y = data.datesForRent[data.datesForRent.length-1].year;
		   
		    var dateString2 = y + "-" + (m <= 9 ? '0' + m : m) + "-" + (d <= 9 ? '0' + d : d); 
		    
		   
		    
		    $.each(data.datesForRent,function(i,item){
		    	
		    	
		    	console.log(item);
			
			
			});
		    
		    
		    $.each(data.amenites,function(i,item){
		    	
		    	
			
			
			});
			
			*/
		    $.each(data.amenites,function(i,item){
	    		$("#amanties").append(
	    				"<li>"+ item.name +"</li>" 

	    		);
				
			});
		    
		    
		    var i = 0;
		    $.each(data.pathToImgs,function(i,item){
		    	
		    	if (i == 0){
		    		$("#dataTarget").append("<li data-target='#myCarousel' data-slide-to="+ i + " class='active'></li>");
		    		
		    		$("#photoGalery").append(
		    					"<div class='item active'>"       
		    		          + 	" <img src="+ item +" style='width:100%; height: 300px'  alt='"+ i+ "'>"
		    		          + "</div>" 

		    		);
		    	}else{
		    		
		    		$("#dataTarget").append("<li data-target='#myCarousel' data-slide-to="+ i + "></li>");
		    		
		    		$("#photoGalery").append(
		    					"<div class='item'>"       
		    		          + 	" <img src="+ item +" style='width:100%; height: 300px' alt='"+ i+ "'>"
		    		          + "</div>" );
		    	}
		    		
		    	
		    	
		    	i++;
			});
		    if(data.pathToImgs != null){
		    	
		    	$("#btnClear").show();
		    }
		    	
		      
		     
	}).fail(function() {
	    
	});
	
	function available(date) {
	    dmy = date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();
	    if ($.inArray(dmy, availableDates) != -1) {
	        return [true, "","Available"];
	    } else {
	        return [false,"","unAvailable"];
	    }
	}
	
	
	$('#resApart').on('click', function() {
		$('#forma').validate({
			
			  rules: {
				  datepicker: {
				        required: true
			      },
			      
			      nubNights: {
				        required: true,
				        digits: true
				  }
		     
			  },
			  messages: {
				  
				  datepicker: { 
					  required: "Datum pocetka rezervacije je obavezan."
				  },
				  nubNights: { 
					  required: "Broj soba je obavezan.",
					  digits: 	"Unesite ceo broj."  
				  }
			  }
			  ,
			  submitHandler: function(form,event) {
				  		
				  
					  event.preventDefault();
					  
					  
					  console.log("---------LESA----------");
					  var s = JSON.stringify({
						  idApartment: idApart,
						  bookingDate: $("#datepicker").val(),
						  
						  numOfNights: $("#nubNights").val(),
						  text : $("#messageHost").val()   
					  });
					  
					  $.ajax({
							url: "/WebProject/rest/reservation/createRes",  // izmeni url
							type: "POST",
							contentType: "application/json",	
							data: s,
							dataType: "JSON"	
					  }).done(function( data, textStatus, jqXHR) {
						   
						  alert(textStatus);
							
						}).fail(function( data, textStatus, jqXHR) {
							alert(data.responseText);
						});
					  
					  
			  }
		
		});	
    	
    });
	
	$( "#datepicker" ).datepicker({ 
		showOtherMonths: true,
		selectOtherMonths: true,
		dateFormat: 'yy-mm-dd',
	    beforeShowDay: available
	});
	
	
   var totalItems = $('.item').length;
   var currentIndex = $('div.item.active').index() + 1;

   var down_index;
  
   $(".next").click(function() {
	   currentIndex_active = $('div.item.active').index() + 2;
	   if (totalItems >= currentIndex_active) {
		   down_index = $('div.item.active').index() + 2;
		   $('.num').html('' + currentIndex_active + '/' + totalItems + '');
	   }
   });

   $(".prev").click(function() {
	   down_index = down_index - 1;
	   if (down_index >= 1) {
		   $('.num').html('' + down_index + '/' + totalItems + '');
	   }
   });

});
