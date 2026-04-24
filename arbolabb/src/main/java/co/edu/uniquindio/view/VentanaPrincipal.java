package co.edu.uniquindio.view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import co.edu.uniquindio.model.ArbolABB;

public class VentanaPrincipal extends JFrame {

    private PanelControles panelControles;
    private PanelArbol panelArbol;
    private PanelConsola panelConsola;

    public VentanaPrincipal(ArbolABB arbol) {
        configurarVentana();
        construirUI(arbol);
    }

    @SuppressWarnings("UseSpecificCatch")
    private void configurarVentana() {
        setTitle("Árbol Binario de Búsqueda (ABB) — Estructura de Datos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1180, 760);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);

        setIconImage(crearIcono());

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
        }
    }

    private void construirUI(ArbolABB arbol) {
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(new Color(10, 14, 28));

        panelControles = new PanelControles();
        JScrollPane scrollControles = new JScrollPane(panelControles);
        scrollControles.setBorder(BorderFactory.createEmptyBorder());
        scrollControles.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollControles.getVerticalScrollBar().setUnitIncrement(12);

        panelArbol = new PanelArbol(arbol);
        JScrollPane scrollArbol = new JScrollPane(panelArbol);
        scrollArbol.setBorder(BorderFactory.createLineBorder(new Color(30, 45, 80), 1));
        scrollArbol.setBackground(new Color(13, 17, 30));

        panelConsola = new PanelConsola();
        panelConsola.setPreferredSize(new Dimension(0, 185));

        JSplitPane splitVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollArbol, panelConsola);
        splitVertical.setResizeWeight(0.65);
        splitVertical.setDividerSize(5);
        splitVertical.setDividerLocation(490);
        splitVertical.setBorder(null);
        splitVertical.setBackground(new Color(10, 14, 28));

        JSplitPane splitHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollControles, splitVertical);
        splitHorizontal.setResizeWeight(0.0);
        splitHorizontal.setDividerSize(5);
        splitHorizontal.setDividerLocation(230);
        splitHorizontal.setBorder(null);
        splitHorizontal.setBackground(new Color(10, 14, 28));

        add(splitHorizontal, BorderLayout.CENTER);

        add(crearBarraEstado(), BorderLayout.SOUTH);
    }

    private JPanel crearBarraEstado() {
        JPanel barra = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 3));
        barra.setBackground(new Color(12, 17, 35));
        barra.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(30, 45, 80)));

        JLabel lbl = new JLabel("Ingeniería de Sistemas  ·  Estructura de Datos  ·  Árbol Binario de Búsqueda (ABB)");
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 10));
        lbl.setForeground(new Color(60, 85, 140));
        barra.add(lbl);
        return barra;
    }

    private Image crearIcono() {

        int size = 32;
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(size, size, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(100, 60, 200));
        g2.fillOval(12, 2, 8, 8);
        g2.setColor(new Color(60, 130, 220));
        g2.fillOval(4, 14, 8, 8);
        g2.fillOval(20, 14, 8, 8);
        g2.setColor(new Color(40, 160, 100));
        g2.fillOval(0, 24, 7, 7);
        g2.fillOval(13, 24, 7, 7);
        g2.fillOval(26, 24, 6, 6);
        g2.setColor(new Color(80, 100, 160));
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawLine(16, 10, 8, 14);
        g2.drawLine(16, 10, 24, 14);
        g2.drawLine(8, 22, 3, 24);
        g2.drawLine(8, 22, 16, 24);
        g2.drawLine(24, 22, 29, 24);
        g2.dispose();
        return img;
    }

    public PanelControles getPanelControles() {
        return panelControles;
    }

    public PanelArbol getPanelArbol() {
        return panelArbol;
    }

    public PanelConsola getPanelConsola() {
        return panelConsola;
    }
}
