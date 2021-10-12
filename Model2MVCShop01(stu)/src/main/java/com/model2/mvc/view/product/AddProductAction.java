package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class AddProductAction extends Action {

	public AddProductAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int price = Integer.parseInt(request.getParameter("price"));
		
		ProductVO productVO = new ProductVO();
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate"));
		productVO.setPrice(price);
		productVO.setFileName(request.getParameter("fileName"));
		
		System.out.println(productVO+" - AddProductAction");
		
		ProductService service = new ProductServiceImpl();
		System.out.println("ProductService ���� - AddProductAction");
		service.addProduct(productVO);
		request.setAttribute("productVO", productVO);	//addProductView���� ���� data�� �̰����� ���� �� addProduct�� forward(redirect�� �Ϲ������� response�� �ϰ� �ٽ� request �ϱ� ������ request�� ����� data�� ���ư�)
		System.out.println("addProduct ���� �Ϸ� - AddProductAction");
		
		return "forward:/product/addProduct.jsp";	//��ǰ ������ �Է��ϰ� �Ϸ��� �ڿ��� ��� �������� �̵��ؾ� ������?
	}

}
