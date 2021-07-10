package cn.liaocp.amireux.core.domain;

import java.util.List;

/**
 * 树形结构
 *
 * @author Chunping.Liao
 */
public interface TreeDomain<T> extends IdDomain {

	String ROOT_NODE = "0";

	void setParentId(String parentId);

	String getParentId();

	List<T> getChildren();

	void setChildren(List<T> children);

}
