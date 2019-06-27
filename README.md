## EasySegmentedBarView



本库主要提供一个简单易用的自定义分段控件，方便快速实现分段效果，支持xml配置、代码配置、分段规则按均分/比例分、数字分段、文本分段、渐变分段、bar条样式正常/圆形/三角形，segment文字样式、进度设置、进度指示标记类型设置、分段描述设置、分段间距、其它更多自定义设置等功能。



## 功能
- 支持xml配置；
- 支持通过代码配置；
- 支持bar条分段规则按均分/比例分；
- 支持数字分段、文本分段；
- 支持bar条渐变分段；
- 支持设置bar条side样式正常/圆形/三角形；
- 支持segment文字样式；
- 支持进度设置，unit单位显示，进度为数字或指定文本；
- 支持进度指示标记类型设置,上部显示/覆盖叠加显示；
- 支持进度指示三角形是否显示；
- 支持分段间距设置；
- 支持其它更多自定义设置、包括颜色、长宽、间距等；

## 关于我
[![github](https://img.shields.io/badge/GitHub-zhou--you-green.svg)](https://github.com/zhou-you)   [![csdn](https://img.shields.io/badge/CSDN-zhouy478319399-green.svg)](http://blog.csdn.net/zhouy478319399)
## 联系方式
本群旨在为使用我github项目的人提供方便，如果遇到问题欢迎在群里提问。
#### 欢迎加入QQ交流群（Q1群已满，请加入Q2群）

[![](https://img.shields.io/badge/%E7%82%B9%E6%88%91%E4%B8%80%E9%94%AE%E5%8A%A0%E5%85%A5Q1%E7%BE%A4-581235049%28%E5%B7%B2%E6%BB%A1%29-blue.svg)](http://shang.qq.com/wpa/qunwpa?idkey=1e1f4bcfd8775a55e6cf6411f6ff0e7058ff469ef87c4d1e67890c27f0c5a390)

[![](https://img.shields.io/badge/%E7%82%B9%E6%88%91%E4%B8%80%E9%94%AE%E5%8A%A0%E5%85%A5Q2%E7%BE%A4-832887601-blue.svg)](http://shang.qq.com/wpa/qunwpa?idkey=f3c997d1c3cc6a8c9fa46d3fde0d663f50e4e6d0e6441b8cc276bef39befd24c)

![](http://img.blog.csdn.net/20170601165330238)![](https://img-blog.csdnimg.cn/20190627164802234.jpg)
## 演示（请star支持）
![](https://github.com/zhou-you/EasySegmentedBarView/raw/master/screenshot/1.gif) ![](https://github.com/zhou-you/EasySegmentedBarView/raw/master/screenshot/2.gif)
![](http://img.blog.csdn.net/20170901150322496) ![](http://img.blog.csdn.net/20170901150342851) ![](http://img.blog.csdn.net/20170901150354651)
![](http://img.blog.csdn.net/20170901150411893) ![](http://img.blog.csdn.net/20170901150421896) ![](http://img.blog.csdn.net/20170901150433725)

## 版本说明
[![release](https://img.shields.io/badge/release-v1.0.9-orange.svg)](https://github.com/zhou-you/EasySegmentedBarView/blob/master/update.md)

## Demo下载

[![downloads](https://img.shields.io/badge/downloads-460k-blue.svg)](https://github.com/zhouyou/EasySegmentedBarView/blob/master/apk/segmentedbar_demo.apk?raw=true)

## 用法介绍
### build.gradle设置

```
dependencies {
 compile 'com.zhouyou:segmentedbar:1.0.9'
}

```
想查看所有版本，请点击下面地址。

[![jcenter](https://img.shields.io/badge/Jcenter-Latest%20Release-orange.svg)](https://jcenter.bintray.com/com/zhouyou/segmentedbar/)
### 在xml布局中
```
<com.zhouyou.view.segmentedbar.SegmentedBarView
                android:id="@+id/barView"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:paddingBottom="5dp"
                android:paddingTop="5dp"
                app:sbv_empty_segment_text="No segments"
                app:sbv_segment_gap_width="5dp"
                app:sbv_segment_rule="average"
                app:sbv_segment_text_size="15sp"
                app:sbv_show_description_text="true"
                app:sbv_side_style="rounded"
                app:sbv_side_text_style="twoSided"
                app:sbv_value_sign_height="30dp"
                app:sbv_value_sign_round="8dp"
                app:sbv_value_sign_width="100dp"
                />
```
### 具体属性设置
| 属性 | 类型 |默认值 |说明 |
|--------|--------|--------|--------|
|   sbv_side_style|enum|  rounded  |分段条样式normal/rounded/angle,normal:正常样式 rounded:圆角样式  angle:三角样式   |
|   sbv_sliderType|enum| 进度框   | 支持设置：Sign/Slider  Sign:进度框指示，在分段条上部  Slider:滑块指示，覆盖在分段条上  |
|   sbv_sliderImg|reference|  没有默认图片  |   设置sbv_sliderType属性为Slider模式时，需要设置图片，例如：app:sbv_sliderImg="@mipmap/slider" |
|   sbv_segment_gap_width|dimension|  2dp  |每个分段之间的间隙，如果不需要可以设置0dp    |
|   sbv_side_text_style|enum|  oneSided |分段条上文字显示样式oneSided/twoSided  oneSided例如：<50   twoSided 例如：40-60  |
|   sbv_segment_text_size|dimension|  14sp  |分段条上文字颜色   |
|   sbv_bar_height|dimension|  24dp  |  分段条高度  |
|    sbv_show_segment_text    | boolean       |true （显示）     |是否显示分段条上文字       |
|    sbv_show_description_text    | boolean       |false(不显示)       |是否显示分段条底部说明文字       |
|    sbv_show_description_top_text    | boolean       |false(不显示)       |是否显示分段条上部说明文字       |
|    sbv_segment_bg    | boolean       |false(不显示)       |是否显示分段条背景      |
|    sbv_segment_startcolor    | color       |红色       |渐变背景的起始颜色（sbv_segment_bg属性为true才起作用 ）      |
|    sbv_segment_endcolor    | color       |绿色       |渐变背景的结束颜色（sbv_segment_bg属性为true才起作用 ）      |
|    sbv_empty_segment_text    | string       |Empty       |空分段展示的提示文字       |
|   sbv_empty_segment_background|color|  #858585 | 空分段bar条的背景颜色   |
|    sbv_value_sign_background|color| #7492E2   |  进度块背景颜色 |
|   sbv_value_sign_border_color|color|  #7492E2  | 进度块边框颜色   |
|   sbv_value_text_size|dimension| 14sp   |  进度文字大小  |
|   sbv_value_sign_border_size|dimension|  2dp  |分段条上部value背景框边框粗细    |
|   sbv_value_sign_height|dimension| 32dp   | 进度块高度   |
|   sbv_value_sign_width|dimension|  72dp  |  进度块宽度  |
|   sbv_value_sign_round|dimension|  4dp  |进度块圆角大小    |
|   sbv_arrow_height|dimension| 5dp   |进度块上三角指示高度    |
|   sbv_arrow_width|dimension|  10dp  |  进度块上三角指示宽度  |
|    sbv_show_sign_boder    | boolean       |false(不显示)       |是否显示进度块边框       |
|   sbv_description_text_color|color|  Color.DKGRAY  |  描述文字字体颜色  |
|   sbv_description_text_size|dimension| 14sp   | 描述文字字体大小   |
|   sbv_description_box_height|dimension|  24dp |分段条上部描述文字方块高度    |
|   sbv_description_box_top_height|dimension|  24dp  | 分段条底部描述文字方块高度  |
|   sbv_descriptionAlign|enum|Center    |分段条底部描述文字对齐方式支持Center/Both模式，Center:居中显示 ，Both：两端显示   |
|   sbv_descriptionTopAlign|enum| Center   |分段条上部部描述文字对齐方式支持Center/Both模式，Center:居中显示 ，Both：两端显示    |
|   sbv_segment_rule|enum| average |设置分段规则scale/average模式，scale：按比例分段  average：平均分段   | 
### 在代码中

#### 方式一

```
       SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.barView);
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment = new Segment(0, 4.5f, "Low", Color.parseColor("#EF3D2F"));
        segments.add(segment);
        Segment segment2 = new Segment(4.5f, 9.5f, "Optimal", Color.parseColor("#8CC63E"));
        segments.add(segment2);
        Segment segment3 = new Segment(9.5f, 20f, "High", Color.parseColor("#EF3D2F"));
        segments.add(segment3);
        barView.setValueWithUnit(13.96f, "10<sup>12</sup>/l");
        //barView.setSegmentSideRule(SegmentedBarViewSideRule.average);//通过代码设置规则
        //barView.setValue(13.96f);
        //barView.setValue(13.96f,"Optimal");
        barView.setSegments(segments);
```

#### 方式二

```
       SegmentedBarView barView = new SegmentedBarView(this);
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment = new Segment(0, 4.5f, "Low", Color.parseColor("#EF3D2F"));
        segments.add(segment);
        Segment segment2 = new Segment(4.5f, 6.5f, "Optimal", Color.parseColor("#8CC63E"));
        segments.add(segment2);
        Segment segment3 = new Segment(6.5f, 20f, "High", Color.parseColor("#EF3D2F"));
        segments.add(segment3);
        barView.setValue(4.96f);
        barView.setUnit("m");
        barView.setSideTextStyle(SegmentedBarViewSideTextStyle.TWO_SIDED);
        barView.setSideStyle(SegmentedBarViewSideStyle.ROUNDED);
        barView.setSegments(segments);
        barView.setShowDescriptionText(false);
        barView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        barView.setPadding(0, getResources().getDimensionPixelSize(R.dimen.vertical_padding), 0, 0);
        sideStyleLayout.addView(barView);
```

#### 方式三

```
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment = new Segment(0, 4.5f, "Low", Color.parseColor("#EF3D2F"));
        segments.add(segment);
        Segment segment2 = new Segment(4.5f, 6.5f, "Optimal", Color.parseColor("#8CC63E"));
        segments.add(segment2);
        Segment segment3 = new Segment(6.5f, 20f, "High", Color.parseColor("#EF3D2F"));
        segments.add(segment3);
        SegmentedBarView barView = SegmentedBarView.builder(this)
                .segments(segments)
                .value(5.25f)
                .unit("ml<sup>2</sup>")
                .showDescriptionText(true)
                .sideStyle(SegmentedBarViewSideStyle.ANGLE)
                .build();
        barView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        barView.setPadding(0, getResources().getDimensionPixelSize(R.dimen.vertical_padding), 0, 0);
        javaCodeLayout.addView(barView);
```
### 具体方法设置
| 方法 | 说明 |
|--------|--------|
|setBarHeight(int barHeight)|     设置分段条高度   |
|setDescriptionBoxHeight(int descriptionBoxHeight)|    分段条底部部描述文字方块高度    |
|setDescriptionTextColor(int descriptionTextColor)|   分段条底部描述文字颜色     |
|setDescriptionTextSize(int descriptionTextSize)|   分段条底部描述文字字体大小      |
|setDrawSegmentBg(boolean drawSegmentBg)|   设置是否显示分段条背景    |
|setEmptySegmentColor(int emptySegmentColor)|   设置没有分段时分段条颜色     |
|setGapWidth(int gapWidth)|     设置分段之间的间距   |
|setGradientBgSegmentColor(int startColor, int endColor)|   设置分段条背景的渐变色，启始颜色值和结束颜色值     |
|setSegments(List<Segment> segments)|   设置分段集合     |
|setSegmentSideRule(int sideRule)|     设置分段规则scale/average模式，scale：按比例分段  average：平均分段      |
|setSegmentTextColor(int segmentTextColor)|   分段条上文字颜色     |
|setSegmentTextSize(int segmentTextSize)|   分段条上文字字体大小     |
|setShowDescriptionText(boolean showDescriptionText)|    是否显示分段条底部的描述文字    |
|setShowSegmentText(boolean showSegmentText)| 是否显示分段条上的文字       |
|setSideStyle(int sideStyle)| 分段条样式normal/rounded/angle,normal:正常样式 rounded:圆角样式  angle:三角样式 ，例如：setSideStyle(SegmentedBarViewSideStyle.NORMAL)   | 
|setSideTextStyle(int sideTextStyle)|    分段条上文字显示样式`SegmentedBarViewSideTextStyle.TWO_SIDED`和`SegmentedBarViewSideTextStyle.ONE_SIDED `  |
|setUnit(String unit)|   设置单位 ，例如："`ml<sup>2</sup>`"    |
|**setValue(Float value)**|    设置当前进度值  |
|**setValue(float value, String valueText)**|   设置当前进度值，例如（80，“优秀”）,进度框上文字不会显示80，会显示“优秀”，主要用于转换使用 |
|setValueSegment(Integer valueSegment)|   设置文字分段时，进度位置，例如：分3段，从0开始  ValueSegment=1，表示进度块在第二个位置上展示   |
|setValueSegmentText(String valueSegmentText)|  设置分段进度块上的文字描述，配合setValueSegment使用，只针对文字分段没有数字进度，具体看Demo4，非数字分段      |
|setValueSignColor(int valueSignColor)|   进度框背景颜色     |
|setValueSignSize(int width, int height)|    进度框大小设置    |
|setValueTextColor(int valueTextColor)|   进度框上文字字体颜色     |
|setValueTextSize(int valueTextSize)|    进度框上文字字体大小    |
|setValueWithUnit(Float value, String unitHtml)|    设置有单位的进度    |

### Other
其它更多设置和使用方法，请参考Demo

## 感谢
在此感谢[SegmentedBarView-Android](https://github.com/gspd-mobi/SegmentedBarView-Android)作者提供的开源库，对于本库的完成提供了很大的帮助。
