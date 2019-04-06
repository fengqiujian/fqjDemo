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
 <style type="text/css"> 
#search{ 
text-align: center;
position:relative;
}
.autocomplete{
border: 1px solid #9ACCFB;
background-color: white;
text-align: left;
z-index: 9999;
}
.autocomplete li{
list-style-type: none;

}
.clickable {
cursor: default;
}
.highlight {
background-color: #9ACCFB;
}

 *{
            margin: 0;
            padding: 0;
            list-style: none;
        }
        .tab{
            margin: 10px auto;
            width: 80%;
            background: #e3e3e3;
            padding: 10px;
            position: relative;
            overflow: hidden;
        }
        .search{
            margin-top: 10px;
            margin-left: 15px;
        }
        .search_title{
            margin-right: 5px;
            vertical-align: middle;
        }
        .title_disposal{
            position: absolute;
            top: 21px;
            left: 41%;
            color:#9c99b7;
        }
        .list_disposal{
            margin-top: 5px;
            width: 39%;
        }
        .list_disposal,.list1,.list2,.list3{
            float: left;
            border: 1px solid #838791;
        }
        .list1,.list2,.list3{
            width: 100px;
            height: 270px;
            overflow-x: hidden;
            overflow-y: scroll;
            background: white;

        }
        .list3{
            width: 130px;
        }
        .list_disposal li{
            cursor: pointer;
            width: 100%;
            height: 20px;
            line-height: 20px;
        }
        .list_disposal li:hover{
            background: blue;
            color: #ffffff;
        }
        .table_disposal{
            border: 1px solid #838791 ;
            height: 280px;
            width: 60%;
            margin-top: 5px;
            margin-left: 340px;
        }
        .table_disposal th{
            height: 39px;
            border-right: 1px solid #838791 ;
            border-bottom: 1px solid #838791 ;
        }
        .table_disposal tbody tr td{
            background: white;
            text-align: center;
            height: 20px;
            line-height: 20px;
        }
        .button_disposal{
            margin-top: 10px;
            margin-left: 40%;
        }
        .button_disposal button{
            width: 72px;
            height: 25px;
        }
</style>
<script src="../../js/jquery-2.1.4.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script src="../../js/bootstrap-table.min.js"></script>
<script src="../../js/bootstrap-table-zh-CN.min.js"></script>
<script src="../../assets/js/bootbox.js"></script>
	<script src="../../assets/js/alert.js"></script>
<script src="../../pagejs/client/patient/disposalItem.js"></script>
<script src="../../pagejs/client/patient/disposalItemsearch.js"></script>
</head>
<body>
	<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<div class="container-fluid" style="margin-top:15px;">
			   			<div class="row">
			   				<div class="col-sm-2" style="line-height: 30px;">
			   					<a class="text-dection" href="/enjoyhisfy/client/register/register_index.htm">返回 /处置项目</a>						
			   				</div>	
			   				<!-- <div class="col-sm-1">
			   					<span class="btn-block">搜索处置</span>
			   				</div>	
			   				<div class="col-sm-3" id = "search">
			   					<input class="form-control" type="text" id="search-text" name="search-text" />
			   				</div>	 -->
			   				<div class="col-sm-2">
								<%-- <button type="button" class="btn btn-block btn-success"><a href="/enjoyhisfy/client/case/case_record_view.htm?pid=${patientId}" style="color: #FFFFFF;">病历记录</a></button> --%>
								<a type="button" href="/enjoyhisfy/client/case/case_record_view.htm?pid=${patientId}" class="btn btn-block btn-success">病历记录</a> 
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
		   			<input type="hidden" id="zdzk" value="${zk}"/>		   			
		   			<br class="clear" />		   			
		   			<!--table START	   			
   					<div class="col-sm-2 allborder addoverflow">		   			 	
				    	<div class="row" id="">
				    		<ul class="nav nav-pills nav-stacked bigtabbtn" id="his_service_item">
							</ul>
				    	</div>				    						    
		   			</div>-->	
		   			 <div class="col-sm-12 tabbox">
		   			 	<div id="myTabContent" class="tab-content bigtabcon">
		   			 		<form  class="form-horizontal" role="form">
		   			 			<table class="table table-bordered"  style="word-break:break-all; word-wrap:break-all;">						  
								   <thead>
								      <tr>
								         <th style="width: 10% ;text-align:center;line-height: 100%" >牙位</th>
								         <th style="width: 15% ;text-align:center;">处置名称</th>
								         <th style="width: 10% ;text-align:center;">单价</th>
								         <th style="width: 10% ;text-align:center;">单位</th>
								         <th style="width: 2.5%; border-left:0;border-right:0;" ></th>
								         <th style="width: 4%;border-left:0;border-right:0; ;text-align:center;">数量</th>
								         <th style="width: 2.5%;border-left:0;border-right:0; "></th>
								         <th style="width: 10% ;text-align:center;">金额</th>							         
								         <th style="width: 5% ;text-align:center;">操作</th>	
								      </tr>
								   </thead>
								   <tbody id="firstList">
								   </tbody>
								</table>
								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
              				 		添加牙位
            					</button>
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
								      <label  class="col-sm-4 control-label">权限金额</label>
								      <div class="col-sm-7">
								      	<i style="position: absolute;left: 0;bottom: 5px;">￥</i>
								        <input  type="text" class="form-control" id="firstname2" 
                                         placeholder=""  onfocus="if (value =='0.00'){value=''}"
                                          onchange="jshj()" value="0.00" onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
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
								    <div class="col-sm-6">
								    	<button type="button" class="btn btn-block btn-success" onclick="clearMainList()">清空</button>
								    </div>
								     <div class="col-sm-6">
								    	<button type="button" class="btn btn-block btn-success" onclick="subTable()">完成处置</button>
								    </div>								    
								</div>
								
		   			 		</form>						   
		   			    </div>		   							
				    </div>		   						   				   			
			    </div>
		    </div>	
		</div>		
		<!--选择处置项 模态框（Modal） -->
		<div class="modal bs-example-modal-lg" id="secendFl" tabindex="-1" role="dialog" aria-labelledby="mylargeModalLabel" aria-hidden="true">
        	<div class="modal-dialog modal-lg">
        		<div class="modal-content">
        			<div class="col-sm-12" style="z-index: 100;margin-bottom: 10px;">				 								   					   			
			   			<div class="col-sm-12">		
		   					<div class="container-fluid text-center">
		   					
		   					<button type="button" class="close"
              					 data-dismiss="modal" aria-hidden="true">
                  					&times;
            				</button>
            				<div style="float: left; margin-top: 8px" id = "search">
			   					<span class="search_title">搜索设置</span>
			   					<input  type="text" id="search-text" name="search-text" placeholder="名称、首字母" />   				
		   					</div>
		   					<h5>为(<span style="display: none;" id="showTooth"></span><span id="showTooth2"></span>)开处置&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h5>
		   					</div>
		   					<div class="col-sm-2 allborder addoverflow"">	
						    	<div class="row" id="ajaxtab1">
						    		<ul class="nav nav-pills nav-stacked tabbtn" id="his_service_item">
						    		
									</ul>
						    	</div>				    						    
				   			 </div>
		   					<div class="col-sm-2 allborder addoverflow" style="padding-left: 5px">	
						    	<div class="row" id="ajaxtab2">
						    		<ul class="nav nav-pills nav-stacked tabbtn" id="his_service_item_fl">
						    		
									</ul>
						    	</div>				    						    
				   			 </div>
		   					<div class="col-sm-2 allborder addoverflow" style="padding-left: 5px">	
						    	<div class="row" id="ajaxtab3">
						    		<ul class="nav nav-pills nav-stacked tabbtn" id="his_service_item_xm">
						    		
									</ul>
						    	</div>				    						    
				   			 </div>
				   			 
				   			<div class="col-sm-6 tabbox" >
				   			 	<div id="myTabContent" class="tab-content tabcon" style="width: 100%; height:400px;overflow: auto;">
								   <table id="thirdTable" class="table table-bordered">
								   		<tr>
								         <th style="text-align:center;" >处置名称</th>
								         <th style="text-align:center;">单价</th>
								         <th style="text-align:center;">单位</th>
								         <th style="text-align:center;">操作</th>	
								      </tr>
								   <tbody id="thirdList">
								   </tbody>
								   </table>
				   			    </div>		   							
						    </div>	
						   
			   			</div>		   			
			        </div>
			         <div class="modal-content">
	        			<div class="modal-footer">
	        			 <!-- data-dismiss="modal"-->
	        			<input type="hidden" id="secondChoos">
			            <button type="button" class="btn btn-default" onclick="clearThirdTable()">清空
			            </button>	
			            <button type="button" class="btn btn-primary" onclick="subThirdTable()">
              				 完成
            			</button>		            
			         </div> 
        		</div>       	
        		</div>
        	</div>
		</div> 
		<!--选择处置项 模态框（Modal） -->
		<!-- 牙位选择模态框（Modal） -->
		<div class="modal  fade" id="myModal" tabindex="-1" role="dialog" 
		   aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
			    <div class="modal-content">
			        <div class="container-fluid">
						<div class="col-sm-12 addbg-color middle" style="background-color: #dbe0e4; margin-top: 10px;padding-bottom:10px;">
							<div class="row">
								<div class="col-sm-12 text-center">
									<h4>选择牙位</h4>									
								</div>
								<div class="col-sm-12 text-center">
									<div class="col-md-12 ">											
												<div class="col-sm-1 " style=" margin-right: 5px;">
													<div class="row">
													</div>																														
												</div>
												<div class="col-sm-3 " style=" margin-right: 5px;">
													<div class="row">
														<button id="cy" type="button" class="toothbtn btn btn-block btn-success">恒牙</button>
													</div>																														
												</div>
												<div class="col-sm-3 " >
													<div class="row">
													</div>																														
												</div>
												<div class="col-sm-3 " >
													<div class="row">
														<button id="ry" type="button" class="toothbtn btn btn-block btn-success">乳牙</button>		
													</div>																																		
												</div>															
												<div class="col-sm-2 " >
													<div class="row">
													</div>																																		
												</div>															
											</div>								
								</div>
								<div class="kong"></div>
								<div class="col-sm-11 middle allborder clear addzoom" style="background-color: #FFFFFF;padding: 10px 0;">
									<div class="row">
										<div class="col-sm-11 middle allborder">
										  <div class="row" id="yw">
										  	<div class="col-sm-6  border-right add-border-btn" style="border-bottom: 1px solid black;">	
												<div class="row row-xs-1 upaddbg">
													<div class="col-xs-1 up" data-value="18">18</div>
													<div class="col-xs-1 up" data-value="17">17</div>
													<div class="col-xs-1 up" data-value="16">16</div>
													<div class="col-xs-1 up" data-value="15">15</div>
													<div class="col-xs-1 up" data-value="14">14</div>
													<div class="col-xs-1 up" data-value="13">13</div>
													<div class="col-xs-1 up" data-value="12">12</div>
													<div class="col-xs-1 up" data-value="11">11</div>
												</div>							  							 
										    </div>	
										    <div class="col-sm-6 " style="border-bottom: 1px solid black;">	
												<div class="row row-xs-1 upaddbg">
													<div class="col-xs-1 up" data-value="21" style="margin-left:2px;">21</div>
													<div class="col-xs-1 up" data-value="22">22</div>
													<div class="col-xs-1 up" data-value="23">23</div>
													<div class="col-xs-1 up" data-value="24">24</div>
													<div class="col-xs-1 up" data-value="25">25</div>
													<div class="col-xs-1 up" data-value="26">26</div>
													<div class="col-xs-1 up" data-value="27">27</div>
													<div class="col-xs-1 up" data-value="28">28</div>
												</div>							  							 
										    </div>
										    <div class="col-sm-6  border-right add-border-btm">	
												<div class="row row-xs-1 downaddbg">
													<div class="col-xs-1 down" data-value="48">48</div>
													<div class="col-xs-1 down" data-value="47">47</div>
													<div class="col-xs-1 down" data-value="46">46</div>
													<div class="col-xs-1 down" data-value="45">45</div>
													<div class="col-xs-1 down" data-value="44">44</div>
													<div class="col-xs-1 down" data-value="43">43</div>
													<div class="col-xs-1 down" data-value="42">42</div>
													<div class="col-xs-1 down" data-value="41">41</div>
												</div>				  							 
										    </div>	
										   <div class="col-sm-6 ">	
												<div class="row row-xs-1 downaddbg">
													<div class="col-xs-1 down" data-value="31" style="margin-left:2px;">31</div>
													<div class="col-xs-1 down" data-value="32">32</div>
													<div class="col-xs-1 down" data-value="33">33</div>
													<div class="col-xs-1 down" data-value="34">34</div>
													<div class="col-xs-1 down" data-value="35">35</div>
													<div class="col-xs-1 down" data-value="36">36</div>
													<div class="col-xs-1 down" data-value="37">37</div>
													<div class="col-xs-1 down" data-value="38">38</div>
												</div>								 
										   </div>	
										  </div>
									    </div>						
									</div>						
								</div>
								<div class="kong"></div>
								<div class="col-md-12 ">
									<div class="">
										<div class="col-sm-12">
										<div class="row">								
											<div class="col-md-8 ">											
												<div class="col-sm-3 " style="margin-left:5px;">
														<div class="row">
															<button id="all" type="button" class="toothbtn btn btn-block btn-success">全口</button>
														</div>
													</div>
													<div class="col-sm-3 " style="margin-left:5px;">
														<div class="row">
															<button id="half-up" type="button" class="toothbtn btn btn-block btn-success">上半口</button>
														</div>
													</div>
													<div class="col-sm-3 " style="margin-left:5px;">
														<div class="row">
															<button id="half-down" type="button" class="toothbtn btn btn-block btn-success">下半口</button>
														</div>
													</div>															
											</div>
											<div class="col-md-4 ">
												<div class="row">
													<div class="col-sm-5" style="margin-left:5px;display: block;" id="show_Choice_tooth">
														<div class="row">
															<button type="button" id="Choice_tooth" class="btn btn-block btn-success" data-dismiss="modal">确定</button>
														</div>
													</div>
													<div class="col-sm-5" style="margin-left:5px;display: none;" id="show_update_tooth" >
														<div class="row">
															<button type="button" id="update_tooth" class="btn btn-block btn-success" data-dismiss="modal">确定</button>
														</div>
													</div>
													<div class="col-sm-5" style="margin-left:5px;">
														<div class="row">
															<button type="button" id="blank" class="btn btn-block btn-success">清空</button>
														</div>
													</div>
												</div>												
											</div>
										</div>
									</div>
									</div>
								</div>
							</div>
						</div>
					</div>
			        <div class="modal-footer">		        
			        </div>
			    </div><!-- /.modal-content -->
			</div>
		</div><!-- /.modal -->
		<script>
			//清空选择牙齿
			$("#blank").click(function(){
				$(".col-sm-6 .col-xs-1").css("background","");
				$(".col-sm-6 .col-xs-1").removeClass("myselected");
			});
		</script>
		
</body>
</html>