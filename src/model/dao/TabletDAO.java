package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.DAOFactory;
import model.dto.*;

import java.sql.*;

import model.dao.*;

public class TabletDAO{

	private JDBCUtil jdbcUtil = null;		// JDBCUtil 객체를 지정하기 위한 변수
	
	// tablet의 기본 정보를 포함하는 query문
	private static String query = "SELECT TABLET.T_BATTERY AS TABLET_BATTERY, " +
								         "TABLET.T_MEMORY AS TABLET_MEMORY, " +
								         "TABLET.T_OS AS TABLET_OS, " +
								         "TABLET.T_SIZE AS TABLET_SIZE, " +
								         "TABLET.PRODUCT_ID AS TABLET_ID, " +
								         "PRODUCT.NAME AS TABLET_NAME, " +
								         "PRODUCT.COLOR AS TABLET_COLOR, " +
								         "PRODUCT.PRICE AS TABLET_PRICE, " +
								         "PRODUCT.BRAND AS TABLET_BRAND, " +
								         "PRODUCT.RELEASED_DATE AS TABLET_RELEASED_DATE, " +
								         "PRODUCT.WEIGHT AS TABLET_WEIGHT, " +
								         "PRODUCT.P_KIND AS TABLET_KIND ";		

	// TabletDAOImpl 생성자
	public TabletDAO() {   
		try {
	        jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }   
	}
	
	// 전체 태블릿을 List으로 반환하는 메소드
	public List<Tablet> getTabletList() {
		// PRODUCT 테이블을 상속받았기 때문에 전체 태블릿 정보를 가져옴
		String allQuery = query + "FROM TABLET, PRODUCT "
							+ "WHERE TABLET.PRODUCT_ID = PRODUCT.PRODUCT_ID";		
		jdbcUtil.setSql(allQuery);		// JDBCUtil 에 query 설정
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행			
			List<Tablet> list = new ArrayList<Tablet>();		// TabletDTO 객체들을 담기위한 list 객체
			while (rs.next()) {	
				Tablet dto = new Tablet();		// 하나의 TabletDTO 객체 생성 후 정보 설정
				dto.setProductId(rs.getString("TABLET_ID"));
				dto.settBattery(rs.getString("TABLET_BATTERY"));
				dto.settMemory(rs.getString("TABLET_MEMORY"));
				dto.settOS(rs.getString("TABLET_OS"));
				dto.settSize(rs.getDouble("TABLET_SIZE"));
				dto.setName(rs.getString("TABLET_NAME"));
				dto.setColor(rs.getString("TABLET_COLOR"));
				dto.setpKind(rs.getInt("TABLET_KIND"));
				dto.setPrice(rs.getString("TABLET_PRICE"));
				dto.setBrand(rs.getString("TABLET_BRAND"));
				dto.setReleased_date(rs.getDate("TABLET_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("TABLET_WEIGHT"));
				list.add(dto);		// list 객체에 정보를 설정한 TabletDTO 객체 저장
			}
			return list;		// 태블릿 정보를 저장한 dto 들의 목록을 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		return null;	
	}
	
	// 태블릿의 이름으로 정보를 검색하여 해당 태블릿의 정보를 갖고 있는 TabletDTO 객체를 반환하는 메소드
	public List<Tablet> getTabletByName(String tname) {
		// 기본 쿼리와 합쳐져  tname을 포함하는 name을 가진 tablet 정보를 가져오는 테이블
		String searchQuery = query + "FROM TABLET, PRODUCT "
				+ "WHERE TABLET.PRODUCT_ID = PRODUCT.PRODUCT_ID AND PRODUCT.NAME LIKE ? ";	 
		jdbcUtil.setSql(searchQuery);	// JDBCUtil 에 query 문 설정
		System.out.println("tname: " + tname);
		Object[] param = new Object[] { "%" + tname + "%" };		// 태블릿을 찾기 위한 조건으로 이름을 설정
		jdbcUtil.setParameters(param);				// JDBCUtil 에 query문의 매개변수 값으로 사용할 매개변수 설정
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행
			List<Tablet> list = new ArrayList<Tablet>();		// TabletDTO 객체들을 담기위한 list 객체
			while (rs.next()) {						// 찾은 카메라의 정보를 TabletDTO 객체에 설정
				Tablet dto = new Tablet();
				dto.setProductId(rs.getString("TABLET_ID"));
				System.out.println("dto : " + dto.getProductId());
				dto.settBattery(rs.getString("TABLET_BATTERY"));
				dto.settMemory(rs.getString("TABLET_MEMORY"));
				dto.settOS(rs.getString("TABLET_OS"));
				dto.settSize(rs.getDouble("TABLET_SIZE"));
				dto.setName(rs.getString("TABLET_NAME"));
				dto.setColor(rs.getString("TABLET_COLOR"));
				dto.setpKind(rs.getInt("TABLET_KIND"));
				dto.setPrice(rs.getString("TABLET_PRICE"));
				dto.setBrand(rs.getString("TABLET_BRAND"));
				dto.setReleased_date(rs.getDate("TABLET_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("TABLET_WEIGHT"));
				list.add(dto);
			}
			return list;				// 찾은 학생의 정보를 담고 있는 TabletDTO 객체 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}
		return null;
	}

	
	// 모델명으로 정보를 검색하여 해당 태블릿의 정보를 갖고 있는 TabletDTO 객체를 반환하는 메소드
	public Tablet getTabletById(String tId) {
		// 기본 쿼리와 합쳐져  tId를 포함하는 name을 가진 tablet 정보를 가져오는 테이블
		String searchQuery = query + "FROM TABLET, PRODUCT "
				+ "WHERE TABLET.PRODUCT_ID = PRODUCT.PRODUCT_ID AND PRODUCT.PRODUCT_ID = ?"; 	 
		jdbcUtil.setSql(searchQuery);	// JDBCUtil 에 query 문 설정
		System.out.println("tId: " + tId);
		Object[] param = new Object[] { tId };		// 태블릿을 찾기 위한 조건으로 이름을 설정
		jdbcUtil.setParameters(param);				// JDBCUtil 에 query문의 매개변수 값으로 사용할 매개변수 설정
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행
			Tablet dto = new Tablet();		// TabletDTO 객체들을 담기위한 list 객체
			if (rs.next()) {						// 찾은 학생의 정보를 TabletDTO 객체에 설정
				dto.setProductId(rs.getString("TABLET_ID"));
				dto.settBattery(rs.getString("TABLET_BATTERY"));
				dto.settMemory(rs.getString("TABLET_MEMORY"));
				dto.settOS(rs.getString("TABLET_OS"));
				dto.settSize(rs.getDouble("TABLET_SIZE"));
				dto.setName(rs.getString("TABLET_NAME"));
				dto.setColor(rs.getString("TABLET_COLOR"));
				dto.setpKind(rs.getInt("TABLET_KIND"));
				dto.setPrice(rs.getString("TABLET_PRICE"));
				dto.setBrand(rs.getString("TABLET_BRAND"));
				dto.setReleased_date(rs.getDate("TABLET_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("TABLET_WEIGHT"));
			}
			return dto;				// 찾은 학생의 정보를 담고 있는 TabletDTO 객체 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}
		return null;
	}
	
	// TabletDTO 객체에 담겨 있는 태블릿의 정보를 기반으로 태블릿 정보를 tablet 테이블에 삽입하는 메소드

	public int insertTablet(Tablet tablet) {
		int result = 0;
		String insertQuery = "INSERT INTO TABLET (PRODUCT_ID, T_BATTERY, T_MEMORY, T_OS, T_SIZE) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?) " 
				+ "INSERT INTO PRODUCT(PRODUCT_ID, NAME, COLOR, PRICE, BRAND, RELEASED_DATE, WEIGHT, P_KIND)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		// query 문에 사용할 매개변수 값을 갖는 매개변수 배열 생성
		Object[] param = new Object[] { tablet.getProductId(), tablet.gettBattery(), tablet.gettMemory(), tablet.gettOS(), tablet.gettSize(),
				tablet.getProductId(), tablet.getName(), tablet.getColor(), tablet.getPrice(), tablet.getBrand(), tablet.getReleased_date(), tablet.getWeight(), 3 };	
		
		jdbcUtil.setSql(insertQuery);			// JDBCUtil 에 insert 문 설정
		jdbcUtil.setParameters(param);			// JDBCUtil 에 매개변수 설정
				
		try {				
			result = jdbcUtil.executeUpdate();		// insert 문 실행
			System.out.println(tablet.getProductId() + " 제품명의 정보가 삽입되었습니다.");
		} catch (SQLException ex) {
			System.out.println("입력오류 발생!!!");
			if (ex.getErrorCode() == 1)
				System.out.println("동일한 제품정보가 이미 존재합니다."); 
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		return result;		// insert 에 의해 반영된 레코드 수 반환	
	}
	
	// TabletDTO 객체에 설정되어 있는 정보를 토대로 테이블의 정보를 수정하는 메소드

	public int updateTablet(Tablet tablet) {
		
		String updateQuery = "UPDATE TABLET SET ";
		int index = 0;
		Object[] tempParam = new Object[15];		// update 문에 사용할 매개변수를 저장할 수 있는 임시 배열
		
		if (tablet.getProductId() != null) {		// 이름이 설정되어 있을 경우
			updateQuery += "PRODUCT_ID = ?, ";		// update 문에 이름 수정 부분 추가
			tempParam[index++] = tablet.getProductId();		// 매개변수에 수정할 이름 추가
		}
		if (tablet.getBrand() != null) {		// 브랜드가 설정되어 있을 경우
			updateQuery += "BRAND = ?, ";		// update 문에 브랜드 수정 부분 추가
			tempParam[index++] = tablet.getBrand();		// 매개변수에 수정할 브랜드 추가
		}
		if (tablet.getColor() != null) {		// 색깔이 설정되어 있을 경우
			updateQuery += "COLOR = ?, ";		// update 문에 색깔 수정 부분 추가
			tempParam[index++] = tablet.getColor();		// 매개변수에 수정할 색깔 추가
		}
		if (tablet.getName() != null) {		// 이름이 설정되어 있을 경우
			updateQuery += "NAME = ?, ";		// update 문에 이름 수정 부분 추가
			tempParam[index++] = tablet.getName();		// 매개변수에 수정할 이름 추가
		}
		if (tablet.getpKind() == 3) {		// 종류가 설정되어 있을 경우
			updateQuery += "P_KIND = ?, ";		// update문에 종류 수정 부분 추가
			tempParam[index++] = tablet.getpKind();		// 매개변수에 수정할 종류 추가
		}
		if (tablet.getReleased_date() != null) {		// 출시일이 설정되어 있을 경우
			updateQuery += "RELEASED_DATE = ?, ";		// update문에 출시일 수정 부분 추가
			tempParam[index++] = tablet.getReleased_date();		// 매개변수에 수정할 출시일 추가
		}
		if (tablet.gettBattery() != null) {		// 배터리가 설정되어 있을 경우
			updateQuery += "T_BATTERY = ?, ";		// update 문에 배터리 수정 부분 추가
			tempParam[index++] = tablet.gettBattery();		// 매개변수에 수정할 배터리 추가
		}
		if (tablet.gettMemory() != null) {		// 메모리가 설정되어 있을 경우
			updateQuery += "T_MEMORY = ?, ";		// update 문에 메모리 수정 부분 추가
			tempParam[index++] = tablet.gettMemory();		// 매개변수에 수정할 메모리 추가
		}
		if (tablet.gettOS() != null) {		// 운영체제가 설정되어 있을 경우
			updateQuery += "T_OS = ?, ";		// update 문에 운영체제 수정 부분 추가
			tempParam[index++] = tablet.gettOS();		// 매개변수에 수정할 운영체제 추가
		}
		if (tablet.gettSize() >= 0) {		// 크기가 설정되어 있을 경우
			updateQuery += "T_SIZE = ?, ";		// update 문에 크기 수정 부분 추가
			tempParam[index++] = tablet.gettSize();		// 매개변수에 수정할 크기 추가
		}
		if (tablet.getWeight() >= 0) {		// 무게가 설정되어 있을 경우
			updateQuery += "WEIGHT = ? ";		// update 문에 무게 수정 부분 추가
			tempParam[index++] = tablet.getWeight();		// 매개변수에 수정할 무게 추가
		}
		
		updateQuery += " WHERE PRODUCT_ID = ? ";		// update 문에 조건 지정
		updateQuery = updateQuery.replace(", WHERE", " WHERE");		// update 문의 where 절 앞에 있을 수 있는 , 제거
		
		tempParam[index++] = tablet.getProductId();		// 찾을 조건에 해당하는 학번에 대한 매개변수 추가
		
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

	// product_id를 전달받아 해당 태블릿의 정보를 삭제하는 메소드
	public void deleteTablet(String tId) {
	      String deleteQuery1 = "DELETE FROM TABLET WHERE PRODUCT_ID = ? ";
	      String deleteQuery2 = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ? ";
	      
	      try {
	         jdbcUtil.setSql(deleteQuery1);         // JDBCUtil 에 query 문 설정
	         Object[] param = new Object[] { tId };
	         jdbcUtil.setParameters(param);         // JDBCUtil 에 매개변수 설정
	         int result = jdbcUtil.executeUpdate();      // delete 문 실행
	         if( result != 0 ) {
	             System.out.println("product 삭제 완료"); 
	         }
	         
	         jdbcUtil.setSql(deleteQuery2);         // JDBCUtil 에 query 문 설정
	         jdbcUtil.setParameters(param);         // JDBCUtil 에 매개변수 설정
	         result = jdbcUtil.executeUpdate();      // delete 문 실행
	         if( result != 0 ) {
	             System.out.println("phone 삭제 완료"); 
	         }
	         return; // delete 에 의해 반영된 레코드 수 반환
	      } catch (Exception ex) {
	         jdbcUtil.rollback();
	         ex.printStackTrace();      
	      } finally {
	         jdbcUtil.commit();
	         jdbcUtil.close();      // ResultSet, PreparedStatement, Connection 반환
	      }
	      return;
	}
}
