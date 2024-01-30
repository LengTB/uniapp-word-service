package top.tobycold.wordservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.tobycold.wordservice.common.Result;
import top.tobycold.wordservice.dto.UserDTO;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    /**
     * 签到接口
     * 补签接口
     */

    /**
     * 登录接口
     * @return
     */
    @PostMapping("login")
    public Result<String> login(@RequestBody UserDTO userDTO){
        log.info("用户登录：{}", userDTO);
        return Result.success();
    }

    @GetMapping("signin")
    public Result<String> signIn() {
        return Result.success("签到成功");
    }

}
