package top.tobycold.wordservice.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@RequestMapping("static")
@Slf4j
public class StaticResourceController {

    @GetMapping("{name}")
    public void getStaticResource(@PathVariable String name, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        log.info("请求静态资源：" + name);
        File file = new File("F:\\照片\\桌面壁纸\\雷军\\车到山前必有路.jpg");
        FileInputStream fileReader = new FileInputStream(file);
        byte[] bytes = fileReader.readAllBytes();
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.write(bytes);
    }
}
