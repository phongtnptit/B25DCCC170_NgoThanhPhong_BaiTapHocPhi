
import java.util.*;

class SinhVien {
    private String maSV;
    private String hoTen;
    private String ngaySinh;

    public SinhVien(String maSV, String hoTen, String ngaySinh) {
        this.maSV = maSV;
        this.hoTen = chuanHoaTen(hoTen);
        this.ngaySinh = chuanHoaNgaySinh(ngaySinh);
    }

    private String chuanHoaTen(String ten) {
        String[] words = ten.trim().toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            if (sb.length() > 0) {
                sb.append(" ");
            }

            sb.append(Character.toUpperCase(word.charAt(0)));
            sb.append(word.substring(1));
        }

        return sb.toString();
    }

    private String chuanHoaNgaySinh(String ngaySinh) {
        String[] parts = ngaySinh.split("/");

        int ngay = Integer.parseInt(parts[0]);
        int thang = Integer.parseInt(parts[1]);
        int nam = Integer.parseInt(parts[2]);

        return String.format("%02d/%02d/%04d", ngay, thang, nam);
    }

    @Override
    public String toString() {
        return maSV + " " + hoTen + " " + ngaySinh;
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        ArrayList<SinhVien> ds = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String maSV = sc.nextLine().trim();
            String hoTen = sc.nextLine();
            String ngaySinh = sc.nextLine();

            ds.add(new SinhVien(maSV, hoTen, ngaySinh));
        }

        for (SinhVien sv : ds) {
            System.out.println(sv);
        }
    }
}
