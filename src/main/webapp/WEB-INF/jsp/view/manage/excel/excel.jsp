<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%> 
<!-- /.card -->
<!-- Horizontal Form -->
<%-- <div class="card">
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
                        <label for="userNm" class="col-1 col-form-label right">변수명</label>
                        <div class="col-2">
                          <input type="text" id="varCd" data-enter="search" name="varCd" class="form-control" placeholder="변수명" />
                        </div>
                        
                        <label for="value" class="col-1 col-form-label right">변수값</label>
                        <div class="col-2">
                          <input type="text" id="varVal" data-enter="search" name="varVal" class="form-control" placeholder="변수값" />
                        </div>
                        
                        
                    </div>
                </div>
                
                <div class="col-2">
                        <button type="button" id="btnSearch" data-click="searchUser" class="btn btn-primary float-right"><i class="fas fa-search"></i> <spring:message code="button.search" />
                  </button>
                </div>
               
            </div>
        </form>

        <!-- /.card-body -->
    </div>
</div> --%>
<!-- /.card -->


<div class="row">

    <div class="col-12">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">엑셀</h3>
                
                <div class="card-tools">
                    <div class="input-group input-group-sm">
                     <div class="btn-group"> 
                        <form id="excelUploadForm" name="excelUploadForm" enctype="multipart/form-data" method="post" action= "excel/excelUpload.do">
                            <input  type="file" id="excelFile" name="excelFile" />
                         </form>
                        <!-- <button type="button" data-grid-id="grdList" onclick="excelDownload(this.id)" id="btnExcel" class="btn btn-default btn-sm"><i class="fas fa-download"></i> 엑셀다운로드</button>
                        <button type="button" data-grid-id="grdList" data-click="addUser" id="btnAddList" class="btn btn-success btn-sm"><i class="fas fa-plus"></i>추가</button>
                        <button type="button" data-grid-id="grdList" data-click="saveUser" id="btnSaveList"  class="btn btn-info btn-sm"><i class="fas fa-save"></i> 저장</button>
                        <button type="button" data-grid-id="grdList" data-click="removeUser" id="btnDelList" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> 삭제</button> -->
                        <button type="button" data-grid-id="grdList" onclick="excelUpload()" id="btnUpload" class="btn btn-default btn-sm"><i class="fas fa-upload"></i> 엑셀업로드</button>
                        <button type="button" data-grid-id="grdList" onclick="excelDownLoad" id="btnDownload" class="btn btn-default btn-sm"><i class="fas fa-download"></i> 양식다운로드</button>
                        <button type="button" data-grid-id="grdList" data-click="saveExcel" id="btnSaveList"  class="btn btn-info btn-sm"><i class="fas fa-save"></i> 저장</button>
                     </div>
                    </div>
                </div>
                <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGrid"   data-grid-id="grdList" data-pagenation="N" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/excel/excelList.xml'/>">
                </div>
            </div>

        </div>
        <!-- /.card -->
    </div>
    <!-- /.col -->

</div>
<!-- /.row -->
<script>
function checkFileType(filePath) {
	var fileFormat = filePath.split(".");

    if (fileFormat.indexOf("xls") > -1 || fileFormat.indexOf("xlsx") > -1) {
    	return true;
    } else {
    	return false;
    }
}

function excelUpload(){
	console.log("excelUpload 시작 ==== ")
	var file = $("#excelFile").val();
	console.log("file :: ", file);
	
	if(file == "" || file == null){
		alert("파일을 선택해주세요.")
		return false;
		
	} else if(!checkFileType(file)){
		alert("엑셀파일만 업로드 가능합니다.");
		return false;
	}
	
	// 1. 엑셀 업로드 트랜잭션 시작.
	if (confirm("업로드 하시겠습니까?")) {
		var options = {
				success : function(data) {
					// alert("모든 데이터가 업로드 되었습니다.");
					console.log("data {}", data);
					grdList.loadJson(data);
				},
				type : "POST",
				complete : function(){
                    grdList.setCheckableExpression("values['expr'] <> '에러가 있어요'", true); // success 후에 콜백 개념 ajaxsubmit한 후 가장 마지막에 실행 - 그리드 checkbox 활성화/비활성화
                }
		};
		
		$("#excelUploadForm").ajaxSubmit(options); // 2. form에 선언된 action을 탄다. (excel/excelUpload.do)
	}
}

function excelDownload(){
	//grdList.exportExcel("엑셀다운로드.xlsx");
	// 서버에 올라간 엑셀 양식 다운
}
function saveExcel(){
    var saveJson = grdList.getSaveJson();
    if(saveJson.length){
        if(!confirm("수정된 데이터를 저장하시겠습니까?"))
            return ;
        var url = "<c:url value='/${currentMenuId}/save${urlSuffix}'/>";
        Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
            if(Utilities.processResult(data,jqXHR,"사용자 저장에 실패했습니다."))
            {
                alert("사용자 저장에  성공했습니다.");
                //gridView.resetAllRowStatus();
                search();
            }
        });
    }
    else{
        alert("수정된 데이터가 없습니다.");
    }
}

/* function addUser(data){
    if(!data)
        return;
    data.stat = "c";
    grdList.addRow(data);
    
}

function removeUser(){
	
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
        //data.stat = "d";
        var url = "<c:url value='/${currentMenuId}/delete${urlSuffix}'/>";
        Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
            if(Utilities.processResult(data,jqXHR,"사용자 삭제에 실패했습니다."))
            {
                alert("사용자 삭제에  성공했습니다.");
                //grdList.removeCheckRow();
                search();
            }
        });
    }
    else{
        alert("선택된 데이터가 없습니다.");
    }
} */

</script>