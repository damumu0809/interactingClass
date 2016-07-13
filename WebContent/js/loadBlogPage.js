var txt;
var txt1;
var page = 1;
var pagedata = {"page":page};

function post(){
    $.post("/interactingClass/BlogPage",pagedata,function(res){

    console.log(page);


    $("#blog").empty();
    $("#fenye").empty();
    var message = $.parseJSON(res);
    var code = message.code;

    if(code == 1){
        alert("请先登录！");
        window.location.href = "login.jsp";

    }



    var pages = message.pages;
    var list= message.list;

    var userName, issueTime,like,comment,topic, text, hasAcc,acc;
    var type, href;
    var picture, video, music;
    var imgNum = 0;
    var videoNum= 0;
    var musicNum =0;
    var slide = 0;
    //list遍历
    list.forEach(function(item, index, array){
        txt = '';
        var id = item.id;
        userName = item.user_name;
        issueTime = item.issueTime;
        like = item.like;
        comment = item.comment;
        topic = item.topic;
        text = item.text;
        hasAcc = item.hasAcc;

        if(hasAcc == 1){
            //有附件
            picture = new Array();
            video = new Array();
            music = new Array();
            acc = item.acc;
            imgNum = 0;
            videoNum = 0;
            musicNum = 0;

            acc.forEach(function(item, index, array){
                type = item.type;
                href = item.href;

                //必须是相对路径 才能显示图片
                href = href.replace("E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps","../..")

                if(type==="picture"){
                    //图片
                    imgNum++;
                    picture.push(href)
                }
                if(type==="video"){
                    videoNum++;
                    video.push(href);
                }
                if(type==="music"){
                    musicNum++;
                    music.push(href);
                }

            });

            if(imgNum == 1){
                //单张图片
                txt = '';
                txt = '<img src="'+picture[0]+'" alt="">'+
                    '<div class="blog-desc">'+
                    '<div class="desc-left">'+
                    '<i class="des-q"></i>';
            }
            if(imgNum >= 2){
                //slide图片
                slide++;
                txt = '';
                txt = '<div  class="callbacks_container blog-slide-n" id="top'+slide+'">'+
                    '<ul class="rslides " id="slider'+slide+'">';

                picture.forEach(function(item, index, array){
                    txt = txt + '<li>'+
                        '<img src="'+ item+'" alt="">'+
                        '</li>';

                });
                txt = txt+ '</ul>'+'</div>'+
                    '<div class="blog-desc">'+
                    '<div class="desc-left">'+
                    '<i class="des-q"></i>';

            }
            if(musicNum == 1){
                //音频
                txt = '';
                txt = '<audio controls>'+
                    '<source src="'+music[0]+'" type="audio">'+
                    '</audio>'+
                    '<div class="blog-desc">'+
                    '<div class="desc-left">'+
                    '<i class="des-r"></i>';
            }
            if(videoNum == 1){
                //视频
                //问题：部分视频播放没有画面 编码问题
                txt = '';
                txt = '<video width="320" height="240" controls>'+
                    '<source src="'+video[0]+'" type="video/mp4">'+
                    '<source src="'+video[0].replace(".mp4",".ogg")+'" type="video/ogg">'+
                    '<source src="'+video[0].replace(".mp4",".webm")+'" type="video/webm">'+
                    '<object data="'+video[0]+'" width="320" height="240">'+
                    '<embed src="'+video[0].replace(".mp4",".swf")+'" width="320" height="240">'+
                    '</object>'+
                    '</video>'+
                    '<div class="blog-desc">'+
                    '<div class="desc-left">'+
                    '<i class="des-s"></i>';
                /*'<div class="video-f">'+
                 '<iframe src="'+video[0]+ '" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>'+
                 '</div>'*/
                /*<video width="320" height="240" controls>
                 <source src="movie.mp4" type="video/mp4">
                 <source src="movie.ogg" type="video/ogg">
                 <source src="movie.webm" type="video/webm">
                 <object data="movie.mp4" width="320" height="240">
                 <embed src="movie.swf" width="320" height="240">
                 </object>
                 </video>*/
            }
        }


        if(hasAcc == 0){
            txt =
                '<div class="blog-desc">'+
            '<div class="desc-left">';

        }


           txt = txt +
                '<p style="font-size: 2%">'+issueTime+'</p>'+
                '</div>'+
                '<div class="desc-right">'+
                '<h3>'+topic+'</h3>'+
                '<ul class="blog-list" id="'+id+'">'+
                '<li><a href="personal.jsp?id='+id+'" id="user'+id+'">'+userName+'</a></li>'+
                '<li><a href="javascript:" id="like'+id+'"><span class="person"></span></a> '+like+ '赞</li>'+
                '<li><a href="single.jsp?id='+id+'#comment" id="comment'+id+'"><span class="cmt"></span></a>'+ comment+ '评论</li>'+
                '</ul>'+
                '<span class="blog-line"></span>'+
                '<p class="head-sub">'+text.substr(0, 50)+'</p>'+
                '<div class="read-more">'+
                '<a href="single.jsp?id='+id+'">Read More</a>'+
                '</div>'+
                '</div>';
        $("#blog").append(txt);



        $("#like"+id).click(function(){
            //传递的是data对象
            var data={"id":id};
            $.post("/interactingClass/Like",data,function(res){
                var message = $.parseJSON(res);
                if(message.code == 0){
                    //点赞成功，刷新页面
                    window.location.reload();
                }
            });
        });

        //$("#comment"+id).click(function(){
        //    var data={"id":id};
        //    $.post("/interactingClass/Comment",data);
        //});



    });
    txt1 = '';
    txt1='<li><a href="javascript:" id="lastPage"><i></i></a></li>'+
        '<li class="col-md-2">'+
        '<input  style="width:30px" id="page" name="page" >'+
        '<button style="width:30px" id="goto">goto</button>'+
        '</li>'+
        '<li class="nme"><a href="javascript:" id="nextPage"><i class="right"></i></a></li>';
    $("#fenye").append(txt1);

    for(slide; slide > 0; slide--){
        $("#slider"+slide).responsiveSlides({
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
    }

    //总页面和当前页面的显示


    //点击上下页以及输入页数的显示


    $("#lastPage").click(function(){
        if(page -1  >= 1){
            page = page -1;
            pagedata.page = page;
            post();
        }else{
            //当前是第一页
            alert("当前已是第一页");
        }
    });

    $("#nextPage").click(function(){
        if(page + 1 <= pages){
            page = page + 1;
            pagedata.page = page;
            post();
        }else{
            alert("当前已是最后一页");
        }
    });

    $("#page").attr("value",page);

    $("#goto").click(function(){
        var newPage;
        if($("#page").val() == null){
            newPage = 1;
        }else{
             newPage = $("#page").val();
        }
        console.log(newPage);
        if(newPage>=1 && newPage <= pages){
            page = newPage;
            pagedata.page = page;
            post();
        }else{
            alert("该页不存在！");
        }

    });

});}

post();

//只能上传一种附件
/*
$("#picture").onblur(function(){
    if($("#picture").val() != null){
        $("#video").setAttribute("disabled", disabled);
        $("#music").setAttribute("disabled", disabled);
    }
})
$("#music").onblur(function(){
    if($("#music").val() != null){
        $("#video").setAttribute("disabled", disabled);
        $("#picture").setAttribute("disabled", disabled);
    }
})
$("#video").onblur(function(){
    if($("#video").val() != null){
        $("#picture").setAttribute("disabled", disabled);
        $("#music").setAttribute("disabled", disabled);
    }
})
*/
