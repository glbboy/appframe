package com.cnframe.repository;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cnframe.model.sys.SysMenu;
import com.cnframe.model.sys.SysRole;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unchecked")
@Repository
public class SysPrivilegeRepositoryImpl  implements SysPrivilegeRepositoryCustom{
	private final static String PRIVILEGE_USER="USER";
	private final static String PRIVILEGE_ROLE="ROLE";
	private final static String PRIVILEGE_MENU="MENU";
	@PersistenceContext 
	private EntityManager em;
	
	public List<SysRole> findRoleListByUserCode(String userCode) {
		String sql = "select a.* from sys_role a,sys_privilege b "
				+ " where a.rolecode=b.privilegemastervalue "
				+ " and b.privilegemaster=? and b.privilegemastervalue=? and b.privilegeaccess=? ";
		Query query =  em.createNativeQuery(sql,SysRole.class);
		query.setParameter(0, PRIVILEGE_USER);
		query.setParameter(1, userCode);
		query.setParameter(2, PRIVILEGE_ROLE);
        return query.getResultList();
	}
	
	public List<SysMenu> findMenuListByRoleCode(String roleCode) {
		String sql = "select a.* from sys_Menu a,sys_privilege b "
				+ " where a.rolecode=b.privilegemastervalue "
				+ " and b.privilegemaster=? and b.privilegemastervalue=? and b.privilegeaccess=? ";
		Query query =  em.createNativeQuery(sql,SysMenu.class);
		query.setParameter(0, PRIVILEGE_ROLE);
		query.setParameter(1, roleCode);
		query.setParameter(2, PRIVILEGE_MENU);
        return  (List<SysMenu>)query.getResultList();
	}

}
