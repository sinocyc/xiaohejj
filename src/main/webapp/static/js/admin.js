/**
 * admin页面的js文件
 */

$(function() {
	locateByIp();
	loadSubjectUl('#admin-tutors-subject-filter');
	loadGradeUl('#admin-tutors-grade-filter');
	isAdminLogin();
});

//更新页面中与城市相关的内容,currCity改变时调用
function updateInfoByCity(cityId, provId) {
	updateDistrUlByCity(cityId, '#admin-tutors-distr-filter');
	updateTutorListByCondPage(1, 8);
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

//给筛选条件的li绑定点击事件
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

//给定页码，根据页面筛选条件，生成查询条件数据
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
					var photoUrl = 'http://pub.xhtutor.com/photo/' + photo;
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
					var tutorListItemStr = '<div class="row admin-tutor-item">' +
								'<div class="col-sm-1 no-padding-r tutor-photo">' +
								'	<a href="#">' +
								'    	<img class="img-responsive img-rounded center-block" src="' + photoUrl + '" alt="photo">' +
							  	'	</a>' +
								'</div>' +
								'<div class="col-sm-11">' +
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
							    '	<span><strong>自我描述：</strong>' + tutorItem.intro + '</span>' +
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




