package Bigproject3;


public class Date 
{
	public int year;
	public int month;
	public int day;
	public int hour;
	public int minute;
	public Date(int year,int month,int day,int hour,int minute)
	{
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}
	public int getYear()
	{
		return year;
	}
	public int getMonth()
	{
		return month;
	}
	public int getDay()
	{
		return day;
	}
	public int getHour()
	{
		return hour;
	}
	public int getMinute()
	{
		return minute;
	}
	public void setYear(int year)
	{
		this.year = year;
	}
	public void setMonth(int month)
	{
		this.month = month;
	}
	public void setDay(int day)
	{
		this.day = day;
	}
	public void setHour(int hour)
	{
		this.hour = hour;
	}
	public void setMinute(int minute)
	{
		this.minute = minute;
	}
	public String toString()
	{
		return year+"."+month+"."+day+" "+hour+":"+minute;
	}
	public String toTimeString()
	{
		String finalhour = "";
		String finalminute = "";
		if(hour<=9)
			finalhour = "0"+hour+"";
		else
			finalhour = hour+"";
		if(minute<=9)
			finalminute = "0"+minute+"";
		else
			finalminute = minute+"";
		return finalhour+":"+finalminute;
	}
	public String toDateString()
	{
		String finalmonth = "";
		String finalday = "";
		if(month<=9)
			finalmonth = "0"+month+"";
		else
			finalmonth = month+"";
		if(day<=9)
			finalday = "0"+day+"";
		else
			finalday = day+"";
		
		return year+"-"+finalmonth+"-"+finalday;
	}
	
}
