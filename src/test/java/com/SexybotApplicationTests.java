package com;

import com.converter.text.model.ConvertedText;
import com.converter.text.repository.ConvertedTextRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SexybotApplicationTests {

	@Autowired
	ConvertedTextRepository convertedTextRepository;

	@Test
	void contextLoads() {
		System.out.println(convertedTextRepository.count());
		long level = convertedTextRepository.findAll()
				.stream()
				.map(ConvertedText::getData)
				.filter(it -> it.containsKey("level"))
				.count();
		System.out.println(level);
	}

}
