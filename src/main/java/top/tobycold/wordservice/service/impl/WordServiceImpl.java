package top.tobycold.wordservice.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import top.tobycold.wordservice.common.BaseValues;
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
     * 复习模式返回时间集
     * <p>
     * 艾宾浩斯记忆遗忘曲线记忆法
     * 1 2 4 7 15 这个天周期来进行
     * <p>
     * 示例：
     * 用户返回一个单词，在确定学习提交接口中，定义学习次数 studyCount, this studyCount = 1
     * 提交接口中要有提交用户，和提交时间，还有就是防止用户刷单词程度，必须校验提交时间是否为该学习时间，否出提交失败
     */
    @Autowired
    WordMapper wordMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserMapper userMapper;
    @Autowired
    StudyWordMapper studyWordMapper;


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
     * 获得一组单词
     * 示例：20个
     *
     * @return
     */
    public Set<StudyWordEntity> getGroupWord() {

        //学习的单词缓存
        //用用户id作为key，用StudyWordEntity作为value
        SetOperations setOperations = redisTemplate.opsForSet();
        try {
            Assert.notNull(setOperations, "redis没有该用户的单词缓存记录!");
        } catch (Exception e1) {
            ValueOperations valueOperations = redisTemplate.opsForValue();
            try {
                Object redisGroupCount = valueOperations.get(BaseValues.getID());
                Assert.notNull(redisGroupCount, "redis中没有这个值");
            } catch (Exception e2) {
                UserEntity userEntity = userMapper.selectOne(new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getId, BaseValues.getID()));
                valueOperations.set(BaseValues.getID(), userEntity.getGroup_count());
            }
            setOperations.add(BaseValues.getID(), wordMapper.selectGroupWord(((int)valueOperations.get(BaseValues.getID()) - 1) * 20, 20));
        }

        return setOperations.members(BaseValues.getID());
    }

    /**
     * 学习单词方法
     *
     * @param word
     * @return
     */
    public boolean pushWord(String word) {
        SetOperations setOperations = redisTemplate.opsForSet();
        Set<StudyWordEntity> members = setOperations.members(BaseValues.getID());
        members.forEach(studyWordEntity -> {
            if (studyWordEntity.equals(word)) {
                //如果单词已经存在，那么就将学习次数加一
                if (studyWordEntity.getCount() < 3) {
                    studyWordEntity.setCount(studyWordEntity.getCount() + 1);
                }
            }
        });
        return false;
    }
}
