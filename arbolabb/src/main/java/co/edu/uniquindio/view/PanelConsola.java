package co.edu.uniquindio.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;



public class PanelConsola extends JPanel {

    private final JTextPane areaTexto;
    private final StyledDocument doc;

    
    private static final Color COLOR_FONDO   = new Color(8, 12, 22);
    private static final Color COLOR_OK      = new Color(80, 220, 130);
    private static final Color COLOR_ERROR   = new Color(255, 90, 90);
    private static final Color COLOR_INFO    = new Color(120, 180, 255);
    private static final Color COLOR_TITULO  = new Color(210, 160, 255);
    private static final Color COLOR_DATO    = new Color(255, 200, 80);
    private static final Color COLOR_NEUTRO  = new Color(160, 180, 210);

    public PanelConsola() {
        setLayout(new BorderLayout());
        setBackground(COLOR_FONDO);

        areaTexto = new JTextPane();
        areaTexto.setEditable(false);
        areaTexto.setBackground(COLOR_FONDO);
        areaTexto.setBorder(new EmptyBorder(8, 10, 8, 10));
        doc = areaTexto.getStyledDocument();

        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(40, 60, 100), 1));
        scroll.setBackground(COLOR_FONDO);
        scroll.getVerticalScrollBar().setBackground(COLOR_FONDO);

        add(scroll, BorderLayout.CENTER);

        
        JLabel titulo = new JLabel("  CONSOLA DE OPERACIONES");
        titulo.setFont(new Font("Monospaced", Font.BOLD, 11));
        titulo.setForeground(new Color(100, 140, 220));
        titulo.setBackground(new Color(14, 20, 38));
        titulo.setOpaque(true);
        titulo.setBorder(new EmptyBorder(5, 8, 5, 8));
        add(titulo, BorderLayout.NORTH);

        imprimirBienvenida();
    }

    private void imprimirBienvenida() {
        imprimirTitulo("═══════════════════════════════════════════════");
        imprimirTitulo("   ÁRBOL BINARIO DE BÚSQUEDA (ABB) — Editor   ");
        imprimirTitulo("═══════════════════════════════════════════════");
        imprimirInfo("Ingrese valores y utilice las operaciones del panel izquierdo.");
        imprimirNeutro("Regla ABB: menores a la izquierda · mayores a la derecha\n");
    }

    
    public void imprimirOK(String texto) {
        agregar("✓ " + texto + "\n", COLOR_OK, Font.PLAIN);
        scrollAbajo();
    }

    public void imprimirError(String texto) {
        agregar("✗ " + texto + "\n", COLOR_ERROR, Font.BOLD);
        scrollAbajo();
    }

    public void imprimirInfo(String texto) {
        agregar("ℹ " + texto + "\n", COLOR_INFO, Font.PLAIN);
        scrollAbajo();
    }

    public void imprimirTitulo(String texto) {
        agregar(texto + "\n", COLOR_TITULO, Font.BOLD);
        scrollAbajo();
    }

    public void imprimirDato(String etiqueta, Object valor) {
        agregar("  " + etiqueta + ": ", COLOR_NEUTRO, Font.PLAIN);
        agregar(String.valueOf(valor) + "\n", COLOR_DATO, Font.BOLD);
        scrollAbajo();
    }

    public void imprimirNeutro(String texto) {
        agregar(texto + "\n", COLOR_NEUTRO, Font.PLAIN);
        scrollAbajo();
    }

    public void imprimirRecorrido(String tipo, List<Integer> elementos) {
        imprimirTitulo("── Recorrido " + tipo + " ──────────────────");
        if (elementos.isEmpty()) {
            imprimirInfo("(árbol vacío)");
            return;
        }
        StringBuilder sb = new StringBuilder("  [ ");
        for (int i = 0; i < elementos.size(); i++) {
            sb.append(elementos.get(i));
            if (i < elementos.size() - 1) sb.append(" → ");
        }
        sb.append(" ]");
        agregar(sb.toString() + "\n", COLOR_DATO, Font.BOLD);
        scrollAbajo();
    }

    public void imprimirNiveles(List<List<Integer>> niveles) {
        imprimirTitulo("── Recorrido por Niveles (Amplitud) ──────────");
        if (niveles.isEmpty()) {
            imprimirInfo("(árbol vacío)");
            return;
        }
        for (int i = 0; i < niveles.size(); i++) {
            agregar("  Nivel " + i + ": ", COLOR_NEUTRO, Font.PLAIN);
            agregar(niveles.get(i).toString() + "\n", COLOR_DATO, Font.BOLD);
        }
        scrollAbajo();
    }

    public void imprimirSeparador() {
        agregar("  ─────────────────────────────────────────────\n", new Color(50, 70, 110), Font.PLAIN);
        scrollAbajo();
    }

    public void limpiar() {
        try {
            doc.remove(0, doc.getLength());
        } catch (BadLocationException ignored) {}
        imprimirBienvenida();
    }

    

    private void agregar(String texto, Color color, int estilo) {
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setForeground(attrs, color);
        StyleConstants.setBold(attrs, estilo == Font.BOLD);
        StyleConstants.setFontFamily(attrs, "Monospaced");
        StyleConstants.setFontSize(attrs, 12);
        try {
            doc.insertString(doc.getLength(), texto, attrs);
        } catch (BadLocationException ignored) {}
    }

    private void scrollAbajo() {
        SwingUtilities.invokeLater(() -> {
            areaTexto.setCaretPosition(doc.getLength());
        });
    }
}
