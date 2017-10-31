package com.quickveggies.entities;

import com.quickveggies.db.erp.gl.GeneralLedgerSystem;
import java.io.InputStream;

public class Expenditure extends GeneralLedgerSystem.LedgingEntity {

	private int id;
        
	private final int companyId;

	private String payee;

	private String type;

	private String amount;
	
	private String date;
	
	private String comment;
	
	private InputStream receipt;

        public Expenditure()
        {
            this.companyId = Company.getCurrentCompany().getId();
        }

        public Expenditure(int id, int companyId, String payee, String type, String amount, String date, String comment, InputStream receipt)
        {
            this.id = id;
            this.companyId = companyId;
            this.payee = payee;
            this.type = type;
            this.amount = amount;
            this.date = date;
            this.comment = comment;
            this.receipt = receipt;
        }
        
	public int getId() {
		return id;
	}

        @Deprecated
	public void setId(int id) {
		this.id = id;
	}
        
	public int getCompanyId() {
		return this.companyId;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public InputStream getReceipt() {
		return receipt;
	}

	public void setReceipt(InputStream receipt) {
		this.receipt = receipt;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

    @Override
    public boolean equals(GeneralLedgerSystem.LedgingEntity obj)
    {
        return this.id == ((Expenditure)obj).id;
    }
}
