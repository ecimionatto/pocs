package com.codenuance.kodeego.repository;

import com.codenuance.kodeego.model.Code;
import com.codenuance.kodeego.model.CodeType;

public interface CodeRepository extends CrudOperatable<Code> {
	CodeType findCodeType(String codeType);
}