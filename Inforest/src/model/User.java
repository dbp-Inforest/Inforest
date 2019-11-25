package model;

/**
 * ����� ������ ���� �ʿ��� ������ Ŭ����. USERINFO ���̺��� ������
 */
public class User {
	private String userId;
	private String password;
	private String name;
	private String email;
	private String phone;
	private int commId;
	private String commName;

	public User() { }		// �⺻ ������
	
	public User(String userId, String password, String name, String email, String phone, int commId) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.commId = commId;
	}
	
	public User(String userId, String password, String name, String email, String phone, int commId, String commName) {
		this(userId, password, name, email, phone, commId);
		this.commName = commName;
	}

	public User(String userId, String name) {
		this.userId = userId;
		this.name = name;
	}
	
	/*public void update(User updateUser) {
        this.password = updateUser.password;
        this.name = updateUser.name;
        this.email = updateUser.email;
        this.phone = updateUser.phone;
    }*/
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getCommId() {
		return commId;
	}

	public void setCommId(int commId) {
		this.commId = commId;
	}

	public String getCommName() {
		return commName;
	}

	public void setCommName(String commName) {
		this.commName = commName;
	}

	
	/* ��й�ȣ �˻� */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameUser(String userid) {
        return this.userId.equals(userid);
    }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + ", phone="
				+ phone + ", commId=" + commId + "]";
	}	
}