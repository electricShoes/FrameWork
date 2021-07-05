<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!-- Font Awesome -->
<link rel="stylesheet" href="<c:url value='/template/AdminLTE-3.0.5/plugins/fontawesome-free/css/all.min.css'/>" />
<!-- Ionicons -->
<link rel="stylesheet" href="<c:url value='/ionicons/2.0.1/css/ionicons.min.css'/>" />
<!-- overlayScrollbars -->
<link rel="stylesheet" href="<c:url value='/template/AdminLTE-3.0.5/plugins/overlayScrollbars/css/OverlayScrollbars.min.css'/>" />
<link rel="stylesheet" href="<c:url value='/template/AdminLTE-3.0.5/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css'/>" />
<link rel="stylesheet" href="<c:url value='/template/AdminLTE-3.0.5/plugins/myTooltip/myTooltip.css'/>" />

<!-- Theme style -->
<link rel="stylesheet" href="<c:url value='/template/AdminLTE-3.0.5/plugins/bootstrap-treeview/bootstrap-treeview.min.css'/>" />
<link rel="stylesheet" href="<c:url value='/template/AdminLTE-3.0.5/dist/css/adminlte.css'/>" />
<!-- Google Font: Source Sans Pro -->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet" />
<link rel="stylesheet" href="<c:url value='/template/AdminLTE-3.0.5/dist/css/boxicons.css'/>" />
<link rel="stylesheet" href="<c:url value='/template/AdminLTE-3.0.5/dist/css/mdm.css'/>" />
<link rel="stylesheet" href="<c:url value='/template/AdminLTE-3.0.5/dist/css/font.css'/>" />
<link rel="stylesheet" href="<c:url value='/template/AdminLTE-3.0.5/dist/css/sumoselect.css'/>" />

<link rel="shortcut icon" href="<c:url value='/images/favicon.png'/>" type="image/x-icon" />
<!-- jQuery -->
<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/jquery/jquery.min.js'/>"></script>
<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/jquery-ui/jquery-ui.js'/>"></script>
<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/jquery-blockui/jquery.blockUI.js'/>"></script>

<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/jquery-form/jquery.form.min.js'/>"></script>
<%-- <script src="<c:url value='/template/AdminLTE-3.0.5/plugins/jquery-fileupload//jquery.fileupload.js'/>"></script> --%>

<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/jquery-cookie/jquery.cookie.js'/>"></script>
<!-- Bootstrap 4 -->
<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/bootstrap/js/bootstrap.bundle.min.js'/>"></script>
<!-- overlayScrollbars -->
<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js'/>"></script>
<!-- AdminLTE App -->
<script src="<c:url value='/template/AdminLTE-3.0.5/dist/js/adminlte.min.js'/>"></script>
<!-- 
 <script src="<c:url value='/template/AdminLTE-3.0.5/dist/js/adminlte.js'/>"></script>
  -->
<!-- jQuery Knob Chart -->
<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/jquery-knob/jquery.knob.min.js'/>"></script>
<!-- daterangepicker -->
<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/moment/moment.min.js'/>"></script>
<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/daterangepicker/daterangepicker.js'/>"></script>
<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js'/>"></script>
<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.ko.min.js'/>"></script>
<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/bootstrap-treeview/bootstrap-treeview.min.js'/>"></script>
<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/myTooltip/myTooltip.js'/>"></script>
<!-- form selectbox-->

<script src="<c:url value='/template/AdminLTE-3.0.5/dist/js/sumoselect.js'/>"></script>
<script src="<c:url value='/template/AdminLTE-3.0.5/dist/js/jquery.sumoselect.min.js'/>"></script>
<!-- ChartJS -->
<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/chart.js/Chart.min.js'/>"></script>
<!-- Sparkline -->
<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/sparklines/sparkline.js'/>"></script>
<!-- JQVMap -->
<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/jqvmap/jquery.vmap.min.js'/>"></script>
<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/jqvmap/maps/jquery.vmap.usa.js'/>"></script>
<!-- Summernote -->
<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/summernote/summernote-bs4.min.js'/>"></script>
<!-- overlayScrollbars -->
<script src="<c:url value='/template/AdminLTE-3.0.5/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js'/>"></script>
<!-- AdminLTE App -->
<%-- <script src="<c:url value='/template/AdminLTE-3.0.5/dist/js/adminlte.js'/>"></script> --%>



<!-- 탭추가 -->

<script src="<c:url value='/js/manager/iframe-tab/appx.js'/>"></script>
<script src="<c:url value='/js/manager/iframe-tab/bootstrap-tab.js'/>"></script>


<script src="<c:url value='/js/manager/jszip.min.js'/>"></script>
<script src="<c:url value='/js/manager/sheetJS/xlsx.full.min.js'/>"></script>
<!-- 
<link href="<c:url value='/js/manager/realgrid/realgrid.2.1.0/realgrid-style.css'/>" rel="stylesheet" />
<script src="<c:url value='/js/manager/realgrid/realgrid.2.1.0/realgrid-lic.js'/>"></script>
<script src="<c:url value='/js/manager/realgrid/realgrid.2.1.0/realgrid.2.1.0.min.js'/>"></script>
 -->
 <link href="<c:url value='/js/manager/realgrid/realgrid.2.3.3/realgrid-style.css'/>" rel="stylesheet" />
<script src="<c:url value='/js/manager/realgrid/realgrid.2.3.3/realgrid-lic.js'/>"></script>
<script src="<c:url value='/js/manager/realgrid/realgrid.2.3.3/realgrid.2.3.3.min.js'/>"></script>

<script src="<c:url value='/js/manager/utilities.js'/>"></script>
<%-- <script src="<c:url value='/js/manager/ez-tree.js'/>"></script> --%>
<script src="<c:url value='/js/manager/gridWrap.js'/>"></script>


<script type="text/javascript">
var _urlSuffix = "${urlSuffix}";
var _PROGRAM_ID ='${currentMenuId}';
var _basePath = "<c:url value='/${currentMenuId}' />";
var _code_url = "<c:url value='/${currentMenuId}/commCode/getComboCode'/>${urlSuffix}";
var _file_upload_url = "<c:url value='/0000000000/file/uploadTempFile'/>${urlSuffix}";


$(document).ajaxError(function(event, jqxhr, settings, thrownError) {
    if (jqxhr.status == _ERROR_NEED_LOGIN) {
        alert("세션이 종료되었습니다.");
        top.location.href = "<c:url value='/login/' />";
    } else if (jqxhr.status == _ERROR_BAN_LOGIN) {
        alert("다른 사용자가 로그인해 로그아웃 합니다.");
        top.location.href = "<c:url value='/' />";
    } else if (jqxhr.status == _ERROR_HAS_NO_RIGHT) {
        alert("해당 메뉴에 권한이 없습니다.");
    }
    
    
});
function logout(){
	Utilities.getAjax("<c:url value='/login/logout${urlSuffix}' />",{},true,function(data,jqXHR){
        if(Utilities.processResult(data,jqXHR,"로그아웃에 실패했습니다."))
        {
        	 top.location.href = "<c:url value='/login/index.do' />";
        }
    });
}
</script>
<style>
  .gridView {
  	text-align:center;
    width: 100%;
    height: 500px;
  }
  .gridview_pagination{
    margin-top: 10px;
  }
  .right {
    text-align:right;
  }
  .left {
    text-align:left;
  }
  .center {
    text-align:center;
  }
  .list-group-item {
  padding : 4px;
  }
  .treeview span.icon
  {
  margin-right : 10px !important;
  }
</style>
