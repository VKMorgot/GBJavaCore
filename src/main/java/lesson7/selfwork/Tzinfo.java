package lesson7.selfwork;

public class Tzinfo {
    private String name;
    private String abbr;
    private boolean dst;
    private float offset;


    // Getter Methods

    public String getName() {
        return name;
    }

    public String getAbbr() {
        return abbr;
    }

    public boolean getDst() {
        return dst;
    }

    public float getOffset() {
        return offset;
    }

    // Setter Methods

    public void setName(String name) {
        this.name = name;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public void setDst(boolean dst) {
        this.dst = dst;
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }
}
