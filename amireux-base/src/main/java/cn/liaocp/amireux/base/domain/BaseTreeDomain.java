package cn.liaocp.amireux.base.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonInclude;

import cn.liaocp.amireux.core.domain.TreeDomain;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode(callSuper=false)
public abstract class BaseTreeDomain<T extends TreeDomain<T>> extends BaseDomain {

	private static final long serialVersionUID = 6929603476782128159L;

	@NotBlank(message = "上级不能为空")
	@Column(nullable = false)
	private String parentId = TreeDomain.ROOT_NODE;

	@Transient
	private List<T> children;

}
