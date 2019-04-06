<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>患者资料</title>
		<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${path}/css/his.css"/>
		<link rel="stylesheet" type="text/css" href="${path}/laydate/need/laydate.css"/>
	</head>
	<body>
		<input type="hidden" value="${userType}" id="userType"/>
		<input type="hidden" value="${uid}" id="uid"/>

		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<div class="container-fluid " style="margin-top: 15px;">
			   			<div class="row">
			   				<div class="col-sm-2" style="line-height: 30px;">
			   					<%--<a class="text-dection" href="/enjoyhisfy/client/patient/patient_manager.htm">返回 /</a>--%>
								<%--<a class="text-dection" href="#">新建患者</a>		--%>
			   				</div>
			   			</div>	   			
			   		</div>
			   		<hr />
			   		<form id="patient-form" class="form-horizontal" role="form" action="/enjoyhisfy/client/patient/save" method="post">
			   			<div class="col-sm-4">
			   				<div class="form-group">
						      <label  class="col-sm-4 control-label">患者姓名<sup style="color: red;">*</sup></label>
						      <div class="col-sm-8">
						         <input type="text" class="form-control" id="patName" name="patName" placeholder="请输入名字" maxlength=50>
						      </div>
						    </div>
			   			</div>
			   			<div class="col-sm-4">
			   				<div class="form-group">
						      <label  class="col-sm-4 control-label">病历编号</label>
						      <div class="col-sm-8">
						         <input type="text" class="form-control" id="patNo" name="patNo" placeholder="自动生成" readonly="readonly">
						      </div>
						    </div>
			   			</div>
			   			<div class="col-sm-4">
			   				<div class="form-group">
						      <label  class="col-sm-4 control-label">患者性别<sup style="color: red;">*</sup></label>
						      <div class="col-sm-8">
						        <select id="userSex" name="userSex" class="form-control">
						           <option value="男">男</option>
						         <option value="女">女</option>						      
						        </select>
						      </div>
						    </div>
			   			</div>
			   			<div class="col-sm-4">
			   				<div class="form-group">
						      <label  class="col-sm-4 control-label">身份证号</label>
						      <div class="col-sm-8">
						         <input id="persid" name="persid" type="text" class="form-control" placeholder="请输入身份证号" maxlength=45>
						      </div>
						    </div>
			   			</div>
			   			<div class="col-sm-4">
			   				<div class="form-group">
						      <label  class="col-sm-4 control-label">年龄</label>
						      <div class="col-sm-8">
						         <input id="age" name="age" type="text" class="form-control" placeholder="请输入年龄" maxlength=11>
						      </div>
						    </div>
			   			</div>
			   			<div class="col-sm-4">
			   				<div class="form-group">
						      <label  class="col-sm-4 control-label">出生年月</label>
						      <div class="col-sm-8">
						         <input type="text" class="form-control" id="birthday" name="birthday" placeholder="">
						      </div>
						    </div>
			   			</div>
			   			<div class="col-sm-4">
			   				<div class="form-group">
						      <label  class="col-sm-4 control-label">手机号码<sup style="color: red;">*</sup></label>
						      <div class="col-sm-8">
						         <input type="text" class="form-control" id="mobile" name="mobile" placeholder="请输入手机号码" maxlength=15>
						      </div>
						    </div>
			   			</div>
			   			<div class="col-sm-4">
			   				<div class="form-group">
						      <label  class="col-sm-4 control-label">联系电话</label>
						      <div class="col-sm-8">
						         <input id="tel" name="tel" type="text" class="form-control"  placeholder="请输入联系电话 " maxlength=45>
						      </div>
						    </div>
			   			</div>
			   			<div class="col-sm-4">
			   				<div class="form-group">
						      <label  class="col-sm-4 control-label">电子邮箱</label>
						      <div class="col-sm-8">
						         <input id="email" name="email" type="text" class="form-control" placeholder="请输入电子邮箱" maxlength=45>
						      </div>
						    </div>
			   			</div>
			   			<br class="clear" />
			   			<div class="col-sm-4">
			   				<div class="form-group">
						      <label  class="col-sm-4 control-label">患者来源<sup style="color: red;">*</sup></label>
						      <div class="col-sm-8">
								  <select class="form-control" id="source" name="source" required="required" data-id="${register.deptCode }"
										  url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_dict&&column1=dict_name&&column2=dict_name&&sqlstr={'where':'dict_type=\'HZLY\''}">
								  </select>
						      </div>
						    </div>
			   			</div>
			   			<div class="col-sm-4">
			   				<div class="form-group">
						      <label  class="col-sm-4 control-label">介绍人</label>
						      <div class="col-sm-8">
						         <input type="text" class="form-control" id="introducer" name="introducer" placeholder="" maxlength=20>
						      </div>
						    </div>
			   			</div>
			   			<div class="col-sm-4">
			   				<div class="form-group">
						      <label  class="col-sm-4 control-label">初诊医师<sup style="color: red;">*</sup></label>
						      <div class="col-sm-8">
								  <c:choose>
									  <c:when test="${userType==1}">
										  <select class="form-control" id="dentistId" name="maindocId" required="required" data-id="${uid}" url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_employee&&column1=id&&column2=employee_name&&sqlstr={'where':'id=${uid}'}">
										  </select>
									  </c:when>
									  <c:otherwise>
										  <select class="form-control" id="dentistId" name="maindocId" required="required" data-id="${register.dentistId }" url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_employee&&column1=id&&column2=employee_name&&sqlstr={'where':'user_type=1 and is_show=1'}">
										  </select>
									  </c:otherwise>
								  </c:choose>

							  </div>
						    </div>
			   			</div>
			   			<div class="col-sm-9">
			   				<div class="form-group">
						      <label  class="col-sm-2 control-label" style="text-align: center">初诊询问</label>
						      <div class="col-sm-10 ">
						            <label class="checkbox-inline">
								      <input type="checkbox" id="inlineCheckbox2" name="newlyAsk" value="肝炎"> 肝炎
								    </label>
								    <label class="checkbox-inline">
								      <input type="checkbox" id="inlineCheckbox3" name="newlyAsk" value="结核"> 结核
								    </label>
								    <label class="checkbox-inline">
								      <input type="checkbox" id="inlineCheckbox2" name="newlyAsk" value="糖尿病"> 糖尿病
								    </label>
								    <label class="checkbox-inline">
								      <input type="checkbox" id="inlineCheckbox3" name="newlyAsk" value="高血压"> 高血压
								    </label>
								    <label class="checkbox-inline">
								      <input type="checkbox" id="inlineCheckbox2" name="newlyAsk" value="冠心病"> 冠心病
								    </label>
								    <label class="checkbox-inline">
								      <input type="checkbox" id="inlineCheckbox3" name="newlyAsk" value="风湿病"> 风湿病
								    </label>
								    <br class="clear" />
								    <label class="checkbox-inline pull-left" >
								      <input type="checkbox" id="inlineCheckbox2" name="newlyAsk" value="癫痫"> 癫痫
								    </label>
								   <div class="col-sm-10 ">
							         <label  class="col-sm-2 control-label">其他</label>
								      <div class="col-sm-10">
								         <input type="text" class="form-control" id="" name="newlyAsk" placeholder="" style="width: 98%">
								      </div>
							      </div>
						      </div>
						    </div>
			   			</div>
						<div class="col-sm-8">
							<div class="form-group">
								<label  class="col-sm-2 control-label">地址</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="address" name="address" placeholder="" maxlength=255>
								</div>
							</div>
						</div>
			   			<div class="col-sm-8">
			   				<div class="form-group">
						      <label  class="col-sm-2 control-label">过敏史</label>
						      <div class="col-sm-10">
						         <input type="text" class="form-control" id="allergicHis" name="allergicHis" placeholder="" maxlength=255>
						      </div>
						    </div>
			   			</div>
			   			<div class="col-sm-8">
			   				<div class="form-group">
						      <label  class="col-sm-2 control-label">备注</label>
						      <div class="col-sm-10">
						         <textarea id="remark" name="remark" class="form-control" rows="3" maxlength=255></textarea>
						      </div>
						    </div>
			   			</div>
			   			<br class="clear" />			   			
			   			<div class="col-sm-2">
			   				<button id="add-btn" type="button"  class="btn btn-block btn-success">保存资料</button>
			   			</div>
			   		</form>
				</div>
			</div>
		</div>		   		   	
		<script src="${path}/js/jquery-2.1.4.min.js"></script>
		<script src="${path}/js/bootstrap.min.js"></script>	 
		<script src="../../assets/js/bootbox.js"></script>
	<script src="../../assets/js/alert.js"></script>
		<script src="${path}/js/jquery/jquery.form.js"></script>
		<script src="${path}/laydate/laydate.js"></script>
		<script>
		
			$(function(){
				
				//动态获取select的option
				 $("select").each(function(i,n){
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
				            	tempAjax += "<option value=''>请选择</option>";
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
				var cb = function(){
		                    window.parent.$('#patient-table').bootstrapTable('refresh');
		                    window.parent.$('#modal').modal("hide");
				}
				$("#add-btn").click(function () {
					$(this).attr("disabled",true);
		            $("#patient-form").ajaxSubmit(function (result) {
		                if(result.returndata){
		                    alert("操作成功！",cb);
//		                    window.parent.$('#patient-table').bootstrapTable('refresh');
//		                    window.parent.$('#modal').modal("hide");
		                }else{
							$("#add-btn").attr("disabled",false);
							alert("操作失败，请填写必填项( * 为必填项)！");
		                }
		            });
		            return false;
		        });
				
				//年龄与日期联动
				$('#age').keyup(function(){
					show();
				});
			});
			
			//出生年月选择	
			;!function(){
			laydate.skin('molv');
			laydate({
			    elem: '#birthday',
			    event: 'click', //触发事件
		        format: 'YYYY-MM-DD', //日期格式
		        //istime: true, //是否开启时间选择
		        isclear: true, //是否显示清空
		        istoday: true, //是否显示今天
		        issure: true, //是否显示确认
		        festival: true, //是否显示节日
		        min: '1900-01-01 00:00:00', //最小日期
		        max: '2099-12-31 23:59:59', //最大日期
		        start: '1970-6-15 23:00:00',    //开始日期
		        fixed: false, //是否固定在可视区域
		        zIndex: 99999999, //css z-index 
				    choose: function(dates){ //选择好日期的回调
				     var age=$("#age");//年龄		        
			         var mydate = new Date();//获得当前年份 2016
			         age.val(mydate.getFullYear()-dates.substr(0,4));//2016-出生年份
				    }
			   })
			
			}();
			
			//获取当前日期
			function show(){
			   var age=$("#age");//年龄
			   var mydate = new Date();//
			   var str = "" + mydate.getFullYear()-age.val() + "-";
			   str += "01" + "-";
			   str += "01" + "";
	// 		   return str;
				$("#birthday").val(str);
			}
		</script>
	</body>
</html>
