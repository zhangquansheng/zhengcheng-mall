package com.zhengcheng.mall.bbs.common.apiresult;

public enum ApiResult {
    SUCCESS(20000, "响应成功"),
    ILLEGAL_PARAMS(40000, "非法的参数"),
    INVALID_AUTH(40003, "权限不足"),
    RESOUCE_NOT_FOUND(40004, "未找到该信息"),
    BUSINESS_FAIL(40005, "业务处理失败"),
    SYSTEM_EXCEPTION(50000, "系统异常");

    private final int    code;
    private final String msg;

    private ApiResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public ApiResultMap<String, Object> getMap() {
        return (new ApiResultMap()).add("code", this.getCode()).add("msg", this.getMsg());
    }

    public ApiResultMap<String, Object> getMap(BaseSubResult baseSubResult) {
        return (new ApiResultMap()).add("code", this.getCode()).add("msg", this.getMsg())
                .add("subCode", baseSubResult.getSubCode()).add("subMsg", baseSubResult.getSubMsg());
    }

    public String toString() {
        return "code=" + this.code + "," + "msg" + "=" + this.msg;
    }
}
