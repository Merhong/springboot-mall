package shop.mtcoding.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        String name = "홍길동";
        request.setAttribute("price", name);

        ArrayList<String> list = new ArrayList<>();
        list.add("바나나");
        list.add("딸기");
        list.add("참외");

        request.setAttribute("banana", list.get(0));
        request.setAttribute("ddalgi", list.get(1));
        request.setAttribute("chamwei", list.get(2));

        request.setAttribute("lost", list);
        return "hello";
    }
}