package org.atguigu.cloud.mapper;

import org.apache.ibatis.annotations.Param;
import org.atguigu.cloud.entities.Storage;
import tk.mybatis.mapper.common.Mapper;
import io.seata.rm.tcc.api.BusinessActionContextParameter;

public interface StorageMapper extends Mapper<Storage> {
    /**
     *  自定义decrease，对应mapper.XXX.xml
     * 扣减库存
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}