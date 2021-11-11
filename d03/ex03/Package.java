import java.net.URL;

public class Package {
    private int num;
    private URL url;
	private int status;

    public Package(int num, URL url) {
        this.num = num;
        this.url = url;
		this.status = 0;
    }
    public synchronized int getNum() {
        return num;
    }
    public synchronized URL getUrl() {
        return url;
    }
	public synchronized int getStatus(){
		return status;
	}
	public synchronized void setStatus(){
		status = 1;
	}
}