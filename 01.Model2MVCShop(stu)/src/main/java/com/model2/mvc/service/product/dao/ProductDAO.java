package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;


public class ProductDAO {
	
	public ProductDAO(){
	}

	public void insertProduct(ProductVO productVO) throws Exception {
		
		Connection con = null;
		con = DBUtil.getConnection();

		String sql = "INSERT INTO product VALUES(SEQ_PRODUCT_PROD_NO.NEXTVAL,?,?,?,?,?,sysdate)";	//������� ������ ũ�Ⱑ �� �ٲ�µ� sql���� �ٲ���� �ϳ�
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.executeUpdate();
		
		con.close();
		System.out.println("DB�� Query ���� �Ϸ�");
	}	//insertProduct(productVO) complete

	public ProductVO findProduct(int prodNo) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "SELECT * FROM product WHERE prod_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);

		ResultSet rs = stmt.executeQuery();

		ProductVO productVO = new ProductVO();
		while (rs.next()) {
			//productVO = new ProductVO();
			productVO.setProdNo(rs.getInt("PROD_NO"));
			productVO.setProdName(rs.getString("PROD_NAME"));
			productVO.setProdDetail(rs.getString("PROD_DETAIL"));
			productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
			productVO.setPrice(rs.getInt("PRICE"));
			productVO.setFileName(rs.getString("IMAGE_FILE"));
			productVO.setRegDate(rs.getDate("REG_DATE"));
		}
		System.out.println(productVO+" - ProductDAO");
		
		con.close();

		return productVO;
	}	//findProduct(prodNo) complete

	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM product ";
		if (searchVO.getSearchCondition() != null) {	//�˻� �� ��ǰ��ȣ(value=0), ��ǰ��(value=1), ��ǰ����(value=2) �� �ش��ϴ� ��ȣ�� ��ġ�� ���� query �Է�
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " WHERE prod_no="+Integer.parseInt(searchVO.getSearchKeyword());
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " WHERE prod_name='"+searchVO.getSearchKeyword()+"'";
			} else {
				sql += " WHERE price="+Integer.parseInt(searchVO.getSearchKeyword());
			}
		}
		sql += " ORDER BY prod_no";

		PreparedStatement stmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery();

		rs.last();
		int total = rs.getRow();
		System.out.println("�ο��� ��:" + total);

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage() :: "+searchVO.getPage());
		System.out.println("searchVO.getPageUnit() :: "+searchVO.getPageUnit());

		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				ProductVO productVO = new ProductVO();
				productVO.setProdNo(rs.getInt("PROD_NO"));
				productVO.setProdName(rs.getString("PROD_NAME"));
				productVO.setProdDetail(rs.getString("PROD_DETAIL"));
				productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
				productVO.setPrice(rs.getInt("PRICE"));
				productVO.setFileName(rs.getString("IMAGE_FILE"));
				productVO.setRegDate(rs.getDate("REG_DATE"));

				list.add(productVO);
				if (!rs.next()) break;
			}
		}
		System.out.println("list.size() :: "+list.size());
		map.put("list", list);
		System.out.println("map().size() :: "+map.size());

		con.close();
			
		return map;
	}	//getProductList(searchVO) complete ?

	public void updateProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "UPDATE product SET prod_name=?,prod_detail=?,manufacture_day=?,price=?,image_file=?,reg_date=sysdate WHERE prod_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.setInt(6, productVO.getProdNo());
		stmt.executeUpdate();
		
		con.close();
	}	//updateProduct(productVO) complete
}