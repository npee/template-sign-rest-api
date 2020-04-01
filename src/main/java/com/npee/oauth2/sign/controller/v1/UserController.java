package com.npee.oauth2.sign.controller.v1;

import com.npee.oauth2.sign.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. User"})
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @ApiOperation(value = "회원 리스트 출력", notes = "회원 전체 리스트를 조회")
    @GetMapping("/users")
    public String readAllUsers() {

        return "User List";
    }

    @ApiOperation(value = "특정 회원 조회", notes = "특정한 회원 한 명만 조회")
    @GetMapping("/user/{userNo}")
    public String readUser(
            @ApiParam(value = "회원 번호", required = true) @PathVariable Long userNo) {

        return "A User";
    }

    @ApiOperation(value = "회원 추가", notes = "회원 정보 생성")
    @PostMapping("/user")
    public String createUser(
            @ApiParam(value = "회원 아이디", required = true) @RequestParam String uid,
            @ApiParam(value = "회원 비밀번호", required = true) @RequestParam String password) {

        return "Create User";
    }

    @ApiOperation(value = "회원정보 수정", notes = "특정한 회원 정보 수정")
    @PutMapping("/user/{userNo}")
    public String updateUser(
            @ApiParam(value = "회원 번호", required = true) @PathVariable Long userNo,
            @ApiParam(value = "회원 아이디", required = true) @RequestParam String uid) {

        return "Update User";
    }

    @ApiOperation(value = "회원 삭제", notes = "특정한 회원 삭제")
    @DeleteMapping("/user/{userNo}")
    public String deleteUser(
            @ApiParam(value = "회원 번호", required = true) @PathVariable Long userNo) {

        return "Delete User";
    }

}
