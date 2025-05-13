package xuan.core.meihua.settings;

import java.util.Date;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 梅花易数 - 基础设置
 *
 * @author 善待
 * @version 2.0.0
 * @blessing ☯福生无量☯
 */
@Data
public class MeiHuaJiChuSetting {

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别（0:女。1:男）
     */
    private int sex;

    /**
     * 占事
     */
    private String occupy;

    /**
     * 日期
     */
    private Date date;

    /**
     * 日期类型（0:公历。1:农历）
     */
    private int dateType;

    /**
     * 闰月类型（0:不使用闰月。1:使用闰月）
     */
    private int leapMonthType;

    /**
     * 节气类型（0:按天计算。1:按分钟计算）
     */
    private int jieQiType;

    /**
     * 排盘类型（0:日期。1:自动。2:数字。3:单数。4:双数）
     */
    private int paiPanType;

    /**
     * 排盘数
     */
    private int paiPanShu;

    /**
     * 排盘单数
     */
    private int paiPanDanShu;

    /**
     * 排盘双数1
     */
    private int paiPanShuangShuOne;

    /**
     * 排盘双数2
     */
    private int paiPanShuangShuTwo;

    /**
     * 双数排盘时上下卦类型（0:双数求和计算上下卦。1:双数不求和计算上下卦）
     */
    private int paiPanShuangShuShangXiaGuaType;

    /**
     * 双数排盘时动爻类型（0:双数求和计算动爻。1:双数求和加时辰数计算动爻）
     */
    private int paiPanShuangShuDongYaoType;

    /**
     * 年干支类型（0:以正月初一作为新年的开始。1:以立春当天作为新年的开始。2:以立春交接时刻作为新年的开始）
     */
    private int yearGanZhiType;

    /**
     * 月干支类型（0:以节交接当天起算。1:以节交接时刻起算）
     */
    private int monthGanZhiType;

    /**
     * 日干支类型（0:晚子时日柱按明天。1:晚子时日柱按当天）
     */
    private int dayGanZhiType;

    /**
     * 时干支类型（0:支持早子时和晚子时）
     */
    private int hourGanZhiType;

//*******************************************************************************************************************************

    /**
     * 初始化设置
     */
    public MeiHuaJiChuSetting() {

        this.name = ""; // 姓名（默认→ 空）
        this.sex = 1; // 性别（默认→ 男）
        this.occupy = ""; // 占事（默认→ 空）
        this.date = new Date(); // 日期（默认→ 当前时刻）
        this.dateType = 0; // 日期类型（默认→ 公历）
        this.leapMonthType = 0; // 闰月类型（默认→ 不使用闰月）
        this.jieQiType = 1; // 节气类型（默认→ 按分钟计算）
        this.paiPanType = 0; // 排盘类型（默认→ 日期）
        this.paiPanShu = 111; // 排盘数（默认→ 111）
        this.paiPanDanShu = 111; // 排盘单数（默认→ 111）
        this.paiPanShuangShuOne = 111; // 排盘双数1（默认→ 111）
        this.paiPanShuangShuTwo = 111; // 排盘双数2（默认→ 111）
        this.paiPanShuangShuShangXiaGuaType = 1; // 双数排盘时上下卦类型（默认→ 双数不求和计算上下卦）
        this.paiPanShuangShuDongYaoType = 0; // 双数排盘时动爻类型（默认→ 双数求和计算动爻）
        this.yearGanZhiType = 2; // 年干支类型（默认→ 以立春交接时刻作为新年的开始）
        this.monthGanZhiType = 1; // 月干支类型（默认→ 以节交接时刻起算）
        this.dayGanZhiType = 0; // 日干支类型（默认→ 晚子时日柱按当天）
        this.hourGanZhiType = 0; // 时干支类型（默认→ 支持早子时和晚子时）

    }

//===============================================================================================================================

    /**
     * 姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = StringUtils.isNotBlank(name) ? name : "";
    }

    /**
     * 性别
     *
     * @param sex 性别（0:女。1:男）
     */
    public void setSex(int sex) {
        this.sex = (sex == 0 || sex == 1) ? sex : 1;
    }

    /**
     * 占事
     *
     * @param occupy 占事
     */
    public void setOccupy(String occupy) {
        this.occupy = StringUtils.isNotBlank(occupy) ? occupy : "";
    }

    /**
     * 日期
     *
     * @param date 日期
     */
    public void setDate(Date date) {
        this.date = (null != date) ? date : new Date();
    }

    /**
     * 日期类型
     *
     * @param dateType 日期类型（0:公历。1:农历）
     */
    public void setDateType(int dateType) {
        this.dateType = (dateType == 0 || dateType == 1) ? dateType : 0;
    }

    /**
     * 闰月类型
     *
     * @param leapMonthType 闰月类型（0:不使用闰月。1:使用闰月）
     */
    public void setLeapMonthType(int leapMonthType) {
        this.leapMonthType = (leapMonthType == 0 || leapMonthType == 1) ? leapMonthType : 0;
    }

    /**
     * 节气类型
     *
     * @param jieQiType 节气类型（0:按天计算。1:按分钟计算）
     */
    public void setJieQiType(int jieQiType) {
        this.jieQiType = (jieQiType == 0 || jieQiType == 1) ? jieQiType : 1;
    }

    /**
     * 排盘类型
     *
     * @param paiPanType 排盘类型（0:日期。1:自动。2:数字。3:单数。4:双数）
     */
    public void setPaiPanType(int paiPanType) {
        this.paiPanType = (paiPanType == 0 || paiPanType == 1 || paiPanType == 2 || paiPanType == 3 || paiPanType == 4) ? paiPanType : 0;
    }

    /**
     * 排盘数
     *
     * @param paiPanShu 排盘数
     */
    public void setPaiPanShu(int paiPanShu) {
        this.paiPanShu = (paiPanShu > 100 && paiPanShu < 999) ? paiPanShu : 111;
    }

    /**
     * 排盘单数
     *
     * @param paiPanDanShu 排盘单数
     */
    public void setPaiPanDanShu(int paiPanDanShu) {
        this.paiPanDanShu = (paiPanDanShu > 0 && paiPanDanShu < 2147483647) ? paiPanDanShu : 111;
    }

    /**
     * 排盘双数1
     *
     * @param paiPanShuangShuOne 排盘双数1
     */
    public void setPaiPanShuangShuOne(int paiPanShuangShuOne) {
        this.paiPanShuangShuOne = (paiPanShuangShuOne > 0 && paiPanShuangShuOne < 2147483647) ? paiPanShuangShuOne : 111;
    }

    /**
     * 排盘双数2
     *
     * @param paiPanShuangShuTwo 排盘双数2
     */
    public void setPaiPanShuangShuTwo(int paiPanShuangShuTwo) {
        this.paiPanShuangShuTwo = (paiPanShuangShuTwo > 0 && paiPanShuangShuTwo < 2147483647) ? paiPanShuangShuTwo : 111;
    }

    /**
     * 双数排盘时上下卦类型
     *
     * @param paiPanShuangShuShangXiaGuaType 双数排盘时上下卦类型（0:双数求和计算上下卦。1:双数不求和计算上下卦）
     */
    public void setPaiPanShuangShuShangXiaGuaType(int paiPanShuangShuShangXiaGuaType) {
        this.paiPanShuangShuShangXiaGuaType = (paiPanShuangShuShangXiaGuaType == 0 || paiPanShuangShuShangXiaGuaType == 1) ? paiPanShuangShuShangXiaGuaType : 1;
    }

    /**
     * 双数排盘时动爻类型
     *
     * @param paiPanShuangShuDongYaoType 双数排盘时动爻类型（0:双数求和计算动爻。1:双数求和加时辰数计算动爻）
     */
    public void setPaiPanShuangShuDongYaoType(int paiPanShuangShuDongYaoType) {
        this.paiPanShuangShuDongYaoType = (paiPanShuangShuDongYaoType == 0 || paiPanShuangShuDongYaoType == 1) ? paiPanShuangShuDongYaoType : 0;
    }

    /**
     * 年干支类型
     *
     * @param yearGanZhiType 年干支类型（0:以正月初一作为新年的开始。1:以立春当天作为新年的开始。2:以立春交接时刻作为新年的开始）
     */
    public void setYearGanZhiType(int yearGanZhiType) {
        this.yearGanZhiType = (yearGanZhiType == 0 || yearGanZhiType == 1 || yearGanZhiType == 2) ? yearGanZhiType : 2;
    }

    /**
     * 月干支类型
     *
     * @param monthGanZhiType 月干支类型（0:以节交接当天起算。1:以节交接时刻起算）
     */
    public void setMonthGanZhiType(int monthGanZhiType) {
        this.monthGanZhiType = (monthGanZhiType == 0 || monthGanZhiType == 1) ? monthGanZhiType : 1;
    }

    /**
     * 日干支类型
     *
     * @param dayGanZhiType 日干支类型（0:晚子时日干支按当天。1:晚子时日干支按明天）
     */
    public void setDayGanZhiType(int dayGanZhiType) {
        this.dayGanZhiType = (dayGanZhiType == 0 || dayGanZhiType == 1) ? dayGanZhiType : 0;
    }

    /**
     * 时干支类型
     *
     * @param hourGanZhiType 时干支类型（0:支持早子时和晚子时）
     */
    public void setHourGanZhiType(int hourGanZhiType) {
        this.hourGanZhiType = (hourGanZhiType == 0 || hourGanZhiType == 1) ? hourGanZhiType : 0;
    }


}
