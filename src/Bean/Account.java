package Bean;

import java.io.Serializable;

public class Account implements Serializable{
	private String username;
	private String password;
	private int money;
	private int level;
	private int taskamount;
	private int taskenabledamount;
	//private TaskList[] tasklist;
	
	public Account(){
		this.money = 100;
		this.level = 0;
		this.taskamount = 0;
		this.taskenabledamount = 0;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
		// initial psw money level taskamount...
		/*
		getAccount getaccount = new getAccount(username);
		this.password = getaccount.getPassWord();
		this.level = getaccount.getLevel();
		this.money = getaccount.getMoney();
		*/
	}
	public String getPassword() {
		return password;
	}
	public void  setPassword(String password) {
		this.password = password;
	}
	
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getTaskamount(){
		return taskamount;
	}
	public void setTaskamount(int taskamount) {
		this.taskamount = taskamount;
	}
	public int getTaskenabledamount(){
		return taskenabledamount;
	}
	public void setTaskenabledamount(int taskenabledamount){
		this.taskenabledamount = taskenabledamount;
	}
}