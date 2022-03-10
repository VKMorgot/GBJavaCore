package lesson7.selfwork;

public class Info {
    private boolean n;
    private float geoid;
    private String url;
    private float lat;
    private float lon;
    Tzinfo TzinfoObject;
    private float def_pressure_mm;
    private float def_pressure_pa;
    private String slug;
    private float zoom;
    private boolean nr;
    private boolean ns;
    private boolean nsr;
    private boolean p;
    private boolean f;
    private boolean _h;


    // Getter Methods

    public boolean getN() {
        return n;
    }

    public float getGeoid() {
        return geoid;
    }

    public String getUrl() {
        return url;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public Tzinfo getTzinfo() {
        return TzinfoObject;
    }

    public float getDef_pressure_mm() {
        return def_pressure_mm;
    }

    public float getDef_pressure_pa() {
        return def_pressure_pa;
    }

    public String getSlug() {
        return slug;
    }

    public float getZoom() {
        return zoom;
    }

    public boolean getNr() {
        return nr;
    }

    public boolean getNs() {
        return ns;
    }

    public boolean getNsr() {
        return nsr;
    }

    public boolean getP() {
        return p;
    }

    public boolean getF() {
        return f;
    }

    public boolean get_h() {
        return _h;
    }

    // Setter Methods

    public void setN(boolean n) {
        this.n = n;
    }

    public void setGeoid(float geoid) {
        this.geoid = geoid;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public void setTzinfo(Tzinfo tzinfoObject) {
        this.TzinfoObject = tzinfoObject;
    }

    public void setDef_pressure_mm(float def_pressure_mm) {
        this.def_pressure_mm = def_pressure_mm;
    }

    public void setDef_pressure_pa(float def_pressure_pa) {
        this.def_pressure_pa = def_pressure_pa;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public void setNr(boolean nr) {
        this.nr = nr;
    }

    public void setNs(boolean ns) {
        this.ns = ns;
    }

    public void setNsr(boolean nsr) {
        this.nsr = nsr;
    }

    public void setP(boolean p) {
        this.p = p;
    }

    public void setF(boolean f) {
        this.f = f;
    }

    public void set_h(boolean _h) {
        this._h = _h;
    }
}
