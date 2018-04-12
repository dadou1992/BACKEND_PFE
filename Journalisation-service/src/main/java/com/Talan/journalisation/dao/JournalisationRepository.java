package com.Talan.journalisation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Talan.journalisation.entities.Journalisation;

public interface JournalisationRepository extends JpaRepository<Journalisation, Long>{

}
