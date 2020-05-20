package com.sist.manager;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.AllDAO;
import com.sist.dao.SpecialPointVO;

public class SpecialPointManager2 {
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
					Elements people_count = doc.select("div.score_result div.score_reple");
					SpecialPointVO vo = new SpecialPointVO();
					vo.setMovie_id(Integer.parseInt(url.substring(url.lastIndexOf("=") + 1)));
					System.out.println("==========영화정보==========");
					System.out.println("movie_id=" + vo.getMovie_id());

					for (int i = 0; i <= people_count.size(); i++) {
						Element name1 = doc.select("div.score_reple dd").get(i);
						String name3 = name1.text().substring(2);
						vo.setName(name3);
						System.out.println("name=" + name3);

						Element score1 = doc.select("div.score_result em").get(i);
						String score3 = score1.text();
						vo.setScore(Double.parseDouble(score3));

						Element content1 = doc.select("div.score_reple p").get(i);
						String content3 = content1.text();
						vo.setContent(content3);
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
