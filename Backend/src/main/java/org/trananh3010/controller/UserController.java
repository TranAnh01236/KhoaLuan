package org.trananh3010.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trananh3010.exception.ResourceNotFoundException;
import org.trananh3010.model.User;
import org.trananh3010.repository.UserRepository;
import org.trananh3010.ultilities.MyHttpResponse;
import org.trananh3010.ultilities.MyHttpResponseArray;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired(required = true)
    private UserRepository userRepository;
	
	@GetMapping("/")
    public MyHttpResponseArray getAllUsers() {
        List<User> users = userRepository.findAll();
      
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
			objects.add(users.get(i));
		}
        if (users!=null && users.size()>0) {
			return new MyHttpResponseArray(200, "Tìm thành công", objects);
		}
        return new MyHttpResponseArray(404, "Không tìm thấy", null);
    }

    @GetMapping("/{id}")
    public MyHttpResponse getUserbyId(@PathVariable(value = "id") String userId)
        throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
			return new MyHttpResponse(404, "không tìm thấy", null);
		}
        return new MyHttpResponse(200, "Tìm thành công", user);
    }
    
    @PostMapping("/")
    public User createUser(@Validated @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") String userId,
         @Validated @RequestBody User user) throws ResourceNotFoundException {
        User user1 = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));

        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setAccount(user.getAccount());
        user1.setEmail(user.getEmal());
        user1.setPassword(user.getPassword());
        final User updatedUser = userRepository.save(user1);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") String userId)
         throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
