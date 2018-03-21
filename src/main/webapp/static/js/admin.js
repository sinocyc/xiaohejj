/**
 * admin页面的js文件
 */

$(function() {
	locateByIp();
	loadSubjectUl('#admin-tutors-subject-filter');
	loadGradeUl('#admin-tutors-grade-filter');
	isAdminLogin();
	//学生filter加载
	loadSubjectUl('#admin-students-subject-filter');
	loadGradeUl('#admin-students-grade-filter');
});

//更新页面中与城市相关的内容,currCity改变时调用
function updateInfoByCity(cityId, provId) {
	updateDistrUlByCity(cityId, '#admin-tutors-distr-filter');
	updateDistrUlByCity(cityId, '#admin-students-distr-filter');
	isAdminLogin();
}

// 判断是否登录，修改页面内容
function isAdminLogin() {
	$.ajax({
		url: '/as/admin/isAdminLogin',
		type: 'GET',
		cache: false,
		success: function(result) {
			if(result.code == 1) {
				$('.after-login').show();
				updateTutorListByCondPage(1, 8);
				updateOrderListByCondPage(1, 8);
			} else {
				$('.before-login').show();
				$('#admin-login-modal').modal('show');
			}
		},
		error: function() {
			$('.before-login').show();
			$('#admin-login-modal').modal('show');
		}
	});
}

function loadTutorsInfo() {
	
}

//top-row中tutor退出登录按钮
$('body').on('click', '#admin-top-row-logout', function() {
	adminLogout();
});

// admin退出登录
function adminLogout() {
	$.ajax({
		url: '/as/admin/adminLogout',
		type: 'GET',
		success: function(result) {
			window.location.reload(true);
		},
		error: function() {
			window.location.reload(true);
		}
	});
}

//tutor给筛选条件的li绑定点击事件
$('#admin-tutor-filters').on('click', 'li', function() {
	$(this).siblings('.active').removeClass('active');
	$(this).addClass('active');
	updateTutorListByCondPage(1, 8);
});

function updateTutorListByCondPage(pageNum, pageSize) {
	var conditionData = generateTutorConditionData(pageNum, pageSize);
	updateTutorListByCondition(conditionData);
	updateTutorPagerInfo(pageNum, pageSize);
}

//tutor给定页码，根据页面筛选条件，生成查询条件数据
function generateTutorConditionData(pageNum, pageSize) {
	var subjectId = $('#admin-tutors-subject-filter li.active').attr('subject-id');
	var gradeId = $('#admin-tutors-grade-filter li.active').attr('grade-id');
	var distrId = $('#admin-tutors-distr-filter li.active').attr('distr-id');
	var modeId = $('#admin-tutors-mode-filter li.active').attr('mode-id');
	var tutorType = $('#admin-tutors-type-filter li.active').attr('tutor-type');
	var gender = $('#admin-tutors-gender-filter li.active').attr('gender');
	var teachCityId = $('#current-city').attr('city-id');
	var start = (pageNum - 1) * pageSize;
	var num = pageSize;
	////////////////////暂时不设置teachCityId参数///////////////////////////////////////////////////////////
	var conditionData = {
		subjectId: subjectId,
		gradeId: gradeId,
		distrId: distrId,
		modeId: modeId,
		tutorType: tutorType,
		gender: gender,
		teachCityId: teachCityId,
		start: start,
		num: num
	};
	return conditionData;
}

function updateTutorListByCondition(conditionData) {
	$.ajax({
		url: '/as/tutor/getTutorsDetailByCondition',
		type: 'POST',
		data: conditionData,
		success: function(result) {
			if(result.code == 1) {
				var tutorList = result.extend.tutorList;
				$('#admin-tutors-list-wrap').empty();
				$.each(tutorList, function(tutorIndex, tutorItem) {
					var nameStr = tutorItem.realName;
					var subjectStr = '';
					$.each(tutorItem.subjectList, function(subjectIndex, subjectItem) {
						subjectStr = subjectStr + subjectItem.name + ' / ';
					});
					var gradeStr = '';
					$.each(tutorItem.gradeList, function(gradeIndex, gradeItem) {
						gradeStr = gradeStr + gradeItem.name + ' / ';
					});
					var distrStr = '';
					$.each(tutorItem.distrList, function(distrIndex, distrItem) {
						distrStr = distrStr + distrItem.name + ' / ';
					});
					var modeStr = '';
					$.each(tutorItem.modeList, function(modeIndex, modeItem) {
						modeStr = modeStr + modeItem.name + ' / ';
					});
					var photo = tutorItem.photo ? tutorItem.photo : 'default-photo.png';
					var photoUrl = 'https://tutor-public.oss-cn-hangzhou.aliyuncs.com/photo/' + photo;
					var tutorTypeName = '';
					switch(tutorItem.tutorType) {
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
					var genderName = '';
					if(tutorItem.gender == 'm') {
						genderName = '男';
					} else {
						genderName = '女';
					}
					var time = new Date(tutorItem.lastLogin);
					var timeStr = time.toLocaleDateString() + ' ' + time.getHours() + ':' + time.getMinutes() + ':' + time.getSeconds();
					var tutorId = tutorItem.id;
					var statusStr = '';
					var btnStr = '';
					switch(tutorItem.status) {
					case 1:
						statusStr = '可接单';
						btnStr = '	<button tutor-id="' + tutorId + '" class="btn btn-danger btn-sm tutor-hide">隐藏</button>' +
								'	<button tutor-id="' + tutorId + '" class="btn btn-success btn-sm tutor-show hidden">显示</button>';
						break;
					case 9:
						statusStr = '隐藏';
						btnStr = '	<button tutor-id="' + tutorId + '" class="btn btn-danger btn-sm tutor-hide hidden">隐藏</button>' +
						'	<button tutor-id="' + tutorId + '" class="btn btn-success btn-sm tutor-show">显示</button>';
						break;
					default:
						statusStr = '--';
						btnStr = '	<button tutor-id="' + tutorId + '" class="btn btn-danger btn-sm tutor-hide">隐藏</button>' +
						'	<button tutor-id="' + tutorId + '" class="btn btn-success btn-sm tutor-show hidden">显示</button>';
					}
					var tutorListItemStr = '<div class="row admin-tutor-item">' +
								'<div class="col-sm-1 no-padding-r tutor-photo">' +
								'	<a href="#">' +
								'    	<img class="img-responsive img-rounded center-block" src="' + photoUrl + '" alt="photo">' +
							  	'	</a>' +
								'</div>' +
								'<div class="col-sm-10">' +
								'	<span><strong>姓名：</strong>' + nameStr + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
								'	<span><strong>性别：</strong>' + genderName + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
								'	<span><strong>身份：</strong>' + tutorTypeName + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
								'	<span><strong>学校：</strong>' + tutorItem.university + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
								'	<span><strong>专业：</strong>' + tutorItem.major + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
								'	<span><strong>最近登录：</strong>' + timeStr + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
								'	<span><strong>手机：</strong>' + tutorItem.phone + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
							    '	<span><strong>邮箱：</strong>' + tutorItem.email + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
								'	<span><strong>QQ：</strong>' + tutorItem.qq + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
							    '	<span><strong>身份证：</strong>' + tutorItem.idCardNum + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
								'	<span><strong>科目：</strong>' + subjectStr + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
							    '	<span><strong>年级：</strong>' + gradeStr + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
							    '	<span><strong>地区：</strong>' + distrStr + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
							    '	<span><strong>方式：</strong>' + modeStr + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
							    '	<span><strong>自我描述：</strong>' + tutorItem.intro + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
							    '	<span><strong>状态：</strong>' + statusStr + '</span>' +
								'</div>' +
								'<div class="col-sm-1">' +
								btnStr +
								'</div>' +
							'</div>';
					$('#admin-tutors-list-wrap').append(tutorListItemStr);
				});
			}
		}
	});
}

//更新tutors列表的pager的显示内容
function updateTutorPagerInfo(pageNum, pageSize) {
	$('#admin-tutors-page-num').text(pageNum);
	if(pageNum <= 1) {
		$('#admin-tutor-pager li.first-page-hide').hide();
	} else {
		$('#admin-tutor-pager li.first-page-hide').show();
	}
	// 验证是否有下一页
	var conditionData = generateTutorConditionData(pageNum + 1, pageSize);
	$.ajax({
		url: '/as/tutor/tutorPageHasContent',
		type: 'POST',
		data: conditionData,
		success: function(result) {
			if(result.code == 1) {
				// 下一页有内容
				$('#admin-tutor-pager li.last-page-hide').show();
			} else {
				// 下一页没有内容
				$('#admin-tutor-pager li.last-page-hide').hide();
			}
		},
		error: function() {
			$('#admin-tutor-pager li.last-page-hide').hide();
		}
	});
}

//给tutor pager的按钮绑定事件
$('#admin-tutors-page-first').click(function() {
	updateTutorListByCondPage(1, 8);
});
$('#admin-tutors-page-previous').click(function() {
	var pageNum = parseInt($('#admin-tutors-page-num').text());
	if(pageNum && pageNum > 1) {
		updateTutorListByCondPage(pageNum - 1, 8);
	}
});
$('#admin-tutors-page-next').click(function() {
	var pageNum = parseInt($('#admin-tutors-page-num').text());
	if(pageNum) {
		updateTutorListByCondPage(pageNum + 1, 8);
	}
});

//student给筛选条件的li绑定点击事件
$('#admin-student-filters').on('click', 'li', function() {
	$(this).siblings('.active').removeClass('active');
	$(this).addClass('active');
	updateOrderListByCondPage(1, 8);
});

function updateOrderListByCondPage(pageNum, pageSize) {
	var conditionData = generateOrderConditionData(pageNum, pageSize);
	updateOrderListByCondition(conditionData);
	updateOrderPagerInfo(pageNum, pageSize);
}

//student给定页码，根据页面筛选条件，生成查询条件数据
function generateOrderConditionData(pageNum, pageSize) {
	var subjectId = $('#admin-students-subject-filter li.active').attr('subject-id');
	var gradeId = $('#admin-students-grade-filter li.active').attr('grade-id');
	var distrId = $('#admin-students-distr-filter li.active').attr('distr-id');
	var teachCityId = $('#current-city').attr('city-id');
	var start = (pageNum - 1) * pageSize;
	var num = pageSize;
	////////////////////暂时不设置teachCityId参数///////////////////////////////////////////////////////////
	var conditionData = {
		subjectId: subjectId,
		gradeId: gradeId,
		distrId: distrId,
		teachCityId: teachCityId,
		start: start,
		num: num
	};
	return conditionData;
}

function updateOrderListByCondition(conditionData) {
	$.ajax({
		url: '/as/order/getOrdersDetailByCondition',
		type: 'POST',
		data: conditionData,
		success: function(result) {
			if(result.code == 1) {
				var orderList = result.extend.orderList;
				$('#admin-students-list-wrap').empty();
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
					var priceStr = orderItem.price == null ? '--' : orderItem.price;
					var orderId = orderItem.id;
					var statusStr = '';
					var btnStr = '';
					if(orderItem.status == 2) {
						statusStr = '已有教员';
						btnStr = '	<button tutor-id="' + orderId + '" class="btn btn-success btn-sm order-assign hidden">分配</button>' +
						'	<button tutor-id="' + orderId + '" class="btn btn-danger btn-sm order-assign-cancel">取消分配</button>';
					} else {
						statusStr = '未分配';
						btnStr = '	<button tutor-id="' + orderId + '" class="btn btn-success btn-sm order-assign">分配</button>' +
								'	<button tutor-id="' + orderId + '" class="btn btn-danger btn-sm order-assign-cancel hidden">取消</button>';
					}
					var orderListItemStr = '<div class="row admin-student-item">' +
								'<div class="col-sm-11">' +
								'	<span><strong>编号：</strong>' + codeStr + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
								'	<span><strong>科目：</strong>' + subjectName + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
								'	<span><strong>年级：</strong>' + gradeName + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
								'	<span><strong>联系人：</strong>' + orderItem.contactName + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
								'	<span><strong>电话：</strong>' + orderItem.phone + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
								'	<span><strong>位置：</strong>' + address + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
								'	<span><strong>要求：</strong>' + orderItem.message + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
							    '	<span><strong>更新时间：</strong>' + timeStr + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
								'	<span><strong>课时费：</strong>' + priceStr + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
							    '	<span><strong>状态：</strong>' + statusStr + '</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
								'</div>' +
								'<div class="col-sm-1">' +
								btnStr +
								'</div>' +
							'</div>';
					$('#admin-students-list-wrap').append(orderListItemStr);
				});
			}
		}
	});
}

//更新students列表的pager的显示内容
function updateOrderPagerInfo(pageNum, pageSize) {
	$('#admin-orders-page-num').text(pageNum);
	if(pageNum <= 1) {
		$('#admin-order-pager li.first-page-hide').hide();
	} else {
		$('#admin-order-pager li.first-page-hide').show();
	}
	// 验证是否有下一页
	var conditionData = generateOrderConditionData(pageNum + 1, pageSize);
	$.ajax({
		url: '/as/order/orderPageHasContent',
		type: 'POST',
		data: conditionData,
		success: function(result) {
			if(result.code == 1) {
				// 下一页有内容
				$('#admin-order-pager li.last-page-hide').show();
			} else {
				// 下一页没有内容
				$('#admin-order-pager li.last-page-hide').hide();
			}
		},
		error: function() {
			$('#admin-order-pager li.last-page-hide').hide();
		}
	});
}

//给student pager的按钮绑定事件
$('#admin-orders-page-first').click(function() {
	updateOrderListByCondPage(1, 8);
});
$('#admin-orders-page-previous').click(function() {
	var pageNum = parseInt($('#admin-orders-page-num').text());
	if(pageNum && pageNum > 1) {
		updateOrderListByCondPage(pageNum - 1, 8);
	}
});
$('#admin-orders-page-next').click(function() {
	var pageNum = parseInt($('#admin-orders-page-num').text());
	if(pageNum) {
		updateOrderListByCondPage(pageNum + 1, 8);
	}
});

//admin登录时表单验证
//验证用户名事件绑定
$('body').on('blur', '#admin-login-uname', function() {
	checkAdminLoginUname(this);
});
$('body').on('focus', '#admin-login-uname', function() {
	removeCheckStatus(this);
});
//验证密码事件绑定
$('body').on('blur', '#admin-login-password', function() {
	checkPassword(this);
});
$('body').on('focus', '#admin-login-password', function() {
	removeCheckStatus(this);
});
//admin登录
$('body').on('click', '#admin-login-btn', function() {
	$('#admin-login-uname').trigger('blur');
	$('#admin-login-password').trigger('blur');
	
	if($('#admin-login-modal .form-help-text').is(':visible')) {
		return;
	}
	$.ajax({
		url: '/as/admin/adminLoginByUserName',
		type: 'POST',
		data: {
			userName: $('#admin-login-uname').val(),
			password: $('#admin-login-password').val()
		},
		success: function(result) {
			if(result.code == 1) {
				$('#admin-login-modal').modal('hide');
				window.location.reload(true);
			} else {
				$('#admin-login-fail-hint').text('用户名或密码错误，请重新登录。');
				$('#admin-login-fail-hint').show();
			}
		},
		error: function() {
			$('#admin-login-fail-hint').text('登录失败，请重新登录。');
			$('#admin-login-fail-hint').show();
		}
	});
});
//登录modal中登录按钮失去焦点时，登录失败的提示要隐藏
$('body').on('blur', '#admin-login-btn', function() {
	$('#admin-login-fail-hint').empty();
	$('#admin-login-fail-hint').hide();
});
//取消登录后清空表单
$('body').on('hidden.bs.modal', '#admin-login-modal', function() {
	$('#admin-login-uname').val('');
	$('#admin-login-password').val('');
	removeCheckStatus('#admin-login-uname');
	removeCheckStatus('#admin-login-password');
});

//验证admin登录时的用户名格式
function checkAdminLoginUname(selector) {
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

// 教员隐藏/显示按钮点击事件
$('#admin-tutors-list-wrap').on('click', '.tutor-hide', function() {
	var tutorId = $(this).attr('tutor-id');
	tutorModifyStatus(tutorId, 9);
	$(this).addClass('hidden');
	$(this).siblings('.tutor-show').removeClass('hidden');
});
$('#admin-tutors-list-wrap').on('click', '.tutor-show', function() {
	var tutorId = $(this).attr('tutor-id');
	tutorModifyStatus(tutorId, 1);
	$(this).addClass('hidden');
	$(this).siblings('.tutor-hide').removeClass('hidden');
});

function tutorModifyStatus(tutorId, status) {
	$.ajax({
		url: '/as/tutor/tutorModifyStatus',
		type: 'POST',
		data: {id: tutorId, status: status},
		success: function(result) {
			if(result.code == 1) {
				var pageNum = parseInt($('#admin-tutors-page-num').text());
				if(pageNum) {
					updateTutorListByCondPage(pageNum, 8);
				}
			}
		}
	});
}

// order分配/取消分配按钮点击事件
$('#admin-students-list-wrap').on('click', '.order-assign', function() {
	$(this).addClass('hidden');
	$(this).siblings('.order-assign-cancel').removeClass('hidden');
});
$('#admin-students-list-wrap').on('click', '.order-assign-cancel', function() {
	$(this).addClass('hidden');
	$(this).siblings('.order-assign').removeClass('hidden');
});




