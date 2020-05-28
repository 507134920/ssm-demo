package com.ssm.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;

import com.ssm.entity.Home;
import com.ssm.service.BuyService;
import com.ssm.util.AjaxResult;
import com.ssm.util.AlipayConfig;
import com.ssm.util.JsonResult;
import org.apache.logging.log4j.core.appender.routing.Route;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@Controller
public class AlipayController {
    private Logger log =
            Logger.getLogger(AlipayController.class.getSimpleName());
    @Autowired
    private BuyService buyService;


    //1 根据手机号 显示房租 以及 时间信息 距离现在还多少时间到期
    @RequestMapping("/findPersonHome.do")
    //@RequiresRoles(value={"SysManager","Tenant"},logical= Logical.OR)
    public String updatePassword(String phone, Model model) {
        List<Map<String, Object>> objectByPhone = buyService.findTime(phone);
        Object nowtime = objectByPhone.get(0).get("new");
        Object startleasetime = objectByPhone.get(0).get("startleasetime");
        Object endleasetime = objectByPhone.get(0).get("endleasetime");
        /**
         * 计算距离现在还多少时间到期
         */
        float day = 0; // 返回的时间差
        float day1 = 0;
        String time1,time2;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        time1 =  df.format(nowtime);
        time2 =  df.format(endleasetime);
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = df.parse(time1); //当前时间
            date2 = df.parse(time2); //结束租赁时间
            long millisecond = date2.getTime() - date1.getTime();

            day = millisecond / (24 * 60 * 60 * 1000);
            day1 = millisecond % (24 * 60 * 60 * 1000);
            if (day > 0){
                if (day1 >0){
                    day+=1;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("相差天数为："+day);
        Map<String,Object> homeMessage = new HashMap<>();
        homeMessage.put("startleasetime",startleasetime);
        homeMessage.put("endleasetime",endleasetime);
        homeMessage.put("day",day+"天");
        homeMessage.put("phone",phone);
        model.addAttribute("homeMessage",homeMessage);
        return "Alipay/personHome_list";
    }


    //2 支付 增加到期时间
    // 1 普通支付  如果剩余天数>0 则增加一个月的租期
    @RequestMapping(value = "/goAlipayP1.do")
    @ResponseBody
    public AjaxResult goAlipayP1(String phone, Integer num){
        AjaxResult ajaxResult = new AjaxResult();
        try{
            buyService.addTime(num,phone);
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("支付成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("支付失败");
        }
        return ajaxResult;
    }
    // 如果剩余天数<0 则减掉逾期的时间
    @RequestMapping(value = "/goAlipayP2.do")
    @ResponseBody
    public AjaxResult goAlipayP2(String phone, Integer num){
        AjaxResult ajaxResult = new AjaxResult();
        try{
            buyService.addTime1(num,phone);
            ajaxResult.setSuccess(true);
            ajaxResult.setMessage("支付成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("支付失败");
        }
        return ajaxResult;
    }

    // 2 扫码支付修改 后增加时间
    @RequestMapping(value = "/goAlipay.do", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String goAlipay(String phone,Integer num, HttpServletRequest request, HttpServletRequest response) throws Exception {

        Map<String, Object> objectByPhone = buyService.findObjectByPhone(phone);
        if (objectByPhone.size() == 0){
            return "AlipayError";
        }
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = objectByPhone.get("id").toString();//订单编号
        //付款金额，必填
        float price = (float)objectByPhone.get("price");
        float price_tol = price * num;
        String total_amount = String.valueOf(price_tol);//订单总金额
        //订单名称，必填
        String subject = objectByPhone.get("hourse")+"----"+objectByPhone.get("floor")+"----"+objectByPhone.get("room_number");
        //商品描述，可空   房租 + 支付月数
        String body = "月租："+objectByPhone.get("price")+"，支付数量（月）：" + num;

        //String OrderID = String.valueOf(order.getId());
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result;

        //1、完成支付、修改状态
        boolean b1 = buyService.addTime(num,phone);
        if (b1 == true){
            try {
                result = alipayClient.pageExecute(alipayRequest).getBody();
                System.out.println("开始支付");
                return result;
            }catch (Exception e){
                System.out.println("支付失败");
                return "AlipayError";
            }

        }
        System.out.println("支付失败");
        return "AlipayError";
    }

    /**
     * @Description: 支付宝同步通知页面
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/alipayReturnNotice.do")
    public String  alipayReturnNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {

        log.info("支付成功, 进入同步通知接口...");

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        log.info("params为："+params);
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        //log.info("验证签名"+signVerified);
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            // 修改订单、库存状态


            log.info("********************** 支付成功(支付宝同步通知) **********************");
            log.info("* 订单号: {}"+ out_trade_no);
            log.info("* 支付宝交易号: {}"+ trade_no);
            log.info("* 实付金额: {}"+ total_amount);
            log.info("***************************************************************");

        }else {
            log.info("支付, 验签失败...");
        }
        return "index";
    }

    /**
     * @Description: 支付宝异步通知页面
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/alipayNotifyNotice.do")
    @ResponseBody
    public String alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {

        log.info("支付成功, 进入异步通知接口...");

        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
        */
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知

                // 修改叮当状态

                log.info("********************** 支付成功(支付宝异步通知) **********************");
                log.info("* 订单号: {}"+out_trade_no);
                log.info("* 支付宝交易号: {}"+ trade_no);
                log.info("* 实付金额: {}"+total_amount);
                log.info("***************************************************************");
            }
            log.info("支付成功...");

        }else {//验证失败
            log.info("支付, 验签失败...");
        }
        return "index";
    }
}
