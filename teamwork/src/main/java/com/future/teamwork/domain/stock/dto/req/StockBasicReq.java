package com.future.teamwork.domain.stock.dto.req;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
  *
  * @Description:
  * @Author:         Chen Xiang
  * @CreateDate:     2022/3/20 14:54
 */
@Data
public class StockBasicReq extends BaseBean4Req{

    private String fields;

    private StockBasicReqParam params;

    @Data
    @AllArgsConstructor
    class StockBasicReqParam{
        @JSONField(name = "List_status")
        private String ListStatus;
    }

    public static void main(String[] args) {
        StockBasicReq stockBasicReq = new StockBasicReq();
        stockBasicReq.setApiName("stock_basic");
        StockBasicReqParam param = stockBasicReq.new StockBasicReqParam("L");
        stockBasicReq.setParams(param);
        System.out.println("stockBasicReq = " + JSON.toJSONString(stockBasicReq));

        String result = HttpUtil.post("http://api.tushare.pro", JSON.toJSONString(stockBasicReq));
        System.out.println("result = " + result);
    }
}
