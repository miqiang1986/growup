package com.miqiang.baoding.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.miqiang.baoding.common.PublicMsgs;
import com.miqiang.baoding.entity.Permission;
import com.miqiang.baoding.service.IPermissionService;
import com.miqiang.baoding.util.AssertParam;
import com.miqiang.baoding.util.CacheConstant;
import com.miqiang.baoding.util.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miqiang
 * @since 2022-08-16
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService service;

    @RequestMapping("/save")
    @ApiOperation(value="车间管理-添加", notes="车间管理-添加")
    @PostMapping(value = "/save")
    @CacheEvict(value={CacheConstant.PUPIL_TREE, CacheConstant.PARENT_TREE, CacheConstant.ADMIN_TREE}, allEntries=true)
    public Result<?> save(@RequestBody Permission permission) {
        String errMsg = AssertParam.verificationInterval(permission);
        if (StringUtils.isNotBlank(errMsg)) {
            return Result.error(errMsg);
        }
        String msg;
        if (StringUtils.isNotBlank(permission.getId())) {
            Permission oldData = service.getById(permission.getId());
            if (null == oldData) {
                return Result.error(PublicMsgs.PARAM_NOT_EXIST);
            }
            permission.setUpdateTime(new Date());
            msg = PublicMsgs.UPDATE_SUCCESS;
            errMsg = PublicMsgs.UPDATE_ERROR;
        } else {
            msg = PublicMsgs.SAVE_SUCCESS;
            errMsg = PublicMsgs.SAVE_ERROR;
        }
        // 重复校验
        LambdaQueryWrapper<Permission> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Permission::getName, permission.getName())
                .eq(StringUtils.isNotBlank(permission.getParentId()), Permission::getParentId, permission.getParentId())
                .ne(StringUtils.isNotBlank(permission.getId()), Permission::getId, permission.getId());
        List<Permission> existDatas = service.list(queryWrapper);
        if (null != existDatas && !existDatas.isEmpty()) {
            return Result.error("权限名称已存在！");
        }
        boolean result = service.saveOrUpdate(permission);
        if (!result) {
            return Result.error(errMsg);
        }
        return Result.OK(msg);
    }



}
