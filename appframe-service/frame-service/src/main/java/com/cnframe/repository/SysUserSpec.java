package com.cnframe.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.cnframe.model.sys.SysUser;

public class SysUserSpec implements Specification<SysUser> {
	private final SysUser example;

	public SysUserSpec(SysUser example) {
		this.example = example;
	}

	@Override
	public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (StringUtils.isNotBlank(example.getUserCode())) {
			predicates.add(cb.like(root.get("userCode").as(String.class), "%" + example.getUserCode() + "%"));
		}
		if (StringUtils.isNotBlank(example.getUserName())) {
			predicates.add(cb.like(root.get("userName").as(String.class), "%" + example.getUserName() + "%"));
		}
		if (StringUtils.isNotBlank(example.getDeptCode())) {
			predicates.add(cb.equal(root.get("deptCode").as(String.class), example.getDeptCode()));
		}
		if (StringUtils.isNotBlank(example.getEmployeeCode())) {
			predicates.add(cb.equal(root.get("employeeCode").as(String.class), example.getEmployeeCode()));
		}
		if (StringUtils.isNotBlank(example.getUserType())) {
			predicates.add(cb.equal(root.get("userType").as(String.class), example.getUserType()));
		}
		//添加排序的功能  
        query.orderBy(cb.asc(root.get("userCode").as(String.class))); 
		Predicate[] predicate = new Predicate[predicates.size()];
		query.where(cb.and(predicates.toArray(predicate)));
		return query.getRestriction();
	}

}
