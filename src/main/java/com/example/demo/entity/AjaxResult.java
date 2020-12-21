package com.example.demo.entity;

import java.util.HashMap;

/**
 * 操作信息提醒
 */
public class AjaxResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 状态类型
     */
    public enum Type {
        /**
         * 成功
         */
        SUCCESS(0),
        /**
         * 警告
         */
        WARN(301),
        /**
         * 错误
         */
        ERROR(500);

        private int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * 初始化一个新创建的AjaxResult对象，使其表示一个空消息
     */
    public AjaxResult() {

    }

    /**
     * 初始化一个新创建的AjaxResult对象
     * @param type 状态类型
     * @param msg 返回内容
     */
    public AjaxResult(Type type, String msg) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的AjaxResult 对象
     * @param msg 返回内容
     * @param code 状态码
     */
    public AjaxResult(String msg, int code) {
        super.put(MSG_TAG, msg);
        super.put(CODE_TAG, code);
    }

    /**
     * 初始化一个新创建的AjaxResult对象
     * @param type 状态类型
     * @param msg 返回内容
     * @param data 数据对象
     */
    public AjaxResult(Type type, String msg, Object data) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        if (null != data) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(Type.SUCCESS, msg, data);
    }

    /**
     * 返回成功信息
     * @param msg 返回内容
     * @return 成功信息
     */
    public static AjaxResult success(String msg) {
        return success(msg, null);
    }

    /**
     * 返回成功数据
     * @param data 数据对象
     * @return 成功信息
     */
    public static AjaxResult success(Object data) {
        return success("操作成功", data);
    }

    /**
     * 返回成功信息
     * @return
     */
    public static AjaxResult success() {
        return success("操作成功");
    }

    /**
     * 返回警告信息
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告信息
     */
    public static AjaxResult warn(String msg, Object data) {
        return new AjaxResult(Type.WARN, msg, data);
    }

    /**
     * 返回警告信息
     * @param msg 返回内容
     * @return 警告信息
     */
    public static AjaxResult warn(String msg) {
        return warn(msg, null);
    }

    /**
     * 返回错误信息
     * @param msg 返回内容
     * @param data 数据对象
     * @return 错误信息
     */
    public static AjaxResult error(String msg, Object data) {
        return new AjaxResult(Type.ERROR, msg, data);
    }

    /**
     * 返回错误信息
     * @param msg 返回内容
     * @return 错误信息
     */
    public static AjaxResult error(String msg){
        return error(msg, null);
    }

    /**
     * 返回错误信息
     * @param msg 返回内容
     * @param code 提示码
     * @return 错误信息
     */
    public static AjaxResult error(String msg, int code) {
        return new AjaxResult(msg, code);
    }
}
