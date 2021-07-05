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
                        <label for="srchCmpyNm" class="col-1 col-form-label right">회사명</label>
                        <div class="col-2">
                          <input type="text" id="srchCmpyNm" data-enter="search" name="srchCmpyNm" class="form-control" placeholder="회사명" />
                        </div>
                        
                        <label for="srchBizrRegNo" class="col-1 col-form-label right">사업자등록번호</label>
                        <div class="col-2">
                          <input type="text" id="srchBizrRegNo" data-enter="search" name="srchBizrRegNo" class="form-control" placeholder="사업자등록번호" />
                        </div>
                        
                        <label for="srchCorpSsn" class="col-1 col-form-label right">법인번호</label>
                        <div class="col-2">
                          <input type="text" id="srchCorpSsn" data-enter="search" name="srchCorpSsn" class="form-control" placeholder="법인번호" />
                        </div>
                        
                        <label for="srchRepNm" class="col-1 col-form-label right">대표자성명</label>
                        <div class="col-2">
                          <input type="text" id="srchRepNm" data-enter="search" name="srchRepNm" class="form-control" placeholder="대표자성명" />
                        </div>
                        
                    </div>
                </div>
                
                <div class="col-2">
                       <button type="button" id="btnSearch" class="btn btn-primary float-right"><i class="fas fa-search"></i>검색
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
                <h3 class="card-title">법인정보</h3>
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGrid" style="height:200px"  data-grid-id="grdList" data-pagenation="Y" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/corp/corp.xml'/>">
                </div>
            </div>

        </div>
        <!-- /.card -->
    </div>
    <!-- /.col -->
<div class="card card-info">
              <div class="card-header">
                <h3 class="card-title">법인 정보</h3>
                <div class="card-tools">
                    <div class="input-group input-group-sm">
                     <div class="btn-group"> 
                        <button type="button" data-grid-id="grdList" onclick="excelDownload(this.id)" id="btnExcel" class="btn btn-default btn-sm"><i class="fas fa-download"></i> 엑셀다운로드</button>
                        <button type="button" data-grid-id="grdList" data-click=newCorp id="newCorp" class="btn btn-success btn-sm"><i class="fas fa-plus"></i>추가</button>
                        <button type="button" data-grid-id="grdList" data-click="saveCorp" id="saveCorp"  class="btn btn-info btn-sm"><i class="fas fa-save"></i> 저장</button>
                        <button type="button" data-grid-id="grdList" data-click="removeCorp" id="removeCorp" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> 삭제</button>
                     </div>
                    </div>
                </div>
              </div>
              <!-- /.card-header -->
             
              <form class="form-horizontal" id="frmMain" style = "width: 1650px;">
                <div class="card-body">                      
                  <div class="form-group row">
                    <label for="cmpyCd" class="col-2 col-form-label right">회사코드</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${cmpyCd}"/>'  class="form-control" id="cmpyCd" name="cmpyCd"  placeholder="회사코드" maxlength ="4">
                    </div>
                    <label for="cmpyNm" class="col-2 col-form-label right">회사명</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${cmpyNm}"/>'  class="form-control" id="cmpyNm" name="cmpyNm"  placeholder="회사명">
                    </div>
                    <label for="cmpyEnNm" class="col-2 col-form-label right">회사영문명</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${cmpyEnNm}"/>'  class="form-control" id="cmpyEnNm" name="cmpyEnNm"  placeholder="회사영문명">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="corpNm" class="col-2 col-form-label right">공시회사명</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${corpNm}"/>'  class="form-control" id="corpNm" name="corpNm"  placeholder="공시회사명">
                    </div>
                     <label for="bizrRegNo" class="col-2 col-form-label right">사업자등록번호</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${bizrRegNo}"/>'  class="form-control" id="bizrRegNo" name="bizrRegNo"  placeholder="사업자등록번호">
                    </div>
                     <label for="corpSsn" class="col-2 col-form-label right">법인등록번호</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${corpSsn}"/>'  class="form-control" id="corpSsn" name="corpSsn"  placeholder="법인등록번호">
                    </div>
                  </div>
                  
                  <div class="form-group row">
                    <label for="aitmCd" class="col-2 col-form-label right">종목</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${aitmCd }"/>'  class="form-control" id="aitmCd" name="aitmCd"  placeholder="종목">
                    </div>
                    <label for="tpisCd" class="col-2 col-form-label right">업종</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${tpisCd }"/>'  class="form-control" id="tpisCd" name="tpisCd"  placeholder="업종">
                    </div>
                    <label for="repNm" class="col-2 col-form-label right">대표자성명</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${repNm }"/>'  class="form-control" id="repNm" name="repNm"  placeholder="대표자성명">
                    </div>
                  </div>
                   <div class="form-group row">
                    <label for="repCrfrNo" class="col-2 col-form-label right">대표자주민번호</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${repCrfrNo}"/>'  class="form-control" id="repCrfrNo" name="repCrfrNo"  placeholder="대표자주민번호">
                    </div>
                    <label for="zipNo" class="col-2 col-form-label right">우편번호</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${zipNo}"/>'  class="form-control" id="zipNo" name="zipNo"  placeholder="우편번호">
                    </div>
                    <label for="pbzAdr" class="col-2 col-form-label right">사업장주소</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${pbzAdr}"/>'  class="form-control" id="pbzAdr" name="pbzAdr"  placeholder="사업장주소">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="pbzDtlAdr" class="col-2 col-form-label right">사업장상세주소</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${pbzDtlAdr}"/>'  class="form-control" id="pbzDtlAdr" name="pbzDtlAdr"  placeholder="사업장상세주소">
                    </div>
                     <label for="telNo" class="col-2 col-form-label right">전화번호</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${telNo}"/>'  class="form-control" id="telNo" name="telNo"  placeholder="전화번호">
                    </div>
                     <label for="faxNo" class="col-2 col-form-label right">팩스번호</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${faxNo}"/>'  class="form-control" id="faxNo" name="faxNo"  placeholder="팩스번호">
                    </div>
                  </div>
                  
                  <div class="form-group row">
                    <label for="homePage" class="col-2 col-form-label right">홈페이지</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${homePage }"/>'  class="form-control" id="homePage" name="homePage"  placeholder="홈페이지">
                    </div>
                    <label for="estbDt" class="col-2 col-form-label right">설립일</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${estbDt }"/>'  class="form-control" id="estbDt" name="estbDt"  placeholder="설립일">
                    </div>
                    <div class="col-2">
                      <input type="hidden" value='<c:out value="${regId }"/>'  class="form-control" id="regId" name="regId"  placeholder="작성자">
                    </div>
                  </div>
                  <div class="form-group row">
                    <div class="col-2">
                      <input type="hidden" value='<c:out value="${regDt }"/>'  class="form-control" id="regDt" name="regDt"  placeholder="작성일">
                    </div>
                    <div class="col-2">
                      <input type="hidden" value='<c:out value="${modId }"/>'  class="form-control" id="modId" name="modId"  placeholder="수정자">
                    </div>
                    <div class="col-2">                                                                                                               
                      <input type="hidden" value='<c:out value="${modDt}"/>'  class="form-control" id="modDt" name="modDt"  placeholder="수정일">
                    </div>
                  </div>
                  
                </div>
                <!-- /.card-body -->
              </form>
            </div>
    </div>
    <!-- /.col -->
<!-- /.row -->
<script>

var formData = ""; //Form Data

function grdList_rowChanged(grdList,oldRow,newRow,rowData){
    if(newRow > -1){
        formData = Utilities.setRowData("frmMain", formData, grdList, oldRow, rowData); // 조회된 rowData를 입력한 form에 자동 세팅, formData 변수에 해당 form 데이터 세팅
    }
}

$("#cmpyCd").attr("readonly", true);

function excelDownload(id){
    if(id == "btnExcel"){
        grdList.exportExcel("법인관리_공통코드.xlsx");
    }else if(id == "btnExcelDetail"){
        grdDetail.exportExcel("법인관리_하부코드.xlsx");
    }
}

$("#btnSearch").click(function(){
    $("form#frmSearch").find("#currentPageNo").val(1);
    search();
});
$("#newCorp").click(function(){
	console.log("!23123" + frmMain) 
	 $("#frmMain")[0].reset();
	$("#cmpyCd").attr("readonly", false);
});
function search()
{
    var url = "<c:url value='/${currentMenuId}/getList${urlSuffix}'/>";
    console.log("1=================" + url)  
    var param = Utilities.formToMap("frmSearch");
	grdList.loadUrl(url, param);
	$("#cmpyCd").attr("readonly", true);

	}
function grdList_pageMove(gridView, pageNo) {
		$("form#frmSearch").find("#currentPageNo").val(pageNo);
		search();
	}
$("#saveCorp").click(function(){
           
	var param = Utilities.formToMap("frmMain");
    
    if(!validate(param))
        return;
    var url = "<c:url value='/${currentMenuId}/saveCorp${urlSuffix}'/>";
    Utilities.getAjax(url,param,true,function(data,jqXHR){
        if(Utilities.processResult(data,jqXHR,"코드 추가에 실패했습니다."))
        {
        	search();
        	alert("코드추가에 성공했습니다.");
            opener.saveCorp(data.result);
        }
    }); 
});
 
function validate(param){
    if(!param.cmpyCd){
        alert("회사코드는 필수 입니다.");
        $("#cmpyCd").focus();
        return false;
    }
    if(!param.cmpyNm){
        alert("회사명은 필수 입니다.");
        $("#cmpyNm").focus();
        return false;
    }

    return true;
}
	function removeCorp() {
		var list = grdList.getCheckedJson();
		/* console.log("list ::  "+ list) */
		if (list.length == 0) {
			alert("체크된 데이터가 존재하지 않습니다.");
			return;
		}
		var saveJson = grdList.getCheckedJson();
		if (saveJson.length) {
			if (!confirm("데이터를 삭제하면 복구할  수 없습니다. 계속하시겠습니까?"))
				return;
			var url = "<c:url value='/${currentMenuId}/deleteCorp${urlSuffix}'/>";
			 /* console.log("1=================" + url) */
			Utilities.getAjax(url, saveJson, true, function(data, jqXHR) {
				if (Utilities.processResult(data, jqXHR, "삭제에 실패했습니다.")) {
					search();
					alert("삭제에  성공했습니다.");
					grdList.removeCheckRow();
				}
			});
		} else {
			alert("선택된 데이터가 없습니다.");
		}
	}

</script>