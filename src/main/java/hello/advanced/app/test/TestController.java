package hello.advanced.app.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/ttt")
    public String test(){
        return "test";
    }
}
