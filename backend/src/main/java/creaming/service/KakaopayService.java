package creaming.service;

import creaming.domain.kakaopay.Kakaopay;
import creaming.domain.kakaopay.KakaopayRepository;
import creaming.dto.KakaopayDto;
import creaming.dto.OrderDto;
import creaming.dto.RegisterDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class KakaopayService {

    private static final String HOST = "https://kapi.kakao.com";
    private static final String APP_ADMIN_KEY = "1d674649bcbf1fe696a3cb5d258049cf";
    private static final String CID = "TC0ONETIME"; // 테스트 결제용 CID
    private final KakaopayRepository kakaopayRepository;
    private KakaopayDto.KakaopayReady kakaoPayReadyDto;
    private int amount;
    private String localUrl = "http://localhost:9999/api/";
    private String serverUrl = "https://k4b204.p.ssafy.io/api/";

    // 결제 준비
    public Kakaopay kakaoPayReady(int amount) {
        this.amount = amount;

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + APP_ADMIN_KEY);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=utf-8");

        Kakaopay kakaopay = new Kakaopay();
        kakaopayRepository.save(kakaopay);
        System.out.println(kakaopay.getId());

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", CID);
        params.add("partner_order_id", kakaopay.getId() + "");
        params.add("partner_user_id", "melon");
        params.add("item_name", "CREAMING");
        params.add("quantity", amount + "");
        params.add("total_amount", amount + "");
        params.add("tax_free_amount", "0");
        params.add("approval_url", serverUrl + "kakao-pay/success/" + kakaopay.getId()); // 프론트 페이지 주소로 보내기
        params.add("cancel_url", serverUrl + "kakao-pay/cancel");
        params.add("fail_url", serverUrl + "/kakao-pay/fail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        try {
            KakaopayDto.KakaopayReady kakaoPayReadyDto = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaopayDto.KakaopayReady.class);

            log.info("" + kakaoPayReadyDto);

            kakaopay.setAmount(amount);
            kakaopay.setCreated_at(kakaoPayReadyDto.getCreated_at());
            kakaopay.setTid(kakaoPayReadyDto.getTid());
            kakaopay.setUrl(kakaoPayReadyDto.getNext_redirect_pc_url());

            return kakaopay;

        } catch (RestClientException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        kakaopay.setUrl("/kakao-pay");
        return kakaopay;
    }


    // 결제 승인
    public KakaopayDto.KakaopayApproval kakaoPayInfo(Long kakaopayId, String pg_token) {
        Kakaopay kakaopay = kakaopayRepository.findById(kakaopayId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + APP_ADMIN_KEY);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", CID);
        params.add("tid", kakaopay.getTid());
        params.add("partner_order_id", kakaopay.getId() + "");
        params.add("partner_user_id", "melon");
        params.add("pg_token", pg_token);
        params.add("total_amount", kakaopay.getAmount() + "");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            KakaopayDto.KakaopayApproval kakaoPayApprovalDto = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaopayDto.KakaopayApproval.class);
            log.info("" + kakaoPayApprovalDto);

            return kakaoPayApprovalDto;

        } catch (RestClientException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}
