
CREATE DATABASE IF NOT EXISTS WebRTCObserver;

CREATE TABLE `WebRTCObserver`.`Users`
(
    `id`              INT  NOT NULL AUTO_INCREMENT COMMENT 'The identifier of the user for inside relations, never outside',
    `uuid`            BINARY(16) UNIQUE   DEFAULT NULL COMMENT 'The UUID of the user published outside ',
    `username`        VARCHAR(255) UNIQUE DEFAULT NULL COMMENT 'The username of the user',
    `password_digest` BINARY(64)          DEFAULT NULL COMMENT 'The hash of the password using the salt',
    `password_salt`   BINARY(32)          DEFAULT NULL COMMENT 'The salt for the password',
    `role`            ENUM ('admin','client') NOT NULL COMMENT 'The role of the user determines of which endpoint it can access to',
    PRIMARY KEY (`id`),
    KEY `users_username_key` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='Users';

CREATE TABLE `WebRTCObserver`.`Observers`
(
    `id`          INT NOT NULL AUTO_INCREMENT COMMENT 'The identifier of the observer for inside relations, never outside',
    `uuid`        BINARY(16) UNIQUE DEFAULT NULL COMMENT 'The UUID of the observer published outside ',
    `name`        VARCHAR(255)      DEFAULT NULL COMMENT 'The name of the obersver',
    `description` VARCHAR(255)      DEFAULT NULL COMMENT 'The description for the observer',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='Observers';

USE `WebRTCObserver`;
 
INSERT INTO `Users`
(`id`,
 `uuid`,
 `username`,
 `password_digest`,
 `password_salt`,
 `role`)
VALUES (1,
        UNHEX(REPLACE('58a6314b-188c-4659-a046-553a7f8c96de', '-', '')),
        'balazs',
        UNHEX('e77183b020e803e858c39b95652c81084f19ed11e2e2d18433bcb2c8a8a46768'),
        UNHEX('e12'),
        'admin');

INSERT INTO `Observers`
(`id`,
 `uuid`,
 `name`,
 `description`)
VALUES (1,
        UNHEX(REPLACE('86ed98c6-b001-48bb-b31e-da638b979c72', '-', '')),
        'demo',
        'demo description');


CREATE TABLE `WebRTCObserver`.`ActiveStreams`
(
    `observerUUID`        BINARY(16) NOT NULL COMMENT 'The UUID of the observer the SSRC belongs to',
    `SSRC`                BIGINT NOT NULL COMMENT 'The SSRC identifier',
    `callUUID`            BINARY(16) NOT NULL COMMENT 'The UUID of the call the stream belongs to',
    PRIMARY KEY (`observerUUID`,`SSRC`),
    INDEX `ActiveStreams_call_index` (`callUUID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='A table to track the active streams';
 

CREATE TABLE `WebRTCObserver`.`PeerConnections`
(
    `peerConnectionUUID`  BINARY(16) NOT NULL COMMENT 'The UUID of the peer connection',
    `created`             TIMESTAMP,
    `updated`             TIMESTAMP,
    `state`               ENUM ('joined','detached','rejoined'),
    `browserID`           VARCHAR (64),
    `timeZone`            VARCHAR (64),
    `callUUID`            BINARY(16) NOT NULL COMMENT 'The UUID of the call the peer connection belongs to',
    `observerUUID`        BINARY(16) NOT NULL COMMENT 'The UUID of the observer reported the peer connection',
    PRIMARY KEY (`peerConnectionUUID`),
    INDEX `PeerConnections_updated_index` (`updated`),
    INDEX `PeerConnections_state_index` (`state`),
    INDEX `PeerConnections_callUUID_index` (`callUUID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='A table to store peer connection reports generated by the service';
  

CREATE TABLE `WebRTCObserver`.`SentReports`
(
    `signature`             VARBINARY(255) COMMENT 'The signature of the report, which is sent',
    `peerConnectionUUID`    BINARY(16) COMMENT 'The UUID of the peerConnection sent the report',
    `reported`              TIMESTAMP      DEFAULT NULL COMMENT 'The timestamp of the report has been sent',
    PRIMARY KEY (`signature`),
    INDEX `SentReports_peerConnectionUUID_index` (`peerConnectionUUID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='SentReports';
  
  