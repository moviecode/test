package com.itaiti.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class LocalDateTimeTest {

	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");

	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public final static String FULL_TIME_ZERO="yyyy-MM-dd 00:00:00";

	public static void main(String[] args) {
		/*Calendar calendar = Calendar.getInstance();   
		calendar.add(Calendar.DATE, -1);    //�õ�ǰһ��   
		calendar.add(Calendar.MONTH, -1);    //�õ�ǰһ����   
		int year = calendar.get(Calendar.YEAR);   
		int month = calendar.get(Calendar.MONTH)+1;   
		int day = calendar.get(Calendar.DATE);
		System.out.println(year);
        System.out.println(month);
        System.out.println(day);
        LocalDateTime now = LocalDateTime.of(year, month, day, 0, 0);
        System.out.println(now);*/
		
		LocalDateTime now = LocalDateTime.of(1970, 1, 1, 0, 0);//LocalDateTime.now();
		System.out.println(now.getMonth().getValue());
		System.out.println(now.getYear());
		System.out.println(now.getDayOfMonth());
	}
	
	public static void t(){
		/*String dateStr = getFullStringDateAgo(30);
		System.out.println(dateStr);*/
		/*Date date = getDate(dateStr);
		System.out.println(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);//��ȡ���
        int month=cal.get(Calendar.MONTH);//��ȡ�·�
        int day=cal.get(Calendar.DATE);//��ȡ��
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);*/
	}

	public static void sub() {

		System.out.println(LocalDateTime.now());
		LocalDate date = LocalDate.now();
		System.out.println(date);
		System.out.println(date.getDayOfMonth());
		System.out.println(date.getDayOfYear());
	}

	public static void test() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime now2 = LocalDateTime.now();

		System.out.println(now.getMonth().getValue());
		System.out.println(now.equals(now2));
	}
	
	/**
	 * �õ���ǰʱ���ǰ��������
	 * @param day
	 * @return
	 */
	public static String getFullStringDateAgo(int day){
		//SimpleDateFormat sdf = new SimpleDateFormat(FULL_TIME_ZERO);
		System.out.println(System.currentTimeMillis());
		long currentTime = System.currentTimeMillis()-(day*24*60*60*1000);
		System.out.println(currentTime);
		return sdfDay.format(new Date(currentTime));
	}
	
	public static Date getDate(String source){
		//SimpleDateFormat sdf = new SimpleDateFormat(FULL_TIME_ZERO);
		try {
			return sdfTime.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void getTimeByCalendar(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);//��ȡ���
        int month=cal.get(Calendar.MONTH) + 1;//��ȡ�·�
        int day=cal.get(Calendar.DATE);//��ȡ��
        int hour=cal.get(Calendar.HOUR);//Сʱ
        int minute=cal.get(Calendar.MINUTE);//��           
        int second=cal.get(Calendar.SECOND);//��
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);//һ�ܵĵڼ���
        System.out.println("���ڵ�ʱ���ǣ���Ԫ"+year+"��"+month+"��"+day+"��      "+hour+"ʱ"+minute+"��"+second+"��       ����"+(WeekOfYear-1));
    }
}
