<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>处置项目</title>
 <link href="../../css/bootstrap.min.css" rel="stylesheet" />
 <link href="../../css/bootstrap-table.min.css" rel="stylesheet" />
 <link rel="stylesheet" type="text/css" href="../../css/disposal.css"/>
<script src="../../js/jquery-2.1.4.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script src="../../js/bootstrap-table.min.js"></script>
<script src="../../js/bootstrap-table-zh-CN.min.js"></script>
<script src="../../assets/js/bootbox.js"></script>
	<script src="../../assets/js/alert.js"></script>
<script src="../../pagejs/client/patient/pendingDisposal.js"></script>
</head>
<body>
	<div class="container">
			<div class="row">
				<div class="col-sm-11">
					<div class="container-fluid ">
			   			<div class="row">
			   				<div class="col-sm-2" style="line-height: 30px;">
			   					<a class="text-dection" href="/enjoyhisfy/client/register/register_index.htm">返回 /处置项目</a>						
			   				</div>	
			   				<div class="col-sm-2">
								<a class="btn btn-block btn-success" href="/enjoyhisfy/client/case/case_record_view.htm?pid=${patientId}" style="color: #FFFFFF;">病历记录</a>
							</div>
			   			</div>	   			
			   		</div>
		   		<hr />		   		
					<div class="col-sm-3">
						<div class="frm-group">
							<span>患者姓名：</span><span id="patName"></span>
						</div>		   				
		   		    </div>	   			
		   			<div class="col-sm-3">
		   				<div class="form-group">
		   					<span>手机号码：</span><span id="mobile"></span>
		   				</div>			   				
		   			</div>	
		   			<div class="col-sm-3">
		   				<div class="form-group">
		   					<span>病历编号：</span><span id="patNo"></span>
		   				</div>			   				
		   			</div>	
		   			<input type="hidden" id="regId" value="${id}"/>		   			
		   			<input type="hidden" id="patientId" value="${patientId}"/>		   			
		   			<br class="clear" />		   			
		   			<!--table START-->		   			
   					
		   			 <div class="col-sm-12 tabbox">
		   			 	<div id="myTabContent" class="tab-content bigtabcon">
		   			 		<form  class="form-horizontal" role="form">
		   			 			<table class="table table-bordered"  style="word-break:break-all; word-wrap:break-all;">						  
								   <thead>
								      <tr>
								         <th style="width: 15% ">牙位</th>
								         <th style="width: 10% ">一级分类</th>
								         <th style="width: 10% ">二级分类</th>
								         <th style="width: 10% ">处置名称</th>
								         <th style="width: 10% ">单价</th>
								         <th style="width: 5% ">单位</th>
								         <th style="width: 10% ">数量</th>
								         <th style="width: 10% ">金额</th>							         
								      </tr>
								   </thead>
								   <tbody id="firstList">
								   </tbody>
								</table>
								<div class="col-sm-6 pull-right">
									<div class="form-group">
								      <label  class="col-sm-4 control-label">处置小计</label>
								      <div class="col-sm-7">
								      	<i style="position: absolute;left: 0;bottom: 5px;">￥</i>
								        <input style="border: 0;background-color: white;" type="text" class="form-control" id="firstname" 
                                         placeholder="" readonly="readonly" value="0.00">
								      </div>
								    </div>
								    <div class="form-group" style="display: none;">
								      <label  class="col-sm-4 control-label">折扣金额</label>
								      <div class="col-sm-7">
								      	<i style="position: absolute;left: 0;bottom: 5px;">￥</i>
								        <input style="border: 0;background-color: white;" type="text" class="form-control" id="firstname2" 
                                         placeholder="" readonly="readonly" value="0">
								      </div>
								    </div>
								    <div class="form-group">
								      <label  class="col-sm-4 control-label">合计费用</label>
								      <div class="col-sm-7">
								      	<i style="position: absolute;left: 0;bottom:5px;">￥</i>
								        <input  style="border: 0;background-color: white;"   type="text" class="form-control" id="firstname3" 
                                         placeholder="" readonly="readonly"  value="0.00">
								      </div>
								    </div>
								</div>
		   			 		</form>						   
		   			    </div>		   							
				    </div>		   						   				   			
			    </div>
		    </div>	
		</div>
</body>
</html>