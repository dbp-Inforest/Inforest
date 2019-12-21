package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.LaptopDAO;
import model.dto.Laptop;

public class LaptopDAO{

	private JDBCUtil jdbcUtil = null;
	
	private static String query = "SELECT L.L_PURPOSE AS LAPTOP_PURPOSE, " +
			  "        L.L_DISPLAY AS LAPTOP_DISPLAY,L.L_CPU AS LAPTOP_CPU, " +
			  "        L.L_RAM_MEMORY AS LAPTOP_RAM_MEMORY, L.L_OS AS LAPTOP_OS, " +
			  "        L.PRODUCT_ID AS LAPTOP_ID, L.L_SSD AS LAPTOP_SSD, " +
			  "		   P.NAME AS LAPTOP_NAME, P.COLOR AS LAPTOP_COLOR, " +
			  "		   P.PRICE AS LAPTOP_PRICE,  P.BRAND  AS LAPTOP_BRAND, " +
			  " 	   P.RELEASED_DATE  AS LAPTOP_RELEASED_DATE, P.WEIGHT  AS LAPTOP_WEIGHT, " +
			  "		   P.P_KIND  AS LAPTOP_KIND ";
	
	public LaptopDAO() {   
		try {
	        jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }   
	}
	   
	public List<Laptop> getLaptopList() {
		// 기본 쿼리와 합쳐짐 
		String allQuery = query + "FROM LAPTOP l, PRODUCT P " +
											"WHERE l.PRODUCT_ID = P.PRODUCT_ID ";		
		jdbcUtil.setSql(allQuery);		// JDBCUtil 에 query 설정
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행			
			List<Laptop> list = new ArrayList<Laptop>();		// Laptop 객체들을 담기위한 list 객체
			while (rs.next()) {	
				Laptop dto = new Laptop();		// 하나의 Laptop 객체 생성 후 정보 설정
				dto.setlPurpose(rs.getString("LAPTOP_PURPOSE"));
				dto.setlDisplay(rs.getString("LAPTOP_DISPLAY"));
				dto.setlCPU(rs.getString("LAPTOP_CPU"));
				dto.setlRAMMemory(rs.getString("LAPTOP_RAM_MEMORY"));
				dto.setlOS(rs.getString("LAPTOP_OS"));
				dto.setProductId(rs.getString("LAPTOP_ID"));
				dto.setlSSD(rs.getString("LAPTOP_SSD"));
				//--
				dto.setName(rs.getString("LAPTOP_NAME"));
				dto.setColor(rs.getString("LAPTOP_COLOR"));
				dto.setPrice(rs.getString("LAPTOP_PRICE"));
				dto.setBrand(rs.getString("LAPTOP_BRAND"));
				dto.setReleased_date(rs.getDate("LAPTOP_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("LAPTOP_WEIGHT"));
				dto.setpKind(rs.getInt("LAPTOP_KIND"));
				
				list.add(dto);		// list 객체에 정보를 설정한 Laptop 객체 저장
			}
			return list;		// 학생정보를 저장한 dto들의 목록을 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		return null;		
	}

	public int insertLaptop(Laptop laptop) {
		int result = 0;
		String insertQuery1 = "INSERT INTO PRODUCT (PRODUCT_ID, NAME, COLOR, PRICE, BRAND, RELEASED_DATE, WEIGHT, P_KIND) " +
	 			 "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
		
		String insertQuery2 = "INSERT INTO LAPTOP (L_PURPOSE, L_DISPLAY, L_CPU, L_RAM_MEMORY, L_OS, PRODUCT_ID, L_SSD) " +
							 "VALUES (?, ?, ?, ?, ?, ?, ?) ";
		
		// query 문에 사용할 매개변수 값을 갖는 매개변수 배열 생성 물음표 다 받아오기 
		Object[] param1 = new Object[] {laptop.getProductId(), laptop.getName(), laptop.getColor(), laptop.getPrice(), laptop.getBrand(), laptop.getReleased_date(), laptop.getWeight(), 1};		

		Object[] param2 = new Object[] {laptop.getlPurpose(), laptop.getlDisplay(), laptop.getlCPU(), laptop.getlRAMMemory(),
				laptop.getlOS(), laptop.getProductId(), laptop.getlSSD()};		
			
		try {				
			jdbcUtil.setSql(insertQuery1);			// JDBCUtil 에 insert 문 설정
			jdbcUtil.setParameters(param1);			// JDBCUtil 에 매개변수 설정
			result = jdbcUtil.executeUpdate();		// insert 문 실행
			
			jdbcUtil.setSql(insertQuery2);			// JDBCUtil 에 insert 문 설정
			jdbcUtil.setParameters(param2);			// JDBCUtil 에 매개변수 설정
			result = jdbcUtil.executeUpdate();		// insert 문 실행
			System.out.println(laptop.getProductId() + " 제품정보가 삽입되었습니다.");
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

	public int updateLaptop(Laptop laptop) {
		
		String updateQuery1 = "UPDATE PRODUCT SET ";
		String updateQuery2 = "UPDATE LAPTOP SET ";
		int index = 0;
		Object[] tempParam = new Object[10];		// update 문에 사용할 매개변수를 저장할 수 있는 임시 배열
		
		if (laptop.getBrand() != null) {		// 브랜드가 설정되어 있을 경우
			updateQuery1 += "BRAND = ?, ";		// update 문에 브랜드 수정 부분 추가
			tempParam[index++] = laptop.getBrand();		// 매개변수에 수정할 이름 추가
		}
		if (laptop.getColor() != null) {		// 색깔이 설정되어 있을 경우
			updateQuery1 += "COLOR = ?, ";		// update 문에 색깔 수정 부분 추가
			tempParam[index++] = laptop.getColor();		// 매개변수에 수정할 색깔 추가
		}
		if (laptop.getlCPU() != null) {		// cpu가 설정되어 있을 경우
			updateQuery2 += "L_CPU = ?, ";		// update 문에 cpu 수정 부분 추가
			tempParam[index++] = laptop.getlCPU();		// 매개변수에 수정할 휴대폰 추가
		}
		if (laptop.getlDisplay() != null) {		// display가 설정되어 있을 경우
			updateQuery2 += "L_DISPLAY = ?, ";		// update 문에 display 수정 부분 추가
			tempParam[index++] = laptop.getlDisplay();		// 매개변수에 수정할 display 추가
		}
		if (laptop.getlOS() != null) {		// os가 설정되어 있을 경우
			updateQuery2 += "L_OS = ?, ";		// update 문에 os 수정 부분 추가
			tempParam[index++] = laptop.getlOS();		// 매개변수에 수정할 os 추가
		}
		if (laptop.getlPurpose() != null) {		// purpose가 설정되어 있을 경우
			updateQuery2 += "L_PURPOSE = ?, ";		// update 문에 purpose 수정 부분 추가
			tempParam[index++] = laptop.getlPurpose();		// 매개변수에 수정할 purpose 추가
		}
		if (laptop.getlRAMMemory()!= null) {		// 메모리가 설정되어 있을 경우
			updateQuery2 += "L_RAM_MEMORY = ?, ";		// update 문에 메모리 수정 부분 추가
			tempParam[index++] = laptop.getlRAMMemory();		// 매개변수에 수정할 메모리 추가
		}
		if (laptop.getlSSD() != null) {		// ssd가 설정되어 있을 경우
			updateQuery2 += "L_SSD = ?, ";		// update 문에 ssd 수정 부분 추가
			tempParam[index++] = laptop.getlSSD();		// 매개변수에 수정할 ssd 추가
		}
		if (laptop.getName() != null) {		// 이름이 설정되어 있을 경우
			updateQuery1 += "NAME = ?, ";		// update 문에 이름 수정 부분 추가
			tempParam[index++] = laptop.getName();		// 매개변수에 수정할 이름 추가
		}
		if (laptop.getpKind() == 1) {		// pKind가 설정되어 있을 경우
			updateQuery1 += "P_KIND = ?, ";		// update 문에 pKind 수정 부분 추가
			tempParam[index++] = laptop.getpKind();		// 매개변수에 수정할 pKind 추가
		}
		if (laptop.getPrice() != null) {		// price가 설정되어 있을 경우
			updateQuery1 += "PRICE = ?, ";		// update 문에 price 수정 부분 추가
			tempParam[index++] = laptop.getPrice();		// 매개변수에 수정할 price 추가
		}
		if (laptop.getProductId() != null) {		// id가 설정되어 있을 경우
			updateQuery2 += "PRODUCT_ID = ?, ";		// update 문에 id 수정 부분 추가
			tempParam[index++] = laptop.getProductId();		// 매개변수에 수정할 id 추가
		}
		if (laptop.getReleased_date() != null) {		// 출시일이 설정되어 있을 경우
			updateQuery1 += "NAME = ?, ";		// update 문에 출시일 수정 부분 추가
			tempParam[index++] = laptop.getReleased_date();		// 매개변수에 수정할 출시 추가
		}
		if (laptop.getWeight() > 0) {		// 무게가 설정되어 있을 경우
			updateQuery1 += "WEIGHT = ?, ";		// update 문에 무게 수정 부분 추가
			tempParam[index++] = laptop.getWeight();		// 매개변수에 수정할 무게 추가
		}
		
		updateQuery1 += "WHERE PRODUCT_ID = ? ";		// update 문에 조건 지정
		updateQuery1 = updateQuery1.replace(", WHERE", " WHERE");		// update 문의 where 절 앞에 있을 수 있는 , 제거
		
		updateQuery2 += "WHERE PRODUCT_ID = ? ";		// update 문에 조건 지정
		updateQuery2 = updateQuery2.replace(", WHERE", " WHERE");		// update 문의 where 절 앞에 있을 수 있는 , 제거
		
		tempParam[index++] = laptop.getProductId();		// 찾을 조건에 해당하는 에 대한 매개변수 추가
		
		Object[] newParam = new Object[index];
		for (int i=0; i < newParam.length; i++)		// 매개변수의 개수만큼의 크기를 갖는 배열을 생성하고 매개변수 값 복사
			newParam[i] = tempParam[i];
		
		jdbcUtil.setSql(updateQuery1);			// JDBCUtil에 update 문 설정
		jdbcUtil.setSql(updateQuery2);	
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


	public void deleteLaptop(String lProductId) {
	      String deleteQuery1 = "DELETE FROM LAPTOP WHERE PRODUCT_ID = ? ";
	      String deleteQuery2 = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ? ";
	      
	      try {
	         jdbcUtil.setSql(deleteQuery1);         // JDBCUtil 에 query 문 설정
	         Object[] param = new Object[] { lProductId };
	         jdbcUtil.setParameters(param);         // JDBCUtil 에 매개변수 설정
	         int result = jdbcUtil.executeUpdate();      // delete 문 실행
	         if( result != 0 ) {
	             System.out.println("product 삭제 완료"); 
	         }
	         
	         jdbcUtil.setSql(deleteQuery2);         // JDBCUtil 에 query 문 설정
	         jdbcUtil.setParameters(param);         // JDBCUtil 에 매개변수 설정
	         result = jdbcUtil.executeUpdate();      // delete 문 실행
	         if( result != 0 ) {
	             System.out.println("Laptop 삭제 완료"); 
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


	public List<Laptop> getLaptopByName(String lName) {
		// TODO Auto-generated method stub
		String searchQuery = query  + "FROM LAPTOP l, PRODUCT P " +
				"WHERE l.PRODUCT_ID = P.PRODUCT_ID " + "AND P.Name LIKE ?";
		Object[] param = new Object[] { "%" + lName + "%" };

		jdbcUtil.setSql(searchQuery);
		jdbcUtil.setParameters(param); 
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Laptop> list = new ArrayList<Laptop>();
			while (rs.next()) {
				Laptop dto = new Laptop();
				dto.setlPurpose(rs.getString("LAPTOP_PURPOSE"));
				dto.setlDisplay(rs.getString("LAPTOP_DISPLAY"));
				dto.setlCPU(rs.getString("LAPTOP_CPU"));
				dto.setlRAMMemory(rs.getString("LAPTOP_RAM_MEMORY"));
				dto.setlOS(rs.getString("LAPTOP_OS"));
				dto.setProductId(rs.getString("LAPTOP_ID"));
				dto.setlSSD(rs.getString("LAPTOP_SSD"));
				//--
				dto.setName(rs.getString("LAPTOP_NAME"));
				dto.setColor(rs.getString("LAPTOP_COLOR"));
				dto.setPrice(rs.getString("LAPTOP_PRICE"));
				dto.setBrand(rs.getString("LAPTOP_BRAND"));
				dto.setReleased_date(rs.getDate("LAPTOP_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("LAPTOP_WEIGHT"));
				dto.setpKind(rs.getInt("LAPTOP_KIND"));
				
				list.add(dto);		// list 객체에 정보를 설정한 Laptop 객체 저장
			}
			return list;	
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public Laptop getLaptopById(String lId) {
		// TODO Auto-generated method stub
		String searchQuery = query + "FROM LAPTOP l, PRODUCT P " +
				"WHERE l.PRODUCT_ID = P.PRODUCT_ID AND P.PRODUCT_ID = ?";
		Object[] param = new Object[] {lId};

		jdbcUtil.setSql(searchQuery);
		jdbcUtil.setParameters(param);
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			Laptop dto = new Laptop();
			while (rs.next()) {
				dto.setlPurpose(rs.getString("LAPTOP_PURPOSE"));
				dto.setlDisplay(rs.getString("LAPTOP_DISPLAY"));
				dto.setlCPU(rs.getString("LAPTOP_CPU"));
				dto.setlRAMMemory(rs.getString("LAPTOP_RAM_MEMORY"));
				dto.setlOS(rs.getString("LAPTOP_OS"));
				dto.setProductId(rs.getString("LAPTOP_ID"));
				dto.setlSSD(rs.getString("LAPTOP_SSD"));
				//--
				dto.setName(rs.getString("LAPTOP_NAME"));
				dto.setColor(rs.getString("LAPTOP_COLOR"));
				dto.setPrice(rs.getString("LAPTOP_PRICE"));
				dto.setBrand(rs.getString("LAPTOP_BRAND"));
				dto.setReleased_date(rs.getDate("LAPTOP_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("LAPTOP_WEIGHT"));
				dto.setpKind(rs.getInt("LAPTOP_KIND"));
			}
			return dto;	
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}


}
