package com.skhu.controller;

import com.skhu.common.UserLevelCheck;
import com.skhu.dto.UserDto;
import com.skhu.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.skhu.domain.UserLevel.ADMIN;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    @UserLevelCheck(level = ADMIN)
    public ResponseEntity<Page<UserDto.UserResponse>> getUsers(@RequestBody UserDto.UserSearchRequest request,
                                                               @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(adminService.getUsers(request, pageable));
    }

    @DeleteMapping("/{id}")
    @UserLevelCheck(level = ADMIN)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}