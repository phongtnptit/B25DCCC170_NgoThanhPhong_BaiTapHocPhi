
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
        return soTinChi * 675000L;
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

class DuLieuKhongHopLeException extends Exception {
    public DuLieuKhongHopLeException(String message) {
        super(message);
    }
}

class QuanLyHocPhi {
    private ArrayList<SinhVien> danhSach = new ArrayList<>();
    private HashSet<String> maDaCo = new HashSet<>();

    public void themSinhVien(String he, String maSV, String tc)
            throws DuLieuKhongHopLeException {

        if (!he.equals("CQ") &&
                !he.equals("CLC") &&
                !he.equals("LT")) {
            throw new DuLieuKhongHopLeException("Ma he khong hop le");
        }

        int soTinChi;

        try {
            soTinChi = Integer.parseInt(tc);
        } catch (NumberFormatException e) {
            throw new DuLieuKhongHopLeException("So tin chi khong hop le");
        }

        if (soTinChi < 1 || soTinChi > 30) {
            throw new DuLieuKhongHopLeException("So tin chi ngoai pham vi");
        }

        if (maDaCo.contains(maSV)) {
            throw new DuLieuKhongHopLeException("Ma sinh vien bi trung");
        }

        SinhVien sv;

        if (he.equals("CQ")) {
            sv = new ChinhQuy(maSV, soTinChi);
        } else if (he.equals("CLC")) {
            sv = new ChatLuongCao(maSV, soTinChi);
        } else {
            sv = new LienThong(maSV, soTinChi);
        }

        danhSach.add(sv);
        maDaCo.add(maSV);
    }

    public void inDanhSach() {
        danhSach.sort((a, b) -> {
            if (a.hocPhi() != b.hocPhi()) {
                return Long.compare(b.hocPhi(), a.hocPhi());
            }

            return a.getMaSV().compareTo(b.getMaSV());
        });

        long tong = 0;

        for (SinhVien sv : danhSach) {
            String he;

            if (sv instanceof ChinhQuy) {
                he = "CQ";
            } else if (sv instanceof ChatLuongCao) {
                he = "CLC";
            } else {
                he = "LT";
            }

            System.out.println(
                    sv.getMaSV() + " " + he + " " + sv.hocPhi());

            tong += sv.hocPhi();
        }

        System.out.println("TONG " + tong);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        QuanLyHocPhi ql = new QuanLyHocPhi();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] a = line.split("\\s+");

            String he = a[0];
            String maSV = a[1];
            String tc = a[2];

            try {
                ql.themSinhVien(he, maSV, tc);
            } catch (DuLieuKhongHopLeException e) {
                System.out.println("INVALID " + maSV);
            }
        }

        ql.inDanhSach();
    }
}