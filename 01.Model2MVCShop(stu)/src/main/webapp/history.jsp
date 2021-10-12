<%@ page contentType="text/html; charset=EUC-KR" %>

<html>
<head>

<title>열어본 상품 보기</title>

</head>
<body>
	당신이 열어본 상품을 알고 있다
<br>
<br>
<%	//왜 최근 본 상품 목록이 쌓이는 게 아니고 가장 최근에 본 품목 하나만 cookie로 받아서 출력하는 거지...? --> GetProductAction에서 해결
	request.setCharacterEncoding("euc-kr");
	response.setCharacterEncoding("euc-kr");
	
	String history = "";
	Cookie[] cookies = request.getCookies();
	if (cookies != null && cookies.length > 0) {
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals("history")) {
				history = cookie.getValue()+",";
			}
		}
		
		System.out.println(history+" - history.jsp");
		
		if (history != null || history != "") {
			String[] h = history.split(",");
			for (int i = 0; i < h.length; i++) {
				if (!h[i].equals("null")) {
%>
<a href="/getProduct.do?prodNo=<%=h[i]%>&menu=search" target="rightFrame"><%=h[i]%></a>
<br>
<%
				}
			}
		}
	}
%>

</body>
</html>