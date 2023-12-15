package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.dto.HobbyDTO;
import db.dto.PersonInfoDTO;
import db.util.DBConnectionManager;

public class HobbyDAO {

	//필드변수
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	public List<HobbyDTO> selectAllTHobbyList(){
		conn = DBConnectionManager.connectDB();

		String sql =  " SELECT * FROM t_hobby_list" ;

		List<HobbyDTO> hobbyList = null;
		//
		try {
			psmt = conn.prepareStatement(sql);
			

			rs = psmt.executeQuery();
			//hobbyList = new ArrayList<HobbyDTO>();
			//if (hobbyList.size() >0)
			while(rs.next()) { 
				
				if(hobbyList == null) { // null없는거고 null 아니면 뭐라도 담겨!
					hobbyList = new ArrayList<HobbyDTO>();
				}
				
				HobbyDTO hobbyDTO = new HobbyDTO(rs.getInt("id"), rs.getInt("no"), rs.getString("hobby"), rs.getInt("prefer"));

				hobbyList.add(hobbyDTO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeDB(conn, psmt, rs);
		}

		return hobbyList;
	}
	
	public void selectTHobbyList() {

		//DB연결코드
		conn = DBConnectionManager.connectDB();

		String sql =  " SELECT id, no, hobby, prefer FROM t_hobby_list "
				+ " WHERE id = ? " ;  //매개변수를 활용한 sql 쿼리문
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, 6); //(?위치 인덱스, 세팅할 값)
			rs = psmt.executeQuery(); //준비된 sql 쿼리문 실행!
			while(rs.next()) { 

				int id = rs.getInt("id");
				int no = rs.getInt("no");
				String hobby = rs.getString("hobby");
				int prefer = rs.getInt("prefer");
				System.out.println(id + " " + no + " " + hobby + " " + prefer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeDB(conn, psmt, rs);
		}
	}

	public void selectTHobbyListById(int paramId) { //parameter

		conn = DBConnectionManager.connectDB();
		
		String sql =  " SELECT id hobby_id, no hobby_no, hobby, prefer FROM t_hobby_list "
				+ " WHERE id = ? " ;  //매개변수를 활용한 sql 쿼리문

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, paramId); //(?위치 인덱스, 세팅할 값)

			rs = psmt.executeQuery(); //준비된 sql 쿼리문 실행!

			while(rs.next()) { 

				int id = rs.getInt("id");
				int no = rs.getInt("no");
				String hobby = rs.getString("hobby");
				int prefer = rs.getInt("prefer");

				System.out.println(id + " " + no + " " + hobby + " " + prefer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeDB(conn, psmt, rs);
		}
	}

	public void selectTHobbyListByName(String paramName) { //parameter
		
		conn = DBConnectionManager.connectDB();
		
		String sql =  " SELECT hl.id, pi.name, hl.no, hl.hobby, hl.prefer "
				+ " FROM t_hobby_list hl, t_person_info pi "
				+ " WHERE hl.id = pi.id "
				+ " AND pi.name = ? " ;  //매개변수를 활용한 sql 쿼리문

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, paramName); //(?위치 인덱스, 세팅할 값)

			rs = psmt.executeQuery(); //준비된 sql 쿼리문 실행!

			while(rs.next()) { 

				int id = rs.getInt("id");
				String name = rs.getString("name");
				int no = rs.getInt("no");
				String hobby = rs.getString("hobby");
				int prefer = rs.getInt("prefer");

				//System.out.println(id + " " + name + " "+ no + " " + hobby + " " + prefer);
				System.out.printf("%d %s %d %s %d\n", id, name, no, hobby, prefer);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeDB(conn, psmt, rs);
		}
	}

	public void  selectTHobbyListByPrefer1(int moreThanPrefer) {
		
		conn = DBConnectionManager.connectDB();
		
		String sql =  " SELECT hl.id, pi.name, hl.no, hl.hobby, hl.prefer "
				+ " FROM t_hobby_list hl, t_person_info pi "
				+ " WHERE hl.id = pi.id "
				+ " AND hl.prefer >= ? " ;  //매개변수를 활용한 sql 쿼리문

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, moreThanPrefer); //(?위치 인덱스, 세팅할 값)
			rs = psmt.executeQuery(); //준비된 sql 쿼리문 실행!

			while(rs.next()) { 
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int no = rs.getInt("no");
				String hobby = rs.getString("hobby");
				int prefer = rs.getInt("prefer");

				System.out.printf("%d %s %d %s %d\n", id, name, no, hobby, prefer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeDB(conn, psmt, rs);
		}
	}


	public void  selectTHobbyListByPrefer2(boolean isHigh) {
		
		conn = DBConnectionManager.connectDB();
		
		String sql =  " SELECT hl.id, pi.name, hl.no, hl.hobby, hl.prefer "
				+ " FROM t_hobby_list hl, t_person_info pi "
				+ " WHERE hl.id = pi.id "
				+ " AND hl.prefer BETWEEN ? AND ? ";  // 1 5  ,  6  10

		try {
			psmt = conn.prepareStatement(sql);
			//Connection 활용해서 sql 명령을 실행하는 객체

			if(isHigh) {
				psmt.setInt(1, 6);
				psmt.setInt(2, 10);
			} else {
				psmt.setInt(1, 1);
				psmt.setInt(2, 5);
			}

			rs = psmt.executeQuery(); //준비된 sql 쿼리문 실행!

			while(rs.next()) { 

				int id = rs.getInt("id");
				String name = rs.getString("name");
				int no = rs.getInt("no");
				String hobby = rs.getString("hobby");
				int prefer = rs.getInt("prefer");
				System.out.printf("%d %s %d %s %d\n", id, name, no, hobby, prefer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeDB(conn, psmt, rs);
		}
	}

}
