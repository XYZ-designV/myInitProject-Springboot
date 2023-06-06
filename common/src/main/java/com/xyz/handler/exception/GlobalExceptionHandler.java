package com.xyz.handler.exception;


import com.xyz.constants.AppHttpCodeEnum;
import com.xyz.domain.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ConstraintDeclarationException;
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
    @ExceptionHandler(ConstraintDeclarationException.class)
    public ResponseResult controllerGetExceptionHandler(ConstraintDeclarationException e) {
        // TODO get参数校验
        System.out.println(e);
        log.error("get请求的对象参数校验异常处理 -->" + e.getMessage(),e);
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR, e.getMessage());
    }
    // Post请求的对象参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult controllerPostExceptionHandler(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getAllErrors();
        log.error("post请求的对象参数校验异常处理 -->" + getValidExceptionMsg(allErrors));
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR, getValidExceptionMsg(allErrors));
    }

    // 全局异常处理
    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e){
        //打印异常信息
        log.error("全局异常处理 -->" + e.getMessage(),e);
        //从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage());
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
                    stringBuilder.append((((FieldError)error).getField())).append(";");
                }
                stringBuilder.append(error.getDefaultMessage()).append(";");
            });
        }
        return stringBuilder.toString();
    }

}
