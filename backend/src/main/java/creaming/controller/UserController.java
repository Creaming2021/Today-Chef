package creaming.controller;

import creaming.dto.UserDto;
import creaming.dto.MemberDto;
import creaming.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 로그인 응답값 : memberId, nickname
    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity<UserDto.LoginResponseDto> login(@RequestBody @Valid UserDto.LoginRequestDto dto) {
        UserDto.LoginResponseDto result = userService.login(dto.getKakaoId());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/check")
    @Operation(summary = "이메일 중복 확인")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        Boolean result = userService.checkEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/signup")
    @Operation(summary = "회원 가입")
    public ResponseEntity<UserDto.LoginResponseDto> signup(@RequestBody @Valid UserDto.SignupRequestDto dto) {
        UserDto.LoginResponseDto result = userService.signUp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
