package lesson7.selfwork;

public class TestJson {
    private float now;
    private String now_dt;
    Info InfoObject;


    // Getter Methods

    public float getNow() {
        return now;
    }

    public String getNow_dt() {
        return now_dt;
    }

    public Info getInfo() {
        return InfoObject;
    }

    // Setter Methods

    public void setNow(float now) {
        this.now = now;
    }

    public void setNow_dt(String now_dt) {
        this.now_dt = now_dt;
    }

    public void setInfo(Info infoObject) {
        this.InfoObject = infoObject;
    }
}

