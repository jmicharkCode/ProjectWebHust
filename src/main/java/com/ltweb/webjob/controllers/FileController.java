package com.ltweb.webjob.controllers;

import com.ltweb.webjob.dtos.ResponseMessage;
import com.ltweb.webjob.dtos.StoreDto;
import com.ltweb.webjob.services.FileImp.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth/file")
@CrossOrigin(origins = "*")
public class FileController {
    @Autowired
    private FileStorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@ModelAttribute StoreDto storeDto) {
        String message = new String("");
        try {
            storageService.store(storeDto);
            message = "Uploaded the file successfully: " + storeDto.getFile().getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + storeDto.getFile().getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}


