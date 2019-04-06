<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
	<title>Pos卡管理</title>
 		<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
		<link href="../css/bootstrap-table.min.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="../css/his.css"/>
		<script src="../js/jquery-2.1.4.min.js"></script>
		<script src="../js/bootstrap.min.js"></script>
		<script src="../js/bootstrap-table.min.js"></script>
 		<script src="../js/bootstrap-table-zh-CN.min.js"></script>
 		<script src="../../assets/js/bootbox.js"></script>
		<script src="../../assets/js/alert.js"></script>

</head>
<body>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12" style="margin-top: 15px;">
					<div class="container-fluid ">
			   			<div class="row">	
			   				<div class="col-md-12">
			   					<div class="row">			   							
								    <div class="col-sm-3 ">
								    	<div class="form-group">
								    		<label for="firstname" class="col-sm-5 control-label">POS机名称</label>
										    <div class="col-sm-7">
										        <input type="text" class="form-control" id="zkfa" />
										    </div>
								    	</div>							      
								    </div>
								    <div class="col-sm-3 ">
								    	<div class="form-group">
								    		<label for="firstname" class="col-sm-5 control-label">银行名称</label>
										    <div class="col-sm-7">
										        <input type="text" class="form-control" id="unitselect"/>
										    </div>
								    	</div>							      
								    </div>
								    <div class="col-sm-3 ">
								    	<div class="form-group">
								    		<div class="checkbox">
										        <label>
										            <input type="checkbox" id="isLater" value="1">仅显示有效POS
										        </label>
										    </div> 
										</div>		
								    </div>
								    <div class="col-sm-3 ">
								    	<div class="form-group col-sm-6">
								    		<button type="button" class="btn btn-block btn-success" id="search-btn">查询</button>
								    	</div>							      
								    	<div class="form-group col-sm-6">
								    		<button type="button" class="btn btn-block btn-success" id="search-btn1">添加</button>
								    	</div>							      
								    </div>
								</div>
			   				</div>			   							
			   			</div>	   				
			   		</div>
			   		<hr />			   		
			   			<!--底部表格 START-->
			   			<div class="col-md-12 table-responsive">
			   				<div class="row">
			   					<table class="table table-bordered" style="word-break:break-all; word-wrap:break-all;" id="table_text"></table>
			   			<!--底部表格 END-->
			   				</div>
			   			</div>
					</form>
				</div>				
			</div>
		</div>	
		
		
<div class="modal bs-example-modal-lg" tabindex="-1" id="myModal" role="dialog" aria-labelledby="mylargeModalLabel" aria-hidden="true" data-backdrop ="static">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title text-center" id="myModalLabel">
                  	新增POS卡
                </h4>
            </div>
            <div class="modal-body" style="overflow: auto;">
               <form class="form-horizontal myforms" role="form" name="hpd" id="hpd">
						    <br>
						    <div class="form-group" id="showrymc">      
						    <label class="col-sm-2 text-right" for="employeeName" >POS机名称:</label>     
						    <div class="col-sm-4">
						        <input type="text"  class="form-control" id="posName" name="posName" placeholder="请输入POS机名称">   
						    </div>   
						    </div>
						    
						    <div class="form-group"  id="showmm">   
						    <label class="col-sm-2 text-right" for="password">银行:</label>     
						    <div class="col-sm-4">   
						       <input type="text" class="form-control" id="bankName" name="bankName" placeholder="请输入银行">     
						    </div>   
						    </div>
						    <div class="form-group"  id="showsjh">   
						    <label class="col-sm-2 text-right" for="mobile">银行账户:</label>     
						    <div class="col-sm-4">   
						        <input type="text" class="form-control"  id="bankNo" name="bankNo" placeholder="请输入银行账户">     
						    </div>   
						    </div>   
						</form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="clean()">清空</button>
                <button type="button" class="btn btn-sure" onclick="save()">确定</button>
            </div>
        </div><!--modal content-->
    </div>
</div>
		
		
		<script>
		$(function () {
			//初始化表格
			initTable();

			$("#search-btn").click(function(){
				$('#table_text').bootstrapTable('refresh');
			});
			$("#search-btn1").click(function(){
				showSave();
			});

		});
			
		function initTable(){
			$('#table_text').bootstrapTable({
				method: 'post',
				url: "/enjoyhisfy/pos/getList.json",
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
				pageSize: 10,      //每页的记录行数（*）
//				pageList: [10,20,50],  //可供选择的每页的行数（*）
				queryParams: queryParams,//传递参数（*）
				sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
				minimumCountColumns: 2,    //最少允许的列数
				clickToSelect: true,    //是否启用点击选中行
				contentType: "application/x-www-form-urlencoded",
				searchOnEnterKey: true,
//	 		search:true,
				columns:
					[
						{
							field: 'id',
							align: 'center',
							title: 'ID'
						},
						{
							field: 'posName',
							align: 'center',
							title: 'POS名称'
						},
						{
							field: 'bankName',
							align: 'center',
							title: '银行名称'
						},
						{
							field: 'bankNo',
							align: 'center',
							title: '银行账户'
						},
						{
							field: 'status',
							align: 'center',
							title: '操作',
							formatter: function(value,row,index){
								var msg = value==0?"启用":"停用";
								var url = value==1?0:1;
								var str = "<a href='#' "+
//									"onclick='updateStuts(\""+row.id+"\","+row.isdele+")'>"+msg+"</a>"
									"onclick='updatePos("+row.id+","+url+")'>"+msg+"</a>"
			 			        return str;
						    }
						}
					],
				pagination:true,
			});
		}
		
		function showSave(){
			clean();
			$('#myModal').modal({
				backdrop: 'static',
				keyboard: true
			})
		}
		
		
		//table查询参数
		function queryParams(params) {
			var isLater = 0
			if($('#isLater').is(':checked')) {
				isLater = 1;
			}
			return {
				posName:$('#zkfa').val(),
				bankName:$('#unitselect').val(),
				isLater:isLater,
				pageSize: params.pageSize,
				pageNum: params.pageNumber,
			};

		}
		
		function save(){
			var posName = $("#posName").val();
			var bankName = $("#bankName").val();
			var bankNo = $("#bankNo").val();
			
			if (posName==""||posName==null) {
				alert("POS机名称不能为空");
				return false;
			} if (bankName==""||bankName==null) {
				alert("银行不能为空！");
				return false;
			}if (bankNo==""||bankNo==null) {
				alert("银行账户不能为空！");
				return false;
			}
			
			$.ajax({
				url:"/enjoyhisfy/pos/savePos.json",
				type:'POST',
				async:false,
				dataType:'json',
				data:$('#hpd').serialize(),
				success:function(data,textStatus,XMLHttpRequest){
					if(data.success){
						alert("添加成功",cb)
						//$('#myModal').modal('hide');
						//$('#table_text').bootstrapTable('refresh');
					}else{
						alert("添加失败")
						clean();
					}
				}
			});
		}
		var cb = function(){
			$('#myModal').modal('hide');
			$('#table_text').bootstrapTable('refresh');
		}
		function clean(){
			$("#hpd input").val("");//清空input的数据
		}
		function updatePos(cardId,str){
			$.ajax({
				url:"/enjoyhisfy/pos/updatePos.json",
				type:'POST',
				async:false,
				dataType:'json',
				data:{
					id:cardId,
					status:str
				},
				success:function(data,textStatus,XMLHttpRequest){
					if(data.success){
						$('#table_text').bootstrapTable('refresh');
					}else{
						alert("操作失败")
					}
				}
			});
		}
		
		</script>
	</body>
</html>