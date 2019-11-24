package model.dto;

public class InforestUser {

	private String userId;
	private String password;
	private String name;
	private int age;
	private int gender;
	private int position;
	private String introduce;
	
	public InforestUser() { }		// 기본 생성자
	
	public InforestUser(String userId, String password, String name, int age, int gender, int position, String introduce) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.position = position;
		this.introduce = introduce;
	}
	
	public InforestUser(String userId, String password, String name) {
		this.userId = userId;
		this.name = name;
		this.password = password;
	}
	
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
}
