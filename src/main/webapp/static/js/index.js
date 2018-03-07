/**
 * index.html页面的js文件
 */
// 页面加载后
$(function() {
	locateByIp();
	isTutorLoginShowHide();
	updateOrderFormSubjectGradeSel();
});

//更新页面中与城市相关的内容,currCity改变时调用
function updateInfoByCity(cityId, provId) {
	updateTutorListByCity(cityId);
	updateOrderFormLocationSelect(cityId, provId);
}

//宣传2设置文字垂直居中，谷歌浏览器加载图片的顺序问题，导致高度无法获取，采用$(window).load(function(){})
//资源加载完成，也要执行一次
$(window).on( 'load', function() {
$('#ad2-line1-text').css('margin-top', ($('#ad2-line1-img').height() - $('#ad2-line1-text').height()) / 2 + 'px');
});
$(window).on( 'load', function() {
$('#ad2-line2-text').css('margin-top', ($('#ad2-line2-img').height() - $('#ad2-line2-text').height()) / 2 + 'px');
});
$(window).resize(function () {
$('#ad2-line1-text').css('margin-top', ($('#ad2-line1-img').height() - $('#ad2-line1-text').height()) / 2 + 'px');
});
$(window).resize(function () {
$('#ad2-line2-text').css('margin-top', ($('#ad2-line2-img').height() - $('#ad2-line2-text').height()) / 2 + 'px');
});

/* 首页tutor的样式
<div class="col-lg-4 col-md-4 col-sm-4 no-padding-lr">
	<div class="row">
		<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 home-tutor-photo">
			<a href="#">
		    	<img class="img-responsive img-rounded" src="https://tutor-static.oss-cn-hangzhou.aliyuncs.com/static/images/photo200.png" alt="photo">
	  		</a>
	  		<p class="text-center"><strong>崔教员</strong></p>
		</div>
		<div class="col-lg-7 col-md-7 col-sm-7 col-xs-7 no-padding-lr">
			<p><strong>科目：</strong>数学 英语</p>
	    	<p><strong>年级：</strong>三年级 五年级 六年级</p>
	    	<p><strong>地区：</strong>京口区 句容市 西湖区</p>
	    	<div class="tutor-list-btns">
		    	<button class="btn btn-primary btn-sm customize-btn">意向</button>
		    	<button class="btn btn-primary btn-sm customize-btn">详情</button>
		    </div>
		</div>
	</div>
</div>*/

function updateTutorListByCity(cityId) {
	$.ajax({
		url: '/as/tutor/getTutorsByCondition',
		type: 'POST',
		////////////////////暂时不设置teachCityId参数///////////////////////////////////////////////////////////
		data: {teachCityId: cityId, start: 0, num: 12},
		success: function(result) {
			if(result.code == 1) {
				var tutorList = result.extend.tutorList;
				$('#home-tutor-list').empty();
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
					var photo = tutorItem.photo ? tutorItem.photo : 'default-photo.png';
					var tutorId = tutorItem.id;
					$('#home-tutor-list').append(
						$('<div></div>').addClass('col-lg-4 col-md-4 col-sm-6 col-xs-6 no-padding-lr').append(
							$('<div></div>').addClass('row').append(
								$('<div></div>').addClass('col-lg-5 col-md-5 col-sm-5 col-xs-5 home-tutor-photo').append(
									//$('<a></a>').attr('href', '#').append(
										$('<img/>').attr({
											'class': 'img-responsive img-rounded tutor-detail',
											src: 'https://tutor-public.oss-cn-hangzhou.aliyuncs.com/photo/' + photo,
											alt: 'photo',
											tutorId: tutorId
										})
									//)
								).append(
									$('<p></p>').attr('class', 'text-center').append(
										$('<strong></strong>').text(nameStr)
									)
								)
							).append(
								$('<div></div>').addClass('col-lg-7 col-md-7 col-sm-7 col-xs-7 no-padding-lr').append(
									$('<p></p>').append($('<strong></strong>').text('科目：')).append(subjectStr)
								).append(
									$('<p></p>').append($('<strong></strong>').text('年级：')).append(gradeStr)
								).append(
									$('<p></p>').append($('<strong></strong>').text('地区：')).append(distrStr)
								).append(
									$('<div></div>').addClass('tutor-list-btns').append(
										$('<button></button>').addClass('btn btn-primary btn-sm customize-btn tutor-detail').attr('tutorId', tutorId).text('意向')
										//  添加一个空格，按钮之间的间距
									).append(' ').append(
										$('<button></button>').addClass('btn btn-primary btn-sm customize-btn tutor-detail').attr('tutorId', tutorId).text('详情')
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





