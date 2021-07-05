<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%> 
<link rel="stylesheet" href="<c:url value='/css/mwrs/style.css'/>" />

<div class="row">
<section id="team" class="team ">
            <div class="" data-aos="fade-up">
                <div class="section-title ">
                    <h3>AI <span>판독</span></h3>
                    <div>등록한 수중촬영 이미지를 AI가 판독합니다.</div>
                </div>
            </div>
            <div class="" data-aos="fade-up">
                <div class="searchBox">
                    <form id="searchParm">
                        <div style="float:left;">
                            <label for="shootDevice">촬영기기</label>
                            <select id="shootDevice" name="shootDevice">
                                <option value="sonar">소나</option>
                                <option value="underPhoto">수중촬영</option>
                            </select>
                            <label for="seaRegion">이미지 파일</label>
                            <input type="file" name="imgFile" id="imgFile" accept="image/*"/>
                        </div>
                        <div style="float:right">
                            <button id="registBtn" type="button">등록</button>
                        </div>
                        <div style="clear:both"></div>
                    </form>
                </div>
                
                <div class="">
                    <div class="col50">
                        <div id="topRes" class="">
                            <img src="images/mwrs/sonar/noimg.png" alt="이미지" class="responImg" id="resImg">
                        </div>                        
                    </div>
                    <div class="col50">
                        <div class="bz_data">
                            <div class="row">
                                <div>조사해역</div>
                                <div>위도</div>
                                <div>경도</div>
                                <div>카테고리</div>
                                <div>수심</div>
                            </div>
                        </div>
                    </div>
                    <div style="clear:both"></div>
                </div>
                
            </div>
        </section><!-- End Team Section -->

 
</div>
<!-- /.row -->
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

$(document).ready(function(){
init();

function init(){
}

//검색함수
function search(){
    var parm = $("#searchParm").serializeObject();
    console.log(parm)
    //데이터 받는 로직 필요
    var data =[
                {key:"01",y:34.80001,x:128.31021,sea:"남해",category:"타이어",deep:"15m",img:"images/mwrs/sonar/01.jpeg"},
                {key:"02",y:35.08152,x:128.52121,sea:"남해",category:"어망",deep:"11m",img:"images/mwrs/sonar/02.jpg"},
                {key:"03",y:35.08352,x:128.48621,sea:"남해",category:"어망",deep:"11m",img:"images/mwrs/sonar/03.jpg"},
                {key:"04",y:35.08552,x:128.47121,sea:"남해",category:"타이어",deep:"8m",img:"images/mwrs/sonar/04.jpg"},                        
            ]

    setRowData(data);
}

//조회이벤트
$("#searchBtn").on("click",function(){
    search();
});

//로우 데이터 세팅
function setRowData(data){
     $(".bz_data .row:not(:first-child)").remove();
     var str = "";
     
     for (var i = 0, ii = data.length; i < ii; i++) {
        var rowData = data[i];
        str += "<div class='row rowData' data-key='"+rowData.key+"' data-img='"+rowData.img+"'>";
        str += "<div>"+rowData.sea+"</div>";
        str += "<div>"+rowData.y+"</div>";
        str += "<div>"+rowData.x+"</div>";
        str += "<div>"+rowData.category+"</div>";
        str += "<div>"+rowData.deep+"</div>";
        str += "</div>";
     }

     $(".bz_data").append(str);
}

$(".bz_data").on("click",".rowData",function(){
   var imgSrc = $(this).data().img;
   $("#resImg").attr("src",imgSrc);

});

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
        }
    }
});
});
</script>