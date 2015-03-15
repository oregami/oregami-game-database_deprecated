package org.oregami.entities.user;

import org.oregami.entities.BaseEntityUUID;
import org.oregami.entities.KeyObjects.RoleKey;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Role extends BaseEntityUUID {
	
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
