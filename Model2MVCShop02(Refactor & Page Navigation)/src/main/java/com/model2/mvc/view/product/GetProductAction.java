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
		
		String history = "";		//history.jsp���� ','�� parsing �Ͽ� �� �迭�� ������ string
		Cookie[] cookies = request.getCookies();	//session id�� ������ ��� cookie���� ������ �迭
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals("history")) {	//cookie�� name�� history�� ��쿡 �����ų �׸�
				history = cookie.getValue();	//cookie�� value�� history�� ����
				history += ",";	//cookie�� name�� history�� ��� ���� ','�� �̾�ٿ� history.jsp���� parsing �ϰ� ��
			}
		}
		
		history += request.getParameter("prodNo");	//cookie�� name�� history�� ��� ���� ','�� �̾�ٿ� history.jsp���� parsing �ϰ� ��
		response.addCookie( new Cookie("history", history) );
		
		return "forward:/product/getProduct.jsp";
	}
}