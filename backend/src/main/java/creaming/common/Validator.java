package creaming.common;

import creaming.dto.ReviewDto;
import creaming.exception.BaseException;
import creaming.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    public void ratingValidator(Integer rating) {
        if(rating < 0 || rating > 5) {
            throw new BaseException(ErrorCode.VALID_EXCEPTION);
        }
    }

}
