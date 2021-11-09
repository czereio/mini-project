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
			//Form ��ȿ�� ����
		 	var name = $("input[name=prodName]").val();//document.detailForm.prodName.value;
			var detail = $("input[name=prodDetail]").val();//document.detailForm.prodDetail.value;
			var manuDate = $("input[name=manuDate]").val();//document.detailForm.manuDate.value;
			var price = $("input[name=price]").val();//document.detailForm.price.value;
		
			if(name == null || name.length<1){
				alert("��ǰ���� �ݵ�� �Է��Ͽ��� �մϴ�.");
				return;
			}
			if(detail == null || detail.length<1){
				alert("��ǰ�������� �ݵ�� �Է��Ͽ��� �մϴ�.");
				return;
			}
			if(manuDate == null || manuDate.length<1){
				alert("�������ڴ� �ݵ�� �Է��ϼž� �մϴ�.");
				return;
			}
			if(price == null || price.length<1){
				alert("������ �ݵ�� �Է��ϼž� �մϴ�.");
				return;
			}
			/*
			document.detailForm.action='/product/addProduct';
			document.detailForm.submit();
			*/
			$("form").attr("method","POST").attr("action","/product/addProduct").submit();
		}
		
		$(function() {
			$( "td.ct_btn01:contains('���')" ).on("click" , function() {
				//Debug..
				//alert(  $( "td.ct_btn01:contains('���')" ).html() );
				fncAddProduct();
			});
			
			$( "td.ct_btn01:contains('���')" ).on("click" , function() {
				//Debug..
				//alert(  $( "td.ct_btn01:contains('���')" ).html() );
				$("form[name=detailForm]")[0].reset();
			});
			
			/* $(".input-group-addon").on("click", function() {		
				alert("�� �� �����µ�");
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
	
	<!--  ȭ�鱸�� div Start /////////////////////////////////////-->
	<div class="container">
	
		<div class="page-header text-center">
	       <h3 class=" text-info">�� ǰ �� ��</h3>
	       <h5 class="text-muted">�������� ����� <strong class="text-danger">��</strong> �� �Ǵµ�?</h5>
	    </div>
	    
	    <!-- form Start /////////////////////////////////////-->
		<form class="form-horizontal">
		
		  <div class="form-group">
		    <label for="prodName" class="col-sm-offset-1 col-sm-3 control-label">�� ǰ ��</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="prodName" name="prodName" placeholder="��ǰ���� �Է����ּ���.">
		    </div>
		  </div>
		
		  <div class="form-group">
		    <label for="prodDetail" class="col-sm-offset-1 col-sm-3 control-label">��ǰ ������</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="prodDetail" name="prodDetail" placeholder="�������� �Է����ּ���.">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="manuDate" class="col-sm-offset-1 col-sm-3 control-label">��������</label>
		    <div class="col-sm-4">
		      <div class='input-group date' id='datepicker'>
	               <input type='text' class="form-control" id="manuDate" name="manuDate" placeholder="�������ڸ� ������ּ���." >
	               <span class="input-group-addon">
	               	<span class="glyphicon glyphicon-calendar" id="registerDate"></span>
	               </span>
	               </input>
            </div>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="price" class="col-sm-offset-1 col-sm-3 control-label">����</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="price" name="price" placeholder="��ǰ ������ �Է����ּ���.">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="fileName" class="col-sm-offset-1 col-sm-3 control-label">��ǰ �̹���</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="fileName" name="fileName" placeholder="��ǰ �̹����� ÷�����ּ���.">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <div class="col-sm-offset-4  col-sm-4 text-center">
		      <button type="button" class="btn btn-primary"  >�� &nbsp;��</button>
			  <a class="btn btn-primary btn" href="#" role="button">�� &nbsp;��</a>
		    </div>
		  </div>
		</form>
		<!-- form Start /////////////////////////////////////-->
	    
 	</div>
	<!--  ȭ�鱸�� div Start /////////////////////////////////////-->
 	
</body>
</html>