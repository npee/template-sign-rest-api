package com.npee.oauth2.sign.controller.v1;

import com.npee.oauth2.sign.advice.exception.CustomUserExistsException;
import com.npee.oauth2.sign.advice.exception.CustomUserSigninFailedException;
import com.npee.oauth2.sign.model.User;
import com.npee.oauth2.sign.model.config.CommonResult;
import com.npee.oauth2.sign.model.config.SingleResult;
import com.npee.oauth2.sign.repository.UserRepository;
import com.npee.oauth2.sign.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Api(tags = {"2. Sign"})
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SignController {

    private final UserRepository userRepository;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation(value = "일반회원 회원가입", notes = "아이디와 비밀번호를 이용하여 회원가입")
    @PostMapping("/signup")
    public CommonResult signup(
            @ApiParam(value = "회원 아이디", required = true) @RequestParam String uid,
            @ApiParam(value = "회원 비밀번호", required = true) @RequestParam String password) {

        Optional<User> user = userRepository.findByUid(uid);
        if (user.isPresent()) {
            throw new CustomUserExistsException();
        }

        User newUser = userRepository.save(User.builder()
            .uid(uid)
            .password(passwordEncoder.encode(password))
            .build());

        return responseService.getSingleResult(newUser);
    }

    @ApiOperation(value = "일반회원 로그인", notes = "일반회원 가입시 등록한 아이디와 비밀번호로 로그인")
    @PostMapping("/signin")
    public SingleResult<User> signin(
            @ApiParam(value = "회원 아이디", required = true) @RequestParam String uid,
            @ApiParam(value = "회원 비밀번호", required = true) @RequestParam String password) {

        User user = userRepository.findByUid(uid).orElseThrow(CustomUserSigninFailedException::new);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomUserSigninFailedException();
        }

        return responseService.getSingleResult(user);
    }
}
