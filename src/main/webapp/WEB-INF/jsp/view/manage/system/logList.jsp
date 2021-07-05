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
                        <label for="strDt" class="col-1 col-form-label right">시작일</label>
                        <div class="col-2">
                          <input type="text" id="strDt" data-enter="search" data-type="date" data-button="Y" name="strDt" class="form-control" placeholder="시작일" />
                        </div>
                        
                        <label for="endDt" class="col-1 col-form-label right">종료일</label>
                        <div class="col-2">
                          <input type="text" id="endDt" data-enter="search" data-type="date" data-button="Y" name="endDt" class="form-control" placeholder="종료일" />
                        </div>
                        
                        
                    </div>
                </div>
                
                <div class="col-2">
                        <button type="button" id="btnSearch" data-click="searchLog" class="btn btn-primary float-right"><i class="fas fa-search"></i> <spring:message code="button.search" />
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
                <h3 class="card-title">로그</h3>

                <div class="card-tools">
                    <div class="input-group input-group-sm">
                     <div class="btn-group"> 
                        <button type="button" data-grid-id="grdList" onclick="excelDownload(this.id)" id="btnExcel" class="btn btn-default btn-sm"><i class="fas fa-download"></i> 엑셀다운로드</button> 
                        <button type="button" data-grid-id="grdList" data-click="removeLog" id="btnDelList" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> 삭제</button>
                     </div>
                    </div>
                </div>
                <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGrid"   data-grid-id="grdList" data-pagenation="Y" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/system/sqlLog.xml'/>">
                </div>
            </div>

        </div>
        <!-- /.card -->
    </div>
    <!-- /.col -->

</div>
<!-- /.row -->


<div class="modal fade" id="sqlModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Sql view</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>
                    <textarea class="form-control" id="printSql" style="margin-top: 0px; margin-bottom: 0px; height: 500px;"></textarea>
                </p>
            </div>
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-click="copyClip" id="copyClip">Copy</button>
<!--                 <button type="button" class="btn btn-primary" id="saveFile">Save</button> -->
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>


<script>

/************************************
 * 엑셀 다운로드
 ************************************/
function excelDownload(id){
    grdList.exportExcel("로그관리.xlsx");
}

/************************************
 * 클립 복사
 ************************************/
function copyClip(){
    $("#printSql").select();
    document.execCommand('copy');    
}

/************************************
 * 그리드 cell 더블 클릭 이벤트
 ************************************/
function grdList_cellDblClick(gridView, row, col,rowData,value){
    if(col == "logSql"){
        $("#printSql").val(value);
        $("#sqlModal").modal('show');        
    }
}

/************************************
 * 로그 조회
 ************************************/
function searchLog(){
	$("form#frmSearch").find("#currentPageNo").val(1);
    search();
}

/************************************
 * 조회
 ************************************/
function search()
{
    var url = "<c:url value='/${currentMenuId}/getList${urlSuffix}'/>";
    var param = Utilities.formToMap("frmSearch");
    grdList.loadUrl(url,param);
}

/************************************
 * 페이지 이동 함수
 ************************************/
function grdList_pageMove(gridView,pageNo){
    $("form#frmSearch").find("#currentPageNo").val(pageNo);
    search();
}

/************************************
 * 로그 행 추가
 ************************************/
function addLog(data){
    if(!data)
        return;
    data.stat = "n";
    grdList.addRow(data);
}

/************************************
 * 로그 삭제
 ************************************/
function removeLog(){
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
        var url = "<c:url value='/${currentMenuId}/removeLogList${urlSuffix}'/>";
        Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
            if(Utilities.processResult(data,jqXHR,"로그 삭제에 실패했습니다."))
            {
                alert("로그 삭제에  성공했습니다.");
                grdList.removeCheckRow();
            }
        });
    }
    else{
        alert("선택된 데이터가 없습니다.");
    }
}



</script>