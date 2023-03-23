package tec.bd.gui;

import javax.swing.*;

public class AboutAppFrame extends JFrame {

    public AboutAppFrame() {

        JLabel aboutLbl = new JLabel("<html>Developed by: Maximilian Latysh<br>TEC ID: 2022091544</html>", SwingConstants.CENTER);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        this.setTitle("About Weather Report");
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        panel.add(aboutLbl);
        JButton okBtn = new JButton("OK");
        panel.add(okBtn);
        okBtn.addActionListener(e -> this.dispose());
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.getContentPane().add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
