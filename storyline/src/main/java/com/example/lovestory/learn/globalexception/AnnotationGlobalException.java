package com.example.lovestory.learn.globalexception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class AnnotationGlobalException {


    @ResponseBody
    @ExceptionHandler(BizException.class)
    public String bizExceptionHandle(HttpServletRequest req, BizException e) {
        log.error("出现异常{}", e);
        return "error";
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String doHandle(HttpServletRequest req, Exception e) {
        log.error("出现异常{}", e);
        return "error";
    }
//
//    /**
//     * 处理自定义的业务异常
//     * @param req
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = BizException.class)
//    @ResponseBody
//    public ResultResponse bizExceptionHandler(HttpServletRequest req, BizException e){
//        logger.error("发生业务异常！原因是：{}",e.getErrorMsg());
//        return ResultResponse.error(e.getErrorCode(),e.getErrorMsg());
//    }
//
//    /**
//     * 处理其他异常
//     * @param req
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value =Exception.class)
//    @ResponseBody
//    public ResultResponse exceptionHandler(HttpServletRequest req, Exception e){
//        logger.error("未知异常！原因是:",e);
//        return ResultResponse.error(ExceptionEnum.INTERNAL_SERVER_ERROR);
//    }

}
