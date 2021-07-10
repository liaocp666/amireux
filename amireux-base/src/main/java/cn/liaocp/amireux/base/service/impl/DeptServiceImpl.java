package cn.liaocp.amireux.base.service.impl;

import cn.liaocp.amireux.base.domain.Dept;
import cn.liaocp.amireux.base.repository.BaseRepository;
import cn.liaocp.amireux.base.repository.DeptRepository;
import cn.liaocp.amireux.base.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Chunping.Liao
 */
@Service
@RequiredArgsConstructor
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptService {

    private final DeptRepository deptRepository;

    @Override
    public BaseRepository<Dept> getBaseDomainRepository() {
        return deptRepository;
    }
}
