<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="utf-8" />
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <meta name="viewport" content="width=device-width" />
 <title>就诊记录</title>
 <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" />
 <link href="../bootstrap/css/bootstrap-table.min.css" rel="stylesheet" />
 <link href="../bootstrap/css/his.css" rel="stylesheet" />
  <!--@*1、Jquery组件引用*@-->
 <script src="../bootstrap/js/jquery-2.1.4.min.js"></script>
 <script src="../bootstrap/js/bootstrap.min.js"></script>
 <script src="../bootstrap/js/bootstrap-table.min.js"></script>
 <script src="../bootstrap/js/bootstrap-table-zh-CN.min.js"></script>
 <script src="../assets/js/bootbox.js"></script>
	<script src="../assets/js/alert.js"></script>
 <script>
 var oTable;
 $(function () {
 //1.初始化Table
 oTable = new TableInit();
 oTable.Init();
 
 //2.初始化Button的点击事件
 var oButtonInit = new ButtonInit();
 oButtonInit.Init();
 
});
 
var TableInit = function () {
 var oTableInit = new Object();
 //初始化Table
 oTableInit.Init = function () {
  $('#tb_departments').bootstrapTable({
   url: 'getOrganizList.json',   //请求后台的URL（*）
   method: 'post',      //请求方式（*）
   toolbar: '#toolbar',    //工具按钮用哪个容器
   striped: true,      //是否显示行间隔色
//   cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
//   pagination: true,     //是否显示分页（*）
//   sortable: false,      //是否启用排序
//   sortOrder: "asc",     //排序方式
   queryParams: oTableInit.queryParams,//传递参数（*）
   sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
//   pageNumber:1,      //初始化加载第一页，默认第一页
//   pageSize: 2,      //每页的记录行数（*）
//   pageList: [10, 25, 50, 100],  //可供选择的每页的行数（*）
  // search: true,      //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
   strictSearch: true,
//   showColumns: true,     //是否显示所有的列
//   showRefresh: true,     //是否显示刷新按钮
//   minimumCountColumns: 2,    //最少允许的列数
   clickToSelect: true,    //是否启用点击选中行
//   height: 500,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
   uniqueId: "ID",      //每一行的唯一标识，一般为主键列
//   showToggle:true,     //是否显示详细视图和列表视图的切换按钮
//   cardView: false,     //是否显示详细视图
//   detailView: false,     //是否显示父子表
   rowStyle: function (row, index) {
    //这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
    var strclass = "";
    if (row.status == "开始扎帐") {
        strclass = 'success';//还有一个active
    }
    else if (row.ORDER_STATUS == "已删除") {
        strclass = 'danger';
    }
    else {
        return {};
    }
    return { classes: strclass }
},
   columns: [/*{
    checkbox: true
   },*/ {
    field: 'employeeName',
    title: '名字'
   }, 
   {
    field: 'id',
    title: 'ID'
   },
   {
    field: 'password',
    title: '密码'
   },{
    field: 'unitCode',
    title: '公司编码'
   }, ]
  });
 };
 
 //得到查询的参数
 oTableInit.queryParams = function (params) {
  var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
   name: "a", //页面大小
   id: "b",
   statu: "1"
  };
  return temp;
 };
 return oTableInit;
};

function find(){
	 $('#tb_departments').bootstrapTable('refresh');
}
 
var ButtonInit = function () {
 var oInit = new Object();
 var postdata = {};
 
 oInit.Init = function () {
  //初始化页面上面的按钮事件
 };

 return oInit;
};
 </script>
</head>
<body>
 <div class="panel-body" style="padding-bottom:0px;">
	  <div class="col-sm-12">
	  	<div class="panel panel-default">  
		    <div class="panel-body">
			    <form id="formSearch" class="form-horizontal">
				     <div class="form-group" style="margin-top:15px">
				        <label class="control-label col-sm-1" for="txt_search_departmentname">患者姓名</label>
					      <div class="col-sm-2">
					       <input type="text" class="form-control" id="txt_search_departmentname" oninput="find()">
					      </div>
				        <label class="control-label col-sm-1" for="txt_search_statu">手机号</label>
					      <div class="col-sm-2">
					       <input type="text" class="form-control" id="txt_search_statu">
					      </div>
				        <label class="control-label col-sm-1" for="txt_search_statu">状态</label>
					    <div class="col-sm-2">
					        <select size="1"  multiple="multiple" rows="10" class="form-control">
						         <option>1</option>
						         <option>2</option>
						         <option>3</option>
						         <option>4</option>
						         <option>5</option>
						    </select>
					    </div>
				      <div class="col-sm-2" style="text-align:left;">
				      	<a class="text-dection" href="#">查看历史就诊记录</a>				      
				      </div>
				     </div>
			    </form>
		    </div>
	    </div>  
	  </div>
	  <div id="toolbar" class="btn-group btngroup">
		   <button id="btn_add" type="button" class="btn btn-success">
		    <span class="" aria-hidden="true">全部 (<i>11</i>)</span>
		   </button>
		   <button id="btn_edit" type="button" class="btn btn-success">
		    <span class="" aria-hidden="true">今日挂号 (<i>9</i>)</span>
		   </button>
		   <button id="btn_delete" type="button" class="btn btn-success">
		    <span class="" aria-hidden="true">今日预约未到 (<i>2</i>)</span>
		   </button>
	  </div>
  <div class="col-sm-12">
  	 <table id="tb_departments"></table>
  </div>

 </div>
</body>
</html>