package top.tobycold.wordservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.tobycold.wordservice.entity.UserEntity;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
