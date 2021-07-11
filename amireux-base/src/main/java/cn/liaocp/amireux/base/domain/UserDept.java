package cn.liaocp.amireux.base.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户部门表
 * 
 * @author Chunping.Liao
 */
@Data
@Entity
@Table(name = "system_user_dept")
@EqualsAndHashCode(callSuper = true)
public class UserDept extends BaseDomain {

	private static final long serialVersionUID = -7886822610110207100L;

	/**
	 * @see User#getId()
	 */
	private String userId;

	/**
	 * @see Dept#getId()
	 */
	private String deptId;

}
