package xuan.utils;

import org.junit.Test;

import java.util.Date;

/**
 * 真太阳时工具测试
 *
 * @author 善待
 * @version 2.0.0
 * @blessing ☯福生无量☯
 */
public class TrueSolarDateUtilTest {

    /**
     * 获取真太阳时
     */
    @Test
    public void getTrueSolarDate() {

        // 初始化日期
        Date date = new Date();

        // 获取真太阳时
        String address = "上海市"; // 地区
        double lng = 121; // 经度
//        Date trueSolarTime = TrueSolarDateUtil.getTrueSolarDate(date); // 获取真太阳时（根据发出请求的机器所在地区计算）
//        Date trueSolarTime = TrueSolarDateUtil.getTrueSolarDate(date, address); // 获取真太阳时（根据地区计算）
        Date trueSolarTime = TrueSolarDateUtil.getTrueSolarDate(date, lng); // 获取真太阳时（根据经度计算）

        // 格式化日期
        String dateStr = DateUtil.getDateStr(date, "yyyy-MM-dd HH:mm:ss"); // 标准时刻
        String trueSolarTimeStr = DateUtil.getDateStr(trueSolarTime, "yyyy-MM-dd HH:mm:ss"); // 真太阳时
        System.out.println(address + "，标准时刻→ " + dateStr); // 上海市，标准时刻→ 2025-04-30 08:22:14
        System.out.println(address + "，真太阳时→ " + trueSolarTimeStr); // 上海市，真太阳时→ 2025-04-30 08:29:17

    }


}
