package xuan.core.bazi.utils;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import xuan.utils.CommonUtil;
import xuan.core.bazi.maps.BaZiJiChuMap;
import xuan.core.bazi.maps.BaZiShenShaMap;
import xuan.core.bazi.settings.BaZiShenShaSetting;

/**
 * 八字 - 神煞工具
 *
 * @author 善待
 * @version 2.0.0
 * @blessing ☯福生无量☯
 */
public class BaZiShenShaUtil {

    /**
     * 八字 - 神煞设置
     */
    private BaZiShenShaSetting baZiShenShaSetting;

    /**
     * 性别（0:女。1:男）
     */
    private int sex;
    /**
     * 季节
     */
    private String jiJie;
    /**
     * 年干支纳音五行
     */
    private String yearGanZhiNaYinWuXing;

    /**
     * 年干
     */
    private String yearGan;
    /**
     * 月干
     */
    private String monthGan;
    /**
     * 日干
     */
    private String dayGan;
    /**
     * 时干
     */
    private String hourGan;
    /**
     * 大运干
     */
    private String daYunGan;
    /**
     * 流年干
     */
    private String liuNianGan;
    /**
     * 流月干
     */
    private String liuYueGan;
    /**
     * 流日干
     */
    private String liuRiGan;
    /**
     * 流时干
     */
    private String liuShiGan;

    /**
     * 年支
     */
    private String yearZhi;
    /**
     * 月支
     */
    private String monthZhi;
    /**
     * 日支
     */
    private String dayZhi;
    /**
     * 时支
     */
    private String hourZhi;
    /**
     * 大运支
     */
    private String daYunZhi;
    /**
     * 流年支
     */
    private String liuNianZhi;
    /**
     * 流月支
     */
    private String liuYueZhi;
    /**
     * 流日支
     */
    private String liuRiZhi;
    /**
     * 流时支
     */
    private String liuShiZhi;

    /**
     * 年干支
     */
    private String yearGanZhi;
    /**
     * 月干支
     */
    private String monthGanZhi;
    /**
     * 日干支
     */
    private String dayGanZhi;
    /**
     * 时干支
     */
    private String hourGanZhi;
    /**
     * 大运干支
     */
    private String daYunGanZhi;
    /**
     * 流年干支
     */
    private String liuNianGanZhi;
    /**
     * 流月干支
     */
    private String liuYueGanZhi;
    /**
     * 流日干支
     */
    private String liuRiGanZhi;
    /**
     * 流时干支
     */
    private String liuShiGanZhi;

    /**
     * 年干支神煞
     */
    private List<String> yearGanZhiShenSha = new ArrayList<>();
    /**
     * 月干支神煞
     */
    private List<String> monthGanZhiShenSha = new ArrayList<>();
    /**
     * 日干支神煞
     */
    private List<String> dayGanZhiShenSha = new ArrayList<>();
    /**
     * 时干支神煞
     */
    private List<String> hourGanZhiShenSha = new ArrayList<>();
    /**
     * 大运干支神煞
     */
    private List<String> daYunGanZhiShenSha = new ArrayList<>();
    /**
     * 流年干支神煞
     */
    private List<String> liuNianGanZhiShenSha = new ArrayList<>();
    /**
     * 流月干支神煞
     */
    private List<String> liuYueGanZhiShenSha = new ArrayList<>();
    /**
     * 流日干支神煞
     */
    private List<String> liuRiGanZhiShenSha = new ArrayList<>();
    /**
     * 流时干支神煞
     */
    private List<String> liuShiGanZhiShenSha = new ArrayList<>();

//*******************************************************************************************************************************

    /**
     * 使用自定义设置初始化
     *
     * @param baZiShenShaSetting 八字 - 神煞设置
     * @param sex                性别（0:女。1:男）
     * @param jiJie              季节
     * @param yearGanZhiNaYin    年干支纳音
     * @param yearGanZhi         年干支（若为空则不计算此柱神煞，若格式错误则此柱神煞不完整）
     * @param monthGanZhi        月干支（若为空则不计算此柱神煞，若格式错误则此柱神煞不完整）
     * @param dayGanZhi          日干支（若为空则不计算此柱神煞，若格式错误则此柱神煞不完整）
     * @param hourGanZhi         时干支（若为空则不计算此柱神煞，若格式错误则此柱神煞不完整）
     * @param daYunGanZhi        大运干支（若为空则不计算此柱神煞，若格式错误则此柱神煞不完整）
     * @param liuNianGanZhi      流年干支（若为空则不计算此柱神煞，若格式错误则此柱神煞不完整）
     * @param liuYueGanZhi       流月干支（若为空则不计算此柱神煞，若格式错误则此柱神煞不完整）
     * @param liuRiGanZhi        流日干支（若为空则不计算此柱神煞，若格式错误则此柱神煞不完整）
     * @param liuShiGanZhi       流时干支（若为空则不计算此柱神煞，若格式错误则此柱神煞不完整）
     */
    public BaZiShenShaUtil(
            BaZiShenShaSetting baZiShenShaSetting, int sex, String jiJie, String yearGanZhiNaYin, String yearGanZhi, String monthGanZhi,
            String dayGanZhi, String hourGanZhi, String daYunGanZhi, String liuNianGanZhi, String liuYueGanZhi, String liuRiGanZhi, String liuShiGanZhi
    ) {

        // 1、八字设置
        this.baZiShenShaSetting = baZiShenShaSetting; // 八字 - 神煞设置

        // 2、处理数据
        this.sex = sex; // 性别
        this.jiJie = jiJie; // 季节
        this.yearGanZhiNaYinWuXing = (null != yearGanZhiNaYin && yearGanZhiNaYin.length() == 3) ? yearGanZhiNaYin.substring(2, 3) : "木"; // 年干支纳音五行
        this.yearGanZhi = (null != yearGanZhi && yearGanZhi.length() == 2) ? yearGanZhi : CommonUtil.EMPTY; // 年干支
        this.yearGan = this.yearGanZhi.substring(0, 1); // 年干
        this.yearZhi = this.yearGanZhi.substring(1, 2); // 年支
        this.monthGanZhi = (null != monthGanZhi && monthGanZhi.length() == 2) ? monthGanZhi : CommonUtil.EMPTY; // 月干支
        this.monthGan = this.monthGanZhi.substring(0, 1); // 月干
        this.monthZhi = this.monthGanZhi.substring(1, 2); // 月支
        this.dayGanZhi = (null != dayGanZhi && dayGanZhi.length() == 2) ? dayGanZhi : CommonUtil.EMPTY; // 日干支
        this.dayGan = this.dayGanZhi.substring(0, 1); // 日干
        this.dayZhi = this.dayGanZhi.substring(1, 2); // 日支
        this.hourGanZhi = (null != hourGanZhi && hourGanZhi.length() == 2) ? hourGanZhi : CommonUtil.EMPTY; // 时干支
        this.hourGan = this.hourGanZhi.substring(0, 1); // 时干
        this.hourZhi = this.hourGanZhi.substring(1, 2); // 时支
        this.daYunGanZhi = (null != daYunGanZhi && daYunGanZhi.length() == 2) ? daYunGanZhi : CommonUtil.EMPTY; // 大运干支
        this.daYunGan = this.daYunGanZhi.substring(0, 1); // 大运干
        this.daYunZhi = this.daYunGanZhi.substring(1, 2); // 大运支
        this.liuNianGanZhi = (null != liuNianGanZhi && liuNianGanZhi.length() == 2) ? liuNianGanZhi : CommonUtil.EMPTY; // 流年干支
        this.liuNianGan = this.liuNianGanZhi.substring(0, 1); // 流年干
        this.liuNianZhi = this.liuNianGanZhi.substring(1, 2); // 流年支
        this.liuYueGanZhi = (null != liuYueGanZhi && liuYueGanZhi.length() == 2) ? liuYueGanZhi : CommonUtil.EMPTY; // 流月干支
        this.liuYueGan = this.liuYueGanZhi.substring(0, 1); // 流月干
        this.liuYueZhi = this.liuYueGanZhi.substring(1, 2); // 流月支
        this.liuRiGanZhi = (null != liuRiGanZhi && liuRiGanZhi.length() == 2) ? liuRiGanZhi : CommonUtil.EMPTY; // 流日干支
        this.liuRiGan = this.liuRiGanZhi.substring(0, 1); // 流日干
        this.liuRiZhi = this.liuRiGanZhi.substring(1, 2); // 流日支
        this.liuShiGanZhi = (null != liuShiGanZhi && liuShiGanZhi.length() == 2) ? liuShiGanZhi : CommonUtil.EMPTY; // 流时干支
        this.liuShiGan = this.liuShiGanZhi.substring(0, 1); // 流时干
        this.liuShiZhi = this.liuShiGanZhi.substring(1, 2); // 流时支

        // 3、初始化数据
        initializeShenSha(); // 初始化神煞

    }

//===============================================================================================================================

    /**
     * 获取年干支神煞
     *
     * @return 年干支神煞（如：[太极贵人]）
     */
    public List<String> getYearGanZhiShenSha() {
        return this.yearGanZhiShenSha;
    }

    /**
     * 获取月干支神煞
     *
     * @return 月干支神煞（如：[太极贵人]）
     */
    public List<String> getMonthGanZhiShenSha() {
        return this.monthGanZhiShenSha;
    }

    /**
     * 获取日干支神煞
     *
     * @return 日干支神煞（如：[太极贵人]）
     */
    public List<String> getDayGanZhiShenSha() {
        return this.dayGanZhiShenSha;
    }

    /**
     * 获取时干支神煞
     *
     * @return 时干支神煞（如：[太极贵人]）
     */
    public List<String> getHourGanZhiShenSha() {
        return this.hourGanZhiShenSha;
    }

    /**
     * 获取大运干支神煞
     *
     * @return 大运干支神煞（如：[太极贵人]）
     */
    public List<String> getDaYunGanZhiShenSha() {
        return this.daYunGanZhiShenSha;
    }

    /**
     * 获取流年干支神煞
     *
     * @return 流年干支神煞（如：[太极贵人]）
     */
    public List<String> getLiuNianGanZhiShenSha() {
        return this.liuNianGanZhiShenSha;
    }

    /**
     * 获取流月干支神煞
     *
     * @return 流月干支神煞（如：[太极贵人]）
     */
    public List<String> getLiuYueGanZhiShenSha() {
        return this.liuYueGanZhiShenSha;
    }

    /**
     * 获取流日干支神煞
     *
     * @return 流日干支神煞（如：[太极贵人]）
     */
    public List<String> getLiuRiGanZhiShenSha() {
        return this.liuRiGanZhiShenSha;
    }

    /**
     * 获取流时干支神煞
     *
     * @return 流时干支神煞（如：[太极贵人]）
     */
    public List<String> getLiuShiGanZhiShenSha() {
        return this.liuShiGanZhiShenSha;
    }

//-------------------------------------------------------------------------------------------------------------------------------

    /**
     * 计算元辰
     */
    private void yuanChen() {

        String yearGanYinYangMap = BaZiJiChuMap.TIAN_GAN_YIN_YANG.get(this.yearGan); // 天干阴阳（天干为键）
        Map<String, String> yuanChenYangNanMap = BaZiShenShaMap.YUAN_CHEN_YANG_NAN; // 元辰（地支+地支），阳男阴女
        Map<String, String> yuanChenYinNanMap = BaZiShenShaMap.YUAN_CHEN_YIN_NAN; // 元辰（地支+地支），阴男阳女

        Map<String, String> map = new HashMap<>();
        switch (yearGanYinYangMap + (this.sex == 0 ? "女" : "男")) {
            case "阳男":
            case "阴女":
                map = yuanChenYangNanMap; // 阳男阴女
                break;
            case "阴男":
            case "阳女":
                map = yuanChenYinNanMap;  // 阴男阳女
                break;
        }
        addShenSha4(map, this.yearZhi, "");

    }

    /**
     * 计算天德贵人
     */
    private void tianDeGuiRen() {

        Map<String, String> tianDeGuiRenMap = BaZiShenShaMap.TIAN_DE_GUI_REN; // 天德贵人（地支+天干或地支为键）

        this.yearGanZhiShenSha.add(tianDeGuiRenMap.get(this.monthZhi + this.yearGan));
        this.monthGanZhiShenSha.add(tianDeGuiRenMap.get(this.monthZhi + this.monthGan));
        this.dayGanZhiShenSha.add(tianDeGuiRenMap.get(this.monthZhi + this.dayGan));
        this.hourGanZhiShenSha.add(tianDeGuiRenMap.get(this.monthZhi + this.hourGan));
        this.yearGanZhiShenSha.add(tianDeGuiRenMap.get(this.monthZhi + this.yearZhi));
        this.monthGanZhiShenSha.add(tianDeGuiRenMap.get(this.monthZhi + this.monthZhi));
        this.dayGanZhiShenSha.add(tianDeGuiRenMap.get(this.monthZhi + this.dayZhi));
        this.hourGanZhiShenSha.add(tianDeGuiRenMap.get(this.monthZhi + this.hourZhi));
        this.dayGanZhiShenSha.add(tianDeGuiRenMap.get(this.monthZhi + this.daYunGan));
        this.dayGanZhiShenSha.add(tianDeGuiRenMap.get(this.monthZhi + this.daYunZhi));
        this.liuNianGanZhiShenSha.add(tianDeGuiRenMap.get(this.monthZhi + this.liuNianGan));
        this.liuNianGanZhiShenSha.add(tianDeGuiRenMap.get(this.monthZhi + this.liuNianZhi));
        this.liuYueGanZhiShenSha.add(tianDeGuiRenMap.get(this.monthZhi + this.liuYueGan));
        this.liuYueGanZhiShenSha.add(tianDeGuiRenMap.get(this.monthZhi + this.liuYueZhi));
        this.liuRiGanZhiShenSha.add(tianDeGuiRenMap.get(this.monthZhi + this.liuRiGan));
        this.liuRiGanZhiShenSha.add(tianDeGuiRenMap.get(this.monthZhi + this.liuRiZhi));
        this.liuShiGanZhiShenSha.add(tianDeGuiRenMap.get(this.monthZhi + this.liuShiGan));
        this.liuShiGanZhiShenSha.add(tianDeGuiRenMap.get(this.monthZhi + this.liuShiZhi));

    }

    /**
     * 计算拱禄
     */
    private void gongLu() {

        Map<String, String> gongLuMap = BaZiShenShaMap.GONG_LU; // 拱禄（干支+干支+地支为键）

        this.yearGanZhiShenSha.add(gongLuMap.get(this.dayGanZhi + this.hourGanZhi + this.yearZhi));
        this.monthGanZhiShenSha.add(gongLuMap.get(this.dayGanZhi + this.hourGanZhi + this.monthZhi));
        this.dayGanZhiShenSha.add(gongLuMap.get(this.dayGanZhi + this.hourGanZhi + this.dayZhi));
        this.hourGanZhiShenSha.add(gongLuMap.get(this.dayGanZhi + this.hourGanZhi + this.hourZhi));
        this.dayGanZhiShenSha.add(gongLuMap.get(this.dayGanZhi + this.hourGanZhi + this.daYunZhi));
        this.liuNianGanZhiShenSha.add(gongLuMap.get(this.dayGanZhi + this.hourGanZhi + this.liuNianZhi));
        this.liuYueGanZhiShenSha.add(gongLuMap.get(this.dayGanZhi + this.hourGanZhi + this.liuYueZhi));
        this.liuRiGanZhiShenSha.add(gongLuMap.get(this.dayGanZhi + this.hourGanZhi + this.liuRiZhi));
        this.liuShiGanZhiShenSha.add(gongLuMap.get(this.dayGanZhi + this.hourGanZhi + this.liuShiZhi));

    }

    /**
     * 计算华盖
     */
    private void huaGai() {

        Map<String, String> huaGaiMap = BaZiShenShaMap.HUA_GAI; // 华盖（地支+地支为键）

//        this.yearGanZhiShenSha.add(huaGaiMap.get(this.yearZhi + this.yearZhi));
        this.yearGanZhiShenSha.add(huaGaiMap.get(this.dayZhi + this.yearZhi));
        this.monthGanZhiShenSha.add(huaGaiMap.get(this.yearZhi + this.monthZhi));
        this.monthGanZhiShenSha.add(huaGaiMap.get(this.dayZhi + this.monthZhi));
        this.dayGanZhiShenSha.add(huaGaiMap.get(this.yearZhi + this.dayZhi));
//        this.dayGanZhiShenSha.add(huaGaiMap.get(this.dayZhi + this.dayZhi));
        this.hourGanZhiShenSha.add(huaGaiMap.get(this.yearZhi + this.hourZhi));
        this.hourGanZhiShenSha.add(huaGaiMap.get(this.dayZhi + this.hourZhi));
        this.dayGanZhiShenSha.add(huaGaiMap.get(this.yearZhi + this.daYunZhi));
        this.dayGanZhiShenSha.add(huaGaiMap.get(this.dayZhi + this.daYunZhi));
        this.liuNianGanZhiShenSha.add(huaGaiMap.get(this.yearZhi + this.liuNianZhi));
        this.liuNianGanZhiShenSha.add(huaGaiMap.get(this.dayZhi + this.liuNianZhi));
        this.liuYueGanZhiShenSha.add(huaGaiMap.get(this.yearZhi + this.liuYueZhi));
        this.liuYueGanZhiShenSha.add(huaGaiMap.get(this.dayZhi + this.liuYueZhi));
        this.liuRiGanZhiShenSha.add(huaGaiMap.get(this.yearZhi + this.liuRiZhi));
        this.liuRiGanZhiShenSha.add(huaGaiMap.get(this.dayZhi + this.liuRiZhi));
        this.liuShiGanZhiShenSha.add(huaGaiMap.get(this.yearZhi + this.liuShiZhi));
        this.liuShiGanZhiShenSha.add(huaGaiMap.get(this.dayZhi + this.liuShiZhi));

    }

    /**
     * 计算童子煞
     */
    private void tongZiSha() {

        Map<String, String> tongZiShaMap = BaZiShenShaMap.TONG_ZI_SHA; // 童子煞（季节+地支为键，年柱纳音五行+地支为键）

        this.dayGanZhiShenSha.add(tongZiShaMap.get(this.jiJie + this.dayZhi));
        this.hourGanZhiShenSha.add(tongZiShaMap.get(this.jiJie + this.hourZhi));
        this.dayGanZhiShenSha.add(tongZiShaMap.get(this.yearGanZhiNaYinWuXing + this.dayZhi));
        this.hourGanZhiShenSha.add(tongZiShaMap.get(this.yearGanZhiNaYinWuXing + this.hourZhi));

    }

    /**
     * 计算三奇贵人
     */
    private void sanQiGuiRen() {

        if ("甲戊庚".equals(this.yearGan + this.monthGan + this.dayGan) || "乙丙丁".equals(this.yearGan + this.monthGan + this.dayGan) || "壬癸辛".equals(this.yearGan + this.monthGan + this.dayGan)) {
            this.yearGanZhiShenSha.add("三奇贵人");
            this.monthGanZhiShenSha.add("三奇贵人");
            this.dayGanZhiShenSha.add("三奇贵人");
        } else if ("甲戊庚".equals(this.monthGan + this.dayGan + this.hourGan) || "乙丙丁".equals(this.monthGan + this.dayGan + this.hourGan) || "壬癸辛".equals(this.monthGan + this.dayGan + this.hourGan)) {
            this.monthGanZhiShenSha.add("三奇贵人");
            this.dayGanZhiShenSha.add("三奇贵人");
            this.hourGanZhiShenSha.add("三奇贵人");
        }

    }

    /**
     * 计算六甲空亡
     */
    private void liuJiaKongWang() {

        Map<String, String> liuJiaKongWangMap = BaZiShenShaMap.LIU_JIA_KONG_WANG; // 六甲空亡（年干支\日干支+四柱地支为键）

        this.yearGanZhiShenSha.add(liuJiaKongWangMap.get(this.yearGanZhi + this.yearZhi));
        this.yearGanZhiShenSha.add(liuJiaKongWangMap.get(this.dayGanZhi + this.yearZhi));
        this.monthGanZhiShenSha.add(liuJiaKongWangMap.get(this.yearGanZhi + this.monthZhi));
        this.monthGanZhiShenSha.add(liuJiaKongWangMap.get(this.dayGanZhi + this.monthZhi));
        this.dayGanZhiShenSha.add(liuJiaKongWangMap.get(this.yearGanZhi + this.dayZhi));
        this.dayGanZhiShenSha.add(liuJiaKongWangMap.get(this.dayGanZhi + this.dayZhi));
        this.hourGanZhiShenSha.add(liuJiaKongWangMap.get(this.yearGanZhi + this.hourZhi));
        this.hourGanZhiShenSha.add(liuJiaKongWangMap.get(this.dayGanZhi + this.hourZhi));
        this.dayGanZhiShenSha.add(liuJiaKongWangMap.get(this.yearGanZhi + this.daYunZhi));
        this.dayGanZhiShenSha.add(liuJiaKongWangMap.get(this.dayGanZhi + this.daYunZhi));
        this.liuNianGanZhiShenSha.add(liuJiaKongWangMap.get(this.yearGanZhi + this.liuNianZhi));
        this.liuNianGanZhiShenSha.add(liuJiaKongWangMap.get(this.dayGanZhi + this.liuNianZhi));
        this.liuYueGanZhiShenSha.add(liuJiaKongWangMap.get(this.yearGanZhi + this.liuYueZhi));
        this.liuYueGanZhiShenSha.add(liuJiaKongWangMap.get(this.dayGanZhi + this.liuYueZhi));
        this.liuRiGanZhiShenSha.add(liuJiaKongWangMap.get(this.yearGanZhi + this.liuRiZhi));
        this.liuRiGanZhiShenSha.add(liuJiaKongWangMap.get(this.dayGanZhi + this.liuRiZhi));
        this.liuShiGanZhiShenSha.add(liuJiaKongWangMap.get(this.yearGanZhi + this.liuShiZhi));
        this.liuShiGanZhiShenSha.add(liuJiaKongWangMap.get(this.dayGanZhi + this.liuShiZhi));

    }

    /**
     * 计算天罗地网
     */
    private void tianLuoDiWang() {

        Map<String, String> tianLuoDiWangMap = BaZiShenShaMap.TIAN_LUO_DI_WANG; // 天罗地网（年支\日支+其余三支为键）

        this.yearGanZhiShenSha.add(tianLuoDiWangMap.get(this.yearZhi + this.monthZhi));
        this.yearGanZhiShenSha.add(tianLuoDiWangMap.get(this.yearZhi + this.dayZhi));
        this.yearGanZhiShenSha.add(tianLuoDiWangMap.get(this.yearZhi + this.hourZhi));
        this.yearGanZhiShenSha.add(tianLuoDiWangMap.get(this.monthZhi + this.yearZhi));
//        this.yearGanZhiShenSha.add(tianLuoDiWangMap.get(this.dayZhi) + this.yearZhi);
//        this.yearGanZhiShenSha.add(tianLuoDiWangMap.get(this.hourZhi) + this.yearZhi);
        this.monthGanZhiShenSha.add(tianLuoDiWangMap.get(this.monthZhi + this.dayZhi));
        this.monthGanZhiShenSha.add(tianLuoDiWangMap.get(this.dayZhi + this.monthZhi));
        this.dayGanZhiShenSha.add(tianLuoDiWangMap.get(this.dayZhi + this.hourZhi));
        this.dayGanZhiShenSha.add(tianLuoDiWangMap.get(this.hourZhi + this.dayZhi));
        this.hourGanZhiShenSha.add(tianLuoDiWangMap.get(this.hourZhi + this.monthZhi));
        this.hourGanZhiShenSha.add(tianLuoDiWangMap.get(this.monthZhi + this.hourZhi));
        this.hourGanZhiShenSha.add(tianLuoDiWangMap.get(this.dayZhi + this.hourZhi));
        this.dayGanZhiShenSha.add(tianLuoDiWangMap.get(this.yearZhi + this.daYunZhi));
        this.dayGanZhiShenSha.add(tianLuoDiWangMap.get(this.dayZhi + this.daYunZhi));
        this.dayGanZhiShenSha.add(tianLuoDiWangMap.get(this.daYunZhi + this.yearZhi));
        this.dayGanZhiShenSha.add(tianLuoDiWangMap.get(this.daYunZhi + this.dayZhi));
        this.liuNianGanZhiShenSha.add(tianLuoDiWangMap.get(this.yearZhi + this.liuNianZhi));
        this.liuNianGanZhiShenSha.add(tianLuoDiWangMap.get(this.dayZhi + this.liuNianZhi));
        this.liuNianGanZhiShenSha.add(tianLuoDiWangMap.get(this.liuNianZhi + this.yearZhi));
        this.liuNianGanZhiShenSha.add(tianLuoDiWangMap.get(this.liuNianZhi + this.dayZhi));
        this.liuYueGanZhiShenSha.add(tianLuoDiWangMap.get(this.yearZhi + this.liuYueZhi));
        this.liuYueGanZhiShenSha.add(tianLuoDiWangMap.get(this.dayZhi + this.liuYueZhi));
        this.liuYueGanZhiShenSha.add(tianLuoDiWangMap.get(this.liuYueZhi + this.yearZhi));
        this.liuYueGanZhiShenSha.add(tianLuoDiWangMap.get(this.liuYueZhi + this.dayZhi));
        this.liuRiGanZhiShenSha.add(tianLuoDiWangMap.get(this.yearZhi + this.liuRiZhi));
        this.liuRiGanZhiShenSha.add(tianLuoDiWangMap.get(this.dayZhi + this.liuRiZhi));
        this.liuRiGanZhiShenSha.add(tianLuoDiWangMap.get(this.liuRiZhi + this.yearZhi));
        this.liuRiGanZhiShenSha.add(tianLuoDiWangMap.get(this.liuRiZhi + this.dayZhi));
        this.liuShiGanZhiShenSha.add(tianLuoDiWangMap.get(this.yearZhi + this.liuShiZhi));
        this.liuShiGanZhiShenSha.add(tianLuoDiWangMap.get(this.dayZhi + this.liuShiZhi));
        this.liuShiGanZhiShenSha.add(tianLuoDiWangMap.get(this.liuShiZhi + this.yearZhi));
        this.liuShiGanZhiShenSha.add(tianLuoDiWangMap.get(this.liuShiZhi + this.yearZhi));

    }

    /**
     * 计算冲天煞
     */
    private void chongTianSha() {

        // 判断年干支与月干支是否相同
        if (this.yearGanZhi.equals(this.monthGanZhi)) {
            this.yearGanZhiShenSha.add("冲天煞");
            this.monthGanZhiShenSha.add("冲天煞");

        }

        // 判断日干支与时干支是否相同
        if (this.dayGanZhi.equals(this.hourGanZhi)) {
            this.dayGanZhiShenSha.add("冲天煞");
            this.hourGanZhiShenSha.add("冲天煞");
        }

    }

    /**
     * 计算金神
     */
    private void jinShen() {

        // 判断日支，若匹配则添加日柱神煞
        for (String key : BaZiShenShaMap.JIN_SHEN) {
            if (this.dayGanZhi.equals(key)) this.dayGanZhiShenSha.add("金神");
        }

        // 判断时支，若匹配则添加时柱神煞
        for (String key : BaZiShenShaMap.JIN_SHEN) {
            if (this.hourGanZhi.equals(key)) this.hourGanZhiShenSha.add("金神");
        }

    }

    /**
     * 通用：添加神煞
     *
     * @param strs        神煞常量
     * @param shenShaName 神煞名称
     */
    private void addShenSha1(String[] strs, String shenShaName) {

        // 判断日支，若匹配则添加日柱神煞
        for (String key : strs) {
            if (this.dayGanZhi.equals(key)) this.dayGanZhiShenSha.add(shenShaName);
        }

    }

    /**
     * 通用：添加神煞
     *
     * @param map    神煞常量
     * @param ganZhi 天干\地支
     */
    private void addShenSha2(Map<String, String> map, String ganZhi) {

        this.yearGanZhiShenSha.add(map.get(ganZhi + this.yearGan));
        this.monthGanZhiShenSha.add(map.get(ganZhi + this.monthGan));
        this.dayGanZhiShenSha.add(map.get(ganZhi + this.dayGan));
        this.hourGanZhiShenSha.add(map.get(ganZhi + this.hourGan));
        this.daYunGanZhiShenSha.add(map.get(ganZhi + this.daYunGan));
        this.liuNianGanZhiShenSha.add(map.get(ganZhi + this.liuNianGan));
        this.liuYueGanZhiShenSha.add(map.get(ganZhi + this.liuYueGan));
        this.liuRiGanZhiShenSha.add(map.get(ganZhi + this.liuRiGan));
        this.liuShiGanZhiShenSha.add(map.get(ganZhi + this.liuShiGan));

    }

    /**
     * 通用：添加神煞
     *
     * @param map    神煞常量
     * @param ganZhi 天干\地支
     */
    private void addShenSha3(Map<String, String> map, String ganZhi) {

        this.yearGanZhiShenSha.add(map.get(ganZhi + this.yearGanZhi));
        this.monthGanZhiShenSha.add(map.get(ganZhi + this.monthGanZhi));
        this.dayGanZhiShenSha.add(map.get(ganZhi + this.dayGanZhi));
        this.hourGanZhiShenSha.add(map.get(ganZhi + this.hourGanZhi));
        this.daYunGanZhiShenSha.add(map.get(ganZhi + this.daYunGanZhi));
        this.liuNianGanZhiShenSha.add(map.get(ganZhi + this.liuNianGanZhi));
        this.liuYueGanZhiShenSha.add(map.get(ganZhi + this.liuYueGanZhi));
        this.liuRiGanZhiShenSha.add(map.get(ganZhi + this.liuRiGanZhi));
        this.liuShiGanZhiShenSha.add(map.get(ganZhi + this.liuShiGanZhi));

    }

    /**
     * 通用：添加神煞
     *
     * @param map     神煞常量
     * @param ganZhi1 天干\地支
     * @param ganZhi2 天干\地支
     */
    private void addShenSha4(Map<String, String> map, String ganZhi1, String ganZhi2) {

        this.yearGanZhiShenSha.add(map.get(ganZhi1 + this.yearZhi));
        this.monthGanZhiShenSha.add(map.get(ganZhi1 + this.monthZhi));
        this.dayGanZhiShenSha.add(map.get(ganZhi1 + this.dayZhi));
        this.hourGanZhiShenSha.add(map.get(ganZhi1 + this.hourZhi));
        this.yearGanZhiShenSha.add(map.get(ganZhi2 + this.yearZhi));
        this.monthGanZhiShenSha.add(map.get(ganZhi2 + this.monthZhi));
        this.dayGanZhiShenSha.add(map.get(ganZhi2 + this.dayZhi));
        this.hourGanZhiShenSha.add(map.get(ganZhi2 + this.hourZhi));
        this.daYunGanZhiShenSha.add(map.get(ganZhi1 + this.daYunZhi));
        this.daYunGanZhiShenSha.add(map.get(ganZhi2 + this.daYunZhi));
        this.liuNianGanZhiShenSha.add(map.get(ganZhi1 + this.liuNianZhi));
        this.liuNianGanZhiShenSha.add(map.get(ganZhi2 + this.liuNianZhi));
        this.liuYueGanZhiShenSha.add(map.get(ganZhi1 + this.liuYueZhi));
        this.liuYueGanZhiShenSha.add(map.get(ganZhi2 + this.liuYueZhi));
        this.liuRiGanZhiShenSha.add(map.get(ganZhi1 + this.liuRiZhi));
        this.liuRiGanZhiShenSha.add(map.get(ganZhi2 + this.liuRiZhi));
        this.liuShiGanZhiShenSha.add(map.get(ganZhi1 + this.liuShiZhi));
        this.liuShiGanZhiShenSha.add(map.get(ganZhi2 + this.liuShiZhi));

    }

//----------------------------------------------------------------

    /**
     * 初始化神煞
     */
    protected void initializeShenSha() {

        // 1、计算数据
        if (this.baZiShenShaSetting.getTaiJiGuiRen() == 0) addShenSha4(BaZiShenShaMap.TAI_JI_GUI_REN, this.yearGan, this.dayGan); // 太极贵人
        if (this.baZiShenShaSetting.getTianYiGuiRen() == 0) addShenSha4(BaZiShenShaMap.TIAN_YI_GUI_REN, this.yearGan, this.dayGan); // 天乙贵人
        if (this.baZiShenShaSetting.getFuXingGuiRen() == 0) addShenSha4(BaZiShenShaMap.FU_XING_GUI_REN, this.yearGan, this.dayGan); // 福星贵人
        if (this.baZiShenShaSetting.getWenChangGuiRen() == 0) addShenSha4(BaZiShenShaMap.WEN_CHANG_GUI_REN, this.yearGan, this.dayGan); // 文昌贵人
        if (this.baZiShenShaSetting.getTianChuGuiRen() == 0) addShenSha4(BaZiShenShaMap.TIAN_CHU_GUI_REN, this.yearGan, this.dayGan); // 天厨贵人
        if (this.baZiShenShaSetting.getYueDeGuiRen() == 0) addShenSha2(BaZiShenShaMap.YUE_DE_GUI_REN, this.monthZhi); // 月德贵人
        if (this.baZiShenShaSetting.getDeXiuGuiRen() == 0) addShenSha2(BaZiShenShaMap.DE_XIU_GUI_REN, this.monthZhi); // 德秀贵人
        if (this.baZiShenShaSetting.getTianDeGuiRen() == 0) tianDeGuiRen(); // 天德贵人
        if (this.baZiShenShaSetting.getTianGuanGuiRen() == 0) addShenSha4(BaZiShenShaMap.TIAN_GUAN_GUI_REN, this.yearGan, this.dayGan); // 天官贵人
        if (this.baZiShenShaSetting.getSanQiGuiRen() == 0) sanQiGuiRen(); // 三奇贵人
        if (this.baZiShenShaSetting.getLiuJiaKongWang() == 0) liuJiaKongWang(); // 六甲空亡
        if (this.baZiShenShaSetting.getJieLuKongWang() == 0) this.hourGanZhiShenSha.add(BaZiShenShaMap.JIE_LU_KONG_WANG.get(this.dayGan + this.hourZhi)); // 截路空亡
        if (this.baZiShenShaSetting.getYinZhuYangShou() == 0) addShenSha3(BaZiShenShaMap.YIN_ZHU_YANG_SHOU, this.monthZhi); // 阴注阳受
        if (this.baZiShenShaSetting.getTianLuoDiWang() == 0) tianLuoDiWang(); // 天罗地网
        if (this.baZiShenShaSetting.getShiEDaBai() == 0) addShenSha1(BaZiShenShaMap.SHI_E_DA_BAI, "十恶大败"); // 十恶大败
        if (this.baZiShenShaSetting.getYinChaYangCuo() == 0) addShenSha1(BaZiShenShaMap.YIN_CHA_YANG_CUO, "阴差阳错"); // 阴差阳错
        if (this.baZiShenShaSetting.getSiFeiRi() == 0) this.dayGanZhiShenSha.add(BaZiShenShaMap.SI_FEI_RI.get(this.jiJie + this.dayGanZhi)); // 四废日
        if (this.baZiShenShaSetting.getLiuXiuRi() == 0) addShenSha1(BaZiShenShaMap.LIU_XIU_RI, "六秀日"); // 六秀日
        if (this.baZiShenShaSetting.getShiLingRi() == 0) addShenSha1(BaZiShenShaMap.SHI_LING_RI, "十灵日"); // 十灵日
        if (this.baZiShenShaSetting.getKuiGangRi() == 0) addShenSha1(BaZiShenShaMap.KUI_GANG_RI, "魁罡日"); // 魁罡日
        if (this.baZiShenShaSetting.getBaZhuanRi() == 0) addShenSha1(BaZiShenShaMap.BA_ZHUAN_RI, "八专日"); // 八专日
        if (this.baZiShenShaSetting.getJiuChouRi() == 0) addShenSha1(BaZiShenShaMap.JIU_CHOU_RI, "九丑日"); // 九丑日
        if (this.baZiShenShaSetting.getGuLuanSha() == 0) addShenSha1(BaZiShenShaMap.GU_LUAN_SHA, "孤鸾煞"); // 孤鸾煞
        if (this.baZiShenShaSetting.getHongYanSha() == 0) addShenSha4(BaZiShenShaMap.HONG_YAN_SHA, "", this.dayGan); // 红艳煞
        if (this.baZiShenShaSetting.getGouJiaoSha() == 0) addShenSha4(BaZiShenShaMap.GOU_JIAO_SHA, this.yearZhi, ""); // 勾绞煞
        if (this.baZiShenShaSetting.getTongZiSha() == 0) tongZiSha(); // 童子煞
        if (this.baZiShenShaSetting.getChongTianSha() == 0) chongTianSha(); // 冲天煞
        if (this.baZiShenShaSetting.getHuaGai() == 0) huaGai(); // 华盖
        if (this.baZiShenShaSetting.getGuoYin() == 0) addShenSha4(BaZiShenShaMap.GUO_YIN, this.yearGan, this.dayGan); // 国印
        if (this.baZiShenShaSetting.getJinShen() == 0) jinShen(); // 金神
        if (this.baZiShenShaSetting.getJinYu() == 0) addShenSha4(BaZiShenShaMap.JIN_YU, this.yearGan, this.dayGan); // 金舆
        if (this.baZiShenShaSetting.getGongLu() == 0) gongLu(); // 拱禄
        if (this.baZiShenShaSetting.getLuShen() == 0) addShenSha4(BaZiShenShaMap.LU_SHEN, "", this.dayGan); // 禄神
        if (this.baZiShenShaSetting.getJiangXing() == 0) addShenSha4(BaZiShenShaMap.JIANG_XING, this.yearZhi, this.dayZhi); // 将星
        if (this.baZiShenShaSetting.getTaoHua() == 0) addShenSha4(BaZiShenShaMap.TAO_HUA, this.yearZhi, this.dayZhi); // 桃花
        if (this.baZiShenShaSetting.getTianXi() == 0) addShenSha4(BaZiShenShaMap.TIAN_XI, this.yearZhi, ""); // 天喜
        if (this.baZiShenShaSetting.getHongLuan() == 0) addShenSha4(BaZiShenShaMap.HONG_LUAN, this.yearZhi, ""); // 红鸾
        if (this.baZiShenShaSetting.getTianYi() == 0) addShenSha4(BaZiShenShaMap.TIAN_YI, this.monthZhi, ""); // 天医
        if (this.baZiShenShaSetting.getCiGuan() == 0) addShenSha4(BaZiShenShaMap.CI_GUAN_LU_MING, this.yearGanZhiNaYinWuXing, ""); // 词馆
        if (this.baZiShenShaSetting.getXueTang() == 0) addShenSha4(BaZiShenShaMap.XUE_TANG_LU_MING, this.yearGanZhiNaYinWuXing, ""); // 学堂
        if (this.baZiShenShaSetting.getTianShe() == 0) addShenSha3(BaZiShenShaMap.TIAN_SHE, this.monthZhi); // 天赦
        if (this.baZiShenShaSetting.getYiMa() == 0) addShenSha4(BaZiShenShaMap.YI_MA, this.yearZhi, this.dayZhi); // 驿马
        if (this.baZiShenShaSetting.getYangRen() == 0) addShenSha4(BaZiShenShaMap.YANG_REN, "", this.dayGan); // 羊刃
        if (this.baZiShenShaSetting.getFeiRen() == 0) addShenSha4(BaZiShenShaMap.FEI_REN, "", this.dayGan); // 飞刃
        if (this.baZiShenShaSetting.getLiuXia() == 0) addShenSha4(BaZiShenShaMap.LIU_XIA, "", this.dayGan); // 流霞
        if (this.baZiShenShaSetting.getJieSha() == 0) addShenSha4(BaZiShenShaMap.JIE_SHA, this.yearZhi, this.dayZhi); // 劫煞
        if (this.baZiShenShaSetting.getWangShen() == 0) addShenSha4(BaZiShenShaMap.WANG_SHEN, this.yearZhi, this.dayZhi); // 亡神
        if (this.baZiShenShaSetting.getDiaoKe() == 0) addShenSha4(BaZiShenShaMap.DIAO_KE, this.yearZhi, this.dayZhi); // 吊客
        if (this.baZiShenShaSetting.getPiMa() == 0) addShenSha4(BaZiShenShaMap.PI_MA, this.yearZhi, this.dayZhi); // 披麻
        if (this.baZiShenShaSetting.getSangMen() == 0) addShenSha4(BaZiShenShaMap.SANG_MEN, this.yearZhi, ""); // 丧门
        if (this.baZiShenShaSetting.getZaiSha() == 0) addShenSha4(BaZiShenShaMap.ZAI_SHA, this.yearZhi, ""); // 灾煞
        if (this.baZiShenShaSetting.getGuChen() == 0) addShenSha4(BaZiShenShaMap.GU_CHEN, this.yearZhi, ""); // 孤辰
        if (this.baZiShenShaSetting.getGuaXiu() == 0) addShenSha4(BaZiShenShaMap.GUA_XIU, this.yearZhi, ""); // 寡宿
        if (this.baZiShenShaSetting.getYuanChen() == 0) yuanChen(); // 元辰
        if (this.baZiShenShaSetting.getXueRen() == 0) addShenSha4(BaZiShenShaMap.XUE_REN, this.monthZhi, ""); // 血刃
        if (this.baZiShenShaSetting.getTianZhuan() == 0) addShenSha3(BaZiShenShaMap.TIAN_ZHUAN, this.monthZhi); // 天转
        if (this.baZiShenShaSetting.getDiZhuan() == 0) addShenSha3(BaZiShenShaMap.DI_ZHUAN, this.monthZhi); // 地转
        if (this.baZiShenShaSetting.getLiuE() == 0) addShenSha3(BaZiShenShaMap.LIU_E, this.yearZhi); // 六厄

        // 2、删除重复数据
        this.yearGanZhiShenSha = CommonUtil.removeDuplicates(this.yearGanZhiShenSha); // 年干支神煞
        this.monthGanZhiShenSha = CommonUtil.removeDuplicates(this.monthGanZhiShenSha); // 月干支神煞
        this.dayGanZhiShenSha = CommonUtil.removeDuplicates(this.dayGanZhiShenSha); // 日干支神煞
        this.hourGanZhiShenSha = CommonUtil.removeDuplicates(this.hourGanZhiShenSha); // 时干支神煞
        this.daYunGanZhiShenSha = CommonUtil.removeDuplicates(this.daYunGanZhiShenSha); // 大运干支神煞
        this.liuNianGanZhiShenSha = CommonUtil.removeDuplicates(this.liuNianGanZhiShenSha); // 流年干支神煞
        this.liuYueGanZhiShenSha = CommonUtil.removeDuplicates(this.liuYueGanZhiShenSha); // 流月干支神煞
        this.liuRiGanZhiShenSha = CommonUtil.removeDuplicates(this.liuRiGanZhiShenSha); // 流日干支神煞
        this.liuShiGanZhiShenSha = CommonUtil.removeDuplicates(this.liuShiGanZhiShenSha); // 流时干支神煞

    }


}
