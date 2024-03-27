package org.atguigu.cloud.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.atguigu.cloud.entities.Pay;
import org.atguigu.cloud.entities.PayDTO;
import org.atguigu.cloud.resp.ResultData;
import org.atguigu.cloud.resp.ReturnCodeEnum;
import org.atguigu.cloud.service.PayService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/pay")
@Tag(name = "支付服务模块",description = "支付crud")
@Slf4j
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping(value = "/add")
    @Operation(summary = "新增",description = "json格式，新增支付信息")
    public ResultData<String> addPay(@RequestBody Pay pay){
        log.info("payment8001:controller:add:{}",pay.toString());
        int i = payService.add(pay);
        return ResultData.success("成功插入记录"+i);
    }
    @DeleteMapping(value = "/del/{id}")
    @Operation(summary = "删除")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id){
        log.info("payment8001:controller:delete:{}",id);
        int i = payService.delete(id);
        return ResultData.success(i);
    }
    @PutMapping(value = "/update")
    @Operation(summary = "更改")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO){
        log.info("payment8001:controller:update:{}",payDTO.toString());
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        int i = payService.update(pay);
        return ResultData.success("成功修改："+i);
    }
    @Operation(summary = "根据id获取")
    @GetMapping(value = "/get/{id}")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){
        log.info("payment8001:controller:getById:{}",id);
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }
    @GetMapping(value = "/getAll")
    @Operation(summary = "获取全部")
    public ResultData<List<Pay>> getAll(){
        log.info("payment8001:controller:getAll");
        List<Pay> list = payService.getAll();
        return ResultData.success(list);
    }
    /*
        获取consul配置信息
     */
    @Value("${server.port}")
    private String port;
    @GetMapping(value = "/get/info")
    public String getInfo(@Value("${atguigu.info}") String info){
        return "8002 get consul profile->"+info+"  from:"+port;
    }
    @GetMapping("/timeout")
    public ResultData<String> timeout(){
        try {
            TimeUnit.SECONDS.sleep(60);
        }catch (InterruptedException e){
            e.printStackTrace();
            ResultData.fail(ReturnCodeEnum.RC500.getCode(), "wait too long");
        }
        return ResultData.success("wait 60s");
    }
}
