/*
 * Copyright 2014 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.linklife.domain.ibator;

/**
 * <p>
 * AlertModel.java
 * </p>
 *
 * <pre>
 * 用于向AlertPage网页模板传递数据
 * </pre>
 *
 * @author caisupeng
 */
public class AlertModel {

    /** 通知标题 */
    private String title;

    /** 通知内容 */
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
