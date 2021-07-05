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

<body class="hold-transition sidebar-mini layout-fixed">
    <!-- Site wrapper -->
    <div class="wrapper">
        <!-- Navbar -->
        <nav class="main-header navbar navbar-expand">
            <!-- Left navbar links -->
            <ul class="navbar-nav">
                <li class="nav-item "><a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a></li>
                <li class="nav-item"><a href="<c:url value='/index.do'/>" class="nav-link ">Home</a></li>
                <c:forEach var="menu" items="${ menuTree.nodes}">
                    <c:if test='${menu.menuShowYn eq "Y" }'>
                        <li class="nav-item d-none d-sm-inline-block <c:out value='${topMenu.menuId eq menu.menuId?"active":""}' />">
                        <a href="#" class="nav-link" data-action="showMenu" data-menu-id="<c:out value="${menu.menuId}" />">
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
            <!-- SEARCH FORM -->
            <%--     <form class="form-inline ml-3"> --%>
            <!--       <div class="input-group input-group-sm"> -->
            <!--         <input class="form-control form-control-navbar" type="search" placeholder="Search" aria-label="Search"> -->
            <!--         <div class="input-group-append"> -->
            <!--           <button class="btn btn-navbar" type="submit"> -->
            <!--             <i class="fas fa-search"></i> -->
            <!--           </button> -->
            <!--         </div> -->
            <!--       </div> -->
            <%--     </form> --%>

            <!-- Right navbar links -->
            <ul class="navbar-nav ml-auto">
                <!--  
                <li class="nav-item dropdown"><a class="nav-link" data-toggle="dropdown" href="#"> <i class="far fa-comments"></i> <span class="badge  navbar-badge">3</span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                        <a href="#" class="dropdown-item">
                        
                            <div class="media">
                                <img src="<c:url value='/template/AdminLTE-3.0.5/dist/img/user1-128x128.jpg'/>" alt="User Avatar" class="img-size-50 mr-3 img-circle">
                                <div class="media-body">
                                    <h3 class="dropdown-item-title">
                                        Brad Diesel <span class="float-right text-sm text-danger"><i class="fas fa-star"></i></span>
                                    </h3>
                                    <p class="text-sm">Call me whenever you can...</p>
                                    <p class="text-sm text-muted">
                                        <i class="far fa-clock mr-1"></i> 4 Hours Ago
                                    </p>
                                </div>
                            </div>
                        </a>
                        <div class="dropdown-divider"></div>
                        <a href="#" class="dropdown-item">
                        
                            <div class="media">
                                <img src="<c:url value='/template/AdminLTE-3.0.5/dist/img/user8-128x128.jpg'/>" alt="User Avatar" class="img-size-50 img-circle mr-3">
                                <div class="media-body">
                                    <h3 class="dropdown-item-title">
                                        John Pierce <span class="float-right text-sm text-muted"><i class="fas fa-star"></i></span>
                                    </h3>
                                    <p class="text-sm">I got your message bro</p>
                                    <p class="text-sm text-muted">
                                        <i class="far fa-clock mr-1"></i> 4 Hours Ago
                                    </p>
                                </div>
                            </div> 
                        </a>
                        <div class="dropdown-divider"></div>
                        <a href="#" class="dropdown-item">
                         
                            <div class="media">
                                <img src="<c:url value='/template/AdminLTE-3.0.5/dist/img/user3-128x128.jpg'/>" alt="User Avatar" class="img-size-50 img-circle mr-3">
                                <div class="media-body">
                                    <h3 class="dropdown-item-title">
                                        Nora Silvester <span class="float-right text-sm text-warning"><i class="fas fa-star"></i></span>
                                    </h3>
                                    <p class="text-sm">The subject goes here</p>
                                    <p class="text-sm text-muted">
                                        <i class="far fa-clock mr-1"></i> 4 Hours Ago
                                    </p>
                                </div>
                            </div> 
                        </a>
                        <div class="dropdown-divider"></div>
                        <a href="#" class="dropdown-item dropdown-footer">See All Messages</a>
                    </div>
                </li>
               
                <li class="nav-item dropdown"><a class="nav-link" data-toggle="dropdown" href="#"> <i class="far fa-bell"></i>
                        <span class="badge navbar-badge">15</span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                        <span class="dropdown-item dropdown-header">15 Notifications</span>
                        <div class="dropdown-divider"></div>
                        <a href="#" class="dropdown-item"> <i class="fas fa-envelope mr-2"></i> 4 new messages <span class="float-right text-muted text-sm">3 mins</span>
                        </a>
                        <div class="dropdown-divider"></div>
                        <a href="#" class="dropdown-item"> <i class="fas fa-users mr-2"></i> 8 friend requests <span class="float-right text-muted text-sm">12 hours</span>
                        </a>
                        <div class="dropdown-divider"></div>
                        <a href="#" class="dropdown-item"> <i class="fas fa-file mr-2"></i> 3 new reports <span class="float-right text-muted text-sm">2 days</span>
                        </a>
                        <div class="dropdown-divider"></div>
                        <a href="#" class="dropdown-item dropdown-footer">See All Notifications</a>
                    </div>
                </li>-->
                <li class="nav-item"><a class="nav-link" data-click="logout" href="#"><i class='bx bx-log-out bx-sm  bx-rotate-180'></i></a></li>
                <!-- 				<li class="nav-item"><a class="nav-link" data-widget="control-sidebar" data-slide="true" href="#" role="button"> <i class="fas fa-th-large"></i></a></li> -->

            </ul>
        </nav>
        <!-- /.navbar -->

        <!-- Main Sidebar Container -->
        <aside class="main-sidebar bggray ">
            <!-- Brand Logo -->
            <a href="<c:url value='/template/AdminLTE-3.0.5/index3.html'/>" class="brand-link">
                <img src="<c:url value='/template/AdminLTE-3.0.5/dist/img/AdminLTELogo.png'/>" alt="AdminLTE Logo" class="brand-image" style="opacity: 1">
                <span class="brand-text ">MDM</span>
            </a>

            <!-- Sidebar -->
            <div class="sidebar">
                <!-- Sidebar Menu -->
                <nav class="mt-2">
                    <ul class="nav nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
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
                                                <a href="<c:url value='/${empty subMenu1.menuUrl? "" : menuUrl}'/>" class="nav-link <c:out value='${subMenu1.menuId eq level4Menu.menuId?" active":""}' />">
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
                                                                    <a href="<c:url value='/${empty subMenu2.menuUrl? "" :menuUrl}'/>" class="nav-link <c:out value='${subMenu2.menuId eq level5Menu.menuId?" active":""}' />">
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
            <section class="content-header">
                <div class="container-fluid">
                    <div class="row mb-2">
                        <div class="col-6">
                            <h1>
                                <c:out value="${currentMenu.menuNm}" />
                            </h1>
                        </div>
                        <div class="col-6">
                            <ol class="breadcrumb float-sm-right">
                                <c:if test="${not empty level2Menu}">
                                    <li class="breadcrumb-item active">
                                        <c:out value="${level2Menu.menuNm}" />
                                    </li>
                                </c:if>
                                <c:if test="${not empty level3Menu}">
                                    <li class="breadcrumb-item active">
                                        <c:out value="${level3Menu.menuNm}" />
                                    </li>
                                </c:if>
                                <c:if test="${not empty level4Menu}">
                                    <li class="breadcrumb-item active">
                                        <c:out value="${level4Menu.menuNm}" />
                                    </li>
                                </c:if>
                                <c:if test="${not empty level5Menu}">
                                    <li class="breadcrumb-item active">
                                        <c:out value="${level5Menu.menuNm}" />
                                    </li>
                                </c:if>
                            </ol>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->
            </section>

            <!-- Main content -->
            <section class="content">
                <tiles:insertAttribute name="content" />
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
</body>

</html>
