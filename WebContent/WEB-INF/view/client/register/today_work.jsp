<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html lang="en">
	<head>
 <meta charset="utf-8" />
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <meta name="viewport" content="width=device-width" />
 <title>今日工作</title>
 <link href="${path}/css/bootstrap.min.css" rel="stylesheet" />
 <link href="${path}/css/bootstrap-table.min.css" rel="stylesheet" />
 <link href="${path}/css/his.css" rel="stylesheet" />
		<link rel="stylesheet" href="${path}/layer/skin/layer.css" />
</head>
<body>
	  <div id="toolbar" class="btn-group btngroup">
		   <button id="btn_add" type="button" class="btn btn-success btn-color" value="${yesterday}" data-type="">
		    <span class="" aria-hidden="true">昨日就诊回访 (<i id="count1"></i>)</span>
		   </button>
		   <button id="btn_edit" type="button" class="btn btn-success" value="${today}" data-type="1">
		    <span class="" aria-hidden="true" >今日预约确认(<i id="count2"></i>)</span>
		   </button>
		   <button id="btn_delete" type="button" class="btn btn-success" value="${tomorrow}" data-type="1">
		    <span class="" aria-hidden="true">明日预约确认(<i id="count3"></i>)</span>
		   </button>
	  </div>
  <div class="col-sm-12">
  	 <table id="register-table"></table>
  </div>
 </div>
<!--发送短信模态框start-->
		 <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  data-backdrop ="static">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title text-center" id="myModalLabel">发短信</h4>
		            </div>
		            <div class="modal-body">
		            	<div class="container-fluid">		            		
	            			 <form class="form-horizontal" role="form">
								 <input id="registerId" type="hidden" />
							    <div class="form-group">
							        <label  class="col-sm-2 control-label">患者姓名</label>
							        <div class="col-sm-10">
							            <input id="patName_id" type="text" class="form-control"  readonly="readonly" value="偶小米" placeholder="偶小米" maxlength=50>
							        </div>
							    </div>
							    <div class="form-group">
							        <label  class="col-sm-2 control-label">患者性别</label>
							        <div class="col-sm-10">
							           <input type="text" class="form-control"  readonly="readonly" value="女" placeholder="女" maxlength=3>
							        </div>
							    </div>
								 <div class="form-group">
							        <label  class="col-sm-2 control-label">手机号码</label>
							        <div class="col-sm-10">
							            <input id="mobile_id" type="text" class="form-control"  readonly="readonly" value="13754322322" placeholder="13754322322" maxlength=15>
							        </div>
							    </div>
							    <div class="form-group">
							        <label  class="col-sm-2 control-label">就诊医生</label>
							        <div class="col-sm-10">
							            <input id="docName_id" type="text" class="form-control"  readonly="readonly" value="陆小曼" placeholder="陆小曼">
							        </div>
							    </div>
							    <div class="form-group">
							        <label  class="col-sm-2 control-label">就诊事项</label>
							        <div class="col-sm-10">
							            <input  id="serviceItems_id" type="text" class="form-control"  readonly="readonly" value="种植" placeholder="种植">
							        </div>
							    </div>
							    <div class="form-group">
							        <label  class="col-sm-2 control-label">时间</label>
							        <div class="col-sm-5">
							            <input id="date_id"  type="text" class="form-control"  readonly="readonly" value="2016-09-08" placeholder="2016-09-08">
							        </div>
							        <div class="col-sm-5">
							            <input id="time_id" type="text" class="form-control"  readonly="readonly" value="10:00" placeholder="10:00">
							        </div>
							    </div>
							    <div class="form-group">
							        <label  class="col-sm-2 control-label">短信模板</label>
							        <div class="col-sm-10">
							            <select class="form-control field" id="select-model">
								            <option value="1">预约模板</option>
								            <!-- <option value="2">回访模板</option> -->
								        </select> 
							        </div>
							    </div>
							    <div class="form-group">
							        <label  class="col-sm-2 control-label">短信内容</label>
							        <div class="col-sm-10 textareas">
							            <div class="border-all">
							            	<p id="template1">
												<span>尊敬的</span>
												<span id="template1_patName" ></span>
												<span>您好，您预约了</span>
												<span id="template1_time" ></span>
												<span>至欢乐口腔${hisOrganiz.unitName}分院看诊，</span>
												<!-- <span>的</span>
												<span id="template1_docName"></span>
												<span>医生进行</span>
												<span id="template1_serviceItems"></span> -->
												<span>就诊地址：</span>
												<span>${hisOrganiz.address}请您提前安排好时间，如需改约请您致电：${hisOrganiz.tel}</span>
											</p>
							            </div>
										<div class="border-all" style="display:none ">
											<p id="template2">
												<span id="template2_patName" ></span>
												<span>您好，您预约的欢乐口腔${hisOrganiz.unitName}分院,</span>
												<span id="template2_docName"></span>
												<span>医生的</span>
												<span id="template2_serviceItems"></span>
												<span>项目即将到期。</span><br />
												<span>到期时间：</span>
												<span id="template2_time" ></span><br />
												<span>就诊地址：</span>
												<span>${hisOrganiz.address}</span><br />
												<span>客服电话：${hisOrganiz.tel}</span>
											</p>
										</div>
							        </div>
							    </div>
							    <div class="col-sm-3 col-sm-offset-1">
							    	<button type="button" id="sendMsg-btn" class="btn btn-block btn-success">发送提醒</button>
							    </div>
							</form> 
		            	</div>
		           </div>
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal -->
		</div> 
		<!--发送短信模态框end-->
 <!--@*1、Jquery组件引用*@-->
 <script src="${path}/js/jquery-2.1.4.min.js"></script>
 <script src="${path}/js/bootstrap.min.js"></script>
 <script src="${path}/js/bootstrap-table.min.js"></script>
 <script src="${path}/js/bootstrap-table-zh-CN.min.js"></script>
 <script src="../../assets/js/bootbox.js"></script>
	<script src="../../assets/js/alert.js"></script>
	  <script src="${path}/layer/layer.js"></script>
 <script>
 $(function () {
	 //初始化表格
	 initTable();

	 //列表切换
	 $(".btngroup button").on("click",function(){
		 $(this).addClass("btn-color").siblings().removeClass("btn-color");
		 $('#register-table').bootstrapTable('refresh');
	 });

	//模态框打开事件
	$("#").click(function(){
		$.ajax({
			type : 'PUT',
			url : 'xxx',
			contentType : 'application/json',
			data : jsonStr,
			dataType : 'json',
			async : true,        // 异步
			success : function(data) {
				if (data.error != null && data.error != '') {


				} else {
					$('#').attr('data-toggle', 'modal').attr('data-target','#myModal'); // 给元素添加属性 打开模态框
				}
			},
			error : function(data) {

			}
		});
	});

	//短信模板选择
    $("#select-model").change(function(){
		var txt=$(this).val();
		//alert(txt);
        var areas=$(".textareas ").find(".border-all");
		//0预约 1回访
        if (txt!=1) {
        	areas.eq(0).css("display","none");
        	areas.eq(1).css("display","block");
        }else{
        	areas.eq(0).css("display","block");
        	areas.eq(1).css("display","none");
        }
                     
	});

	 $('#sendMsg-btn').click(function(){
		 var num = $('#select-model>option:selected').val();
		 var registerId = $("#registerId").val();
		 var mobile = $("#mobile_id").val();
		 var msg = $("#template"+num).children().text();
		 //console.info(msg);
		 //alert("registerId="+registerId+"mobile="+mobile+"msg="+msg);
		 //return false;
		 $.ajax({
		  url: "/enjoyhisfy/client/register/send_msg.json",
		  data:{"mobile":mobile,"registerId":registerId,"msg":msg},
		  type: 'post',
		  dataType: 'json',
		  success: function (data) {
			  $('#register-table').bootstrapTable('refresh');
			  $('#myModal').fadeOut('normal');
			  $('.modal-backdrop').fadeOut('normal');
			  layer.msg('操作成功！');
		  }
		  });
	 });
});
 
 
 function initTable(){
		$('#register-table').bootstrapTable({
 		method: 'post',
 		url: "/enjoyhisfy/client/register/find_register.json",
 		pagination: true,
 		queryParamsType:'',	//默认值为 'limit' ,在默认情况下 传给服务端的值为：offset  limit sort
 							// 设置为 ''  在这种情况下传给服务器的值为：pageSize  pageNumber
 		toolbar: '#toolbar',    //工具按钮用哪个容器
	   	striped: true,      //是否显示行间隔色
	   	cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	   	pagination: true,     //是否显示分页（*）
	   	sortable: false,      //是否启用排序
	   	sortOrder: "asc",     //排序方式
	   	pageNumber:1,      //初始化加载第一页，默认第一页
	   	pageSize: 1000,      //每页的记录行数（*）
	   	pageList: [10,20,50],  //可供选择的每页的行数（*）
	 	queryParams: queryParams,//传递参数（*）
	   	sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
	  	minimumCountColumns: 2,    //最少允许的列数
	   	clickToSelect: true,    //是否启用点击选中行
	 	contentType: "application/x-www-form-urlencoded",
	 	searchOnEnterKey: true,
// 		search:true,
 	    columns:
 	    	[
			   {
			    field: 'isnew',
			    align: 'center',
			    title: '初/复诊',
			    formatter: function(value,row,index){
	        		if(value==0) return "复诊";
	        		else return "初诊";
		        	}
			   },
			  /* {
			    field: 'status',
			    align: 'center',
// 			    visible:false,
			    title: '就诊状态',
			    formatter: function(value,row,index){
			    	//通过formatter
			    	//状态，1预约，2挂号，3待收费，4已诊结，5预约未到，6取消预约，7退号
	        		if(value==1)
						return '预约';
	        		else if(value==2)
						return '已挂号';
	        		else if(value==3)
						return '待收费';
	        		else if(value==4)
						return '已完成';
	        		else if(value==5)
	        			return "预约未到";
	        		else if(value==6)
	        			return "取消预约";
	        		else
	        			return "退号";
	        	}
			   },*/
			   {
			    field: 'patName',
			    align: 'center',
			    title: '患者姓名'
			   },{
			    field: 'mobile',
			    align: 'center',
			    title: '手机号码'
			   },{
			    field: 'patNo',
			    align: 'center',
			    title: '病历编号'
			   },{
			    field: 'docName',
			    align: 'center',
			    title: '医生'
			   },{
			    field: 'status',
			    align: 'center',
			    title: '时间',
				formatter:function(value,row,index){
					if(value==1)
						return row.beginTime.substring(11,16);
					else
						return row.time.substring(11,16);
				}
			   }, {
			    field: 'serviceItems',
			    align: 'center',
			    title: '事项'
			   }, {
			    field: 'source',
			    align: 'center',
			    title: '来源'
			   },
				{
					field: 'id',
					align: 'center',
					title: '电话回访',
					formatter: function(value,row,index){
//						console.info(row.outCall=="0")
						if(row.outCall=="0"){
							var str ='<input type="checkbox" onclick="sendMsgOrCall('+row.registerId+','+row.mobile+',1);"  value="'+row.registerId+'"/>';
						}else{
							var str ='<input type="checkbox"  checked="checked" disabled="disabled" value="'+row.registerId+'"/>';
						}
						return str;
					}
				},
				{
					field: 'id',
					align: 'center',
					title: '短信回访',
					formatter: function(value,row,index){
						if(row.msgId==0)
							var str ='<input type="checkbox"  onclick="sendMsgOrCall('+row.registerId+','+row.mobile+',2);" value="'+row.registerId+'"/>';
						else
							var str ='<input type="checkbox"  checked="checked" disabled="disabled" value="'+row.registerId+'"/>';
						return str;
					}
				},
			  {
			    field: 'id',
			    align: 'center',
			    title: '操作',
			    formatter: function(value,row,index){
					console.info(row)
 					var str ='<a href="javascript:void(0);" patName="'+row.patName+'"  mobile="'+row.mobile+'"  docName="'+row.docName+'"  serviceItems="'+row.serviceItems+'"  time="'+row.time+'"  onclick="sendMsg(this,'+row.registerId+')" data-toggle="modal" data-target="#myModal">发短信</a>';
 			        return str;
			    }
			   }
		   ],
 	  		pagination:true,
 	});
	//计算tab标签上的统计数量
		 $.ajax({
			 url: "/enjoyhisfy/client/register/find_todaywork_count.json",
//			 data:{},
			 type: 'post',
			 dataType: 'json',
			 success: function (data) {
	//				 console.info(data.returndata.count1);
				 $("#count1").text(data.returndata.count1);
				 $("#count2").text(data.returndata.count2);
				 $("#count3").text(data.returndata.count3);
			 }
		 });

	}
 
		//table查询参数
		function queryParams(params) {
			return {
				dateTime: $('.btngroup .btn-color').attr("value"),
				type:$('.btngroup .btn-color').attr("data-type"),
				pageSize: params.pageSize,
				pageNumber: params.pageNumber,
			};
		
		}
	function sendMsgOrCall(registerId,mobile,type){
		$.ajax({
			url: "/enjoyhisfy/client/register/update_msg_call.json",
			data:{"registerId":registerId,"mobile":mobile,"type":type},
			type: 'post',
			dataType: 'json',
			success: function (data) {
				$('#register-table').bootstrapTable('refresh');
			}
		});
	}

	 function sendMsg(obj,registerId){
		 var jqueryObj = $(obj);
		 $('#registerId').val(registerId);
		 $('#patName_id').val(jqueryObj.attr('patName'));
		 $('#mobile_id').val(jqueryObj.attr('mobile'));
		 $('#docName_id').val(jqueryObj.attr('docName'));
		 $('#serviceItems_id').val(jqueryObj.attr('serviceItems'));
		 $('#date_id').val(jqueryObj.attr('time').substring(0,10));
		 $('#time_id').val(jqueryObj.attr('time').substring(11,16));
		//给模板动态赋值
		 $('#template1_patName').text(jqueryObj.attr('patName'));
		 $('#template1_docName').text(jqueryObj.attr('docName'));
		 $('#template1_serviceItems').text(jqueryObj.attr('serviceItems'));
		 $('#template1_time').text(jqueryObj.attr('time'));

		 $('#template2_patName').text(jqueryObj.attr('patName'));
		 $('#template2_docName').text(jqueryObj.attr('docName'));
		 $('#template2_serviceItems').text(jqueryObj.attr('serviceItems'));
		 $('#template2_time').text(jqueryObj.attr('time'));

	 }

	    
 </script>
</body>
</html>