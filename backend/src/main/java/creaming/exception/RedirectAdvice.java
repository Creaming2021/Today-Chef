package creaming.exception;

import creaming.common.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.view.RedirectView;

@RestControllerAdvice(annotations = RestController.class)
@RequiredArgsConstructor
public class RedirectAdvice {

    private final AppProperties appProperties;

    // redirect
    @ExceptionHandler({RedirectException.class})
    public RedirectView exceptionHandler(RedirectException e) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(appProperties.getServerUrl() + e.getRedirectCode().getUrl());
        return redirectView;
    }
}
