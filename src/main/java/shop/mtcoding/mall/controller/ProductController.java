package shop.mtcoding.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.mall.model.Product;
import shop.mtcoding.mall.model.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository; // IoC 컨테이너의 내용 DI하기 위해 사용
    // question? SRP 위배에 관련된 것.

    @GetMapping("/home")
    public String home(HttpServletRequest request) {
        List<Product> productList = productRepository.findAll();
        request.setAttribute("productList", productList);


        return "home"; // ViewResolver 클래스 발동 WEB-INF/views/home.jsp
        // prefix :  WEB-INF/views/ 를 붙이고
        // suffix : .jsp를 붙여줌
    }

    @GetMapping("/write")
    public String writepage() {
        return "write";
    }

    @PostMapping("/product")    // 포스트맵핑에는 모델명을 넣는다.
    public void write(String name, int price, int qty, HttpServletResponse response) throws IOException { // 파싱한 값을 받는다.
        System.out.println("name : " + name);
        System.out.println("price : " + price);
        System.out.println("qty : " + qty);

        productRepository.save(name, price, qty); // IoC 컨테이너 DI
        response.sendRedirect("/");
    }
}
