package creaming.controller;

import creaming.dto.KakaopayDto;
import creaming.service.KakaopayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/kakao-pay")
@RequiredArgsConstructor
public class KakaopayController {

    private final KakaopayService kakaoPayService;


    @GetMapping
    public ResponseEntity<KakaopayDto.KakaopayAddress> kakaoPay(@RequestParam("amount") int amount,
                                                                HttpServletResponse response) throws IOException {
        log.info("kakaoPay post.. amount: {}", amount);

//        return "redirect:" + kakaoPay3334Service.kakaoPayReady(amount); // 프론트 주소를 보내보자
        String result = kakaoPayService.kakaoPayReady(amount);
        log.info("result: {}", result);
        return ResponseEntity.status(HttpStatus.OK).body(new KakaopayDto.KakaopayAddress(result));
    }

    @GetMapping("/success/{kakaopayId}")
    public void kakaoPaySuccess(@PathVariable("kakaopayId") Long kakaopayId,
                                HttpServletResponse response,
                                @RequestParam("pg_token") String pg_token) throws IOException {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess kakaopayId : " + kakaopayId);
        log.info("kakaoPaySuccess pg_token : " + pg_token);
        KakaopayDto.KakaopayApproval result = kakaoPayService.kakaoPayInfo(kakaopayId, pg_token);

        response.sendRedirect("https://k4b204.p.ssafy.io");
    }

    @GetMapping("/cancel")
    public void kakaoPayCancel(HttpServletResponse response) throws IOException {
        log.info("kakaoPayCancel get .....");
        response.sendRedirect("https://k4b204.p.ssafy.io");
    }

    @GetMapping("/fail")
    public void kakaoPayFail(HttpServletResponse response) throws IOException {
        log.info("kakaoPayFail get .....");
        response.sendRedirect("https://k4b204.p.ssafy.io");
    }

}
