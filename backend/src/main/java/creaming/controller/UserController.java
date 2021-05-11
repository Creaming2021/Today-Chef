package creaming.controller;

import creaming.dto.UserDto;
import creaming.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    // TODO : jwt 추가

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity<UserDto.LoginResponseDto> login(@RequestBody @Valid UserDto.LoginRequestDto dto) {
        UserDto.LoginResponseDto result = userService.login(dto.getKakaoId());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/email")
    @Operation(summary = "이메일 중복 확인")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        Boolean result = userService.checkEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/nickname")
    @Operation(summary = "닉네임 중복 확인")
    public ResponseEntity<Boolean> checkNickname(@RequestParam String nickname) {
        Boolean result = userService.checkNickname(nickname);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/signup")
    @Operation(summary = "회원 가입")
    public ResponseEntity<UserDto.LoginResponseDto> signup(@RequestBody @Valid UserDto.SignupRequestDto dto) {
        UserDto.LoginResponseDto result = userService.signUp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
