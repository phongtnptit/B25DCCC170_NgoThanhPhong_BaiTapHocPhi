
import java.util.*;

abstract class SinhVien {
    protected String maSV;
    protected int soTinChi;

    public SinhVien(String maSV, int soTinChi) {
        this.maSV = maSV;
        this.soTinChi = soTinChi;
    }

    public abstract long hocPhi();

    public String getMaSV() {
        return maSV;
    }
}

class ChinhQuy extends SinhVien {
    public ChinhQuy(String maSV, int soTinChi) {
        super(maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return soTinChi * 450000L;
    }
}

class ChatLuongCao extends SinhVien {
    public ChatLuongCao(String maSV, int soTinChi) {
        super(maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return (long) (soTinChi * 450000L * 1.5);
    }
}

class LienThong extends SinhVien {
    public LienThong(String maSV, int soTinChi) {
        super(maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return soTinChi * 450000L + 2000000L;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        ArrayList<SinhVien> ds = new ArrayList<>();
        ArrayList<String> maHe = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String he = sc.next();
            String maSV = sc.next();
            int tinChi = sc.nextInt();

            SinhVien sv;

            if (he.equals("CQ")) {
                sv = new ChinhQuy(maSV, tinChi);
            } else if (he.equals("CLC")) {
                sv = new ChatLuongCao(maSV, tinChi);
            } else {
                sv = new LienThong(maSV, tinChi);
            }

            ds.add(sv);
            maHe.add(he);
        }

        for (int i = 0; i < n; i++) {
            SinhVien sv = ds.get(i);

            System.out.println(
                    sv.getMaSV() + " " +
                            maHe.get(i) + " " +
                            sv.hocPhi());
        }
    }
}