/**
 * tutors页面的js文件
 */
// 页面加载后
$(function() {
	locateByIp();
	isTutorLoginShowHide();
	loadSubjectUl('#tutors-subject-filter');
	loadGradeUl('#tutors-grade-filter');
	updateOrderFormSubjectGradeSel();
});
//更新页面中与城市相关的内容,currCity改变时调用
function updateInfoByCity(cityId, provId) {
	updateDistrUlByCity(cityId, '#tutors-distr-filter');
	updateTutorListByCondPage(1, 6);
	updateOrderFormLocationSelect(cityId, provId);
}

// tutors页面tutor列表中项的样式
/*<div class="row tutors-tutor-item">
	<div class="col-lg-2 no-padding-r tutor-photo">
		<a href="#">
	    	<img class="img-responsive img-rounded center-block" src="static/images/test.png" alt="photo">
		</a>
	</div>
	<div class="col-lg-10">
		<div class="row">
			<div class="col-lg-8">
				<p>
					<span class="tutor-name">崔教员</span>
					<span>
						&nbsp;&nbsp;&nbsp;&nbsp;男&nbsp;
						<span class="glyphicon glyphicon-user name-icon"></span>
						&nbsp;&nbsp;&nbsp;&nbsp;专业教师&nbsp;
						<span class="glyphicon glyphicon-check teacher-icon"></span>
						&nbsp;&nbsp;&nbsp;&nbsp;长安大学&nbsp;
						<span class="glyphicon glyphicon-education degree-icon"></span>
					</span>
				</p>
			</div>
			<div class="col-lg-4">
		    	<button class="btn btn-info btn-sm">意向</button>
		    	<button class="btn btn-info btn-sm">详情</button>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<p><strong>科目：</strong>数学 英语 数学 英语 数学 英语 数学 英语</p>
			</div>
			<div class="col-lg-6">
		    	<p><strong>地区：</strong>京口区 句容市 西湖区 京口区 句容市 西湖区 京口区 句容市 西湖区</p>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
		    	<p><strong>年级：</strong>三年级 五年级 六年级 三年级 五年级 六年级 三年级 五年级 六年级</p>
			</div>
			<div class="col-lg-6">
		    	<p><strong>方式：</strong>教员上门 学生上门</p>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
		    	<p><strong>自我描述：</strong>毕业于长安大学（211重点大学），本科是工科材料专业。数学和物理成绩一直非常优秀，教学生认真负责，有耐心。</p>
			</div>
		</div>
	</div>
</div>*/
// 给筛选条件的li绑定点击事件
$('#tutors-tutor-filters').on('click', 'li', function() {
	$(this).siblings('.active').removeClass('active');
	$(this).addClass('active');
	updateTutorListByCondPage(1, 6);
});

// 给tutor pager的按钮绑定事件
$('#tutors-page-first').click(function() {
	updateTutorListByCondPage(1, 6);
});
$('#tutors-page-previous').click(function() {
	var pageNum = parseInt($('#tutors-page-num').text());
	if(pageNum && pageNum > 1) {
		updateTutorListByCondPage(pageNum - 1, 6);
	}
});
$('#tutors-page-next').click(function() {
	var pageNum = parseInt($('#tutors-page-num').text());
	if(pageNum) {
		updateTutorListByCondPage(pageNum + 1, 6);
	}
});

//更新pager的显示内容
function updateTutorPagerInfo(pageNum, pageSize) {
	$('#tutors-page-num').text(pageNum);
	if(pageNum <= 1) {
		$('#tutors-tutor-pager li.first-page-hide').hide();
	} else {
		$('#tutors-tutor-pager li.first-page-hide').show();
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

// 给定页码，根据页面筛选条件，生成查询条件数据
function generateTutorConditionData(pageNum, pageSize) {
	var subjectId = $('#tutors-subject-filter li.active').attr('subject-id');
	var gradeId = $('#tutors-grade-filter li.active').attr('grade-id');
	var distrId = $('#tutors-distr-filter li.active').attr('distr-id');
	var modeId = $('#tutors-mode-filter li.active').attr('mode-id');
	var tutorType = $('#tutors-type-filter li.active').attr('tutor-type');
	var gender = $('#tutors-gender-filter li.active').attr('gender');
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

function updateTutorListByCondPage(pageNum, pageSize) {
	var conditionData = generateTutorConditionData(pageNum, pageSize);
	updateTutorListByCondition(conditionData);
	updateTutorPagerInfo(pageNum, pageSize);
}

function updateTutorListByCondition(conditionData) {
	$.ajax({
		url: '/as/tutor/getTutorsByCondition',
		type: 'POST',
		data: conditionData,
		success: function(result) {
			if(result.code == 1) {
				var tutorList = result.extend.tutorList;
				$('#tutors-list-wrap').empty();
				$.each(tutorList, function(tutorIndex, tutorItem) {
					var nameStr = tutorItem.realName.substring(0, 1) + '教员';
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
					var tutorId = tutorItem.id;
					$('#tutors-list-wrap').append(
						$('<div></div>').addClass('row tutors-tutor-item').append(
							$('<div></div>').addClass('col-lg-2 col-md-2 col-sm-2 col-xs-2 no-padding-r tutor-photo').append(
								//$('<a></a>').attr('href', '#').append(
									$('<img/>').attr({
										'class': 'img-responsive img-rounded center-block tutor-detail',
										src: photoUrl,
										alt: 'photo',
										tutorId: tutorId
									})
								//)
							)
						).append(
							$('<div></div>').addClass('col-lg-10 col-md-10 col-sm-10 col-xs-10').append(
								$('<div></div>').addClass('row').append(
									$('<div></div>').addClass('col-lg-8 col-md-8 col-sm-8 col-xs-8 no-padding-r').append(
										$('<p></p>').append(
											$('<span></span>').addClass('tutor-name').text(nameStr)
										).append(
											$('<span></span>').append(
												'&nbsp;&nbsp;&nbsp;&nbsp;' + genderName + '&nbsp;'
											).append(
												$('<span></span>').addClass('glyphicon glyphicon-user name-icon')
											).append(
												'&nbsp;&nbsp;&nbsp;&nbsp;' + tutorTypeName + '&nbsp;'
											).append(
												$('<span></span>').addClass('glyphicon glyphicon-check teacher-icon')
											).append(
												'&nbsp;&nbsp;&nbsp;&nbsp;' + tutorItem.university + '&nbsp;'
											).append(
												$('<span></span>').addClass('glyphicon glyphicon-education degree-icon')
											)
										)
									)
								).append(
									$('<div></div>').addClass('col-lg-4 col-md-4 col-sm-4 col-xs-4 no-padding-lr').append(
										$('<button></button>').addClass('btn btn-info btn-sm tutor-detail').attr('tutorId', tutorId).text('意向')
										//  添加一个空格，按钮之间的间距
									).append(' ').append(
										$('<button></button>').addClass('btn btn-info btn-sm tutor-detail').attr('tutorId', tutorId).text('详情')
									)
								)
							).append(
								$('<div></div>').addClass('row').append(
									$('<div></div>').addClass('col-lg-6 col-md-6 col-sm-6 col-xs-6').append(
										$('<p></p>').append(
											$('<strong><strong>').text('科目：')
										).append(
											subjectStr
										)
									)
								).append(
									$('<div></div>').addClass('col-lg-6 col-md-6 col-sm-6 col-xs-6').append(
										$('<p></p>').append(
											$('<strong><strong>').text('地区：')
										).append(
											distrStr
										)
									)
								)
							).append(
								$('<div></div>').addClass('row').append(
									$('<div></div>').addClass('col-lg-6 col-md-6 col-sm-6 col-xs-6').append(
										$('<p></p>').append(
											$('<strong><strong>').text('年级：')
										).append(
											gradeStr
										)
									)
								).append(
									$('<div></div>').addClass('col-lg-6 col-md-6 col-sm-6 col-xs-6').append(
										$('<p></p>').append(
											$('<strong><strong>').text('方式：')
										).append(
											modeStr
										)
									)
								)
							).append(
								$('<div></div>').addClass('row').append(
									$('<div></div>').addClass('col-lg-12 col-md-12 col-sm-12 col-xs-12').append(
										$('<p></p>').append(
											$('<strong><strong>').text('自我描述：')
										).append(
											tutorItem.intro
										)
									)
								)
							)
						)
					);

				});
			}
		}
	});
}




