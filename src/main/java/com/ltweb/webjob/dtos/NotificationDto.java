package com.ltweb.webjob.dtos;

import com.ltweb.webjob.entities.StatusNoti;
import com.ltweb.webjob.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    int id;
    UserDto sender;
    UserDto receiver;
    String link;
    String Description;
    StatusNoti status;
}
