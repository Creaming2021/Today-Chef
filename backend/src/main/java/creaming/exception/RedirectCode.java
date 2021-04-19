package creaming.exception;

import lombok.Getter;

@Getter
public enum RedirectCode {

    // example
    WRONG_EMAIL_CHECK("/signup/fail", "이메일 인증 실패");

    private final String url;
    private String message;

    RedirectCode(String url, String message) {
        this.url = url;
        this.message = message;
    }

    RedirectCode(String url) {
        this.url = url;
    }
}
