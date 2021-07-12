CREATE TABLE `accounts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` char(50) DEFAULT NULL,
  `password` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `accounts_categories` (
  `accountId` bigint NOT NULL,
  `categoryId` bigint NOT NULL,
  PRIMARY KEY (`accountId`,`categoryId`),
  KEY `categoryId` (`categoryId`),
  CONSTRAINT `accounts_categories_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `accounts` (`id`) ON DELETE CASCADE,
  CONSTRAINT `accounts_categories_ibfk_2` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `balances` (
  `accountId` bigint NOT NULL,
  `total` float DEFAULT NULL,
  PRIMARY KEY (`accountId`),
  CONSTRAINT `balances_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `accounts` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` char(200) DEFAULT NULL,
  `image` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `budgets` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `accountId` bigint NOT NULL,
  `categoryId` bigint NOT NULL,
  `name` char(100) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `status` float DEFAULT NULL,
  `date` date DEFAULT NULL,
  `actualAmount` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `accountId` (`accountId`),
  KEY `categoryId` (`categoryId`),
  CONSTRAINT `budgets_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `accounts` (`id`) ON DELETE CASCADE,
  CONSTRAINT `budgets_ibfk_2` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `profiles` (
  `accountId` bigint NOT NULL,
  `fullname` char(200) DEFAULT NULL,
  `email` char(200) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `avatar` text,
  `job` varchar(200) DEFAULT NULL,
  `isMale` bit(1) DEFAULT NULL,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`accountId`),
  CONSTRAINT `profiles_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `accounts` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `transactions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `accountId` bigint NOT NULL,
  `type` char(100) NOT NULL,
  `amount` float NOT NULL,
  `categoryId` bigint NOT NULL,
  `date` date DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `accountId` (`accountId`),
  KEY `transactions_ibfk_2` (`categoryId`),
  CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `accounts` (`id`) ON DELETE CASCADE,
  CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO categories (name, image)
VALUES ('travel', 'https://i.ibb.co/94Y6gqR/car-48px.png'),
('food', 'https://i.ibb.co/0rqthPZ/hamburger-48px.png'),
('hospital', 'https://i.ibb.co/9gssnM6/hospital-3-48px.png'),
('bank', 'https://i.ibb.co/ByGWWQF/android-48px.png');