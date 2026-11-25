
import java.util.*;

interface MienGiam {
    long tinhGiam(long hocPhi);
}

class ConChinhSach implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return hocPhi * 50 / 100;
    }
}

class HoNgheo implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return hocPhi * 70 / 100;
    }
}

class HocBong implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return Math.min(hocPhi, 3000000L);
    }
}

abstract class SinhVien {
    protected String maSV;
    protected int soTinChi;
    protected MienGiam mienGiam;

    public SinhVien(String maSV, int soTinChi) {
        this.maSV = maSV;
        this.soTinChi = soTinChi;
        this.mienGiam = null;
    }

    public abstract long hocPhi();

    public void setMienGiam(MienGiam mienGiam) {
        this.mienGiam = mienGiam;
    }

    public long tienGiam() {
        if (mienGiam == null) {
            return 0;
        }

        return mienGiam.tinhGiam(hocPhi());
    }

    public long phaiNop() {
        return hocPhi() - tienGiam();
    }

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

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        ArrayList<SinhVien> ds = new ArrayList<>();
        HashMap<String, SinhVien> map = new HashMap<>();
        HashMap<String, String> heCuaSV = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String he = sc.next();
            String maSV = sc.next();
            int tc = sc.nextInt();

            SinhVien sv;

            if (he.equals("CQ")) {
                sv = new ChinhQuy(maSV, tc);
            } else if (he.equals("CLC")) {
                sv = new ChatLuongCao(maSV, tc);
            } else {
                sv = new LienThong(maSV, tc);
            }

            ds.add(sv);
            map.put(maSV, sv);
            heCuaSV.put(maSV, he);
        }

        int m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            String maSV = sc.next();
            String maCS = sc.next();

            if (!map.containsKey(maSV)) {
                System.out.println("NOT FOUND " + maSV);
                continue;
            }

            MienGiam mg = null;

            if (maCS.equals("CS")) {
                mg = new ConChinhSach();
            } else if (maCS.equals("HN")) {
                mg = new HoNgheo();
            } else if (maCS.equals("HB")) {
                mg = new HocBong();
            }

            map.get(maSV).setMienGiam(mg);
        }

        HashMap<String, Integer> soLuong = new HashMap<>();
        HashMap<String, Long> tongTien = new HashMap<>();

        for (SinhVien sv : ds) {
            String maSV = sv.getMaSV();
            String he = heCuaSV.get(maSV);

            long hocPhi = sv.hocPhi();
            long giam = sv.tienGiam();
            long phaiNop = sv.phaiNop();

            System.out.println(
                    maSV + " " + he + " " +
                            hocPhi + " " + giam + " " + phaiNop);

            soLuong.put(he, soLuong.getOrDefault(he, 0) + 1);

            tongTien.put(
                    he,
                    tongTien.getOrDefault(he, 0L) + phaiNop);
        }

        String[] cacHe = { "CQ", "CLC", "LT" };

        for (String he : cacHe) {
            if (soLuong.containsKey(he)) {
                System.out.println(
                        he + " " +
                                soLuong.get(he) + " " +
                                tongTien.get(he));
            }
        }
    }
}