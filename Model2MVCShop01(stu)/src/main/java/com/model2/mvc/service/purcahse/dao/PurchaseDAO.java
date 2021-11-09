package com.model2.mvc.service.purcahse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.purchase.vo.PurchaseVO;


public class PurchaseDAO {
	
	public PurchaseDAO(){
	}

	public PurchaseVO findPurchase(int a) {
		return null;
	}
	
	public HashMap<String,Object> getPurchaseList(SearchVO searchVO, String b) {
		return null;
	}
	
	public HashMap<String,Object> getSaleList(SearchVO searchVO) {
		return null;
	}
	
	public void insertPurchase(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = null;
		con = DBUtil.getConnection();

		String sql = "INSERT INTO transaction VALUES(SEQ_TRANSACTION_TRAN_NO.NEXTVAL,?,?,?,?,?,sysdate)";	//등록일자 데이터 크기가 안 바뀌는데 sql에서 바꿔줘야 하나
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
		stmt.setString(2, purchaseVO.getBuyer().getUserId());
		stmt.setString(3, purchaseVO.getPaymentOption());
		stmt.setString(4, purchaseVO.getBuyer().getUserName());
		stmt.setString(5, purchaseVO.getBuyer().getPhone());
		stmt.setString(6, purchaseVO.getBuyer().getEmail());
		stmt.setString(7, purchaseVO.getDlvyRequest());
		stmt.setString(8, purchaseVO.getTranCode());
		stmt.setDate(9, purchaseVO.getOrderDate());
		stmt.setString(10, purchaseVO.getDlvyDate());
		
		stmt.executeUpdate();
		
		con.close();
		System.out.println("DB에 Query 전송 완료");
	}
	
	public void updatePurchase(PurchaseVO purchaseVO) {
		
	}
	
	public void updateTranCode(PurchaseVO purchaseVO) {
		
	}
}