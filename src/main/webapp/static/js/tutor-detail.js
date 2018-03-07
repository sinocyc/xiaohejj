/**
 * tutor-detail页面的js文件
 */
// 页面加载后
$(function() {
	locateByIp();
	isTutorLoginShowHide();
	updateOrderFormSubjectGradeSel();
	
	var url = window.location.href;
	var tutorId = getUrlQueryParamValue('tutorId');
	updateTutorDetailPageById(tutorId);
});

//更新页面中与城市相关的内容,currCity改变时调用
function updateInfoByCity(cityId, provId) {
	updateOrderFormLocationSelect(cityId, provId);
}

// 根据id更新tutor-detail页面的信息
function updateTutorDetailPageById(tutorId) {
	$.ajax({
		url: '/as/tutor/getTutorInfoById',
		type: 'POST',
		data: {tutorId: tutorId},
		success: function(result) {
			if(result.code == 1) {
				var tutor = result.extend.tutor;
				var photo = tutor.photo ? tutor.photo : 'default-photo.png';
				var photoUrl = 'https://tutor-public.oss-cn-hangzhou.aliyuncs.com/photo/' + photo;
				var nameStr = tutor.realName.substring(0, 1) + '教员';
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
				$('#tutor-detail-photo').attr('src', photoUrl);
				$('#tutor-detail-real-name').text(nameStr);
				$('#tutor-detail-gender').text(genderStr);
				$('#tutor-detail-univ').text(tutor.university);
				$('#tutor-detail-major').text(tutor.major);
				$('#tutor-detail-type').text(tutorTypeName);
				$('#tutor-detail-subject').text(subjectStr);
				$('#tutor-detail-grade').text(gradeStr);
				$('#tutor-detail-distr').text(distrStr);
				$('#tutor-detail-mode').text(modeStr);
				$('#tutor-detail-intro').text(tutor.intro);
			} else {
				$('#common-hint-modal-body').text('加载教员详情失败，请刷新页面重试。');
				$('#common-hint-modal').modal();
			}
		},
		error: function() {
			$('#common-hint-modal-body').text('加载教员详情失败，请检查网络后重试。');
			$('#common-hint-modal').modal();
		}
	});
}








