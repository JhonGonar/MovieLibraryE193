## Taller Práctico 4– MovieLibrary


Información del Estudiante

Nombre: [Nombre completo]
Código: [Código estudiantil]
Fecha: [Fecha de entrega]

Documento de reflexión breve
1. ViewModel

El ViewModel se encarga de manejar los datos que usa la interfaz sin depender directamente de ella. Mantiene la información disponible incluso cuando hay cambios como rotaciones de pantalla. Esto evita perder datos y también ayuda a que la lógica esté separada de la vista, haciendo el código más organizado.

2. LiveData

LiveData es un contenedor de datos que puede ser observado. Cuando el valor cambia, la interfaz se actualiza automáticamente. Además, tiene en cuenta el ciclo de vida de la aplicación, por lo que solo actualiza cuando la UI está activa, evitando errores y consumo innecesario de recursos.

3. Repository

El Repository centraliza el acceso a los datos. Es el encargado de decidir si la información viene de una base de datos local, una API o cualquier otra fuente. El ViewModel solo solicita los datos sin preocuparse por su origen, lo que hace que la arquitectura sea más limpia y fácil de mantener.

4. Navigation Component, NavController y Safe Args

El Navigation Component permite organizar la navegación entre pantallas dentro de la aplicación. Define cómo se conectan los fragmentos y las rutas que puede seguir el usuario.

El NavController es quien maneja el cambio de una pantalla a otra según las acciones definidas.

Safe Args permite enviar datos entre pantallas de forma segura, evitando errores relacionados con los tipos de datos.

5. Room (Entity, DAO, Database)

Room facilita el manejo de bases de datos en Android trabajando sobre SQLite, pero de una forma más organizada.

Entity: define la estructura de las tablas en la base de datos.
DAO: contiene las operaciones que se pueden realizar, como insertar, consultar, actualizar o eliminar datos.
Database: es la clase que conecta todo, configurando la base de datos y relacionando las entidades con los DAO.

Esto permite trabajar con datos locales de forma más clara y segura.


Video demostracion de funcionalidad: 

[https://youtu.be/Demostracion-APP-Taller-4](https://youtube.com/shorts/g8hnfLzDt3w?feature=shared) 
