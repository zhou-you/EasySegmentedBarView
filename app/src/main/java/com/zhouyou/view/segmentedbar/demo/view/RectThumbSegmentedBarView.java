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

package com.zhouyou.view.segmentedbar.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.zhouyou.view.segmentedbar.SegmentedBarView;

/**
 * <p>描述：自定义分段view</p>
 * 作者： zhouyou<br>
 * 日期： 2017/12/12 20:36 <br>
 * 版本： v1.0<br>
 */
public class RectThumbSegmentedBarView extends SegmentedBarView {
    public RectThumbSegmentedBarView(Context context) {
        super(context);
    }

    public RectThumbSegmentedBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 覆写该方法定义自己想要的标记效果
     * @param canvas  画板
     * @param thumbW  bar条覆盖物标记宽度
     * @param thumbH  bar条覆盖物标记高度
     * @param centerX 当前进度所在的中心点x坐标
     * @param centerY 当前进度所在的中心点Y坐标
     * @param color   当前标记所在位置对应的bar条区间颜色
     * @param paint   画笔paint
     */
    @Override
    protected void drawCustomThumb(Canvas canvas, int thumbW, int thumbH, float centerX, float centerY, int color, Paint paint) {
        //使用paint.setShadowLayer要注意关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        Paint mPaint = paint;
        mPaint.setColor(Color.WHITE);
        int bgShadowColor = Color.argb(80, 0, 0, 0);
        int radius =10;
        mPaint.setShadowLayer(radius, 0, 0, bgShadowColor);//加阴影效果
        canvas.drawRect(centerX-thumbW/2+radius,centerY-thumbH/2+radius,centerX+thumbW/2-radius,centerY+thumbH/2-radius,paint);
        mPaint.clearShadowLayer();//清空掉阴影
        mPaint.setColor(color);
        canvas.drawRect(centerX-thumbW/2+18,centerY-thumbH/2+18,centerX+thumbW/2-18,centerY+thumbH/2-18,paint);
    }
}
