package creaming.controller;

import creaming.dto.KakaopayDto;
import creaming.dto.OrderDto;
import creaming.dto.RegisterDto;
import creaming.service.KakaopayService;
import creaming.service.MemberService;
import creaming.service.OrderService;
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
    private String localUrl = "http://localhost:8080/payment/";
    private String serverUrl = "https://k4b204.p.ssafy.io/payment";

    @GetMapping
    public ResponseEntity<KakaopayDto.KakaopayAddress> kakaoPay(@RequestParam("amount") int amount,
                                                                HttpServletResponse response) throws IOException {
        log.info("kakaoPay post.. amount: {}", amount);
        String result = kakaoPayService.kakaoPayReady(amount).getUrl();
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
        log.info("result: " + result);

        response.sendRedirect(serverUrl + "success");
    }

    @GetMapping("/cancel")
    public void kakaoPayCancel(HttpServletResponse response) throws IOException {
        log.info("kakaoPayCancel get .....");
        response.sendRedirect(serverUrl + "fail");
    }

    @GetMapping("/fail")
    public void kakaoPayFail(HttpServletResponse response) throws IOException {
        log.info("kakaoPayFail get .....");
        response.sendRedirect(serverUrl + "fail");
    }

}
