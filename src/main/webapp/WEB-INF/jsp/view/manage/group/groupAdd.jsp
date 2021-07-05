<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%>
<div class="card card-info">
              <div class="card-header">
                <h3 class="card-title">그룹 정보</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form class="form-horizontal" id="frmMain">
                <div class="card-body">
                 <div class="form-group row">
                    <label for="grpId" class="col-2 col-form-label right">그룹아이디</label>
                    <div class="col-10">
                      <input type="text" required="required" value='<c:out value="${group.grpId }"/>'  class="form-control" id="grpId" name="grpId" placeholder="그룹아이디">
                    </div>
                  </div>
                  
                  <div class="form-group row">
                    <label for="grpNm" class="col-2 col-form-label right">그룹명</label>
                    <div class="input-group col-10">
                      <input required="required" type="text" data-change="resetCheckId" class="form-control" value='<c:out value="${group.grpNm }" />' id="grpNm" name="grpNm" placeholder="그룹명">
                    </div>
                  </div>
                </div>  
                <!-- /.card-body -->
                <div class="card-footer">
                  <a id="btnCancel" href="#;"  class="btn btn-default float-right"><i class="fas fa-ban"></i> 닫기</a>
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
                var url = "<c:url value='/0102010200/addGroup${urlSuffix}'/>";
                Utilities.getAjax(url,param,true,function(data,jqXHR){
                    if(Utilities.processResult(data,jqXHR,"그룹 추가에 실패했습니다."))
                    {
                        alert("그룹추가에 성공했습니다.");
                        opener.addGroup(data.result);
                        top.close();
                    }
                });
            });
            function validate(param){
                if(!param.grpId){
                    alert("그룹 아이디는 필수 입니다.");
                    $("#grpId").focus();
                    return false;
                }
                  if(!param.grpNm){
                    alert("그룹 명은 필수 입니다.");
                    $("#grpNm").focus();
                    return false;
                }
                
                return true;
            }
            
            $(document).ready(function(){
                
            });
            </script>