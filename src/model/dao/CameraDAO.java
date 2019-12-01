package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.CameraDAO;
import model.dto.Camera;

public class CameraDAO {

	private JDBCUtil jdbcUtil = null;

	private static String query = "SELECT CAMERA.C_DISPLAY AS CAMERA_DISPLAY, " +
	         "CAMERA.C_PIXEL AS CAMERA_PIXEL, " +
	         "CAMERA.C_BATTERY AS CAMERA_BATTERY, " +
	         "CAMERA.C_VIBRATION AS CAMERA_VIBRATION, " +
	         "CAMERA.C_BURSTSHOT AS CAMERA_BURSTSHOT, " +
	         "CAMERA.C_LENS AS CAMERA_LENS, " +
	         "CAMERA.PRODUCT_ID AS CAMERA_ID, " +
	         "PRODUCT.NAME AS CAMERA_NAME, " +
	         "PRODUCT.COLOR AS CAMERA_COLOR, " +
	         "PRODUCT.PRICE AS CAMERA_PRICE, " +
	         "PRODUCT.BRAND AS CAMERA_BRAND, " +
	         "PRODUCT.RELEASED_DATE AS CAMERA_RELEASED_DATE, " +
	         "PRODUCT.WEIGHT AS CAMERA_WEIGHT, " +
	         "PRODUCT.P_KIND AS CAMERA_KIND ";		
	
	public CameraDAO() {   
		try {
	        jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }   
	}
	
	public List<Camera> getCameraList() {
		// PRODUCT 테이블을 상속받았기 때문에 전체 태블릿 정보를 가져옴
		String allQuery = query + "FROM CAMERA, PRODUCT "
							+ "WHERE CAMERA.PRODUCT_ID = PRODUCT.PRODUCT_ID";	
		
		jdbcUtil.setSql(allQuery);		// JDBCUtil 에 query 설정
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행			
			List<Camera> list = new ArrayList<Camera>();		// Camera 객체들을 담기위한 list 객체
	
			while (rs.next()) {	
				Camera dto = new Camera();		// 하나의 Camera 객체 생성 후 정보 설정
				dto.setProductId(rs.getString("CAMERA_ID"));
				dto.setcBattery(rs.getString("CAMERA_BATTERY"));
				dto.setcPixel(rs.getDouble("CAMERA_PIXEL"));
				dto.setName(rs.getString("CAMERA_NAME"));
				dto.setcBurstshot(rs.getDouble("CAMERA_BURSTSHOT"));
				dto.setcDisplay(rs.getDouble("CAMERA_DISPLAY"));
				dto.setcLens(rs.getString("CAMERA_LENS"));
				dto.setcVibration(rs.getString("CAMERA_VIBRATION"));
				dto.setColor(rs.getString("CAMERA_COLOR"));
				dto.setpKind(rs.getInt("CAMERA_KIND"));
				dto.setPrice(rs.getString("CAMERA_PRICE"));
				dto.setBrand(rs.getString("CAMERA_BRAND"));
				dto.setReleased_date(rs.getDate("CAMERA_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("CAMERA_WEIGHT"));
				
				list.add(dto);		// list 객체에 정보를 설정한 Camera 객체 저장
			}
			return list;		// 카메라 정보를 저장한 dto 들의 목록을 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		return null;	
	}

	// Camera 객체에 담겨 있는 태블릿의 정보를 기반으로 태블릿 정보를 Camera 테이블에 삽입하는 메소드
	public int insertCamera(Camera Camera) {
		int result = 0;
		// 트렌젝션? 사용해야하나
		String insertQuery = "INSERT INTO Camera (PRODUCT_ID, C_DISPLAY, C_PIXEL, C_BATTERY, C_VIBRATION, C_BURSTSHOT, C_LENS) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?) "  
				+ "INSERT INTO PRODUCT(PRODUCT_ID, NAME, COLOR, PRICE, BRAND, RELEASED_DATE, WEIGHT, P_KIND)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		// query 문에 사용할 매개변수 값을 갖는 매개변수 배열 생성
		Object[] param = new Object[] { Camera.getProductId(), Camera.getcDisplay(), Camera.getcPixel(), Camera.getcBattery(), Camera.getcVibration(), Camera.getcBurstshot(), Camera.getcLens(),
				Camera.getProductId(), Camera.getName(), Camera.getColor(), Camera.getPrice(), Camera.getBrand(), Camera.getReleased_date(), Camera.getWeight(), 2 };	
		
		jdbcUtil.setSql(insertQuery);			// JDBCUtil 에 insert 문 설정
		jdbcUtil.setParameters(param);			// JDBCUtil 에 매개변수 설정
				
		try {				
			result = jdbcUtil.executeUpdate();		// insert 문 실행
			System.out.println(Camera.getProductId() + " 제품명의 정보가 삽입되었습니다.");
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
	
	// Camera 객체에 설정되어 있는 정보를 토대로 테이블의 정보를 수정하는 메소드
	
	public int updateCamera(Camera camera) {
		
		String updateQuery = "UPDATE Camera SET ";
		int index = 0;
		Object[] tempParam = new Object[15];		// update 문에 사용할 매개변수를 저장할 수 있는 임시 배열
		
		if (camera.getProductId() != null) {		// 이름이 설정되어 있을 경우
			updateQuery += "PRODUCT_ID = ?, ";		// update 문에 이름 수정 부분 추가
			tempParam[index++] = camera.getProductId();		// 매개변수에 수정할 이름 추가
		}
		if (camera.getBrand() != null) {		// 브랜드가 설정되어 있을 경우
			updateQuery += "BRAND = ?, ";		// update 문에 브랜드 수정 부분 추가
			tempParam[index++] = camera.getBrand();		// 매개변수에 수정할 브랜드 추가
		}
		if (camera.getColor() != null) {		// 색깔이 설정되어 있을 경우
			updateQuery += "COLOR = ?, ";		// update 문에 색깔 수정 부분 추가
			tempParam[index++] = camera.getColor();		// 매개변수에 수정할 색깔 추가
		}
		if (camera.getName() != null) {		// 이름이 설정되어 있을 경우
			updateQuery += "NAME = ?, ";		// update 문에 이름 수정 부분 추가
			tempParam[index++] = camera.getName();		// 매개변수에 수정할 이름 추가
		}
		if (camera.getpKind() == 3) {		// 종류가 설정되어 있을 경우
			updateQuery += "P_KIND = ?, ";		// update문에 종류 수정 부분 추가
			tempParam[index++] = camera.getpKind();		// 매개변수에 수정할 종류 추가
		}
		if (camera.getReleased_date() != null) {		// 출시일이 설정되어 있을 경우
			updateQuery += "RELEASED_DATE = ?, ";		// update문에 출시일 수정 부분 추가
			tempParam[index++] = camera.getReleased_date();		// 매개변수에 수정할 출시일 추가
		}
		if (camera.getcDisplay() >= 0) {		// 디스플레이가 설정되어 있을 경우
			updateQuery += "C_DISPLAY = ?, ";		// update 문에 디스플레이 수정 부분 추가
			tempParam[index++] = camera.getcDisplay();		// 매개변수에 수정할 디스플레이 추가
		}
		if (camera.getcPixel() >= 0) {		// 픽셀이 설정되어 있을 경우
			updateQuery += "C_PIXEL = ?, ";		// update 문에 픽셀 수정 부분 추가
			tempParam[index++] = camera.getcPixel();		// 매개변수에 수정할 픽셀 추가
		}
		if (camera.getcBattery() != null) {		// 배터리가 설정되어 있을 경우
			updateQuery += "C_BATTERY = ?, ";		// update 문에 배터리 수정 부분 추가
			tempParam[index++] = camera.getcBattery();		// 매개변수에 수정할 배터리 추가
		}
		if (camera.getcVibration() != null) {		// 바이브레이션이 설정되어 있을 경우
			updateQuery += "C_VIBRATION = ?, ";		// update 문에 바이브레이션 수정 부분 추가
			tempParam[index++] = camera.getcVibration();		// 매개변수에 수정할 바이브레이션 추가
		}
		if (camera.getcBurstshot() >= 0) {		// 버스트샷이 설정되어 있을 경우
			updateQuery += "C_BURSTSHOT = ?, ";		// update 문에 버스트샷 수정 부분 추가
			tempParam[index++] = camera.getcBurstshot();		// 매개변수에 수정할 버스트샷 추가
		}
		if (camera.getcLens() != null) {		// 렌즈가 설정되어 있을 경우
			updateQuery += "C_LENS = ? ";		// update 문에 렌즈 수정 부분 추가
			tempParam[index++] = camera.getcLens();		// 매개변수에 수정할 렌즈 추가
		}
		
		updateQuery += "WHERE PRODUCT_ID = ? ";		// update 문에 조건 지정
		updateQuery = updateQuery.replace(", WHERE", " WHERE");		// update 문의 where 절 앞에 있을 수 있는 , 제거
		
		tempParam[index++] = camera.getProductId();		// 찾을 조건에 해당하는 학번에 대한 매개변수 추가
		
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

	
	// product_id를 전달받아 해당 카메라의 정보를 삭제하는 메소드
	public int deleteCamera(String cId) {
		String deleteQuery = "DELETE FROM CAMERA WHERE PRODUCT_ID = ?";
		
		jdbcUtil.setSql(deleteQuery);			// JDBCUtil 에 query 문 설정
		Object[] param = new Object[] { cId };
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


	// 카메라의 이름으로 정보를 검색하여 해당 카메라의 정보를 갖고 있는 Camera 객체를 반환하는 메소드
	public List<Camera> getCameraByName(String cName) {
		// 기본 쿼리와 합쳐져  cName을 포함하는 name을 가진 Camera 정보를 가져오는 테이블
		String searchQuery = query + "FROM CAMERA, PRODUCT "
				+ "WHERE CAMERA.PRODUCT_ID = PRODUCT.PRODUCT_ID AND PRODUCT.NAME LIKE ?";
		
		Object[] param = new Object[] { "%" + cName + "%"};

		jdbcUtil.setSql(searchQuery);
		jdbcUtil.setParameters(param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Camera> list = new ArrayList<Camera>();
			while (rs.next()) { 
				// 찾은 카메라의 정보를 Camera 객체에 설정
				Camera dto = new Camera();
				dto.setProductId(rs.getString("CAMERA_ID"));
				dto.setcBattery(rs.getString("CAMERA_BATTERY"));
				dto.setcBurstshot(rs.getDouble("CAMERA_BURSTSHOT"));
				dto.setcDisplay(rs.getDouble("CAMERA_DISPLAY"));
				dto.setcLens(rs.getString("CAMERA_LENS"));
				dto.setcPixel(rs.getDouble("CAMERA_PIXEL"));
				dto.setcVibration(rs.getString("CAMERA_VIBRATION"));
				dto.setName(rs.getString("CAMERA_NAME"));
				dto.setColor(rs.getString("CAMERA_COLOR"));
				dto.setpKind(rs.getInt("CAMERA_KIND"));
				dto.setPrice(rs.getString("CAMERA_PRICE"));
				dto.setBrand(rs.getString("CAMERA_BRAND"));
				dto.setReleased_date(rs.getDate("CAMERA_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("CAMERA_WEIGHT"));
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


	// 모델명으로 정보를 검색하여 해당 태블릿의 정보를 갖고 있는 Camera 객체를 반환하는 메소드
	public Camera getCameraById(String cId) {
		// 기본 쿼리와 합쳐져  tId를 포함하는 name을 가진 CAMERA 정보를 가져오는 테이블
		String searchQuery = query + "FROM CAMERA, PRODUCT "
				+ "WHERE CAMERA.PRODUCT_ID = PRODUCT.PRODUCT_ID AND PRODUCT.PRODUCT_ID = ? ";
		
		jdbcUtil.setSql(searchQuery);	// JDBCUtil 에 query 문 설정
		Object[] param = new Object[] { ("%" + cId + "%") };		// 태블릿을 찾기 위한 조건으로 이름을 설정
		jdbcUtil.setParameters(param);				// JDBCUtil 에 query문의 매개변수 값으로 사용할 매개변수 설정
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행
			Camera dto = new Camera();		// Camera 객체들을 담기위한 list 객체
			while (rs.next()) {						// 찾은 학생의 정보를 Camera 객체에 설정
				dto.setProductId(rs.getString("CAMERA_ID"));
				dto.setcBattery(rs.getString("CAMERA_BATTERY"));
				dto.setcBurstshot(rs.getDouble("CAMERA_BURSTSHOT"));
				dto.setcDisplay(rs.getDouble("CAMERA_DISPLAY"));
				dto.setcLens(rs.getString("CAMERA_LENS"));
				dto.setcPixel(rs.getDouble("CAMERA_PIXEL"));
				dto.setcVibration(rs.getString("CAMERA_VIBRATION"));
				dto.setName(rs.getString("CAMERA_NAME"));
				dto.setColor(rs.getString("CAMERA_COLOR"));
				dto.setpKind(rs.getInt("CAMERA_KIND"));
				dto.setPrice(rs.getString("CAMERA_PRICE"));
				dto.setBrand(rs.getString("CAMERA_BRAND"));
				dto.setReleased_date(rs.getDate("CAMERA_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("CAMERA_WEIGHT"));
			}
			return dto;				// 찾은 학생의 정보를 담고 있는 Camera 객체 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}
		return null;
	}
	
	
}
