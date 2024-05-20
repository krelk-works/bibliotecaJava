# Biblioteca Can Casacuberta

Aquest projecte és una aplicació de gestió per a la Biblioteca Can Casacuberta. L'aplicació està desenvolupada en Java utilitzant Swing per a la interfície gràfica i MySQL per a la base de dades.
Aclaracions: Es tracta d'un projecte Educatiu i no te cap motiu d'interés diferit que la propia práctica i coneixement en materia de programació.

## Funcionalitats Principals

### Interfície de Bibliotecari
- **Gestió de Llibres**:
  - Afegir llibres a la base de dades.
  - Modificar les dades dels llibres existents.
  - Eliminar llibres de la base de dades.
- **Control de Préstecs**:
  - Facilitar el procés de préstec i devolució de llibres.
  - Gestionar sancions per retards en la devolució de llibres.
- **Gestió d'Usuaris**:
  - Registrar nous usuaris.
  - Mantenir els perfils dels usuaris, incloent l'historial de préstecs.

### Interfície de Lector
- **Buscar Llibres Disponibles**:
  - Consultar els llibres disponibles a la biblioteca.
- **Consultar Històric de Préstecs**:
  - Revisar el propi historial de préstecs.
  - Veure l'estat dels préstecs, incloent si han estat retornats dins del termini o amb retard.

## Requisits del Sistema
- Java 8 o superior.
- MySQL 5.7 o superior.

## Configuració del Projecte
1. Clonar el repositori del projecte.
2. Crear la base de dades i les taules utilitzant els scripts SQL proporcionats. L'arxiu SQL esta a la carpeta FILES
3. Afegir al teu projecte, un cop obert, la llibreria de referencia del mysql-connector.jar (carpeta FILES).
4. Configurar la connexió a la base de dades MySQL en el fitxer `Connexio.java`.
5. Executar l'aplicació des de la classe `App.java`.

## Estructura del Projecte
- `Connexio`: Classe per a gestionar la connexió a la base de dades.
- `FinestraLogin`: Interfície de login per a l'accés dels usuaris.
- `FinestraBibliotecari`: Interfície per a les funcions de gestió dels bibliotecaris.
- `FinestraLector`: Interfície per a les funcions dels lectors.
- `LlibresGUI`: Gestió visual dels llibres.
- `PrestecsGUI`: Gestió visual dels préstecs.
- `UsuarisGUI`: Gestió visual dels usuaris.
- `ConsultaLlibres`: Consulta de llibres disponibles.
- `ConsultaPrestecs`: Consulta de l'historial de préstecs dels lectors.
- `NouPrestec`: Creació de nous préstecs.
- `Devolucio`: Gestió de la devolució de llibres i sancions.
