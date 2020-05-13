package org.cap.testmgt.dao;

import java.math.BigInteger;

import org.cap.testmgt.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuestionDao extends JpaRepository<Question,BigInteger> {

}
