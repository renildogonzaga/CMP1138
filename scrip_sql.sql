-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.5.24-log - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Copiando estrutura para tabela doutorado_bd.bancos_dados
CREATE TABLE IF NOT EXISTS `bancos_dados` (
  `bdd_id` int(11) NOT NULL AUTO_INCREMENT,
  `tip_id` int(11) DEFAULT NULL,
  `bdd_servidor` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `bdd_nomebd` varchar(50) COLLATE latin1_general_ci DEFAULT NULL,
  `bdd_usuario` varchar(50) COLLATE latin1_general_ci DEFAULT NULL,
  `bdd_senha` varchar(50) COLLATE latin1_general_ci DEFAULT NULL,
  `bdd_observacao` varchar(50) COLLATE latin1_general_ci DEFAULT NULL,
  `bdd_customizar_campos` varchar(50) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`bdd_id`),
  KEY `bdd_tip_01` (`tip_id`),
  CONSTRAINT `bdd_tip_01` FOREIGN KEY (`tip_id`) REFERENCES `tipos_bd` (`tip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- Copiando dados para a tabela doutorado_bd.bancos_dados: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `bancos_dados` DISABLE KEYS */;
INSERT INTO `bancos_dados` (`bdd_id`, `tip_id`, `bdd_servidor`, `bdd_nomebd`, `bdd_usuario`, `bdd_senha`, `bdd_observacao`, `bdd_customizar_campos`) VALUES
	(2, 1, 'localhost', 'classicmodels', 'vicente', 'vicente', 'null', 'null'),
	(3, 1, 'localhost', 'sakila', 'vicente', 'vicente', 'null', 'null'),
	(7, 1, 'localhost', 'ssbm', 'vicente', 'vicente', 'null', 'null'),
	(18, 1, 'localhost', 'learning_mysql', 'vicente', 'vicente', 'null', 'null');
/*!40000 ALTER TABLE `bancos_dados` ENABLE KEYS */;


-- Copiando estrutura para tabela doutorado_bd.tipos_bd
CREATE TABLE IF NOT EXISTS `tipos_bd` (
  `tip_id` int(11) NOT NULL AUTO_INCREMENT,
  `tip_sigla` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `tip_descricao` varchar(40) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`tip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- Copiando dados para a tabela doutorado_bd.tipos_bd: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `tipos_bd` DISABLE KEYS */;
INSERT INTO `tipos_bd` (`tip_id`, `tip_sigla`, `tip_descricao`) VALUES
	(1, 'MySQL', 'MySQL'),
	(2, 'Oracle', 'Oracle'),
	(3, 'SQL Server', 'SQL Server'),
	(5, 'Postgres', NULL);
/*!40000 ALTER TABLE `tipos_bd` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
