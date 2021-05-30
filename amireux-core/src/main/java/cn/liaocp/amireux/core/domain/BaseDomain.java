package cn.liaocp.amireux.core.domain;

import cn.liaocp.amireux.core.util.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDomain implements Serializable {

    @Column(nullable = false, length = 36, updatable = false)
    @CreatedBy
    @JsonIgnore
    private String createUser;

    @Column(nullable = false)
    @JsonIgnore
    private Instant createTime;

    @JsonIgnore
    private Instant updateTime;

    @Column(nullable = false, length = 36, updatable = true)
    @JsonIgnore
    private String updateUser;

    @PrePersist
    protected void prePersist() {
        Instant now = DateTimeUtil.now();
        this.createTime = now;
        this.updateTime = now;
        this.createUser = "temp user";
        this.updateUser = "temp user";
        // TODO Assign values to createUser and updateUser
    }

    @PreUpdate
    protected void preUpdate() {
        this.updateTime = DateTimeUtil.now();
        this.updateUser = "temp user";
        // TODO Assign values and updateUser
    }

    @PreRemove
    protected void preRemove() {
        this.updateTime = DateTimeUtil.now();
        this.updateUser = "temp user";
        // TODO Assign values and updateUser
    }

}
