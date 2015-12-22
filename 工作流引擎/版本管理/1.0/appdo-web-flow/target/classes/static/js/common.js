//字符串去空格
function trim(s) {
	var count = s.length;
	var st = 0;
	var end = count - 1;

	if (s == "")
		return s;
	while (st < count) {
		if (s.charAt(st) == " ")
			st++;
		else
			break;
	}
	while (end > st) {
		if (s.charAt(end) == " ")
			end--;
		else
			break;
	}
	return s.substring(st, end + 1);
}
function myformatter(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();  
    return y+'\\'+(m<10?('0'+m):m)+'\\'+(d<10?('0'+d):d);  
}
function myparser(s){  
    if (!s) return new Date();
    if(/^\d+$/.test(s)){
    	return new Date();
    }
    var ss = (s.split('\\'));  
    var y = parseInt(ss[0],10);  
    var m = parseInt(ss[1],10);  
    var d = parseInt(ss[2],10);  
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){  
        return new Date(y,m-1,d);  
    } else {  
        return new Date();  
    }  
}
function dateCompare(date1,date2){
	date1 = date1.replace(/\\/gi,"/")+" 23:59:59";
	date2 = date2.replace(/\\/gi,"/")+" 23:59:59";
	var time1 = new Date(date1).getTime();
	var time2 = new Date(date2).getTime();
	if(time1 < new Date().getTime()){
		return 0 ;
	}else if(time1 > time2){
		return 1;
	}else if(time1 == time2){
		return 2;
	}else{
		return 3;
	}
}