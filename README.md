# Challenge Mendel

---

## Descripción

Se va a utilizar una arquitectura hexagonal para la implementación,
por ventajas para testing y desacoplamiento.
Esto nos va a permitir aplicar los principios SOLID.

La estructura está compuesta:
* Dominio:
  * Models
  * Exceptions
* Application:
  * Port
    * in
    * out
  * Service
* Infrastructure:
  * Adapter
    * in
    * out

## Consideraciones

* Se agregó el /api/v1/ antes del recurso, por si a futuro es necesario versionar va a ser mucho más sencillo mantener
  la retro compatibilidad


## Cómo iniciar el proyecto?

Docker 

```bash
    docker build -t challenge .
```
```bash
    docker run -p 8080:8080 challenge 
```

---

Docker compose

```bash
  docker compose up
```


## Endpoints

---

* Guardar Transacción

```
PUT /transactions/$transaction_id
```

Por la descripción y los ejemplos, me parece que debería ser un POST,
dado que el put debería ser para modificaciones y no para agregar recursos.

---
* Consultar Transacciones por Tipo:

```
GET /transactions/types/$type
```
Si el tipo es uno inexistente retorno una lista vacía


---
* Consulta Suma Acumulativa por Id:
```
GET /transactions/sum/$transaction_id
```
Si el transaction id es inexistente retorno 0
