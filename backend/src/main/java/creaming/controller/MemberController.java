package creaming.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("members")
public class MemberController {

    public void test() {

        String uuidStr = UUID.randomUUID().toString();

    }

}

