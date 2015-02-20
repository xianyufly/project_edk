var Messager = {
	alert : function(msg) {
		$('#msg').text(msg);
		$('.alert').css('display','');
		$('.alert').alert();
	}
};
