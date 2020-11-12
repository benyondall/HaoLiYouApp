package e.commerce.shopping.guide.common.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shili Last Modifiy : 2010-07-02
 */
public class DateTime {

    public enum Format {
        full, sffull, md, mmdd, sfull, yymd, yymmdd, yyyymd, yyyymmdd, yyyymm, yyyym, yyyy, mm
    }


    /**
     * 判断当前字符串是否为标准时间类型  包含 yyyy-mm-dd hh-MM-SS 和 yyyy-m-d h-M-S
     *
     * @param date 时间
     * @return
     */
    public static boolean checkDate(String date) {
        if (date == null || date.length() == 0) {
            return false;
        }
        String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) "
                + "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    /**
     * 验证时间格式是否正确
     *
     * @param date 时间
     *             时间格式 例如:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static boolean checkDate(String date, Format f) {
        SimpleDateFormat df = new SimpleDateFormat(getDateTimeFormat(f));
        Date d = null;
        try {
            d = df.parse(date);
        } catch (Exception e) {
            return false;
        }
        String s1 = df.format(d);
        return date.equals(s1);
    }

    /**
     * 返回指定格式的当前时间
     * <p>
     * 时间日期格式
     *
     * @return 一个Date类型时间
     */
    public static Date getDate() {
        return getDate(null);
    }

    /**
     * 返回指定格式的当前时间
     * <p>
     * 时间日期格式
     *
     * @return 一个Date类型时间
     */
    public static Date getDate(Format f) {
        if (f == null) {
            return new Date();
        } else {
            SimpleDateFormat df = new SimpleDateFormat(getDateTimeFormat(f));
            Date d = new Date();
            try {
                d = df.parse(d.toString());
            } catch (Exception e) {
            }
            return d;
        }
    }

    /**
     * 给出一个字符串 返回时间类型数据
     *
     * @param f         枚举时间格式
     * @param _dateTime 时间字符串
     * @return 一个时间日期类型;
     */
    public static Date getDate(Format f, String _dateTime) {
        Date temp_d;
        SimpleDateFormat df = new SimpleDateFormat(getDateTimeFormat(f));
        try {
            temp_d = df.parse(_dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
            temp_d = null;
        }
        return temp_d;
    }

    /**
     * 给出一个字符串 返回时间类型数据
     *
     * @param f         枚举时间格式
     * @param _dateTime 时间字符串
     * @return 一个时间日期类型;
     */
    public static Date getDateThrowsException(Format f, String _dateTime) throws ParseException {
        Date temp_d;
        SimpleDateFormat df = new SimpleDateFormat(getDateTimeFormat(f));
        temp_d = df.parse(_dateTime);
        return temp_d;
    }

    /**
     * 对枚举类型进行格式转换
     *
     * @param f
     * @return
     */
    private static String getDateTimeFormat(Format f) {
        if (f.equals(Format.full)) {
            return "yyyy-MM-dd HH:mm:ss";
        }

        if (f.equals(Format.sffull)) {
            return "yyyy-MM-dd HH:mm";
        }

        if (f.equals(Format.sfull)) {
            return "yyyy-M-d H:m:s";
        }

        if (f.equals(Format.yyyymmdd)) {
            return "yyyy-MM-dd";
        }

        if (f.equals(Format.yyyymd)) {
            return "yyyy-M-d";
        }

        if (f.equals(Format.yymmdd)) {
            return "yy-MM-dd";
        }

        if (f.equals(Format.yymd)) {
            return "yy-M-d";
        }

        if (f.equals(Format.mmdd)) {
            return "MM-dd";
        }

        if (f.equals(Format.md)) {
            return "M-d";
        }

        if (f.equals(Format.yyyymm)) {
            return "yyyy-MM";
        }

        if (f.equals(Format.yyyym)) {
            return "yyyy-M";
        }

        if (f.equals(Format.yyyy)){
            return "yyyy";
        }

        if (f.equals(Format.mm)){
            return "MM";
        }

        return "";
    }

    /**
     * 获取当前时间的日期
     *
     * @return
     */
    public static int getDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取一个时间日期的日期
     *
     * @param date 一个时间
     * @return
     */
    public static int getDay(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 通过年和月来获取当月有多少天
     *
     * @param _year  给定的年份四位 如2002
     * @param _month 给定的年份 如3
     * @return 返回一个整形
     */
    public static int getDayCountByMonth(int _year, int _month) {
        int monthDay[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if ((_year % 4 == 0 && _year % 100 != 0) || _year % 400 == 0)
            monthDay[1]++;
        return monthDay[_month - 1];
    }

    /**
     * 获取当前时间的小时数
     *
     * @return 小时数
     */
    public static int getHour() {
        Calendar c = Calendar.getInstance();
        // int a = c.get(Calendar.HOUR_OF_DAY);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取一个时间日期的小时数
     *
     * @param date 一个时间
     * @return 小时数
     */
    public static int getHour(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取当前时间的豪秒数
     *
     * @return 豪秒数
     */
    public static int getMillisecond() {
        Calendar c = Calendar.getInstance();
        // int a = c.get(Calendar.HOUR_OF_DAY);
        return c.get(Calendar.MILLISECOND);
    }

    /**
     * 获取当前时间的分钟数
     *
     * @return 分钟数
     */
    public static int getMinute() {
        Calendar c = Calendar.getInstance();
        // int a = c.get(Calendar.HOUR_OF_DAY);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 获取当前时间的月份
     *
     * @return
     */
    public static int getMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(getDate(Format.full));
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取一个时间日期的月份
     *
     * @param date 一个时间
     * @return
     */
    public static int getMonth(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前时间的分钟数
     *
     * @return 分钟数
     */
    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 获取当前时间的秒数
     *
     * @return 秒数
     */
    public static int getSecond() {
        Calendar c = Calendar.getInstance();
        // int a = c.get(Calendar.HOUR_OF_DAY);
        return c.get(Calendar.SECOND);
    }


    /**
     * 返回一个给定的[时间]的字符串
     * <p>
     * .Format.full 枚举时间格式
     *
     * @return 时间字符串 例:2010-03-01 1-2-3
     */
    public static String getStrDate(Format f) {
        SimpleDateFormat df = new SimpleDateFormat(getDateTimeFormat(f));
        return df.format(getDate());
    }

    /**
     * 返回一个给定的[时间]的字符串
     * <p>
     * .Format.full 枚举时间格式
     *
     * @param _datetime 相关时间
     * @return 时间字符串 例:2010-03-01
     */
    public static String getStrDate(Format f, Date _datetime) {
        if (_datetime==null){
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(getDateTimeFormat(f));
        return df.format(_datetime);
    }

    /**
     * 返回一个给定的[时间]的字符串
     * <p>
     * .Format.full 枚举时间格式
     *
     * @param _datetime 时间长整型参数 相关时间
     * @return 时间字符串 例:2010-03-01
     */
    public static String getStrDate(Format f, long _datetime) {
        SimpleDateFormat df = new SimpleDateFormat(getDateTimeFormat(f));
        Date d = new Date(_datetime);
        return df.format(d);
    }

    /**
     * 返回一个给定的[时间]的字符串
     * <p>
     * <p>
     * .Format.full 输出的格式
     * <p>
     * .Format.full 输入的格式
     *
     * @param _datetime 相关时间字符串
     * @return 时间字符串 如果转换出错 则返回""空字符串
     */
    public static String getStrDate(Format f, Format sourcef, String _datetime) {
        SimpleDateFormat df = new SimpleDateFormat(getDateTimeFormat(sourcef));
        SimpleDateFormat df2 = new SimpleDateFormat(getDateTimeFormat(f));
        String _dateTime = "";

        Date _date = null;
        _date = getDate(sourcef, _datetime);

        return df2.format(_date);
    }

    /**
     * 返回一个给定的[时间间隔]的字符串
     *
     * @param Interval 间隔的天数 1为明天
     * @return 时间字符串 例:2010-03-01
     * @f Format.full 枚举时间格式
     */
    public static String getStrDate(Format f, int Interval) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, Interval);
        SimpleDateFormat df = new SimpleDateFormat(getDateTimeFormat(f));
        return df.format(c.getTime());
    }

    /**
     * 返回从 时间d1 到 时间d2 相差的秒数
     *
     * @param d1 开始时间
     * @param d2 结束时间
     * @return
     */
    public static int getTimeDiff(Date d1, Date d2) {
        long _diff = d2.getTime() - d1.getTime();
        int _temp = Math.round(_diff / 1000);
        return _temp;
    }

    /**
     * 返回从 时间d1 到 时间d2 相差的秒数
     *
     * @param d1 开始时间字符串 2010-01-01 01:02:33
     * @param d2 结束时间字符串 2010-02-01 01:02:33
     * @return 返回秒数 格式有错误则返回-1;
     */
    public static int getTimeDiff(String d1, String d2) {
        int _temp = 0;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date _d1 = df.parse(d1);
            Date _d2 = df.parse(d2);
            long _diff = _d2.getTime() - _d1.getTime();
            _temp = Math.round(_diff / 1000);
        } catch (ParseException e) {
            _temp = -1;
            e.printStackTrace();

        }
        return _temp;
    }

    /**
     * 获取当前时间的年份
     *
     * @return
     */
    public static int getYear() {
        Calendar c = Calendar.getInstance();
        c.setTime(getDate(Format.full));
        return c.get(Calendar.YEAR);
    }

    /**
     * 获取一个给定的时间日期的年份
     *
     * @param date 一个时间
     * @return
     */
    public static int getYear(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获得一个f格式的当前时间的字符串
     *
     * @param f
     * @return
     */
    public static String getNowDate(Format f) {
        SimpleDateFormat df = new SimpleDateFormat(getDateTimeFormat(f));
        return df.format(getDate(f));
    }

    /**
     * // string类型转换为date类型
     * // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
     * // HH时mm分ss秒，
     * // strTime的时间格式必须要与formatType的时间格式相同
     *
     * @param strTime
     * @param f
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String strTime, Format f) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(getDateTimeFormat(f));
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    //

    /**
     * String类型转换为long类型
     *
     * @param strTime 时间字符串
     * @param f       时间类型格式
     * @return
     */
    public static long stringToLong(String strTime, Format f) throws ParseException {
        Date date = stringToDate(strTime, f); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    //

    /**
     * date类型转换为long类型
     *
     * @param date 日期型时间类型
     * @return
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }


// 	//获得本周一 00:00:00
// 	public  static Date getMondayDayOfWeek(Date date){
//   	 Calendar calendar = Calendar.getInstance();
//	        calendar.setTime(date);
//	        calendar.set(Calendar.HOUR_OF_DAY, 0);
//	        calendar.set(Calendar.MINUTE, 0);
//	        calendar.set(Calendar.SECOND, 0);
//	        calendar.set(Calendar.MILLISECOND, 0);
//	        calendar.setFirstDayOfWeek(Calendar.MONDAY);
////	        calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
//	        
//	        int week = calendar.get(Calendar.DAY_OF_WEEK);
//	        calendar.add(Calendar.DAY_OF_WEEK, 1 - week);
//			return calendar.getTime();
//
//	        
//   }
// 	//获得本周天 23:59:59
//   public  static Date getSunDayOfWeek(Date date){
//   	 Calendar calendar = Calendar.getInstance();
//	        calendar.setTime(date);
//	        calendar.set(Calendar.HOUR_OF_DAY, 23);
//	        calendar.set(Calendar.MINUTE, 59);
//	        calendar.set(Calendar.SECOND, 59);
//	        calendar.set(Calendar.MILLISECOND,0);
//	        calendar.setFirstDayOfWeek(Calendar.MONDAY);
//	        calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
//	       
//	        return calendar.getTime();
//	        
//   }

    public static String longToString(long millis, Format f) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        SimpleDateFormat df = new SimpleDateFormat(getDateTimeFormat(f));
        return df.format(cal.getTime());
    }


    /**
     * 得到本周周一
     *
     * @return yyyy-MM-dd
     */
    public static Date getMondayDayOfWeek() {
        Calendar c = Calendar.getInstance();
        int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayofweek == 0)
            dayofweek = 7;
        c.add(Calendar.DATE, -dayofweek + 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return c.getTime();
    }

    /**
     * 得到本周周日
     *
     * @return yyyy-MM-dd
     */
    public static Date getSunDayOfWeek() {
        Calendar c = Calendar.getInstance();
        int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayofweek == 0)
            dayofweek = 7;
        c.add(Calendar.DATE, -dayofweek + 7);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return c.getTime();
    }

    /**
     * 将时间转换类型截取字段
     */
    public static String subDate(String date) {
        try {
            Date currentDate = DateTime.stringToDate(date, Format.full);
            String times = DateTime.getStrDate(Format.yymmdd, currentDate);
            String[] dateArray = times.split("-");
            String dateStr = dateArray[1] + "/" + dateArray[2] + "/" + dateArray[0];
            return dateStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 时间显示年月日
     */
    public static String showymdDate(String date) {
        if (date == null) {
            return "";
        }
        try {
            Date currentDate = DateTime.stringToDate(date, Format.full);
            String times = DateTime.getStrDate(Format.yyyymmdd, currentDate);
            return times;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 判断是不是今天
     *
     * @param time
     * @return
     */
    public static boolean isNow(String time) {
        Date date = getDate(Format.full, time);
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        //获取今天的日期
        String nowDay = sf.format(now);


        //对比的时间
        String day = sf.format(date);

        return day.equals(nowDay);
    }

    /**
     * 判断是不是昨天
     *
     * @param time
     * @return
     */
    public static boolean isYesterday(String time) {
        Date date = getDate(Format.full, time);
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        //获取昨天的日期
        String yesterday = sf.format(new Date(now.getTime() - 86400000L));
        //对比的时间
        String day = sf.format(date);

        return day.equals(yesterday);
    }


    /**
     * 毫秒数转时分
     * @param time
     * @return
     */
    public static String getGapTime(long time) {
        long day = time / (1000 * 60 * 60 * 24);
        long hours = (time - day * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (time - day * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
        String diffTime = "";
        if (minutes < 10) {
            diffTime = hours + ":0" + minutes;
        } else {
            diffTime = hours + ":" + minutes;
        }
        return diffTime;
    }

    /**
     * 毫秒数转时分秒 汉字
     * @param time
     * @return
     */
    public static String getCnGapTime(long time) {
        long hours = time / (1000 * 60 * 60);
        long minutes = (time - hours * (1000 * 60 * 60)) / (1000 * 60);
        long second = (time - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / 1000;
        return hours+"小时"+ minutes +"分"+ second +"秒";
    }

    /**
     * 获得 时分
     * @param date
     * @param f
     * @return
     */
    public static String getHoursAndMinutes(String date, Format f) {
        String time = "";
        try {
            long d = stringToLong(date, f);
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(d);
            int hours = c.get(Calendar.HOUR_OF_DAY);
            int minutes = c.get(Calendar.MINUTE);
            String m;
            String h;
            if (minutes<10){
                m = "0"+minutes;
            }else{
                m = minutes+"";
            }
            if (hours<10){
                h = "0"+hours ;
            }else{
                h = hours+"";
            }
            time = h+":"+m;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return time;
    }

    public static void main(String[] args) {
//        String time = "";
//
//            Date d = new Date();
//            Calendar c = Calendar.getInstance();
//            c.setTimeInMillis(d.getTime());
//            int hours = c.get(Calendar.HOUR_OF_DAY);
//            int minutes = c.get(Calendar.MINUTE);
//            String m;
//            String h;
//            if (minutes<10){
//                m = "0"+minutes;
//            }else{
//                m = minutes+"";
//            }
//            if (hours<10){
//                h = "0"+hours ;
//            }else{
//                h = hours+"";
//            }
//            time = h+":"+m;
        //yyyy-MM-dd HH:mm:ss
        System.out.println(getHoursAndMinutes("2018-10-10 09:09:09", Format.full));
    }

    /**
     * 比较两个日期的大小
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isDateOneBigger(String str1, String str2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = true;
        } else if (dt1.getTime() < dt2.getTime()) {
            isBigger = false;
        }
        return isBigger;
    }

    public static final String FORMAT_COMMON_DAY = "yyyy-MM-dd";
    public static final String FORMAT_COMMON = "yyyy-MM-dd HH:mm:ss";

    /**
     * Sting 转 Date
     * @param dateString
     * @param pattern
     * @return
     */
    public static Date parseString(String dateString, String pattern) {
        Date ret = null;
        try {
            if (dateString != null && pattern != null) {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                ret = sdf.parse(dateString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ret;
    }
    /**
     * 获取某年第一天日期
     *
     * @param year
     *            年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }
    /**
     * 获取某年最后一天日期
     *
     * @param year
     *            年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }


    public static Date getTomorrow(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DAY_OF_MONTH, +1);//明天

        return calendar.getTime();
    }

}
