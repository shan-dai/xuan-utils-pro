package xuan.core.ziwei.utils;

import java.util.*;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import xuan.core.ziwei.settings.ZiWeiJiChuSetting;

/**
 * 紫微斗数 - 基础工具
 *
 * @author 善待
 * @version 2.0.0
 * @blessing ☯福生无量☯
 */
public class ZiWeiJiChuUtil {

    /**
     * 获取日期
     *
     * @param setting 紫微斗数 - 基础设置
     * @return 日期
     */
    public static Map<String, Object> getDate(ZiWeiJiChuSetting setting) {

        Solar solar;
        Lunar lunar;

        // 1、判断日期类型
        Date date = setting.getDate(); // 日期
        if (setting.getDateType() == 0) {
            // 1.1、按公历日期计算
            solar = new Solar(date); // 公历日期
            lunar = solar.getLunar(); // 农历日期
        } else {
            // 1.2、按农历日期计算
            try {
                lunar = new Lunar(date.getYear() + 1900, setting.getLeapMonthType() == 0 ? (date.getMonth() + 1) : -(date.getMonth() + 1), date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds()); // 农历日期
                solar = lunar.getSolar(); // 公历日期
            } catch (IllegalArgumentException e) {
//                lunar = new Lunar(); // 当前农历日期
//                solar = lunar.getSolar(); // 当前公历日期
                throw new IllegalArgumentException(e);
            }
        }

        // 2、添加日期并返回
        Map<String, Object> map = new HashMap<>();
        map.put("solar", solar); // 公历日期
        map.put("lunar", lunar); // 农历日期
        return map;

    }

    /**
     * 获取干支
     *
     * @param setting 紫微斗数 - 基础设置
     * @param lunar   农历日期
     * @return 干支
     */
    public static Map<String, List<String>> getGanZhi(ZiWeiJiChuSetting setting, Lunar lunar) {

        List<String> list;
        Map<String, List<String>> map = new HashMap<>();

        // 1、判断年干支类型
        if (setting.getYearGanZhiType() == 0) {
            list = Arrays.asList(lunar.getYearGan(), lunar.getYearZhi(), lunar.getYearInGanZhi()); // 以正月初一作为新年的开始
        } else if (setting.getYearGanZhiType() == 1) {
            list = Arrays.asList(lunar.getYearGanByLiChun(), lunar.getYearZhiByLiChun(), lunar.getYearInGanZhiByLiChun()); // 以立春当天作为新年的开始
        } else {
            list = Arrays.asList(lunar.getYearGanExact(), lunar.getYearZhiExact(), lunar.getYearInGanZhiExact()); // 以立春交接的时刻作为新年的开始
        }
        map.put("yearGanZhi", list);

        // 2、判断月干支类型
        if (setting.getMonthGanZhiType() == 0) {
            list = Arrays.asList(lunar.getMonthGan(), lunar.getMonthZhi(), lunar.getMonthInGanZhi()); // 以节交接当天起算
        } else {
            list = Arrays.asList(lunar.getMonthGanExact(), lunar.getMonthZhiExact(), lunar.getMonthInGanZhiExact()); // 以节交接时刻起算
        }
        map.put("monthGanZhi", list);

        // 3、判断日干支类型
        if (setting.getDayGanZhiType() == 0) {
            list = Arrays.asList(lunar.getDayGanExact(), lunar.getDayZhiExact(), lunar.getDayInGanZhiExact()); // 晚子时日干支算明天
        } else {
            list = Arrays.asList(lunar.getDayGanExact2(), lunar.getDayZhiExact2(), lunar.getDayInGanZhiExact2()); // 晚子时日干支算当天
        }
        map.put("dayGanZhi", list);

        // 4、支持早子时和晚子时
        map.put("hourGanZhi", Arrays.asList(lunar.getTimeGan(), lunar.getTimeZhi(), lunar.getTimeInGanZhi()));

        // 5、返回干支
        return map;

    }

    /**
     * 获取星位宫位
     *
     * @param zhuXing  诸星
     * @param xingMing 星名
     * @return 星位宫位
     */
    public static int getXingWeiGongWei(List<String> zhuXing, String xingMing) {

        int xingWeiGongWei = 0;

        A:
        for (int i = 0; i < zhuXing.size(); i++) {
            if (!"".equals(zhuXing.get(i))) {
                String[] oneGongXing = zhuXing.get(i).split("_");
                if (oneGongXing.length == 1) {
                    if (xingMing.equals(zhuXing.get(i))) {
                        xingWeiGongWei = i + 1;
                        break;
                    }
                } else {
                    for (String item : oneGongXing) {
                        if (xingMing.equals(item)) {
                            xingWeiGongWei = i + 1;
                            break A;
                        }
                    }
                }
            }
        }

        return xingWeiGongWei;

    }

    /**
     * 添加诸星
     *
     * @param zhuXing 诸星
     * @param oneList 每一个宫位中的诸星
     * @param index   索引（0 ~ 11）
     * @param mark    标识
     */
    public static void addZhuXing(List<String> zhuXing, List<String> oneList, int index, String mark) {

        if (!"".equals(zhuXing.get(index))) {
            String[] oneGongXing = zhuXing.get(index).split("_");
            if (oneGongXing.length == 1) {
                oneList.add(mark + zhuXing.get(index));
            } else {
                for (String item : oneGongXing) {
                    oneList.add(mark + item);
                }
            }
        }

    }


}
