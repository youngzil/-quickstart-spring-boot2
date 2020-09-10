package com.quickstart.springboot.netty.action.controller;

import com.quickstart.springboot.netty.action.common.enums.StatusEnum;
import com.quickstart.springboot.netty.action.common.pojo.CustomProtocol;
import com.quickstart.springboot.netty.action.common.res.BaseResponse;
import com.quickstart.springboot.netty.action.common.service.PrometheusCustomMonitor;
import com.quickstart.springboot.netty.action.server.HeartBeatServer;
import com.quickstart.springboot.netty.action.vo.req.SendMsgReqVO;
import com.quickstart.springboot.netty.action.vo.res.SendMsgResVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Function:
 *
 * @author crossoverJie Date: 22/05/2018 14:46
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/")
public class IndexController {

  @Autowired
  private HeartBeatServer heartbeatClient;

  /**
   * 统计 service
   */

  @Autowired
  private PrometheusCustomMonitor monitor;


  /**
   * 向服务端发消息
   */
  @ApiOperation("服务端发送消息")
  @RequestMapping("sendMsg")
  @ResponseBody
  public BaseResponse<SendMsgResVO> sendMsg(@RequestBody SendMsgReqVO sendMsgReqVO) {
    BaseResponse<SendMsgResVO> res = new BaseResponse();
    heartbeatClient.sendMsg(new CustomProtocol(sendMsgReqVO.getId(), sendMsgReqVO.getMsg()));

    monitor.getOrderCount().increment();

    SendMsgResVO sendMsgResVO = new SendMsgResVO();
    sendMsgResVO.setMsg("OK");
    res.setCode(StatusEnum.SUCCESS.getCode());
    res.setMessage(StatusEnum.SUCCESS.getMessage());
    res.setDataBody(sendMsgResVO);
    return res;
  }
}
