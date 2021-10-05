package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;


public class GetProductAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		ProductService service = new ProductServiceImpl();
		ProductVO productVO = service.getProduct(prodNo);
		
		request.setAttribute("productVO", productVO);
		
		String history = "";		//history.jsp에서 ','를 parsing 하여 새 배열에 저장할 string
		Cookie[] cookies = request.getCookies();	//session id를 포함한 모든 cookie들을 저장할 배열
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals("history")) {	//cookie의 name이 history일 경우에 실행시킬 항목
				history = cookie.getValue();	//cookie의 value를 history에 저장
				history += ","+request.getParameter("prodNo");	//cookie의 name이 history일 경우 값을 ','로 이어붙여 history.jsp에서 parsing 하게 함
				response.addCookie( new Cookie("history", history) );
			}
		}
		
		System.out.println(history);
		//Cookie cookie = new Cookie("prodNo", request.getParameter("prodNo"));
		//response.addCookie( new Cookie("history", request.getParameter("prodNo")) ); //왜 최근 본 상품 항목이 갱신이 되고 있지?
		
		//String cookie = null;
		
		return "forward:/product/getProduct.jsp";
	}
}