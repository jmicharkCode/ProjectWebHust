package com.ltweb.webjob.services;

import com.ltweb.webjob.dtos.NotifyDto;
import com.ltweb.webjob.dtos.NotifyStatusDto;
import com.ltweb.webjob.entities.Candidate;
import com.ltweb.webjob.entities.Company;
import com.ltweb.webjob.entities.JobApplication;
import com.ltweb.webjob.entities.Notification;
import com.ltweb.webjob.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    private NotificationRepository notiRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    ModelMapper modelMapper;

    private Notification convertToEntity(NotifyDto dto) {
        Notification notify = modelMapper.map(dto, Notification.class);
        return notify;
    }

    private NotifyStatusDto convertToDto(Notification entity) {
        NotifyStatusDto dto = modelMapper.map(entity, NotifyStatusDto.class);
        return dto;
    }

    @Override
    public ResponseEntity<?> createNotiForCompany(NotifyDto dto) {
        try {
            Notification notify = convertToEntity(dto);

            Optional<Candidate> optCandidate =  candidateRepository.findById(dto.getCandidateId());
            Optional<Company> optCompany =  companyRepository.findById(dto.getCompanyId());

            if(!optCandidate.isPresent() || !optCompany.isPresent()) {
                return new ResponseEntity<>("Khong co id", HttpStatus.BAD_REQUEST);
            }
            else {
                notify.setCandidate(optCandidate.get());
                notify.setCompany(optCompany.get());
                notify.setStatus(1);
                notiRepository.save(notify);
                return new ResponseEntity<>("Success", HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> setNotiForStatus(int jobAppId) {

        // tim notify
        int notiId = 0;

        Optional<JobApplication> jobAppOpt =  jobApplicationRepository.findById(jobAppId);
        int idCandidate = jobAppOpt.get().getCandidate().getId();
        List<Notification> notiList = candidateRepository.findById(idCandidate).get().getNotifies();

        int idJob = jobAppOpt.get().getJob().getId();
        int idCompany = jobRepository.findById(idJob).get().getCompany().getId();

        for(Notification noti: notiList) {
            if(noti.getCandidate().getId() == idCandidate && noti.getCompany().getId() == idCompany) {
                notiId = noti.getId();
                break;
            }
        }
        Notification noti = notiRepository.findById(notiId).get();
        if(notiId == 0){
            return new ResponseEntity<>("notification ID = 0", HttpStatus.BAD_REQUEST);
        }
        switch(jobAppOpt.get().getStatus()) {
            case 1:
                noti.setDescription("Ban da ung tuyen thanh cong");
                break;
            case 2:
                noti.setDescription("Hay cho lich phong van");
                break;
            case 3:
                noti.setDescription("Ban co lich phong van");
                break;
            case 4:
                noti.setDescription("Vui long cho phan hoi");
                break;
            case 5:
                noti.setDescription("Ban da duoc tuyen");
                break;
            case 6:
                noti.setDescription("Da huy don ung tuyen");
                break;
        }
        notiRepository.save(noti);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @Override
    public NotifyStatusDto getNotiForStatus(int jobAppId) {

        // tim notify
        int notiId = 0;

        Optional<JobApplication> jobAppOpt =  jobApplicationRepository.findById(jobAppId);
        int idCandidate = jobAppOpt.get().getCandidate().getId();
        List<Notification> notiList = candidateRepository.findById(idCandidate).get().getNotifies();

        int idJob = jobAppOpt.get().getJob().getId();
        int idCompany = jobRepository.findById(idJob).get().getCompany().getId();

        for(Notification noti: notiList) {
            if(noti.getCandidate().getId() == idCandidate && noti.getCompany().getId() == idCompany) {
                notiId = noti.getId();
                break;
            }
        }
        Notification noti = notiRepository.findById(notiId).get();
       /* if(notiId == 0){
            return new ResponseEntity<>("notification ID = 0", HttpStatus.BAD_REQUEST);
        }*/


        //
        Notification notiGet = notiRepository.findById(notiId).get();
        NotifyStatusDto dto = convertToDto(notiGet);
        return dto;

    }

    @Override
    public ResponseEntity<?> createNotiForCandiate(NotifyDto dto) {
        Notification notify = convertToEntity(dto);
        return null;
    }
}
