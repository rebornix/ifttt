package Bigproject3;

import java.sql.SQLException;

public class taskList 
{
	private String userName = null;
	
	private int amount = 0;
	private getTask []tasks ;
	private getTaskIDs taskIDs;
	
	public taskList(String userName) throws SQLException
	{
		this.userName = userName;
		taskIDs = new getTaskIDs(this.userName);
		this.amount = taskIDs.getNumOfTasks();
		tasks = new getTask[this.amount];
		
		for(int i = 0;i<this.amount;i++)
		{
			int tempTaskID = taskIDs.getTheTask(i);
			tasks[i]=new getTask(tempTaskID);
		}
	}
	public getTask[] getTaskList(){
		return tasks;
	}
	public int getAmount()
	{
		return amount;
	}
}
