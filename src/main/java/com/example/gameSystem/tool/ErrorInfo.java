package com.example.gameSystem.tool;

import java.util.HashMap;

/**
 * 错误参数
 *
 * @author lzp
 * @version 6.0
 * @created 2014-3-21 下午7:52:38
 */
public class ErrorInfo {
    public int code;
    public String msg;
    public String FRIEND_INFO = "";
    public String PROCESS_INFO = "";

    /**
     * 返回页面路径
     **/
    public String returnUrl;
    /**
     * 返回页面描述
     **/
    public String returnMsg;
    /**
     * 各实体Id
     */
    public long entityId;
    /**
     * client
     */
    public long client;
    /**
     * 附加数据放到Map里面
     */
    public HashMap<String, String> mapObj;

    public ErrorInfo() {
        this.code = 0;
        this.msg = "";
    }

    public ErrorInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void clear() {
        this.code = 0;
        this.msg = "";
    }

    @Override
    public String toString() {
        return "ErrorInfo [msg=" + msg + ", code=" + code + ", FRIEND_INFO="
                + FRIEND_INFO + ", PROCESS_INFO=" + PROCESS_INFO
                + ", returnUrl=" + returnUrl + ", returnMsg=" + returnMsg
                + ", entityId=" + entityId + ", client=" + client + ", mapObj="
                + mapObj + "]";
    }

    /**
     * 构建错误信息
     *
     * @param error
     * @param code
     * @param msg
     * @param returnUrl
     * @param returnMsg
     * @return
     */
    public static ErrorInfo createError(ErrorInfo error, int code, String msg, String returnUrl, String returnMsg) {
        error.msg = msg;
        error.code = code;
        error.returnMsg = returnMsg;
        error.returnUrl = returnUrl;
        error.client = error.client;
        return error;
    }

}

