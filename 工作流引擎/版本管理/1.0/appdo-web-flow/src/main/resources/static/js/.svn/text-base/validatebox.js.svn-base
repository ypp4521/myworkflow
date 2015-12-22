$.extend($.fn.validatebox.defaults.rules, {
	multiple : {
		validator : function(value, vtypes) {
			var returnFlag = true;
			var opts = $.fn.validatebox.defaults;
			for (var i = 0; i < vtypes.length; i++) {
				var methodinfo = /([a-zA-Z_]+)(.*)/.exec(vtypes[i]);
				var rule = opts.rules[methodinfo[1]];
				if (value && rule) {
					var parame = eval(methodinfo[2]);
					if (!rule["validator"](value, parame)) {
						returnFlag = false;
						this.message = rule.message;
						break;
					}
				}
			}
			return returnFlag;
		}
	}, 
	ZHONGYINGSHU:{
    	validator:function (value,param){
    		return /^[\u4E00-\u9FA5A-Za-z0-9_]+$/.test(value); 
    	},
    	message:'只支持中文、英文、数字'
    },
    version:{
    	validator:function (value,param){
    		return /^\d{2}\.\d{2}\.\d{4}$/.test(value);
    	},
    	message:'版本号格式不正确'
    },
    shuzidian:{
    	validator:function (value,param){
    		return /^\d(?:[+-]?d+(?:.d+)?|.d*?)?\d$/.test(value);
    	},
    	message:'只能输入数组和点'
    }
    ,
	idCard: {// 验证身份证
         validator: function (value) {
             return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
         },
         message: '身份证号码格式不正确'
     },
     minLength: {
         validator: function (value, param) {
             return value.length >= param[0];
         },
         message: '请输入至少（2）个字符.'
     },
     maxLength: {
         validator: function (value, param) {
             return value.length <= param[0];
         },
         message: '{1}'
     },
     length: { validator: function (value, param) {
         var len = $.trim(value).length;
         return len >= param[0] && len <= param[1];
     	},
         message: "{0}-{1}个字符"
     },
     phone: {// 验证电话号码
         validator: function (value) {
             return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
         },
         message: '格式不正确,请使用下面格式:020-88888888'
     },
     mobile: {// 验证手机号码
         validator: function (value) {
             return /^(13|15|18)\d{9}$/i.test(value);
         },
         message: '手机号码格式不正确'
     },
     intOrFloat: {// 验证整数或小数
         validator: function (value) {
             return /^\d+(\.\d+)?$/i.test(value);
         },
         message: '请输入数字，并确保格式正确'
     },
     currency: {// 验证货币
         validator: function (value) {
             return /^\d+(\.\d+)?$/i.test(value);
         },
         message: '货币格式不正确'
     },
     qq: {// 验证QQ,从10000开始
         validator: function (value) {
             return /^[1-9]\d{4,9}$/i.test(value);
         },
         message: 'QQ号码格式不正确'
     },
     number: {
	        validator: function (value, param) {
	            return /^\d+$/.test(value);
	        },
	        message: '请输入数字'
	 },
     integer: {// 验证整数 可正负数
         validator: function (value) {
             //return /^[+]?[1-9]+\d*$/i.test(value);

             return /^([+]?[0-9])|([-]?[0-9])+\d*$/i.test(value);
         },
         message: '请输入整数'
     },
     age: {// 验证年龄
         validator: function (value) {
             return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value);
         },
         message: '年龄必须是0到120之间的整数'
     },

     chinese: {// 验证中文
         validator: function (value) {
             return /^[\Α-\￥]+$/i.test(value);
         },
         message: '请输入中文'
     },
     english: {// 验证英语
         validator: function (value) {
             return /^[A-Za-z]+$/i.test(value);
         },
         message: '请输入英文'
     },
     unnormal: {// 验证是否包含空格和非法字符
         validator: function (value) {
             return /.+/i.test(value);
         },
         message: '输入值不能为空和包含其他非法字符'
     },
     username: {// 验证用户名
         validator: function (value) {
             return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
         },
         message: '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
     },
     faxno: {// 验证传真
         validator: function (value) {
             //            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value);
             return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
         },
         message: '传真号码不正确'
     },
     zip: {// 验证邮政编码
         validator: function (value) {
             return /^[1-9]\d{5}$/i.test(value);
         },
         message: '邮政编码格式不正确'
     },
     ip: {// 验证IP地址
         validator: function (value,param) {
        	 return /^(\d|[1-9]\d|[1]\d{2}|2[0-5]{2})(\.(\d|[1-9]\d|[1]\d{2}|2[0-5]{2})){3}$/.test(value)
             //return /d+.d+.d+.d+/i.test(value);
         },
         message: '{0}'
     },
     name: {// 验证姓名，可以是中文或英文
         validator: function (value) {
             return /^[\Α-\￥]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
         },
         message: '请输入姓名'
     },
     date: { //格式yyyy-MM-dd或yyyy-M-d
         validator: function (value) {
             return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value);
         },
         message: '请输入合适的日期格式'
     },
     msn: {
         validator: function (value) {
             return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
         },
         message: '请输入有效的msn帐号(例：abc@hotnail(msn/live).com)'
     },
     appName:{
    	 validator: function (value,param) {
    		 var len = $.trim(value).length;
             if(!(len >= param[4] && len <= param[5])){
            	 $.fn.validatebox.defaults.rules.appName.message = param[4]+"-"+param[5]+"个字符" ;
            	 return false;
             }
             if(!$.fn.validatebox.defaults.rules.nonRepaet.validator(value,param)){
            	 $.fn.validatebox.defaults.rules.appName.message = param[2];
            	 return false;
             } 
             return true;
         },
         message: ''
     },
     accName:{
    	 validator: function (value,param) {
    		 var len = $.trim(value).length;
             if(!(len >= param[4] && len <= param[5])){
            	 $.fn.validatebox.defaults.rules.accName.message = param[4]+"-"+param[5]+"个字符(非中文)" ;
            	 return false;
             }
             
             if($.fn.validatebox.defaults.rules.chinese.validator(value)){
            	 
        		 $.fn.validatebox.defaults.rules.accName.message = param[4]+"-"+param[5]+"个字符(非中文)" ;
        		 return false;
            	 
             }
             
             if(!$.fn.validatebox.defaults.rules.nonRepaet.validator(value,param)){
            	 $.fn.validatebox.defaults.rules.accName.message = param[2];
            	 return false;
             } 
             return true;
         },
         message: ''
     },
     nonRepaet:{
    	 validator: function (value,param) {
             var flag ; 
             var data = param[1]+"="+value;
             if(value!=""){
	    		 $.ajax({
	                 url : param[0],  
	                 type : 'get',
	                 timeout : 60000,
	                 data: data ,
	                 async: false,
	                 success : function(data, textStatus, jqXHR) {     
	                     if (data.status == "ok") {  
	                         flag = true;      
	                     }else{
	                    	 var id = $("#"+param[3]).val();//表单里id的的值
	                    	 if(id && id==data.info){
	                    		 flag = true;
	                    	 }else{
	                    		 flag = false;
	                    	 }
	                         //$.fn.validatebox.defaults.rules.nonRepaet.message = param[2];
	                     }  
	                 }  
	             });
             }
    		 if(flag){  
                 $("#"+param[1]).removeClass('validatebox-invalid');  
             }  
             return flag;
         },
         message: '{2}' 
     },
     port: {// 验证端口号
         validator: function (value,param) {
         	value = $.trim(value);
         	if(param[0]==0){
         		if($.fn.validatebox.defaults.rules.number.validator(value) && (value >= 0 && value<=65535)){
             		return true ;
             	}else{
             		return false ;
             	}
         	}else {
         		if(/^\d*([ ]?\d{0,}){0,}?$/i.test(value)){
         			arr = value.split(" ");
         			for(i=0;i<arr.length;i++){
         				var index = parseInt(arr[i]);
         				if(!($.fn.validatebox.defaults.rules.number.validator(index) && (index >= 0 && index <= 65535 ))){
                     		return false ;
                     	}
         			}
         			return true ;
         		}
         	}
         	return false; 
         },
         message: '{1}'
     }
});