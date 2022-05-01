package com.malay.emr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.malay.emr.entities.SavedTermsEntity;
import com.malay.emr.entities.TermTypesEntity;

@Repository
public interface SavedTermsDAO extends JpaRepository<SavedTermsEntity, Integer>{

	@Query("select t from SavedTermsEntity t where t.term = ?1 and t.type = ?2 ")
	SavedTermsEntity findOneByTermAndType(String term,TermTypesEntity type);
	
}
