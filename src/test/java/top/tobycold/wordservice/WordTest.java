package top.tobycold.wordservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.tobycold.wordservice.common.BaseValues;
import top.tobycold.wordservice.entity.WordEntity;
import top.tobycold.wordservice.mapper.WordMapper;

import java.util.List;

@SpringBootTest
public class WordTest {
    @Autowired
    WordMapper wordMapper;
    @Test
    public void test(){
        int count = 3; //第一组
        List<WordEntity> wordEntities = wordMapper.selectGroupWord((count - 1) * 20 , 20);
        System.out.println(wordEntities);
    }
}
