<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="code" uri="/WEB-INF/tlds/ezTagLib.tld"%>
<!-- /.card -->
<!-- Horizontal Form -->
<div class="card card-info">
	<div class="card-header">
		<h3 class="card-title">그리드 기본정보</h3>
	</div>
	<!-- /.card-header -->
	<!-- form start -->
	<form class="form-horizontal" id="frmMeta">
		<input id="deletable" name="deletable" type="hidden" value="N" /> <input id="rollbackable" name="rollbackable" type="hidden" value="N" /> <input id="appendable" name="appendable" type="hidden" value="N" />
		<div class="card-body">
			<div class="form-group row">
				<label for="readonly" class="col-1 col-form-label right">읽기 전용</label>
				<div class="col-1">
					<select id="readOnly" name="readOnly" class="form-control">
						<option value="Y">Y</option>
						<option value="N" selected>N</option>
					</select>
				</div>
				<label for="showCheckbox" class="col-1 col-form-label right">체크박스 표시</label>
				<div class="col-1">
					<select id="showCheckbox" name="showCheckbox" class="form-control">
						<option value="Y" selected>Check</option>
						<option value="R">Radio</option>
						<option value="N">N</option>
					</select>
				</div>
				<label for="showNumber" class="col-1 col-form-label right">줄번호보이기</label>
				<div class="col-1">
					<select id="showNumber" name="showNumber" class="form-control">
						<option value="Y" selected>Y</option>
						<option value="N">N</option>
					</select>
				</div>
				<label for="showStateBar" class="col-1 col-form-label right">상태바 표시</label>
				<div class="col-1">
					<select id="showStateBar" name="showStateBar" class="form-control">
						<option value="Y" selected>Y</option>
						<option value="N">N</option>
					</select>
				</div>
				<label for="showHeaderSum" class="col-1 col-form-label right">헤더합계표시</label>
				<div class="col-1">
					<select id="showHeaderSum" name="showHeaderSum" class="form-control">
						<option value="Y">Y</option>
						<option value="N" selected>N</option>
					</select>
				</div>
				<label for="showFooter" class="col-1 col-form-label right">footer 표시</label>
				<div class="col-1">
					<select id="showFooter" name="showFooter" class="form-control">
						<option value="Y">Y</option>
						<option value="N" selected>N</option>
					</select>
				</div>

			</div>


			<div class="form-group row">
				<label for="insertable" class="col-1 col-form-label right">Insert가능</label>
				<div class="col-1">
					<select id="insertable" name="insertable" class="form-control">
						<option value="Y">Y</option>
						<option value="N" selected>N</option>
					</select>
				</div>
				<label for="editable" class="col-1 col-form-label right">수정가능</label>
				<div class="col-1">
					<select id="editable" name="editable" class="form-control">
						<option value="Y" selected>Y</option>
						<option value="N">N</option>
					</select>
				</div>
				<label for="rowSelection" class="col-1 col-form-label right">줄전체선택</label>
				<div class="col-1">
					<select id="rowSelection" name="rowSelection" class="form-control">
						<option value="Y">Y</option>
						<option value="N" selected>N</option>
					</select>
				</div>
				<label for="gridfitStyle" class="col-1 col-form-label right">컬럼자동크기</label>
				<div class="col-1">
					<select id="gridfitStyle" name="gridfitStyle" class="form-control">
						<option value="none">없음</option>
						<option value="even">비율로 맞춤</option>
						<!-- 						<option value="evenfill" selected>한페이지에 맞춤</option> -->
					</select>
				</div>
				<label for="columnMove" class="col-1 col-form-label right">컬럼이동가능</label>
				<div class="col-1">
					<select id="columnMove" name="columnMove" class="form-control">
						<option value="Y">Y</option>
						<option value="N" selected>N</option>
					</select>
				</div>
				<label for="setSort" class="col-1 col-form-label right">컬럼정렬</label>
				<div class="col-1">
					<select id="setSort" name="setSort" class="form-control">
						<option value="exclusive">컬럼클릭시</option>
						<option value="none" selected>없음</option>
					</select>
				</div>
			</div>
		</div>
	</form>
</div>

<div class="card card-secondary">
	<div class="card-header">
		<h3 class="card-title">테이블 정보</h3>
		<div class="card-tools">
			<div class="input-group input-group-sm">
				<input type="text" name="tableName" id="tableName" class="form-control" style="min-width: 200px" placeholder="table name">
				<div class="input-group-append">
					<div class="btn btn-primary" id="btnSearch">
						<i class="fas fa-search"></i>
					</div>
				</div>
				<button type="button" class="btn btn-default btn-sm" id="btnAdd" data-click="addColumn">
					<i class="fas fa-plus"></i>
				</button>
				<button type="button" class="btn btn-success btn-sm" id="btnSave">
					<i class="fas fa-save"></i>
				</button>
				<button type="button" class="btn btn-info btn-sm" id="btnDb">
					<i class="fas fa-database"></i>
				</button>
				<button type="button" class="btn btn-danger btn-sm" id="btnDelete">
					<i class="fas fa-trash"></i>
				</button>
			</div>


		</div>
	</div>
	<!-- /.card-header -->
	<div class="card-body">
		<form class="form-horizontal" id="frmTable">
			<table id="tblColInfo" class="table-bordered table-hover" style="text-align: center">
				<col style="width: 2%" />
				<col style="width: 8%" />
				<col style="width: 6%" />
				<col style="width: 6%" />
				<!-- 			     28 -->

				<col style="width: 5%" />
				<col style="width: 5%" />
				<col style="width: 6%" />
				<col style="width: 6%" />
				<!--                  22 -->
				<col style="width: 5%" />
				<col style="width: 7%" />
				<col style="width: 7%" />
				<col style="width: 5%" />
				<col style="width: 5%" />

				<!--                  26 -->
				<col style="width: 6%" />
				<col style="width: 5%" />
				<col style="width: 5%" />
				<col style="width: 5%" />

				<col style="width: 7%" />
				<!--                  23 -->
				<thead>
					<tr>
						<th></th>
						<th>컬럼명</th>
						<th>Column</th>
						<th>type</th>
						<th>max-length</th>
						<th>width</th>
						<th>text-align</th>
						<th>필수</th>
						<th>PK</th>
						<th>읽기전용</th>
						<th>기본값</th>
						<th>자리수</th>
						<th>소수점</th>
						<th>format</th>
						<th>대분류</th>
						<th>상위코드</th>
						<th>코드레벨</th>
						<th>감추기</th>
					</tr>
				</thead>
				<tbody id="tbdCols">

				</tbody>
			</table>
		</form>
	</div>

</div>
<!-- Large modal -->

<!-- <div class="modal fade" id="xmlModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true"> -->
<!--   <div class="modal-dialog modal-lg"> -->
<!--     <div class="modal-content"> -->
<!--         <textarea class="form-control"  id="printXml"   style="margin-top: 0px; margin-bottom: 0px; height: 500px;"></textarea> -->
<!--     </div> -->
<!--   </div> -->
<!-- </div> -->


<div class="modal fade" id="xmlModal">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Large Modal</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>
					<textarea class="form-control" id="printXml" style="margin-top: 0px; margin-bottom: 0px; height: 500px;"></textarea>
				</p>
			</div>
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<!-- 				<button type="button" class="btn btn-primary" id="copyClip">Copy</button> -->
				<button type="button" class="btn btn-primary" id="saveFile">Save</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<script type="text/javascript">
	var _INDEX_ = 0;
	var _TABLE_INFO = null;
	var _TABLE_NAME = null;
	var _SAVE_FILE_NAME = null;
	$("#tableName").keydown(function(key) {
		if (key.keyCode == 13) {//키가 13이면 실행 (엔터는 13)
			searchTable();
		}
	});
	
	/************************************
	 * 조회 클릭 이벤트
	 ************************************/
	$("#btnSearch").click(function() {
		searchTable();
	});
	
	/************************************
	 * 저장 클릭 이벤트
	 ************************************/
	$("#btnSave").click(function() {
		generateXml();
	});
	
	/************************************
	 * DB 클릭 이벤트
	 ************************************/
	$("#btnDb").click(function() {
		generateDbXml();
	});
	
	/************************************
	 * 삭제 클릭 이벤트
	 ************************************/
	$("#btnDelete").click(function() {
		$("input[type=checkbox]:checked").each(function() {
			var colId = $(this).attr("data-col-id");
			$("tr[data-col-id=" + colId + "]").remove();
		});
	});
	
	/************************************
	 * 클립보드 복사 클릭 이벤트
	 ************************************/
	$("#copyClip").click(function() {
		$("#printXml").select();
		document.execCommand('copy');
	});
	
	/************************************
	 * 파일 저장 클릭 이벤트
	 ************************************/
	$("#saveFile").click(function() {
		Utilities.downloadText($("#printXml").val(), _SAVE_FILE_NAME);
	});

	/************************************
	 * 컬럼 추가
	 ************************************/
	function addColumn() {
		++_INDEX_;
		var id = "COLUMN" + _INDEX_;
		var data = {
			columnId : id,
			field : id,
			name : ""
		};
		appendRow(data);

	}
	
	/************************************
	 * 테이블 조회
	 ************************************/
	function searchTable() {
		_TABLE_INFO = null;
		_TABLE_NAME = null;
		var url = "<c:url value='/${currentMenuId}/selectColInfo${urlSuffix}'/>";
		var param = {
			tableName : $("#tableName").val()
		};

		if (!param.tableName) {
			alert("테이블명을 입력하세요");
			return false;
		}
		Utilities.getAjax(url, param, true, function(data, result) {
			if (Utilities.processResult(data, result, "에러발생.")) {
				_TABLE_INFO = data;
				_TABLE_NAME = param.tableName.toUpperCase();
				parseTable(data);
			}
		});
	}
	
	/************************************
	 * 행 추가
	 ************************************/
	function parseTable(list) {
		$("#tbdCols").html("");
		for (var i = 0; i < list.length; i++) {
			var data = convertColumn(list[i]);
			appendRow(data);
		}
	}
	
	/************************************
	 * 컬럼 옵션 지정
	 ************************************/
	function convertColumn(col) {
		var data = {
			columnId : col.columnId,
			field : Utilities.convert2CamelCase(col.columnName),
			name : col.comments,
			required : col.nullable == "Y" ? "N" : "Y",
			readOnly : col.pk ? "Y" : "N",
			pk : col.pk ? "Y" : "N"
		};
		getColType(col, data);
		return data;
	}
	
	/************************************
	 * 컬럼 타입 지정
	 ************************************/
	function getColType(col, data) {
		var dataType = col.dataType.toUpperCase();
		data.defaultValue = col.dataDefault;
		data.textAlignment = "left";
		if (col.columnName.indexOf("_YN") > 0) {
			data.type = "checkbox";
			data.textAlignment = "center";
			data.maxLength = col.dataLength;
		} else if (dataType.indexOf("CHAR") > -1 || dataType == "CLOB") {
			data.type = "char";
			if (dataType != "CLOB")
				data.maxLength = col.dataLength;
		} else if (dataType == "NUMBER") {
			data.textAlignment = "right";
			var length = col.dataPrecision;
			var place = col.dataScale;
			data.digit = length - place;
			data.place = place;
			if (place == 0) {
				data.type = "int";
				data.maxLength = length;

			} else {
				data.type = "number";
				data.maxLength = length + 1;
			}
		} else if (dataType == "DATE" || dataType == "TIMESTAMP") {
			data.textAlignment = "center";
			data.type = "date";
		} else
			data.taype = "char";
	}

	/************************************
	 * 행 추가
	 ************************************/
	function appendRow(data) {
		var tbdCols = $("#tbdCols");
		var tr = $("<tr/>");
		tr.attr("data-col-id", data.columnId);
		tbdCols.append(tr);
		var td = $("<td/>");
		tr.append(td);
		var element = $('<input type="checkbox" />');
		element.attr("data-col-id", data.columnId);
		td.append(element);

		appendColName(tr, data);
		appendColField(tr, data);
		appendColType(tr, data);
		appendColLength(tr, data);
		appendColWidth(tr, data);
		appendColAlign(tr, data);
		appendColRequired(tr, data);
		appendColPk(tr, data);
		appendColReadOnly(tr, data);
		appendColDefault(tr, data);
		appendColDigit(tr, data);
		appendColPlace(tr, data);
		appendColPattern(tr, data);
		appendColCommCode(tr, data);
		appendColHidden(tr, data);

	}
	
	/************************************
	 * 컬럼명 추가
	 ************************************/
	function appendColName(tr, data) {
		var td = $("<td/>");
		td.attr("data-col-id", data.columnId);
		var element = $('<input type="text" class="form-control" name="colName"  />');
		element.attr("data-col-id", data.columnId);
		element.attr("data-col-type", "name");
		element.val(data.name);
		td.append(element);
		tr.append(td);
	}
	
	/************************************
	 * 컬럼 필드 추가
	 ************************************/
	function appendColField(tr, data) {
		var td = $("<td/>");
		td.attr("data-col-id", data.columnId);
		var element = $('<input type="text" class="form-control" name="colFiled"  />');
		element.attr("data-col-id", data.columnId);
		element.attr("data-col-type", "field");
		element.val(data.field);
		td.append(element);
		tr.append(td);

	}
	
	/************************************
	 * 컬럼 타입 추가
	 ************************************/
	function appendColType(tr, data) {
		var td = $("<td/>");
		td.attr("data-col-id", data.columnId);
		var element = $('<select class="form-control" name="colType"  />');
		element.attr("data-col-id", data.columnId);
		element.attr("data-col-type", "type");
		var opt1 = $("<option/>");
		opt1.attr("value", "char");
		opt1.html("char");
		var opt2 = $("<option/>");
		opt2.attr("value", "number");
		opt2.html("number");
		var opt3 = $("<option/>");
		opt3.attr("value", "int");
		opt3.html("int");
		var opt4 = $("<option/>");
		opt4.attr("value", "checkbox");
		opt4.html("checkbox");
		var opt5 = $("<option/>");
		opt5.attr("value", "combo");
		opt5.html("combo");
		var opt6 = $("<option/>");
		opt6.attr("value", "date");
		opt6.html("date");
		var opt6_1 = $("<option/>");
		opt6_1.attr("value", "datemonth");
		opt6_1.html("datemonth");
		var opt6_2 = $("<option/>");
		opt6_2.attr("value", "datetime");
		opt6_2.html("datetime");
		var opt6_3 = $("<option/>");
		opt6_3.attr("value", "time");
		opt6_3.html("time");

		var opt7 = $("<option/>");
		opt7.attr("value", "button");
		opt7.html("button");

		element.val(data.type);
		element.append(opt1);
		element.append(opt2);
		element.append(opt3);
		element.append(opt4);
		element.append(opt5);
		element.append(opt6);
		element.append(opt6_1);
		element.append(opt6_2);
		element.append(opt6_3);
		element.append(opt7);

		element.val(data.type);
		td.append(element);
		tr.append(td);

	}
	
	/************************************
	 * 컬럼 길이 추가
	 ************************************/
	function appendColLength(tr, data) {
		var td = $("<td/>");
		td.attr("data-col-id", data.columnId);
		var element = $('<input type="text" class="form-control" name="colMaxLength"  />');
		element.attr("data-col-id", data.columnId);
		element.attr("data-col-type", "maxLength");
		element.val(data.maxLength);
		td.append(element);
		tr.append(td);
	}
	
	/************************************
	 * 컬럼 폭 지정
	 ************************************/
	function appendColWidth(tr, data) {
		var td = $("<td/>");
		td.attr("data-col-id", data.columnId);
		var element = $('<input type="text" class="form-control" name="colWidth"  />');
		element.attr("data-col-id", data.columnId);
		element.attr("data-col-type", "width");
		element.val(80);
		td.append(element);
		tr.append(td);
	}
	
	/************************************
	 * 컬럼 Aliance 지정
	 ************************************/
	function appendColAlign(tr, data) {
		var td = $("<td/>");
		td.attr("data-col-id", data.columnId);
		var element = $('<select class="form-control" name="colAlign"  />');
		element.attr("data-col-id", data.columnId);
		element.attr("data-col-type", "textAlignment");

		var opt1 = $("<option/>");
		opt1.attr("value", "left");
		opt1.html("left");
		var opt2 = $("<option/>");
		opt2.attr("value", "center");
		opt2.html("center");
		var opt3 = $("<option/>");
		opt3.attr("value", "right");
		opt3.html("right");

		element.append(opt1);
		element.append(opt2);
		element.append(opt3);

		element.val(data.textAlignment);
		td.append(element);
		tr.append(td);
	}
	
	/************************************
	 * 컬럼 필수값
	 ************************************/
	function appendColRequired(tr, data) {
		var td = $("<td/>");
		td.attr("data-col-id", data.columnId);
		var element = $('<select class="form-control" name="colRequired"  />');
		element.attr("data-col-id", data.columnId);
		element.attr("data-col-type", "required");
		var opt1 = $("<option/>");
		opt1.attr("value", "Y");
		opt1.html("Y");
		var opt2 = $("<option/>");
		opt2.attr("value", "N");
		opt2.html("N");

		element.append(opt1);
		element.append(opt2);
		element.val(data.required == "Y" ? "Y" : "N");
		td.append(element);
		tr.append(td);
	}
	
	/************************************
	 * 컬럼 pk 지정
	 ************************************/
	function appendColPk(tr, data) {
		var td = $("<td/>");
		td.attr("data-col-id", data.columnId);
		var element = $('<select class="form-control" name="colPk"  />');
		element.attr("data-col-id", data.columnId);
		element.attr("data-col-type", "Pk");
		var opt1 = $("<option/>");
		opt1.attr("value", "Y");
		opt1.html("Y");
		var opt2 = $("<option/>");
		opt2.attr("value", "");
		opt2.html("N");

		element.append(opt1);
		element.append(opt2);
		element.val(data.readOnly == "Y" ? "Y" : "");
		td.append(element);
		tr.append(td);
	}
	
	/************************************
	 * 컬럼 readonly 지정
	 ************************************/
	function appendColReadOnly(tr, data) {
		var td = $("<td/>");
		td.attr("data-col-id", data.columnId);
		var element = $('<select class="form-control" name="colReadOnly"  />');
		element.attr("data-col-id", data.columnId);
		element.attr("data-col-type", "readOnly");
		var opt1 = $("<option/>");
		opt1.attr("value", "Y");
		opt1.html("Y");
		var opt2 = $("<option/>");
		opt2.attr("value", "");
		opt2.html("N");

		element.append(opt1);
		element.append(opt2);
		element.val(data.readOnly == "Y" ? "Y" : "");
		td.append(element);
		tr.append(td);
	}

	/************************************
	 * 컬럼 default 값 지정
	 ************************************/
	function appendColDefault(tr, data) {

		var td = $("<td/>");
		td.attr("data-col-id", data.columnId);
		var element = $('<input type="text" class="form-control" name="colDefaultValue"  />');
		element.attr("data-col-id", data.columnId);
		element.attr("data-col-type", "defaultValue");
		if (data.defaultValue)
			element.val($.trim(data.defaultValue.replace(/\'/gi, "")));
		td.append(element);
		tr.append(td);
	}
	
	/************************************
	 * 컬럼 숫자 format 지정
	 ************************************/
	function appendColDigit(tr, data) {

		var td = $("<td/>");
		td.attr("data-col-id", data.columnId);
		var element = $('<input type="text" class="form-control" name="colDigit"  />');
		element.attr("data-col-id", data.columnId);
		element.attr("data-col-type", "digit");
		element.val(data.digit);
		td.append(element);
		tr.append(td);
	}
	
	function appendColPlace(tr, data) {

		var td = $("<td/>");
		td.attr("data-col-id", data.columnId);
		var element = $('<input type="text" class="form-control" name="colPlace"  />');
		element.attr("data-col-id", data.columnId);
		element.attr("data-col-type", "place");
		element.val(data.place);
		td.append(element);
		tr.append(td);
	}
	function appendColPattern(tr, data) {

		var td = $("<td/>");
		td.attr("data-col-id", data.columnId);
		var element = $('<input type="text" class="form-control" name="colPattern"  />');
		element.attr("data-col-id", data.columnId);
		element.attr("data-col-type", "pattern");
		element.val(data.pattern);
		td.append(element);
		tr.append(td);
	}
	function appendColCommCode(tr, data) {

		var td = $("<td/>");
		td.attr("data-col-id", data.columnId);
		var element = $('<input type="text" class="form-control" name="colRootCodeId"  />');
		element.attr("data-col-id", data.columnId);
		element.attr("data-col-type", "rootCodeId");
		td.append(element);
		tr.append(td);

		var td = $("<td/>");
		td.attr("data-col-id", data.columnId);
		var element = $('<input type="text" class="form-control" name="colUpCodeId"  />');
		element.attr("data-col-id", data.columnId);
		element.attr("data-col-type", "upCodeId");
		td.append(element);
		tr.append(td);

		var td = $("<td/>");
		td.attr("data-col-id", data.columnId);
		var element = $('<input type="text" class="form-control" name="colCodeLevel"  />');
		element.attr("data-col-id", data.columnId);
		element.attr("data-col-type", "codeLevel");
		td.append(element);
		tr.append(td);

	}
	function appendColHidden(tr, data) {
		var td = $("<td/>");
		td.attr("data-col-id", data.columnId);
		var element = $('<select class="form-control" name="colHidden"  />');
		element.attr("data-col-id", data.columnId);
		element.attr("data-col-type", "hidden");
		var opt1 = $("<option/>");
		opt1.attr("value", "Y");
		opt1.html("Y");
		var opt2 = $("<option/>");
		opt2.attr("value", "N");
		opt2.html("N");

		element.append(opt1);
		element.append(opt2);
		element.val("N");
		td.append(element);
		tr.append(td);
	}
	function generateXml() {
		_SAVE_FILE_NAME = Utilities.convert2CamelCase(_TABLE_NAME.substring(3)) + ".xml";
		var doc = $.parseXML('<grid/>');
		var grid = doc.getElementsByTagName("grid")[0];
		var meta = doc.createElement("meta");
		grid.appendChild(meta);
		var metaJson = Utilities.formToMap("frmMeta");
		for (key in metaJson) {
			if (metaJson.hasOwnProperty(key)) {
				elem = doc.createElement(key);
				$(elem).attr("value", metaJson[key]);
				meta.appendChild(elem)
			}
		}

		$("tr[data-col-id]").each(function() {
			var tr = $(this);

			var column = doc.createElement("column");
			tr.find("[data-col-type]").each(function() {
				if ($(this).val())
					$(column).attr($(this).attr("data-col-type"), $(this).val());
			});
			grid.appendChild(column);
		});

		var gXml = (new XMLSerializer()).serializeToString(grid);

		// 		var xml = $(grid.documentElement);
		// 		var rootChildXml=$('<root />').append(xml).html();

		var xml = '<?xml version="1.0" encoding="UTF-8" standalone="no"?>\n' + Utilities.formatXml(gXml, "    ");
		$("#printXml").val(xml);
		$("#xmlModal").modal('show');
	}
	function paddingRight(str, len) {

		for (var i = str.length; i < len; i++) {
			str += ' ';
		}
		return str;
	}

	function generateDbXml() {
		_SAVE_FILE_NAME = Utilities.convert2CamelCase(_TABLE_NAME).substring(2)  + "_SqlMapper.xml";
		var list = _TABLE_INFO;
		var tableName = Utilities.convert2CamelCase(_TABLE_NAME.substring(3));
		var doc = $.parseXML('<mapper/>');
		var mapper = doc.getElementsByTagName("mapper")[0];
		mapper.setAttribute("namespace", "ezsmart." + tableName);

		var pkCond = doc.createElement("sql");
		pkCond.setAttribute("id", "sqlPkConditions");
		$(pkCond).append(getDbPKCondSql(pkCond));
		mapper.appendChild(pkCond);

		var cols = doc.createElement("sql");
		cols.setAttribute("id", "sqlCols");
		$(cols).append(getColsSql(cols));
		mapper.appendChild(cols);

		var cond = doc.createElement("sql");
		cond.setAttribute("id", "sqlConditions");
		getDbCondSql(cond, doc)
		mapper.appendChild(cond);

		getSelectSql(mapper, doc);
	    
		var tblName = Utilities.convert2CamelCase(_TABLE_NAME).substring(2);

		var ins = doc.createElement("insert");
		ins.setAttribute("id", "insert" + tblName);
		$(ins).append("\n        INSERT INTO "+_TABLE_NAME+" (\n");
		var inc = doc.createElement("include");
		$(inc).attr("refid", "sqlCols");
		$(ins).append(inc);
		$(ins).append("\n         ) VALUES (\n");

		for (var i = 0; i < list.length; i++) {
			var col = list[i];
			var column = col.columnName;
			var name = Utilities.convert2CamelCase(col.columnName);
			var prefix = i == 0 ? "               " : "             , ";
			if (name == "regDt" || name == "modDt")
				$(ins).append(prefix + "SYSDATE\n");
			else
				$(ins).append(prefix + "#" + "{" + name + "}\n");
		}
		$(ins).append("         )\n");
		mapper.appendChild(ins);

		var upd = doc.createElement("update");
		upd.setAttribute("id", "update" + tblName);
		$(upd).append("\n       UPDATE " + _TABLE_NAME + "\n");
		var fst = false;
		for (var i = 0; i < list.length; i++) {
			var col = list[i];

			if (col.pk)
				continue;

			var column = col.columnName;
			var name = Utilities.convert2CamelCase(col.columnName);
			if (name == "regDt" || name == "regId")
				continue;

			var prefix = !fst ? "          SET " : "            , ";
			fst = true;
			var str = prefix + paddingRight(column, 20) + "=         ";
			if (name == "modDt")
				$(upd).append(str + "SYSDATE\n");
			else
				$(upd).append(str + "#" + "{" + name + "}\n");
		}
		var inc = doc.createElement("include");
		$(inc).attr("refid", "sqlPkConditions");
		upd.appendChild(inc);

		mapper.appendChild(upd);

		var del = doc.createElement("delete");
		del.setAttribute("id", "delete" + tblName);
		$(del).append("\n       DELETE FROM " + _TABLE_NAME + "\n");
		var inc = doc.createElement("include");
		$(inc).attr("refid", "sqlPkConditions");
		del.appendChild(inc);
		mapper.appendChild(del);

		var gXml = (new XMLSerializer()).serializeToString(mapper);
		var xml = '<?xml version="1.0" encoding="UTF-8"?>\n<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">\n'
				+ Utilities.formatXml(gXml, "    ");
		$("#printXml").val(xml);
		$("#xmlModal").modal('show');
	}

	function getDbPKCondSql() {
		var list = _TABLE_INFO;
		var w = "\n        WHERE ";
		var a = "\n          AND ";
		var sql = "";
		for (var i = 0; i < list.length; i++) {
			var col = list[i];
			if (!col.pk)
				continue;
			var col = list[i];
			var column = col.columnName;
			var name = Utilities.convert2CamelCase(col.columnName);
			sql += sql == "" ? w : a;
			sql += paddingRight(column, 18) + "=       " + "#" + "{" + name + "}";
		}
		return sql + "\n";
	}
	function getColsSql() {
		var list = _TABLE_INFO;
		var w = "\n              ";
		var a = "\n            , ";
		var sql = "";
		for (var i = 0; i < list.length; i++) {
			var col = list[i];
			var column = col.columnName;
			sql += sql == "" ? w : a;
			sql += column;
		}
		return sql + "\n";
	}
	function getDbCondSql(cont, doc) {
		var list = _TABLE_INFO;
		$(cont).append("         FROM " + _TABLE_NAME);
		$(cont).append("\n        WHERE 1 = 1\n");
		for (var i = 0; i < list.length; i++) {
			var col = list[i];
			var column = col.columnName;
			var name = Utilities.convert2CamelCase(col.columnName);
			if (name == "regId" || name == "regDt" || name == "modId" || name == "modDt")
				continue;
			var iF = $(doc.createElement("if"));
			iF.attr("test", name + " != null and " + name + " != ''");
			$(iF).append("\n          AND " + paddingRight(column, 18) + "=       " + "#" + "{" + name + "}\n");
			$(cont).append(iF);
		}

	}
	function getSelectSql(mapper, doc) {

		var s1 = doc.createElement("select");
		$(s1).attr("id", "selectListCount");
		$(s1).attr("resultType", "int");
		$(s1).append("\n        SELECT COUNT(1)\n");
		var inc = doc.createElement("include");
		$(inc).attr("refid", "sqlConditions");
		s1.appendChild(inc);
		mapper.appendChild(s1);

		var s1 = doc.createElement("select");
		$(s1).attr("id", "selectList");
		$(s1).attr("resultType", "EzMap");

		var inc = doc.createElement("include");
		$(inc).attr("refid", "ezsmart.inc.pagingHeader");
		s1.appendChild(inc);
		$(s1).append("\n       SELECT ");

		var inc = doc.createElement("include");
		$(inc).attr("refid", "sqlCols");
		s1.appendChild(inc);

		var inc = doc.createElement("include");
		$(inc).attr("refid", "sqlConditions");
		s1.appendChild(inc);
		$(s1).append('\n       ORDER BY REG_DT DESC\n');

		var inc = doc.createElement("include");
		$(inc).attr("refid", "ezsmart.inc.pagingFooter");
		s1.appendChild(inc);
		mapper.appendChild(s1);

		var tbl = Utilities.convert2CamelCase(_TABLE_NAME).substring(2);

		var s1 = doc.createElement("select");
		$(s1).attr("id", "select" + tbl);
		$(s1).attr("resultType", "EzMap");
		$(s1).append("\n       SELECT ");

		var inc = doc.createElement("include");
		$(inc).attr("refid", "sqlCols");
		s1.appendChild(inc);

		var inc = doc.createElement("include");
		$(inc).attr("refid", "sqlPkConditions");
		$(s1).append("\n         FROM "+_TABLE_NAME+"\n");
		s1.appendChild(inc);
		mapper.appendChild(s1);
	}
</script>