package com.example.anrdoid.lastapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {


    public static List<News> fetchNews(String theUrl) {
        URL url = createUrl(theUrl);

        String jsonres = null;
        try {
            jsonres = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();

        }
        List<News> newsList = extractFeatureFromJson(jsonres);

        return newsList;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<News> extractFeatureFromJson(String newsJSON) {
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }
        List<News> newss = new ArrayList<>();
        try {
            JSONObject jsonob = new JSONObject(newsJSON);
            JSONObject responses = jsonob.getJSONObject("response");
            JSONArray newsArray = responses.getJSONArray("results");

            for (int i = 0; i < newsArray.length(); i++) {
                JSONObject currentindex = newsArray.getJSONObject(i);
                String Title = currentindex.getString("webTitle");
                String secname = currentindex.getString("sectionName");
                String date = currentindex.getString("webPublicationDate");
                String url = currentindex.getString("webUrl");
                String type = currentindex.getString("type");
                String pillar = currentindex.getString("pillarName");
                JSONArray tagsa = currentindex.getJSONArray("tags");
                String author="";
                News Inew;
                if (tagsa.length()!= 0) {
                    JSONObject ctag = tagsa.getJSONObject(0);
                    author = ctag.getString("webTitle");
                    Inew=new News(Title,secname,date,url,pillar,type,author);
                }else{
                    author = " ";
                    Inew = new News(Title, secname, date, url, pillar, type);
                }

                newss.add(Inew);
            }

        } catch (JSONException e) {

            Log.e("QueryUtils", "Problem parsing the newss JSON results", e);
        }
        return newss;
    }
}
