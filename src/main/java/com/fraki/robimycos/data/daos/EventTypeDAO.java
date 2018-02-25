package com.fraki.robimycos.data.daos;

import com.fraki.robimycos.data.entities.EventTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by bambo on 10.10.2017.
 */
public interface EventTypeDAO extends JpaRepository<EventTypeEntity, Long> {

    Optional<EventTypeEntity> getById(long id);

}
