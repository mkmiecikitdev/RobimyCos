package com.fraki.robimycos.data.daos;

import com.fraki.robimycos.data.entities.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Created by bambo on 14.10.2017.
 */
public interface ConversationsDAO extends JpaRepository<ConversationEntity, Long> {

    @Query("SELECT c FROM ConversationEntity c WHERE ((c.user1.login = ?1 AND c.user2.id = ?2) OR (c.user2.login = ?1 AND c.user1.id = ?2))")
    ConversationEntity findByUsers(String login, long id);

    @Query("SELECT c FROM ConversationEntity c WHERE ((c.user1.login = ?1) OR (c.user2.login = ?1))")
    List<ConversationEntity> findConversationsByLogin(String login);

    Optional<ConversationEntity> findById(long id);
}
