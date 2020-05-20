package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AllDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";

	public AllDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception ex) {
		}
	}

	public void disConnection() {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			// TODO: handle exception
		}
	}

	public void NetizenEvaluationTrendInsert(NetizenEvaluationTrendVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO Netizen_Evaluation_Trend (movie_id,evaluation_point,people_count,male_rating,female_rating,age_10,age_20,age_30,age_40,age_50,production_point,acting_point,story_point,visual_point,ost_point) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getMovie_id());
			ps.setDouble(2, vo.getEvaluation_point());
			ps.setInt(3, vo.getPeople_count());
			ps.setDouble(4, vo.getMale_rating());
			ps.setDouble(5, vo.getFemale_rating());
			ps.setDouble(6, vo.getAge_10());
			ps.setDouble(7, vo.getAge_20());
			ps.setDouble(8, vo.getAge_30());
			ps.setDouble(9, vo.getAge_40());
			ps.setDouble(10, vo.getAge_50());
			ps.setInt(11, vo.getProduction_point());
			ps.setInt(12, vo.getActing_point());
			ps.setInt(13, vo.getStory_point());
			ps.setInt(14, vo.getVisual_point());
			ps.setInt(15, vo.getOst_point());

			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}

	}
	public void SpecialPointInsert(SpecialPointVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO SpecialPoint (movie_id,name,score,content) VALUES(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getMovie_id());
			ps.setString(2, vo.getName());
			ps.setDouble(3, vo.getScore());
			ps.setString(4, vo.getContent());
			
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}

	}
	public void AudienceEvaluationTrendInsert(AudienceEvaluationTrendVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO Audience_Evaluation_Trend (movie_id,evaluation_point,people_count,male_rating,female_rating,age_10,age_20,age_30,age_40,age_50,production_point,acting_point,story_point,visual_point,ost_point) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getMovie_id());
			ps.setDouble(2, vo.getEvaluation_point());
			ps.setInt(3, vo.getPeople_count());
			ps.setDouble(4, vo.getMale_rating());
			ps.setDouble(5, vo.getFemale_rating());
			ps.setDouble(6, vo.getAge_10());
			ps.setDouble(7, vo.getAge_20());
			ps.setDouble(8, vo.getAge_30());
			ps.setDouble(9, vo.getAge_40());
			ps.setDouble(10, vo.getAge_50());
			ps.setInt(11, vo.getProduction_point());
			ps.setInt(12, vo.getActing_point());
			ps.setInt(13, vo.getStory_point());
			ps.setInt(14, vo.getVisual_point());
			ps.setInt(15, vo.getOst_point());

			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}

	}
	public void WatchingTrendInsert(WatchingTrendVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO Watching_Trend (movie_id,male_rating,female_rating,age_10,age_20,age_30,age_40,age_50) VALUES(?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getMovie_id());
			ps.setDouble(2, vo.getMale_rating());
			ps.setDouble(3, vo.getFemale_rating());
			ps.setDouble(4, vo.getAge_10());
			ps.setDouble(5, vo.getAge_20());
			ps.setDouble(6, vo.getAge_30());
			ps.setDouble(7, vo.getAge_40());
			ps.setDouble(8, vo.getAge_50());

			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}

	}

}
