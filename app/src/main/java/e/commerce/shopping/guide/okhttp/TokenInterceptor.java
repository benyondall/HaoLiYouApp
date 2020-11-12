package e.commerce.shopping.guide.okhttp;


import java.io.IOException;
import java.nio.charset.Charset;

import e.commerce.shopping.guide.common.tools.Validate;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public abstract class TokenInterceptor implements Interceptor {

    //final String login_false = "{\"success\":false,\"code\":400,\"message\":\"用户名或密码错误\"}";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        if (isTokenExpired(response)) {//根据和服务端的约定判断token过期
            //同步请求方式，获取最新的Token
            String newSession = getNewToken();
            if (Validate.isNotNull(newSession)) {
                //使用新的Token，创建新的请求
                Request newRequest = chain.request()
                        .newBuilder()
                        .header("access_token", newSession)
                        .build();
                //重新请求
                return chain.proceed(newRequest);
            }
            else{//没有获取到token
                logout();
            }
        }
        return response;
    }

    /**
     * 根据Response，判断Token是否失效
     *
     * @param response
     * @return
     */
    private boolean isTokenExpired(Response response) {
        try {
            // 第一种，当header的content-length不确定的情况下会出错
//            ResponseBody responseBody = response.peekBody(1024 * 1024);//关键代码

            // 第二种
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            Charset UTF8 = Charset.forName("UTF-8");
            String json = buffer.clone().readString(UTF8);

            // 第三种绝对不行，这个东西一次请求只有一次读取
//            BufferedSource source = responseBody.source();
//            String json = source.readString(Charset.defaultCharset());
            //登录失败验证,不能再去刷新token
//            if (StringUtils.isNotBlank(json) && json.contains(login_false)){
//                return false;
//            }
            // 这个判断可能有坑 hanzhe
//            if (StringUtils.isNotBlank(json) && json.contains("400") && json.contains("false") && json.contains("\"success\":false")) {
////                Gson gson = new Gson();
////                BaseResp baseResp = gson.fromJson(json, BaseResp.class);
////                if (baseResp!=null && 400 == baseResp.getResCode()) {
////                    return true;
////                }
//                return true;
//            }
            if (Validate.isNotNull(json) && json.contains("\"code\":401")) {
//                Gson gson = new Gson();
//                BaseResp baseResp = gson.fromJson(json, BaseResp.class);
//                if (baseResp!=null && 400 == baseResp.getResCode()) {
//                    return true;
//                }
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if (response.code() == 401 ) {
            return true;
        }
        return false;
    }

    /**
     * 同步请求方式，获取最新的Token
     *
     * @return
     */
    public abstract String getNewToken() throws IOException;

    public abstract void logout();
}
