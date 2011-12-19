package Bean;


import java.sql.SQLException;

import Bigproject3.*;

public class AccountListBean 
{
	private int accountAmount = 0; 
	
	private getAccountIDs accountIDs;
	private getAccount [] accounts;
	
	///////account////
	private String [] userNames;
	private String [] passWords;
	private int [] money;
	private int [] level;
	private int [] recordamount;
	//private int [] taskenabledamount;
	
	
	
	
	
	

	public static void main(String[]args) throws SQLException
	{
		new AccountListBean().fortest();
	}
	
	public AccountListBean() throws SQLException 
	{
		accountIDs = new getAccountIDs();
		
		this.accountAmount = accountIDs.getNumOfAccouts();
		
		accounts = new getAccount[this.accountAmount];
		
		userNames = new String[this.accountAmount];
		passWords = new String[this.accountAmount];
		money = new int [this.accountAmount];
		level = new int [this.accountAmount];
		recordamount = new int [this.accountAmount];
		//taskenabledamount = new int [this.accountAmount];
		
		for(int i = 0;i<this.accountAmount;i++)
		{
			String tempAccountID = accountIDs.getTheAccount(i);
			accounts[i] = new getAccount(tempAccountID);
			userNames[i] = accounts[i].getID();
			passWords[i] = accounts[i].getPassWord();
			money[i] = accounts[i].getMoney();
			level[i] = accounts[i].getLevel();
			recordamount[i] = accounts[i].getRecordAmount();
			
		}
		
	}
	
	public int getAccountAmount()
	{
		return this.accountAmount;
	}
	public String [] getUserNames()
	{
		return this.userNames;
	}
	public String[] getPassWrod()
	{
		return this.passWords;
	}
	public int [] getMoney()
	{
		return money;
	}
	public int [] getLevel()
	{
		return this.level;
	}
	public int [] getRecordAmount()
	{
		return this.recordamount;
	}
	public void fortest()
	{
		for(int i = 0;i<this.accountAmount;i++)
		{
			System.out.println(this.userNames[i]+" : "+this.passWords[i]+" "+this.level[i]+
					" "+this.money[i]+" "+this.recordamount[i]);
		}
	}
	
}