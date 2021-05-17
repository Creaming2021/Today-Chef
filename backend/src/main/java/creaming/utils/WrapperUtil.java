package creaming.utils;

import creaming.exception.FunctionWithException;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class WrapperUtil {

    // 람다식 내 try catch 문을 없애기 위한 방법
    private <T, R, E extends Exception> Function<T, R> wrapper(FunctionWithException<T, R, E> fe) {
        return arg -> {
            try {
                return fe.apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

}
