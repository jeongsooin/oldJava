package com.study.android.signin_signup_sample;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translation extends AsyncTask<String, Void, String> {

        String clientId = "uOsR2TyFQbwC5NrT5Png";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "ByU8CaES6A";//애플리케이션 클라이언트 시크릿값";
        StringBuffer response;
        String result;

        @Override
        protected String doInBackground(String... strings) {

                try {
                        String text = URLEncoder.encode(strings[0], "UTF-8");
                        String apiURL = "https://openapi.naver.com/v1/language/translate";
                        URL url = new URL(apiURL);
                        HttpURLConnection con = (HttpURLConnection)url.openConnection();
                        con.setRequestMethod("POST");
                        con.setRequestProperty("X-Naver-Client-Id", clientId);
                        con.setRequestProperty("X-Naver-Client-Secret", clientSecret);


                        String language = "";
                        switch (strings[1]) {
                                case "English":
                                        language = "en";
                                        break;
                                case "汉语":
                                        language = "zh-CN";
                                        break;
                                case "日本語":
                                        language = "ja";
                                        break;
                        }

                        // post request
                        String postParams = "source=ko&target="+language+"&text=" + text;
                        con.setDoOutput(true);
                        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                        wr.writeBytes(postParams);
                        wr.flush();
                        wr.close();

                        int responseCode = con.getResponseCode();
                        BufferedReader br;

                        if(responseCode==200) { // 정상 호출
                                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        } else {  // 에러 발생
                                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                        }

                        String inputLine;
                        response = new StringBuffer();

                        while ((inputLine = br.readLine()) != null) {
                                response.append(inputLine);
                        }

                        br.close();

                        JSONObject object1 = new JSONObject(response.toString());
                        JSONObject object2 = object1.getJSONObject("message");
                        JSONObject object3 = object2.getJSONObject("result");
                        result = object3.getString("translatedText");


                } catch (Exception e) {
                        System.out.println(e);
                }

                return result;
        }

}
