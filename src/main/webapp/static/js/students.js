/**
 * students页面的js文件
 */
$(function() {
	locateByIp();
	isTutorLoginShowHide();
	loadSubjectUl('#students-subject-filter');
	loadGradeUl('#students-grade-filter');
});
//更新页面中与城市相关的内容,currCity改变时调用
function updateInfoByCity(cityId, provId) {
	updateDistrUlByCity(cityId, '#students-distr-filter');
	updateStudentListByCondPage(1, 8);
}

//给筛选条件的li绑定点击事件
$('#students-student-filters').on('click', 'li', function() {
	$(this).siblings('.active').removeClass('active');
	$(this).addClass('active');
	updateStudentListByCondPage(1, 8);
});
// 给order pager的按钮绑定事件
$('#students-page-first').click(function() {
	updateStudentListByCondPage(1, 8);
});
$('#students-page-previous').click(function() {
	var pageNum = parseInt($('#students-page-num').text());
	if(pageNum && pageNum > 1) {
		updateStudentListByCondPage(pageNum - 1, 8);
	}
});
$('#students-page-next').click(function() {
	var pageNum = parseInt($('#students-page-num').text());
	if(pageNum) {
		updateStudentListByCondPage(pageNum + 1, 8);
	}
});

// 更新pager的显示内容
function updateOrderPagerInfo(pageNum, pageSize) {
	$('#students-page-num').text(pageNum);
	if(pageNum <= 1) {
		$('#students-student-pager li.first-page-hide').hide();
	} else {
		$('#students-student-pager li.first-page-hide').show();
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
				$('#students-student-pager li.last-page-hide').show();
			} else {
				// 下一页没有内容
				$('#students-student-pager li.last-page-hide').hide();
			}
		},
		error: function() {
			$('#students-student-pager li.last-page-hide').hide();
		}
	});
}

// 给定页码，根据页面筛选条件，生成查询条件数据
function generateOrderConditionData(pageNum, pageSize) {
	var subjectId = $('#students-subject-filter li.active').attr('subject-id');
	var gradeId = $('#students-grade-filter li.active').attr('grade-id');
	var distrId = $('#students-distr-filter li.active').attr('distr-id');
	var cityId = $('#current-city').attr('city-id');
	var start = (pageNum - 1) * pageSize;
	var num = pageSize;
	////////////////////暂时不设置cityId参数///////////////////////////////////////////////////////////
	var conditionData = {
		subjectId: subjectId,
		gradeId: gradeId,
		distrId: distrId,
		cityId: cityId,
		start: start,
		num: num
	};
	return conditionData;
}
// 根据条件更新页面中的order列表
function updateStudentListByCondPage(pageNum, pageSize) {
	var conditionData = generateOrderConditionData(pageNum, pageSize);
	updateStudentListByCondition(conditionData);
	updateOrderPagerInfo(pageNum, pageSize);
}

// students页面order表格的格式
/*<tr>
	<td class="code-col">1505799037882</td>
	<td class="sub-col">数学</td>
	<td class="grade-col">五年级</td>
	<td class="loc-col">京口区 北海路100号附近北海路100号附近北海路100号附近北海路100号附近</td>
	<td class="message-col">小孩子上五年级，数学基础比较薄弱，希望找个有耐心的老师来辅导作业。辅导时间是周一到周五每天晚上6点到8点，课时费每小时60元。要求辅导老师是大三及以上，讲解比较清晰。</td>
	<td class="price-col">60/小时</td>
	<td class="time-col">2017-09-19 10:11:29</td>
	<td class="status-col">申请订单</td>
</tr>*/
function updateStudentListByCondition(conditionData) {
	$.ajax({
		url: '/as/order/getOrdersByCondition',
		type: 'POST',
		data: conditionData,
		success: function(result) {
			if(result.code == 1) {
				var orderList = result.extend.orderList;
				$('#orders-table-body').empty();
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
					var operation;
					if(orderItem.status == 2) {
						operation = $('<button></button>').addClass('btn btn-default btn-sm').attr('disabled', 'disabled').text('已有教员');
					} else {
						operation = $('<button></button>').addClass('btn btn-info btn-sm apply-order').attr('order-id', orderItem.id).text('申请订单');
					}
					$('#orders-table-body').append(
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

// 申请订单按钮
$('#orders-table-body').on('click', '.apply-order', function() {
	var orderId = $(this).attr('order-id');
	$.ajax({
		url: '/as/tutor/applyOrder',
		type: 'POST',
		data: {orderId: orderId},
		success: function(result) {
			$('#common-hint-modal-body').text(result.extend.message);
			$('#common-hint-modal').modal();
		},
		error: function() {
			$('#common-hint-modal-body').text('申请订单失败，请检查网络后重试。');
			$('#common-hint-modal').modal();
		}
	});
});




