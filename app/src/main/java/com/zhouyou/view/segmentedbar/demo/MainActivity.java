
/*
 * Copyright (C) 2017 zhouyou(478319399@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhouyou.view.segmentedbar.demo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.zhouyou.view.segmentedbar.Segment;
import com.zhouyou.view.segmentedbar.SegmentedBarView;
import com.zhouyou.view.segmentedbar.SegmentedBarViewSideRule;
import com.zhouyou.view.segmentedbar.SegmentedBarViewSideStyle;
import com.zhouyou.view.segmentedbar.SegmentedBarViewSideTextStyle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LinearLayout javaCodeLayout, sideStyleLayout, segmentTextStyleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            //actionBar.setHomeButtonEnabled(true);
            //actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.openOptionsMenu();
        }
        
        //setTitle("分段组件(SegmentedBarView)");
        javaCodeLayout = (LinearLayout) findViewById(R.id.java_code_layout);
        sideStyleLayout = (LinearLayout) findViewById(R.id.side_style_layout);
        segmentTextStyleLayout = (LinearLayout) findViewById(R.id.segment_text_style_layout);
        createNormalBarViewByXml();
        createNormalBarView();
        createBarViewWithBuilder();
        createBarViewRuleByAvage();
        createBarViewRuleByScal();
        createBarViewWithoutNumericValue();
        createBarViewWithCustomSettings();
        createBarViewWithManySegments();
        createBarViewWithNoSegments();
        createNormalBarViewSideStyleRounded();
        createNormalBarViewSideStyleNormal();
        createNormalBarViewSideStyleAngle();
        createNormalBarViewWithOneSidedSideText();
        createNormalBarViewWithTwoSidedSideText();
        createNormalBarViewWithValueUnit();
        createNormalBarViewWithValueTxt();
        createBarViewWithBgColors();
        createBarViewWithWaterSliderType();
        createBarViewWithOilSliderType();
        createBarViewWithOilSliderType2();
        createBarViewWithOilSliderType3();
        createBarViewWithOilSliderType4();
        createBarViewWithOilSliderType5();
        createBarViewWithOilSliderType6();
        createBarthumb4();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.adout_layout:
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //通过xml配置BarView
    public void createNormalBarViewByXml() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.xml_bar_view);
        final ArrayList<Segment> segments = new ArrayList<>();
        segments.add(new Segment(0, 50, "正常", Color.RED));
        segments.add(new Segment(50, 100, "严重", Color.BLUE));
        barView.setValue(30f);
        barView.setSegments(segments);
    }

    //通过代码添加SegmentedBarView
    private void createNormalBarView() {
        SegmentedBarView barView = new SegmentedBarView(this);
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment = new Segment(0, 4.5f, "Low", Color.parseColor("#EF3D2F"));
        segments.add(segment);
        Segment segment2 = new Segment(4.5f, 10.5f, "Optimal", Color.parseColor("#8CC63E"));
        segments.add(segment2);
        Segment segment3 = new Segment(10.5f, 20f, "High", Color.parseColor("#EF3D2F"));
        segments.add(segment3);
        //barView.setValueWithUnit(13.96f, "10<sup>12</sup>/l");
        barView.setValueWithUnit(20.0f, "10<sup>12</sup>/l");
        barView.setSegments(segments);
        barView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        barView.setPadding(0, getResources().getDimensionPixelSize(R.dimen.vertical_padding), 0, 0);
        javaCodeLayout.addView(barView);
    }

    //通过代码添加（build方式）
    private void createBarViewWithBuilder() {
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
    }

    //分段规则-均分
    public void createBarViewRuleByAvage() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.rule_avage_bar_view);
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
    }

    //分段规则-比例分配
    public void createBarViewRuleByScal() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.rule_scal_bar_view);
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment = new Segment(0, 4.5f, "Low", Color.parseColor("#EF3D2F"));
        segments.add(segment);
        Segment segment2 = new Segment(4.5f, 9.5f, "Optimal", Color.parseColor("#8CC63E"));
        segments.add(segment2);
        Segment segment3 = new Segment(9.5f, 20f, "High", Color.parseColor("#EF3D2F"));
        segments.add(segment3);
        barView.setValueWithUnit(13.96f, "10<sup>12</sup>/l");
        //barView.setPadding(0, getResources().getDimensionPixelSize(R.dimen.vertical_padding), 0, 0);//设置padding
        //barView.setValue(13.96f);
        //barView.setValue(13.96f,"Optimal");
        barView.setSegments(segments);
    }

    //非数字分段 只支持均分 默认均分
    public void createBarViewWithoutNumericValue() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.without_numeric_bar_view);
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment = new Segment("Negative Left", null, Color.parseColor("#EF3D2F"));
        segments.add(segment);
        Segment segment2 = new Segment("Positive", null, Color.parseColor("#8CC63E"));
        segments.add(segment2);
        Segment segment3 = new Segment("Negative Right", null, Color.parseColor("#EF3D2F"));
        segments.add(segment3);
        barView.setValueSegment(1);
        barView.setValueSegmentText("Your result");
        barView.setUnit("ml<sup>2</sup>");
        barView.setShowDescriptionText(false);//不显示描述文字
        barView.setSegments(segments);
    }

    //自定义设置
    private void createBarViewWithCustomSettings() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.custom_bar_view);
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment = new Segment(0, 30f, "Bad", Color.parseColor("#2C3E50"));
        segments.add(segment);
        Segment segment2 = new Segment(30f, 70f, "Ok", Color.parseColor("#5F91A6"));
        segments.add(segment2);
        Segment segment3 = new Segment(70f, 120f, "Good", Color.parseColor("#ED8C2B"));
        segments.add(segment3);
        Segment segment4 = new Segment(120f, 180f, "Perfect", Color.parseColor("#911146"));
        segments.add(segment4);
        barView.setValueSignColor(Color.parseColor("#1A5717"));
        barView.setValue(90f);
        barView.setBarHeight(60);
        barView.setValueSignSize(220, 130);
        barView.setSegments(segments);
        barView.setValueTextSize(60);
        barView.setDescriptionTextSize(30);
        barView.setSegmentTextSize(60);
        barView.setValueTextColor(Color.parseColor("#aaffaa"));
        barView.setSegmentTextColor(Color.parseColor("#999999"));
        barView.setDescriptionTextColor(Color.parseColor("#aa5555"));
        barView.setGapWidth(0);
        barView.setShowDescriptionText(true);
        barView.setSegmentSideRule(SegmentedBarViewSideRule.SCALE);
        barView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        barView.setPadding(0, getResources().getDimensionPixelSize(R.dimen.vertical_padding), 0, 0);
    }

    //众多分段
    private void createBarViewWithManySegments() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.many_bar_view);
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment = new Segment(0, 20f, "Low", Color.parseColor("#EF3D2F"));
        Segment segment2 = new Segment(20f, 50f, "Optimal", Color.parseColor("#8CC63E"));
        Segment segment3 = new Segment(50f, 100f, "High", Color.parseColor("#EF3D2F"));
        segments.add(segment);
        segments.add(segment2);
        segments.add(segment3);
        segments.add(segment2);
        segments.add(segment3);
        segments.add(segment2);
        segments.add(segment3);
        segments.add(segment2);
        segments.add(segment3);
        segments.add(segment2);
        segments.add(segment3);
        segments.add(segment2);
        segments.add(segment3);
        segments.add(segment2);
        segments.add(segment3);
        segments.add(segment2);
        segments.add(segment3);
        barView.setValue(13f);
        barView.setSegments(segments);
    }

    //空分段
    private void createBarViewWithNoSegments() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.empty_bar_view);
        barView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        barView.setPadding(0, getResources().getDimensionPixelSize(R.dimen.vertical_padding), 0, 0);
    }

    //bar条样式-圆角
    private void createNormalBarViewSideStyleRounded() {
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
    }

    //bar条样式-正常
    private void createNormalBarViewSideStyleNormal() {
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
        barView.setSideStyle(SegmentedBarViewSideStyle.NORMAL);
        barView.setSegments(segments);
        barView.setShowDescriptionText(true);
        barView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        barView.setPadding(0, getResources().getDimensionPixelSize(R.dimen.vertical_padding), 0, 0);
        sideStyleLayout.addView(barView);
    }

    //bar条样式-三角
    private void createNormalBarViewSideStyleAngle() {
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
        barView.setSideStyle(SegmentedBarViewSideStyle.ANGLE);
        barView.setSegments(segments);
        barView.setShowDescriptionText(true);
        barView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        barView.setPadding(0, getResources().getDimensionPixelSize(R.dimen.vertical_padding), 0, 0);
        sideStyleLayout.addView(barView);
    }

    //Segment文字样式---ONE_SIDED
    private void createNormalBarViewWithOneSidedSideText() {
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
        barView.setSideTextStyle(SegmentedBarViewSideTextStyle.ONE_SIDED);//可以通过xml配置
        barView.setSegments(segments);
        barView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        barView.setPadding(0, getResources().getDimensionPixelSize(R.dimen.vertical_padding), 0, 0);
        segmentTextStyleLayout.addView(barView);
    }

    //Segment文字样式---TWO_SIDED
    private void createNormalBarViewWithTwoSidedSideText() {
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
        barView.setSideTextStyle(SegmentedBarViewSideTextStyle.TWO_SIDED);//可以通过xml配置
        barView.setSegments(segments);
        barView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        barView.setPadding(0, getResources().getDimensionPixelSize(R.dimen.vertical_padding), 0, 0);
        segmentTextStyleLayout.addView(barView);
    }

    // //设置显示进度-数字、单位
    private void createNormalBarViewWithValueUnit() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.unit_bar_view);
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment = new Segment(0, 4.5f, "Low", Color.parseColor("#EF3D2F"));
        segments.add(segment);
        Segment segment2 = new Segment(4.5f, 6.5f, "Some text", "Optimal", Color.parseColor("#8CC63E"));
        segments.add(segment2);
        Segment segment3 = new Segment(6.5f, 20f, "High", Color.parseColor("#EF3D2F"));
        segments.add(segment3);
        barView.setValue(4.96f);
        barView.setUnit("m<sup>5</sup>/s<sup>2</sup>");//setUnit("μmol/l");setUnit("μ/l") setUnit("m")
        barView.setSegments(segments);
        barView.setShowDescriptionText(true);
        barView.setSideStyle(SegmentedBarViewSideStyle.ANGLE);
    }

    //设置显示进度-中文
    private void createNormalBarViewWithValueTxt() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.value_txt_bar_view);
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment = new Segment(0, 4.5f, "Low", Color.parseColor("#EF3D2F"));
        segments.add(segment);
        Segment segment2 = new Segment(4.5f, 6.5f, "Some text", "Optimal", Color.parseColor("#8CC63E"));
        segments.add(segment2);
        Segment segment3 = new Segment(6.5f, 20f, "High", Color.parseColor("#EF3D2F"));
        segments.add(segment3);
        barView.setValue(4.96f, "正常");//显示的时候用“正常代替” 4.96f
        barView.setSegments(segments);
        barView.setShowDescriptionText(true);
        barView.setSideStyle(SegmentedBarViewSideStyle.ANGLE);
    }

    //设置渐变背景
    private void createBarViewWithBgColors() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.bg_colors_bar_view);
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment = new Segment(0, 4.5f, "一般", Color.TRANSPARENT);
        segments.add(segment);
        Segment segment2 = new Segment(4.5f, 6.5f, "正常", Color.TRANSPARENT);
        segments.add(segment2);
        Segment segment3 = new Segment(6.5f, 20f, "槽糕", Color.TRANSPARENT);
        segments.add(segment3);
        barView.setValue(4.96f);//显示的时候用“正常代替” 4.96f
        barView.setSegments(segments);
        //barView.setGradientBgSegmentColor(Color.RED,Color.GREEN);//通过代码设置渐变
        barView.setShowDescriptionText(true);
    }

    //设置滑块类型
    private void createBarViewWithWaterSliderType() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.slider_type_bar_view_water);
        int colors[] = new int[]{Color.parseColor("#00e4ff"), Color.parseColor("#00aeff"), Color.parseColor("#0172ff")};
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment1 = new Segment(0.0f, 40.0f, "", colors[0]).setDescriptionText("00").setTopDescriptionText("缺水");
        segments.add(segment1);
        //中间显示用40%&60%  “&” 分割
        Segment segment2 = new Segment(40.0f, 60.0f, "", colors[1]).setDescriptionText("40%&60%").setTopDescriptionText("正常");
        segments.add(segment2);
        Segment segment3 = new Segment(60.0f, 100.0f, "", colors[2]).setDescriptionText("99.9%").setTopDescriptionText("湿润");
        segments.add(segment3);
        barView.setShowDescriptionText(true);
        barView.setValue(30.0f);
        barView.setSegments(segments);
    }

    //设置滑块类型
    private void createBarViewWithOilSliderType() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.slider_type_bar_view_oil);
        int colors[] = new int[]{Color.parseColor("#a8db62"), Color.parseColor("#8bc93a"), Color.parseColor("#72ab2a")};
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment1 = new Segment(0.0f, 40.0f, "", colors[0]).setDescriptionText("00").setTopDescriptionText("缺油");
        segments.add(segment1);
        //中间显示用40%&60%  “&” 分割
        Segment segment2 = new Segment(40.0f, 60.0f, "", colors[1]).setDescriptionText("40%&60%").setTopDescriptionText("正常");
        segments.add(segment2);
        Segment segment3 = new Segment(60.0f, 100.0f, "", colors[2]).setDescriptionText("99.9%").setTopDescriptionText("偏油");
        segments.add(segment3);
        barView.setShowDescriptionText(true);
        barView.setValue(80.0f);
        barView.setSegments(segments);
    }

    private void createBarViewWithOilSliderType2() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.slider_type_bar_view_oil2);
        int colors[] = new int[]{Color.parseColor("#a8db62"), Color.parseColor("#8bc93a"), Color.parseColor("#72ab2a"), Color.YELLOW};
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment1 = new Segment(0.0f, 30.0f, "", colors[0]).setDescriptionText("").setTopDescriptionText("缺油");
        segments.add(segment1);
        //中间显示用40%&60%  “&” 分割
        Segment segment2 = new Segment(30.0f, 50.0f, "", colors[1]).setDescriptionText("52.1%&63.2%").setTopDescriptionText("正常");
        segments.add(segment2);
        Segment segment3 = new Segment(60.0f, 80.0f, "", colors[2]).setDescriptionText("&80.3%").setTopDescriptionText("偏油");
        segments.add(segment3);
        Segment segment4 = new Segment(80.0f, 100.0f, "", colors[3]).setDescriptionText("").setTopDescriptionText("严重");
        segments.add(segment4);
        barView.setShowDescriptionText(true);
        barView.setValue(70.0f);
        barView.setSegments(segments);
    }

    private void createBarViewWithOilSliderType3() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.slider_type_bar_view_oil3);
        int colors[] = new int[]{Color.parseColor("#a8db62"), Color.parseColor("#8bc93a"), Color.parseColor("#72ab2a"), Color.YELLOW, Color.RED};
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment1 = new Segment(0.0f, 20.0f, "", colors[0]).setDescriptionText("").setTopDescriptionText("缺油");
        segments.add(segment1);
        //中间显示用40%&60%  “&” 分割
        Segment segment2 = new Segment(20.0f, 45.0f, "", colors[1]).setDescriptionText("52.1%&63.2%").setTopDescriptionText("正常");
        segments.add(segment2);
        Segment segment3 = new Segment(45.0f, 60.0f, "", colors[2]).setDescriptionText("&70.3%").setTopDescriptionText("偏油");
        segments.add(segment3);
        Segment segment4 = new Segment(60.0f, 80.0f, "", colors[3]).setDescriptionText("&80.2%").setTopDescriptionText("严重");
        segments.add(segment4);
        Segment segment5 = new Segment(80.0f, 100.0f, "", colors[4]).setDescriptionText("").setTopDescriptionText("糟糕");
        segments.add(segment5);
        barView.setShowDescriptionText(true);
        barView.setValue(55.0f);
        barView.setSegments(segments);
    }

    //设置滑块类型
    private void createBarViewWithOilSliderType4() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.slider_type_bar_view_oil4);
        int colors[] = new int[]{Color.parseColor("#a8db62"), Color.parseColor("#8bc93a")};
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment1 = new Segment(0.0f, 40.0f, "", colors[0]).setDescriptionText("").setTopDescriptionText("缺油");
        segments.add(segment1);
        //中间显示用40%&60%  “&” 分割
        Segment segment2 = new Segment(40.0f, 100.0f, "", colors[1]).setDescriptionText("40%&").setTopDescriptionText("正常");
        segments.add(segment2);
        barView.setShowDescriptionText(true);
        barView.setValue(60.0f);
        barView.setSegments(segments);
    }

    //设置滑块类型
    private void createBarViewWithOilSliderType5() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.slider_type_bar_view_oil5);
        int colors[] = new int[]{Color.parseColor("#a8db62"), Color.parseColor("#8bc93a")};
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment1 = new Segment(0.0f, 40.0f, "", colors[0]).setDescriptionText("00").setTopDescriptionText("缺油");
        segments.add(segment1);
        //中间显示用40%&60%  “&” 分割
        Segment segment2 = new Segment(40.0f, 100.0f, "", colors[1]).setDescriptionText("40%&100%").setTopDescriptionText("正常");
        segments.add(segment2);
        barView.setShowDescriptionText(true);
        barView.setValue(60.0f);
        barView.setSegments(segments);
    }
    //设置滑块类型
    private void createBarViewWithOilSliderType6() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.slider_type_bar_view_thumb);
        int colors[] = new int[]{Color.parseColor("#a8db62"), Color.parseColor("#8bc93a"),Color.parseColor("#72ab2a")};
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment1 = new Segment(0.0f, 15.0f, "", colors[0]).setDescriptionText("00").setTopDescriptionText("缺油");
        segments.add(segment1);
        //中间显示用40%&60%  “&” 分割
        Segment segment2 = new Segment(15.0f, 45.0f, "", colors[1]).setDescriptionText("15.0%&45.0%").setTopDescriptionText("正常");
        segments.add(segment2);
        Segment segment3 = new Segment(45.0f, 100.0f, "", colors[2]).setDescriptionText("100%").setTopDescriptionText("严重");
        segments.add(segment3);
        barView.setShowDescriptionText(true);
        barView.setValue(14.5f);
        barView.setSegments(segments);
    }

    //设置滑块类型 --非数字分段
    private void createBarthumb4() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.slider_type_bar_view_thumb4);
        int colors[] = new int[]{Color.parseColor("#fae8d7"), Color.parseColor("#f3d0c0"),Color.parseColor("#deb1a0")
                ,Color.parseColor("#cda485"),Color.parseColor("#bc9375"),Color.parseColor("#8a6145")};
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment1 = new Segment("", "亮白", colors[0]);
        segments.add(segment1);
        Segment segment2 = new Segment("", "红润", colors[1]);
        segments.add(segment2);
        Segment segment3 = new Segment("", "自然", colors[2]);
        segments.add(segment3);
        Segment segment4 = new Segment("", "小麦", colors[3]);
        segments.add(segment4);
        Segment segment5 = new Segment("", "暗哑", colors[4]);
        segments.add(segment5);
        Segment segment6 = new Segment("", "黝黑", colors[5]);
        segments.add(segment6);
        barView.setShowDescriptionText(true);
        barView.setValueSegment(4);
        barView.setSegments(segments);
    }
    
    public void onMore(View view){
        Intent intent = new Intent(MainActivity.this, TestActivity.class);
        startActivity(intent);
    }

}
