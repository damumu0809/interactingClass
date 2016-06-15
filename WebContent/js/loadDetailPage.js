function getDetail(id){
    console.log(id);
    var data = {"id": id};
    $.post("/interactingClass/DetailPage",data,function(res){
        var message = $.parseJSON(res);
        var blog = message.blog;
        var userName = blog.user_name;
        var issueTime = blog.issueTime;
        var like = blog.like;
        var comment = blog.comment;
        var topic = blog.topic;
        var text  = blog.text;
        var hasAcc = blog.hasAcc;

        var type, href;
        var picture, video, music;
        var imgNum = 0;
        var videoNum= 0;
        var musicNum =0;

        var txt = '';
        if(hasAcc == 1){
            //有附件
            picture = new Array();
            video = new Array();
            music = new Array();
            allAcc = blog.allAcc;
            imgNum = 0;
            videoNum = 0;
            musicNum = 0;

            allAcc.forEach(function(item, index, array){
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
                txt = '';
                txt = '<div  class="callbacks_container blog-slide-n" id="top">'+
                    '<ul class="rslides " id="slider">';

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
        txt = txt +
            '<p style="font-size: 2%">'+issueTime+'</p>'+
            '</div>'+
            '<div class="desc-right">'+
            '<h3>'+topic+'</h3>'+
            '<ul class="blog-list">'+
            '<li>'+ userName+'</li>'+
            '<li><a href="#like"><span class="person"></span></a> '+like+ '赞</li>'+
            '<li><a href="#comment"><span class="cmt"></span></a>'+ comment+ '评论</li>'+
            '</ul>'+
            '<span class="blog-line"></span>'+
            '<p class="head-sub">'+text+'</p>'+
            '</div>';

        $("#blog").append(txt);
        if(imgNum >= 2){
            $("#slider").responsiveSlides({
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

        txt =
            '<h3 class="top-head caty">'+like+'赞</h3>'+
            '<span class="blog-line thik thiky"></span>';
        $("#like").append(txt);


        if(like >= 1){
            txt = '<div class="comments-top" id="likePerson">'+
                '</div>';
            $("#like").append(txt);//框框
            var allLike = blog.allLike;
            allLike.forEach(function(item,index,array){
                $("#likePerson").append(item+'  ');
            });

        }

        //评论显示
        txt='<h3 class="top-head caty">'+comment+'评论</h3>'+
            '<span class="blog-line thik thiky"></span>';
        $("#comment").append(txt);

        if(comment >= 1){
            var allComment = blog.allComment;
            allComment.forEach(function(item, index, array){
                var comment_id = item.comment_id;
                var person = item.person;
                var time = item.time;
                var content = item.content;
                var reply = item.reply;
                if(reply == null){
                    txt = '<div class="comments-top">'+
                        '<div class="comment-right">'+
                        '<p>'+person+' &nbsp;&nbsp;'+time+'</p>'+
                        '<p class="cmt-mat">'+content+'</p>'+
                        '<div >'+
                            '<form action="/interactingClass/Comment" method="post">'+

                        '<input type="text" name="content" placeholder="评论内容" >'+
                        '<input type="hidden" name="id" value="'+id+'" />'+
                        '<input type="hidden" name="reply_id" value="'+comment_id+'" />'+

                        '<button type="submit">REPLY</button>'+
                            '</form>'+

                        '</div>'+//reply要加评论id
                        '</div>'+
                        '<div class="clearfix"></div>'+//不能少这句
                        '</div>';
                }else{
                    //评论有回复
                    txt = '<div class="comments-top">'+
                        '<div class="comment-right">'+
                        '<p>'+person+' &nbsp;&nbsp;'+time+'</p>'+
                        '<p class="cmt-mat">'+content+'</p>';
                    reply.forEach(function(item,index,array){

                        var reply_person = item.person;
                        var reply_time = item.time;
                        var reply_content = item.content;
                        txt = txt+
                            '<p>'+reply_person+' &nbsp;&nbsp;'+reply_time+'</p>'+
                            '<p class="cmt-mat">'+reply_content+'</p>';

                    });
                    txt = txt+
                        '<form action="/interactingClass/Comment" method="post">'+

                        '<input type="text" name="content" placeholder="评论内容" >'+
                        '<input type="hidden" name="id" value="'+id+'" />'+
                        '<input type="hidden" name="reply_id" value="'+comment_id+'" />'+

                        '<button type="submit">REPLY</button>'+
                        '</form>'+
                    '</div>'+
                    '<div class="clearfix"></div>'+
                    '</div>';
                }

                $("#comment").append(txt);

            });


        }

        $("#reply").attr("value",id );
    });


}