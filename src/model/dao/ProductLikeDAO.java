package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dao.ProductLikeDAO;
import model.dto.Camera;
import model.dto.ProductLike;
import model.dto.Ranking;
//
//class LikeList {
//	private String pId;
//	private int likeCount;
//	
//	public void setPid(String pId) {
//        this.pId = pId;
//       
//    }
//	
//	public void setLikeCount(int likeCount) {
//		this.likeCount = likeCount;
//	}
//	
//	public String getPId() {
//		return pId;
//    }
// 
//	public int getLikeCount() {
//		return likeCount;
//	}
//}

public class ProductLikeDAO {
	Ranking lList = new Ranking();
	List<Ranking> list = new ArrayList<Ranking>();		// LikeList ��ü���� ������� list ��ü
	int like_count;	//���ƿ� ��
	
	private JDBCUtil jdbcUtil = null;
	
	private static String phone_q = 	"SELECT P.PRODUCT_ID, P.USER_ID " +
										" FROM PRODUCT_LIKE P, PHONE PH " +
										" WHERE P.PRODUCT_ID = PH.PRODUCT_ID ";
	
	private static String laptop_q = 	"SELECT P.PRODUCT_ID, P.USER_ID " +
										" FROM PRODUCT_LIKE P, LAPTOP L " +
										" WHERE P.PRODUCT_ID = L.PRODUCT_ID ";
	
	private static String camera_q = 	"SELECT P.PRODUCT_ID, P.USER_ID " +
										" FROM PRODUCT_LIKE P, CAMERA C " +
										" WHERE P.PRODUCT_ID = C.PRODUCT_ID ";
	
	private static String tablet_q = 	"SELECT P.PRODUCT_ID, P.USER_ID " +
										" FROM PRODUCT_LIKE P, TABLET T " +
										" WHERE P.PRODUCT_ID = T.PRODUCT_ID ";
	
	public ProductLikeDAO() {
		jdbcUtil = new JDBCUtil();
	}
		
	public List<Ranking> getPhoneLikeList() {
		jdbcUtil.setSql(phone_q);		// JDBCUtil �� query ����
			
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����		
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			ProductLike dto = new ProductLike();		
			while (rs.next()) {	
						// �ϳ��� ProductLike ��ü ���� �� ���� ����
				dto.setProductId(rs.getString("PRODUCT_ID"));
				dto.setUserId(rs.getString("USER_ID"));	
				
				String pId = dto.getProductId();
				System.out.println("pId: " + pId);
				if (map.containsKey(pId)) {	//key ���� �̹� �����ϸ�(true)
					like_count = map.get(pId);	//value�� (map.get(pId)) : "like_count"
					like_count ++;	//ī��Ʈ += 1;
				}
				else {	//key ���� ������ϸ� (false)
					map.put(pId, 0);	//�ش� ��ǰ�� ���ƿ� ���� 0���� �ʱ�ȭ
					System.out.println("ù Ű �߰�");
				}
			}
			
			
			List<String> keySetList = new ArrayList<>(map.keySet());
		    //value �������� //
		    Collections.sort(keySetList, new Comparator<String>() {
		        @Override
		        public int compare(String o1, String o2) {
		           return map.get(o2).compareTo(map.get(o1));
		        }
		    });
		    
		    System.out.println("===========<< �������� test ���â >>============");
		    for ( String key : map.keySet() ) {
		        System.out.println("���1) key : " + key +" / value : " + map.get(key));
		    }
		    System.out.println("=======================");
		    
		    int c = 0;
		    for ( String key : map.keySet() ) {
		    	if(c > 10) {
		    		break;
		    	}
		    	else {
		    		lList.setProductId(key);
		    		lList.setLikeCount(map.get(key));
		    		list.add(lList);
		    		c ++;
		    	}
		    }
		    return list;
		    
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
				
		return null;	
	}
	
	public List<Ranking> getLaptopLikeList() {
		jdbcUtil.setSql(laptop_q);		// JDBCUtil �� query ����
			
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����		
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			ProductLike dto = new ProductLike();		
			while (rs.next()) {	
						// �ϳ��� ProductLike ��ü ���� �� ���� ����
				dto.setProductId(rs.getString("PRODUCT_ID"));
				dto.setUserId("USER_ID");	
				
				String pId = dto.getProductId();
				if (map.containsKey(pId)) {	//key ���� �̹� �����ϸ�(true)
					like_count = map.get(pId);	//value�� (map.get(pId)) : "like_count"
					like_count ++;	//ī��Ʈ += 1;
				}
				else {	//key ���� ������ϸ� (false)
					map.put(pId, 0);	//�ش� ��ǰ�� ���ƿ� ���� 0���� �ʱ�ȭ
				}
			}
			
			
			List<String> keySetList = new ArrayList<>(map.keySet());
		    //value �������� //
		    Collections.sort(keySetList, new Comparator<String>() {
		        @Override
		        public int compare(String o1, String o2) {
		           return map.get(o2).compareTo(map.get(o1));
		        }
		    });
		    
		    System.out.println("===========<< �������� test ���â >>============");
		    for ( String key : map.keySet() ) {
		        System.out.println("���1) key : " + key +" / value : " + map.get(key));
		    }
		    System.out.println("=======================");
		    
		    int c = 0;
		    for ( String key : map.keySet() ) {
		    	if(c > 10) {
		    		break;
		    	}
		    	else {
		    		lList.setProductId(key);
		    		lList.setLikeCount(map.get(key));
		    		list.add(lList);
		    		c ++;
		    	}
		    }
		    return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
				
		return null;	
	}
	
	public List<Ranking> getCameraLikeList() {
		jdbcUtil.setSql(camera_q);		// JDBCUtil �� query ����
			
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����		
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			ProductLike dto = new ProductLike();		
			while (rs.next()) {	
						// �ϳ��� ProductLike ��ü ���� �� ���� ����
				dto.setProductId(rs.getString("PRODUCT_ID"));
				dto.setUserId("USER_ID");	
				
				String pId = dto.getProductId();
				if (map.containsKey(pId)) {	//key ���� �̹� �����ϸ�(true)
					like_count = map.get(pId);	//value�� (map.get(pId)) : "like_count"
					like_count ++;	//ī��Ʈ += 1;
				}
				else {	//key ���� ������ϸ� (false)
					map.put(pId, 0);	//�ش� ��ǰ�� ���ƿ� ���� 0���� �ʱ�ȭ
				}
			}
			
			
			List<String> keySetList = new ArrayList<>(map.keySet());
		    //value �������� //
		    Collections.sort(keySetList, new Comparator<String>() {
		        @Override
		        public int compare(String o1, String o2) {
		           return map.get(o2).compareTo(map.get(o1));
		        }
		    });
		    
		    System.out.println("===========<< �������� test ���â >>============");
		    for ( String key : map.keySet() ) {
		        System.out.println("���1) key : " + key +" / value : " + map.get(key));
		    }
		    System.out.println("=======================");
		    
		    int c = 0;
		    for ( String key : map.keySet() ) {
		    	if(c > 10) {
		    		break;
		    	}
		    	else {
		    		lList.setProductId(key);
		    		lList.setLikeCount(map.get(key));
		    		list.add(lList);
		    		c ++;
		    	}
		    }
		    return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
				
		return null;	
	}
	
	public List<Ranking> getTabletLikeList() {
		jdbcUtil.setSql(tablet_q);		// JDBCUtil �� query ����
			
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����		
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			ProductLike dto = new ProductLike();		
			while (rs.next()) {	
						// �ϳ��� ProductLike ��ü ���� �� ���� ����
				dto.setProductId(rs.getString("PRODUCT_ID"));
				dto.setUserId("USER_ID");	
				
				String pId = dto.getProductId();
				if (map.containsKey(pId)) {	//key ���� �̹� �����ϸ�(true)
					like_count = map.get(pId);	//value�� (map.get(pId)) : "like_count"
					like_count ++;	//ī��Ʈ += 1;
				}
				else {	//key ���� ������ϸ� (false)
					map.put(pId, 0);	//�ش� ��ǰ�� ���ƿ� ���� 0���� �ʱ�ȭ
				}
			}
			
			
			List<String> keySetList = new ArrayList<>(map.keySet());
		    //value �������� //
		    Collections.sort(keySetList, new Comparator<String>() {
		        @Override
		        public int compare(String o1, String o2) {
		           return map.get(o2).compareTo(map.get(o1));
		        }
		    });
		    
		    System.out.println("===========<< �������� test ���â >>============");
		    for ( String key : map.keySet() ) {
		        System.out.println("���1) key : " + key +" / value : " + map.get(key));
		    }
		    System.out.println("=======================");
		    
		    int c = 0;
		    for ( String key : map.keySet() ) {
		    	if(c > 10) {
		    		break;
		    	}
		    	else {
		    		lList.setProductId(key);
		    		lList.setLikeCount(map.get(key));
		    		list.add(lList);
		    		c ++;
		    	}
		    }
		    return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
				
		return null;	
	}
/*
	public int insertProductLike(ProductLike productLike) {
		int result = 0;
		String insertQuery = "INSERT INTO PRODUCT_LIKE (PRODUCT_ID, USER_ID) " +
							 "VALUES (?, ?) ";
		
		// query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ���� ����ǥ �� �޾ƿ��� 
		Object[] param = new Object[] {productLike.getProductId(), productLike.getUserId()};
		jdbcUtil.setSql(insertQuery);			// JDBCUtil �� insert �� ����
		jdbcUtil.setParameters(param);			// JDBCUtil �� �Ű����� ����
				
		try {				
			result = jdbcUtil.executeUpdate();		// insert �� ����
			System.out.println(productLike.getProductId() + " ��ǰ������ ���ԵǾ����ϴ�.");
		} catch (SQLException ex) {
			System.out.println("�Է¿��� �߻�!!!");
			if (ex.getErrorCode() == 1)
				System.out.println("������ ��ǰ������ �̹� �����մϴ�."); 
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return result;		// insert �� ���� �ݿ��� ���ڵ� �� ��ȯ	
	}

*/
	/*
	public int deleteProductLike(int uid, int pid) { //����
		String deleteQuery = "DELETE FROM PRODUCT_LIKE WHERE USER_ID = ? AND PRODUCT_ID = ? ";
		
		jdbcUtil.setSql(deleteQuery);			// JDBCUtil �� query �� ����
		Object[] param = new Object[] {uid, pid};
		jdbcUtil.setParameters(param);			// JDBCUtil �� �Ű����� ����
		
		try {
			int result = jdbcUtil.executeUpdate();		// delete �� ����
			return result;						// delete �� ���� �ݿ��� ���ڵ� �� ��ȯ
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();		
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}
		return 0;
	}

	
	public List<ProductLike> getProductLikeById(String plId) {
		// TODO Auto-generated method stub
		String searchQuery = query + "WHERE PRODUCT_LIKE.plname = ?";
		Object[] param = new Object[] {plId};

		jdbcUtil.setSql(searchQuery);
		jdbcUtil.setParameters(param);
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<ProductLike> list = new ArrayList<ProductLike>();
			while (rs.next()) {
				ProductLike dto = new ProductLike();
				dto.setProductId(rs.getString("PRODUCT_ID"));
				dto.setUserId("USER_ID");
				list.add(dto);		// list ��ü�� ������ ������ LaptopDTO ��ü ����
			}
			return list;	
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public int updateProductLike(ProductLike productLike) {
		// TODO Auto-generated method stub
		return 0;
	}
*/
	
}
