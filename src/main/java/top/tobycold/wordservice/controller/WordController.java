package top.tobycold.wordservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tobycold.wordservice.common.Result;
import top.tobycold.wordservice.service.WordService;

@RestController
public class WordController {



    /**
     * 获取单词
     * @param wordGroupDTO
     * count : 学习多少个单词
     * group : 学习第几组单词
     * @return
     */
    @Autowired
    WordService wordService;

    @GetMapping("word")
    public Result<?> getGroupWord(){
        wordService.getGroupWord();
        return Result.error();
    }

}
