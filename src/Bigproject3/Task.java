package Bigproject3;


public class Task implements Runnable
{
	private int taskID;
	//private String taskname;
	
	private This thisClass;
	private That thatClass;
	
	public Task(int taskID,This thisClass,That thatClass)
	{
		this.taskID = taskID;
		this.thisClass = thisClass;
		this.thatClass = thatClass;
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				if(thisClass.doThis() == 1)
				{
					thatClass.dothat();
					break;
				}
				else if(thisClass.doThis()==3)
				{
					System.out.println("This time,there is no new weibo");
					break;
				}
				Thread.sleep(5000);
			}
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("Task "+taskID+" has completed.");
	}
		
}
