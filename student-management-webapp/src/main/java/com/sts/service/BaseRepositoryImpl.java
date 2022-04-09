package com.sts.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sts.component.SearchCriteria;
import com.sts.component.SearchCriteriaParser;
import com.sts.component.SearchSpecifications;
import com.sts.entity.Base;
import com.sts.repository.BaseRepository;

@Service
@Transactional
public abstract class BaseRepositoryImpl<T extends Base, ID extends Serializable> implements BaseService<T, ID> {
	
	@Autowired
	private BaseRepository<T, ID> abstractBaseRepository;
	
	@Autowired
    private SearchCriteriaParser searchCriteriaParser;

	@Override
	public T save(T entity) {
		return (T) abstractBaseRepository.save(entity);
	}

	@Override
	public List<T> findAll() {
		 return abstractBaseRepository.findAll();
	}
	
	

	@Override
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		return abstractBaseRepository.findAll(spec, pageable);
	}

	@Override
	public Optional<T> findById(ID entityId) {
		return abstractBaseRepository.findById(entityId);
	}

	@Override
	public T update(T entity) {
		return (T) abstractBaseRepository.save(entity);
	}

	@Override
	public T updateById(T entity, ID entityId) {
		Optional<T> optional = abstractBaseRepository.findById(entityId);
        if(optional.isPresent()){
            return (T) abstractBaseRepository.save(entity);
        }else{
            return null;
        }
	}

	@Override
	public void delete(T entity) {
		  abstractBaseRepository.delete(entity);
		
	}

	@Override
	public void deleteById(ID entityId) {
		   abstractBaseRepository.deleteById(entityId);
		
	}
	
	public List<T> search(String searchString, Integer pageSize, Integer pageIndex) {
		List<SearchCriteria> searchCriteria = searchCriteriaParser.parse(searchString);
        List<SearchSpecifications<T>> specList = searchCriteria.stream().map(criterion -> new SearchSpecifications<T>(criterion)).collect(Collectors.toList());
        Specification<T> specs = andSpecification(specList).orElseThrow(() -> new IllegalArgumentException("No criteria provided"));
        List<Sort> sortList = generateSortList(searchCriteria);
        Sort sort = andSort(sortList).orElse(Sort.unsorted());
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
        Page<T> page = abstractBaseRepository.findAll(specs, pageable);
        return page.getContent();
	}
	
	private <U, V extends Sort> Optional<Sort> andSort(List<V> criteria) {
		
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

	private <U, V extends Specification<U>> Optional<Specification<U>> andSpecification(List<V> criteria) {
		Iterator<V> itr = criteria.iterator();
       if (itr.hasNext()) {
           Specification<U> spec = Specification.where(itr.next());
           while (itr.hasNext()) {
               spec = spec.and(itr.next());
           }
           return Optional.of(spec);
       }
       return Optional.empty();
	}
	

}
