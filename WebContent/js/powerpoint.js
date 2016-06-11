/**
 * Created by Yaya on 2016/5/14.
 */
jQuery(document).ready(function() {
    //alert(document.getElementById("presentation").offsetWidth+","+document.getElementById("presentation").offsetHeight);
    //document.getElementById("presentation").style.width="1300px";
    initBarrageScreen();
    $("#barrage_btn,.d_del").click(function(){
        var caption=$("#barrage_btn").html();
        $(".dm,.d_del").toggle(1000);
        //alert(caption);
        if(caption=="开启弹幕"){
            $("#barrage_btn").html("关闭弹幕");
            getChatMessage();
        }else{
            $("#barrage_btn").html("开启弹幕");
            stopChatMessage();
        }
    });
    $("#test_btn").click(function(){
        test();
    });
    initPopupMenu();
});
var timerChatMessage=null;
function initPopupMenu(){
    var json=eval("({aaData:[['http://www.baidu.com','百度首页'],['javascript:closeBarrage();','关闭弹幕'],['javascript:openBarrage();','打开弹幕'],['index.jsp','退出页面']]})");
}
function getChatMessage(){
    var url="../../teach_classroom_data_action?action=get_new_chat_message";
    $.post(url,function(data){
        var json=eval("("+data+")");
        var list=json.aaData;
        if(list.length>0){
            addBarrageMessages(list);
        }
        timerChatMessage=setTimeout(getChatMessage,5000);
    });
}
function stopChatMessage(){
    clearTimeout(timerChatMessage);
}
function addBarrageMessages(list){
    for(var i=0;i<list.length;i++){
        var id=list[i][0];
        var userName=list[i][1];
        var message=list[i][2];
        var div="<div id=\"div_"+id+"\">"+message+"</div>";
        if($(".d_show").find("div").length>10){
            var obj=$(".d_show").find("div:eq(0)");
            obj.remove();
        }
        $(".d_show").append(div);
    }
    $("#tip_div").html("当前条数："+$(".d_show").find("div").length);
    initBarrageScreen();
}
function test(){
    var divHtml="";
    $(".d_show").find("div").each(function(n){
        divHtml=divHtml+$(this).html()+"\r\n";
    });
    alert(divHtml);
}
function openBarrage(){
    var caption=$("#barrage_btn").html();
    if(caption=="开启弹幕"){
        $("#barrage_btn").html("关闭弹幕");
        $(".dm,.d_del").toggle(1000);
        getChatMessage();
    }
}
function closeBarrage(){
    var caption=$("#barrage_btn").html();
    if(caption=="关闭弹幕"){
        $("#barrage_btn").html("开启弹幕");
        $(".dm,.d_del").toggle(1000);
        stopChatMessage();
    }
}
function goHome(){
    window.location.href="index.jsp";
}
function goPPT(){
	window.location.href="index.jsp";
}