<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%> 
<!-- /.card -->
<!-- Horizontal Form -->
<%-- <div class="card">
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
                        <label for="userNm" class="col-1 col-form-label right">변수명</label>
                        <div class="col-2">
                          <input type="text" id="varCd" data-enter="search" name="varCd" class="form-control" placeholder="변수명" />
                        </div>
                        
                        <label for="value" class="col-1 col-form-label right">변수값</label>
                        <div class="col-2">
                          <input type="text" id="varVal" data-enter="search" name="varVal" class="form-control" placeholder="변수값" />
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
</div> --%>
<!-- /.card -->

<div class="row">
    <div class="col-12">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">SAP</h3>

                <div class="card-tools">
                    <div class="input-group input-group-sm">
                     <div class="btn-group"> 
                        <button type="button" onclick="btn(this.id)" id="btn1" class="btn btn-success btn-sm"><i class="fas fa-plus"></i> 버튼1</button>
                        <button type="button" onclick="btn(this.id)" id="btn2"  class="btn btn-info btn-sm"><i class="fas fa-save"></i> 버튼2</button>
                     </div>
                    </div>
                </div>
                <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div class="card-body">
                </div>
            </div>

        </div>
        <!-- /.card -->
    </div>
    <!-- /.col -->

</div>
<!-- /.row -->
<script>
function btn(id) {
	// alert($(this).id)
	alert(id)
}

</script>