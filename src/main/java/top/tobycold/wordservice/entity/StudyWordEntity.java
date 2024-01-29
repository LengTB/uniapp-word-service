package top.tobycold.wordservice.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("study_word")
public class StudyWordEntity {
    private String word;
    private String user;
    private int count;
    private DateTime date;
}
