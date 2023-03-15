/*
package com.ltweb.webjob.mappers;

import com.ltweb.webjob.dtos.JobDto;
import com.ltweb.webjob.dtos.NotificationDto;
import com.ltweb.webjob.entities.Job;
import com.ltweb.webjob.entities.Notification;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationMapper {

    public static NotificationDto toDto(Notification entity) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setId(entity.getId());
        notificationDto.setSender(UserMapper.toDto(entity.getSender()));
        notificationDto.setReceiver(UserMapper.toDto(entity.getReceiver()));
        notificationDto.setDescription(entity.getDescription());
        notificationDto.setStatus(entity.getStatus());
        notificationDto.setLink(entity.getLink());

        return notificationDto;
    }
    public static Notification toEntity(NotificationDto jobDto) {
        Notification entity = new Notification();
        entity.setId(jobDto.getId());
        entity.setCandidate(CandidateMapper.toEntity(jobDto.getSender()));
        entity.setReceiver(UserMapper.toEntity(jobDto.getReceiver()));
        entity.setDescription(jobDto.getDescription());
        entity.setStatus(jobDto.getStatus());
        entity.setLink(jobDto.getLink());
        return entity;
    }

    public static List<NotificationDto> toDtoList(List<Notification> entities){
        return entities.stream().map(NotificationMapper::toDto).collect(Collectors.toList());
    }

    public static List<NotificationDto> toDtoList(Iterable<Notification> entities) {
        List<NotificationDto> notificationDto = new LinkedList<>();
        entities.forEach(e -> notificationDto.add(toDto(e)));
        return notificationDto;
    }
}
*/
