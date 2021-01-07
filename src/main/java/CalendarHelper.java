import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;
import java.util.TimeZone;

/**
 * @author JiangYangfan
 * @version $Id$
 * @since 2020/12/28 5:23 下午
 */
public class CalendarHelper {

    private static final String TIME_ZONE_ID = "CTT";

    public static Calendar get(Integer year, Integer month) {
        Calendar ctt = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_ID));
        if (null != year) {
            ctt.set(Calendar.YEAR, year);
        }
        if (null != month) {
            ctt.set(Calendar.MONTH, month - 1);
        }

        ctt.set(Calendar.DAY_OF_MONTH, 1);

        return ctt;
    }

    public static Calendar get() {
        return get(null, null);
    }

    public static Integer[][] map(int year, int month) {
        Calendar calendar = CalendarHelper.get(year, month);
        int nowMonth = calendar.get(Calendar.MONTH);

        Integer[][] map = new Integer[6][7];
        while (calendar.get(Calendar.MONTH) == nowMonth) {
            int i_6 = calendar.get(Calendar.WEEK_OF_MONTH) - 1;
            int i_7 = calendar.get(Calendar.DAY_OF_WEEK) - 1;

            map[i_6][i_7] = calendar.get(Calendar.DAY_OF_MONTH);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        return map;
    }

    public  static ArrayList<Calendar> choose(int year, int month, Set<Integer> day_of_week_set) {
        Calendar calendar = CalendarHelper.get(year, month);
        int nowMonth = calendar.get(Calendar.MONTH);

        ArrayList<Calendar> choose = new ArrayList<>();
        while (calendar.get(Calendar.MONTH) == nowMonth) {
            if ((day_of_week_set).contains(calendar.get(Calendar.DAY_OF_WEEK))) {
                choose.add((Calendar)calendar.clone());
            }
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        choose.sort(Calendar::compareTo);
        return choose;
    }
}
