-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-05-2024 a las 00:06:04
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `biblioteca`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `llibres`
--

CREATE TABLE `llibres` (
  `ID_Llibre` int(11) NOT NULL,
  `Títol` varchar(255) DEFAULT NULL,
  `Autor` varchar(255) DEFAULT NULL,
  `ISBN` varchar(20) DEFAULT NULL,
  `Editorial` varchar(255) DEFAULT NULL,
  `Any_Publicació` int(11) DEFAULT NULL,
  `Categoria` varchar(100) DEFAULT NULL,
  `Estat` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `llibres`
--

INSERT INTO `llibres` (`ID_Llibre`, `Títol`, `Autor`, `ISBN`, `Editorial`, `Any_Publicació`, `Categoria`, `Estat`) VALUES
(1, 'El Quixot 2', 'Miguel de Cervantes', '1234567890123', 'Editorial Planeta', 1605, 'Novel·la', 'disponible'),
(2, 'La Divina Comèdia', 'Dante Alighieri', '1234567890124', 'Editorial Anaya', 1320, 'Poesia', 'disponible'),
(3, '1984', 'George Orwell', '1234567890125', 'Secker & Warburg', 1949, 'Distopia', 'disponible'),
(4, 'Cien años de soledad', 'Gabriel García Márquez', '1234567890126', 'Editorial Sudamericana', 1967, 'Realisme màgic', 'disponible'),
(5, 'El Principito', 'Antoine de Saint-Exupéry', '1234567890127', 'Reynal & Hitchcock', 1943, 'Conte', 'disponible'),
(6, 'Don Juan Tenorio', 'José Zorrilla', '1234567890128', 'Editorial Castalia', 1844, 'Teatre', 'disponible'),
(7, 'El nombre de la rosa', 'Umberto Eco', '1234567890129', 'Editorial Bompiani', 1980, 'Novel·la històrica', 'disponible'),
(8, 'Crimen y castigo', 'Fiódor Dostoyevski', '1234567890130', 'The Russian Messenger', 1866, 'Novel·la', 'disponible'),
(9, 'En busca del tiempo perdido', 'Marcel Proust', '1234567890131', 'Grasset & Gallimard', 1913, 'Novel·la', 'disponible'),
(10, 'Los miserables', 'Victor Hugo', '1234567890132', 'A. Lacroix, Verboeckhoven & Cía.', 1862, 'Novel·la', 'disponible'),
(11, 'Matar a un ruiseñor', 'Harper Lee', '1234567890133', 'J.B. Lippincott & Co.', 1960, 'Novel·la', 'disponible'),
(12, 'El gran Gatsby', 'F. Scott Fitzgerald', '1234567890134', 'Charles Scribner\'s Sons', 1925, 'Novel·la', 'disponible'),
(13, 'Orgullo y prejuicio', 'Jane Austen', '1234567890135', 'T. Egerton', 1813, 'Novel·la', 'disponible'),
(14, 'Frankenstein', 'Mary Shelley', '1234567890136', 'Lackington, Hughes, Harding, Mavor & Jones', 1818, 'Novel·la gòtica', 'disponible'),
(15, 'Drácula', 'Bram Stoker', '1234567890137', 'Archibald Constable and Company', 1897, 'Novel·la gòtica', 'disponible'),
(16, 'Alicia en el país de las maravillas', 'Lewis Carroll', '1234567890138', 'Macmillan', 1865, 'Conte', 'disponible'),
(17, 'Las aventuras de Sherlock Holmes', 'Arthur Conan Doyle', '1234567890139', 'George Newnes', 1892, 'Misteri', 'disponible'),
(18, 'La Odisea', 'Homero', '1234567890140', 'Grecia Antiga', -800, 'Epopeia', 'disponible'),
(19, 'La Ilíada', 'Homero', '1234567890141', 'Grecia Antiga', -750, 'Epopeia', 'disponible'),
(20, 'Hamlet', 'William Shakespeare', '1234567890142', 'N. Ling', 1603, 'Teatre', 'disponible'),
(21, 'La metamorfosis', 'Franz Kafka', '1234567890143', 'Kurt Wolff Verlag', 1915, 'Novel·la', 'disponible'),
(22, 'El retrato de Dorian Gray', 'Oscar Wilde', '1234567890144', 'Lippincott\'s Monthly Magazine', 1890, 'Novel·la', 'disponible'),
(23, 'Las uvas de la ira', 'John Steinbeck', '1234567890145', 'The Viking Press', 1939, 'Novel·la', 'disponible'),
(24, 'Lolita', 'Vladimir Nabokov', '1234567890146', 'Olympia Press', 1955, 'Novel·la', 'disponible'),
(25, 'Ulises', 'James Joyce', '1234567890147', 'Shakespeare and Company', 1922, 'Novel·la', 'disponible'),
(26, 'La montaña mágica', 'Thomas Mann', '1234567890148', 'S. Fischer Verlag', 1924, 'Novel·la', 'disponible'),
(27, 'Rayuela', 'Julio Cortázar', '1234567890149', 'Editorial Sudamericana', 1963, 'Novel·la', 'disponible'),
(28, 'Don Quijote de la Mancha', 'Miguel de Cervantes', '1234567890150', 'Francisco de Robles', 1605, 'Novel·la', 'disponible'),
(29, 'El amor en los tiempos del cólera', 'Gabriel García Márquez', '1234567890151', 'Editorial Oveja Negra', 1985, 'Novel·la', 'disponible'),
(30, 'La sombra del viento', 'Carlos Ruiz Zafón', '1234567890152', 'Editorial Planeta', 2001, 'Novel·la', 'disponible'),
(31, 'Cien años de soledad', 'Gabriel García Márquez', '1234567890153', 'Editorial Sudamericana', 1967, 'Realismo mágico', 'disponible'),
(32, 'La casa de los espíritus', 'Isabel Allende', '1234567890154', 'Editorial Sudamericana', 1982, 'Novel·la', 'disponible'),
(33, 'Pedro Páramo', 'Juan Rulfo', '1234567890155', 'Fondo de Cultura Económica', 1955, 'Novel·la', 'disponible'),
(34, 'El coronel no tiene quien le escriba', 'Gabriel García Márquez', '1234567890156', 'Editorial Harper & Row', 1961, 'Novel·la', 'disponible'),
(35, 'La tregua', 'Mario Benedetti', '1234567890157', 'Editorial Alfa', 1960, 'Novel·la', 'disponible'),
(36, 'El túnel', 'Ernesto Sabato', '1234567890158', 'Editorial Sur', 1948, 'Novel·la', 'disponible'),
(37, 'Fahrenheit 451', 'Ray Bradbury', '1234567890159', 'Ballantine Books', 1953, 'Ciència ficció', 'disponible'),
(38, 'La naranja mecánica', 'Anthony Burgess', '1234567890160', 'William Heinemann', 1962, 'Distopia', 'disponible'),
(39, 'Rebelión en la granja', 'George Orwell', '1234567890161', 'Secker and Warburg', 1945, 'Distopia', 'disponible'),
(40, 'Moby-Dick', 'Herman Melville', '1234567890162', 'Harper & Brothers', 1851, 'Aventura', 'disponible'),
(52, 'Prova', 'Prova', '123214414', 'Nose', 2005, 'Prova', 'disponible');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `préstecs`
--

CREATE TABLE `préstecs` (
  `ID_Préstec` int(11) NOT NULL,
  `ID_Llibre` int(11) DEFAULT NULL,
  `ID_Usuari` int(11) DEFAULT NULL,
  `Data_Préstec` date DEFAULT NULL,
  `Data_Retorn_Prevista` date DEFAULT NULL,
  `Data_Retorn_Real` date DEFAULT NULL,
  `Estat` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `préstecs`
--

INSERT INTO `préstecs` (`ID_Préstec`, `ID_Llibre`, `ID_Usuari`, `Data_Préstec`, `Data_Retorn_Prevista`, `Data_Retorn_Real`, `Estat`) VALUES
(11, 1, 5, '2023-05-01', '2023-05-15', '2024-05-20', 'completat'),
(12, 2, 6, '2023-05-02', '2023-05-24', '2024-05-20', 'completat'),
(13, 3, 7, '2023-05-03', '2023-05-17', '2024-05-20', 'completat'),
(14, 4, 8, '2023-05-04', '2023-05-18', '2024-05-20', 'completat'),
(15, 5, 9, '2023-05-05', '2023-05-19', '2024-05-20', 'completat'),
(16, 6, 10, '2023-05-06', '2023-05-20', '2024-05-20', 'completat'),
(17, 7, 11, '2023-05-07', '2023-05-21', '2024-05-20', 'completat'),
(18, 8, 12, '2023-05-08', '2023-05-22', '2024-05-20', 'completat'),
(19, 9, 13, '2023-05-09', '2023-05-23', '2024-05-20', 'completat'),
(20, 10, 14, '2023-05-10', '2023-05-24', '2024-05-20', 'completat'),
(21, 1, 5, '2024-05-20', '2024-06-04', '2024-05-20', 'completat'),
(22, 3, 16, '2024-05-20', '2024-06-04', '2024-05-20', 'completat'),
(23, 1, 5, '2024-05-20', '2024-06-04', '2024-05-20', 'completat'),
(24, 2, 6, '2024-05-20', '2024-06-04', '2024-05-20', 'completat'),
(25, 1, 15, '2024-05-20', '2024-06-04', '2024-05-20', 'completat'),
(26, 1, 6, '2024-05-20', '2024-06-04', '2024-05-20', 'completat');

-- --------------------------------------------------------

-- INSERT INTO `préstecs` (`ID_Préstec`, `ID_Llibre`, `ID_Usuari`, `Data_Préstec`, `Data_Retorn_Prevista`, `Data_Retorn_Real`, `Estat`) VALUES (26, 1, 6, '2024-05-15', '2024-05-23', NULL, 'actiu');
-- Estructura de tabla para la tabla `usuaris`
--

CREATE TABLE `usuaris` (
  `ID_Usuari` int(11) NOT NULL,
  `Nom` varchar(255) DEFAULT NULL,
  `Cognoms` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Contrasenya` varchar(150) NOT NULL,
  `Telèfon` varchar(20) DEFAULT NULL,
  `Rol` varchar(50) DEFAULT NULL,
  `Data_Registre` date DEFAULT curdate(),
  `Sancio_Fins` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuaris`
--

INSERT INTO `usuaris` (`ID_Usuari`, `Nom`, `Cognoms`, `Email`, `Contrasenya`, `Telèfon`, `Rol`, `Data_Registre`, `Sancio_Fins`) VALUES
(1, 'Administrador', 'a', 'adm@cancasacuberta.cat', 'L@p1neda', '784561234', 'bibliotecari', '2024-05-19', NULL),
(5, 'Joan', 'Garcia', 'joan.garcia@example.com', '1234', '123456789', 'lector', '2024-05-20', '2024-06-04'),
(6, 'Maria', 'Martínez', 'maria.martinez@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(7, 'Anna', 'López', 'anna.lopez@example.com', '1234', '123456789', 'lector', '2024-05-20', '2024-06-04'),
(8, 'Pere', 'Sánchez', 'pere.sanchez@example.com', '1234', '123456789', 'lector', '2024-05-20', '2024-06-04'),
(9, 'Laura', 'González', 'laura.gonzalez@example.com', '1234', '123456789', 'lector', '2024-05-20', '2024-06-10'),
(10, 'Carles', 'Fernández', 'carles.fernandez@example.com', '1234', '123456789', 'lector', '2024-05-20', '2024-06-17'),
(11, 'Sergi', 'Pérez', 'sergi.perez@example.com', '1234', '123456789', 'lector', '2024-05-20', '2024-06-04'),
(12, 'Marta', 'Rodríguez', 'marta.rodriguez@example.com', '1234', '123456789', 'lector', '2024-05-20', '2024-06-11'),
(13, 'Jordi', 'Gómez', 'jordi.gomez@example.com', '1234', '123456789', 'lector', '2024-05-20', '2024-06-09'),
(14, 'Clara', 'Ruiz', 'clara.ruiz@example.com', '1234', '123456789', 'lector', '2024-05-20', '2024-06-11'),
(15, 'Ramon', 'Hernández', 'ramon.hernandez@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(16, 'Núria', 'Díaz', 'nuria.diaz@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(17, 'Albert', 'Moreno', 'albert.moreno@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(18, 'Eva', 'Muñoz', 'eva.munoz@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(19, 'Marc', 'Álvarez', 'marc.alvarez@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(20, 'Isabel', 'Romero', 'isabel.romero@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(21, 'Raül', 'Serrano', 'raul.serrano@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(22, 'Rosa', 'Blanco', 'rosa.blanco@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(23, 'Francesc', 'Méndez', 'francesc.mendez@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(24, 'Teresa', 'Ortega', 'teresa.ortega@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(25, 'Antoni', 'Ramírez', 'antoni.ramirez@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(26, 'Susanna', 'Castro', 'susanna.castro@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(27, 'Andreu', 'Vargas', 'andreu.vargas@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(28, 'Elena', 'Rubio', 'elena.rubio@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(29, 'Xavier', 'Molina', 'xavier.molina@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(30, 'Paula', 'Suárez', 'paula.suarez@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(31, 'David', 'Ortiz', 'david.ortiz@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(32, 'Aina', 'Domínguez', 'aina.dominguez@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(33, 'Ricard', 'Garrido', 'ricard.garrido@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(34, 'Dolors', 'Gil', 'dolors.gil@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(35, 'Arnau', 'Santos', 'arnau.santos@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(36, 'Mireia', 'Cruz', 'mireia.cruz@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(37, 'Júlia', 'Flores', 'julia.flores@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(38, 'Oriol', 'Jiménez', 'oriol.jimenez@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(39, 'Pilar', 'Reyes', 'pilar.reyes@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(40, 'Gerard', 'Torres', 'gerard.torres@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(41, 'Meritxell', 'Carrasco', 'meritxell.carrasco@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(42, 'Enric', 'Aguilar', 'enric.aguilar@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(43, 'Adela', 'Navarro', 'adela.navarro@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(44, 'Josep', 'Pascual', 'josep.pascual@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(45, 'Noemi', 'Herrera', 'noemi.herrera@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(46, 'Martí', 'Peña', 'marti.pena@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(47, 'Blanca', 'Arias', 'blanca.arias@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(48, 'Lluís', 'Parra', 'lluis.parra@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(49, 'Sandra', 'Mendoza', 'sandra.mendoza@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(50, 'Ferran', 'Vega', 'ferran.vega@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(51, 'Marina', 'Delgado', 'marina.delgado@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(52, 'Quim', 'Fuentes', 'quim.fuentes@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(53, 'Eva', 'Campos', 'eva.campos@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL),
(54, 'Toni', 'Cabrera', 'toni.cabrera@example.com', '1234', '123456789', 'lector', '2024-05-20', NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `llibres`
--
ALTER TABLE `llibres`
  ADD PRIMARY KEY (`ID_Llibre`);

--
-- Indices de la tabla `préstecs`
--
ALTER TABLE `préstecs`
  ADD PRIMARY KEY (`ID_Préstec`),
  ADD KEY `ID_Llibre` (`ID_Llibre`),
  ADD KEY `ID_Usuari` (`ID_Usuari`);

--
-- Indices de la tabla `usuaris`
--
ALTER TABLE `usuaris`
  ADD PRIMARY KEY (`ID_Usuari`),
  ADD KEY `Email` (`Email`),
  ADD KEY `Contrasenya_Index` (`Contrasenya`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `llibres`
--
ALTER TABLE `llibres`
  MODIFY `ID_Llibre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT de la tabla `préstecs`
--
ALTER TABLE `préstecs`
  MODIFY `ID_Préstec` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `usuaris`
--
ALTER TABLE `usuaris`
  MODIFY `ID_Usuari` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `préstecs`
--
ALTER TABLE `préstecs`
  ADD CONSTRAINT `préstecs_ibfk_1` FOREIGN KEY (`ID_Llibre`) REFERENCES `llibres` (`ID_Llibre`),
  ADD CONSTRAINT `préstecs_ibfk_2` FOREIGN KEY (`ID_Usuari`) REFERENCES `usuaris` (`ID_Usuari`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;