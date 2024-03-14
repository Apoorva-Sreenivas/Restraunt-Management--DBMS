-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: restraunt-management
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `C_ID` int NOT NULL,
  `CNAME` varchar(20) NOT NULL,
  `CPHONE` decimal(10,0) NOT NULL,
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (101,'Alice Johnson',5551111),(102,'Bob Smith',5552222),(103,'Charlie Brown',5553333),(104,'David Lee',5554444),(105,'Emma Davis',5555555);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `inv_update_table`
--

DROP TABLE IF EXISTS `inv_update_table`;
/*!50001 DROP VIEW IF EXISTS `inv_update_table`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `inv_update_table` AS SELECT 
 1 AS `ingredient_id`,
 1 AS `item_id`,
 1 AS `quantity`,
 1 AS `order_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `INGREDIENT_ID` int NOT NULL,
  `ING_NAME` varchar(10) DEFAULT NULL,
  `QUANTITY` int DEFAULT NULL,
  `EXPIRY` date DEFAULT NULL,
  `SUPPLIER_ID` int DEFAULT NULL,
  PRIMARY KEY (`INGREDIENT_ID`),
  KEY `INV_FK` (`SUPPLIER_ID`),
  CONSTRAINT `INV_FK` FOREIGN KEY (`SUPPLIER_ID`) REFERENCES `supplier` (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (101,'A',99,'2024-06-01',1),(102,'B',149,'2024-08-15',2),(103,'C',197,'2024-07-20',3),(104,'D',75,'2024-09-10',4),(105,'E',120,'2024-10-05',5);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_ing`
--

DROP TABLE IF EXISTS `item_ing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_ing` (
  `ITEM_ID` int NOT NULL,
  `INGREDIENT_ID` int NOT NULL,
  PRIMARY KEY (`ITEM_ID`,`INGREDIENT_ID`),
  KEY `IING-FK2_idx` (`INGREDIENT_ID`),
  CONSTRAINT `IING-FK1` FOREIGN KEY (`ITEM_ID`) REFERENCES `items` (`ITEM_ID`),
  CONSTRAINT `IING-FK2` FOREIGN KEY (`INGREDIENT_ID`) REFERENCES `inventory` (`INGREDIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_ing`
--

LOCK TABLES `item_ing` WRITE;
/*!40000 ALTER TABLE `item_ing` DISABLE KEYS */;
INSERT INTO `item_ing` VALUES (1,101),(2,101),(2,102),(3,103),(4,104),(5,105);
/*!40000 ALTER TABLE `item_ing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `ITEM_ID` int NOT NULL,
  `ITEM_NAME` varchar(20) DEFAULT NULL,
  `PRICE` decimal(5,2) NOT NULL,
  PRIMARY KEY (`ITEM_ID`),
  UNIQUE KEY `ITEM_ID_UNIQUE` (`ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'A',10.99),(2,'B',8.49),(3,'C',15.75),(4,'D',6.99),(5,'E',12.50);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `STAFF_ID` int NOT NULL,
  `USERNAME` varchar(10) NOT NULL,
  `PASSWORD` varchar(10) NOT NULL,
  PRIMARY KEY (`STAFF_ID`),
  UNIQUE KEY `USERNAME_UNIQUE` (`USERNAME`),
  UNIQUE KEY `PASSWORD_UNIQUE` (`PASSWORD`),
  CONSTRAINT `FKEY1` FOREIGN KEY (`STAFF_ID`) REFERENCES `staff` (`STAFF_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (1,'manager1','password1');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ord_items`
--

DROP TABLE IF EXISTS `ord_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ord_items` (
  `ORDER_ID` int NOT NULL,
  `ITEM_ID` int NOT NULL,
  `QUANTITY` int NOT NULL,
  PRIMARY KEY (`ORDER_ID`,`ITEM_ID`),
  KEY `ORD_ITE FK2_idx` (`ITEM_ID`),
  CONSTRAINT `ORD_ITE FK1` FOREIGN KEY (`ORDER_ID`) REFERENCES `orders` (`ORDER_ID`) ON DELETE CASCADE,
  CONSTRAINT `ORD_ITE FK2` FOREIGN KEY (`ITEM_ID`) REFERENCES `items` (`ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ord_items`
--

LOCK TABLES `ord_items` WRITE;
/*!40000 ALTER TABLE `ord_items` DISABLE KEYS */;
INSERT INTO `ord_items` VALUES (1001,1,2),(1002,2,1),(1003,3,3),(1004,4,1),(1005,5,7),(1006,2,2),(1006,5,4);
/*!40000 ALTER TABLE `ord_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `ORDER_ID` int NOT NULL,
  `C_ID` int NOT NULL,
  `STAFF_ID` int NOT NULL,
  `DATE` date NOT NULL,
  `STATUS` char(1) NOT NULL,
  PRIMARY KEY (`ORDER_ID`),
  KEY `ORDERS-FK1_idx` (`STAFF_ID`),
  KEY `C_ID` (`C_ID`),
  CONSTRAINT `ORDERS-FK1` FOREIGN KEY (`STAFF_ID`) REFERENCES `staff` (`STAFF_ID`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`C_ID`) REFERENCES `customer` (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1001,101,1,'2024-03-05','D'),(1002,102,2,'2024-03-06','D'),(1003,103,3,'2024-03-07','D'),(1004,104,4,'2024-03-08','P'),(1005,105,5,'2024-03-09','P'),(1006,103,3,'2024-03-10','P');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `inv_update` AFTER UPDATE ON `orders` FOR EACH ROW BEGIN 
if(new.status='D') then 
    UPDATE inventory i
    SET i.quantity = i.quantity - (SELECT oi.quantity FROM ord_items oi where oi.order_id=order_id)
    WHERE i.ingredient_id = all (SELECT ii.ingredient_id FROM ord_items oi, items i, item_ing ii WHERE order_id=oi.order_id and oi.item_id=i.item_id and i.item_id=ii.ITEM_ID); 
    end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `STAFF_ID` int NOT NULL,
  `STNAME` varchar(20) NOT NULL,
  `POSITION` varchar(10) NOT NULL,
  `PHONE` decimal(10,0) NOT NULL,
  PRIMARY KEY (`STAFF_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'John Doe','Manager',5551234),(2,'Jane Smith','Server',5555678),(3,'Bob Johnson','Server',5559999),(4,'Alice Brown','Janitor',5551111),(5,'Emily Davis','Security',5552222);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `supplier_id` int NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `phone` decimal(10,0) NOT NULL,
  `ADDRESS` varchar(20) NOT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'ABC Supplier',1234567890,'123 Supplier St.'),(2,'XYZ Supplier',9876543210,'456 Provider Ave.'),(3,'LMN Supplier',5555555555,'789 Vendor Blvd.'),(4,'PQR Supplier',9998887777,'321 Supplier Lane.'),(5,'EFG Supplier',4443332222,'555 Vendor Rd.');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supply_orders`
--

DROP TABLE IF EXISTS `supply_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supply_orders` (
  `SORDER_ID` int NOT NULL,
  `INGREDIENT_ID` int NOT NULL,
  `QUANTITY` int NOT NULL,
  `SUPPLIER_ID` int NOT NULL,
  `STATUS` char(1) NOT NULL,
  `ORDER_DATE` date NOT NULL,
  PRIMARY KEY (`SORDER_ID`),
  UNIQUE KEY `SORDER_ID_UNIQUE` (`SORDER_ID`),
  KEY `SUPPLIER_ID` (`SUPPLIER_ID`),
  KEY `INGREDIENT_ID` (`INGREDIENT_ID`),
  CONSTRAINT `supply_orders_ibfk_1` FOREIGN KEY (`SUPPLIER_ID`) REFERENCES `supplier` (`supplier_id`),
  CONSTRAINT `supply_orders_ibfk_2` FOREIGN KEY (`INGREDIENT_ID`) REFERENCES `inventory` (`INGREDIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supply_orders`
--

LOCK TABLES `supply_orders` WRITE;
/*!40000 ALTER TABLE `supply_orders` DISABLE KEYS */;
INSERT INTO `supply_orders` VALUES (1,101,50,1,'P','2024-03-01'),(2,102,30,2,'P','2024-03-02'),(3,103,40,3,'P','2024-03-03'),(4,104,20,4,'P','2024-03-04'),(5,105,35,5,'P','2024-03-05');
/*!40000 ALTER TABLE `supply_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `inv_update_table`
--

/*!50001 DROP VIEW IF EXISTS `inv_update_table`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `inv_update_table` AS select `i`.`INGREDIENT_ID` AS `ingredient_id`,`i1`.`ITEM_ID` AS `item_id`,`o`.`QUANTITY` AS `quantity`,`o`.`ORDER_ID` AS `order_id` from ((`inventory` `i` join `item_ing` `i1`) join `ord_items` `o`) where ((`i`.`INGREDIENT_ID` = `i1`.`INGREDIENT_ID`) and (`i1`.`ITEM_ID` = `o`.`ITEM_ID`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-11 22:55:07
