$(document).ready(function(){
	$( ".send" ).on( "submit", function( event ){ 
		event.preventDefault();
		$.ajax({
			url:'Chat',
			type:'POST',
			dataType:'json',
			data:$("#form").serialize(),
			succes:function(data){
				alert(data);
			},
		    error: function(xhr, resp, text) { 
			console.log(xhr, resp, text); 
			},		
 		})
		return false;
	})
});
