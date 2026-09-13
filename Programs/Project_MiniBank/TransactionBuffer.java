public class TransactionBuffer {
    private String[] buf = new String[5];
    private int cnt = 0;
    private int in = 0;
    private int out = 0;

    public synchronized void produce(String tx) throws InterruptedException {
        while (cnt == buf.length) {
            wait();
        }
        buf[in] = tx;
        in = (in + 1) % buf.length;
        cnt++;
        notifyAll();
    }

    public synchronized String consume() throws InterruptedException {
        while (cnt == 0) {
            wait();
        }
        String tx = buf[out];
        out = (out + 1) % buf.length;
        cnt--;
        notifyAll();
        return tx;
    }
}
