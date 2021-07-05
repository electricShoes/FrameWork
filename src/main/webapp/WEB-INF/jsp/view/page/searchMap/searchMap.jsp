<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%> 
<!-- /.card -->
<!-- Horizontal Form -->
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=50fzenluas&callback=initMap"></script>
<link rel="stylesheet" href="<c:url value='/css/mwrs/style.css'/>" />
<div class="row">
    <section id="team" class="team " style="width:100%">
            <div class="container" data-aos="fade-up">
                <div class="section-title ">
                    <h3>지도 조회 <span>서비스</span></h3>
                    <div>AI 해양쓰레기 지도 조회 서비스를 제공합니다.</div>
                </div>
            </div>
            <div class="container" data-aos="fade-up">
                <div class="searchBox">
                    <form id="searchParm">
                        <div style="float:left;">
                            <label for="dtasNm">촬영기기</label>
                            <select id="dtasNm" name="dtasNm">
                               <!-- <option value="">전체</option>
                                <option value="sonar dataset">소나</option>
                                <option value="underwater photo dataset">수중촬영</option>
                                -->
                            </select>

                            <label for="seaNm">해역</label>
                            <select id="seaNm" name="seaNm">
                       
                            </select>
                            
                            <label for="portNm">항구</label>
                            <select id="portNm" name="portNm">
   
                            </select>

                            <label for="objNm">카테고리</label>
                            <select id="objNm" name="objNm">
          
                            </select>
                        </div>
                        <div style="float:right;">  
                            <button id="searchBtn" type="button">조회</button>
                        </div>      
                        <div style="clear:both"></div>
                    </form>
                </div>
                <div class="">
                    <div class="col50">
                        <div id="map"></div>
                    </div>
                    <div class="col50">
                        <div id="topRes" class="">
                           <div class="bz_data2">
                                <div class=" row">
                                    <div>조사해역</div>
                                    <div>위도</div>
                                    <div>경도</div>
                                    <div>수심</div>
                                </div>
                                <div class=" row">
                                    <div id="seaOut">N/A</div>
                                    <div id="yOut">N/A</div>
                                    <div id="xOut">N/A</div>
                                    <div id="deepOut">N/A</div>
                                </div>
                            </div>
                            <div class="bz_data2 " style="margin-right:0px;margin-left:0px">
                                <div class="row">
                                    <div>카테고리</div>                  
                                    <div>XnYn</div>
                                    <div>XxYx</div>
                                    <div>표기색상</div>                   
                                </div>
                            </div>
                            <div class="bz_data5" style="height:110px;overflow-y:scroll;overflow-x:hidden;margin: 0 -8px;">
                            </div> 
                        </div>
                        <div id="botRes" class="">
                            <img src="images/noimg.png" alt="이미지" class="responImg" id="resImg">                                                       
                        </div>
                    </div>
                </div>
                
            </div>
        </section><!-- End Team Section -->
</div>
<!-- /.row -->

<script src="<c:url value='/js/mwrs/MarkerClustering.js'/>"></script>
<script src="<c:url value='/js/mwrs/comm.js'/>"></script> <!-- 셀렉트박스 만들기-->

 <script>
        //serializeObject
        $.fn.serializeObject = function() {
            var obj = null;        
            try {
                if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) {        
                var arr = this.serializeArray();        
                if(arr){           
                    obj = {};             
                        $.each(arr, function() {            
                        obj[this.name] = this.value;            
                        });           
                    }        
                }        
            }catch(e) {
                alert(e.message);        
            }finally  {}        
            return obj;        
        };
        
    var tmpHomeUrl      = ""; //url
    var apiUri          = tmpHomeUrl+"/api/oauth/token";
    var selectMapList   = tmpHomeUrl+"/api/api/selectMapList";
    var selectMapMarker = tmpHomeUrl+"/api/api/selectMapMarker";
    

    $(document).ready(function(){
        var map = null;
        var markers = [];
        init();
        
        initCodeSelectBox("DTAS_CD","",false,"D","dtasNm","A",tmpHomeUrl); //퐐영기기
        initCodeSelectBox("SEA_CD","",false,"D","seaNm","A",tmpHomeUrl);   //해역
        initCodeSelectBox("PORT_CD","",false,"D","portNm","A",tmpHomeUrl); //항구
        initCodeSelectBox("OBJ_CD","",false,"D","objNm","A",tmpHomeUrl);  //카테고리

        function init(){
            initMap();
            
           // search();
        }
        //지도 생성
        function initMap() {
            map = new naver.maps.Map('map', {
                center: new naver.maps.LatLng(36.2095704, 127.4205399),
                zoom: 7
            });
        }

        //검색함수
        function search(){
            init();
            var parm = $("#searchParm").serializeObject();
            var dtasNm = parm.dtasNm;
            var seaNm = parm.seaNm;
            var objNm = parm.objNm;
            var portNm = parm.portNm;
            
            
            if(dtasNm == "" && seaNm == "" && objNm == "" && portNm =="" ){
                alert("1개 이상의 조회 조건을 선택해주세요.");
                return;
            }
            

            var token = apiAccess();            

            var data = parm;
            data.access_token = token;   
            //console.log(data)      
             //데이터 받는 로직 필요
             $.ajax({
                url : selectMapList
                , async : true
                , type : "POST"
                , cache:false
                , timeout : 30000 
                , data : JSON.parse(JSON.stringify(data))
                , dataType : "json"
                , xhrFields: {
                        withCredentials: false
                }
                , beforeSend: function (xhr) {
                    var hgt = $("html").height();
                    $("#loadingBlock").css("height",hgt);
                    $("#loadingBlock").show();
                    xhr.setRequestHeader("Accept", "application/json");
                   
                }
                , success : function (data, statusText, xhr) { 
                    if(data.data.length > 0) {
                        var result = new Array
                        $.each(data.data, function(i, item) {
                            //var img = "../image/"+item.flUpFldrNm + "/" +item.imgFlNm;
                            var img = 'searchImg/img/'+item.dtasNm+'/'+item.imgFlNm+'${urlSuffix}'
                             var jsonData = {
                                 stdyDtaMngNo:item.stdyDtaMngNo,
                                 y:item.lattDd,
                                 x:item.lngtDd,
                                 img:img,
                                 deep:item.dpthOfWter,
                                 sea:item.seaNm,
                                 imgWdth:item.imgWdth,
                                 imgHgt:item.imgHgt
                                 }
                            result.push(jsonData);
                            
                        });
                       // console.log(result)
                        setMarkers(result);
                    }
                }
                , error : function (xhr, statusText, errorThrown) { 
                    console.log("error status :: " + xhr.status + " || statusText :: " + xhr.responseText + " || errorThrown :: " + errorThrown);
                }, complete : function() {
                                $("#loadingBlock").hide();  
                            }
            });
        }


        //조회이벤트
        $("#searchBtn").on("click",function(){
            search();
        });
        
        $("#seaNm").on("change", function(e){
            initCodeSelectBox("PORT_CD",$(this).val(),false,"D","portNm","A",tmpHomeUrl); //퐐영기기
        });
        
        //마커 html 디자인
        var htmlMarker1 = {
        content: '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(images/mwrs/map/cluster-marker-1.png);background-size:contain;"></div>',
        size: N.Size(40, 40),
        anchor: N.Point(20, 20)
        },
        htmlMarker2 = {
            content: '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(images/mwrs/map/cluster-marker-2.png);background-size:contain;"></div>',
            size: N.Size(40, 40),
            anchor: N.Point(20, 20)
        },
        htmlMarker3 = {
            content: '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(images/mwrs/map/cluster-marker-3.png);background-size:contain;"></div>',
            size: N.Size(40, 40),
            anchor: N.Point(20, 20)
        },
        htmlMarker4 = {
            content: '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(images/mwrs/map/cluster-marker-4.png);background-size:contain;"></div>',
            size: N.Size(40, 40),
            anchor: N.Point(20, 20)
        },
        htmlMarker5 = {
            content: '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(images/mwrs/map/cluster-marker-5.png);background-size:contain;"></div>',
            size: N.Size(40, 40),
            anchor: N.Point(20, 20)
        };

        //지도에 마커 세팅
        function setMarkers(data){  
            markers = [];         
            
            //마커 생성
            for (var i = 0, ii = data.length; i < ii; i++) {
                var spot = data[i];
                var latlng = new naver.maps.LatLng(spot.y, spot.x);
                var marker = new naver.maps.Marker({
                        position: latlng,
                        draggable: false,
                        data:spot //데이터 정보 넣기                        
                    });
               
                //마커 클릭 이벤트
                marker.addListener('click', function(e) {
                    var ovl = e.overlay;
                    //alert(ovl.data);
                    markerData(ovl.data)
                });

                markers.push(marker);               
            }
            
            //마커 클러스터(마커 그룹화)
            var markerClustering = new MarkerClustering({
                    minClusterSize: 4,
                    maxZoom: 14,
                    map: map,
                    markers: markers,
                    disableClickZoom: false,
                    gridSize: 200,
                    icons: [htmlMarker1, htmlMarker2, htmlMarker3, htmlMarker4, htmlMarker5],
                    indexGenerator: [10, 100, 200, 500, 1000],
                    stylingFunction: function(clusterMarker, count) {
                        $(clusterMarker.getElement()).find('div:first-child').text(count);
                    }
            });
        }
        
        //마커 데이터 출력 이벤트
        function markerData(data){
            //console.log(data)
            $("#resImg").attr("src",data.img);
            $("#seaOut").text(data.sea);
            $("#yOut").text(data.y);
            $("#xOut").text(data.x);
            $("#deepOut").text(data.deep);

        
            selectMarker(data);
            
            
            //$("#categoryOut").text(data.category);
        }
        
    });

     //마커선택
    function selectMarker(mapData){
        $(".objData").remove();
        var parm = {'stdyDtaMngNo':mapData.stdyDtaMngNo};
        var token = apiAccess();
        //console.log(token, parm)

        var data = parm;
        data.access_token = token;   
            //데이터 받는 로직 필요
            $.ajax({
            url : selectMapMarker
            , async : true
            , type : "POST"
            , cache:false
            , timeout : 30000 
            , data : JSON.parse(JSON.stringify(data))
            , dataType : "json"
            , xhrFields: {
                    withCredentials: false
            }
            , beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            }
            , success : function (data, statusText, xhr) { 
                if(data.data.length > 0) {
                    //console.log(mapData)
                    //console.log(data.data)
                    var color = new Array();
                    var dataDiv = "";
                   /* var dataDiv ='<div class="bz_data2 objData" style="margin-right:0px;margin-left:0px">'
                    dataDiv+='<div class="row">'
                    dataDiv+='<div>카테고리</div>'                    
                    dataDiv+='<div>XnYn</div>'
                    dataDiv+='<div>XxYx</div>'
                    dataDiv+='<div>표기색상</div>'                    
                    dataDiv+='</div>'
                    dataDiv+='</div>'
                    dataDiv+='<div class="bz_data5 objData" style="height:110px;overflow-y:scroll;overflow-x:hidden;margin: 0 -15px;">'*/
                    //이미지 크기 구하기
                    var imgWdth = mapData.imgWdth
                    var imgHgt = mapData.imgHgt                    
                   
                    setTimeout(function() {
                        var resImgWdth = $("#resImg").width();
                        var resImgHgt = $("#resImg").height();
                        var wdthRate = resImgWdth/imgWdth;
                        var hgtRate = resImgHgt/imgHgt;

                        $.each(data.data, function(i, item) {
                            var xMin = Math.floor(item.bndbxXMin * wdthRate);
                            var xMax = Math.floor(item.bndbxXMax * wdthRate);
                            var yMin = Math.floor(item.bndbxYMin * hgtRate);
                            var yMax = Math.floor(item.bndbxYMax * hgtRate);
                            var boxWdth = xMax - xMin;
                            var boxHght = yMax - yMin;

                            var rndColor = Math.round( Math.random() * 0xFFFFFF ).toString(16);
                            var colorChk = rndColor.length == 6?rndColor:"0"+rndColor;
                            color.push("#"+colorChk);
                            dataDiv +='<div class="row objData">';
                            dataDiv +='<div>'+item.objNm+'</div>';
                            dataDiv +='<div>'+xMin+', '+yMin+'</div>';
                            dataDiv +='<div>'+xMax+', '+yMax+'</div>';
                            dataDiv +='<div style="background:'+color[i]+'"></div>';
                            dataDiv +='</div>';    

                            //바인딩 박스 그리기
                            var bindBox ="<div class='objData' title='"+item.objNm+"' style='position:absolute;border: 3px solid "+color[i]+";width:"+boxWdth+"px;height:"+boxHght+"px;top:"+yMin+"px;left:"+xMin+"px;cursor:pointer'></div>";
                            $("#botRes").prepend(bindBox);
                        });
                        
                        $(".bz_data5").append(dataDiv);                
                    }, 500); 
                }  
                
            }
            , error : function (xhr, statusText, errorThrown) { 
                console.log("error status :: " + xhr.status + " || statusText :: " + xhr.responseText + " || errorThrown :: " + errorThrown);
            }
        });
    }

    function apiAccess(){           
            var token;
            $.ajax({
            url : apiUri
            , async : false
            , type : "POST"
            , cache:false
            , timeout : 30000 
            , data : JSON.parse(JSON.stringify({"client_id":"CSPI-Client", "grant_type":"password", "username":"2020000001"}))
            , dataType : "json"
            , crossDomain: true
            , xhrFields: {
                    withCredentials: false
            }
            , beforeSend: function (xhr) {
                var hgt = $("html").height();
                $("#loadingBlock").css("height",hgt);
                $("#loadingBlock").show();
                xhr.setRequestHeader("Accept", "application/json");
            }
            , success : function (xhr, statusText) {
               // console.log("xhr.access_token :: " + xhr.access_token);
               // console.log("xhr.token_type :: " + xhr.token_type);
               // console.log("xhr.refresh_token :: " + xhr.refresh_token);
               // console.log("xhr.expires_in :: " + xhr.expires_in);
               // console.log("statusText :: " + statusText);
                
                data = '';
                data += '"data":"[]"}';
                var param = {"access_token":xhr.access_token, "data":data};
               // console.log("param :: " + param);
                token = xhr.access_token;
            }
            , error: function(xhr, statusText, errorThrown) {
               // console.log("xhr :: " + xhr.status);
               //console.log("statusText :: " + statusText);
               // console.log("errorThrown :: " + errorThrown);
            }, complete : function() {
                $("#loadingBlock").hide();  
            }
        });

        return token;
    }
    </script>
