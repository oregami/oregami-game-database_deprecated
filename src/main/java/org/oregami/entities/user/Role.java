package org.oregami.entities.user;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.oregami.entities.BaseEntity;
import org.oregami.entities.KeyObjects.RoleKey;

@Entity
public class Role extends BaseEntity {
	
	private static final long serialVersionUID = -4155348505815245710L;

	@Enumerated(EnumType.STRING)
	private RoleKey roleKey;

	public RoleKey getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(RoleKey roleKey) {
		this.roleKey = roleKey;
	}
	
	
	public static Role createRole(RoleKey roleKey) {
		Role role = new Role();
		role.setRoleKey(roleKey);
		return role;
	}
	
	
}
