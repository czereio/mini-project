package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class GetProductAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		ProductService service = new ProductServiceImpl();
		Product product = service.getProduct(prodNo);
		
		request.setAttribute("product", product);
		
		System.out.println(request.getParameter("prodNo")+" - GetProductAction");
		
		String history = "";		//history.jsp에서 ','를 parsing 하여 새 배열에 저장할 string
		Cookie[] cookies = request.getCookies();	//session id를 포함한 모든 cookie들을 저장할 배열
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals("history")) {	//cookie의 name이 history일 경우에 실행시킬 항목
				history = cookie.getValue();	//cookie의 value를 history에 저장
				history += ",";	//cookie의 name이 history일 경우 값을 ','로 이어붙여 history.jsp에서 parsing 하게 함
			}
		}
		
		history += request.getParameter("prodNo");	//cookie의 name이 history일 경우 값을 ','로 이어붙여 history.jsp에서 parsing 하게 함
		response.addCookie( new Cookie("history", history) );
		
		return "forward:/product/getProduct.jsp";
	}
}