package com.repository;

import com.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Long> {
    @Query("select m from Message m " +
            "where ((m.sender.id = :senderId and m.receiver.id = :receiverId) " +
            "or (m.sender.id = :receiverId and m.receiver.id = :senderId))")
    List<Message> findAllBySenderAndReceiver(@Param("senderId") Long senderId,
                                             @Param("receiverId") Long receiverId);
}
