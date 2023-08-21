package mainbooks.com.br.MainBooks.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;

@Controller
public class HelloControler {

    @GetMapping("/hello")
    public String hello(Model model){
        return "Index";
    }
}
