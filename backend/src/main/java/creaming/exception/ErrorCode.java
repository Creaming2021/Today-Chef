package creaming.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    /**
     * ErrorCode Convention
     * 첫,두번째 숫자 : 서비스 종류
     * 세번째 숫자 : 응답 0, 에러 1
     * 네,다섯번째 숫자 : 번호
     * ex) 01(서비스종류)0(응답)01(번호)
     *
     * !!!!!!!! 에러코드 만들 때 Httpstatus 는 검색을 통해 확인
     */

    // Member
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "01000", "없는 계정"),

    // Course
    COURSE_NOT_FOUND(HttpStatus.NOT_FOUND, "02000", "없는 강의"),

    // Review
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "03000", "없는 후기"),
    REVIEW_COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "03001", "없는 댓글"),

    // 서버 에러
    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "00100"),
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "00101"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "00102"),
    VALID_EXCEPTION(HttpStatus.BAD_REQUEST, "00103");
    /////////////////////////////////////////

    private final HttpStatus status;
    private final String code;
    private String message;


    ErrorCode(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}