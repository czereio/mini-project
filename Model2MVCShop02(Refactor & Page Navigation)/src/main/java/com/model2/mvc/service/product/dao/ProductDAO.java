package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;


public class ProductDAO {
	
	public ProductDAO(){
	}

	public void insertProduct(Product product) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "INSERT INTO product VALUES(SEQ_PRODUCT_PROD_NO.NEXTVAL,?,?,?,?,?,sysdate)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, product.getProdName());
		stmt.setString(2, product.getProdDetail());
		stmt.setString(3, product.getManuDate());
		stmt.setInt(4, product.getPrice());
		stmt.setString(5, product.getFileName());
		stmt.executeUpdate();
		
		con.close();
		System.out.println("DB�� Query ���� �Ϸ�");
	}	//insertProduct(productVO) complete

	public Product findProduct(int prodNo) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "SELECT * FROM product WHERE prod_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);

		ResultSet rs = stmt.executeQuery();

		Product product = new Product();
		while (rs.next()) {
			//productVO = new ProductVO();
			product.setProdNo(rs.getInt("PROD_NO"));
			product.setProdName(rs.getString("PROD_NAME"));
			product.setProdDetail(rs.getString("PROD_DETAIL"));
			product.setManuDate(rs.getString("MANUFACTURE_DAY"));
			product.setPrice(rs.getInt("PRICE"));
			product.setFileName(rs.getString("IMAGE_FILE"));
			product.setRegDate(rs.getDate("REG_DATE"));
		}
		System.out.println(product+" - ProductDAO");
		
		con.close();

		return product;
	}	//findProduct(prodNo) complete

	public Map<String,Object> getProductList(Search search) throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		Connection con = DBUtil.getConnection();
		
		System.out.println("productDao start");
		System.out.println(search.getSearchKeyword());
		
		String sql = "SELECT prod_no, prod_name, price, reg_date FROM product ";
		
		if (search.getSearchCondition() != null) {	//�˻� �� ��ǰ��ȣ(value=0), ��ǰ��(value=1), ��ǰ����(value=2) �� �ش��ϴ� ��ȣ�� ��ġ�� ���� query �Է�
			if (search.getSearchCondition().equals("0") && !search.getSearchCondition().equals("")) {
				sql += " WHERE prod_no LIKE '%"+search.getSearchKeyword()+"%'";
			} else if (search.getSearchCondition().equals("1") && !search.getSearchCondition().equals("")) {
				sql += " WHERE prod_name LIKE '%"+search.getSearchKeyword()+"%'";
			} else if (search.getSearchCondition().equals("2") && !search.getSearchCondition().equals("")){
				sql += " WHERE price LIKE '%"+search.getSearchKeyword()+"%'";
			}
		}
		sql += " ORDER BY prod_no";
		
		System.out.println("ProductDAO Original SQL :: " + sql);
		
		//totalCount get :: �� �ϴ� ����?
		int totalCount = this.getTotalCount(sql);
		System.out.println("ProductDAO totalCount :: " + totalCount);
		
		//--> currentPage �Խù��� �޵��� Query �ٽñ���
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		System.out.println(search);

		List<Product> list = new ArrayList<Product>();
		
		while(rs.next()){
			Product product = new Product();
			product.setProdNo(rs.getInt("PROD_NO"));	//�������� �� error :: �� ���� �پ��� ������ ���� ���� ������ �׷� �� ����
			product.setProdName(rs.getString("PROD_NAME"));
			product.setPrice(rs.getInt("PRICE"));
			product.setRegDate(rs.getDate("REG_DATE"));
			list.add(product);
		}
		
		//==> totalCount ���� ����
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage �� �Խù� ���� ���� List ����
		map.put("list", list);

		rs.close();
		pStmt.close();
		con.close();

		return map;
	}	//getProductList(searchVO) complete ?

	public void updateProduct(Product product) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "UPDATE product SET prod_name=?,prod_detail=?,manufacture_day=?,price=?,image_file=?,reg_date=sysdate WHERE prod_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, product.getProdName());
		stmt.setString(2, product.getProdDetail());
		stmt.setString(3, product.getManuDate());
		stmt.setInt(4, product.getPrice());
		stmt.setString(5, product.getFileName());
		stmt.setInt(6, product.getProdNo());
		stmt.executeUpdate();
		
		con.close();
	}	//updateProduct(productVO) complete
	
		// �Խ��� Page ó���� ���� ��ü Row�� ��(totalCount) return
		private int getTotalCount(String sql) throws Exception {
			
			sql = "SELECT COUNT(*) "+
			          "FROM ( " +sql+ ") countTable";
			
			Connection con = DBUtil.getConnection();
			PreparedStatement pStmt = con.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			int totalCount = 0;
			if( rs.next() ){
				totalCount = rs.getInt(1);	//column index number(prodNo) 
			}
			
			pStmt.close();
			con.close();
			rs.close();
			
			return totalCount;
		}
		
		// �Խ��� currentPage Row ��  return 
		private String makeCurrentPageSql(String sql , Search search){
			sql = 	"SELECT * "+ 
						"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
										" 	FROM (	"+sql+" ) inner_table "+
										"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
						"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
			
			System.out.println("UserDAO :: make SQL :: "+ sql);	
			
			return sql;
		}
}