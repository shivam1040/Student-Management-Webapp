package com.sts.component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;


public class SearchSpecifications<T> implements Specification<T> {
	private SearchCriteria criteria;

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		switch (criteria.getOperation()) {
        case EQUALITY:
            return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
        case NEGATION:
            return criteriaBuilder.notEqual(root.get(criteria.getKey()), criteria.getValue());
        case LESS_THAN:
            return criteriaBuilder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
        case GREATER_THAN:
            return criteriaBuilder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
        default:
            return null;
    }
	}

	public SearchCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	public SearchSpecifications(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	public SearchSpecifications() {
		super();
		// TODO Auto-generated constructor stub
	}

	
    
}
