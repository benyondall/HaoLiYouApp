package e.commerce.shopping.guide.okhttp;


import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {
    private static HttpUtils retrofitUtils;
    private static Retrofit retrofit;
    private static Boolean debug = false;
    private static String hostName = "";
    private static String token = null;
    private static TokenExpiredListener tokenExpiredListener = null;
    private static Context mContext = null;

    private HttpUtils() {

    }

    public TokenExpiredListener getTokenExpiredListener() {
        return tokenExpiredListener;
    }

    public void setTokenExpiredListener(TokenExpiredListener tokenExpiredListener) {
        HttpUtils.tokenExpiredListener = tokenExpiredListener;
    }

    /**
     * 建议在MyApplication中 初始化
     *
     * @param _debug
     */
    public void init(String _hostName, Boolean _debug) {
        retrofit = null;
        debug = _debug;
        hostName = _hostName;
    }

    /**
     * 提供服务器地址系信息
     * @return
     */
    public String getUrl() {
        return hostName;
    }

    /**
     * 设置 token 访问时可以携带此token
     *
     * @param username
     * @param password
     * @param key
     */
    public void setAuthToken(String username, String password, String key) {
        reset();
        key = key;
        String _token = username + "@,@" + password;
        token = _token;
    }

    /**
     * 设置 token 访问时可以携带此token
     *
     * @param _token token
     */
    public void setAuthToken(String _token) {
        reset();
        token = _token;
    }

    /**
     * 设置上下文
     *
     * @param context context
     */
    public void setContext(Context context) {
        mContext = context;
    }

    /**
     * 重新设置 token 和 核心工具
     */
    public void reset() {
        retrofit = null;
        token = null;
    }

    public static HttpUtils getInstance() {
        if (mContext != null) {
            /**
             * 发广播
             */
            Intent intent = new Intent();
            intent.setAction(".CustomView.SimpleListView$MyReceiver");
            mContext.sendBroadcast(intent);
        }
        if (retrofitUtils == null) {
            synchronized (HttpUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new HttpUtils();
                }
            }
        }
        return retrofitUtils;
    }

    public String getToken() {
        return token;
    }


    public static synchronized Retrofit getRetrofit() {
        if (retrofit == null) {

            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
            if (debug) {
                //显示日志
                logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            }

            HeaderInterceptor headerInterceptor = new HeaderInterceptor(token);
            TokenInterceptor tokenInterceptor = new TokenInterceptor() {
                @Override
                public String getNewToken() throws IOException {
                    if (tokenExpiredListener != null) {
                        return tokenExpiredListener.getNewToken();
                    }
                    return null;
                }

                @Override
                public void logout() {
                    if (tokenExpiredListener != null) {
                        tokenExpiredListener.logout();
                    }
                }
            };
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(logInterceptor)
                    .addInterceptor(headerInterceptor)
                    .addInterceptor(tokenInterceptor)
                    //配置SSlSocketFactory
//                    .sslSocketFactory(SSLSocketFactoryUtils.createSSLSocketFactory(), SSLSocketFactoryUtils.createTrustAllManager())
//                    .hostnameVerifier(new SSLSocketFactoryUtils.TrustAllHostnameVerifier())
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS).build();
            Gson gson = new GsonBuilder()
                    //解决map Double 问题
                    .registerTypeAdapter(Map.class,
                            new JsonDeserializer<Map<String, Object>>() {
                                @Override
                                public Map<String, Object> deserialize(JsonElement json, Type typeOfT,
                                                                       JsonDeserializationContext context) throws JsonParseException {
                                    Map treeMap = new HashMap<>();
                                    JsonObject jsonObject = json.getAsJsonObject();
                                    Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                                    for (Map.Entry<String, JsonElement> entry : entrySet) {
                                        if (entry.getValue().isJsonArray()) {
                                            treeMap.put(entry.getKey(), entry.getValue());
                                        } else {
                                            treeMap.put(entry.getKey(), entry.getValue().getAsString());
                                        }
                                    }
                                    return treeMap;
                                }
                            })
                    .registerTypeAdapter(Date.class, new DateAdapterNull())
                    //解决 日期格式问题
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();

            retrofit = new Retrofit.Builder().baseUrl(hostName).client(httpClient)
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

//            retrofit = new Retrofit.Builder().baseUrl(hostName).client(httpClient).addConverterFactory(JacksonConverterFactory.create())
//                    .build();

        }
        return retrofit;
    }

    public static class NullOnEmptyConverterFactory extends Converter.Factory {

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
            return new Converter<ResponseBody, Object>() {
                @Override
                public Object convert(ResponseBody body) throws IOException {
                    if (body.contentLength() == 0) return null;
                    return delegate.convert(body);
                }
            };
        }
    }


    /***
     * 读取*.cer公钥证书文件， 获取公钥证书信息
     * @author xgh
     */
    public static void testReadX509CerFile(InputStream inStream) throws Exception {

        try {
            // 读取证书文件

            // 创建X509工厂类
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            // 创建证书对象
            X509Certificate oCert = (X509Certificate) cf
                    .generateCertificate(inStream);
            inStream.close();
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
            String info = null;
            // 获得证书版本
            info = String.valueOf(oCert.getVersion());
            System.out.println("证书版本:" + info);
            // 获得证书序列号
            info = oCert.getSerialNumber().toString(16);
            System.out.println("证书序列号:" + info);
            // 获得证书有效期
            Date beforedate = oCert.getNotBefore();
            info = dateformat.format(beforedate);
            System.out.println("证书生效日期:" + info);
            Date afterdate = oCert.getNotAfter();
            info = dateformat.format(afterdate);
            System.out.println("证书失效日期:" + info);
            // 获得证书主体信息
            info = oCert.getSubjectDN().getName();
            System.out.println("证书拥有者:" + info);
            // 获得证书颁发者信息
            info = oCert.getIssuerDN().getName();
            System.out.println("证书颁发者:" + info);
            // 获得证书签名算法名称
            info = oCert.getSigAlgName();
            System.out.println("证书签名算法:" + info);

        } catch (Exception e) {
            System.out.println("解析证书出错！");
            e.printStackTrace();
        }
    }

    private static class DateAdapterNull implements JsonSerializer {
        //数据返回时date转成json字符

        @Override
        public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context) {
            if (src == null) {
                return new JsonPrimitive("");
            } else {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return new JsonPrimitive(formatter.format(src));
            }
        }
    }

    public <T> T getApiService(Class<T> clazz) {
        Retrofit retrofit = getRetrofit();
        return retrofit.create(clazz);
    }


    public interface TokenExpiredListener {

        String getNewToken() throws IOException;
        void logout();
    }
}

