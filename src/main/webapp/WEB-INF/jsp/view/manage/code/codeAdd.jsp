<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%>
<div class="card card-info">
              <div class="card-header">
                <h3 class="card-title">공통코드 정보</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form class="form-horizontal" id="frmMain">
                <div class="card-body">
                    
                  <div class="form-group row">
                    <label for="mstCd" class="col-2 col-form-label right">코드ID</label>
                    <div class="col-10">
                      <input required="required" type="text" class="form-control" id="mstCd" name="mstCd" placeholder="코드ID">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="mstNm" class="col-2 col-form-label right">코드명</label>
                    <div class="col-10">
                      <input type="text" required="required" class="form-control" id="mstNm" name="mstNm" placeholder="코드명">
                    </div>
                  </div>
                  
                  <div class="form-group row">
                    <label for="useYn" class="col-2 col-form-label right">사용여부</label>
                    <div class="col-10">
                        <code:commCode className="form-control" prefixValue="" prefixLabel="전체" id="useYn" name="useYn" mstCd ="USE_YN"/>
                    </div>
                  </div>
                  
                  <div class="form-group row">
                    <label for="refFldNm1" class="col-2 col-form-label right">참조필드명1</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refFldNm1}"/>'  class="form-control" id="refFldNm1" name="refFldNm1"  placeholder="참조필드명1">
                    </div>
                    <label for="refFldNm2" class="col-2 col-form-label right">참조필드명2</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refFldNm2}"/>'  class="form-control" id="refFldNm2" name="refFldNm2"  placeholder="참조필드명2">
                    </div>
                    <label for="refFldNm3" class="col-2 col-form-label right">참조필드명3</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refFldNm3}"/>'  class="form-control" id="refFldNm3" name="refFldNm3"  placeholder="참조필드명3">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="refFldNm4" class="col-2 col-form-label right">참조필드명4</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refFldNm4}"/>'  class="form-control" id="refFldNm4" name="refFldNm4"  placeholder="참조필드명4">
                    </div>
                     <label for="refFldNm5" class="col-2 col-form-label right">참조필드명5</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refFldNm5}"/>'  class="form-control" id="refFldNm5" name="refFldNm5"  placeholder="참조필드명5">
                    </div>
                  </div>
                  
                  <div class="form-group row">
                    <label for="refNumNm1" class="col-2 col-form-label right">참조숫자명1</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refNumNm1 }"/>'  class="form-control" id="refNumNm1" name="refNumNm1"  placeholder="참조숫자명1">
                    </div>
                    <label for="refNumNm2" class="col-2 col-form-label right">참조숫자명2</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refNumNm2 }"/>'  class="form-control" id="refNumNm2" name="refNumNm2"  placeholder="참조숫자명2">
                    </div>
                    <label for="refNumNm3" class="col-2 col-form-label right">참조숫자명3</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refNumNm3 }"/>'  class="form-control" id="refNumNm3" name="refNumNm3"  placeholder="참조숫자명3">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="refNumNm4" class="col-2 col-form-label right">참조숫자명4</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refNumNm4 }"/>'  class="form-control" id="refNumNm4" name="refNumNm4"  placeholder="참조숫자명4">
                    </div>
                     <label for="refNumNm5" class="col-2 col-form-label right">참조숫자명5</label>
                    <div class="col-2">
                      <input type="text" value='<c:out value="${refNumNm5 }"/>'  class="form-control" id="refNumNm5" name="refNumNm5"  placeholder="참조숫자명5">
                    </div>
                  </div>
                </div>
                
                
                <!-- /.card-body -->
                <div class="card-footer">
                  <a id="btnCancel" href="#;" class="btn btn-default float-right"><i class="fas fa-ban"></i> 닫기</a>
                  <a id="btnSave" class="btn btn-info float-right"><i class="fas fa-save"></i> 저장</a>
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
            	var url = "<c:url value='/${currentMenuId}/addCode${urlSuffix}'/>";
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
            	if(!param.mstCd){
            		alert("코드 아이디는 필수 입니다.");
            		$("#mstCd").focus();
            		return false;
            	}
            	if(!param.mstNm){
                    alert("코드명은 필수 입니다.");
                    $("#mstNm").focus();
                    return false;
                }

            	return true;
            }
            </script>