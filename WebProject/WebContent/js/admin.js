$(document).ready(function() {
	
	
		$.ajax({
		  	url: "/WebProject/rest/userService/currentUser",
			type: "GET",
			contentType: "application/json",
			dataType: "JSON"
		  
		}).done(function( data, textStatus, jqXHR ) {
			
			$("#curentUser").addClass( "glyphicon glyphicon-user" );
			$("#curentUser").html(data.name);
			
			    $("#t01 tbody").append(
			        "<tr>"
			            +"<td>"+data.name+"</td>"
			            +"<td>"+data.lastName+"</td>"
			            +"<td>"+data.gander+"</td>"
			            +"<td>"+data.role+"</td>"  
			        +"</tr>" );
			    
			
	
		}).fail(function() {
		    
		});
		
		
		$(".link").click(function(e) {
		    e.preventDefault();
		    $('#cont').children().hide();
		    
		    console.log($(this).data('rel'));
		    
		    $('#' + $(this).data('rel')).parentsUntil("div#cont").show();		//pokazi sve od njega na gore   
		    $('#' + $(this).data('rel')).siblings().show();						//svu njegovu bracu					
		    $('#' + $(this).data('rel')).show();								//i na kaju pokazi njega
		});
		
		
		$("#getAllUsers").one( "click", function() {	
			$.ajax({
			  	url: "/WebProject/rest/userService/getAllUsers",
				type: "GET",
				contentType: "application/json",
				dataType: "JSON"
			  
			}).done(function( data, textStatus, jqXHR ) {
				
				$.each(data,function(i,item){
				    $("#t02 tbody").append(
				        "<tr class='user'>"
				    		+"<td>"+item.userName+"</td>"
				            +"<td>"+item.name+"</td>"
				            +"<td>"+item.lastName+"</td>"
				            +"<td>"+item.gander+"</td>"
				            +"<td>"+item.role+"</td>"
				         );  
				    if(item.role.trim() != "admin"){
				    	console.log("sss");
				    	 $("#t02 .user").append("<td><a style='cursor:pointer;'><i style='font-size:24px;' class='fa fa-edit'></i> Izmeni ulogu korisniku</a> </td>"
							        +"</tr>" ); 
				    }else{
				    	$("#t02 tbody").append("</tr>"); 
				    }
				        
				 });
		
			}).fail(function() {
			    
			});
		});
			
		$("#logout").click(function(event) {
		
			  event.preventDefault();	
			  $.ajax({
				  	url: "/WebProject/rest/userService/logout",
					type: "POST",
					contentType: "application/json",
					dataType: "JSON"
				  
			  }).done(function( data, textStatus, jqXHR ) {
					alert(textStatus);
					goTo();
				}).fail(function() {
				    
				});
			
			
		});
		
		$("#gsearch").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#tbody tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		 });
		
		
		
		$('#gsearch').keyup(function(){
		    // Search Text
		   var search = $(this).val();

		    // Hide all table tbody rows
		   $('table tbody tr').hide();

		    // Count total search result
		   var len = $('table tbody  td:nth-child(2):contains("'+search+'")').length;

		    
		      // Searching text in columns and show match row
	       $('table tbody  td:contains("'+search+'")').each(function(){
	          $(this).closest('tr').show();
	       });
		    

		  });
		
		
		function goTo(){
			alert("SSSSSSSSSSS ")
			window.location = "http://localhost:8090/WebProject/";
		}
	

});


