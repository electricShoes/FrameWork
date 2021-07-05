if (!window["Utilities"]) {

	var _ERROR_NEED_LOGIN = 462;
	var _ERROR_HAS_NO_RIGHT = 463;
	var _ERROR_BAN_LOGIN = 464;
	var _ERROR_KEY_DUPLICATE = 486;
    var _ERROR_USER_ERROR = 487;

	window["custoError"] = function(element, msg) {
		this.element = element;
		this.msg = msg;
	};

	if (!console)
		console = {
			log : function(msg) {
			}
		};
	window["Utilities"] = {
		_NULL_RETURN : true,
		browserType : null,
		browserMode : null,
		uniCodeByte : 3,
		dialogs : [],
		getReadableFileSizeString : function(fileSizeInBytes) {

			if (!fileSizeInBytes)
				return "0 KB";
			var i = -1;
			var byteUnits = [ ' KB', ' MB', ' GB', ' TB', 'PB', 'EB', 'ZB', 'YB' ];
			do {
				fileSizeInBytes = fileSizeInBytes / 1024;
				i++;
			} while (fileSizeInBytes > 1024);

			return Math.max(fileSizeInBytes, 0.1).toFixed(1) + byteUnits[i];
		},
		guid : function() {
			function s4() {
				return Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
			}
			return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
		},
		uuid : function() {
			function s4() {
				return Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
			}
			return s4() + s4() + s4() + s4() + s4() + s4() + s4() + s4();
		},
		getClipboardData : function(e) {
			var pastedText = undefined;
			if (window.clipboardData && window.clipboardData.getData) { // IE
				pastedText = window.clipboardData.getData('Text');
			} else {
				if (!e)
					e = event;
				if (!e)
					return null;
				if (e.clipboardData && e.clipboardData.getData) {
					pastedText = e.clipboardData.getData('text/plain');
				} else
					(e.originalEvent && e.originalEvent.clipboardData)
				pastedText = e.originalEvent.clipboardData.getData('text/plain');
			}
			return pastedText;

		},
		getByteLength : function(s) {
			var b, i, c;
			for (b = i = 0; c = s.charCodeAt(i++); b += c >> 11 ? this.uniCodeByte : c >> 7 ? 2 : 1)
				;
			return b;
		},
		parseInt : function(str, x) {
			if (!str)
				return 0;
			if (!x)
				x = 10;
			var ret = parseInt(str, x);
			if (!ret)
				ret = 0;
			return ret;
		},
		getBrowserType : function() {

			if (this.browserType)
				return this.browserType;
			var _ua = navigator.userAgent;
			var rv = -1;

			// IE 11,10,9,8
			var trident = _ua.match(/Trident\/(\d.\d)/i);
			if (trident != null) {
				if (trident[1] == "7.0")
					return this.browserType = rv = "IE" + 11;
				if (trident[1] == "6.0")
					return this.browserType = rv = "IE" + 10;
				if (trident[1] == "5.0")
					return this.browserType = rv = "IE" + 9;
				if (trident[1] == "4.0")
					return this.browserType = rv = "IE" + 8;
			}

			if (navigator.appName == 'Microsoft Internet Explorer')
				return this.browserMode = rv = "IE" + 7;

			var agt = _ua.toLowerCase();
			if (agt.indexOf("chrome") != -1)
				return this.browserType = 'Chrome';
			if (agt.indexOf("opera") != -1)
				return this.browserType = 'Opera';
			if (agt.indexOf("staroffice") != -1)
				return this.browserType = 'Star Office';
			if (agt.indexOf("webtv") != -1)
				return this.browserType = 'WebTV';
			if (agt.indexOf("beonex") != -1)
				return this.browserType = 'Beonex';
			if (agt.indexOf("chimera") != -1)
				return this.browserType = 'Chimera';
			if (agt.indexOf("netpositive") != -1)
				return this.browserType = 'NetPositive';
			if (agt.indexOf("phoenix") != -1)
				return this.browserType = 'Phoenix';
			if (agt.indexOf("firefox") != -1)
				return this.browserType = 'Firefox';
			if (agt.indexOf("safari") != -1)
				return this.browserType = 'Safari';
			if (agt.indexOf("skipstone") != -1)
				return this.browserType = 'SkipStone';
			if (agt.indexOf("netscape") != -1)
				return this.browserType = 'Netscape';
			if (agt.indexOf("mozilla/5.0") != -1)
				return this.browserType = 'Mozilla';
		},
		getBrowserMode : function() {

			if (this.browserMode)
				return this.browserMode;
			if (this.getBrowserType().substring(0, 2) != "IE")
				return this.browserMode = this.getBrowserType();

			var _ua = navigator.userAgent;
			if (0 > _ua.indexOf("compatible"))
				return this.browserMode = this.getBrowserType();
			// IE 11,10,9,8
			var idx = _ua.indexOf("MSIE ");
			if (idx < 0)
				return this.browserMode = this.getBrowserType();
			idx += 5;
			var ver = _ua.substring(idx, _ua.indexOf(".", idx));
			if (ver)
				return this.browserMode = "IE" + ver;
			if (navigator.appName == 'Microsoft Internet Explorer')
				return this.browserMode = "IE" + 7;
			return this.browserMode = "IE";
		},
		isNumberCode : function(code) {
			return code >= '0' && code <= '9';
		},
		getNumberOnly : function(str) {
			var ret = "";
			for (var i = 0; str && i < str.length; i++) {
				var ch = str.substring(i, i + 1);
				if (this.isNumberCode(ch))
					ret += ch;
			}
			return ret;
		},
		isNumberOnly : function(str) {
			var ret = "";
			for (var i = 0; str && i < str.length; i++) {
				var ch = str.substring(i, i + 1);
				if (!this.isNumberCode(ch))
					return false
			}
			return true;
		},
		isAlphaCode : function(code) {
			code = code.toUpperCase();
			return code >= 'A' && code <= 'Z';
		},
		getAlphaOnly : function(str) {
			var ret = "";
			for (var i = 0; str && i < str.length; i++) {
				var ch = str.substring(i, i + 1);
				if (this.isAlphaCode(ch))
					ret += ch;
			}
			return ret;
		},
		isAlphaNumberCode : function(code) {
			return this.isAlphaCode(code) || this.isNumberCode(code);
		},
		getAlphaNumberOnly : function(str) {
			var ret = "";
			for (var i = 0; str && i < str.length; i++) {
				var ch = str.substring(i, i + 1);
				if (this.isAlphaNumberCode(ch))
					ret += ch;
			}
			return ret;
		},
        
        /**
         * @description 공백제거
         * @param value
         **/
		trim : function(value) {
			if (!value)
				return value;
			return $.trim(value);
		},
        
		getFormObject : function(frm) {
			if (!frm)
				return null;
			var selector = null;
			if (typeof frm === "string") {
				if (frm.substring(0, 1) != "#")
					selector = "#" + frm;
			} else
				selector = frm;

			var jFrm = $(selector);
			if (!jFrm || !jFrm.length)
				return null;
			return jFrm;
		},
		paramToMap : function(param) {
			if (!param)
				return null;
			var map = {};
			if (param.substring(0, 1) == "?" || param.substring(0, 1) == "#")
				param = param.substring(1);
			var arr = param.split("&");
			for (var i = 0; i < arr.length; i++) {
				var str = arr[i];
				var idx = arr[i].indexOf("=");
				var name = "";
				var value = "";
				if (idx < 0)
					map[str] = null;
				else if (idx == 0)
					continue;
				else
					map[str.substring(0, idx)] = decodeURIComponent(str.substring(idx + 1));
			}
			return map;
		},
		mapToForm : function(map, frm) {
			if (!map)
				return;
			var jFrm = Utilities.getFormObject(frm);
			if (!jFrm || !jFrm.length)
				return null;
			var frmObj = jFrm[0];
			frmObj.reset();
			for ( var name in map) {

				jFrm.find("[name=" + name + "]").val(map[name]);
			}
			return;
//			var arr = jFrm.serializeArray();
//			$.each(arr, function() {
//				// if (typeof map[this.name] === "undefined" ||
//				// !frmObj[this.name])
//
//				if (typeof map[this.name] === "undefined" || jFrm.find("[name=" + this.name + "]").length == 0)
//					return;
//				jFrm.find("[name=" + this.name + "]").val(typeof jFrm.find("[name=" + this.name + "]").length == 1 ? map[this.name] : [ map[this.name] ]);
//			});
		},
		mapToParam : function(map) {
			if (!map)
				return "";
			return $.param(map);

		},
		mapToFormData : function(map) {
			var formData = new FormData();
			if (map) {
				for ( var name in map) {
					var ret = formData.append(name, map[name]);
				}
			}

			return formData;
		},
		formToParam : function(frm) {
			var jFrm = Utilities.getFormObject(frm);
			if (!jFrm || !jFrm.length)
				return null;
			return jFrm.serialize();
		},
		formToMap : function(frm) {
			var jFrm = Utilities.getFormObject(frm);
			if (!jFrm || !jFrm.length)
				return null;
			var map = {};
			var disa = jFrm.find("[disabled]");
			disa.prop("disabled", false);
			var arr = jFrm.serializeArray();
			$.each(arr, function() {
				if (typeof map[this.name] === "undefined") {
					map[this.name] = this.value || '';
				} else {
					if (!map[this.name].push) {
						map[this.name] = [ map[this.name] ];
					}
					map[this.name].push(this.value || '');

				}
			});
			disa.prop("disabled", true);
			return map;

		},
		replaceString : function(str, rep, value) {

			if (!rep || !str)
				return str;

			var val = null;
			try {
				var arr = str.split(rep);
				val = arr.join(value);
			} catch (e) {
				val = str.replace(rep, value);
				while (val != str) {
					str = val;
					val = str.replace(rep, value);
				}
				return val;
			}
			return val;

		},
		isNumberInput : function(input) {
			return $(input).attr("type") == "number" || $(input).attr("data-type") == "number" || $(input).attr("data-type") == "ssn1" || $(input).attr("data-type") == "ssn2";
		},
		isCalendarInput : function(input) {
			return $(input).attr("type") == "date" || $(input).attr("data-type") == "date";
		},

		isCheckPass : function(value) {
			if (value == null)
				return true;
			if (value == "")
				return true;
			return false;
		},

		assertFalse : function(value) {
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return value.toString() == false.toString();
		},
		assertTrue : function(value) {
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return value.toString() == true.toString();
		},
		checkDecimalMax : function(value, max) {
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return Number(value.toString()) <= Number(max.toString());
		},
		checkDecimalMin : function(value, min) {
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return Number(value.toString()) >= Number(min.toString());
		},
		checkMax : function(value, max) {
			return this.checkDecimalMax(value, max);
		},
		checkMin : function(value, min) {
			return this.checkDecimalMin(value, min);
		},
		checkDigits : function(value, integer, fraction) {
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			var values = value.toString().split(".");
			if (values.length == 0 || values.length > 2)
				return false;

			intValue = values[0];
			fraValue = values.length == 2 ? values[1] : "";

			if (integer > -1) {
				intValue = Utilities.parseInt(intValue);
				if (integer == 0 && (intValue != 0))
					return false;
				if (intValue.toString().length > integer)
					return false;
			}
			if (fraction > -1 && fraValue.length > 0) {
				if (values.length != 2)
					return false;
				var fr = Utilities.parseInt(fraValue);
				if (!typeof fr === "NaN")
					return false;
				if (fr.toString().length > fraction)
					return false;

			}

			return true;
		},
		checkFuture : function(value) {
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return Number(value) > Number(new Date());
		},
		checkPast : function(value) {
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return Number(value) < Number(new Date());
		},
		checkNotNull : function(value) {
			return value != null;
		},
		checkNull : function(value) {
			return value == null;
		},
		checkPattern : function(value, pattern, flags) {
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			var flag = "";
			for (var i = 0; flags != null && i < flags.length; i++) {
				if (0x02 == flags[i])
					flag += "i";
				else if (0x08 == flags[i])
					flag += "m";
			}
			var reg = null;
			if (flag == "")
				reg = new RegExp(pattern);
			else
				reg = new RegExp(pattern, flag);
			return reg.test(value);
		},
		checkSize : function(value, min, max) {
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return this.checkMax(value.length, max) && this.checkMin(value.length, min);
		},
		checkSizeB : function(value, min, max) {
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			var btLen = Utilities.getByteLength(value);
			return this.checkMax(btLen, max) && this.checkMin(btLen, min);
		},
		checkIsInteger : function(value) {
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			var num = Number(value.toString());
			if (num == NaN)
				return false;
			return num == Utilities.parseInt(value.toString());
		},
        
        /**
         * @description 이메일 검증
         * @param value
         **/
		checkEmail : function(value) {

			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			// var pattern =
			// "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-.]+\.[a-zA-Z0-9-.]+$";
			// var pattern
			// ="^[0-9a-zA-Z]([\-.\w]*[0-9a-zA-Z\-_+])*@([0-9a-zA-Z][\-\w]*[0-9a-zA-Z]\.)+[a-zA-Z]{2,9}$";
			var pattern = "^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$";
			var flags = [ 0x02 ];
			return this.checkPattern(value, pattern, flags);
		},
        
		checkCreditCardNumber : function(value) {
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return true;
		},
		checkLength : function(value, min, max) {
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			if (!typeof value === 'string')
				return false;
			return this.checkSize(value, min, max);
		},
		checkNotBlank : function(value) {
			return this.checkNotEmpty(Utilities.trim(value));
		},
		checkNotEmpty : function(value) {
			if (value == null)
				return false;
			if (value.length == 0)
				return false;
			return true;
		},

		checkRange : function(value, min, max) {
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return value <= max && value >= min;
		},
		checkSafeHtml : function(value, type, add) {
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return true;
		},
		checkScriptAssert : function(value, lang, script, alias) {
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return true;
		},
		checkURL : function(value) {
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			var reg = /(http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w-.\/?%&;=]*)?)/gi;
			var url = value.match(reg);
			if (url == null || url.length == 0)
				return false;
			if (url[0] == value)
				return true;
			return false;
		},
		checkNumberOnly : function(value) {
			return this.checkPattern(value, "^[0-9]*$", "");
		},
		goUrl : function(url, context) {
			if (!context)
				context = window;
			context.open(url, "_self");
		},
		checkAllBox : function(name, checked) {
			$("input[name=" + name + "]").prop("checked", checked);
		},
		toggleCheckBox : function(name) {
			var checked = false;
			if (Utilities.hasUncheckedBox(name))
				checked = true;
			Utilities.checkAllBox(name, checked);

		},
		hasUncheckedBox : function(name) {
			var chks = $("input[name=" + name + "]");
			for (var i = 0; i < chks.length; i++) {
				if ($(chks[i]).attr("checkAll"))
					continue;
				if (!chks[i].checked)
					return true;
			}
			return false;
		},
		objectToPostParam : function(obj) {
			var strParam = "";
			for ( var i in obj) {
				if (strParam.length)
					strParam += "&";
				strParam += i.toString() + "=" + obj[i];
			}
			return strParam;
		},
		overrideEvent : function(fnName, fn, bReplace) {
			if (!window[fnName] || bReplace)
				window[fnName] = fn;
			else {
				var orgFn = window[fnName];
				window[fnName] = function() {

					try {
						if (!orgFn.apply(this, arguments))
							return fn.apply(this, arguments);
					} catch (e) {
						console.error(e);
						return fn.apply(this, arguments);
					}
				};
			}

		},
		ajax : function(param) {
			$.ajax(param);
		},
		toJSON : function(param) {
			return JSON.stringify(param);
		},
		parseJSON : function(param) {
			return $.parseJSON(param);
		},
		getObject : function(url, param, jsonBody, option) {
			var nocaching;
			if (url.indexOf("?") > 0)
				nocaching = "&";
			else
				nocaching = "?";
			var url = url + nocaching + "nocachingParam=" + Utilities.guid();
			var retVal = null;
			var opt = {
				url : url,
				dataType : 'json',
				data : jsonBody && param ? Utilities.toJSON(param) : param,
				type : 'POST',
				cache : false,
				contentType : jsonBody ? 'application/json; charset=utf-8' : 'application/x-www-form-urlencoded; charset=utf-8',
				async : false,
				success : function(data, textStatus, jqXHR) {
					if (data.error) {
						if (data.message)
							alert(data.message);
					} else
						retVal = data;
				},
				error : function(jqXHR, textStatus, errorThrown) {

					retVal = null;
				},
				handleError : true

			};
            
			if (option)
				opt = $.extend(opt, option);
			Utilities.ajax(opt);
			return retVal;
		},
		getAjax : function(url, param, jsonBody, callback, option) {
			var nocaching;
			if (url.indexOf("?") > 0)
				nocaching = "&";
			else
				nocaching = "?";
			var url = url + nocaching + "nocachingParam=" + Utilities.guid();
			var returnValue = null;
			var opt = {
				url : url,
				dataType : 'json',
				data : jsonBody && param ? Utilities.toJSON(param) : param,
				type : 'POST',
				cache : false,
				contentType : jsonBody ? 'application/json; charset=utf-8' : 'application/x-www-form-urlencoded; charset=utf-8',
				async : true,
				success : function(data, textStatus, jqXHR) {
					result = true;
					returnValue = data;
				},
				error : function(jqXHR, textStatus, errorThrown) {
					returnValue = null;
				},
				complete : function(jqXHR, textStatus) {
					if (callback) {
						try {
							callback(returnValue, jqXHR);
						} catch (e) {
							console.error(e);
						}
					}
				},
				handleError : true
			};
			if (option)
				opt = $.extend(opt, option);
			Utilities.ajax(opt);
		},
		ajaxDownload : function(url, param, jsonBody, progressCallback, completeCallback, option) {
			var callback = function(returnValue, jqXHR) {
				jqXHR.response = returnValue;
				Utilities.saveAjaxFile(jqXHR, null);
				if (completeCallback)
					completeCallback(returnValue, jqXHR);
			}
			var prgCallbaock = function(loaded, total, percentComplete) {
				if (progressCallback) {
					progressCallback(loaded, total, percentComplete);
				}

			};
			var opt = {
				dataType : false,
				contentType : false,
				processData : false,
				xhrFields : {
					responseType : 'blob'
				},
				xhr : function() {
					var xhr = new window.XMLHttpRequest();
					xhr.onprogress = function(evt) {
						if (evt.lengthComputable) {
							var percentComplete = evt.loaded / evt.total;
							prgCallbaock(evt.loaded, evt.total, percentComplete);
						} else {
							prgCallbaock(evt.loaded);
						}
					};
					xhr.addEventListener("loadstart", function(evt) {
						prgCallbaock(0, 0, 0);
					}, false);
					return xhr;
				}
			};

			if (option)
				opt = $.extend(opt, option);

			Utilities.getAjax(url, param, jsonBody, callback, opt);

		},
		saveAjaxFile : function(req, filename) {
			if (!filename) {
				var desc = req.getResponseHeader("content-disposition");
				if (!desc)
					return false;
				var idx = desc.indexOf("filename=");
				if (idx > 0) {
					filename = desc.substring(idx + "filename=".length);
					if (filename.substring(0, 1) == '"' || filename.substring(0, 1) == "'")
						filename = filename.substring(1);
					idx = filename.lastIndexOf('"');
					if (idx >= 0)
						filename = filename.substring(0, idx);
					idx = filename.lastIndexOf("'");
					if (idx >= 0)
						filename = filename.substring(0, idx);
				}

			}
			filename = decodeURIComponent(filename);
			if (typeof window.chrome !== "undefined") {
				var link = document.createElement("a");
				var res = req.response;
				if (!res) {
					res = new Blob([ req.responseText ], {
						type : 'text/plain'
					});
				}
				link.href = window.URL.createObjectURL(res);
				link.download = filename;
				link.click();
			} else if (typeof window.navigator.msSaveBlob !== "undefined") {
				var blob = new Blob([ req.response || req.resopnseText ], {
					type : 'application/force-download'
				});
				window.navigator.msSaveBlob(blob, filename);
			} else {
				var file = new File([ req.response || req.responseText ], filename, {
					type : 'application/force-download'
				});
				window.open(URL.createObjectURL(file));
			}
		},
		downloadText : function(text,filename){
			if (typeof window.chrome !== "undefined") {
				var link = document.createElement("a");
				var res = new Blob([ text], {
					type : 'text/plain'
				});
				link.href = window.URL.createObjectURL(res);
				link.download = filename;
				link.click();
			} else if (typeof window.navigator.msSaveBlob !== "undefined") {
				var blob = new Blob([ text ], {
					type : 'application/force-download'
				});
				window.navigator.msSaveBlob(blob, filename);
			} else {
				var file = new File([ text ], filename, {
					type : 'application/force-download'
				});
				window.open(URL.createObjectURL(file));
			}
		},
		min : function(x1, x2) {
			return x1 > x2 ? x2 : x1;
		},
		max : function(x1, x2) {
			return x1 < x2 ? x2 : x1;
		},
		getRect : function(left, right, top, bottom) {
			return {
				left : left,
				right : right,
				top : top,
				bottom : bottom,
				height : function() {
					return this.bottom - this.top;
				},
				width : function() {
					return this.right - this.left;

				},
				ptInRect : function(x, y) {
					return x >= this.left && x <= this.right && y >= this.top && y <= this.bottom;
				},
				interSection : function(rect) {
					var section = Utilities.getRect();
					section.left = Utilities.max(this.left, rect.left);
					section.right = Utilities.min(this.right, rect.right);
					section.top = Utilities.max(this.top, rect.top);
					section.bottom = Utilities.min(this.bottom, rect.bottom);
					return section;
				},
				union : function(rect) {
					var section = Utilities.getRect();
					section.left = Utilities.min(this.left, rect.left);
					section.right = Utilities.max(this.right, rect.right);
					section.top = Utilities.min(this.top, rect.top);
					section.bottom = Utilities.max(this.bottom, rect.bottom);
					return section;
				},
				normalize : function() {
					var left = this.left, right = this.right, top = this.top, bottom = this.bottom;
					if (!left)
						left = 0;
					if (!right)
						right = 0;
					if (!top)
						top = 0;
					if (!bottom)
						bottom = 0;
					this.top = Utilities.min(top, bottom);
					this.left = Utilities.min(left, right);
					this.bottom = Utilities.max(top, bottom);
					this.right = Utilities.max(left, right);
					return this;
				},
				moveRect : function(x, y) {
					this.left -= x;
					this.right -= x;
					this.top -= y;
					this.bottom -= y;
					return this;
				}
			}.normalize();
		},
		getBoundRect : function(element, onlyView) {
			var bounds = Utilities.getRect();
			var rect = element.offset();
			bounds.left = rect.left;
			bounds.top = rect.top;

			bounds.right = rect.left + element.outerWidth();
			bounds.bottom = rect.top + element.outerHeight();
			var rect;
			if (onlyView) {
				var view = Utilities.getViewport();
				return view.interSection(bounds);
			}
			return bounds;
		},
		getViewport : function() {
			var viewport = Utilities.getRect();
			var win = $(window);
			viewport.top = win.scrollTop();
			viewport.left = win.scrollLeft();
			viewport.right = viewport.left + win.width();
			viewport.bottom = viewport.top + win.height();
			return viewport;
		},
		isOnScreen : function(element) {
			var viewport = Utilities.getViewport();
			var bounds = Utilities.getBoundRect(element);
			var ret = viewport.right < bounds.left || viewport.left > bounds.right || viewport.bottom < bounds.top || viewport.top > bounds.bottom;
			return !ret;
		},
		removeCookie : function(name) {
			return $.removeCookie(name);
		},
		setCookie : function(name, value, day) {
			if (day == undefined)
				day = 1;
			Utilities.removeCookie(name);
			$.cookie(name, value, {
				expires : day,
				path : '/'
			});
		},
		getCookie : function(name) {
			return $.cookie(name);
		},
		byteCheck : function(elSlt) {
			var codeByte = 0;
			for (var idx = 0; idx < elSlt.val().length; idx++) {
				var oneChar = escape(elSlt.val().charAt(idx));
				if (oneChar.length == 1) {
					codeByte++;
				} else if (oneChar.indexOf("%u") != -1) {
					codeByte += 3;
				} else if (oneChar.indexOf("%") != -1) {
					codeByte++;
				}
			}
			return codeByte;
		},
		maxLengthCheck : function(elSlt, title, maxLength) {
			var obj = elSlt;
			if (maxLength == null) {
				maxLength = obj.attr("data-max-byte") != null ? obj.attr("data-max-byte") : 1000;
			}
			if (Number(Utilities.byteCheck(obj)) > Number(maxLength)) {
				alert(title + "이(가) 입력가능문자수를 초과하였습니다.\n(영문, 숫자, 일반 특수문자 : " + maxLength + " / 한글, 한자, 기타 특수문자 : " + parseInt(maxLength / 3, 10) + ").");
				elSlt.val(Utilities.cutByteString(elSlt, maxLength));
				obj.focus();
				return false;
			} else {
				return true;
			}
		},
		encodeURI : function(url) {
			if (!url)
				return "";

			var idxUrl = url.indexOf('?');
			var rootUrl = url.substring(0, idxUrl);
			var newUrl = "";
			if (idxUrl > 0) {
				var paramStr = url.substring(idxUrl + 1, url.length);
				var paramArr = paramStr.split('&');
				var newParam = [];
				$.each(paramArr, function(idx, obj) {
					var objArr = obj.split('=');
					var key = objArr[0];
					var val = objArr[1];
					if (val != '' || val != 'undefined' || val != null) {
						val = encodeURI(val);
					}
					newParam.push(key + '=' + val);
				});
				if (newParam.length > 0) {
					var newParamStr = newParam.join('&');
					newUrl = rootUrl + '?' + newParamStr;
				} else {
					newUrl = url;
				}
			}
			return newUrl;
		},
		windowOpen : function(url, name, width, height) {
			var spec = "width=" + width + ",height=" + height;
			var win = window.open(url, name, spec);
		},
		processResult : function(data, jqXHR, defaultErrorMsg) {
			if(jqXHR.status == _ERROR_NEED_LOGIN)
                return false;
			if (jqXHR.status != 200) {
                if(!data)
                    data = jqXHR.responseJSON;
				var msg = data ? data.errorMsg || defaultErrorMsg : defaultErrorMsg;
				if (jqXHR.status == _ERROR_KEY_DUPLICATE) {
					msg = "중복된 키가 존재합니다.";
				}
                

				if (msg) {
					alert(msg);
				}
				return false;
			}
            if(data == null || data==false){
                if(defaultErrorMsg)
                    alert(defaultErrorMsg);
                return false;
            }
			if (data) {
				if (data.errorCode || data.errorMsg) {
					var msg = data.errorMsg || defaultErrorMsg;
					if (data.errorMsg) {
						alert(msg);
					}
					return false;
				}

			}
			return true;
		},
		convert2CamelCase : function(underScore) {
			fChar = underScore.substring(0, 1);
			fChar2 = fChar.toLowerCase();
			if (underScore.indexOf('_') < 0 && fChar == fChar2) {
				return underScore;
			}
			var result = "";
			var nextUpper = false;
			var len = underScore.length;

			for (var i = 0; i < len; i++) {
				var currentChar = underScore.charAt(i);
				var uChar = currentChar.toUpperCase();
				var lChar = currentChar.toLowerCase();
				if (currentChar == '_') {
					nextUpper = true;
				} else {
					if (nextUpper) {
						result += uChar;
						nextUpper = false;
					} else {
						result += lChar;
					}
				}
			}
			return result;
		},
		paddingZero : function(num, place) {
			var strNum = num + "";
			for (var i = strNum.length; i < place; i++) {
				strNum = "0" + strNum;
			}
			return strNum;
		},
		isNotEmpty : function(obj) {
			return !this.isEmpty(obj)
		},
        
        /*
         * @description : 입력받은 값이 비었는지 체크한다.
         * @param : obj - 배열[], json{}, String ''
         */
		isEmpty : function(obj) {
			if (obj == null)
				return true;
			if (typeof (obj) == typeof ([])) {
				return obj.length == 0;
			}
			if (typeof (obj) == typeof ({})) {
				for (key in obj) {
					return true;
				}
				return false;
			}
			if (typeof (obj) == typeof (" ")) {
				return obj.length == 0;
			}
			return false;
		},
        
        /**
         * @description 비밀번호 검증
         * @param pw
         */
		validatePasswordMsg : function(pw) {
			var o = {
				length : [ 6, 16 ],
				lower : 1,
				upper : 1,
				alpha : 1, /* lower + upper */
				numeric : 1,
				special : 1,
				custom : [ /* regexes and/or functions */],
				badWords : [],
				badSequenceLength : 5,
				noQwertySequences : true,
				spaceChk : true,
				noSequential : false
			};
			/* space bar check */
			if (o.spaceChk && /\s/g.test(pw)) {
				return "<p style='line-height:200%;'><span style='color:#E3691E; font-weight:bold;'>사용불가</span> : 비밀번호 재작성 필요" + "<br/>"
						+ "<span style='color:#999; font-weight:bold;'>영문 대소문자, 숫자 및 특수문자 사용</span></p>";
			}
			/* password 길이 체크 */
			if (pw.length < o.length[0])
				return "<p style='line-height:200%;'><span style='color:#E3691E; font-weight:bold;'>사용불가</span>" + "<br/>" + "<span style='color:#999; font-weight:bold;'>비밀번호는 " + o.length[0]
						+ "자 이상 입력하셔야 합니다.</span></p>";
			if (pw.length > o.length[1])
				return "<p style='line-height:200%;'><span style='color:#E3691E; font-weight:bold;'>사용불가</span>" + "<br/>" + "<span style='color:#999;'>비밀번호는 " + o.length[1]
						+ "자 이내로 입력하셔야 합니다.</span></p>";
			/* bad sequence check */
			if (o.badSequenceLength && pw.length >= o.length[0]) {
				var lower = "abcdefghijklmnopqrstuvwxyz", upper = lower.toUpperCase(), numbers = "0123456789", qwerty = "qwertyuiopasdfghjklzxcvbnm", start = o.badSequenceLength - 1, seq = "_"
						+ pw.slice(0, start);
				for (i = start; i < pw.length; i++) {
					seq = seq.slice(1) + pw.charAt(i);
					if (lower.indexOf(seq) > -1 || upper.indexOf(seq) > -1 || numbers.indexOf(seq) > -1 || (o.noQwertySequences && qwerty.indexOf(seq) > -1)) {
						return "<p style='line-height:200%;'>비밀번호 안전도 <span style='color:#E5E5E5'>|</span> <span style='color:#E3691E; font-weight:bold;'>낮음</span> "
								+ "<span style='color:#E3691E; font-weight:bold; font-size:20px; position: relative; top: 1.5px;'>―</span>"
								+ "<span style='color:#E5E5E5; font-weight:bold; font-size:20px; position: relative; top: 1.5px;''>―</span>"
								+ "<span style='color:#E5E5E5; font-weight:bold; font-size:20px; position: relative; top: 1.5px;''>―</span>" + "<br/>"
								+ "<span style='color:#999; font-weight:bold;'>안전도가 높은 비밀번호를 권장합니다.</span></p>";
					}
				}
			}
			/* password 정규식 체크 */
			var re = {
				lower : /[a-z]/g,
				upper : /[A-Z]/g,
				alpha : /[A-Z]/gi,
				numeric : /[0-9]/g,
				special : /[\W_]/g
			}, rule, i;
			var lower = (pw.match(re['lower']) || []).length > 0 ? 1 : 0;
			var upper = (pw.match(re['upper']) || []).length > 0 ? 1 : 0;
			var numeric = (pw.match(re['numeric']) || []).length > 0 ? 1 : 0;
			var special = (pw.match(re['special']) || []).length > 0 ? 1 : 0;
			/* 숫자로만 이루어지면 낮음 */
			if ((pw.match(re['numeric']) || []).length == pw.length) {
				return "<p style='line-height:200%;'><span style='color:#E3691E; font-weight:bold;'>사용불가</span> : 비밀번호 재작성 필요" + "<br/>"
						+ "<span style='color:#999; font-weight:bold;'>영문 대소문자, 숫자 및 특수문자 사용</span></p>";
			}
			/* 숫자, 알파벳(대문자, 소문자), 특수문자 2가지 조합 */
			else if (lower + upper + numeric + special <= 2) {
				return "<p style='line-height:200%;'>비밀번호 안전도 <span style='color:#E5E5E5'>|</span> <span style='color:#E3691E; font-weight:bold;'>낮음</span> "
						+ "<span style='color:#E3691E; font-weight:bold; font-size:20px; position: relative; top: 1.5px;'>―</span>"
						+ "<span style='color:#E5E5E5; font-weight:bold; font-size:20px; position: relative; top: 1.5px;''>―</span>"
						+ "<span style='color:#E5E5E5; font-weight:bold; font-size:20px; position: relative; top: 1.5px;''>―</span>" + "<br/>"
						+ "<span style='color:#999; font-weight:bold;'>안전도가 높은 비밀번호를 권장합니다.</span></p>";
			}
			/* 숫자, 알파벳(대문자, 소문자), 특수문자 4가지 조합 */
			else if (lower + upper + numeric + special <= 3) {
				return "<p style='line-height:200%;'>비밀번호 안전도 <span style='color:#E5E5E5'>|</span> <span style='color:#E3691E; font-weight:bold;'>적정</span> "
						+ "<span style='color:#E3691E; font-weight:bold; font-size:20px; position: relative; top: 1.5px;'>―</span>"
						+ "<span style='color:#E3691E; font-weight:bold; font-size:20px; position: relative; top: 1.5px;''>―</span>"
						+ "<span style='color:#E5E5E5; font-weight:bold; font-size:20px; position: relative; top: 1.5px;''>―</span>" + "<br/>"
						+ "<span style='color:#999; font-weight:bold;'>안전하게 사용하실 수 있는 비밀번호 입니다.</span></p>";
			}
			/* 숫자, 알파벳(대문자, 소문자), 특수문자 4가지 조합 */
			else {
				return "<p style='line-height:200%;'>비밀번호 안전도 <span style='color:#E5E5E5'>|</span> <span style='color:#E3691E; font-weight:bold;'>높음</span> "
						+ "<span style='color:#E3691E; font-weight:bold; font-size:20px; position: relative; top: 1.5px;'>―</span>"
						+ "<span style='color:#E3691E; font-weight:bold; font-size:20px; position: relative; top: 1.5px;''>―</span>"
						+ "<span style='color:#E3691E; font-weight:bold; font-size:20px; position: relative; top: 1.5px;''>―</span>" + "<br/>"
						+ "<span style='color:#999; font-weight:bold;'>예측하기 힘든 비밀번호로 더욱 안전합니다.</span></p>";
			}
			/* enforce the no sequential, identical characters rule */
//			if (o.noSequential && /([\S\s])\1/.test(pw))
//				return "no sequential";
//			/* enforce word ban (case insensitive) */
//			for (i = 0; i < o.badWords.length; i++) {
//				if (pw.toLowerCase().indexOf(o.badWords[i].toLowerCase()) > -1)
//					return "bad word";
//			}
//			/* enforce custom regex/function rules */
//			for (i = 0; i < o.custom.length; i++) {
//				rule = o.custom[i];
//				if (rule instanceof RegExp) {
//					if (!rule.test(pw))
//						return "custom";
//				} else if (rule instanceof Function) {
//					if (!rule(pw))
//						return "custom";
//				}
//			}

		},
        
		validatePassword : function(pw) {
			var o = {
				length : [ 6, 16 ],
				lower : 1,
				upper : 1,
				alpha : 1, /* lower + upper */
				numeric : 1,
				special : 1,
				custom : [ /* regexes and/or functions */],
				badWords : [],
				badSequenceLength : 5,
				noQwertySequences : true,
				spaceChk : true,
				noSequential : false
			};
			/* space bar check */
			if (o.spaceChk && /\s/g.test(pw)) {
				return false;
			}
			/* password 길이 체크 */
			if (pw.length < o.length[0])
				return false;
			if (pw.length > o.length[1])
				return false;
			/* bad sequence check */
			if (o.badSequenceLength && pw.length >= o.length[0]) {
				var lower = "abcdefghijklmnopqrstuvwxyz", upper = lower.toUpperCase(), numbers = "0123456789", qwerty = "qwertyuiopasdfghjklzxcvbnm", start = o.badSequenceLength - 1, seq = "_"
						+ pw.slice(0, start);
				for (i = start; i < pw.length; i++) {
					seq = seq.slice(1) + pw.charAt(i);
					if (lower.indexOf(seq) > -1 || upper.indexOf(seq) > -1 || numbers.indexOf(seq) > -1 || (o.noQwertySequences && qwerty.indexOf(seq) > -1)) {
						return false;
					}
				}
			}
			/* password 정규식 체크 */
			var re = {
				lower : /[a-z]/g,
				upper : /[A-Z]/g,
				alpha : /[A-Z]/gi,
				numeric : /[0-9]/g,
				special : /[\W_]/g
			}, rule, i;
			var lower = (pw.match(re['lower']) || []).length > 0 ? 1 : 0;
			var upper = (pw.match(re['upper']) || []).length > 0 ? 1 : 0;
			var numeric = (pw.match(re['numeric']) || []).length > 0 ? 1 : 0;
			var special = (pw.match(re['special']) || []).length > 0 ? 1 : 0;
			/* 숫자로만 이루어지면 낮음 */
			if ((pw.match(re['numeric']) || []).length == pw.length) {
				return false;
			}
			/* 숫자, 알파벳(대문자, 소문자), 특수문자 2가지 조합 */
			else if (lower + upper + numeric + special <= 2) {
				return false;
			}
			/* 숫자, 알파벳(대문자, 소문자), 특수문자 4가지 조합 */
			else if (lower + upper + numeric + special <= 3) {
				return true;
			}
			/* 숫자, 알파벳(대문자, 소문자), 특수문자 4가지 조합 */
			else {
				return true;
			}
		},
		formatXml : function(xml) {
			var reg = /(>)(<)(\/*)/g;
			var wsexp = / *(.*) +\n/g;
			var contexp = /(<.+>)(.+\n)/g;
			xml = xml.replace(reg, '$1\n$2$3').replace(wsexp, '$1\n').replace(contexp, '$1\n$2');
			var pad = 0;
			var formatted = '';
			var lines = xml.split('\n');
			var indent = 0;
			var lastType = 'other';
			// 4 types of tags - single, closing, opening, other (text, doctype,
			// comment) - 4*4 = 16 transitions
			var transitions = {
				'single->single' : 0,
				'single->closing' : -1,
				'single->opening' : 0,
				'single->other' : 0,
				'closing->single' : 0,
				'closing->closing' : -1,
				'closing->opening' : 0,
				'closing->other' : 0,
				'opening->single' : 1,
				'opening->closing' : 0,
				'opening->opening' : 1,
				'opening->other' : 1,
				'other->single' : 0,
				'other->closing' : -1,
				'other->opening' : 0,
				'other->other' : 0
			};

			for (var i = 0; i < lines.length; i++) {
				var ln = lines[i];
				var single = Boolean(ln.match(/<.+\/>/)); // is this line a
				// single tag? ex.
				// <br />
				var closing = Boolean(ln.match(/<\/.+>/)); // is this a closing
				// tag? ex. </a>
				var opening = Boolean(ln.match(/<[^!].*>/)); // is this even
				// a tag (that's
				// not
				// <!something>)
				var type = single ? 'single' : closing ? 'closing' : opening ? 'opening' : 'other';
				var fromTo = lastType + '->' + type;
				lastType = type;
				var padding = '';

				indent += transitions[fromTo];
				for (var j = 0; j < indent; j++) {
					padding += '    ';
				}

				formatted += padding + ln + '\n';
			}

			return formatted;
		},
		openFilePopup : function(uploadMode, fileId) {

		},
        
        /**
         * 파일명의 확장자 가져오기
         *
         * @function Utilities.getFileExt
         * @description 입력된 파일경로에서 확장자를 추출하여 반환한다.
         * @param {String} fname 파일경로
         * @returns {String} 파일확장자
         * @example Utilities.getFileExt("/aaaa/bbbb/abcd.gif"); > gif
         */
		getFileExt : function(fileName) {
			var idx = fileName.lastIndexOf(".");
			if (idx > -1 && fileName.length > idx + 2)
				return fileName.substring(idx + 1);
			else
				return "";
		},
        
        /**
         * 파일명 가져오기
         *
         * @function Utilities.getFileName
         * @description 입력된 파일 경로에서 파일명을 추출하여 반환한다.
         * @param {String} str 파일명
         * @returns {String} 파일확장자
         * @example Utilities.getFileName("/aaaa/bbbb/abcd.gif"); >
         *          abcd.gif
         */
        getFileName: function(str) {
            var paths = str.split(/\/|\\/g);
            return paths[paths.length - 1];
        },
        
		getFileUploader : function(options) {
			var uploader = {
				id : Utilities.uuid(),
				currentId : 0,
				fileList : [],
				acceptFiles : [],
				inputUpload : null,
				addCallback : options.addCallback,
				addFile : function() {
					this.inputUpload.click();
				},
				removeFile : function(id) {
					for (var i = 0; i < this.fileList.length; i++) {
						if (this.fileList[i].id == id) {
							var data = this.fileList[i];
							this.cancelUpload(data.id);
							this.fileList.splice(i, 1);
							return true;
						}
					}
					return false;
				},
				removeAllFile : function() {
					this.cancelAllUpload();
					fileList.length = 0;
				},
				upload : function(id, url, data, resultCallback, progressCallback) {
                    url = url || _file_upload_url;
					var that = this
					var fileInfo = this.findFile(id);
					if (!fileInfo)
						return false;
					if (fileInfo.jqXHR) {
						return false;
					}
					fileInfo.resultCallback = resultCallback;
					fileInfo.progressCallback = progressCallback;
					var result = false;
					var resultData = null;
					var formData = Utilities.mapToFormData(data);
					formData.append("uploadFile", fileInfo.file, fileInfo.file.name);

					$.ajax({
						xhr : function() {
							var xhr = new window.XMLHttpRequest();
							fileInfo.jqXHR = xhr;
							xhr.upload.addEventListener("progress", function(evt) {
								if (evt.lengthComputable) {
									var percentComplete = evt.loaded / evt.total;
									that.onUploadProgress(fileInfo, evt.loaded, evt.total, percentComplete);
								}
							}, false);
							return xhr;
						},
						type : 'POST',
						url : url,
						data : formData,
						processData : false,
						contentType : false,
						success : function(data, textStatus, jqXHR) {
							fileInfo.uploaded = true;
							result = true;
							resultData = data;
						},
						error : function(jqXHR, textStatus, errorThrown) {
							fileInfo.uploaded = false;
						},
						complete : function(jqXHR, textStatus) {
							fileInfo.jqXHR = null;
							that.onUploadComplete(fileInfo, resultData, result);
						}
					});
				},
				cancelUpload : function(data) {
					if (!data)
						return;
					if (typeof (data) == typeof (" "))
						data = this.findFile(data);
					if (data.jqXHR) {
						data.jqXHR.abort();
						data.jqXHR = null;
					}
				},
				cancelAllUpload : function() {
					for (var i = 0; i < this.fileList.length; i++) {
						this.cancelUpload(this.fileList[i]);
					}
				},
				findFile : function(id) {
					for (var i = 0; i < this.fileList.length; i++) {
						if (this.fileList[i].id == id)
							return this.fileList[i];
					}
					return null;
				},
				onAddFile : function(e, data) {

					data.id = ++this.currentId;
					this.fileList.push(data);
					if (this.options.addCallback) {
						this.options.addCallback(e, data);
					}
				},
				onChange : function(e, data) {
					return true;
				},
				onUploadProgress : function(fileInfo, loaded, total, percent) {
					// resultCallback, progressCallback
					if (fileInfo.progressCallback) {
						fileInfo.progressCallback(fileInfo.id, loaded, total, percent);
					}
				},
				onUploadComplete : function(fileInfo, resultData, result) {
					if (fileInfo.uploaded) {
						this.removeFile(fileInfo.id);
					}
					if (fileInfo.resultCallback) {
						fileInfo.resultCallback(fileInfo.id, resultData, result);
					}
					fileInfo.progressCallback = null;
					fileInfo.resultCallback = null;
				},
				createUploader : function(options) {
					this.options = options;
					var inputUpload = $('<input id="fileupload_' + this.id + '" type="file" name="files" multiple data-sequential-uploads="true" style="display:none"/>');
					this.inputUpload = inputUpload;
					var that = this;

					inputUpload.change(function(e) {
						var list = [];
						for (var i = 0; i < e.target.files.length; i++) {
							var data = {
								file : e.target.files[i],
								fileList : e.target.files
							};
							if (!that.onChange(e, data))
								return false;
							that.onAddFile(e, data);
						}

					});
					return this;
				}
			};
			return uploader.createUploader(options);
		},
        blockUI : function(){
            $.blockUI();
        },
        unblockUI : function(){
            $.unblockUI();
        },
        
        /**
         * Row 클릭 이벤트(grdList_rowChanged) 발생시,
         * 선언할 경우, 해당 rowData를 Form에 자동 세팅해준다.
         * @param formId : form Id
         * @param formData : jsp에 선언한 전역변수명, 해당 변수에 Object 형태의 데이터를 담는다.
         * @param gridId : grid Id
         * @param oldRow : 선택하기전 행의 주소
         * @param rowData : 선택한 행의 데이터
         *
         **/
        setRowData : function(formId, formData, gridId, oldRow, rowData){
            var curFormData = $('#'+formId).serializeObject(); // 현재 폼 Object
            setFormData = function(){
                $.each(rowData, function(key, value) {
                    $("#" + formId+" #"+key).val(value); // 파라미터를 받은 formId의 key(컴포넌트 id)에 value를 세팅
                });
            }
            
            if(formData == ""){
                // 폼 데이터 처음 세팅 시
                setFormData();
                return $('#'+formId).serializeObject();
                
            }else {
                if(JSON.stringify(formData) !== JSON.stringify(curFormData)){
                    if(confirm("변경된 데이터가 존재합니다. 이동하시겠습니까?")){
                        setFormData();
                        return "";
                    }else {
                        setTimeout(function() {
                            // Timeout 함수 미사용시, setCurrent가 먹히지 않으므로 시간차를 설정하여 실행될 수 있게 함.
                            gridId.setCurrent({itemIndex: oldRow}, true);    
                        }, 100);
                        return "";
                    }
                }else {
                    setFormData();
                    return $('#'+formId).serializeObject();
                }
            }
        },
        
        
        /* =================================================================================
         * 날짜 관련 공통 함수
         * =================================================================================*/ 
         
        /**
         * String형식으로 오늘 날짜 반환
         * @function Utilities.getStrToday
         * @description String형식으로 오늘 날짜 반환
         * @param {(String)}
         * @returns {Date} 날짜결과
         **/
        getStrToday : function(){
           var date = new Date();
           var year  = date.getFullYear();
           var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
           var day   = date.getDate();
           if (("" + month).length == 1) { month = "0" + month; }
           if (("" + day).length   == 1) { day   = "0" + day;   }

           return ("" + year + month + day);
        },
        
        /**
         * 4글자 시각데이터 시각:분 형태로 반환, 벨리데이션 포함(24시간)
         * pla120w mtngTime 참조
         * @function Utilities.timeColumn
         * @description String형식으로 입력 시각 반환
         * @param {(String)}
         * @returns {Date} 날짜결과
         * @example Utilities.timeColumn("2341"); > 23:41
         **/
        timeColumn : function(time,type) {
            // replace 함수를 사용하여 콜론( : )을 공백으로 치환
            var replaceTime = time.replace(/\:/g, "");
            // 텍스트박스의 입력값이 4~5글자 사이가 되는 경우에만 실행
            if(replaceTime.length >= 4 && replaceTime.length < 5) {
                var hours = replaceTime.substring(0, 2);      // 선언한 변수 hours에 시간값을 담는다.
                var minute = replaceTime.substring(2, 4);     // 선언한 변수 minute에 분을 담는다.

                // isFinite함수를 사용하여 문자가 선언되었는지 확인
                if(isFinite(hours + minute) == false) {
                    alert("문자는 입력하실 수 없습니다.");
                    time.value = "00:00";
                    return false;
                }
                // 두 변수의 시간과 분을 합쳐 입력한 시간이 24시가 넘는지를 체크
                if(hours + minute > 2400) {
                    alert("시간은 24시를 넘길 수 없습니다.");
                    time.value = "24:00";
                    return false;
                }
                // 입력한 분의 값이 59분을 넘는지 체크
                if(minute > 59) {
                    alert("분은 59분을 넘길 수 없습니다.");
                    time.value = hours + ":00";
                    return false;
                }
                time.value = hours + ":" + minute;
                return true;
            }else{
                if(type == "grid"){
                    alert("시간 형식이 올바르지 않습니다.");
                    time.value = "00:00";
                    return false;
                }
            }
        },
         
        /**
         * String타입 날짜를 date로 변경
         *
         * @function Utilities.stringToDate
         * @description String형식의 날짜를 Date형식으로 변환
         * @param {(String)}
         * @returns {Date} 날짜결과
         * @example Utilities.stringToDate("20200101"); > 2020-01-01
         **/
         stringToDate : function(stringDate){
             // return stringDate.substr(0,4)+"/"+stringDate.substr(4,2)+"/"+stringDate.substr(6,2)
             return stringDate.substr(0,4)+"-"+stringDate.substr(4,2)+"-"+stringDate.substr(6,2)
        },
        
        /**
         * 자바스크립트 Date 객체를 Time 스트링으로 변환
         *
         * @function Utilities.toTimeString
         * @description 자바스크립트 Date 객체를 Time 스트링으로 변환
         * @param {Date}
         *            date Date객체
         * @returns {String} time Time 형식 문자열
         * @example Utilities.toTimeString(new Date()); > "201708071230"
         */
        toTimeString: function(date) { // formatTime(date)
            var year  = date.getFullYear();
            var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
            var day   = date.getDate();
            var hour  = date.getHours();
            var min   = date.getMinutes();

            if (("" + month).length == 1) { month = "0" + month; }
            if (("" + day).length   == 1) { day   = "0" + day;   }
            if (("" + hour).length  == 1) { hour  = "0" + hour;  }
            if (("" + min).length   == 1) { min   = "0" + min;   }

            return ("" + year + month + day + hour + min);
        },
        
        /**
         * 현재 시각을 Time 형식으로 리턴
         *
         * @function Utilities.getCurrentTime
         * @description 현재 시각을 Time 형식으로 리턴
         * @returns {String} Time 형식 문자열
         * @example Utilities.getCurrentTime(); > "201708081010"
         */
        getCurrentTime: function() {
            return Utilities.toTimeString(new Date());
        },
        
        /**
         * 현재 年을 YYYY형식으로 리턴
         *
         * @function Utilities.getYear
         * @description 현재 年을 YYYY형식으로 리턴
         * @returns {String} 문자열
         * @example Utilities.getYear(); > "2020"
         */
        getYear: function() {
            return Utilities.getCurrentTime().substr(0,4);
        },
        
        /**
         * 현재 月을 MM형식으로 리턴
         *
         * @function Utilities.getMonth
         * @description 현재 月을 MM형식으로 리턴
         * @returns {String} 문자열
         * @example Utilities.getMonth(); > "08"
         */
        getMonth: function() {
            return Utilities.getCurrentTime().substr(4,2);
        },
        
        /**
         * 현재 日을 DD형식으로 리턴
         *
         * @function Utilities.getDay
         * @description 현재 月을 MM형식으로 리턴
         * @returns {String} 문자열
         * @example Utilities.getDay(); > "08"
         */
        getDay: function() {
            return Utilities.getCurrentTime().substr(6,2);
        },
        
        /**
         * 현재 時를 HH형식으로 리턴
         *
         * @function Utilities.getHour
         * @description 현재 月을 MM형식으로 리턴
         * @returns {String} 문자열
         * @example Utilities.getHour(); > "10"
         */
        getHour: function() {
            return Utilities.getCurrentTime().substr(8,2);
        },
        
        /**
         *  현재 요일 리턴
         *
         * @function Utilities.getDayOfWeek
         * @description 입력날짜 요일 리턴
         * @returns {String} 문자열
         * @example Utilities.getDayOfWeek(date,lang); > "MON"
         */
         getDayOfWeek: function(date,lang) {
            var year = date.substring(0,4);
            var month = date.substring(4,6);
            var day = date.substring(6,8);
            var now = new Date(year, month-1, day);
        
            var day = now.getDay(); // 일요일=0,월요일=1,...,토요일=6
            var week = new Array("일","월","화","수","목","금","토");
            
            if(lang == "EN"){
                week = new Array("SUN","MON","TUE","WED","THU","FRI","SAT");
            }else if(lang == "CH"){
                week = new Array("日","月","火","水","木","金","土");
            }
        
            return week[day];
        },
        
        /**
         * 특정 날짜로부터 날짜 더하는 계산
         *
         * @function Utilities.addDate
         * @description 날짜계산로직
         * @param sDate : 날짜
         * @param v : 더할 날짜
         * @param t - d : 일 / m : 월 / y : 년, default : d(일)
         * @returns {string} 날짜 yymmdd
         * @example Utilities.addDate('20201114', 10, 'd') => '20201124'
         */
        addDate: function(sDate, v, t) {

            var yy = parseInt(sDate.substr(0, 4), 10);
            var mm = parseInt(sDate.substr(4, 2), 10);
            var dd = parseInt(sDate.substr(6, 2), 10);

            if(t == "d"){
                d = new Date(yy, mm - 1, dd + v);
            }else if(t == "m"){
                d = new Date(yy, mm - 1 + v, dd);
            }else if(t == "y"){
                d = new Date(yy + v, mm - 1, dd);
            }else{
                d = new Date(yy, mm - 1, dd + v);
            }

            yy = d.getFullYear();
            mm = d.getMonth() + 1; mm = (mm < 10) ? '0' + mm : mm;
            dd = d.getDate(); dd = (dd < 10) ? '0' + dd : dd;
            return '' + yy + mm + dd;
        },
        
        /**
         * 입력 날짜 기준으로 해당 월의 시작 년월일(日) 구하기
         *
         * @function Utilities.getFirstMonthDay
         * @description 월의 시작 년월일(日) 구하기
         * @returns {string} 날짜 yyyymmdd
         * @example Utilities.getFirstYmd('20201124') => '20201101'
         */
        getFirstYmd: function(date){
            var year = date.substr(0,4);
            var month = date.substr(4,2);
            var firstDay = new Date( year, month, '1').getDate();
            return year+month+"0"+firstDay;
        },
        /**
         * 입력 날짜 기준으로 해당 월의 마지막 년월일(日) 구하기
         *
         * @function Utilities.getFirstMonthDay
         * @description 월의 마지막 년월일(日) 구하기
         * @returns {string} 날짜 yyyymmdd
         * @example Utilities.getLastYmd('20201124') => '20201130'
         */
        getLastYmd: function(date){
            var year = date.substr(0,4);
            var month = date.substr(4,2);
            var firstDay = new Date( year, month, '0').getDate();
            
            return year+month+firstDay;
        },
        
        /**
         * 입력 날짜 기준으로 해당 년의 첫 년원일 구하기
         *
         * @function Utilities.getFirstYearDay
         * @description 년의 첫 년원일 구하기
         * @returns {string} 날짜 yyyymmdd
         * @example Utilities.getLastYmd('20201124') => '20200101'
         */
        getFirstYearDay: function(date){
            var year = date.substr(0,4);
            return year+'0101';
        },
        
        /**
         * 입력 날짜 기준으로 해당 년의 마지막 년원일 구하기
         *
         * @function Utilities.getLastYearDay
         * @description 년의 마지막 년원일
         * @returns {string} 날짜 yyyymmdd
         * @example Utilities.getLastYear('20201124') => '20200101'
         */
        getLastYearDay: function(date){
            var year = date.substr(0,4);
            return year+'1231';
        },
        
        /* =================================================================================
         * 날짜 관련 공통 함수 끝
         * ================================================================================= */ 
         
        
        /* =================================================================================
         * 문자열 관련 공통 함수
         * ================================================================================= */
         
        /**
         * decode 함수
         *
         * @function Utilities.decode
         * @description 기준문자열(these) 문자열 기준으로 첫번째 대상문자열과 같으면 첫번째(value)를
         *              틀리면 두번째 대상문자열(other)을 반환한다.
         * @param {String}
         *            value 첫번째 대상 문자열
         * @param {String}
         *            these 기준 문자열
         * @param {String}
         *            other 두번째 대상 문자열
         * @returns {String} 문자열
         * @example Utilities.decode("aa", "aa", "cc"); > aa
         * @example Utilities.decode("aa", "bb", "cc"); > cc
         */
        decode: function(value, these, other) {
            return these === value ? other : value
        },
        
        /**
         * 왼쪽에 특정 문자열 덧붙이기
         *
         * @function Utilities.lpad
         * @description 문자열이 일정 길이가 될때까지 왼쪽에 특정 문자를 덧붙인다.
         * @param {(String|Number)}
         *            val 덧붙이는 대상 문자열을 지정한다.
         * @param {Number}
         *            len 전체 문자열 갯수
         * @param {String}
         *            str value의 길이가 n이 될때까지 왼쪽에 덧붙일 문자열을 지정한다. 입력되지 않을
         *            경우 기본값 "0"을 사용한다.
         * @returns {String} 문자열
         * @example Utilities.lpad(123, 6, "0"); > 000123
         */
        lpad: function(val, len, str) {
            var v = new String(val), n = (len || 2) - v.length, s = str || "0", p = "", i;
            for (i = 0; i < n; i += 1) {
                p += s;
            }
            return p + v;
        },
        
        /**
         * 오른쪽에 특정 문자열 덧붙이기
         *
         * @function Utilities.rPad
         * @description 문자열이 일정 길이가 될때까지 오른쪽에 특정 문자를 덧붙인다.
         * @param {(String|Number)}
         *            orgStr 덧붙이는 대상 문자열을 지정한다.
         * @param {Number}
         *            len 전체 문자열 갯수
         * @param {String}
         *            toPad value의 길이가 n이 될때까지 왼쪽에 덧붙일 문자열을 지정한다. 입력되지
         *            않을 경우 기본값 "0"을 사용한다.
         * @returns {String} 문자열
         * @example Utilities.rPad(123, 6, "0"); > 123000
         */
        rPad: function( orgStr, len, toPad ){
            if(!orgStr){
                return "";
            }
            if(!len){
                len = 0;
            }
            if(!toPad){
                toPad = "0";
            }
            orgStr = String(orgStr);
            while( orgStr.length < len  ){
                orgStr = orgStr + toPad;
            }
            return orgStr;
        },
        
        /**
         * 특수문자 제거
         *
         * @function Utilities.removeSpecialChar
         * @description 입력받은 문자열에 포함된 특수문자를 제거한다.
         * @param {String}
         *            orgStr 문자열
         * @returns {String} 문자열
         * @example Utilities.removeSpecialChar("@!#213423!@#"); >
         *          "213423"
         */
        removeSpecialChar: function( str ){
            var regExp = /[\{\}\[\]\/?.,;:|\)*~'!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
            if( regExp.test(str) ){
                return str.replace(regExp, '');
            }
            return str;
        },
        
        /**
         * 전화번호 형식 변환
         *
         * @function Utilities.strToTelNo
         * @description 입력받은 전화번호에 - 추가.
         * @param {String} orgStr 문자열
         * @returns {String} 문자열
         * @example Utilities.strToTelNo("01012348899"); > 010-1234-8899
         */
        strToTelNo: function( str ){
            if(str.length==8){
                return str.replace(/(\d{4})(\d{4})/, '$1-$2');
            }else{
                return str.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3");
            }

        },
        
        /**
         * 숫자 자리수 3자리 마다 콤마
         *
         * @function Utilities.numberWithCommas
         * @description 숫자 자리수 3자리 마다 콤마
         * @param {String}
         *            orgStr 문자열
         * @returns {String} 문자열
         * @example Utilities.numberWithCommas("123654"); > 123,654
         */
        numberWithCommas:function (str) {
            if( str != null ) {
                return str.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            } else {
                return '';
            }
        },
        
        /**
         * 숫자 콤마 제거
         *
         * @function Utilities.removeCommas
         * @description 숫자 콤마 제거.
         * @param {String}
         *            orgStr 문자열
         * @returns {String} 문자열
         * @example Utilities.removeCommas("123,654"); > 123654
         */
        removeCommas: function (str){
            return str.replace(/,/gi,"");
        },
        
        /**
         * 사업자 등록번호 형식 출력
         * @function Utilities.bregNoFormat
         * @description 사업자 등록번호 형식 수정
         * @param {String}
         *            orgStr 문자열
         * @returns {String} true 문자열, false ""
         * @example Utilities.bregNoFormat("1234567890"); > "123-45-67890"
         */
        bregNoFormat: function(data){
            var str = data;
            return str.replace(/(^[0-9]{3})([0-9]{2})([0-9]{5})/,"$1-$2-$3");
        },

        /**
         * 주민등록번호 형식 출력
         * @function Utilities.rsnoShowFormat
         * @description 주민등록번호 형식 출력
         * @param {String}
         *            orgStr 문자열
         * @returns {String} true 문자열, false ""
         * @example Utilities.rsnoShowFormat("7809191852341"); > "780919-1852341"
         */
        rsnoShowFormat: function(data){
            var str = data;
            return str.replace(/(^[0-9]{6})([0-9]{7})/,"$1-$2");
        },

        /**
         * 주민등록번호 형식 출력(뒷자리 숨기기)
         * @function Utilities.rsnoHideFormat
         * @description 주민등록번호 형식 출력(뒷자리 숨기기)
         * @param {String}
         *            orgStr 문자열
         * @returns {String} true 문자열, false ""
         * @example Utilities.rsnoHideFormat("7809191852341"); > "780919-1******"
         */
        rsnoHideFormat: function(data){
            var str = data;
            return str.replace(/(^[0-9]{6})([0-9]{1})([0-9]{6})/,"$1-$2******");
        },
        
        /* =================================================================================
         * 문자열 관련 공통 함수 끝
         * ================================================================================= */
         
         
         
        /* =================================================================================
         * 밸리데이션 관련 공통 함수
         * ================================================================================= */
        
        /**
         * 유효한(존재하는) 월(月)인지 체크
         *
         * @function Utilities.isValidDate
         * @description 유효한(존재하는) 년월일 인지 체크
         * @param yyyy 년도
         * @param MM 월
         * @param dd 일
         * @returns {Boolean} 체크결과
         * @example Utilities.isValidDate("2020", "11", "24"); > true
         **/
        isValidDate: function(yyyy, MM, dd) {
            var oDate = new Date();
            oDate.setFullYear(yyyy);
            oDate.setMonth(MM - 1);
            oDate.setDate(dd);
            
            if (oDate.getFullYear() != yyyy
                || oDate.getMonth() + 1 != MM
                || oDate.getDate() != dd) {
            
              return false;
            }
            return true;
        },
        
        /**
         * 유효한(존재하는) 월(月)인지 체크
         *
         * @function Utilities.isValidMonth
         * @description 유효한(존재하는) 월(月)인지 체크
         * @param {(String|Number)}
         *            month 월문자열
         * @returns {Boolean} 체크결과
         * @example Utilities.isValidMonth("11"); > true
         **/
        isValidMonth: function(month) {
            var m = parseInt(month,10);
            return (m >= 1 && m <= 12);
        },
          
        /**
          * 유효한(존재하는) 일(日)인지 체크
          *
          * @function Utilities.isValidDay
          * @description 유효한(존재하는) 일(日)인지 체크
          * @param {(String|Number)}
          *            yyyy 년
          * @param {(String|Number)}
          *            mm 월
          * @param {(String|Number)}
          *            dd 일
          * @returns {Boolean} 체크결과
          * @example Utilities.isValidDay("2017", "08", "08"); > true
          */
        isValidDay: function(yyyy, mm, dd) {
            var m = parseInt(mm,10) - 1;
            var d = parseInt(dd,10);
            var end = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
            if ((yyyy % 4 == 0 && yyyy % 100 != 0) || yyyy % 400 == 0) {
                end[1] = 29;
            }
            
            return (d >= 1 && d <= end[m]);
        },
        
        /**
         * 유효한(존재하는) 시(時)인지 체크
         *
         * @function Utilities.isValidHour
         * @description 유효한(존재하는) 시(時)인지 체크
         * @param {String}
         *            hh 시
         * @returns {Boolean} 체크결과
         * @example Utilities.isValidHour("12"); > true
         */
        isValidHour: function(hh) {
            var h = parseInt(hh,10);
            return (h >= 1 && h <= 24);
        },
        
        /**
         * 유효한(존재하는) 분(分)인지 체크
         *
         * @function Utilities.isValidMin
         * @description 유효한(존재하는) 분(分)인지 체크
         * @param {String}
         *            mi 분
         * @returns {Boolean} 체크결과
         * @example Utilities.isValidMin("59"); > true
         */
        isValidMin: function(mi) {
            var m = parseInt(mi,10);
            return (m >= 1 && m <= 59);
        },
        
        isSSN:function (jumin1, jumin2) {
            var n = 2;
            var sum = 0;
            for (var i = 0; i < jumin1.length; i++){
              sum += parseInt(jumin1.substr(i, 1)) * n++;
            }
            
            for (var i = 0; i < jumin2.length - 1; i++) {
              sum += parseInt(jumin2.substr(i, 1)) * n++;
              if (n == 10){
                n = 2;
              }
            }

            var checkSum = 11 - sum % 11;

            if (checkSum == 11){
              checkSum = 1;
            }

            if (checkSum == 10){
              checkSum = 0;
            }

            if (checkSum != parseInt(jumin2.substr(6, 1))){
              return false;
            }
            return true;
        },
         
        /**
         *  주민번호 검증
         *  Utilities.chkRsno("7809191852341"); > true/false
         **/
        chkRsno: function(str){
            socialRegNumber = String(str); // 문자로 변환

            if(!socialRegNumber || socialRegNumber.length!=13) {
                alert("주민등록번호 형식이 잘못되었습니다.");
                return false;
            }

            // 숫자가 아닌 것을 입력한 경우
            if (isNaN(socialRegNumber)) {
                alert("주민등록 입력이 잘못되었습니다.");
                return false;
            }

            var jumin1     = socialRegNumber.substr(0,6)    // 앞자리
              , jumin2     = socialRegNumber.substr(6,7)    // 뒷자리
              , gender     = jumin2.substr(0,1)             // 성별 1~4
              , formalYear = (gender < 3) ? '19' : '20'     // 연도 계산 - 1 또는 2: 1900년대, 3 또는 4: 2000년대
              , yyyy       = formalYear + jumin1.substr(0,2)
              , MM         = jumin1.substr(2,2)
              , dd         = jumin1.substr(4,2);

            // 성별부분이 1 ~ 4 가 아닌 경우
            if (gender < 1 || gender > 4) {
                alert("주민등록번호 뒷자리가 잘못되었습니다.");
                return false;
            }

            // 날짜 유효성 검사
            if (Utilities.isValidDate(yyyy, MM, dd) == false) {
                alert("주민등록번호 앞자리가 잘못되었습니다.");
                return false;
            }

            // Check Digit 검사
            if (Utilities.isSSN(jumin1, jumin2)) {
                alert("주민등록번호 형식이 잘못되었습니다.");
                return false;
            }
            return true;
        },
        
        /**
         * 전화번호 검증
         *
         * @function Utilities.chkTelNo
         * @description 입력받은 전화번호검증.
         * @param {String}
         *            str 문자열
         * @returns {Boolean} 정상전화번호는 true, 비정상전화번호는 false
         * @example Utilities.chkTelNo("01012341234"); > true
         */
        chkTelNo: function (str){
            var regTel = /^(01[016789]{1}|070|02|0[3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})|(15(22|44|66|77|88|99)|16(00|44|61|66|70|88)|18(11|55|77|99))([0-9]{4})$/;
            if(regTel.test(str)) {
                //전화번호 형식 맞음
                return true;
            }else {
                //전화번호 형식 틀림
                return false;
            }
        },
        
        /**
         * 숫자만 입력
         *
         * @function Utilities.numkeyCheck
         * @description 숫자만 입력
         * @param
         * @returns {Boolean} 숫자 true, 비숫자는 false
         * @example Utilities.numkeyCheck("01012341234"); > true
         */
        numkeyCheck: function() {
            event = event || window.event;
            var keyID = (event.which) ? event.which : event.keyCode;
            if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 )
                return true;
            else
                return false;
        },
        
        /**
         * 사업자등록번호 검증
         * @function Utilities.bregNoCheck
         * @description 입력받은 사업자등록번호 검증
         * @param bregNo
         * @return {Boolean} 사업자등록번호 true, 그외는 false
         * @example Utilities.bregNoCheck("1234567890"); >false
         */
        bregNoCheck: function(bregNo){
            var sum      = 0;
            var getlist  = new Array(10);
            var chkvalue = new Array("1","3","7","1","3","7","1","3","5");
            for(var i=0; i<10; i++){
                getlist[i] = bregNo.substring(i, i+1);
            }
            
            for(var i=0; i<9; i++){
                sum += getlist[i]*chkvalue[i];
            }
            
            sum = sum + parseInt((getlist[8]*5)/10);
            sidliy = sum % 10;
            sidchk = 0;
            
            if(sidliy != 0)
                sidchk = 10 - sidliy;
            else
                sidchk = 0;
                
            if(sidchk != getlist[9])
                return false;
            else
                return true;
        },
        
        /* =================================================================================
         * 밸리데이션 관련 공통 함수 끝
         * ================================================================================= */
         
         
         
        /* =================================================================================
         * 그리드 관련 공통 함수
         * ================================================================================= */
         
        /**
         * 그리드 중복데이터 검증
         * @function Utilities.chkDupGrid
         * @description 그리드 중복데이터 검증
         * @param gridId
         * @param fieldName
         * @param value
         * @return count
         * @example Utilities.chkDupGrid(grdList,'menuId', menuId);
         */
       chkDupGrid: function(gridId, fieldName, value){
           var values = gridId.dataProvider.getFieldValues(fieldName);
           var cnt = 0;
           // values.forEach(function (obj, idx, objs) {
           values.forEach(function (obj) {
               if (obj === value) {
                   cnt += 1;
               }
           });
           
           return cnt;
       }
       
       
       /* =================================================================================
        * 그리드 관련 공통 함수 끝
        * ================================================================================= */
       
	};
}

function makeAction(element) {
	var selector = null;

	if (element) {
		selector = $(element).find('input[data-check-all]');

	} else
		selector = $('input[data-check-all]');

	selector.click(function(e) {
		var name = $(this).attr("data-check-all");
		var checked = this.checked;
		$("input[type=checkbox][data-check-id=" + name + "]").each(function() {
			this.checked = checked;
		});
	});

	if (element) {
		selector = $(element).find('select[data-change]');
		selector.push(element);
	} else
		selector = $('select[data-change]');

	selector.each(function() {
		var action = $(this).attr("data-change");
		if (action) {
			$(this).change(function(e) {
				e.preventDefault();
				try {
					if (window[action] && typeof window[action] === "function")
						window[action]($(this), $(this).data());

				} catch (e) {
					console.error(e);
				}
			});
		}
	});

	// --------------------------------------------------------------
	if (element) {
		selector = $(element).find('[data-click]');
		selector.push(selector);
	}

	else
		selector = $('[data-click]');

	selector.each(function() {
        var action = $(this).attr("data-click");
		if (action) {
			$(this).css("cursor", "pointer");
            $(this).click(function(e) { // data-click 태그
                e.preventDefault();
				try {
					if (window[action] && typeof window[action] === "function"){
                        
                        var gridId = $(this).attr("data-grid-id");
                        
                        console.log("1. " + gridId);
                        
                        if(gridId != undefined && window[gridId] != undefined){
                            // 버튼과 연동되는 Grid가 있을 경우(data-grid-id 태그에 연동되는 Grid를 선언했을 경우)
                            
                            /* 현재 포커스를 갖는 셀의 CellIndex 값
                             * itemIndex : 행 주소
                             * column : 컬럼 id
                             * dataRow :
                             * fieldIndex :
                             * fieldName :
                             */
                            var rowIndex = window[gridId].getCurrent();
                            console.log("rowIndex :: {} ",  window[gridId].getCurrent());
                            
                            if(rowIndex.itemIndex > -1){
                                
                                /*
                                 * 선택된 현재 행이 있을 경우,
                                 * 지정한 itemIndex의 값들을 JSON객체로 받는다.
                                 * 입력된 itemIndex가 dataRow와 연결된 행이 아닌경우 null이 출력
                                 */
                                var rowData = window[gridId].getValues(rowIndex.itemIndex);
                                console.log("rowData :: {} ", rowData);
                                
                                var id = $(this).attr("id");
                                console.log("id :: " + $(this).attr("id"));
                                
                                window[action]($(this), $(this).data(), rowIndex, rowData, id); // element, data, row정보, rowData, 컴포넌트id
                                
                            }else {
                                window[action]($(this), $(this).data()); // element, data
                            }
                            
                        }else {
                            // data-grid-id 태그가 없을 경우, element와 data만
                            window[action]($(this), $(this).data()); // element, data
                        }
                    }
						

				} catch (e) {
					console.error(e);
				}
			});
		}
	});

	if (element) {
		selector = $(element).find('[data-enter]');
		selector.push(selector);
	} else
		selector = $('[data-enter]');

	selector.each(function() {
		var action = $(this).attr("data-enter");
		if (action) {
			$(this).keydown(function(e) {
				try {
					if (e.which == 13) {
						e.preventDefault();
						if (window[action] && typeof window[action] === "function")
							window[action]($(this), $(this).data());

					}
				} catch (e) {
					console.error(e);
				}
			});
		}
	});

	if (element) {
		selector = $(element).find('[data-change]');
		selector.push(selector);
	} else
		selector = $('[data-change]');

	selector.each(function() {
		var action = $(this).attr("data-change");
		if (action) {
			$(this).change(function(e) {

				if (window[action] && typeof window[action] === "function") {
					e.preventDefault();
					window[action]($(this), $(this).data());
				}
			});
		}
	});

	if (element) {
		selector = $(element).find('[data-type=date]');
		selector.push(element);
	} else
		selector = $('[data-type=date]');

	selector.each(function() {
		var element = $(this);
		if (element.attr("data-type") != "date")
			return;

		var data = element.data();

		element.datepicker({
			language : 'ko',
			format : 'yyyy-mm-dd',
			autoclose : true,
		});

		if (data.button == 'Y') {
			element.wrap(" <div class='input-group ' ></div>");
			var divParent = element.parent();
			$('<div class="input-group-append"><span class="input-group-text"><i style="cursor:pointer" class="fa fa-calendar"></i> </span></div>').insertAfter(element);
			divParent.find("i.fa-calendar").click(function() {
				element.datepicker("show");
			});

		} else {

		}

	});
}

// 오브젝트 함수
$.fn.serializeObject = function() {

    var obj = null;
    try {
        // this[0].tagName이 form tag일 경우
        if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) {
            var arr = this.serializeArray();
            if(arr){
                obj = {};
                jQuery.each(arr, function() {
                    // obj의 key값은 arr의 name, obj의 value는 value값
                    obj[this.name] = this.value;
                });
            }
        }
    }catch(e) {
        alert(e.message);
    }finally  {}
    
    return obj;
};

/* 사용법
 <input type="text" id="lngtDd" data-enter="search" name="lngtDd" class="form-control" onkeypress="return isNumberKey(event)" onkeyup="return delHangle(event)">
 */
function delHangle(evt){ //한글을 지우는 부분, keyup부분에 넣어준다.
    var objTarget = evt.srcElement || evt.target;
    var _value = event.srcElement.value;  
    if(/[ㄱ-ㅎㅏ-ㅡ가-핳]/g.test(_value)) { 
        //  objTarget.value = objTarget.value.replace(/[ㄱ-ㅎㅏ-ㅡ가-핳]/g,''); // g가 핵심: 빠르게 타이핑할때 여러 한글문자가 입력되어 버린다.
        objTarget.value = null;
        //return false;
    }
}

/* 사용법
 <input type="text" id="lngtDd" data-enter="search" name="lngtDd" class="form-control" onkeypress="return isNumberKey(event)" onkeyup="return delHangle(event)">
 */
function isNumberKey(evt) { // 숫자를 제외한 값을 입력하지 못하게 한다. 
    var charCode = (evt.which) ? evt.which : event.keyCode;
    // Textbox value       
    var _value = event.srcElement.value;     

    if (event.keyCode < 48 || event.keyCode > 57) { 
        if (event.keyCode != 46) { //숫자와 . 만 입력가능하도록함
            return false; 
        } 
    } 
    return true;
}

$(document).ready(function() {
	makeAction();
});