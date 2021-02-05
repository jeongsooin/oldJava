<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="org.json.simple.JSONArray"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.io.OutputStreamWriter"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="java.net.URL"%>
<%

	response.setCharacterEncoding("UTF-8");
	request.setCharacterEncoding("UTF-8");
	
	String ApiKey = "AAAAozwTYn4:APA91bFtmQLcvx1sb18BE54HbVjNPJga37O0b3s9B-4SSiriosl3V9CGUAwj7CxVtBwfNWLfFLMiWdtuFWzkj9wPrHz_v4jIDrvknUWUB4-azKjAL7OsRc5HaQFHTN2Ej4j_2xInBkCZ";
	String fcmURL = "https://fcm.googleapis.com/fcm/send";
	
	String to = request.getParameter("to");
	String title = request.getParameter("title");
	String body = request.getParameter("body");

	try {
		
		URL url = new URL(fcmURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + ApiKey);
		conn.setRequestProperty("Content-Type", "application/json");
		
		JSONObject json = new JSONObject();
		
		JSONObject notification = new JSONObject();
		notification.put("title", title);
		notification.put("body", body);

		
		json.put("to", to);
		json.put("notification", notification);
		
		try {
			
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(json.toString());
			wr.flush();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String output;
			System.out.println("Output From Server ..... \n");
			
			while ((output = br.readLine()) != null) {
				System.out.println(output);	
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
			out.println("ERROR " + "E2 " + e2.getMessage());
		}
		
		out.println("SUCCESS");
		
	} catch (Exception e1) {
		e1.printStackTrace();
		out.println("ERROR " + "E1 " + e1.getMessage());
	}
%>