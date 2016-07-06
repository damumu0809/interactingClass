//把loadWorkPage注释掉就可以调用page() 因为loadWorkPage中有page变量
//加载作业页面
var txt2 = '<div class="panel-group" id="homeworkAccordion" role="tablist" aria-multiselectable="true">';

var workpage = 1;
var workpagedata = {"page":workpage};

function studentPage(){
    $("#fenyework").empty();
    var theme, finish, href, taskNum, name, hasExpired, time, deadLine, id;
    $.post("/interactingClass/StudentPage",workpagedata, function (res) {
        var message = $.parseJSON(res);
        var list = message.list;
        var pages = message.pages;
        var count = message.count;//用于显示第几次作业
        var txt1;
        if (list.length != 0) {
            txt2 = '<div class="panel-group" id="homeworkAccordion" role="tablist" aria-multiselectable="true">';
            $("#homework").append(txt2);
        }
        $("#homeworkAccordion").empty();
        var themetxt;
        //遍历数组
        list.forEach(function (item, index, array) {
            //获取Json数据
            id = item.taskNum;
            theme = item.theme;
            finish = item.finish;
            href = item.href;
            name = item.name;
            time = item.time;
            deadLine = item.deadLine;
            hasExpired = item.hasExpired;
            //计数
            taskNum = count- (workpage-1)*5 - index;


            txt1 = '<div class="panel panel-default">' +
                '<div class="panel-heading" role="tab" id="workOne">' +
                '<h4 class="panel-title">' +
                '<a  id="WorkA" class="collapsed" role="button" data-toggle="collapse" data-parent="#homeworkAccordion" href="#collapseWorkOne" aria-expanded="false" aria-controls="collapseOne">' +
                '第一次作业' +
                '</a>' +
                '</h4>' +
                '</div>' +
                '<div id="collapseWorkOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">' +
                '<div class="panel-body" id="panelBody">' +
                '<p>第一次作业内容 </p>';


            $("#homeworkAccordion").append(txt1);
            $("#workOne").attr("id", "work" + taskNum);
            $("#collapseWorkOne").attr("aria-labelledby", "heading" + taskNum);
            $("#collapseWorkOne").attr("id", "collapseWork" + taskNum);
            $("#WorkA").attr("href", "#collapseWork" + taskNum);
            $("#WorkA").attr("aria-controls", "#collapse" + taskNum);
            $("#WorkA").text("第" + taskNum + "次作业");

            $("#WorkA").attr("id", "WorkA" + taskNum);
            $("#panelBody p").text(theme);

            if (finish == true) {
                //已完成显示作业链接
                themetxt = '<p>发布于' + time + '   过期时间:' + deadLine + '</p>';
                $("#work" + taskNum + " h4").append(themetxt);

                txt1 = '<a href=' + href + '>' + name + '</a>' +
                    '</div>' +
                    '</div>' +
                    '</div>';
            } else {
                //已过期且未完成显示此次作业未提交
                if (hasExpired == true) {
                    themetxt = '<p><strong>[逾期未交]</strong>发布于' + time + '   过期时间:' + deadLine + '</p>';
                    $("#work" + taskNum + " h4").append(themetxt);
                    txt1 = '</div>' +
                        '</div>' +
                        '</div>';
                } else {
                    //未完成显示作业内容和上传界面
                    //并显示[未完成]

                    themetxt = '<p><strong>[未完成]</strong>发布于' + time + '   过期时间:' + deadLine + '</p>';
                    $("#work" + taskNum + " h4").append(themetxt);
                    txt1 = '<form action="/interactingClass/UploadWork" method="post" enctype="multipart/form-data">' +
                        '<input type="file" name="file"  />' +
                        '<input type="hidden" name="taskNum" value=' + id + ' />' +
                        '<input type="submit" value="提交" />' +
                        '</form>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                }
            }
            $("#panelBody").append(txt1);
            $("#panelBody").attr("id", "panelBody" + taskNum);


        });

        <!-- 分页-->
        var txtpage = '<div class="pagnations">'+
            '<ul class="page-list" id="fenyework">'+
            '</ul>'+
            '</div>';
        $("#homeworkAccordion").append(txtpage);

        var pagetxt ='<li><a href="javascript:" id="lastPagework"><i></i></a></li>'+
            '<li class="col-md-2">'+
            '<input  style="width:30px" id="pagework" name="page" >'+
            '<button style="width:30px" id="gotowork">goto</button>'+
            '</li>'+
            '<li class="nme"><a href="javascript:" id="nextPagework"><i class="right"></i></a></li>';
        $("#fenyework").append(pagetxt);

        $("#lastPagework").click(function(){
            if(workpage -1  >= 1){
                workpage = workpage -1;
                workpagedata.page = workpage;
                studentPage();
            }else{
                //当前是第一页
                alert("当前已是第一页");
            }
        });

        $("#nextPagework").click(function(){
            if(workpage + 1 <= pages){
                workpage = workpage + 1;
                workpagedata.page = workpage;
                studentPage();
            }else{
                alert("当前已是最后一页");
            }
        });

        $("#pagework").attr("value",workpage);

        $("#gotowork").click(function(){
            var newPage;
            if($("#pagework").val() == null){
                newPage = 1;
            }else{
                newPage = $("#pagework").val();
            }
            console.log(newPage);
            if(newPage>=1 && newPage <= pages){
                workpage = newPage;
                workpagedata.page = workpage;
                studentPage();
            }else{
                alert("该页不存在！");
            }

        });
    });
}



function teacherPage(){

    $("#fenyework").empty();
    var txt1, txt3;

    var theme, hrefs_files, taskNum, hrefs, files, href, name, time, deadLine, id;
    //taskNum为显示的第几次作业
    //id表示真正的数据库里面的id

    $.post("/interactingClass/TeacherPage",workpagedata, function(res){
        var message = $.parseJSON(res);
        var list = message.list;
        var pages = message.pages;
        var count = message.count;//用于显示第几次作业
        txt2 = '<div class="panel-group" id="homeworkAccordion" role="tablist" aria-multiselectable="true">';
        if(list.length != 0){
            $("#homework").append(txt2);
        }
        $("#homeworkAccordion").empty();

        //遍历数组
        list.forEach(function(item, index, array ){

            //添加删除作业按钮

            txt1 = '<div class="panel panel-default">'+
                '<div class="panel-heading" role="tab" id="workOne">'+
                '<h4 class="panel-title">'+
                '<a  id="WorkA" class="collapsed" role="button" data-toggle="collapse" data-parent="#homeworkAccordion" href="#collapseWorkOne" aria-expanded="false" aria-controls="collapseOne">'+
                '第一次作业'+
                '</a>'+
                '<button id="deleteWorkOne">删除</button>'+
                '</h4>'+
                '</div>'+
                '<div id="collapseWorkOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">'+
                '<div class="panel-body" id="panelBody">'+
                '<p>第一次作业内容 </p>';

            id = item.taskNum;
            theme = item.theme;
            taskNum = count- (workpage-1)*5 - index;
            time = item.time;
            deadLine = item.deadLine;
            $("#homeworkAccordion").append(txt1);
            $("#workOne").attr("id", "work"+taskNum);
            $("#collapseWorkOne").attr("aria-labelledby", "heading"+taskNum);
            $("#collapseWorkOne").attr("id", "collapseWork"+taskNum);
            $("#WorkA").attr("href", "#collapseWork"+taskNum);
            $("#WorkA").attr("aria-controls", "#collapse"+taskNum);
            $("#WorkA").text("第"+taskNum+"次作业");
            $("#WorkA").attr("id","WorkA"+taskNum);
            $("#deleteWorkOne").attr("id","deleteWork"+id);
            $("#panelBody p").text(theme);


            hrefs_files = item.hrefs_files;//数组
            //添加已提交几份作业
            $("#WorkA"+taskNum).text("第"+taskNum+"次作业");

            themetxt = '<p><strong>[已提交数'+hrefs_files.length+']</strong>发布于'+time+'   过期时间:'+deadLine+'</p>';
            $("#work"+taskNum+" h4").append(themetxt);

            hrefs_files.forEach(function(item, index, array){
                href = item.href;
                name = item.file;
                txt3 =  '<a href='+href+  '>' +name+ '</a>';
                $("#panelBody").append(txt3);
            });
            txt3 =
                '</div>'+
                '</div>'+
                '</div>';
            $("#panelBody").append(txt3);

            $("#panelBody").attr("id","panelBody"+taskNum);

            $("#deleteWork"+id).click(function(){
                alert("确定要删除吗？");
                var workid = {"workId":id};
                $.post("/interactingClass/DeleteWork", workid, function(res){
                    var message = $.parseJSON(res);
                    var code = message.code;
                    if(code == 0){
                        //删除成功
                        alert("删除成功！");
                        window.location.reload();
                    }
                });
            });
        });

        <!-- 分页-->
        var txtpage = '<div class="pagnations">'+
            '<ul class="page-list" id="fenyework">'+
            '</ul>'+
            '</div>';
        $("#homeworkAccordion").append(txtpage);

        var pagetxt ='<li><a href="javascript:" id="lastPagework"><i></i></a></li>'+
            '<li class="col-md-2">'+
            '<input  style="width:30px" id="pagework" name="page" >'+
            '<button style="width:30px" id="gotowork">goto</button>'+
            '</li>'+
            '<li class="nme"><a href="javascript:" id="nextPagework"><i class="right"></i></a></li>';
        $("#fenyework").append(pagetxt);

        $("#lastPagework").click(function(){
            if(workpage -1  >= 1){
                workpage = workpage -1;
                workpagedata.page = workpage;
                teacherPage();
            }else{
                //当前是第一页
                alert("当前已是第一页");
            }
        });

        $("#nextPagework").click(function(){
            if(workpage + 1 <= pages){
                workpage = workpage + 1;
                workpagedata.page = workpage;
                teacherPage();
            }else{
                alert("当前已是最后一页");
            }
        });

        $("#pagework").attr("value",workpage);

        $("#gotowork").click(function(){
            var newPage;
            if($("#pagework").val() == null){
                newPage = 1;
            }else{
                newPage = $("#pagework").val();
            }
            console.log(newPage);
            if(newPage>=1 && newPage <= pages){
                workpage = newPage;
                workpagedata.page = workpage;
                teacherPage();
            }else{
                alert("该页不存在！");
            }

        });

    });

    //老师发布作业功能
    var txt12 = ' <button type="button" class="btn btn-primary btn-md buttonPosition" data-toggle="modal" data-target="#myModalWork">发布作业 </button>'+
        '<div class="modal fade" id="myModalWork" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">'+
        '<div class="modal-dialog" role="document">'+
        '<div class="modal-content">'+
        '<div class="modal-header">'+
        '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '<h4 class="modal-title" id="myModalLabelWork">发布作业</h4>'+
        '</div>'+
        '<form class="form-horizontal" action="/interactingClass/IssueWork" method="post">'+
        '<div class="modal-body">'+
        '<div class="form-group">'+
        '<div class="col-sm-offset-1 col-sm-9">'+
        '<textarea class="form-control" rows="3" placeholder="请输入作业内容" name="theme"></textarea>'+
        '</div>'+
        '</div>'+
        '<div class="form-group">'+
        '<label  class="col-sm-3 control-label">Deadline</label>'+
        '<div class="col-sm-3">'+
        '<select class="form-control" name="year">'+
        '<option value="2016">2016年</option>'+
        '<option value="2017">2017年</option>'+
        '<option value="2018">2018年</option>'+
        '<option value="2019">2019年</option>'+
        '<option value="2020">2020年</option>'+
        '</select>'+
        '</div>'+
        '<div class="col-sm-2">'+
        '<select class="form-control" name="month">'+
        '<option value="1">1月</option>'+
        '<option value="2">2月</option>'+
        '<option value="3">3月</option>'+
        '<option value="4">4月</option>'+
        '<option value="5">5月</option>'+
        '<option value="6">6月</option>'+
        '<option value="7">7月</option>'+
        '<option value="8">8月</option>'+
        '<option value="9">9月</option>'+
        '<option value="10">10月</option>'+
        '<option value="11">11月</option>'+
        '<option value="12">12月</option>'+
        '</select>'+
        '</div>'+
        '<div class="col-sm-2">'+
        '<select class="form-control" name="day">'+
        '<option value="1">1日</option>'+
        '<option value="2">2日</option>'+
        '<option value="3">3日</option>'+
        '<option value="4">4日</option>'+
        '<option value="5">5日</option>'+
        '<option value="6">6日</option>'+
        '<option value="7">7日</option>'+
        '<option value="8">8日</option>'+
        '<option value="9">9日</option>'+
        '<option value="10">10日</option>'+
        '<option value="11">11日</option>'+
        '<option value="12">12日</option>'+
        '<option value="13">13日</option>'+
        '<option value="14">14日</option>'+
        '<option value="15">15日</option>'+
        '<option value="16">16日</option>'+
        '<option value="17">17日</option>'+
        '<option value="18">18日</option>'+
        '<option value="19">19日</option>'+
        '<option value="20">20日</option>'+
        '<option value="21">21日</option>'+
        '<option value="22">22日</option>'+
        '<option value="23">23日</option>'+
        '<option value="24">24日</option>'+
        '<option value="25">25日</option>'+
        '<option value="26">26日</option>'+
        '<option value="27">27日</option>'+
        '<option value="28">28日</option>'+
        '<option value="29">29日</option>'+
        '<option value="30">30日</option>'+
        '<option value="31">31日</option>'+
        '</select>'+
        '</div>'+
        '</div>'+
        '</div>'+
        '<div class="modal-footer">'+
        '<button type="submit" data-toggle="modal" data-target="#myModalWork" id="issueWork">发布</button>'+
        '</div>'+
        '</form>'+
        '</div>'+
        '</div>'+
        '</div>';

    $("#homework").append(txt12);

}








