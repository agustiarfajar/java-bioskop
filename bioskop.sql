/*
Navicat MySQL Data Transfer

Source Server         : myProject
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : bioskop

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2022-02-16 16:49:04
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `daerah`
-- ----------------------------
DROP TABLE IF EXISTS `daerah`;
CREATE TABLE `daerah` (
  `id_daerah` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_daerah`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of daerah
-- ----------------------------

-- ----------------------------
-- Table structure for `film`
-- ----------------------------
DROP TABLE IF EXISTS `film`;
CREATE TABLE `film` (
  `id_film` int(11) NOT NULL AUTO_INCREMENT,
  `judul` varchar(50) DEFAULT NULL,
  `kategori` varchar(50) DEFAULT NULL,
  `jam_tayang_1` text DEFAULT NULL,
  `jam_tayang_2` text DEFAULT NULL,
  `jam_tayang_3` text DEFAULT NULL,
  `harga` double DEFAULT NULL,
  PRIMARY KEY (`id_film`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of film
-- ----------------------------

-- ----------------------------
-- Table structure for `kursi`
-- ----------------------------
DROP TABLE IF EXISTS `kursi`;
CREATE TABLE `kursi` (
  `id_kursi` int(11) NOT NULL AUTO_INCREMENT,
  `no_kursi` varchar(3) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `id_studio` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_kursi`),
  KEY `id_studio` (`id_studio`),
  CONSTRAINT `kursi_ibfk_1` FOREIGN KEY (`id_studio`) REFERENCES `studio` (`id_studio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of kursi
-- ----------------------------

-- ----------------------------
-- Table structure for `pemesanan`
-- ----------------------------
DROP TABLE IF EXISTS `pemesanan`;
CREATE TABLE `pemesanan` (
  `id_pemesanan` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `id_film` int(11) DEFAULT NULL,
  `id_kursi` int(11) DEFAULT NULL,
  `id_studio` int(11) DEFAULT NULL,
  `jam_tayang` text DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`id_pemesanan`),
  KEY `id_user` (`id_user`),
  KEY `id_film` (`id_film`),
  KEY `id_studio` (`id_studio`),
  KEY `id_kursi` (`id_kursi`),
  CONSTRAINT `pemesanan_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `pemesanan_ibfk_2` FOREIGN KEY (`id_film`) REFERENCES `film` (`id_film`),
  CONSTRAINT `pemesanan_ibfk_3` FOREIGN KEY (`id_studio`) REFERENCES `studio` (`id_studio`),
  CONSTRAINT `pemesanan_ibfk_4` FOREIGN KEY (`id_kursi`) REFERENCES `kursi` (`id_kursi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of pemesanan
-- ----------------------------

-- ----------------------------
-- Table structure for `studio`
-- ----------------------------
DROP TABLE IF EXISTS `studio`;
CREATE TABLE `studio` (
  `id_studio` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) DEFAULT NULL,
  `id_daerah` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_studio`),
  KEY `id_daerah` (`id_daerah`),
  CONSTRAINT `studio_ibfk_1` FOREIGN KEY (`id_daerah`) REFERENCES `daerah` (`id_daerah`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of studio
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `hak_akses` int(11) DEFAULT NULL,
  `no_telp` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
