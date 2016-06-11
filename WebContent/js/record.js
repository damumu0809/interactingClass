$.ajaxSetup(
	{
		async:false
	}
);

function initPage(){

	var url="get_fileName.jsp";
	var fileName="asdasd";
	var file;
	var Id="";

	$.post(url,function(data){

		file = JSON.parse(data);
		fileName=file.fileName;
		Id=file.Id;

	});
	var url="powerpoint.jsp?fileName="+fileName;
	window.location.href=url;


}
function download()
{
	var url="get_fileName.jsp";
	var fileName="asdasd";
	var file;


	$.post(url,function(data){

		file = JSON.parse(data);
		fileName=file.fileName;


	});
	var url="download.jsp?fileName="+fileName;
	window.location.href=url;

}

jQuery(document).ready(function() {
	Metronic.init(); // init metronic core componets
	Layout.init(); // init layout
	QuickSidebar.init(); // init quick sidebar
	Demo.init(); // init demo features
	Index.init();
	Index.initDashboardDaterange();
	Index.initJQVMAP(); // init index page's custom scripts
	Index.initCalendar(); // init index page's custom scripts
	Index.initCharts(); // init index page's custom scripts
	Index.initChat();
	Index.initMiniCharts();
	Tasks.initDashboardWidget();
	initLeftMenu("teach");
	initRecord();
});
function clearTable(){
	$('.datatable').dataTable().fnClearTable();
}
function initRecord(){
	$('.datatable').dataTable( {        				
		"paging":true,
		"searching":false,
		"oLanguage": {
			"aria": {
				"sortAscending": ": activate to sort column ascending",
				"sortDescending": ": activate to sort column descending"
			},
			"sProcessing":   "处理中...",
			"sLengthMenu":   "_MENU_ 记录/页",
			"sZeroRecords":  "没有匹配的记录",
			"sInfo":         "显示第 _START_ 至 _END_ 项记录，共 _TOTAL_ 项",
			"sInfoEmpty":    "显示第 0 至 0 项记录，共 0 项",
			"sInfoFiltered": "(由 _MAX_ 项记录过滤)",
			"sInfoPostFix":  "",
			"sSearch":       "过滤:",
			"oPaginate": {
				"sFirst":    "首页",
				"sPrevious": "上页",
				"sNext":     "下页",
				"sLast":     "末页"
			}
		},
		"aoColumns": [{"mRender": function(data, type, full) {
				sReturn = '<input type="checkbox" class="checkboxes" value="'+data+'"/>';
				return sReturn;
			},"orderable": false
		},{},{},{},{},{},{},{}],
		"aLengthMenu": [[5,10,15,20,25,40,50,-1],[5,10,15,20,25,40,50,"所有记录"]],
		"fnDrawCallback": function(){$(".checkboxes").uniform();$(".group-checkable").uniform();},
		"sAjaxSource": "../../teach_classroom_data_action?action=get_record&table_name=teach_file"
	});
	$('.datatable').find('.group-checkable').change(function () {
		var set = jQuery(this).attr("data-set");
		var checked = jQuery(this).is(":checked");
		jQuery(set).each(function () {
            if (checked) {
                $(this).attr("checked", true);
                $(this).parents('tr').addClass("active");
            } else {
                $(this).attr("checked", false);
                $(this).parents('tr').removeClass("active");
            }
		});
		jQuery.uniform.update(set);
	});
	$('.datatable').on('change', 'tbody tr .checkboxes', function () {
		$(this).parents('tr').toggleClass("active");
	});
}
function addNew(){
	var projectId=encodeURI(document.getElementById("project_id").value);
	var title=encodeURI(document.getElementById("title").value);
	var content=encodeURI(document.getElementById("content").value);
	var executor=encodeURI(document.getElementById("executor").value);
	var beginTime=encodeURI(document.getElementById("begin_time").value);
	var endTime=encodeURI(document.getElementById("end_time").value);
	var expectEndTime=encodeURI(document.getElementById("expect_end_time").value);
	var endTag=document.getElementById("end_tag").value;
	var status=encodeURI(document.getElementById("status").value);
	var url="../../teach_classroom_data_action?action=add_record&table_name=teach_file"+
		"&project_id="+projectId+"&title="+title+"&content="+content+"&executor="+
		executor+"&begin_time="+beginTime+"&end_time="+endTime+"&expect_end_time="+expectEndTime+"&end_tag="+endTag+"&status="+status;
	$.post(url,function(e){processAddRecordResult(e);}).error(function(e){alert("发生错误了！");})
}
function processAddRecordResult(data){
	window.location.reload();
}
function deleteRecord(){
	var selectedCount=0;
	var set = $('.datatable').find('.group-checkable').attr("data-set");
	var url="../../teach_classroom_data_action?action=delete_record&table_name=teach_file";
	jQuery(set).each(function () {
		var checked = $(this).attr("checked");
        if (checked) {
        	var id=this.value;
        	url=url+"&id="+id;
            selectedCount++;
        } else {
        }
	});
	if(selectedCount>0){
		var tip="";
		if(selectedCount==1){
			tip="您确定要删除这个记录吗？";
		}else{
			tip="您确定要删除这 "+selectedCount+" 条记录吗？";
		}
		if(confirm(tip)){
			$.post(url,function(e){processDeleteRecordResult(e);}).error(function(e){alert("删除记录发生错误了！");})
		}
	}else{
		var tip="您尚未选择记录，请您先在列表里选择要操作的记录！";
		showMsg(tip);
	}
}
function processDeleteRecordResult(data){
	var json=eval("("+data+")");
	if(json.result_code==0){
		window.location.reload();
	}
}
function showChecked(){
	var selectedCount=0;
	var Id="";
	var set = $('.datatable').find('.group-checkable').attr("data-set");
	var url="../../teach_classroom_data_action?action=delete_record&table_name=teach_file";
	jQuery(set).each(function () {
		var checked = $(this).attr("checked");

		if (checked) {

			id=this.value;
			selectedCount++;
			alert("js de id="+id);

		} else {

		}

	});
	return id;

}

function showRecord(){
	var set = $('.datatable').find('.group-checkable').attr("data-set");
	jQuery(set).each(function () {
		var checked = $(this).attr("checked");
        if (checked) {
        	var id=this.value;
        	showRecordById(id);
        } else {
        }
	});
}
function processDeleteRecordResult(data){
	var json=eval("("+data+")");
	if(json.result_code==0){
		window.location.reload();
	}
}

function showRecordById(id){
	var url="../../teach_classroom_data_action?action=get_record_by_id&table_name=teach_file&id="+id;
	$.post(url,function(e){processShowRecordById(e);}).error(function(e){alert("processShowRecordById()发生错误了！");})
}
function processShowRecordById(data){
	data=eval("("+data+")");
	var list=data.aaData;
	document.getElementById("id").value=list[0][0];
	document.getElementById("project_id").value=list[0][1];
	document.getElementById("title").value=list[0][2];
	document.getElementById("content").value=list[0][3];
	document.getElementById("begin_time").value=list[0][4];
	document.getElementById("end_time").value=list[0][5];
	document.getElementById("expect_end_time").value=list[0][6];
	document.getElementById("executor").value=list[0][7];
	document.getElementById("end_tag").value=list[0][8];
	document.getElementById("status").value=list[0][9];
}
function modify(){
	var id=document.getElementById("id").value;
	var projectId=encodeURI(document.getElementById("project_id").value);
	var title=document.getElementById("title").value;
	var content=encodeURI(document.getElementById("content").value);
	var beginTime=document.getElementById("begin_time").value;
	var endTime=document.getElementById("end_time").value;
	var expectEndTime=encodeURI(document.getElementById("expect_end_time").value);
	var executor=encodeURI(document.getElementById("executor").value);
	var endTag=document.getElementById("end_tag").value;
	var status=document.getElementById("status").value;
	var url="../../teach_classroom_data_action?action=modify_record&table_name=teach_file"+
		"&id="+id+"&project_id="+projectId+"&title="+title+"&content="+content+"&begin_time="+
		beginTime+"&end_time="+endTime+"&expect_end_time="+expectEndTime+"&executor="+executor+"&end_tag="+endTag+"&status="+status;
	alert(url);
	$.post(url,function(e){processModifyRecordResult(e);}).error(function(e){alert("发生错误了！");})
}
function processModifyRecordResult(data){
	var json=eval("("+data+")");
	if(checkSession(json)){
		window.location="../../home/main/login.jsp";
	}
	if(json.result_code==0){
		window.location.reload();
	}
}
function checkSession(json){
	var ok=true;
	if(json.result_code==3){
		ok=false;
	}
	return ok;
}
function uploadFile(){
	window.location="file_upload.jsp";
}
function makeDefaultCourseware(){
	var selectedCount=0;
	var filePath="sdfsdf";
	var set = $('.datatable').find('.group-checkable').attr("data-set");
	var url="set_selected.jsp";
	jQuery(set).each(function () {
		var checked
			= $(this).attr("checked");
        if (checked) {
        	var id=this.value;
        	url=url+"?id="+id;
            selectedCount++;
        } else {
        }
	});

	$.post(url,function(data){
		filePath =data;
	});
		alert("已设为当前播放");
}
function processMakeDefaultRecordResult(){
	var json=eval("("+data+")");
	if(json.result_code==0){
		window.location.reload();
	}
}

