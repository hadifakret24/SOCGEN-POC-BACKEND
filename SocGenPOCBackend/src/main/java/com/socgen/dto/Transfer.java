package com.socgen.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Transfer")
public class Transfer {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name = "date")
	private Date date ;
	
	@Column(name = "accountTransmitter")
	private String accountTransmitter ;
	
	@Column(name = "accountReceiver")
	private String accountReceiver;
	
	@Column(name = "amount")
	private String amount;
	
	@Column(name = "myBalance")
	private int myBalance;
	
	public Transfer() {
		
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccountTransmitter() {
		return accountTransmitter;
	}

	public void setAccountTransmitter(String accountTransmitter) {
		this.accountTransmitter = accountTransmitter;
	}

	public String getAccountReceiver() {
		return accountReceiver;
	}

	public void setAccountReceiver(String accountReceiver) {
		this.accountReceiver = accountReceiver;
	}

	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public int getMyBalance() {
		return myBalance;
	}
	public void setMyBalance(int myBalance) {
		this.myBalance = myBalance;
	}
	
	
	
	
}
