package com.codenuance.kodeego.repository;

import java.util.Collection;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.codenuance.kodeego.model.Code;
import com.codenuance.kodeego.model.CodeType;

@Repository
public class CodeRepositoryImpl extends CrudOperations<Code> implements
		CodeRepository {

	public CodeType findCodeType(String codeType) {
		return getEntityManager().find(CodeType.class, codeType);
	}

	public Collection<CodeType> findAllCodeTypes() {
		TypedQuery<CodeType> query = getEntityManager().createQuery(
				"from CodeType", CodeType.class);
		return query.getResultList();
	}

	public Collection<Code> findByCodeType(Collection<String> types) {

		TypedQuery<Code> query = getEntityManager().createNamedQuery(
				Code.FIND_BY_TYPES, Code.class);
		query.setParameter("types", types);

		return query.getResultList();
	}
}
