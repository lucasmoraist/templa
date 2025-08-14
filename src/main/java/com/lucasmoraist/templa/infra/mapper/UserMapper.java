package com.lucasmoraist.templa.infra.mapper;

import com.lucasmoraist.templa.domain.model.User;
import com.lucasmoraist.templa.infra.db.entity.UserEntity;

public final class UserMapper {

    public static User toDomain(UserEntity user) {
        return new User(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.id(),
                user.email(),
                user.password(),
                user.role()
        );
    }

}
