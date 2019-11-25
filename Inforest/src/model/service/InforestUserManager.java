package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.impl.InforestUserDAOImpl;
import model.dto.InforestUserDTO;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * UserDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
 */
public class InforestUserManager {
	private static InforestUserManager userMan = new InforestUserManager();
	private InforestUserDAOImpl userDAO;
	private InforestUserDTO userDTO;
//	private UserAnalysis userAanlysis;

	private InforestUserManager() {
		try {
			userDAO = new InforestUserDAOImpl();
			userDTO = new InforestUserDTO();
//			userAnalysis = new UserAnalysis(userDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static InforestUserManager getInstance() {
		return userMan;
	}
	
	public int create(InforestUserDTO user) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(user.getUserId()) == true) {
			throw new ExistingUserException(user.getUserId() + "�� �����ϴ� ���̵��Դϴ�.");
		}
		return userDAO.insertInforestUser(user);
	}

	public int update(InforestUserDTO user) throws SQLException, UserNotFoundException {
		int oldCommId = findUser(user.getUserId()).getCommId();
		if (user.getCommId() != oldCommId) { 	// �Ҽ� Ŀ��Ƽ�ϰ� �����
			Community comm = commDAO.findCommunity(oldCommId);  // ���� �Ҽ� Ŀ�´�Ƽ
			if (comm != null && user.getUserId().equals(comm.getChairId())) {
				// ����ڰ� ���� �Ҽ� Ŀ�´�Ƽ�� ȸ���� ��� -> �� Ŀ�´�Ƽ�� ȸ���� null�� ���� �� ����
				comm.setChairId(null);
				commDAO.updateChair(comm);
			}
		}
		return userDAO.updateInforestUser(user);
	}	

	public int remove(String userId) throws SQLException, UserNotFoundException {
		int commId = findUser(userId).getCommId();
		Community comm = commDAO.findCommunity(commId);  // �Ҽ� Ŀ�´�Ƽ
		if (comm != null && userId.equals(comm.getChairId())) {
			// ����ڰ� �Ҽ� Ŀ�´�Ƽ�� ȸ���� ��� -> �� Ŀ�´�Ƽ�� ȸ���� null�� ���� �� ����
			comm.setChairId(null);
			commDAO.updateChair(comm);
		}
		return userDAO.removeInforestUser(userId);
	}

	public InforestUserDTO findUser(String userId)
		throws SQLException, UserNotFoundException {
		User user = userDAO.findUser(userId);
		
		if (user == null) {
			throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
		}		
		return user;
	}

	public List<InforestUserDTO> findUserList() throws SQLException {
			return userDAO.getInforestUserList();
	}
	
//	public List<InforestUserDTO> findUserList(int currentPage, int countPerPage)
//		throws SQLException {
//		return userDAO.findUserList(currentPage, countPerPage);
//	}

	public boolean login(String userId, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		InforestUserDTO user = findUser(userId);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return true;
	}

	public InforestUserDAOImpl getUserDAO() {
		return this.userDAO;
	}
	
//	public List<InforestUserDAOImpl> makeFriends(String userId) throws Exception {
//	return userAanlysis.recommendFriends(userId);
//}

//public List<User> findCommunityMembers(int commId) throws SQLException {
//	return userDAO.findUsersInCommunity(commId);
//}
	
//	public Community createCommunity(Community comm) throws SQLException {
//		return commDAO.create(comm);		
//	}
//
//	public int updateCommunity(Community comm) throws SQLException {
//		return commDAO.update(comm);				
//	}
//	
//	public Community findCommunity(int commId) throws SQLException {
//		Community comm = commDAO.findCommunity(commId); 
//		
//		List<User> memberList = userDAO.findUsersInCommunity(commId);
//		comm.setMemberList(memberList);
//		
//		int numOfMembers = userDAO.getNumberOfUsersInCommunity(commId);
//		comm.setNumOfMembers(numOfMembers);
//		return comm;
//	}
//	
//	public List<Community> findCommunityList() throws SQLException {
//		return commDAO.findCommunityList();
//	}
	
}
