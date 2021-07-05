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
		   <div class="searh_group ">     
		   	<!-- /.상단조회버튼추가예시-->	
					 <div class="btn-group"> 
						<button type="button" data-grid-id="grdList" id="btnDelList"  data-click="delCodeM" class="btn  btn-sm"><i class="fas fa-trash"></i> 추가</button>
                        <button type="button" data-grid-id="grdList" id="btnLangList"  data-click="lang" class="btn btn-sm"><i class="fas fa-trash"></i> 저장</button>
					 </div>
			<!-- /.상단조회버튼추가예시 end-->	
					 <button type="button" id="btnSearch" class="btn btn-primary btn-sm"><i class="fas fa-search"></i>검색</button>
					</div>
	
	<div class="card-body search_wrap">
		<form role="form" id="frmSearch">
		  <input type="hidden" name="currentPageNo" id="currentPageNo" value="1" />
		  <input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="10" />
			<div class=" row">
				<div class="col-10">
				    <div class="form-group row">
					    <label for="codeLevel" class="col-1 col-form-label right">분류</label>
	                    <div class="col-2">
	                      <code:commCode className="form-control" selectedValue = "Y" prefixValue="" prefixLabel="전체" id="useYn" name="useYn" mstCd ="USE_YN"/>
	                    </div>
					    
                        <label for="mstCd" class="col-1 col-form-label right">코드ID</label>
                        <div class="col-2">
                          <input type="text" id="mstCd" name="mstCd" data-enter="search"  class="form-control" placeholder="코드아이디" />
                        </div>
                        
					    <label for="mstNm" class="col-1 col-form-label right">코드명</label>
	                    <div class="col-2">
	                      <input type="text" id="mstNm" name="mstNm" data-enter="search" class="form-control" placeholder="코드명" />
	                    </div>
	                    
					</div>
				</div>
				
             <!--   <div class="col-2">
                        <button type="button" id="btnSearch" class="btn btn-primary float-right"><i class="fas fa-search"></i>검색
                  </button>
                </div>-->
               
			</div>
		</form>

		<!-- /.card-body -->
	</div>
</div>
<!-- /.card -->


<div class="row">
	<div class="col-12">
		<div class="card  ">
			<div class="card-header">
				<h3 class="card-title">공통코드</h3>

				<div class="card-tools">
					<div class="input-group input-group-sm">
					 <div class="btn-group"> 
                        <button type="button" data-grid-id="grdList" onclick="excelDownload(this.id)" id="btnExcel" class="btn btn-default btn-sm"><i class="fas fa-download"></i> 엑셀다운로드</button>
						<button type="button" data-grid-id="grdList" id="btnAddList"  data-click="addCodeM" class="btn btn-success btn-sm"><i class="fas fa-plus"></i>추가</button>
						<button type="button" data-grid-id="grdList" id="btnSaveList" data-click="saveCodeM" class="btn btn-info btn-sm"><i class="fas fa-save"></i> 저장</button>
						<button type="button" data-grid-id="grdList" id="btnDelList"  data-click="delCodeM" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> 삭제</button>
                        <button type="button" data-grid-id="grdList" id="btnLangList"  data-click="lang" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> 언어</button>
					 </div>
					</div>
				</div>
				<!-- /.card-tools -->
			</div>
			<!-- /.card-header -->
			<div class="card-body p-0">
			 
				<div id="divGrid" style="height:275px"  data-grid-id="grdList" data-pagenation="Y" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/code/codeList1.xml'/>">
				</div>
			
			</div>

		</div>
		<!-- /.card -->
	</div>
	<!-- /.col -->

	<div class="col-12">
		<div class="card card-primary card-outline">
			<div class="card-header">
				<h3 class="card-title">하부코드</h3>
				<div class="card-tools">
                    <div class="input-group input-group-sm">
	                    <div class="btn-group"> 
	                        <button type="button" data-grid-id="grdDetail" onclick="excelDownload(this.id)" id="btnExcelDetail" class="btn btn-default btn-sm"><i class="fas fa-download"></i> 엑셀다운로드</button>
                            <button type="button" id="btnAddDetail"   data-grid-id="grdDetail" data-click="addCodeD" class="btn btn-success btn-sm"><i class="fas fa-plus"></i> 추가</button>
	                        <button type="button" id="btnSaveDetail"  data-grid-id="grdDetail" data-click="saveCodeD" class="btn btn-info btn-sm"><i class="fas fa-save"></i> 저장</button>
	                        <button type="button" id="btnDelDetail"   data-grid-id="grdDetail" data-click="delCodeD" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> 삭제</button>
	                    </div>
                    </div>
                </div>
			</div>
			<!-- /.card-header -->
			<div class="card-body p-0">
			<!-- 
                <div id="divGridDetail" style="height:250px"  data-grid-id="grdDetail" data-pagenation="N" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/code/codeList2.xml'/>">
                </div>
 			-->
				<!-- /.mail-box-messages -->
			</div>

		</div>
		<!-- /.card -->
	</div>
	<!-- /.col -->



</div>
<!-- /.row -->
<script>

var head1 = "";
var head2 = "";
var head3 = "";
var head4 = "";
var head5 = "";

function excelDownload(id){
	
	if(id == "btnExcel"){
		grdList.exportExcel("코드관리_공통코드.xlsx");
	}else if(id == "btnExcelDetail"){
		grdDetail.exportExcel("코드관리_하부코드.xlsx");
	}
}
function excelUpload(){
    grdList.importExcel();
}
function grdDetail_load(gridView,gridId){

}
function grdList_load(gridView,gridId){
// 	search();
}
function grdList_rowChanged(grdList,oldRow,newRow,rowData){
	/*
	if(newRow > -1){
		grdDetail.commit();
		var saveJson = grdDetail.getSaveJson();
		if(saveJson.length){
			if(!confirm("변경된 데이터가 존재합니다. 먼저 저장하시겠습니까?"))
	        {
				grdDetail.cancel();
	        }
			else
				saveDetail();
		} 
		var param = {
			mstCd : rowData.mstCd,
			recordCountPerPage : 100000
		};
				
		//grdDetail.setColumnProperty("refCd1","header","참조필드1");
        //grdDetail.setColumnProperty("refCd2","header","참조필드2");
        //grdDetail.setColumnProperty("refCd3","header","참조필드3");
        //grdDetail.setColumnProperty("refCd4","header","참조필드4");
        //grdDetail.setColumnProperty("refCd5","header","참조필드5");
		
		//head1 = rowData.refFldNm1;
		//head2 = rowData.refFldNm2;
		//head3 = rowData.refFldNm3;
        //head4 = rowData.refFldNm4;
        //head5 = rowData.refFldNm5;
        
		//grdDetail.setColumnProperty("refCd1","header",head1);
		//grdDetail.setColumnProperty("refCd2","header",head2);
		//grdDetail.setColumnProperty("refCd3","header",head3);
		//grdDetail.setColumnProperty("refCd4","header",head4);
		//grdDetail.setColumnProperty("refCd5","header",head5);
		
		var url = "<c:url value='/${currentMenuId}/getListDetail'/>${urlSuffix}";
		grdDetail.loadUrl(url,param);
	}
	*/
	var param = {
        mstCd : rowData.mstCd,
        recordCountPerPage : 100000
    };
	
	var url = "<c:url value='/${currentMenuId}/getListDetail'/>${urlSuffix}";
    grdDetail.loadUrl(url,param);
}
function onGridLoad(gridView,gridId){

}
function onGridCellClick(gridView,rowIndex,columnName,colIndex,fieldIndex){
	
}
function onGridCellDblClick(gridView,rowIndex,columnName,colIndex,fieldIndex){
    
}
function onGridRowChanged(gridView, oldRow, newRow){
	
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
function saveCodeM(){
	var saveJson = grdList.getSaveJson();
	
	if(saveJson.length){
        if(!confirm("수정된 데이터를 저장하시겠습니까?"))
            return ;
        var url = "";
        
        url = "<c:url value='/${currentMenuId}/save${urlSuffix}'/>";
        
        Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
            if(Utilities.processResult(data,jqXHR,"코드 저장에 실패했습니다."))
            {
                alert("코드 저장에  성공했습니다.");
                //gridView.resetAllRowStatus();
                search();
            }
        });
    }
    else{
        alert("수정된 데이터가 없습니다.");
    }
}
function addCodeM(data){
	
	if(!data)
		return;
	data.stat = "c";
	grdList.addRow(data);
}
function delCodeM(){
	var list = grdList.getCheckedJson();
	
	/*created상태인 row를 그리드에서만 삭제할때*/
    if(grdList.getCurrentJson().stat == "c"){
    	console.log("grdList.getCurrentJson().itemIndex + " + grdList.getCurrentJson().itemIndex);
    	grdList.removeRow(grdList.getCurrentJson().itemIndex);
    /*다른상태일때 트랜잭션 삭제*/
    }else{
    	
    	if(list.length ==0)
        {
            alert("체크된 데이터가 존재하지 않습니다.");
            return;
        }
        
        var saveJson = grdList.getCheckedJson();
        if(saveJson.length){
            if(!confirm("데이터를 삭제하면 복구할  수 없습니다. 계속하시겠습니까?"))
                return ;
            var url = "";
            
            url = "<c:url value='/${currentMenuId}/removeCodeList${urlSuffix}'/>";
            Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
                if(Utilities.processResult(data,jqXHR,"코드 삭제에 실패했습니다."))
                {
                    alert("코드 삭제에  성공했습니다.");
                    //grdList.removeCheckRow();
                    search();
                }
            });
        }
        else{
            alert("선택된 데이터가 없습니다.");
        }
    }
	
}

function resetCodeM(){
    
    var idx = grdList.getCurrentJson().itemIndex;
    
    grdList.restoreUpdatedRows(idx);
    
}

function lang(){
	var saveJson = grdList.getSaveJson();
	var url = "";
    
    url = "<c:url value='/${currentMenuId}/langDown${urlSuffix}'/>";
    
    Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
        if(Utilities.processResult(data,jqXHR,"코드 저장에 실패했습니다."))
        {
            alert("코드 저장에  성공했습니다.");
            //search();
        }
    });
    
}

function saveCodeD(){
    var saveJson = grdDetail.getSaveJson();
    
    if(saveJson.length){
        if(!confirm("수정된 데이터를 저장하시겠습니까?"))
            return ;
        var url = "";
        
        url = "<c:url value='/${currentMenuId}/saveDetail${urlSuffix}'/>";
        
        Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
            if(Utilities.processResult(data,jqXHR,"코드 저장에 실패했습니다."))
            {
                alert("코드 저장에  성공했습니다.");
                search();
            }
        });
    }
    else{
        alert("수정된 데이터가 없습니다.");
    }
}
function addCodeD(data){
    
	var data = grdList.getCurrentJson();
	
    if(!data)
        return;
    data.stat = "c";
    grdDetail.addRow(data);
}
function delCodeD(){
    var list = grdDetail.getCheckedJson();
    
    /*created상태인 row를 그리드에서만 삭제할때*/
    if(grdDetail.getCurrentJson().stat == "c"){
    	grdDetail.removeRow(grdDetail.getCurrentJson().itemIndex);
    /*다른상태일때 트랜잭션 삭제*/
    }else{
    	
    	if(list.length ==0)
        {
            alert("체크된 데이터가 존재하지 않습니다.");
            return;
        }
        
        var saveJson = grdDetail.getCheckedJson();
        if(saveJson.length){
            if(!confirm("데이터를 삭제하면 복구할  수 없습니다. 계속하시겠습니까?"))
                return ;
            var url = "";
            
            url = "<c:url value='/${currentMenuId}/removeCodeDetailList${urlSuffix}'/>";
            Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
                if(Utilities.processResult(data,jqXHR,"코드 삭제에 실패했습니다."))
                {
                    alert("코드 삭제에  성공했습니다.");
                    //grdList.removeCheckRow();
                    search();
                }
            });
        }
        else{
            alert("선택된 데이터가 없습니다.");
        }
    	
    }
    
    
}

$("#btnSearch").click(function(){
	
	alert("#btnSearch");
	$("form#frmSearch").find("#currentPageNo").val(1);
	search();
});

</script>