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
		
		
		$("#getAmenity").one( "click", function() { 
				$.ajax({
				  	url: "/WebProject/rest/AdminService/getAmenities",
					type: "GET",
					contentType: "application/json",
					dataType: "JSON"
				  
				}).done(function( data, textStatus, jqXHR ) {
					
					$.each(data,function(i,item){
						
				  		 $("#t03 #tbody03").append("<tr id=" +item.id+ " data-name='"+item.name +"'><td>"+item.name+"</td><td><button class='btn btn-info btn-xs btn-edit'>Edit</button><button class='btn btn-danger btn-xs btn-delete'>Delete</button></td></tr>");
						
						
					});
		
				}).fail(function() {
				    
				});
		});
		
		
		$("#createAmanity").click(function(e) {
		  		console.log("sasasa");	
		  		
		  		
		  		
		  		if($('#nameApart').val() != "" && $('#nameApart').val().length >= 2)
		  		{
		  			
		  			e.preventDefault();
		  			
		  			var s = JSON.stringify({name: $('#nameApart').val()});
		  			
		  		   $.ajax({
						url: "/WebProject/rest/AdminService/addAmenity",
						type: "POST",
						contentType: "application/json",	
						data: s,
						dataType: "JSON"	
				   }).done(function( data, textStatus, jqXHR) {
					   

				  		 $("#t03 #tbody03").append("<tr id=" +data.id+ " data-name='"+data.name +"'><td>"+data.name+"</td><td><button class='btn btn-info btn-xs btn-edit'>Edit</button><button class='btn btn-danger btn-xs btn-delete'>Delete</button></td></tr>");
							
					   
					  
					}).fail(function( data, textStatus, jqXHR) {
						
						alert(data.responseText);
						
					});
				    
		  		}else{
		  			alert("Ime sadrzaja apartmana mora da sadrzi vise od 2 znaka.")
		  		}
		
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
					
					if(item.role.trim() == "admin"){
					    $("#t02 tbody").append(
					        "<tr class='user'>"
					    		+"<td>"+item.userName+"</td>"
					            +"<td>"+item.name+"</td>"
					            +"<td>"+item.lastName+"</td>"
					            +"<td>"+item.gander+"</td>"
					            +"<td>"+item.role+"</td>"
					            + "</tr>"
					         ); 
					}else{
						 $("#t02 tbody").append(
							        "<tr class='user'>"
							    		+"<td>"+item.userName+"</td>"
							            +"<td>"+item.name+"</td>"
							            +"<td>"+item.lastName+"</td>"
							            +"<td>"+item.gander+"</td>"
							            +"<td>"+item.role+"</td>"   
							            + "</tr>"
							         ); 
						
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
			
			window.location = "/WebProject/";
		}
		
		
		 $("body").on("click", ".btn-edit", function(){

		        var name = $(this).parents("tr").attr('data-name');

		       

		    

		        $(this).parents("tr").find("td:eq(0)").html('<input name="edit_name" value="'+name+'">');

		        

		    

		        $(this).parents("tr").find("td:eq(1)").prepend("<button class='btn btn-info btn-xs btn-update'>Update</button><button class='btn btn-warning btn-xs btn-cancel'>Cancel</button>")

		        $(this).hide();

		    });

		   

		    $("body").on("click", ".btn-cancel", function(){

		        var name = $(this).parents("tr").attr('data-name');

		        $(this).parents("tr").find("td:eq(0)").text(name);

		   

		        $(this).parents("tr").find(".btn-edit").show();

		        $(this).parents("tr").find(".btn-update").remove();

		        $(this).parents("tr").find(".btn-cancel").remove();

		    });

		   

		    $("body").on("click", ".btn-update", function(){

		        var name = $(this).parents("tr").find("input[name='edit_name']").val();

		        $(this).parents("tr").find("td:eq(0)").text(name);

		        $(this).parents("tr").attr('data-name', name);

		        $(this).parents("tr").find(".btn-edit").show();

		        $(this).parents("tr").find(".btn-cancel").remove();

		        $(this).parents("tr").find(".btn-update").remove();

		    });
	
		    $("body").on("click", ".btn-delete", function(){
		    	
		    	var idAmn = $(this).parents("tr").attr('id');
		    	console.log(idAmn);
		    	
		    	 $.ajax({
                     url: '/WebProject/rest/AdminService?id=' + idAmn,
                     type: 'DELETE',
                     contentType: 'application/json',
                    
                 }).done(function( data, textStatus, jqXHR) {
                	 alert(data.responseText);
				}).fail(function( data, textStatus, jqXHR) {
					
					alert(data.responseText);
					
				});

		        $(this).parents("tr").remove();

		    });
});


