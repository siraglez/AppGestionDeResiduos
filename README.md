# AppGestionDeResiduos
link al repositorio: https://github.com/siraglez/AppGestionDeResiduos.git 

## Proyecto 

En el proyecto se nos pedía realizar una aplicacion que se encargue de gestionar los residuos, la aplicación debe estar compuesta por: 

  -> Pantalla de inicio con un saludo personalizado y botones de navegación.
  
  -> Pantalla de calendario con la opcion de recordatorios.
  
  -> Pantalla con un mapa con ubicaciones para los puntos de reciclaje.
  
  -> Pantalla para las estadísticas de la aplicacion. 

## Pantalla inicio 

Esta sería la pantalla principal y se relaciona con la MainActivity. 

Esta clase se encarga de crear una inicio para que el usuario sea binevenido, con un saludo el cual cambia dependiendo de la hora en la que se esta utilizando la aplicación y con una bienvenida al usuario. 

Después encontramos los correspondientes botones que nos permitiran navegar a la pantalla del calendario, a la del mapa y a la de las estadísticas.

## Pantalla calendario 

En la pantalla de calendario nos encontramos con un calendario en el cual el usuario puede moverse libremente y navegar hasta la fecha que este quiera elegir. 

Contiene dos botones: 

  -> Establecer Recordatorio: este botón permite al usuario elegir una fecha y añadir un recordatorio personalizado.

  -> Volver a la página principal: este botón hace que el usuario vuelva al origen y pueda seguir haciendo uso de la aplicacion a su gusto.

Por último, al final del activity encontramos los recordatorios que el usuario ha programado.

## Pantalla mapa

En esta pantalla se muetran difrenetes ubicaciones en las que los usuarios pueden ir a el punto de reciclaje más conveniente. 

--> Presenta un botón para volver a la pantalla principal 

En primer lugar se quiso vincular con google maps para tener el mapa a tiempo real, finalmente hemos optado por una imagen representativa del mapa de la comunidad de Madrid, haciedno un boceto con los pines verdes indicando dónde pueden los usuarios dirigirse para reciclar.


## Pantalla estadísticas

En esta pantalla se le muestran al usuario las estadísticas de la aplicación, como la aplicación no esta siendo utilizada por usuarios reales no se tienen estadísticas reales, por lo tanto hacemos uso de estadísticas ficticias con las cuáles el usuario puede interactúar. 

A parte de esto la pantalla cuenta con un botón que devuelve al usuario a la pantalla de inicio para que pueda seguir utilizando la aplicacion libremente.
