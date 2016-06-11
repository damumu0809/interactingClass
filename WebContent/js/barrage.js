//初始化弹幕
function initBarrageScreen(){
	var _top=0;
	$(".d_show").find("div").show().each(function(){
		var _left=$(window).width()-$(this).width();
		var _height=$(window).height();
		_top=_top+76;
		if(_top>=_height-100){
			_top=0;
		}
		$(this).css({left:_left,top:_top,color:getReandomColor()});
		var time=10000;
		if($(this).index()%2==0){
			time=15000;
		}
		$(this).animate({left:"-"+_left+"px"},time,function(){
		});
	});
}
//随机获取颜色值
function getReandomColor(){
	return '#'+(function(h){
	return new Array(7-h.length).join("0")+h
	})((Math.random()*0x1000000<<0).toString(16))
}
