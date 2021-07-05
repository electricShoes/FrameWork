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
                <div class="col-sm-10">
                    <div class="form-group row">
                        <label for="langGb" class="col-sm-1 col-form-label right">구분</label>
                        <div class="col-sm-2">
                         <!--  <input type="text" id="langGb" data-enter="search" name="langGb" class="form-control" placeholder="언어구분" /> -->
                            <code:commCode className="form-control" selectedValue = "Y" prefixValue="" prefixLabel="전체" id="gubnCd" name="gubnCd" mstCd ="LANG_GUBN"/>
                        </div>
                        
                        <label for="gubnCd" class="col-sm-1 col-form-label right">언어</label>
                        <div class="col-sm-2">
                         <!--  <input type="text" id="langGb" data-enter="search" name="langGb" class="form-control" placeholder="언어구분" /> -->
                             <code:commCode className="form-control" selectedValue = "Y" prefixValue="" prefixLabel="전체" id="langGb" name="langGb" mstCd ="LANG_CD"/>
                        </div>
                        
                        <label for="langCd" class="col-sm-1 col-form-label right">다국어코드</label>
                        <div class="col-sm-2">
                          <input type="text" id="langCd" data-enter="search" name="langCd" class="form-control" placeholder="다국어 코드" />
                        </div>                    
                    </div>
                </div>
                
                <div class="col-sm-2">
                        <button type="button" id="btnSearch" data-click="searchLang" class="btn btn-primary float-right"><i class="fas fa-search"></i>검색
                  </button>
                </div>
               
            </div>
        </form>

        <!-- /.card-body -->
    </div>
</div>
<!-- /.card -->


<div class="row">

    <div class="col-lg-12">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">다국어</h3>

                <div class="card-tools">
                    <div class="input-group input-group-sm">
                     <div class="btn-group">
                        <button type="button" data-grid-id="grdList" onclick="excelDownload(this.id)" id="btnExcel" class="btn btn-default btn-sm"><i class="fas fa-download"></i> 엑셀다운로드</button> 
                        <button type="button" data-grid-id="grdList" data-click="newLang" id="btnAddList" class="btn btn-success btn-sm"><i class="fas fa-plus"></i>추가</button>
                        <button type="button" data-grid-id="grdList" data-click="saveLang" id="btnSaveList"  class="btn btn-info btn-sm"><i class="fas fa-save"></i> 저장</button>
                        <button type="button" data-grid-id="grdList" data-click="removeLang" id="btnDelList" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> 삭제</button>
                     </div>
                    </div>
                </div>
                <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGrid"   data-grid-id="grdList" data-pagenation="Y" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/lang/langM.xml'/>">
                </div>
            </div>

        </div>
        <!-- /.card -->
    </div>
    <!-- /.col -->

</div>
<!-- /.row -->
<script>
function searchLang(){
	$("form#frmSearch").find("#currentPageNo").val(1);
    search();
}
function newLang(data){   
	    if(!data)
	        return;
	    data.stat = "c";
	    grdList.addRow(data);
}
function search()
{
    var url = "<c:url value='/${currentMenuId}/getList${urlSuffix}'/>";
    var param = Utilities.formToMap("frmSearch");
    grdList.loadUrl(url,param);
}
function grdList_pageMove(gridView,pageNo){
    $("form#frmSearch").find("#currentPageNo").val(pageNo);
    search();
}
function saveLang(){
    var saveJson = grdList.getSaveJson();
    if(saveJson.length){
        if(!confirm("수정된 데이터를 저장하시겠습니까?"))
            return ;
        var url = "<c:url value='/${currentMenuId}/save${urlSuffix}'/>";
        console.log("========================"+url);
        Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
            if(Utilities.processResult(data,jqXHR,"사용자 저장에 실패했습니다."))
            {
            	search();
                alert("사용자 저장에  성공했습니다.");
                gridView.resetAllRowStatus();
            }
        });
    }
    else{
        alert("수정된 데이터가 없습니다.");
    }
}

function removeLang(){
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
        var url = "<c:url value='/${currentMenuId}/removeLangList${urlSuffix}'/>";
        Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
            if(Utilities.processResult(data,jqXHR,"다국어 삭제에 실패했습니다."))
            {
                alert("다국어 삭제에  성공했습니다.");
                grdList.removeCheckRow();
            }
        });
    }
    else{
        alert("선택된 데이터가 없습니다.");
    }
}

function excelDownload(id){
    if(id == "btnExcel"){
        grdList.exportExcel("법인관리_공통코드.xlsx");
    }else if(id == "btnExcelDetail"){
        grdDetail.exportExcel("법인관리_하부코드.xlsx");
    }
}


</script>