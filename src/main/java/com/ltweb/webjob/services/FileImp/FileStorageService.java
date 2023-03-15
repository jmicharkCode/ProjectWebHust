package com.ltweb.webjob.services.FileImp;

import com.ltweb.webjob.dtos.StoreDto;
import com.ltweb.webjob.entities.FileDB;
import com.ltweb.webjob.repositories.FileDBRepository;
import com.ltweb.webjob.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService implements FileService {

    @Autowired
    private FileDBRepository fileDBRepository;

    @Override
    public FileDB store(StoreDto storeDto) throws IOException {
        String fileName = StringUtils.cleanPath(storeDto.getFile().getOriginalFilename());
        FileDB FileDB = new FileDB(storeDto.getUserId(),fileName, storeDto.getFile().getContentType(), storeDto.getFile().getBytes());

        return fileDBRepository.save(FileDB);
    }

    @Override
    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    @Override
    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    @Override
    public Stream<FileDB> getFilesByTypeAndUserId(String type, int userId) {
        return fileDBRepository.findAllByTypeAndUserId(type,userId).stream();
    }

}
