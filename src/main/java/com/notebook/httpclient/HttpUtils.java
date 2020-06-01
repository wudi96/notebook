package com.notebook.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author luorigong
 */
public class HttpUtils {

  private static final String DEFAULT_CHARSET_NAME = "UTF-8";

  private static final int TIME_TO_LIVE = 30;

  private static final int MAX_CONNECTION = 200;

  private static final CloseableHttpClient CLOSEABLE_HTTP_CLIENT;

  static {
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(
        TIME_TO_LIVE, TimeUnit.SECONDS);
    connectionManager.setMaxTotal(MAX_CONNECTION);
    connectionManager.setDefaultMaxPerRoute(MAX_CONNECTION);

    CLOSEABLE_HTTP_CLIENT = HttpClientBuilder.create()
        .setConnectionManager(connectionManager)
        .build();
  }

  /**
   * 获取默认json请求的消息头 Content-Type : application/json
   */
  private static List<NameValuePair> getJsonHeader() {
    List<NameValuePair> header = new ArrayList<>();
    header.add(new BasicNameValuePair("Content-Type", "application/json"));
    return header;
  }


  public static String doPostJson(String url, List<NameValuePair> header,
      String json)
      throws IOException {
    HttpPost postRequest = new HttpPost(url);
    postRequest.setEntity(new StringEntity(json, DEFAULT_CHARSET_NAME));
    for (NameValuePair h : header) {
      postRequest.setHeader(h.getName(), h.getValue());
    }
    CloseableHttpResponse response = httpclient().execute(postRequest);
    HttpEntity entity = response.getEntity();
    return EntityUtils.toString(entity, DEFAULT_CHARSET_NAME);
  }

  public static String doGet(String url, List<NameValuePair> header) throws IOException {
    HttpGet httpGet = new HttpGet(url);
    CloseableHttpResponse response = httpclient().execute(httpGet);
    return EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET_NAME);
  }

  private static CloseableHttpClient httpclient() {
    return CLOSEABLE_HTTP_CLIENT;
  }
}
