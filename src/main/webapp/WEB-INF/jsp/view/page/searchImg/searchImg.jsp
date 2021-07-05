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
                        <button type="button" id="btnNewList" name="btnNewList" data-click="" class="btn  btn-sm"><i class="fas fa-trash"></i> 추가</button>
                        <!-- <button type="button" id="btnDelList" name="btnDelList" data-click="" class="btn  btn-sm"><i class="fas fa-trash"></i> 삭제</button>  -->
                     </div>
            <!-- /.상단조회버튼추가예시 end-->    
                     <button type="button" id="btnSearch" class="btn btn-primary btn-sm"><i class="fas fa-search"></i>검색
                  </button>
                    </div>
    
    <div class="card-body search_wrap">
        <form role="form" id="frmSearch">
          <input type="hidden" name="currentPageNo" id="currentPageNo" value="1" />
          <input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="30" />
            <div class=" row">
                <div class="col-10">
                    <div class="form-group row">
                        <label for="codeLevel" class="col-1 col-form-label right">촬영기기</label>
                        <div class="col-2">
                          <code:commCode className="width100" selectedValue = "Y" prefixValue="" prefixLabel="선택" id="dtasNm" name="dtasNm" mstCd ="DTAS_CD"/>
                        </div>
                        
                        <label for="mstCd" class="col-1 col-form-label right">해역</label>
                        <div class="col-2">
                          <code:commCode className="width100" selectedValue = "Y" prefixValue="" prefixLabel="선택" id="seaNm" name="seaNm" mstCd ="SEA_CD"/>
                        </div>
                        
                        <label for="mstNm" class="col-1 col-form-label right">항구</label>
                        <div class="col-2">
                          <code:commCode className="width100" selectedValue = "Y" prefixValue="" prefixLabel="선택" id="portCd" name="portCd" mstCd ="PORT_CD"/>
                        </div>
                        
                        <label for="mstNm" class="col-1 col-form-label right">카테고리</label>
                        <div class="col-2">
                          <code:commCode className="width100" selectedValue = "Y" prefixValue="" prefixLabel="선택" id="objNm" name="objNm" mstCd ="OBJ_CD"/>
                        </div>
                        
                    </div>
                </div>
               
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
                <h3 class="card-title">학습데이터 마스터</h3>
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGrid" style="height:275px"  data-grid-id="grdList" data-pagenation="Y" data-type="grid" data-grid-callback="onGridLoad1" data-tpl-url="<c:url value='/gridTemplate/page/searchImgList1.xml'/>">
                </div>
            </div>

        </div>
        <!-- /.card -->
    </div>
    <!-- /.col -->

    <div class="col-12">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">학습데이터 정보</h3>
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGridDetail" style="height:250px"  data-grid-id="grdDetail" data-pagenation="N" data-type="grid" data-grid-callback="onGridLoad2" data-tpl-url="<c:url value='/gridTemplate/page/searchImgList2.xml'/>">
                </div>

                <!-- /.mail-box-messages -->
            </div>

        </div>
        <!-- /.card -->
    </div>
    <!-- /.col -->



</div>
<!-- /.row -->
<script>
$(document).ready(function(){
   
	$("#btnSearch").click(function(){
	    $("form#frmSearch").find("#currentPageNo").val(1);
	    search();
	});
	
	$("#btnNewList").click(function(e){
	   e.preventDefault();
	   tmpNumber = "";
	   var url = "<c:url value='/searchImg/searchImgMasterPopup${urlSuffix}'/>?stdyDtaMngNo="+tmpNumber;
	   Utilities.windowOpen(url,"searchImgMasterPopup",1000,800);
	});
	
	 /*해역 체인지 이벤트*/
    $("#seaNm").on("change", function(e){
        initCodeSelectBox("PORT_CD",$(this).val(),false,"D","portCd","S"); //퐐영기기
    });


});


/*셀렉트박스 만들기 
headerGb : //M:ZZ_CODE_M , D:ZZ_CODE_D
objId : 셀렉트박스 ID
initVal : 첫번째 박스값이 S:선택 A:전체 
*/
function initCodeSelectBox(mstCode,tmpRefCd1,tmpAsync,headerGb,objId,initVal){
    
      var initText = "전체";
      
      if(initVal=="S"){
          initText = "선택";
      }else if(initVal=="A"){
          initText = "전체";
      }
      
      var strHtml = "";
      //var data = parm;
      //data.access_token = token;   
      //console.log(data)      
       //데이터 받는 로직 필요
       
       $.ajax({
            url : "<c:url value='/${currentMenuId}/selectCodeList${urlSuffix}'/>"
          , async : tmpAsync //true, false
          , type : "POST"
          , cache:false
          , timeout : 30000 
          , data : { 
                       cmpyCd   : '0000'  //그룹코드Id
                      ,mstCd    : mstCode //마스터코드Id
                      ,refCd1   : tmpRefCd1 //REF_CD
                      ,headerGb : headerGb //M:ZZ_CODE_M , D:ZZ_CODE_D
                      
                   }
          //, data : JSON.parse(JSON.stringify(data))
          , dataType : "json"
          , xhrFields: {
                  withCredentials: false
          }
          , beforeSend: function (xhr) {
              xhr.setRequestHeader("Accept", "application/json");
          }
          , success : function (data, statusText, xhr) {
              $("#"+objId).children("*").remove();
              strHtml+="<option value=''>"+initText+"</option>";
              if(data.data.length > 0) {
                      $.each(data.data, function(i, item) {
                          strHtml+="<option value='"+item.code+"'>"+item.name+"</option>";
                      });
                      $("#"+objId).html(strHtml);
              }else{
                  strHtml+="";
                  $("#"+objId).html(strHtml);
              }
          }
          , error : function (xhr, statusText, errorThrown) { 
              console.log("error status :: " + xhr.status + " || statusText :: " + xhr.responseText + " || errorThrown :: " + errorThrown);
          }
      });
      
  }// end Code
function excelDownload(id){
    
    if(id == "btnExcel"){
        grdList.exportExcel("학습데이터 마스터.xlsx");
    }else if(id == "btnExcelDetail"){
        grdDetail.exportExcel("학습데이터 정보.xlsx");
    }
}

function grdDetail_load(gridView,gridId){
	//그리드 두번째 그리드 init시
}
function grdList_load(gridView,gridId){
	//그리드 첫번째 그리드 init시
}


function grdList_rowChanged(grdList,oldRow,newRow,rowData){
    
    var param = {
    	stdyDtaMngNo : rowData.stdyDtaMngNo,
    	//objNm : document.getElementById( 'objNm' ).value,
        recordCountPerPage : 100000
    };
    
    var url = "<c:url value='/${currentMenuId}/getListDetail'/>${urlSuffix}";
    grdDetail.loadUrl(url,param);
}
function onGridLoad1(gridView,gridId){
  //콜백함수 안먹음
}

function onGridCellClick(gridView,rowIndex,columnName,colIndex,fieldIndex){
    /*그리드 셀클릭이벤트 모든 그리드에 대해서 gridview이름으로 분기처리해야됨.*/
    if(gridView.gridId=="grdList" && columnName=="imgBtn"){
    	
    	 var tmpStdyDtaMngNo = gridView.getCurrentJson().stdyDtaMngNo;
         var url = "<c:url value='/searchImg/searchImgMasterPopup${urlSuffix}'/>?stdyDtaMngNo="+tmpStdyDtaMngNo;
         Utilities.windowOpen(url,"searchImgMasterPopup",1000,800);
    	
    }
}

function onGridCellDblClick(gridView,rowIndex,columnName,colIndex,fieldIndex){
    //사용안함
}

function onGridRowChanged(gridView, oldRow, newRow){
    //사용안함.
}


function search()
{

    var url = "<c:url value='/${currentMenuId}/getList${urlSuffix}'/>";
    var param = Utilities.formToMap("frmSearch");
    if(!param.dtasNm && !param.seaNm && !param.portCd && !param.objNm)
    {
       alert("최소 1개 이상 조회조건을 선택 해 주세요");
       $("#dtasNm").focus();
       return false;
    }
    grdList.loadUrl(url,param);
    
    
}
function grdList_pageMove(gridView,pageNo){
    $("form#frmSearch").find("#currentPageNo").val(pageNo);
    search();
}
</script>