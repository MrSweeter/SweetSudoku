package com.mrsweeter.sweetsudoku.domains;

public class Chronometer {
	
	private long timeStart;
	private long pauseStart;
	private long pauseDuree;
	private long duree;
	private String timeEnd = null;
	
	public Chronometer()	{
		this(0);
	}
	
	public Chronometer(long time) {
		duree = time;
	}
	
	public void start()	{
		timeStart = System.currentTimeMillis();
		pauseStart = 0;
		pauseDuree = 0;
	}
	
	/**
	 * Put Chrono in pause
	 */
	public void pause()	{
		if (pauseStart == 0)	{
			pauseStart = System.currentTimeMillis();
			timeEnd = timeToString('h', 'm', 's', ' ');
		} else {
			pauseDuree = System.currentTimeMillis() - pauseStart;
			pauseStart = 0;
			duree -= pauseDuree;
			timeEnd = null;
		}
	}
	
	/**
	 * Stop the chrono
	 */
	public void stop()	{
		timeEnd = timeToString('h', 'm', 's', ' ');
	}
	
	/**
	 * Convert time of chrono to String
	 * @param h Character to show to say hours
	 * @param m Character to show to say minutes
	 * @param s Character to show to say secondes
	 * @param c Character to show to say hundredths of a second
	 * @return String time (format 00h00m00s000c)
	 */
	public String timeToString(char h, char m, char s, char c)	{
		
		if (timeEnd != null)	{return timeEnd;}
		
		long temp = (duree + (System.currentTimeMillis() - timeStart))/10;
		
		int hours = (int) (temp / 360000);
		int minutes = (int) ((temp % 360000) / 6000);
		int secondes = (int) (temp % 6000) / 100;
		int centiemes = (int) (temp % 100);
		
		if (c != ' ')	{
			return String.format("%02d" + h + "%02d" + m + "%02d" + s + "%02d0" + c, hours, minutes, secondes, centiemes);
		} else {
			return String.format("%02d" + h + "%02d" + m + "%02d" + s, hours, minutes, secondes);
		}
	}
}