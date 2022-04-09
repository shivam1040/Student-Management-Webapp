package com.sts.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.sts.entity.Base;

public interface BaseService<T extends Base, ID extends Serializable> {
	public abstract T save(T entity);
    public abstract List<T> findAll(); // you might want a generic Collection if u prefer
    public abstract Page<T> findAll(Specification<T> spec, Pageable pageable);

    public abstract Optional<T> findById(ID entityId);
    public abstract T update(T entity);
    public abstract T updateById(T entity, ID entityId);   
    public abstract void delete(T entity);
    public abstract void deleteById(ID entityId);
}
