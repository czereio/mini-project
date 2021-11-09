<%@ page contentType="text/html; charset=EUC-KR" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>상품 목록 조회</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<link href="/css/animate.min.css" rel="stylesheet">
   	<link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="/javascript/bootstrap-dropdownhover.min.js"></script>
	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	
	<style>
	  body {
            padding-top : 50px;
        }
    </style>
	
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
		$( "td.ct_btn01:contains('검색')" ).on("click" , function() {
			//Debug..
			//alert(  $( "td.ct_btn01:contains('검색')" ).html() );
			fncGetList(1);
		});
		
		$( "td:nth-child(2)" ).on("click" , function() {
			 self.location ="/product/getProduct?prodNo="+$(this).find("input[name=prodNo]").val();
		});
					
		//==> userId LINK Event End User 에게 보일수 있도록 
		$( "td:nth-child(2)" ).css("color" , "red");
		
		
		$( "td:nth-child(6) > i" ).on("click" , function() {
			//Debug..
			//alert($(".ct_list_pop td:nth-child(3)").attr("data-prodNo"));
			//self.location ="/product/getProduct?prodNo="+$(".ct_list_pop td:nth-child(3)").attr("data-prodNo");//attributer로 접근 가능
			//var prodNo = $(".ct_list_pop td:nth-child(3)").attr("data-prodNo");
			var prodNo = $(this).find("input[name=prodNo2]").val();
			alert(prodNo);
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
							
							var displayValue =  "<br/><h7>"
												+"상품명 : "+JSONData.prodName+"<br/>"
												+"가   격 : "+JSONData.price+"<br/>"
												+"등록일 : "+JSONData.regDateString+"<br/><br/>"
												+"<c:if test="${user.role == 'admin'}">"
												+"<a href='/product/updateProduct?prodNo="+JSONData.prodNo+"'>수정</a>"
												+"</c:if></h7>";
							//Debug...									
							//alert(displayValue);
							$("h7").remove();
							$( "#"+prodNo+"" ).html(displayValue);
						}
				});
		});
		
		$()
		
		$( ".ct_list_pop td:nth-child(3)" ).css("color" , "red");
		$("h7").css("color" , "red");
		
		$(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");
		
	})


</script>
</head>

<body>
	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
	
		<div class="page-header text-info">
	       <h3>상품목록조회</h3>
	    </div>
	    
	    <!-- table 위쪽 검색 Start /////////////////////////////////////-->
	    <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
		    	</p>
		    </div>
		    
		    <div class="col-md-6 text-right">
			    <form class="form-inline" name="detailForm">
			    
				  <div class="form-group">
				    <select class="form-control" name="searchCondition" >
						<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품번호</option>
						<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>상품명</option>
						<option value="2"  ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>가격</option>
					</select>
				  </div>
				  
				  <div class="form-group">
				    <label class="sr-only" for="searchKeyword">검색어</label>
				    <input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="검색어"
				    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
				  </div>
				  
				  <button type="button" class="btn btn-default">검색</button>
				  
				  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				  <input type="hidden" id="currentPage" name="currentPage" value=""/>
				  
				</form>
	    	</div>
	    	
		</div>
		<!-- table 위쪽 검색 Start /////////////////////////////////////-->
		
		
      <!--  table Start /////////////////////////////////////-->
      <table class="table table-hover table-striped" >
      
        <thead>
          <tr>
            <th align="center">No</th>
            <th align="left" >상품명</th>
            <th align="left">가격</th>
            <th align="left">등록일</th>
            <th align="left">현재상태</th>
            <th align="left">간략정보</th>
          </tr>
        </thead>
       
		<tbody>
		
		  <c:set var="i" value="0" />
		  <c:forEach var="product" items="${list}">		<!-- copy & paste는 신중히 -->
			<c:set var="i" value="${ i+1 }" />
			<tr>
			  <td align="center">${ i }</td>
			  <td align="left"  title="Click : 상품 정보 확인" data-prodNo="${product.prodNo}">${product.prodName}
			  	<input type="hidden" name="prodNo" value="${product.prodNo}" />
			  </td>
			  <td align="left">${product.price}</td>
			  <td align="left">${product.regDate}</td>
			  <td align="left">판매중</td>
			  <td align="left">
			  	<i class="glyphicon glyphicon-ok" id= "${product.prodNo}">
			  		<input type="hidden" name="prodNo2" value="${product.prodNo}">
			  	</i>
			  		
			  </td>
			</tr>
          </c:forEach>
        
        </tbody>
      
      </table>
	  <!--  table End /////////////////////////////////////-->
	  
 	</div>
 	<!--  화면구성 div End /////////////////////////////////////-->
 	
 	
 	<!-- PageNavigation Start... -->
	<jsp:include page="../common/pageNavigator_new.jsp"/>
	<!-- PageNavigation End... -->
	

</body>
</html>