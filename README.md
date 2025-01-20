# Challenge Mendel

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

