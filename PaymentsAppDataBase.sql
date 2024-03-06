create database PaymentsApp;

show databases;

use PaymentsApp;

create table User(
UserID      int not null,
FirstName   varchar(10),
LastName    varchar(10),
PhoneNum    long,
DOB         date,
Address     varchar(50),
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
IFSCNumber          varchar(10),
BankAccountType     varchar(10),
BankAccountPin      varchar(10),
primary key        (BankAccountNumber),
foreign key        (UserID)
references          User (UserID));

desc BankAccount;

create table Transaction(
UserID           int not null,
TrnxnID          int not null,
TrnxnAmount      double,
TrnxnSource      varchar(20),
TrnxDestination  varchar(20),
primary key      (TrnxnID),
foreign key      (UserID)
references        User (UserID)
);

desc Transaction;
