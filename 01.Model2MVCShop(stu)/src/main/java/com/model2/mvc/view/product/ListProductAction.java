package com.model2.mvc.view.product;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class ListProductAction extends Action {

	public ListProductAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
				
		SearchVO searchVO = new SearchVO();
		
		int page = 1;
		if(request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		
		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));	//검색 때 상품번호, 상품명, 상품가격 중 하나를 매개변수로 받는 것 같다.
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));		//검색어(String)
		
		String pageUnit=getServletContext().getInitParameter("pageSize");	//이 친구의 존재 이유가 뭘까
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		ProductService service = new ProductServiceImpl();
		HashMap<String,Object> map = service.getProductList(searchVO);

		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		
		return "forward:/product/listProduct.jsp";
	}

}
