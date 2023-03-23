package tec.bd.gui;

import javax.swing.*;

import tec.bd.ApplicationContext;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame  {

    private final ApplicationContext APP_CONTEXT;

    public MainFrame(ApplicationContext appContext) {
        this.APP_CONTEXT = appContext;
        setUIFont(new javax.swing.plaf.FontUIResource("Monospaced",Font.PLAIN,12));

        JMenuItem byZipCode = new JMenuItem("By Zip Code");
        byZipCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReportFrame(APP_CONTEXT.openWeatherService, FrameTypes.ZIP_CODE);
            }
        });
        JMenuItem byCity = new JMenuItem("By City");
        byCity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReportFrame(APP_CONTEXT.openWeatherService, FrameTypes.CITY);
            }
        });
        JMenuItem byIMNZipCode = new JMenuItem("By IMN Zip Code");
        byIMNZipCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReportFrame(APP_CONTEXT.imnService, FrameTypes.IMN_ZIP_CODE);
            }
        });
        JMenuItem byIMNCity = new JMenuItem("By IMN City");
        byIMNCity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReportFrame(APP_CONTEXT.imnService, FrameTypes.IMN_CITY);
            }
        });

        JMenu weatherReportMenu = new JMenu("Weather Report");
        weatherReportMenu.add(byZipCode);
        weatherReportMenu.add(byCity);
        weatherReportMenu.add(byIMNZipCode);
        weatherReportMenu.add(byIMNCity);

        JMenuItem aboutApp = new JMenuItem("About Weather Report");
        aboutApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutAppFrame();
            }
        });

        JMenu about = new JMenu("Help");
        about.add(aboutApp);


        JMenuBar menubar = new JMenuBar();
        menubar.add(weatherReportMenu);
        menubar.add(about);

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        mainPanel.add(new JLabel("Weather Report"));

        this.setTitle("Weather Report");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setJMenuBar(menubar);
    }

    public static void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof javax.swing.plaf.FontUIResource) UIManager.put (key, f);
        }
    } 
}
