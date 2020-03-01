/**
 * PureSport
 * create by zw at 2018年4月19日
 * version: v1.0
 */
package puresport.constant;

/**
 * @author zw
 *
 */
public enum EnumRoleType {
	Sporter(0, "运动员"),
	Coach(10, "教练员"),
	Doctor(0, "科医员"),
	Admin(0, "管理员"),
	Assistor(1, "辅助人员");
	
	private int id;
	private String name;
	
	private EnumRoleType(int aid, String aname){
		id = aid;
		name = aname;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	} 
	
	
}
