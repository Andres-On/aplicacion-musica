**Proyecto de Aplicación Móvil MusicFinder**

Alumno : Andres Ongaro Miranda

1. Descripción del proyecto:
El proyecto consiste en el desarrollo de una aplicación móvil para Android llamada MusicFinder, diseñada para permitir a los usuarios buscar artistas o bandas musicales y acceder a información detallada sobre ellos.
La app mostrará datos como biografía, discografía, letras de canciones populares y enlaces a sitios oficiales o redes sociales, utilizando una API pública de música (por ejemplo, TheAudioDB, Last.fm o Musixmatch API) (The AudioDB, s.f.).
El objetivo principal es ofrecer una herramienta práctica, visualmente atractiva y fácil de usar para los amantes de la música que deseen conocer más sobre sus artistas favoritos.

2. Exposición del problema:
En la actualidad, los fanáticos de la música deben utilizar diversas plataformas o motores de búsqueda para obtener información completa sobre un artista: biografía en una página, letras en otra y redes sociales en otra distinta.
Esto implica pérdida de tiempo y una experiencia fragmentada.
La aplicación MusicFinder busca unificar esta información en un solo lugar, simplificando el acceso a los datos más relevantes de cada artista.
Además, puede servir como base para futuras integraciones, como recomendaciones personalizadas o conexión con servicios de streaming.


3. Plataforma:
La aplicación se desarrollará para dispositivos Android, utilizando Android Studio como entorno de desarrollo y Kotlin o Java como lenguaje principal.
La app consumirá datos desde una API RESTful externa mediante solicitudes HTTP (GET) y parseo de JSON.
Se implementará un diseño adaptable a distintas resoluciones de pantalla mediante XML layouts y el uso de componentes de Material Design.
En fases posteriores, se podría considerar la publicación de la app en Google Play Store. (Spotify for Developers, s.f.).

4. Interfaz de usuario e interfaz de administrador:
Interfaz de usuario (Frontend):
- Pantalla inicial con barra de búsqueda para ingresar el nombre del artista o banda.
- Resultados con nombre, imagen y breve descripción.
- Pantalla de detalles con biografía, lista de canciones y enlaces a redes o sitios oficiales.
- Opción de marcar artistas como “favoritos” (guardados localmente con    SharedPreferences o Room Database).
- Diseño limpio, intuitivo y con colores oscuros o neutros, acorde con una app musical.

Interfaz de administrador (Backend o gestión interna):
En esta primera versión, no se incluirá una interfaz administrativa compleja, ya que los datos provendrán directamente de una API.
Sin embargo, en versiones futuras se podría añadir un panel de control web para:
- Administrar artistas destacados.
- Agregar o editar manualmente información.
- Analizar las búsquedas más frecuentes de los usuarios.

5. Funcionalidad
La aplicación incluirá las siguientes funciones principales:	                                  
- Búsqueda de artistas: Permite al usuario ingresar el nombre del artista y obtener resultados desde la API.
- Visualización de biografía: Muestra información detallada del artista, su origen, género musical, trayectoria y discografía.
- Letras de canciones: Accede a letras de canciones populares a través de la API.
- Favoritos: Guarda artistas preferidos en el dispositivo para acceder sin conexión.
- Enlaces externos: Proporciona accesos directos a redes sociales o páginas oficiales.
*Opcionalmente, se podría incluir un sistema básico de notificaciones o una sección de “artistas recomendados”.

6. Diseño (wireframes o esquemas de página)
Pantalla 1 – Inicio / Búsqueda:
- Barra superior con logo y campo de búsqueda.
- Lista de resultados con imagen y nombre del artista.
Pantalla 2 – Detalles del artista:
- Imagen del artista o banda.
- Sección de biografía.
- Lista de canciones o álbumes.
- Botón de “Agregar a favoritos”.
- Enlaces a redes o sitio web.
Pantalla 3 – Favoritos:
- Lista de artistas guardados.
- Opción para eliminar o ver detalles.

7 Grafico

<img width="504" height="338" alt="image" src="https://github.com/user-attachments/assets/4a69c610-cdd2-4ce8-aa9f-03755619c196" />

