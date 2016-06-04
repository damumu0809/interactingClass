jQuery(document).ready(function() {    
   QuickSidebar.init(); // init quick sidebar
});
jQuery(document).ready(function($) {
	$(".scroll").click(function(event){		
		event.preventDefault();
		$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
	});
});
$(window).load(function() {
		// Slideshow 4
		$("#slider4").responsiveSlides({
		auto: true,
        pager: true,
        nav: true,
        speed: 500,
        namespace: "callbacks",
        before: function () {
          $('.events').append("<li>before event fired.</li>");
        },
        after: function () {
          $('.events').append("<li>after event fired.</li>");
        }
	});		
});
$( "span.menu" ).click(function() {
	$( "ul.navig" ).slideToggle( "slow", function() {
		   // Animation complete.
	});
});
addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); }

//菜单初始化函数
function menuInit(){
	var url=window.location.href;
	$.post("menu.jsp",function(data){
		var htm = "";
		var json = eval("("+data+")");
		var list = json.Data;
		for(var i = 0;i<list.length;i++){
			htm+=		"<li class='"+list[i][2]+"'>";
			htm+=		"	<a href='"+list[i][1]+"' class='"+list[i][4]+"'>";
			htm+=		"		"+list[i][0];
			htm+=		"	</a>";
			htm+=		"</li>";
		}
		document.getElementById("menu_ul").innerHTML+=htm;
	})
}