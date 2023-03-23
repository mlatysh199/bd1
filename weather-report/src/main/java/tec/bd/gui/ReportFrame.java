package tec.bd.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import tec.bd.weather.model.Report;
import tec.bd.weather.service.WeatherService;

public class ReportFrame extends JFrame {

    private Report report = new Report();
    private JTable table;
    private JTextField textField;

    public ReportFrame(WeatherService weatherService, FrameTypes frameType) {

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        switch (frameType) {
            case CITY:
                this.setTitle("Report by City");
                panel.add(new JLabel("City:"), c);
                break;
            case ZIP_CODE:
                this.setTitle("Report by Zip Code");
                panel.add(new JLabel("Zip Code:"), c);
                break;
            case IMN_CITY:
                this.setTitle("Report by IMN City");
                panel.add(new JLabel("City:"), c);
                break;
            case IMN_ZIP_CODE:
                this.setTitle("Report by IMN Zip Code");
                panel.add(new JLabel("Zip Code:"), c);
                break;
            default:
                break;
        }
        c.gridx = 1;
        panel.add(textField = new JTextField(10), c);
        textField.addActionListener(e -> {
            try {
                switch (frameType) {
                    case CITY:
                        updateReport(weatherService.byCity(textField.getText()));
                        break;
                    case ZIP_CODE:
                        updateReport(weatherService.byZipCode(Integer.parseInt((textField.getText()))));
                        break;
                    case IMN_CITY:
                        updateReport(weatherService.byCity(textField.getText()));
                        break;
                    case IMN_ZIP_CODE:
                        updateReport(weatherService.byZipCode(Integer.parseInt((textField.getText()))));
                        break;
                    default:
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        JButton buttonGet = new JButton("Get Report");
        JButton buttonCancel = new JButton("Cancel");
        buttonGet.addActionListener(e -> {
            try {
            switch (frameType) {
                case CITY:
                    updateReport(weatherService.byCity(textField.getText()));
                    break;
                case ZIP_CODE:
                    updateReport(weatherService.byZipCode(Integer.parseInt((textField.getText()))));
                    break;
                case IMN_CITY:
                    updateReport(weatherService.byCity(textField.getText()));
                    break;
                case IMN_ZIP_CODE:
                    updateReport(weatherService.byZipCode(Integer.parseInt((textField.getText()))));
                    break;
                default:
                    break;
            }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        buttonCancel.addActionListener(e -> {
            this.dispose();
        });
        c.gridx = 0;
        c.gridy = 1;
        panel.add(buttonGet, c);
        c.gridx = 1;
        panel.add(buttonCancel, c);
        table = new JTable();
        updateTable();
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        panel.add(table, c);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        this.add(panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void updateReport(Report report) {
        this.report = report;
        updateTable();
    }

    private void updateTable() {
        DefaultTableModel model = new DefaultTableModel(
            new String[] { "Key", "Value" }, 0
        );
        for (Map.Entry<String,Object> entry : report.toMap().entrySet()) {
            model.addRow(new Object[] { entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : "N/A" });
        }
        table.setModel(model);
    }
}
