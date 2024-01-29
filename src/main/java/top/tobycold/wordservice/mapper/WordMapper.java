package top.tobycold.wordservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.tobycold.wordservice.entity.WordEntity;

@Mapper
public interface WordMapper extends BaseMapper<WordEntity> {
}
