package com.sts.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sts.component.SearchCriteria;
import com.sts.component.SearchCriteriaParser;
import com.sts.component.SearchSpecification;
import com.sts.entity.Student;
import com.sts.repository.StudentRepository;

@Service
public class MainService {
	
	 @Autowired
	    private SearchCriteriaParser searchCriteriaParser;

	    @Autowired
	    private StudentRepository mainRepository;

	public List<Student> search(String searchString, Integer pageSize, Integer pageIndex) {
		List<SearchCriteria> searchCriteria = searchCriteriaParser.parse(searchString);
        List<SearchSpecification> specList = searchCriteria.stream().map(criterion -> new SearchSpecification(criterion)).collect(Collectors.toList());
        Specification<Student> specs = andSpecification(specList).orElseThrow(() -> new IllegalArgumentException("No criteria provided"));
        List<Sort> sortList = generateSortList(searchCriteria);
        Sort sort = andSort(sortList).orElse(Sort.unsorted());
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
        Page<Student> page = mainRepository.findAll(specs, pageable);
        return page.getContent();
	}

	private <T, V extends Sort> Optional<Sort> andSort(List<V> criteria) {
		
		 Iterator<V> itr = criteria.iterator();
	        if (itr.hasNext()) {
	            Sort sort = (itr.next());
	            while (itr.hasNext()) {
	                sort = sort.and(itr.next());
	            }
	            return Optional.of(sort);
	        }
	        return Optional.empty();
	}

	private List<Sort> generateSortList(List<SearchCriteria> criteria) {
		List<Sort> res=new ArrayList<Sort>();
		
		for(SearchCriteria criterion:criteria) {
		    switch (criterion.getOperation()) {
            case SORT_ASC:
                res.add(Sort.by(org.springframework.data.domain.Sort.Order.asc(criterion.getKey())));
            case SORT_DESC:
                res.add(Sort.by(org.springframework.data.domain.Sort.Order.desc(criterion.getKey())));
            default:
        }
		}
		
		return res;
	}

	private <T, V extends Specification<T>> Optional<Specification<T>> andSpecification(List<V> criteria) {
		Iterator<V> itr = criteria.iterator();
        if (itr.hasNext()) {
            Specification<T> spec = Specification.where(itr.next());
            while (itr.hasNext()) {
                spec = spec.and(itr.next());
            }
            return Optional.of(spec);
        }
        return Optional.empty();
	}

}
