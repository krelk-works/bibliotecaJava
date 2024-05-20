-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-05-2024 a las 19:24:56
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
(1, 'El Quixot 2', 'Miguel de Cervantes', '1234567890123', 'Editorial Planeta', 1605, 'Novel·la', 'Disponible'),
(2, 'La Divina Comèdia', 'Dante Alighieri', '1234567890124', 'Editorial Anaya', 1320, 'Poesia', 'Disponible'),
(3, '1984', 'George Orwell', '1234567890125', 'Secker & Warburg', 1949, 'Distopia', 'Disponible'),
(4, 'Cien años de soledad', 'Gabriel García Márquez', '1234567890126', 'Editorial Sudamericana', 1967, 'Realisme màgic', 'Disponible'),
(5, 'El Principito', 'Antoine de Saint-Exupéry', '1234567890127', 'Reynal & Hitchcock', 1943, 'Conte', 'Disponible'),
(6, 'Don Juan Tenorio', 'José Zorrilla', '1234567890128', 'Editorial Castalia', 1844, 'Teatre', 'Disponible'),
(7, 'El nombre de la rosa', 'Umberto Eco', '1234567890129', 'Editorial Bompiani', 1980, 'Novel·la històrica', 'Disponible'),
(8, 'Crimen y castigo', 'Fiódor Dostoyevski', '1234567890130', 'The Russian Messenger', 1866, 'Novel·la', 'Disponible'),
(9, 'En busca del tiempo perdido', 'Marcel Proust', '1234567890131', 'Grasset & Gallimard', 1913, 'Novel·la', 'Disponible'),
(10, 'Los miserables', 'Victor Hugo', '1234567890132', 'A. Lacroix, Verboeckhoven & Cía.', 1862, 'Novel·la', 'Disponible'),
(11, 'Matar a un ruiseñor', 'Harper Lee', '1234567890133', 'J.B. Lippincott & Co.', 1960, 'Novel·la', 'Disponible'),
(12, 'El gran Gatsby', 'F. Scott Fitzgerald', '1234567890134', 'Charles Scribner\'s Sons', 1925, 'Novel·la', 'Disponible'),
(13, 'Orgullo y prejuicio', 'Jane Austen', '1234567890135', 'T. Egerton', 1813, 'Novel·la', 'Disponible'),
(14, 'Frankenstein', 'Mary Shelley', '1234567890136', 'Lackington, Hughes, Harding, Mavor & Jones', 1818, 'Novel·la gòtica', 'Disponible'),
(15, 'Drácula', 'Bram Stoker', '1234567890137', 'Archibald Constable and Company', 1897, 'Novel·la gòtica', 'Disponible'),
(16, 'Alicia en el país de las maravillas', 'Lewis Carroll', '1234567890138', 'Macmillan', 1865, 'Conte', 'Disponible'),
(17, 'Las aventuras de Sherlock Holmes', 'Arthur Conan Doyle', '1234567890139', 'George Newnes', 1892, 'Misteri', 'Disponible'),
(18, 'La Odisea', 'Homero', '1234567890140', 'Grecia Antiga', -800, 'Epopeia', 'Disponible'),
(19, 'La Ilíada', 'Homero', '1234567890141', 'Grecia Antiga', -750, 'Epopeia', 'Disponible'),
(20, 'Hamlet', 'William Shakespeare', '1234567890142', 'N. Ling', 1603, 'Teatre', 'Disponible'),
(21, 'La metamorfosis', 'Franz Kafka', '1234567890143', 'Kurt Wolff Verlag', 1915, 'Novel·la', 'Disponible'),
(22, 'El retrato de Dorian Gray', 'Oscar Wilde', '1234567890144', 'Lippincott\'s Monthly Magazine', 1890, 'Novel·la', 'Disponible'),
(23, 'Las uvas de la ira', 'John Steinbeck', '1234567890145', 'The Viking Press', 1939, 'Novel·la', 'Disponible'),
(24, 'Lolita', 'Vladimir Nabokov', '1234567890146', 'Olympia Press', 1955, 'Novel·la', 'Disponible'),
(25, 'Ulises', 'James Joyce', '1234567890147', 'Shakespeare and Company', 1922, 'Novel·la', 'Disponible'),
(26, 'La montaña mágica', 'Thomas Mann', '1234567890148', 'S. Fischer Verlag', 1924, 'Novel·la', 'Disponible'),
(27, 'Rayuela', 'Julio Cortázar', '1234567890149', 'Editorial Sudamericana', 1963, 'Novel·la', 'Disponible'),
(28, 'Don Quijote de la Mancha', 'Miguel de Cervantes', '1234567890150', 'Francisco de Robles', 1605, 'Novel·la', 'Disponible'),
(29, 'El amor en los tiempos del cólera', 'Gabriel García Márquez', '1234567890151', 'Editorial Oveja Negra', 1985, 'Novel·la', 'Disponible'),
(30, 'La sombra del viento', 'Carlos Ruiz Zafón', '1234567890152', 'Editorial Planeta', 2001, 'Novel·la', 'Disponible'),
(31, 'Cien años de soledad', 'Gabriel García Márquez', '1234567890153', 'Editorial Sudamericana', 1967, 'Realismo mágico', 'Disponible'),
(32, 'La casa de los espíritus', 'Isabel Allende', '1234567890154', 'Editorial Sudamericana', 1982, 'Novel·la', 'Disponible'),
(33, 'Pedro Páramo', 'Juan Rulfo', '1234567890155', 'Fondo de Cultura Económica', 1955, 'Novel·la', 'Disponible'),
(34, 'El coronel no tiene quien le escriba', 'Gabriel García Márquez', '1234567890156', 'Editorial Harper & Row', 1961, 'Novel·la', 'Disponible'),
(35, 'La tregua', 'Mario Benedetti', '1234567890157', 'Editorial Alfa', 1960, 'Novel·la', 'Disponible'),
(36, 'El túnel', 'Ernesto Sabato', '1234567890158', 'Editorial Sur', 1948, 'Novel·la', 'Disponible'),
(37, 'Fahrenheit 451', 'Ray Bradbury', '1234567890159', 'Ballantine Books', 1953, 'Ciència ficció', 'Disponible'),
(38, 'La naranja mecánica', 'Anthony Burgess', '1234567890160', 'William Heinemann', 1962, 'Distopia', 'Disponible'),
(39, 'Rebelión en la granja', 'George Orwell', '1234567890161', 'Secker and Warburg', 1945, 'Distopia', 'Disponible'),
(40, 'Moby-Dick', 'Herman Melville', '1234567890162', 'Harper & Brothers', 1851, 'Aventura', 'Disponible');

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

-- --------------------------------------------------------

--
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
  `Data_Registre` date DEFAULT curdate()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuaris`
--

INSERT INTO `usuaris` (`ID_Usuari`, `Nom`, `Cognoms`, `Email`, `Contrasenya`, `Telèfon`, `Rol`, `Data_Registre`) VALUES
(1, 'Administrador', 'a', 'adm@cancasacuberta.cat', 'L@p1neda', '784561234', 'bibliotecari', '2024-05-19'),
(2, 'Leo', 'Craciun', 'leo', '1234', '685568861', 'bibliotecari', '2024-05-20');

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
  MODIFY `ID_Llibre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT de la tabla `préstecs`
--
ALTER TABLE `préstecs`
  MODIFY `ID_Préstec` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuaris`
--
ALTER TABLE `usuaris`
  MODIFY `ID_Usuari` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
