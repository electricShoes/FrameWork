<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%>


<div class="card card-primary card-outline">
	<div class="card-header">
		<h3 class="card-title">메일보내기</h3>
		<div class="card-tools">
			<div class="input-group input-group-sm">
				<div class="btn-group">
					<button type="button" class="btn btn-primary btn-sm" data-click="sendMail">
						<i class="far fa-envelope"></i> 보내기
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- /.card-header -->
	<div class="card-body">
		<div class="form-group">
			<input class="form-control" id="emailTo" name="emailTo"
				placeholder="To:">
		</div>
		<div class="form-group">
			<input class="form-control" id="subject" name="subject"
				placeholder="Subject:">
		</div>
		<div class="form-group">
			<textarea  id="bodyContent" name="bodyContent"
				class="form-control" style="height: 300px"></textarea>
		</div>
		<div class="form-group">
			<div class="btn btn-default " data-click="addAttach">
				<i class="fas fa-paperclip"></i> 첨부파일
				<!--                     <input type="file" name="attachment"> -->
			</div>
			<p style="height: 10px"></p>
			<div class="row" id="attatchment" style="min-height: 70px;"></div>
		</div>
	</div>
	<!-- /.card-body -->
	<div class="card-footer">
		<div class="float-right">
			<!--                   <button type="button" class="btn btn-default"><i class="fas fa-pencil-alt"></i> Draft</button> -->
			<button type="button" class="btn btn-primary btn-sm" data-click="sendMail">
				<i class="far fa-envelope"></i> 보내기
			</button>
		</div>
		<!--                 <button type="reset" class="btn btn-default"><i class="fas fa-times"></i> Discard</button> -->
	</div>
	<!-- /.card-footer -->
</div>

<div id="attTemplate" style="display:none">
				<div class="col-2" >
				<form name="frmAtt" >
					<input type="hidden" id="fileName" name="fileName" />
					<input type="hidden" id="saveName" name="saveName" />
					<input type="hidden" id="fileSize" name="fileSize" />
					<input type="hidden" id="mimeType" name="mimeType" />
					<input type="hidden" id="id" name="id" />
					<input type="hidden" id="fileExt" name="fileExt" />
					<input type="hidden" id="uploaded" name="uploaded" />
				</form>
					<div class="card card-outline card-primary">
						<div class="card-header">
							<h3 class="card-title" ></h3>

							<div class="card-tools">
								<button type="button" class="btn btn-tool" >
									<i class="fas fa-times"></i>
								</button>
							</div>
						</div>
						<div class="card-body p-0">
							<div class="progress">
								<div class="progress-bar bg-primary  progress-bar-striped"
									role="progressbar" aria-valuenow="0" aria-valuemin="0"
									aria-valuemax="100" style="width: 0%">
									<span class="sr-only"></span>
								</div>
							</div>
						</div>
					</div>
				</div>
</div>

<script>
var fileUploader = Utilities.getFileUploader({
	addCallback : addFormFile
});
function addAttach(){
	fileUploader.addFile();
}
function addFormFile(evt, data) {
	var html = $("#attTemplate").html();
	var att = $(html);
	var frm = att.find("form");
	frm.attr("attachment","file");
	var file = data.file;
	var fileExt = Utilities.getFileExt(file.name);
	
	var fileInfo = {
		fileName : file.name,
		fileSize : file.size,
		mimeType : file.type,
		saveName : null,
		id : data.id,
		fileExt : fileExt
	};
	att.find("h3").html(file.name);
	Utilities.mapToForm(fileInfo,frm);
	$("#attatchment").append(att);
	att.find(".btn-tool").click(function(){
		fileUploader.removeFile(data.id);
		att.remove();
	});
	var url = "<c:url value='/${currentMenuId}/file/uploadTempFile'/>${urlSuffix}";
	fileUploader.upload(data.id, url, data, function(id, resultData, result){
		att.find("#uploaded").val("uploaded");
		att.find(".card-body").hide();
		att.find("#saveName").val(resultData.saveName);
	}, function(id, loaded, total, percent){
		var width = parseInt(percent*100,10);
		att.find("[role=progressbar]").css("width",width+"%");
	});
	
}
function sendMail(){
	var param = {
			emailTo : $("#emailTo").val(),
			subject : $("#subject").val(),
			body : $("#bodyContent").val()
	};
	if(!param.emailTo){
		alert("받는 사람을 입력하세요");
		return;
	}
	if(!param.subject){
		alert("제목을 입력하세요");
		return;
	}
	if(!param.body){
		alert("내용을 입력하세요");
		return;
	}
	var attList = [];
	$("form[attachment=file]").each(function(){
		var map = Utilities.formToMap($(this));
		attList.push(map);
	});
	
	param.attachment = attList;
	var url = "<c:url value='/${currentMenuId}/mail/sendMail'/>${urlSuffix}";
	$.blockUI();
	Utilities.getAjax(url, param, true, function(data, jqXHR) {
		if (Utilities.processResult(data, jqXHR, "메일전송에 실패했습니다.")) {
			alert("메일전송  성공했습니다.");
			location.reload(true);
		}
		$.unblockUI();
	});

}
</script>