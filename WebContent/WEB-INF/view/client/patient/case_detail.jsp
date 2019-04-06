<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>病历记录详情</title>
	<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="${path}/css/his.css"/>
	<style>
		.myselected{background: red}
	</style>
</head>
<body>

<input type="hidden" value="${hisPatientCase.tooth}" id="toothId"/>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-2">
			<div class="row">
				<ol class="breadcrumb text-center" style="background: none;margin-bottom: 0;">
					<li><a href="/enjoyhisfy/client/case/case_record_view.htm?pid=${patient.id}">返回</a></li>
					<li><a href="#">病历记录详情</a></li>
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
						<span>记录时间:</span><span>${jldate}</span>
					</div>
				</div>
			</div>
		</div>
	</div><!--头部信息-->
	<div class="col-sm-12">
		<div class="col-sm-2">
			<div class="row">
				牙位图
			</div>
		</div>
		<div class="col-sm-8  allborder  addzoom" style="background-color: #FFFFFF;padding: 10px 0;">
			<div class="row">
				<div class="col-sm-11 middle  allborder mantooth" style="overflow: auto;display: block;">
					<div class="row">
						<div class="col-sm-6  man border-right add-border-btn" style="border-bottom: 1px solid black;">
							<div class="row row-xs-1 upaddbg">
								<div  id="tooth-id-18" class="col-xs-1 up" data-value="18" style="margin-left: 40px;">18</div>
								<div  id="tooth-id-17" class="col-xs-1 up" data-value="17">17</div>
								<div  id="tooth-id-16" class="col-xs-1 up" data-value="16">16</div>
								<div  id="tooth-id-15" class="col-xs-1 up" data-value="15">15</div>
								<div id="tooth-id-14" class="col-xs-1 up" data-value="14">14</div>
								<div id="tooth-id-13" class="col-xs-1 up" data-value="13">13</div>
								<div id="tooth-id-12" class="col-xs-1 up" data-value="12">12</div>
								<div id="tooth-id-11" class="col-xs-1 up" data-value="11">11</div>
							</div>
						</div>
						<div class="col-sm-6 man"  style="border-bottom: 1px solid black;">
							<div class="row row-xs-1 upaddbg">
								<div id="tooth-id-21" class="col-xs-1 up" data-value="21" style="margin-left:24px;">21</div>
								<div id="tooth-id-22" class="col-xs-1 up" data-value="22">22</div>
								<div id="tooth-id-23" class="col-xs-1 up" data-value="23">23</div>
								<div id="tooth-id-24" class="col-xs-1 up" data-value="24">24</div>
								<div id="tooth-id-25" class="col-xs-1 up" data-value="25">25</div>
								<div id="tooth-id-26" class="col-xs-1 up" data-value="26">26</div>
								<div id="tooth-id-27" class="col-xs-1 up" data-value="27">27</div>
								<div id="tooth-id-28" class="col-xs-1 up" data-value="28">28</div>
							</div>
						</div>
						<div class="col-sm-6 man border-right add-border-btm">
							<div class="row row-xs-1 downaddbg">
								<div id="tooth-id-48" class="col-xs-1 up" data-value="48" style="margin-left: 40px;">48</div>
								<div id="tooth-id-47" class="col-xs-1 up" data-value="47">47</div>
								<div id="tooth-id-46" class="col-xs-1 up" data-value="46">46</div>
								<div id="tooth-id-45" class="col-xs-1 up" data-value="45">45</div>
								<div id="tooth-id-44" class="col-xs-1 up" data-value="44">44</div>
								<div id="tooth-id-43" class="col-xs-1 up" data-value="43">43</div>
								<div id="tooth-id-42" class="col-xs-1 up" data-value="42">42</div>
								<div id="tooth-id-41" class="col-xs-1 up" data-value="41">41</div>
							</div>
						</div>
						<div class="col-sm-6 man">
							<div class="row row-xs-1 downaddbg">
								<div id="tooth-id-31" class="col-xs-1 up" data-value="31" style="margin-left:24px;">31</div>
								<div id="tooth-id-32" class="col-xs-1 up" data-value="32">32</div>
								<div id="tooth-id-33" class="col-xs-1 up" data-value="33">33</div>
								<div id="tooth-id-34" class="col-xs-1 up" data-value="34">34</div>
								<div id="tooth-id-35" class="col-xs-1 up" data-value="35">35</div>
								<div id="tooth-id-36" class="col-xs-1 up" data-value="36">36</div>
								<div id="tooth-id-37" class="col-xs-1 up" data-value="37">37</div>
								<div id="tooth-id-38" class="col-xs-1 up" data-value="38">38</div>
							</div>
						</div>
					</div>
				</div>
				<%--乳牙 start--%>
				<div class="col-sm-11 middle  allborder babytooth" style="display: none">
					<div class="row">
						<div class="col-sm-6 babys  border-right add-border-btn" style="border-bottom: 1px solid black;">
							<div class="row row-xs-1 upaddbg">
								<div id="tooth-id-56" class="col-xs-1 up" data-value="56" style="margin-left: 68px;">56</div>
								<div id="tooth-id-55" class="col-xs-1 up" data-value="55">55</div>
								<div id="tooth-id-54" class="col-xs-1 up" data-value="54">54</div>
								<div id="tooth-id-53" class="col-xs-1 up" data-value="53">53</div>
								<div id="tooth-id-52" class="col-xs-1 up" data-value="52">52</div>
								<div id="tooth-id-51" class="col-xs-1 up" data-value="51">51</div>
							</div>
						</div>
						<div class="col-sm-6 babys" style="border-bottom: 1px solid black;">
							<div class="row row-xs-1 upaddbg">
								<div id="tooth-id-61" class="col-xs-1 up" data-value="61" style="margin-left:10px;">61</div>
								<div id="tooth-id-62" class="col-xs-1 up" data-value="62">62</div>
								<div id="tooth-id-63" class="col-xs-1 up" data-value="63">63</div>
								<div id="tooth-id-64" class="col-xs-1 up" data-value="64">64</div>
								<div id="tooth-id-65" class="col-xs-1 up" data-value="65">65</div>
								<div id="tooth-id-66" class="col-xs-1 up" data-value="66">66</div>
							</div>
						</div>
						<div class="col-sm-6 babys border-right add-border-btm">
							<div class="row row-xs-1 downaddbg">
								<div id="tooth-id-86" class="col-xs-1 up" data-value="86" style="margin-left: 68px;">86</div>
								<div id="tooth-id-85" class="col-xs-1 up" data-value="85">85</div>
								<div id="tooth-id-84" class="col-xs-1 up" data-value="84">84</div>
								<div id="tooth-id-83" class="col-xs-1 up" data-value="83">83</div>
								<div id="tooth-id-82" class="col-xs-1 up" data-value="82">82</div>
								<div id="tooth-id-81" class="col-xs-1 up" data-value="81">81</div>
							</div>
						</div>
						<div class="col-sm-6 babys">
							<div class="row row-xs-1 downaddbg">
								<div id="tooth-id-71" class="col-xs-1 down" data-value="71" style="margin-left:10px;">71</div>
								<div id="tooth-id-72" class="col-xs-1 down" data-value="72">72</div>
								<div id="tooth-id-73" class="col-xs-1 down" data-value="73">73</div>
								<div id="tooth-id-74" class="col-xs-1 down" data-value="74">74</div>
								<div id="tooth-id-75" class="col-xs-1 down" data-value="75">75</div>
								<div id="tooth-id-76" class="col-xs-1 down" data-value="76">76</div>
							</div>
						</div>
					</div>
				</div>
				<%--乳牙 end--%>
			</div>
		</div>

		<br class="clear" />
		<div class="col-sm-2">
			<div class="row">
				主诉
			</div>
		</div>
		<div class="col-sm-8">
			<div class="row">
				<div class="form-group">
					<textarea id="zs" class="form-control" rows="3" readonly>${hisPatientCase.zs}</textarea>
				</div>
			</div>
		</div>
		<br class="clear" />
		<div class="col-sm-2">
			<div class="row">
				现病史
			</div>
		</div>
		<div class="col-sm-8">
			<div class="row">
				<div class="form-group">
					<textarea id="xbs"  class="form-control" rows="3" readonly>${hisPatientCase.xbs}</textarea>
				</div>
			</div>
		</div>
		<br class="clear" />
		<div class="col-sm-2">
			<div class="row">
				既往病史
			</div>
		</div>
		<div class="col-sm-8">
			<div class="row">
				<div class="form-group">
					<textarea id="jwbs" class="form-control" rows="3" placeholder="" readonly>${hisPatientCase.jwbs}</textarea>
				</div>
			</div>
		</div>
		<br class="clear" />
		<div class="col-sm-2">
			<div class="row">
				检查
			</div>
		</div>
		<div class="col-sm-8">
			<div class="row">
				<div class="form-group">
					<textarea id="jc" class="form-control" rows="3" readonly>${hisPatientCase.jc}</textarea>
				</div>
			</div>
		</div>
		<br class="clear" />
		<div class="col-sm-2">
			<div class="row">
				诊断
			</div>
		</div>
		<div class="col-sm-8">
			<div class="row">
				<div class="form-group">
					<textarea id="zd" class="form-control" rows="3"  readonly>${hisPatientCase.zd}</textarea>
				</div>
			</div>
		</div>
		<br class="clear" />
		<div class="col-sm-2">
			<div class="row">
				处理
			</div>
		</div>
		<div class="col-sm-8">
			<div class="row">
				<div class="form-group">
					<textarea id="cl" class="form-control" rows="3" readonly>${hisPatientCase.cl}</textarea>
				</div>
			</div>
		</div><br class="clear" />
		<div class="col-sm-2">
			<div class="row">
				医嘱
			</div>
		</div>
		<div class="col-sm-8">
			<div class="row">
				<div class="form-group">
					<textarea id="yz" class="form-control" rows="3" placeholder="注意事项如下" readonly>${hisPatientCase.yz}</textarea>
				</div>
			</div>
		</div><br class="clear" />
		<div class="col-sm-2">
			<div class="row">
				就诊事项
			</div>
		</div>
		<div class="col-sm-8">
			<div class="row">
				<div class="form-group">
					<textarea id="jzsx" class="form-control" rows="3" placeholder="请填写就诊事项" readonly>${hisPatientCase.jzsx}</textarea>
				</div>
			</div>
		</div><br class="clear" />
		<div class="col-sm-2">
			<div class="row">
				备注
			</div>
		</div>
		<div class="col-sm-8">
			<div class="row">
				<div class="form-group">
					<textarea id="remark" class="form-control" rows="3" placeholder="请填写备注" readonly>${hisPatientCase.remark}</textarea>
				</div>
			</div>
		</div>
		<div class="col-sm-12">
	</div>
</div>
</body>

<!--@*1、Jquery组件引用*@-->
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
	//牙位选择
	$(function(){
		var tooth = $('#toothId').val();
		console.info(tooth)
		console.info(tooth.split(","));

		if(tooth.split(",")[0]<51){
			$(".mantooth").css("display","block");
			$(".babytooth").css("display","none");
		}else{
			$(".mantooth").css("display","none");
			$(".babytooth").css("display","block");
		}
		$.each(tooth.split(","),function(i,n) {
			console.info(n);
//			$('#tooth-id-'+n).addClass("myselected");
			$('#tooth-id-'+n).css("background","gray");
		});
	});
</script>


</html>