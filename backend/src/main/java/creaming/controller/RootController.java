package creaming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String homeRedirect() {
        return "redirect:/swagger-ui/index.html";
    }

}