<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%>
<!-- /.card -->
<!-- Horizontal Form -->
<div class="card">
    <!--               <div class="card-header"> -->
    <!--                 <h3 class="card-title">Horizontal Form</h3> -->
    <!--               </div> -->
    <!-- /.card-header -->
    <!-- form start -->
    <!-- /.card-header -->
    <div class="card-body">

        <div class=" row">
            <div class="col-10">





                <button type="button" id="btnInsert" class="btn btn-primary float-right">
                    <i class="fas fa-search"></i>목록
                </button>
                <button type="button" id="btnAllInsert" class="btn btn-primary float-right">
                    <i class="fas fa-search"></i>승인요청
                </button>
                <button type="button" id="btnAdd" class="btn btn-primary float-right">
                    <i class="fas fa-search"></i>삭제
                </button>
                <button type="button" id="btnCom" class="btn btn-primary float-right">
                    <i class="fas fa-search"></i>저장
                </button>
                <button type="button" id="btnDel" class="btn btn-primary float-right">
                    <i class="fas fa-search"></i>신규
                </button>
                <button type="button" id="btnExel" class="btn btn-primary float-right">
                    <i class="fas fa-search"></i>조회
                </button>

            </div>
        </div>


        <!-- /.card-body -->
    </div>
    <div class="card-body">
        <div class=" row">
            <div class="col-10">
                <div class="form-group row">
                    <label for="srchUserNm" class="col-1 col-form-label right">거래처명 </label>
                    <div class="col-2">
                        <input type="text" id="srchUserNm" data-enter="search" name="userNm" class="form-control"
                            placeholder="거래처명 " />
                    </div>

                    <label for="srchUserId" class="col-1 col-form-label right">대표자성명 </label>
                    <div class="col-2">
                        <input type="text" id="srchUserId" data-enter="search" name="userId" class="form-control"
                            placeholder="대표자성명 " />
                    </div>

                    <label for="srchUserId" class="col-1 col-form-label right">사업자번호 </label>
                    <div class="col-2">
                        <input type="text" id="srchUserId" data-enter="search" name="userId" class="form-control"
                            placeholder="사업자번호 " />
                    </div>

                    <label for="srchUserId" class="col-1 col-form-label right"> </label>
                    <div class="col-2"></div>

                    <label for="srchUserId" class="col-1 col-form-label right">등록일자 </label>
                    <div class="col-2">
                        <input type="text" id="srchUserId" data-enter="search" name="userId" class="form-control"
                            placeholder="등록일자  " />
                    </div>

                    <label for="srchUserId" class="col-1 col-form-label right">등록자 </label>
                    <div class="col-2">
                        <input type="text" id="srchUserId" data-enter="search" name="userId" class="form-control" placeholder="등록자 " />
                    </div>

                    <label for="srchUserId" class="col-1 col-form-label right">상태 </label>
                    <div class="col-2">
                        <input type="text" id="srchUserId" data-enter="search" name="userId" class="form-control" placeholder="상태 " />
                    </div>

                    <label for="srchUserId" class="col-1 col-form-label right"> </label>
                    <div class="col-2"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /.card -->


<div class="row">

    <%-- <div class="col-12">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">사용자</h3>

                <div class="card-tools">
                    <div class="input-group input-group-sm">
                     <div class="btn-group"> 
                        <button type="button" data-grid-id="grdList" onclick="excelDownload(this.id)" id="btnExcel" class="btn btn-default btn-sm"><i class="fas fa-download"></i> 엑셀다운로드</button>
                        <button type="button" data-grid-id="grdList" data-click="newUser" id="btnAddList" class="btn btn-success btn-sm"><i class="fas fa-plus"></i>추가</button>
                        <button type="button" data-grid-id="grdList" data-click="saveUser" id="btnSaveList"  class="btn btn-info btn-sm"><i class="fas fa-save"></i> 저장</button>
                        <button type="button" data-grid-id="grdList" data-click="removeUser" id="btnDelList" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> 삭제</button>
                     </div>
                    </div>
                </div>
                <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">
                <div id="divGrid"   data-grid-id="grdList" data-pagenation="Y" data-type="grid" data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/user/user.xml'/>">
                </div>
            </div>

        </div>
        <!-- /.card -->
    </div> --%>
    <!-- /.col -->
    <div class="col-12">
        <div class="card card-primary card-outline">
            <div class="card-header">
                <h3 class="card-title">General</h3>

                <div class="card-tools">
                    <div class="input-group input-group-sm"></div>
                </div>
                <!-- /.card-tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body p-0">

                <input type="hidden" name="empNo" id="empNo" value="${user.empNo }" />
                <div class="card-body">
                    <div class="form-group row">
                        <label for="userNm" class="col-1 col-form-label right">사업자등록번호</label>
                        <div class="input-group col-2">
                            <input type="hidden" id="chkId" /> <input type="text" required="required"
                                value='<c:out value="${user.userNm }"/>' class="form-control" id="userNm" name="userNm"
                                placeholder="사업자등록번호">
                            <div class="input-group-append">
                                <span class="input-group-text" id='checkDupl' data-click="checkDupl"><i class="fas fa-check"></i>
                                    중복체크</span>
                            </div>
                        </div>

                        <label for="userId" class="col-1 col-form-label right">거래처명</label>
                        <div class="input-group col-2">

                            <input required="required" type="text" data-change="resetCheckId" class="form-control"
                                value='<c:out value="${user.userId }" />' id="userId" name="userId" placeholder="거래처명">

                        </div>

                        <label for="pwd" class="col-1 col-form-label right">국가코드</label>
                        <div class="col-2">
                            <input type="password" autocomplete="false" required="required" maxLength="16"
                                class="js-mytooltip-pw form-control" id="pwd" name="pwd" placeholder="국가코드"
                                data-mytooltip-direction="bottom" data-mytooltip-dinamic-content="true"
                                data-mytooltip-action="focus" data-mytooltip-animate-duration="0" />
                        </div>

                        <label for="pwdCfg" class="col-1 col-form-label right">Account group</label>
                        <div class="col-2">
                            <input type="password" autocomplete="false" required="required" maxLength="16" class="form-control"
                                id="pwdCfg" name="pwdCfg" placeholder="Account group">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="emlAdr" class="col-1 col-form-label right">법인번호</label>
                        <div class="col-2">
                            <input type="email" value='<c:out value="${user.emlAdr }"/>' required class="form-control" id="emlAdr"
                                name="emlAdr" placeholder="법인번호">
                        </div>

                        <%-- <label for="mobileNo" class="col-1 col-form-label right">전화</label>
                        <div class="col-2">
                          <input type="text" value='<c:out value="${user.mobileNo }"/>' required class="form-control" id="mobileNo" name="mobileNo"  placeholder="전화">
                        </div> --%>

                        <label for="langGb" class="col-1 col-form-label right">약어</label>
                        <div class="col-2">
                            <code:commCode className="form-control" id="langGb" name="langGb" mstCd="LANG_GB"
                                selectedValue="${langGb }" />
                        </div>

                        <label for="niceAuthKey" class="col-1 col-form-label right">대표자</label>
                        <div class="col-2">
                            <input type="text" value='<c:out value="${user.niceAuthKey }"/>' required class="form-control"
                                id="niceAuthKey" name="niceAuthKey" placeholder="대표자">
                        </div>

                        <label for="lastLgiDt" class="col-1 col-form-label right">검색어</label>
                        <div class="col-2">
                            <input type="text" value='<c:out value="${user.lastLgiDt }"/>' required class="form-control"
                                id="lastLgiDt" name="lastLgiDt" readonly>
                        </div>
                    </div>

                    <div class="form-group row">

                        <label for="emlAdr" class="col-1 col-form-label right">전화번호</label>
                        <div class="col-2">
                            <input type="email" value='<c:out value="${user.emlAdr }"/>' required class="form-control" id="emlAdr"
                                name="emlAdr" placeholder="전화번호">
                        </div>

                        <%-- <label for="mobileNo" class="col-1 col-form-label right">전화</label>
                            <div class="col-2">
                              <input type="text" value='<c:out value="${user.mobileNo }"/>' required class="form-control" id="mobileNo" name="mobileNo"  placeholder="전화">
                            </div> --%>

                        <label for="langGb" class="col-1 col-form-label right">추가정보3</label>
                        <div class="col-2">
                            <code:commCode className="form-control" id="langGb" name="langGb" mstCd="LANG_GB"
                                selectedValue="${langGb }" />
                        </div>

                        <label for="niceAuthKey" class="col-1 col-form-label right">업종</label>
                        <div class="col-2">
                            <input type="text" value='<c:out value="${user.niceAuthKey }"/>' required class="form-control"
                                id="niceAuthKey" name="niceAuthKey" placeholder="업종">
                        </div>

                        <label for="lastLgiDt" class="col-1 col-form-label right">배송지</label>
                        <div class="col-2">
                            <input type="text" value='<c:out value="${user.lastLgiDt }"/>' required class="form-control"
                                id="lastLgiDt" name="lastLgiDt" readonly>
                        </div>
                    </div>

                    <div class="form-group row">

                        <label for="emlAdr" class="col-1 col-form-label right">Vendor</label>
                        <div class="col-2">
                            <input type="email" value='<c:out value="${user.emlAdr }"/>' required class="form-control" id="emlAdr"
                                name="emlAdr" placeholder="Vendor">
                        </div>

                        <label for="langGb" class="col-1 col-form-label right">추가정보4</label>
                        <div class="col-2">
                            <code:commCode className="form-control" id="langGb" name="langGb" mstCd="LANG_GB"
                                selectedValue="${langGb }" />
                        </div>

                        <label for="niceAuthKey" class="col-1 col-form-label right">업태</label>
                        <div class="col-2">
                            <input type="text" value='<c:out value="${user.niceAuthKey }"/>' required class="form-control"
                                id="niceAuthKey" name="niceAuthKey" placeholder="업태">
                        </div>

                        <label for="lastLgiDt" class="col-1 col-form-label right"></label>
                        <div class="col-2"></div>
                    </div>

                    <div class="form-group row">

                        <label for="emlAdr" class="col-1 col-form-label right">우편번호</label>
                        <div class="col-2">
                            <input type="email" value='<c:out value="${user.emlAdr }"/>' required class="form-control" id="emlAdr"
                                name="emlAdr" placeholder="우편번호">
                        </div>

                        <%-- <label for="mobileNo" class="col-1 col-form-label right">전화</label>
                            <div class="col-2">
                              <input type="text" value='<c:out value="${user.mobileNo }"/>' required class="form-control" id="mobileNo" name="mobileNo"  placeholder="전화">
                            </div> --%>

                        <label for="langGb" class="col-1 col-form-label right">주소</label>
                        <div class="col-2">
                            <code:commCode className="form-control" id="langGb" name="langGb" mstCd="LANG_GB"
                                selectedValue="${langGb }" />
                        </div>

                        <label for="niceAuthKey" class="col-1 col-form-label right">상세주소</label>
                        <div class="col-5">
                            <input type="text" value='<c:out value="${user.niceAuthKey }"/>' required class="form-control"
                                id="niceAuthKey" name="niceAuthKey" placeholder="대표자">
                        </div>

                        <label for="lastLgiDt" class="col-1 col-form-label right"></label>
                        <div class="col-2"></div>
                    </div>

                    <div class="form-group row">

                        <label for="emlAdr" class="col-1 col-form-label right">Street 2</label>
                        <div class="col-2">
                            <input type="email" value='<c:out value="${user.emlAdr }"/>' required class="form-control" id="emlAdr"
                                name="emlAdr" placeholder="Street 2">
                        </div>

                        <%-- <label for="mobileNo" class="col-1 col-form-label right">전화</label>
                            <div class="col-2">
                              <input type="text" value='<c:out value="${user.mobileNo }"/>' required class="form-control" id="mobileNo" name="mobileNo"  placeholder="전화">
                            </div> --%>

                        <label for="langGb" class="col-1 col-form-label right">Street 3</label>
                        <div class="col-2">
                            <input type="text" value='<c:out value="${user.niceAuthKey }"/>' required class="form-control"
                                id="niceAuthKey" name="niceAuthKey" placeholder="Street 3">
                        </div>

                        <label for="niceAuthKey" class="col-1 col-form-label right">메일주소</label>
                        <div class="col-5">
                            <input type="text" value='<c:out value="${user.niceAuthKey }"/>' required class="form-control"
                                id="niceAuthKey" name="niceAuthKey" placeholder="메일주소">
                        </div>

                        <label for="lastLgiDt" class="col-1 col-form-label right"></label>
                        <div class="col-2"></div>
                    </div>

                    <div class="form-group row">

                        <label for="emlAdr" class="col-1 col-form-label right">이전계정번호</label>
                        <div class="col-2">
                            <input type="email" value='<c:out value="${user.emlAdr }"/>' required class="form-control" id="emlAdr"
                                name="emlAdr" placeholder="이전계정번호">
                        </div>

                        <%-- <label for="mobileNo" class="col-1 col-form-label right">전화</label>
                            <div class="col-2">
                              <input type="text" value='<c:out value="${user.mobileNo }"/>' required class="form-control" id="mobileNo" name="mobileNo"  placeholder="전화">
                            </div> --%>

                        <label for="langGb" class="col-1 col-form-label right">조정계정</label>
                        <div class="col-2">
                            <input type="text" value='<c:out value="${user.niceAuthKey }"/>' required class="form-control"
                                id="niceAuthKey" name="niceAuthKey" placeholder="조정계정">
                        </div>

                        <label for="niceAuthKey" class="col-1 col-form-label right">정렬키</label>
                        <div class="col-2">
                            <input type="text" value='<c:out value="${user.niceAuthKey }"/>' required class="form-control"
                                id="niceAuthKey" name="niceAuthKey" placeholder="정렬키">
                        </div>

                        <label for="lastLgiDt" class="col-1 col-form-label right">지급조건</label>
                        <div class="col-2">
                            <input type="text" value='<c:out value="${user.niceAuthKey }"/>' required class="form-control"
                                id="niceAuthKey" name="niceAuthKey" placeholder="지급조건">
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.card-body -->
        </div>

    </div>
    <!-- /.card -->
</div>

<div class="col-12">
    <div class="card card-primary card-outline">
        <div class="card-header">
            <h3 class="card-title">Sales</h3>
            <div class="col-2">
                <button type="button"data-click="btnAdd" id="btnAdd" class="btn btn-primary float-right">
                    <i class="fas fa-search"></i>추가
                </button>
            </div>
        </div>
        <!-- /.card-header -->
        <div class="card-body p-0">
            <div id="divGrid" style="height: 320px" data-grid-id="grdList" data-pagenation="Y" data-type="grid"
                data-grid-callback="onGridLoad" data-tpl-url="<c:url value='/gridTemplate/test/testTwo.xml'/>"></div>
        </div>

    </div>
    <!-- /.card -->
</div>




<!-- /.row -->
<script>
function btnAdd(){
    var url = "<c:url value='/${currentMenuId}/testPopup${urlSuffix}'/>";
    Utilities.windowOpen(url,"testPopup",1920,300);
}
	
</script>