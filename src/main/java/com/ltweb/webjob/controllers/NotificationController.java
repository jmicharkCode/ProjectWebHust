package com.ltweb.webjob.controllers;

import com.ltweb.webjob.dtos.*;
import com.ltweb.webjob.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/v1")
@CrossOrigin("*")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @PostMapping("/notify/createForCompany")
    public ResponseEntity<?> createNotifyForCompany (@RequestBody NotifyDto dto){
        return notificationService.createNotiForCompany(dto);
    }

    @PutMapping("/notify/setNotifyForStatus")
    public ResponseEntity<?> setNotiForStatus (int jobAppId) {
        return notificationService.setNotiForStatus(jobAppId);
    }

    @PutMapping("/notify/getNotifyForStatus")
    public ResponseEntity<NotifyStatusDto> getNotiFy (@PathVariable int id){
        return new ResponseEntity<>(notificationService.getNotiForStatus(id), HttpStatus.OK);
    }

}
