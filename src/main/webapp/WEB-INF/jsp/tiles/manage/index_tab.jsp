<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>MWRS</title>
    <!-- Tell the browser to be responsive to screen width -->
   <!--  <meta name="viewport" content="width=device-width, initial-scale=1"> Non-responsive Bootstrap -->
    <tiles:insertTemplate template="header.jsp" />
	
</head>

<body class="hold-transition sidebar-mini layout-fixed main_body ">

    <!-- Site wrapper -->
    <div class="wrapper">
        <!-- Navbar -->
        <nav class="main-header navbar navbar-expand">
            <!-- Left navbar links -->
            <ul class="navbar-nav">
            	<!-- 
                <li class="nav-item "><a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a></li>
               	 -->
               	<li class="nav-item "><a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a></li>
               <%--  <li class="nav-item"><a href="<c:url value='/index.do'/>" class="nav-link ">Home</a></li> --%>
                <c:forEach var="menu" items="${ menuTree.nodes}">
                    <c:if test='${menu.menuShowYn eq "Y" }'>
                        <li class="nav-item d-none d-sm-inline-block <c:out value='${topMenu.menuId eq menu.menuId?"active":""}' />">
                        <a href="<c:out value="${empty menu.menuUrl?'#':menu.menuUrl}" />.do" class="nav-link"  data-action="showMenu" data-menu-id="<c:out value="${menu.menuId}" />" <c:out value='${empty menu.menuUrl?"":"data-click=openTab"}' /> data-title='<c:out value="${menu.menuNm }" />'>
                        <c:out value="${menu.menuNm }" />
                        </a></li>
                    </c:if>
                </c:forEach>
            </ul>
            <script type="text/javascript">
                $(document).ready(function() {
                    $("[data-action=showMenu]").click(function() {
                        $("[data-type=submenulist]").hide();
                        $("[data-type=submenulist][data-top-menu-id=" + $(this).attr("data-menu-id") + "]").show();
                    });
                });

            </script>

            <!-- Right navbar links -->
            <ul class="navbar-nav ml-auto">
                
                <li class="nav-item"><a class="nav-link" data-click="logout" href="#"><i class='bx bx-log-out bx-sm  bx-rotate-180'></i></a></li>
            </ul>
        </nav>
        <!-- /.navbar -->

        <!-- Main Sidebar Container -->
        <aside class="main-sidebar bggray">
            <!-- Brand Logo -->
            <a href="#" class="brand-link" style="cursor: default;">
              <%--   <img src="<c:url value='/template/AdminLTE-3.0.5/dist/img/AdminLTELogo.png'/>" alt="AdminLTE Logo" class="brand-image" style="opacity: 1"> --%>
                <span class="brand-text ">MW<span style="color:#106eea">RS</span></span>
            </a>

            <!-- Sidebar -->
            <div class="sidebar">
                <!-- Sidebar Menu -->
                <nav class="mt-2">
                    <ul class="nav nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                    	${infoMenu}
                        <!--         메뉴구성 -->
                        <c:forEach var="topLevelMenu" items="${ menuTree.nodes}">
                            <c:forEach var="menu" items="${ topLevelMenu.nodes}">
                                <c:if test='${menu.menuShowYn eq "Y" }'>
                                    <li class="nav-item has-treeview <c:out value='${menu.menuId eq level3Menu.menuId?" menu-open":""}' />" style="<c:out value='${topLevelMenu.menuId eq topMenu.menuId? "":"display:none" }' />" data-type="submenulist" data-top-menu-id="<c:out value='${topLevelMenu.menuId }' />">
                                    <a href="#" class="nav-link <c:out value='${menu.menuId eq level3Menu.menuId?" active":""}' />">
                                    <i class="nav-icon <c:out value='${ empty menu.menuIcon ? " far fa-folder" : menu.menuIcon }' />"></i>
                                    <p>
                                        <c:out value='${menu.menuNm}' />
                                        <i class="right fas fa-angle-bottom"></i>
                                    </p>
                                    </a>
                                    <ul class="nav nav-treeview">
                                        <c:forEach var="subMenu1" items="${ menu.nodes}">
                                            <c:if test='${subMenu1.menuShowYn eq "Y" }'>
                                                <c:set var="menuUrl" value="${ subMenu1.menuUrl}${urlSuffix }" />
                                                <li class="nav-item2  <c:out value='${subMenu1.menuId eq level4Menu.menuId?" menu-open":""}' />">
                                                <a href="<c:url value='/${empty subMenu1.menuUrl? "" : menuUrl}'/>" data-menu-id="<c:out value='${subMenu1.menuId }'/>" data-click="openTab" data-title="<c:out value='${subMenu1.menuNm }'/>" class="nav-link <c:out value='${subMenu1.menuId eq level4Menu.menuId?" active":""}' />">
                                                <i class="<c:out value='${empty subMenu1.menuIcon? " bx bxs-chevron-right" : subMenu1.menuIcon }' /> nav-icon"></i>
                                                <p>
                                                    <c:out value='${subMenu1.menuNm}' />
                                                    <c:if test="${subMenu1.childrenCount > 0  }">
                                                        <i class="right fas fa-angle-left"></i>
                                                    </c:if>
                                                </p>
                                                </a>
                                                <c:if test="${subMenu1.childrenCount > 0  }">
                                                    <ul class="nav nav-treeview">
                                                        <c:forEach var="subMenu2" items="${ subMenu1.nodes}">
                                                            <c:if test='${subMenu2.menuShowYn eq "Y" }'>
                                                                <c:set var="menuUrl" value="${ subMenu2.menuUrl}${urlSuffix }" />
                                                                <li class="nav-item2">
                                                                    <a href="<c:url value='/${empty subMenu2.menuUrl? "" :menuUrl}'/>" data-menu-id="<c:out value='${subMenu2.menuId }'/>" data-click="openTab" data-title="<c:out value='${subMenu2.menuNm }'/>" class="nav-link <c:out value='${subMenu2.menuId eq level5Menu.menuId?" active":""}' />">
                                                                    <i class="<c:out value='${empty subMenu2.menuIcon? " far fa-dot-circle" : subMenu2.menuIcon }' /> nav-icon"></i>
                                                                    <p>
                                                                        <c:out value='${subMenu2.menuNm}' />
                                                                    </p>
                                                                    </a>
                                                                </li>
                                                            </c:if>
                                                        </c:forEach>
                                                    </ul>
                                                </c:if>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </c:forEach>

                    </ul>
                </nav>
                <!-- /.sidebar-menu -->
            </div>
            <!-- /.sidebar -->
            
            
        </aside>

        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header ">
                
                
<!--                 <div class="content-tabs"> -->
                    <div class="page-tabs menuTabs tab-ui-menu " id="tab-menu">
                       
                        <div class="page-tabs-content " >
                        </div>
                   
                    </div>
<!--                 </div> -->
                

            </section>

            <!-- Main content -->
            <section class="content">
               <div class="content-iframe " >
                     <div class="tab-content " id="tab-content">

                    </div>
                </div>
<%--                 <tiles:insertAttribute name="content" /> --%>
            </section>
            <!-- /.content -->
        </div>
        <!-- /.content-wrapper -->

        <footer class="main-footer">
         Copyright &copy; 2020 <a href="<c:url value='/index.do'/>">CSPI company</a>.
          All rights reserved.
        </footer>
        <!-- Control Sidebar -->
        <aside class="control-sidebar control-sidebar-dark">
            <!-- Control sidebar content goes here -->
        </aside>
        <!-- /.control-sidebar -->
    </div>
    <!-- ./wrapper -->
    <script>
    
    
    function openTab(element,data){
        var href = element.attr("href");
        if(href && href.length>2){
            var id = data.menuId; 
            var option = {id : data.menuId,
                         title : data.title,
                         close : true,
                         url : href
                      };
            addTabs(option); 
        }
        
    }
//     $(document).ready(function(){
//         addTabs({
//             id: '10008',
//             title: '탭테스트',
//             close: true,
//             url: 'http://news.naver.com/'
//         });    
//     });
//     App.setbasePath("/");
//     App.setGlobalImgPath("dist/img/");

//     App.fixIframeCotent();
    </script>
</body>

</html>
