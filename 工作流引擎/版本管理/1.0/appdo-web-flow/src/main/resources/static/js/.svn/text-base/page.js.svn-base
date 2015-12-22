var totalpage, pagesize, cpage, count, curcount, outstr,pageNoId,argLength,prevPageId,nextPageId;
// 初始
cpage = 1;
totalpage = "";
outstr = "";
form = "";
function jumpPage(pageNo, type) {
	
	if (1 == type) {
		if(pageNoId!=null&&pageNoId!=""){
			document.getElementById(pageNoId).value = document
			.getElementById(nextPageId).value;
		}else{
			document.getElementById('pageNo').value = document
			.getElementById('next').value;
		}
		
	} else if (2 == type) {
		if(pageNoId!=null&&pageNoId!=""){
			document.getElementById(pageNoId).value = document
			.getElementById(prevPageId).value;
		}else{
			ocument.getElementById('pageNo').value = document
				.getElementById('pre').value;
		}

	} else {
		if(pageNoId!=null&&pageNoId!=""){
			document.getElementById(pageNoId).value = pageNo-1;
		}else{
			document.getElementById('pageNo').value = pageNo-1;
		}
	}
	document.forms[form].submit();
	return false;

}
function gotopage(target) {
	cpage = target; // 把页面计数定位到第几
	setpage();
	// reloadpage(target); //调用显示页面函数显示第几 这个功能是用在页面内容用ajax载入的情
}
function setpage(goform ,pagenum, allPage,totalCount,showDiv,pageNo,prevId,nextId) {
	pagenum = parseInt(pagenum);
	argLength = arguments.length;
	pageNoId="";
	totalpage = allPage;
	form = goform;
	cpage = pagenum;
	if (totalpage == 1) {
		outstr="";
	}
	if(argLength>=6){
		pageNoId = pageNo;
		prevPageId = prevId;
		nextPageId = nextId;
	}
	if (totalpage <= 7 && totalpage > 1) { // 总页数小于七页
		for (var count = 1; count <= totalpage; count++) {
			if (count != cpage) {
				outstr = outstr
						+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage("
						+ count + "," + 3 + ")'>" + count + "</a></li>";
			} else {
				outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
			}
		}
	}
	if (totalpage > 7) { // 总页数大于七页
		if (cpage < 6) {
			for (var count = 1; count <= 6; count++) {

				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
				}
			}
			outstr = outstr
					+ "<li class='page_kuang'>...</li><li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage("
					+ totalpage + "," + 3 + ")'>" + totalpage + "</a></li>";

			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)'  onclick='return jumpPage("
					+ 0+"," + 1 + ")'>下一页</a></li>";
		} else if ((totalpage - cpage) > 3) {
			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)'  onclick='return jumpPage("
					+ 0+"," + 2 + ")'>上一页</a></li>";
			outstr = outstr + "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage("
					+ 1 + "," + 3 + ")'>" + 1 + "</a></li><li class='page_kuang'>...</li>";
			
			for (count = (cpage - 3); count <= cpage + 2; count++) {
				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
				}
			}

			outstr = outstr
					+ "<li class='page_kuang'>...</li><li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage("
					+ totalpage + "," + 3 + ")'>" + totalpage + "</a></li>";
			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return jumpPage("
					+0 +"," + 1 + ")'> 下一页</a></li>";
		} else {
			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return jumpPage("
					+0 +"," + 2 + ")'>上一页</a></li>";
			outstr = outstr + "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage("
					+ 1 + "," + 3 + ")'>" + 1 + "</a></li><li class='page_kuang'>...</li>";
			for (count = totalpage-5; count <= totalpage; count++) {
				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button' style='color:#000;'>" + count + "</a></li>";
				}
			}

		}
	}
	if(totalCount!=0&&totalCount!=-1){
	if(argLength>5){
		document.getElementById(showDiv).innerHTML= outstr + "<strong>共"+totalCount+"条</strong> "  + "</div>";
	}else if(argLength==4){
		document.getElementById("pages").innerHTML= outstr + "<strong>共"+totalCount+"条</strong> "  + "</div>";
	}
	outstr = "";
	}
}
// setpage(); //调用分页
// -->
function jumpPage_2(pageNo, type) {
	
	if (1 == type) {

		document.getElementById('pageNo2').value = document
				.getElementById('next2').value;
	} else if (2 == type) {
		document.getElementById('pageNo2').value = document
				.getElementById('pre2').value;

	} else {

		document.getElementById('pageNo2').value = pageNo-1;

	}
	jQuery.ajax({
		url:jQuery('#'+form_2).attr('action'),//url:jQuery('#appEvaluteForm').attr('action'),
		type : "post",
		data : jQuery('#'+form_2).serialize(),//jQuery('#appEvaluteForm').serialize(),
		dataType:"html",
		success : function(msg){
			var reg = /[\s\S]*<script>([\s\S]*)<\/script>[\s\S]*/g;
            var match=reg.exec(msg);
            if(match!=null){
                    MyScript=match[1];
                  var script=document.createElement("script");//在模版页创建新的<script>标签
                    script.text=MyScript;//给新的script标签赋值
                    var Html=msg.replace(MyScript,"");//将剩下的text祛除<script>部分，插入模版页
                    document.getElementById("store_3").innerHTML=Html;
                  document.getElementsByTagName("head")[0].appendChild(script);//把该标签加入<head>
             }else{
                     document.getElementById("store_3").innerHTML=msg;
            }
		},
		error : function(msg){
		}
	});
	return false;

}



function setpage_2(goform ,pagenum, allPage,totalCount) {

	totalpage = allPage;
	form_2 = goform;
	cpage = pagenum;
	if (totalpage == 1) {
		outstr="";
	}
	if (totalpage <= 7 && totalpage > 1) { // 总页数小于七页
		for (var count = 1; count <= totalpage; count++) {
			if (count != cpage) {
				outstr = outstr
						+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_2("
						+ count + "," + 3 + ")'>" + count + "</a></li>";
			} else {
				outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
			}
		}
	}
	if (totalpage > 7) { // 总页数大于七页
		if (cpage < 6) {
			for (var count = 1; count <= 6; count++) {

				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_2("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
				}
			}
			outstr = outstr
					+ "<li class='page_kuang'>...</li><li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_2("
					+ totalpage + "," + 3 + ")'>" + totalpage + "</a></li>";

			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return jumpPage_2("
					+ 0+"," + 1 + ")'>下一页</a></li>";
		} else if ((totalpage - cpage) > 3) {

			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return jumpPage_2("
					+ 0+"," + 2 + ")'>上一页</a></li>";
			outstr = outstr + "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_2("
					+ 1 + "," + 3 + ")'>" + 1 + "</a></li><li class='page_kuang'>...</li>";
			for (count = cpage - 3; count <= cpage + 2; count++) {
				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_2("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
				}
			}

			outstr = outstr
					+ "<li class='page_kuang'>...</li><li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_2("
					+ totalpage + "," + 3 + ")'>" + totalpage + "</a></li>";
			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return jumpPage_2("
					+0 +"," + 1 + ")'> 下一页</a></li>";
		} else {
			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return jumpPage_2("
					+0 +"," + 2 + ")'>上一页</a></li>";
			outstr = outstr + "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_2("
					+ 1 + "," + 3 + ")'>" + 1 + "</a></li><li class='page_kuang'>...</li>";
			for (count = parseInt((cpage - 2) / 10) * 10 + 5; count <= totalpage; count++) {
				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_2("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button' style='color:#000;'>" + count + "</a></li>";
				}
			}

		}
	}
	if(totalCount!=0&&totalCount!=-1){
	document.getElementById("pages2").innerHTML= outstr + "<strong>共"+totalCount+"条</strong> "  + "</div>";
	outstr = "";
	}
}


//消息推送历史查询分页
function jumpPage_push_his(pageNo, type) {
	
	if (1 == type) {

		document.getElementById('pageNoPushHis').value = document
				.getElementById('nextPushHis').value;
	} else if (2 == type) {
		document.getElementById('pageNoPushHis').value = document
				.getElementById('prePushHis').value;

	} else {

		document.getElementById('pageNoPushHis').value = pageNo-1;

	}
	jQuery.ajax({
		url:jQuery('#pushMsgHisForm').attr('action'),
		type : "post",
		data : jQuery('#pushMsgHisForm').serialize(),
		dataType:"html",
		success : function(msg){
			var reg = /[\s\S]*<script>([\s\S]*)<\/script>[\s\S]*/g;
            var match=reg.exec(msg);
            if(match!=null){
                    MyScript=match[1];
                  var script=document.createElement("script");//在模版页创建新的<script>标签
                    script.text=MyScript;//给新的script标签赋值
                    var Html=msg.replace(MyScript,"");//将剩下的text祛除<script>部分，插入模版页
                    jQuery('#push_msg_his_liebiao').html(Html);//数据
                  document.getElementsByTagName("head")[0].appendChild(script);//把该标签加入<head>
             }else{
					 jQuery('#push_msg_his_liebiao').html(msg);
            }
		},
		error : function(msg){
		}
	});
	return false;

}


/**消息推送历史记录分页初始化*/
function setpage_push_his(goform ,pagenum, allPage,totalCount) {

	totalpage = allPage;
	form_2 = goform;
	cpage = pagenum;
	if (totalpage == 1) {
		outstr="";
	}
	if (totalpage <= 7 && totalpage > 1) { // 总页数小于七页
		for (var count = 1; count <= totalpage; count++) {
			if (count != cpage) {
				outstr = outstr
						+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_push_his("
						+ count + "," + 3 + ")'>" + count + "</a></li>";
			} else {
				outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
			}
		}
	}
	if (totalpage > 7) { // 总页数大于七页
		if (cpage < 6) {
			for (var count = 1; count <= 6; count++) {

				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_push_his("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
				}
			}
			outstr = outstr
					+ "<li class='page_kuang'>...</li><li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_push_his("
					+ totalpage + "," + 3 + ")'>" + totalpage + "</a></li>";

			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return jumpPage_push_his("
					+ 0+"," + 1 + ")'>下一页</a></li>";
		} else if ((totalpage - cpage) > 3) {

			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return jumpPage_push_his("
					+ 0+"," + 2 + ")'>上一页</a></li>";
			outstr = outstr + "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_push_his("
					+ 1 + "," + 3 + ")'>" + 1 + "</a></li><li class='page_kuang'>...</li>";
			for (count = cpage - 3; count <= cpage + 2; count++) {
				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_push_his("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
				}
			}

			outstr = outstr
					+ "<li class='page_kuang'>...</li><li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_push_his("
					+ totalpage + "," + 3 + ")'>" + totalpage + "</a></li>";
			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return jumpPage_push_his("
					+0 +"," + 1 + ")'> 下一页</a></li>";
		} else {
			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return jumpPage_push_his("
					+0 +"," + 2 + ")'>上一页</a></li>";
			outstr = outstr + "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_push_his("
					+ 1 + "," + 3 + ")'>" + 1 + "</a></li><li class='page_kuang'>...</li>";
			for (count = parseInt((cpage - 2) / 10) * 10 + 5; count <= totalpage; count++) {
				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_push_his("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button' style='color:#000;'>" + count + "</a></li>";
				}
			}

		}
	}
	if(totalCount!=0&&totalCount!=-1){
	document.getElementById("push_msg_his_page_info").innerHTML= outstr + "<strong>共"+totalCount+"条</strong> "  + "</div>";
	outstr = "";
	}
}


/**用户分组分页初始化*/
function setpage_user_group(goform ,pagenum, allPage,totalCount) {

	totalpage = allPage;
	form_2 = goform;
	cpage = pagenum;
	if (totalpage == 1) {
		outstr="";
	}
	if (totalpage <= 7 && totalpage > 1) { // 总页数小于七页
		for (var count = 1; count <= totalpage; count++) {
			if (count != cpage) {
				outstr = outstr
						+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_user_group("
						+ count + "," + 3 + ")'>" + count + "</a></li>";
			} else {
				outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
			}
		}
	}
	if (totalpage > 7) { // 总页数大于七页
		if (cpage < 6) {
			for (var count = 1; count <= 6; count++) {

				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_user_group("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
				}
			}
			outstr = outstr
					+ "<li class='page_kuang'>...</li><li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_user_group("
					+ totalpage + "," + 3 + ")'>" + totalpage + "</a></li>";

			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return jumpPage_user_group("
					+ 0+"," + 1 + ")'>下一页</a></li>";
		} else if ((totalpage - cpage) > 3) {

			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return jumpPage_user_group("
					+ 0+"," + 2 + ")'>上一页</a></li>";
			outstr = outstr + "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_user_group("
					+ 1 + "," + 3 + ")'>" + 1 + "</a></li><li class='page_kuang'>...</li>";
			for (count = cpage - 3; count <= cpage + 2; count++) {
				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_user_group("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
				}
			}

			outstr = outstr
					+ "<li class='page_kuang'>...</li><li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_user_group("
					+ totalpage + "," + 3 + ")'>" + totalpage + "</a></li>";
			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return jumpPage_user_group("
					+0 +"," + 1 + ")'> 下一页</a></li>";
		} else {
			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return jumpPage_user_group("
					+0 +"," + 2 + ")'>上一页</a></li>";
			outstr = outstr + "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_user_group("
					+ 1 + "," + 3 + ")'>" + 1 + "</a></li><li class='page_kuang'>...</li>";
			for (count = parseInt((cpage - 2) / 10) * 10 + 5; count <= totalpage; count++) {
				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_user_group("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button' style='color:#000;'>" + count + "</a></li>";
				}
			}

		}
	}
	if(totalCount!=0&&totalCount!=-1){
	document.getElementById("user_group_page_info").innerHTML= outstr + "<strong>共"+totalCount+"条</strong> "  + "</div>";
	outstr = "";
	}
}

//用户分组分页跳转
function jumpPage_user_group(pageNo, type) {
	
	if (1 == type) {

		document.getElementById('pageNoUserGroup').value = document
				.getElementById('nextUserGroup').value;
	} else if (2 == type) {
		document.getElementById('pageNoUserGroup').value = document
				.getElementById('preUserGroup').value;

	} else {

		document.getElementById('pageNoUserGroup').value = pageNo-1;

	}
	jQuery.ajax({
		url:jQuery('#userGroupForm').attr('action'),
		type : "post",
		data : jQuery('#userGroupForm').serialize(),
		dataType:"html",
		success : function(msg){
			var reg = /[\s\S]*<script>([\s\S]*)<\/script>[\s\S]*/g;
            var match=reg.exec(msg);
            if(match!=null){
                    MyScript=match[1];
                  var script=document.createElement("script");//在模版页创建新的<script>标签
                    script.text=MyScript;//给新的script标签赋值
                    var Html=msg.replace(MyScript,"");//将剩下的text祛除<script>部分，插入模版页
                    jQuery('#user_group_div').html(html);//数据
                  document.getElementsByTagName("head")[0].appendChild(script);//把该标签加入<head>
             }else{
					 jQuery('#user_group_div').html(msg);
            }
		},
		error : function(msg){
		}
	});
	return false;
}


function ajaxJumpPage(pageNo, type) {
	
	if (1 == type) {
		
		document.getElementById('term_pageNo').value = document
				.getElementById('term_next').value;
	} else if (2 == type) {
		document.getElementById('term_pageNo').value = document
				.getElementById('term_pre').value;

	} else {

		document.getElementById('term_pageNo').value = pageNo-1;

	}
	jQuery.ajax({
		url:jQuery('#'+form3).attr('action'),
		type : "post",
		data:jQuery('#'+form3).serialize(),
		dataType:"html",
		success : function(msg){
			var reg = /[\s\S]*<script>([\s\S]*)<\/script>[\s\S]*/g;
            var match=reg.exec(msg);
            if(match!=null){
                    MyScript=match[1];
                  var script=document.createElement("script");//在模版页创建新的<script>标签
                    script.text=MyScript;//给新的script标签赋值
                    var Html=msg.replace(MyScript,"");//将剩下的text祛除<script>部分，插入模版页
                    if(document.getElementById("cony5") != null){
                        document.getElementById("cony5").innerHTML=Html;
                    }else if(document.getElementById("baseInfoDiv") != null){
                        document.getElementById("baseInfoDiv").innerHTML=Html;
                    }
                  document.getElementsByTagName("head")[0].appendChild(script);//把该标签加入<head>
             }else{
            	 if(document.getElementById("cony5") != null){
                     document.getElementById("cony5").innerHTML=msg;
                   }else if(document.getElementById("baseInfoDiv") != null){
                        document.getElementById("baseInfoDiv").innerHTML=msg;
                   }
            }
		},
		error : function(msg){
		}
	});
	return false;

}
function ajaxSetpage(goform ,pagenum, allPage,totalCount) {

	totalpage = allPage;
	form3 = goform;
	cpage = pagenum;
	if (totalpage == 1) {
		outstr="";
	}
	if (totalpage <= 7 && totalpage > 1) { // 总页数小于七页
		for (var count = 1; count <= totalpage; count++) {
			if (count != cpage) {
				outstr = outstr
						+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return ajaxJumpPage("
						+ count + "," + 3 + ")'>" + count + "</a></li>";
			} else {
				outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
			}
		}
	}
	if (totalpage > 7) { // 总页数大于七页
		if (cpage < 6) {
			for (var count = 1; count <= 6; count++) {

				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return ajaxJumpPage("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
				}
			}
			outstr = outstr
					+ "<li class='page_kuang'>...</li><li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return ajaxJumpPage("
					+ totalpage + "," + 3 + ")'>" + totalpage + "</a></li>";

			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return ajaxJumpPage("
					+ 0+"," + 1 + ")'>下一页</a></li>";
		} else if ((totalpage - cpage) > 3) {

			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)'  onclick='return ajaxJumpPage("
					+ 0+"," + 2 + ")'>上一页</a></li>";
			outstr = outstr + "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return ajaxJumpPage("
					+ 1 + "," + 3 + ")'>" + 1 + "</a></li><li class='page_kuang'>...</li>";
			for (count = cpage - 3; count <= cpage + 2; count++) {
				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return ajaxJumpPage("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
				}
			}

			outstr = outstr
					+ "<li class='page_kuang'>...</li><li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return ajaxJumpPage("
					+ totalpage + "," + 3 + ")'>" + totalpage + "</a></li>";
			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return ajaxJumpPage("
					+0 +"," + 1 + ")'> 下一页</a></li>";
		} else {
			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return ajaxJumpPage("
					+0 +"," + 2 + ")'>上一页</a></li>";
			outstr = outstr + "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return ajaxJumpPage("
					+ 1 + "," + 3 + ")'>" + 1 + "</a></li><li class='page_kuang'>...</li>";
			for (count = parseInt((cpage - 2) / 10) * 10 + 5; count <= totalpage; count++) {
				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return ajaxJumpPage("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button' style='color:#000;'>" + count + "</a></li>";
				}
			}

		}
	}
	if(totalCount!=0&&totalCount!=-1){
	document.getElementById("pages_term").innerHTML=outstr + "<strong>共"+totalCount+"条</strong> "  + "</div>";
	outstr = "";
	}
}




function ajaxJumpPagePush(pageNo, type) {
	
	if (1 == type) {
		
		document.getElementById('push_pageNo').value = document
				.getElementById('push_next').value;
	} else if (2 == type) {
		document.getElementById('push_pageNo').value = document
				.getElementById('push_pre').value;

	} else {

		document.getElementById('push_pageNo').value = pageNo-1;

	}
	jQuery.ajax({
		url:jQuery('#'+form4).attr('action'),
		type : "post",
		data:jQuery('#'+form4).serialize(),
		dataType:"html",
		success : function(msg){
			var reg = /[\s\S]*<script>([\s\S]*)<\/script>[\s\S]*/g;
            var match=reg.exec(msg);
            if(match!=null){
                    MyScript=match[1];
                  var script=document.createElement("script");//在模版页创建新的<script>标签
                    script.text=MyScript;//给新的script标签赋值
                    var Html=msg.replace(MyScript,"");//将剩下的text祛除<script>部分，插入模版页
                    document.getElementById("push_msg_div").innerHTML=Html;
                  document.getElementsByTagName("head")[0].appendChild(script);//把该标签加入<head>
             }else{
                     document.getElementById("push_msg_div").innerHTML=msg;
            }
		},
		error : function(msg){
		}
	});
	return false;

}
function ajaxSetpagePush(goform ,pagenum, allPage,totalCount) {

	totalpage = allPage;
	form4 = goform;
	cpage = pagenum;
	if (totalpage == 1) {
		outstr="";
	}
	if (totalpage <= 7 && totalpage > 1) { // 总页数小于七页
		for (var count = 1; count <= totalpage; count++) {
			if (count != cpage) {
				outstr = outstr
						+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return ajaxJumpPagePush("
						+ count + "," + 3 + ")'>" + count + "</a></li>";
			} else {
				outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
			}
		}
	}
	if (totalpage > 7) { // 总页数大于七页
		if (cpage < 6) {
			for (var count = 1; count <= 6; count++) {

				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return ajaxJumpPagePush("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
				}
			}
			outstr = outstr
					+ "<li class='page_kuang'>...</li><li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return ajaxJumpPagePush("
					+ totalpage + "," + 3 + ")'>" + totalpage + "</a></li>";

			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return ajaxJumpPagePush("
					+ 0+"," + 1 + ")'>下一页</a></li>";
		} else if ((totalpage - cpage) > 3) {

			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)'  onclick='return ajaxJumpPagePush("
					+ 0+"," + 2 + ")'>上一页</a></li>";
			outstr = outstr + "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return ajaxJumpPagePush("
					+ 1 + "," + 3 + ")'>" + 1 + "</a></li><li class='page_kuang'>...</li>";
			for (count = cpage - 3; count <= cpage + 2; count++) {
				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return ajaxJumpPagePush("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
				}
			}

			outstr = outstr
					+ "<li class='page_kuang'>...</li><li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return ajaxJumpPagePush("
					+ totalpage + "," + 3 + ")'>" + totalpage + "</a></li>";
			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return ajaxJumpPagePush("
					+0 +"," + 1 + ")'> 下一页</a></li>";
		} else {
			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return ajaxJumpPagePush("
					+0 +"," + 2 + ")'>上一页</a></li>";
			outstr = outstr + "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return ajaxJumpPagePush("
					+ 1 + "," + 3 + ")'>" + 1 + "</a></li><li class='page_kuang'>...</li>";
			for (count = parseInt((cpage - 2) / 10) * 10 + 5; count <= totalpage; count++) {
				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return ajaxJumpPagePush("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button' style='color:#000;'>" + count + "</a></li>";
				}
			}

		}
	}
	if(totalCount!=0&&totalCount!=-1){
	document.getElementById("pages_push").innerHTML=outstr + "<strong>共"+totalCount+"条</strong> "  + "</div>";
	outstr = "";
	}
}


function jumpPage_3(pageNo, type) {
	
	if (1 == type) {

		document.getElementById('pageNo3').value = document
				.getElementById('next3').value;
	} else if (2 == type) {
		document.getElementById('pageNo3').value = document
				.getElementById('pre3').value;

	} else {

		document.getElementById('pageNo3').value = pageNo-1;

	}
	jQuery.ajax({
		url:jQuery('#'+form_3).attr('action'),
		type : "post",
		data : jQuery('#'+form_3).serialize(),
		dataType:"html",
		success : function(msg){
			var reg = /[\s\S]*<script>([\s\S]*)<\/script>[\s\S]*/g;
            var match=reg.exec(msg);
            if(match!=null){
                    MyScript=match[1];
                  var script=document.createElement("script");//在模版页创建新的<script>标签
                    script.text=MyScript;//给新的script标签赋值
                    var Html=msg.replace(MyScript,"");//将剩下的text祛除<script>部分，插入模版页
                    document.getElementById("store_2").innerHTML=Html;
                  document.getElementsByTagName("head")[0].appendChild(script);//把该标签加入<head>
             }else{
                     document.getElementById("store_2").innerHTML=msg;
            }
		},
		error : function(msg){
		}
	});
	return false;

}



function setpage_3(goform ,pagenum, allPage,totalCount) {

	totalpage = allPage;
	form_3 = goform;
	cpage = pagenum;
	if (totalpage == 1) {
		outstr="";
	}
	if (totalpage <= 7 && totalpage > 1) { // 总页数小于七页
		for (var count = 1; count <= totalpage; count++) {
			if (count != cpage) {
				outstr = outstr
						+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_3("
						+ count + "," + 3 + ")'>" + count + "</a></li>";
			} else {
				outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
			}
		}
	}
	if (totalpage > 7) { // 总页数大于七页
		if (cpage < 6) {
			for (var count = 1; count <= 6; count++) {

				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_3("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
				}
			}
			outstr = outstr
					+ "<li class='page_kuang'>...</li><li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_3("
					+ totalpage + "," + 3 + ")'>" + totalpage + "</a></li>";

			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return jumpPage_3("
					+ 0+"," + 1 + ")'>下一页</a></li>";
		} else if ((totalpage - cpage) > 3) {

			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return jumpPage_3("
					+ 0+"," + 2 + ")'>上一页</a></li>";
			outstr = outstr + "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_3("
					+ 1 + "," + 3 + ")'>" + 1 + "</a></li><li class='page_kuang'>...</li>";
			for (count = cpage - 3; count <= cpage + 2; count++) {
				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_3("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button'>" + count + "</a></li>";
				}
			}

			outstr = outstr
					+ "<li class='page_kuang'>...</li><li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_3("
					+ totalpage + "," + 3 + ")'>" + totalpage + "</a></li>";
			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return jumpPage_3("
					+0 +"," + 1 + ")'> 下一页</a></li>";
		} else {
			outstr = outstr + "<li class='page_up'><a href='javascript:void(0)' onclick='return jumpPage_3("
					+0 +"," + 2 + ")'>上一页</a></li>";
			outstr = outstr + "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_3("
					+ 1 + "," + 3 + ")'>" + 1 + "</a></li><li class='page_kuang'>...</li>";
			for (count = parseInt((cpage - 2) / 10) * 10 + 5; count <= totalpage; count++) {
				if (count != cpage) {
					outstr = outstr
							+ "<li class='page_kuang'><a href='javascript:void(0)' class='cheked_page_a' onclick='return jumpPage_3("
							+ count + "," + 3 + ")'>" + count + "</a></li>";
				} else {
					outstr = outstr + "<li class='page_kuang'><a class='page_kuang_button' style='color:#000;'>" + count + "</a></li>";
				}
			}

		}
	}
	if(totalCount!=0&&totalCount!=-1){
	document.getElementById("pages3").innerHTML=outstr + "<strong>共"+totalCount+"条</strong> "  +"</div>";
	outstr = "";
	}
	
	
}