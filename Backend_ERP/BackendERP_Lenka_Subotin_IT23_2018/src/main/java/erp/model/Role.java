package erp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

/**
 * The persistent class for the role database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@ToString
@Entity
@Table(name="roles", schema ="public")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ROLE_ROLEID_GENERATOR", sequenceName="ROLE_SEQ",  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLE_ROLEID_GENERATOR")
	@Column(name = "roleid")
	@NotNull
	private Integer roleID;

	@Column(name = "rolename")
	@NotNull(message = "Role name is required field!")
	private String roleName;
	
	@JsonIgnore
	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="role", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	private List<User> users;
	
	public Role() {
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String getAuthority() {
		return roleName;
	}
	
}
