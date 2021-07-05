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
                        <label for="menuNm" class="col-1 col-form-label right">주소명</label>
                        <div class="col-2">
                          <input type="text" id="keyword" data-enter="searchAddress" name="keyword" class="form-control" placeholder="주소명" />
                        </div>
                        
                    </div>
                </div>
                
                <div class="col-2">
                        <button type="button" id="btnSearch" data-click="searchAddress" class="btn btn-primary float-right"><i class="fas fa-search"></i> <spring:message code="button.search" />
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
                <h3 class="card-title">주소찾기</h3>

                <div class="card-tools">
                    <div class="input-group input-group-sm">
                     <div class="btn-group"> 
                        
                     </div>
                    </div>
                </div>
                <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGrid"   data-grid-id="grdList" data-pagenation="Y" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/system/address.xml'/>">
                </div>
            </div>

        </div>
        <!-- /.card -->
    </div>
    <!-- /.col -->

</div>
<!-- /.row -->


<script>
function grdList_cellDblClick(gridView, row, col,rowData,value){
    
}
function searchAddress(){
    $("form#frmSearch").find("#currentPageNo").val(1);
    search();
}
function search()
{
    var url = "<c:url value='/${currentMenuId}/getList${urlSuffix}'/>";
    var param = Utilities.formToMap("frmSearch");
    if(!param.keyword || param.keyword.length<2)
     {
        alert("검색어는 최소 2자 이상 입력해 주세요");
        $("#keyword").focus();
        return false;
     }
    grdList.loadUrl(url,param);
}

function grdList_pageMove(gridView,pageNo){
    $("form#frmSearch").find("#currentPageNo").val(pageNo);
    search();
}
</script>