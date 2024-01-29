package top.tobycold.wordservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("word_user")
public class UserEntity {
    //主键id
    private String id;
    //名称
    private String name;
    //微信openid
    private String openid;
    //学习组数
    private Integer group;
}
