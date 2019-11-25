package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.impl.InforestUserDAOImpl;
import model.dto.InforestUserDTO;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
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
			throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
		}
		return userDAO.insertInforestUser(user);
	}

	public int update(InforestUserDTO user) throws SQLException, UserNotFoundException {
		int oldCommId = findUser(user.getUserId()).getCommId();
		if (user.getCommId() != oldCommId) { 	// 소속 커뮤티니가 변경됨
			Community comm = commDAO.findCommunity(oldCommId);  // 기존 소속 커뮤니티
			if (comm != null && user.getUserId().equals(comm.getChairId())) {
				// 사용자가 기존 소속 커뮤니티의 회장인 경우 -> 그 커뮤니티의 회장을 null로 변경 및 저장
				comm.setChairId(null);
				commDAO.updateChair(comm);
			}
		}
		return userDAO.updateInforestUser(user);
	}	

	public int remove(String userId) throws SQLException, UserNotFoundException {
		int commId = findUser(userId).getCommId();
		Community comm = commDAO.findCommunity(commId);  // 소속 커뮤니티
		if (comm != null && userId.equals(comm.getChairId())) {
			// 사용자가 소속 커뮤니티의 회장인 경우 -> 그 커뮤니티의 회장을 null로 변경 및 저장
			comm.setChairId(null);
			commDAO.updateChair(comm);
		}
		return userDAO.removeInforestUser(userId);
	}

	public InforestUserDTO findUser(String userId)
		throws SQLException, UserNotFoundException {
		User user = userDAO.findUser(userId);
		
		if (user == null) {
			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
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
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
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
