/**
 * PureSport
 * create by zw at 2018年4月28日
 * version: v1.0
 */
package puresport.mvc.r16groupusr;

import java.io.Serializable;
import java.util.List;

/**
 * @author zw
 *
 */
public class R16GroupUsrDTO implements Serializable{
	
	private Long id;
	private String title;
	private List<Long> userIds;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Long> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}

}
