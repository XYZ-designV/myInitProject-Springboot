package com.xyz.handler.exception;


import com.xyz.constants.AppHttpCodeEnum;
import com.xyz.domain.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

/**
 * 全局异常处理器
 * @author xyz
 * 2023/5/30
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // 自定义异常处理
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        //打印异常信息
        log.error("抛出了自定义异常,异常信息为:",e);
        //从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(e.getCode(),e.getMsg());
    }

    // get请求的对象参数校验异常
    @ExceptionHandler(BindException.class)
    public ResponseResult controllerGetExceptionHandler(BindException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        log.error("get请求的对象参数校验异常处理 -->" +  getValidExceptionMsg(allErrors));
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,  getValidExceptionMsg(allErrors));
    }
    // Post请求的对象参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult controllerPostExceptionHandler(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getAllErrors();
        log.error("post请求的对象参数校验异常处理 -->" + getValidExceptionMsg(allErrors));
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR, getValidExceptionMsg(allErrors));
    }
    // get请求参数校验
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseResult handleMissingParameterException(MissingServletRequestParameterException ex) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!"".equals(ex.getParameterName()) && ex.getParameterName().equals("pageNum")) {
            stringBuilder.append("分页页数不能为空");
        }
        if (!"".equals(ex.getParameterName()) && ex.getParameterName().equals("pageSize")) {
            stringBuilder.append("分页大小不能为空");
        }
        if (StringUtils.hasText(stringBuilder.toString())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,stringBuilder.toString());
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,ex.getMessage());
    }

    // 全局异常处理
    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e){
        //打印异常信息
        log.error("全局异常处理 -->" + e.getMessage(),e);
        //从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),"系统错误");
    }


    /**
     * 获取参数校验Msg
     * @param errors 参数异常对象
     * @return Msg
     */
    private String getValidExceptionMsg(List<ObjectError> errors) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!CollectionUtils.isEmpty(errors)) {
            errors.forEach(error -> {
                if (error instanceof FieldError) {
//                    stringBuilder.append((((FieldError)error).getField())).append(";");
//                } else {
                    stringBuilder.append(error.getDefaultMessage()).append("; ");
                }
            });
        }
        return stringBuilder.toString();
    }

}
