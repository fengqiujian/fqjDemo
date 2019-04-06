$(function(){
	getList();
});

function getList() {
	  $('#tb_departments').bootstrapTable({
	   method: 'POST',      //请求方式（*）
	   url: '../account/getList.json?patientId='+$("#regId").val(),
	   pagination: false,
	   singleSelect: false,
	   striped: true,      //是否显示行间隔色
	   sortable: true,      //是否启用排序
//	   sortOrder: "asc",     //排序方式
//	   queryParams: getList(),//传递参数（*）
	   sidePagination: "server", //服务端请求
//	   clickToSelect: true,    //是否启用点击选中行
//	   height: 500,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	   columns: [ 
	   {
	    field: 'id',
	    title: '账单号',
	    formatter:function(value,row,index){
			var type = row.oldStatementCode;
			if(type!=null||type!=undefined){
				return type;
			}else{
				return value;
			}
		 }
	   }, 
	   {
	    field: 'modifyTime',
	    title: '日期',
	   },
	   {
	    field: 'docName',
	    title: '就诊医生'
	   },{
	    field: 'accountTypeName',
	    title: '账单类型'
	   },{
	    field: 'payAmount',
	    title: '账单金额',
		formatter:function(value,row,index){
			var type = row.accountTypeName;
			if(type==2){
				return "￥"+value.toFixed(2);
			}else{
				return "￥"+row.totalAmount.toFixed(2);
			}
		 }
	   },{
	    field: 'statusName',
	    title: '操作',
		formatter:function(value,row,index){
			var date = row.createTime;
			date = date.substring(0,10); 
			if(row.accountType==2){
				var oldStatementCode = "0"
				if(row.oldStatementCode!=undefined){
					oldStatementCode = row.oldStatementCode;
				}
				return '<a href="javascript:void(0);"'+
				'onclick="webCallCharge('+row.patId+','+row.statementItemid+','+row.regId+',\''+date+'\','+row.id+','+oldStatementCode+');">查看详情</a>'
			}else if(row.accountType==3){
				return '<a href="javascript:void(0);"'+
				'onclick="webCallArrears2('+row.patId+','+row.id+');">查看详情</a>'
			}else if(row.accountType==9){
				return '<a href="javascript:void(0);"'+
				'onclick="webCallAdvance2('+row.patId+','+row.id+',1);">查看详情</a>'
			}else if(row.accountType==10){
				return '<a href="javascript:void(0);"'+
				'onclick="webCallAdvance2('+row.patId+','+row.id+',2);">查看详情</a>'
			}else if(row.accountType==11){
				if(row.payAmount>0){
					return '';
				}else{
					return '<a href="javascript:void(0);"'+
					'onclick="webCallAdvance2('+row.patId+','+row.id+',3);">查看详情</a>'
				}
			}else{
				return '';
			}
		}
	   }], 
	  });
};


/**
 * 收欠款
 * @param id
 */
function webCallArrears(id){
	id = id.toString();
	Cef.webCallArrears(id);
}

/**
 * 预充值管理
 * @param id
 */
function webCallAdvance(id){
	id = id.toString();
	Cef.webCallAdvance(id);
}

/**
 * 查看已完成
 */
function webCallCharge(patId, statementItemid, regId,date,id,oldStatementCode){
	patId = patId.toString();
	statementItemid = statementItemid.toString();
	regId = regId.toString();
	id = id.toString()+"#";
	if(oldStatementCode!="0"){
		id = id+oldStatementCode;
	}
	oldStatementCode = oldStatementCode.toString();
	Cef.webCallCharge(patId, statementItemid, regId, 3,date,id)
}
/**
 * 查看收欠款
 */
function webCallArrears2(patId,id){
 patId = patId.toString();
 id = id.toString();
 Cef.webCallArrears2(patId,id);
}

/**
 * 查看预充值管理
 */
function webCallAdvance2(patId,id,type){
	patId = patId.toString();
	id = id.toString();
	Cef.webCallAdvance2(patId,id,type);
}