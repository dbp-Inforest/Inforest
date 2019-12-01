package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.InforestUserDAO;
import model.dto.InforestUser;
import model.dto.InforestUser;

public class InforestUserDAO {

	private JDBCUtil jdbcUtil = null;

	private static String query = "SELECT INFORESTUSER.USER_ID, " +
								  "       INFORESTUSER.PASSWORD, " +
								  "       INFORESTUSER.NAME, " +
								  "       INFORESTUSER.AGE " +
								  "       INFORESTUSER.GENDER, " +
								  "       INFORESTUSER.POSITION, " +
								  "       INFORESTUSER.INTRODUCE " +
								  "FROM INFOREST_USER ";
	

	public List<InforestUser> getInforestUserList() {
		String allQuery = query;	
		jdbcUtil.setSql(allQuery);		// JDBCUtil 에 query 설정
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행			
			List<InforestUser> list = new ArrayList<InforestUser>();		// InforestUser 객체들을 담기위한 list 객체
			while (rs.next()) {	
				InforestUser dto = new InforestUser();		// 하나의 InforestUser 객체 생성 후 정보 설정
				dto.setUserId(rs.getString("USER_ID"));
				dto.setPassword(rs.getString("PASSWORD"));
				dto.setName(rs.getString("NAME"));
				dto.setAge(rs.getInt("AGE"));
				dto.setGender(rs.getInt("GENDER"));
				dto.setPosition(rs.getInt("POSITION"));
				dto.setIntroduce(rs.getString("INTRODUCE"));
				list.add(dto);		// list 객체에 정보를 설정한 InforestUser 객체 저장	
			} // 유저 정보를 저장한 dto들의 목록을 반환
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		return null;	
	}

	// InforestUser 객체에 담겨 있는 태블릿의 정보를 기반으로 태블릿 정보를 Inforest_User 테이블에 삽입하는 메소드
	public int insertInforestUser(InforestUser user) {
		int result = 0;
		String insertQuery = "INSERT INTO INFOREST_USER (USER_ID, PASSWORD, NAME, AGE, GENDER, POSITION, INTRODUCE) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?) ";
		
		// query 문에 사용할 매개변수 값을 갖는 매개변수 배열 생성
		Object[] param = new Object[] { user.getUserId(), user.getPassword(), user.getName(), user.getAge(), user.getGender(),
										user.getPosition(), user.getIntroduce() };	
		
		jdbcUtil.setSql(insertQuery);			// JDBCUtil 에 insert 문 설정
		jdbcUtil.setParameters(param);			// JDBCUtil 에 매개변수 설정
				
		try {				
			result = jdbcUtil.executeUpdate();		// insert 문 실행
			System.out.println(user.getUserId() + " 의 정보가 삽입되었습니다.");
		} catch (SQLException ex) {
			System.out.println("입력오류 발생!!!");
			if (ex.getErrorCode() == 1)
				System.out.println("동일한 유저정보가 이미 존재합니다."); 
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		return result;		// insert 에 의해 반영된 레코드 수 반환
	}

	public int updateInforestUser(InforestUser user) {
		String updateQuery = "UPDATE INFOREST_USER SET ";
		int index = 0;
		Object[] tempParam = new Object[15];		// update 문에 사용할 매개변수를 저장할 수 있는 임시 배열
		
		if (user.getUserId() != null) {		// 이름이 설정되어 있을 경우
			updateQuery += "USER_ID = ?, ";		// update 문에 이름 수정 부분 추가
			tempParam[index++] = user.getUserId();		// 매개변수에 수정할 이름 추가
		}
		if (user.getPassword() != null) {		// 브랜드가 설정되어 있을 경우
			updateQuery += "PASSWORD = ?, ";		// update 문에 브랜드 수정 부분 추가
			tempParam[index++] = user.getPassword();		// 매개변수에 수정할 브랜드 추가
		}
		if (user.getName() != null) {		// 색깔이 설정되어 있을 경우
			updateQuery += "NAME = ?, ";		// update 문에 색깔 수정 부분 추가
			tempParam[index++] = user.getName();		// 매개변수에 수정할 색깔 추가
		}
		if (user.getAge() <= 0) {		// 이름이 설정되어 있을 경우
			updateQuery += "AGE = ?, ";		// update 문에 이름 수정 부분 추가
			tempParam[index++] = user.getAge();		// 매개변수에 수정할 이름 추가
		}
		if (user.getGender() == 3) {		// 종류가 설정되어 있을 경우
			updateQuery += "GENDER = ?, ";		// update문에 종류 수정 부분 추가
			tempParam[index++] = user.getGender();		// 매개변수에 수정할 종류 추가
		}
		if (user.getPosition() >= 0) {		// 출시일이 설정되어 있을 경우
			updateQuery += "POSITION = ?, ";		// update문에 출시일 수정 부분 추가
			tempParam[index++] = user.getPosition();		// 매개변수에 수정할 출시일 추가
		}
		if (user.getIntroduce() != null) {		// 배터리가 설정되어 있을 경우
			updateQuery += "INTRODUCE = ?, ";		// update 문에 배터리 수정 부분 추가
			tempParam[index++] = user.getIntroduce();		// 매개변수에 수정할 배터리 추가
		}
		
		updateQuery += "WHERE USER_ID = ? ";		// update 문에 조건 지정
		updateQuery = updateQuery.replace(", WHERE", " WHERE");		// update 문의 where 절 앞에 있을 수 있는 , 제거
		
		tempParam[index++] = user.getUserId();		// 찾을 조건에 해당하는 학번에 대한 매개변수 추가
		
		Object[] newParam = new Object[index];
		for (int i=0; i < newParam.length; i++)		// 매개변수의 개수만큼의 크기를 갖는 배열을 생성하고 매개변수 값 복사
			newParam[i] = tempParam[i];
		
		jdbcUtil.setSql(updateQuery);			// JDBCUtil에 update 문 설정
		jdbcUtil.setParameters(newParam);		// JDBCUtil 에 매개변수 설정
		
		try {
			int result = jdbcUtil.executeUpdate();		// update 문 실행
			return result;			// update 에 의해 반영된 레코드 수 반환
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		return 0;
	}

	public int deleteInforestUser(int uId) {
		String deleteQuery = "DELETE FROM Inforest_User WHERE USER_ID = ?";
		
		jdbcUtil.setSql(deleteQuery);			// JDBCUtil 에 query 문 설정
		Object[] param = new Object[] { uId };
		jdbcUtil.setParameters(param);			// JDBCUtil 에 매개변수 설정
		
		try {
			int result = jdbcUtil.executeUpdate();		// delete 문 실행
			return result;						// delete 에 의해 반영된 레코드 수 반환
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();		
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}
		return 0;
	}


	public List <InforestUser> getUserByPosititon(String position) {
		String searchQuery = query + "WHERE INFOREST_USER.POSITION = ?";
		Object[] param = new Object[] { position };

		jdbcUtil.setSql(searchQuery);
		jdbcUtil.setParameters(param);
			
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<InforestUser> list = new ArrayList<InforestUser>();	
			while (rs.next()) {
				InforestUser dto = new InforestUser();
				dto.setUserId(rs.getString("USER_ID"));
				dto.setPassword(rs.getString("PASSWORD"));
				dto.setName(rs.getString("NAME"));
				dto.setAge(rs.getInt("AGE"));
				dto.setGender(rs.getInt("GENDER"));
				dto.setPosition(rs.getInt("POSITION"));
				dto.setIntroduce(rs.getString("INTRODUCE"));
				list.add(dto);
			}				
			return list;	
		} catch (Exception ex) {					
			ex.printStackTrace();				
		} finally {				
			jdbcUtil.close();
		}
		return null;
	}

	
	public InforestUser getInforestUserById(String uId) {
		String searchQuery = query + "WHERE INFOREST_USER.USER_ID = ?";
		Object[] param = new Object[] { uId };

		jdbcUtil.setSql(searchQuery);
		jdbcUtil.setParameters(param);
			
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			InforestUser dto = new InforestUser();
			while (rs.next()) {
				dto.setUserId(rs.getString("USER_ID"));
				dto.setPassword(rs.getString("PASSWORD"));
				dto.setName(rs.getString("NAME"));
				dto.setAge(rs.getInt("AGE"));
				dto.setGender(rs.getInt("GENDER"));
				dto.setPosition(rs.getInt("POSITION"));
				dto.setIntroduce(rs.getString("INTRODUCE"));
			}				
			return dto;			
		} catch (Exception ex) {					
			ex.printStackTrace();				
		} finally {				
			jdbcUtil.close();
		}
		return null;
	}

	public boolean existingUser(String userId) {
		InforestUser userDTO = null;
		userDTO = getInforestUserById(userId);
		if(userDTO != null)
			return true;
		return false;
	}
}
