<%@ page contentType="text/html; charset=EUC-KR" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>��ǰ ��� ��ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">

	function fncGetList(currentPage) {
		/*
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	   	*/
	   	$("#currentPage").val(currentPage)
	   	$("form").attr("method", "POST").attr("action","/product/listProduct").submit();
	}
	
	$(function() {
		$( "td.ct_btn01:contains('�˻�')" ).on("click" , function() {
			//Debug..
			//alert(  $( "td.ct_btn01:contains('�˻�')" ).html() );
			fncGetList(1);
		});
		
		//��ǰ �������� �� �ҷ������µ� . . . ���� �̰� . . .
		$( ".ct_list_pop td:nth-child(3)" ).on("click" , function() {
			//Debug..
			//alert($(".ct_list_pop td:nth-child(3)").attr("data-prodNo"));
			//self.location ="/product/getProduct?prodNo="+$(".ct_list_pop td:nth-child(3)").attr("data-prodNo");//attributer�� ���� ����
			//var prodNo = $(".ct_list_pop td:nth-child(3)").attr("data-prodNo");
			var prodNo = $(this).find("input[name=prodNo]").val();
			//alert(prodNo);
			$.ajax( 
					{
						url : "/product/json/getProduct/"+prodNo ,
						method : "GET" ,
						dataType : "json" ,
						headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
						success : function(JSONData , status) {

							//Debug...
							//alert(status);
							//Debug...
							//alert("JSONData : \n"+JSONData);
							
							var displayValue = "<h3>"
														+"��ǰ�� : "+JSONData.prodName+"<br/>"
														+"��   �� : "+JSONData.price+"<br/>"
														+"����� : "+JSONData.regDateString+"<br/><br/>"
														+"<a href='/product/getProduct?prodNo="+JSONData.prodNo+"'>������</a>"
														+"<c:if test="${user.role == 'admin'}">"
														+"<a href='/product/updateProduct?prodNo="+JSONData.prodNo+"'> / ����</a>"
														+"</c:if>"
														+"</h3>";
							//Debug...									
							//alert(displayValue);
							$("h3").remove();
							$( "#"+prodNo+"" ).html(displayValue);
						}
				});
		});
		
		
		$( ".ct_list_pop td:nth-child(3)" ).css("color" , "red");
		$("h7").css("color" , "red");
		
		$(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");
		
	})


</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">
<!-- <form name="detailForm" action="/product/listProduct?menu=manage" method="post"> -->
<form name="detailForm">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">��ǰ ����	
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" ${ ! empty search.searchCondition && search.searchCondition == 0 ? "selected" : ""}>��ǰ��ȣ</option>
				<option value="1" ${ ! empty search.searchCondition && search.searchCondition == 1 ? "selected" : ""}>��ǰ��</option>
				<option value="2" ${ ! empty search.searchCondition && search.searchCondition == 2 ? "selected" : ""}>��ǰ����</option>
			</select>
			<input type="text" name="searchKeyword" value="${ ! empty search.searchKeyword ? search.searchKeyword : ""}" class="ct_input_g" style="width:200px; height:19px" />
		</td>
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<!-- <a href="javascript:fncGetList(1);">�˻�</a> -->	<!-- function fncGetProductList(currentPage)�� �μ��� currentPage ���� �Է������� �ʾ� �߻��� ���� -->
						�˻�
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >��ü ${resultPage.totalCount} �Ǽ�, ���� ${resultPage.currentPage} ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">
			��ǰ��<br/>
			<h7>(click : view product detail)</h7>
		</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">�������</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	
	<c:set var="i" value="0"/>
	<c:forEach var="product" items="${list}">
		<c:set var="i" value="${i+1}"/>
		<tr class="ct_list_pop">
			<td align="center">${i}</td>	<%-- ���� ��� �� list.size()�� ������ 1�� �����Ͽ� ��� --%>
			<td></td>
			<td align="left" data-prodNo="${product.prodNo}"><!-- data-prodNo :: ���� �޾ƿ��� ������ ȭ�鿡 ����ϰ� ������ ���� �� ���� attributer -->
				<!-- <a href="/product/getProduct?prodNo=${product.prodNo}">${product.prodName}</a> -->
				<input type="hidden" name="prodNo" value="${product.prodNo}"/>
				${product.prodName}
			</td>
			<td></td>
			<td align="left">${product.price}</td>
			<td></td>
			<td align="left">${product.regDate}</td>
			<td></td>
			<td align="left"><%--=productVO.getTranCode()--%>�Ǹ���</td>	
		</tr>
		<tr>
			<td id="${product.prodNo}" colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>	
	</c:forEach>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		<input type="hidden" id="currentPage" name="currentPage" value=""/>
			<jsp:include page="../common/pageNavigator.jsp"/>
    	</td>
	</tr>
</table>
<!--  ������ Navigator �� -->

</form>

</div>
</body>
</html>