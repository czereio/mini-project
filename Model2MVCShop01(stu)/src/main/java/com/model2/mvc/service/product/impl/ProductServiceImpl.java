package com.model2.mvc.service.product.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;

public class ProductServiceImpl implements ProductService {

	//*Field
	private ProductDAO productDAO;
	
	//*Constructor
	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
		productDAO = new ProductDAO();	//field�� ���� �ϰ� ���� constructor ������ �� ���ִ� �ҷ��� ���� �翬�� ���� �׷��ϱ� null pointer �ߴ� �ſ���
	}
	
	//*Method
	@Override
	public void addProduct(ProductVO productVO) throws Exception {
		// TODO Auto-generated method stub
		productDAO.insertProduct(productVO);
	}

	@Override
	public ProductVO getProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return productDAO.findProduct(prodNo);
	}

	@Override
	public HashMap<String, Object> getProductList(SearchVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return productDAO.getProductList(searchVO);
	}

	@Override
	public void updateProduct(ProductVO productVO) throws Exception {
		// TODO Auto-generated method stub
		productDAO.updateProduct(productVO);
	}

}
