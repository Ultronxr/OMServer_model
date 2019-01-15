package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * 获取当前时间
 *
 */
public class GetCurrentTime {

    public static String rawTime(){
        return new Date().toString();
    }

    public static String formatedTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String formatedTime(String formatStr){
        return new SimpleDateFormat(formatStr).format(new Date());
    }

}
