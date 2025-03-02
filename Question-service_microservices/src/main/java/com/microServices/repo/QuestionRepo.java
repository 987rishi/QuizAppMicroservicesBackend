package com.microServices.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microServices.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer>{

	List<Question> getQuestionsByCategory(String cat);

	@Query(value="SELECT q.id from question q where q.category=:category order by RANDOM() LIMIT :numQ",nativeQuery=true)
	List<Integer> findByCategoryRandomQuestions(String category, int numQ);

}
