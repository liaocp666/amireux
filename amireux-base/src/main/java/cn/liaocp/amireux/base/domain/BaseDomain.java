package cn.liaocp.amireux.base.domain;

import cn.liaocp.amireux.core.domain.AuditorDomain;
import cn.liaocp.amireux.core.domain.IdDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Chunping.Liao
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@DynamicUpdate
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseDomain implements Serializable, IdDomain, AuditorDomain {

    private static final long serialVersionUID = 3166808065275155473L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

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
    @Column(nullable = false, length = 36)
    @JsonIgnore
    private String updateUser;
    @Transient
    @JsonIgnore
    private Integer pageNo = 0;

    @Transient
    @JsonIgnore
    private Integer pageSize = 10;

    @SuppressWarnings("unused")
    public void setPageNo(Integer pageNo) {
        if (pageNo > 0) {
            this.pageNo = pageNo - 1;
            return;
        }
        this.pageNo = pageNo;
    }

    @Transient
    private Map<String, Object> expand = new HashMap<>();

    protected void put(String key, Object value) {
        expand.put(key, value);
    }

    protected Object get(String key) {
        return expand.get(key);
    }
}
