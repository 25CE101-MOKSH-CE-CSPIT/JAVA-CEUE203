import java.io.Serializable;

public class Customer implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    private static long cnt = 100;

    private final String id;
    private String nm;
    private String em;
    private String mb;
    private Address ad;

    public static class Address implements Serializable {
        private static final long serialVersionUID = 1L;
        private String ln;
        private String ct;
        private String pc;

        public Address(String ln, String ct, String pc) {
            this.ln = ln;
            this.ct = ct;
            this.pc = pc;
        }

        public String getLine() { return ln; }
        public String getCity() { return ct; }
        public String getPincode() { return pc; }

        @Override
        public String toString() {
            return ln + ", " + ct + " - " + pc;
        }
    }

    private static synchronized String genId() {
        cnt++;
        return "CUST" + cnt;
    }

    public Customer(String nm, String em, String mb, Address ad) {
        this.id = genId();
        this.nm = nm;
        this.em = em;
        this.mb = mb;
        this.ad = ad;
    }

    public Customer(String nm, String em, String mb) {
        this(nm, em, mb, null);
    }

    public String getId() { return id; }
    public String getName() { return nm; }
    public String getEmail() { return em; }
    public String getMobile() { return mb; }
    public Address getAddress() { return ad; }

    @Override
    public Customer clone() {
        try {
            return (Customer) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Customer(this.nm, this.em, this.mb, this.ad);
        }
    }

    @Override
    public String toString() {
        return id + " | " + nm + " | " + em + " | " + mb;
    }
}
