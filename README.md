# APP ANDROID PLACES

Detalles técnicos implementación.

## ARQUITECTURA (CLEAN ARCHITECTURE)

Como arquitectura se usa CleanArchitecture, el codigo fuente posee 3 modulos, 2 del tipo Android (App, Data) y 1 del tipo java(Domain)

![Descripción de la imagen](https://github.com/android10/Sample-Data/raw/master/Android-CleanArchitecture-Kotlin/architecture/clean_architecture_reloaded_layers.png)


### APP

Este módulo posee todo lo relacionado a la capa de UI, los activitys, fragments, dialogs, Viewmodels, adapters, mappers(para transformar un objeto del modulo data a uno del modulo app).

### DOMAIN

Este módulo posee la interface de los repositorios(sin implementación), los casos de uso y los data class que se mapean desde el modulo data y que posteriormente los data class del modulo domain seran mapeados a un data class del modulo presentation.

### DATA

Este módulo posee todo lo relacionado a la capa de red(consumo de apis) , base de datos local(room), aqui se crean las implementaciones para consumir la api rest de películas, tambien se crean las implementación de los repository definidos en el modulo domain, los cuales seran usados en los UseCase de dicho modulo. Por ultimo también se creo un remote mediator quien hara el trabajo de gestionar la paginación entre la api rest, y room, para devolver un pagingData el cual sera utilizado en el modulo App.

## PATRON DE DISEÑO MVVM

Como patrón de diseño se usa MVVM debido a su simplicidad y a que se adapta a los componentes de android como lo son los ViewModels, StateFlows.

##### LOGICA DE LA UI

En resumen cada fragment tiene un Sealed Interface el cual contiene los estados de cada pantalla, si dicho estado retorna algo sera un DataClass, si no retorna nada sera un dataObject.

En todos los viewmodel se crea un StateFlow<UIState> que sera del tipo UISTATE creado para cada pantalla, por último cada fragment se subscribe al stateFlow y en base a cada estado ejecuta una determinada acción.

##### FLAVORS

El proyecto tiene 2 FLAVORS (pinkStyle y normalStyle), cada flavor maneja un estilo diferente, tanto en los temas claros y nocturnos, tambien cada flavor asigna un icono, nombre de app y nombre para la BD interna en Room.


## PASOS PARA EJECUTAR EL PROYECTO

Al usar la api de Google Maps, se requiere añadir la KEY en el archico local.properties:



    MAPS_API_KEY=AIzaSyC_x1tHr_FID5An4pY7e9B6aJau89raCig
    

## CAPTURAS

![Descripción de la imagen](https://i.ibb.co/mykTywK/screen1.png)

![Descripción de la imagen](https://i.ibb.co/pjwfdvX/screen2.png)

![Descripción de la imagen](https://i.ibb.co/8jGN97G/screen4.png)
