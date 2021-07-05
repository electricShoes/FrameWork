<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%>
<div class="card card-info">
              <div class="card-header">
                <h3 class="card-title">공통코드 상세정보</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form class="form-horizontal" id="frmMain">
                <div class="card-body">
                    
                  <div class="form-group row">
                    <label for="mstCd" class="col-2 col-form-label right">코드ID</label>
                    <div class="col-10">
                      <input required="required" type="text"  value='<c:out value="${mstCd}"/>' class="form-control" id="mstCd" name="mstCd" placeholder="코드ID">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="dtlCd" class="col-2 col-form-label right">상세코드ID</label>
                    <div class="col-10">
                      <input required="required" type="text" class="form-control" id="dtlCd" name="dtlCd" placeholder="상세코드ID">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="dtlNm" class="col-2 col-form-label right">상세코드명</label>
                    <div class="col-10">
                      <input type="text" required="required" class="form-control" id="dtlNm" name="dtlNm" placeholder="상세코드명">
                    </div>
                  </div>
                  
                  <div class="form-group row">
                    <label for="useYn" class="col-2 col-form-label right">사용여부</label>
                    <div class="col-10">
                        <code:commCode className="form-control" prefixValue="" prefixLabel="전체" id="useYn" name="useYn" mstCd ="USE_YN"/>
                    </div>
                  </div>
                  
                  <div class="form-group row">
                    <label for="refCd1" class="col-2 col-form-label right">참조필드1</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refCd1}"/>'  class="form-control" id="refCd1" name="refCd1"  placeholder="참조필드1">
                    </div>
                    <label for="refCd2" class="col-2 col-form-label right">참조필드2</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refCd2}"/>'  class="form-control" id="refCd2" name="refCd2"  placeholder="참조필드2">
                    </div>
                    <label for="refCd3" class="col-2 col-form-label right">참조필드3</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refCd3}"/>'  class="form-control" id="refCd3" name="refCd3"  placeholder="참조필드3">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="refCd4" class="col-2 col-form-label right">참조필드4</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refCd4}"/>'  class="form-control" id="refCd4" name="refCd4"  placeholder="참조필드4">
                    </div>
                     <label for="refCd5" class="col-2 col-form-label right">참조필드5</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refCd5}"/>'  class="form-control" id="refCd5" name="refCd5"  placeholder="참조필드5">
                    </div>
                  </div>
                  
                  <div class="form-group row">
                    <label for="refNum1" class="col-2 col-form-label right">참조숫자1</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refNum1 }"/>'  class="form-control" id="refNum1" name="refNum1"  placeholder="참조숫자1">
                    </div>
                    <label for="refNum2" class="col-2 col-form-label right">참조숫자2</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refNum2 }"/>'  class="form-control" id="refNum2" name="refNum2"  placeholder="참조숫자2">
                    </div>
                    <label for="refNum3" class="col-2 col-form-label right">참조숫자3</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refNum3 }"/>'  class="form-control" id="refNum3" name="refNum3"  placeholder="참조숫자3">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="refNum4" class="col-2 col-form-label right">참조숫자명4</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refNum4 }"/>'  class="form-control" id="refNum4" name="refNum4"  placeholder="참조숫자4">
                    </div>
                     <label for="refNum5" class="col-2 col-form-label right">참조숫자명5</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refNum5 }"/>'  class="form-control" id="refNum5" name="refNum5"  placeholder="참조숫자5">
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
            	            	
            	if(!validate(param))
            		return;
            	var url = "<c:url value='/${currentMenuId}/addCodeDetail${urlSuffix}'/>";
            	Utilities.getAjax(url,param,true,function(data,jqXHR){
            		if(Utilities.processResult(data,jqXHR,"코드 추가에 실패했습니다."))
            		{
            			alert("코드추가에 성공했습니다.");
            			opener.addCode(data.result);
            			top.close();
            		}
            	});
            });
            function validate(param){
            	if(!param.dtlCd){
            		alert("코드 아이디는 필수 입니다.");
            		$("#mstCd").focus();
            		return false;
            	}
            	if(!param.dtlNm){
                    alert("코드명은 필수 입니다.");
                    $("#mstNm").focus();
                    return false;
                }

            	return true;
            }
            </script>