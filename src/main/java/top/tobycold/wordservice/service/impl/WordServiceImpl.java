package top.tobycold.wordservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import top.tobycold.wordservice.common.BaseValues;
import top.tobycold.wordservice.dto.WordDTO;
import top.tobycold.wordservice.entity.StudyWordEntity;
import top.tobycold.wordservice.entity.UserEntity;
import top.tobycold.wordservice.entity.WordEntity;
import top.tobycold.wordservice.mapper.StudyWordMapper;
import top.tobycold.wordservice.mapper.UserMapper;
import top.tobycold.wordservice.mapper.WordMapper;
import top.tobycold.wordservice.service.WordService;

import java.util.List;
import java.util.Set;

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

    /**
     * 复习模式返回时间集
     *
     * 艾宾浩斯记忆遗忘曲线记忆法
     *  1 2 4 7 15 这个天周期来进行
     *
     *  示例：
     *      用户返回一个单词，在确定学习提交接口中，定义学习次数 studyCount, this studyCount = 1
     *      提交接口中要有提交用户，和提交时间，还有就是防止用户刷单词程度，必须校验提交时间是否为该学习时间，否出提交失败
     */
    @Autowired
    WordMapper wordMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserMapper userMapper;
    @Autowired
    StudyWordMapper studyWordMapper;

    //初始化学习接口，将需要学习的单词转移到study_word表中
    public void initStudyWord() {
        //查询学习的用户
        QueryWrapper<UserEntity> user = new QueryWrapper<>();
        user.eq("id", BaseValues.getID());
        UserEntity userEntity = userMapper.selectOne(user);
        String userEntityName = userEntity.getName();
        LambdaQueryWrapper<WordEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<WordEntity> wordEntities = wordMapper.selectList(lambdaQueryWrapper);
        wordEntities.forEach(wordEntity -> {
            //将所有单词添加到学习表中
            StudyWordEntity studyWordEntity = new StudyWordEntity();
            studyWordEntity.setWord(wordEntity.getWord());
            studyWordEntity.setUser(userEntityName);
            studyWordEntity.setDate(null);
            studyWordEntity.setCount(0);
            studyWordMapper.insert(studyWordEntity);
        });
    }

    public Set<WordEntity> getGroupWord() {
        //学习的单词缓存
        SetOperations setOperations = redisTemplate.opsForSet();
        Long size = setOperations.size(BaseValues.getID());
        if (size > 0) {
            return setOperations.members(BaseValues.getID());
        }
        //如果没有值就只能去数据库中取值

        return null;
    }
    //提交单词接口
    public boolean pushWord(@RequestBody WordDTO wordDTO){
        SetOperations setOperations = redisTemplate.opsForSet();
        //查找学习缓存中是否有该单词
        if (setOperations.isMember(BaseValues.getID(), wordDTO.getWord())) {
           return false;
        }
        //添加单词到缓存中
        setOperations.add(BaseValues.getID(), wordDTO.getWord());
        return true;
    }
}
