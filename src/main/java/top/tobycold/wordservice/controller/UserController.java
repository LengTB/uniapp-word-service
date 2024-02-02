package top.tobycold.wordservice.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import top.tobycold.wordservice.common.Result;
import top.tobycold.wordservice.dto.UserDTO;
import top.tobycold.wordservice.entity.UserEntity;
import top.tobycold.wordservice.mapper.UserMapper;

import java.util.Properties;

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
     *
     * @return
     */
    @PostMapping("login")
    public Result<String> login(@RequestBody UserDTO userDTO) {
        log.info("用户登录：{}", userDTO);
        try {
            YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
            yamlPropertiesFactoryBean.setResources(new ClassPathResource("test.yml"));
            Properties properties = yamlPropertiesFactoryBean.getObject();
            Object appid = properties.get("appid");
            Object secret = properties.get("secret");
            Object grant_type = properties.get("grant_type");

            try {
                CloseableHttpClient aDefault = HttpClients.createDefault();

                HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/sns/jscode2session?appid="
                        + appid
                        + "&secret=" + secret
                        + "&js_code=" + userDTO.getCode()  //这个code是微信端login方法中的code哦
                        + "&grant_type=" + grant_type);
                CloseableHttpResponse response = aDefault.execute(httpGet);
                String responseString = EntityUtils.toString(response.getEntity());
                try {
                    JSONObject responseEntity = JSONUtil.parseObj(responseString);
                    Object openid = responseEntity.get("openid");
                    /**
                     *  Object session_key = responseEntity.get("session_key");//这里先保留不做使用，因为本人还没有应用场景
                    */
                    UserEntity userEntity = userMapper.selectOne(new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getOpenid, openid));
                    try {
                        Assert.notNull(userEntity, "这是一个新用户");
                    }catch (Exception e){
                        userMapper.insert(new UserEntity( userDTO.getInfo(), (String) openid, 0));
                    }
                    log.info("openid: {}", openid);
                }catch (Exception e){
                    log.error("login接口这里出错了，请排查JSON转换这里是否出了问题！");
                    return Result.error();
                }

                log.info("{}", responseString);
            }catch (Exception e){
                log.error("login接口这里出错了，请排差一下httpClient这里是否请求有误！");
                return Result.error();
            }

        } catch (Exception e) {
            /**
             * 写这里的时候，我特意用 ClassPathResource加载其他路径的资源，其中不免有非spring boot容器管理的资源，后面我也查了资料！
             * 总结： ClassPathResource只能用于加载spring boot容器管理的资源，其他资源请使用FileResource等加载
             */
            log.error("login接口这里出错了，请排查resource资源路径是否有误！");
            return Result.error();
        }
        return Result.success();
    }

    @PostMapping("signin")
    public Result<String> signIn(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        log.info("用户签到：{}", userDTO);
        log.info("{}", request.getContentType());
        return Result.success("签到成功");
    }

    /**
     * 这里是博客园随笔中的示例代码，无业务功能，不删是为了以后不记得再来取经！
     *
     * @return
     */
    @GetMapping("test")
    @Deprecated
    public Result<?> test() {
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
    public Object test2(UserEntity userEntity) {
        return userEntity.getId();
    }

}
