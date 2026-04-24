# Arbol-ABB
Aplicación grafica para construir un arbol ABB-con todas las operaciones


[![Licencia: GPL v3](https://img.shields.io/badge/Licencia-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

---

## 📋 Descripción

**Arbol-ABB** es una aplicación gráfica que permite construir y manipular visualmente un **Árbol Binario de Búsqueda (ABB)**, ejecutando sobre él todas las operaciones fundamentales de la estructura. Desarrollada como proyecto académico para el curso de Estructuras de Datos del Programa de Ingeniería de Sistemas y Computación.

---

## ⚙️ Operaciones disponibles

La aplicación implementa las siguientes operaciones sobre el árbol:

| Operación | Descripción |
|---|---|
| `estaVacio` | Verifica si el árbol no contiene nodos |
| `agregarDato` | Inserta un nuevo nodo respetando el orden ABB |
| `eliminarDato` | Elimina un nodo gestionando los tres casos posibles |
| `existeDato` | Busca si un valor existe en el árbol |
| `obtenerPeso` | Retorna el número total de nodos |
| `obtenerAltura` | Retorna la altura del árbol |
| `obtenerNivel` | Retorna el nivel de un nodo dado |
| `obtenerMenor` | Obtiene el nodo con el menor valor |
| `obtenerNodoMayor` | Obtiene el nodo con el mayor valor |
| `obtenerNodoMenor` | Obtiene el nodo con el menor valor |
| `contarHojas` | Cuenta los nodos hoja del árbol |
| `borrarArbol` | Elimina todos los nodos del árbol |

### Recorridos

| Recorrido | Orden |
|---|---|
| `recorrerInOrden` | Izquierda → Raíz → Derecha |
| `recorrerPreOrden` | Raíz → Izquierda → Derecha |
| `recorrerPostOrden` | Izquierda → Derecha → Raíz |
| `imprimirAmplitud` | Por niveles (BFS) |

---


## 🚀 Instalación y ejecución

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/<usuario>/Arbol-ABB.git
   ```
2. Abrir el proyecto en el IDE de preferencia (IntelliJ IDEA, Eclipse, NetBeans).
3. Compilar y ejecutar la clase principal.

---

## 📄 Licencia

Este proyecto se distribuye bajo la licencia **GNU General Public License v3.0**. Consulta el archivo [`LICENSE`](LICENSE) para más detalles.