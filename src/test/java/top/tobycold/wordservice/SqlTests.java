package top.tobycold.wordservice;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.tobycold.wordservice.entity.WordEntity;
import top.tobycold.wordservice.mapper.WordMapper;

import java.util.List;

@SpringBootTest
public class SqlTests {
    @Autowired
    WordMapper wordMapper;
    @Test
    public void  selectTest(){
//        QueryWrapper<WordEntity> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("word", "abandon");
//        WordEntity wordEntity = wordMapper.selectOne(queryWrapper);
//        System.out.println(wordEntity.toString());

        LambdaQueryWrapper<WordEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<WordEntity> wordEntities = wordMapper.selectList(lambdaQueryWrapper);
        wordEntities.forEach(System.out::println);
    }
}
