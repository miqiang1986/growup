package com.miqiang.baoding.service;

import com.miqiang.baoding.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.miqiang.baoding.util.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miqiang
 * @since 2022-08-16
 */
public interface IPermissionService extends IService<Permission> {

    Result<?> getPupilTree();
    Result<?> getParentTree();
    Result<?> getAdminTree();

}
