/**
 * 创建时间：2015年11月4日 文件名：LeaveRequest.js 描述： pengpeng.yuan@zymobi.com
 * saveForm?saveOrsubmit=1 保存并提交，开始走工作流
 * saveForm?saveOrsubmit=0 保存草稿箱，不走工作流
 */
$(document).ready(function(){
		var id = $("#id").val();
		var metaCustom_id = $("#metaid").val();
		if(id && metaCustom_id){
			//打开
			jQuery.ajax({
				url : ctx+'/settingMeta/findBusiByBusiKey/'+metaCustom_id+'/'+id,
				type : 'post',
				dataType : 'json',
				success : function(msg){
					var obj = eval(msg);
					if(obj.status == 'ok'){
						var val;
						for(var i=0;i<obj.result.length;i++){
							val=obj.result[i];
							if(val.type=="radios"){
								$("input[name='"+val.code+"'][value='"+val.value+"']").attr("checked",'checked');
							}else if(val.type=="checkboxs"){
								var vals=val.value.split(",");
								for(var j=0;j<vals.length;j++){
									$("input[name='"+val.code+"'][value='"+vals[j]+"']").attr("checked",true);
								}
							}else if(val.type=="textarea"){
								$("textarea[name='"+val.code+"']").text(val.value);
							}else{
								$("input[name='"+val.code+"']").val(val.value);
							}
						}
					}else{
						$.messager.alert('提示',obj.info,'info');
					}
				},
				error: function(msg){
					var obj = eval(msg);
					$.messager.alert('提示',obj.info,'info');
				}
			}) 
		}else{
			//新增
		}
		$('.st_head').removeClass('panel-body');
		$('.layout-panel-center').css('top','25px');
		$('.layout-panel-north').css('border','none');
	});

$(function() {
	$('#begin_date,#end_date').datetimepicker({
		format : 'yyyy-mm-dd hh:ii',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1
	}).on('changeDate', function(ev) {
		if ($('#begin_date').val() != "" && $('#end_date').val()) {
			var a = $('#begin_date').val();
			var b = $('#end_date').val();
			a = a.replace(/-/g, '/');
			b = b.replace(/-/g, '/');
			var time1 = new Date(a);
			var time2 = new Date(b);
			time1 = time1.getTime();
			time2 = time2.getTime();
			c = time2 - time1;
			hour = Math.round(c / (1000 * 3600));
			c = c / (1000 * 3600 * 24);
			d = Math.round(c);
			$('#countday').val(c);
			if (c > d) {
				var lefthour = hour - (d * 24);
			} else {
				var lefthour = hour - ((d - 1) * 24);
			}
			if (lefthour == 24)
				lefthour = 0;
			$('#countday').val(d);
			$('#counthour').val(lefthour);
		}
	});
})
function addSb() {
	jQuery.ajax({
		url : ctx+'/settingMeta/saveForm?saveOrsubmit=1',
		type : 'post',
		data : $('#submitForm').serialize(),
		dataType : 'json',
		success : function(msg) {
			var obj = eval(msg);
			if (obj.status == 'ok') {
				$.messager.alert('提示', obj.info, 'info');
			} else {
				$.messager.alert('提示', obj.info, 'info');
			}
		},
		error : function(msg) {
			var obj = eval(msg);
			$.messager.alert('提示', obj.info, 'info');
		}

	})
}
function addAd() {
	var day = $('#countday').val();
	var reason_info = $('#reason_info').val();
	var oper_type = $("input[type='radio']:checked").val();
	if (day < 0 || day == '') {
		$.messager.alert('提示', '输入的请假时间不符合规范,请重新选择','error');
		return;
	}
	if ( reason_info == '') {
		$.messager.alert('提示', '请输入请假事由','error');
		return;
	}
	if ( oper_type == '') {
		$.messager.alert('提示', '请选择请假类型','error');
		return;
	}
	$.ajax({
		url : ctx+'/settingMeta/saveForm?saveOrsubmit=0',
		type : 'post',
		data : $('#submitForm').serialize(),
		dataType : 'json',
		success : function(data) {
			var obj = eval('(' + data + ')');
			if (obj.status == 'ok') {
				$("#id").val(obj.result);
				$.messager.alert('提示', obj.info, 'info');
			} else {
				$.messager.alert('提示', obj.info, 'info');
			}
		},
		error : function(msg) {
			var obj = eval(msg);
			$.messager.alert('提示', obj.info, 'info');
		}

	});
}