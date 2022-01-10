package ltd.vastchain.nft.http.model;

public class HttpResponseModel<T> {
    private int code;
    private String msg;
    private T userToken;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getUserToken() {
        return userToken;
    }

    public void setUserToken(T userToken) {
        this.userToken = userToken;
    }
}
