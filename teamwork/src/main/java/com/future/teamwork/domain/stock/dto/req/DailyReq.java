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
public class DailyReq extends BaseBean4Req{

    private String fields;

    private DailyBasicReqParam params;

    @Data
    @AllArgsConstructor
    class DailyBasicReqParam {
        @JSONField(name = "ts_code")
        private String tsCode;
        @JSONField(name = "trade_date")
        private String tradeDate;
        @JSONField(name = "start_date")
        private String startDate;
        @JSONField(name = "end_date")
        private String endDate;
    }

    public static void main(String[] args) {
        DailyReq stockBasicReq = new DailyReq();
        stockBasicReq.setApiName("daily");
        DailyBasicReqParam param = stockBasicReq.new DailyBasicReqParam("000001.SZ","20220302",null,null);
        stockBasicReq.setParams(param);
        System.out.println("stockBasicReq = " + JSON.toJSONString(stockBasicReq));

        String result = HttpUtil.post("http://api.tushare.pro", JSON.toJSONString(stockBasicReq));
        System.out.println("result = " + result);
    }
}
