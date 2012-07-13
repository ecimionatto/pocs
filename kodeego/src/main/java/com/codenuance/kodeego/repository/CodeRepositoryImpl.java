package com.codenuance.kodeego.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

		List<Code> resultList = query.getResultList();
		ArrayList<Code> filteredCodes = new ArrayList<Code>();
		for (Code code : resultList) {
			boolean allTypesPresent = true;
			for (String type : types) {
				if (!isCodeTypeAssigned(code, type)) {
					allTypesPresent = false;
					break;
				}
			}
			if (allTypesPresent) {
				filteredCodes.add(code);
			}
		}

		return filteredCodes;
	}

	private boolean isCodeTypeAssigned(Code code, String type) {
		for (CodeType codeType : code.getTypes()) {
			if (type.equals(codeType.getType())) {
				return true;
			}
		}
		return false;
	}
	
}
