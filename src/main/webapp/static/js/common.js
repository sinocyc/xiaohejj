/**
 * 每个页面都需要用到的js
 */
// 根据ip定位当前城市
function locateByIp() {
	$.ajax({
		url: '/as/city/currentCity',
		type: 'GET',
		success: function(result){
			if(result.code == 1) {
				var city = result.extend.city;
				updateCurrCity(city.name, city.id, city.provId);
			} else {
				updateCurrCity('南京市', 75, 10);
			}
		},
		error: function() {
			updateCurrCity('南京市', 75, 10);
		}
	});
}

// 判断是否登录，修改页面内容
function isTutorLoginShowHide() {
	$.ajax({
		url: '/as/tutor/isTutorLogin',
		type: 'GET',
		cache: false,
		success: function(result) {
			if(result.code == 1) {
				$('.after-login').show();
			} else {
				$('.before-login').show();
			}
		},
		error: function() {
			$('.before-login').show();
		}
	});
}
// 获得url参数的方法
function getUrlQueryParamValue(paramName) {
	var searchStr = window.location.search;
	var keyValue = new Object();
	if(searchStr) {
		var queryStr = searchStr.substring(1);
		var paramArr = queryStr.split('&');
		for(var i = 0; i < paramArr.length; i++) {
			if(paramArr[i].indexOf('=') != -1) {
				var tempArr = paramArr[i].split('=');
				var key = tempArr[0];
				var value = tempArr[1];
				if(key) {
					keyValue[key] = value;
				}
			}
		}
	}
	return keyValue[paramName];
}

// 给教员详情的按钮添加跳转页面的事件
$('body').on('click', '.tutor-detail', function() {
	var tutorId = $(this).attr('tutorId');
	window.location.href = 'tutor-detail.html' + '?tutorId=' + tutorId;
});

// 给请家教的按钮添加跳转至指定页面的事件
$('body').on('click', '.find-tutor', function() {
	window.location.href = 'shome.html';
});
// top-row教员注册按钮
$('body').on('click', '#top-row-tutor-register', function() {
	window.location.href = 'thome.html';
});
// top-row中个人中心按钮
$('body').on('click', '#top-row-tutor-home', function() {
	window.location.href = 'thome.html';
});
// top-row中tutor退出登录按钮
$('body').on('click', '#top-row-logout', function() {
	tutorLogout();
});

// 给top-row选择省份的select添加change事件
$('#top-row').on('change', '#home-prov-select', function() {
	updateCitysByProv($('#home-prov-select').val(), '#home-city-select');
});
//  top-row切换城市按钮绑定点击事件
$('#top-row').on('click', '#city-button', function() {
	updateProvSelect('#home-prov-select', $('#current-city').attr('prov-id'));
	updateCitysByProv($('#current-city').attr('prov-id'), '#home-city-select', $('#current-city').attr('city-id'));
	$(this).hide();
	$('#home-city-selects').show();
});

//  top-row确认城市按钮绑定点击事件
$('#top-row').on('click', '#home-city-selects button', function() {
	if($('#home-city-select option:selected').text() && $('#home-city-select').val() && $('#home-prov-select').val()) {
		var cityName = $('#home-city-select option:selected').text();
		var cityId = $('#home-city-select').val();
		var provId = $('#home-prov-select').val();
		updateCurrCity(cityName, cityId, provId);
		// 发送请求添加cookie
		$.ajax({
			url: '/as/city/setCurrCity',
			type: 'POST',
			data: {currCityId: cityId}
		});
	}
	$('#home-city-selects').hide();
	$('#city-button').show();
});

// orderform的省select绑定change事件，更新城市selects
$('#order-panel').on('change', '#order-province', function() {
	updateCitysByProv($('#order-province').val(), '#order-city');
});
// orderform城市的select添加change事件，更新地区select
$('#order-panel').on('change', '#order-city', function() {
	updateDistrsByCity($('#order-city').val(), '#order-district');
});
// 更新orderform中subject 和 grade 的select
function updateOrderFormSubjectGradeSel() {
	loadSubjectSelect('#order-subject');
	loadGradeSelect('#order-grade');
}
// 更新orderform省 城 区 的select
function updateOrderFormLocationSelect(cityId, provId) {
	updateProvSelect('#order-province', provId);
	updateCitysByProv(provId, '#order-city', cityId);
}

// 发布家教需求按钮绑定事件
// 阻止表单按钮的默认提交行为
$('#order-panel').on('submit', '#order-form', function() {
	return false;
});
// ajax发布order
$('#order-panel').on('click', '#order-publish-button', function() {
	$('#order-contact-name').trigger('blur');
	$('#order-phone').trigger('blur');
	$('#order-detail-addr').trigger('blur');
	$('#order-message').trigger('blur');
	
	if($('#order-panel .form-help-text').is(':visible')) {
		$('#common-hint-modal-body').text('您填写的有些格式不正确，请根据提示修改后再提交。');
		$('#common-hint-modal').modal();
		return;
	}
	
	$.ajax({
		url: '/as/order/publish',
		type: 'POST',
		data: $('#order-form').serialize(),
		success: function(result) {
			if(result.code == 1) {
				$('#order-success-modal').modal();
			} else {
				$('#common-hint-modal-body').text('家教需求发布失败，请重新发布。');
				$('#common-hint-modal').modal();
			}
		},
		error: function() {
			$('#common-hint-modal-body').text('家教需求发布失败，请检查网络后重试。');
			$('#common-hint-modal').modal();
		}
	});
});

// order发布成功后,去查看或留在本页(留在本页刷新是为了清空表单)
$('body').on('click', '#order-success-check', function() {
	window.location.href = 'students.html';
});
$('body').on('hidden.bs.modal', '#order-success-modal', function() {
	window.location.reload(true);
});
// 登录modal中不同登录方式显示不同的按钮
$('body').on('click', '#t-login-choose-phone', function() {
	$('#t-login-button').hide();
	$('#t-login-button-phone').show();
});
$('body').on('click', '#t-login-choose-uname', function() {
	$('#t-login-button-phone').hide();
	$('#t-login-button').show();
});
// tutor手机号登录
$('body').on('click', '#t-login-button-phone', function() {
	$('#t-login-phone').trigger('blur');
	$('#t-login-password-phone').trigger('blur');
	
	if($('#t-login-modal .form-help-text').is(':visible')) {
		return;
	}
	$.ajax({
		url: '/as/tutor/tutorLoginByPhone',
		type: 'POST',
		data: {
			phone: $('#t-login-phone').val(),
			password: $('#t-login-password-phone').val()
		},
		success: function(result) {
			if(result.code == 1) {
				$('#t-login-modal').modal('hide');
				window.location.reload(true);
			} else {
				$('#login-fail-hint').text('手机号或密码错误，请重新登录。');
				$('#login-fail-hint').show();
			}
		},
		error: function() {
			$('#login-fail-hint').text('登录失败，请重新登录。');
			$('#login-fail-hint').show();
		}
	});
});
// tutor用户名登录
$('body').on('click', '#t-login-button', function() {
	$('#t-login-uname').trigger('blur');
	$('#t-login-password').trigger('blur');
	
	if($('#t-login-modal .form-help-text').is(':visible')) {
		return;
	}
	$.ajax({
		url: '/as/tutor/tutorLoginByUserName',
		type: 'POST',
		data: {
			userName: $('#t-login-uname').val(),
			password: $('#t-login-password').val()
		},
		success: function(result) {
			if(result.code == 1) {
				$('#t-login-modal').modal('hide');
				window.location.reload(true);
			} else {
				$('#login-fail-hint').text('用户名或密码错误，请重新登录。');
				$('#login-fail-hint').show();
			}
		},
		error: function() {
			$('#login-fail-hint').text('登录失败，请重新登录。');
			$('#login-fail-hint').show();
		}
	});
});
// 登录modal中登录按钮失去焦点时，登录失败的提示要隐藏
$('body').on('blur', '#t-login-button', function() {
	$('#login-fail-hint').empty();
	$('#login-fail-hint').hide();
});
$('body').on('blur', '#t-login-button-phone', function() {
	$('#login-fail-hint').empty();
	$('#login-fail-hint').hide();
});
// 取消登录后清空表单
$('body').on('hidden.bs.modal', '#t-login-modal', function() {
	$('#t-login-uname').val('');
	$('#t-login-password').val('');
	removeCheckStatus('#t-login-uname');
	removeCheckStatus('#t-login-password');
	
	$('#t-login-phone').val('');
	$('#t-login-password-phone').val('');
	removeCheckStatus('#t-login-phone');
	removeCheckStatus('#t-login-password-phone');
});

// tutor退出登录
function tutorLogout() {
	$.ajax({
		url: '/as/tutor/tutorLogout',
		type: 'GET',
		success: function(result) {
			window.location.reload(true);
		},
		error: function() {
			window.location.reload(true);
		}
	});
}

// tutor登录时表单验证
//验证用户名事件绑定
$('body').on('blur', '#t-login-uname', function() {
	checkTutorLoginUname(this);
});
$('body').on('focus', '#t-login-uname', function() {
	removeCheckStatus(this);
});
// 验证密码事件绑定
$('body').on('blur', '#t-login-password', function() {
	checkPassword(this);
});
$('body').on('focus', '#t-login-password', function() {
	removeCheckStatus(this);
});
// tutor手机号登录时表单验证
//验证手机号事件绑定
$('body').on('blur', '#t-login-phone', function() {
	checkPhone(this);
});
$('body').on('focus', '#t-login-phone', function() {
	removeCheckStatus(this);
});
// 验证密码事件绑定
$('body').on('blur', '#t-login-password-phone', function() {
	checkPassword(this);
});
$('body').on('focus', '#t-login-password-phone', function() {
	removeCheckStatus(this);
});

// orderform表单验证
// orderform联系人格式验证
$('#order-panel').on('blur', '#order-contact-name', function() {
	checkOrderContactName(this);
});
$('#order-panel').on('focus', '#order-contact-name', function() {
	removeCheckStatus(this);
});
// orderform电话格式验证
$('#order-panel').on('blur', '#order-phone', function() {
	checkPhone(this);
});
$('#order-panel').on('focus', '#order-phone', function() {
	removeCheckStatus(this);
});
// orderform详细地址格式验证
$('#order-panel').on('blur', '#order-detail-addr', function() {
	checkDetailAddr(this);
});
$('#order-panel').on('focus', '#order-detail-addr', function() {
	removeCheckStatus(this);
});
// orderform备注格式验证
$('#order-panel').on('blur', '#order-message', function() {
	checkMessage(this);
});
$('#order-panel').on('focus', '#order-message', function() {
	removeCheckStatus(this);
});

function updateCurrCity(cityName, cityId, provId) {
	$('#current-city').text(cityName);
	$('#current-city').attr('city-id', cityId);
	$('#current-city').attr('prov-id', provId);
	// 更新页面中与城市相关的内容
	updateInfoByCity(cityId, provId);
}

//  获取省份select列表,选中指定id
function updateProvSelect(provSel, selectedProvId) {
	$.ajax({
		url: '/as/province/getAll',
		type: 'GET',
		success: function(result) {
			$(provSel).empty();
			var provList = result.extend.provList;
			$.each(provList, function(index, item) {
				$(provSel).append($('<option></option>').append(item.name).val(item.id));
			});
			if(selectedProvId) {
				$(provSel).val(selectedProvId);
			}
		}
	});
}
//  根据省份获取城市的方法，选中id
function updateCitysByProv(provId, citySel, selectedCityId) {
	$.ajax({
		url: '/as/city/getCitysByProv',
		type: 'POST',
		data: {'provId': provId},
		success: function(result) {
			$(citySel).empty();
			var cityList = result.extend.cityList;
			$.each(cityList, function(index, item) {
				$(citySel).append($('<option></option>').append(item.name).val(item.id));
			});
			if (selectedCityId) {
				$(citySel).val(selectedCityId);
			}
			// 触发城市select的change事件
			$(citySel).trigger('change');
		}
	});
}
//更新城市select的方法，不触发city select的change事件
function updateCitysByProvNoTrigger(provId, citySel, selectedCityId) {
	$.ajax({
		url: '/as/city/getCitysByProv',
		type: 'POST',
		data: {'provId': provId},
		success: function(result) {
			$(citySel).empty();
			var cityList = result.extend.cityList;
			$.each(cityList, function(index, item) {
				$(citySel).append($('<option></option>').append(item.name).val(item.id));
			});
			if (selectedCityId) {
				$(citySel).val(selectedCityId);
			}
		}
	});
}
//  根据城市获取地区并更新select
function updateDistrsByCity(cityId, distrSel, selectedDistrId) {
	$.ajax({
		url: '/as/district/getDistrsByCity',
		type: 'POST',
		data: {'cityId': cityId},
		success: function(result) {
			$(distrSel).empty();
			var distrList = result.extend.distrList;
			$.each(distrList, function(index, item) {
				$(distrSel).append($('<option></option>').append(item.name).val(item.id));
			});
			if(selectedDistrId) {
				$(distrSel).val(selectedDistrId);
			}
		}
	});
}

// order-panel的科目select内容加载
function loadSubjectSelect(selectSel) {
	$.ajax({
		url: '/as/subject/getAll',
		type: 'GET',
		success: function(result) {
			if(result.code == 1) {
				$(selectSel).empty();
				var subjectList = result.extend.subjectList;
				$.each(subjectList, function(index, item) {
					$(selectSel).append(
						$('<option></option>').val(item.id).text(item.name)
					);
				});
			}
		}
	});
}
// order-panel的年级select加载
function loadGradeSelect(selectSel) {
	$.ajax({
		url: '/as/grade/getAll',
		type: 'GET',
		success: function(result) {
			if(result.code == 1) {
				$(selectSel).empty();
				var gradeList = result.extend.gradeList;
				$.each(gradeList, function(index, item) {
					$(selectSel).append(
						$('<option></option>').val(item.id).text(item.name)
					);
				});
			}
		}
	});
}

// 移除校验状态
function removeCheckStatus(selector) {
	$ele = $(selector);
	$ele.parent().removeClass('has-error has-success has-feedback');
	$ele.nextAll('.form-help-text').empty();
	$ele.nextAll().hide();
}

// 验证order的联系人格式
function checkOrderContactName(selector) {
	var $ele = $(selector);
	var val = $ele.val();
	if(!val) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 联系人不能为空');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else if(val.length > 25) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 长度不超过25个字符');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else {
		$ele.parent().addClass('has-success has-feedback');
		$ele.nextAll('.glyphicon-ok').show();
	}
}

// 验证tutor登录时的用户名格式
function checkTutorLoginUname(selector) {
	var $ele = $(selector);
	var val = $ele.val();
	if(!val) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 用户名不能为空');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else if(val.length > 25) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 长度不超过25个字符');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else {
		$ele.parent().addClass('has-success has-feedback');
		$ele.nextAll('.glyphicon-ok').show();
	}
}

//密码格式验证
function checkPassword(selctor) {
	var $ele = $(selctor);
	var val = $ele.val();
	if(!val) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 密码不能为空');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else if(val.length < 6) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 密码不能少于6个字符');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else if(val.length > 30) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 密码不能超过30个字符');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else {
		$ele.parent().addClass('has-success has-feedback');
		$ele.nextAll('.glyphicon-ok').show();
	}
}

// 邮箱格式验证方法
function checkEmail(selector) {
	var $ele = $(selector);
	var val = $ele.val();
	var emailRegex =  /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
	if(!val) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 邮箱不能为空');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else if(!emailRegex.test(val)) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 邮箱格式不正确');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else {
		$ele.parent().addClass('has-success has-feedback');
		$ele.nextAll('.glyphicon-ok').show();
	}
}

// 手机号码验证方法
function checkPhone(selector) {
	var $ele = $(selector);
	var val = $ele.val();
	var phoneRegex = /^[0-9]{11}$/;
	if(!val) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 手机号码不能为空');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else if(!phoneRegex.test(val)) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 手机号码格式不正确(11位数字)');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else {
		$ele.parent().addClass('has-success has-feedback');
		$ele.nextAll('.glyphicon-ok').show();
	}
}
// 详细地址验证方法
function checkDetailAddr(selector) {
	var $ele = $(selector);
	var val = $ele.val();
	if(val) {
		if(val.length > 50) {
			$ele.parent().addClass('has-error');
			$ele.next('.form-help-text').text('* 长度不超过50个字符');
			$ele.next('.form-help-text').show();
		} else {
			$ele.parent().addClass('has-success');
		}
	}
}

function checkMessage(selector) {
	var $ele = $(selector);
	var val = $ele.val();
	if(val) {
		if(val.length > 250) {
			$ele.parent().addClass('has-error');
			$ele.next('.form-help-text').text('* 长度不超过250个字符');
			$ele.next('.form-help-text').show();
		} else {
			$ele.parent().addClass('has-success');
		}
	}
}

//tutors的subject筛选ul加载方法
function loadSubjectUl(ulSelector) {
	$.ajax({
		url: '/as/subject/getAll',
		type: 'GET',
		success: function(result) {
			if(result.code == 1) {
				$(ulSelector).empty();
				$(ulSelector).append($('<li></li>').addClass('active').attr('subject-id', '').text('不限'));
				var subjectList = result.extend.subjectList;
				$.each(subjectList, function(index, item) {
					$(ulSelector).append(
						$('<li></li>').attr('subject-id', item.id).text(item.name)
					);
				});
			}
		}
	});
}
// tutors的grade筛选ul加载方法
function loadGradeUl(ulSelector) {
	$.ajax({
		url: '/as/grade/getAll',
		type: 'GET',
		success: function(result) {
			if(result.code == 1) {
				$(ulSelector).empty();
				$(ulSelector).append($('<li></li>').addClass('active').attr('grade-id', '').text('不限'));
				var gradeList = result.extend.gradeList;
				$.each(gradeList, function(index, item) {
					$(ulSelector).append(
						$('<li></li>').attr('grade-id', item.id).text(item.name)
					);
				});
			}
		}
	});
}

// tutors的distr筛选ul加载方法
function updateDistrUlByCity(cityId, ulSelector) {
	$.ajax({
		url: '/as/district/getDistrsByCity',
		type: 'POST',
		data: {'cityId': cityId},
		success: function(result) {
			$(ulSelector).empty();
			$(ulSelector).append($('<li></li>').addClass('active').attr('distr-id', '').text('不限'));
			var distrList = result.extend.distrList;
			$.each(distrList, function(index, item) {
				$(ulSelector).append($('<li></li>').attr('distr-id', item.id).text(item.name));
			});
		}
	});
}
// top 切换显示导航
$('body').on('click', '#top-menu-toggle', function() {
	if($('#nav').is(':visible')) {
		$('#nav').addClass('hidden-xs');
	} else {
		$('#nav').removeClass('hidden-xs');
	}
});





