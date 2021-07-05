<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%>
<div class="card card-info">
              <div class="card-header">
                <%-- <h3 class="card-title">메뉴 정보${menuId }</h3> --%>
                <h3 class="card-title">메뉴 정보</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form class="form-horizontal" id="frmMain">
                <div class="card-body">
                  <div class="form-group row">
                    <label for="menuSeq" class="col-2 col-form-label right">시스템</label>
                    <div class="col-10">
                      <%-- <input type="hidden"  value='<c:out value="${rootMenuId }"/>' readonly="readonly"  class="form-control" id="rootMenuId" name="rootMenuId"  placeholder="시스템메뉴"> --%>
                      <input type="hidden"  value='<c:out value="${upMenuId }"/>' readonly="readonly"  class="form-control" id="upMenuId" name="upMenuId"  placeholder="상위메뉴" />
                      <code:commCode className="form-control" id="rootMenuId" name="rootMenuId" mstCd ="SYSTEM_GB" selectedValue="${rootMenuId }"/>
                      
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="menuId" class="col-2 col-form-label right">메뉴아이디</label>
                    <div class="col-10">
                      <input required="required" readonly="readonly" type="text" class="form-control" value='<c:out value="${menuId }"/>' id="menuId" name="menuId" placeholder="menuId">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="menuNm" class="col-2 col-form-label right">메뉴명</label>
                    <div class="col-10">
                      <input type="text" required="required" value='<c:out value="${menuNm }"/>'  class="form-control" id="menuNm" name="menuNm" placeholder="메뉴명">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="menuUrl" class="col-2 col-form-label right">주소</label>
                    <div class="col-10">
                      <input type="text" value='<c:out value="${menuUrl }"/>'  class="form-control" id="menuUrl" name="menuUrl" placeholder="메뉴주소">
                    </div>
                  </div>
                   <div class="form-group row">
                    <label for="menuLevel" class="col-2 col-form-label right">레벨</label>
                    <div class="col-10">
                        <input type="text" readonly="readonly" required="required" value='<c:out value="${menuLevel }"/>'  class="form-control" id="menuLevel" name="menuLevel" placeholder="메뉴레벨">
                    </div>
                  </div>
                  <div class="form-group row" >
                    <label for="menuSeq" class="col-2 col-form-label right">순번</label>
                    <div class="col-10">
                      <input type="text" value='<c:out value="${menuSeq }"/>' required class="form-control" id="menuSeq" name="menuSeq"  placeholder="정렬순번">
                    </div>
                  </div>
                  <div class="form-group row" >
                    <label for="menuIcon" class="col-2 col-form-label right">메뉴아이콘</label>
                    <div class="col-10">
                      <input type="text" value='<c:out value="${menuIcon }"/>' required class="form-control" id="menuIcon" name="menuIcon"  placeholder="메뉴아이콘">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="menuShowYn" class="col-2 col-form-label right">메뉴보이기</label>
                    <div class="col-10">
                        <%-- <code:commCode className="form-control"  id="menuShowYn" name="menuShowYn" codeLevel="3" rootCodeId="010" upCodeId="010" selectedValue="${menuShowYn}"/> --%>
                        <code:commCode className="form-control" id="menuShowYn" name="menuShowYn" mstCd ="YN" selectedValue="${menuShowYn}"/>
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="menuAuthYn" class="col-2 col-form-label right">권한체크</label>
                    <div class="col-10">
                        <%-- <code:commCode className="form-control"  id="menuAuthYn" name="menuAuthYn" codeLevel="3" rootCodeId="010" upCodeId="010" selectedValue="${menuAuthYn}"/> --%>
                        <code:commCode className="form-control" id="menuAuthYn" name="menuAuthYn" mstCd ="YN" selectedValue="${menuAuthYn}"/>
                    </div>
                  </div>
                  <%-- <div class="form-group row">
                    <label for="sqlLogYn" class="col-2 col-form-label right">로그사용</label>
                    <div class="col-10">
                        <code:commCode className="form-control"  id="sqlLogYn" name="sqlLogYn" codeLevel="3" rootCodeId="010" upCodeId="010" selectedValue="${sqlLogYn}"/>
                    </div>
                  </div> --%>
                  <div class="form-group row">
                    <label for="codeSort" class="col-2 col-form-label right">사용여부</label>
                    <div class="col-10">
                        <code:commCode className="form-control" id="useYn" name="useYn" mstCd ="YN" selectedValue="${useYn}"/>
                    </div>
                  </div>
                  </div>
                <!-- /.card-body -->
                <div class="card-footer">
                  <a id="btnCancel" href="#;" class="btn btn-default float-right"><i class="fas fa-ban"></i> 닫기</a>
                  <a id="btnSave" href="#;" class="btn btn-info float-right"><i class="fas fa-save"></i> 저장</a>
                </div>
                <!-- /.card-footer -->
              </form>
            </div>
            <script>
            $("#btnCancel").click(function(){
            	top.close();
            });
            $("#btnSave").click(function(){
            	
            	var param = Utilities.formToMap("frmMain");
            	if(param.menuLevel==1)
            	{
            		param.rootMenuId = param.menuId;
            		param.upMenuId = "";
            	}
            	
            	if(!validate(param))
            		return;
            	// var url = "<c:url value='/${currentMenuId}/addMenu${urlSuffix}'/>";
            	var url = "<c:url value='/goMenuMng/addMenu${urlSuffix}'/>";
            	Utilities.getAjax(url,param,true,function(data,jqXHR){
            		if(Utilities.processResult(data,jqXHR,"메뉴 추가에 실패했습니다."))
            		{
            			alert("메뉴추가에 성공했습니다.");
            			opener.addMenu(data.result);
            			top.close();
            		}
            	});
            });
            function validate(param){
            	if(!param.menuId){
            		alert("메뉴 아이디는 필수 입니다.");
            		$("#menuId").focus();
            		return false;
            	}
            	if(!param.menuNm){
                    alert("메뉴 명은 필수 입니다.");
                    $("#menuNm").focus();
                    return false;
                }
//             	if(!param.menuLevel){
//                     alert("메뉴레벨은 필수 입니다.");
//                     $("#menuLevel").focus();
//                     return false;
//                 }
//             	if(!Utilities.isNumberOnly(param.menuLevel)){
//             		alert("메뉴레벨은 숫자 형식이어야합니다.");
//                     $("#menuLevel").focus();
//                     return false;
//             	}
            	if(!param.menuSeq){
                    alert("순번은 필수 입니다.");
                    $("#menuSeq").focus();
                    return false;
                }
                if(!Utilities.isNumberOnly(param.menuSeq)){
                    alert("순번은 숫자 형식이어야합니다.");
                    $("#menuSeq").focus();
                    return false;
                }
            	
            	return true;
            }
            </script>