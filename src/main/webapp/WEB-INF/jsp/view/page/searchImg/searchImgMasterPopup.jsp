<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%>
    <script type="text/javascript">
    var tmpGubun     = ""; //최초입력, 수정구분 
    
    var fileUploadGb = false;
    
    var photoOrgWidth  = 0;
    var photoOrgHeight = 0;

    $(document).ready(function(){
        
        init();
        
        //파일업로드 이벤트
        $("#imgFile").change(function(e){
            var imgFile = $(this).val();
            if( imgFile != "" ){
                var ext = imgFile.split('.').pop().toLowerCase();
                var whiteList = ['gif','png','jpg','jpeg','bmp'];
                var wleng = whiteList.length;
                for(var i=0; i<wleng;i++){
                    var upper = whiteList[i].toUpperCase();
                    whiteList.push(upper);
                }
                if($.inArray(ext,whiteList ) == -1) {
                    alert("gif, png, jpg, jpeg, bmp 파일만 사용 할 수 있습니다.")//이미지 파일만 선택해주세요.
                    $(this).val("");
                    return;
                }else{
                    //이미지 미리보기
                    var fakeUrl = URL.createObjectURL(e.target.files[0])
                    $("#resImg").attr("src",fakeUrl);
                   
                    /*업로드시 파일원본 이미지 width height 구하기*/
                    var tmpFile = e.target.files[0];
                    var _URL = window.URL || window.webkitURL;
                    var img = new Image();
                    img.src = _URL.createObjectURL(tmpFile);
                    
                    img.onload = function() {
                        //debug
                        //console.log("넓이 : " + img.width + ", 높이 : " + img.height);
                        photoOrgWidth  = img.width;
                        photoOrgHeight = img.height;
                    }
                   var tmpRowCnt =  grdDetail.getRowCount();
                   
                   
                   
                   if(tmpRowCnt > 0){
                       grdDetail.clearRows();
                      
                   }
                    
                    $(".objData").remove();
                    
                    fileUploadGb = true;
                    
                }
            }
        });
        
        /*해역 체인지 이벤트*/
        $("#seaNm").on("change", function(e){
            initCodeSelectBox("PORT_CD",$(this).val(),false,"D","portCd","S"); //퐐영기기
        });

    });
    
    
    function init(){
        //debug
        //alert("값은?"+'<c:out value="${stdyDtaMngNo}"/>');
        if('<c:out value="${stdyDtaMngNo}"/>'== null || '<c:out value="${stdyDtaMngNo}"/>' == ""){
            ///조회   
              tmpGubun = "I"; 
        }else{
              $("#btnReadOut").hide();
              tmpGubun = "U"; 
              fnSearch();
        }//end if
        //debug
        //alert("값은["+tmpGubun+"]");
        
    }//end fucntion


    //학습데이터정보조회
    function fnDetailSearch()
    {
        var url = "<c:url value='/${currentMenuId}/getListDetail${urlSuffix}'/>";
        var param = {
                stdyDtaMngNo : '<c:out value="${stdyDtaMngNo}"/>'
         };
            
        grdDetail.loadUrl(url,param);
    }
    
    //전체삭제
    function fnDelAll(){
        
         if(!confirm("전체삭제 하시겠습니까?"))
             return ;
         
         var param = {
                 stdyDtaMngNo : '<c:out value="${stdyDtaMngNo}"/>'
          };
         
         //debug
         //console.log("값은"+JSON.stringify(param));
          var url = "<c:url value='/${currentMenuId}/deleteAllPopupList${urlSuffix}'/>";
          
          Utilities.getAjax(url,param,true,function(data,jqXHR){
              if(Utilities.processResult(data,jqXHR,"삭제에 실패했습니다."))
              {
                  alert("삭제완료되었습니다.");
                  opener.search();
                  window.close();
              }
          });
       
    }//end function
    
    //학습데이터 정보 및 이미지조회
    function fnSearch(){
         var param = {
                stdyDtaMngNo : '<c:out value="${stdyDtaMngNo}"/>'
         };
        
        //debug
        //console.log("값은"+JSON.stringify(param));
         var url = "<c:url value='/${currentMenuId}/selectImage${urlSuffix}'/>";
         
         Utilities.getAjax(url,param,true,function(data,jqXHR){
             if(Utilities.processResult(data,jqXHR,"조회에 실패했습니다."))
             {
                 var result = data
                 console.log(JSON.stringify(data));
                 
                 $("#resImg").attr("src",'${currentMenuId}/img/'+result.data1[0].dtasNm+'/'+result.data1[0].imgFlNm+'${urlSuffix}');
                 
                 //촬영기기
                 $("#dtasNm").val(result.data1[0].dtasNm);
                
                 //항구
                 $("#portCd").val(result.data1[0].seaNm);

                 //위도
                 $("#lattDd").val(result.data1[0].lattDd);

                 //경도
                 $("#lngtDd").val(result.data1[0].lngtDd);

                 //수심
                 $("#dpthOfWter").val(result.data1[0].dpthOfWter);
                 
                 //비고
                 $("#rmkHeader").val(result.data1[0].rmk);
                 
                 //해역
                 $("#seaNm").val(result.data2[0].dtlCd);
                 
                 setTimeout(function() {
                 var resImgWdth = $("#resImg").width();
                 var resImgHgt = $("#resImg").height();
                 var wdthRate = resImgWdth/result.data1[0].imgWdth;
                 var hgtRate = resImgHgt/result.data1[0].imgHgt;
                 
                 var color = new Array();

                 $.each(result.data3, function(i, item) {
                     var xMin = Math.floor(item.bndbxXMin * wdthRate);
                     var xMax = Math.floor(item.bndbxXMax * wdthRate);
                     var yMin = Math.floor(item.bndbxYMin * hgtRate);
                     var yMax = Math.floor(item.bndbxYMax * hgtRate);
                     var boxWdth = xMax - xMin;
                     var boxHght = yMax - yMin;

                     var rndColor = Math.round( Math.random() * 0xFFFFFF ).toString(16);
                     var colorChk = rndColor.length == 6?rndColor:"0"+rndColor;
                     color.push("#"+colorChk);
                     
                     //바인딩 박스 그리기
                     var bindBox ="<div class='objData' title='"+item.objNm+"' style='position:absolute;border: 3px solid "+color[i]+";width:"+boxWdth+"px;height:"+boxHght+"px;top:"+yMin+"px;left:"+xMin+"px;cursor:pointer'></div>";
                     $("#img1").prepend(bindBox);
                 });
                 },500);
                 //학습데이터 정보 조회
                 fnDetailSearch();
                 
             }
         });
        
    }// end Code


    function saveList(){
                
        var saveJson = grdDetail.getAllJson();
        // debug
        //console.log("값은"+JSON.stringify(saveJson));
        
        var tmpVali = true;
        
        /*신규로 등록할때*/
        if(tmpGubun=="I"){
            if(fileUploadGb == false){
                alert("첨부파일을 선택해주세요.");
                tmpVali = false;
                return;
            }
        }
        
        if($("#dtasNm").val()==""){
            alert("촬영기기를 선택해주세요");
            tmpVali = false;
            return;
        }

        if($("#seaNm").val()==""){
            alert("해역을 선택해주세요");
            tmpVali = false;
            return;
        }

        if($("#portCd").val()==""){
            alert("항구를 선택해주세요");
            tmpVali = false;
            return;
        }

        if($("#lattDd").val()==""){
            alert("위도를 입력해주세요");
            tmpVali = false;
            return;
        }

        if($("#lngtDd").val()==""){
            alert("경도를 입력해주세요");
            tmpVali = false;
            return;
        }

        if($("#dpthOfWter").val()==""){
            alert("수심을 입력해주세요");
            tmpVali = false;
            return;
        }

        
        if(tmpVali === true){
            
            if(!confirm("수정된 데이터를 저장하시겠습니까?"))
                return ;
           
           
            var formData = new FormData();
            formData.append('stdyDtaMngNo',tmpGubun == "I"? '' : '<c:out value="${stdyDtaMngNo}"/>');
            formData.append('dtasNm',      $("#dtasNm").val());  /*촬영기기*/
            formData.append('seaNm',       $("#portCd").val());   /*항구*/
            formData.append('lattDd',      $("#lattDd").val());  /*위도*/
            formData.append('lngtDd',      $("#lngtDd").val());  /*경도*/
            formData.append('dpthOfWter',  $("#dpthOfWter").val());  /*수심*/
            formData.append('rmkHeader',   $("#rmkHeader").val());  /*수심*/
            formData.append('list',        JSON.stringify(saveJson));  /*하단그리드*/
           // formData.append('file',        $('input[name="imgFile"]')[0].files[0]); ///파일업로드
            
            if(fileUploadGb == true){
                formData.append('file',        $('input[name="imgFile"]')[0].files[0]); ///파일업로드
            }else{
                formData.append('file',        null); 
            } 
            
            $.ajax({
                type : 'POST',
                enctype : 'multipart/form-data',
                processData : false,
                contentType : false,
                cache : false,
                timeout : 600000,
                url : "<c:url value='/${currentMenuId}/saveImgMasterPopupList${urlSuffix}'/>",
                dataType : 'JSON',
                data : formData,
                success : function(result) {
                    //이 부분을 수정해서 다양한 행동을 할 수 있으며,
                    //여기서는 데이터를 전송받았다면 순수하게 OK 만을 보내기로 하였다.
                    //-1 = 잘못된 확장자 업로드, -2 = 용량초과, 그외 = 성공(1)
                    if (result === -1) {
                        alert('jpg, gif, png, bmp 확장자만 업로드 가능합니다.');
                        return false; 
                        //이후 동작 ...
                    } else if (result === -2) {
                        alert('파일이 10MB를 초과하였습니다.');
                        return false;
                       //  이후 동작 ...
                    } else if (result === -3) {
                        alert('파일저장이실패되었습니다.');
                        return false;
                       //  이후 동작 ...
                    } else {
                        alert('저장이 완료되었습니다.');
                        opener.search();
                        window.close();
                      //   이후 동작 ...
                    }
                }
               // 전송실패에대한 핸들링은 고려하지 않음
            });
        }
        
    }//end function
    
    
    
    function fnReadOut(){
        
        var tmpVali = true;
        
        /*판독은 신규 팝업일때만 */
        if(tmpGubun=="I"){
            if(fileUploadGb == false){
                alert("첨부파일을 선택해주세요.");
                tmpVali = false;
                return;
            }
        }//end if
        
        if($("#dtasNm").val()==""){
            alert("촬영기기를 선택해주세요");
            tmpVali = false;
            return;
        }
        
        if(tmpVali === true){
            
            if(!confirm("판독하시겠습니까?"))
                return ;
           
            var formData2 = new FormData();
                formData2.append('dtasNm',      $("#dtasNm").val());  /*촬영기기*/
            
            if(fileUploadGb == true){
                formData2.append('file',        $('input[name="imgFile"]')[0].files[0]); ///파일업로드
            }else{
                formData2.append('file',        null); 
            } 
            
            $.ajax({
                type : 'POST',
                enctype : 'multipart/form-data',
                processData : false,
                contentType : false,
                cache : false,
                timeout : 600000,
                url : "<c:url value='/${currentMenuId}/selectReadOutPopupList${urlSuffix}'/>",
                dataType : 'JSON',
                data : formData2,
                beforeSend: function (xhr) {
                    var hgt = $("html").height();
                    $("#loadingBlock").css("height",hgt)
                    $("#loadingBlock").show();
                    },
                success : function(result) {
                    //이 부분을 수정해서 다양한 행동을 할 수 있으며,
                    //여기서는 데이터를 전송받았다면 순수하게 OK 만을 보내기로 하였다.
                    //-1 = 잘못된 확장자 업로드, -2 = 용량초과, 그외 = 성공(1)
                    if (result.errCode == "ERROR") {
                        alert('통신 오류가 발생하였습니다.');
                        return false; 
                        //이후 동작 ...
                    }else if(result.errCode == "FILE"){
                    	 alert('파일을 다시 확인해주세요.');
                         return false; 
                    } else {                    	
                    	var result =result.result;
                    	var length = result.length;                    	
                    	if(length>0){
                    		var resImgWdth = $("#resImg").width();
                            var resImgHgt = $("#resImg").height();
                            var wdthRate = resImgWdth/photoOrgWidth;
                            var hgtRate = resImgHgt/photoOrgHeight;
                            
                            var color = new Array();
                            
                            
                            $.each(result, function(i, item) {
                                var xMin = Math.floor(item.bndbxXMin * wdthRate);
                                var xMax = Math.floor(item.bndbxXMax * wdthRate);
                                var yMin = Math.floor(item.bndbxYMin * hgtRate);
                                var yMax = Math.floor(item.bndbxYMax * hgtRate);
                                var boxWdth = xMax - xMin;
                                var boxHght = yMax - yMin;

                                var rndColor = Math.round( Math.random() * 0xFFFFFF ).toString(16);
                                var colorChk = rndColor.length == 6?rndColor:"0"+rndColor;
                                color.push("#"+colorChk);
                                
                                //바인딩 박스 그리기
                                var bindBox ="<div class='objData' title='"+item.objNm+"' style='position:absolute;border: 3px solid "+color[i]+";width:"+boxWdth+"px;height:"+boxHght+"px;top:"+yMin+"px;left:"+xMin+"px;cursor:pointer'></div>";
                                $("#img1").prepend(bindBox);
                            });
                          //   이후 동작 ...
                         
                          
                             grdDetail.closeLoading();
                             grdDetail.loadJson(result);
                    	}else{
                    		alert("데이터가 없습니다.");
                    		grdDetail.clearRows();                         
                            $(".objData").remove();
                    	}
                        
                    
                    }
                },
                error:function(){
                	alert("통신 오류가 발생하였습니다.");
                	grdDetail.clearRows();                         
                    $(".objData").remove();
                },
                complete : function() {
                    $("#loadingBlock").hide();  
                }
               // 전송실패에대한 핸들링은 고려하지 않음
            });
        }
       
   }// end Code
    
    function addCodeM(data){
        if(!data)
            return;
        data.stat = "c";
        grdDetail.addRow(data);
    }
    
    function delCodeD(){
        var list = grdDetail.getCheckedJson();
        if(list.length == 0)
        {
            alert("체크된 데이터가 존재하지 않습니다.");
            return;
        }else{
            grdDetail.removeCheckRow();
        }
        
    }
    
    
    /*셀렉트박스 만들기 
    headerGb : //M:ZZ_CODE_M , D:ZZ_CODE_D
    objId : 셀렉트박스 ID
    initVal : 첫번째 박스값이 S:선택 A:전체 
  */
    function initCodeSelectBox(mstCode,tmpRefCd1,tmpAsync,headerGb,objId,initVal){
        
          var initText = "전체";
          
          if(initVal=="S"){
              initText = "선택";
          }else if(initVal=="A"){
              initText = "전체";
          }
          
          var strHtml = "";
          //var data = parm;
          //data.access_token = token;   
          //console.log(data)      
           //데이터 받는 로직 필요
           
           $.ajax({
                url : "<c:url value='/${currentMenuId}/selectCodeList${urlSuffix}'/>"
              , async : tmpAsync //true, false
              , type : "POST"
              , cache:false
              , timeout : 30000 
              , data : { 
                           cmpyCd   : '0000'  //그룹코드Id
                          ,mstCd    : mstCode //마스터코드Id
                          ,refCd1   : tmpRefCd1 //REF_CD
                          ,headerGb : headerGb //M:ZZ_CODE_M , D:ZZ_CODE_D
                          
                       }
              //, data : JSON.parse(JSON.stringify(data))
              , dataType : "json"
              , xhrFields: {
                      withCredentials: false
              }
              , beforeSend: function (xhr) {
                  xhr.setRequestHeader("Accept", "application/json");
              }
              , success : function (data, statusText, xhr) {
                  $("#"+objId).children("*").remove();
                  strHtml+="<option value=''>"+initText+"</option>";
                  if(data.data.length > 0) {
                          $.each(data.data, function(i, item) {
                              strHtml+="<option value="+item.code+">"+item.name+"</option>";
                          });
                          $("#"+objId).html(strHtml);
                  }else{
                      strHtml+="";
                      $("#"+objId).html(strHtml);
                  }
              }
              , error : function (xhr, statusText, errorThrown) { 
                  console.log("error status :: " + xhr.status + " || statusText :: " + xhr.responseText + " || errorThrown :: " + errorThrown);
              }
          });
          
      }// end Code

    </script>

<!-- /.card -->
<!-- Horizontal Form -->
<div id='loadingBlock' style=' position:absolute;top:0px;left:0px;width: 100%;height: 100%;background-color:#00000054 ;z-index: 999;display:none ;'>
      <!--   <img id="loadingImg" src="/img/loading.svg" style=' position: absolute;top:50%;left:50%; transform: translate(-50%, -50%); display: block;'> -->
</div>  
<div class="card">
   <div class="searh_group">     
    <!-- /.상단조회버튼추가예시-->    
         <div class="btn-group" style="margin-right:5px;"> 
            <button type="button" data-grid-id="grdDetail" id="btnLangList" data-click="saveList" class="btn btn-sm" style="margin-right: 5px;"><i class="fas fa-trash"></i>저장</button>
            <button type="button" id="btnDelList" name="btnDelList" data-click="fnDelAll" class="btn  btn-sm"><i class="fas fa-trash"></i>전체삭제</button>
         </div>
    <!-- /.상단조회버튼추가예시 end-->    
         <input type="file" name="imgFile" id="imgFile" accept="image/*">
    </div>
    <form role="form" id="frmSearch">
    <div id="search" class="searh_group">
        <label for="codeLevel" class="popLabel">촬영기기</label>
        <div class="" style="width: 100px;">
          <code:commCode className="width100" selectedValue = "Y" prefixValue="" prefixLabel="선택" id="dtasNm" name="dtasNm" mstCd ="DTAS_CD"/>
        </div>
        <label for="mstCd" class="popLabel">해역</label>
        <div class="" style="width: 70px;">
          <!--<code:commCode className="form-control" selectedValue = "Y" prefixValue="" prefixLabel="선택" id="seaNm" name="seaNm" mstCd ="SEA_CD"/>-->
          <code:commCode className="width100" selectedValue = "Y" prefixValue="" prefixLabel="선택" id="seaNm" name="seaNm" mstCd ="SEA_CD"/>
        </div>
        
        <label for="mstNm" class="popLabel">항구</label>
        <div class="" style="width: 70px;">
          <code:commCode className="width100" selectedValue = "Y" prefixValue="" prefixLabel="선택" id="portCd" name="portCd" mstCd ="PORT_CD"/>
        </div>
        
        <!-- 하단 그리드 셀렉트박스 때문에 필요함 없에버리면 안됨 -->
        <label for="mstNm" class="popLabel" style="display: none;">카테고리</label>
        <div class="" style="width: 100px;display: none;">
          <code:commCode className="width100" selectedValue = "Y" prefixValue="" prefixLabel="선택" id="objNm" name="objNm" mstCd ="OBJ_CD"/>
        </div>
        <!-- 하단 그리드 셀렉트박스 때문에 필요함 없에버리면 안됨 -->
    
        <label for="codeLevel" class="popLabel">위도</label>
        <div class="" style="width: 50px;">
          <input type="text" id="lattDd" data-enter="search" name="lattDd" class="form-control" onkeypress="return isNumberKey(event)" onkeyup="return delHangle(event)">
        </div>
        
        <label for="codeLevel" class="popLabel">경도</label>
        <div class="" style="width: 50px;">
          <input type="text" id="lngtDd" data-enter="search" name="lngtDd" class="form-control" onkeypress="return isNumberKey(event)" onkeyup="return delHangle(event)">
        </div>
        
        <label for="codeLevel" class="popLabel">수심</label>
        <div class="" style="width: 50px;">
          <input type="text" id="dpthOfWter" data-enter="search" name="deepOut" class="form-control" onkeypress="return isNumberKey(event)" onkeyup="return delHangle(event)">
        </div>
        
        <label for="codeLevel" class="popLabel">비고</label>
        <div class="" style="width: 120px;">
          <input type="text" id="rmkHeader" data-enter="search" name="rmkHeader" class="form-control">
        </div>
    </div>
    
    <div id="img0" style="height: 400px; width: 100%; border:1px solid gold;  position:relative; text-align: center">
        <span id="img1" style="position: relative;display:inline-block;height:100%">      
            <img src="../images/noimg.png" alt="이미지" id="resImg" style="max-width:100%;max-height: 100%;">
        </span>       
    </div>
    </form>
</div>

<!-- /.card -->
<div class="row">
    <div class="col-12">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">학습데이터 정보</h3>
                <div class="card-tools">
                    <div class="input-group input-group-sm">
                        <div class="btn-group"> 
                            <button type="button" id="btnReadOut"   data-grid-id="grdDetail" data-click="fnReadOut" class="btn btn-success btn-sm"><i class="fas fa-plus"></i>판독</button>
                            <button type="button" id="btnAddDetail"   data-grid-id="grdDetail" data-click="addCodeM" class="btn btn-success btn-sm"><i class="fas fa-plus"></i>추가</button>
                            <button type="button" id="btnDelDetail"   data-grid-id="grdDetail" data-click="delCodeD" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i>삭제</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGridDetail" style="height:250px"  data-grid-id="grdDetail" data-pagenation="N" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/page/searchImgMasterPopUpList.xml'/>">
                </div>
                <!-- /.mail-box-messages -->
            </div>
        </div>
        <!-- /.card -->
    </div>
    <!-- /.col -->
</div>
<!-- /.row -->
