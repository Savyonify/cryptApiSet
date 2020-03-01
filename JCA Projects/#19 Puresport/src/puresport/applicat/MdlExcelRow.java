/**
 * PureSport
 * create by zw at 2018年4月13日
 * version: v1.0
 */
package puresport.applicat;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author zw
 *
 */
public class MdlExcelRow implements Serializable {

	private List<String> listStrColums;

	public MdlExcelRow(){}
	
	public MdlExcelRow(List<String> list) {
		listStrColums = list;
	}

	public String getByIndex(int index) {
		if (Objects.isNull(listStrColums)) {
			throw new IllegalArgumentException("listStrColums is null");
		}
		if (index < listStrColums.size()) {
			return listStrColums.get(index);
		} else {
			throw new IllegalArgumentException("index is null");
		}
	}

	
	/**
	 * @return the listStrColums
	 */
	public List<String> getListStrColums() {
		return listStrColums;
	}

	/**
	 * @param listStrColums the listStrColums to set
	 */
	public void setListStrColums(List<String> listStrColums) {
		this.listStrColums = listStrColums;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if (Objects.isNull(listStrColums)) {
			return "empty";
		}
		StringBuilder sBuilder = new StringBuilder();
		listStrColums.forEach(e -> sBuilder.append(e).append(","));
		return sBuilder.toString();
	}

}
