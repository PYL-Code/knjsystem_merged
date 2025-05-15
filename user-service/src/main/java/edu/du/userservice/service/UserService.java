package edu.du.userservice.service;

import edu.du.userservice.dto.UserDto;

public interface UserService {
    static void update(UserDto userDto) {
    }

    void updateUser(UserDto userDto);
}