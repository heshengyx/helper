<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<form class="form-horizontal" role="form">
	<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="nameEdit">名称</label>
	<div class="col-sm-10">
<input type="text" id="nameEdit" placeholder="名称" class="col-xs-10 col-sm-10" />
</div>
</div>
<div class="form-group">
	<label class="col-sm-2 control-label no-padding-right" for="titleEdit">标题</label>
	<div class="col-sm-10">
<input type="text" id="titleEdit" placeholder="标题" class="col-xs-10 col-sm-10" />
</div>
</div>

	<!--
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="buildingNameEdit">楼盘名称</label>
		<div class="col-sm-10">
			<input type="text" id="buildingNameEdit" placeholder="楼盘名称" class="col-xs-10 col-sm-10" />
		</div>
	</div>-->
	
	<div class="form-group">
		<div class="col-md-offset-2 col-md-10">
			<button class="btn btn-sm btn-info" type="button" id="btn-update"><i class="icon-save bigger-110"></i>保 存</button>
		</div>
	</div>
</form>
<script type="text/javascript">	
$(document).ready(function() {
	var url = "${ctx}/manage/pad/getData?random="+ Math.random();
	var params = {
		id: "${param.id}"
	};
	$.post(url, params, function(result) {
		if ("500" == result.code) {
			dialog({
			    title: '消息',
			    width: 200,
			    content: result.message,
			    okValue: '确定',
			    ok: true,
			    cancel: false
			}).showModal();
		} else {
			$("#nameEdit").val(result.data.name);
$("#titleEdit").val(result.data.title);

		}
	}, "json");
	
	$("#btn-update").click(function() {
		url = "${ctx}/manage/pad/update?random="+ Math.random();
		params = {
			id: "${param.id}",
			name: $("#nameEdit").val(),
title: $("#titleEdit").val(),

			random: Math.random()
		};
		$.post(url, params, function(result) {
			dialog({
			    title: '消息',
			    width: 200,
			    content: result.message,
			    okValue: '确定',
			    ok: function () {
			    	if (result.code == "500") {
			    		return true;	
			    	} else {
			    		_myDialog.close().remove();
		                doSearch();	
			    	}
		    	},
			    cancel: false
			}).showModal();
		}, "json");
	});
});
</script>