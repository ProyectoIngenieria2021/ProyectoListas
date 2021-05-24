package entity;

public class AppData {
	
	private int totalUsers;
	private int totalLists;
	private int totalItems;
	
	public AppData(int totalUsers, int totalLists, int totalItems) {
		super();
		this.totalUsers = totalUsers;
		this.totalLists = totalLists;
		this.totalItems = totalItems;
	}

	public int getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}

	public int getTotalLists() {
		return totalLists;
	}

	public void setTotalLists(int totalLists) {
		this.totalLists = totalLists;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	
	
	
	

}
