package puresport.mvc.comm;


public class CommWrapper<T> {
	private T obj;
	
	public CommWrapper(T t) {
		this.obj = t;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}
}
