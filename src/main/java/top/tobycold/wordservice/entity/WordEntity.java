package top.tobycold.wordservice.entity;

import lombok.Data;

@Data
public class WordEntity {
    // 分类
    private String initial;
    // 释义
    private String mean;
    // 发音
    private String pronunciation;
    // 单词
    private String word;
}
