package com.npee.oauth2.sign.advice;

import com.npee.oauth2.sign.advice.exception.CustomUserExistsException;
import com.npee.oauth2.sign.advice.exception.CustomUserSigninFailedException;
import com.npee.oauth2.sign.model.config.CommonResult;
import com.npee.oauth2.sign.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice(basePackages = "com.npee.oauth2.sign")
public class ExceptionAdvice {

    private final ResponseService responseService;
    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("unKnown.code")), getMessage("unKnown.message"));
        // return responseService.getFailResult();
    }

    @ExceptionHandler(CustomUserSigninFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult emailSigninFaildException(HttpServletRequest request, CustomUserSigninFailedException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("signinFailed.code")), getMessage("signinFailed.message"));
    }

    @ExceptionHandler(CustomUserExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult emailSigninFaildException(HttpServletRequest request, CustomUserExistsException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("userExists.code")), getMessage("userExists.message"));
    }

    private String getMessage(String code) {
        return getMessage(code, null);
    }

    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
