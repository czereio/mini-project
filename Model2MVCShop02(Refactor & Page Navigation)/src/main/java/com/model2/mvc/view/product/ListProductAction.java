package com.model2.mvc.view.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
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
		
		System.out.println("hi - ListProductAction");
		
		Search search = new Search();
		
		int currentPage = 1;	//���� �������� initial value

		System.out.println(request.getParameter("currentPage")+" - ListProductAction");	//jsp file���� function�� ���� ���������� �ʾ� ���� ����. -�ذ�
		
		if(request.getParameter("currentPage") != null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		System.out.println(currentPage+" - ListProductAction");
		
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		
		System.out.println("ListProductAction searchCondition : " + request.getParameter("searchCondition"));
		System.out.println("ListProductAction searchKeyword : " + request.getParameter("searchKeyword"));
		
		// web.xml  meta-data �� ���� ��� ���� 
		int pageSize = Integer.parseInt(getServletContext().getInitParameter("pageSize"));
		int pageUnit = Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		search.setPageSize(pageSize);
		
		//System.out.println("ListProductAction pageSize : " + pageSize);
		//System.out.println("ListProductAction pageUnit : " + pageUnit);
		
		// Business logic ����
		ProductService productService = new ProductServiceImpl();
		Map<String , Object> map = productService.getProductList(search);
		
		Page resultPage	= 
					new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("ListProductAction ::"+resultPage);
		
		
		
		// Model �� View ����
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		
		return "forward:/product/listProduct.jsp";
		
	}

}
