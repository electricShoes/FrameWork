// JavaScript Document

$(document).ready(function () {
    $('input:checkbox').prop('checked', false);

    $("#termsAgree, #policyAgree").focus(function () {
        $(this).siblings().addClass("focus");

    }).blur(function () {
        $(this).siblings().removeClass("focus");
    }).click(function () {
        $(this).siblings().toggleClass("on");
        checkAgreeState();
    });
	//메뉴 닫기 버튼
	$('a.topmenu').click(function(){
		$('.leftMenu').removeClass('on').attr('style','');
		$('.menuW').removeClass('on').attr('style','')
		$('.menuW li a').attr('style','background-position:center 21px');
		$('.depth_01').attr('style','display:none');
		$('.depth_02 li a').attr('style','');
		$('.contents').attr('style','margin-left:91px');
	})

	$('.swiper-slide').click(function(){
		$('.swiper-slide').removeClass('on');
		$(this).addClass('on');
	})

	$('.menuW > li > a').click(function(){
		$('.menuW > li').removeClass('on');
		$('.depth_01').hide();
		$(this).parents().addClass('on');
		$('.contents').attr('style','margin-left:211px');
		$(this).siblings('.depth_01').show();
		$('.leftMenu').css({'width':'210px'});
		$('.menuW>li>a').css({
			'padding':'15px 0 15px 49px',
			'text-align' : 'left',
			'background-position':'14px center'
		})
	})

	$('.depth_01 > li > a').click(function(){
		$('.depth_02').hide();
		$('.depth_01 > li > a').removeClass('on');
		$('.depth_01 > li > a').css({'background':'none'})
		$('.depth_02 > li > a').removeClass('on');
		$(this).addClass('on');
		$(this).siblings('.depth_02').show();
		$(this).next('.depth_02').children('li:first-child').children().addClass('on');
		if($(this).siblings('ul').hasClass('depth_02')){
			$(this).css({'background':'url("common/images/submenu_arr.png")','background-repeat' : 'no-repeat','background-position':'158px center'})
		}else{
			$('.depth_01 > li > a').css({'background':'none'})
		}
	})

	$('.depth_02 > li > a').click(function(){
		$('.depth_02 > li > a').removeClass('on');
		$(this).addClass('on');
	})

	$('.form-control').SumoSelect();

	$('.popConW').css({
		'margin-left' : $('.popConW').width()/2 *-1,
		'margin-top' : $('.popConW').height()/2 *-1,
	})
	$('.popClose').click(function(){
		$('.popup').hide();
	})
	
	//탭 활성화	
	$('.depth_01 li a.depth_01_list').click(function(){
		var depth1_id = $(this).attr('id'); //1뎁스 아이디
		var depth1_text = $('#'+depth1_id).text(); //1뎁스 텍스트
		var menu = $('#'+depth1_id).parent('li').find('.depth_02 li'); //2뎁스
		var menuCount = $('#'+depth1_id).parent('li').find('.depth_02 li').length; //2뎁스 갯수
		
		//동일 1뎁스 메뉴가 없다면 생성
		if($("#depth1_btn").find("#"+depth1_id+"_btn").length == 0) {  
			$('.conSt').attr('style','display:none');
 			$('#depth1_btn').append('<li id="'+depth1_id+'_btn"><p>'+depth1_text+'</p><a href="#url" class="tab_closeBtn"><img src="common/images/tab_close.png" /></a><a href="#url" class="tab_boxBtn"><img src="common/images/tab_box.png" /></a></li>'); //버튼 생성
			$('#conWrap').append('<div class="'+depth1_id+' conSt"><ul class="tabListSty01" id="tabList"></ul><div class="contW d_t"></div></div>'); //중간에 탭과 컨텐츠를 넣을 공간생성(메뉴의 아이디와 동일한 클래스명)
			
			//탭 메뉴 생성 
			for(i = 1; i <= menuCount; i++) {
				var menu_current = $("#"+depth1_id).parent('li').find('.depth_02 li').eq(i-1).find('a');
				var menu_id =menu_current.attr('id');//메뉴에서 아이디 가져옴
				var menu_txt =menu_current.text(); //메뉴에서 텍스트를 가져옴
				$('#tabList').append('<li id="'+menu_id+'_tab"><a href="#url">'+menu_txt+'</a>'); // 탭을 생성하고 아이디와 텍스트를 넣음
				$('.contW').append('<div class="typeBox" id="'+menu_id+'_con" style="display:none"></div>'); // div를 생성
				$('.contW .typeBox:first-child').attr('style','display:block'); //첫번째 div만 보여주고
				$('#tabList li:first-child').find('a').addClass('on');	//탭도 첫번째에 on 넣어주기
			}
		} 
		
		//탭 클릭시 활성화와 컨텐츠 보여주기 
		$('#tabList li').click(function(){ 
			var tab_id = $(this).attr('id');
			tab_id = tab_id.split("_");
			var con_id = tab_id[0]+"_"+tab_id[1]+"_con";
			var leftMenu_id = tab_id[0]+"_"+tab_id[1];
			$('#tabList li a').removeClass('on');$(this).find('a').addClass('on'); //탭메뉴 활성화
			$('.contW .typeBox').attr('style','display:none');
			$("#"+con_id).attr('style','display:block'); // 컨텐츠 영역활성화
			$('.depth_02_list').removeClass('on');$("#"+leftMenu_id).addClass('on'); //레프트메뉴 활성화
		});
		
		//왼쪽메뉴 클릭시 탭 활성화
		$('.depth_02_list').click(function(){  
			var tab_choice = $(this).attr('id');
			$('#tabList li a').removeClass('on');$("#"+tab_choice+"_tab a").addClass('on');	
			$('.contW .typeBox').attr('style','display:none');
			$("#"+tab_choice+"_con").attr('style','display:block');
		});
		
		//하단 1뎁스 버튼 클릭시 활성화
		$('.tab_boxBtn').click(function(){ 
			$('.conSt').attr('style','display:none');
			var depth1_id = $(this).parent('li').attr('id');
			depth1_id = depth1_id.split("_");
			depth1_id = depth1_id[0]+"_"+depth1_id[1];
			$('.'+depth1_id).attr('style','display:block');
		});
		//하단 1뎁스 버튼 클릭시 종료
		$('.tab_closeBtn').click(function(){ 
			var depth1_id = $(this).parent('li').attr('id');
			depth1_id = depth1_id.split("_");
			depth1_id = depth1_id[0]+"_"+depth1_id[1];
			$('.'+depth1_id).attr('style','display:none');
		});
		
	 });
	 
	
	// 기간 탭
	$('.dateSt').click(function(){ 
		$('.dateSt').removeClass('on');
		$(this).addClass('on');
	});

});

