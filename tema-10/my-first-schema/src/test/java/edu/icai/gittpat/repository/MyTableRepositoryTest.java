package edu.icai.gittpat.repository;

import edu.icai.gittpat.model.MyTable;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.BDDAssertions.then;

@Transactional
@SpringBootTest
public class MyTableRepositoryTest {

	@Autowired
	private MyTableRepository myTableRepository;

	private MyTable create() {
		LocalDate date = LocalDate.parse("2016-08-16");
		LocalTime time = LocalTime.parse("10:15:45");
		String field1 = "Pedro";
		BigDecimal field2 = new BigDecimal("10.5");
		MyTable element = new MyTable(null, field1,field2, date, time,true);

		return element;
	}

	@Test
	public void given_repository_when_add_then_Ok() {

		//Given
		var counterBefore = myTableRepository.count();
		var element = create();

		//When
		myTableRepository.save(element);

		//Then
		var counterAfter = myTableRepository.count();
		then(counterAfter).isEqualTo(++counterBefore);
	}

}
