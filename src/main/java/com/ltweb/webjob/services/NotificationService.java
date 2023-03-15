package com.ltweb.webjob.services;

import com.ltweb.webjob.dtos.NotifyDto;
import com.ltweb.webjob.dtos.NotifyStatusDto;
import com.ltweb.webjob.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface NotificationService {
    ResponseEntity<?> createNotiForCompany(NotifyDto dto);
    ResponseEntity<?> createNotiForCandiate(NotifyDto dto);

    ResponseEntity<?> setNotiForStatus(int jobAppId);

    NotifyStatusDto getNotiForStatus(int jobAppId);
}
