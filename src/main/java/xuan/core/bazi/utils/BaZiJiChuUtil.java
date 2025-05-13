package xuan.core.bazi.utils;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import xuan.utils.CommonUtil;
import xuan.core.bazi.settings.BaZiJiChuSetting;

import java.util.*;

/**
 * 八字 - 基础工具
 *
 * @author 善待
 * @version 2.0.0
 * @blessing ☯福生无量☯
 */
public class BaZiJiChuUtil {

    /**
     * 获取日期
     *
     * @param setting 八字 - 基础设置
     * @return 日期
     */
    public static Map<String, Object> getDate(BaZiJiChuSetting setting) {

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
     * @param setting 八字 - 基础设置
     * @param lunar   农历日期
     * @return 干支
     */
    public static Map<String, List<String>> getGanZhi(BaZiJiChuSetting setting, Lunar lunar) {

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
     * 返回干支加分
     *
     * @param shengKe   天干和干支五行的生克关系
     * @param ganOrZhi1 天干或地支
     * @param ganOrZhi2 天干或地支
     * @param jiaFen    加分
     * @return 干支加分
     */
    public static int ganZhiJiaFen(Map<String, String> shengKe, String ganOrZhi1, String ganOrZhi2, int jiaFen) {
        int ganZhiJiaFen = 0;
        String qiangOrRuo = shengKe.get(ganOrZhi1 + ganOrZhi2); // '身强'或'身弱'
        if ("身强".equals(qiangOrRuo)) ganZhiJiaFen += jiaFen;
        return ganZhiJiaFen;
    }

    /**
     * 骨重转为文字
     *
     * @param guZhong 骨重
     * @return 骨重文字（如：七两）
     */
    public static String guZhongCharacters(Integer guZhong) {

        long liang = Long.parseLong(guZhong.toString().substring(0, 1)); // 两重
        long qian = Long.parseLong(guZhong.toString().substring(1, 2)); // 钱重
        String newGuZhong = CommonUtil.shuToHan(liang) + "两";
        if (0 != qian) newGuZhong += CommonUtil.shuToHan(qian) + "钱";
        return newGuZhong;

    }


}
