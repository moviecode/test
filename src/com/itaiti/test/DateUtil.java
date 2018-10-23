package com.itaiti.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat(
			"yyyy-MM-dd");
	
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat(
	"yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdfMillisecond = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	/**
	 * 鑾峰彇YYYY鏍煎紡
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 鑾峰彇YYYY-MM-DD鏍煎紡
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 鑾峰彇YYYYMMDD鏍煎紡
	 * 
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 鑾峰彇YYYY-MM-DD HH:mm:ss鏍煎紡
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}
	
	/**
	 * 鑾峰彇 yyyyMMddHHmmssfff鏍煎紡瀛楃涓�
	 * @return
	 */
	public static String getMillisecond(){
		return sdfMillisecond.format(new Date());
	}

	/**
	* @Title: compareDate
	* @Description: (鏃ユ湡姣旇緝锛屽鏋渟>=e 杩斿洖true 鍚﹀垯杩斿洖false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 鏍煎紡鍖栨棩鏈�
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 鏍￠獙鏃ユ湡鏄惁鍚堟硶
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 濡傛灉throw java.text.ParseException鎴栬�匩ullPointerException锛屽氨璇存槑鏍煎紡涓嶅
			return false;
		}
	}
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 濡傛灉throw java.text.ParseException鎴栬�匩ullPointerException锛屽氨璇存槑鏍煎紡涓嶅
			return 0;
		}
	}
	  /**
     * <li>鍔熻兘鎻忚堪锛氭椂闂寸浉鍑忓緱鍒板ぉ鏁�
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("鐩搁殧鐨勫ぉ鏁�="+day);
      
        return day;
    }
    
    /**
     * 寰楀埌n澶╀箣鍚庣殑鏃ユ湡
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util鍖�
        canlendar.add(Calendar.DATE, daysInt); // 鏃ユ湡鍑� 濡傛灉涓嶅鍑忎細灏嗘湀鍙樺姩
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 寰楀埌n澶╀箣鍚庢槸鍛ㄥ嚑
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util鍖�
        canlendar.add(Calendar.DATE, daysInt); // 鏃ユ湡鍑� 濡傛灉涓嶅鍑忎細灏嗘湀鍙樺姩
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        
        return dateStr;
    }
    
    public static void main(String[] args) {
    	//System.out.println(getDays());
    	//System.out.println(getAfterDayWeek("3"));
    	System.out.println(getBeforeDayDate(30));
    	System.out.println(getLocalDateTime());
    }
    
    /**
	 * 鑾峰彇涓�涓湀涔嬪墠鐨勬棩鏈�
	 * @return
	 */
	public static LocalDateTime getLocalDateTime(){
		Calendar calendar = Calendar.getInstance();   
		calendar.add(Calendar.DATE, -1);    //寰楀埌鍓嶄竴澶�   
		calendar.add(Calendar.MONTH, -1);    //寰楀埌鍓嶄竴涓湀   
		int year = calendar.get(Calendar.YEAR);   
		int month = calendar.get(Calendar.MONTH)+1;   
		int day = calendar.get(Calendar.DATE);
        LocalDateTime now = LocalDateTime.of(year, month, day, 0, 0);
        return now;
	}
	
	/**
	 * 鑾峰彇涓�涓湀涔嬪墠鐨勬棩鏈�
	 * @return
	 */
	public static LocalDateTime getBeforeDayDate(int days){
		Calendar calendar = Calendar.getInstance();   
		calendar.add(Calendar.DATE, days);    //寰楀埌鍓嶄竴澶�   
		//calendar.add(Calendar.MONTH, -1);    //寰楀埌鍓嶄竴涓湀   
		int year = calendar.get(Calendar.YEAR);   
		int month = calendar.get(Calendar.MONTH)+1;   
		int day = calendar.get(Calendar.DATE);
        LocalDateTime now = LocalDateTime.of(year, month, day, 0, 0);
        return now;
	}

}
