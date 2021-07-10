package cn.liaocp.amireux.base.domain;

import java.util.List;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonInclude;

import cn.liaocp.amireux.core.domain.TreeDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@DynamicUpdate
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTreeDomain<T extends TreeDomain<T>> extends BaseDomain {

	private static final long serialVersionUID = 6929603476782128159L;

	private String parentId;

	@Transient
	private List<T> children;

}
