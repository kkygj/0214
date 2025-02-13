package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 取得目前時間的字串格式
    public static String getCurrentTime() {
        return DATE_FORMAT.format(new Date());
    }
}
