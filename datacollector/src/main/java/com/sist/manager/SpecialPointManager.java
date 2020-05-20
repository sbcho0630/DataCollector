package com.sist.manager;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

import com.sist.dao.SpecialPointVO;
import com.sist.dao.AllDAO;

public class SpecialPointManager {
	public List<String> movieLinkData() {

		List<String> list = new ArrayList<String>();
		try {
			for (int i = 1; i <= 40; i++) {
				Document doc = Jsoup.connect(
						"https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=pnt&date=20200517&page=" + i + "").get();
				Elements link = doc.select("td.title div.tit5 a");
				for (int j = 0; j < link.size(); j++) {
					String strLink = link.get(j).attr("href");
					String mLink = strLink.replace("basic", "point");
					list.add("https://movie.naver.com" + mLink);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return list;
	}

	public void SpecialPointData() {
		try {
			List<String> list = movieLinkData();
			int k = 1;
			AllDAO dao = new AllDAO();
			for (String url : list) {
				try {
					Document doc = Jsoup.connect(url).get();
					// System.out.println("doc="+doc);
					Elements people_id = doc.select("dl.p_review dt a");
					SpecialPointVO vo = new SpecialPointVO();
					vo.setMovie_id(Integer.parseInt(url.substring(url.lastIndexOf("=") + 1)));
					System.out.println("==========영화정보==========");
					System.out.println("movie_id=" + vo.getMovie_id());
					for (int i = 0; i <= people_id.size(); i++) {
						Element name = doc.select("dl.p_review dt a").get(i);
						String name2 = name.text();
						vo.setName(name2);
						System.out.println("name=" + vo.getName());

						Element score = doc.select("div.reporter_score em").get(i);
						String score2 = score.text();
						vo.setScore(Double.parseDouble(score2));
						System.out.println("score=" + vo.getScore());

						Element content = doc.select("p.tx_report").get(i);
						String content2 = content.text();
						vo.setContent(content2);
						System.out.println("content=" + vo.getContent());

						

						dao.SpecialPointInsert(vo);

					}
					
					k++;
					System.out.println("k=" + k);

				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
		}
	}

}
