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
    <div class="card-body">
        <form role="form" id="frmSearch">
          <input type="hidden" name="currentPageNo" id="currentPageNo" value="1" />
          <input type="hidden" name="exGrpId" id="exGrpId" value="${grpId }" />
            <div class=" row">
                <div class="col-10">
                    <div class="form-group row">
                        <label for="userNm" class="col-2 col-form-label right">사용자명</label>
                        <div class="col-4">
                          <input type="text" id="userNm" data-enter="searchUser" name="userNm" class="form-control" placeholder="사용자명" />
                        </div>
                        <label for="userId" class="col-2 col-form-label right">아이디</label>
                        <div class="col-4">
                          <input type="text" id="userId" data-enter="searchUser" name="userId" class="form-control" placeholder="아이디" />
                        </div>
                    </div>
                </div>
                <div class="col-2">
                        <button type="button" id="btnSearch" data-click="searchMenu" class="btn  btn-primary float-right"><i class="fas fa-search"></i>검색
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
                <h3 class="card-title">사용자</h3>
                <div class="card-tools">
                    <div class="input-group input-group-sm">
                     <div class="btn-group">
                        <button type="button" data-grid-id="grdList" data-click="addSelected" id="btnAddUserList" class="btn btn-success btn-sm"><i class="fas fa-plus"></i>선택추가</button>
                     </div>
                    </div>
                </div>
                <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGrid"   data-grid-id="grdList" data-pagenation="Y" data-type="grid" data-tpl-url="<c:url value='/gridTemplate/group/groupUser.xml'/>">
                </div>
            </div>

        </div>
        <!-- /.card -->
    </div>
    
    <!-- /.col -->

</div>
<!-- /.row -->
<script>
function searchMenu(){
    $("form#frmSearch").find("#currentPageNo").val(1);
    search();
}
function search()
{
    var url = "<c:url value='/0102010200/user/getList'/>${urlSuffix}";
    var param = Utilities.formToMap("frmSearch");
    grdList.loadUrl(url,param);
}
function grdList_pageMove(gridView,pageNo){
    $("form#frmSearch").find("#currentPageNo").val(pageNo);
    search();
}
function addSelected(){
     var list = grdList.getCheckedJson();
    if(list.length ==0)
    {
        alert("체크된 데이터가 존재하지 않습니다.");
        return;
    }
    for(var i=0;i<list.length;i++){
        list[i].grpId = "${grpId}";
    }
    var url = "<c:url value='/0102010200/group/addGroupUserList'/>${urlSuffix}";
    Utilities.getAjax(url,list,true,function(data,jqXHR){
        if(Utilities.processResult(data,jqXHR,"그룹 사용자 추가에 실패했습니다."))
        {
            alert("그룹 사용자 추가에  성공했습니다.");
            opener.addGroupUser(list);
            top.close();
        }
    });
   
}

</script>