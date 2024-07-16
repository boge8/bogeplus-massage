package com.bogeplus.common.exception;

import lombok.Data;
import org.omg.SendingContext.RunTime;

@Data
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Integer code =500;
    private String msg;
        public BizException(Integer code,String message) {
            super(message);
            this.code = code;
            this.msg = message;
        }
           public BizException(String message) {
            super(message);
            this.msg = message;
        }

}
