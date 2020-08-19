$(document).ready(function() {
	
	$(".link").click(function(e) {
	    e.preventDefault();
	    $('.content-container div').hide();
	    $('#' + $(this).data('rel')).show();
	});
	
	console.log($('#link').attr('href'));
	
	 $("#link5").click(function(){
		 alert("sss");
		    $("#content5").load("test.html");
		  });
	
	
	 
	$("#s").on("change paste keyup", function() {
		//console.log($(this).val());
		console.log( encodeURIComponent($(this).val()) );
		$('.mapouter').hide();
		$("#frame").attr('src', "https://maps.google.com/maps?q=" + encodeURIComponent($("#s").val()) +"&t=&z=13&ie=UTF8&iwloc=&output=embed");
	
		 $('.mapouter').show();
		 console.log("sssssssss");
		 console.log($("a.navigate-link").attr("href"));
		 showHref();
	  })
	  .change();
	
	function showHref(){
		 console.log("sssssssss");
		 console.log($("#frame").find(".navigate-link").get());
	}
	
	$( "#frameDemo" ).contents().find( "a" ).css( "background-color", "#BADA55" );

	$('#frame').on( 'load', function() {
		
		
		console.log($("#frame").contents().find( ".navigate-link" ).html() );
		console.log( $(document).contents().find(".gmap_canvas").get());
       // alert(value);
		
		//console.log(value);
		
	});
	
	$('.changesrc').click(function() {
		$('.mapouter').hide();
		console.log( encodeURIComponent($("#s").val()) );
		$("#frame").attr('src', "https://maps.google.com/maps?q=" + encodeURIComponent($("#s").val()) +"&t=&z=13&ie=UTF8&iwloc=&output=embed");
        $('.mapouter').show();
       
    });
	
	
	
	//https://www.google.com/maps?ll=37.776564,-122.450719&z=13&t=m&hl=en-US&gl=US&mapclient=embed&daddr=University+of+San+Francisco+2130+Fulton+St+San+Francisco,+CA+94117+United+States@37.7765643,-122.4507187
	 function readURL(input) {
		 
		 
	        if (input.files && input.files[0]) {
	            var reader = new FileReader();

	            reader.onload = function (e) {
	                $('#image_upload_preview').attr('src', e.target.result);
	                console.log(input.files[0]);
	                $.ajax({
	    			  	url: "/WebProject/rest/testService/saveImg",
	    				type: "POST",
	    				data: JSON.stringify(e.target.result),
	    				contentType: "application/json",
	    				dataType: "JSON"
	    			  
	    		  }).done(function( data, textStatus, jqXHR) {
	    				console.log(textStatus);
	    				//goTo();
	    				alert( "Handler for .click() called." );
	    			}).fail(function() {
	    			    
	    			});
	                
	            }
	            console.log();
	            reader.readAsDataURL(input.files[0]);
	        }
	    }

	    $("#inputFile").change(function () {
	        readURL(this);
	        
	        console.log($(this).val());
	    });
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    ///
	    var map = L.map('map').setView([-28.4792625, 24.6727135], 4);

	    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
	      attribution: '&copy; <a href="https://osm.org/copyright">OpenStreetMap</a> contributors'
	    }).addTo(map);

	    var searchControl = L.esri.Geocoding.geosearch().addTo(map);

	    var results = L.layerGroup().addTo(map);

	    searchControl.on('results', function (data) {
	      results.clearLayers();
	      for (var i = data.results.length - 1; i >= 0; i--) {
	        results.addLayer(L.marker(data.results[i].latlng));
	        console.log(data.results[i]);
	      }
	    });
	    
	    
	    var popup = L.popup();
	    
	    function onMapClick(e) {
			popup
				.setLatLng(e.latlng)
				.setContent("You clicked the map at " + e.latlng.toString())
				.openOn(map);
		}
	    map.on('click', onMapClick);
	    ///
	    
	    
	    
	    
	    
	    
	   /* 
	    var mymap = L.map('mapid').setView([51.505, -0.09], 13);

		L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
			maxZoom: 18,
			attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
				'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
				'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
			id: 'mapbox/streets-v11',
			tileSize: 512,
			zoomOffset: -1
		}).addTo(mymap);

		L.marker([51.5, -0.09]).addTo(mymap)
			.bindPopup("<b>Hello world!</b><br />I am a popup.").openPopup();

		L.circle([51.508, -0.11], 500, {
			color: 'red',
			fillColor: '#f03',
			fillOpacity: 0.5
		}).addTo(mymap).bindPopup("I am a circle.");

		L.polygon([
			[51.509, -0.08],
			[51.503, -0.06],
			[51.51, -0.047]
		]).addTo(mymap).bindPopup("I am a polygon.");


		var popup = L.popup();

		function onMapClick(e) {
			popup
				.setLatLng(e.latlng)
				.setContent("You clicked the map at " + e.latlng.toString())
				.openOn(mymap);
		}

		mymap.on('click', onMapClick);
	    
	    
	    
	    
	 
	 ///
	/*$("#logout").click(function() {
		 

		  $.ajax({
			  	url: "/WebProject/rest/userService/logout",
				type: "POST",
				contentType: "application/json",
				dataType: "JSON"
			  
		  }).done(function( data, textStatus, jqXHR) {
				console.log(textStatus);
				//goTo();
				 alert( "Handler for .click() called." );
			}).fail(function() {
			    
			});
		  
		  
	});
	
	
	
	$("#curentUser").click(function() {
		  $.ajax({
			  	url: "/WebProject/rest/userService/getAllUsers",
				type: "GET",
				contentType: "application/json",
				dataType: "JSON"
			  
		  }).done(function( data, textStatus, jqXHR) {
			  	for(i=0; i < data.length; i++){
			  		console.log(data[i].userName);
			  	}
			}).fail(function() {
			    
			});
		  
		  
	});
	
	
	
	
	
	function goTo(){
		alert("SSSSSSSSSSS ")
		window.location = "http://localhost:8090/WebProject/login.html";
	}
	
	
	
	
		$('#forma').validate({
			
			  rules: {
				  userName: {
				        required: true
				      },
				      password: {
				        required: true
			      },
			  },
				  messages: {
					  userName: {
						  required: "Please enter username;"
					  },
					  password: { 
						  required:"Please enter password;"
					  }
				  }
			  ,
			  submitHandler: function(form,event) {
				  event.preventDefault();
					
					var s = JSON.stringify({
							userName : $("#userName").val(),
							password : $("#password").val()	
					});

					$.ajax({
							url: "/WebProject/rest/userService/login",
							type: "POST",
							contentType: "application/json",	
							data: s,
							dataType: "JSON"
							
							
					  }).done(function( data, textStatus, jqXHR) {
						   
							alert(textStatus);
							console.log(data);
						}).fail(function( data, textStatus, jqXHR) {
							alert(data.responseText);
							
						});
				  }
		
		});	
	
		
		
	
	
	/*$('#forma').submit(function(event) {
		event.preventDefault();
		
		var s = JSON.stringify({
				userName : $("#userName").val(),
				password : $("#password").val()	
		});

		$.ajax({
				url: "/WebProject/rest/userService/login",
				type: "POST",
				contentType: "application/json",	
				data: s,
				dataType: "JSON"
				
				
		  }).done(function( data, textStatus, jqXHR) {
			   
				alert(textStatus);
			}).fail(function() {
			    
			});
		
	});	*/
	
});