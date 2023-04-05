package org.trananh3010.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trananh3010.model.User;
import org.trananh3010.repository.UserRepository;
import org.trananh3010.ultilities.MyHttpResponse;

@RestController
@RequestMapping("/auths")
public class AuthController {
	@Autowired
    private UserRepository userRepository;
	
	@PostMapping("/login")
	public MyHttpResponse login(@Validated @RequestBody Map<String, String> info) {
        User user = userRepository.findByAccount(info.get("account"));
        if (user == null) {
			return new MyHttpResponse(404, "Sai tài khoản", null);
		}
    	if(user.getAccount().getPassword().trim().equals(info.get("password"))) {
    		return new MyHttpResponse(200, "Đăng nhập thành công", user);
    	}
    	return new MyHttpResponse(404, "Sai mật khẩu", null);
    }
}
