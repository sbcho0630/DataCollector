package com.sist.manager;
import java.util.*;
import com.sist.dao.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.NetizenEvaluationTrendVO;
public class NetizenEvaluationTrendManager {
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
	public void NetizenEvaluationData(){
		try {
			List<String> list=movieLinkData();
			int k=1;
			AllDAO dao=new AllDAO();
			for(String url:list){
				try {
					Document doc=Jsoup.connect(url).get();
					//System.out.println("doc="+doc);
					Element evaluation_point = doc.select("div#netizen_point_tab_inner").get(0);
					Element people_count = doc.select("span.user_count em").get(0);
					String peoplec=people_count.text();
					peoplec=peoplec.replaceAll(",", "");
					Element male_rating = doc.selectFirst("div.grp_male strong.graph_point");
					Element female_rating = doc.selectFirst("div.grp_female strong.graph_point");
					Element age_10 = doc.select("div.grp_age strong.graph_point").get(0);
					Element age_20 = doc.select("div.grp_age strong.graph_point").get(1);
					Element age_30 = doc.select("div.grp_age strong.graph_point").get(2);
					Element age_40 = doc.select("div.grp_age strong.graph_point").get(3);
					Element age_50 = doc.select("div.grp_age strong.graph_point").get(4);
					Element production_point = doc.select("li.point01 span.grp_score").get(0);
					String pp = production_point.text();
					pp = pp.replaceAll("%", "");
					Element acting_point = doc.select("li.point02 span.grp_score").get(0);
					String ap = acting_point.text();
					ap = ap.replaceAll("%", "");
					Element story_point = doc.select("li.point03 span.grp_score").get(0);
					String sp = story_point.text();
					sp = sp.replaceAll("%", "");
					Element visual_point = doc.select("li.point04 span.grp_score").get(0);
					String vp = visual_point.text();
					vp = vp.replaceAll("%", "");
					Element ost_point = doc.select("li.point05 span.grp_score").get(0);
					String op = ost_point.text();
					op = op.replaceAll("%", "");
					
					NetizenEvaluationTrendVO vo=new NetizenEvaluationTrendVO();
					vo.setMovie_id(Integer.parseInt(url.substring(url.lastIndexOf("=")+1)));
					System.out.println("vo시작");
					double ep = Double.parseDouble(evaluation_point.text());
					vo.setEvaluation_point(ep);
					System.out.println("evaluation_point=" + evaluation_point.text());
					int pc = Integer.parseInt(peoplec);
					vo.setPeople_count(pc);
					System.out.println("people_count=" + people_count.text());
					double mr = Double.parseDouble(male_rating.text());
					vo.setMale_rating(mr);
					System.out.println("male_rating=" + male_rating.text());
					double fr = Double.parseDouble(female_rating.text());
					vo.setFemale_rating(fr);
					System.out.println("female_rating=" + female_rating.text());
					double age10 = Double.parseDouble(age_10.text());
					vo.setAge_10(age10);
					System.out.println("age_10=" + age_10.text());
					double age20 = Double.parseDouble(age_20.text());
					vo.setAge_20(age20);
					System.out.println("age_20=" + age_20.text());
					double age30 = Double.parseDouble(age_30.text());
					vo.setAge_30(age30);
					System.out.println("age_30=" + age_30.text());
					double age40 = Double.parseDouble(age_40.text());
					vo.setAge_40(age40);
					System.out.println("age_40=" + age_40.text());
					double age50 = Double.parseDouble(age_50.text());
					vo.setAge_50(age50);
					System.out.println("age_50=" + age_50.text());
					int productionp = Integer.parseInt(pp);
					vo.setProduction_point(productionp);
					System.out.println("production_point=" + pp);
					int actingp = Integer.parseInt(ap);
					vo.setActing_point(actingp);
					System.out.println("acting_point=" + ap);
					int storyp = Integer.parseInt(sp);
					vo.setStory_point(storyp);
					System.out.println("story_point=" + sp);
					int visualp = Integer.parseInt(vp);
					vo.setVisual_point(visualp);
					System.out.println("visual_point=" + vp);
					int ostp = Integer.parseInt(op);
					vo.setOst_point(ostp);
					System.out.println("ost_point=" + op);
					System.out.println("==========영화정보==========");
					System.out.println("Movie_id="+vo.getMovie_id());
					System.out.println("k="+k);
					
					//EvaluationTrend 수집 끝
					 dao.NetizenEvaluationTrendInsert(vo);
					k++;
				} catch (Exception e) {}
			}
		} catch (Exception e) {}
	}
	
}
