package test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SimpleWeather {
    // 天气情况查询接口地址
    public static String API_URL = "http://apis.juhe.cn/simpleWeather/query";
    // 接口请求Key
    public static String API_KEY = "5e043c4d4cc7d7056db3ada68ca3bd70";

//    public static void main(String[] args) {
////        String cityName = "北京";
////        queryWeather(cityName);
//    }

    /**
     * 根据城市名查询天气情况
     *
     * @param cityName
     */
    public static String[] queryWeather(String cityName) {
        Map<String, Object> params = new HashMap<>();//组合参数
        params.put("city", cityName);
        params.put("key", API_KEY);
        String queryParams = urlencode(params);
        
        String []s = new String[40];
        s[0] = "false";

        String response = doGet(API_URL, queryParams);
        
        JSONObject jsonObject = JSONObject.fromObject(response);
        JSONObject result = jsonObject.getJSONObject("result");
        JSONObject realtime = result.getJSONObject("realtime");
        JSONArray future = result.getJSONArray("future");
        
        try {
            
        	int error_code = jsonObject.getInt("error_code");
            if (error_code == 0) {
                System.out.println("调用接口成功");

                
                  
                s[0] = result.getString("city");
                s[1] = realtime.getString("info");               
                s[2] = realtime.getString("temperature");
                s[3] = realtime.getString("humidity");
                s[4] = realtime.getString("direct");
                s[5] = realtime.getString("power");
                String aqi = realtime.getString("aqi");
                s[6] = aqi;
                int Aqi = Integer.parseInt(aqi);
                if(Aqi>0 && Aqi<51){
                	s[7]="优";
                }else if(Aqi<101 && Aqi>50){
                	s[7]="良";
                }else if(Aqi>100 && Aqi<201){
                	s[7]="中";
                }else if(Aqi>200){
                	s[7]="差";
                }
                
                switch (s[1]) {
				case "晴":
					s[39]="'img/d00.gif'";
					break;
				case "多云":
					s[39]="'img/d01.gif'";
					break;
				case "阴":
					s[39]="'img/d02.gif'";
					break;
				case "阵雨":
					s[39]="'img/d03.gif'";
					break;
				case "雷阵雨":
					s[39]="'img/d04.gif'";
					break;
				case "雨夹雪":
					s[39]="'img/d06.gif'";
					break;
				case "小雨":
					s[39]="'img/d07.gif'";
					break;
				case "中雨":
					s[39]="'img/d08.gif'";
					break;
				case "大雨":
					s[39]="'img/d09.gif'";
					break;
				case "暴雨":
					s[39]="'img/d10.gif'";
					break;
				case "大暴雨":
					s[39]="'img/d11.gif'";
					break;
				case "特大暴雨":
					s[39]="'img/d12.gif'";
					break;
				case "阵雪":
					s[39]="'img/d13.gif'";
					break;
				case "小雪":
					s[39]="'img/d14.gif'";
					break;
				case "中雪":
					s[39]="'img/d15.gif'";
					break;
				case "大雪":
					s[39]="'img/d16.gif'";
					break;
				case "暴雪":
					s[39]="'img/d17.gif'";
					break;
				case "雾":
					s[39]="'img/d18.gif'";
					break;
				case "霾":
					s[39]="'img/d53.gif'";
					break;
				
				default:
					break;
				}
                
                
                for (int i = 0; i < future.size(); i++) { 
                	JSONObject s1 =  (JSONObject)future.get(i);
                	
                		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(s1.getString("date")); 
                		String now = new SimpleDateFormat("MM月dd日").format(date);
                		int a = 8+(4*i);
                		s[a] = now; 
                		
            			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            	        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
            	        Calendar cal = Calendar.getInstance();
            	        cal.setTime(date);
            	        
            	        //一周的第几天
            	        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            	        if (w < 0){
            	            w = 0;
            	        }
            	        int e=28+i;
            	        s[e] = weekDays[w];
            	        
            	        
                	
                		int c = 10+(4*i);
                		String wt = s1.getString("weather");
                		if(wt.indexOf("转")>0){
                			s[c] = wt.substring(0, wt.indexOf("转"));
                		}else{
                			s[c] = wt;
                		}
                		int b = 9+(4*i);
                		
                		switch (s[c]) {
        				case "晴":
        					s[b]="'img/d00.gif'";
        					break;
        				case "多云":
        					s[b]="'img/d01.gif'";
        					break;
        				case "阴":
        					s[b]="'img/d02.gif'";
        					break;
        				case "阵雨":
        					s[b]="'img/d03.gif'";
        					break;
        				case "雷阵雨":
        					s[b]="'img/d04.gif'";
        					break;
        				case "雨夹雪":
        					s[b]="'img/d06.gif'";
        					break;
        				case "小雨":
        					s[b]="'img/d07.gif'";
        					break;
        				case "中雨":
        					s[b]="'img/d08.gif'";
        					break;
        				case "大雨":
        					s[b]="'img/d09.gif'";
        					break;
        				case "暴雨":
        					s[b]="'img/d10.gif'";
        					break;
        				case "大暴雨":
        					s[b]="'img/d11.gif'";
        					break;
        				case "特大暴雨":
        					s[b]="'img/d12.gif'";
        					break;
        				case "阵雪":
        					s[b]="'img/d13.gif'";
        					break;
        				case "小雪":
        					s[b]="'img/d14.gif'";
        					break;
        				case "中雪":
        					s[b]="'img/d15.gif'";
        					break;
        				case "大雪":
        					s[b]="'img/d16.gif'";
        					break;
        				case "暴雪":
        					s[b]="'img/d17.gif'";
        					break;
        				case "雾":
        					s[b]="'img/d18.gif'";
        					break;
        				case "霾":
        					s[b]="'img/d53.gif'";
        					break;
        				
        				default:
        					break;
        				}
                		
                		
                		
                		
                		
                		
                		
                		
                		int d = 11+(4*i);
                		String dt = s1.getString("direct");
                		if(dt.indexOf("转")>0){
                			s[d] = dt.substring(0, dt.indexOf("转"));
                		}else{
                			s[d] = dt;
                		}
                		
                		int g = 33+i;
                		String tp = s1.getString("temperature");
                		s[g] = tp.substring(tp.indexOf("/")+1, tp.indexOf("℃"));
                		
                    System.out.println("currentfuture:" + s1);
                } 
                 
                
                
                return s;
            } else {
                System.out.println("调用接口失败：" + jsonObject.getString("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
        
        return s;
    }

    /**
     * get方式的http请求
     *
     * @param httpUrl 请求地址
     * @return 返回结果
     */
    public static String doGet(String httpUrl, String queryParams) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(new StringBuffer(httpUrl).append("?").append(queryParams).toString());
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(5000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(6000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                // 封装输入流，并指定字符集
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                // 存放数据
                StringBuilder sbf = new StringBuilder();
                String temp;
                while ((temp = bufferedReader.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append(System.getProperty("line.separator"));
                }
                result = sbf.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();// 关闭远程连接
            }
        }
        return result;
    }

    /**
     * 将map型转为请求参数型
     *
     * @param data
     * @return
     */
    public static String urlencode(Map<String, ?> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, ?> i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        String result = sb.toString();
        result = result.substring(0, result.lastIndexOf("&"));
        return result;
    }
}