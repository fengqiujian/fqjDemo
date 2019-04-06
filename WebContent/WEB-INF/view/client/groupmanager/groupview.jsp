<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>组维护</title>
		<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${path}/css/his.css"/>
	</head>
	<body>
		<div class="container">
           <div class="modal-content">
              	<!--患者table -->				
               <div class="modal-body">
               <div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript"> try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){} </script>
				<ul class="breadcrumb">
					<li>
						<i class="icon-home home-icon"></i>
						<a href="#">首页</a>
					</li>
					<li class="active"><a href="#">后台系统管理</a></li>
					<li class="active">组维护 </li>
				</ul><!-- .breadcrumb -->
			</div>
              <form class="form-horizontal sbcondition" method="post" role="form" >
				<div class="col-xs-10">
					<div class="row">
					<div class="col-sm-2"></div>
						<div class="col-sm-7">
							<div class="form-group">
						      <label for="depName" class="col-sm-5 control-label">组名称:</label>
						      <div class="col-sm-7">
						         <input type="text" class="form-control" id="groupName2"  name="groupName" value="" placeholder="组名称">
						      </div>
						    </div>
						</div>		
						<!-- <div class="col-sm-4">
						<div class="form-group">
					      <label for="unitCode" class="col-sm-3 control-label">院区:</label>
					      <div class="col-sm-9">
					         <select  class="form-control" id="unitId1" name="unitId1" required="required" data-id=""  
					         url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_organiz&&column1=id&&column2=unit_name">
							</select>
					      	</div>
					    	</div>
						</div> -->	
						
					</div>								
				</div>
				<div class="col-xs-2">
					<div class="form-group">
					  <button type="button" name="search" class="btn btn-info mybtn" style="height:34px ;line-height:14px;">查询</button>
				    </div>
				</div>	
				<!-- <div class="col-xs-1">
					<div class="form-group">
					  <button type="button" id="add-modal" class="btn btn-info" style="height:34px ;line-height:14px;" data-toggle = "modal" data-target =".bs-example-modal-lg">增加</button>
				    </div>
				</div>	 -->
			</form>
				<!-- 
				添加信息
				选择处置项 模态框（Modal）
				<div class="modal bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="mylargeModalLabel" aria-hidden="true" data-backdrop="static">
			      	<div class="modal-dialog modal-lg">
			      		<div class="modal-content">
			      			<div class="col-sm-12" style="z-index: 100;margin-bottom: 10px;">				 								   			
			   			table START
				   			<div class="col-sm-12" style="background-color: #fff;">		   				
			   					<div class="container-fluid text-center">
			   					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					                  ×
					            </button>
			   						<h3>添加组信息</h3>
			   					</div>
							 <form class="form-horizontal myforms" role="form" action="addOrEdit_depart.json" name="activitys" id="activitys">
							    <br>
							   <div class="form-group" style="display:none;">   
							    <label class="col-sm-2 text-right" for="id">ID:</label>     
							    <div class="col-sm-4">   
							        <input type="text" class="form-control"  onblur="checkUser(this)" id="id" name="id" placeholder="ID">     
							    	<input type="hidden"   id="hiddenid" name="hiddenid" placeholder="hiddenid">
							    </div>   
							    <div id="check" ></div>
							    </div> 
							    <div class="form-group">   
							    <label class="col-sm-2 text-right" for="groupName">组名称:</label>     
							    <div class="col-sm-4">   
							        <input type="text" class="form-control"   onblur="checkUserName(this)" id="depName1" name="groupName" placeholder="组名称">     
							    </div>   
							    </div>
							    
							    <div class="form-group">      
							    	<label class="col-sm-2 text-right" for="unitId">院区:</label>     
							    <div class="col-sm-4">
							        <select  class="form-control" id="unitId" name="unitId" required="required" data-id=""  
							        url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_organiz&&column1=id&&column2=unit_name">
									</select>   
							    </div>   
						    </div>
							    <div class="form-group">
									<label class="col-sm-2 text-right"> 状态:</label>
									&nbsp;&nbsp;&nbsp;
								   	<label class="checkbox-inlin">
								      	<input type="radio" name="status" id="status1"  value="1" checked>有效
								   	</label>&nbsp;&nbsp;&nbsp;&nbsp;
								   	<label class="checkbox-inlin">
								      	<input type="radio" name="status" id="status0"  value="0"> 无效 
								    </label>
								</div>
							   	<br class="clear" />
							   	<div class="modal-footer">
					          		<button type="button" id="btn_submit" class="btn btn-primary btnAdd" data-dismiss="modal"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
						        </div>
							</form>
				   			</div>		   			
				        	</div>
			      		</div>
			      	</div>
				</div> 
				 -->
				<!--信息修改-->
				<!--选择处置项 模态框（Modal） -->
		<div class="modal bs-example-modal-lg"  id="showGroup" tabindex="-1" role="dialog" aria-labelledby="mylargeModalLabel" aria-hidden="true">
        	<div class="modal-dialog modal-lg">
        		<div class="modal-content">
        			<div class="col-sm-12" style="z-index: 100;margin-bottom: 10px;">				 								   					   			
			   			<div class="col-sm-12">		
		   					<div class="container-fluid text-center">
		   					<input type="hidden" id="showGroupId">
		   					<button type="button" class="close"
              					 data-dismiss="modal" aria-hidden="true">
                  					&times;
            				</button>
		   					<h1>人员管理</h1>
		   					</div>
		   					<div class="col-sm-12 allborder add_overflow">	
						    	<div id="showGroupPersonnel">
						    		
						    	</div>				    						    
				   			 </div>
			   			</div>		   			
			        </div>
			         <div class="modal-content">
	        			<div class="modal-footer">
	        			 <!-- data-dismiss="modal"-->
	        			<input type="hidden" id="secondChoos">
			            <button type="button" class="btn btn-default" onclick="clearModal()">取消
			            </button>	
			            <button type="button" class="btn btn-primary" onclick="save()">
              				 保存
            			</button>		            
			         </div> 
        		</div>       	
        		</div>
        	</div>
		</div> 
		<!--选择处置项 模态框（Modal） -->
               <table id="patient-table" class="col-xs-12" > </table>
             </div>
         </div><!-- /.modal-content -->
		</div>	
		<script src="${path}/js/jquery-2.1.4.min.js"></script>
		<script src="${path}/js/bootstrap.min.js"></script>		
		<script src="${path}/assets/js/bootstrap-table.min.js"></script>
		<script src="${path}/assets/js/bootstrap-table-zh-CN.min.js"></script>
		<script src="../../assets/js/bootbox.js"></script>
	<script src="../../assets/js/alert.js"></script>
		<script>
		function checkUserName(object){
			var dn = object.value;
			if (dn==null||dn=="") {
				alert("当前名称不能为空！");
				return false;
			}
			
			var xmlHttpRequest = $.post("/enjoyhisfy/client/pages/check_user_name.htm",{"depName":dn},function(data,textstatus){
				if(data==1){
					alert("当前名称已存在！")
					$(".myforms input").val("");//清空input的数据
					return false;
				}else if(data==2){
					document.getElementById("check").innerHTML = "<font color='green'>当前名称可用</font>";
				}
			});
		}
		 var $table = $('#patient-table'), 
			$search=$("#search");
			$(function(){
				//初始化表格
			    initPatientTable();
			  //添加
				$("#add-modal").on("click", function() {
					$("#meng").fadeIn(500);
					$("#modal").fadeIn(500);
					$(".tooltips").fadeOut();
				});
			  
				//添加信息btnAdd提交 请求数据
				$(".btnAdd").click(function() {
					var d = document.getElementById('depName1').value;
					if (d==null||d=="") {
						alert("组名不能为空！");
						return false;
					}
					var pid = $('#unitId option:selected').val();
					if (!pid) {
						alert("请选择院区！");
						return false;
					}
					
					$.ajax({
						cache : true,
						type : "POST",
						url : "/enjoyhisfy/client/groupmanager/addOrEdit_depart.json",
						data : $('#activitys').serialize(),
						dataType : "json",
						async : false,
						error : function(request) {
							alert("添加失败");
							return false;
						},
						success : function(data) {
							if (data == 1) {
								$("#modal").fadeOut();
								$("#meng").fadeOut();
								$('#id').attr("disabled",false);
								$(".bs-example-modal-lg input").val("");//清空input的数据
								alert("添加成功！",cb);
								//window.location.reload();
							} else {
								alert("数据处理错误");
								return false;
							}
						}
					});

				});
				var cb = function(){
					window.location.reload();
				}
				//关闭
				$(".close").click(function() {
					$("#meng").fadeOut(500);
					$("#modal").fadeOut(500);
					$(".modaltwo").fadeOut(500);
					$(".myform input").val("");//清空input的数据
					$('#id').attr("disabled",false);
					window.location.reload();
				});
				
				//查询按钮
				$(".mybtn").click(function() {
					
					//刷新表格
					$('#patient-table').bootstrapTable('refresh');
					//清空input的数据
					$(".sbcondition input").val("");
				});
            });
			
			/**验证ID在数据库中是否存在*/
			function checkUser(object){
				var id = object.value;
				if (id==null||id=="") {
					alert("当前ID不能为空！");
					return false;
				}
				var xmlHttpRequest = $.post("/enjoyhisfy/client/pages/check_id.htm",{"id":id},function(data,textstatus){
					if(data==1){
						document.getElementById("check").innerHTML = "<font color='red'>当前ID已存在</font>";
						object.focus();
					}
					else if(data==2){
						document.getElementById("check").innerHTML = "<font color='green'>当前ID可用</font>";
					}
				});
			}
			
			//初始化表格
			function initPatientTable(){
				$('#patient-table').bootstrapTable({
		    		method: 'post',
		    		url: "/enjoyhisfy/client/groupmanager/find_List.json",
		    		pagination: true,
		    		queryParamsType:'',	//默认值为 'limit' ,在默认情况下 传给服务端的值为：offset  limit sort
		   		   	striped: true,      //是否显示行间隔色
		   		   	cache: true,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		   		   	pagination: true,     //是否显示分页（*）
		   		   	sortable: false,      //是否启用排序
		   		   	sortOrder: "asc",     //排序方式
		   		   	pageNumber:1,      //初始化加载第一页，默认第一页
		   		   	pageSize: 5,      //每页的记录行数（*）
		   		   	pageList: [5,10],  //可供选择的每页的行数（*）
		   		 	queryParams: queryParams,//传递参数（*）
		   		   	sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
		   		  	minimumCountColumns: 2,    //最少允许的列数
		   		   	clickToSelect: true,    //是否启用点击选中行
		   		 	contentType: "application/x-www-form-urlencoded",
		   		 	searchOnEnterKey: true,
		    	    columns: 
		    	    		[ {
				    	        field: 'id',
				    	        title: 'ID',
				    	        align: 'center'
				    	    },{
					    	        field: 'groupName',
					    	        title: '组名称',
					    	        align: 'center'
					    	    },{
					    	    	field: 'unitId',
					    	        title: '操作',
					    	        align: 'center',
				    	        	formatter: function(value,row,index){
				    	        		var id = row.id;
				    	        		var str = '<a href="javascript:void(0);"'+
				    					'onclick="showGroup('+id+')">人员管理</a>'
				    					return str;
				    	        	}
					    	    }
				    	    /* ,{
					    	        field: 'unitId',
					    	        title: '分院',
					    	        align: 'center',
				    	        	formatter: function(value,row,index){
					    	        	var unitName = ""
				    	        	     $.ajax({ url: "/enjoyhisfy/client/hisorganiz/findEntityInfo.json?id="+value, 
			    	        	    		 async :false, 
			    	        	    		 success: function(data){
					    	        			if(data!=null){
						    	        		   unitName = $.parseJSON(data).unitName;
						    	        		}
				    	        	      }});
					    	        	return unitName;
					    	        }
					    	    } */
				    	    ],
		    	    pagination:true
		    	});
			}
			
			function showGroup(id){
				$('#showGroupId').val(id);
				showGroupPersonnel(id);
				$('#showGroup').modal({
					backdrop: 'static',
					keyboard: true
				})
			}
			
			function showGroupPersonnel(id){
				$.ajax({
					type : "POST",
					url : "/enjoyhisfy/client/groupmanager/showGroupPersonnel.json",
					dataType : "json",
					data:{groupId:id},  //页面
					success : function(data) {
						var datas = data.rows;		
						var htmlText = "";
						$(datas).each(function(){
								htmlText += '<label class="checkbox-inline"><input type="checkbox" name="checkName" value="'+this.id+'"';
								if(this.isGroup==1){
									htmlText += 'checked ="checked" ';
								}
								htmlText += '  />'+this.employeeName+'</label>';
						});
						$('#showGroupPersonnel').html(htmlText);
					}
				});
			}
			
			//编辑
			function edit(id){
				//$('#id').attr("disabled",true);
				 //修改数据
                $(".mengone").fadeIn(500);
				$(".modaltwo").fadeIn(500);
				$(".tooltips").fadeOut();
				$(".myforms input").val("");//清空input的数据
				$.ajax({
					type : "POST",
					url : "/enjoyhisfy/client/groupmanager/modify_groupInfo.json",
					dataType : "json",
					data:{"id":id},  //页面
					success : function(data) {
						//console.info(data)
 						$(".myforms :input").eq(0).val(data.returndata.id);
 						$(".myforms :input").eq(1).val(data.returndata.id);
 						$(".myforms :input").eq(2).val(data.returndata.groupName);
 						$(".myforms :input").eq(3).val(data.returndata.unitId);
 						if (data.returndata.status==1) {
 							$(".myforms :input").eq(4).val(1);
						}else {
 							$(".myforms :input").eq(5).val(0);
						}
					}
				});
			}
			
			//修改信息提交
			$(".edid").click(function() {
				window.location.reload();
				$("#activitys").ajaxSubmit(function(result) {
					if (result == 1) {
						$(".meng").fadeOut(500);
						$(".modaltwo").fadeOut(500);
						alert("操作成功！",cb);
						//window.location.reload();
					} else {
						alert("操作失败！");
						return false;
					}
				});
				return false;
			});
			
			//删除
			function del(id,status){
            	 $.ajax({
                     type: "POST",
                     dataType: "json",
                     url: "/enjoyhisfy/client/groupmanager/delete_group.htm",
                     data:{"id":id,"status":status},
                     error : function(request) {
							alert("操作失败");
							return false;
						},
						success : function(data) {
							if (data == 1) {
								$("#modal").fadeOut();
								$("#meng").fadeOut();
								alert("操做成功！",cb);
								//window.location.reload();
							} else {
								alert("数据处理错误");
								return false;
							}
						}
                 });
            	
			}
			//chaxun tiao jian 
			function queryParams(params) {
				return {
					gName:$('#groupName2').val(),
					uId:$('#unitId1').val(),
					pageSize: params.pageSize,
					pageNumber: params.pageNumber,
				};
			}
			/* $(function(){
				//动态获取select的option docroomId
				 $("#unitId1").each(function(i,n){
	                var url = $('#'+n.id).attr('url');
	                if(url!=undefined){
		                var selectedId = $('#'+n.id).attr('data-id');
		                $.ajax({
				            url : url,
				            type : 'post',
				            async: false,
				            dataType : 'json',
				            success : function(data) {
				            	var tempAjax = "";
				            	tempAjax += "<option value=''>--------请选择--------</option>";
				                $.each(data.returndata,function(i,n){
				                    if(n.id==selectedId)
					                    tempAjax += "<option value='"+n.id+"' selected='selected'>"+n.name+"</option>";
				                    else
					                    tempAjax += "<option value='"+n.id+"'>"+n.name+"</option>";
				                });
				                $("#"+n.id).empty();
				                $("#"+n.id).append(tempAjax);
				            }
				        });
	                }
	            });
			});	 */		
			/* $(function(){
				//动态获取select的option docroomId
				 $("#unitId").each(function(i,n){
	                var url = $('#'+n.id).attr('url');
	                if(url!=undefined){
		                var selectedId = $('#'+n.id).attr('data-id');
		                $.ajax({
				            url : url,
				            type : 'post',
				            async: false,
				            dataType : 'json',
				            success : function(data) {
				            	var tempAjax = "";
				            	tempAjax += "<option value=''>--------请选择--------</option>";
				                $.each(data.returndata,function(i,n){
				                    if(n.id==selectedId)
					                    tempAjax += "<option value='"+n.id+"' selected='selected'>"+n.name+"</option>";
				                    else
					                    tempAjax += "<option value='"+n.id+"'>"+n.name+"</option>";
				                });
				                $("#"+n.id).empty();
				                $("#"+n.id).append(tempAjax);
				            }
				        });
	                }
	            });
			});	 */	
			
			function clearModal(){
				$('#showGroup').modal('hide')
			}
			
			function save(){
				
				var names = getUnitNames();
				var isdel = 0;
				if(names==""){
					isdel = 1
				}
				var data = {
		 				groupId:$('#showGroupId').val(),
		 				names:	names,
		 				isdel: isdel
		 			};
				 $.ajax({
					url : "/enjoyhisfy/client/groupmanager/save.json",
		 			type:'POST',
		 			async:false,
		 			dataType:'json',
		 			data:data,
		 			success:function(data,textStatus,XMLHttpRequest){
		 				if(data.success){
		 					alert("添加成功",hideModel);
		 					//$('#showGroup').modal('hide');
		 				}else{
		 					alert("添加失败");
		 				}
		 			}
		 		});  
			}
			hideModel = function(){
				$('#showGroup').modal('hide');
			}
			function getUnitNames(){ 
		 		var chk_value =[];
		 		var chk_val = '';
		 		var i = 1;
		 		$('input[name="checkName"]:checked').each(function(){
		 			if(i>1){
		 				chk_val += ",";
		 			}
		 			chk_value.push($(this).val());
		 			chk_val += $(this).val();
		 			i++;
		 		});
		 		return chk_val;
		 	} 
		</script>
	</body>
</html>