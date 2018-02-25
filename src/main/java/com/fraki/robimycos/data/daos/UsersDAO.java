package com.fraki.robimycos.data.daos;

import com.fraki.robimycos.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Created by bambo on 09.10.2017.
 */
public interface UsersDAO extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(long id);

    Optional<UserEntity> getById(long id);

    Optional<UserEntity> findByLogin(String login);

    Optional<UserEntity> getByLogin(String login);

    @Query("SELECT u FROM UserEntity u WHERE u.login != ?1")
    List<UserEntity> findAllOthers(String login);
}
