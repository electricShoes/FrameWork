<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%> 
<!-- /.card -->
<!-- Horizontal Form -->
<script type="text/javascript">
$(document).ready(function(){
    init();
});
function init(){
    search();
}// end init
function search(){
     
    var param = {
                stdyDtaMngNo   : '<c:out value="${stdyDtaMngNo}"/>'
        };
     
        var url = "<c:url value='/${currentMenuId}/selectImgBindPopList${urlSuffix}'/>";
   
        //Utilities.getAjax(url, param, jsonBody, callback, opt);
        Utilities.getAjax(url,param,true,function(data,result){
            //console.log("값은>>>>>>>>>>>>>>>"+ JSON.stringify(data.data));
           if(data.data.length > 0) {
        	   var result = new Array();
                $.each(data.data, function(i, item) {
                    var img = "../image/"+item.flUpFldrNm + "/" +item.imgFlNm;
                     //이미지 크기 구하기 
                    var jsonData = {
                         stdyDtaMngNo:item.stdyDtaMngNo,
                         y:item.lattDd,
                         x:item.lngtDd,
                         img:img,
                         deep:item.dpthOfWter,
                         seaNm:item.seaNm,
                         objNm : item.objNm,
                         imgWdth:item.imgWdth,
                         imgHgt:item.imgHgt,
                         bndbxXMin : item.bndbxXMin,
                         bndbxXMax : item.bndbxXMax,
                         bndbxYMin : item.bndbxYMin,
                         bndbxYMax : item.bndbxYMax,
                         }
                    result.push(jsonData);                            
                });
               console.log(result)
                //alert("~~~~~~~~~~~");
            //setDrawImg(result);
            }
        });
        //setDrawImg(result);
}// end search

//로우 데이터 세팅
function setDrawImg(data){             
	  var c = document.getElementById("myCanvas");
      var ctx = c.getContext("2d");
      var img = data.img;
      ctx.drawImage(img,10,10);
}
    
</script>

<div class="row">

    <div class="col-12">
        <div class="card card-primary card-outline">
            <div class="card-header" style="padding-top: 10px;">
                <h3 class="card-title">이미지 바운드 팝업</h3>

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
                <canvas id="myCanvas" style="width: 765px; height: 495px; border:1px solid #d3d3d3;"></canvas>
                <!-- 
                <div id="topRes" class="">
                    <img src="/img/sonar/noimg.png" alt="이미지" class="responImg" id="resImg">
                </div>
                
                 -->
            </div>
            <div class="card-footer">
                <a id="btnCancel" href="#;" data-click="close" class="btn btn-default float-right"><i class="fas fa-ban"></i> 닫기</a>
            </div>
        </div>
        <!-- /.card -->
    </div>
    
    <!-- /.col -->

</div>
