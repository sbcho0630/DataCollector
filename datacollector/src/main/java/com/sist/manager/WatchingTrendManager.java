package com.sist.manager;
import java.util.*;
import com.sist.dao.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.AudienceEvaluationTrendVO;
public class WatchingTrendManager {
	public List<String> movieLinkData(){
		
		List<String> list=new ArrayList<String>();
		try {
			for(int i=1;i<=40;i++){
				Document doc=Jsoup.connect("https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=pnt&date=20200517&page="+i+"").get();
				Elements link=doc.select("td.title div.tit5 a");
				for(int j=0;j<link.size();j++){
					String strLink=link.get(j).attr("href");
					String mLink = strLink.replace("basic", "point");
					list.add("https://movie.naver.com"+mLink);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return list;
	}
	
	public void WatchingTrendData(){
		try {
			List<String> list=movieLinkData();
			int k=1;
			AllDAO dao=new AllDAO();
			for(String url:list){
				try {
					
							Document doc = Jsoup.connect(url).get();
							 //System.out.println("doc2=" + doc2);

							Element age_10 = doc.select("div.graph_box strong.graph_percent").get(0);
							String age1 = age_10.text();
							String age10 = age1.replaceAll("%", "");
							System.out.println("age_10=" + age10);

							Element age_20 = doc.select("div.graph_box strong.graph_percent").get(1);
							String age2 = age_20.text();
							String age20 = age2.replaceAll("%", "");
							System.out.println("age_20=" + age20);

							Element age_30 = doc.select("div.graph_box strong.graph_percent").get(2);
							String age3 = age_30.text();
							String age30 = age3.replaceAll("%", "");
							System.out.println("age_30=" + age30);

							Element age_40 = doc.select("div.graph_box strong.graph_percent").get(3);
							String age4 = age_40.text();
							String age40 = age4.replaceAll("%", "");
							System.out.println("age_40=" + age40);

							Element age_50 = doc.select("div.graph_box strong.graph_percent").get(4);
							String age5 = age_50.text();
							String age50 = age5.replaceAll("%", "");
							System.out.println("age_50=" + age50);

							
							String doc1 = doc.toString();
							int sper = doc1.indexOf("sPer") + 5;
							String atemp = doc1.substring(sper, sper + 10);
							atemp=atemp.substring(atemp.indexOf(":")+1,atemp.indexOf("}"));
							String a = atemp.trim();
							double male_rating = Double.parseDouble(a);
							System.out.println("malerating=" + male_rating);

							int sper2 = doc1.indexOf("sPer", sper) + 5;
							String btemp = doc1.substring(sper2, sper2 + 10);
							btemp=btemp.substring(btemp.indexOf(":")+1,btemp.indexOf("}"));
							String b=btemp.trim();
							double female_rating = Double.parseDouble(b);
							System.out.println("femalerating=" + female_rating);
							
							
							WatchingTrendVO vo = new WatchingTrendVO();
							
							vo.setMovie_id(Integer.parseInt(url.substring(url.lastIndexOf("=")+1)));
							System.out.println("movieid="+vo.getMovie_id());
							double age101 = Double.parseDouble(age10);
							vo.setAge_10(age101);

							double age201 = Double.parseDouble(age20);
							vo.setAge_20(age201);

							double age301 = Double.parseDouble(age30);
							vo.setAge_30(age301);

							double age401 = Double.parseDouble(age40);
							vo.setAge_40(age401);

							double age501 = Double.parseDouble(age50);
							vo.setAge_50(age501);
							vo.setMale_rating(male_rating);
							vo.setFemale_rating(female_rating);
							// Watchingtrend 수집 끝
							System.out.println("k="+k);
							dao.WatchingTrendInsert(vo);
					k++;
				} catch (Exception e) {}
			}
		} catch (Exception e) {}
	}
	
}
