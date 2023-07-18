package shop.mtcoding.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/home")
    public String home() {
        return "home"; // ViewResolver 클래스 발동 WEB-INF/views/home.jsp
        // prefix :  WEB-INF/views/ 를 붙이고
        // suffix : .jsp를 붙여줌

    }

    @GetMapping("/write")
    public String write() {
        return "write";
    }
}
