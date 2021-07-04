package cn.liaocp.amireux.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@DynamicUpdate
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseDomain implements Serializable, IdDomain {

	private static final long serialVersionUID = 3166808065275155473L;

	@Column(nullable = false, length = 36, updatable = false)
    @CreatedBy
    @JsonIgnore
    private String createUser;

	@CreationTimestamp
    @Column(nullable = false, updatable = false)
    @JsonIgnore
    private Instant createTime;

	@UpdateTimestamp
    @Column(nullable = false)
    @JsonIgnore
    private Instant updateTime;

	@LastModifiedBy
    @Column(nullable = false, length = 36, updatable = true)
    @JsonIgnore
    private String updateUser;

	@Transient
    @JsonIgnore
	private Integer pageNo = 0;

    @Transient
    @JsonIgnore
	private Integer pageSize = 10;

}
