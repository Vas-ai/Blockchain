package com.malay.emr.repository;
import com.malay.emr.entities.CredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CredentialsDAO extends JpaRepository<CredentialsEntity,Integer>{
    CredentialsEntity findByEmail(String email);
    
}
