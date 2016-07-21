
    var spread = new GcSpread.Sheets.Spread(document.getElementById("ss"));

    initSpread(spread);

    $("#btnPrint").click(function () {
        // used to adjust print range, should set with printInfo (refer custom print for detail)
        //spread.sheets[0].setText(31, 11, " ");

        spread.print();
    });
    function initSpread(spread) {
        var sheet = spread.getActiveSheet();

        sheet.isPaintSuspended(true);
        sheet.allowCellOverflow(true);
        sheet.setName("blog");

        $.post("/interactingClass/Statistic", function (res) {
            var message = $.parseJSON(res);
            console.log(message);

            var userNum = message.userNum;
            var blogNum = message.blogNum;
            var userBlog = message.userBlog;

            $("#userNum").text("总用户："+userNum);
            $("#blogNum").text("总博客数："+blogNum);


            sheet.addSpan(1, 1, 1, 3);
            sheet.setValue(1, 1, "总用户");
            sheet.addSpan(1, 4, 1, 1);
            sheet.setValue(1, 4, userNum );
            sheet.addSpan(1, 5, 1, 3);
            sheet.setValue(1, 5, "总博客");
            sheet.addSpan(1, 8, 1, 1);
            sheet.setValue(1, 8, blogNum);
            sheet.addSpan(2, 1, 1, 4);
            sheet.setValue(2, 1, "用户名");
            sheet.addSpan(2, 5, 1, 1);
            sheet.setValue(2, 5, "已发博客");
            sheet.addSpan(2, 6, 1, 1);
            sheet.setValue(2, 6, "赞");
            sheet.addSpan(2, 7, 1, 1);
            sheet.setValue(2, 7, "评论");
            sheet.addSpan(2, 8, 1, 1);
            sheet.setValue(2, 8, "占比");
            sheet.addSpan(2, 9, 1, 4);
            sheet.setValue(2, 9, "图示");

            userBlog.forEach(function(item, index, array){
                var username = item.username;
                var blog = item.blog;
                var likeNum = item.likeNum;
                var commentNum = item.commentNum;

                //var txt = '<tr>'+
                //    '<td>'+username+'</td>'+
                //    '<td>'+blog+'</td>'+
                //    '<td>'+likeNum+'</td>'+
                //    '<td>'+commentNum+'</td>'+
                //    '</tr>';
                //$("table").append(txt);
                sheet.addSpan(index+3, 1, 1, 4);
                sheet.setValue(index+3, 1, username);
                sheet.addSpan(index+3, 5, 1, 1);
                sheet.setValue(index+3, 5, blog);
                sheet.addSpan(index+3, 6, 1, 1);
                sheet.setValue(index+3, 6, likeNum);
                sheet.addSpan(index+3, 7, 1, 1);
                sheet.setValue(index+3, 7, commentNum);

                var t  = blog/blogNum;

                sheet.addSpan(index+3, 8, 1, 1);
                sheet.setValue(index+3, 8, t);

                //var colors = ["#0099FF", "#33FFFF", "#9E0142", "#D53E4F", "#F46D43", "#FDAE61", "#FEE08B", "#E6F598", "#ABDDA4", "#66C2A5", "#3288BD", "#5E4FA2"];
                sheet.addSpan(index+3, 9, 1, 4);

                sheet.setFormula(index+3, 9, '=HBARSPARKLINE('+t+',"#D53E4F")');
            });


            sheet.getCells(1, 1, 1, 3).backColor("#D9D9FF");
            sheet.getCells(1, 5, 1, 7).backColor("#D9D9FF");
            sheet.getCells(2, 1, 2, 8).backColor("#D9FFD9");//两点坐标
           // sheet.text().hAlign(GcSpread.Sheets.HorizontalAlign.center);


            //sheet.addSpan(31, 5, 1, 4);
            //sheet.getCell(31, 5).text("Figure 1").hAlign(1);
        });



        //sheet.setBorder(new GcSpread.Sheets.Range(1, 1, 18, 10), new GcSpread.Sheets.LineBorder("Black", GcSpread.Sheets.LineStyle.thin), { all: true });
        //sheet.setBorder(new GcSpread.Sheets.Range(4, 4, 3, 6), new GcSpread.Sheets.LineBorder("Black", GcSpread.Sheets.LineStyle.dotted), { inside: true });
        //sheet.setBorder(new GcSpread.Sheets.Range(7, 4, 3, 6), new GcSpread.Sheets.LineBorder("Black", GcSpread.Sheets.LineStyle.dotted), { inside: true });
        //sheet.setBorder(new GcSpread.Sheets.Range(11, 4, 3, 6), new GcSpread.Sheets.LineBorder("Black", GcSpread.Sheets.LineStyle.dotted), { inside: true });
        //sheet.setBorder(new GcSpread.Sheets.Range(14, 4, 3, 6), new GcSpread.Sheets.LineBorder("Black", GcSpread.Sheets.LineStyle.dotted), { inside: true });

        //fillSampleData(sheet, new GcSpread.Sheets.Range(4, 4, 6, 6));
        //fillSampleData(sheet, new GcSpread.Sheets.Range(11, 4, 6, 6));

        //sheet.setColumnWidth(0, 40);
        //sheet.setColumnWidth(1, 40);
        //sheet.setColumnWidth(3, 40);
        //sheet.setColumnWidth(4, 40);
        //sheet.setColumnWidth(11, 40);

        sheet.isPaintSuspended(false);
    }

    //function fillSampleData(sheet, range) {
    //    for (var i = 0; i < range.rowCount; i++) {
    //        for (var j = 0; j < range.colCount; j++) {
    //            sheet.setValue(range.row + i, range.col + j, Math.ceil(Math.random() * 300));
    //        }
    //    }
    //}






