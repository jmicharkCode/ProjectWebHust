package com.ltweb.webjob.repositories;

import com.ltweb.webjob.entities.FileDB;
import com.ltweb.webjob.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer> {
}
