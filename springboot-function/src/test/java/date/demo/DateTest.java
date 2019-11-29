package date.demo;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

/**
 * 时间测试
 *
 * @author 三多
 * @Time 2019/10/11
 */
public class DateTest {

    /**
     * java 时间处理参考 <a href="https://stackoverflow.com/questions/51711756/zoneddatetime-to-mysql-datetime"></a>
     * 时间处理类：{@link ZonedDateTime}
     */
    @Test
    public void testGtmTODate() {
        //String oldDateStr = "2016-10-15T00:00:00.000+08:00";
        String oldDateStr = "2019-10-11T10:51:10.692+08:00";
        //此格式只有  jdk 1.7才支持  yyyy-MM-dd'T'HH:mm:ss.SSSXXX

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");  //yyyy-MM-dd'T'HH:mm:ss.SSSZ
        Date date = null;
        try {
            date = df.parse(oldDateStr);
            System.out.println(date);

            SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            System.out.println(df1.parse(date.toString()));
            System.out.println(df1.format(date));

            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(df2.format(date));
            //java 1.8 线程安全
            String string = "2019-10-11T10:51:10.6921766+08:00";
            ZonedDateTime parse = ZonedDateTime.parse(string);
            LocalDateTime localDateTime = parse.toLocalDateTime();
            System.out.println("localDateTime:"+localDateTime);
            ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
            System.out.println("zonedDateTime:" + zonedDateTime);
            LocalDate localDate = parse.toLocalDate();
            System.out.println("localDate:"+localDate);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String format = parse.format(formatter);
            System.out.println("格式化后的:"+ format);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
