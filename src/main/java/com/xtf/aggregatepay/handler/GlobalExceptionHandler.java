package com.xtf.aggregatepay.handler;

import cn.hutool.json.JSONUtil;
import com.xtf.aggregatepay.Consts;
import com.xtf.aggregatepay.core.LogicException;
import com.xtf.aggregatepay.dto.ApiResp;
import com.xtf.aggregatepay.util.RepKit;
import com.xtf.aggregatepay.util.ReqKit;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

/**
 * 简介
 * <p>
 * 项目名称:   [aggregate-pay]
 * 包:        [com.xtf.aggregatepay.handler]
 * 类名称:    [ExceptionHandler]
 * 类描述:    []
 * 创建人:    [于海慧]
 * 创建时间:  [2018/9/4]
 * 修改人:    []
 * 修改时间:  []
 * 修改备注:  []
 * 版本:     [v1.0]
 */

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request ,HttpServletResponse response) {
        log.error("缺少请求参数 ${}", e);
        if(ReqKit.isAjaxRequest(request)) {
            ApiResp apiResp = ApiResp.builder().respCode(Consts.SYS_COMMON_ERR_CODE).respMsg("缺少请求参数>>" + e.getMessage()).build();
            RepKit.writeJson(response,JSONUtil.toJsonStr(apiResp));
        }else{
            request.setAttribute("msg","缺少请求参数");
            return "error_msg";
        }
        return "error_msg";
    }

    /**
     * 处理所有业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(LogicException.class)
    public String handleBusinessException(LogicException e, HttpServletRequest request ,HttpServletResponse response){
        log.error("系统业务操作失败：异常编号 {} 异常信息 {}",e.getErrCode(),e.getErrMsg());
        if(ReqKit.isAjaxRequest(request)) {
            ApiResp apiResp = ApiResp.builder().respCode(Consts.SYS_COMMON_ERR_CODE).respMsg(e.getPrettyExceptionMsg()).build();
            RepKit.writeJson(response,JSONUtil.toJsonStr(apiResp));
        }else{
            request.setAttribute("msg","系统业务操作失败：异常编号 "+e.getErrCode()+" 异常信息 "+e.getErrMsg());
            return "error_msg";
        }
        return "error_msg";
    }

    /**
     * 处理所有接口数据验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request ,HttpServletResponse response){
        log.error("数据不合法：异常信息>>{}",e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        String errorMesssage = "请求参数不合法:\n";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + "\n";
        }
        log.error("数据校验失败原因:"+errorMesssage);

        if(ReqKit.isAjaxRequest(request)) {
            ApiResp apiResp =ApiResp.builder().respCode(Consts.SYS_COMMON_ERR_CODE).respMsg(errorMesssage).build();
            RepKit.writeJson(response,JSONUtil.toJsonStr(apiResp));
        }else{
            request.setAttribute("msg","数据校验失败原因:"+errorMesssage);
            return "error_msg";
        }
        return "error_msg";
    }

    @ExceptionHandler(value =BindException.class)
    public String handleBindException(BindException e, HttpServletRequest request ,HttpServletResponse response)  {
        // ex.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，则调用ex.getAllErrors()
        log.error("数据绑定失败：异常信息>>{}",e.getMessage());
        FieldError fieldError = e.getFieldError();
        StringBuilder sb = new StringBuilder();
        sb.append(fieldError.getField()).append("=[").append(fieldError.getRejectedValue()).append("]")
                .append(fieldError.getDefaultMessage());
        if(ReqKit.isAjaxRequest(request)) {
            ApiResp apiResp =ApiResp.builder().respCode(Consts.SYS_COMMON_ERR_CODE).respMsg(sb.toString()).build();
            RepKit.writeJson(response,JSONUtil.toJsonStr(apiResp));
        }else{
            request.setAttribute("msg","数据绑定失败：异常信息>>"+e.getMessage());
            return "error_msg";
        }
        return "error_msg";
    }


    @ExceptionHandler(value =ValidationException.class)
    public String handleValidationException(ValidationException e, HttpServletRequest request ,HttpServletResponse response) {
        log.error("数据校验：校验未通过，校验结果为>>{}",e.getMessage());
        if(ReqKit.isAjaxRequest(request)) {
            ApiResp apiResp = ApiResp.builder().respCode(Consts.SYS_COMMON_ERR_CODE).respMsg(e.getMessage()).build();
            RepKit.writeJson(response,JSONUtil.toJsonStr(apiResp));
        }else{
            request.setAttribute("msg","数据校验：校验未通过，校验结果为>>"+e.getMessage());
            return "error_msg";
        }
        return "error_msg";
    }

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request ,HttpServletResponse response){
        log.error("系统错误：异常信息为>>{}",e.getMessage());
        e.printStackTrace();
        if(ReqKit.isAjaxRequest(request)) {
            ApiResp apiResp = ApiResp.builder().respCode(Consts.SYS_COMMON_ERR_CODE).respMsg("系统错误了，请联系管理员！").build();
            RepKit.writeJson(response,JSONUtil.toJsonStr(apiResp));
        }else{
            request.setAttribute("msg","系统错误：异常信息为>>"+e.getMessage());
            return "error_msg";
        }
        return "error_msg";
    }
    @ExceptionHandler(value =MultipartException.class)
    public String handleMultipartException(MultipartException multipartException, HttpServletRequest request ,HttpServletResponse response){
        log.error("上传文件过大");
        if(ReqKit.isAjaxRequest(request)) {
            ApiResp apiResp = ApiResp.builder().respCode(Consts.SYS_COMMON_ERR_CODE).respMsg("上传的文件过大，单个文件不要超过1MB,一次上传不能超过10MB").build();
            RepKit.writeJson(response,JSONUtil.toJsonStr(apiResp));
        }else{
            request.setAttribute("msg","上传的文件过大，单个文件不要超过1MB,一次上传不能超过10MB");
            return "error_msg";
        }
        return "error_msg";
    }


}
