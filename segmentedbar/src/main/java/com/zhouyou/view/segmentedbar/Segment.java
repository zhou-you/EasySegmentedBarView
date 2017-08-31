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
/**
 * <p>描述：分段对象</p>
 * 作者： zhouyou<br>
 * 日期： 2016/12/01 15:40 <br>
 * 版本： v1.0<br>
 */
public class Segment {
    private String customText;                           //自定义显示文本
    private String descriptionText;                      //描述信息
    private String topDescriptionText;                   //分段控件上部文字描述
    private int color;                                   //分段控件颜色
    private float minValue = -1;                         //分段控件的起始值（start）
    private float maxValue = -1;                         //分段控件的结束值(end)

    public Segment(float minValue, float maxValue, String descriptionText, int color) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.descriptionText = descriptionText;
        this.color = color;
    }

    public Segment(String customText, String descriptionText, int color) {
        this.customText = customText;
        this.descriptionText = descriptionText;
        this.color = color;
    }

    public Segment(float minValue, float maxValue, int color) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.color = color;
    }

    public Segment(float minValue, float maxValue, String customText, String descriptionText, int color) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.customText = customText;
        this.descriptionText = descriptionText;
        this.color = color;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public int getColor() {
        return color;
    }

    public float getMinValue() {
        return minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public String getCustomText() {
        return customText;
    }

    public void setCustomText(String customText) {
        this.customText = customText;
    }

    public Segment setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
        return this;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public String getTopDescriptionText() {
        return topDescriptionText;
    }

    public Segment setTopDescriptionText(String topDescriptionText) {
        this.topDescriptionText = topDescriptionText;
        return this;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "descriptionText='" + descriptionText + '\'' +
                ", color=" + color +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                '}';
    }
}
