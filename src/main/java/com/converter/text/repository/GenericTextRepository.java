package com.converter.text.repository;

import com.converter.text.model.GenericText;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericTextRepository extends JpaRepository<GenericText,Integer> {
}
