package shop.mtcoding.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.mall.model.Product;
import shop.mtcoding.mall.model.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {

    // 위에 어노테이션이 있어야 꺼내서 쓸 수 있음.
    // IoC 컨테이너의 내용 DI하기 위해 사용
    // question? SRP 위배에 관련된 것.
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String home(HttpServletRequest request) {

        List<Product> productList = productRepository.findAll();
        request.setAttribute("productList", productList); // request에 productList 담아서 view에서 사용

        // ViewResolver 클래스 발동 WEB-INF/views/home.jsp
        // prefix :  WEB-INF/views/ 를 붙이고
        // suffix : .jsp를 붙여줌
        return "home";
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
        response.sendRedirect("/"); // 홈페이지로 redirect
        // return "redirect:/"; // Springboot에서 지원하는 Redirect 문법
    }

    @GetMapping("/product/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request) {
        System.out.println("id : " + id);
        Product product = productRepository.findById(id);
        request.setAttribute("p", product); // 가방(request)에 수건(product)을 담아서 view에서 사용
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getQty());
        return "detail";
    }

    // 삭제요청, Redirect (정석)
    // @PostMapping("/product/delete")
    // public String delete(HttpServletResponse response) throws IOException {
    //     response.sendRedirect("/"); // 삭제버튼을 누르고 다시 홈페이지로 Redirect
    //     return "home";
    // }

    // 삭제요청, Redirect (Springboot 문법)
    @PostMapping("/product/delete")
    public String delete(int id) {

        // id로 데이터(튜플) 삭제
        productRepository.deleteById(id);

        // Redirect : Springboot에서 지원하는 문법
        return "redirect:/";
    }

    // 상품 수정 요청 -> 홈페이지로 Redirect
    @PostMapping("/product/update")
    public String update(int id, String name, int price, int qty, HttpServletResponse response) throws IOException {

        // 상품 수정 (update)
        productRepository.updateRepo(id, name, price, qty); // 업데이트할 값을 매개변수로 메서드 호출

        // Redirect : Springboot에서 지원하는 문법
        return "redirect:/";
    }

}