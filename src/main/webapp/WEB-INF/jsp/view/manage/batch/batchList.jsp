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
                        <label for="batNm" class="col-1 col-form-label right">배치명</label>
                        <div class="col-2">
                          <input type="text" id="batNm" data-enter="search" name="batNm" class="form-control" placeholder="배치명" />
                        </div>
                        
                        <label for="batId" class="col-1 col-form-label right">배치아이디</label>
                        <div class="col-2">
                          <input type="text" id="batId" data-enter="search" name="batId" class="form-control" placeholder="배치아이디" />
                        </div>
                        
                        
                    </div>
                </div>
                
                <div class="col-2">
                        <button type="button" id="btnSearch" data-click="searchBatch" class="btn btn-primary float-right"><i class="fas fa-search"></i> <spring:message code="button.search" />
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
                <h3 class="card-title">배치</h3>

                <div class="card-tools">
                    <div class="input-group input-group-sm">
                    </div>
                </div>
                <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGrid"   data-grid-id="grdList" data-pagenation="Y" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/system/batch.xml'/>">
                </div>
            </div>

        </div>
        <!-- /.card -->
    </div>
    <!-- /.col -->
    <div class="col-12">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">상세정보</h3>
                
                <div class="card-tools">
                    <div class="input-group input-group-sm">
                    </div>
                </div>
                <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <form class="form-horizontal" id="frmMain">
                    <div class="card-body">
                     <div class="form-group row">
                        
                        <label for="batId" class="col-1 col-form-label right">batchId</label>
                         <div class="col-2">
                             <input type="text" value='<c:out value="${batId}"/>'  class="form-control" id="batId" name="batId"  placeholder="batchId">
                        </div>
                        
                        <label for="batObjType" class="col-1 col-form-label right">BatchObjType</label>
                        <div class="col-2">
                            <input type="text" value='<c:out value="${batObjType}"/>'  class="form-control" id="batObjType" name="batObjType"  placeholder="BatchObjType">
                        </div>
                        
                        <label for="useYn" class="col-1 col-form-label right">UseYn</label>
                        <div class="col-2">
                          <input type="text" value='<c:out value="${useYn}"/>'  class="form-control" id="useYn" name="useYn"  placeholder="UseYn">
                        </div>
                     </div>
                      
                      <div class="form-group row" >
                        <label for="batNm" class="col-1 col-form-label right">batchNm</label>
                        <div class="col-8">
                            <input type="text" value='<c:out value="${batNm}"/>'  class="form-control" id="batNm" name="batNm"  placeholder="BatchNm">
                        </div>
                     </div>
                      
                      <div class="form-group row" >
                        <label for="batObjNm" class="col-1 col-form-label right">batchObjNm</label>
                        <div class="col-8">
                            <input type="text" value='<c:out value="${batObjNm}"/>'  class="form-control" id="batObjNm" name="batObjNm"  placeholder="BatchObjNm">
                        </div>
                     </div>
                     
                     <div class="form-group row" >
                        <label for="batDesc" class="col-1 col-form-label right">BatchDesc</label>
                        <div class="col-8">
                            <input type="text" value='<c:out value="${batDesc}"/>'  class="form-control" id="batDesc" name="batDesc"  placeholder="BatchDesc">
                        </div>
                     </div>
                     
                     <div class="form-group row" >
                        <label for="batCron" class="col-1 col-form-label right">BatchCron</label>
                        <div class="col-8">
                            <input type="text" value='<c:out value="${batCron}"/>'  class="form-control" id="batCron" name="batCron"  placeholder="BatchCron">
                        </div>
                     </div>
                     
                    </div>  
                    <!-- /.card-body -->
              </form>
            </div>

        </div>
        <!-- /.card -->
    </div>

</div>
<!-- /.row -->
<script>

var formData = ""; //Form Data

function grdList_rowChanged(grdList,oldRow,newRow,rowData){
    if(newRow > -1){
        formData = Utilities.setRowData("frmMain", formData, grdList, oldRow, rowData); // 조회된 rowData를 입력한 form에 자동 세팅, formData 변수에 해당 form 데이터 세팅
    }
}

function searchBatch(){
	$("form#frmSearch").find("#currentPageNo").val(1);
    search();
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





</script>