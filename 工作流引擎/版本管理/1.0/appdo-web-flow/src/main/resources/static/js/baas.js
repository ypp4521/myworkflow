/*----------- content-------------*/
var mbaasSid;
var bassAppObj;
function sso_(){
    var self = this;
    self.ssocallback = function(data, cb){
        if (14504 == data.status || '14504' == data.status) {
            $.cookie('x-mas-app-info', '/public', {
                path: '/'
            });
            location = location;
            return;
        }
        else {
                        if ('ok' == data.status) {
                if (typeof cb === 'function')
                                data.status='0';
                    cb(data);
            }
            else {
                alert('login exception');
            }
                }
    };
  //sso单点登陆
    self.loadingLogin = function(loginCb){
                loginCb = (loginCb == null ? null : loginCb);
                $.ajax({
                        type: "get",
                        url: mbaasLoginUrl,
                        crossDomain: true,
                        dataType: "jsonp",
                        jsonp: "callback",
                        jsonpCallback: "ssocallback",
                        success: function(data){
                                data.status='0';
                                loginCb(data);
                                addSidSession();
                        },
                        error: function(err){
                        	 var data={};
                             data.status='1';
                             loginCb(data);
                     }
             });
     };
 //调用baas创建应用接口
 self.addAppToMbaas = function(baasAppObj){
     var appInfo = eval("("+baasAppObj+")");
     var bassAppUrl = mbaasAddAppUrl.replace('appid', appInfo.appid);
     var data = JSON.stringify(appInfo);
     $.ajax({
                     type: 'GET',
                     url: bassAppUrl,
                     data:{"_method":"POST","_headers":'{"x-mas-app-info":"'+appInfo.appid+'/public"}',"_field":data},
                     dataType: "jsonp",
                     jsonp: "callback",
                     jsonpCallback: "ssocallback",
                     success: function(data){
                     },
                     error: function(err){
                     }
             });
 };
//调用baas删除应用接口
 self.delAppToMbaas = function(appid){
     var bassAppUrl = mbaasAddAppUrl.replace('appid', appid);
     $.ajax({
                     type: 'GET',
                     url: bassAppUrl,
                     data: {'_method':'DELETE','body':'{"appid":"'+appid+'"}'},
                     dataType: "jsonp",
                     jsonp: "callback",
                     jsonpCallback: "ssocallback",
                     success: function(data){
                     },
                     error: function(err){
                     }
             });
 };
};
var SSO = new sso_();
