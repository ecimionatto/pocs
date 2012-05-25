package com.codenuance.kodeego.repository;

import java.util.Collection;

import com.codenuance.kodeego.model.Code;
import com.codenuance.kodeego.model.CodeType;

public interface CodeRepository extends CrudOperatable<Code> {
	CodeType findCodeType(String codeType);

	Collection<CodeType> findAllCodeTypes();

	Collection<Code> findByCodeType(Collection<String> types);
}