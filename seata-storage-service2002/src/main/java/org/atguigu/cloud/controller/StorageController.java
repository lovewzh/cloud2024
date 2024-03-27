package org.atguigu.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.atguigu.cloud.service.StorageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther zzyy
 * @create 2023-12-01 18:09
 */

@RestController
@Slf4j
public class StorageController
{
    @Resource
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @RequestMapping("/storage/decrease")
    public ResultData decrease(Long productId, Integer count) {
        log.info(productId+"---"+count);
        storageService.decrease(productId, count);
        return ResultData.success("扣减库存成功!");
    }
}
