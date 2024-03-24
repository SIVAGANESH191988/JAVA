create database Payments_CLI_Application;

show databases;

use Payments_CLI_Application;

create table User_Info(
UserID      int not null,
FirstName   varchar(10),
LastName    varchar(10),
PhoneNumber   varchar(15),
DOB         date,
CommunicationAddress     varchar(20),
Wallet      double default 0.00,
Password    varchar(10),
primary key (UserID)
);
ALTER TABLE User_Info MODIFY UserID INT NOT NULL AUTO_INCREMENT;
select* from user_info;
desc User_Info;

create table Wallet_Details(
UserID          int not null,
CurrentBalance  double,
primary key     (UserID),
foreign key     (UserID) 
references       User_Info(UserID)
);

desc Wallet_Details;

create table BankAccount_Details(
UserID              int not null,
BankAccountNumber   int not null,
BankAccountName     varchar(20),
IFSCCode          varchar(10),
BankAccountType_ID     int not null,
BankAccountPin      varchar(10),
primary key        (BankAccountNumber),
foreign key        (UserID)
references          User_Info (UserID),
foreign key(BankAccountType_ID)
references BankAccount_Type(BankAccountType_ID));

desc BankAccount_Details;

create table BankAccount_Type(
BankAccounType        enum ( "SAVINGS", "CURRENT","SALARY", "LOAN"),
BankAccountType_ID     int not null,
AccountType_Code        varchar(3),
AccountType_Descpr     varchar(25),
primary key           (BankAccountType_ID)
);
Select * from BankAccount_Details where UserID = ba.getUserId();
desc BankAccount_Type;

create table Transaction_Details(
UserID                   int not null,
TransactionID           int not null,
TransactionAmount       double,
TransactionType         enum ("CREDIT","DEBIT"),
Transaction_UserID     int not  null,
Transaction_Date         datetime,
Transaction_Account_Type    enum ("BANK_ACCOUNT","WALLET","CASH"),
primary key      (TransactionID),
foreign key      (Transaction_UserID)
references        User_Info (UserID));


desc Transaction_Details;
