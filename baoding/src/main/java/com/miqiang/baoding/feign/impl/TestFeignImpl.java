package com.miqiang.baoding.feign.impl;

import com.miqiang.baoding.feign.TestFeign;
import com.miqiang.baoding.util.Result;
import org.springframework.stereotype.Component;

/**
 * @author miqiang
 * @version 1.0
 * @description
 * @createTime 2022-07-22  14:17
 */
@Component
public class TestFeignImpl implements TestFeign {

    @Override
    public Result<?> test() {
        return null;
    }
}
