package top.tobycold.wordservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tobycold.wordservice.dto.WordDTO;
import top.tobycold.wordservice.entity.WordEntity;
import top.tobycold.wordservice.mapper.WordMapper;
import top.tobycold.wordservice.service.WordService;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {
    /**
     * 单词要分组一次请求多少个，调整数量，请求了多少组，学习了多少组
     * 20 * 1 = 返回的单词数量
     * 1 - 20
     * 20 * 2 = 返回的单词数量
     * 20 - 40
     * 20 * 3 = 返回的单词数量
     * 40 - 60
     * (count-1 * 20 + 1) - (count * 20)
     */
    @Autowired
    WordMapper wordMapper;
    public List<WordEntity> getByGroup(WordDTO wordDTO) {
        wordMapper.selectPage();
        return null;
    }
}
