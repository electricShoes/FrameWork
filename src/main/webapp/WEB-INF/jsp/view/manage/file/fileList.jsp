<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%> 
<!-- /.card -->
<!-- Horizontal Form -->
<div class="card">
    <!--               <div class="card-header"> -->
    <!--                 <h3 class="card-title">Horizontal Form</h3> -->
    <!--               </div> -->
    <!-- /.card-header -->
    <!-- form start -->
    <!-- /.card-header -->
    <div class="card-body">
        <form role="form" id="frmSearch">
          <input type="hidden" name="currentPageNo" id="currentPageNo" value="1" />
            <div class=" row">
                <div class="col-10">
                    <div class="form-group row">
                        <label for="fileName" class="col-1 col-form-label right">파일명</label>
                        <div class="col-2">
                          <input type="text" id="FileName" data-enter="search" name="fileName" class="form-control" placeholder="파일명" />
                        </div>
                        
                         <label for="fileId" class="col-1 col-form-label right">파일ID</label>
                        <div class="col-2">
                          <input type="text" id="fileId" data-enter="search" name="fileId" class="form-control" placeholder="파일아이디" />
                        </div>
                    </div>
                </div>
                <div class="col-2">
                        <button type="button" id="btnSearch" data-click="searchFile" class="btn btn-primary float-right"><i class="fas fa-search"></i>검색
                  </button>
                </div>
               
            </div>
        </form>

        <!-- /.card-body -->
    </div>
</div>
<!-- /.card -->


<div class="row">

    <div class="col-12">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">첨부파일</h3>

                <div class="card-tools">
                    <div class="input-group input-group-sm">
                     <div class="btn-group"> 
                        <button type="button" data-grid-id="grdList" data-click="uploadFile" id="btnUpload"  class="btn btn-success btn-sm"><i class="fas fa-upload"></i> upload</button>
                        <button type="button" data-grid-id="grdList" onclick="excelDownload(this.id)" id="btnExcel" class="btn btn-default btn-sm"><i class="fas fa-download"></i> 엑셀다운로드</button>
                        <button type="button" data-grid-id="grdList" data-click="downloadFile" id="btnDownload" class="btn btn-info btn-sm"><i class="fas fa-save"></i> 다운로드</button>
                        <button type="button" data-grid-id="grdList" data-click="removeFile" id="btnDelList" data-action="del" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> 삭제</button>
                     </div>
                    </div>
                </div>
                <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGrid"   data-grid-id="grdList" data-pagenation="Y" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/file/fileInfo.xml'/>">
                </div>
            </div>

        </div>
        <!-- /.card -->
    </div>
    <!-- /.col -->

</div>
<!-- /.row -->
<script>
function excelDownload(id){
	grdList.exportExcel("첨부파일관리.xlsx");
}
function searchFile(){
	$("form#frmSearch").find("#currentPageNo").val(1);
    search();
}
function uploadFile(){
	Utilities.openFilePopup(true);
	var url = "<c:url value='/${currentMenuId}/fileInfo${urlSuffix}'/>";
    Utilities.windowOpen(url,"fileInfo",700,550);
}
function downloadFile(){
	var list = grdList.getCheckedJson();
    if (list.length == 0) {
        alert("체크된 데이터가 존재하지 않습니다.");
        return;
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
//     var fnDownProg = function(loaded, total, percentComplete) {
//         if (loaded && !total)
//             total = fileSize;
//         if (total && !percentComplete)
//             percentComplete = loaded / total;
//         onDownloadProgress(fileId, fileNo, loaded, total, percentComplete);
//     };
//     var fnDownComp = function(returnValue, jqXHR) {
//         onDownloadComplete(fileId, fileNo, fileSize, returnValue, jqXHR);
//     };
    var list = grdList.getJsonRows();
    for (var i = 0; i < list.length; i++) {
        if (fileId == list[i].fileId && fileNo == list[i].fileNo) {
            var json = grdList.getJsonRow(i);
            Utilities.ajaxDownload(url, json, true);

            break;
        }
    }
}
function grdList_btnFileinfo_buttonClicked(gridView,row,col,json){
	var url = "<c:url value='/${currentMenuId}/fileInfo${urlSuffix}'/>?fileId=" + json.fileId;
    Utilities.windowOpen(url,"fileInfo",700,550);
}

function search()
{
    var url = "<c:url value='/${currentMenuId}/getList${urlSuffix}'/>";
    var param = Utilities.formToMap("frmSearch");
    console.log(">>>>>>>>>>>>>>>");
    grdList.loadUrl(url,param);
}
function grdList_pageMove(gridView,pageNo){
    $("form#frmSearch").find("#currentPageNo").val(pageNo);
    search();
}

function removeFile(){
    var list = grdList.getCheckedJson();
    if(list.length ==0)
    {
        alert("체크된 데이터가 존재하지 않습니다.");
        return;
    }
    var saveJson = grdList.getCheckedJson();
    if(saveJson.length){
        if(!confirm("데이터를 삭제하면 복구할  수 없습니다. 계속하시겠습니까?"))
            return ;
        var url = "<c:url value='/${currentMenuId}/removeFileList${urlSuffix}'/>";
        Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
            if(Utilities.processResult(data,jqXHR,"파일 삭제에 실패했습니다."))
            {
                alert("파일 삭제에  성공했습니다.");
                grdList.removeCheckRow();
            }
        });
    }
    else{
        alert("선택된 데이터가 없습니다.");
    }
}

function grdList_btnFileinfo__ButtonClicked(gridView,row,col,json){
    
}


</script>