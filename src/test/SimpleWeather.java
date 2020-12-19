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
    // ���������ѯ�ӿڵ�ַ
    public static String API_URL = "http://apis.juhe.cn/simpleWeather/query";
    // �ӿ�����Key
    public static String API_KEY = "5e043c4d4cc7d7056db3ada68ca3bd70";

//    public static void main(String[] args) {
////        String cityName = "����";
////        queryWeather(cityName);
//    }

    /**
     * ���ݳ�������ѯ�������
     *
     * @param cityName
     */
    public static String[] queryWeather(String cityName) {
        Map<String, Object> params = new HashMap<>();//��ϲ���
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
                System.out.println("���ýӿڳɹ�");

                
                  
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
                	s[7]="��";
                }else if(Aqi<101 && Aqi>50){
                	s[7]="��";
                }else if(Aqi>100 && Aqi<201){
                	s[7]="��";
                }else if(Aqi>200){
                	s[7]="��";
                }
                
                switch (s[1]) {
				case "��":
					s[39]="'img/d00.gif'";
					break;
				case "����":
					s[39]="'img/d01.gif'";
					break;
				case "��":
					s[39]="'img/d02.gif'";
					break;
				case "����":
					s[39]="'img/d03.gif'";
					break;
				case "������":
					s[39]="'img/d04.gif'";
					break;
				case "���ѩ":
					s[39]="'img/d06.gif'";
					break;
				case "С��":
					s[39]="'img/d07.gif'";
					break;
				case "����":
					s[39]="'img/d08.gif'";
					break;
				case "����":
					s[39]="'img/d09.gif'";
					break;
				case "����":
					s[39]="'img/d10.gif'";
					break;
				case "����":
					s[39]="'img/d11.gif'";
					break;
				case "�ش���":
					s[39]="'img/d12.gif'";
					break;
				case "��ѩ":
					s[39]="'img/d13.gif'";
					break;
				case "Сѩ":
					s[39]="'img/d14.gif'";
					break;
				case "��ѩ":
					s[39]="'img/d15.gif'";
					break;
				case "��ѩ":
					s[39]="'img/d16.gif'";
					break;
				case "��ѩ":
					s[39]="'img/d17.gif'";
					break;
				case "��":
					s[39]="'img/d18.gif'";
					break;
				case "��":
					s[39]="'img/d53.gif'";
					break;
				
				default:
					break;
				}
                
                
                for (int i = 0; i < future.size(); i++) { 
                	JSONObject s1 =  (JSONObject)future.get(i);
                	
                		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(s1.getString("date")); 
                		String now = new SimpleDateFormat("MM��dd��").format(date);
                		int a = 8+(4*i);
                		s[a] = now; 
                		
            			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            	        String[] weekDays = {"������", "����һ", "���ڶ�", "������", "������", "������", "������"};
            	        Calendar cal = Calendar.getInstance();
            	        cal.setTime(date);
            	        
            	        //һ�ܵĵڼ���
            	        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            	        if (w < 0){
            	            w = 0;
            	        }
            	        int e=28+i;
            	        s[e] = weekDays[w];
            	        
            	        
                	
                		int c = 10+(4*i);
                		String wt = s1.getString("weather");
                		if(wt.indexOf("ת")>0){
                			s[c] = wt.substring(0, wt.indexOf("ת"));
                		}else{
                			s[c] = wt;
                		}
                		int b = 9+(4*i);
                		
                		switch (s[c]) {
        				case "��":
        					s[b]="'img/d00.gif'";
        					break;
        				case "����":
        					s[b]="'img/d01.gif'";
        					break;
        				case "��":
        					s[b]="'img/d02.gif'";
        					break;
        				case "����":
        					s[b]="'img/d03.gif'";
        					break;
        				case "������":
        					s[b]="'img/d04.gif'";
        					break;
        				case "���ѩ":
        					s[b]="'img/d06.gif'";
        					break;
        				case "С��":
        					s[b]="'img/d07.gif'";
        					break;
        				case "����":
        					s[b]="'img/d08.gif'";
        					break;
        				case "����":
        					s[b]="'img/d09.gif'";
        					break;
        				case "����":
        					s[b]="'img/d10.gif'";
        					break;
        				case "����":
        					s[b]="'img/d11.gif'";
        					break;
        				case "�ش���":
        					s[b]="'img/d12.gif'";
        					break;
        				case "��ѩ":
        					s[b]="'img/d13.gif'";
        					break;
        				case "Сѩ":
        					s[b]="'img/d14.gif'";
        					break;
        				case "��ѩ":
        					s[b]="'img/d15.gif'";
        					break;
        				case "��ѩ":
        					s[b]="'img/d16.gif'";
        					break;
        				case "��ѩ":
        					s[b]="'img/d17.gif'";
        					break;
        				case "��":
        					s[b]="'img/d18.gif'";
        					break;
        				case "��":
        					s[b]="'img/d53.gif'";
        					break;
        				
        				default:
        					break;
        				}
                		
                		
                		
                		
                		
                		
                		
                		
                		int d = 11+(4*i);
                		String dt = s1.getString("direct");
                		if(dt.indexOf("ת")>0){
                			s[d] = dt.substring(0, dt.indexOf("ת"));
                		}else{
                			s[d] = dt;
                		}
                		
                		int g = 33+i;
                		String tp = s1.getString("temperature");
                		s[g] = tp.substring(tp.indexOf("/")+1, tp.indexOf("��"));
                		
                    System.out.println("currentfuture:" + s1);
                } 
                 
                
                
                return s;
            } else {
                System.out.println("���ýӿ�ʧ�ܣ�" + jsonObject.getString("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
        
        return s;
    }

    /**
     * get��ʽ��http����
     *
     * @param httpUrl �����ַ
     * @return ���ؽ��
     */
    public static String doGet(String httpUrl, String queryParams) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String result = null;// ���ؽ���ַ���
        try {
            // ����Զ��url���Ӷ���
            URL url = new URL(new StringBuffer(httpUrl).append("?").append(queryParams).toString());
            // ͨ��Զ��url���Ӷ����һ�����ӣ�ǿת��httpURLConnection��
            connection = (HttpURLConnection) url.openConnection();
            // �������ӷ�ʽ��get
            connection.setRequestMethod("GET");
            // �������������������ĳ�ʱʱ�䣺15000����
            connection.setConnectTimeout(5000);
            // ���ö�ȡԶ�̷��ص�����ʱ�䣺60000����
            connection.setReadTimeout(6000);
            // ��������
            connection.connect();
            // ͨ��connection���ӣ���ȡ������
            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                // ��װ����������ָ���ַ���
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                // �������
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
            // �ر���Դ
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
                connection.disconnect();// �ر�Զ������
            }
        }
        return result;
    }

    /**
     * ��map��תΪ���������
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