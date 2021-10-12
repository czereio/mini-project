package com.model2.mvc.service.product.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;

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
	public void addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		productDAO.insertProduct(product);
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return productDAO.findProduct(prodNo);
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return productDAO.getProductList(search);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		productDAO.updateProduct(product);
	}

}
