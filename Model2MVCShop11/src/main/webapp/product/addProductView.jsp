<%@ page contentType="text/html; charset=EUC-KR" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
  <script>
	
	<!-- <script type="text/javascript" src="../javascript/calendar.js"> -->
	</script>
	
	<style>
       body {
        	padding-top : 50px;
        }
    </style>
    
	
	<script type="text/javascript">
	
		$(function() {
			$("button.btn.btn-primary").on("click", function() {
				fncAddProduct();
			});
			
			$("a[href='#' ]").on("click" , function() {
				$("form")[0].reset();
			});
		})
	
		function fncAddProduct(){
			//Form 유효성 검증
		 	var name = $("input[name=prodName]").val();//document.detailForm.prodName.value;
			var detail = $("input[name=prodDetail]").val();//document.detailForm.prodDetail.value;
			var manuDate = $("input[name=manuDate]").val();//document.detailForm.manuDate.value;
			var price = $("input[name=price]").val();//document.detailForm.price.value;
		
			if(name == null || name.length<1){
				alert("상품명은 반드시 입력하여야 합니다.");
				return;
			}
			if(detail == null || detail.length<1){
				alert("상품상세정보는 반드시 입력하여야 합니다.");
				return;
			}
			if(manuDate == null || manuDate.length<1){
				alert("제조일자는 반드시 입력하셔야 합니다.");
				return;
			}
			if(price == null || price.length<1){
				alert("가격은 반드시 입력하셔야 합니다.");
				return;
			}
			/*
			document.detailForm.action='/product/addProduct';
			document.detailForm.submit();
			*/
			$("form").attr("method","POST").attr("action","/product/addProduct").submit();
		}
		
		$(function() {
			$( "td.ct_btn01:contains('등록')" ).on("click" , function() {
				//Debug..
				//alert(  $( "td.ct_btn01:contains('등록')" ).html() );
				fncAddProduct();
			});
			
			$( "td.ct_btn01:contains('취소')" ).on("click" , function() {
				//Debug..
				//alert(  $( "td.ct_btn01:contains('취소')" ).html() );
				$("form[name=detailForm]")[0].reset();
			});
			
			/* $(".input-group-addon").on("click", function() {		
				alert("왜 안 나오는데");
				$('#datepicker').datepicker();
				
			}); */
			
			$("#registerDate").on("click",function() {
				$( "#manuDate" ).datepicker({
				      showOtherMonths: true,
				      selectOtherMonths: true,
				      dateFormat: "yy-mm-dd"
				});
			});
			
			
			
			
		});
		/*
		function resetData(){
			document.detailForm.reset();
		}
		*/
	</script>
</head>

<body>

	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
	
		<div class="page-header text-center">
	       <h3 class=" text-info">상 품 등 록</h3>
	       <h5 class="text-muted">제조일자 등록이 <strong class="text-danger">왜</strong> 안 되는데?</h5>
	    </div>
	    
	    <!-- form Start /////////////////////////////////////-->
		<form class="form-horizontal">
		
		  <div class="form-group">
		    <label for="prodName" class="col-sm-offset-1 col-sm-3 control-label">상 품 명</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="prodName" name="prodName" placeholder="상품명을 입력해주세요.">
		    </div>
		  </div>
		
		  <div class="form-group">
		    <label for="prodDetail" class="col-sm-offset-1 col-sm-3 control-label">상품 상세정보</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="prodDetail" name="prodDetail" placeholder="상세정보를 입력해주세요.">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="manuDate" class="col-sm-offset-1 col-sm-3 control-label">제조일자</label>
		    <div class="col-sm-4">
		      <div class='input-group date' id='datepicker'>
	               <input type='text' class="form-control" id="manuDate" name="manuDate" placeholder="제조일자를 등록해주세요." >
	               <span class="input-group-addon">
	               	<span class="glyphicon glyphicon-calendar" id="registerDate"></span>
	               </span>
	               </input>
            </div>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="price" class="col-sm-offset-1 col-sm-3 control-label">가격</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="price" name="price" placeholder="상품 가격을 입력해주세요.">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="fileName" class="col-sm-offset-1 col-sm-3 control-label">상품 이미지</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="fileName" name="fileName" placeholder="상품 이미지를 첨부해주세요.">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <div class="col-sm-offset-4  col-sm-4 text-center">
		      <button type="button" class="btn btn-primary"  >수 &nbsp;정</button>
			  <a class="btn btn-primary btn" href="#" role="button">취 &nbsp;소</a>
		    </div>
		  </div>
		</form>
		<!-- form Start /////////////////////////////////////-->
	    
 	</div>
	<!--  화면구성 div Start /////////////////////////////////////-->
 	
</body>
</html>