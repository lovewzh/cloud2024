package org.atguigu.cloud.controller;

import jakarta.annotation.Resource;
import org.atguigu.cloud.apis.PayFeignApi;
import org.atguigu.cloud.entities.Pay;
import org.atguigu.cloud.entities.PayDTO;
import org.atguigu.cloud.resp.ResultData;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/feign")
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping(value = "/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        ResultData stringResultData = payFeignApi.addPay(pay);
        return stringResultData;
    }
    @GetMapping(value = "/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        return  payFeignApi.getById(id);
    }
    @GetMapping(value = "/pay/get/info")
    public String getInfo(){
        return payFeignApi.getInfo();
    }

    @GetMapping("/pay/getAll")
    public ResultData getALl(){
        return payFeignApi.getAll();
    }
    @DeleteMapping("/pay/del/{id}")
    public ResultData delete(@PathVariable("id") Integer id){
//        restTemplate.delete(url+"/pay/del/"+id);
        return payFeignApi.deletePay(id);
    }
    @PutMapping("/pay/update")
    public ResultData update(@RequestBody PayDTO payDTO){
        return payFeignApi.updatePay(payDTO);
    }

    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }
        System.out.println("===================================");
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }
        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }

    @GetMapping(value = "/pay/timeout")
    public ResultData<String> timeout(){
        return  payFeignApi.timeout();
    }
}
