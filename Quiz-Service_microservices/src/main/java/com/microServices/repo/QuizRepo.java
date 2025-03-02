package com.microServices.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microServices.model.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Integer>{

}
