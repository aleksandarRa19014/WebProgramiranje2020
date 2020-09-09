$(document).ready(function() {
	
	
	 
	var images = [];
	
	var amenites = [];
	
	var user;
	
	$.ajax({
	  	url: "/WebProject/rest/userService/currentUser",
		type: "GET",
		contentType: "application/json",
		dataType: "JSON"
	  
	}).done(function( data, textStatus, jqXHR ) {
	
		      user = data;
		 
	}).fail(function() {
	    
	});
	
	
	$.ajax({
	  	url: "/WebProject/rest/AdminService/getAmenities",
		type: "GET",
		contentType: "application/json",
		dataType: "JSON"
	  
	}).done(function( data, textStatus, jqXHR ) {
		
		$.each(data,function(i,item){
			
			 $('#containerSadrzaj')
			 	.append(" <label for=' "+ item.name + " '>" + item.name + "</label></div> ") 
		        .append(" <input type='checkbox' id=' " + item.id +" ' name='interest' value="+ item.name +"> ")
		        
		        .append("<br>");
		    
			
			 
	  		 
			
		});

	}).fail(function() {
	    
	});
	
	
	
	
	
	 $.validator.addMethod("endDate", function(value, element) {
         var startDate = $('#checkindate').val();
         return Date.parse(startDate) <= Date.parse(value) || value == "";
     });
	
	
	$('#forma').validate({
		
		  rules: {
			  nameApart: {
			        required: true
		      },
		      typeApart: {
			        required: true
			  },
			  numbRoom: {
			        required: true,
			        digits: true
			  },
			  numbGuests: {
			        required: true,
			        digits: true
			  },
			  price: {
			        required: true,
			        digits: true
			  },
			  
			  address: {
			        required: true
		      },
		      place: {
			        required: true
		      },
		      zipCode: {
		    	  required: true,
                  minlength: 5,
                  maxlength: 5,
                  digits: true
		      },
		      
		      checkindate: {
			        required: true
		      },
		      checkoutdate: {
			        required: true,
			        endDate: "#checkindate"
		      },
	     
		  },
		  messages: {
			  
			  nameApart: { 
				  required: "Ime apartmana je obavezno."
			  },
			  typeApart: { 
				  required: "Odabir tipa apartmana je obavezan."
			  },
			  numbRoom: { 
				  required: "Broj soba je obavezan.",
				  digits: 	"Unesite ceo broj."  
			  },
			  numbGuests: { 
				  required: "Broj gostiju je obavezan.",
				  digits: 	"Unesite ceo broj."  
			  },
			  price: { 
				  required: "Cena nocenja je obavezana.",
				  digits: 	"Unesite ceo broj."  
			  },
			  address: { 
				  required: "Adresa apartmana je obavezno."
			  },
			  place: { 
				  required: "Mesto apartmana je obavezano."
			  },
			  zipCode: { 
                  required: "Postanski broj je obavezan.",
                  minlength: "Postanski broj mora da ima 5 cifara.",
                  maxlength: "Postanski broj mora da ima 5 cifara.",
                  digits: "Postanski broj se sastoji samo od cifara."

			  },
			  checkindate: { 
				  required: "Datum za izdavanje je obavezan."
			  },
			  checkoutdate: { 
				  required: "Datum za izdavanje je obavezan.",
				  endDate:"Krajnji datum mora da bude veci od pocetnog."
			  },
			  
		  }
		  ,
		  submitHandler: function(form,event) {
			  		
			  
				  event.preventDefault();
				  
				  
				  $("input:checked").each(function(){
						 amenites.push(this.id);
						 console.log(amenites);
				  });
				  
				  var s = JSON.stringify({
					  nameApartment: $("#nameApart").val(),
					  typeApart: $( "#typeApart option:selected" ).text().toLowerCase(),
					  numRoom: $("#numbRoom").val(),
					  numOfGuests: $("#numbGuests").val(),
					  price: $("#price").val(),
					  host : user,
					  street: $("#address").val(),
					  place:  $("#place").val(),
					  zipCode: $("#zipCode").val(),
					  checkInTime: $("#checkindate").val(),
					  chackOutTime: $("#checkoutdate").val(),
					  amenites : amenites,
					  images: images,
					    
				  });
				  
				  //console.log(s);
				  console.log(images);
				 $.ajax({
						url: "/WebProject/rest/apartmentService/addApartment",
						type: "POST",
						contentType: "application/json",	
						data: s,
						dataType: "JSON"	
				  }).done(function( data, textStatus, jqXHR) {
					   
					  console.log(data);
						
					}).fail(function( data, textStatus, jqXHR) {
						alert(data.responseText);
					});
					
			  }
	
	});	
	
	  
	
	
	function readURL(input) {
        if (input.files && input.files[0]) {
        	
        	
        	
        	for(let i= 0; i < input.files.length; i++){
        		
        		var reader = new FileReader();
                
                reader.onload = function (e) {
                   
                    
                    
                     var img = $('<img />').attr('src', e.target.result).appendTo('#imgContainer');
                    
                     images.push(e.target.result);
                     
                }
                
                reader.readAsDataURL(input.files[i]);
        		
                
        		
        		
        	}
        	console.log(images);
        }
    }
    
    $("#imgInp").change(function(){
        readURL(this);
        $("#btnClear").show();
    });
    
    
    $('#btnClear').on('click', function() {
    	$("#imgContainer").html('');
    	$("#btnClear").hide();
    });
    
});