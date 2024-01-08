package p;

public class Buffer {
	
	private String msg;

	
	public synchronized String getMsg() {
		return msg;		
	}

	public synchronized void setMsg(String msg) {
		msg = msg;
	}
}
