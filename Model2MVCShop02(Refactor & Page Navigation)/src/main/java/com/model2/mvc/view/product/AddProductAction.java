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
		//System.out.println("ProductService 생성 - AddProductAction");
		service.addProduct(product);
		request.setAttribute("product", product);	//addProductView에서 받은 data를 이곳에서 설정 후 addProduct로 forward(redirect는 암묵적으로 response를 하고 다시 request 하기 때문에 request에 저장된 data가 날아감)
		//System.out.println("addProduct 실행 완료 - AddProductAction");
		
		return "forward:/product/addProduct.jsp";	//상품 정보를 입력하고 완료한 뒤에는 어느 페이지로 이동해야 맞을까?
	}

}
