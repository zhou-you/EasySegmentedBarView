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

import com.zhouyou.view.segmentedbar.Segment;
import com.zhouyou.view.segmentedbar.SegmentedBarView;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        createBarViewWithOilSliderType();
    }

    //设置滑块类型
    private void createBarViewWithOilSliderType() {
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
        barView.setValue(44.6f);
        barView.setSegments(segments);
    }
}
