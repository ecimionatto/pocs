package com.codenuance.kodeego.repository;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.codenuance.kodeego.model.Code;
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

	@Test
	public void shouldFidnCodeByTypes() {
		CodeType codeType1 = new CodeType();
		codeType1.setType("codetest1");
		codeRepository.getEntityManager().persist(codeType1);

		CodeType codeType2 = new CodeType();
		codeType2.setType("codetest2");
		codeRepository.getEntityManager().persist(codeType2);

		CodeType codeType3 = new CodeType();
		codeType3.setType("codetest3");
		codeRepository.getEntityManager().persist(codeType3);

		Code code1 = new Code();
		HashSet<CodeType> types = new HashSet<CodeType>();
		types.add(codeType1);
		types.add(codeType2);
		types.add(codeType3);
		code1.setTypes(types);
		code1.setCode("code1");
		codeRepository.create(code1);

		Code code2 = new Code();
		HashSet<CodeType> types2 = new HashSet<CodeType>();
		types2.add(codeType1);
		types2.add(codeType3);
		code2.setTypes(types2);
		code2.setCode("code2");
		codeRepository.create(code2);

		Code code3 = new Code();
		HashSet<CodeType> types3 = new HashSet<CodeType>();
		types3.add(codeType2);
		code3.setTypes(types3);
		code3.setCode("code3");
		codeRepository.create(code3);

		codeRepository.getEntityManager().clear();

		
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add(codeType1.getType());
		arrayList.add(codeType2.getType());
		arrayList.add(codeType3.getType());
		
		Collection<Code> codes = codeRepository.findByCodeType(arrayList);

		assertThat(codes.size(), CoreMatchers.equalTo(1));
		assertThat(codes, JUnitMatchers.hasItems(code1));

	}

}
