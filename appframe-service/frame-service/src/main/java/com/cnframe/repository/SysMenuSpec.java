package com.cnframe.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.cnframe.model.sys.SysMenu;

public class SysMenuSpec implements Specification<SysMenu> {
	private final SysMenu example;

	public SysMenuSpec(SysMenu example) {
		this.example = example;
	}

	@Override
	public Predicate toPredicate(Root<SysMenu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (StringUtils.isNotBlank(example.getAppCode())) {
			predicates.add(cb.equal(root.get("appCode").as(String.class), example.getAppCode() ));
		}
		if (StringUtils.isNotBlank(example.getMenuCode())) {
			predicates.add(cb.equal(root.get("menuCode").as(String.class), example.getMenuCode()));
		}
		
		//添加排序的功能  
		List<Order> orders = new ArrayList<Order>();
		Order order = cb.asc(root.get("appCode").as(String.class));
		orders.add(order);
		order = cb.asc(root.get("pmenuCode").as(String.class));
		orders.add(order);
		order = cb.asc(root.get("sort").as(Integer.class));
		orders.add(order);
		query.orderBy(orders);
		Predicate[] predicate = new Predicate[predicates.size()];
		query.where(cb.and(predicates.toArray(predicate)));
		return query.getRestriction();
	}

}
