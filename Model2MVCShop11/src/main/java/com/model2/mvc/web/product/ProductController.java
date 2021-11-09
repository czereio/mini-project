package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

//==> 상관리 Controller
@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
		
	public ProductController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping(value="addProduct",method=RequestMethod.GET)
	public String addProduct() throws Exception {

		System.out.println("/product/addProduct : GET");
		
		return "forward:/product/addProductView.jsp";
	}
	
	@RequestMapping(value="addProduct",method=RequestMethod.POST)
	public String addProduct( @ModelAttribute("product") Product product) throws Exception {

		System.out.println("/product/addProduct : POST");
		//Business Logic
		productService.addProduct(product);
		
		return "forward:/product/addProduct.jsp";
	}
	
	@RequestMapping(value="getProduct",method=RequestMethod.GET)
	public String getProduct(@RequestParam("prodNo") int prodNo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		System.out.println("/product/getProduct : GET");
		//Business Logic
		Product product = productService.getProduct(prodNo);
		// Model 과 View 연결
		model.addAttribute("product", product);
		
		String history = "";		//history.jsp에서 ','를 parsing 하여 새 배열에 저장할 string
		Cookie[] cookies = request.getCookies();	//session id를 포함한 모든 cookie들을 저장할 배열
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals("history")) {	//cookie의 name이 history일 경우에 실행시킬 항목
				history = cookie.getValue();	//cookie의 value를 history에 저장
				history += ",";
			}
		}
		
		history += request.getParameter("prodNo");	//cookie의 name이 history일 경우 값을 ','로 이어붙여 history.jsp에서 parsing 하게 함
		Cookie cookie = new Cookie("history", history);
		cookie.setPath("/");
		response.addCookie(cookie);	//공통적인 쿠키 내용 추가 부분
		
		return "forward:/product/getProduct.jsp";
	}
	
	@RequestMapping(value="updateProduct",method=RequestMethod.GET)
	public String updateProduct( @RequestParam("prodNo") int prodNo , Model model ) throws Exception{

		System.out.println("/product/updateProduct : GET");
		//Business Logic
		Product product = productService.getProduct(prodNo);
		// Model 과 View 연결
		model.addAttribute("product", product);
		
		return "forward:/product/updateProductView.jsp";
	}
	
	@RequestMapping(value="updateProduct",method=RequestMethod.POST)
	public String updateProduct( @ModelAttribute("product") Product product , Model model , HttpSession session) throws Exception{

		System.out.println("/product/updateProduct : POST");
		//Business Logic
		productService.updateProduct(product);
		
		return "redirect:/product/getProduct?prodNo="+product.getProdNo();
	}
	
	@RequestMapping(value="listProduct")
	public String listProduct( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("/product/listProduct : GET / POST");
		
		if(search.getCurrentPage() == 0){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String , Object> map = productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		//System.out.println(map.get("list"));
		
		return "forward:/product/listProduct.jsp";
	}
}