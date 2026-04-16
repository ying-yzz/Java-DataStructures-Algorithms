# Práctica 3: Buscador de Biblioteca Digital (BD)

Este proyecto implementa el motor de búsqueda de una biblioteca con 76 libros en formato texto.

### Características principales:
* **Indexación y Búsqueda:** El sistema escanea los documentos para construir un índice de términos que permite el acceso directo a la información.
* **Estructura de Datos Index:** Utiliza un `Map<Termino, ListaConPI<Posting>>` implementado mediante una **Tabla Hash Enlazada**.
* **Optimización Matemática:**
    * Implementación del cálculo de `hashCode` mediante la **Regla de Horner** para mayor eficiencia.
    * Consistencia garantizada entre los métodos `equals` y `hashCode`.
* **Gestión de Memoria y Rendimiento:**
    * Implementación de **Rehashing** dinámico: cuando el factor de carga supera el 0.75, la capacidad de la tabla se duplica al siguiente número primo.
    * Análisis estadístico de colisiones y coste promedio de localización.
