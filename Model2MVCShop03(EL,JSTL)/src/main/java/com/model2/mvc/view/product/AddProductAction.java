package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class AddProductAction extends Action {

	public AddProductAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int price = Integer.parseInt(request.getParameter("price"));
		
		Product product = new Product();
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setManuDate(request.getParameter("manuDate"));
		product.setPrice(price);
		product.setFileName(request.getParameter("fileName"));
		
		System.out.println(product+" - AddProductAction");
		
		ProductService service = new ProductServiceImpl();
		//System.out.println("ProductService ���� - AddProductAction");
		service.addProduct(product);
		request.setAttribute("product", product);	//addProductView���� ���� data�� �̰����� ���� �� addProduct�� forward(redirect�� �Ϲ������� response�� �ϰ� �ٽ� request �ϱ� ������ request�� ����� data�� ���ư�)
		//System.out.println("addProduct ���� �Ϸ� - AddProductAction");
		
		return "forward:/product/addProduct.jsp";	//��ǰ ������ �Է��ϰ� �Ϸ��� �ڿ��� ��� �������� �̵��ؾ� ������?
	}

}
