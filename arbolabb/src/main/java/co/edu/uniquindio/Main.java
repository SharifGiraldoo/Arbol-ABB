package co.edu.uniquindio;

import javax.swing.SwingUtilities;

import co.edu.uniquindio.controller.ControladorABB;
import co.edu.uniquindio.model.ArbolABB;
import co.edu.uniquindio.view.VentanaPrincipal;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ArbolABB arbol = new ArbolABB();

            VentanaPrincipal ventana = new VentanaPrincipal(arbol);

            @SuppressWarnings("unused")
            ControladorABB controlador = new ControladorABB(arbol, ventana);

            ventana.setVisible(true);
        });
    }
}
