package com.cnframe.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
@Transactional(readOnly=true)
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
	boolean support(String modelType);
	
	public List<Object[]> listBySQL(String sql);
	@Transactional
    public void updateBySql(String sql,Object...args);
    @Transactional
    public void updateByHql(String hql,Object...args);
}
