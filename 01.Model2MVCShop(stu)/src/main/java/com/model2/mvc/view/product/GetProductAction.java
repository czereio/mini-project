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
		
		String history = "";		//history.jsp���� ','�� parsing �Ͽ� �� �迭�� ������ string
		Cookie[] cookies = request.getCookies();	//session id�� ������ ��� cookie���� ������ �迭
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals("history")) {	//cookie�� name�� history�� ��쿡 �����ų �׸�
				history = cookie.getValue();	//cookie�� value�� history�� ����
				history += ","+request.getParameter("prodNo");	//cookie�� name�� history�� ��� ���� ','�� �̾�ٿ� history.jsp���� parsing �ϰ� ��
				response.addCookie( new Cookie("history", history) );
			}
		}
		
		System.out.println(history);
		//Cookie cookie = new Cookie("prodNo", request.getParameter("prodNo"));
		//response.addCookie( new Cookie("history", request.getParameter("prodNo")) ); //�� �ֱ� �� ��ǰ �׸��� ������ �ǰ� ����?
		
		//String cookie = null;
		
		return "forward:/product/getProduct.jsp";
	}
}