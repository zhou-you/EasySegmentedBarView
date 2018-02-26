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

package com.zhouyou.view.segmentedbar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.Html;
import android.text.Layout;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;
import java.util.List;

/**
 * <p>描述：分段控件</p>
 * 作者： zhouyou<br>
 * 日期： 2015/12/18 13:40<br>
 * 版本： v1.0<br>
 * <p/>
 * 修改者： zhouyou  日期:2016/12/01 13:40<br>
 * 1.可以支持设置背景、渐变背景<br>
 * 2.可以支持分段设置渐变背景，每段的渐变颜色都可以不一样<br>
 * 3.支持可以设置两种模式的标记 sbv_sliderType  Sign/Cover 具体看代码<br>
 * 4.可以支持设置Slider的图片sbv_sliderImg<br>
 * 5.支持底部描述符文字segment 中间对齐和两端对齐  两端对齐的文字描述符需要用“&”符合分割  sbv_descriptionAlign<br>
 * 6.可以支持设置顶部描述符文字segment 中间对齐和两端对齐  两端对齐的文字描述符需要用“&”符合分割  sbv_descriptionAlign<br>
 */
public class SegmentedBarView extends View {
    private int[] segmentBgColors = new int[]{Color.RED, Color.BLUE};  //设置渐变的背景颜色
    private RectF roundRectangleBounds;
    private Paint fillPaint;
    private Paint signborderPaint;

    private int valueSignHeight;                                 //滑块的高度
    private int valueSignWidth;                                  //滑块的宽度
    private int valueSignColor;                                  //滑块的颜色
    private Float value;                                        //滑块显示的数值               
    private String valueText;                                   //滑块内显示的文本如果设置了valueText，value会自动不起作用
    private Integer valueSegment;                               //表示选中第几个
    private String valueSegmentText;                            //每段分段控件上显示的内容
    private Rect rectBounds;
    private Rect valueSignBounds;
    private int valueSignRound;                                  //滑块的圆角大小
    private float valueSignCenter = -1;
    private int currentBarColor;

    private int emptySegmentColor;                               //没有数据时的颜色
    private int arrowHeight;                                     //滑块箭头的高度
    private int arrowWidth;                                      //滑块箭头的宽度
    private int gapWidth;                                        //分段控件之间的间隔
    private int barHeight;                                       //分段条的高度

    private List<Segment> segments;                             //分段控件集合
    private String unit;
    private Paint emptyPaint;
    private Paint grapPaint;
    private DecimalFormat formatter;
    private String emptySegmentText;                             //分段控件数据为空时显示的内容，List<Segment> segments 为null或者size是0
    private int barRoundingRadius = 0;

    private boolean showSegmentText;                             //是否显示分段控件上的文本
    private int sideStyle = SegmentedBarViewSideStyle.ROUNDED;   //设置分段控件两边的样式
    private int sideTextStyle = SegmentedBarViewSideTextStyle.ONE_SIDED;

    private int segmentTextSize;                                 //分段控件文字字体大小
    private int segmentTextColor = Color.WHITE;                  //分段控件文字字体颜色
    private Paint segmentTextPaint;

    private int valueTextSize;                                   //滑块数值文字字体大小
    private int valueTextColor = Color.WHITE;                    //滑块文字字体颜色，默认白色
    private StaticLayout valueTextLayout;
    private TextPaint valueTextPaint;                            //滑块数值文本

    private int descriptionTextColor = Color.DKGRAY;             //描述文字字体颜色，默认灰色
    private int descriptionHighlightTextColor;                   //描述文字高亮字体颜色，默认：descriptionTextColor
    private int descriptionAlign;                                //bar条目底部描述文本对齐方式
    private int descriptionTopAlign;                             //bar条目顶部描述文本对齐方式
    private int descriptionTextSize;                             //描述文字字体大小
    private int descriptionBoxHeight;                            //底部描述文本所占的高度
    private int descriptionBoxTopHeight;                         //底部描述文本距分段条的距离
    private Paint descriptionTextPaint;
    private static final int ALIGN_CENTER = 0;                   //描述文字对齐方式-居中
    private static final int ALIGN_BOTH = 1;                     //描述文字对齐方式-两边
    private boolean showDescriptionText;                         //是否显示分段控件下方的描述文本
    private boolean showDescriptionTopText;                      //是否显示分段控件顶部的描述文本

    private int sliderType;                                      //滑块的类型：sign:标记  slider:滑块
    private int sliderImage;                                      //COVER类型，覆盖图片
    private static final int SIGN = 0;                           //滑块在分段控件上面-非叠加的展现形式
    private static final int SLIDER = 1;                         //滑块在分段控件上部显示-叠加的展现形式
    private static final int CUSTOM = 2;                         //滑块在分段控件上部显示-叠加的展现形式 采用自定义

    private Path trianglePath;
    private Path triangleboderPath;

    private Point point1;
    private Point point2;
    private Point point3;
    private Rect segmentRect;
    private boolean isDrawSegmentBg = false;
    private int value_sign_border_size;
    private boolean show_sign_boder;
    private int sideRule;                                          //设置分段规则
    private int value_sign_border_color;

    private Bitmap mBitmap;
    private int thembH, thembW;
    private boolean isShowThumb;

    public SegmentedBarView(Context context) {
        super(context);
        init(context, null);
    }

    public SegmentedBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SegmentedBarView, 0, 0);
        try {
            Resources resources = getResources();
            sliderImage = a.getResourceId(R.styleable.SegmentedBarView_sbv_sliderImg, -1);
            sliderType = a.getInt(R.styleable.SegmentedBarView_sbv_sliderType, SIGN);
            descriptionAlign = a.getInt(R.styleable.SegmentedBarView_sbv_descriptionAlign, ALIGN_CENTER);
            descriptionTopAlign = a.getInt(R.styleable.SegmentedBarView_sbv_descriptionTopAlign, ALIGN_CENTER);
            segmentTextSize = a.getDimensionPixelSize(R.styleable.SegmentedBarView_sbv_segment_text_size, resources.getDimensionPixelSize(R.dimen.sbv_segment_text_size));
            valueTextSize = a.getDimensionPixelSize(R.styleable.SegmentedBarView_sbv_value_text_size, resources.getDimensionPixelSize(R.dimen.sbv_value_text_size));
            descriptionTextSize = a.getDimensionPixelSize(R.styleable.SegmentedBarView_sbv_description_text_size, resources.getDimensionPixelSize(R.dimen.sbv_description_text_size));
            barHeight = a.getDimensionPixelSize(R.styleable.SegmentedBarView_sbv_bar_height, resources.getDimensionPixelSize(R.dimen.sbv_bar_height));
            valueSignHeight = a.getDimensionPixelSize(R.styleable.SegmentedBarView_sbv_value_sign_height, resources.getDimensionPixelSize(R.dimen.sbv_value_sign_height));
            valueSignWidth = a.getDimensionPixelSize(R.styleable.SegmentedBarView_sbv_value_sign_width, resources.getDimensionPixelSize(R.dimen.sbv_value_sign_width));
            arrowHeight = a.getDimensionPixelSize(R.styleable.SegmentedBarView_sbv_arrow_height, resources.getDimensionPixelSize(R.dimen.sbv_arrow_height));
            arrowWidth = a.getDimensionPixelSize(R.styleable.SegmentedBarView_sbv_arrow_width, resources.getDimensionPixelSize(R.dimen.sbv_arrow_width));
            gapWidth = a.getDimensionPixelSize(R.styleable.SegmentedBarView_sbv_segment_gap_width, resources.getDimensionPixelSize(R.dimen.sbv_segment_gap_width));
            valueSignRound = a.getDimensionPixelSize(R.styleable.SegmentedBarView_sbv_value_sign_round, resources.getDimensionPixelSize(R.dimen.sbv_value_sign_round));
            descriptionBoxHeight = a.getDimensionPixelSize(R.styleable.SegmentedBarView_sbv_description_box_height, resources.getDimensionPixelSize(R.dimen.sbv_description_box_height));
            descriptionBoxTopHeight = a.getDimensionPixelSize(R.styleable.SegmentedBarView_sbv_description_box_top_height, resources.getDimensionPixelSize(R.dimen.sbv_description_box_height));
            value_sign_border_size = a.getDimensionPixelSize(R.styleable.SegmentedBarView_sbv_value_sign_border_size, resources.getDimensionPixelSize(R.dimen.sbv_value_sign_border_size));
            thembH = a.getDimensionPixelSize(R.styleable.SegmentedBarView_sbv_thumb_h, (int) (barHeight * 1.3f));
            thembW = a.getDimensionPixelSize(R.styleable.SegmentedBarView_sbv_thumb_w, (int) (barHeight * 1.3f));

            showSegmentText = a.getBoolean(R.styleable.SegmentedBarView_sbv_show_segment_text, true);
            showDescriptionText = a.getBoolean(R.styleable.SegmentedBarView_sbv_show_description_text, false);
            showDescriptionTopText = a.getBoolean(R.styleable.SegmentedBarView_sbv_show_description_top_text, false);

            valueSegmentText = a.getString(R.styleable.SegmentedBarView_sbv_value_segment_text);
            if (valueSegmentText == null) {
                valueSegmentText = resources.getString(R.string.sbv_value_segment);
            }
            emptySegmentText = a.getString(R.styleable.SegmentedBarView_sbv_empty_segment_text);
            if (emptySegmentText == null) {
                emptySegmentText = resources.getString(R.string.sbv_empty);
            }
            isDrawSegmentBg = a.getBoolean(R.styleable.SegmentedBarView_sbv_segment_bg, false);
            show_sign_boder = a.getBoolean(R.styleable.SegmentedBarView_sbv_show_sign_boder, false);
            int segment_bg_startcolor = a.getColor(R.styleable.SegmentedBarView_sbv_segment_startcolor, context.getResources().getColor(R.color.sbv_segment_bg_startcolor));
            value_sign_border_color = a.getColor(R.styleable.SegmentedBarView_sbv_value_sign_border_color, context.getResources().getColor(R.color.sbv_value_sign_boder_color));
            descriptionTextColor = a.getColor(R.styleable.SegmentedBarView_sbv_description_text_color, descriptionTextColor);
            descriptionHighlightTextColor = a.getColor(R.styleable.SegmentedBarView_sbv_description_highlight_text_color, -1);
            segmentBgColors[0] = segment_bg_startcolor;
            int segment_end_endtcolor = a.getColor(R.styleable.SegmentedBarView_sbv_segment_endcolor, context.getResources().getColor(R.color.sbv_segment_bg_endcolor));
            segmentBgColors[1] = segment_end_endtcolor;
            valueSignColor = a.getColor(R.styleable.SegmentedBarView_sbv_value_sign_background, context.getResources().getColor(R.color.sbv_value_sign_background));
            emptySegmentColor = a.getColor(R.styleable.SegmentedBarView_sbv_empty_segment_background, context.getResources().getColor(R.color.sbv_empty_segment_background));
            sideStyle = a.getInt(R.styleable.SegmentedBarView_sbv_side_style, SegmentedBarViewSideStyle.ROUNDED);
            sideTextStyle = a.getInt(R.styleable.SegmentedBarView_sbv_side_text_style, SegmentedBarViewSideTextStyle.ONE_SIDED);
            sideRule = a.getInt(R.styleable.SegmentedBarView_sbv_segment_rule, SegmentedBarViewSideRule.AVERAGE);
        } finally {
            a.recycle();
        }

        if (sliderImage != -1 || sliderType == CUSTOM) {
            isShowThumb = true;
        }
        if (isShowThumb) {
            if (sliderType == SLIDER) {
                mBitmap = BitmapFactory.decodeResource(getResources(), sliderImage);
                thembH = mBitmap.getWidth();
                thembW = mBitmap.getHeight();
            }
        }

        formatter = new DecimalFormat("##.####");

        segmentTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        segmentTextPaint.setColor(Color.WHITE);
        segmentTextPaint.setStyle(Paint.Style.FILL);

        valueTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        valueTextPaint.setColor(Color.WHITE);
        valueTextPaint.setStyle(Paint.Style.FILL);
        valueTextPaint.setTextSize(valueTextSize);
        valueTextPaint.setColor(valueTextColor);

        descriptionTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        descriptionTextPaint.setColor(Color.DKGRAY);
        descriptionTextPaint.setStyle(Paint.Style.FILL);

        fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setAntiAlias(true);

        signborderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        signborderPaint.setStyle(Paint.Style.STROKE);
        signborderPaint.setStrokeWidth(value_sign_border_size);
        signborderPaint.setColor(value_sign_border_color);
        signborderPaint.setAntiAlias(true);

        emptyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        emptyPaint.setStyle(Paint.Style.FILL);

        grapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        grapPaint.setStyle(Paint.Style.FILL);

        rectBounds = new Rect();
        roundRectangleBounds = new RectF();
        valueSignBounds = new Rect();
        segmentRect = new Rect();

        trianglePath = new Path();
        trianglePath.setFillType(Path.FillType.EVEN_ODD);

        triangleboderPath = new Path();

        point1 = new Point();
        point2 = new Point();
        point3 = new Point();
        if (sliderType == SLIDER || sliderType == CUSTOM) {
            arrowHeight = 0;
            valueSignHeight = 0;
        }

        if (!showDescriptionTopText) {
            descriptionBoxTopHeight = 0;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        valueSignCenter = -1;
        if (isDrawSegmentBg) {//是否画背景 默认：不画
            drawBgSegment(canvas);
        }
        int segmentsSize = segments == null ? 0 : segments.size();
        if (segmentsSize > 0) {
            Segment lastSegment = segments.get(segments.size() - 1);
            for (int segmentIndex = 0; segmentIndex < segmentsSize; segmentIndex++) {//画分段控件
                Segment segment = segments.get(segmentIndex);
                drawSegment(canvas, segment, segmentIndex, segmentsSize, lastSegment.getMaxValue());
            }
        } else {//显示空数据
            drawEmptySegment(canvas);
        }

        if (!valueIsEmpty()) {//判断是否有数据
            switch (sliderType) {
                case SIGN://设置标记
                    drawValueSign(canvas, valueSignSpaceHeight(), (int) valueSignCenter);
                    break;
                case SLIDER://设置滑块 覆盖图片
                    if (!isShowThumb) throw new RuntimeException("plase add sbv_sliderImg!!!!!");
                    drawSliderImg(canvas, mBitmap, thembW, thembH, valueSignCenter, rectBounds.top - getXtop() + thembH / 2, fillPaint);
                    break;
                case CUSTOM://设置滑块 自定义覆盖
                    drawCustomThumb(canvas, thembW, thembH, valueSignCenter, rectBounds.top - getXtop() + thembH / 2, currentBarColor, fillPaint);
                    break;
            }
        }
    }

    /**
     * 计算滑块为图片或者自定义模式时，两边间距
     */
    private int getXLeft() {
        return ((sliderType == SLIDER || sliderType == CUSTOM) && isShowThumb && valueSegment == null) ? thembW / 2 : 0;
    }

    /**
     * 计算滑块为图片或者自定义模式时，bar条和thumb高度差
     */
    private int getXtop() {
        int xtop = thembH - barHeight > 0 ? (thembH - barHeight) / 2 : 0;
        return ((sliderType == SLIDER || sliderType == CUSTOM) && isShowThumb) ? xtop : 0;
    }

    /**
     * 自定义分段thumb 图片指示---如果想自定义标记，可以继承SegmentedBarView，重写drawSliderImg方法
     *
     * @param canvas  画板
     * @param bitmap  图片对应的bitmap
     * @param thumbW  bar条覆盖物标记宽度(图片的宽度)
     * @param thumbH  bar条覆盖物标记高度(图片的高度)
     * @param centerX 当前进度所在的中心点x坐标
     * @param centerY 当前进度所在的中心点y坐标
     * @param paint   画笔paint
     */
    private void drawSliderImg(Canvas canvas, Bitmap bitmap, int thumbW, int thumbH, float centerX, float centerY, Paint paint) {
        canvas.drawBitmap(bitmap, centerX - thumbW / 2, centerY - thumbH / 2, paint);
    }

    /**
     * 自定义分段thumb指示---如果想自定义标记，可以继承SegmentedBarView，重写drawCustomThumb方法
     *
     * @param canvas  画板
     * @param thumbW  bar条覆盖物标记宽度
     * @param thumbH  bar条覆盖物标记高度
     * @param centerX 当前进度所在的中心点x坐标
     * @param centerY 当前进度所在的中心点Y坐标
     * @param color   当前标记所在位置对应的bar条区间颜色
     * @param paint   画笔paint
     */
    protected void drawCustomThumb(Canvas canvas, int thumbW, int thumbH, float centerX, float centerY, int color, Paint paint) {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        paint.setColor(color);
        int r = Math.max(thumbH, thumbW) / 2;
        paint.setMaskFilter(new BlurMaskFilter(5, BlurMaskFilter.Blur.SOLID));
        canvas.drawCircle(centerX, centerY, r, paint);
        paint.setMaskFilter(null);
    }

    //显示空数据
    private void drawEmptySegment(Canvas canvas) {
        int segmentsSize = 1;
        float singleSegmentWidth = getContentWidth() / segmentsSize;
        rectBounds.set(getPaddingLeft(), valueSignSpaceHeight() + getPaddingTop() + descriptionBoxTopHeight() + getXtop(), (int)
                singleSegmentWidth + getPaddingLeft(), barHeight + valueSignSpaceHeight() + getPaddingTop() +
                descriptionBoxTopHeight() + getXtop());
        emptyPaint.setColor(emptySegmentColor);
        barRoundingRadius = rectBounds.height() / 2;
        if (barRoundingRadius > singleSegmentWidth / 2) {
            sideStyle = SegmentedBarViewSideStyle.NORMAL;
        }

        segmentRect.set(rectBounds);

        switch (sideStyle) {
            case SegmentedBarViewSideStyle.ROUNDED:
                roundRectangleBounds.set(rectBounds.left, rectBounds.top, rectBounds.right, rectBounds.bottom);
                canvas.drawRoundRect(roundRectangleBounds, barRoundingRadius, barRoundingRadius, emptyPaint);
                break;
            case SegmentedBarViewSideStyle.ANGLE:
                rectBounds.set(barRoundingRadius + getPaddingLeft(),
                        valueSignSpaceHeight() + descriptionBoxTopHeight() + getPaddingTop() + getXtop(),
                        getWidth() - getPaddingRight() - barRoundingRadius,
                        barHeight + valueSignSpaceHeight() + descriptionBoxTopHeight() + getPaddingTop() + getXtop());
                canvas.drawRect(
                        rectBounds,
                        emptyPaint
                );
                //Draw left triangle
                point1.set(rectBounds.left - barRoundingRadius, rectBounds.top + barRoundingRadius);
                point2.set(rectBounds.left, rectBounds.top);
                point3.set(rectBounds.left, rectBounds.bottom);

                drawTriangle(canvas, point1, point2, point3, emptyPaint);

                //Draw right triangle
                point1.set(rectBounds.right + barRoundingRadius, rectBounds.top + barRoundingRadius);
                point2.set(rectBounds.right, rectBounds.top);
                point3.set(rectBounds.right, rectBounds.bottom);

                drawTriangle(canvas, point1, point2, point3, emptyPaint);
                break;
            case SegmentedBarViewSideStyle.NORMAL:
                canvas.drawRect(
                        rectBounds,
                        emptyPaint
                );
            default:
                break;
        }

        if (showSegmentText) {
            String textToShow;
            textToShow = emptySegmentText;
            segmentTextPaint.setTextSize(segmentTextSize);
            drawTextCentredInRectWithSides(canvas, segmentTextPaint, textToShow, segmentRect.left, segmentRect.top,
                    segmentRect.right, segmentRect.bottom);
        }
    }

    //是否需要画背景
    private void drawBgSegment(Canvas canvas) {
        int segmentsSize = 1;
        float singleSegmentWidth = getContentWidth() / segmentsSize;
        rectBounds.set(getPaddingLeft() + getXLeft(), valueSignSpaceHeight() + getPaddingTop() + descriptionBoxTopHeight() + getXtop(), (int)
                singleSegmentWidth + getPaddingRight(), barHeight + valueSignSpaceHeight() + descriptionBoxTopHeight() + getPaddingTop());

        LinearGradient lg = new LinearGradient(rectBounds.left, rectBounds.top, rectBounds.right, rectBounds.bottom, segmentBgColors[0], segmentBgColors[1], Shader.TileMode.MIRROR);  //
        emptyPaint.setShader(lg);

        barRoundingRadius = rectBounds.height() / 2;
        if (barRoundingRadius > singleSegmentWidth / 2) {
            sideStyle = SegmentedBarViewSideStyle.NORMAL;
        }

        segmentRect.set(rectBounds);
        switch (sideStyle) {
            case SegmentedBarViewSideStyle.ROUNDED:
                roundRectangleBounds.set(rectBounds.left, rectBounds.top, rectBounds.right, rectBounds.bottom);
                canvas.drawRoundRect(roundRectangleBounds, barRoundingRadius, barRoundingRadius, emptyPaint);
                break;
            case SegmentedBarViewSideStyle.ANGLE:
                rectBounds.set(barRoundingRadius + getPaddingLeft(),
                        valueSignSpaceHeight() + descriptionBoxTopHeight() + getPaddingTop(),
                        getWidth() - getPaddingRight() - barRoundingRadius,
                        barHeight + valueSignSpaceHeight() + descriptionBoxTopHeight() + getPaddingTop());
                canvas.drawRect(rectBounds, emptyPaint
                );
                //Draw left triangle
                point1.set(rectBounds.left - barRoundingRadius, rectBounds.top + barRoundingRadius);
                point2.set(rectBounds.left, rectBounds.top);
                point3.set(rectBounds.left, rectBounds.bottom);

                drawTriangle(canvas, point1, point2, point3, emptyPaint);

                //Draw right triangle
                point1.set(rectBounds.right + barRoundingRadius, rectBounds.top + barRoundingRadius);
                point2.set(rectBounds.right, rectBounds.top);
                point3.set(rectBounds.right, rectBounds.bottom);

                drawTriangle(canvas, point1, point2, point3, emptyPaint);
                break;
            case SegmentedBarViewSideStyle.NORMAL:
                canvas.drawRect(rectBounds, emptyPaint);
            default:
                break;
        }


        if (showSegmentText) {
            String textToShow;
            textToShow = emptySegmentText;
            segmentTextPaint.setTextSize(segmentTextSize);
            drawTextCentredInRectWithSides(canvas, segmentTextPaint, textToShow, segmentRect.left, segmentRect.top,
                    segmentRect.right, segmentRect.bottom);
        }
    }

    private int getContentWidth() {
        return getWidth() - getPaddingLeft() - getPaddingRight() - getXLeft() * 2;
    }

    private int getContentHeight() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

    private void drawSegment(Canvas canvas, Segment segment, int segmentIndex, int segmentsSize, float valueMax) {
        boolean isLeftSegment = segmentIndex == 0;
        boolean isRightSegment = segmentIndex == segmentsSize - 1;
        boolean isLeftAndRight = isLeftSegment && isRightSegment;
        float singleSegmentWidth, segmentLeft, segmentRight;
        boolean isValueCenter = false;
        if (sideRule == SegmentedBarViewSideRule.SCALE) {//按比例分段
            float spacing = segment.getMaxValue() - segment.getMinValue();
            singleSegmentWidth = getContentWidth() * spacing / valueMax;
            float temp = getContentWidth() / valueMax * segment.getMinValue();
            segmentLeft = temp;
            if (!isLeftSegment) {
                segmentLeft += gapWidth;
            }
            segmentRight = getContentWidth() / valueMax * segment.getMaxValue();
            if (!isLeftSegment && isDrawSegmentBg) {
                RectF mRectF = new RectF();
                grapPaint.setColor(Color.WHITE);
                mRectF.set(temp, valueSignSpaceHeight() + getPaddingTop() + descriptionBoxTopHeight() + getXtop(), temp + gapWidth,
                        barHeight + valueSignSpaceHeight() + descriptionBoxTopHeight() + getPaddingTop() + getXtop());
                canvas.drawRect(mRectF, grapPaint);
            }
        } else {//平均分段
            singleSegmentWidth = (getContentWidth() + gapWidth) / segmentsSize - gapWidth;
            segmentLeft = (singleSegmentWidth + gapWidth) * segmentIndex;
            segmentRight = segmentLeft + singleSegmentWidth;
            if (!isLeftSegment && isDrawSegmentBg) {
                RectF mRectF = new RectF();
                grapPaint.setColor(Color.WHITE);
                mRectF.set(segmentLeft, valueSignSpaceHeight() + getPaddingTop() + descriptionBoxTopHeight() + getXtop(), segmentLeft + gapWidth,
                        barHeight + valueSignSpaceHeight() + descriptionBoxTopHeight() + getPaddingTop() + getXtop());
                canvas.drawRect(mRectF, grapPaint);
            }
        }
        // Segment bounds
        rectBounds.set((int) segmentLeft + getPaddingLeft() + getXLeft(), valueSignSpaceHeight() + descriptionBoxTopHeight() +
                getPaddingTop() + getXtop(), (int) segmentRight + getPaddingRight() + getXLeft(), barHeight + valueSignSpaceHeight() +
                descriptionBoxTopHeight() + getPaddingTop() + getXtop());
        // Calculating value sign position
        if (valueSegment != null && valueSegment == segmentIndex) {
            valueSignCenter = segmentLeft + getPaddingLeft() + (singleSegmentWidth / 2) + getXLeft();
            currentBarColor = segment.getColor();
            isValueCenter = true;
        } else if (value != null && (value >= segment.getMinValue() && value < segment.getMaxValue() || (isRightSegment && segment.getMaxValue() == value))) {
            float valueSignCenterPercent = (value - segment.getMinValue()) / (segment.getMaxValue() - segment.getMinValue());
            valueSignCenter = (int) (segmentLeft + getPaddingLeft() + getXLeft() + valueSignCenterPercent * singleSegmentWidth);
            currentBarColor = segment.getColor();
            isValueCenter = true;
        }

        fillPaint.setColor(segment.getColor());
        segmentRect.set(rectBounds);
        // Drawing segment (with specific bounds if left or right)
        if (isLeftSegment || isRightSegment) {
            barRoundingRadius = rectBounds.height() / 2;
            if (barRoundingRadius > singleSegmentWidth / 2) {
                sideStyle = SegmentedBarViewSideStyle.NORMAL;
            }

            switch (sideStyle) {
                case SegmentedBarViewSideStyle.ROUNDED:
                    roundRectangleBounds.set(rectBounds.left, rectBounds.top, rectBounds.right, rectBounds.bottom);
                    canvas.drawRoundRect(roundRectangleBounds, barRoundingRadius, barRoundingRadius, fillPaint);
                    if (!isLeftAndRight) {
                        if (isLeftSegment) {
                            rectBounds.set((int) segmentLeft + barRoundingRadius + getPaddingLeft() + getXLeft(),
                                    valueSignSpaceHeight() + descriptionBoxTopHeight() + getPaddingTop() + getXtop(), (int)
                                            segmentRight + getPaddingRight() + getXLeft(), barHeight + valueSignSpaceHeight() +
                                            descriptionBoxTopHeight() + getPaddingTop() + getXtop());
                            canvas.drawRect(rectBounds, fillPaint
                            );
                        } else {
                            rectBounds.set((int) segmentLeft + getPaddingLeft() + getXLeft(), valueSignSpaceHeight() +
                                    descriptionBoxTopHeight() + getPaddingTop() + getXtop(), (int) segmentRight -
                                    barRoundingRadius + getPaddingRight() + getXLeft(), barHeight + valueSignSpaceHeight() +
                                    descriptionBoxTopHeight() + getPaddingTop() + getXtop());
                            canvas.drawRect(
                                    rectBounds,
                                    fillPaint
                            );
                        }
                    }
                    break;
                case SegmentedBarViewSideStyle.ANGLE:
                    if (isLeftAndRight) {
                        rectBounds.set((int) segmentLeft + barRoundingRadius + getPaddingLeft() + getXLeft(), valueSignSpaceHeight
                                () + descriptionBoxTopHeight() + getPaddingTop() + getXtop(), (int) segmentRight -
                                barRoundingRadius + getPaddingLeft() + getXLeft(), barHeight + valueSignSpaceHeight() +
                                getPaddingTop() + descriptionBoxTopHeight() + getXtop());
                        canvas.drawRect(
                                rectBounds,
                                fillPaint
                        );
                        //Draw left triangle
                        point1.set(rectBounds.left - barRoundingRadius, rectBounds.top + barRoundingRadius);
                        point2.set(rectBounds.left, rectBounds.top);
                        point3.set(rectBounds.left, rectBounds.bottom);

                        drawTriangle(canvas, point1, point2, point3, fillPaint);

                        //Draw right triangle
                        point1.set(rectBounds.right + barRoundingRadius, rectBounds.top + barRoundingRadius);
                        point2.set(rectBounds.right, rectBounds.top);
                        point3.set(rectBounds.right, rectBounds.bottom);

                        drawTriangle(canvas, point1, point2, point3, fillPaint);
                    } else {
                        if (isLeftSegment) {
                            rectBounds.set((int) segmentLeft + barRoundingRadius + getPaddingLeft() + getXLeft(),
                                    valueSignSpaceHeight() + descriptionBoxTopHeight() + getPaddingTop() + getXtop(), (int)
                                            segmentRight + getPaddingLeft() + getXLeft(), barHeight + valueSignSpaceHeight() +
                                            descriptionBoxTopHeight() + getPaddingTop() + getXtop());
                            canvas.drawRect(
                                    rectBounds,
                                    fillPaint
                            );
                            //Draw left triangle
                            point1.set(rectBounds.left - barRoundingRadius, rectBounds.top + barRoundingRadius);
                            point2.set(rectBounds.left, rectBounds.top);
                            point3.set(rectBounds.left, rectBounds.bottom);

                            drawTriangle(canvas, point1, point2, point3, fillPaint);
                        } else {
                            rectBounds.set((int) segmentLeft + getPaddingLeft() + getXLeft(), valueSignSpaceHeight() +
                                    descriptionBoxTopHeight() + getPaddingTop() + getXtop(), (int) segmentRight -
                                    barRoundingRadius + getPaddingLeft() + getXLeft(), barHeight + valueSignSpaceHeight() +
                                    descriptionBoxTopHeight() + getPaddingTop() + getXtop());
                            canvas.drawRect(
                                    rectBounds,
                                    fillPaint
                            );
                            //Draw right triangle
                            point1.set(rectBounds.right + barRoundingRadius, rectBounds.top + barRoundingRadius);
                            point2.set(rectBounds.right, rectBounds.top);
                            point3.set(rectBounds.right, rectBounds.bottom);

                            drawTriangle(canvas, point1, point2, point3, fillPaint);
                        }
                    }
                    break;
                case SegmentedBarViewSideStyle.NORMAL:
                    canvas.drawRect(rectBounds, fillPaint);
                default:
                    break;
            }
        } else {
            canvas.drawRect(rectBounds, fillPaint);
        }

        // Drawing segment text
        if (showSegmentText) {
            String textToShow;
            if (segment.getCustomText() != null) {
                textToShow = segment.getCustomText();
            } else {
                if (isLeftSegment || isRightSegment) {
                    if (isLeftAndRight || sideTextStyle == SegmentedBarViewSideTextStyle.TWO_SIDED) {
                        textToShow = String.format("%s - %s", formatter.format(segment.getMinValue()), formatter
                                .format(segment.getMaxValue()));
                    } else if (isLeftSegment) {
                        textToShow = String.format("<%s", formatter.format(segment.getMaxValue()));
                    } else {
                        textToShow = String.format(">%s", formatter.format(segment.getMinValue()));
                    }
                } else {
                    textToShow = String.format("%s - %s", formatter.format(segment.getMinValue()), formatter.format
                            (segment.getMaxValue()));
                }
            }

            segmentTextPaint.setTextSize(segmentTextSize);
            segmentTextPaint.setColor(segmentTextColor);
            drawTextCentredInRect(canvas, segmentTextPaint, textToShow, segmentRect);
        }

        //Drawing segment description text
        if (showDescriptionText) {
            descriptionTextPaint.setTextSize(descriptionTextSize);
            descriptionTextPaint.setColor(isValueCenter?descriptionHighlightTextColor==-1?descriptionTextColor:descriptionHighlightTextColor:descriptionTextColor);
            switch (descriptionAlign) {
                case ALIGN_CENTER:
                    drawTextCentredInRectWithSides(canvas, descriptionTextPaint, segment.getDescriptionText(),
                            segmentRect.left, segmentRect.bottom, segmentRect.right, segmentRect.bottom +
                                    descriptionBoxHeight);
                    break;
                case ALIGN_BOTH:
                    drawTextLeftRightInRectBothSides(canvas, descriptionTextPaint, segment.getDescriptionText(),
                            segmentRect.left, segmentRect.bottom, segmentRect.right, segmentRect.bottom +
                                    descriptionBoxHeight, isLeftSegment, isRightSegment, segmentIndex);
                    break;
            }
        }

        if (showDescriptionTopText) {
            descriptionTextPaint.setTextSize(descriptionTextSize);
            descriptionTextPaint.setColor(isValueCenter?descriptionHighlightTextColor==-1?descriptionTextColor:descriptionHighlightTextColor:descriptionTextColor);
            switch (descriptionTopAlign) {
                case ALIGN_CENTER:
                    drawTextCentredInRectWithSides(canvas, descriptionTextPaint, segment.getTopDescriptionText(),
                            segmentRect.left, segmentRect.top - descriptionBoxTopHeight(), segmentRect.right,
                            segmentRect.top);
                    break;
                case ALIGN_BOTH:
                    drawTextLeftRightInRectBothSides(canvas, descriptionTextPaint, segment.getTopDescriptionText(),
                            segmentRect.left, segmentRect.top - descriptionBoxTopHeight(), segmentRect.right,
                            segmentRect.top, isLeftSegment, isRightSegment, segmentIndex);
                    break;
            }
        }
    }

    private void drawValueSign(Canvas canvas, int valueSignSpaceHeight, int valueSignCenter) {
        boolean valueNotInSegments = valueSignCenter == -1;
        if (valueNotInSegments) {
            valueSignCenter = getContentWidth() / 2 + getPaddingLeft();
        }
        valueSignBounds.set(valueSignCenter - valueSignWidth / 2,
                getPaddingTop(),
                valueSignCenter + valueSignWidth / 2,
                valueSignHeight - arrowHeight + getPaddingTop());
        fillPaint.setColor(valueSignColor);

        // Move if not fit horizontal
        if (valueSignBounds.left < getPaddingLeft()) {
            int difference = -valueSignBounds.left + getPaddingLeft();
            roundRectangleBounds.set(valueSignBounds.left + difference, valueSignBounds.top, valueSignBounds.right +
                    difference, valueSignBounds.bottom);
        } else if (valueSignBounds.right > getMeasuredWidth() - getPaddingRight()) {
            int difference = valueSignBounds.right - getMeasuredWidth() + getPaddingRight();
            roundRectangleBounds.set(valueSignBounds.left - difference, valueSignBounds.top, valueSignBounds.right -
                    difference, valueSignBounds.bottom);
        } else {
            roundRectangleBounds.set(valueSignBounds.left, valueSignBounds.top, valueSignBounds.right,
                    valueSignBounds.bottom);
        }

        canvas.drawRoundRect(
                roundRectangleBounds,
                valueSignRound,
                valueSignRound,
                fillPaint
        );
        if (show_sign_boder) {
            canvas.drawRoundRect(
                    roundRectangleBounds,
                    valueSignRound,
                    valueSignRound,
                    signborderPaint
            );
        }

        // Draw arrow
        if (!valueNotInSegments) {
            int difference = 0;
            if (valueSignCenter - arrowWidth / 2 < barRoundingRadius + getPaddingLeft()) {
                difference = barRoundingRadius - valueSignCenter + getPaddingLeft();
            } else if (valueSignCenter + arrowWidth / 2 > getMeasuredWidth() - barRoundingRadius - getPaddingRight()) {
                difference = (getMeasuredWidth() - barRoundingRadius) - valueSignCenter - getPaddingRight();
            }

            point1.set(valueSignCenter - arrowWidth / 2 + difference, valueSignSpaceHeight - arrowHeight +
                    getPaddingTop());
            point2.set(valueSignCenter + arrowWidth / 2 + difference, valueSignSpaceHeight - arrowHeight +
                    getPaddingTop());
            point3.set(valueSignCenter + difference, valueSignSpaceHeight + getPaddingTop());

            drawTriangle(canvas, point1, point2, point3, fillPaint);
            if (show_sign_boder) {
                drawTriangleBoder(canvas, point1, point2, point3, signborderPaint);
            }
        }

        // Draw value text
        if (valueTextLayout != null) {
            canvas.translate(roundRectangleBounds.left, roundRectangleBounds.top + roundRectangleBounds.height() / 2
                    - valueTextLayout.getHeight() / 2);
            valueTextLayout.draw(canvas);
        }
    }

    private void drawTriangle(Canvas canvas, Point point1, Point point2, Point point3, Paint paint) {
        trianglePath.reset();
        trianglePath.moveTo(point1.x, point1.y);
        trianglePath.lineTo(point2.x, point2.y);
        trianglePath.lineTo(point3.x, point3.y);
        trianglePath.lineTo(point1.x, point1.y);
        trianglePath.close();

        canvas.drawPath(trianglePath, paint);
    }

    /**
     * 将三角形的一条顶边用颜色给覆盖掉
     */
    private void drawTriangleBoder(Canvas canvas, Point point1, Point point2, Point point3, Paint paint) {
        triangleboderPath.reset();
        triangleboderPath.moveTo(point1.x, point1.y);
        triangleboderPath.lineTo(point2.x, point2.y);
        paint.setColor(fillPaint.getColor());
        paint.setStrokeWidth(value_sign_border_size + 1f);
        canvas.drawPath(triangleboderPath, paint);
        triangleboderPath.reset();
        paint.setStrokeWidth(value_sign_border_size);
        float value = value_sign_border_size / 6;
        triangleboderPath.moveTo(point1.x - value, point1.y - value);
        triangleboderPath.lineTo(point3.x, point3.y);
        triangleboderPath.lineTo(point2.x + value, point2.y - value);
        paint.setColor(value_sign_border_color);
        canvas.drawPath(triangleboderPath, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int minWidth = getPaddingLeft() + getPaddingRight();
        int height = barHeight;
        if (sliderType == CUSTOM || sliderType == SLIDER) {
            height = Math.max(barHeight, thembH);
        }
        int minHeight = height + getPaddingBottom() + getPaddingTop();
        if (!valueIsEmpty()) {
            minHeight += valueSignHeight + arrowHeight;
        }
        if (showDescriptionText) {
            minHeight += descriptionBoxHeight;
        }
        if (showDescriptionTopText) {
            minHeight += descriptionBoxTopHeight;
        }
       /* if(isShowThumb){
            minWidth+=imgW;
        }*/
        int w = resolveSizeAndState(minWidth, widthMeasureSpec, 0);
        int h = resolveSizeAndState(minHeight, heightMeasureSpec, 0);

        setMeasuredDimension(w, h);
    }

    private int valueSignSpaceHeight() {
        if (valueIsEmpty()) return 0;
        return valueSignHeight;
    }

    private int descriptionBoxTopHeight() {
        if (valueIsEmpty()) return 0;
        return descriptionBoxTopHeight;
    }

    private boolean valueIsEmpty() {
        return value == null && valueSegment == null;
    }

    private void drawTextCentredInRect(Canvas canvas, Paint paint, String text, Rect outsideRect) {
        drawTextCentredInRectWithSides(canvas, paint, text, outsideRect.left, outsideRect.top, outsideRect.right,
                outsideRect.bottom);
    }

    /**
     * 自己扩展了可以支持左右的下标
     */
    private void drawTextLeftRightInRectBothSides(Canvas canvas, Paint paint, String text, float left, float top,
                                                  float right, float bottom, boolean isLeftSegment, boolean
                                                          isRightSegment, int index) {
        paint.setTextAlign(Paint.Align.CENTER);
        float textHeight = paint.descent() - paint.ascent();
        float textOffset = (textHeight / 2) - paint.descent();
        float textWidth = paint.measureText(text);
        if (isLeftSegment) {
            canvas.drawText(text, left + textWidth / 2, (top + bottom) / 2 + textOffset, paint);
            return;
        }
        if (isRightSegment) {
            if (segments.size() <= 2) {
                if (text.contains("&")) {//必须是&分割
                    String[] texts = TextUtils.split(text, "&");
                    canvas.drawText(texts[0], left, (top + bottom) / 2 + textOffset, paint);
                    float rightTextWidth = paint.measureText(texts[1]);
                    canvas.drawText(texts[1], right - rightTextWidth / 2, (top + bottom) / 2 + textOffset, paint);
                    return;
                }
            }
            canvas.drawText(text, right - textWidth / 2, (top + bottom) / 2 + textOffset, paint);
            return;
        }
        /*if (index % 2 == 1) {//如果不是在两端  对2求余
            if (text.contains("&")) {//必须是&分割
                String[] texts = TextUtils.split(text, "&");
                canvas.drawText(texts[0], left, (top + bottom) / 2 + textOffset, paint);
                canvas.drawText(texts[1], right, (top + bottom) / 2 + textOffset, paint);
            }
        } else {
            canvas.drawText(text, right - textWidth / 2, (top + bottom) / 2 + textOffset, paint);
        }*/
        if (!isLeftSegment && !isRightSegment) {//如果不是在两端  对2求余
            if (text.contains("&")) {//必须是&分割
                String[] texts = TextUtils.split(text, "&");
                if (index % 2 == 1) {
                    canvas.drawText(texts[0], left, (top + bottom) / 2 + textOffset, paint);
                    canvas.drawText(texts[1], right, (top + bottom) / 2 + textOffset, paint);
                } else {
                    canvas.drawText(texts[1], right, (top + bottom) / 2 + textOffset, paint);
                }
            }
        } else {
            canvas.drawText(text, right - textWidth / 2, (top + bottom) / 2 + textOffset, paint);
        }
    }

    private void drawTextCentredInRectWithSides(Canvas canvas, Paint paint, String text, float left, float top, float
            right, float bottom) {
        paint.setTextAlign(Paint.Align.CENTER);

        float textHeight = paint.descent() - paint.ascent();
        float textOffset = (textHeight / 2) - paint.descent();
        if (TextUtils.isEmpty(text)) return;
        canvas.drawText(text, (left + right) / 2, (top + bottom) / 2 + textOffset, paint);
    }

    private void createValueTextLayout() {
        if (valueIsEmpty()) {
            valueTextLayout = null;
            return;
        }
        String text = value != null ? formatter.format(value) : valueSegmentText;
        if (value != null && unit != null && !unit.isEmpty())
            text += String.format(" <small>%s</small>", unit);
        if (!TextUtils.isEmpty(valueText)) {
            text = valueText;
        }
        Spanned spanned = Html.fromHtml(text);

        valueTextLayout = new StaticLayout(spanned, valueTextPaint, valueSignWidth, Layout.Alignment.ALIGN_CENTER, 1,
                0, false);
    }

    public String getValueSegmentText() {
        return valueSegmentText;
    }

    public void setValueSegmentText(String valueSegmentText) {
        this.valueSegmentText = valueSegmentText;
        createValueTextLayout();
        invalidate();
        requestLayout();
    }


    public void setSegments(List<Segment> segments) {
        this.segments = segments;
        invalidate();
        requestLayout();
    }

    public void setUnit(String unit) {
        this.unit = unit;
        createValueTextLayout();
        invalidate();
        requestLayout();
    }

    public void setValue(Float value) {
        this.value = value;
        createValueTextLayout();
        invalidate();
        requestLayout();
    }

    public void setValueWithUnit(Float value, String unitHtml) {
        this.value = value;
        this.unit = unitHtml;
        if (!valueIsEmpty()) createValueTextLayout();
        invalidate();
        requestLayout();
    }

    public void setGapWidth(int gapWidth) {
        this.gapWidth = gapWidth;
        invalidate();
        requestLayout();
    }

    public void setBarHeight(int barHeight) {
        this.barHeight = barHeight;
        invalidate();
        requestLayout();
    }

    public void setShowDescriptionText(boolean showDescriptionText) {
        this.showDescriptionText = showDescriptionText;
        invalidate();
        requestLayout();
    }

    public void setValueSignSize(int width, int height) {
        this.valueSignWidth = width;
        this.valueSignHeight = height;
        if (!valueIsEmpty()) createValueTextLayout();
        invalidate();
        requestLayout();
    }

    public void setValueSignColor(int valueSignColor) {
        this.valueSignColor = valueSignColor;
        invalidate();
        requestLayout();
    }

    public void setShowSegmentText(boolean showSegmentText) {
        this.showSegmentText = showSegmentText;
        invalidate();
        requestLayout();
    }

    public void setSideStyle(int sideStyle) {
        this.sideStyle = sideStyle;
        invalidate();
        requestLayout();
    }

    public void setEmptySegmentColor(int emptySegmentColor) {
        this.emptySegmentColor = emptySegmentColor;
        invalidate();
        requestLayout();
    }

    /**
     * 是否显示渐变背景
     */
    public void setDrawSegmentBg(boolean drawSegmentBg) {
        this.isDrawSegmentBg = drawSegmentBg;
        invalidate();
        requestLayout();
    }

    /**
     * 设置渐变背景
     *
     * @param startColor 起始颜色
     * @param endColor   //结束颜色
     */
    public void setGradientBgSegmentColor(int startColor, int endColor) {
        this.segmentBgColors[0] = startColor;
        this.segmentBgColors[1] = endColor;
        invalidate();
        requestLayout();
    }

    /**
     * 设置滑块显示的字体样式
     */
    public void setSideTextStyle(int sideTextStyle) {
        this.sideTextStyle = sideTextStyle;
        invalidate();
        requestLayout();
    }

    /**
     * 设置描述的文本字体大小
     */
    public void setDescriptionTextSize(int descriptionTextSize) {
        this.descriptionTextSize = descriptionTextSize;
        invalidate();
        requestLayout();
    }

    /**
     * 设置分段控件上文本字体大小
     */
    public void setSegmentTextSize(int segmentTextSize) {
        this.segmentTextSize = segmentTextSize;
        invalidate();
        requestLayout();
    }

    /**
     * 设置滑块的字体大小
     */
    public void setValueTextSize(int valueTextSize) {
        this.valueTextSize = valueTextSize;
        valueTextPaint.setTextSize(valueTextSize);
        invalidate();
        requestLayout();
    }

    /**
     * 设置描述的文本字体颜色
     */
    public void setDescriptionTextColor(int descriptionTextColor) {
        this.descriptionTextColor = descriptionTextColor;
        invalidate();
        requestLayout();
    }

    /**
     * 设置显示的数值内容
     *
     * @param value     控件的进度
     * @param valueText //控件滑块显示的内容
     */
    public void setValue(float value, String valueText) {
        this.valueText = valueText;
        this.value = value;
        createValueTextLayout();
        invalidate();
        requestLayout();
    }

    /**
     * bar条上显示文字的颜色
     */
    public void setSegmentTextColor(int segmentTextColor) {
        this.segmentTextColor = segmentTextColor;
        invalidate();
        requestLayout();
    }

    /**
     * 设置滑块上显示文字的颜色
     */
    public void setValueTextColor(int valueTextColor) {
        this.valueTextColor = valueTextColor;
        valueTextPaint.setColor(valueTextColor);
        invalidate();
        requestLayout();
    }

    /**
     * 设置显示描述文字TextVIew
     */
    public void setDescriptionBoxHeight(int descriptionBoxHeight) {
        this.descriptionBoxHeight = descriptionBoxHeight;
        invalidate();
        requestLayout();
    }


    public Integer getValueSegment() {
        return valueSegment;
    }

    public void setValueSegment(Integer valueSegment) {
        this.sideRule = SegmentedBarViewSideRule.AVERAGE;//强制等比例分
        this.valueSegment = valueSegment;
        invalidate();
        requestLayout();
    }

    /**
     * 设置分段规则：等比分配，比例分配
     */
    public void setSegmentSideRule(int sideRule) {
        this.sideRule = sideRule;
        invalidate();
        requestLayout();
    }

    /**
     * 获取当前bar条所在位置的颜色
     */
    public int getCurrentBarColor(){
        return currentBarColor;
    }

    public static Builder builder(Context context) {
        return new SegmentedBarView(context).new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder segments(List<Segment> segments) {
            SegmentedBarView.this.segments = segments;
            return this;
        }

        public Builder unit(String unit) {
            SegmentedBarView.this.unit = unit;
            SegmentedBarView.this.createValueTextLayout();
            return this;
        }

        public Builder value(Float value) {
            SegmentedBarView.this.value = value;
            SegmentedBarView.this.createValueTextLayout();
            return this;
        }

        public Builder valueSegment(Integer valueSegment) {
            SegmentedBarView.this.valueSegment = valueSegment;
            SegmentedBarView.this.createValueTextLayout();
            return this;
        }

        public Builder valueSegmentText(String valueSegmentText) {
            SegmentedBarView.this.valueSegmentText = valueSegmentText;
            SegmentedBarView.this.createValueTextLayout();
            return this;
        }

        public Builder gapWidth(int gapWidth) {
            SegmentedBarView.this.gapWidth = gapWidth;
            return this;
        }

        public Builder barHeight(int barHeight) {
            SegmentedBarView.this.barHeight = barHeight;
            return this;
        }

        public Builder showDescriptionText(boolean showDescriptionText) {
            SegmentedBarView.this.showDescriptionText = showDescriptionText;
            return this;
        }

        public Builder valueSignSize(int width, int height) {
            SegmentedBarView.this.valueSignWidth = width;
            SegmentedBarView.this.valueSignHeight = height;
            return this;
        }

        public Builder valueSignColor(int valueSignColor) {
            SegmentedBarView.this.valueSignColor = valueSignColor;
            return this;
        }

        public Builder showSegmentText(boolean showText) {
            SegmentedBarView.this.showSegmentText = showText;
            return this;
        }

        public Builder sideStyle(int sideStyle) {
            SegmentedBarView.this.sideStyle = sideStyle;
            return this;
        }

        public Builder emptySegmentColor(int emptySegmentColor) {
            SegmentedBarView.this.emptySegmentColor = emptySegmentColor;
            return this;
        }

        public Builder sideTextStyle(int sideTextStyle) {
            SegmentedBarView.this.sideTextStyle = sideTextStyle;
            return this;
        }

        public Builder descriptionTextSize(int descriptionTextSize) {
            SegmentedBarView.this.descriptionTextSize = descriptionTextSize;
            return this;
        }

        public Builder segmentTextSize(int segmentTextSize) {
            SegmentedBarView.this.segmentTextSize = segmentTextSize;
            return this;
        }

        public Builder valueTextSize(int valueTextSize) {
            SegmentedBarView.this.valueTextSize = valueTextSize;
            return this;
        }

        public Builder descriptionTextColor(int descriptionTextColor) {
            SegmentedBarView.this.descriptionTextColor = descriptionTextColor;
            return this;
        }

        public Builder segmentTextColor(int segmentTextColor) {
            SegmentedBarView.this.segmentTextColor = segmentTextColor;
            return this;
        }

        public Builder valueTextColor(int valueTextColor) {
            SegmentedBarView.this.valueTextColor = valueTextColor;
            return this;
        }

        public Builder descriptionBoxHeight(int descriptionBoxHeight) {
            SegmentedBarView.this.descriptionBoxHeight = descriptionBoxHeight;
            return this;
        }


        public Builder sideRule(int sideRule) {
            SegmentedBarView.this.sideRule = sideRule;
            return this;
        }

        public SegmentedBarView build() {
            return SegmentedBarView.this;
        }

    }
}
