package cn.liaocp.amireux.base.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cn.liaocp.amireux.core.domain.TreeDomain;

/**
 * 部门表
 *
 * @author Chunping.Liao
 */
@Data
@Entity
@Table(name = "system_dept")
@EqualsAndHashCode(callSuper=false)
public class Dept extends BaseTreeDomain<Dept> implements TreeDomain<Dept> {

	private static final long serialVersionUID = 1780331509303964178L;

	@NotBlank(message = "部门名称不能为空")
    @Column(nullable = false)
    private String title;

    /**
     * 部门标识
     */
    @NotBlank(message = "部门标识不能为空")
    @Column(nullable = false)
    private String keyword;

    /**
     * 部门排序
     */
    @NotNull(message = "序号不能为空")
    @Column(nullable = false)
    private Integer sortNum;

    /**
     * 部门状态
     */
    @NotNull(message = "部门状态不能为空")
    @Column(name = "is_enable", columnDefinition = "tinyint", length = 1, nullable = false)
    private Boolean enable;

}
