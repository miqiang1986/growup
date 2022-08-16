package com.miqiang.baoding.service.impl;

import com.miqiang.baoding.entity.Permission;
import com.miqiang.baoding.mapper.PermissionMapper;
import com.miqiang.baoding.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miqiang.baoding.util.Result;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miqiang
 * @since 2022-08-16
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public Result<?> getPupilTree() {
        return null;
    }

    @Override
    public Result<?> getParentTree() {
        return null;
    }

    @Override
    public Result<?> getAdminTree() {
        return null;
    }
}
