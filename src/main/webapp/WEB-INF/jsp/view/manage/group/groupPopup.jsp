<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%> 
<!-- /.card -->
<!-- Horizontal Form -->

<div class="row">

    <div class="col-12">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">그룹</h3>

                <div class="card-tools">
                    <div class="input-group input-group-sm">
                     <div class="btn-group"> 
                        <button type="button" data-grid-id="grdList" data-click="saveGroupChecked" id="btnSaveList" data-action="save" class="btn btn-info btn-sm"><i class="fas fa-save"></i> 저장</button>
                     </div>
                    </div>
                </div>
                <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGrid"   data-grid-id="grdList" data-pagenation="N" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/group/groupPopup2.xml'/>">
                </div>
            </div>
            <div class="card-footer">
                <a id="btnCancel" href="#;" data-click="close" class="btn btn-default float-right"><i class="fas fa-ban"></i> 닫기</a>
            </div>
        </div>
        <!-- /.card -->
    </div>
    
    <!-- /.col -->

</div>
<!-- /.row -->
<script>
function onGridDataLoaded(gridView,gridJson,srcJson){
	console.log("onGridDataLoaded ====== ")
	for(var i=0;i<gridJson.length;i++){
		var json = gridJson[i];
		gridView.checkItem(i,json.checkYn == "Y");
	}
}
function onGridLoad(){
	console.log("onGridLoad ====== ")
	search();
}
function search()
{
    var url = "<c:url value='/0102010200/group/getGroupCheckList${urlSuffix}'/>";
    console.log("url :: " + url)
    var param = {userId : '<c:out value="${userId}"/>',
    		     menuId : '<c:out value="${menuId}"/>'
    		};
    grdList.loadUrl(url,param);
}
function saveGroupChecked(){
	var list = grdList.getCheckedJson();
	for(var i=0;i<list.length;i++){
		list[i].checkYn='Y';
		
		list[i].userId='<c:out value="${userId}"/>';
		list[i].menuId='<c:out value="${menuId}"/>';
		
	}
	opener.saveGroupChecked('<c:out value="${userId}"/>' || '<c:out value="${menuId}"/>',list);
	top.close();
}
</script>