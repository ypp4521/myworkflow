<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../../../common/common.jsp"%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>表单设计器</title>
    <link href="${ctx }/static/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <!--[if lte IE 6]>
    <link rel="stylesheet" type="text/css" href="${ctx }/styles/bootstrap/css/bootstrap-ie6.css">
    <![endif]-->
    <!--[if lte IE 7]>
    <link rel="stylesheet" type="text/css" href="${ctx }/styles/bootstrap/css/ie.css">
    <![endif]-->
    <link href="${ctx }/static/css/site.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="easyui-layout" style="width: 100%; height: 100%;">
	<div region="north" split="false" border="false" class="st_head">
		<div class="titleTop">
			<span><img class="titleImage" src="${ctx }/static/images/daohang.png">
			</span>
			<span>单据管理->表单设计</span>
		</div>
	</div>
	<div  region="center" style=" overflow-y: hidden;padding-left:50px;padding-top:10px">
    <form method="post" id="saveform" name="saveform" action="">
        <input type="hidden" name="fields" id="fields" value="${form.fieldNum }">
        <div class="row">
        	<table width="100%">
        		<tr>
        			<td>模板名称</td>
        			<td><input type="text" id="businame" value="${metaBusi.businame }"/></td>
        			<td>模板编码</td>
        			<td><input type="text" id="busicode" value="${metaBusi.busicode }"/></td>
        			<td>应用</td>
        			<td>
        				<select id="isapp">
        					<option value="pc">pc</option>
        					<option value="app">app</option>
        				</select>
        			</td>
        		</tr>
        		<tr>
        			<td>描述</td>
        			<td colspan="5">
        				<textarea rows="2" cols="100" id="remark">${metaBusi.remark }</textarea>
        			</td>
        		</tr>
        	</table>
        </div>
        <div class="row">
            <div class="span2">
                <ul class="nav nav-list">
                    <li class="nav-header" style="padding-left: 0px">${metaCustom.metaname }(${metaCustom.metacode })表单</li>
                    <c:forEach items="${listField }" var="field">
                    	<li><a href="javascript:void(0);" onclick="formDesign.exec('${field.fieldtype}','${field.fieldname }','${field.fieldcode }');" class="btn btn-link">${field.fieldname }</a></li>
                    </c:forEach>
                    <!-- 
                    <li><a href="javascript:void(0);" onclick="formDesign.exec('macros');" class="btn btn-link">宏控件</a></li>
                    <li><a href="javascript:void(0);" onclick="formDesign.exec('progressbar');" class="btn btn-link">进度条</a></li>
                    <li><a href="javascript:void(0);" onclick="formDesign.exec('qrcode');" class="btn btn-link">二维码</a></li>
                    <li><a href="javascript:void(0);" onclick="formDesign.exec('more');" class="btn btn-link">一起参与...</a></li>
                     -->
                </ul>
            </div>

            <div class="span0">
                <script id="formEditor" type="text/plain" style="width:100%;">${originalHtml}</script>
            </div>
        </div>
        <div align="center">
        	<input type="button" value="保存" onclick="formDesign.fnCheckForm('save')"/>
        	<input type="button" value="返回" onclick="window.location.href='${ctx}/settingMeta/metaBusiList/${metaid}'"/>
        </div>
    </form>
    </div>
    </div>
<script type="text/javascript" charset="utf-8" src="${ctx }/static/js/jquery.js"></script>

<script type="text/javascript" charset="utf-8" src="${ctx }/workflow/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/workflow/ueditor/ueditor.all.js"> </script>
<script type="text/javascript" charset="utf-8" src="${ctx }/workflow/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/workflow/ueditor/formdesign/formdesign.v4.js"></script>
<!-- script start-->
<script type="text/javascript">
var metaCode='${metaCustom.metacode }';
var formEditor = UE.getEditor('formEditor',{
    qftool:true,//是否显示，设计器的 toolbars
    textarea: 'design_content',
    //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
    toolbars:[[
        'fullscreen', 'source', '|', 'undo', 'redo', '|','bold', 'italic', 'underline', 'fontborder', 'strikethrough',  'removeformat', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist','|', 'fontfamily', 'fontsize', '|', 'indent', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',  'link', 'unlink',  '|',  'horizontal',  'spechars',  'wordimage', '|', 'inserttable', 'deletetable',  'mergecells',  'splittocells']],
    //focus时自动清空初始化时的内容
    //autoClearinitialContent:true,
    //关闭字数统计
    wordCount:false,
    //关闭elementPath
    elementPathEnabled:false,
    //默认的编辑区域高度
    initialFrameHeight:300
    ///,iframeCssUrl:"${ctx }/static/js/bootstrap/css/bootstrap.css" //引入自身 css使编辑器兼容你网站css
    //更多其他参数，请参考ueditor.config.js中的配置项
});
var selectfieldcode;
var selectfieldname;
var formDesign = {
    /*执行控件*/
    exec : function (method,fieldname,fieldcode) {
    	selectfieldcode=fieldcode;
    	selectfieldname=fieldname;
    	formEditor.execCommand(method);
    },
    /*
     Javascript 解析表单
     template 表单设计器里的Html内容
     fields 字段总数
     */
    parse_form:function(template,fields)
    {
        //正则  radios|checkboxs|select 匹配的边界 |--|  因为当使用 {} 时js报错 (plugins|fieldname|fieldflow)
        var preg =  /(\|-<span(((?!<span).)*plugins=\"(radios|checkboxs|select)\".*?)>(.*?)<\/span>-\||<(img|input|textarea|select).*?(<\/select>|<\/textarea>|\/>))/gi,preg_attr =/(\w+)=\"(.?|.+?)\"/gi,preg_group =/<input.*?\/>/gi;
        if(!fields) fields = 0;

        var template_parse = template,template_data = new Array(),add_fields=new Object(),checkboxs=0;

        var pno = 0;
        template.replace(preg, function(plugin,p1,p2,p3,p4,p5,p6){
            var parse_attr = new Array(),attr_arr_all = new Object(),name = '', select_dot = '' , is_new=false;
            var p0 = plugin;
            var tag = p6 ? p6 : p4;
            //alert(tag + " \n- t1 - "+p1 +" \n-2- " +p2+" \n-3- " +p3+" \n-4- " +p4+" \n-5- " +p5+" \n-6- " +p6);

            if(tag == 'radios' || tag == 'checkboxs')
            {
                plugin = p2;
            }else if(tag == 'select')
            {
                plugin = plugin.replace('|-','');
                plugin = plugin.replace('-|','');
            }
            plugin.replace(preg_attr, function(str0,attr,val) {
                if(attr=='name')
                {
                    if(val=='NEWFIELD')
                    {
                        is_new=true;
                        fields++;
                        val = 'data_'+fields;
                    }
                    name = val;
                }

                if(tag=='select' && attr=='value')
                {
                    if(!attr_arr_all[attr]) attr_arr_all[attr] = '';
                    attr_arr_all[attr] += select_dot + val;
                    select_dot = ',';
                }else
                {
                    attr_arr_all[attr] = val;
                }
                var oField = new Object();
                oField[attr] = val;
                parse_attr.push(oField);
            })
            /*alert(JSON.stringify(parse_attr));return;*/
            if(tag =='checkboxs') /*复选组  多个字段 */
            {
                plugin = p0;
                plugin = plugin.replace('|-','');
                plugin = plugin.replace('-|','');
                var name = 'checkboxs_'+checkboxs;
                attr_arr_all['parse_name'] = name;
                attr_arr_all['name'] = '';
                attr_arr_all['value'] = '';

                attr_arr_all['content'] = '<span qplugins="checkboxs"  title="'+attr_arr_all['title']+'">';
                var dot_name ='', dot_value = '';
                p5.replace(preg_group, function(parse_group) {
                    var is_new=false,option = new Object();
                    parse_group.replace(preg_attr, function(str0,k,val) {
                        if(k=='name')
                        {
                        	if(val=='NEWFIELD')
                            {
                                is_new=true;
                                fields++;
                                val = 'data_'+fields;
                            }

                            attr_arr_all['name'] += dot_name + val;
                            dot_name = ',';

                        }
                        else if(k=='value')
                        {
                            attr_arr_all['value'] += dot_value + val;
                            dot_value = ',';

                        }
                        option[k] = val;
                    });

                    if(!attr_arr_all['options']) attr_arr_all['options'] = new Array();
                    attr_arr_all['options'].push(option);
                    if(!option['checked']) option['checked'] = '';
                    var checked = option['checked'] ? 'checked="checked"' : '';
                    attr_arr_all['content'] +='<input type="checkbox" name="'+option['name']+'" value="'+option['value']+'" fieldname="' + attr_arr_all['fieldname'] + option['fieldname'] + '" fieldflow="' + attr_arr_all['fieldflow'] + '" showval="'+option['showval']+'" '+checked+'/>'+option['showval']+'&nbsp;';

                    if(is_new)
                    {
                        var arr = new Object();
                        arr['name'] = option['name'];
                        arr['qplugins'] = attr_arr_all['qplugins'];
                        arr['fieldname'] = attr_arr_all['fieldname'] + option['fieldname'];
                        arr['fieldflow'] = attr_arr_all['fieldflow'];
                        add_fields[option['name']] = arr;
                    }

                });
                attr_arr_all['content'] += '</span>';

                //parse
                template = template.replace(plugin,attr_arr_all['content']);
                template_parse = template_parse.replace(plugin,'{'+name+'}');
                template_parse = template_parse.replace('{|-','');
                template_parse = template_parse.replace('-|}','');
                template_data[pno] = attr_arr_all;
                checkboxs++;

            }else if(name)
            {
                if(tag =='radios') /*单选组  一个字段*/
                {
                    plugin = p0;
                    plugin = plugin.replace('|-','');
                    plugin = plugin.replace('-|','');
                    attr_arr_all['value'] = '';
                    attr_arr_all['content'] = '<span qplugins="radios" name="'+attr_arr_all['name']+'" title="'+attr_arr_all['title']+'">';
                    var dot='';
                    p5.replace(preg_group, function(parse_group) {
                        var option = new Object();
                        parse_group.replace(preg_attr, function(str0,k,val) {
                            if(k=='value')
                            {
                                attr_arr_all['value'] += dot + val;
                                dot = ',';
                            }
                            option[k] = val;
                        });
                        //option['name'] = attr_arr_all['name'];
                        if(!attr_arr_all['options']) attr_arr_all['options'] = new Array();
                        attr_arr_all['options'].push(option);
                        if(!option['checked']) option['checked'] = '';
                        var checked = option['checked'] ? 'checked="checked"' : '';
                        attr_arr_all['content'] +='<input type="radio" name="'+option['name']+'" value="'+option['value']+'" showval="'+option['showval']+'" '+checked+'/>'+option['showval']+'&nbsp;';

                    });
                    attr_arr_all['content'] += '</span>';

                }else
                {
                    attr_arr_all['content'] = is_new ? plugin.replace(/NEWFIELD/,name) : plugin;
                }
                //attr_arr_all['itemid'] = fields;
                //attr_arr_all['tag'] = tag;
                template = template.replace(plugin,attr_arr_all['content']);
                template_parse = template_parse.replace(plugin,'{'+name+'}');
                template_parse = template_parse.replace('{|-','');
                template_parse = template_parse.replace('-|}','');
                if(is_new)
                {
                    var arr = new Object();
                    arr['name'] = name;
                    arr['qplugins'] = attr_arr_all['qplugins'];
                    arr['title'] = attr_arr_all['title'];
                    arr['orgtype'] = attr_arr_all['orgtype'];
                    arr['fieldname'] = attr_arr_all['fieldname'];
                    arr['fieldflow'] = attr_arr_all['fieldflow'];
                    add_fields[arr['name']] = arr;
                }
                template_data[pno] = attr_arr_all;


            }
            pno++;
        })
        var view = template.replace(/{\|-/g,'');
        view = view.replace(/-\|}/g,'');
        var parse_form = new Object({
            'fields':fields,//总字段数
            'template':template,//完整html
            'parse':view,
            'data':template_data,//控件属性
            'add_fields':add_fields//新增控件
        });
        return JSON.stringify(parse_form);
    },
    /*type  =  save 保存设计 versions 保存版本  close关闭 */
    fnCheckForm : function ( type ) {
        if(formEditor.queryCommandState( 'source' ))
            formEditor.execCommand('source');//切换到编辑模式才提交，否则有bug

        if(formEditor.hasContents()){
            formEditor.sync();/*同步内容*/

            //--------------以下仅参考-----------------------------------------------------------------------------------------------------
            var type_value='',formid=0,fields=$("#fields").val(),formeditor='';

            if( typeof type!=='undefined' ){
                type_value = type;
            }
            //获取表单设计器里的内容
            formeditor=formEditor.getContent();
            //解析表单设计器控件
            var parse_form = this.parse_form(formeditor,fields);
            //alert(parse_form);
            //异步提交数据
            $.ajax({
                type: 'POST',
                url : '${ctx}/settingMeta/saveMetaBusi',
                dataType : 'json',
                data : {'id':'${metaBusi.id}','businame' : $("#businame").val(),'busicode':$("#busicode").val(),'remark':$("#remark").val(),'metaid':'${metaid}','isapp':$("#isapp").val(),'parseForm':parse_form},
                success : function(data){
                	if(data.status == 'ok'){
						if('${metaBusi.id}'!=data.result){
							window.location.href="${ctx}/settingMeta/formDesigner/${metaid}/"+data.result;
						}else{
							alert('表单保存成功');
						}
					} else {
						alert('表单保存失败');
					}
                }
            });

        } else {
            alert('表单内容不能为空！')
            $('#submitbtn').button('reset');
            return false;
        }
    } ,
    /*预览表单*/
    fnReview : function (){
        if(formEditor.queryCommandState( 'source' ))
            formEditor.execCommand('source');/*切换到编辑模式才提交，否则部分浏览器有bug*/

        if(formEditor.hasContents()){
            formEditor.sync();       /*同步内容*/

            /*设计form的target 然后提交至一个新的窗口进行预览*/
            document.saveform.target="mywin";
            window.open('','mywin',"menubar=0,toolbar=0,status=0,resizable=1,left=300,top=0,scrollbars=1,width=" +(screen.availWidth-500) + ",height=" + (screen.availHeight-250) + "\"");

            document.saveform.action="${ctx}/settingMeta/metaBusiPreview";
            document.saveform.submit(); //提交表单
        } else {
            alert('表单内容不能为空！');
            return false;
        }
    }
};

</script>
<!-- script end -->
</body>
</html>