package top.tobycold.wordservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.tobycold.wordservice.common.Result;
import top.tobycold.wordservice.dto.UserDTO;
import top.tobycold.wordservice.entity.UserEntity;
import top.tobycold.wordservice.mapper.UserMapper;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    /**
     * 签到接口
     * 补签接口
     */
    @Autowired
    UserMapper userMapper;

    /**
     * 登录接口
     * @return
     */
    @PostMapping("login")
    public String login(@RequestBody UserDTO userDTO){
        log.info("用户登录：{}", userDTO);
        return "Result.success()";
    }

    @PostMapping("signin")
    public Result<String> signIn(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        log.info("用户签到：{}", userDTO);
        log.info("{}", request.getContentType());
        return Result.success("签到成功");
    }

    /**
     * 这里是博客园随笔中的示例代码，无业务功能，不删是为了以后不记得再来取经！
     * @return
     */
    @GetMapping("test")
    @Deprecated
    public Result<?> test(){
        log.info("test");
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        //特殊的方法引用
        userMapper.selectList(queryWrapper.eq(UserEntity::getId, "1"));
        //其他常见的方法引用
        userMapper.selectList(queryWrapper.eq(userEntity -> userEntity.getId(), "1"));
        userMapper.selectList(queryWrapper.eq(this::test2, "1"));
        return Result.success("test");
    }
    @Deprecated
    public Object test2(UserEntity userEntity){
        return userEntity.getId();
    }

}
