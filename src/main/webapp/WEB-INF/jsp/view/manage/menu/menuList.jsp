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
        <%-- <form role="form" id="frmSearch">
          <input type="hidden" name="currentPageNo" id="currentPageNo" value="1" />
            <div class=" row">
                <div class="col-10">
                    <div class="form-group row">
                        <label for="rootMenuId" class="col-1 col-form-label right">시스템메뉴</label>
                        <div class="col-1">
                          <code:commCode className="form-control" prefixValue="" prefixLabel="전체" id="rootMenuId" name="rootMenuId" mstCd ="SYSTEM_GB" selectedValue="${rootCodeId}"/>
                        </div>
                        <label for="menuLevel" class="col-1 col-form-label right">메뉴레벨</label>
                        <div class="col-1">
                            <select id="menuLevel"  name="menuLevel" class="form-control" >
                                <option value="">전체</option>
                                <option value="1">1</option> 
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </div>
                        <label for="menuNm" class="col-1 col-form-label right">메뉴명</label>
                        <div class="col-2">
                          <input type="text" id="menuNm" data-enter="search" name="menuNm" class="form-control" placeholder="메뉴명" />
                        </div>
                        
                        <label for="menuId" class="col-1 col-form-label right">메뉴아이디</label>
                        <div class="col-2">
                          <input type="text" id="menuId" data-enter="search" name="menuId" class="form-control" placeholder="메뉴아이디" />
                        </div>
                        
                        
                    </div>
                </div>
                
                <div class="col-2">
                        <button type="button" id="btnSearch" class="btn btn-primary float-right"><i class="fas fa-search"></i>검색
                  </button>
                </div>
               
            </div>
        </form> --%>
        
        <form role="form" id="frmSearch">
            <input type="hidden" name="currentPageNo" id="currentPageNo" value="1" />
            <input type="hidden" id="menuId" data-enter="search" name="menuId" class="form-control" placeholder="메뉴아이디" />
        </form>

        <!-- /.card-body -->
    </div>
</div>
<!-- /.card -->


<div class="row">
<div class="col-2">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">메뉴</h3>
                <div class="card-tools">
                    <div class="input-group input-group-sm">
                        <div class="btn-group"> 
                            <!-- <button type="button"id="btnDelList" class="btn btn-default btn-sm"><i class="far fa-circle"></i> -->
                            <!-- <button type="button"id="btnDelList" class="btn btn-default btn-sm">
                                <i class="far fa-circle"  style="font-size:125%;"><strong class="fa-stack-1x" style="font-size:70%; bottom:35%;">1</strong></i>
                            </button>
                            <button type="button"id="btnDelList" class="btn btn-default btn-sm">
                                <i class="far fa-circle"  style="font-size:125%;"><strong class="fa-stack-1x" style="font-size:70%; bottom:35%;">2</strong></i>
                            </button>
                            <button type="button"id="btnDelList" class="btn btn-default btn-sm">
                                <i class="far fa-circle"  style="font-size:125%;"><strong class="fa-stack-1x" style="font-size:70%; bottom:35%;">3</strong></i>
                            </button> -->
                            <span id="level1" onclick="levelClick(this.id)" value="false" style="cursor:pointer; background-color:#D6D6D6;">&nbsp <b>&#9312;</b> &nbsp</span>
                            <span id="level2" onclick="levelClick(this.id)" style="cursor:pointer; background-color:#BBBBBB;">&nbsp <b>&#9313;</b> &nbsp</span>
                            <span id="level3" onclick="levelClick(this.id)" style="cursor:pointer; background-color:#AEAEAE;">&nbsp <b>&#9314;</b> &nbsp</span>
                            <span id="levelA" onclick="levelClick(this.id)" style="cursor:pointer; background-color:#939393;">&nbsp <b>A      </b> &nbsp&nbsp</span>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divMenuTree" data-grid-id="grdTree" style="height:600px" data-grid-callback="onGridLoad"  data-tpl-url="<c:url value='/gridTemplate/menu/menu.xml'/>"  data-type="tree" data-url="<c:url value='/${currentMenuId}/menu/getMenuTree'/>${urlSuffix}">
                </div>
            </div>

        </div>
        <!-- /.card -->
    </div>
    <div class="col-10">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">메뉴</h3>
                <div class="card-tools">
                    <div class="input-group input-group-sm">
                     <div class="btn-group"> 
                        <button type="button" data-grid-id="grdList" onclick="excelDownload(this.id)" id="btnExcel" class="btn btn-default btn-sm"><i class="fas fa-download"></i> 엑셀다운로드</button>
                        <button type="button" data-grid-id="grdList" id="btnAddList" data-action="add" class="btn btn-success btn-sm"><i class="fas fa-plus"></i>시스템코드추가</button>
                        <button type="button" data-grid-id="grdList" id="btnSaveList" data-action="save" class="btn btn-info btn-sm"><i class="fas fa-save"></i> 저장</button>
                        <button type="button" data-grid-id="grdList" id="btnDelList" data-action="del" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> 삭제</button>
                     </div>
                    </div>
                </div>
                <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGrid"  style="height:100px"  data-grid-id="grdList" data-pagenation="N" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/menu/menuMain.xml'/>">
                </div>
            </div>

        </div>
        <!-- /.card -->
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">하부메뉴</h3>
                <div class="card-tools">
                    <div class="input-group input-group-sm">
                        <div class="btn-group">
                            <button type="button" data-grid-id="grdList" onclick="excelDownload(this.id)" id="btnExcelDetail" class="btn btn-default btn-sm"><i class="fas fa-download"></i> 엑셀다운로드</button>
                            <button type="button" id="btnAddDetail"  data-grid-id="grdDetail" data-action="add" class="btn btn-success btn-sm"><i class="fas fa-plus"></i> 추가</button>
                            <button type="button" id="btnSaveDetail"  data-grid-id="grdDetail" data-action="save" class="btn btn-info btn-sm"><i class="fas fa-save"></i> 저장</button>
                            <button type="button" id="btnDelDetail"  data-grid-id="grdDetail" data-action="del" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> 삭제</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGridDetail" style="height:434px"  data-grid-id="grdDetail" data-pagenation="N" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/menu/menuDetail.xml'/>">
                </div>
                <!-- /.mail-box-messages -->
            </div>

        </div>
    </div>
    <!-- /.col -->

    <%-- <div class="col-8">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">하부메뉴</h3>
                <div class="card-tools">
                    <div class="input-group input-group-sm">
                        <div class="btn-group">
                            <button type="button" data-grid-id="grdList" onclick="excelDownload(this.id)" id="btnExcelDetail" class="btn btn-default btn-sm"><i class="fas fa-download"></i> 엑셀다운로드</button>
                            <button type="button" id="btnAddDetail"  data-grid-id="grdDetail" data-action="add" class="btn btn-success btn-sm"><i class="fas fa-plus"></i> 추가</button>
                            <button type="button" id="btnSaveDetail"  data-grid-id="grdDetail" data-action="save" class="btn btn-info btn-sm"><i class="fas fa-save"></i> 저장</button>
                            <button type="button" id="btnDelDetail"  data-grid-id="grdDetail" data-action="del" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> 삭제</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGridDetail" style="height:260px"  data-grid-id="grdDetail" data-pagenation="N" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/menu/menuDetail.xml'/>">
                </div>
                <!-- /.mail-box-messages -->
            </div>

        </div>
        <!-- /.card -->
    </div> --%>
    <!-- /.col -->



</div>
<!-- /.row -->
<script>
function htmlCallback(grid, itemIndex, colName){
	var str;
	
	if(grid.gridId == "grdList"){
		// 메뉴
		str = '<button type="button" onclick="_onGridButtonClick(this)" id="mainBtn_' + itemIndex + '" data-field-name="' + colName + '" data-grid-id="' + grid.gridId
            + '" style="width:100%;heigth:100%" data-row="' + itemIndex + '" class="btn btn-xs btn-primary">그룹</button>';
			
	}else if(grid.gridId == "grdDetail"){
		// 하부메뉴
		str = '<button type="button" onclick="_onGridButtonClick(this)" id="detailBtn_' + itemIndex + '" data-field-name="' + colName + '" data-grid-id="' + grid.gridId
        + '" style="width:100%;heigth:100%" data-row="' + itemIndex + '" class="btn btn-xs btn-primary">그룹</button>';
	}
	
	return str;
}

/************************************
 * 엑셀 다운로드
 ************************************/
function excelDownload(id){
    if(id == "btnExcel"){
        grdList.exportExcel("메뉴관리_메뉴.xlsx");
    }else if(id == "btnExcelDetail"){
        grdDetail.exportExcel("메뉴관리_하부메뉴.xlsx");
    }
}
/************************************
 * 트리 그리드 선택 이벤트
 ************************************/
function treeSelected(evt,data){
	$("#menuId").val(data.id);
	$("form#frmSearch").find("#currentPageNo").val(1);
    search();
	
// 	selectedData = data;
}

/************************************
 * 트리 선택해제 이벤트
 ************************************/
function treeUnselected(evt,data){
// 	$("#menuId").val("");
// 	$("form#frmSearch").find("#currentPageNo").val(1);
//     search();
}

/************************************
 * 하부메뉴 그리드 로드 함수
 ************************************/
function grdDetail_load(gridView,gridId){

}

/************************************
 * 메뉴 그리드 로드 함수
 ************************************/
function grdList_load(gridView,gridId){
//  search();
}

/************************************
 * 메뉴 그리드 행 변경 이벤트
 ************************************/
function grdList_rowChanged(grdList,oldRow,newRow,rowData){
    if(newRow > -1){
    	grdDetail.commit();
        var saveJson = grdDetail.getSaveJson();
        if(saveJson.length){
            if(!confirm("변경된 데이터가 존재합니다. 먼저 저장하시겠습니까?"))
            {
                // 변경 내용 취소 후, 이동
            	grdDetail.cancel();
            }
            else
            	// saveDetail 함수 실행
                saveDetail();
        } 
        var param = {
            upMenuId : rowData.menuId,
            menuLevel : Number(rowData.menuLevel) +1,
            rootmenuId : rowData.rootMenuId,
            recordCountPerPage : 100000
        };
        var url = "<c:url value='/${currentMenuId}/getList'/>${urlSuffix}";
        grdDetail.loadUrl(url,param);
    }
}

/************************************
 * 트리 그리드 로드 함수
 ************************************/
function grdTree_load(){
	var url = "<c:url value='/${currentMenuId}/menu/getMenuTree'/>${urlSuffix}";
    grdTree.loadUrl(url,{});
}

/************************************
 * 트리 그리드 데이터 로드 함수
 ************************************/
function grdTree_dataLoaded(gridView, jsonRows, data){
	var url  = "images/icon/"; // icon이 있는 경로
    var iconName = ["", "user.png", "menu2.png", "menu3.png", "menu4.png"]; // icon 이미지의 파일명
	grdTree.setTreeIcons(data, url, iconName);
	
	// expand(itemIndex, recursive, force, level)
	grdTree.expand(0, true, false, 2) // 3레벨 자동 default 세팅
}

/************************************
 * 메뉴 그리드 데이터 로드 함수
 ************************************/
function grdList_dataLoaded(gridView, jsonRows, data){
	for(var i=0; i<jsonRows.length; i++){
		if(Utilities.isEmpty(jsonRows[i].menuUrl)){
	        // menuUrl이 없을 경우, 그룹버튼 숨기기
			$("#mainBtn_"+i).hide();
	    }else {
	    	// menuUrl이 없을 경우, 그룹버튼 보이기
	    	$("#mainBtn_"+i).show();
	    }
	}
}

/************************************
 * 하부메뉴 그리드 데이터 로드 함수
 ************************************/
function grdDetail_dataLoaded(gridView, jsonRows, data){
	for(var i=0; i<jsonRows.length; i++){
        if(Utilities.isEmpty(jsonRows[i].menuUrl)){
            $("#detailBtn_"+i).hide();
        }else {
            $("#detailBtn_"+i).show();
        }
    }
}

/************************************
 * 트리 그리드 행 변경 함수
 ************************************/
function grdTree_rowChanged(grdView,oldRow,newRow,rowData){
    if(!rowData)
        return;
    $("#menuId").val(rowData.menuId);
    $("form#frmSearch").find("#currentPageNo").val(1);
    search();
}


function onGridLoad(gridView,gridId){
    
}
function onGridCellClick(gridView,rowIndex,columnName,colIndex,fieldIndex){
    
}
function onGridCellDblClick(gridView,rowIndex,columnName,colIndex,fieldIndex){
    
}
function onGridRowChanged(gridView, oldRow, newRow){
    
}
/*
 * 메뉴 Level 클릭 이벤트
 * 1 : 1레벨까지 펼치기
 * 2 : 2레벨까지 펼치기
 * 3 : 3레벨까지 펼치기
 * F : 전부 펼치기
 */
function levelClick(id){
	var options; // option 조건 변수
	
	switch(id){
        case "level1" : // 1레벨
            options = {fields:["menuLevel"], value:"1", wrap:false}; // wrap을 false로 해야 조회값이 없을때 초기로 돌아가지 않음.
            treeLevelExpand(grdTree, options);
            break;
            
        case "level2" : // 2레벨
        	options = {fields:["menuLevel"], value:"2", wrap:false};
        	treeLevelExpand(grdTree, options);
            break;
            
        case "level3" : // 3레벨
        	options = {fields:["menuLevel"], value:"3", wrap:false};	
        	treeLevelExpand(grdTree, options);
        	break;
        	
        case "levelA" : // 4레벨
        	options = {fields:["menuLevel"], value:"4", wrap:false};
        	treeLevelExpand(grdTree, options);
            break;
            
        default :
        	break;
    }
}
/*
 * option에 입력한 조건에 따라 일치하는 treeGrid expand 실행함수
 * param : grdId / 그리드 Id
 * param : options / 검색할 options 조건
 */
function treeLevelExpand(grdId, options)
{
	grdId.collapseAll(true); // 전체 노드 접기
	
    var ret; // searchData의 return값인 dataRow를 담기위한 변수
	var rowId; // row Index
	var parents; // 부모 노드의 dataRow
	var length = grdId.getRowCount(); // 해당 Grid의 길이
	
	for(var i=0; i<length; i++){
		options.startIndex = i; // 검색을 시작할 처음 위치 지정.
		ret = grdId.searchData(options);
        
		if(ret){
			rowId = ret.dataRow;
	        parents = grdId.getAncestors(rowId);
	        if(parents) {
	            for (var j=parents.length-1; j>=0; j--) {
	                grdId.expand(grdId.getItemIndex(parents[j])); // 부모 노드의 길이만큼 expand
	                // console.log(grdId.getItemIndex(parents[j]))
	            }
	        }	
		}
    }
	// grdId.setCurrent({itemIndex:grdId.getItemIndex(rowId), fieldIndex:ret.fieldIndex}); // 마지막 행 focus
	// grdId.setCurrent({itemIndex:0}); 
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

/************************************
 * 저장
 ************************************/
function saveMenu(gridView){
    var saveJson = gridView.getSaveJson();
    
    if(validate(saveJson)){
    	if(saveJson.length){
            if(!confirm("저장하시겠습니까?"))
                return ;
            var url = "<c:url value='/${currentMenuId}/save${urlSuffix}'/>";
            Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
                if(Utilities.processResult(data,jqXHR,"메뉴 저장에 실패했습니다."))
                {
                    alert("메뉴 저장에  성공했습니다.");
                    gridView.resetAllRowStatus();
                }
            });
        }
        else{
            alert("저장된 데이터가 없습니다.");
        }
    }
    
}

function addMenu(data){
    if(!data)
        return;
    data.stat = "n";
    if(data.menuLevel == 1)
        grdList.addRow(data);
    else
        grdDetail.addRow(data);
}

/************************************
 * 삭제
 ************************************/
function removeMenu(gridView){
    var list = gridView.getCheckedJson();
    if(list.length ==0)
    {
        alert("체크된 데이터가 존재하지 않습니다.");
        return;
    }
    var saveJson = gridView.getCheckedJson();
    if(saveJson.length){
        if(!confirm("데이터를 삭제하면 복구할  수 없습니다. 계속하시겠습니까?"))
            return ;
        var url = "<c:url value='/${currentMenuId}/removeMenuList${urlSuffix}'/>";
        Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
            if(Utilities.processResult(data,jqXHR,"메뉴 삭제에 실패했습니다."))
            {
                alert("메뉴 삭제에  성공했습니다.");
                gridView.removeCheckRow();
            }
        });
    }
    else{
        alert("선택된 데이터가 없습니다.");
    }
}

/************************************
 * 메뉴 시스템코드 추가 버튼 클릭 이벤트
 ************************************/
function grdList_btnGroup_buttonClicked(gridView,row,col,json){
	var url = "<c:url value='/${currentMenuId}/group/groupPopup${urlSuffix}'/>?menuId="+json.menuId;
    Utilities.windowOpen(url,"groupPopup",500,580);
}

/************************************
 * 하부메뉴 그룹 버튼 클릭 이벤트
 ************************************/
function grdDetail_btnGroup_buttonClicked(gridView,row,col,json){
	//alert("여기냐");
	if(Utilities.isEmpty(json.menuUrl)){
        alert("메뉴 주소가 있는 경우에만 그룹설정을 하실 수 있습니다.")
        return false;
    }
	
	var url = "<c:url value='/${currentMenuId}/group/groupPopup${urlSuffix}'/>?menuId="+json.menuId;
    Utilities.windowOpen(url,"groupPopup",500,580);
    
    
    /* test mdj
    var tmpNumber = "UDP0001626";
    var url = "<c:url value='/searchImg/searchImgBindPopup${urlSuffix}'/>?stdyDtaMngNo="+tmpNumber;
	Utilities.windowOpen(url,"searchImgBindPopup",800,600);
	*/
}

/************************************
 * 그룹 설정 저장 이벤트
 ************************************/
function saveGroupChecked(menuId,saveJson){
    var url = "<c:url value='/${currentMenuId}/group/setMenuGroup${urlSuffix}'/>?menuId="+ menuId;
    
    Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
        if(Utilities.processResult(data,jqXHR,"그룹 설정에 실패했습니다."))
        {
            alert("그룹 설정에  성공했습니다.");
            
        }
    });
}

/************************************
 * 조회 버튼 클릭 이벤트
 ************************************/
$("#btnSearch").click(function(){
    $("form#frmSearch").find("#currentPageNo").val(1);
    search();
});

/************************************
 * 시스템코드 추가 버튼 클릭 이벤트
 ************************************/
$("#btnAddList").click(function(){
	
	var jsonRows = grdTree.getJsonRows(); // 트리 그리드의 행 정보
	var length = jsonRows.length; // 행 길이
	var menuId; // menuId를 담을 변수
	var menuSeq; // menuSeq를 담을 변수
	
	for(var i=length-1; i>0; i--){
		// 마지막 행부터 검색
		if(jsonRows[i].menuLevel == "1"){
			// 1레벨인 경우, 1레벨 중 메뉴아이디가 가장 큼으로 for문 종료
			menuId = jsonRows[i].menuId;
			menuSeq = jsonRows[i].menuSeq;
			i=0; // for문 종료
		}
	}
	
	// 형변환 후, 1 더하기
	var nextId = (parseInt(menuId.substr(0,2)) + 1) + "";
	var nextSeq = (parseInt(menuSeq) + 1) + "";
	
	if(nextId.length == 1){
		// 한자리인 경우, 0 붙이기
		nextId = "0" + nextId;
	}
	
	nextId = nextId + menuId.substr(2, 8); // 다음 메뉴아이디 채번
	
	// 1레벨 메뉴 생성이므로 팝업으로 추가
	var url = "<c:url value='/${currentMenuId}/add${urlSuffix}'/>?menuId="+nextId+"&menuSeq="+nextSeq+"&menuLevel=1";
    Utilities.windowOpen(url,"addMenu",900,730);
});

/************************************
 * 추가 버튼 클릭 이벤트
 ************************************/
$("#btnAddDetail").click(function(){
    var data = grdList.getCurrentJson();
    if(!data){
        alert("먼저 상위 메뉴를 선택하세요");
        return;
        
    }
    if(data.menuUrl){
        alert("메뉴 URL이 설정된 메뉴 하부에는 메뉴를 추가할 수 없습니다.");
        return;
    }
    if(data.menuLevel >=5){
        alert("5단계 하위에는 메뉴를 추가할 수 없습니다.");
        return;
    }
    
    var jsonRows = grdDetail.getJsonRows(); // 하부메뉴 그리드의 행 정보
    var length = jsonRows.length; // 행 길이
    var menuId; // menuId를 담을 변수
    var menuLevel; // menuLevel
    var upMenuId; // 상위메뉴
    var rootMenuId; // 시스템메뉴
    var preVal; // 앞 코드
    var val; // 레벨 따라 추출 및 증가하는 코드
    var postVal; // 뒤 코드
    var nextId; // 추출한 menuId의 substr한 값을 담을 변수
    
    if(length == 0){
    	// 처음 하부메뉴 추가시
    	menuId = data.menuId;
    	menuLevel = data.menuLevel + 1;
    	upMenuId = data.menuId;
    	rootMenuId = data.rootMenuId;
    }else {
    	for(var i=0; i<length; i++){
            if(Utilities.isEmpty(menuId)){
                menuId = jsonRows[i].menuId;
                menuLevel = jsonRows[i].menuLevel;
                upMenuId = jsonRows[i].upMenuId;
                rootMenuId = jsonRows[i].rootMenuId;
            }else {
                if(menuId < jsonRows[i].menuId){
                    menuId = jsonRows[i].menuId;
                    menuLevel = jsonRows[i].menuLevel;
                    upMenuId = jsonRows[i].upMenuId;
                    rootMenuId = jsonRows[i].rootMenuId;
                }
            }
        }
    }
    
    switch(menuLevel) {
        case 2 : 
            // 2레벨
            val = (parseInt(menuId.substr(2,2)) + 1) + "";
            preVal = menuId.substr(0, 2);
            postVal = menuId.substr(4, 6);
            break;
            
        case 3 :
            // 3레벨
            val = (parseInt(menuId.substr(4,2)) + 1) + "";
            preVal = menuId.substr(0, 4);
            postVal = menuId.substr(6, 4);
            break;
        
        case 4 :
            // 4레벨
            val = (parseInt(menuId.substr(6,2)) + 1) + "";
            preVal = menuId.substr(0, 6);
            postVal = menuId.substr(8, 2);
            break;
            
        case 5 :
            // 5레벨
            val = (parseInt(menuId.substr(8,2)) + 1) + "";
            preVal = menuId.substr(0, 8);
            break;
            
        default :
            break;
    }
    
    if(!isNaN(val)){
    	if(val.length == 1){
            // 한자리인 경우, 0 붙이기
            val = "0" + val;
            nextId = preVal + val + postVal; // 다음에 채번되는 코드
        }else if(val.length == 3){
            nextId = ""; // 해당 레벨에서 메뉴 추가할 수 있는 한도를 넘었을 경우, 공백 처리
        }else {
        	// 2자리일 경우
        	nextId = preVal + val + postVal; // 다음에 채번되는 코드
        }
    }else {
    	nextId = ""; // 숫자가 아닌 경우 공백처리
    }
    
    var row = {}; // 행에 추가될 데이터 변수
    row.stat = "c";
    row.menuId = nextId;
    row.menuLevel = menuLevel;
    row.upMenuId = upMenuId;
    row.rootMenuId = rootMenuId;
    row.menuShowYn = "Y";
    row.useYn = "Y";
    row.menuAuthYn = "Y";
    
    // console.log("row ::", row);
    grdDetail.addRow(row);
});

function validate(param){
	// console.log(param);
	for(i=0; i<param.length; i++){
		if(!param[i].menuId){
	        alert("메뉴 아이디는 필수 입니다.");
	        return false;
	    }
	    if(!param[i].menuNm){
	        alert("메뉴 명은 필수 입니다.");
	        return false;
	    }
	    if(!param[i].menuLevel){
	         alert("메뉴레벨은 필수 입니다.");
	         return false;
	    }
	    if(!Utilities.isNumberOnly(param[i].menuLevel)){
	      alert("메뉴레벨은 숫자 형식이어야합니다.");
	         return false;
	    }
	    if(!param[i].menuSeq){
	        alert("순번은 필수 입니다.");
	        return false;
	    }
	    if(!Utilities.isNumberOnly(param[i].menuSeq)){
	        alert("순번은 숫자 형식이어야합니다.");
	        return false;
	    }
	}
	
    return true;
}

/************************************
 * 하부메뉴 삭제 클릭 이벤트
 ************************************/
$("#btnDelDetail").click(function(){
    
    removeMenu(grdDetail);
});

/************************************
 * 메뉴 삭제 클릭 이벤트
 ************************************/
$("#btnDelList").click(function(){
    removeMenu(grdList);
    
});

/************************************
 * 하부메뉴 저장 클릭 이벤트
 ************************************/
$("#btnSaveDetail").click(function(){
    
    saveMenu(grdDetail);
});

/************************************
 * 메뉴 저장 클릭 이벤트
 ************************************/
$("#btnSaveList").click(function(){
    saveMenu(grdList);
    
});
</script>