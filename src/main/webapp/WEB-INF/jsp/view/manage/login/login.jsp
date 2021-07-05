<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%>
<body class="hold-transition login-page">
<div class="login-box ">
    <div class="logobox">
        <div class="logo">
            <!-- <img src="/template/AdminLTE-3.0.5/dist/img/logo.png" alt=""> -->
        </div>
         <div class="login-logo ">    MWRS </div>
         <p class=""> MARINE WASTE READING SYSTEM</p>
    </div>
    
  <div class="login">
    <div class="login-card ">
     <h1>Login</h1>
      <p >Sign in to start your session</p>

      <form id="frmMain"  method="post">
        <div class="input-group mb-3">
          <input type="text" data-enter="signIn" id="userId" name="userId" class="form-control" placeholder="id" value="" 
<%--           value="<c:out value='${loginId }'/>" --%>
           />
    <!--      <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-envelope "></span>
            </div>
          </div>-->
        </div>
        <div class="input-group mb-3">
          <input type="password" data-enter="signIn" id="pwd" name="pwd" value=""  class="form-control" placeholder="password">
<!--          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>-->
        </div>
        <div class="row">
          <div class="col-8 ">
          <label class="remember">   Remember Me
              <input type="checkbox" id="saveId" name="saveId" value="Y">
              <span class="checkmark"></span>
        </label>
<!--            <div class="icheck-light" >
              <input type="checkbox" id="saveId" name="saveId" value="Y" / >
              <label for="remember">
                Remember Me
              </label>
            </div>-->
          </div>
          <!-- /.col -->
          <div class="col-4">
            <a href="#;" data-click="signIn" class="btn btn-login">Sign In</a>
          </div>
          <!-- /.col -->
        </div>
      </form>
    </div>
    <!-- /.login-card-body -->
  </div>
</div>

AAAA :: ${urlSuffix}


<script>
$(document).ready(function(){
    if($("#userId").val())
    {
        $("#pwd")[0].focus();
        $("#saveId").prop("checked",true);
    }
    else
        $("#userId")[0].focus();
        
});
function signIn(){
    var param = Utilities.formToMap("frmMain");
    if(!param.userId){
        alert("로그인 아이디를 입력해 주세요");
        $("#userId").focus();
        return false;
    }
    if(!param.pwd){
        alert("로그인 아이디를 입력해 주세요");
        $("#userId").focus();
        return false;
    }
    var url = "<c:url value='/login/login${urlSuffix}'/>";
    
    alert("url :: "+url);
    Utilities.getAjax(url,param,true,function(data,jqXHR){
        if(Utilities.processResult(data,jqXHR,"로그인에 실패했습니다."))
        {
            location.href="<c:url value='/'/>";
        }
    });
    
}
</script>
<!-- /.login-box -->
</body>