<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%>
<div class="card card-info">
              <div class="card-header">
                <h3 class="card-title">다국어 등록</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form class="form-horizontal" id="frmMain">
                <div class="card-body">

                 <div class="form-group row">
                    <label for="gubnCd" class="col-2 col-form-label right">다국어 구분</label>
                    <div class="col-10">
                    <!-- 
                      <code:commCode className="form-control" prefixValue="" prefixLabel="" id="gubnCd" name="gubnCd" codeLevel="3" rootCodeId="050" upCodeId="020" selectedValue=""/>
                       -->
                    </div>
                  </div>
                  
                  <div class="form-group row">
                    <label for="langNm" class="col-2 col-form-label right">다국어</label>
                    <div class="col-10">
                      <input type="text" autocomplete="false" required="required" maxLength="300" class="form-control" id="langNm" name="langNm" placeholder="다국어">
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
            	var url = "<c:url value='/${currentMenuId}/addLang${urlSuffix}'/>";
            	
            	Utilities.getAjax(url,param,true,function(data,jqXHR){
            		if(Utilities.processResult(data,jqXHR,"다국어 추가해 실패하였습니다."))
            		{
            			alert("다국어 추가에 성공했습니다.");
            			console.log(data.result)
            			opener.addLang(data.result);
            			top.close();
            		}
            	});
            });
            
            function validate(param){
            	if(!param.langNm){
            		alert("다국어는 필수입력입니다.");
            		$("#langNm").focus();
            		return false;
            	}
            	return true;
            }
            
            $(document).ready(function(){
            	
            	
            });
            </script>