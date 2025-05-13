package xuan.core.bazi;

import java.util.*;

import xuan.utils.DateUtil;
import xuan.utils.CommonUtil;
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import com.nlf.calendar.EightChar;
import com.nlf.calendar.eightchar.Yun;
import xuan.core.bazi.maps.BaZiJiChuMap;
import com.nlf.calendar.eightchar.DaYun;
import com.nlf.calendar.eightchar.LiuNian;
import xuan.core.bazi.utils.BaZiJiChuUtil;
import xuan.core.bazi.utils.BaZiShenShaUtil;
import xuan.core.bazi.utils.BaZiGanZhiLiuYiUtil;
import xuan.core.bazi.settings.BaZiJiChuSetting;
import xuan.core.bazi.settings.BaZiShenShaSetting;
import xuan.core.bazi.settings.BaZiGanZhiLiuYiSetting;

/**
 * 八字
 *
 * @author 善待
 * @version 2.0.0
 * @blessing ☯福生无量☯
 */
public class BaZi {

    /**
     * 八字 - 基础设置
     */
    private BaZiJiChuSetting baZiJiChuSetting;
    /**
     * 八字 - 神煞设置
     */
    private BaZiShenShaSetting baZiShenShaSetting = new BaZiShenShaSetting();
    /**
     * 八字 - 干支留意设置
     */
    private BaZiGanZhiLiuYiSetting baZiGanZhiLiuYiSetting = new BaZiGanZhiLiuYiSetting();

    /**
     * 公历日期
     */
    private Solar solar;
    /**
     * 农历日期
     */
    private Lunar lunar;

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
     * 八字
     */
    protected EightChar eightChar;
    /**
     * 运
     */
    protected Yun yun;
    /**
     * 小运持续的年数
     */
    protected int xiaoYunYearSum;

    /**
     * 流年
     */
    protected List<List<String>> liuNian;
    /**
     * 流年是否已初始化（true:已初始化。false:未初始化）
     */
    protected boolean liuNianStatus;

    /**
     * 八字神煞工具
     */
    protected BaZiShenShaUtil baZiShenShaUtil;
    /**
     * 八字神煞工具是否已初始化（true:已初始化。false:未初始化）
     */
    protected boolean baZiShenShaUtilStatus;

//*******************************************************************************************************************************

    /**
     * 使用基础设置初始化
     *
     * @param baZiJiChuSetting 八字 - 基础设置
     */
    public BaZi(BaZiJiChuSetting baZiJiChuSetting) {

        this.baZiJiChuSetting = baZiJiChuSetting; // 基础设置

        // 1、获取日期
        Map<String, Object> date = BaZiJiChuUtil.getDate(baZiJiChuSetting);
        this.solar = (Solar) date.get("solar"); // 公历日期
        this.lunar = (Lunar) date.get("lunar"); // 农历日期

        // 2、获取干支
        Map<String, List<String>> ganZhi = BaZiJiChuUtil.getGanZhi(baZiJiChuSetting, this.lunar);
        List<String> yearGanZhi = ganZhi.get("yearGanZhi");
        this.yearGan = yearGanZhi.get(0); // 年干
        this.yearZhi = yearGanZhi.get(1); // 年支
        this.yearGanZhi = yearGanZhi.get(2); // 年干支
        List<String> monthGanZhi = ganZhi.get("monthGanZhi");
        this.monthGan = monthGanZhi.get(0); // 月干
        this.monthZhi = monthGanZhi.get(1); // 月支
        this.monthGanZhi = monthGanZhi.get(2); // 月干支
        List<String> dayGanZhi = ganZhi.get("dayGanZhi");
        this.dayGan = dayGanZhi.get(0); // 日干
        this.dayZhi = dayGanZhi.get(1); // 日支
        this.dayGanZhi = dayGanZhi.get(2); // 日干支
        List<String> hourGanZhi = ganZhi.get("hourGanZhi");
        this.hourGan = hourGanZhi.get(0); // 时干
        this.hourZhi = hourGanZhi.get(1); // 时支
        this.hourGanZhi = hourGanZhi.get(2); // 时干支

        // 3、初始化外部类
        EightChar eightChar = this.lunar.getEightChar();
        eightChar.setSect(baZiJiChuSetting.getDayGanZhiType() + 1);
        this.eightChar = eightChar; // 八字
        this.yun = eightChar.getYun(baZiJiChuSetting.getSex(), baZiJiChuSetting.getQiYunLiuPaiType() + 1); // 运

        // 4、初始化必要数据
        initializeLiuNian(); // 初始化流年

    }

    /**
     * 神煞设置（可选）
     * <p>
     * <hr/>
     * 可选，若不设置则使用默认值，详情请在设置中查看
     */
    public void baZiShenShaSetting(BaZiShenShaSetting baZiShenShaSetting) {
        this.baZiShenShaSetting = baZiShenShaSetting;
    }

    /**
     * 干支留意设置（可选）
     * <p>
     * <hr/>
     * 可选，若不设置则使用默认值，详情请在设置中查看
     */
    public void baZiGanZhiLiuYiSetting(BaZiGanZhiLiuYiSetting baZiGanZhiLiuYiSetting) {
        this.baZiGanZhiLiuYiSetting = baZiGanZhiLiuYiSetting;
    }

//===============================================================================================================================

    /**
     * 获取公历日期（Solar型）
     *
     * @return 公历日期（如：2024-01-01）
     */
    public Solar getSolar() {
        return this.solar;
    }

    /**
     * 获取农历日期（Lunar型）
     *
     * @return 农历日期（如：二〇二三年冬月二十）
     */
    public Lunar getLunar() {
        return this.lunar;
    }

    /**
     * 获取公历日期（String型）
     *
     * @return 公历日期（如：2024年01月01日00时00分00秒）
     */
    public String getSolarStr() {
        return DateUtil.getSolarStr(this.solar);
    }

    /**
     * 获取农历日期（String型）
     *
     * @return 农历日期（如：二〇二三年冬月二十(早)子时）
     */
    public String getLunarStr() {
        return DateUtil.getLunarStr(this.lunar);
    }

    /**
     * 获取公历日期（Date型）
     *
     * @return 公历日期（如：Mon Jan 01 00:00:00 CST 2024）
     */
    public Date getSolarDate() {
        return DateUtil.stringToDate(DateUtil.getSolarStr(this.solar));
    }

    /**
     * 获取农历日期（Date型）
     *
     * @return 农历日期（如：Mon Nov 20 00:00:00 CST 2023）
     */
    public Date getLunarDate() {
        return DateUtil.stringToDate(DateUtil.getLunarStr2(this.lunar));
    }


    /**
     * 获取姓名
     *
     * @return 姓名（如：命主）
     */
    public String getName() {
        return this.baZiJiChuSetting.getName();
    }

    /**
     * 获取性别
     *
     * @return 性别（如：男）
     */
    public String getSex() {
        return this.baZiJiChuSetting.getSex() == 0 ? "女" : "男";
    }

    /**
     * 获取占事
     *
     * @return 占事（如：未填写）
     */
    public String getOccupy() {
        return this.baZiJiChuSetting.getOccupy();
    }

    /**
     * 获取造
     *
     * @return 造（如：乾造）
     */
    public String getZao() {
        return this.baZiJiChuSetting.getSex() == 0 ? "坤造" : "乾造";
    }

    /**
     * 获取太岁类型
     *
     * @return 太岁类型，若无数据则返回null（如：值）
     */
    public String getTaiSuiType() {

        if (this.baZiJiChuSetting.getYearGanZhiType() == 0) {
            return BaZiJiChuMap.TAI_SUI_TYPE.get(new Lunar().getYearZhi() + this.yearZhi); // 以正月初一起算
        } else if (this.baZiJiChuSetting.getYearGanZhiType() == 1) {
            BaZiJiChuMap.TAI_SUI_TYPE.get(new Lunar().getYearZhiByLiChun() + this.yearZhi); // 以立春当天起算
        } else if (this.baZiJiChuSetting.getYearGanZhiType() == 2) {
            BaZiJiChuMap.TAI_SUI_TYPE.get(new Lunar().getYearZhiExact() + this.yearZhi); // 以立春交接时刻起算
        }

        return BaZiJiChuMap.TAI_SUI_TYPE.get(new Lunar().getYearZhi() + this.yearZhi);

    }

    /**
     * 获取星期
     *
     * @return 星期（如：周一）
     */
    public String getXingQi() {
        return "周" + this.lunar.getWeekInChinese();
    }

    /**
     * 获取季节
     *
     * @return 季节（如：孟春）
     */
    public String getJiJie() {
        return this.lunar.getSeason();
    }

    /**
     * 获取生肖
     *
     * @return 生肖（如：鼠）
     */
    public String getShengXiao() {

        if (this.baZiJiChuSetting.getYearGanZhiType() == 0) {
            return this.lunar.getYearShengXiao(); // 以正月初一起算
        } else if (this.baZiJiChuSetting.getYearGanZhiType() == 1) {
            return this.lunar.getYearShengXiaoByLiChun(); // 以立春当天起算
        } else if (this.baZiJiChuSetting.getYearGanZhiType() == 2) {
            return this.lunar.getYearShengXiaoExact(); // 以立春交接时刻起算
        }

        return this.lunar.getYearShengXiao();

    }

    /**
     * 获取年冲生肖
     *
     * @return 年冲生肖（如：鼠）
     */
    public String getYearChongShengXiao() {
        return BaZiJiChuMap.DI_ZHI_SHENG_XIAO.get(BaZiJiChuMap.DI_ZHI_CHONG.get(this.yearZhi));
    }

    /**
     * 获取月冲生肖
     *
     * @return 月冲生肖（如：鼠）
     */
    public String getMonthChongShengXiao() {
        return BaZiJiChuMap.DI_ZHI_SHENG_XIAO.get(BaZiJiChuMap.DI_ZHI_CHONG.get(this.monthZhi));
    }

    /**
     * 获取日冲生肖
     *
     * @return 日冲生肖（如：鼠）
     */
    public String getDayChongShengXiao() {
        return BaZiJiChuMap.DI_ZHI_SHENG_XIAO.get(BaZiJiChuMap.DI_ZHI_CHONG.get(this.dayZhi));
    }

    /**
     * 获取时冲生肖
     *
     * @return 时冲生肖（如：鼠）
     */
    public String getHourChongShengXiao() {
        return BaZiJiChuMap.DI_ZHI_SHENG_XIAO.get(BaZiJiChuMap.DI_ZHI_CHONG.get(this.hourZhi));
    }

    /**
     * 获取星座
     *
     * @return 星座（如：魔羯座）
     */
    public String getXingZuo() {
        return this.solar.getXingZuo() + "座";
    }

    /**
     * 获取月相
     *
     * @return 月相（如：朔）
     */
    public String getYueXiang() {
        return this.lunar.getYueXiang();
    }

    /**
     * 获取月将
     *
     * @return 月将（如：子）
     */
    public String getYueJiang() {
        return BaZiJiChuMap.YUE_JIANG.get(this.monthZhi).get(0);
    }

    /**
     * 获取月将神
     *
     * @return 月将神（如：神后）
     */
    public String getYueJiangShen() {
        return BaZiJiChuMap.YUE_JIANG.get(this.monthZhi).get(1);
    }

    /**
     * 获取五不遇时
     *
     * @return 五不遇时（true:符合。false:不符合）
     */
    public boolean getWuBuYuShi() {
        return this.hourGanZhi.equals(BaZiJiChuMap.WU_BU_YU_SHI.get(this.dayGan));
    }


    /**
     * 获取值年九星五行
     *
     * @return 值年九星五行（如：木）
     */
    public String getYearJiuXingWuXing() {
        return this.lunar.getYearNineStar(this.baZiJiChuSetting.getYearGanZhiType() + 1).getWuXing();
    }

    /**
     * 获取值月九星五行
     *
     * @return 值月九星五行（如：木）
     */
    public String getMonthJiuXingWuXing() {
        return this.lunar.getMonthNineStar(this.baZiJiChuSetting.getMonthGanZhiType() + 2).getWuXing();
    }

    /**
     * 获取值日九星五行
     *
     * @return 值日九星五行（如：木）
     */
    public String getDayJiuXingWuXing() {
        return this.lunar.getDayNineStar().getWuXing();
    }

    /**
     * 获取值时九星五行
     *
     * @return 值时九星五行（如：木）
     */
    public String getHourJiuXingWuXing() {
        return this.lunar.getTimeNineStar().getWuXing();
    }


    /**
     * 获取值年九星方位
     *
     * @return 值年九星方位（如：震）
     */
    public String getYearJiuXingFangWei() {
        return this.lunar.getYearNineStar(this.baZiJiChuSetting.getYearGanZhiType() + 1).getPosition();
    }

    /**
     * 获取值月九星方位
     *
     * @return 值月九星方位（如：震）
     */
    public String getMonthJiuXingFangWei() {
        return this.lunar.getMonthNineStar(this.baZiJiChuSetting.getMonthGanZhiType() + 2).getPosition();
    }

    /**
     * 获取值日九星方位
     *
     * @return 值日九星方位（如：震）
     */
    public String getDayJiuXingFangWei() {
        return this.lunar.getDayNineStar().getPosition();
    }

    /**
     * 获取值时九星方位
     *
     * @return 值时九星方位（如：震）
     */
    public String getHourJiuXingFangWei() {
        return this.lunar.getTimeNineStar().getPosition();
    }


    /**
     * 获取值年九星方位描述
     *
     * @return 值年九星方位描述（如：正东）
     */
    public String getYearJiuXingFangWeiMiaoShu() {
        return this.lunar.getYearNineStar(this.baZiJiChuSetting.getYearGanZhiType() + 1).getPositionDesc();
    }

    /**
     * 获取值月九星方位描述
     *
     * @return 值月九星方位描述（如：正东）
     */
    public String getMonthJiuXingFangWeiMiaoShu() {
        return this.lunar.getMonthNineStar(this.baZiJiChuSetting.getMonthGanZhiType() + 2).getPositionDesc();
    }

    /**
     * 获取值日九星方位描述
     *
     * @return 值日九星方位描述（如：正东）
     */
    public String getDayJiuXingFangWeiMiaoShu() {
        return this.lunar.getDayNineStar().getPositionDesc();
    }

    /**
     * 获取值时九星方位描述
     *
     * @return 值时九星方位描述（如：正东）
     */
    public String getHourJiuXingFangWeiMiaoShu() {
        return this.lunar.getTimeNineStar().getPositionDesc();
    }


    /**
     * 获取值年九星之玄空九星
     *
     * @return 值年九星之玄空九星（如：文曲）
     */
    public String getYearJiuXingXuanKong() {
        return this.lunar.getYearNineStar(this.baZiJiChuSetting.getYearGanZhiType() + 1).getNameInXuanKong();
    }

    /**
     * 获取值月九星之玄空九星
     *
     * @return 值月九星之玄空九星（如：文曲）
     */
    public String getMonthJiuXingXuanKong() {
        return this.lunar.getMonthNineStar(this.baZiJiChuSetting.getMonthGanZhiType() + 2).getNameInXuanKong();
    }

    /**
     * 获取值日九星之玄空九星
     *
     * @return 值日九星之玄空九星（如：文曲）
     */
    public String getDayJiuXingXuanKong() {
        return this.lunar.getDayNineStar().getNameInXuanKong();
    }

    /**
     * 获取值时九星之玄空九星
     *
     * @return 值时九星之玄空九星（如：文曲）
     */
    public String getHourJiuXingXuanKong() {
        return this.lunar.getTimeNineStar().getNameInXuanKong();
    }


    /**
     * 获取值年九星之北斗九星
     *
     * @return 值年九星之北斗九星（如：天权）
     */
    public String getYearJiuXingBeiDou() {
        return this.lunar.getYearNineStar(this.baZiJiChuSetting.getYearGanZhiType() + 1).getNameInBeiDou();
    }

    /**
     * 获取值月九星之北斗九星
     *
     * @return 值月九星之北斗九星（如：天权）
     */
    public String getMonthJiuXingBeiDou() {
        return this.lunar.getMonthNineStar(this.baZiJiChuSetting.getMonthGanZhiType() + 2).getNameInBeiDou();
    }

    /**
     * 获取值日九星之北斗九星
     *
     * @return 值日九星之北斗九星（如：天权）
     */
    public String getDayJiuXingBeiDou() {
        return this.lunar.getDayNineStar().getNameInBeiDou();
    }

    /**
     * 获取值时九星之北斗九星
     *
     * @return 值时九星之北斗九星（如：天权）
     */
    public String getHourJiuXingBeiDou() {
        return this.lunar.getTimeNineStar().getNameInBeiDou();
    }


    /**
     * 获取值年九星之奇门九星
     *
     * @return 值年九星之奇门九星（如：天心）
     */
    public String getYearJiuXingQiMen() {
        return this.lunar.getYearNineStar(this.baZiJiChuSetting.getYearGanZhiType() + 1).getNameInQiMen();
    }

    /**
     * 获取值月九星之奇门九星
     *
     * @return 值月九星之奇门九星（如：天心）
     */
    public String getMonthJiuXingQiMen() {
        return this.lunar.getMonthNineStar(this.baZiJiChuSetting.getMonthGanZhiType() + 2).getNameInQiMen();
    }

    /**
     * 获取值日九星之奇门九星
     *
     * @return 值日九星之奇门九星（如：天心）
     */
    public String getDayJiuXingQiMen() {
        return this.lunar.getDayNineStar().getNameInQiMen();
    }

    /**
     * 获取值时九星之奇门九星
     *
     * @return 值时九星之奇门九星（如：天心）
     */
    public String getHourJiuXingQiMen() {
        return this.lunar.getTimeNineStar().getNameInQiMen();
    }


    /**
     * 获取值年九星之太乙九星
     *
     * @return 值年九星之太乙九星（如：轩辕）
     */
    public String getYearJiuXingTaiYi() {
        return this.lunar.getYearNineStar(this.baZiJiChuSetting.getYearGanZhiType() + 1).getNameInTaiYi();
    }

    /**
     * 获取值月九星之太乙九星
     *
     * @return 值月九星之太乙九星（如：轩辕）
     */
    public String getMonthJiuXingTaiYi() {
        return this.lunar.getMonthNineStar(this.baZiJiChuSetting.getMonthGanZhiType() + 2).getNameInTaiYi();
    }

    /**
     * 获取值日九星之太乙九星
     *
     * @return 值日九星之太乙九星（如：轩辕）
     */
    public String getDayJiuXingTaiYi() {
        return this.lunar.getDayNineStar().getNameInTaiYi();
    }

    /**
     * 获取值时九星之太乙九星
     *
     * @return 值时九星之太乙九星（如：轩辕）
     */
    public String getHourJiuXingTaiYi() {
        return this.lunar.getTimeNineStar().getNameInTaiYi();
    }


    /**
     * 获取年干
     *
     * @return 年干（如：甲）
     */
    public String getYearGan() {
        return this.yearGan;
    }

    /**
     * 获取月干
     *
     * @return 月干（如：甲）
     */
    public String getMonthGan() {
        return this.monthGan;
    }

    /**
     * 获取日干
     *
     * @return 日干（如：甲）
     */
    public String getDayGan() {
        return this.dayGan;
    }

    /**
     * 获取时干
     *
     * @return 时干（如：甲）
     */
    public String getHourGan() {
        return this.hourGan;
    }


    /**
     * 获取年支
     *
     * @return 年支（如：子）
     */
    public String getYearZhi() {
        return this.yearZhi;
    }

    /**
     * 获取月支
     *
     * @return 月支（如：子）
     */
    public String getMonthZhi() {
        return this.monthZhi;
    }

    /**
     * 获取日支
     *
     * @return 日支（如：子）
     */
    public String getDayZhi() {
        return this.dayZhi;
    }

    /**
     * 获取时支
     *
     * @return 时支（如：子）
     */
    public String getHourZhi() {
        return this.hourZhi;
    }


    /**
     * 获取年干支
     *
     * @return 年干支（如：甲子）
     */
    public String getYearGanZhi() {
        return this.yearGanZhi;
    }

    /**
     * 获取月干支
     *
     * @return 月干支（如：甲子）
     */
    public String getMonthGanZhi() {
        return this.monthGanZhi;
    }

    /**
     * 获取日干支
     *
     * @return 日干支（如：甲子）
     */
    public String getDayGanZhi() {
        return this.dayGanZhi;
    }

    /**
     * 获取时干支
     *
     * @return 时干支（如：甲子）
     */
    public String getHourGanZhi() {
        return this.hourGanZhi;
    }


    /**
     * 获取年干五行
     *
     * @return 年干五行（如：木）
     */
    public String getYearGanWuXing() {
        return BaZiJiChuMap.TIAN_GAN_WU_XING.get(this.yearGan);
    }

    /**
     * 获取月干五行
     *
     * @return 月干五行（如：木）
     */
    public String getMonthGanWuXing() {
        return BaZiJiChuMap.TIAN_GAN_WU_XING.get(this.monthGan);
    }

    /**
     * 获取日干五行
     *
     * @return 日干五行（如：木）
     */
    public String getDayGanWuXing() {
        return BaZiJiChuMap.TIAN_GAN_WU_XING.get(this.dayGan);
    }

    /**
     * 获取时干五行
     *
     * @return 时干五行（如：木）
     */
    public String getHourGanWuXing() {
        return BaZiJiChuMap.TIAN_GAN_WU_XING.get(this.hourGan);
    }


    /**
     * 获取年支五行
     *
     * @return 年支五行（如：木）
     */
    public String getYearZhiWuXing() {
        return BaZiJiChuMap.DI_ZHI_WU_XING.get(this.yearZhi);
    }

    /**
     * 获取月支五行
     *
     * @return 月支五行（如：木）
     */
    public String getMonthZhiWuXing() {
        return BaZiJiChuMap.DI_ZHI_WU_XING.get(this.monthZhi);
    }

    /**
     * 获取日支五行
     *
     * @return 日支五行（如：木）
     */
    public String getDayZhiWuXing() {
        return BaZiJiChuMap.DI_ZHI_WU_XING.get(this.dayZhi);
    }

    /**
     * 获取时支五行
     *
     * @return 时支五行（如：木）
     */
    public String getHourZhiWuXing() {
        return BaZiJiChuMap.DI_ZHI_WU_XING.get(this.hourZhi);
    }


    /**
     * 获取年干支五行
     *
     * @return 年干支五行（如：木水）
     */
    public String getYearGanZhiWuXing() {
        return getYearGanWuXing() + getYearZhiWuXing();
    }

    /**
     * 获取月干支五行
     *
     * @return 月干支五行（如：木水）
     */
    public String getMonthGanZhiWuXing() {
        return getMonthGanWuXing() + getMonthZhiWuXing();
    }

    /**
     * 获取日干支五行
     *
     * @return 日干支五行（如：木水）
     */
    public String getDayGanZhiWuXing() {
        return getDayGanWuXing() + getDayZhiWuXing();
    }

    /**
     * 获取时干支五行
     *
     * @return 时干支五行（如：木水）
     */
    public String getHourGanZhiWuXing() {
        return getHourGanWuXing() + getHourZhiWuXing();
    }


    /**
     * 获取年干支纳音
     *
     * @return 年干支纳音（如：天上火）
     */
    public String getYearGanZhiNaYin() {
        return BaZiJiChuMap.NA_YIN.get(this.yearGanZhi);
    }

    /**
     * 获取月干支纳音
     *
     * @return 月干支纳音（如：天上火）
     */
    public String getMonthGanZhiNaYin() {
        return BaZiJiChuMap.NA_YIN.get(this.monthGanZhi);
    }

    /**
     * 获取日干支纳音
     *
     * @return 日干支纳音（如：天上火）
     */
    public String getDayGanZhiNaYin() {
        return BaZiJiChuMap.NA_YIN.get(this.dayGanZhi);
    }

    /**
     * 获取时干支纳音
     *
     * @return 时干支纳音（如：天上火）
     */
    public String getHourGanZhiNaYin() {
        return BaZiJiChuMap.NA_YIN.get(this.hourGanZhi);
    }


    /**
     * 获取年干支空亡
     *
     * @return 年干支空亡（如：子丑）
     */
    public String getYearGanZhiKongWang() {
        return BaZiJiChuMap.KONG_WANG.get(this.yearGanZhi);
    }

    /**
     * 获取月干支空亡
     *
     * @return 月干支空亡（如：子丑）
     */
    public String getMonthGanZhiKongWang() {
        return BaZiJiChuMap.KONG_WANG.get(this.monthGanZhi);
    }

    /**
     * 获取日干支空亡
     *
     * @return 日干支空亡（如：子丑）
     */
    public String getDayGanZhiKongWang() {
        return BaZiJiChuMap.KONG_WANG.get(this.dayGanZhi);
    }

    /**
     * 获取时干支空亡
     *
     * @return 时干支空亡（如：子丑）
     */
    public String getHourGanZhiKongWang() {
        return BaZiJiChuMap.KONG_WANG.get(this.hourGanZhi);
    }


    /**
     * 获取年干支主星
     *
     * @return 年干支主星（如：正财）
     */
    public String getYearGanZhiZhuXing() {
        return BaZiJiChuMap.SHI_SHEN.get(this.yearGanZhi);
    }

    /**
     * 获取月干支主星
     *
     * @return 月干支主星（如：正财）
     */
    public String getMonthGanZhiZhuXing() {
        return BaZiJiChuMap.SHI_SHEN.get(this.monthGanZhi);
    }

    /**
     * 获取日干支主星
     *
     * @return 日干支主星（如：正财）
     */
    public String getDayGanZhiZhuXing() {
        return BaZiJiChuMap.SHI_SHEN.get(this.dayGanZhi);
    }

    /**
     * 获取时干支主星
     *
     * @return 时干支主星（如：正财）
     */
    public String getHourGanZhiZhuXing() {
        return BaZiJiChuMap.SHI_SHEN.get(this.hourGanZhi);
    }


    /**
     * 获取年支藏干
     *
     * @return 年支藏干（如：[乙]）
     */
    public List<String> getYearZhiCangGan() {
        return BaZiJiChuMap.DI_ZHI_CANG_GAN.get(this.yearZhi);
    }

    /**
     * 获取月支藏干
     *
     * @return 月支藏干（如：[乙]）
     */
    public List<String> getMonthZhiCangGan() {
        return BaZiJiChuMap.DI_ZHI_CANG_GAN.get(this.monthZhi);
    }

    /**
     * 获取日支藏干
     *
     * @return 日支藏干（如：[乙]）
     */
    public List<String> getDayZhiCangGan() {
        return BaZiJiChuMap.DI_ZHI_CANG_GAN.get(this.dayZhi);
    }

    /**
     * 获取时支藏干
     *
     * @return 时支藏干（如：[乙]）
     */
    public List<String> getHourZhiCangGan() {
        return BaZiJiChuMap.DI_ZHI_CANG_GAN.get(this.hourZhi);
    }


    /**
     * 获取年支藏干五行
     *
     * @return 年支藏干五行（如：[木]）
     */
    public List<String> getYearZhiCangGanWuXing() {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < getYearZhiCangGan().size(); i++) {
            list.add(BaZiJiChuMap.TIAN_GAN_WU_XING.get(getYearZhiCangGan().get(i)));
        }

        return list;

    }

    /**
     * 获取月支藏干五行
     *
     * @return 月支藏干五行（如：[木]）
     */
    public List<String> getMonthZhiCangGanWuXing() {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < getMonthZhiCangGan().size(); i++) {
            list.add(BaZiJiChuMap.TIAN_GAN_WU_XING.get(getMonthZhiCangGan().get(i)));
        }

        return list;

    }

    /**
     * 获取日支藏干五行
     *
     * @return 日支藏干五行（如：[木]）
     */
    public List<String> getDayZhiCangGanWuXing() {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < getDayZhiCangGan().size(); i++) {
            list.add(BaZiJiChuMap.TIAN_GAN_WU_XING.get(getDayZhiCangGan().get(i)));
        }

        return list;

    }

    /**
     * 获取时支藏干五行
     *
     * @return 时支藏干五行（如：[木]）
     */
    public List<String> getHourZhiCangGanWuXing() {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < getHourZhiCangGan().size(); i++) {
            list.add(BaZiJiChuMap.TIAN_GAN_WU_XING.get(getHourZhiCangGan().get(i)));
        }

        return list;

    }


    /**
     * 获取年干支副星
     *
     * @return 年干支副星（如：[正印]）
     */
    public List<String> getYearGanZhiFuXing() {

        List<String> yearGanZhiFuXing = new ArrayList<>();

        for (String cangGan : getYearZhiCangGan()) {
            yearGanZhiFuXing.add(BaZiJiChuMap.SHI_SHEN.get(this.dayGan + cangGan));
        }

        return yearGanZhiFuXing;

    }

    /**
     * 获取月干支副星
     *
     * @return 月干支副星（如：[正印]）
     */
    public List<String> getMonthGanZhiFuXing() {

        List<String> monthGanZhiFuXing = new ArrayList<>();

        for (String cangGan : getMonthZhiCangGan()) {
            monthGanZhiFuXing.add(BaZiJiChuMap.SHI_SHEN.get(this.dayGan + cangGan));
        }

        return monthGanZhiFuXing;

    }

    /**
     * 获取日干支副星
     *
     * @return 日干支副星（如：[正印]）
     */
    public List<String> getDayGanZhiFuXing() {

        List<String> dayGanZhiFuXing = new ArrayList<>();

        for (String cangGan : getDayZhiCangGan()) {
            dayGanZhiFuXing.add(BaZiJiChuMap.SHI_SHEN.get(this.dayGan + cangGan));
        }

        return dayGanZhiFuXing;

    }

    /**
     * 获取时干支副星
     *
     * @return 时干支副星（如：[正印]）
     */
    public List<String> getHourGanZhiFuXing() {

        List<String> hourGanZhiFuXing = new ArrayList<>();

        for (String cangGan : getHourZhiCangGan()) {
            hourGanZhiFuXing.add(BaZiJiChuMap.SHI_SHEN.get(this.dayGan + cangGan));
        }

        return hourGanZhiFuXing;

    }


    /**
     * 获取年干支自坐
     *
     * @return 年干支自坐（如：长生）
     */
    public String getYearGanZhiZiZuo() {
        return BaZiJiChuMap.SHI_ER_ZHANG_SHENG.get(this.yearGan + this.yearZhi);
    }

    /**
     * 获取月干支自坐
     *
     * @return 月干支自坐（如：长生）
     */
    public String getMonthGanZhiZiZuo() {
        return BaZiJiChuMap.SHI_ER_ZHANG_SHENG.get(this.monthGan + this.monthZhi);
    }

    /**
     * 获取日干支自坐
     *
     * @return 日干支自坐（如：长生）
     */
    public String getDayGanZhiZiZuo() {
        return BaZiJiChuMap.SHI_ER_ZHANG_SHENG.get(this.dayGan + this.dayZhi);
    }

    /**
     * 获取时干支自坐
     *
     * @return 时干支自坐（如：长生）
     */
    public String getHourGanZhiZiZuo() {
        return BaZiJiChuMap.SHI_ER_ZHANG_SHENG.get(this.hourGan + this.hourZhi);
    }


    /**
     * 获取年干支星运
     *
     * @return 年干支星运（如：长生）
     */
    public String getYearGanZhiXingYun() {
        return BaZiJiChuMap.SHI_ER_ZHANG_SHENG.get(this.dayGan + this.yearZhi);
    }

    /**
     * 获取月干支星运
     *
     * @return 月干支星运（如：长生）
     */
    public String getMonthGanZhiXingYun() {
        return BaZiJiChuMap.SHI_ER_ZHANG_SHENG.get(this.dayGan + this.monthZhi);
    }

    /**
     * 获取日干支星运
     *
     * @return 日干支星运（如：长生）
     */
    public String getDayGanZhiXingYun() {
        return BaZiJiChuMap.SHI_ER_ZHANG_SHENG.get(this.dayGan + this.dayZhi);
    }

    /**
     * 获取时干支星运
     *
     * @return 时干支星运（如：长生）
     */
    public String getHourGanZhiXingYun() {
        return BaZiJiChuMap.SHI_ER_ZHANG_SHENG.get(this.dayGan + this.hourZhi);
    }


    /**
     * 获取五行木数量
     *
     * @param isDiZhiCangGan 是否包含地支藏干（true:是。false:否）
     * @return 五行木数量（如：1）
     */
    public int getWuXingMuCount(boolean isDiZhiCangGan) {
        return computeWuXingCount("木", isDiZhiCangGan);
    }

    /**
     * 获取五行火数量
     *
     * @param isDiZhiCangGan 是否包含地支藏干（true:是。false:否）
     * @return 五行火数量（如：1）
     */
    public int getWuXingHuoCount(boolean isDiZhiCangGan) {
        return computeWuXingCount("火", isDiZhiCangGan);
    }

    /**
     * 获取五行土数量
     *
     * @param isDiZhiCangGan 是否包含地支藏干（true:是。false:否）
     * @return 五行土数量（如：1）
     */
    public int getWuXingTuCount(boolean isDiZhiCangGan) {
        return computeWuXingCount("土", isDiZhiCangGan);
    }

    /**
     * 获取五行金数量
     *
     * @param isDiZhiCangGan 是否包含地支藏干（true:是。false:否）
     * @return 五行金数量（如：1）
     */
    public int getWuXingJinCount(boolean isDiZhiCangGan) {
        return computeWuXingCount("金", isDiZhiCangGan);
    }

    /**
     * 获取五行水数量
     *
     * @param isDiZhiCangGan 是否包含地支藏干（true:是。false:否）
     * @return 五行水数量（如：1）
     */
    public int getWuXingShuiCount(boolean isDiZhiCangGan) {
        return computeWuXingCount("水", isDiZhiCangGan);
    }


    /**
     * 获取五行旺衰
     *
     * @return 五行旺衰（如：[木旺, 火相, 水休, 金囚, 土死]）
     */
    public List<String> getWuXingWangShuai() {
        return BaZiJiChuMap.WU_XING_WANG_SHUAI.get(this.monthZhi);
    }

    /**
     * 获取五行缺失
     *
     * @return 五行缺失（如：[木]）
     */
    public List<String> getWuXingQueShi() {

        Map<String, String> tianGanWuXingMap = BaZiJiChuMap.TIAN_GAN_WU_XING; // 天干五行
        Map<String, String> diZhiWuXingMap = BaZiJiChuMap.DI_ZHI_WU_XING; // 地支五行

        Set<String> set = new HashSet<>(
                Arrays.asList(tianGanWuXingMap.get(this.yearGan), tianGanWuXingMap.get(this.monthGan), tianGanWuXingMap.get(this.dayGan), tianGanWuXingMap.get(this.hourGan),
                        diZhiWuXingMap.get(this.yearZhi), diZhiWuXingMap.get(this.monthZhi), diZhiWuXingMap.get(this.dayZhi), diZhiWuXingMap.get(this.hourZhi))
        );

        return CommonUtil.getListUnlike(new ArrayList<>(set), Arrays.asList("木", "火", "土", "金", "水"));

    }


    /**
     * 获取胎元
     *
     * @param isNaYin 是否携带纳音（true:是。false:否）
     * @return 胎元（如：辛未）
     */
    public String getTaiYuan(boolean isNaYin) {

        String taiYuan = this.eightChar.getTaiYuan();
        if (isNaYin) taiYuan = taiYuan + "（" + this.eightChar.getTaiYuanNaYin() + "）";
        return taiYuan;

    }

    /**
     * 获取胎息
     *
     * @param isNaYin 是否携带纳音（true:是。false:否）
     * @return 胎息（如：辛未）
     */
    public String getTaiXi(boolean isNaYin) {

        String taiXi = this.eightChar.getTaiXi();
        if (isNaYin) taiXi = taiXi + "（" + this.eightChar.getTaiXiNaYin() + "）";
        return taiXi;

    }

    /**
     * 获取命宫
     *
     * @param isNaYin 是否携带纳音（true:是。false:否）
     * @return 命宫（如：辛未）
     */
    public String getMingGong(boolean isNaYin) {

        String mingGong = this.eightChar.getMingGong();
        if (isNaYin) mingGong = mingGong + "（" + this.eightChar.getMingGongNaYin() + "）";
        return mingGong;

    }

    /**
     * 获取身宫
     *
     * @param isNaYin 是否携带纳音（true:是。false:否）
     * @return 身宫（如：辛未）
     */
    public String getShenGong(boolean isNaYin) {

        String shenGong = this.eightChar.getShenGong();
        if (isNaYin) shenGong = shenGong + "（" + this.eightChar.getShenGongNaYin() + "）";
        return shenGong;

    }


    /**
     * 获取年干支神煞
     *
     * @return 年干支神煞（如：[太极贵人]）
     */
    public List<String> getYearGanZhiShenSha() {

        initializeShenSha(); // 初始化神煞
        return this.baZiShenShaUtil.getYearGanZhiShenSha();

    }

    /**
     * 获取月干支神煞
     *
     * @return 月干支神煞（如：[太极贵人]）
     */
    public List<String> getMonthGanZhiShenSha() {

        initializeShenSha(); // 初始化神煞
        return this.baZiShenShaUtil.getMonthGanZhiShenSha();

    }

    /**
     * 获取日干支神煞
     *
     * @return 日干支神煞（如：[太极贵人]）
     */
    public List<String> getDayGanZhiShenSha() {

        initializeShenSha(); // 初始化神煞
        return this.baZiShenShaUtil.getDayGanZhiShenSha();

    }

    /**
     * 获取时干支神煞
     *
     * @return 时干支神煞（如：[太极贵人]）
     */
    public List<String> getHourGanZhiShenSha() {

        initializeShenSha(); // 初始化神煞
        return this.baZiShenShaUtil.getHourGanZhiShenSha();

    }


    /**
     * 获取天干留意
     *
     * @return 天干留意（如：[甲己相合]）
     */
    public List<String> getTianGanLiuYi() {
        return BaZiGanZhiLiuYiUtil.tanGanLiuYi(this.baZiGanZhiLiuYiSetting, getYearGan(), getMonthGan(), getDayGan(), getHourGan(), CommonUtil.EMPTY, CommonUtil.EMPTY, CommonUtil.EMPTY, CommonUtil.EMPTY, CommonUtil.EMPTY);
    }

    /**
     * 获取地支留意
     *
     * @return 地支留意（如：[子丑六合]）
     */
    public List<String> getDiZhiLiuYi() {
        return BaZiGanZhiLiuYiUtil.diZhiLiuYi(this.baZiGanZhiLiuYiSetting, getYearZhi(), getMonthZhi(), getDayZhi(), getHourZhi(), CommonUtil.EMPTY, CommonUtil.EMPTY, CommonUtil.EMPTY, CommonUtil.EMPTY, CommonUtil.EMPTY);
    }


    /**
     * 获取上一节
     *
     * @return 上一节（如：大雪）
     */
    public String getPrevJie() {
        return getLunar().getPrevJie(this.baZiJiChuSetting.getJieQiType() == 0).toString();
    }

    /**
     * 获取上一节日期
     *
     * @return 上一节日期（如：2023-12-07 17:32:44）
     */
    public String getPrevJieDateStr() {
        return getLunar().getPrevJie(this.baZiJiChuSetting.getJieQiType() == 0).getSolar().toYmdHms();
    }

    /**
     * 获取距上一节天数
     *
     * @return 距上一节天数（如：1）
     */
    public int getPrevJieDay() {

        Map<String, Long> prevMap = DateUtil.dateInterval(getPrevJieDateStr(), this.solar.toYmdHms()); // 计算上一节气与排盘日期的时间间隔
        return Math.toIntExact(prevMap.get("days"));

    }


    /**
     * 获取下一节
     *
     * @return 下一节（如：小寒）
     */
    public String getNextJie() {
        return getLunar().getNextJie(this.baZiJiChuSetting.getJieQiType() == 0).toString();
    }

    /**
     * 获取下一节日期
     *
     * @return 下一节日期（如：2024-01-06 04:49:08）
     */
    public String getNextJieDateStr() {
        return getLunar().getNextJie(this.baZiJiChuSetting.getJieQiType() == 0).getSolar().toYmdHms();
    }

    /**
     * 获取距下一节天数
     *
     * @return 距下一节天数（如：1）
     */
    public int getNextJieDay() {

        Map<String, Long> nextMap = DateUtil.dateInterval(this.solar.toYmdHms(), getNextJieDateStr()); // 计算排盘日期与下一节气的时间间隔
        return Math.toIntExact(nextMap.get("days"));

    }


    /**
     * 获取出生节气
     *
     * @return 出生节气（如：大雪后24天6小时27分16秒、小寒前5天4小时49分8秒）
     */
    public String getChuShengJieQi() {

        Map<String, Long> prevMap = DateUtil.dateInterval(getPrevJieDateStr(), this.solar.toYmdHms()); // 计算上一节气与排盘日期的时间间隔
        Map<String, Long> nextMap = DateUtil.dateInterval(this.solar.toYmdHms(), getNextJieDateStr()); // 计算排盘日期与下一节气的时间间隔

        long prevDay = prevMap.get("days") > 0 ? prevMap.get("days") : -prevMap.get("days"); // 天
        long prevHours = prevMap.get("hours") > 0 ? prevMap.get("hours") : -prevMap.get("hours"); // 小时
        long prevMinutes = prevMap.get("minutes") > 0 ? prevMap.get("minutes") : -prevMap.get("minutes"); // 分
        long prevSeconds = prevMap.get("seconds") > 0 ? prevMap.get("seconds") : -prevMap.get("seconds"); // 秒

        long nextDay = nextMap.get("days") > 0 ? nextMap.get("days") : -nextMap.get("days"); // 天
        long nextHours = nextMap.get("hours") > 0 ? nextMap.get("hours") : -nextMap.get("hours"); // 小时
        long nextMinutes = nextMap.get("minutes") > 0 ? nextMap.get("minutes") : -nextMap.get("minutes"); // 分
        long nextSeconds = nextMap.get("seconds") > 0 ? nextMap.get("seconds") : -nextMap.get("seconds"); // 秒

        String prevStr = getPrevJie() + "后" + prevDay + "天" + prevHours + "小时" + prevMinutes + "分" + prevSeconds + "秒";
        String nextStr = getNextJie() + "前" + nextDay + "天" + nextHours + "小时" + nextMinutes + "分" + nextSeconds + "秒";

        return prevStr + "、" + nextStr;

    }

    /**
     * 获取人元司令分野
     *
     * @return 人元司令分野（如：癸水值令）
     */
    public String getRenYuanSiLingFenYe() {

        String renYuanSiLingFenYe = "";

        int prevJieDayNumber = (getPrevJieDay() < 0 || getPrevJieDay() > 29) ? 1 : getPrevJieDay();
        if (this.baZiJiChuSetting.getRenYuanType() == 0) {
            renYuanSiLingFenYe = BaZiJiChuMap.ZI_REN_YUAN.get(getMonthZhi()).get(prevJieDayNumber); // 子平真诠法诀
        } else if (this.baZiJiChuSetting.getRenYuanType() == 1) {
            renYuanSiLingFenYe = BaZiJiChuMap.YUAN_REN_YUAN.get(getMonthZhi()).get(prevJieDayNumber); // 渊海子平法诀
        } else if (this.baZiJiChuSetting.getRenYuanType() == 2) {
            renYuanSiLingFenYe = BaZiJiChuMap.XING_REN_YUAN.get(getMonthZhi()).get(prevJieDayNumber); // 星平会海法诀
        } else if (this.baZiJiChuSetting.getRenYuanType() == 3) {
            renYuanSiLingFenYe = BaZiJiChuMap.SAN_REN_YUAN.get(getMonthZhi()).get(prevJieDayNumber); // 三命通会法诀
        } else if (this.baZiJiChuSetting.getRenYuanType() == 4) {
            renYuanSiLingFenYe = BaZiJiChuMap.SHEN_REN_YUAN.get(getMonthZhi()).get(prevJieDayNumber); // 神峰通考法诀
        } else if (this.baZiJiChuSetting.getRenYuanType() == 5) {
            renYuanSiLingFenYe = BaZiJiChuMap.WAN_REN_YUAN.get(getMonthZhi()).get(prevJieDayNumber); // 万育吾之法诀
        }
        renYuanSiLingFenYe = "".equals(renYuanSiLingFenYe) ? "己" : renYuanSiLingFenYe;

        return renYuanSiLingFenYe + BaZiJiChuMap.TIAN_GAN_WU_XING.get(renYuanSiLingFenYe) + "值令";

    }

    /**
     * 获取身体强弱
     *
     * @return 身体强弱（如：身强）
     */
    public String getShenTiQiangRuo() {

        Map<String, String> ganZhiWuXingShengKeMap = BaZiJiChuMap.GAN_ZHI_WU_XING_SHENG_KE; // 天干和干支的五行生克关系（天干+干支为键）

        // 方法一：根据[日元]和[月令]之间的五行生克关系计算
//        return ganZhiWuXingShengKeMap.get(this.dayGan + this.monthZhi);

        // 方法二：根据[日元]和[其他干支]之间的算分进行计算
        int jiaFen = 0; // 加分
        jiaFen += BaZiJiChuUtil.ganZhiJiaFen(ganZhiWuXingShengKeMap, getDayGan(), getYearGan(), 8); // 年干统一为8分
        jiaFen += BaZiJiChuUtil.ganZhiJiaFen(ganZhiWuXingShengKeMap, getDayGan(), getYearZhi(), 4); // 年支统一为4分
        jiaFen += BaZiJiChuUtil.ganZhiJiaFen(ganZhiWuXingShengKeMap, getDayGan(), getMonthGan(), 12); // 月干统一为12分
        jiaFen += BaZiJiChuUtil.ganZhiJiaFen(ganZhiWuXingShengKeMap, getDayGan(), getMonthZhi(), 40); // 月支统一为40分
        jiaFen += BaZiJiChuUtil.ganZhiJiaFen(ganZhiWuXingShengKeMap, getDayGan(), getDayZhi(), 12); // 日支统一为12分
        jiaFen += BaZiJiChuUtil.ganZhiJiaFen(ganZhiWuXingShengKeMap, getDayGan(), getHourGan(), 12); // 时干统一为12分
        jiaFen += BaZiJiChuUtil.ganZhiJiaFen(ganZhiWuXingShengKeMap, getDayGan(), getHourZhi(), 12); // 时支统一为12分

        if (jiaFen == 50) {
            return "平衡"; // 加分等于50：平衡（中庸之道）
        } else if (jiaFen >= 40 && jiaFen <= 60) {
            return "接近平衡"; // 加分>=40且<=60：接近平衡（中庸之道）
        } else if (jiaFen < 40) {
            return "身弱"; // 加分小于40：身弱
        } else {
            return "身强"; // 加分大于60：身强
        }

    }


    /**
     * 获取喜用神
     *
     * @return 喜用神（如：[木]）
     */
    public List<String> getXiYongShen() {

        Map<String, String> tianGanWuXingMap = BaZiJiChuMap.TIAN_GAN_WU_XING; // 天干五行（天干为键）
        Map<String, String> diZhiWuXingMap = BaZiJiChuMap.DI_ZHI_WU_XING; // 地支五行（地支为键）
        Map<String, String> ganZhiWuXingShengKeMap = BaZiJiChuMap.GAN_ZHI_WU_XING_SHENG_KE;// 天干和干支的五行生克关系（天干+干支为键）

        Set<String> set = new HashSet<>();

        // 1、若身强，则寻找：克我、我生、我克的干支
        if ("身强".equals(getShenTiQiangRuo())) {
            if ("身弱".equals(ganZhiWuXingShengKeMap.get(this.dayGan + this.yearGan))) set.add(tianGanWuXingMap.get(this.yearGan)); // 添加年干五行
            if ("身弱".equals(ganZhiWuXingShengKeMap.get(this.dayGan + this.yearZhi))) set.add(diZhiWuXingMap.get(this.yearZhi)); // 添加年支五行
            if ("身弱".equals(ganZhiWuXingShengKeMap.get(this.dayGan + this.monthGan))) set.add(tianGanWuXingMap.get(this.monthGan)); // 添加月干五行
            if ("身弱".equals(ganZhiWuXingShengKeMap.get(this.dayGan + this.monthZhi))) set.add(diZhiWuXingMap.get(this.monthZhi)); // 添加月支五行
            if ("身弱".equals(ganZhiWuXingShengKeMap.get(this.dayGan + this.dayGan))) set.add(tianGanWuXingMap.get(this.dayGan)); // 添加日干五行
            if ("身弱".equals(ganZhiWuXingShengKeMap.get(this.dayGan + this.dayZhi))) set.add(diZhiWuXingMap.get(this.dayZhi)); // 添加日支五行
            if ("身弱".equals(ganZhiWuXingShengKeMap.get(this.dayGan + this.hourGan))) set.add(tianGanWuXingMap.get(this.hourGan)); // 添加时干五行
            if ("身弱".equals(ganZhiWuXingShengKeMap.get(this.dayGan + this.hourZhi))) set.add(diZhiWuXingMap.get(this.hourZhi)); // 添加时支五行
            return new ArrayList<>(set);
        }

        // 2、若身弱，则寻找：同我、生我的干支
        if ("身弱".equals(getShenTiQiangRuo())) {
            if ("身强".equals(ganZhiWuXingShengKeMap.get(this.dayGan + this.yearGan))) set.add(tianGanWuXingMap.get(this.yearGan)); // 添加年干的五行
            if ("身强".equals(ganZhiWuXingShengKeMap.get(this.dayGan + this.yearZhi))) set.add(diZhiWuXingMap.get(this.yearZhi)); // 添加年支的五行
            if ("身强".equals(ganZhiWuXingShengKeMap.get(this.dayGan + this.monthGan))) set.add(tianGanWuXingMap.get(this.monthGan)); // 添加月干的五行
            if ("身强".equals(ganZhiWuXingShengKeMap.get(this.dayGan + this.monthZhi))) set.add(diZhiWuXingMap.get(this.monthZhi)); // 添加月支的五行
            if ("身强".equals(ganZhiWuXingShengKeMap.get(this.dayGan + this.dayGan))) set.add(tianGanWuXingMap.get(this.dayGan)); // 添加日干五行
            if ("身强".equals(ganZhiWuXingShengKeMap.get(this.dayGan + this.dayZhi))) set.add(diZhiWuXingMap.get(this.dayZhi)); // 添加日支的五行
            if ("身强".equals(ganZhiWuXingShengKeMap.get(this.dayGan + this.hourGan))) set.add(tianGanWuXingMap.get(this.hourGan)); // 添加时干的五行
            if ("身强".equals(ganZhiWuXingShengKeMap.get(this.dayGan + this.hourZhi))) set.add(diZhiWuXingMap.get(this.hourZhi)); // 添加时支的五行
            return new ArrayList<>(set);
        }

        // 3、若身体为平衡或接近平衡，则使用具体法获取喜用神
        String season = getJiJie().substring(1, 2);
        int lunarMonth = this.lunar.getMonth(); // 农历月
        if (lunarMonth == 3 || lunarMonth == 6 || lunarMonth == 9 || lunarMonth == 12) season = "四季末";
        return BaZiJiChuMap.XI_YONG_SHEN.get(getDayGan() + season);

    }

    /**
     * 获取喜用神方位
     *
     * @return 喜用神方位（如：[东, 东南]）
     */
    public List<String> getXiYongShenFangWei() {

        List<String> xiYongShenFangWei = new ArrayList<>();

        for (int i = 0; i < getXiYongShen().size(); i++) {
            String xiYongShen = getXiYongShen().get(i);
            List<String> xiYongFangWei = BaZiJiChuMap.WU_XING_FANG_WEI.get(xiYongShen);
            xiYongShenFangWei.addAll(xiYongFangWei);
        }

        return xiYongShenFangWei;

    }

    /**
     * 获取骨重及批注
     *
     * @return 骨重及批注（如：[七两, 此命推来福禄宏，不须愁虑苦劳心。荣华富贵已天定，正笏垂绅拜紫宸。]）
     */
    public List<String> getGuZhong() {

        int yearGuZhong = BaZiJiChuMap.YEAR_GU_ZHONG.get(getYearGanZhi()); // 出生年骨重
        int monthGuZhong = BaZiJiChuMap.MONTH_GU_ZHONG.get(getMonthZhi()); // 出生月骨重
        int dayGuZhong = BaZiJiChuMap.DAY_GU_ZHONG.get(getLunar().getDayInChinese()); // 出生日骨重
        int hourGuZhong = BaZiJiChuMap.HOUR_GU_ZHONG.get(getHourZhi()); // 出生时辰骨重

        int guZhong = yearGuZhong + monthGuZhong + dayGuZhong + hourGuZhong; // 骨重
        guZhong = Math.max(guZhong, 21); // 最轻骨重

        if ("男".equals(getSex())) {
            // 1、男命
            guZhong = Math.min(guZhong, 72); // 男命的最重骨重
            Map<Integer, String> yunChengWan = BaZiJiChuMap.GU_ZHONG_PI_ZHU_WAN; // 骨重批注（男命）
            return Arrays.asList(BaZiJiChuUtil.guZhongCharacters(guZhong), yunChengWan.get(guZhong));
        } else {
            // 2、女命
            guZhong = Math.min(guZhong, 71); // 女命的最重骨重
            Map<Integer, String> yunChengWan = BaZiJiChuMap.GU_ZHONG_PI_ZHU_WOMAN; // 骨重批注（女命）
            return Arrays.asList(BaZiJiChuUtil.guZhongCharacters(guZhong), yunChengWan.get(guZhong));
        }

    }


    /**
     * 获取公历起运日期
     *
     * @return 公历起运日期（如：2032-02-01 00:00:00）
     */
    public String getSolarQiYunDateStr() {
        return this.yun.getStartSolar().toYmdHms();
    }

    /**
     * 获取公历起运信息
     *
     * @return 公历起运信息（如：出生8年1个月后开始起运）
     */
    public String getSolarQiYun() {

        String qiYun = "出生";
        if (this.yun.getStartYear() != 0) qiYun += this.yun.getStartYear() + "年";
        if (this.yun.getStartMonth() != 0) qiYun += this.yun.getStartMonth() + "个月";
        if (this.yun.getStartDay() != 0) qiYun += this.yun.getStartDay() + "天";
        if (this.yun.getStartHour() != 0) qiYun += this.yun.getStartHour() + "小时";
        qiYun += "后开始起运";
        return qiYun;

    }

    /**
     * 获取公历交运信息
     *
     * @return 公历交运信息（如：逢壬、丁年，小寒后25天交运）
     */
    public String getSolarJiaoYun() {

        // 通过起运年份的流年干计算交运干
        String jiaoYunGan = BaZiJiChuMap.JIAO_YUN_GAN.get(this.liuNian.get(this.xiaoYunYearSum).get(2).substring(0, 1));

        // 计算交运农历日期
        Lunar lunar = new Lunar(DateUtil.updateDate(getSolarDate(), this.yun.getStartYear(), this.yun.getStartMonth(), this.yun.getStartDay()));

        // 计算上一节、交运农历日期距上一节的天数
        String prevJie = ""; // 上一节
        int prevJieDay = 0; // 交运农历日期距上一节的天数
        for (int i = 1; i < 60; i++) {
            prevJie = lunar.next(-i).getJie(); // 向前第i天的节，若没有则返回 ""
            if ("".equals(prevJie)) {
                prevJieDay++;
            } else break;
        }

        // 3、设置数据
        return "逢" + jiaoYunGan + "年，" + prevJie + "后" + prevJieDay + "天交运";

    }


    /**
     * 获取星宿
     *
     * @return 星宿（如：毕宿西方白虎）
     */
    public String getXingXiu() {
        return this.lunar.getXiu() + "宿" + this.lunar.getGong() + "方" + this.lunar.getShou();
    }

    /**
     * 获取星宿吉凶
     *
     * @return 星宿吉凶（如：吉）
     */
    public String getXingXiuJiXiong() {
        return this.lunar.getXiuLuck();
    }

    /**
     * 获取星宿吉凶歌诀
     *
     * @return 星宿吉凶歌诀（如：毕星造作主光前，买得田园有余钱，埋葬此日添官职，田蚕大熟永丰年，开门放水多吉庆，合家人口得安然，婚姻若得逢此日，生得孩儿福寿全。）
     */
    public String getXingXiuJiXiongGeJue() {
        return this.lunar.getXiuSong();
    }

    /**
     * 获取彭祖百忌
     *
     * @return 彭祖百忌（如：甲不开仓财物耗散，子不问卜自惹祸殃。）
     */
    public String getPengZuBaiJi() {
        return this.lunar.getPengZuGan() + "，" + this.lunar.getPengZuZhi() + "。";
    }

//-------------------------------------------------------------------------------------------------------------------------------

    /**
     * 计算五行数量
     *
     * @param wuXing         五行
     * @param isDiZhiCangGan 是否包含地支藏干（true:是。false:否）
     * @return 五行数量（如：1）
     */
    private int computeWuXingCount(String wuXing, boolean isDiZhiCangGan) {

        int count = 0;

        if (wuXing.equals(getYearGanWuXing())) count++;
        if (wuXing.equals(getMonthGanWuXing())) count++;
        if (wuXing.equals(getDayGanWuXing())) count++;
        if (wuXing.equals(getHourGanWuXing())) count++;
        if (wuXing.equals(getYearZhiWuXing())) count++;
        if (wuXing.equals(getMonthZhiWuXing())) count++;
        if (wuXing.equals(getDayZhiWuXing())) count++;
        if (wuXing.equals(getHourZhiWuXing())) count++;

        if (isDiZhiCangGan) {
            for (String key : getYearZhiCangGanWuXing()) {
                if (wuXing.equals(key)) count++;
            }
            for (String key : getMonthZhiCangGanWuXing()) {
                if (wuXing.equals(key)) count++;
            }
            for (String key : getDayZhiCangGanWuXing()) {
                if (wuXing.equals(key)) count++;
            }
            for (String key : getHourZhiCangGanWuXing()) {
                if (wuXing.equals(key)) count++;
            }
        }

        return count;

    }

//-------------------------------------------------------------------------------------------------------------------------------

    /**
     * 初始化神煞
     */
    protected void initializeShenSha() {

        if (this.baZiShenShaUtilStatus) return;

        this.baZiShenShaUtil = new BaZiShenShaUtil(
                this.baZiShenShaSetting, this.baZiJiChuSetting.getSex(), getJiJie(), getYearGanZhiNaYin(),
                this.yearGanZhi, this.monthGanZhi, this.dayGanZhi, this.hourGanZhi, CommonUtil.EMPTY,
                CommonUtil.EMPTY, CommonUtil.EMPTY, CommonUtil.EMPTY, CommonUtil.EMPTY
        );

        this.baZiShenShaUtilStatus = true;

    }

    /**
     * 初始化流年
     */
    protected void initializeLiuNian() {

        if (this.liuNianStatus) return;

        // 1、计算大运
        List<List<String>> daYun = new ArrayList<>();
        int daYunLunShu = this.baZiJiChuSetting.getDaYunLunShu() == 0 ? 10 : this.baZiJiChuSetting.getDaYunLunShu();
        DaYun[] daYunSource = this.yun.getDaYun(daYunLunShu);
        for (DaYun dy : daYunSource) {
            daYun.add(new ArrayList<>(Arrays.asList(String.valueOf(dy.getStartYear()), String.valueOf(dy.getStartAge()), dy.getGanZhi())));
        }
        this.xiaoYunYearSum = Integer.parseInt(daYun.get(1).get(1)) - Integer.parseInt(daYun.get(0).get(1)); // 小运持续的年数

        // 2、计算流年
        List<List<String>> liuNian = new ArrayList<>();
        LiuNian[] liuNianSource;
        for (DaYun key : daYunSource) {
            liuNianSource = key.getLiuNian(10);
            for (LiuNian ln : liuNianSource) {
                liuNian.add(new ArrayList<>(Arrays.asList(String.valueOf(ln.getYear()), String.valueOf(ln.getAge()), ln.getGanZhi())));
            }
        }
        this.liuNian = liuNian;

        this.liuNianStatus = true;

    }

    @Override
    public String toString() {

        return "公历:" + getSolarStr() + "   " +
                "农历:" + getLunarStr() + "   " +
                "星期:" + getXingQi() + "   " +
                "季节:" + getJiJie() + "   " +
                "生肖:" + getShengXiao() + "   " +
                "星座:" + getXingZuo() + "   " +
                "月相:" + getYueXiang() + "   " +
                "月将:" + getYueJiang() + "   " +
                "月将神:" + getYueJiangShen() + "   " +
                "五不遇时:" + getWuBuYuShi() + "   " +
                "年干支主星:" + getYearGanZhiZhuXing() + "   " +
                "月干支主星:" + getMonthGanZhiZhuXing() + "   " +
                "日干支主星:" + getDayGanZhiZhuXing() + "   " +
                "时干支主星:" + getHourGanZhiZhuXing() + "   " +
                "年支藏干:" + getYearZhiCangGan() + "   " +
                "月支藏干:" + getMonthZhiCangGan() + "   " +
                "日支藏干:" + getDayZhiCangGan() + "   " +
                "时支藏干:" + getHourZhiCangGan() + "   " +
                "年干支副星:" + getYearGanZhiFuXing() + "   " +
                "月干支副星:" + getMonthGanZhiFuXing() + "   " +
                "日干支副星:" + getDayGanZhiFuXing() + "   " +
                "时干支副星:" + getHourGanZhiFuXing() + "   " +
                "年支自坐:" + getYearGanZhiZiZuo() + "   " +
                "月支自坐:" + getMonthGanZhiZiZuo() + "   " +
                "日支自坐:" + getDayGanZhiZiZuo() + "   " +
                "时支自坐:" + getHourGanZhiZiZuo() + "   " +
                "年支星运:" + getYearGanZhiXingYun() + "   " +
                "月支星运:" + getMonthGanZhiXingYun() + "   " +
                "日支星运:" + getDayGanZhiXingYun() + "   " +
                "时支星运:" + getHourGanZhiXingYun() + "   " +
                "胎元:" + getTaiYuan(true) + "   " +
                "胎息:" + getTaiXi(true) + "   " +
                "命宫:" + getMingGong(true) + "   " +
                "身宫:" + getShenGong(true);

    }


}
