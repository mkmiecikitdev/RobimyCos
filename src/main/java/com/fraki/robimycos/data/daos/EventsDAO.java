package com.fraki.robimycos.data.daos;

import com.fraki.robimycos.data.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by bambo on 10.10.2017.
 */
public interface EventsDAO extends JpaRepository<EventEntity, Long> {

    @Query("SELECT e FROM EventEntity e WHERE e.toUser.login = ?1 AND e.finish = false AND e.date >= ?2 ORDER BY e.date DESC")
    List<EventEntity> findActiveEvents(String login, Date date);

    @Query("SELECT COUNT(*) FROM EventEntity e WHERE e.toUser.login = ?1 AND e.finish = false AND e.date >= ?2")
    short getActiveEventsCount(String login, Date date);

    Optional<EventEntity> findById(long id);

}
