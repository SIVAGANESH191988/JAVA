create database PaymentsApp;

show databases;

use PaymentsApp;

create table User(
UserID      int not null,
FirstName   varchar(10),
LastName    varchar(10),
PhoneNumber   long,
DOB         date,
CommunicationAddress     varchar(20),
Wallet      int not null,
Password    varchar(10),
primary key (UserID)
);

desc User;

create table Wallet(
UserID          int not null,
CurrentBalance  double,
primary key     (UserID),
foreign key     (UserID) 
references       User(UserID)
);

desc Wallet;

create table BankAccount(
UserID              int not null,
BankAccountNumber   int not null,
BankAccountName     varchar(20),
IFSCCode          varchar(10),
BankAccountType     varchar(10),
BankAccountPin      varchar(10),
primary key        (BankAccountNumber),
foreign key        (UserID)
references          User (UserID));

desc BankAccount;

create table Transaction(
UserID           int not null,
TransactionID          int not null,
TransactionAmount      double,
TransactionSource      varchar(20),
TransactionDestination  varchar(20),
primary key      (TransactionID),
foreign key      (UserID)
references        User (UserID)
);

desc Transaction;
