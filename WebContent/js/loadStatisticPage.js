$.post("/interactingClass/Statistic", function (res) {
            var message = $.parseJSON(res);
            console.log(message);

            var userNum = message.userNum;
            var blogNum = message.blogNum;
            var userBlog = message.userBlog;

            $("#userNum").text("总用户："+userNum);
            $("#blogNum").text("总博客数："+blogNum);

            userBlog.forEach(function(item, index, array){
                var username = item.username;
                var blog = item.blog;
                var likeNum = item.likeNum;
                var commentNum = item.commentNum;

                var txt = '<tr>'+
                    '<td>'+username+'</td>'+
                    '<td>'+blog+'</td>'+
                    '<td>'+likeNum+'</td>'+
                    '<td>'+commentNum+'</td>'+
                    '</tr>';
                $("table").append(txt);
            });

});


    function AutomateExcel()
    {

        var oXL = new ActiveXObject("Excel.Application"); //创建应该对象
        var oWB = oXL.Workbooks.Add();//新建一个Excel工作簿
        var oSheet = oWB.ActiveSheet;//指定要写入内容的工作表为活动工作表
        var table = document.all.data;//指定要写入的数据源的id
        var hang = table.rows.length;//取数据源行数
        var lie = table.rows(0).cells.length;//取数据源列数

// Add table headers going cell by cell.
        for (i=0;i<hang;i++){//在Excel中写行
            for (j=0;j<lie;j++){//在Excel中写列
//定义格式
                oSheet.Cells(i+1,j+1).NumberFormatLocal = "@";
//!!!!!!!上面这一句是将单元格的格式定义为文本
                oSheet.Cells(i+1,j+1).Font.Bold = true;//加粗
                oSheet.Cells(i+1,j+1).Font.Size = 10;//字体大小
                oSheet.Cells(i+1,j+1).value = table.rows(i).cells(j).innerText;//向单元格写入值
            }
        }
        oXL.Visible = true;
        oXL.UserControl = true;
    }
