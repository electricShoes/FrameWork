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
<!--<meta name="viewport" content="width=device-width, initial-scale=1"> Non-responsive Bootstrap-->
<tiles:insertTemplate template="header.jsp" />
<style>
  .gridTable {
    width: 100%;
    height: 440px;
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
</style>

</head>
<tiles:insertAttribute name="content" />
</html>
