package cn.liaocp.amireux.base.service.impl;

import java.util.List;

import cn.liaocp.amireux.base.domain.Permission;
import cn.liaocp.amireux.core.util.TreeUtil;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import cn.liaocp.amireux.base.domain.Dept;
import cn.liaocp.amireux.base.repository.BaseRepository;
import cn.liaocp.amireux.base.repository.DeptRepository;
import cn.liaocp.amireux.base.service.DeptService;
import cn.liaocp.amireux.base.service.UserDeptService;
import lombok.RequiredArgsConstructor;

/**
 * @author Chunping.Liao
 */
@Service
@RequiredArgsConstructor
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptService {

    private final DeptRepository deptRepository;

	private final UserDeptService userDeptService;

    @Override
    public BaseRepository<Dept> getBaseDomainRepository() {
        return deptRepository;
    }

	@Override
	public List<Dept> findByUserId(String userId) {
		return findAllById(userDeptService.findDeptIdByUserId(userId));
	}

    @Override
    public Page<Dept> page(Dept dept) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("parentId", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains());
        PageRequest pageRequest = PageRequest.of(dept.getPageNo(), dept.getPageSize(),
                Sort.by(Sort.Order.asc("sortNum"), Sort.Order.asc("createTime")));
        Page<Dept> page = super.page(Example.of(dept), pageRequest);
        page.getContent().forEach(e -> TreeUtil.childrenTree(e, findAll()));
        return page;
    }

    @Override
    public List<Dept> findAll() {
        return deptRepository.findAll(Sort.by(Sort.Order.asc("sortNum"), Sort.Order.asc("createTime")));
    }
}
