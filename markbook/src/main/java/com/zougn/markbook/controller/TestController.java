package com.zougn.markbook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import com.zougn.markbook.domain.BookmarkRequest;

// 使用@RestController注解，表示该类是RESTful控制器，返回数据而非视图
@RestController
// 定义控制器的基础路径
@RequestMapping("/api")
public class TestController {
    // 接收JSON格式参数（适合现代前后端分离架构）
    @PostMapping("/bookmark")
    public ResponseEntity<Map<String, Object>> addBookmarkJson(
        @RequestBody BookmarkRequest request) {
        // 统一处理逻辑
	Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("timestamp", LocalDateTime.now());
        System.out.println(request.getTitle());
        return ResponseEntity.ok(response);
    }

    // 带参数的GET请求示例（参考证据2、19）
    @GetMapping("/greet")
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}

