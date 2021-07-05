<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%>
<div class="card card-info">
              <div class="card-header">
                <h3 class="card-title">배치 정보</h3>
              </div>
              <!-- /.card-header --> 
              <!-- form start -->
              <form class="form-horizontal" id="frmMain">
                <div class="card-body">
                <div class="form-group row">
                    <label for="batchId" class="col-2 col-form-label right">배치아이디</label>
                    <div class="col-10">
                      <input type="text" required="required" value='<c:out value="${batch.batId }"/>'  class="form-control" id="batId" name="batId" placeholder="배치아이디">
                    </div>
                  </div>
                 <div class="form-group row b">
                    <label for="batName" class="col-2 col-form-label right">배치명</label>
                    <div class="col-10">
                      <input type="text" required="required" value='<c:out value="${batch.batName }"/>'  class="form-control" id="batName" name="batName" placeholder="배치명">
                    </div>
                  </div>
                   <div class="form-group row">
                    <label for="batObjType" class="col-2 col-form-label right">배치종류</label>
                    <div class="col-10">
                    	<!-- 
                      <code:commCode className="form-control" id="batObjType" name="batObjType" codeLevel="2" rootCodeId="040" upCodeId="040" selectedValue="${batch.batObjType}"/>
                       -->
                    </div>
                  </div>
                  <div class="form-group row">
                    <label for="batObjName" class="col-2 col-form-label right">실행명</label>
                    <div class="col-10">
                      <input type="text" required="required" value='<c:out value="${batch.batObjName}"/>'  class="form-control" id="batObjName" name="batObjName" placeholder="실행명">
                    </div>
                  </div>
                  
                  <div class="form-group row" >
                    <label for="batCron" class="col-2 col-form-label right">Cron</label>
                    <div class="col-10">
                      <input type="text" value='<c:out value="${batch.batCron }"/>' required class="form-control" id="batCron" name="batCron"  placeholder="Cron express">
                    </div>
                  </div>
                  <div class="form-group row" >
                    <label for="batDesc" class="col-2 col-form-label right">설명</label>
                    <div class="col-10">
                      <input type="text" value='<c:out value="${batch.batDesc }"/>' required class="form-control" id="batDesc" name="batDesc"  placeholder="설명">
                    </div>
                  </div>
                </div>  
                 <div class="form-group row">
                    <label for="useYn" class="col-2 col-form-label right">사용여부</label>
                    <div class="col-10">
                    	<!-- 
                        <code:commCode className="form-control"  id="useYn" name="useYn" codeLevel="3" rootCodeId="010" upCodeId="010" selectedValue="${ useYn}"/>
                         -->
                    </div>
                  </div>
                <!-- /.card-body -->
                <div class="card-footer">
                  <a id="btnCancel" href="#;" data-click="close" class="btn btn-default float-right"><i class="fas fa-ban"></i> 닫기</a>
                  <a id="btnSave" href="#;" data-click="save" class="btn btn-info float-right"><i class="fas fa-save"></i> 저장</a>
                </div>
                <!-- /.card-footer -->
              </form>
            </div>
            <script>
            function save(){
                var param = Utilities.formToMap("frmMain");
                
                if(!validate(param))
                    return;
                var url = "<c:url value='/${currentMenuId}/addBatch${urlSuffix}'/>";
                Utilities.getAjax(url,param,true,function(data,jqXHR){
                    if(Utilities.processResult(data,jqXHR,"배치 추가에 실패했습니다."))
                    {
                        alert("배치추가에 성공했습니다.");
                        opener.addBatch(data.result);
                        top.close();
                    }
                });
            }
            function validate(param){
            	if(!param.batId){
            		alert("배치 아이디는 필수 입니다.");
            		$("#batchId").focus();
            		return false;
            	}
            	
            	if(!param.batName){
                    alert("배치명은 필수 입니다.");
                    $("#batchName").focus();
                    return false;
                }
            	if(!param.batObjType){
                    alert("배치종류는 필수 입니다.");
                    $("#batObjType").focus();
                    return false;
                }
            	if(!param.batObjName){
                    alert("실행명은 필수 입니다.");
                    $("#batObjName").focus();
                    return false;
                }
            	if(!param.batCron){
                    alert("Cron은 필수 입니다.");
                    $("#batCron").focus();
                    return false;
                }
            	return true;
            }
            
            $(document).ready(function(){
            	
            	
            });
            </script>