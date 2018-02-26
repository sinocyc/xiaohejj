/**
 * thome页面的js
 */
// 页面加载后
$(function() {
	locateByIp();
	isTutorLoginShowHide();
	loadSubjectCheckbox('#t-subject');
	loadGradeCheckbox('#t-grade');
	loadModeCheckbox('#t-mode')
	loadLoginTutorInfo();
});

// 根据currCity更新页面相关内容，currCity改变时调用
function updateInfoByCity(cityId, provId) {
	// 更新家乡 省份 城市 的select
	updateProvSelect('#t-birth-prov', provId);
	updateCitysByProv(provId, '#t-birth-city', cityId);
	// 更新当前 省份 城市 的select
	updateProvSelect('#t-curr-prov', provId);
	updateCitysByProv(provId, '#t-curr-city', cityId);
	// 更新辅导 省份 城市 的select
	updateProvSelect('#t-teach-prov', provId);
	updateCitysByProv(provId, '#t-teach-city', cityId);
}

// 加载登录的tutor的信息
function loadLoginTutorInfo() {
	$.ajax({
		url: '/as/tutor/getLoginTutorInfo',
		type: 'GET',
		cache: false,
		success: function(result) {
			if(result.code == 1) {
				var tutor = result.extend.tutor;
				// 更新用户名和头像信息
				var photo = tutor.photo ? tutor.photo : 'default-photo.png';
				var photoUrl = 'http://pub.xhtutor.com/photo/' + photo;
				$('#t-user-name-info').text(tutor.userName);
				$('#t-user-name-modify').text(tutor.userName);
				$('#t-photo-info').attr('src', photoUrl);
				$('#thome-photo-modify').attr('src', photoUrl);
				$('#modal-photo').attr('src', photoUrl);
				// 更新基本信息
				var genderStr = tutor.gender == 'm' ? '男' : '女';
				var tutorTypeName = '';
				switch(tutor.tutorType) {
				case 1:
					tutorTypeName = '在校大学生';
					break;
				case 2:
					tutorTypeName = '大学毕业生';
					break;
				case 3:
					tutorTypeName = '专业教师';
					break;
				default:
					tutorTypeName = '';
				}
				$('#t-real-name-info').text(tutor.realName);
				$('#t-real-name-modify').val(tutor.realName);
				
				$('#t-gender-info').text(genderStr);
				$.each($('#t-gender-modify input[type="radio"]'), function(index, item) {
					if($(item).val() == tutor.gender) {
						$(item).attr('checked', 'checked');
					}
				});
				$.ajax({
					url: '/as/city/getCityDetailById',
					type: 'POST',
					data: {cityId: tutor.birthCityId},
					success: function(result) {
						if(result.code == 1) {
							var city = result.extend.city;
							var birthCityStr = city.province.name + ' ' + city.name;
							$('#t-birth-city-info').text(birthCityStr);
							// 同步modify表单的值
							updateProvSelect('#t-birth-prov-modify', city.province.id);
							updateCitysByProv(city.province.id, '#t-birth-city-modify', city.id);
						}
					}
				});
				$('#t-id-card-info').text(tutor.idCardNum);
				$('#t-id-card-modify').val(tutor.idCardNum);
				$('#t-univ-info').text(tutor.university);
				$('#t-univ-modify').val(tutor.university);
				$('#t-major-info').text(tutor.major);
				$('#t-major-modify').val(tutor.major);
				$('#t-type-info').text(tutorTypeName);
				$('#t-type-modify').val(tutor.tutorType);
				//更新联系方式信息
				$('#t-phone-info').text(tutor.phone);
				$('#t-phone-modify').val(tutor.phone);
				$('#t-email-info').text(tutor.email);
				$('#t-email-modify').val(tutor.email);
				$('#t-qq-info').text(tutor.qq);
				$('#t-qq-modify').val(tutor.qq);
				$.ajax({
					url: '/as/district/getDistrDetailById',
					type: 'POST',
					data: {distrId: tutor.currDistrId},
					success: function(result) {
						if(result.code == 1) {
							var district = result.extend.district;
							var currDistrStr = district.city.province.name + ' ' + district.city.name + ' ' + district.name;
							$('#t-curr-city-info').text(currDistrStr);
							// 同步modify表单的值
							var provId = district.city.province.id;
							var cityId = district.city.id;
							updateProvSelect('#t-curr-prov-modify', provId);
							updateCitysByProvNoTrigger(provId, '#t-curr-city-modify', cityId);
							updateDistrsByCity(cityId, '#t-curr-distr-modify', district.id);
						}
					}
				});
				// 更新家教信息
				var subjectStr = '';
				$.each(tutor.subjectList, function(subjectIndex, subjectItem) {
					subjectStr = subjectStr + subjectItem.name + ' / ';
				});
				var gradeStr = '';
				$.each(tutor.gradeList, function(gradeIndex, gradeItem) {
					gradeStr = gradeStr + gradeItem.name + ' / ';
				});
				var distrStr = '';
				$.each(tutor.distrList, function(distrIndex, distrItem) {
					distrStr = distrStr + distrItem.name + ' / ';
				});
				var modeStr = '';
				$.each(tutor.modeList, function(modeIndex, modeItem) {
					modeStr = modeStr + modeItem.name + ' / ';
				});
				$('#t-subject-info').text(subjectStr);
				loadSubjectCheckboxModify('#t-subject-modify', tutor.subjectList);
				$('#t-grade-info').text(gradeStr);
				loadGradeCheckboxModify('#t-grade-modify', tutor.gradeList);
				$('#t-distr-info').text(distrStr);
				$.ajax({
					url: '/as/city/getCityDetailById',
					type: 'POST',
					data: {cityId: tutor.teachCityId},
					success: function(result) {
						if(result.code == 1) {
							var city = result.extend.city;
							$('#t-teach-city-info').text(city.name);
							// 同步modify表单的值
							var cityId = city.id;
							var provId = city.province.id;
							updateProvSelect('#t-teach-prov-modify', provId);
							updateCitysByProvNoTrigger(provId, '#t-teach-city-modify', cityId);
							loadDistrCheckboxModify(cityId, '#t-distr-modify', tutor.distrList);
						}
					}
				});
				$('#t-mode-info').text(modeStr);
				loadModeCheckboxModify('#t-mode-modify', tutor.modeList);
				$('#t-intro-info').text(tutor.intro);
				$('#t-intro-modify').val(tutor.intro);
			}
		}
	});
	// 更新我的订单
	loadLoginTutorOrdersInfo();
}
// 加载登录的tutor的我的订单信息
function loadLoginTutorOrdersInfo() {
	$.ajax({
		url: '/as/order/getOrdersByTutorId',
		type: 'GET',
		cache: false,
		success: function(result) {
			if(result.code == 1) {
				var orderList = result.extend.orderList;
				$('#thome-order-table-body').empty();
				$.each(orderList, function(orderIndex, orderItem) {
					var code = orderItem.code;
					var codeStr = orderItem.code.substring(0, code.length - 3);
					var subjectName = orderItem.subject.name;
					var gradeName = orderItem.grade.name;
					var distrName = orderItem.district.name;
					var cityName = orderItem.city.name;
					var address = cityName + ' ' + distrName + ' ' + orderItem.detailAddr;
					var time = new Date(orderItem.updateTime);
					var timeStr = time.toLocaleDateString() + ' ' + time.getHours() + ':' + time.getMinutes() + ':' + time.getSeconds();
					var operation = $('<button></button>').addClass('btn btn-info btn-sm cancel-order').attr('order-id', orderItem.id).text('取消订单');
					$('#thome-order-table-body').append(
						$('<tr></tr>').append(
							$('<td></td>').addClass('code-col').text(codeStr)
						).append(
							$('<td></td>').addClass('sub-col').text(subjectName)
						).append(
							$('<td></td>').addClass('grade-col').text(gradeName)
						).append(
							$('<td></td>').addClass('loc-col').text(address)
						).append(
							$('<td></td>').addClass('message-col').text(orderItem.message)
						).append(
							$('<td></td>').addClass('price-col').text(orderItem.price)
						).append(
							$('<td></td>').addClass('time-col').text(timeStr)
						).append(
							$('<td></td>').addClass('status-col').append(operation)
						)
					);
				});
				
			}
		}
	});
}
// 我的订单中取消订单按钮
$('#thome-order-table-body').on('click', '.cancel-order', function() {
	var orderId = $(this).attr('order-id');
	$.ajax({
		url: '/as/tutor/cancelOrder',
		type: 'POST',
		data: {orderId: orderId},
		success: function(result) {
			// 重新加载我的订单
			loadLoginTutorOrdersInfo();
			$('#common-hint-modal-body').text(result.extend.message);
			$('#common-hint-modal').modal();
		},
		error: function() {
			$('#common-hint-modal-body').text('取消订单失败，请检查网络后重试。');
			$('#common-hint-modal').modal();
		}
	});
});
// 加载修改表单的辅导地区专用loadDistrCheckboxModify
function loadDistrCheckboxModify(cityId, distrSel, selectedDistrs) {
	$.ajax({
		url: '/as/district/getDistrsByCity',
		type: 'POST',
		data: {'cityId': cityId},
		success: function(result) {
			$(distrSel).empty();
			var distrList = result.extend.distrList;
			$.each(distrList, function(index, item) {
				$(distrSel).append(
					$('<label></label>').attr('class', 'checkbox-inline').append(
						$('<input/>').attr({
							type: 'checkbox',
							name: 'distrId',
							value: item.id
						}), 
						item.name
					)
				);
			});
			$.each($(distrSel).find('input'), function(checkboxIndex, checkboxItem) {
				var checkedOrNot = false;
				$.each(selectedDistrs, function(distrIndex, distrItem) {
					if($(checkboxItem).val() == distrItem.id) {
						checkedOrNot = true;
						// 退出一层循环
						return false;
					}
				});
				$(checkboxItem).attr('checked', checkedOrNot);
			});
		}
	});
}

// thome info部分的退出登录按钮
$('#thome-tutor-logout').click(function() {
	tutorLogout();
});

// 家乡省份的select添加onchange事件
$('#t-birth-prov').change(function() {
	updateCitysByProv($('#t-birth-prov').val(), '#t-birth-city');
});
// 当前省份的select添加onchange事件
$('#t-curr-prov').change(function() {
	updateCitysByProv($('#t-curr-prov').val(), '#t-curr-city');
});
// 当前城市的select添加change事件，更新地区select
$('#t-curr-city').change(function() {
	updateDistrsByCity($('#t-curr-city').val(), '#t-curr-distr');
});
// 辅导省份的select添加onchange事件
$('#t-teach-prov').change(function() {
	updateCitysByProv($('#t-teach-prov').val(), '#t-teach-city');
});
// 辅导城市的select添加change事件，更新辅导地区checkbox选择项
$('#t-teach-city').change(function() {
	// 省份改变时js改变城市，也会触发city的change事件
	var cityId = $('#t-teach-city').val();
	loadDistrCheckbox(cityId, '#t-distr');
});

//  头像上传
function previewPhoto() {
	var reader = new FileReader();
	var file = document.getElementById('modal-photo-input').files[0];
	if(file) {
		reader.readAsDataURL(file);
		reader.onload = function(e) {
			$('#modal-photo-div').empty();
			$('#modal-photo-div').append($('<img>').attr({
				id: 'modal-photo',
				src: this.result,
				'class': 'img-responsive',
				alt: '请选择正确的图片格式'
			}));
			$('#modal-photo').cropper({
				aspectRatio: 1,
				viewMode: 1
			});
			// 此语句会产生跨域问题
			/*$('#modal-photo').cropper('replace', this.result);*/
		}
	}
};
$('#modal-photo-input').change(function() {
	previewPhoto();
});
$('#photo-crop-btn').on('click', function() {
	var croppedCanvas = $('#modal-photo').cropper('getCroppedCanvas');
	var data = $('#modal-photo').cropper('getData');
	if(croppedCanvas && data) {
		$('#thome-photo').attr('src', croppedCanvas.toDataURL());
		var urlParam = '?x-oss-process=image/crop,x_' + data.x.toFixed() + ',y_' + data.y.toFixed()
				+ ',w_' + data.width.toFixed() + ',h_' +data.height.toFixed();
		$('#t-photo-hidden').attr('urlParam', urlParam);
		
		// 同时更新photo修改的信息
		$('#thome-photo-modify').attr('src', croppedCanvas.toDataURL());
		$('#t-photo-hidden-modify').attr('urlParam', urlParam);
		
		var file = document.getElementById('modal-photo-input').files[0];
		if(file) {
			var fileName = file.name;
			var index = fileName.lastIndexOf('.');
			var fileSuffix = fileName.substring(index);
			$('#t-photo-hidden').attr('fileSuffix', fileSuffix);
			
			// 同时更新photo修改的信息
			$('#t-photo-hidden-modify').attr('fileSuffix', fileSuffix);
			
		}
	}
	$('#thome-photo-modal').modal('hide');
	
});

// cropper.js的跨域问题，未选择图片时不进行cropper
/*$('#thome-photo-modal').on('shown.bs.modal', function (e) {
	$('#modal-photo').cropper({
		aspectRatio: 1,
		viewMode: 1
	});
	
});*/

//加载表单中的科目，年级，地区checkbox
/* checkbox的格式
<label class="checkbox-inline">
	<input type="checkbox" name="subjectId" value=""/>语文
</label>*/
function loadSubjectCheckbox(checkboxSel) {
	$.ajax({
		url: '/as/subject/getAll',
		type: 'GET',
		success: function(result) {
			$(checkboxSel).empty();
			var subjectList = result.extend.subjectList;
			$.each(subjectList, function(index, item) {
				$(checkboxSel).append(
					$('<label></label>').attr('class', 'checkbox-inline').append(
						$('<input/>').attr({
							type: 'checkbox',
							name: 'subjectId',
							value: item.id
						}), 
						item.name
					)
				);
			});
		}
	});
}
function loadGradeCheckbox(checkboxSel) {
	$.ajax({
		url: '/as/grade/getAll',
		type: 'GET',
		success: function(result) {
			$(checkboxSel).empty();
			var gradeList = result.extend.gradeList;
			$.each(gradeList, function(index, item) {
				$(checkboxSel).append(
					$('<label></label>').attr('class', 'checkbox-inline').append(
						$('<input/>').attr({
							type: 'checkbox',
							name: 'gradeId',
							value: item.id
						}), 
						item.name
					)
				);
			});
		}
	});
}
function loadModeCheckbox(checkboxSel) {
	$.ajax({
		url: '/as/mode/getAllModes',
		type: 'GET',
		success: function(result) {
			$(checkboxSel).empty();
			var modeList = result.extend.modeList;
			$.each(modeList, function(index, item) {
				$(checkboxSel).append(
					$('<label></label>').attr('class', 'checkbox-inline').append(
						$('<input/>').attr({
							type: 'checkbox',
							name: 'modeId',
							value: item.id
						}), 
						item.name
					)
				);
			});
		}
	});
}
//加载修改表单的mode
function loadModeCheckboxModify(checkboxSel, selectedModes) {
	$.ajax({
		url: '/as/mode/getAllModes',
		type: 'GET',
		success: function(result) {
			$(checkboxSel).empty();
			var modeList = result.extend.modeList;
			$.each(modeList, function(index, item) {
				$(checkboxSel).append(
					$('<label></label>').attr('class', 'checkbox-inline').append(
						$('<input/>').attr({
							type: 'checkbox',
							name: 'modeId',
							value: item.id
						}), 
						item.name
					)
				);
			});
			$.each($(checkboxSel).find('input'), function(checkboxIndex, checkboxItem) {
				var checkedOrNot = false;
				$.each(selectedModes, function(modeIndex, modeItem) {
					if($(checkboxItem).val() == modeItem.id) {
						checkedOrNot = true;
						// 退出一层循环
						return false;
					}
				});
				$(checkboxItem).attr('checked', checkedOrNot);
			});
		}
	});
}
//加载修改表单中的subject
function loadSubjectCheckboxModify(checkboxSel, selectedSubjects) {
	$.ajax({
		url: '/as/subject/getAll',
		type: 'GET',
		success: function(result) {
			$(checkboxSel).empty();
			var subjectList = result.extend.subjectList;
			$.each(subjectList, function(index, item) {
				$(checkboxSel).append(
						$('<label></label>').attr('class', 'checkbox-inline').append(
								$('<input/>').attr({
									type: 'checkbox',
									name: 'subjectId',
									value: item.id
								}), 
								item.name
						)
				);
			});
			$.each($(checkboxSel).find('input'), function(checkboxIndex, checkboxItem) {
				var checkedOrNot = false;
				$.each(selectedSubjects, function(subjectIndex, subjectItem) {
					if($(checkboxItem).val() == subjectItem.id) {
						checkedOrNot = true;
						// 退出一层循环
						return false;
					}
				});
				$(checkboxItem).attr('checked', checkedOrNot);
			});
		}
	});
}
//加载修改表单中的grade
function loadGradeCheckboxModify(checkboxSel, selectedGrades) {
	$.ajax({
		url: '/as/grade/getAll',
		type: 'GET',
		success: function(result) {
			$(checkboxSel).empty();
			var gradeList = result.extend.gradeList;
			$.each(gradeList, function(index, item) {
				$(checkboxSel).append(
						$('<label></label>').attr('class', 'checkbox-inline').append(
								$('<input/>').attr({
									type: 'checkbox',
									name: 'gradeId',
									value: item.id
								}), 
								item.name
						)
				);
			});
			$.each($(checkboxSel).find('input'), function(checkboxIndex, checkboxItem) {
				var checkedOrNot = false;
				$.each(selectedGrades, function(gradeIndex, gradeItem) {
					if($(checkboxItem).val() == gradeItem.id) {
						checkedOrNot = true;
						// 退出一层循环
						return false;
					}
				});
				$(checkboxItem).attr('checked', checkedOrNot);
			});
		}
	});
}

function loadDistrCheckbox(cityId, distrSel) {
	$.ajax({
		url: '/as/district/getDistrsByCity',
		type: 'POST',
		data: {'cityId': cityId},
		success: function(result) {
			$(distrSel).empty();
			var distrList = result.extend.distrList;
			$.each(distrList, function(index, item) {
				$(distrSel).append(
					$('<label></label>').attr('class', 'checkbox-inline').append(
						$('<input/>').attr({
							type: 'checkbox',
							name: 'distrId',
							value: item.id
						}), 
						item.name
					)
				);
			});
		}
	});
}

//  阻止表单按钮的默认提交行为
$('#thome-register-form').on('submit', function() {
	return false;
});
//  ajax注册tutor
$('#thome-register-button').click(function() {
	$('#t-user-name').trigger('blur');
	$('#t-password').trigger('blur');
	$('#t-password-confirm').trigger('blur');
	$('#t-real-name').trigger('blur');
	$('#t-id-card').trigger('blur');
	$('#t-univ').trigger('blur');
	$('#t-major').trigger('blur');
	$('#t-phone').trigger('blur');
	$('#t-email').trigger('blur');
	$('#t-qq').trigger('blur');
	$('#t-subject').find('input').eq(0).trigger('change');
	$('#t-grade').find('input').eq(0).trigger('change');
	$('#t-distr').find('input').eq(0).trigger('change');
	$('#t-mode').find('input').eq(0).trigger('change');
	$('#t-agreement').find('input').eq(0).trigger('change');
	$('#t-intro').trigger('blur');
	
	if($('#thome-register-form .form-help-text').is(':visible')) {
		$('#common-hint-modal-body').text('您填写的有些格式不正确，请根据提示修改后再提交。');
		$('#common-hint-modal').modal();
		return;
	}
	// 上传photo图片，更新photo字段的值
	var userName = $('#t-user-name').val();
	uploadPhotoToAliOss(userName, '#t-photo-hidden');
	
	$.ajax({
		url: '/as/tutor/register',
		type: 'POST',
		data: $('#thome-register-form').serialize(),
		success: function(result) {
			if(result.code == 1) {
				$('#t-register-success-modal').modal();
			} else {
				$('#common-hint-modal-body').text('注册失败！请重新注册。');
				$('#common-hint-modal').modal();
			}
		},
		error: function() {
			$('#common-hint-modal-body').text('注册失败！请检查网络后重试。');
			$('#common-hint-modal').modal();
		}
	});
});

// 提交时，同时上传photo到阿里云OSS的方法
function uploadPhotoToAliOss(userName, photoUrlSel) {
	var fileSuffix = $(photoUrlSel).attr('fileSuffix');
	var urlParam = $(photoUrlSel).attr('urlParam');
	if(userName && fileSuffix && urlParam) {
		var photoUrl = userName + fileSuffix + urlParam;
		$(photoUrlSel).val(photoUrl);
		
		var fileName = userName + fileSuffix;
		var formData = new FormData();
		var file = document.getElementById('modal-photo-input').files[0];
		formData.append('key', 'photo/' + fileName);
		formData.append('success_action_status', '200');
		formData.append('file', file);
		formData.append('submit', 'Upload to OSS');
		$.ajax({
			url: 'http://pub.xhtutor.com/',
			type: 'POST',
			data: formData,
			processData: false,
	        cache: false,
	        contentType: false,
		});
	} else {
		$(photoUrlSel).val('');
	}
}

// tutor注册成功后modal的绑定事件，跳转页面
$('body').on('hidden.bs.modal', '#t-register-success-modal', function() {
	// reload参数为true时强制从服务器获取最新页面，有清除表单内容的效果（false时是缓存）
	window.location.reload(true);
});

// 表单验证
//验证用户名事件绑定
$('#t-user-name').blur(function() {
	checkUname(this);
});
$('#t-user-name').focus(function() {
	removeCheckStatus(this);
});
// 验证密码事件绑定
$('#t-password').blur(function() {
	checkPassword(this);
	confirmPassword('#t-password-confirm', '#t-password');
});
$('#t-password').focus(function() {
	removeCheckStatus(this);
	removeCheckStatus('#t-password-confirm');
});
// 验证重复密码事件绑定
$('#t-password-confirm').blur(function() {
	confirmPassword(this, '#t-password');
});
$('#t-password-confirm').focus(function() {
	removeCheckStatus(this);
});
// 验证真实姓名事件绑定
$('#t-real-name').blur(function() {
	checkRname(this);
});
$('#t-real-name').focus(function() {
	removeCheckStatus(this);
});
// 验证身份证事件绑定
$('#t-id-card').blur(function() {
	checkIDCard(this);
});
$('#t-id-card').focus(function() {
	removeCheckStatus(this);
});
//验证就读/毕业院校事件绑定
$('#t-univ').blur(function() {
	checkUniversity(this);
});
$('#t-univ').focus(function() {
	removeCheckStatus(this);
});
// 验证所学专业事件绑定
$('#t-major').blur(function() {
	checkMajor(this);
});
$('#t-major').focus(function() {
	removeCheckStatus(this);
});
// 验证手机号码事件绑定
$('#t-phone').blur(function() {
	checkPhoneAndUnique(this);
});
$('#t-phone').focus(function() {
	removeCheckStatus(this);
});
// 验证邮箱事件绑定
$('#t-email').blur(function() {
	checkEmail(this);
});
$('#t-email').focus(function() {
	removeCheckStatus(this);
});
// 验证qq格式事件绑定
$('#t-qq').blur(function() {
	checkQQ(this);
});
$('#t-qq').focus(function() {
	removeCheckStatus(this);
});
// 验证科目checkbox事件绑定
$('#t-subject').on('change', 'input', function() {
	checkCheckbox('#t-subject');
});
// 验证年级checkbox事件绑定
$('#t-grade').on('change', 'input', function() {
	checkCheckbox('#t-grade');
});
// 验证地区checkbox事件绑定
$('#t-distr').on('change', 'input', function() {
	checkCheckbox('#t-distr');
});
// 验证辅导方式checkbox事件绑定
$('#t-mode').on('change', 'input', function() {
	checkCheckbox('#t-mode');
});
// 验证同意服务条款checkbox事件绑定
$('#t-agreement').on('change', 'input', function() {
	checkCheckbox('#t-agreement');
});
// 验证自我介绍事件绑定
$('#t-intro').blur(function() {
	checkIntro(this);
});
$('#t-intro').focus(function() {
	removeCheckStatus(this);
});

// 用户名格式验证
function checkUname(selector) {
	var $ele = $(selector);
	var val = $ele.val();
	if(!val) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 用户名不能为空');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else if(val.length < 3) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 用户名不能少于3个字符');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else if(val.length > 20) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 用户名不能超过20个字符');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else {
		$.ajax({
			url: '/as/tutor/checkUserName',
			type: 'POST',
			async: false,
			data: {userName: val},
			success: function(result) {
				if(result.code == 1) {
					$ele.parent().addClass('has-success has-feedback');
					$ele.nextAll('.glyphicon-ok').show();
				} else {
					// 这里removeStatus是防止feedback图标叠加出现
					removeCheckStatus(selector);
					$ele.parent().addClass('has-error has-feedback');
					$ele.nextAll('.form-help-text').text('* 该用户名已经被使用');
					$ele.nextAll('.glyphicon-remove').show();
					$ele.nextAll('.form-help-text').show();
				}
			},
			error: function() {
				// 这里removeStatus是防止feedback图标叠加出现
				removeCheckStatus('#t-user-name');
				$ele.parent().addClass('has-error has-feedback');
				$ele.nextAll('.form-help-text').text('* 用户名验证失败');
				$ele.nextAll('.glyphicon-remove').show();
				$ele.nextAll('.form-help-text').show();
			}
		});
	}
}

// 手机号码验证方法
function checkPhoneAndUnique(selector) {
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
		$.ajax({
			url: '/as/tutor/checkTutorPhoneUnique',
			type: 'POST',
			async: false,
			data: {phone: val},
			success: function(result) {
				if(result.code == 1) {
					$ele.parent().addClass('has-success has-feedback');
					$ele.nextAll('.glyphicon-ok').show();
				} else {
					// 这里removeStatus是防止feedback图标叠加出现
					removeCheckStatus(selector);
					$ele.parent().addClass('has-error has-feedback');
					$ele.nextAll('.form-help-text').text('* 该手机号已经被注册');
					$ele.nextAll('.glyphicon-remove').show();
					$ele.nextAll('.form-help-text').show();
				}
			},
			error: function() {
				// 这里removeStatus是防止feedback图标叠加出现
				removeCheckStatus(selector);
				$ele.parent().addClass('has-error has-feedback');
				$ele.nextAll('.form-help-text').text('* 手机号验证失败，请检查网络后重试');
				$ele.nextAll('.glyphicon-remove').show();
				$ele.nextAll('.form-help-text').show();
			}
		});
	}
}

// 重复密码格式验证
function confirmPassword(selctor, compareSel) {
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
	} else if(val != $(compareSel).val()) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 两次输入的密码不同');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else if(val == $(compareSel).val()) {
		$ele.parent().addClass('has-success has-feedback');
		$ele.nextAll('.glyphicon-ok').show();
	}
}

// 真实姓名格式验证
function checkRname(selector) {
	var $ele = $(selector);
	var val = $ele.val();
	if(!val) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 真实姓名不能为空');
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

// 身份证号码格式验证
function checkIDCard(selector) {
	var $ele = $(selector);
	var val = $ele.val();
	if(val) {
		if(val.length < 15) {
			$ele.parent().addClass('has-error has-feedback');
			$ele.nextAll('.form-help-text').text('* 长度不少于15个字符');
			$ele.nextAll('.glyphicon-remove').show();
			$ele.nextAll('.form-help-text').show();
		} else if(val.length > 20) {
			$ele.parent().addClass('has-error has-feedback');
			$ele.nextAll('.form-help-text').text('* 长度不超过20个字符');
			$ele.nextAll('.glyphicon-remove').show();
			$ele.nextAll('.form-help-text').show();
		} else {
			$ele.parent().addClass('has-success has-feedback');
			$ele.nextAll('.glyphicon-ok').show();
		}
	}
}

// 就读/毕业院校验证方法
function checkUniversity(selector) {
	var $ele = $(selector);
	var val = $ele.val();
	if(!val) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 院校不能为空');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else if(val.length > 20) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 长度不超过20个字符');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else {
		$ele.parent().addClass('has-success has-feedback');
		$ele.nextAll('.glyphicon-ok').show();
	}
}

// 所学专业验证方法
function checkMajor(selector) {
	var $ele = $(selector);
	var val = $ele.val();
	if(!val) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 专业不能为空');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else if(val.length > 20) {
		$ele.parent().addClass('has-error has-feedback');
		$ele.nextAll('.form-help-text').text('* 长度不超过20个字符');
		$ele.nextAll('.glyphicon-remove').show();
		$ele.nextAll('.form-help-text').show();
	} else {
		$ele.parent().addClass('has-success has-feedback');
		$ele.nextAll('.glyphicon-ok').show();
	}
}

// QQ号格式验证
function checkQQ(selector) {
	var $ele = $(selector);
	var val = $ele.val();
	var qqRegex = /^[0-9]+$/;
	if(val) {
		if(!qqRegex.test(val)) {
			$ele.parent().addClass('has-error has-feedback');
			$ele.nextAll('.form-help-text').text('* 格式不正确(数字)');
			$ele.nextAll('.glyphicon-remove').show();
			$ele.nextAll('.form-help-text').show();
		} else if(val.length > 20) {
			$ele.parent().addClass('has-error has-feedback');
			$ele.nextAll('.form-help-text').text('* 长度不超过20个字符');
			$ele.nextAll('.glyphicon-remove').show();
			$ele.nextAll('.form-help-text').show();
		} else {
			$ele.parent().addClass('has-success has-feedback');
			$ele.nextAll('.glyphicon-ok').show();
		}
	}
}
// 验证radio必选方法
function checkRadio(selector) {
	var $items = $(selector).find('input');
	var result = false;
	$.each($items, function(index, item) {
		if(item.checked) {
			result = true;
			// 跳出循环
			return false;
		}
	});
	if(result) {
		$(selector).next().hide();
	} else {
		$(selector).next().show();
	}
}

// 验证checkbox必选方法
function checkCheckbox(selector) {
	var $items = $(selector).find('input');
	var result = false;
	$.each($items, function(index, item) {
		if(item.checked) {
			result = true;
			// 跳出循环
			return false;
		}
	});
	if(result) {
		$(selector).next().hide();
	} else {
		$(selector).next().show();
	}
}

// 自我描述格式验证
function checkIntro(selector) {
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

// thome页面个人信息修改按钮绑定事件

// 修改按钮切换显示
$('#tutor-info-content .modify-button').click(function() {
	$(this).parents('.tab-pane').find('.before-modify').hide();
	$(this).parents('.tab-pane').find('.while-modify').show();
});
// 取消按钮切换显示
$('#tutor-info-content .modify-cancel-button').click(function() {
	// 取消时重新加载，重置修改表单的信息
	loadLoginTutorInfo();
	$(this).parents('.tab-pane').find('.while-modify').hide();
	$(this).parents('.tab-pane').find('.before-modify').show();
});
//修改结果的modal关闭时，显示before-modify内容
$('#t-modify-result-modal').on('hidden.bs.modal', function() {
	$('#tutor-info-content .while-modify').hide();
	$('#tutor-info-content .before-modify').show();
});
// 确认修改头像按钮
$('#tutor-register-info .modify-submit-button').click(function() {
	if($('#t-photo-hidden-modify').attr('fileSuffix') && $('#t-photo-hidden-modify').attr('urlParam')) {
		// 上传photo图片，更新photo字段的值
		var userName = $('#t-user-name-modify').text();
		uploadPhotoToAliOss(userName, '#t-photo-hidden-modify');
		var photoUrl = $('#t-photo-hidden-modify').val();
		$.ajax({
			url: '/as/tutor/tutorModifySelective',
			type: 'POST',
			data: {photo: photoUrl},
			success: function(result) {
				if(result.code == 1) {
					// 修改成功后重新加载用户信息
					loadLoginTutorInfo();
					$('#t-modify-result-text').text('头像修改成功！');
					$('#t-modify-result-modal').modal();
				} else {
					$('#t-modify-result-text').text('头像修改失败！');
					$('#t-modify-result-modal').modal();
				}
			},
			error: function() {
				$('#t-modify-result-text').text('头像修改失败！');
				$('#t-modify-result-modal').modal();
			}
		});
	} else {
		$('#t-modify-result-text').text('未修改头像！');
		$('#t-modify-result-modal').modal();
	}
});
// 确认修改基本信息按钮
$('#tutor-base-info .modify-submit-button').click(function() {
	$('#t-real-name-modify').trigger('blur');
	$('#t-id-card-modify').trigger('blur');
	$('#t-univ-modify').trigger('blur');
	$('#t-major-modify').trigger('blur');
	
	if($('#tutor-base-info-modify .form-help-text').is(':visible')) {
		$('#common-hint-modal-body').text('您填写的有些格式不正确，请根据提示修改后再提交。');
		$('#common-hint-modal').modal();
		return;
	}
	
	$.ajax({
		url: '/as/tutor/tutorModifySelective',
		type: 'POST',
		data: $('#tutor-base-info-modify').serialize(),
		success: function(result) {
			if(result.code == 1) {
				// 修改成功后重新加载用户信息
				loadLoginTutorInfo();
				$('#t-modify-result-text').text('基本信息修改成功！');
				$('#t-modify-result-modal').modal();
			} else {
				$('#t-modify-result-text').text('基本信息修改失败！');
				$('#t-modify-result-modal').modal();
			}
		},
		error: function() {
			$('#t-modify-result-text').text('基本信息修改失败！');
			$('#t-modify-result-modal').modal();
		}
	});
});
// 确认修改联系方式按钮
$('#tutor-contact-info .modify-submit-button').click(function() {
	$('#t-phone-modify').trigger('blur');
	$('#t-email-modify').trigger('blur');
	$('#t-qq-modify').trigger('blur');
	
	if($('#tutor-contact-info-modify .form-help-text').is(':visible')) {
		$('#common-hint-modal-body').text('您填写的有些格式不正确，请根据提示修改后再提交。');
		$('#common-hint-modal').modal();
		return;
	}
	
	$.ajax({
		url: '/as/tutor/tutorModifySelective',
		type: 'POST',
		data: $('#tutor-contact-info-modify').serialize(),
		success: function(result) {
			if(result.code == 1) {
				// 修改成功后重新加载用户信息
				loadLoginTutorInfo();
				$('#t-modify-result-text').text('联系方式修改成功！');
				$('#t-modify-result-modal').modal();
			} else {
				$('#t-modify-result-text').text('联系方式修改失败！');
				$('#t-modify-result-modal').modal();
			}
		},
		error: function() {
			$('#t-modify-result-text').text('联系方式修改失败！');
			$('#t-modify-result-modal').modal();
		}
	});
});
// 确认修改家教信息按钮
$('#tutor-teach-info .modify-submit-button').click(function() {
	$('#t-subject-modify').find('input').eq(0).trigger('change');
	$('#t-grade-modify').find('input').eq(0).trigger('change');
	$('#t-distr-modify').find('input').eq(0).trigger('change');
	$('#t-mode-modify').find('input').eq(0).trigger('change');
	$('#t-intro-modify').trigger('blur');
	
	if($('#tutor-teach-info-modify .form-help-text').is(':visible')) {
		$('#common-hint-modal-body').text('您填写的有些格式不正确，请根据提示修改后再提交。');
		$('#common-hint-modal').modal();
		return;
	}
	
	$.ajax({
		url: '/as/tutor/tutorModifyTeachInfo',
		type: 'POST',
		data: $('#tutor-teach-info-modify').serialize(),
		success: function(result) {
			if(result.code == 1) {
				// 修改成功后重新加载用户信息
				loadLoginTutorInfo();
				$('#t-modify-result-text').text('家教信息修改成功！');
				$('#t-modify-result-modal').modal();
			} else {
				$('#t-modify-result-text').text('家教信息修改失败！');
				$('#t-modify-result-modal').modal();
			}
		},
		error: function() {
			$('#t-modify-result-text').text('家教信息修改失败！');
			$('#t-modify-result-modal').modal();
		}
	});
});
// 确认修改密码按钮
$('#tutor-password-info .modify-submit-button').click(function() {
	$('#t-password-origin-modify').trigger('blur');
	$('#t-password-modify').trigger('blur');
	$('#t-password-confirm-modify').trigger('blur');
	
	if($('#tutor-password-info-modify .form-help-text').is(':visible')) {
		$('#common-hint-modal-body').text('您填写的有些格式不正确，请根据提示修改后再提交。');
		$('#common-hint-modal').modal();
		return;
	}
	
	$.ajax({
		url: '/as/tutor/tutorModifyPassword',
		type: 'POST',
		data: $('#tutor-password-info-modify').serialize(),
		success: function(result) {
			if(result.code == 1) {
				// 清空密码表单值
				$('#t-password-origin-modify').val('');
				$('#t-password-modify').val('');
				$('#t-password-confirm-modify').val('');
				removeCheckStatus('#t-password-origin-modify');
				removeCheckStatus('#t-password-modify');
				removeCheckStatus('#t-password-confirm-modify');
				
				$('#t-modify-result-text').text(result.extend.message);
				$('#t-modify-result-modal').modal();
			} else {
				$('#t-modify-result-text').text(result.extend.message);
				$('#t-modify-result-modal').modal();
			}
		},
		error: function() {
			$('#t-modify-result-text').text('密码修改失败！');
			$('#t-modify-result-modal').modal();
		}
	});
});


// 修改表单，家乡省份的select添加onchange事件
$('#t-birth-prov-modify').change(function() {
	updateCitysByProv($('#t-birth-prov-modify').val(), '#t-birth-city-modify');
});
// 修改表单，当前省份的select添加onchange事件
$('#t-curr-prov-modify').change(function() {
	updateCitysByProv($('#t-curr-prov-modify').val(), '#t-curr-city-modify');
});
// 修改表单，当前城市的select添加change事件，更新地区select
$('#t-curr-city-modify').change(function() {
	updateDistrsByCity($('#t-curr-city-modify').val(), '#t-curr-distr-modify');
});
// 修改表单，辅导省份的select添加onchange事件
$('#t-teach-prov-modify').change(function() {
	updateCitysByProv($('#t-teach-prov-modify').val(), '#t-teach-city-modify');
});
// 修改表单，辅导城市的select添加change事件，更新辅导地区checkbox选择项
$('#t-teach-city-modify').change(function() {
	// 省份改变时js改变城市，也会触发city的change事件
	var cityId = $('#t-teach-city-modify').val();
	loadDistrCheckbox(cityId, '#t-distr-modify');
});


// 修改表单，验证真实姓名事件绑定
$('#t-real-name-modify').blur(function() {
	checkRname(this);
});
$('#t-real-name-modify').focus(function() {
	removeCheckStatus(this);
});
// 修改表单，验证身份证事件绑定
$('#t-id-card-modify').blur(function() {
	checkIDCard(this);
});
$('#t-id-card-modify').focus(function() {
	removeCheckStatus(this);
});
// 修改表单，验证就读/毕业院校事件绑定
$('#t-univ-modify').blur(function() {
	checkUniversity(this);
});
$('#t-univ-modify').focus(function() {
	removeCheckStatus(this);
});
// 修改表单，验证所学专业事件绑定
$('#t-major-modify').blur(function() {
	checkMajor(this);
});
$('#t-major-modify').focus(function() {
	removeCheckStatus(this);
});
// 修改表单，验证手机号码事件绑定
$('#t-phone-modify').blur(function() {
	checkPhoneAndUnique(this);
});
$('#t-phone-modify').focus(function() {
	removeCheckStatus(this);
});
// 修改表单，验证邮箱事件绑定
$('#t-email-modify').blur(function() {
	checkEmail(this);
});
$('#t-email-modify').focus(function() {
	removeCheckStatus(this);
});
// 修改表单，验证qq格式事件绑定
$('#t-qq-modify').blur(function() {
	checkQQ(this);
});
$('#t-qq-modify').focus(function() {
	removeCheckStatus(this);
});
// 修改表单，验证科目checkbox事件绑定
$('#t-subject-modify').on('change', 'input', function() {
	checkCheckbox('#t-subject-modify');
});
// 修改表单，验证年级checkbox事件绑定
$('#t-grade-modify').on('change', 'input', function() {
	checkCheckbox('#t-grade-modify');
});
// 修改表单，验证地区checkbox事件绑定
$('#t-distr-modify').on('change', 'input', function() {
	checkCheckbox('#t-distr-modify');
});
// 修改表单，验证辅导方式checkbox事件绑定
$('#t-mode-modify').on('change', 'input', function() {
	checkCheckbox('#t-mode-modify');
});
// 修改表单，验证自我介绍事件绑定
$('#t-intro-modify').blur(function() {
	checkIntro(this);
});
$('#t-intro-modify').focus(function() {
	removeCheckStatus(this);
});
// 修改表单，验证初始密码事件绑定
$('#t-password-origin-modify').blur(function() {
	checkPassword(this);
});
$('#t-password-origin-modify').focus(function() {
	removeCheckStatus(this);
});
// 修改表单，验证新密码事件绑定
$('#t-password-modify').blur(function() {
	checkPassword(this);
	confirmPassword('#t-password-confirm-modify', '#t-password-modify');
});
$('#t-password-modify').focus(function() {
	removeCheckStatus(this);
	removeCheckStatus('#t-password-confirm-modify');
});
// 修改表单，验证重复密码事件绑定
$('#t-password-confirm-modify').blur(function() {
	confirmPassword(this, '#t-password-modify');
});
$('#t-password-confirm-modify').focus(function() {
	removeCheckStatus(this);
});




