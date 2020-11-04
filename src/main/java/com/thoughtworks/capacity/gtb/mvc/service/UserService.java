package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserNotValidException;
import com.thoughtworks.capacity.gtb.mvc.po.UserPo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    // Map<Integer, UserPo> users = new HashMap<>();
    List<UserPo> users = new ArrayList<>();
    List<String> names = new ArrayList<>();

    public UserService() {
        UserPo user1 = new UserPo(creatId(), "Lily", "euij478", "lily@qq.com");
        UserPo user2 = new UserPo(creatId(), "xiao_ming", "50fijtg0", "xiaoming@qq.com");
        //   users.put(1, user1);
        //   users.put(2, user2);
        users.add(user1);
        users.add(user2);
    }

    public void register(User user) {
        users.stream().forEach(item -> {
            if (item.getUsername().equals(user.getUsername())) throw new UserNotValidException("用户已存在");
        });
        UserPo userPo = UserPo.builder()
                .id(creatId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
        users.add(userPo);
    }

    private static String creatId() {
        long w = 100000000;
        long r = (long) ((Math.random() + 1) * w);
        return System.currentTimeMillis() + String.valueOf(r).substring(1);
    }

    public UserPo login(String username, String password) {
        List<UserPo> result = users.stream()
                .filter(item -> item.getUsername().equals(username))
                .filter(item -> item.getPassword().equals(password))
                .collect(Collectors.toList());
        if (result.isEmpty()) throw new UserNotValidException("用户名或密码错误");
        return result.get(0);
    }

    public List<UserPo> get() {
        return users;
    }
}
