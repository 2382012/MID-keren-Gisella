import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class pendataanSlipAsramaPutri {
    public static ArrayList<Mahasiswi> daftarMahasiswi = new ArrayList<>();
    public static ArrayList<Slip> daftarSlip = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    public static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    public static void main(String[] args) {
        showMainMenu();
    }

    public static void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nSISTEM PENDATAAN SLIP ASRAMA PUTRI");
            System.out.println("1. Pendaftaran Akun");
            System.out.println("2. Pengajuan Slip");
            System.out.println("3. Persetujuan Pembimbing Asrama");
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
                    pengajuanSlip();
                    break;
                case "3":
                    persetujuanPengurusAsrama();
                    break;
                case "4":
                    notifikasiStatusPengajuan();
                    break;
                case "5":
                    pencatatanWaktuKembali();
                    break;
                case "6":
                    dashboardStatistik();
                    break;
                case "7":
                    pencarianDanFilterSlip();
                    break;
                case "8":
                    isRunning = false;
                    System.out.println("Terima kasih telah menggunakan sistem. Sampai jumpa!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }

    public static void pendaftaranAkunMahasiswi() { // Gisella
        System.out.println("\nPENDAFTARAN AKUN");
        String nama = input("Nama lengkap");
        String asrama = input("Asrama");
        String nomorKamar = input("Nomor kamar");

        Mahasiswi mahasiswi = new Mahasiswi(nama, asrama, nomorKamar);
        daftarMahasiswi.add(mahasiswi);
        System.out.println("Akun mahasiswi berhasil didaftarkan!");
    }

    public static void pengajuanSlip() { // Karenina

    }

    public static void persetujuanPengurusAsrama() { // Gisella
        System.out.println("\nPERSETUJUAN PEMBIMBING ASRAMA");
        if (daftarSlip.isEmpty()) {
            System.out.println("Tidak ada slip yang perlu disetujui.");
            return;
        }

        System.out.println("Daftar Slip yang Menunggu Persetujuan:");
        for (int i = 0; i < daftarSlip.size(); i++) {
            Slip slip = daftarSlip.get(i);
            if (!slip.statusPersetujuan) {
                System.out.println((i+1) + ". " + slip.mahasiswi.nama + " - " + slip.jenisSlip + " - " + slip.alasan);
            }
        }

        int pilihan = Integer.parseInt(input("Pilih nomor slip untuk disetujui")) - 1;
        if (pilihan < 0 || pilihan >= daftarSlip.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        Slip slip = daftarSlip.get(pilihan);
        slip.statusPersetujuan = true;
        System.out.println("Slip telah disetujui.");
    }

    public static void notifikasiStatusPengajuan() {
        System.out.println("\nNOTIFIKASI STATUS PENGAJUAN");
        for (Slip slip : daftarSlip) {
            System.out.println(slip.mahasiswi.nama + " - " + slip.jenisSlip + ": " +
                    (slip.statusPersetujuan ? "Disetujui" : "Menunggu Persetujuan"));
        }
    }

    public static void pencatatanWaktuKembali() {
        System.out.println("\nPENCATATAN WAKTU KELUAR-MASUK");
        System.out.println("Daftar Slip yang Disetujui:");
        for (int i = 0; i < daftarSlip.size(); i++) {
            Slip slip = daftarSlip.get(i);
            if (slip.statusPersetujuan && slip.waktuKembali == null) {
                System.out.println((i+1) + ". " + slip.mahasiswi.nama + " - " + slip.jenisSlip);
            }
        }

        int pilihan = Integer.parseInt(input("Pilih nomor slip untuk mencatat waktu kembali")) - 1;
        if (pilihan < 0 || pilihan >= daftarSlip.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        Slip slip = daftarSlip.get(pilihan);
        String tanggalKembali = input("Tanggal kembali (dd-MM-yyyy)");
        String waktuKembali = input("Waktu kembali (HH:mm)");
        String tanggalWaktuKembali = tanggalKembali + " " + waktuKembali;
        slip.waktuKembali = tanggalWaktuKembali;
        System.out.println("Waktu kembali berhasil dicatat.");
    }

    public static void dashboardStatistik() {

    }

    public static void pencarianDanFilterSlip() {
        System.out.println("\nPENCARIAN DAN FILTER SLIP");
        System.out.println("1. Filter berdasarkan jenis slip");
        System.out.println("2. Filter berdasarkan status persetujuan");

        String pilihan = input("Pilih opsi pencarian/filter");

        switch (pilihan) {
            case "1":
                filterSlipBerdasarkanJenis();
                break;
            case "2":
                filterSlipBerdasarkanStatus();
                break;
            default:
                System.out.println("Pilihan tidak valid");
        }
    }

    private static void filterSlipBerdasarkanJenis() {
        String jenisSlip = input("Masukkan jenis slip (Keluar/Weekend)");
        boolean ditemukan = false;
        for (Slip slip : daftarSlip) {
            if (slip.jenisSlip.equalsIgnoreCase(jenisSlip)) {
                tampilkanInfoSlip(slip);
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Tidak ada slip dengan jenis tersebut.");
        }
    }

    private static void filterSlipBerdasarkanStatus() {
        String status = input("Masukkan status (Disetujui/Menunggu)");
        boolean statusPersetujuan = status.equalsIgnoreCase("Disetujui");
        boolean ditemukan = false;
        for (Slip slip : daftarSlip) {
            if (slip.statusPersetujuan == statusPersetujuan) {
                tampilkanInfoSlip(slip);
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Tidak ada slip dengan status tersebut.");
        }
    }

    private static void tampilkanInfoSlip(Slip slip) {
        System.out.println("Nama: " + slip.mahasiswi.nama);
        System.out.println("Jenis Slip: " + slip.jenisSlip);
        System.out.println("Alasan: " + slip.alasan);
        System.out.println("Tanggal dan Waktu Keluar: " + slip.tanggalKeluar);
        System.out.println("Status: " + (slip.statusPersetujuan ? "Disetujui" : "Menunggu Persetujuan"));
        if (slip.waktuKembali != null) {
            System.out.println("Tanggal dan Waktu Kembali: " + slip.waktuKembali);
        }
        System.out.println("--------------------");
    }

    public static String input(String info) {
        System.out.print(info + " : ");
        return scanner.nextLine();
    }
}

class Mahasiswi {
    String nama;
    String kelas;
    String nomorKamar;

    public Mahasiswi(String nama, String kelas, String nomorKamar) {
        this.nama = nama;
        this.kelas = kelas;
        this.nomorKamar = nomorKamar;
    }
}

class Slip {
    Mahasiswi mahasiswi;
    String jenisSlip;
    String alasan;
    String tanggalKeluar;
    String waktuKembali;
    boolean statusPersetujuan;

    public Slip(Mahasiswi mahasiswi, String jenisSlip, String alasan, String tanggalKeluar) {
        this.mahasiswi = mahasiswi;
        this.jenisSlip = jenisSlip;
        this.alasan = alasan;
        this.tanggalKeluar = tanggalKeluar;
        this.statusPersetujuan = false;
    }
}
