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
		calendar.add(Calendar.DATE, -1);    //得到前一天   
		calendar.add(Calendar.MONTH, -1);    //得到前一个月   
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
		int year = cal.get(Calendar.YEAR);//获取年份
        int month=cal.get(Calendar.MONTH);//获取月份
        int day=cal.get(Calendar.DATE);//获取日
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
	 * 得到当前时间的前几天数据
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
        int year = cal.get(Calendar.YEAR);//获取年份
        int month=cal.get(Calendar.MONTH) + 1;//获取月份
        int day=cal.get(Calendar.DATE);//获取日
        int hour=cal.get(Calendar.HOUR);//小时
        int minute=cal.get(Calendar.MINUTE);//分           
        int second=cal.get(Calendar.SECOND);//秒
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);//一周的第几天
        System.out.println("现在的时间是：公元"+year+"年"+month+"月"+day+"日      "+hour+"时"+minute+"分"+second+"秒       星期"+(WeekOfYear-1));
    }
}
