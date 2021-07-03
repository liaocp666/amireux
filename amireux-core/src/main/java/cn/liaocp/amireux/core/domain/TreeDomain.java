package cn.liaocp.amireux.core.domain;

import java.util.List;

/**
 * 树形结构
 *
 * @author Chunping.Liao
 * @date 2021/7/3
 */
public interface TreeDomain<T extends TreeDomain> extends IdDomain {

    String ROOT_NODE = "0";

    void setParentId(String parentId);

    String getParentId();

    List<T> getChildren();

    void setChildren(List<T> children);

}
