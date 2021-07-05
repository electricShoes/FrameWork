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
                        <label for="srchUserNm" class="col-1 col-form-label right">사용자명</label>
                        <div class="col-2">
                          <input type="text" id="srchUserNm" data-enter="search" name="userNm" class="form-control" placeholder="사용자명" />
                        </div>
                        
                        <label for="srchUserId" class="col-1 col-form-label right">사용자아이디</label>
                        <div class="col-2">
                          <input type="text" id="srchUserId" data-enter="search" name="userId" class="form-control" placeholder="사용자아이디" />
                        </div>
                    </div>
                </div>
                
                <div class="col-2">
                        <button type="button" id="btnSearch" data-click="searchUser" class="btn btn-primary float-right"><i class="fas fa-search"></i> <spring:message code="button.search" />
                  </button>
                </div>
               
            </div>
        </form>

        <!-- /.card-body -->
    </div>
</div>
<!-- /.card -->


<div class="row">

    <%-- <div class="col-12">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">사용자</h3>

                <div class="card-tools">
                    <div class="input-group input-group-sm">
                     <div class="btn-group"> 
                        <button type="button" data-grid-id="grdList" onclick="excelDownload(this.id)" id="btnExcel" class="btn btn-default btn-sm"><i class="fas fa-download"></i> 엑셀다운로드</button>
                        <button type="button" data-grid-id="grdList" data-click="newUser" id="btnAddList" class="btn btn-success btn-sm"><i class="fas fa-plus"></i>추가</button>
                        <button type="button" data-grid-id="grdList" data-click="saveUser" id="btnSaveList"  class="btn btn-info btn-sm"><i class="fas fa-save"></i> 저장</button>
                        <button type="button" data-grid-id="grdList" data-click="removeUser" id="btnDelList" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> 삭제</button>
                     </div>
                    </div>
                </div>
                <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGrid"   data-grid-id="grdList" data-pagenation="Y" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/user/user.xml'/>">
                </div>
            </div>

        </div>
        <!-- /.card -->
    </div> --%>
    <!-- /.col -->
    
    
    <div class="col-12">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">사용자</h3>
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGrid" style="height:320px"  data-grid-id="grdList" data-pagenation="Y" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/user/user.xml'/>">
                </div>
            </div>

        </div>
        <!-- /.card -->
    </div>
    
    <div class="col-12">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">상세정보</h3>
                
                <div class="card-tools">
                    <div class="input-group input-group-sm">
                     <div class="btn-group"> 
                        <button type="button" data-grid-id="grdList" data-click="btnClick" id="btnRestPwd" class="btn btn-danger btn-sm"><i class="fas fa-save"></i> 암호초기화</button>
                        <button type="button" data-grid-id="grdList" data-click="btnClick" id="btnGroup" class="btn btn-info btn-sm"><i class="fas fa-save"></i> 그룹</button>
                        
                        <button type="button" data-grid-id="grdList" onclick="excelDownload(this.id)" id="btnExcel" class="btn btn-default btn-sm"><i class="fas fa-download"></i> 엑셀다운로드</button>
                        <button type="button" data-grid-id="grdList" data-click="newUser" id="btnAddList" class="btn btn-success btn-sm"><i class="fas fa-plus"></i>추가</button>
                        <button type="button" data-grid-id="grdList" data-click="saveUser" id="btnSaveList"  class="btn btn-info btn-sm"><i class="fas fa-save"></i> 저장</button>
                        <button type="button" data-grid-id="grdList" data-click="removeUser" id="btnDelList" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> 삭제</button>
                     </div>
                    </div>
                </div>
                <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <form class="form-horizontal" id="frmMain">
                    <input type="hidden" name="empNo" id="empNo" value="${user.empNo }" />
                    <div class="card-body">
                     <div class="form-group row">
                        <label for="userNm" class="col-1 col-form-label right">사용자명</label>
                        <div class="col-2">
                          <input type="text" required="required" value='<c:out value="${user.userNm }"/>'  class="form-control" id="userNm" name="userNm" placeholder="사용자명">
                        </div>
                        
                        <label for="userId" class="col-1 col-form-label right">로그인아이디</label>
                        <div class="input-group col-2">
                          <input type="hidden" id="chkId" />
                          <input required="required" type="text" data-change="resetCheckId" class="form-control" value='<c:out value="${user.userId }" />' id="userId" name="userId" placeholder="로그인아이디">
                          <div class="input-group-append">
                            <span class="input-group-text" id='checkDupl' data-click="checkDupl"><i class="fas fa-check"></i> 중복체크</span>
                          </div>
                        </div>
                        
                        <label for="pwd" class="col-1 col-form-label right">암호</label>
                        <div class="col-2">
                          <input type="password" autocomplete="false" required="required" maxLength="16" class="js-mytooltip-pw form-control" id="pwd" name="pwd" placeholder="암호" 
                          data-mytooltip-direction="bottom"
                          data-mytooltip-dinamic-content="true" 
                          data-mytooltip-action="focus" 
                          data-mytooltip-animate-duration="0"/>
                        </div>
                        
                        <label for="pwdCfg" class="col-1 col-form-label right">암호확인</label>
                        <div class="col-2">
                          <input type="password" autocomplete="false" required="required" maxLength="16" class="form-control" id="pwdCfg" name="pwdCfg" placeholder="암호확인">
                        </div>
                      </div>
                      
                      <div class="form-group row" >
                        <label for="emlAdr" class="col-1 col-form-label right">이메일</label>
                        <div class="col-2">
                          <input type="email" value='<c:out value="${user.emlAdr }"/>' required class="form-control" id="emlAdr" name="emlAdr"  placeholder="이메일">
                        </div>
                        
                        <%-- <label for="mobileNo" class="col-1 col-form-label right">전화</label>
                        <div class="col-2">
                          <input type="text" value='<c:out value="${user.mobileNo }"/>' required class="form-control" id="mobileNo" name="mobileNo"  placeholder="전화">
                        </div> --%>
                        
                        <label for="langGb" class="col-1 col-form-label right">사용언어</label>
                        <div class="col-2">
                          <code:commCode className="form-control" id="langGb" name="langGb" mstCd ="LANG_GB" selectedValue="${langGb }"/>
                        </div>
                        
                        <label for="niceAuthKey" class="col-1 col-form-label right">본인인증키</label>
                        <div class="col-2">
                          <input type="text" value='<c:out value="${user.niceAuthKey }"/>' required class="form-control" id="niceAuthKey" name="niceAuthKey"  placeholder="본인인증키">
                        </div>
                        
                        <label for="lastLgiDt" class="col-1 col-form-label right">최종접속일</label>
                        <div class="col-2">
                          <input type="text" value='<c:out value="${user.lastLgiDt }"/>' required class="form-control" id="lastLgiDt" name="lastLgiDt" readonly>
                        </div>
                      </div>
                      
                      <div class="form-group row" >
                        <%-- <label for="lastLoginDt" class="col-1 col-form-label right">최종접속일</label>
                        <div class="col-2">
                          <input type="text" value='<c:out value="${user.lastLoginDt }"/>' required class="form-control" id="lastLoginDt" name="lastLoginDt" readonly>
                        </div> --%>
                        
                        <label for="emlAuthYn" class="col-1 col-form-label right">이메일인증여부</label>
                        <div class="col-2">
                          <code:commCode className="form-control" id="emlAuthYn" name="emlAuthYn" mstCd ="YN" selectedValue="${emlAuthYn }"/>
                        </div>
                        
                        <label for="hpAuthYn" class="col-1 col-form-label right">휴대폰인증여부</label>
                        <div class="col-2">
                          <code:commCode className="form-control" id="hpAuthYn" name="hpAuthYn" mstCd ="YN" selectedValue="${hpAuthYn }"/>
                        </div>
                        
                        <label for="delYn" class="col-1 col-form-label right">탈퇴여부</label>
                        <div class="col-2">
                          <code:commCode className="form-control" id="delYn" name="delYn" mstCd ="YN" selectedValue="${delYn }"/>
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
var formData = ""; // Form Data

function excelDownload(id){
	grdList.exportExcel("사용자.xlsx");
}
function searchUser(){
	$("form#frmSearch").find("#currentPageNo").val(1);
    search();
}
function newUser(element,data){
	// var url = "<c:url value='/${currentMenuId}/add${urlSuffix}'/>";
    // Utilities.windowOpen(url,"addUser",900,550);
    $("#frmMain")[0].reset(); // form reset
}
function search()
{
    var url = "<c:url value='/${currentMenuId}/getList${urlSuffix}'/>";
    var param = Utilities.formToMap("frmSearch");
    grdList.loadUrl(url,param);
    formData = ""; // 조회시 변수 초기화
}
function grdList_rowChanged(grdList,oldRow,newRow,rowData){
	if(newRow > -1){
        formData = Utilities.setRowData("frmMain", formData, grdList, oldRow, rowData); // 조회된 rowData를 입력한 form에 자동 세팅, formData 변수에 해당 form 데이터 세팅
        $("#chkId").val($("#userId").val());
    }
}

function grdList_pageMove(gridView,pageNo){
    $("form#frmSearch").find("#currentPageNo").val(pageNo);
    search();
}
function saveUser(){
    // var saveJson = grdList.getSaveJson();
    var param = Utilities.formToMap("frmMain");
    if(param.userLevel==1)
    {
        param.rootUserId = param.userId;
        param.upUserId = "";
    }
            
    if(!validate(param))
        return;
    var url = "<c:url value='/${currentMenuId}/addUser${urlSuffix}'/>";
    Utilities.getAjax(url,param,true,function(data,jqXHR){
        if(Utilities.processResult(data,jqXHR,"저장에 실패했습니다."))
        {
            alert("저장에 성공했습니다.");
            addUser(data.result);
        }
    });
}
function resetCheckId(){
    $("#chkId").val("");
}
function checkDupl(){
    var id = $("#userId").val();
    if(!id){
        alert("아이디를 입력해 주세요");
        return false;
    }
    var param = {
            userId: id
    };
    var url = "<c:url value='/${currentMenuId}/getUser${urlSuffix}'/>";
    Utilities.getAjax(url,param,true,function(data,result){
        if(data && data.userId == id)
        {
            alert("이미 사용중인 아이디 입니다.");
            return false;
        }
        alert("사용할 수 있는 아이디 입니다.");
        $("#chkId").val(id);
    });
}


function validate(param){
    if(!param.userId){
        alert("사용자 아이디는 필수 입니다.");
        $("#userId").focus();
        return false;
    }
    if($("#chkId").val() != $("#userId").val()){ 
        alert("아이디 중복체크를 먼저 해주세요");
        $("#checkDupl").focus();
        return false;
    }
     
    if(!param.userNm){
        alert("사용자 명은 필수 입니다.");
        $("#userNm").focus();
        return false;
    }
    if(!param.pwd && !param.empNo){
        alert("암호를 입력해 주세요");
        $("#pwd").focus();
        return false;
    }
    if(!Utilities.validatePassword(param.pwd) && !param.empNo)
    {
        alert("암호복잡도가 맞지 않습니다.");
        $("#pwd").focus();
        return false;
    }
    if(param.pwd != param.pwdCfg){
        alert("암호가 동일하지 않습니다.");
        $("#pwdCfg").focus();
        return false;
    }
    return true;
}

function addUser(data){
    if(!data)
        return;
    data.stat = "n";
    grdList.addRow(data);
}
function removeUser(){
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
        var url = "<c:url value='/${currentMenuId}/removeUserList${urlSuffix}'/>";
        Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
            if(Utilities.processResult(data,jqXHR,"사용자 삭제에 실패했습니다."))
            {
                alert("사용자 삭제에  성공했습니다.");
                grdList.removeCheckRow();
            }
        });
    }
    else{
        alert("선택된 데이터가 없습니다.");
    }
}

function grdList_btnGroup_buttonClicked(gridView,row,col,json){
	var url = "<c:url value='/${currentMenuId}/group/groupPopup${urlSuffix}'/>?userId="+json.userId;
    Utilities.windowOpen(url,"groupPopup",500,580);
}
// function grdList_btnRestPwd_buttonClicked(gridView,row,col,json){
function btnClick(element, data, rowIndex, rowData, id){
    
	if(rowIndex.itemIndex > -1){
		// 선택된 행이 있을 경우
		var userId = rowData.userId;
	    
	    switch (id){
	       case "btnRestPwd" : 
	           // 비밀번호 초기화
	           
	            var param = {
	                    userId : userId
	            };
	            
	            var url = "<c:url value='/${currentMenuId}/user/resetPassword${urlSuffix}'/>";
	            Utilities.getAjax(url,param,true,function(data,jqXHR){
	                if(Utilities.processResult(data,jqXHR,"암호초기화에 실패했습니다."))
	                {
	                    alert("암호초기화에  성공했습니다.");
	                    grdList.removeCheckRow();
	                }
	            });
	            
	            break;
	            
	       case "btnGroup" :
	           var url = "<c:url value='/${currentMenuId}/group/groupPopup${urlSuffix}'/>?userId="+userId;
	           Utilities.windowOpen(url,"groupPopup",500,580);
	           
	           break;
	            
	        default : 
	            break;
	    }
	}else {
		alert("행을 선택해주세요.");
		return false;
	}
	
	
	
}

// function grdList_secedeUser_ButtonClicked(gridView,row,col){
//     alert(col);
// }
// function saveGroupChecked(userId,saveJson){
function saveGroupChecked(userId,saveJson){
	var url = "<c:url value='/${currentMenuId}/group/setUserGroup${urlSuffix}'/>?userId="+ userId;
	
    Utilities.getAjax(url,saveJson,true,function(data,jqXHR){
        if(Utilities.processResult(data,jqXHR,"그룹 설정에 실패했습니다."))
        {
            alert("그룹 설정에  성공했습니다.");
            
        }
    });
}

$(document).ready(function(){
    $("#pwd").off("focus").on("focus", function() {
        var value = $(this).val();
        $('.js-mytooltip-pw').myTooltip('updateContent', Utilities.validatePasswordMsg(value));
    });
    $("#pwd").off("click").on("click", function() {
        var value = $(this).val();
        if (!Utilities.isEmpty(value)) {
            $('.js-mytooltip-pw').myTooltip('updateContent', Utilities.validatePasswordMsg(value));
        }
    });
    $("#pwd").off("keyup").on("keyup", function() {
        $("#pwd").blur();
        $("#pwd").focus();
    });
    
    $('.js-mytooltip-pw').myTooltip({
        'offset' : 30,
        'theme' : 'light',
        'customClass' : 'mytooltip-content',
        'content' : '<p>t</p>'
    });
});

</script>