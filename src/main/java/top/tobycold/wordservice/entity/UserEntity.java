package top.tobycold.wordservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@TableName("word_user")
public class UserEntity {
    //主键id
    private Long id;
    //名称
    private String name;
    //微信openid
    private String openid;
    //学习组数
    private int group_count;

    public UserEntity(String name, String openid, int group_count) {
        this.name = name;
        this.openid = openid;
        this.group_count = group_count;
    }
}
