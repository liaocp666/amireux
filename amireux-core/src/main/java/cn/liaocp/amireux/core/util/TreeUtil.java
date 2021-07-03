package cn.liaocp.amireux.core.util;

import cn.liaocp.amireux.core.domain.TreeDomain;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chunping.Liao
 * @date 2021/7/3
 */
public class TreeUtil {

    /**
     * Build tree structure data
     *
     * @param all Data to be built
     * @param <T> The implementation classes of {@link TreeDomain}
     * @return tree structure data
     */
    public static <T extends TreeDomain> List<T> tree(List<T> all) {
        List<T> parentPermission = all.stream()
                .filter(e -> StringUtils.equals(TreeDomain.ROOT_NODE, e.getParentId()))
                .map(e -> childrenTree(e, all))
                .collect(Collectors.toList());
        return parentPermission;
    }

    /**
     * Build children
     *
     * @param parent Parent
     * @param all    Data to be built
     * @param <T>    The implementation classes of {@link TreeDomain}
     * @return Parent with collection of children
     */
    public static <T extends TreeDomain> T childrenTree(T parent, List<T> all) {
        List<TreeDomain> children = new LinkedList<>();
        all.forEach(e -> {
            if (parent.getId().equals(e.getParentId())) {
                childrenTree(e, all);
                children.add(e);
            }
        });
        if (!children.isEmpty()) {
            parent.setChildren(children);
        }
        return parent;
    }

}
