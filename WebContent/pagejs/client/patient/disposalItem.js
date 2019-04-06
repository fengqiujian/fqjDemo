$(function(){
//	load();
	loadFirst();
	loadDetailList();
	
	var list=$(".bigtabbtn li");
	list.click(function(){
		$(this).addClass("active").siblings().removeClass("active");
	});		
	
	//全口
	$("#all").click(function(){					
	$(".col-sm-6 .col-xs-1").css("background","gray");		
	$(".col-sm-6 .col-xs-1").addClass("myselected");//给选中的项添加class myselected;	
	});
	//上半口
	$("#half-up").click(function(){	
		$(".col-xs-1").filter(".down").css("background","");	
		$(".upaddbg .col-xs-1").css("background","gray");
		$(".upaddbg .col-xs-1").addClass("myselected");//给选中的项添加class myselected;
		$(".downaddbg .col-xs-1").removeClass("myselected");//移除下半口的class
	});
	//下半口
	$("#half-down").click(function(){
		$(".col-xs-1").filter(".up").css("background","")
		$(".downaddbg .col-xs-1").css("background","gray");
		$(".downaddbg .col-xs-1").addClass("myselected");
		$(".upaddbg .col-xs-1").removeClass("myselected");//移除上半口的class
	});	
	
	//选择单个牙齿
	$(".col-sm-6 .col-xs-1").click(function(){
		$(this).css("background","gray");
		$(this).addClass("myselected");
	});
	
	$("#cy").click(function(){
		$("#yw").html(cy);
	});
	$("#ry").click(function(){
		$("#yw").html(ry);
	});
	$("#Choice_tooth").click(function(){
		var list=$(".col-sm-6 .col-xs-1");//
		var num = "";//定义一个变量
		$($(".myselected")).each(function(i){
			console.info($(this).attr('data-value'));
			num = num+$(this).attr('data-value')+",";//获取牙齿的value值
			$(this).removeClass('myselected');//移除class 清除td的值
		});
//		console.info(num.substr(0,num.length-1));
//			console.info(list);
		list.css("background","");//清空背景色
		var toolsStr = num.substr(0,num.length-1);
		var newt = toolsStr.replace(/,/g, "_");
		if(toolsStr==""){
			return;
		}else{
			var newtoolsStrs = getCHTooths(toolsStr);
			var newLine = "<tr class='tools_"+toolsStr+"  tools_"+newt+"' align='center'><td style='line-height: 100%' onclick='changeTooth(\""+toolsStr+"\",false)'>"+newtoolsStrs+"</td><td  style='display:none;'>"+toolsStr+"</td><th style='text-align:center;' colspan='8'><a href='#' onclick=\"openModel('"+toolsStr+"')\">添加处置项</a></th></tr>";
			$("#firstList").append(newLine);
		}
//		$(doc).text(toolsStr);//给表格项添加值
//		doc = null;
	});	
	$("#update_tooth").click(function(){
		var list=$(".col-sm-6 .col-xs-1");//
		var num = "";//定义一个变量
		$($(".myselected")).each(function(i){
			//console.info($(this).attr('data-value'));
			num = num+$(this).attr('data-value')+",";//获取牙齿的value值
			$(this).removeClass('myselected');//移除class 清除td的值
		});
		console.info(isChangeToBackstage);
		console.info(oldTooth);
//			console.info(list);
		list.css("background","");//清空背景色
		var toolsStr = num.substr(0,num.length-1);
		var newt = toolsStr.replace(/,/g, "_");
		if(toolsStr==""){
			return;
		}else if(isChangeToBackstage==false){
			var toolsClass = "tools_"+oldTooth.replace(/,/g, "_");
			$("."+toolsClass).remove();
			var newtoolsStrs = getCHTooths(toolsStr);
			var newLine = "<tr class='tools_"+toolsStr+" tools_"+newt+"' align='center'><td style='line-height: 100%' onclick='changeTooth(\""+toolsStr+"\",false)'>"+newtoolsStrs+"</td><td  style='display:none;'>"+toolsStr+"</td><th style='text-align:center;' colspan='8'><a href='#' onclick=\"openModel('"+toolsStr+"')\">添加处置项</a></th></tr>";
			$("#firstList").append(newLine);
		}else{
			$.ajax({
				url:"changeTooth.json",
				type:'POST',
				async:false,
				dataType:'json',
				data:{
					regId:$("#regId").val(),
					tooth:oldTooth,
					newtooth:toolsStr
				},
				success:function(data,textStatus,XMLHttpRequest){
					loadDetailList();
				}
			});
		}
//		$(doc).text(toolsStr);//给表格项添加值
//		doc = null;
	});	
                     
});
$(function() {
    $('#myModal').on('hidden.bs.modal',
    function() {
    	oldTooth = '';
    	isChangeToBackstage = '';
    	$("#show_update_tooth").css("display","none");
    	$("#show_Choice_tooth").css("display","block");
    })
});

function changeTooth(tooths,flag){
	oldTooth = tooths;
	isChangeToBackstage = flag
	$("#show_update_tooth").css("display","block");
	$("#show_Choice_tooth").css("display","none");
	$('#myModal').modal('show');
}

function openModel(toolsStr){
	$("#showTooth").html(toolsStr);
	$("#showTooth2").html(getCHTooths(toolsStr));
	getThirdList();
	$("#thirdTable").bootstrapTable('hideColumn', 'itemCode');
	$("#search-text").val("");
	$('#secendFl').modal({
		backdrop: 'static',
		keyboard: true
	})
}

function autoRowSpan(row,col)
{
var tb = document.getElementById("firstList");
var lastValue="";
var value="";
var pos=1;
var docse ;
var docses ;
for(var i=row;i<tb.rows.length;i++){
value = tb.rows[i].cells[col].innerText;
docses = tb.rows[i].cells[col];
value = $(docses).html();
if(lastValue == value){
tb.rows[i].deleteCell(col);
tb.rows[i-pos].cells[col].rowSpan = tb.rows[i-pos].cells[col].rowSpan+1;
pos++;
}else{
lastValue = value;
pos=1;
}
}
}

var oldTooth = '';
var isChangeToBackstage = '';
var cy = '<div class="col-sm-6  border-right add-border-btn" style="border-bottom: 1px solid black;">'+
'	<div class="row row-xs-1 upaddbg">                                                      '+
'		<div class="col-xs-1 up" data-value="18" onclick="onchangeToolth(this)">18</div>                                    '+
'		<div class="col-xs-1 up" data-value="17" onclick="onchangeToolth(this)">17</div>                                    '+
'		<div class="col-xs-1 up" data-value="16" onclick="onchangeToolth(this)">16</div>                                    '+
'		<div class="col-xs-1 up" data-value="15" onclick="onchangeToolth(this)">15</div>                                    '+
'		<div class="col-xs-1 up" data-value="14" onclick="onchangeToolth(this)">14</div>                                    '+
'		<div class="col-xs-1 up" data-value="13" onclick="onchangeToolth(this)">13</div>                                    '+
'		<div class="col-xs-1 up" data-value="12" onclick="onchangeToolth(this)">12</div>                                    '+
'		<div class="col-xs-1 up" data-value="11" onclick="onchangeToolth(this)">11</div>                                    '+
'	</div>							  							                            '+
'</div>	                                                                                    '+
'<div class="col-sm-6 " style="border-bottom: 1px solid black;">	                        '+
'	<div class="row row-xs-1 upaddbg">                                                      '+
'		<div class="col-xs-1 up" data-value="21" onclick="onchangeToolth(this)" style="margin-left:2px;">21</div>           '+
'		<div class="col-xs-1 up" data-value="22" onclick="onchangeToolth(this)">22</div>                                    '+
'		<div class="col-xs-1 up" data-value="23" onclick="onchangeToolth(this)">23</div>                                    '+
'		<div class="col-xs-1 up" data-value="24" onclick="onchangeToolth(this)">24</div>                                    '+
'		<div class="col-xs-1 up" data-value="25" onclick="onchangeToolth(this)">25</div>                                    '+
'		<div class="col-xs-1 up" data-value="26" onclick="onchangeToolth(this)">26</div>                                    '+
'		<div class="col-xs-1 up" data-value="27" onclick="onchangeToolth(this)">27</div>                                    '+
'		<div class="col-xs-1 up" data-value="28" onclick="onchangeToolth(this)">28</div>                                    '+
'	</div>							  							                            '+
'</div>                                                                                     '+
'<div class="col-sm-6  border-right add-border-btm">	                                    '+
'	<div class="row row-xs-1 downaddbg">                                                    '+
'		<div class="col-xs-1 down" data-value="48" onclick="onchangeToolth(this)">48</div>                                  '+
'		<div class="col-xs-1 down" data-value="47" onclick="onchangeToolth(this)">47</div>                                  '+
'		<div class="col-xs-1 down" data-value="46" onclick="onchangeToolth(this)">46</div>                                  '+
'		<div class="col-xs-1 down" data-value="45" onclick="onchangeToolth(this)">45</div>                                  '+
'		<div class="col-xs-1 down" data-value="44" onclick="onchangeToolth(this)">44</div>                                  '+
'		<div class="col-xs-1 down" data-value="43" onclick="onchangeToolth(this)">43</div>                                  '+
'		<div class="col-xs-1 down" data-value="42" onclick="onchangeToolth(this)">42</div>                                  '+
'		<div class="col-xs-1 down" data-value="41" onclick="onchangeToolth(this)">41</div>                                  '+
'	</div>				  							                                        '+
'</div>                                                                                     '+
'<div class="col-sm-6 ">	                                                                '+
'	<div class="row row-xs-1 downaddbg">                                                    '+
'		<div class="col-xs-1 down" data-value="31" onclick="onchangeToolth(this)" style="margin-left:2px;">31</div>         '+
'		<div class="col-xs-1 down" data-value="32" onclick="onchangeToolth(this)">32</div>                                  '+
'		<div class="col-xs-1 down" data-value="33" onclick="onchangeToolth(this)">33</div>                                  '+
'		<div class="col-xs-1 down" data-value="34" onclick="onchangeToolth(this)">34</div>                                  '+
'		<div class="col-xs-1 down" data-value="35" onclick="onchangeToolth(this)">35</div>                                  '+
'		<div class="col-xs-1 down" data-value="36" onclick="onchangeToolth(this)">36</div>                                  '+
'		<div class="col-xs-1 down" data-value="37" onclick="onchangeToolth(this)">37</div>                                  '+
'		<div class="col-xs-1 down" data-value="38" onclick="onchangeToolth(this)">38</div>                                  '+
'	</div>								                                                    '+
'</div>';

var ry = '<div class="col-sm-6  border-right add-border-btn" style="border-bottom: 1px solid black;">'+
'	<div class="row row-xs-1 upaddbg">                                                      '+
'		<div class="col-xs-1 up" data-value="55" onclick="onchangeToolth(this)">55</div>                                    '+
'		<div class="col-xs-1 up" data-value="54" onclick="onchangeToolth(this)">54</div>                                    '+
'		<div class="col-xs-1 up" data-value="53" onclick="onchangeToolth(this)">53</div>                                    '+
'		<div class="col-xs-1 up" data-value="52" onclick="onchangeToolth(this)">52</div>                                    '+
'		<div class="col-xs-1 up" data-value="51" onclick="onchangeToolth(this)">51</div>                                    '+
'	</div>							  							                            '+
'</div>	                                                                                    '+
'<div class="col-sm-6 " style="border-bottom: 1px solid black;">	                        '+
'	<div class="row row-xs-1 upaddbg">                                                      '+
'		<div class="col-xs-1 up" data-value="61" onclick="onchangeToolth(this)" style="margin-left:2px;">61</div>           '+
'		<div class="col-xs-1 up" data-value="62" onclick="onchangeToolth(this)">62</div>                                    '+
'		<div class="col-xs-1 up" data-value="63" onclick="onchangeToolth(this)">63</div>                                    '+
'		<div class="col-xs-1 up" data-value="64" onclick="onchangeToolth(this)">64</div>                                    '+
'		<div class="col-xs-1 up" data-value="65" onclick="onchangeToolth(this)">65</div>                                    '+
'	</div>							  							                            '+
'</div>                                                                                     '+
'<div class="col-sm-6  border-right add-border-btm">	                                    '+
'	<div class="row row-xs-1 downaddbg">                                                    '+
'		<div class="col-xs-1 down" data-value="85" onclick="onchangeToolth(this)">85</div>                                  '+
'		<div class="col-xs-1 down" data-value="84" onclick="onchangeToolth(this)">84</div>                                  '+
'		<div class="col-xs-1 down" data-value="83" onclick="onchangeToolth(this)">83</div>                                  '+
'		<div class="col-xs-1 down" data-value="82" onclick="onchangeToolth(this)">82</div>                                  '+
'		<div class="col-xs-1 down" data-value="81" onclick="onchangeToolth(this)">81</div>                                  '+
'	</div>				  							                                        '+
'</div>                                                                                     '+
'<div class="col-sm-6 ">	                                                                '+
'	<div class="row row-xs-1 downaddbg">                                                    '+
'		<div class="col-xs-1 down" data-value="71" onclick="onchangeToolth(this)" style="margin-left:2px;">71</div>         '+
'		<div class="col-xs-1 down" data-value="72" onclick="onchangeToolth(this)">72</div>                                  '+
'		<div class="col-xs-1 down" data-value="73" onclick="onchangeToolth(this)">73</div>                                  '+
'		<div class="col-xs-1 down" data-value="74" onclick="onchangeToolth(this)">74</div>                                  '+
'		<div class="col-xs-1 down" data-value="75" onclick="onchangeToolth(this)">75</div>                                  '+
'	</div>								                                                    '+
'</div>';


var doc;
var thirdList;

function onchangeToolth(docs){
	$(docs).css("background","gray");
	$(docs).addClass("myselected");
}

function loadDetailList(){
	$.ajax({
		url:"getHisStatementItemDetailList.json",
		type:'POST',
		async:false,
		dataType:'json',
		data:{
			regId:$("#regId").val()
		},
		success:function(data,textStatus,XMLHttpRequest){
			var tableJson = data.rows;
//			var firName = $("#his_service_item .active a").html();
//			var firCode = $("#his_service_item .active a").attr("url");
//		//	
////			<button type="button" class="btn btn-block add">+</button>
			var htmlText = "";
//			$(tableJson).each(function(){
//				var tools = '<td style="display:none;" >'+this.tooth+'</td>';	
//				htmlText += '<tr><td style="display:none;">'+this.itemId+'</td><td>'+this.itemName+'</td><td>'+this.itemNameFl+'</td>'+tools+'<td>'+this.itemNameXm+'</td><td style="display:none;">'+this.itemSubId+'</td><td>'+this.price.toFixed(2)+'</td>'+
//				'<td>'+this.unit+'</td><td style="border-left:0;border-right:0;"><button type="button" class="btn btn-block add" onclick="addNum(this)">+</button></td><td style="border-left:0;border-right:0;">'+this.qty+'</td><td style="border-left:0;border-right:0;"><button type="button" class="btn btn-block min"onclick="minNum(this)">-</button></td><td>'+this.itemAmount.toFixed(2)+'</td><th><a class="glyphicon glyphicon-trash" onclick="removeRow(this)"></a></th></tr>'               
//			});
//			$("#firstList").append(htmlText);
			$(tableJson).each(function(){
				var toothses = getCHTooths(this.tooth);
				htmlText += '<tr class="tools_'+this.tooth+'" align="center"><td  style="line-height: 100%"  onclick="changeTooth(\''+this.tooth+'\',true)">'+toothses+'</td><td style="display:none;">'+this.tooth+'</td><td style="display:none;">'+this.itemId+'</td><td style="display:none;">'+this.id+'</td><td>'+this.itemNameXm+'</td><td>'+this.price.toFixed(2)+'</td>'+
				'<td>'+this.unit+'</td><td style="border-left:0;border-right:0;"><button type="button" class="btn btn-block add" onclick="addNum(\''+this.id+'\',this)">+</button></td><td style="border-left:0;border-right:0;">'+this.qty+'</td><td style="border-left:0;border-right:0;"><button type="button" class="btn btn-block min" onclick="minNum(\''+this.id+'\',this)">-</button></td><td>'+this.itemAmount.toFixed(2)+'</td>'+
				'<th><a onclick="editLSCZX(\''+this.tooth+'\')">修改</a> <a onclick="deleteLSCZX(\''+this.tooth+'\')">删除</a></th></tr>'               
//				htmlText += '<tr><td style="display:none;">'+this.itemId+'</td><td>'+this.itemName+'</td><td>'+this.itemNameFl+'</td>'+tools+'<td>'+this.itemNameXm+'</td><td style="display:none;">'+this.itemSubId+'</td><td>'+this.price.toFixed(2)+'</td>'+
//				'<td>'+this.unit+'</td><td style="border-left:0;border-right:0;"><button type="button" class="btn btn-block add" onclick="addNum(this)">+</button></td><td style="border-left:0;border-right:0;">'+this.qty+'</td><td style="border-left:0;border-right:0;"><button type="button" class="btn btn-block min"onclick="minNum(this)">-</button></td><td>'+this.itemAmount.toFixed(2)+'</td><th><a class="glyphicon glyphicon-trash" onclick="removeRow(this)"></a></th></tr>'               
			});
			$("#firstList").html(htmlText);
			autoRowSpan(0,11);
			autoRowSpan(0,0);
			sumZje();
		}
	});
}

function getCHTooths(tooth){
	var newToothes = "";
	if(tooth=="18,17,16,15,14,13,12,11,21,22,23,24,25,26,27,28,48,47,46,45,44,43,42,41,31,32,33,34,35,36,37,38"){
		newToothes = "全口牙(恒牙)";
	}else if(tooth=="18,17,16,15,14,13,12,11,21,22,23,24,25,26,27,28"){
		newToothes = "上半口(恒牙)";
	}else if(tooth=="48,47,46,45,44,43,42,41,31,32,33,34,35,36,37,38"){
		newToothes = "下半口(恒牙)";
	}else if(tooth=="55,54,53,52,51,61,62,63,64,65,85,84,83,82,81,71,72,73,74,75"){
		newToothes = "全口牙(乳牙)";
	}else if(tooth=="55,54,53,52,51,61,62,63,64,65"){
		newToothes = "上半口(乳牙)";
	}else if(tooth=="85,84,83,82,81,71,72,73,74,75"){
		newToothes = "下半口(乳牙)";
	}else{
		newToothes = tooth;
	}
	return newToothes;
}

function editLSCZX(tooth){
	clearThirdTable();
	$("#showTooth").html(tooth);
	$("#showTooth2").html(getCHTooths(tooth));
	$.ajax({
		url:"getHisStatementItemDetailListLS.json",
		type:'POST',
		async:false,
		dataType:'json',
		data:{
			regId:$("#regId").val(),
			tooth:tooth
		},
		success:function(data,textStatus,XMLHttpRequest){
			var datas = data.rows;
			var htmlText = "";
			$(datas).each(function(){
				htmlText += "<tr class='LthirdList_"+this.itemSubId+"'>" +
				"<td style='display:none;text-align:center;'>"+this.itemId+"</td>" +
				"<td style='display:none;text-align:center;'>"+this.itemSubId+"</td>" +
				"<td style='text-align:center;'>"+this.itemNameXm+"</td>" +
				"<td style='text-align:center;'>"+this.price.toFixed(2)+"</td>" +
				"<td style='text-align:center;'>"+this.unit+"</td>" +
				"<th><a class='glyphicon glyphicon-trash' onclick='removeRow(this)'></a></th></tr>";
			});					 
			$("#thirdList").html(htmlText);
		}
	});
	
	$("#search-text").val("");
	$('#secendFl').modal({
		backdrop: 'static',
		keyboard: true
	})
}
function deleteLSCZX(tooth){
	$.ajax({
		url:"clearLSCZX.json",
		type:'POST',
		async:false,
		dataType:'json',
		data:{
			regId:$("#regId").val(),
			tooth:tooth
		},
		success:function(data,textStatus,XMLHttpRequest){
			loadDetailList();
		}
	});
}

function loadFirst(){
	$.ajax({
		url:"HisPatient.json",
		type:'POST',
		async:false,
		dataType:'json',
		data:{
			patientId:$("#patientId").val()
		},
		success:function(data,textStatus,XMLHttpRequest){
			var obj = data.obj;
			$("#patName").html(obj.patName);
			$("#mobile").html(obj.mobile);
			$("#patNo").html(obj.patNo);
			var datas = data.rows;
			var htmlText = "";
			$(datas).each(function(){
				htmlText += '<li onclick="getSecondList(this,\''+this.itemCode+'\')"><a href="#" url="'+this.itemCode+'">'+this.itemName+'</a></li>'
			});
			$("#his_service_item").html(htmlText);
		}
	});
}

function getSecondList(docs,itemCode){
	$(docs).addClass("active").siblings().removeClass("active");
	$.ajax({
		url:"getHisServiceItemFlList.json",
		type:'POST',
		async:false,
		dataType:'json',
		data:{
			itemCode:itemCode
		},
		success:function(data,textStatus,XMLHttpRequest){
			var datas = data.rows;
			var htmlText = "";
			$(datas).each(function(){
//				htmlText += '<li data-toggle = "modal" data-target ="#secendFl" onclick="getSecondList('+this.itemCode+')"><a href="#" >'+this.itemName+'</a></li>'
				htmlText += '<li class="secondList" onclick="refreshThirdList(this,\''+this.itemCode+'\')"><a>'+this.itemName+'</a></li>'
			});					 
			$("#his_service_item_fl").html(htmlText);
//			$('#secendFl').modal({
//				backdrop: 'static',
//				keyboard: true
//			})
		}
	});
}

function alerts(docs){
	alert(docs);
}

function refreshThirdList(docs,itemCode){
	$(docs).addClass("active").siblings().removeClass("active");
	$("#secondChoos").val(itemCode);
//	$('#thirdTable').bootstrapTable("refresh");
	getThirdList(itemCode);
}

function clearThirdTable(){
	$(".active").removeClass("active");
	$("#thirdList").html("");
	$("#his_service_item_xm").html("");
	$("#his_service_item_fl").html("");
	
}
//function subThirdTable(){
//	var selects = $("#thirdTable").bootstrapTable('getSelections');
//	var json =JSON.stringify(selects);
//	json = {"rows":selects}
//	var secName = $("#ajaxtab .tabbtn .active a").html();
//	$("#ajaxtab .tabbtn .active").removeClass("active");
//	$("#secondChoos").val("");
//	$('#thirdTable').bootstrapTable("refresh");
//	$('#secendFl').modal('hide');
//	getMainTable(json,secName);
//}
function subThirdTable(){
	$('#secendFl').modal('hide');
	subTable2();
	clearThirdTable();
	loadDetailList();
}


function getMainTable(json,secName){
	var tableJson = json.rows;
	var firName = $("#his_service_item .active a").html();
	var firCode = $("#his_service_item .active a").attr("url");
	var tools = '<td style="display:none;" data-toggle="modal" data-target="#myModal" onclick="getDoc(this)">-</td>';	
//	
//	<button type="button" class="btn btn-block add">+</button>
	var htmlText = "";
	$(tableJson).each(function(){
		htmlText += '<tr><td style="display:none;">'+firCode+'</td><td>'+firName+'</td><td>'+secName+'</td>'+tools+'<td>'+this.itemName+'</td><td style="display:none;">'+this.itemCode+'</td><td>'+this.price.toFixed(2)+'</td>'+
		'<td>'+this.unit+'</td><td style="border-left:0;border-right:0;"><button type="button" class="btn btn-block add" onclick="addNum(this)">+</button></td><td style="border-left:0;border-right:0;">1</td><td style="border-left:0;border-right:0;"><button type="button" class="btn btn-block min"onclick="minNum(this)">-</button></td><td>'+this.price.toFixed(2)+'</td><th><a class="glyphicon glyphicon-trash" onclick="removeRow(this)"></a></th></tr>'               
	});
	$("#firstList").append(htmlText);
	sumZje();
}

function removeRow(docs){
	var tr = $(docs).parent().parent();
	$(tr).remove();
//	sumZje();
}

function clearMainList(){
	if(confirm("确认清空所有处置项吗？")){}else{return;}
	$.ajax({
		url:"clearLSCZX.json",
		type:'POST',
		async:false,
		dataType:'json',
		data:{
			regId:	$("#regId").val(),
			tooth:"-"
		},
		success:function(data,textStatus,XMLHttpRequest){
			$("#firstList").html("");
			$("#firstname2").val("");
			sumZje();
		}
	});
	
}

function getThirdList(itemCode){
//	  $('#thirdTable').bootstrapTable({
//		   method: 'post',
//		   url: 'getHisServiceItemXmList.json',
//		   singleSelect: false,
//		   queryParams: getParams,
//		   striped: true,      //是否显示行间隔色
//		   sidePagination: "server", //服务端请求
//		   clickToSelect: true,    //是否启用点击选中行
//		   contentType: "application/x-www-form-urlencoded",
//		   columns: [{
//			field: 'itemCode',
//			title: '处置ID'
//		   },
//		   {
//		    field: 'itemName',
//		    title: '处置名称'
//		   }, 
//		   {
//		    field: 'unit',
//		    title: '单位'
//		   },
//		   {
//		    field: 'price',
//		    title: '单价',
//		    formatter:function(value,row,index){
//				return value.toFixed(2);
//			}
//		   }],
//		  });
	$.ajax({
		url:"getHisServiceItemXmList.json",
		type:'POST',
		async:false,
		dataType:'json',
		data:{
			itemCode:itemCode
		},
		success:function(data,textStatus,XMLHttpRequest){
			var datas = data.rows;
			var htmlText = "";
			var firName = $("#his_service_item .active a").html();
			var firCode = $("#his_service_item .active a").attr("url");
			$(datas).each(function(){
//				htmlText += '<li data-toggle = "modal" data-target ="#secendFl" onclick="getSecondList('+this.itemCode+')"><a href="#" >'+this.itemName+'</a></li>'
				htmlText += '<li class="thirdList" onclick="getThirdListwright(\''+firCode+'\',\''+this.itemCode+'\',\''+this.itemName+'\',\''+this.price.toFixed(2)+'\',\''+this.unit+'\')"><a>'+this.itemName+'/'+this.price.toFixed(2)+'</a></li>'
			});					 
			$("#his_service_item_xm").html(htmlText);
//			$('#secendFl').modal({
//				backdrop: 'static',
//				keyboard: true
//			})
		}
	});
}

function getThirdListwright(firCode,thiCode,thiName,price,unit){
//	thirdList
	var newLine = "<tr class='LthirdList_"+thiCode+"'>" +
			"<td style='display:none;text-align:center;'>"+firCode+"</td>" +
			"<td style='display:none;text-align:center;'>"+thiCode+"</td>" +
			"<td style='text-align:center;'>"+thiName+"</td>" +
			"<td style='text-align:center;'>"+price+"</td>" +
			"<td style='text-align:center;'>"+unit+"</td>" +
			"<th><a class='glyphicon glyphicon-trash' onclick='removeRow(this)'></a></th></tr>";
	var newLineClass = 'LthirdList_'+thiCode;
	if($("."+newLineClass).html()!=undefined){
		$("."+newLineClass).remove();
	}
	$("#thirdList").append(newLine);
}

//function minNum(docs){
//	var add = $(docs).parent();
//	var adds = $(add).prev();
//	var num = parseInt($(adds).html());
//	if(num==1){
//		return;
//	}
//}
function minNum(id,docs){
	var add = $(docs).parent();
	var adds = $(add).prev();
	var num = parseInt($(adds).html());
	if(num==1){
		return;
	}
	$.ajax({
		url:"updateLSqty.json",
		type:'POST',
		async:false,
		dataType:'json',
		data:{
			id:id,
			qty:num-1
		},
		success:function(data,textStatus,XMLHttpRequest){
			if(data.success){
				loadDetailList();
			}else{
				alert("当前状态下，您无法修改</br>请刷新界面");
			}
		}
	});
}
function addNum(id, docs){
	var add = $(docs).parent();
	var adds = $(add).next();
	var num = parseInt($(adds).html());
//	$(adds).html((num+1));
//	calculation(adds,num+1);
	$.ajax({
		url:"updateLSqty.json",
		type:'POST',
		async:false,
		dataType:'json',
		data:{
			id:id,
			qty:num+1
		},
		success:function(data,textStatus,XMLHttpRequest){
			if(data.success){
				loadDetailList();
			}else{
				alert("当前状态下，您无法修改</br>请刷新界面");
			}
		}
	});
}
//function addNum(docs){
//	var add = $(docs).parent();
//	var adds = $(add).next();
//	var num = parseInt($(adds).html());
//	$(adds).html((num+1));
//	calculation(adds,num+1);
//}

function calculation(docs,num){
	var djDoc = $(docs).prev().prev().prev();
//	alert($(djDoc).html());
	var jeDoc = $(docs).next().next();
//	alert($(jeDoc).html());
	var dj = parseFloat($(djDoc).html());
	$(jeDoc).html((dj*num).toFixed(2));
	sumZje();
}

function getParams(){
	return {
		itemCode:$("#secondChoos").val()
	};
}

function getDoc(str){
	doc = str;	
}

function sumZje(){
	var zje = 0;
	$("#firstList").find('tr').each(function () {
			var html=parseFloat($(this).find('td:last').text());
			zje += html;
		 });
	$("#firstname").val(zje.toFixed(2));
	jshj();
}

function jshj(){
	if ($("#firstname2").val()==''){
		$("#firstname2").val('0.00');
	}else{
		var zk1= parseFloat($("#firstname2").val());
		$("#firstname2").val(zk1.toFixed(2));
	}
	var zk = parseFloat($("#firstname2").val());
	$("#firstname2").val(zk.toFixed(2));
	var zje =parseFloat($("#firstname").val());
	var hj = parseFloat(zje-zk);
	if(hj<0){
		alert("折扣金额不得大于处置项金额")
		$("#firstname2").val('0.00');
		$("#firstname3").val(parseFloat(zje).toFixed(2));
		return false
	}
	var zje2 = zje*(1-parseFloat($("#zdzk").val()));
	var hj2 = parseFloat(zje2-zk); 
	if(hj2<0){
		alert("您已超出您的折扣权限，请重新输入")
		$("#firstname2").val('0.00');
		$("#firstname3").val(parseFloat(zje).toFixed(2));
		return false
	}
	$("#firstname3").val(parseFloat(hj).toFixed(2));
}

function getThrListJson(){
	var  html = "";
	var  json = "";
	var  jsonDate = '[';
	var thirds = $('#thirdList').find('tr');
	thirds.each(function () {
		var tools = $("#showTooth").text();
		json="{";
		var i = 0;
         $(this).find('td').each(function () { 
        	if(i==0){
        		json+='"itemId":"'+$(this).text() + '",';
        		json+='"tooth":"'+ tools + '",';
        		json+='"ifFormal":"0",';
        	}
        	if(i==1){
        		json+='"itemSubId":"'+$(this).text() + '",';
        	}
        	if(i==3){
        		json+='"price":"'+$(this).text() + '",';
        		json+='"itemAmount":"'+$(this).text() + '",';
        	}
        	if(i==4){
        		json+='"qty":"1",';
        		json+='"unit":"'+$(this).text() + '"';
        	}
          i++;
         });     
         json += "},"
         jsonDate+=json
       }); 
	jsonDate =jsonDate.substr(0,jsonDate.length-1);
	jsonDate+="]"
	return jsonDate;
}
function getListJson(){
	var  html = "";
	var  json = "";
	var  jsonDate = '[';
	
	var flag = true;
	$('#firstList').find('tr').each(function () {   
		json="{";
		var i = 0;
         $(this).find('td').each(function () { 
        	if(i==0){
        		json+='"itemId":"'+$(this).text() + '",';
        	}
        	if(i==3){
        		flag = false;
        		json+='"tooth":"'+$(this).text() + '",';
        	}
        	if(i==5){
        		json+='"itemSubId":"'+$(this).text() + '",';
        	}
        	if(i==6){
        		json+='"price":"'+$(this).text() + '",';
        	}
        	if(i==7){
        		json+='"unit":"'+$(this).text() + '",';
        	}
        	if(i==9){
        		json+='"qty":"'+$(this).text() + '",';
        	}
        	if(i==11){
        		json+='"itemAmount":"'+$(this).text() + '"';
        	}
          i++;
         });     
         json += "},"
         jsonDate+=json
       }); 
	if(flag){
		return "]";
	}
	jsonDate =jsonDate.substr(0,jsonDate.length-1);
	jsonDate+="]"
	return jsonDate;
}

function subTable(){
	var list = getListJson();
	var data = {};
	if (list==']') {
		data = {
			regId:	$("#regId").val(),
			patientId:	$("#patientId").val()
		}
	} else {
		data = {
				regId:	$("#regId").val(),
				patientId:	$("#patientId").val(),
				sh:	$("#firstname3").val(),
				dz:	$("#firstname2").val(),
				ze:	$("#firstname").val(),
				list:list
			}
	}

	$.ajax({
		url:"saveTable.json",
		type:'POST',
		async:false,
		dataType:'json',
		data:data,
		success:function(data,textStatus,XMLHttpRequest){
			if(data.success){
				window.location='/enjoyhisfy/client/register/register_index.htm';
			}else{
				alert("当前状态下，您无法提交</br>请刷新界面");
			}
		}
	});
}

function subTable2(){
	var list = getThrListJson();
	var data = {};
	if (list==']') {
		data = {
			regId:	$("#regId").val(),
			patientId:	$("#patientId").val()
		}
	} else {
		data = {
			regId:	$("#regId").val(),
			patientId:	$("#patientId").val(),
			list:list
		}
	}

	$.ajax({
		url:"saveTable2.json",
		type:'POST',
		async:false,
		dataType:'json',
		data:data,
		success:function(data,textStatus,XMLHttpRequest){
			if(data.success){
			}else{
				alert("当前状态下，您无法提交</br>请刷新界面");
			}
		}
	});
}



function getItemForTable(code){
	$.ajax({
		url:"getItemForTable.json",
		type:'POST',
		async:false,
		dataType:'json',
		data:{
			code:code
		},
		success:function(data,textStatus,XMLHttpRequest){
			var tableJson = data.obj;
//			var tools = '<td data-toggle="modal" data-target="#myModal" onclick="getDoc(this)">-</td>';	
//			var htmlText =  '<tr><td style="display:none;">'+tableJson.firCode+'</td><td>'+tableJson.firName+'</td><td>'+tableJson.itemParentName+'</td>'+tools+'<td>'+tableJson.itemName+'</td><td style="display:none;">'+tableJson.itemCode+'</td><td>'+tableJson.price.toFixed(2)+'</td>'+
//				'<td>'+tableJson.unit+'</td><td><button type="button" class="btn btn-block add" onclick="addNum(this)">+</button></td><td>1</td><td><button type="button" class="btn btn-block min"onclick="minNum(this)">-</button></td><td>'+tableJson.price.toFixed(2)+'</td><th><a class="glyphicon glyphicon-trash" onclick="removeRow(this)"></a></th></tr>'               
//			$("#firstList").append(htmlText);
//			sumZje();
			getThirdListwright(tableJson.firCode,tableJson.itemCode,tableJson.itemName,tableJson.price.toFixed(2),tableJson.unit)
		}
	});
}
