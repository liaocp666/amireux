package cn.liaocp.amireux.core.domain;

import cn.liaocp.amireux.core.util.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
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

    @Column(nullable = false, length = 36, updatable = false)
    @JsonIgnore
    private String updateUser;

    @PostPersist
    private void PostPersist() {
        Instant now = DateTimeUtil.now();
        this.createTime = now;
        this.updateTime = now;
        // TODO Assign values to createUser and updateUser
    }

    @PostUpdate
    private void updateData() {
        this.updateTime = DateTimeUtil.now();
        // TODO Assign values and updateUser
    }

    @PostRemove
    private void preRemove() {
        this.updateTime = DateTimeUtil.now();
        // TODO Assign values and updateUser
    }

}
