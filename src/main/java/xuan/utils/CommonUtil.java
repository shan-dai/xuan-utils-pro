package xuan.utils;

import java.util.*;
import java.math.BigDecimal;

import org.apache.commons.validator.routines.InetAddressValidator;

/**
 * 通用工具
 *
 * @author 善待
 * @version 2.0.0
 */
public class CommonUtil {

    /**
     * 占位符
     */
    public static final String EMPTY = "--";

    /**
     * 机器ip校验
     *
     * @param ip 机器ip（ipv4或ipv6）
     * @return true:格式正确。false:格式错误
     */
    public static boolean checkIp(String ip) {

        boolean isFlag = true;
        InetAddressValidator validator = InetAddressValidator.getInstance();
        if (!validator.isValidInet4Address(ip) && !validator.isValidInet6Address(ip)) {
            isFlag = false;
        }
        return isFlag;

    }

    /**
     * 数字转换汉字
     *
     * @param number 数字
     * @return 汉字（例如：一）
     */
    public static String shuToHan(long number) {

        final String[] unit = {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};
        final String[] lowercaseNumber = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            sb.insert(0, (lowercaseNumber[Math.toIntExact(number % 10)] + unit[count]));
            number = (number / 10);
            count++;
        }

        return sb.toString().replaceAll("零[千百十]", "零").replaceAll("零+万", "万")
                .replaceAll("零+亿", "亿").replaceAll("亿万", "亿零")
                .replaceAll("零+", "零").replaceAll("零$", "");

    }

    /**
     * 获取一个数的随机数
     *
     * @param range 范围（如→ 0:产生0，1:产生1~1中的随机一位数字，2:产生1~2中的随机一位数字，... ）
     * @return List随机数集合
     */
    public static Integer randomList(int range) {

        int nextInt = new Random().nextInt(range);
        return nextInt == 0 ? 1 : nextInt;

    }

    /**
     * 保留Double型数据的N位小数
     *
     * @param number double类型数据
     * @param count  保留小数的位数
     * @return double型数据
     */
    public static Double getDouble(double number, int count) {

        BigDecimal bigDec = new BigDecimal(number);
        return bigDec.setScale(count, BigDecimal.ROUND_FLOOR).doubleValue();

    }

    /**
     * 将N个数据封装为List集合
     *
     * @param parameter 数据
     * @return List集合
     */
    public static List<Integer> packageList(int... parameter) {

        List<Integer> list = new ArrayList<>();
        for (int i : parameter) {
            if (i < 0) i = 0;
            if (i > 3) i = 3;
            list.add(i);
        }
        return list;

    }

    /**
     * 获取指定个数的随机数
     *
     * @param count 数量
     * @param range 范围（如→ 0:产生0，1:产生0~1中的随机一位数字，2:产生0~2中的随机一位数字，... ）
     * @return List随机数集合
     */
    public static List<Integer> randomList(long count, int range) {

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            list.add(new Random().nextInt(range + 1));
        }

        return list;

    }

    /**
     * 删除list数组中重复的元素
     *
     * @param list list数组
     * @return list数组
     */
    public static List<String> removeDuplicates(List<String> list) {

        // 删除所有null
        list.removeAll(Collections.singleton(null));

        // 删除所有重复数据
        Set<String> set = new LinkedHashSet<>(list);
        return new ArrayList<>(set);

    }

    /**
     * 向List集合中添加指定个数的字符串
     *
     * @param count 元素数量
     * @return 空list集合
     */
    public static List<String> addCharToList(int count, String text) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i <= count - 1; i++) {
            list.add(text);
        }
        return list;

    }

    /**
     * 获取两个List数组中的不同元素
     *
     * @param list1 数组1
     * @param list2 数组2
     * @return 不同元素的数组
     */
    public static List<String> getListUnlike(List<String> list1, List<String> list2) {

        List<String> maxList = list1;
        List<String> minList = list2;
        if (list2.size() > list1.size()) {
            maxList = list2;
            minList = list1;
        }

        Map<String, Integer> map = new HashMap<>(list1.size() + list2.size());
        for (String string : maxList) {
            map.put(string, 1);
        }
        for (String string : minList) {
            Integer count = map.get(string);
            if (null != count) {
                map.put(string, ++count);
                continue;
            }
            map.put(string, 1);
        }

        List<String> unlike = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                unlike.add(entry.getKey());
            }
        }

        return unlike;

    }


}
