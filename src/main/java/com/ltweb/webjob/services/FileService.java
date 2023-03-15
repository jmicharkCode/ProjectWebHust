package com.ltweb.webjob.services;

import com.ltweb.webjob.dtos.StoreDto;
import com.ltweb.webjob.entities.FileDB;

import java.io.IOException;
import java.util.stream.Stream;

public interface FileService {
    FileDB store(StoreDto storeDto) throws IOException;
    FileDB getFile(String id);
    public Stream<FileDB> getAllFiles();
    Stream<FileDB> getFilesByTypeAndUserId(String type, int userId);
}
