package com.future.teamwork.domain.stock.dto.req;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Description: 基础类
 * @Author: Chen Xiang
 * @CreateDate: 2022/3/20 16:31
 * @Version:
 */
@Data
//@AllArgsConstructor
public class BaseBean4Req {
    @JSONField(name = "api_name")
    private String apiName;

    private String token = "19e08ca3ace6ddaa46ad035c0c914da124d1aa9332b0d244c96f2308";
}
