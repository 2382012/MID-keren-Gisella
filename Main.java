import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Mahasiswi> daftarMahasiswi = new ArrayList<>();
    public static ArrayList<Slip> daftarSlip = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm");

    public static void main(String[] args) {
        showMainMenu();
    }

    public static void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nSISTEM PENDATAAN SLIP ASRAMA PUTRI UNAI");
            System.out.println("1. Pendaftaran Akun");
            System.out.println("2. Pengajuan Slip");
            System.out.println("3. Persetujuan Pengurus Asrama");
            System.out.println("4. Notifikasi Status Pengajuan");
            System.out.println("5. Pencatatan Waktu Keluar-Masuk");
            System.out.println("6. Dashboard Statistik");
            System.out.println("7. Pencarian dan Filter Slip");
            System.out.println("8. Keluar");

            String pilihan = input("Pilih menu");
            switch (pilihan) {
                case "1":
                    pendaftaranAkunMahasiswi();
                    break;
                case "2":
                    //pengajuanSlip();
                    break;
                case "3":
                    //persetujuanPengurusAsrama();
                    break;
                case "4":
                    //notifikasiStatusPengajuan();
                    break;
                case "5":
                    //pencatatanWaktuKeluarMasuk();
                    break;
                case "6":
                    //dashboardStatistik();
                    break;
                case "7":
                    //pencarianDanFilterSlip();
                    break;
                case "8":
                    isRunning = false;
                    System.out.println("Terima kasih telah menggunakan sistem ini");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }

    public static void pendaftaranAkunMahasiswi() {
        System.out.println("\nPENDAFTARAN AKUN");
        String nama = input("Nama Lengkap");
        String asrama = input("Asrama");
        String nomorKamar = input("No. Kamar");

        Mahasiswi mahasiswi = new Mahasiswi(nama, asrama, nomorKamar);
        daftarMahasiswi.add(mahasiswi);
        System.out.println("Akun berhasil didaftarkan!");
    }

    public static void pengajuanSlip() {
        //keren punya
    }

    public static void persetujuanPekerjaAsrama() {
        System.out.println("\nPERSETUJUAN PENGURUS ASRAMA");
        if (daftarSlip.isEmpty()) {
            System.out.println("Tidak ada slip yang perlu disetujui.");
            return;
        }

        System.out.println("Daftar Slip yang Menunggu Pesetujuan: ");
        for (int i = 0; i < daftarSlip.size(); i++) {
            Slip slip = daftarSlip.get(i);
            if (!slip.statusPersetujuan) {
                System.out.println((i+1) + ". " + slip.mahasiswi.nama + " - " + slip.jenisSlip + " - " + slip.alasan);
            }
        }

        int pilihan = Integer.parseInt(input("Pilih nomor slip untuk disetujui")) - 1;
        if (pilihan < 0 || pilihan >= daftarSlip.size()) {
            System.out.println("Pilihan tidak valiid.");
            return;
        }

        Slip slip = daftarSlip.get(pilihan);
        slip.statusPersetujuan = true;
        System.out.println("Slip telah disetujui.");
    }

    public static void notifikasiStatusPengajuan() {
        //keren punya
    }

    public static void pencatatanWaktuKeluarMasuk() {
        //gisella punya
    }

    public static void dashboardStatistik() {
        //keren punya
    }

    public static void pencarianDanFilterSlip() {
        //gisella punya
    }




}

class Mahasiswi {
    String nama;
    String asrama;
    String nomorKamar;

    public Mahasiswi(String nama, String kelas, String nomorKamar) {
        this.nama = nama;
        this.asrama = kelas;
        this.nomorKamar = nomorKamar;
    }
}

class Slip {
    Mahasiswi mahasiswi;
    String jenisSlip;
}
