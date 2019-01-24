package com.ldt.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ldt.IMessageService;
import com.ldt.utils.HttpUtil;
import com.ldt.utils.JsonUtils;
import com.ldt.utils.ReadProperties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestMessage {

    public static org.apache.log4j.Logger logger = Logger.getLogger(TestMessage.class);

    @Autowired
    private IMessageService messageService;

    @RequestMapping("/test")
    @ResponseBody
    public String getTestMessage(String messageStr) {
        return messageService.getStr(messageStr);

    }

    @RequestMapping("/testSendJson")
    @ResponseBody
    public Map sendJsonTest(HttpServletRequest request, HttpServletResponse response) {
        String url = ReadProperties.ReadPropertiesFromFiles("api.properties", "testSendJson.url", "CONF_HOME");

        Map resultMap = new HashMap();
        Map dateMap = new HashMap();
        dateMap.put("applSeq", request.getParameter("applSeq"));
        JSONObject jsonData = new JSONObject();
        jsonData.put("head", "mdd");
        jsonData.put("data", dateMap);
        //发送json
        String jsonStr = HttpUtil.sendJson(url, JsonUtils.obj2Json(jsonData));
        //String jsonStr = HttpUtil.httpURLConntJson(url,JsonUtils.obj2Json(jsonData));
        logger.info("返回报文：" + jsonStr);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        Map reMap = (Map) jsonObject;
        resultMap.put("applseq", reMap.get("applSeq"));
        return resultMap;
    }

    @RequestMapping(value = "/testAcceptJson", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getTest(HttpServletRequest request, @RequestBody Map<String, Object> frontData) {
        logger.info("接收json: " + frontData);
        // 遍历Map
        for (Map.Entry entry1 : frontData.entrySet()) {
            logger.info("key:" + entry1.getKey() + ",value:" + entry1.getValue());
        }
        String applSeq = (String) frontData.get("applSeq");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("applSeq", applSeq);

        return jsonObject;
    }


}
