package co.edu.uniquindio.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArbolABB {

    private Nodo raiz;
    private int peso;

    private Nodo nodoResaltado;
    private final List<Nodo> caminoResaltado;

    public ArbolABB() {
        this.raiz = null;
        this.peso = 0;
        this.caminoResaltado = new ArrayList<>();
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public int obtenerPeso() {
        return peso;
    }

    public void borrarArbol() {
        raiz = null;
        peso = 0;
        nodoResaltado = null;
        caminoResaltado.clear();
    }

    public void agregar(int dato) throws Exception {
        if (estaVacio()) {
            raiz = new Nodo(dato);
            peso++;
            return;
        }
        agregarRecursivo(raiz, dato);
    }

    private void agregarRecursivo(Nodo actual, int dato) throws Exception {
        if (dato == actual.getDato()) {
            throw new Exception("El nodo " + dato + " ya existe en el árbol. No se permiten duplicados.");
        }
        if (dato < actual.getDato()) {
            if (actual.getHijoIzquierdo() == null) {
                Nodo nuevo = new Nodo(dato);
                nuevo.setPadre(actual);
                actual.setHijoIzquierdo(nuevo);
                peso++;
            } else {
                agregarRecursivo(actual.getHijoIzquierdo(), dato);
            }
        } else {
            if (actual.getHijoDerecho() == null) {
                Nodo nuevo = new Nodo(dato);
                nuevo.setPadre(actual);
                actual.setHijoDerecho(nuevo);
                peso++;
            } else {
                agregarRecursivo(actual.getHijoDerecho(), dato);
            }
        }
    }

    public Nodo buscar(int dato) {
        caminoResaltado.clear();
        return buscarRecursivo(raiz, dato);
    }

    private Nodo buscarRecursivo(Nodo actual, int dato) {
        if (actual == null) {
            return null;
        }
        caminoResaltado.add(actual);
        if (dato == actual.getDato()) {
            nodoResaltado = actual;
            return actual;
        }
        if (dato < actual.getDato()) {
            return buscarRecursivo(actual.getHijoIzquierdo(), dato);
        } else {
            return buscarRecursivo(actual.getHijoDerecho(), dato);
        }
    }

    public boolean existe(int dato) {
        return buscar(dato) != null;
    }

    public void eliminar(int dato) throws Exception {
        if (estaVacio()) {
            throw new Exception("El árbol está vacío. No se puede eliminar.");
        }
        Nodo nodo = buscarNodo(raiz, dato);
        if (nodo == null) {
            throw new Exception("El nodo " + dato + " no existe en el árbol.");
        }
        eliminarNodo(nodo);
        peso--;
    }

    private Nodo buscarNodo(Nodo actual, int dato) {
        if (actual == null) {
            return null;
        }
        if (dato == actual.getDato()) {
            return actual;
        }
        if (dato < actual.getDato()) {
            return buscarNodo(actual.getHijoIzquierdo(), dato);
        }
        return buscarNodo(actual.getHijoDerecho(), dato);
    }

    private void eliminarNodo(Nodo nodo) {

        if (nodo.esHoja()) {
            if (nodo == raiz) {
                raiz = null;
            } else {
                Nodo padre = nodo.getPadre();
                if (padre.getHijoIzquierdo() == nodo) {
                    padre.setHijoIzquierdo(null);
                } else {
                    padre.setHijoDerecho(null);
                }
            }
        } else if (nodo.getHijoIzquierdo() != null && nodo.getHijoDerecho() != null) {

            Nodo mayorIzquierdo = obtenerMayorDeSubarbol(nodo.getHijoIzquierdo());
            int datoSustituto = mayorIzquierdo.getDato();
            eliminarNodo(mayorIzquierdo);
            peso++;
            nodo.setDato(datoSustituto);
        } else {
            Nodo hijo = (nodo.getHijoIzquierdo() != null)
                    ? nodo.getHijoIzquierdo()
                    : nodo.getHijoDerecho();
            hijo.setPadre(nodo.getPadre());
            if (nodo == raiz) {
                raiz = hijo;
            } else {
                Nodo padre = nodo.getPadre();
                if (padre.getHijoIzquierdo() == nodo) {
                    padre.setHijoIzquierdo(hijo);
                } else {
                    padre.setHijoDerecho(hijo);
                }
            }
        }
    }

    private Nodo obtenerMayorDeSubarbol(Nodo nodo) {
        if (nodo.getHijoDerecho() == null) {
            return nodo;
        }
        return obtenerMayorDeSubarbol(nodo.getHijoDerecho());
    }

    public int obtenerAltura() {
        return calcularAltura(raiz);
    }

    private int calcularAltura(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        int alturaIzq = calcularAltura(nodo.getHijoIzquierdo());
        int alturaDer = calcularAltura(nodo.getHijoDerecho());
        return 1 + Math.max(alturaIzq, alturaDer);
    }

    public int obtenerNivel(int dato) {
        return calcularNivel(raiz, dato, 0);
    }

    private int calcularNivel(Nodo actual, int dato, int nivel) {
        if (actual == null) {
            return -1;
        }
        if (actual.getDato() == dato) {
            return nivel;
        }
        if (dato < actual.getDato()) {
            return calcularNivel(actual.getHijoIzquierdo(), dato, nivel + 1);
        }
        return calcularNivel(actual.getHijoDerecho(), dato, nivel + 1);
    }

    public int contarHojas() {
        return contarHojasRecursivo(raiz);
    }

    private int contarHojasRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.esHoja()) {
            return 1;
        }
        return contarHojasRecursivo(nodo.getHijoIzquierdo())
                + contarHojasRecursivo(nodo.getHijoDerecho());
    }

    public Nodo obtenerNodoMayor() {
        if (estaVacio()) {
            return null;
        }
        Nodo actual = raiz;
        while (actual.getHijoDerecho() != null) {
            actual = actual.getHijoDerecho();
        }
        return actual;
    }

    public Nodo obtenerNodoMenor() {
        if (estaVacio()) {
            return null;
        }
        Nodo actual = raiz;
        while (actual.getHijoIzquierdo() != null) {
            actual = actual.getHijoIzquierdo();
        }
        return actual;
    }

    public List<Integer> obtenerHojas() {
        List<Integer> hojas = new ArrayList<>();
        recolectarHojas(raiz, hojas);
        return hojas;
    }

    private void recolectarHojas(Nodo nodo, List<Integer> hojas) {
        if (nodo == null) {
            return;
        }
        if (nodo.esHoja()) {
            hojas.add(nodo.getDato());
            return;
        }
        recolectarHojas(nodo.getHijoIzquierdo(), hojas);
        recolectarHojas(nodo.getHijoDerecho(), hojas);
    }

    public int obtenerLongitudCamino(int dato) {
        return obtenerNivel(dato); // el nivel equivale a la longitud del camino desde raíz
    }

    public List<Integer> obtenerCamino(int dato) {
        List<Integer> camino = new ArrayList<>();
        construirCamino(raiz, dato, camino);
        return camino;
    }

    private boolean construirCamino(Nodo actual, int dato, List<Integer> camino) {
        if (actual == null) {
            return false;
        }
        camino.add(actual.getDato());
        if (actual.getDato() == dato) {
            return true;
        }
        if (dato < actual.getDato()) {
            if (construirCamino(actual.getHijoIzquierdo(), dato, camino)) {
                return true;
            }
        } else {
            if (construirCamino(actual.getHijoDerecho(), dato, camino)) {
                return true;
            }
        }
        camino.remove(camino.size() - 1);
        return false;
    }

    public List<Integer> recorrerInOrden() {
        List<Integer> resultado = new ArrayList<>();
        recorrerInOrdenRecursivo(raiz, resultado);
        return resultado;
    }

    private void recorrerInOrdenRecursivo(Nodo nodo, List<Integer> resultado) {
        if (nodo == null) {
            return;
        }
        recorrerInOrdenRecursivo(nodo.getHijoIzquierdo(), resultado);
        resultado.add(nodo.getDato());
        recorrerInOrdenRecursivo(nodo.getHijoDerecho(), resultado);
    }

    public List<Integer> recorrerPreOrden() {
        List<Integer> resultado = new ArrayList<>();
        recorrerPreOrdenRecursivo(raiz, resultado);
        return resultado;
    }

    private void recorrerPreOrdenRecursivo(Nodo nodo, List<Integer> resultado) {
        if (nodo == null) {
            return;
        }
        resultado.add(nodo.getDato());
        recorrerPreOrdenRecursivo(nodo.getHijoIzquierdo(), resultado);
        recorrerPreOrdenRecursivo(nodo.getHijoDerecho(), resultado);
    }

    public List<Integer> recorrerPostOrden() {
        List<Integer> resultado = new ArrayList<>();
        recorrerPostOrdenRecursivo(raiz, resultado);
        return resultado;
    }

    private void recorrerPostOrdenRecursivo(Nodo nodo, List<Integer> resultado) {
        if (nodo == null) {
            return;
        }
        recorrerPostOrdenRecursivo(nodo.getHijoIzquierdo(), resultado);
        recorrerPostOrdenRecursivo(nodo.getHijoDerecho(), resultado);
        resultado.add(nodo.getDato());
    }

    public List<List<Integer>> recorrerPorNiveles() {
        List<List<Integer>> niveles = new ArrayList<>();
        if (estaVacio()) {
            return niveles;
        }
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);
        while (!cola.isEmpty()) {
            int tamañoNivel = cola.size();
            List<Integer> nivel = new ArrayList<>();
            for (int i = 0; i < tamañoNivel; i++) {
                Nodo actual = cola.poll();
                nivel.add(actual.getDato());
                if (actual.getHijoIzquierdo() != null) {
                    cola.add(actual.getHijoIzquierdo());
                }
                if (actual.getHijoDerecho() != null) {
                    cola.add(actual.getHijoDerecho());
                }
            }
            niveles.add(nivel);
        }
        return niveles;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public Nodo getNodoResaltado() {
        return nodoResaltado;
    }

    public void setNodoResaltado(Nodo n) {
        this.nodoResaltado = n;
    }

    public List<Nodo> getCaminoResaltado() {
        return caminoResaltado;
    }

    public void limpiarResaltado() {
        nodoResaltado = null;
        caminoResaltado.clear();
    }
}
