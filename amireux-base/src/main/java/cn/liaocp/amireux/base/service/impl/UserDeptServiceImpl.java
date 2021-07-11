package cn.liaocp.amireux.base.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.liaocp.amireux.base.domain.UserDept;
import cn.liaocp.amireux.base.repository.BaseRepository;
import cn.liaocp.amireux.base.repository.UserDeptRepository;
import cn.liaocp.amireux.base.service.UserDeptService;
import lombok.RequiredArgsConstructor;

/**
 * @author Chunping.Liao
 */
@Service
@RequiredArgsConstructor
public class UserDeptServiceImpl extends BaseServiceImpl<UserDept> implements UserDeptService {

	private final UserDeptRepository userDeptRepository;

	@Override
	public BaseRepository<UserDept> getBaseDomainRepository() {
		return userDeptRepository;
	}

	@Override
	public List<String> findDeptIdByUserId(String userId) {
		return userDeptRepository.findDeptIdByUserId(userId);
	}


}
