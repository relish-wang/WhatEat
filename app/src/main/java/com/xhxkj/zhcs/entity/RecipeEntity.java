package com.xhxkj.zhcs.entity;

import java.util.ArrayList;

import com.xhxkj.zhcs.db.DataSupportCompat;

/**
 * Created by r3lish on 2016/2/21.
 */
public class RecipeEntity extends DataSupportCompat<RecipeEntity> {

    Integer iconResId;
    String name;
    String introduce;
    ArrayList<MaterialEntity> materials;
    ArrayList<String> steps;
    Double comment;
    Integer weeklyFrequency;
    String authorName;
    String createTime;

    public RecipeEntity(Integer iconResId, String name, String introduce, ArrayList<MaterialEntity> materials, ArrayList<String> steps, Double comment, Integer weeklyFrequency, String authorName, String createTime) {
        this.iconResId = iconResId;
        this.name = name;
        this.introduce = introduce;
        this.materials = materials;
        this.steps = steps;
        this.comment = comment;
        this.weeklyFrequency = weeklyFrequency;
        this.authorName = authorName;
        this.createTime = createTime;
    }

    public Integer getIconResId() {
        return iconResId;
    }

    public void setIconResId(Integer iconResId) {
        this.iconResId = iconResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<MaterialEntity> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<MaterialEntity> materials) {
        this.materials = materials;
    }

    public Double getComment() {
        return comment;
    }

    public void setComment(Double comment) {
        this.comment = comment;
    }

    public Integer getWeeklyFrequency() {
        return weeklyFrequency;
    }

    public void setWeeklyFrequency(Integer weeklyFrequency) {
        this.weeklyFrequency = weeklyFrequency;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
