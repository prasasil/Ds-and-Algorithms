package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {

  

    public void crawl(String sourceUrl) throws IOException {
        HashSet<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(sourceUrl);
        set.add(sourceUrl);

        while(!queue.isEmpty())
        {
            String url = queue.peek();

            for(String dest : parseUrl(url))
            {
                if(!set.contains(dest)){
                queue.add(dest);
                set.add(dest);
            }}
        }
    }

    private ArrayList<String> parseUrl(String url) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        InputStreamReader in = new InputStreamReader(new URL(url).openConnection().getInputStream());
        BufferedReader br = new BufferedReader(in);
        String input = "";
        while((input = br.readLine())!=null)
        {
           String regex = "http://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            while(matcher.find())
            {
                String mathedString = matcher.group();
                list.add(mathedString);
            }
        }

        return list;
    }





}
