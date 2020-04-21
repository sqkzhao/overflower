package com.kayz.overflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kayz.overflow.models.Answer;

@Repository
public interface AnswerRepo extends CrudRepository<Answer, Long> {
	List<Answer> findAll();
}
