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

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhouyou.view.segmentedbar.Segment;
import com.zhouyou.view.segmentedbar.SegmentedBarView;

import java.util.ArrayList;
import java.util.Random;

public class TestActivity extends AppCompatActivity {
    private SegmentedBarView barView1, barView2, barView3, barView4, barView5, barView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        createBarViewTest();
        createBarViewTest2();
        createBarViewTest3();
        createBarViewTest4();
        createBarViewTest5();
        createBarViewTest6();
        createBarViewTest7();
    }

    //设置滑块类型
    private void createBarViewTest() {
        barView1 = (SegmentedBarView) findViewById(R.id.slider_type_bar_view_thumb);
        int colors[] = new int[]{Color.parseColor("#a8db62"), Color.parseColor("#8bc93a"), Color.parseColor("#72ab2a")};
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment1 = new Segment(0.0f, 15.0f, "", colors[0]).setDescriptionText("00").setTopDescriptionText("缺油");
        segments.add(segment1);
        //中间显示用40%&60%  “&” 分割
        Segment segment2 = new Segment(15.0f, 45.0f, "", colors[1]).setDescriptionText("15.0%&45.0%").setTopDescriptionText("正常");
        segments.add(segment2);
        Segment segment3 = new Segment(45.0f, 100.0f, "", colors[2]).setDescriptionText("100%").setTopDescriptionText("严重");
        segments.add(segment3);
        barView1.setShowDescriptionText(true);
        barView1.setValue(14.6f);
        barView1.setSegments(segments);
    }

    //设置滑块类型
    private void createBarViewTest2() {
        barView2 = (SegmentedBarView) findViewById(R.id.slider_type_bar_view_thumb2);
        int colors[] = new int[]{Color.parseColor("#a8db62"), Color.parseColor("#8bc93a"), Color.parseColor("#72ab2a")};
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment1 = new Segment(0.0f, 1.62f, "", colors[0]).setDescriptionText("").setTopDescriptionText("偏低");
        segments.add(segment1);
        //中间显示用40%&60%  “&” 分割
        Segment segment2 = new Segment(1.62f, 1.98f, "", colors[1]).setDescriptionText("1.62&1.98").setTopDescriptionText("达标");
        segments.add(segment2);
        Segment segment3 = new Segment(1.98f, 9.0f, "", colors[2]).setDescriptionText("").setTopDescriptionText("优");
        segments.add(segment3);
        barView2.setShowDescriptionText(true);
        barView2.setValue(1.6f);
        barView2.setSegments(segments);
    }

    //设置滑块类型 --非数字分段
    private void createBarViewTest3() {
        barView3 = (SegmentedBarView) findViewById(R.id.slider_type_bar_view_thumb3);
        int colors[] = new int[]{Color.parseColor("#fae8d7"), Color.parseColor("#f3d0c0"), Color.parseColor("#deb1a0")
                , Color.parseColor("#cda485"), Color.parseColor("#bc9375"), Color.parseColor("#8a6145")};
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment1 = new Segment("", "亮白", colors[0]).setTopDescriptionText("测试1");
        segments.add(segment1);
        Segment segment2 = new Segment("", "红润", colors[1]).setTopDescriptionText("测试2");
        segments.add(segment2);
        Segment segment3 = new Segment("", "自然", colors[2]).setTopDescriptionText("测试3");
        segments.add(segment3);
        Segment segment4 = new Segment("", "小麦", colors[3]).setTopDescriptionText("测试4");
        segments.add(segment4);
        Segment segment5 = new Segment("", "暗哑", colors[4]).setTopDescriptionText("测试5");
        segments.add(segment5);
        Segment segment6 = new Segment("", "黝黑", colors[5]).setTopDescriptionText("测试6");
        segments.add(segment6);
        barView3.setShowDescriptionText(true);
        barView3.setValueSegment(3);
        barView3.setSegments(segments);
    }


    //设置滑块类型 --非数字分段
    private void createBarViewTest4() {
        barView4 = (SegmentedBarView) findViewById(R.id.slider_type_bar_view_thumb4);
        int colors[] = new int[]{Color.parseColor("#fae8d7"), Color.parseColor("#f3d0c0"), Color.parseColor("#deb1a0")
                , Color.parseColor("#cda485"), Color.parseColor("#bc9375"), Color.parseColor("#8a6145")};
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
        barView4.setShowDescriptionText(true);
        barView4.setValueSegment(4);
        barView4.setSegments(segments);
    }

    //设置滑块类型 --非数字分段
    private void createBarViewTest5() {
        barView5 = (SegmentedBarView) findViewById(R.id.slider_type_bar_view_thumb5);
        int colors[] = new int[]{Color.parseColor("#fae8d7"), Color.parseColor("#f3d0c0"), Color.parseColor("#deb1a0")
                , Color.parseColor("#cda485"), Color.parseColor("#bc9375"), Color.parseColor("#8a6145")};
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
        barView5.setShowDescriptionText(true);
        barView5.setValueSegment(2);
        barView5.setSegments(segments);
    }

    //设置滑块类型 --数字分段
    private void createBarViewTest6() {
        barView6 = (SegmentedBarView) findViewById(R.id.slider_type_bar_view_thumb6);
        int colors[] = new int[]{Color.parseColor("#fae8d7"), Color.parseColor("#f3d0c0"), Color.parseColor("#deb1a0")
                , Color.parseColor("#cda485"), Color.parseColor("#bc9375"), Color.parseColor("#8a6145")};
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment1 = new Segment(0.0f, 20.0f, "亮白", colors[0]);
        segments.add(segment1);
        Segment segment2 = new Segment(20.0f, 40.0f, "红润", colors[1]);
        segments.add(segment2);
        Segment segment3 = new Segment(40.0f, 60.0f, "自然", colors[2]);
        segments.add(segment3);
        Segment segment4 = new Segment(60.0f, 80.0f, "小麦", colors[3]);
        segments.add(segment4);
        Segment segment5 = new Segment(80.0f, 100.0f, "暗哑", colors[4]);
        segments.add(segment5);
        Segment segment6 = new Segment(100.0f, 120.0f, "黝黑", colors[5]);
        segments.add(segment6);
        barView6.setShowDescriptionText(true);
        barView6.setValue(0.0f);
        barView6.setSegments(segments);
    }

    //设置滑块类型 --数字分段
    private void createBarViewTest7() {
        final SegmentedBarView barView = (SegmentedBarView) findViewById(R.id.slider_type_bar_view_thumb7);
        int colors[] = new int[]{Color.parseColor("#FF9800"), Color.TRANSPARENT};
        ArrayList<Segment> segments = new ArrayList<>();
        Segment segment1 = new Segment(0.0f, 20.0f, "", colors[0]).setDescriptionText("不限");
        segments.add(segment1);
        //中间显示用40%&60%  “&” 分割
        Segment segment2 = new Segment(20.0f, 40.0f, "", colors[0]).setDescriptionText("0&100");
        segments.add(segment2);
        Segment segment3 = new Segment(40.0f, 60.0f, "", colors[0]).setDescriptionText("100&200");
        segments.add(segment3);
        Segment segment4 = new Segment(60.0f, 80.0f, "", colors[1]).setDescriptionText("&300+");
        segments.add(segment4);
        Segment segment5 = new Segment(80.0f, 100.0f, "", colors[1]).setDescriptionText("");
        segments.add(segment5);
        barView.setShowDescriptionText(true);
        barView.setValue(60.0f);
        barView.setSegments(segments);
    }

    public void onProgress(View view) {
        barView1.setValue(Float.valueOf(new Random().nextInt(90)));
        barView2.setValue(Float.valueOf(new Random().nextInt(9)));
        barView6.setValue(Float.valueOf(new Random().nextInt(120)));
        progress(barView3);
        progress(barView4);
        progress(barView5);
    }

    private void progress(SegmentedBarView barView) {
        int value = barView.getValueSegment().intValue();
        value++;
        if (value >= 6) {
            value = 0;
        }
        barView.setValueSegment(value);
    }
}
