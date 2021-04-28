package creaming.controller;

import creaming.dto.UserDto;
import creaming.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto.LoginRequestDto dto) {
        MemberDto.Response result = new MemberDto.Response();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkNickname(@RequestParam String nickname) {
        boolean result = false;
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto.SignupRequestDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(1L);
    }

}
