var validateConfig = new Object({
	languages : ($.cookie('language')?$.cookie('language'):'en'),
	language_en : "en",
	language_zh : "zh",
	language_jp : "ja"
}); 


/**
 * 定义DMS 验证相关信息
 */
var dmsValidate = function() {
	
	/**
	 * 针对form 表单进行验证
	 */
	var initForm = function(formObj){
		$(formObj).validate({
            focusInvalid: false, 
            invalidHandler: function(event, validator) { 
            },
            highlight: function(element, errorClass, validClass) { 
            	$(element).closest('.form-group').addClass('has-warning'); 
            },
            unhighlight: function (element, errorClass, validClass) { 
            	$(element).closest('.form-group').removeClass('has-warning');
            	//显示错误信息
            	showErrorTip(null,element,'destroy');
            },
            success: function(error, element) {
            },
            errorPlacement: function(error, element) {
            	//显示错误信息
            	showErrorTip(error,element,'show');
            }
        });
	};
	
	/**
	 * 显示错误信息
	 */
	function showErrorTip(error,element,method){
		var tipContainer;
		if($(element).attr("type")=="checkbox"){
			tipContainer = $(element).closest("div.checkbox-list");
		}else if($(element).attr("type")=="radio"){
			tipContainer = $(element).closest("div.radio-list");
		}else{
			tipContainer = element;
		}
		
		//显示错误信息
		if(error&&!isStringNull(error.text())&&method=='show'){
            $(tipContainer).attr('title', $(error).text()).attr('data-original-title', $(error).text()).tooltip({placement:"top",trigger:"manual",template:'<div class="tooltip" role="tooltip"><div class="tooltip-title"><i class="fa fa-warning"></i></div><div class="tooltip-inner"></div></div>'}).tooltip('show');
            //绑定事件
            $(tipContainer).next("div.tooltip").off("click").on("click",function(){
            	$(tipContainer).tooltip('destroy');
            });
    	}else{
    		$(tipContainer).tooltip('destroy');
    	}
	}
	/**
	 * 主页初始化
	 */
	var init = function(container){
		container==undefined?document:container;
		if($("div form",container).size()>0){
			$("form",container).each(function(index,form){
				//初始化Form
				initForm(form);
				//绑定事件
				bindContainerEvent(form,true);
			});
		}else{
			bindContainerEvent(container,false);
		}
	};
	
	/**
	 * 绑定事件
	 */
	var bindContainerEvent = function(container,isForm){
		$("div.date-picker input",container).on("change",function(event){
			var form = isForm?container:$(this).closest("div form").get(0);
			if(form){
				$(form).validate().element($(this));
			}
		});
		
		$("select.bs-select",container).on("change",function(event){
			if($(this).attr("data-validateFlag")){
				var form = isForm?container:$(this).closest("div form").get(0);
				if(form){
					$(form).validate().element($(this));
				}
			}else{
				$(this).attr("data-validateFlag","true");
			}
		});
	};
	
	return {
		init:function(container){
			init(container);
		},
		initForm:function(formObj){
			initForm(formObj);
		}
	};
}();
/**
 * 执行初始化
 */
$(function(){
	//设置默认值
	$.validator.setDefaults({
			focusCleanup:true,
			autoCreateRanges:true,
//			onkeyup: null,
			ignoreTitle:true

	});
	
	
	/**
	 * 
	 */
	$.validator.addMethod( "required", function( value, element,param ) {
		value = $.trim(value);
		// Check if dependency is met
		if ( !this.depend( param, element ) ) {
			return "dependency-mismatch";
		}
		if ( element.nodeName.toLowerCase() === "select" ) {
			// Could be an array for select-multiple or a string, both are fine this way
			var val = $( element ).val();
			return val && val.length > 0;
		}
		if ( this.checkable( element ) ) {
			return this.getLength( value, element ) > 0;
		}
		return $.trim(value).length > 0;
	});
	/**
	 * 校验VIN 码
	 */
	$.validator.addMethod( "VIN", function( v, element ) {
		v = $.trim(v);
		if(this.optional( element )){
			return true;
		}
		if ( v.length !== 17 ) {
			return false;
		}

		var LL = [ "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" ],
			VL = [ 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 7, 9, 2, 3, 4, 5, 6, 7, 8, 9 ],
			FL = [ 8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2 ],
			rs = 0,
			i, n, d, f, cd, cdv;

		for ( i = 0; i < 17; i++ ) {
			f = FL[ i ];
			d = v.slice( i, i + 1 );
			if ( i === 8 ) {
				cdv = d;
			}
			if ( !isNaN( d ) ) {
				d *= f;
			} else {
				for ( n = 0; n < LL.length; n++ ) {
					if ( d.toUpperCase() === LL[ n ] ) {
						d = VL[ n ];
						d *= f;
						if ( isNaN( cdv ) && n === 8 ) {
							cdv = LL[ n ];
						}
						break;
					}
				}
			}
			rs += d;
		}
		cd = rs % 11;
		if ( cd === 10 ) {
			cd = "X";
		}
		if ( cd == cdv ) {
			return true;
		}
		return false;
	}, $.validator.messages.VIN );
	
	//定义最大长度
	$.validator.addMethod( "maxlength", function(value, element, param ) {
		value = $.trim(value);
		if(this.optional( element )){
			return true;
		}
		var length = $.isArray( value ) ? value.length : getValidatorChineseLength( value, element,this);
		return length <= param;
	}, $.validator.messages.maxlength );
	
	//定义最小长度
	$.validator.addMethod( "minlength", function(value, element, param ) {
		value = $.trim(value);
		if(this.optional( element )){
			return true;
		}
		var length = $.isArray( value ) ? value.length : getValidatorChineseLength( value, element,this);
		return length >= param;
	}, $.validator.messages.minlength );
	
	/**
	 * 限制最大值
	 */
	$.validator.addMethod( "max", function(value, element, param ) {
		value = $.trim(value);
		if(this.optional( element )){
			return true;
		}
		return parseFloat(value) <= parseFloat(param);
	}, $.validator.messages.max);
	
	/**
	 * 限制最小值
	 */
	$.validator.addMethod( "min", function(value, element, param ) {
		value = $.trim(value);
		if(this.optional( element )){
			return true;
		}
		return parseFloat(value) >= parseFloat(param);
	}, $.validator.messages.min );
	
	/**
	 * 限制最小值,不包括等于
	 */
	$.validator.addMethod( "minGT", function(value, element, param ) {
		value = $.trim(value);
		if(this.optional( element )){
			return true;
		}
		return parseFloat(value) > parseFloat(param);
	}, $.validator.messages.minGT );
	
	/**
	 * 限制身份证号
	 */
	$.validator.addMethod( "IDNumber", function(code, element) {
		code = $.trim(code);
		if(this.optional( element )){
			return true;
		}
		
		if(validateConfig.languages.substring(0,2)== validateConfig.language_zh){
			var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
		}else{
			var city={11:"Beijing",12:"Tianjing",13: "Hebei", 14: "Shanxi", 15: "Inner Mongolia", 21: "Liaoning", 22: "Jilin", 23: "Heilongjiang", 31: "Shanghai", 32: "Jiangsu", 33: "Zhejiang", 34: "Anhui", 35: "Fujian", 36: "Jiangxi", 37: "Shandong" ,41:"Henan", 42: "Hubei", 43: "Hunan", 44: "Guangdong", 45: "Guangxi", 46: "Hainan", 50: "Chongqing", 51: "Sichuan", 52: "Guizhou", 53: "Yunnan", 54: "Tibet", 61: "Shanxi", 62: "Gansu",  63: "Qinghai", 64: "Ningxia",65:"xinjiang" ,71:"Taiwan",81: "Hong Kong", 82: "Macao", 91: "foreign"};
		}
        var tip = "";
        
        if(!code || !/^\d{15}$/ ||!/^\d{6}(18|19|20)\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
        	tip = (validateConfig.languages.substring(0,2)== validateConfig.language_zh?"身份证号格式错误.":"ID number format is wrong.");
            return false;
        }
        
       else if(!city[code.substr(0,2)]){
    	    tip = (validateConfig.languages.substring(0,2)== validateConfig.language_zh?"地址编码错误.":"Address code error.");
            return false;
        }
        else{
            //18位身份证需要验证最后一位校验位
            if(code.length == 18){
                code = code.split('');
                //∑(ai×Wi)(mod 11)
                //加权因子
                var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                //校验位
                var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                var sum = 0;
                var ai = 0;
                var wi = 0;
                for (var i = 0; i < 17; i++)
                {
                    ai = code[i];
                    wi = factor[i];
                    sum += ai * wi;
                }
                var last = parity[sum % 11];
                if(parity[sum % 11] != code[17]){
                    tip = (validateConfig.languages.substring(0,2)== validateConfig.language_zh?"校验位错误.":"Parity bit error.");
                    return false;
                }
            }
        }
        return true;
	}, $.validator.messages.IDNumber );
	
	/**
	 * 限制车牌号
	 */
	$.validator.addMethod( "license", function(value, element) {
		value = $.trim(value);
		if(this.optional( element )){
			return true;
		}
		var express = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[警京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{0,1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
	    return express.test(value);
	}, $.validator.messages.license );
	
	/**
	 * 限制手机号
	 */
	$.validator.addMethod( "phone", function(value, element) {
		value = $.trim(value);
		if(this.optional( element )){
			return true;
		}
		var pattern = /^1[34578]\d{9}$/;  
	    if (pattern.test(value)) {  
	        return true;  
	    }  
	    return false;  
	}, $.validator.messages.license );
	
	
	/**
	 * 限制数字位数
	 */
	$.validator.addMethod( "digits", function(value, element) {
		value = $.trim(value);
		if(this.optional( element )){
			return true;
		}
		var g = /^[1-9][0-9]*$/;
		if(g.test(value)){
			return true;
		}
		g = /^[0]?$/;
		if(g.test(value)){
			return true;
		}
		return false;
	}, $.validator.messages.digits );
	
	/**
	 * 限制金额/价格(只能数入正数)
	 */
	$.validator.addMethod( "money", function(value, element) {
		value = $.trim(value);
		if(this.optional( element )){
			return true;
		}
		var g=/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
		if(g.test(value)){
			return true;
		}
		g = /^[0]?$/;
		if(g.test(value)){
			return true;
		}
	}, $.validator.messages.money );
	
	/**
	 * 限制金额/价格(正负数)
	 */
	$.validator.addMethod( "moneyMinus", function(value, element) {
		value = $.trim(value);
		if(this.optional( element )){
			return true;
		}
		var g=/^[-]?(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
		if(g.test(value)){
			return true;
		}
		g = /^[0]?$/;
		if(g.test(value)){
			return true;
		}
	}, $.validator.messages.moneyMinus );
	
	/**
	 * 限制金额/价格
	 */
	$.validator.addMethod( "decimal", function(value, element) {
		value = $.trim(value);
		if(this.optional( element )){
			return true;
		}
		var g=/^(([1-9][0-9]*)|(([0]\.\d{1,}|[1-9][0-9]*\.\d{1,})))$/;
		if(g.test(value)){
			return true;
		}
		g = /^[0]?$/;
		if(g.test(value)){
			return true;
		}
	}, $.validator.messages.decimal );
	
	/**
	 * 限制整数位数
	 */
	$.validator.addMethod( "maxDigit", function(value, element, param ) {
//		if(!$(element).attr("maxlength")){
//			$(element).attr("maxlength",param);
//		}
		value = $.trim(value);
		if(this.optional( element )){
			return true;
		}
		//截取整数
		var digitStr = value.split(".");
	    if (digitStr[0].length<=param) {  
	        return true;
	    }
	    return false;  
	}, $.validator.messages.maxDigit );
	
	/**
	 * 限制整数位数
	 */
	$.validator.addMethod( "maxPrecision", function(value, element, param ) {
//		if(!$(element).attr("maxlength")){
//			$(element).attr("maxlength",param);
//		}
		value = $.trim(value);
		if(this.optional( element )){
			return true;
		}
		//截取整数
		var digitStr = value.split(".");
		if(digitStr.length==2){
			if (digitStr[1].length > param) {  
		        return false;
		    }
		}
	    return true;  
	}, $.validator.messages.maxPrecision);
	
	/**
	 * 限制邮箱
	 */
	$.validator.addMethod( "email", function(value, element) {
		value = $.trim(value);
		if(this.optional( element )){
			return true;
		}
		var g = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
		if (!g.test(value)) {  
	        return false;  
	    }
	    if (value.length>60) {  
	        return false;  
	    }
	    return true;  
	}, $.validator.messages.email );
	
	/**
	 * 限制邮编
	 */
	$.validator.addMethod( "zipCode", function(value, element) {
		value = $.trim(value);
		if(this.optional( element )){
			return true;
		}
		var g = /^[1-9]\d{5}(?!\d)$/;
		if (!g.test(value)) {  
	        return false;  
	    }
	    return true;  
	}, $.validator.messages.zipCode );
	
	/**
	 * 限制配件代码
	 */
	$.validator.addMethod( "partCode", function(value, element) {
		value = $.trim(value);
		if(this.optional( element )){
			return true;
		}
		var g = /^[\d|a-zA-Z|/|\\|-]{1,20}$/;
		if (!g.test(value)) {  
	        return false;  
	    }
	    return true;   
	}, $.validator.messages.partCode );
	
	/**
	 * 限制系统代码
	 */
	$.validator.addMethod( "systemCode", function(value, element) {
		value = $.trim(value);
		if(this.optional( element )){
			return true;
		}
		var g = /^[\d|a-zA-Z|-]{1,}$/;
		if (!g.test(value)) {  
	        return false;  
	    }
		//自动转化写
		if($(element).attr("data-autoUpper")==undefined||$(element).attr("data-autoUpper")=="true"){
			$(element).setDmsValue(value.toUpperCase());
		}
	    return true;   
	}, $.validator.messages.systemCode );
	/**
	 * 限制传真
	 */
	$.validator.addMethod( "fax", function(value, element) {
		if(this.optional( element )){
			return true;
		}
		var g = /^(\d{3,4}-)?\d{7,8}$/;
		if (!g.test(value)) {  
	        return false;  
	    }
	    return true;   
	}, $.validator.messages.fax );
	/**
	 * 密码规则
	 * 1、长度至少8位；
     * 2、数字、字母、字符至少包含两种。
	 */
	$.validator.addMethod( "passWD", function(value, element) {
		if(this.optional( element )){
			return true;
		}
//		var g = /^(?!\d+$)(?![a-zA-Z]+$)(?![\\!\\$\\%\\^\\&\\*\\(\\)\\-\\+\\=\\-_#@]+$).{8,}$/;
		var g = /^(?!\d+$)(?![a-zA-Z]+$)(?![^(a-zA-Z|\d|\u4E00-\u9FA5)]+$).{8,}$/;
		if (!g.test(value)) {  
	        return false;  
	    }
	    return true;   
	}, $.validator.messages.passWD );
	
	//提示消息------覆盖默认值
	$.extend( $.validator.messages, {
	
		required: $.validator.format(validateConfig.languages.substring(0,2)== validateConfig.language_zh?"这是必填字段.":"This is a required field."),
		max: $.validator.format(validateConfig.languages.substring(0,2)== validateConfig.language_zh?"请输入不大于 {0} 的数值":"Please input a value not greater than {0}."),
		min: $.validator.format( validateConfig.languages.substring(0,2)== validateConfig.language_zh?"请输入不小于 {0} 的数值":"Please input a value that is not less than {0}." ),
		minGT: $.validator.format(validateConfig.languages.substring(0,2)== validateConfig.language_zh?"请输入大于 {0} 的数值":"Please input a value greater than {0}."),
		number: $.validator.format( validateConfig.languages.substring(0,2)== validateConfig.language_zh?"请输入有效的数字":"Please input a valid number."),
		minlength:function(parematers,element){
			if($(element).attr("type")=="checkbox"){
				return $.validator.format(validateConfig.languages.substring(0,2)== validateConfig.language_zh?"请输入有效的数字":"At least choose item {0}.", parematers);
			}else{
				return $.validator.format(validateConfig.languages.substring(0,2)== validateConfig.language_zh?"最少要输入 {0} 个字符(注：1汉字=3字符)":"At least input{0} characters (note: 1 Chinese characters = 3 characters).", parematers );
			}
		},
		maxlength:function(parematers,element){
			if($(element).attr("type")=="checkbox"){
				return $.validator.format( validateConfig.languages.substring(0,2)== validateConfig.language_zh?"最多选择 {0} 项":"Max select {0}item.", parematers);
			}else{
				return $.validator.format( validateConfig.languages.substring(0,2)== validateConfig.language_zh?"最多只能输入 {0} 个字符(注：1汉字=3字符)":"Can only input {0} characters at most (Note: 1 Chinese characters = 3 characters).", parematers );
			}
		},
		VIN:function(parematers,element){
			return $.validator.format( validateConfig.languages.substring(0,2)== validateConfig.language_zh?"请输入正确的VIN":"Please input the correct VIN", parematers );
		},
		IDNumber:function(parematers,element){
			return $.validator.format(validateConfig.languages.substring(0,2)== validateConfig.language_zh?"请输入正确的身份证号":"Please input the correct ID number", parematers );
		},
		license:function(parematers,element){
			return $.validator.format(validateConfig.languages.substring(0,2)== validateConfig.language_zh?"请输入正确的车牌号":"Please input the correct license plate number", parematers );
		},
		phone:function(parematers,element){
			return $.validator.format( validateConfig.languages.substring(0,2)== validateConfig.language_zh?"请输入正确的手机号":"please input a valid phone number", parematers );
		},
		maxDigit:function(parematers,element){
			return $.validator.format(validateConfig.languages.substring(0,2)== validateConfig.language_zh?"最多输入{0}位整数":"Input the maximum {0} bit integer", parematers );
		},
		digits:function(parematers,element){
			return $.validator.format( validateConfig.languages.substring(0,2)== validateConfig.language_zh?"最多输入{0}位整数":"Only positive integers can be inputed", parematers );
		},
		email:function(parematers,element){
			return $.validator.format(validateConfig.languages.substring(0,2)== validateConfig.language_zh?"请输入有效的邮件地址(最大长度60)":"Please input a valid email address (max. Length 60)", parematers );
		},
		money:function(parematers,element){
			return $.validator.format(validateConfig.languages.substring(0,2)== validateConfig.language_zh?"请输入正确的金额(最多2位小数)":"Please input the correct amount (up to 2 decimal places)", parematers );
		},
		moneyMinus:function(parematers,element){
			return $.validator.format( validateConfig.languages.substring(0,2)== validateConfig.language_zh?"请输入正确的金额(最多2位小数)":"Please input the correct amount (up to 2 decimal places)", parematers );
		},
		decimal:function(parematers,element){
			return $.validator.format( validateConfig.languages.substring(0,2)== validateConfig.language_zh?"请输入正确的数值":"Please input the correct value", parematers );
		},
		maxPrecision:function(parematers,element){
			return $.validator.format( validateConfig.languages.substring(0,2)== validateConfig.language_zh?"最多输入{0}位小数":"Input up to {0} bit decimal ", parematers );
		},
		zipCode:function(parematers,element){
			return $.validator.format( validateConfig.languages.substring(0,2)== validateConfig.language_zh?"请输入正确的邮政编号":"Please input the correct postal number", parematers );
		},
		partCode:function(parematers,element){
			return $.validator.format(validateConfig.languages.substring(0,2)== validateConfig.language_zh?"请输入正确的配件代码(最大长度20)":"Please input the correct spare part code (maximum length 20)", parematers );
		},
		systemCode:function(parematers,element){
			//alert("lang: "+$.cookie('language'));
			var msg = (validateConfig.languages.substring(0,2)== validateConfig.language_zh?"代码只能为字母数字.":"The code can only be letters and numbers.");
			return $.validator.format( msg, parematers );
		},
		fax:function(parematers,element){
			return $.validator.format(validateConfig.languages.substring(0,2)== validateConfig.language_zh?"请输入正确传真(传真格式为:XXX-12345678或XXXX-1234567或XXXX-12345678)":"Please input the correct fax (fax format: XXX-12345678 or XXXX-1234567 or XXXX-12345678)", parematers );
		},
		passWD:function(parematers,element){
			return $.validator.format(validateConfig.languages.substring(0,2)== validateConfig.language_zh?"长度至少8位;数字,字母,特殊字符至少包含两种":"At least 8 bits; numbers, letters, special characters at least include two types", parematers );
		},
		equalTo:function(parematers,element){
			var compareElement = $(parematers);
			var compareLabel = $(compareElement).closest("div.form-group").find("label:first").text();
			return $.validator.format(validateConfig.languages.substring(0,2)== validateConfig.language_zh?"输入的值与{0}不一致":"The inputed value does not match {0}", compareLabel );
		},
	});
});
