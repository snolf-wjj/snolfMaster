(function ($) {


	/*==================================================================
	[ Validate ]*/
	var input = $('.validate-input .input100');
	var baseUrl = "http://192.168.0.145"

	// $('.submit').on('click', function () {
	// 	var check = true;
	//
	// 	for (var i = 0; i < input.length; i++) {
	// 		if (validate(input[i]) == false) {
	// 			showValidate(input[i]);
	// 			check = false;
	// 		}
	// 	}
	//
	// 	return check;
	// });

	$('#login').on('click', function () {
		if (submitCheck() == true) {
			$.post(baseUrl + "/third/rest/classmates/login", $("#form").serializeArray(), function (data, status) {
				if ("0000" == data.code) {
					layer.msg(data.data.message, {icon: 6, time: 1000});
					window.location.href = data.data.url;
				} else {
					layer.msg(data.msg, {icon: 5, time: 1000});
				}
			});
		}
	});
	$('#formData').on('click', function () {
		if (submitCheck() == true) {
			$.post(baseUrl + "/third/rest/classmates/info/add", $("#form").serializeArray(), function (data, status) {
				if ("0000" == data.code) {
					layer.msg(data.data.message, {icon: 6, time: 1000});
					window.location.href = data.data;
				} else {
					layer.msg(data.msg, {icon: 5, time: 1000});
				}
			});
		}
	});

	$('#register').on('click', function () {
		if (submitCheck() == true) {
			$.post(baseUrl + "/third/rest/classmates/register", $("#form").serializeArray(), function (data, status) {
				if ("0000" == data.code) {
					layer.msg(data.data, {icon: 6, time: 1000});
					setTimeout("window.location.href = '/third/classmates/index.html'", 1000);
				} else {
					layer.msg(data.msg, {icon: 5, time: 1000});
				}
			});
		}
	});

	function submitCheck() {
		var check = true;
		for (var i = 0; i < input.length; i++) {
			if (validate(input[i]) == false) {
				showValidate(input[i]);
				check = false;
			}
		}

		return check;
	}


	$('.validate-form .input100').each(function () {
		$(this).focus(function () {
			hideValidate(this);
		});
	});

	function validate(input) {
		if ($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
			if ($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
				return false;
			}
		} else if ($(input).attr('type') == 'phone' || $(input).attr('name') == 'phone') {
			if ($(input).val().trim().match(/^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/) == null) {
				return false;
			}
		} else if ($(input).attr('type') == 'loginName' || $(input).attr('name') == 'loginName') {
			if ($(input).val().trim().match(/^[0-9a-zA-Z]+$/) == null) {
				return false;
			}
		} else {
			if ($(input).val().trim() == '') {
				return false;
			}
		}
	}

	function showValidate(input) {
		var thisAlert = $(input).parent();

		$(thisAlert).addClass('alert-validate');
	}

	function hideValidate(input) {
		var thisAlert = $(input).parent();

		$(thisAlert).removeClass('alert-validate');
	}


})(jQuery);