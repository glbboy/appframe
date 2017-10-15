package com.cnframe.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class BaseRepositoryImpl <T, ID extends Serializable> extends SimpleJpaRepository<T,ID> implements BaseRepository<T,ID> {
	private final Class<T> domainClass;
	public final EntityManager entityManager;
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.domainClass = domainClass;
        this.entityManager = entityManager;
    }

    @Override
    public boolean support(String modelType) {
        return domainClass.getName().equals(modelType);
    }
    @SuppressWarnings("unchecked")
	@Override
    public List<Object[]> listBySQL(String sql) {
        return entityManager.createNativeQuery(sql).getResultList();
    }
    
    @Override
	public void updateBySql(String sql,Object...args) {
    	Query query = entityManager.createNativeQuery(sql);
    	int i = 0;
    	for(Object arg:args) {
    		query.setParameter(++i,arg);
    	}
    	query.executeUpdate();
	}

	@Override
	public void updateByHql(String hql,Object...args) {
    	Query query = entityManager.createQuery(hql);
    	int i = 0;
    	for(Object arg:args) {
    		System.out.println(arg);
    		query.setParameter(++i,arg);
    	}
    	query.executeUpdate();
	}
    
}
