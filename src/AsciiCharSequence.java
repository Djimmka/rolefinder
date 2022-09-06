import java.sql.Array;

public class AsciiCharSequence implements CharSequence {
    private byte[] arr;

    AsciiCharSequence(byte[] arr) {
        this.arr = arr;
    }

    @Override
    public int length() {
        return arr.length;
    }

    @Override
    public char charAt(int index) {
        if (this.arr != null) {
            return (char) this.arr[index];
        }
        return 0;
    }

    @Override
    public AsciiCharSequence subSequence(int start, int end) {
        if ((start >= 0) && (end <= this.arr.length) && (end > start)) {
            byte[] arr = new byte[(end - start)];
            int j = 0;
            for (int i = start; i < end; i++) {
                arr[j] = this.arr[i];
                j++;
            }
            return new AsciiCharSequence(arr);
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "";
        for (byte i :
                this.arr) {
            if (s != "") {
                s = s + "," + (char) i;
            } else {
                char ch = (char) i;
                s = Character.toString(ch);
            }
        }
        return s;
    }
}
