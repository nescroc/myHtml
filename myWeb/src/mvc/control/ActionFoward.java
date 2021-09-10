package mvc.control;

public class ActionFoward {
	private String url;
	private boolean redircet;
	public ActionFoward() {}
	public ActionFoward(String url) {
		this.url = url;
	}
	public ActionFoward(String url,boolean redircet) {
		this.url = url;
		this.redircet = redircet;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isRedircet() {
		return redircet;
	}
	public void setRedircet(boolean redircet) {
		this.redircet = redircet;
	}
}
