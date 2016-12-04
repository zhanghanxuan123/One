package com.zhx.one.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Author   :zhx
 * Create at 2016/12/3
 * Description: 日期工具类
 */
public class DateUtils {


    public static String getWeek(int num){
        String[] weeks = {"周日","周一","周二","周三","周四","周五","周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDay(num));
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        return weeks[week_index];
    }

    public static Date getDay(int num){
        Date now = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.add(calendar.DATE,-num);//把日期往后增加一天.整数往后推,负数往前移动
        now=calendar.getTime(); //这个时间就是日期往后推一天的结果
        return now;
    }

    /*public static String getDMY(String){
        String dateString = "2012-12-06 ";
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
            Date date = sdf.parse(dateString);
        }
        catch (ParseException e)
        {
            System.out.println(e.getMessage());
        }
        return
    }*/

    public static Date stringToDate(String strDate) {
        if (strDate == null || strDate.equals("")) {
            throw new RuntimeException("strDate is null");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return date;
    }

}
