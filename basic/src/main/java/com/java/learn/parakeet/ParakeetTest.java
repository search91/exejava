package com.java.learn.parakeet;

import java.util.HashMap;
import java.util.Map;
import com.netease.nlp.module.*;
import com.netease.nlp.utils.json.JSONArray;
import com.netease.nlp.utils.json.JSONObject;



public class ParakeetTest {
	public static void main(String[] args) {
		

		Map<String, Object> params = new HashMap<String, Object>();
        String text = "发我";
        params.put("text", text);
        params.put("name", "hydm_hzliuzhujie");
        params.put("secretKey", "0590cc7814660ba34d473d9dca84216da750b202");
        
        Token token = new Token();
        String resStr = token.sendRequest(token.getUrl(), params, "POST");
        System.out.println(resStr);

        JSONObject json = new JSONObject(resStr);
        String message = json.getString("message");
        Long code = json.getLong("code"); //1fail  0success
        System.out.println(message + code);
        JSONArray bodyArray = json.getJSONArray("body"); 
        int length = bodyArray.length();
        for (int i = 0; i < length; i++){
	        JSONObject body = bodyArray.getJSONObject(i);
	        System.out.println("key:" + body.getString("word"));
	        System.out.println("nature:" + body.getString("natureCN"));
	        
        }
        
        

        
        
/*
		
        Map<String, Object> params = new HashMap<String, Object>();
        ArrayList<Object> data = new ArrayList<Object>();
        // batch/Token
        Map<String, String> token1 = new HashMap<String, String>();
        token1.put("text", "发到我");
        data.add(token1);
        // batch/Token
        Map<String, String> token2 = new HashMap<String, String>();
        token2.put("text", "ID:发达");
        data.add(token2);

        JSONArray json = new JSONArray(data);

        params.put("data", json.toString());
        params.put("name", "hydm_hzliuzhujie");
        params.put("secretKey", "0590cc7814660ba34d473d9dca84216da750b202");

        TokenBatch token = new TokenBatch();
        System.out.println(token.sendRequest(token.getUrl(), params, "POST"));
      
*/

	
}

}
