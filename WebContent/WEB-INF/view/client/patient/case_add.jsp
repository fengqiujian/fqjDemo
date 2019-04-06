<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>病历记录添加</title>
<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/his.css" />
</head>
<body>
	<input type="hidden" id="pid" value="${patient.id}" />
	<input type="hidden" id="docId" value="${patient.maindocId}" />
	<input type="hidden" id="nowdate" value="${nowdate}" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<div class="row">
					<ol class="breadcrumb text-center" style="background: none; margin-bottom: 0;">
						<li><a href="/enjoyhisfy/client/case/case_record_view.htm?pid=${patient.id}">返回</a></li>
						<li><a href="#">病历记录添加</a></li>
					</ol>
				</div>
			</div>
		</div>
		<hr style="margin-top: 0" />
		<div class="col-sm-12" style="line-height: 30px;">
			<div class="row">
				<div class="col-sm-12" style="margin-bottom: 15px;">
					<div class="col-sm-2">
						<div class="row">
							<span>患者姓名:</span><span>${patient.patName }</span>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="row">
							<span>性别:</span><span>${patient.userSex }</span>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="row">
							<span>年龄:</span><span>${patient.age }</span>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="row">
							<span>手机号码:</span><span>${patient.mobile }</span>
						</div>
					</div>
					<br class="clear" />
					<div class="col-sm-2">
						<div class="row">
							<span>病历编号:</span><span>${patient.patNo }</span>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="row">
							<span>初诊医生:</span><span>${patient.docName }</span>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="row">
							<span>记录时间:</span><span>${nowdate}</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--头部信息-->
		<div class="col-sm-12">
			<div class="col-sm-2">
				<div class="row">牙位图</div>
			</div>
			<div class="col-sm-8  allborder  addzoom" style="background-color: #FFFFFF; padding: 10px 0;">
				<div class="row">
					<div class="col-sm-11 middle  allborder mantooth" style="overflow: auto; display: block;">
						<div class="row">
							<div class="col-sm-6  man border-right add-border-btn" style="border-bottom: 1px solid black;">
								<div class="row row-xs-1 upaddbg">
									<div class="col-xs-1 up" data-value="18" style="margin-left: 40px;">18</div>
									<div class="col-xs-1 up" data-value="17">17</div>
									<div class="col-xs-1 up" data-value="16">16</div>
									<div class="col-xs-1 up" data-value="15">15</div>
									<div class="col-xs-1 up" data-value="14">14</div>
									<div class="col-xs-1 up" data-value="13">13</div>
									<div class="col-xs-1 up" data-value="12">12</div>
									<div class="col-xs-1 up" data-value="11">11</div>
								</div>
							</div>
							<div class="col-sm-6 man" style="border-bottom: 1px solid black;">
								<div class="row row-xs-1 upaddbg">
									<div class="col-xs-1 up" data-value="21" style="margin-left: 24px;">21</div>
									<div class="col-xs-1 up" data-value="22">22</div>
									<div class="col-xs-1 up" data-value="23">23</div>
									<div class="col-xs-1 up" data-value="24">24</div>
									<div class="col-xs-1 up" data-value="25">25</div>
									<div class="col-xs-1 up" data-value="26">26</div>
									<div class="col-xs-1 up" data-value="27">27</div>
									<div class="col-xs-1 up" data-value="28">28</div>
								</div>
							</div>
							<div class="col-sm-6 man border-right add-border-btm">
								<div class="row row-xs-1 downaddbg">
									<div class="col-xs-1 down" data-value="48" style="margin-left: 40px;">48</div>
									<div class="col-xs-1 down" data-value="47">47</div>
									<div class="col-xs-1 down" data-value="46">46</div>
									<div class="col-xs-1 down" data-value="45">45</div>
									<div class="col-xs-1 down" data-value="44">44</div>
									<div class="col-xs-1 down" data-value="43">43</div>
									<div class="col-xs-1 down" data-value="42">42</div>
									<div class="col-xs-1 down" data-value="41">41</div>
								</div>
							</div>
							<div class="col-sm-6 man">
								<div class="row row-xs-1 downaddbg">
									<div class="col-xs-1 down" data-value="31" style="margin-left: 24px;">31</div>
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
					<%--乳牙 start--%>
					<div class="col-sm-11 middle  allborder babytooth" style="display: none">
						<div class="row">
							<div class="col-sm-6 babys  border-right add-border-btn" style="border-bottom: 1px solid black;">
								<div class="row row-xs-1 upaddbg">
									<div class="col-xs-1 up" data-value="56" style="margin-left: 68px;">56</div>
									<div class="col-xs-1 up" data-value="55">55</div>
									<div class="col-xs-1 up" data-value="54">54</div>
									<div class="col-xs-1 up" data-value="53">53</div>
									<div class="col-xs-1 up" data-value="52">52</div>
									<div class="col-xs-1 up" data-value="51">51</div>
								</div>
							</div>
							<div class="col-sm-6 babys" style="border-bottom: 1px solid black;">
								<div class="row row-xs-1 upaddbg">
									<div class="col-xs-1 up" data-value="61" style="margin-left: 10px;">61</div>
									<div class="col-xs-1 up" data-value="62">62</div>
									<div class="col-xs-1 up" data-value="63">63</div>
									<div class="col-xs-1 up" data-value="64">64</div>
									<div class="col-xs-1 up" data-value="65">65</div>
									<div class="col-xs-1 up" data-value="66">66</div>
								</div>
							</div>
							<div class="col-sm-6 babys border-right add-border-btm">
								<div class="row row-xs-1 downaddbg">
									<div class="col-xs-1 down" data-value="86" style="margin-left: 68px;">86</div>
									<div class="col-xs-1 down" data-value="85">85</div>
									<div class="col-xs-1 down" data-value="84">84</div>
									<div class="col-xs-1 down" data-value="83">83</div>
									<div class="col-xs-1 down" data-value="82">82</div>
									<div class="col-xs-1 down" data-value="81">81</div>
								</div>
							</div>
							<div class="col-sm-6 babys">
								<div class="row row-xs-1 downaddbg">
									<div class="col-xs-1 down" data-value="71" style="margin-left: 10px;">71</div>
									<div class="col-xs-1 down" data-value="72">72</div>
									<div class="col-xs-1 down" data-value="73">73</div>
									<div class="col-xs-1 down" data-value="74">74</div>
									<div class="col-xs-1 down" data-value="75">75</div>
									<div class="col-xs-1 down" data-value="76">76</div>
								</div>
							</div>
						</div>
					</div>
					<%--乳牙 end--%>
				</div>
			</div>
			<div class="col-sm-8 text-center col-sm-offset-2 " style="margin-bottom: 15px;">
				<div class="row">
					<div class="col-sm-10">
						<div class="row">
							<button id="man" type="button" class="toothbtn btn  btn-success " style="margin-right: 3px;">恒牙</button>
							<button id="babys" type="button" class="toothbtn btn  btn-success " style="margin-right: 3px;">乳牙</button>
							<button id="all" type="button" class="toothbtn btn  btn-success allbaby" style="margin-right: 3px;">全口</button>
							<button id="half-up" type="button" class="toothbtn btn  btn-success baby-up" style="margin-right: 3px;">上半口</button>
							<button id="half-down" type="button" class="toothbtn btn  btn-success baby-down" style="margin-right: 3px;">下半口</button>
							<button id="clear-btn" type="button" class="btn  btn-success" data-dismiss="modal">清除</button>
						</div>
					</div>
				</div>
			</div>
			<br class="clear" />
			<div class="col-sm-2">
				<div class="row">主诉</div>
			</div>
			<div class="col-sm-8">
				<div class="row">
					<div class="form-group">
						<textarea id="zs" class="form-control" rows="3"></textarea>
					</div>
				</div>
			</div>
			<br class="clear" />
			<div class="col-sm-2">
				<div class="row">现病史</div>
			</div>
			<div class="col-sm-8">
				<div class="row">
					<div class="form-group">
						<textarea id="xbs" class="form-control" rows="3"></textarea>
					</div>
				</div>
			</div>
			<br class="clear" />
			<div class="col-sm-2">
				<div class="row">既往病史</div>
			</div>
			<div class="col-sm-8">
				<div class="row">
					<div class="form-group">
						<textarea id="jwbs" class="form-control" rows="3" placeholder=""></textarea>
					</div>
				</div>
			</div>
			<br class="clear" />
			<div class="col-sm-2">
				<div class="row">检查</div>
			</div>
			<div class="col-sm-8">
				<div class="row">
					<div class="form-group">
						<textarea id="jc" class="form-control" rows="3"></textarea>
					</div>
				</div>
			</div>
			<br class="clear" />
			<div class="col-sm-2">
				<div class="row">诊断</div>
			</div>
			<div class="col-sm-8">
				<div class="row">
					<div class="form-group">
						<textarea id="zd" class="form-control" rows="3"></textarea>
					</div>
				</div>
			</div>
			<br class="clear" />
			<div class="col-sm-2">
				<div class="row">处理</div>
			</div>
			<div class="col-sm-8">
				<div class="row">
					<div class="form-group">
						<textarea id="cl" class="form-control" rows="3"></textarea>
					</div>
				</div>
			</div>
			<br class="clear" />
			<div class="col-sm-2">
				<div class="row">医嘱</div>
			</div>
			<div class="col-sm-8">
				<div class="row">
					<div class="form-group">
						<textarea id="yz" class="form-control" rows="3"></textarea>
					</div>
				</div>
			</div>
			<br class="clear" />
			<div class="col-sm-2">
				<div class="row">就诊事项</div>
			</div>
			<div class="col-sm-8">
				<div class="row">
					<div class="form-group">
						<textarea id="jzsx" class="form-control" rows="3"></textarea>
					</div>
				</div>
			</div>
			<br class="clear" />
			<div class="col-sm-2">
				<div class="row">备注</div>
			</div>
			<div class="col-sm-8">
				<div class="row">
					<div class="form-group">
						<textarea id="remark" class="form-control" rows="3"></textarea>
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="row">
					<div class="col-sm-5 middle">
						<div class="col-sm-6">
							<button id="save-btn" type="button" class="btn btn-block btn-success">保存</button>
						</div>
						<div class="col-sm-6">
							<button id="cancel-btn" type="button" class="btn btn-block btn-success">取消</button>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>

<script src="${path}/js/jquery-2.1.4.min.js"></script>
<script src="${path}/js/bootstrap.min.js"></script>
<script src="${path}/js/bootstrap-table.min.js"></script>
<script src="${path}/js/bootstrap-table-zh-CN.min.js"></script>
<script src="../../assets/js/bootbox.js"></script>
	<script src="../../assets/js/alert.js"></script>
<script src="${path}/laydate/laydate.js"></script>
<script src="${path}/js/selectUi.js"></script>
<script src="${path}/js/lq.datetimepick.js"></script>

<script>
	var type = 1;//1:恒牙  2：乳牙
	//牙位选择
	$(function() {
		$('#clear-btn').click(function() {
			$("#docId").val('');
			$(".col-sm-6 .col-xs-1").css("background", "");//清空颜色
			$('#zs').val('');
			$('#xbs').val('');
			$('#jwbs').val('');
			$('#jc').val('');
			$('#zd').val('');
			$('#cl').val('');
			$('#yz').val('');
			$('#jzsx').val('');
			$('#remark').val('');

		});

		//恒牙
		$("#man").click(function() {
			type = 1;
			$(".mantooth").css("display", "block");
			$(".babytooth").css("display", "none");
			$(".col-sm-6 .col-xs-1").css("background", "");//清空颜色
		});
		//乳牙
		$("#babys").click(function() {
			type = 2;
			$(".mantooth").css("display", "none");
			$(".babytooth").css("display", "block");
			$(".col-sm-6 .col-xs-1").css("background", "");//清空颜色
		});
		//全口
		$("#all").click(function() {
			$(".myselected").removeClass("myselected");
			if (type == 1) {
				$(".man .col-xs-1").css("background", "gray");
				$(".man .col-xs-1").addClass("myselected");//给选中的项添加class myselected;
			} else {
				$(".babys .col-xs-1").css("background", "gray");
				$(".babys .col-xs-1").addClass("myselected");//给选中的项添加class myselected;
			}
		});
		//上半口
		$("#half-up").click(function() {
			$(".myselected").removeClass("myselected");
			if (type == 1) {
				$(".man .col-xs-1").filter(".down").css("background", "");//清空下半口颜色
				$(".man .upaddbg .col-xs-1 ").css("background", "gray");//恒牙上半口
				$(".man .upaddbg .col-xs-1").addClass("myselected");//给选中的项添加class myselected;
				$(".man .downaddbg .col-xs-1").removeClass("myselected");//移除下半口的class
			} else {
				$(".babys .col-xs-1").filter(".down").css("background", "");
				$(".babys .upaddbg .col-xs-1").css("background", "gray");
				$(".babys .upaddbg .col-xs-1").addClass("myselected");//给选中的项添加class myselected;
				$(".babys .downaddbg .col-xs-1").removeClass("myselected");//移除下半口的class
			}
		});
		//下半口
		$("#half-down").click(function() {
			$(".myselected").removeClass("myselected");
			if (type == 1) {
				$(".man .col-xs-1").filter(".up").css("background", "")
				$(".man .downaddbg .col-xs-1").css("background", "gray");
				$(".man .downaddbg .col-xs-1").addClass("myselected");
				$(".man .upaddbg .col-xs-1").removeClass("myselected");//移除上半口的class
			} else {
				$(".babys .col-xs-1").filter(".up").css("background", "")
				$(".babys .downaddbg .col-xs-1").css("background", "gray");
				$(".babys .downaddbg .col-xs-1").addClass("myselected");
				$(".babys .upaddbg .col-xs-1").removeClass("myselected");//移除上半口的class
			}
		});

		//选择单个牙齿
		$(".col-sm-6 .col-xs-1").click(function() {
			$(".myselected").removeClass("myselected");
			$(this).css("background", "gray");
			$(this).addClass("myselected");
		});
		//清空选择牙齿
		$("#blank").click(function() {
			$(".col-sm-6 .col-xs-1").css("background", "");
		});

		//乳牙选择
		//全口
		$(".allbaby").click(function() {

		});

		//选择单个牙齿
		$(".babys .col-xs-1").click(function() {
			$(this).css("background", "gray");
			$(this).addClass("myselected");
		});

		//保存操作
		$('#save-btn').click(function() {
			var temptooth = "";
			//获取牙齿的值
			$(".myselected").each(function() {//给每一个有myselected的牙齿添加事件
				temptooth += $(this).attr('data-value') + ",";
			});
			console.info(temptooth.substr(0, temptooth.length - 1));
			var tooth = temptooth.substr(0, temptooth.length - 1)
			$.ajax({
				url : "/enjoyhisfy/client/case/patientcase/save",
				data : {
					"pid" : $('#pid').val(),
					"docId" : $("#docId").val(),
					"tooth" : tooth,
					"zs" : $('#zs').val(),
					"xbs" : $('#xbs').val(),
					"jwbs" : $('#jwbs').val(),
					"jc" : $('#jc').val(),
					"zd" : $('#zd').val(),
					"cl" : $('#cl').val(),
					"yz" : $('#yz').val(),
					"jzsx" : $('#jzsx').val(),
					"remark" : $('#remark').val(),
					"nowdate" : $('#nowdate').val()
				},
				type : 'post',
				async : false,
				dataType : 'json',
				success : function(data) {
					alert("操作成功",cb);
					//var pid = $("#pid").val();
					//window.location = "/enjoyhisfy/client/case/case_record_view.htm?pid=" + pid;
				}
			});
		});
	});
	var cb = function(){
		var pid = $("#pid").val();
		window.location = "/enjoyhisfy/client/case/case_record_view.htm?pid=" + pid;
	}
	//取消操作
	$('#cancel-btn').click(function() {
		var pid = $("#pid").val();
		window.location = "/enjoyhisfy/client/case/case_record_view.htm?pid=" + pid;
	})
</script>
</html>