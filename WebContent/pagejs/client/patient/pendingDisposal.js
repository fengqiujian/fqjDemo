$(function(){
//	load();
	loadFirst();
	loadDetailList();
	jshj();
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
	
	$(".pull-right button").click(function(){
		var list=$(".col-sm-6 .col-xs-1");//
		var num = "";//定义一个变量
		$($(".myselected")).each(function(i){
			console.info($(this).attr('data-value'));
			num = num+$(this).attr('data-value')+",";//获取牙齿的value值
			$(this).removeClass('myselected');//移除class 清除td的值
		});
		console.info(num.substr(0,num.length-1));
//			console.info(list);
		list.css("background","");//清空背景色
		var toolsStr = num.substr(0,num.length-1);
		if(toolsStr==""){
			toolsStr = "-"
		}
		$(doc).text(toolsStr);//给表格项添加值
		doc = null;
	});	
                     
});


var doc;
var thirdList;

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
			var firName = $("#his_service_item .active a").html();
			var firCode = $("#his_service_item .active a").attr("url");
		//	
//			<button type="button" class="btn btn-block add">+</button>
			var htmlText = "";
			$(tableJson).each(function(){
				var tooth = getCHTooths(this.tooth);
				var tools = '<td>'+tooth+'</td>';	
				htmlText += '<tr>'+tools+'<td style="display:none;">'+this.itemId+'</td><td>'+this.itemName+'</td><td>'+this.itemNameFl+'</td><td>'+this.itemNameXm+'</td><td style="display:none;">'+this.itemSubId+'</td><td>'+this.price.toFixed(2)+'</td>'+
				'<td>'+this.unit+'</td><td>'+this.qty+'</td><td>'+this.itemAmount.toFixed(2)+'</td></tr>'               
			});
			$("#firstList").append(htmlText);
			autoRowSpan(0,0);
			sumZje();
		}
	});
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
				htmlText += '<li onclick="getSecondList(\''+this.itemCode+'\')"><a href="#" url="'+this.itemCode+'">'+this.itemName+'</a></li>'
			});
//			$("#his_service_item").html(htmlText);
		}
	});
}

function getSecondList(itemCode){
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
			$('#secendFl').modal({
				backdrop: 'static',
				keyboard: true
			})
			getThirdList();
			$("#thirdTable").bootstrapTable('hideColumn', 'itemCode');
		}
	});
}

function alerts(docs){
	alert(docs);
}

function refreshThirdList(docs,itemCode){
	$(docs).addClass("active").siblings().removeClass("active");
	$("#secondChoos").val(itemCode);
	$('#thirdTable').bootstrapTable("refresh");
}

function clearThirdTable(){
	$("#ajaxtab .tabbtn .active").removeClass("active");
	$("#secondChoos").val("");
	$('#thirdTable').bootstrapTable("refresh");
}
function subThirdTable(){
	var selects = $("#thirdTable").bootstrapTable('getSelections');
	var json =JSON.stringify(selects);
	json = {"rows":selects}
	var secName = $("#ajaxtab .tabbtn .active a").html();
	$("#ajaxtab .tabbtn .active").removeClass("active");
	$("#secondChoos").val("");
	$('#thirdTable').bootstrapTable("refresh");
	$('#secendFl').modal('hide');
	getMainTable(json,secName);
}

function getMainTable(json,secName){
	var tableJson = json.rows;
	var firName = $("#his_service_item .active a").html();
	var firCode = $("#his_service_item .active a").attr("url");
	var tools = '<td data-toggle="modal" data-target="#myModal" onclick="getDoc(this)">-</td>';	
//	
//	<button type="button" class="btn btn-block add">+</button>
	var htmlText = "";
	$(tableJson).each(function(){
		htmlText += '<tr><td style="display:none;">'+firCode+'</td><td>'+firName+'</td><td>'+secName+'</td>'+tools+'<td>'+this.itemName+'</td><td style="display:none;">'+this.itemCode+'</td><td>'+this.price.toFixed(2)+'</td>'+
		'<td>'+this.unit+'</td><td><button type="button" class="btn btn-block add" onclick="addNum(this)">+</button></td><td>1</td><td><button type="button" class="btn btn-block min"onclick="minNum(this)">-</button></td><td>'+this.price.toFixed(2)+'</td><th><a class="glyphicon glyphicon-trash" onclick="removeRow(this)"></a></th></tr>'               
	});
	$("#firstList").append(htmlText);
	sumZje();
}

function removeRow(docs){
	var tr = $(docs).parent().parent();
	$(tr).remove();
	sumZje();
}

function clearMainList(){
	$("#firstList").html("");
	$("#firstname2").val("");
	sumZje();
}

function getThirdList(){
	  $('#thirdTable').bootstrapTable({
		   method: 'post',
		   url: 'getHisServiceItemXmList.json',
		   singleSelect: false,
		   queryParams: getParams,
		   striped: true,      //是否显示行间隔色
		   sidePagination: "server", //服务端请求
		   clickToSelect: true,    //是否启用点击选中行
		   contentType: "application/x-www-form-urlencoded",
		   columns: [{
			   checkbox: true,
		   },{
			field: 'itemCode',
			title: '处置ID'
		   },
		   {
		    field: 'itemName',
		    title: '处置名称'
		   }, 
		   {
		    field: 'unit',
		    title: '单位'
		   },
		   {
		    field: 'price',
		    title: '单价',
		    formatter:function(value,row,index){
				return value.toFixed(2);
			}
		   }],
		  });
}

function minNum(docs){
	var add = $(docs).parent();
	var adds = $(add).prev();
	var num = parseInt($(adds).html());
	if(num==1){
		return;
	}
	$(adds).html((num-1));
	calculation(adds,num-1);
}
function addNum(docs){
	var add = $(docs).parent();
	var adds = $(add).next();
	var num = parseInt($(adds).html());
	$(adds).html((num+1));
	calculation(adds,num+1);
}

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
	$("#firstname3").val(parseFloat(hj).toFixed(2));
}

function getListJson(){
	var  html = "";
	var  json = "";
	var  jsonDate = '[';
	
	$('#firstList').find('tr').each(function () {   
		json="{";
		var i = 0;
         $(this).find('td').each(function () { 
        	if(i==0){
        		json+='"itemId":"'+$(this).text() + '",';
        	}
        	if(i==3){
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
//          html += $(this).text() + ',';  
          i++;
         });     
         json += "},"
//         alert(json);
//         alert(html);
         jsonDate+=json
       }); 
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
				alert("添加成功");
			}else{
				alert("添加失败");
			}
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
