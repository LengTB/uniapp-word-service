package top.tobycold.wordservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.tobycold.wordservice.common.Result;
import top.tobycold.wordservice.dto.WordDTO;

@RestController
public class WordController {



    /**
     * 获取单词
     * @param wordDTO
     * count : 学习多少个单词
     * group : 学习第几组单词
     * @return
     */
    @GetMapping("word")
    public Result<?> getWord(@RequestBody WordDTO wordDTO){

        return Result.error();
    }
}
