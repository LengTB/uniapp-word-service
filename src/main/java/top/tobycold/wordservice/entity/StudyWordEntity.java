package top.tobycold.wordservice.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("study_word")
public class StudyWordEntity implements Serializable {
    private String word;
    private String user;
    private int count;
    private DateTime date;
}
