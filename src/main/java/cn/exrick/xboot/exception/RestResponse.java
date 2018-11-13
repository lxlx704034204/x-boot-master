package cn.exrick.xboot.exception;

import java.io.Serializable;

/**
 * Created by hanakei on 01/08/2018.
 */
public class RestResponse implements Serializable {
    private static final long serialVersionUID = 463410688483839360L;

    private boolean success;

    private Integer code=200;

    private String msg;

    private Object data;

    public static final RestResponse SUCCESS_WITHOUT_MSG = new RestResponse(true) {
        private static final long serialVersionUID = 6559272999710759148L;

        @Override
        public void setMsg(String msg) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setData(Object data) {
            throw new UnsupportedOperationException();
        }
    };

    public RestResponse() {
    }

    public RestResponse(boolean success) {
        this.success = success;
    }

    public RestResponse(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
    public RestResponse(boolean success, Integer code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public RestResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public static RestResponse successWithData(Object data) {
        RestResponse response = new RestResponse(true);
        response.setData(data);
        return response;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
