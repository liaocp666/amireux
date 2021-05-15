package cn.liaocp.amireux.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
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

    /**
     * record save data
     */
    @PostPersist
    private void saveData() {
        this.createTime = Instant.now();
        this.updateTime = Instant.now();
        // TODO Assign values to createUser and updateUser
    }

    private void updateData() {
        this.updateTime = Instant.now();
        // TODO Assign values and updateUser
    }

}
