package org.atguigu.cloud.controller;

import jakarta.annotation.Resource;
import org.atguigu.cloud.entities.Pay;
import org.atguigu.cloud.entities.PayDTO;
import org.atguigu.cloud.resp.ResultData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer")
public class OrderController {
//    public static final String url = "http://localhost:8001";

    public static final String url ="http://cloud-payment-service";
    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        return restTemplate.postForObject(url+"/pay/add",pay, ResultData.class);
    }

    @GetMapping("/pay/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id){
//        ResponseEntity<ResultData> forEntity = restTemplate.getForEntity(url + "/pay/get/" + id, ResultData.class, id);
//        System.out.println(forEntity.toString());
        return restTemplate.getForObject(url+"/pay/get/"+id,ResultData.class,id);
    }
    @GetMapping("/pay/getAll")
    public ResultData getALl(){
        return restTemplate.getForObject(url+"/pay/getAll", ResultData.class);
    }
    @DeleteMapping("/pay/del/{id}")
    public ResultData delete(@PathVariable("id") Integer id){
//        restTemplate.delete(url+"/pay/del/"+id);
        ResponseEntity<ResultData> exchange = restTemplate.exchange(url + "/pay/del/" + id, HttpMethod.DELETE, null, ResultData.class, id);
        System.out.println(exchange.getBody());
        ResultData r = exchange.getBody();
        return r;
    }
    @PutMapping("/pay/update")
    public ResultData update(@RequestBody PayDTO payDTO){
        restTemplate.put(url+"/pay/update",payDTO);
        return ResultData.success("修改成功");
    }

    @GetMapping(value = "/pay/get/info")
    public String getConsulInfo(){
        return  restTemplate.getForObject(url+"/pay/get/info", String.class);
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
}
