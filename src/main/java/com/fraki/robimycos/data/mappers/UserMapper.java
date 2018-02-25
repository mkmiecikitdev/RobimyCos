package com.fraki.robimycos.data.mappers;

import com.fraki.robimycos.data.businessmodels.User;
import com.fraki.robimycos.data.entities.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bambo on 09.10.2017.
 */
public class UserMapper {

    public static User convertToUser(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setLogin(entity.getLogin());
        user.setPhone(entity.getPhone());
        user.setTokenFirebase(entity.getTokenFirebase());
        return user;
    }

    public static List<User> convertToUserList(List<UserEntity> entityList) {
        return entityList.stream().map(UserMapper::convertToUser).collect(Collectors.toList());
    }

}
