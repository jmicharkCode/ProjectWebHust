package com.ltweb.webjob.repositories;

import com.ltweb.webjob.entities.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB,String> {
//    @Query(value = "SELECT * FROM file f WHERE f.type = 'image/gif'", nativeQuery = true)
      List<FileDB> findAllByTypeAndUserId (String type,int userId);
}
