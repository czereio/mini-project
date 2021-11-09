package com.model2.mvc.service.purchase.vo;

import java.sql.Date;

import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.vo.UserVO;


public class PurchaseVO {
	
	private UserVO buyer;	//������ ����
	private String dlvyAddr;	//�����
	private String dlvyDate;	//�����
	private String dlvyRequest;	//��ۿ�û����
	private Date orderDate;		//�ֹ�����
	private String paymentOption;	//���� �ɼ�
	private ProductVO purchaseProd;	//�Ǹ� ��ǰ ����
	private String receiverName;	//������ �̸�
	private String receiverPhone;	//������ ��ȭ��ȣ
	private String tranCode;		//��ۻ���(�� ����)
	private int tranNo;		//��ǰ��ȣó�� ���ż����� ��ȣ �ű� ��
	
	public PurchaseVO(){
	}
	
	public UserVO getBuyer() {
		return buyer;
	}
	public void setBuyer(UserVO buyer) {
		this.buyer = buyer;
	}
	public String getlivyAddr() {
		return dlvyAddr;
	}
	public void setDlvyAddr(String dlvyAddr) {
		this.dlvyAddr = dlvyAddr;
	}
	public String getDlvyDate() {
		String[] date = dlvyDate.split("-");
		
		String result = "";
		for (int i = 0; i < date.length; i++) {
			result += date[i];
		}
		return result;
	}
	public void setDlvyDate(String divyDate) {
		this.dlvyDate = dlvyDate;
	}
	public String getDlvyRequest() {
		return dlvyRequest;
	}
	public void setDlvyRequest(String dlvyRequest) {
		this.dlvyRequest = dlvyRequest;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getPaymentOption() {
		return paymentOption;
	}
	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}
	public ProductVO getPurchaseProd() {
		return purchaseProd;
	}
	public void setPurchaseProd(ProductVO purchaseProd) {
		this.purchaseProd = purchaseProd;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getTranCode() {
		return tranCode;
	}
	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}
	public int getTranNo() {
		return tranNo;
	}
	public void setTranNo(int tranNo) {
		this.tranNo = tranNo;
	}
	
	@Override
	public String toString() {
		return "PurchaseVO [buyer=" + buyer + ", dlvyAddr=" + dlvyAddr
				+ ", dlvyDate=" + dlvyDate + ", dlvyRequest=" + dlvyRequest
				+ ", orderDate=" + orderDate + ", paymentOption="
				+ paymentOption + ", purchaseProd=" + purchaseProd
				+ ", receiverName=" + receiverName + ", receiverPhone="
				+ receiverPhone + ", tranCode=" + tranCode + ", tranNo="
				+ tranNo + "]";
	}
}