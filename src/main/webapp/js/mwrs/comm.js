/*통신을위한 토큰*/
function apiSelctBoxAccess(argUrl){           
		var token;
		$.ajax({
		url : argUrl
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
		}
	});

	return token;
}

/*셀렉트박스 만들기 
  headerGb : //M:ZZ_CODE_M , D:ZZ_CODE_D
  objId : 셀렉트박스 ID
  initVal : 첫번째 박스값이 S:선택 A:전체 
  tmpHomeUrl : REST API통신시 필요한 도메임 운영에 올라갈때는 필요없음 (공백으로 넘겨줘야됨)
*/
function initCodeSelectBox(mstCode,tmpRefCd1,tmpAsync,headerGb,objId,initVal,tmpHomeUrl){
  
	var apiUri     = tmpHomeUrl+"/api/oauth/token";
  
	var token = apiSelctBoxAccess(apiUri);    

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
		  url : tmpHomeUrl+"/api/api/selectRestCodeList"
		, async : tmpAsync //true, false
		, type : "POST"
		, cache:false
		, timeout : 30000 
		, data : { 
					 cmpyCd   : '0000'  //그룹코드Id
					,mstCd    : mstCode //마스터코드Id
					,refCd1   : tmpRefCd1 //REF_CD
					,headerGb : headerGb //M:ZZ_CODE_M , D:ZZ_CODE_D
					,access_token : token
					
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
			
			if(initVal != "" && initVal != null && initVal != undefined){
				strHtml+="<option value=''>"+initText+"</option>";
			}
			if(data.data.length > 0) {
				$.each(data.data, function(i, item) {
					strHtml+="<option value="+item.code+">"+item.name+"</option>";
					$("#"+objId).html(strHtml);
				});
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