package com.miqiang.baoding.feign;

import com.miqiang.baoding.feign.impl.TestFeignImpl;
import com.miqiang.baoding.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author miqiang
 * @version 1.0
 * @description
 * @createTime 2022-07-22  13:32
 */
@FeignClient(name = "lixian", path = "test", fallback = TestFeignImpl.class)
public interface TestFeign {

    @PostMapping("test")
    Result<?> test();
}
