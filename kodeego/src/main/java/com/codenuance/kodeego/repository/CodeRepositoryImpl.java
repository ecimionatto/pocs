package com.codenuance.kodeego.repository;

import org.springframework.stereotype.Repository;

import com.codenuance.kodeego.model.Code;
import com.codenuance.kodeego.model.CodeType;

@Repository
public class CodeRepositoryImpl extends CrudOperations<Code> implements
		CodeRepository {

	public CodeType findCodeType(String codeType) {
		return getEntityManager().find(CodeType.class, codeType);
	}
}
