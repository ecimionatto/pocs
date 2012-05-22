package com.codenuance.kodeego.repository;

import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.codenuance.kodeego.model.CodeType;

@Transactional
public class CodeRepositoryTest extends RepositoryIntegrationTest {

	@Autowired
	CodeRepository codeRepository;

	@Test
	public void shoudFindAllCodeTypes() {

		CodeType codeType1 = new CodeType();
		codeType1.setType("codetest1");
		codeRepository.getEntityManager().persist(codeType1);

		CodeType codeType2 = new CodeType();
		codeType2.setType("codetest2");
		codeRepository.getEntityManager().persist(codeType2);

		CodeType codeType3 = new CodeType();
		codeType3.setType("codetest3");
		codeRepository.getEntityManager().persist(codeType3);

		Collection<CodeType> findAllCodeTypes = codeRepository
				.findAllCodeTypes();

		assertThat(findAllCodeTypes,
				JUnitMatchers.hasItems(codeType1, codeType2, codeType3));

	}

}
