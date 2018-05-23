package netty.code;

import java.io.Serializable;

public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1l;
	private String userName;
	private String userId;
	
	public UserInfo() {
		
	}
	
	public UserInfo(String id, String name) {
		this.userId = id;
		this.userName = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
