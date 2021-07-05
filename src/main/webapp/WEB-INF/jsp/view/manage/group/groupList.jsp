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
            <div class=" row">
                <div class="col-10">
                    <div class="form-group row">
                        <label for="grpNm" class="col-1 col-form-label right">그룹명</label>
                        <div class="col-2">
                          <input type="text" id="grpNm" data-enter="searchGroup" name="grpNm" class="form-control" placeholder="그룹명" />
                        </div>
                        
                        <label for="grpId" class="col-1 col-form-label right">그룹아이디</label>
                        <div class="col-2">
                          <input type="text" id="grpId" data-enter="searchGroup" name="grpId" class="form-control" placeholder="그룹아이디" />
                        </div>
                    </div>
                </div>
                
                <div class="col-2">
                        <button type="button" id="btnSearch" data-click=searchGroup class="btn btn-primary float-right"><i class="fas fa-search"></i>검색
                  </button>
                </div>
               
            </div>
        </form>

        <!-- /.card-body -->
    </div>
</div>
<!-- /.card -->


<div class="row">

    <div class="col-4">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">그룹</h3>

                <div class="card-tools">
                    <div class="input-group input-group-sm">
                     <div class="btn-group"> 
                        <button type="button" data-grid-id="grdList" onclick="excelDownload(this.id)" id="btnExcelList" class="btn btn-default btn-sm"><i class="fas fa-download"></i> 엑셀다운로드</button>
                        <button type="button" data-grid-id="grdList" data-click="newGroup" id="btnAddList" class="btn btn-success btn-sm"><i class="fas fa-plus"></i>추가</button>
                        <button type="button" data-grid-id="grdList" data-click="saveGroup" id="btnSaveList" class="btn btn-info btn-sm"><i class="fas fa-save"></i> 저장</button>
                        <button type="button" data-grid-id="grdList" data-click="removeGroup" id="btnDelList" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> 삭제</button>
                     </div>
                    </div>
                </div>
                <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGrid"   data-grid-id="grdList" data-pagenation="Y" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/group/group.xml'/>">
                </div>
            </div>

        </div>
        <!-- /.card -->
    </div>
    <div class="col-4">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">메뉴</h3>

                <div class="card-tools">
                    <div class="input-group input-group-sm">
                     <div class="btn-group"> 
                        <button type="button" data-grid-id="grdMenu" onclick="excelDownload(this.id)" id="btnExcelMenuList" class="btn btn-default btn-sm"><i class="fas fa-download"></i> 엑셀다운로드</button>
                        <button type="button" data-grid-id="grdMenu" data-click="newGroupMenu" id="btnAddMenuList" class="btn btn-success btn-sm"><i class="fas fa-plus"></i>추가</button>
                        <button type="button" data-grid-id="grdMenu" data-click="saveGroupMenu" id="btnSaveMenuList" class="btn btn-info btn-sm"><i class="fas fa-save"></i> 저장</button>
                        <button type="button" data-grid-id="grdMenu" data-click="removeGroupMenu" id="btnDelMenuList" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> 삭제</button>
                     </div>
                    </div>
                </div>
                <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGrid"   data-grid-id="grdMenu" data-pagenation="Y" data-type="grid" data-tpl-url="<c:url value='/gridTemplate/group/groupMenu.xml'/>">
                </div>
            </div>

        </div>
        <!-- /.card -->
    </div>
    <div class="col-4">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">사용자</h3>

                <div class="card-tools">
                    <div class="input-group input-group-sm">
                     <div class="btn-group"> 
                        <button type="button" data-grid-id="grdUser" onclick="excelDownload(this.id)" id="btnExcelUserList" class="btn btn-default btn-sm"><i class="fas fa-download"></i> 엑셀다운로드</button>
                        <button type="button" data-grid-id="grdUser" data-click="newGroupUser" id="btnAddUserList" class="btn btn-success btn-sm"><i class="fas fa-plus"></i>추가</button>
                        <button type="button" data-grid-id="grdUser" data-click="removeGroupUser" id="btnDelUserList" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> 삭제</button>
                     </div>
                    </div>
                </div>
                <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGrid"   data-grid-id="grdUser" data-pagenation="Y" data-type="grid"  data-tpl-url="<c:url value='/gridTemplate/group/groupUser.xml'/>">
                </div>
            </div>

        </div>
        <!-- /.card -->
    </div>
    <!-- /.col -->

</div>
<!-- /.row -->
<script>
var _current_group_id = null;

/************************************
 * 엑셀 다운로드
 ************************************/
function excelDownload(id){
    if(id == "btnExcelList"){
        grdList.exportExcel("그룹관리_그룹.xlsx");
    }else if(id == "btnExcelMenuList"){
        grdDetail.exportExcel("그룹관리_메뉴.xlsx");
    }else if(id == "btnExcelUserList"){
        grdDetail.exportExcel("그룹관리_사용자.xlsx");
    }
}

/************************************
 * 그룹 그리드 행 변경 이벤트
 ************************************/
function grdList_rowChanged(grdView,oldRow,newRow,rowData){
	_current_group_id = rowData.grpId;
	g_currentJson = rowData;
    if(newRow > -1){
    	searchMenu(1);
    	searchUser(1);
    }
}

/************************************
 * 메뉴 조회
 ************************************/
function searchMenu(currentPageNo){
    var param = {
        grpId : _current_group_id,
        currentPageNo : currentPageNo?currentPageNo : 1 
    };
    url = "<c:url value='/${currentMenuId}/getGroupMenuList'/>${urlSuffix}";
    grdMenu.loadUrl(url,param);
}

/************************************
 * 사용자 조회
 ************************************/
function searchUser(currentPageNo){
	var param = {
			grpId : _current_group_id,
	        currentPageNo : currentPageNo?currentPageNo : 1 
	    };
	var url = "<c:url value='/${currentMenuId}/getGroupUserList'/>${urlSuffix}";
    grdUser.loadUrl(url,param);
}

/************************************
 * 그룹 조회
 ************************************/
function searchGroup(){
	$("form#frmSearch").find("#currentPageNo").val(1);
    search();
}

/************************************
 * 그룹 추가
 ************************************/
function newGroup(){
	var url = "<c:url value='/${currentMenuId}/add${urlSuffix}'/>";
    Utilities.windowOpen(url,"addGroup",900,550);
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

function grdList_pageMove(gridView,pageNo){
    $("form#frmSearch").find("#currentPageNo").val(pageNo);
    search();
}
function grdMenu_pageMove(gridView,pageNo){
	searchMenu(pageNo);
}
function grdUser_pageMove(gridView,pageNo){
	searchUser(pageNo);
}

/************************************
 * 그룹 저장
 ************************************/
function saveGroup(){
    var saveJson = grdList.getSaveJson();
    if(saveJson.length){
        if(!confirm("수정된 데이터를 저장하시겠습니까?"))
            return ;
        var url = "<c:url value='/${currentMenuId}/save${urlSuffix}'/>";
        Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
            if(Utilities.processResult(data,jqXHR,"그룹 저장에 실패했습니다."))
            {
                alert("그룹 저장에  성공했습니다.");
                grdList.resetAllRowStatus();
            }
        });
    }
    else{
        alert("수정된 데이터가 없습니다.");
    }
}

/************************************
 * 메뉴 저장
 ************************************/
function saveGroupMenu(){
    var saveJson = grdMenu.getSaveJson();
    if(saveJson.length){
        if(!confirm("수정된 데이터를 저장하시겠습니까?"))
            return ;
        var url = "<c:url value='/${currentMenuId}/saveGroupMenuList${urlSuffix}'/>";
        Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
            if(Utilities.processResult(data,jqXHR,"그룹메뉴 저장에 실패했습니다."))
            {
                alert("그룹메뉴 저장에  성공했습니다.");
                grdMenu.resetAllRowStatus();
            }
        });
    }
    else{
        alert("수정된 데이터가 없습니다.");
    }
}
/************************************
 * 그룹 행 추가
 ************************************/
function addGroup(data){
    if(!data)
        return;
    data.stat = "n";
    grdList.addRow(data);
}

/************************************
 * 메뉴 행 추가
 ************************************/
function addGroupMenu(list){
	if(list)
		   grdMenu.addRows(list);
}

/************************************
 * 사용자 행 추가
 ************************************/
function addGroupUser(list){
    if(list)
           grdUser.addRows(list);
}

/************************************
 * 그룹 삭제
 ************************************/
function removeGroup(){
	var saveJson = grdList.getCheckedJson();
    if(saveJson.length ==0)
    {
        alert("체크된 데이터가 존재하지 않습니다.");
        return;
    }
    
    if(saveJson.length){
        if(!confirm("데이터를 삭제하면 복구할  수 없습니다. 계속하시겠습니까?"))
            return ;
        var url = "<c:url value='/${currentMenuId}/removeGroupList${urlSuffix}'/>";
        Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
            if(Utilities.processResult(data,jqXHR,"그룹 삭제에 실패했습니다."))
            {
                alert("그룹 삭제에  성공했습니다.");
                grdList.removeCheckRow();
            }
        });
    }
    else{
        alert("선택된 데이터가 없습니다.");
    }
}

/************************************
 * 메뉴 삭제
 ************************************/
function removeGroupMenu(){
    var saveJson = grdMenu.getCheckedJson();
    if(saveJson.length ==0)
    {
        alert("체크된 데이터가 존재하지 않습니다.");
        return;
    }
    
    if(saveJson.length){
        if(!confirm("데이터를 삭제하면 복구할  수 없습니다. 계속하시겠습니까?"))
            return ;
        var url = "<c:url value='/${currentMenuId}/removeGroupMenuList${urlSuffix}'/>";
        Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
            if(Utilities.processResult(data,jqXHR,"그룹메뉴 삭제에 실패했습니다."))
            {
                alert("그룹메뉴 삭제에  성공했습니다.");
                grdMenu.removeCheckRow();
            }
        });
    }
    else{
        alert("선택된 데이터가 없습니다.");
    }
}

/************************************
 * 사용자 삭제
 ************************************/
function removeGroupUser(){
    var saveJson = grdUser.getCheckedJson();
    if(saveJson.length ==0)
    {
        alert("체크된 데이터가 존재하지 않습니다.");
        return;
    }
    
    if(saveJson.length){
        if(!confirm("데이터를 삭제하면 복구할  수 없습니다. 계속하시겠습니까?"))
            return ;
        var url = "<c:url value='/${currentMenuId}/removeGroupUserList${urlSuffix}'/>";
        Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
            if(Utilities.processResult(data,jqXHR,"그룹 사용자 삭제에 실패했습니다."))
            {
                alert("그룹 사용자 삭제에  성공했습니다.");
                grdUser.removeCheckRow();
            }
        });
    }
    else{
        alert("선택된 데이터가 없습니다.");
    }
}

/************************************
 * 메뉴 추가
 ************************************/
function newGroupMenu(){
	_current_group_id;
	if(!_current_group_id){
		alert("먼저 그룹을 선택하세요");
		return false;
	}
	var url = "<c:url value='/${currentMenuId}/group/groupMenuPopup${urlSuffix}'/>?grpId="+_current_group_id;
    Utilities.windowOpen(url,"addGroupMenu",700,680);
}

/************************************
 * 사용자 추가
 ************************************/
function newGroupUser(){
    _current_group_id;
    if(!_current_group_id){
        alert("먼저 그룹을 선택하세요");
        return false;
    }
    var url = "<c:url value='/${currentMenuId}/group/groupUserPopup${urlSuffix}'/>?grpId="+_current_group_id;
    Utilities.windowOpen(url,"addGroupUser",700,680);
}

</script>