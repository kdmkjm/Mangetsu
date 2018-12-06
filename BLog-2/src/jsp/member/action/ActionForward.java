package jsp.member.action;
//페이지 이동을 담당
public class ActionForward {
	private boolean isRedirect = false;
	private String nextPath = null;
	
	public ActionForward() {
		super();
	}
	public ActionForward(boolean isRedirect, String nextPath) {
		super();
		this.isRedirect = isRedirect;
		this.nextPath = nextPath;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getNextPath() {
		return nextPath;
	}
	public void setNextPath(String nextPath) {
		this.nextPath = nextPath;
	}
}
