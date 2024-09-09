package Finial.HMS.HomeController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home_Controller {

    @GetMapping("/index")
    public String home() {return "index";}

    @GetMapping("/appointment")
    public String home1() {return "appointment";}
    @GetMapping("/blog-single")
    public String home2() {return "blog-single";}
    @GetMapping("/Register")
    public String register() {return "Register";}


    @GetMapping("/error")
    public String error() {return "error";}

}
