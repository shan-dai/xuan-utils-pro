<h1 align="center" style="text-shadow:2px 2px 5px rgb(200,200,200);">
    排盘工具 - 升级版
</h1>
<p align="center">
    在线地址:
    <a href="https://muxuange.cn/paipan" target="_blank">
    	https://muxuange.cn/paipan
    </a>
</p>
<p align="center">
    主仓库地址:
    <a href="https://gitee.com/shan-dai/xuan-utils-pro" target="_blank">
    	点击访问
    </a>
</p>
<p align="center" style="font-size:17px; background-color:rgb(245, 245, 245); border-radius:50px;">
    v2.0.1
</p>
<p align="center" style="background-color:rgb(245, 245, 245); color:rgb(170,132,56);">
    运行环境：jdk8
</p>


```
特性：

√ 降低代码耦合度
√ 提高代码可扩展性
√ ...
```


## **八字排盘**

🔈<b>八字排盘</b>提供基础进阶数据，如：八字、主星、地势、空亡、神煞等。

### 代码示例

```java
public class BaZiTest {
    public static void main(String[] args) {
        
        // 1、基础设置（可选）
        BaZiJiChuSetting baZiJiChuSetting = new BaZiJiChuSetting();
        baZiJiChuSetting.setSex(1); // 性别（0:女。1:男）
        // 更多基础设置...

        // 2、神煞设置（可选）
        BaZiShenShaSetting baZiShenShaSetting = new BaZiShenShaSetting();
        baZiShenShaSetting.setTaiJiGuiRen(0); // 太极贵人（0:显示。1:关闭）
        // 更多神煞设置...
        
        // 3、干支留意设置（可选）
        BaZiGanZhiLiuYiSetting baZiGanZhiLiuYiSetting = new BaZiGanZhiLiuYiSetting();
        baZiGanZhiLiuYiSetting.setTianGanXiangSheng(0); // 天干相生（0:显示。1:关闭）
        // 更多干支留意设置...

        // 4、初始化
        BaZi baZi = new BaZi(baZiJiChuSetting); // 使用基础设置初始化
        baZi.baZiShenShaSetting(baZiShenShaSetting); // 神煞设置
        baZi.baZiGanZhiLiuYiSetting(baZiGanZhiLiuYiSetting); // 干支留意设置

        // 5、输出结果
        System.out.println(baZi);
        
    }
}
```

输出结果（部分数据）：

```
公历:2024年01月01日00时00分00秒   农历:二〇二三年冬月二十(早)子时   星期:周一   季节:仲冬   生肖:兔   星座:摩羯座   月相:更待   月将:丑   月将神:大吉   五不遇时:false   年干支主星:食神   月干支主星:正印   日干支主星:正印   时干支主星:正印   年支藏干:[乙]   月支藏干:[癸]   日支藏干:[癸]   时支藏干:[癸]   年干支副星:[劫财]   月干支副星:[正印]   日干支副星:[正印]   时干支副星:[正印]   年支自坐:长生   月支自坐:沐浴   日支自坐:沐浴   时支自坐:沐浴   年支星运:帝旺   月支星运:沐浴   日支星运:沐浴   时支星运:沐浴   胎元:乙卯（大溪水）   胎息:己丑（霹雳火）   命宫:丁巳（沙中土）   身宫:癸丑（桑柘木）
```

### 效果预览

![paipan](https://xuan-utils-pro.oss-cn-qingdao.aliyuncs.com/pic/440701d3-3fcd-455b-95fb-ef301a6f757c.png)

=========================================================

## **六爻排盘**

🔈<b>六爻排盘</b>提供基础进阶数据，如：八字、五行、空亡、世应、六亲等。

### 代码示例

```java
public class LiuYaoTest {
    public static void main(String[] args) {

        // 1、基础设置（可选）
        LiuYaoJiChuSetting liuYaoJiChuSetting = new LiuYaoJiChuSetting();
        baZiJiChuSetting.setSex(1); // 性别（0:女。1:男）
        // 更多基础设置...
        
        // 2、神煞设置（可选）
        LiuYaoShenShaSetting liuYaoShenShaSetting = new LiuYaoShenShaSetting();
        liuYaoShenShaSetting.setTaiJiGuiRen(0); // 太极贵人（0:显示。1:关闭）
        // 更多神煞设置...

        // 3、初始化
        LiuYao liuYao = new LiuYao(liuYaoJiChuSetting); // 使用基础设置初始化
        liuYao.liuYaoShenShaSetting(liuYaoShenShaSetting); // 神煞设置

        // 4、输出结果
        System.out.println(liuYao);
        
    }
}
```

输出结果（部分数据）：

```
公历:2024年01月01日00时00分00秒   农历:二〇二三年冬月二十(早)子时   星期:周一   季节:仲冬   生肖:兔   星座:摩羯座   月相:更待   月将:丑   月将神:大吉   五不遇时:false   上卦:离(☲)   下卦:震(☳)   本卦:火雷噬嗑(䷔)   变卦:震为雷(䷲)   互卦:水山蹇(䷦)   错卦:水风井(䷯)   综卦:山火贲(䷕)   伏卦:巽为风(䷸)
```

### 效果预览

![paipan](https://xuan-utils-pro.oss-cn-qingdao.aliyuncs.com/pic/fcc3ae97-2e46-4907-8ad2-f8705785a77b.png)

=========================================================

## **奇门遁甲排盘**

🔈<b>奇门遁甲排盘</b>提供基础进阶数据，如：地盘、天盘、人盘、神盘、动应、克应等。

### 代码示例（转盘）

```java
public class QiMenTest {
    public static void main(String[] args) {
        
        // 1、基础设置（可选）
        QiMenZhuanPanJiChuSetting qiMenZhuanPanJiChuSetting = new QiMenZhuanPanJiChuSetting();
        qiMenZhuanPanJiChuSetting.setSex(1); // 性别（0:女。1:男）
        // 更多基础设置...

        // 2、初始化
        QiMenZhuanPan qiMen = new QiMenZhuanPan(qiMenZhuanPanJiChuSetting); // 使用基础设置初始化

        // 3、输出结果
        System.out.println(qiMen);
        
    }
}
```

输出结果（部分数据）：

```
公历:2024年01月01日00时00分00秒   农历:二〇二三年冬月二十(早)子时   星期:周一   季节:仲冬   生肖:兔   星座:摩羯座   月相:更待   月将:丑   月将神:大吉   五不遇时:false   符头:甲子   节气:冬至上元   局数:阳遁1局   旬首:甲子   值符:天蓬   值使:休门   天乙:天蓬   地乙:休门   太乙:休门   地盘:[戊, 己, 庚, 辛, 壬, 癸, 丁, 丙, 乙]   天盘:[天蓬, 芮禽, 天冲, 天辅, , 天心, 天柱, 天任, 天英]   人盘:[休门, 死门, 伤门, 杜门, , 开门, 惊门, 生门, 景门]   神盘:[值符, 玄武, 太阴, 六合, , 九天, 九地, 螣蛇, 白虎]   六仪击刑:[己击刑（坤二宫）]   奇仪入墓:[辛入墓（巽四宫）]
```

### 效果预览（转盘）

![paipan](https://xuan-utils-pro.oss-cn-qingdao.aliyuncs.com/pic/7e0304f8-4da3-4023-8b9f-4a9b82ee061f.png)

=========================================================

## **梅花易数排盘**

🔈<b>梅花易数排盘</b>提供基础进阶数据，如：上卦、下卦、本卦、变卦、互卦等。

### 代码示例

```java
public class MeiHuaTest {
    public static void main(String[] args) {
        
        // 1、基础设置（可选）
        MeiHuaJiChuSetting meiHuaJiChuSetting = new MeiHuaJiChuSetting();
        meiHuaJiChuSetting.setSex(1); // 性别（0:女。1:男）
        // 更多基础设置...
        
        // 2、初始化
        MeiHua meiHua = new MeiHua(meiHuaJiChuSetting); // 使用基础设置初始化

        // 3、输出结果
        System.out.println(meiHua);
        
    }
}
```

输出结果（部分数据）：

```
公历:2024年01月01日00时00分00秒   农历:二〇二三年冬月二十(早)子时   星期:周一   季节:仲冬   生肖:兔   星座:摩羯座   月相:更待   月将:丑   月将神:大吉   五不遇时:false   卦码:346   上卦:离(☲)   下卦:震(☳)   动爻:6   本卦:火雷噬嗑(䷔)   变卦:震为雷(䷲)   互卦:水山蹇(䷦)   错卦:水风井(䷯)   综卦:山火贲(䷕)
```

### 效果预览

![paipan](https://xuan-utils-pro.oss-cn-qingdao.aliyuncs.com/pic/8a53175a-50dc-40c4-894c-e1fb85773053.png)

=========================================================

## **紫微斗数排盘**

🔈<b>紫微斗数排盘</b>提供基础进阶数据，如：命宫、身宫、四化、诸星等。

### 代码示例

```java
public class ZiWeiTest {
    public static void main(String[] args) {

        // 1、基础设置（可选）
        ZiWeiJiChuSetting ziWeiJiChuSetting = new ZiWeiJiChuSetting();
        ziWeiJiChuSetting.setSex(1); // 性别（0:女。1:男）
        // 更多基础设置...
        
        // 2、初始化
        ZiWei ziWei = new ZiWei(ziWeiJiChuSetting); // 使用基础设置初始化

        // 3、输出结果
        System.out.println(ziWei);
        
    }
}
```

输出结果（部分数据）：

```
公历:2024年01月01日00时00分00秒   农历:二〇二三年冬月二十(早)子时   星期:周一   季节:仲冬   生肖:兔   星座:摩羯座   月相:更待   月将:丑   月将神:大吉   五不遇时:false   五行局:金四局   化禄星宫位略解:化禄星落财帛宫，善于理财，财源滚滚。   化权星宫位略解:化权星落父母宫，父母强势，有矛盾。   化科星宫位略解:化科星落兄弟宫，兄弟有名声，助力多。   化忌星宫位略解:化忌星落命宫宫，不听劝，易焦虑，年少顺利。
```

### 效果预览

![paipan](https://xuan-utils-pro.oss-cn-qingdao.aliyuncs.com/pic/225964a8-b34f-4a2d-b802-c43adc2d7860.png)

=========================================================

## **更多功能正在完善中...**


<p align="right" style="margin:-11px 0 -11px 0; color:green;">
    ℹ 代码仅供参考
</p>
