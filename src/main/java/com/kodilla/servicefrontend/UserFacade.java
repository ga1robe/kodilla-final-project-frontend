package com.kodilla.servicefrontend;


import com.kodilla.servicefrontend.backend.api.UserClient;
import com.kodilla.servicefrontend.backend.api.mapper.UserMapper;
import com.kodilla.servicefrontend.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserFacade {
    private final UserClient userClient;
    private final UserMapper userMapper;

    public User getUserById(final Long id) {
        return userMapper.mapToUser( userClient.getUserById(id) ) ;
    }

    public Integer registerUser(final User user) {
        return userClient.registerUser( userMapper.mapToRegistrationDto(user));
    }

    public List<User> getAllUsers() {
        return userMapper.mapToUserListFromUserListDto( userClient.getAllUsers() );
    }

    public User updateUser(final User user) {
        return userMapper.mapToUser( userClient.updateUser( userMapper.mapToUserDto(user) ) );
    }

    public void deleteUser(final Long id) {
        userClient.deleteUser(id);
    }
}
