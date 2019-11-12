/**
 * 博客详情的js 
 */
var current_user_id;
$(function(){
	current_user_id = $("#current_user_id").val();
	comment_select();
});
/**
 * 查询当前博客的所有评论信息
 */
function comment_select(){
	var id = $("#bid").val();
	console.log(id);
	//使用JSON的方式，获取到评论列表
	$.getJSON("comment.action",{type:1,bid:id},function(data){
		var html = "";
        if(data.list.length==0){
        	html += "<div class='comment_author'>没有人</div>";
            html +=	"<div class='comment_text'>我在等人</div>";
        }else{
        	$.each(data.list,function(i,comment){
        		html += "<div class='comment_author'><span>"+(i+1)+"楼</span><span>"+comment.userName+"</span><span>"+comment.dateTimeStr+"</span>";
        		if(current_user_id!=null&&current_user_id==comment.uid){
        			html +="<span class='comment_delete' onClick='comment_delete("+comment.id+","+comment.bid+")'>删除</span>";
        		}
        		html += "</div>";
                html +=	"<div class='comment_text'>"+comment.content+"</div>";
        	});
        }
        console.log(html);
        $("#comment").html(html);
	});
}
/**
 * 在用户评论前进行一次检查
 */
function check(){
	var content = $("#comment_content").val();
	var uid = $("#uid").val();
	if(content==""){
		alert("兄弟请注意不能为空");
		return false;
	}
	if(uid==""){
		alert("user id is not null");
		return false;
	}
	return true;
}

/**
 * 用户发表评论
 */
function comment_submit(){
	var bid_param = $("#bid").val();
	var uid_param = $("#current_user_id").val();
	var content_param = $("#comment_content").val();
	console.log(bid_param);
	console.log(uid_param);
	console.log(content_param);
	if(check()){
		$.getJSON("comment.action",{type:2,bid:bid_param,uid:uid_param,content:content_param},function(data){
			var flag = data.flag;
			console.log(flag);
			//刷新窗体，将所有的评论展示出来
			comment_select();
			//将内容框清空
			$("#comment_content").val("");
		});
	}
}

/**
 * 删除评论
 * @param id
 * @param bid
 */
function comment_delete(param1,param2){
	$.getJSON("comment.action",{type:3,id:param1,bid:param2},function(data){
		var flag = data.flag;
		console.log(flag);
		if(flag){
			comment_select();//刷新评论列表
		}
	});
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
