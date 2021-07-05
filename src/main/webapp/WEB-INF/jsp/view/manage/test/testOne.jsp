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
        
            <div class=" row">
                <div class="col-10">
                   
                
                
                
                       <button type="button" id="btnSearch" class="btn btn-primary float-right"><i class="fas fa-search"></i>엑셀 </button>
                       <button type="button" id="btnInsert" class="btn btn-primary float-right"><i class="fas fa-search"></i>삭제요청 </button>
                       <button type="button" id="btnAllInsert" class="btn btn-primary float-right"><i class="fas fa-search"></i>승인요청 </button>
                       <button type="button" id="btnAdd" class="btn btn-primary float-right"><i class="fas fa-search"></i>배송처 </button>
                       <button type="button" id="btnCom" class="btn btn-primary float-right"><i class="fas fa-search"></i>일괄등록 </button>
                       <button type="button" id="btnDel" class="btn btn-primary float-right"><i class="fas fa-search"></i>등록 </button>
                       <button type="button" id="btnExel" class="btn btn-primary float-right"><i class="fas fa-search"></i>조회 </button>
                
               </div>
            </div>
     

        <!-- /.card-body -->
    </div>
    <div class="card-body">
     <div class=" row">
                <div class="col-10">
                    <div class="form-group row">
                        <label for="srchUserNm" class="col-1 col-form-label right">거래처명 </label>
                        <div class="col-2">
                          <input type="text" id="srchUserNm" data-enter="search" name="userNm" class="form-control" placeholder="거래처명 " />
                        </div>
                        
                        <label for="srchUserId" class="col-1 col-form-label right">대표자성명 </label>
                        <div class="col-2">
                          <input type="text" id="srchUserId" data-enter="search" name="userId" class="form-control" placeholder="대표자성명 " />
                        </div>
                        
                        <label for="srchUserId" class="col-1 col-form-label right">사업자번호 </label>
                        <div class="col-2">
                          <input type="text" id="srchUserId" data-enter="search" name="userId" class="form-control" placeholder="사업자번호 " />
                        </div>
                        
                        <label for="srchUserId" class="col-1 col-form-label right"> </label>
                        <div class="col-2">
                         
                        </div>
                        
                        <label for="srchUserId" class="col-1 col-form-label right">등록일자 </label>
                        <div class="col-2">
                          <input type="text" id="srchUserId" data-enter="search" name="userId" class="form-control" placeholder="등록일자  " />
                        </div>
                        
                        <label for="srchUserId" class="col-1 col-form-label right">등록자 </label>
                        <div class="col-2">
                          <input type="text" id="srchUserId" data-enter="search" name="userId" class="form-control" placeholder="등록자 " />
                        </div>
                        
                        <label for="srchUserId" class="col-1 col-form-label right">상태 </label>
                        <div class="col-2">
                          <input type="text" id="srchUserId" data-enter="search" name="userId" class="form-control" placeholder="상태 " />
                        </div>
                        
                        <label for="srchUserId" class="col-1 col-form-label right"> </label>
                        <div class="col-2">
                          </div>
                    </div>
                </div>
            </div>
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
                <h3 class="card-title">고객마스터</h3>
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGrid" style="height:320px"  data-grid-id="grdList" data-pagenation="Y" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/test/test.xml'/>">
                </div>
            </div>

        </div>
        <!-- /.card -->
    </div>
    
    
</div>
<!-- /.row -->
<script>


</script>