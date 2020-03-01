/**
 * PureSport
 * create by zw at 2018年4月25日
 * version: v1.0
 */
package puresport.mvc.t1usrbsc;

import java.io.Serializable;

/**
 * @author zw
 *
 */
public class ResExamQuestion implements Serializable{
	private String type;
	private String title;
	private String content;
	private String answer;
	private Integer score;
	private String errorPercent;
	
	public ResExamQuestion(){}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * @return the errorPercent
	 */
	public String getErrorPercent() {
		return errorPercent;
	}

	/**
	 * @param errorPercent the errorPercent to set
	 */
	public void setErrorPercent(String errorPercent) {
		this.errorPercent = errorPercent;
	}
	
	
}
