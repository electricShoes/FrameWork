<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%>
<div class="row">

	<div class="col-12">
		<div class="card card-primary card-outline">
			<div class="card-header">
				<h3 class="card-title">첨부파일</h3>

				<div class="card-tools">
					<div class="input-group input-group-sm">
						<div class="btn-group">
							<button type="button" data-grid-id="grdList" data-click="addFile" id="btnAdd" class="btn btn-default btn-sm">
								<i class="fas fa-plus"></i> 파일추가
							</button>
							<button type="button" data-grid-id="grdList" data-click="uploadFile" id="btnUpload" class="btn btn-success btn-sm">
								<i class="fas fa-upload"></i> upload
							</button>
							<button type="button" data-grid-id="grdList" data-click="downloadFile" id="btnDownload" class="btn btn-info btn-sm">
								<i class="fas fa-download"></i> 다운로드
							</button>
							<button type="button" data-grid-id="grdList" data-click="removeFile" id="btnDelList" data-action="del" class="btn btn-danger btn-sm">
								<i class="fas fa-trash"></i> 삭제
							</button>
						</div>
					</div>
				</div>
				<!-- /.card-tools -->
			</div>
			<!-- /.card-header -->
			<div class="card-body p-0">
				<div id="divGrid" style="height: 420px" data-grid-id="grdList" data-pagenation="N" data-type="grid" data-tpl-url="<c:url value='/gridTemplate/file/filePopup.xml'/>"></div>
			</div>
			<div class="card-footer">
				<a id="btnCancel" href="#;" data-click="close" class="btn btn-default float-right"><i class="fas fa-ban"></i> 닫기</a>
			</div>
		</div>
		<!-- /.card -->
	</div>
	<!-- /.col -->

</div>

<!-- /.row -->
<script>
	var fileUploader = null;
	function addFile() {
		fileUploader.addFile();
	}
	function uploadFile() {

		var url = "<c:url value='/${currentMenuId}/file/uploadFile'/>${urlSuffix}";
		var saveJson = grdList.getCheckedJson();
		if (saveJson.length == 0) {
			alert("체크된 데이터가 존재하지 않습니다.");
			return;
		}
		var cList = [];
		for (var i = 0; i < saveJson.length; i++) {
			if (saveJson[i].stat == "c") {
				cList.push(saveJson[i]);
				fileUploader.upload(saveJson[i].id, url, saveJson[i], onUploadComplete, onUploadProgress);
				continue;
			}

		}
		if (cList.length) {
		} else {
			alert("추가된 파일이 없습니다.");
		}
	}

	function downloadFile() {
		var saveJson = grdList.getCheckedJson();
		if (saveJson.length == 0) {
			alert("체크된 데이터가 존재하지 않습니다.");
			return;
		}
		var list = [];
        var cList = [];
        for (var i = 0; i < saveJson.length; i++) {
            if (saveJson[i].stat == "c") {
                cList.push(saveJson[i]);
                continue;
            }
            list.push(saveJson[i]);
        }
        if (list.length) {
            for(var i=0;i<list.length;i++){
            	var file = list[i];
            	onDownloadfile(file.fileId,file.fileNo,file.fileSize);
            }
        } else {
        	alert("다운로드할 파일이 존재하지 않습니다.");  
        }
		
	}
	function onDownloadfile(fileId, fileNo, fileSize) {
		var url = "<c:url value='/${currentMenuId}/file/downloadFile'/>${urlSuffix}";
		var fnDownProg = function(loaded, total, percentComplete) {
			if (loaded && !total)
				total = fileSize;
			if (total && !percentComplete)
				percentComplete = loaded / total;
			onDownloadProgress(fileId, fileNo, loaded, total, percentComplete);
		};
		var fnDownComp = function(returnValue, jqXHR) {
			onDownloadComplete(fileId, fileNo, fileSize, returnValue, jqXHR);
		};
		var list = grdList.getJsonRows();
		for (var i = 0; i < list.length; i++) {
			if (fileId == list[i].fileId && fileNo == list[i].fileNo) {
				var json = grdList.getJsonRow(i);
				Utilities.ajaxDownload(url, json, true, fnDownProg, fnDownComp);

				break;
			}
		}
	}
	function removeFile() {
		var saveJson = grdList.getCheckedJson();
		if (saveJson.length == 0) {
			alert("체크된 데이터가 존재하지 않습니다.");
			return;
		}
		if (saveJson.length) {
			if (!confirm("데이터를 삭제하면 복구할  수 없습니다. 계속하시겠습니까?"))
				return;
			var list = [];
			var cList = [];
			for (var i = 0; i < saveJson.length; i++) {
				if (saveJson[i].stat == "c") {
					cList.push(saveJson[i]);
					continue;
				}
				list.push(saveJson[i]);
			}
			if (list.length) {
				var url = "<c:url value='/${currentMenuId}/file/removeFileList${urlSuffix}'/>";
				Utilities.getAjax(url, saveJson, true, function(data, jqXHR) {
					if (Utilities.processResult(data, jqXHR, "파일 삭제에 실패했습니다.")) {
						alert("파일 삭제에  성공했습니다.");
						grdList.removeCheckRow();
						removeFileInfo(cList);
					}
				});
			} else {
				grdList.removeCheckRow();
				removeFileInfo(cList);
			}

		} else {
			alert("추가된 파일이 없습니다.");
		}
	}
	function onUploadProgress(id, loaded, total, percent) {
		$("#uploadInfo_" + id).html( /*loaded +"/"+ total +*/parseInt(percent * 100, 10) + " %");
	}
	function onUploadComplete(id, resultData, result) {
		if (result) {
			$("#uploadInfo_" + id).html("전송완료");
			var list = grdList.getJsonRows();
			for (var i = 0; i < list.length; i++) {
				var no = list[i].id;
				if (id == no) {
					grdList.resetRowState(list[i].itemIndex);
					break;
				}
			}
		} else
			$("#uploadInfo_" + id).html("전송실패");
	}
	function onDownloadProgress(fileId, fileNo, loaded, total, percentComplete) {
		var hDiv = $("div[data-type=downloadInfo][data-file-id=" + fileId + "][data-file-no=" + fileNo + "]");
		var html = "전송준비중";
		if (loaded)
			html = parseInt(percentComplete * 100, 10) + " %";
		hDiv.html(html);
	}
	function onDownloadComplete(fileId, fileNo, fileSize, returnValue, jqXHR) {
		var hDiv = $("div[data-type=downloadInfo][data-file-id=" + fileId + "][data-file-no=" + fileNo + "]");
		var html = '<button type="button" onclick="onDownloadfile(\'' + fileId + '\',\'' + fileNo + '\',' + fileSize
				+ ')" id="btnDownload" class="btn btn-info btn-xs"><i class="fas fa-download"></i> 다운로드</button>';
		hDiv.html(html);
	}

	function search() {
		var url = "<c:url value='/${currentMenuId}/file/getList${urlSuffix}'/>";
		var param = {
			recordCountPerPage : 1000000,
			fileId : "<c:out value='${fileInfo.fileId}'/>"
		};
		if (!param.fileId) {
			alert("파일정보가 존재하지 않습니다.");
			return false;
		}
		grdList.loadUrl(url, param);
	}
	function removeFileInfo(list) {
		for (var i = 0; i < list.length; i++) {
			fileUploader.removeFile(list[i].id);
		}
	}

	function grdList_load() {
		search();
	}
	function addFormFile(e, data) {
		var list = grdList.getJsonRows();
		var fileNo = 0;
		for (var i = 0; i < list.length; i++) {
			var no = list[i].fileNo;
			if (no > fileNo)
				fileNo = no;
		}
		fileNo++;
		var file = data.file;
		var fileExt = Utilities.getFileExt(file.name);
		var fileInfo = {
			fileId : "<c:out value='${fileInfo.fileId}'/>",
			fileNo : fileNo,
			fileName : file.name,
			fileSize : file.size,
			mimeType : file.type,
			saveName : null,
			id : data.id,
			fileExt : fileExt,
			stat : 'c'
		};
		grdList.addRow(fileInfo);
	}
	function getFileStatusString(gridView, row, col, data) {
		if (data.stat != 'c') {
			if (data.id) {
				return "전송완료";
			}
			// 			var html = "download";
			var html = '<div data-file-id="'+data.fileId+'" data-file-no="'+data.fileNo+'" data-type="downloadInfo"><button type="button" onclick="onDownloadfile(\'' + data.fileId + '\',\''
					+ data.fileNo + '\',' + data.fileSize + ')" id="btnDownload" class="btn btn-info btn-xs"><i class="fas fa-download"></i> 다운로드</button></div>';
			return html;
		} else {
			return '<div id="uploadInfo_'+ data.id +'">업로드 대기중</div>';
		}

	}
	$(document).ready(function() {
		fileUploader = Utilities.getFileUploader({
			addCallback : addFormFile
		});
		//maxFileCount, maxFileSize, maxTotalSize, multifiles,acceptFiles,url,addCallback
	});
</script>
