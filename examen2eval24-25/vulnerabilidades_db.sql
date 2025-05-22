-- Crear la base de datos
DROP DATABASE IF EXISTS vulnerabilidades_db;

CREATE DATABASE IF NOT EXISTS vulnerabilidades_db;
USE vulnerabilidades_db;

CREATE TABLE Vulnerabilidades (
    id_vulnerabilidad INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    nivel_riesgo TINYINT NOT NULL  -- 1: Bajo, 2: Medio, 3: Alto, 4: Crítico
);

CREATE TABLE Soluciones (
    id_solucion INT PRIMARY KEY AUTO_INCREMENT,
    id_vulnerabilidad INT UNIQUE,  -- Relación 1 a 1
    descripcion TEXT NOT NULL,
    FOREIGN KEY (id_vulnerabilidad) REFERENCES Vulnerabilidades(id_vulnerabilidad)
);

CREATE TABLE Sistemas (
    id_sistema INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    version VARCHAR(50) NOT NULL
);

CREATE TABLE Ataques (
    id_ataque INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    tipo VARCHAR(100) NOT NULL
);

CREATE TABLE Afecta (  -- Relación N a N sin atributos intermedios
    id_vulnerabilidad INT,
    id_sistema INT,
    PRIMARY KEY (id_vulnerabilidad, id_sistema),
    FOREIGN KEY (id_vulnerabilidad) REFERENCES Vulnerabilidades(id_vulnerabilidad),
    FOREIGN KEY (id_sistema) REFERENCES Sistemas(id_sistema)
);

CREATE TABLE Permite (  -- Relación N a N con atributos intermedios
    id_vulnerabilidad INT,
    id_ataque INT,
    impacto TINYINT NOT NULL,  -- 1: Leve, 2: Moderado, 3: Grave, 4: Crítico
    fecha_detectada DATE NOT NULL,
    PRIMARY KEY (id_vulnerabilidad, id_ataque),
    FOREIGN KEY (id_vulnerabilidad) REFERENCES Vulnerabilidades(id_vulnerabilidad),
    FOREIGN KEY (id_ataque) REFERENCES Ataques(id_ataque)
);


-- Insertar vulnerabilidades
INSERT INTO Vulnerabilidades (nombre, descripcion, nivel_riesgo) VALUES
('SQL Injection', 'Inyección de código SQL en consultas', 3),
('Cross-Site Scripting', 'Ejecución de scripts maliciosos en clientes', 2),
('Buffer Overflow', 'Desbordamiento de memoria causando ejecución arbitraria', 4),
('Ransomware', 'Cifrado de archivos con demanda de rescate', 4),
('Phishing', 'Suplantación de identidad para robo de credenciales', 2);

-- Insertar soluciones
INSERT INTO Soluciones (id_vulnerabilidad, descripcion) VALUES
(1, 'Utilizar consultas preparadas y validación de entrada.'),
(2, 'Sanitización de entrada y uso de Content Security Policy.'),
(3, 'Control estricto de memoria y uso de ASLR.'),
(4, 'Implementar copias de seguridad y monitoreo en tiempo real.'),
(5, 'Capacitación a usuarios y autenticación multifactor.');

-- Insertar sistemas afectados
INSERT INTO Sistemas (nombre, version) VALUES
('Windows 10', '21H2'),
('Linux Ubuntu', '22.04'),
('MacOS', 'Ventura'),
('WordPress', '6.1'),
('Android', '13');

-- Insertar ataques
INSERT INTO Ataques (nombre, tipo) VALUES
('Ataque SQL', 'Explotación de SQL Injection'),
('XSS Persistente', 'Ejecución de scripts'),
('Malware Avanzado', 'Distribución de ransomware'),
('Phishing Masivo', 'Envío de correos fraudulentos'),
('DDoS Amplificado', 'Saturación de servidores');

-- Relación N a N sin atributos intermedios (Vulnerabilidades-Sistemas)
INSERT INTO Afecta (id_vulnerabilidad, id_sistema) VALUES
(1, 1), (1, 2), (1, 4),  -- SQL Injection afecta Windows, Linux y WordPress
(2, 4), (2, 5),          -- XSS afecta WordPress y Android
(3, 1), (3, 2),          -- Buffer Overflow afecta Windows y Linux
(4, 1), (4, 3),          -- Ransomware afecta Windows y MacOS
(5, 5);                  -- Phishing afecta Android

-- Relación N a N con atributos intermedios (Vulnerabilidades-Ataques)
INSERT INTO Permite (id_vulnerabilidad, id_ataque, impacto, fecha_detectada) VALUES
(1, 1, 3, '2024-02-15'),  -- SQL Injection -> Ataque SQL
(1, 5, 2, '2023-11-10'),  -- SQL Injection -> DDoS Amplificado (indirecto)
(2, 2, 2, '2023-12-05'),  -- XSS -> XSS Persistente
(3, 3, 4, '2024-01-20'),  -- Buffer Overflow -> Malware Avanzado
(4, 3, 4, '2023-10-30'),  -- Ransomware -> Malware Avanzado
(5, 4, 3, '2023-09-25');  -- Phishing -> Phishing Masivo