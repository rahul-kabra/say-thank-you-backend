package io.saythankyoubackend.repository;

import io.saythankyoubackend.entity.MessageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MessageDataRepository extends JpaRepository<MessageData, Integer> {
    MessageData findByEncodedToken(String token);

    @Modifying
    @Transactional
    @Query(value = "delete from message where submitted_timestamp < (now() - interval 3 hour)", nativeQuery = true)
    void deleteRecords();
}
