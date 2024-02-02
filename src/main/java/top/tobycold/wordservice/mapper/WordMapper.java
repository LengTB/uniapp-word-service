package top.tobycold.wordservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.tobycold.wordservice.entity.WordEntity;

import java.util.List;

@Mapper
public interface WordMapper extends BaseMapper<WordEntity> {
    @Select("select * from word limit #{start}, #{end}")
    List<WordEntity> selectGroupWord(int start, int end);
}
