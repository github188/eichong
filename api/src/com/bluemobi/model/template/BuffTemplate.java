package com.bluemobi.model.template;

import com.bluemobi.model.GameObject;

public class BuffTemplate extends GameObject {
    //编号    
    private int buff_no;
    //描述
    private String buff_des;
    //buff动画编号          
    private int animation;
    //属性类型  0 特殊  1 射击速度  2 速度  3 实弹伤害减免  4 实弹攻击增加  5 光束伤害减免  6 闪避  7 近战伤害减免  8 近战攻击增加  9 特殊伤害减免  10 特殊攻击增加  11 连击  12 反击  13 生命  14 攻击  15 防御  16 先手  17 伤害  18 怒气
    private int property_type;
    //类型  0 特殊  1 增加数值  2 减少数值  3 增减百分比  4 减少百分比  
    private int type;

    /**
     * 获取编号
     */
    public int getBuff_no() {
        return this.buff_no;
    }

    /**
     * 设置编号
     */
    public void setBuff_no(int buff_no) {
        this.buff_no = buff_no;
    }

    /**
     * 获取描述
     */
    public String getBuff_des() {
        return this.buff_des;
    }

    /**
     * 设置描述
     */
    public void setBuff_des(String buff_des) {
        this.buff_des = buff_des;
    }

    /**
     * 获取buff动画编号
     */
    public int getAnimation() {
        return this.animation;
    }

    /**
     * 设置buff动画编号
     */
    public void setAnimation(int animation) {
        this.animation = animation;
    }

    /**
     * 获取属性类型  0 特殊  1 射击速度  2 速度  3 实弹伤害减免  4 实弹攻击增加  5 光束伤害减免  6 闪避  7 近战伤害减免  8 近战攻击增加  9 特殊伤害减免  10 特殊攻击增加  11 连击  12 反击  13 生命  14 攻击  15 防御  16 先手  17 伤害  18 怒气
     */
    public int getProperty_type() {
        return this.property_type;
    }

    /**
     * 设置属性类型  0 特殊  1 射击速度  2 速度  3 实弹伤害减免  4 实弹攻击增加  5 光束伤害减免  6 闪避  7 近战伤害减免  8 近战攻击增加  9 特殊伤害减免  10 特殊攻击增加  11 连击  12 反击  13 生命  14 攻击  15 防御  16 先手  17 伤害  18 怒气
     */
    public void setProperty_type(int property_type) {
        this.property_type = property_type;
    }

    /**
     * 获取类型  0 特殊  1 增加数值  2 减少数值  3 增加百分比  4 减少百分比
     */
    public int getType() {
        return this.type;
    }

    /**
     * 设置类型  0 特殊  1 增加数值  2 减少数值  3 增加百分比  4 减少百分比
     */
    public void setType(int type) {
        this.type = type;
    }
}
