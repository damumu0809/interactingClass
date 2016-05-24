//加载投票页面
var txt = '<div class="panel-group" id="voteAccordion" role="tablist" aria-multiselectable="true">';

    $.post("/interactingClass/VotePage", function(res){
        var message = $.parseJSON(res);
        var list= message.list;

        if(list.length != 0){
            $("#vote").append(txt);
        }

        var voteId;
        var multipleChoice;
        var theme;
        var option1, option2, option3, option4, option5, option6;
        var number1, number2, number3, number4, number5, number6;
        var hasVoted;

        //遍历数组list
        list.forEach(function(item, index, array){
            txt = '<div class="panel panel-default">'+
                '<div class="panel-heading" role="tab" id="voteOne">'+
                '<h4 class="panel-title">'+
                '<a id="voteA" class="collapsed" role="button" data-toggle="collapse" data-parent="#voteAccordion" href="#collapseVoteOne" aria-expanded="false" aria-controls="collapseOne">'+
                '投票1'+
                '</a>'+
                '</h4>'+
                '</div>'+
                '<div id="collapseVoteOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">'+
                '<div class="panel-body" id="panelBody">';

            //读到json内容
            voteId = item.voteId;
            multipleChoice = item.multipleChoice;
            theme = item.theme;
            option1 = item.option1;
            option2 = item.option2;
            option3 = item.option3;
            option4 = item.option4;
            option5 = item.option5;
            option6 = item.option6;
            number1 = item.number1;
            number2 = item.number2;
            number3 = item.number3;
            number4 = item.number4;
            number5 = item.number5;
            number6 = item.number6;
            hasVoted = item.hasVoted;


//                                if(option3 =="null"){
//                                    option3 = null;
//                                }
//                                if(option4 =="null"){
//                                    option4 = null;
//                                }
//                                if(option5 =="null"){
//                                    option5 = null;
//                                }
//                                if(option6 =="null"){
//                                    option6 = null;
//                                }

            if(hasVoted == false){
                //未投票，显示投票页面

                if(multipleChoice == 1){
                    //多选
                    txt = txt +
                        '<form action="/interactingClass/Vote" method="post">'+
                        '<input type="checkbox" value="option1" name="check1">'+option1+
                        '<input type="checkbox" value="option2" name="check2">'+option2+
                        '<input type="checkbox" value="option3" name="check3">'+option3+
                        '<input type="checkbox" value="option4" name="check4">'+option4+
                        '<input type="checkbox" value="option5" name="check5">'+option5+
                        '<input type="checkbox" value="option6" name="check6">'+option6+
                        '<input type="hidden" name="voteId" value=' +voteId+'>'+
                        '<input type="hidden" name="multipleChoice" value=' +multipleChoice+'>'+
                        '<button type="submit">参与投票</button>'+
                        '</form>'+
                        '</div>'+
                        '</div>';
                }else{
                    //单选
                    txt = txt +
                        '<form action="/interactingClass/Vote" method="post">'+
                        '<input type="radio" value="option1" name="vote">'+option1+
                        '<input type="radio" value="option2" name="vote">'+option2+
                        '<input type="radio" value="option3" name="vote">'+option3+
                        '<input type="radio" value="option4" name="vote">'+option4+
                        '<input type="radio" value="option5" name="vote">'+option5+
                        '<input type="radio" value="option6" name="vote">'+option6+
                        '<input type="hidden" name="voteId" value=' +voteId+ '> '+
                        '<input type="hidden" name="multipleChoice" value=' +multipleChoice+'>'+
                        '<button type="submit">参与投票</button>'+
                        '</form>'+
                        '</div>'+
                        '</div>';

                }

                $("#voteAccordion").append(txt);

                $("#voteOne").attr("id", "vote"+voteId);
                $("#collapseVoteOne").attr("aria-labelledby", "heading"+voteId);
                $("#collapseVoteOne").attr("id", "collapseVote"+voteId);
                $("#voteA").attr("href", "#collapseVote"+voteId);
                $("#voteA").attr("aria-controls", "#collapse"+voteId);
                $("#voteA").text(theme);
                $("#voteA").attr("id","voteA"+voteId);
                //空白项不显示
//                                    if(option3 == null){
//                                        $("#panelBody").getAnonymousElementByAttribute("value","option3").attr("display","none");
//                                    }
//                                    if(option4 == null){
//                                        $("#panelBody").getAnonymousElementByAttribute("value","option4").attr("display","none");
//                                    }
//                                    if(option5 == null){
//                                        $("#panelBody").getAnonymousElementByAttribute("value","option5").attr("display","none");
//                                    }
//                                    if(option6 == null) {
//                                        $("#panelBody").getAnonymousElementByAttribute("value", "option6").attr("display", "none");
//                                    }


               
            }else {
                //已投票，显示投票结果
                txt = txt +
                    '<p>'+option1+'</p>' +
                    '<label>'+number1+'</label>' +
                    '<p>'+option2+'</p>' +
                    '<label>'+number2+'</label>' +
                    '<p>'+option3+'</p>' +
                    '<label>'+number3+'</label>' +
                    '<p>'+option4+'</p>' +
                    '<label>'+number4+'</label>' +
                    '<p>'+option5+'</p>' +
                    '<label>'+number5+'</label>' +
                    '<p>'+option6+'</p>' +
                    '<label>'+number6+'</label>';

                $("#voteAccordion").append(txt);
                $("#voteOne").attr("id", "vote" + voteId);
                $("#collapseVoteOne").attr("aria-labelledby", "heading" + voteId);
                $("#collapseVoteOne").attr("id", "collapseVote" + voteId);
                $("#voteA").attr("href", "#collapseVote" + voteId);
                $("#voteA").attr("aria-controls", "#collapse" + voteId);
                $("#voteA").text(theme);
                $("#voteA").attr("id","voteA"+voteId);
                //空白项不显示
                //if (option3 == null) {
                //    $("#panelBody").innerHTML("label").getAnonymousElementByAttribute("text", number3).attr("display", "none");
                //    if (option4 == null) {
                //        $("#panelBody").innerHTML("label").getAnonymousElementByAttribute("text", number4).attr("display", "none");
                //    }
                //    if (option5 == null) {
                //        $("#panelBody").innerHTML("label").getAnonymousElementByAttribute("text", number5).attr("display", "none");
                //    }
                //    if (option6 == null) {
                //        $("#panelBody").innerHTML("label").getAnonymousElementByAttribute("text", number6).attr("display", "none");
                //    }
                //}
            }
            $("#panelBody").attr("id","panelBody"+voteId);
        });
    });


//发布投票添加选项JS
var num = 2;
var addOption ;
$("#addOption").click(function(){
    if(num < 6){
        num++;
        addOption = "<div class='form-group'>"+
            "<div class='col-sm-offset-1 col-sm-7'>"+
            "<input type='choice' class='form-control' placeholder='选项 "+num+" ' name='option "+num+"'>"+
        "</div>"+
        "</div>"
        $("#voteForm").append(addOption);
    }else{
        alert("Sorry,最多6个选项");
    }
    return false;
});

