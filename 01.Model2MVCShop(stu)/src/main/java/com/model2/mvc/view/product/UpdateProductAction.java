package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;


public class UpdateProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		int price = Integer.parseInt(request.getParameter("price"));
		
		ProductVO productVO = new ProductVO();
		productVO.setProdNo(prodNo);
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate"));
		productVO.setPrice(price);
		productVO.setFileName(request.getParameter("fileName"));
		
		ProductService service = new ProductServiceImpl();
		service.updateProduct(productVO);
		
		request.setAttribute("productVO", productVO);
		
		/*
		HttpSession session = request.getSession();
		String sessionId = ((ProductVO)session.getAttribute("user")).getProdNo();
	
		if(sessionId.equals(prodNo)){
			session.setAttribute("productVO", productVO);
		}
		*/
		return "redirect:/getProduct.do?prodNo="+prodNo;
	}
}