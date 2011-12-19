package Bean;

import java.io.Serializable;

public class Account implements Serializable{
	private String username;
	private String password;
	
	//private TaskList[] tasklist;
	
	public Account(){
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
	
}