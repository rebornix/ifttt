package Bean;

import java.io.Serializable;
import java.sql.SQLException;

import Bigproject3.*;

public class RecordListBean implements Serializable
{
	private String userName = null;
	
	private int amount = 0;
	private getRecordIDs recordIDs;
	
	private getRecord []records ;
	private String []recordList;
	
	public void setUserName(String userName) throws SQLException
	{
		this.userName = userName;
		recordIDs = new getRecordIDs(this.userName);
		
		this.amount = recordIDs.getNumOfRecords();
		records = new getRecord[this.amount];
		recordList = new String[this.amount];
		
		for(int i = 0;i<this.amount;i++)
		{
			int tempRecordID = recordIDs.getTheRecord(i);
			records[i] = new getRecord(tempRecordID);
			recordList[i] = records[i].toString();
		}
	}
	public int getAmount()
	{
		return this.amount;
	}
	public String [] getString()
	{
		return recordList;
	}
	
	
}