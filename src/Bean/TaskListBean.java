package Bean;

import java.io.Serializable;
import java.sql.SQLException;

import Bigproject3.getTask;
import Bigproject3.getTaskIDs;

public class TaskListBean implements Serializable
{
	private String userName = null;
	
	private int amount = 0;
	private getTaskIDs taskIDs;
	private getTask []tasks ;
	private TaskItem []taskItems;
	
	
	public void setUserName(String userName) throws SQLException
	{
		this.userName = userName;
		taskIDs = new getTaskIDs(this.userName);
		
		this.amount = taskIDs.getNumOfTasks();
		taskItems = new TaskItem[this.amount];
		tasks = new getTask[this.amount];
		
		for(int i = 0;i<this.amount;i++)
		{
			int tempTaskID = taskIDs.getTheTask(i);
			tasks[i]=new getTask(tempTaskID);
			taskItems[i] = new TaskItem(tempTaskID);
		}
	}
	
	public TaskItem[] getTaskItem()
	{
		return taskItems;
	}
	public getTask[] getTaskList()
	{
		return tasks;
	}
	public int getAmount()
	{
		return amount;
	}
	
	
	
}