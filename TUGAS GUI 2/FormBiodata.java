import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FormBiodata extends JFrame implements ActionListener {

    private JTextField txtNama, txtNim, txtTempatLahir;

    private JComboBox<String> cmbTanggal;
    private JComboBox<String> cmbBulan;
    private JComboBox<String> cmbTahun;

    private JRadioButton rbLakiLaki, rbPerempuan;
    private ButtonGroup grupJenisKelamin;

    private JTextArea txtAlamat;

    private JButton btnTampilkan, btnReset;

    public FormBiodata() {

        setTitle("Biodata Mahasiswa");
        setSize(650, 570);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Judul
        JLabel lblJudul = new JLabel(
            "<html><center>Biodata Mahasiswa<br>Fakultas Ilmu Komputer</center></html>",
            JLabel.CENTER
        );

        lblJudul.setFont(new Font("Arial", Font.PLAIN, 22));
        add(lblJudul, BorderLayout.NORTH);

        // Panel form
        JPanel panelForm = new JPanel(new GridLayout(6, 2, 12, 14));
        panelForm.setBorder(
            BorderFactory.createEmptyBorder(20, 25, 10, 25)
        );

        txtNama = new JTextField();
        txtNim = new JTextField();
        txtTempatLahir = new JTextField();

        // Tanggal
        cmbTanggal = new JComboBox<>();
        cmbTanggal.addItem("Tanggal");

        for (int i = 1; i <= 31; i++) {
            cmbTanggal.addItem(String.valueOf(i));
        }

        // Bulan
        cmbBulan = new JComboBox<>(new String[]{
            "Bulan", "Januari", "Februari", "Maret",
            "April", "Mei", "Juni", "Juli",
            "Agustus", "September", "Oktober",
            "November", "Desember"
        });

        // Tahun
        cmbTahun = new JComboBox<>();
        cmbTahun.addItem("Tahun");

        for (int i = 2010; i >= 1970; i--) {
            cmbTahun.addItem(String.valueOf(i));
        }

        JPanel panelTanggal = new JPanel(new GridLayout(1, 3, 5, 0));
        panelTanggal.add(cmbTanggal);
        panelTanggal.add(cmbBulan);
        panelTanggal.add(cmbTahun);

        // Jenis kelamin
        rbLakiLaki = new JRadioButton("Laki-laki");
        rbPerempuan = new JRadioButton("Perempuan");

        grupJenisKelamin = new ButtonGroup();
        grupJenisKelamin.add(rbLakiLaki);
        grupJenisKelamin.add(rbPerempuan);

        JPanel panelGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelGender.add(rbLakiLaki);
        panelGender.add(rbPerempuan);

        // Alamat
        txtAlamat = new JTextArea(4, 20);
        txtAlamat.setLineWrap(true);
        txtAlamat.setWrapStyleWord(true);

        JScrollPane scrollAlamat = new JScrollPane(txtAlamat);

        // Memasukkan komponen ke form
        panelForm.add(new JLabel("Nama:"));
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM:"));
        panelForm.add(txtNim);

        panelForm.add(new JLabel("Tempat lahir:"));
        panelForm.add(txtTempatLahir);

        panelForm.add(new JLabel("Tanggal lahir:"));
        panelForm.add(panelTanggal);

        panelForm.add(new JLabel("Jenis kelamin:"));
        panelForm.add(panelGender);

        panelForm.add(new JLabel("Alamat:"));
        panelForm.add(scrollAlamat);

        add(panelForm, BorderLayout.CENTER);

        // Tombol
        JPanel panelTombol = new JPanel(new FlowLayout());

        btnTampilkan = new JButton("Tampilkan Biodata");
        btnReset = new JButton("Reset");

        btnTampilkan.addActionListener(this);
        btnReset.addActionListener(this);

        panelTombol.add(btnTampilkan);
        panelTombol.add(btnReset);

        add(panelTombol, BorderLayout.SOUTH);
    }

    // Event Listener
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnTampilkan) {
            tampilkanBiodata();
        }

        if (e.getSource() == btnReset) {
            resetForm();
        }
    }

    // Menampilkan biodata
    private void tampilkanBiodata() {

        String nama = txtNama.getText().trim();
        String nim = txtNim.getText().trim();
        String tempat = txtTempatLahir.getText().trim();
        String alamat = txtAlamat.getText().trim();

        String tanggal = cmbTanggal.getSelectedItem().toString();
        String bulan = cmbBulan.getSelectedItem().toString();
        String tahun = cmbTahun.getSelectedItem().toString();

        String gender = "";

        if (rbLakiLaki.isSelected()) {
            gender = "Laki-laki";
        } else if (rbPerempuan.isSelected()) {
            gender = "Perempuan";
        }

        if (nama.isEmpty() || nim.isEmpty() ||
            tempat.isEmpty() || alamat.isEmpty() ||
            gender.isEmpty() || tanggal.equals("Tanggal") ||
            bulan.equals("Bulan") || tahun.equals("Tahun")) {

            JOptionPane.showMessageDialog(
                this,
                "Mohon lengkapi semua data!"
            );

            return;
        }

        String hasil =
            "BIODATA MAHASISWA\n\n" +
            "Nama          : " + nama + "\n" +
            "NIM           : " + nim + "\n" +
            "Tempat Lahir  : " + tempat + "\n" +
            "Tanggal Lahir : " + tanggal + " " + bulan + " " + tahun + "\n" +
            "Jenis Kelamin : " + gender + "\n" +
            "Alamat        : " + alamat;

        JOptionPane.showMessageDialog(
            this,
            hasil,
            "Hasil Biodata",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Reset form
    private void resetForm() {

        txtNama.setText("");
        txtNim.setText("");
        txtTempatLahir.setText("");

        cmbTanggal.setSelectedIndex(0);
        cmbBulan.setSelectedIndex(0);
        cmbTahun.setSelectedIndex(0);

        grupJenisKelamin.clearSelection();
        txtAlamat.setText("");

    }

        public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormBiodata form = new FormBiodata();
            form.setVisible(true);
        });
    }
}