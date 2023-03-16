package com.converter.text.repository;

import com.converter.text.model.ConvertedText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvertedTextRepository extends JpaRepository<ConvertedText, Integer> {

}
