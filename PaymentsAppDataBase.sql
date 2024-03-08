create database Payments_App;

show databases;

use Payments_App;

create table User(
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

desc User;

create table Bank_Wallet_Details(
UserID          int not null,
CurrentBalance  double,
primary key     (UserID),
foreign key     (UserID) 
references       User(UserID)
);

desc Bank_Wallet_Details;

create table BankAccount(
UserID              int not null,
BankAccountNumber   int not null,
BankAccountName     varchar(20),
IFSCCode          varchar(10),
BankAccountType_ID     int not null,
BankAccountPin      varchar(10),
primary key        (BankAccountNumber),
foreign key        (UserID)
references          User (UserID));
foreign key         (BankAccountType_ID)
references           BankAccountType (BankAccountType_ID)   

desc BankAccount;

create table BankAccountType(
BankAccounType        enum ( "SAVINGS", "CURRENT","SALARY", "LOAN"),
BankAccountType_ID     int not null,
AccountType_Code        varchar(3),
AccountType_Descpr     varchar(25),
primary key           (BankAccountType_ID)
);

desc BankAccountType;

create table Transaction(
UserID                   int not null,
TransactionID           int not null,
TransactionAmount       double,
TransactionType         enum ("CREDIT","DEBIT"),
Transaction_UserID     int not  null,
Transaction_Date         datetime,
Transaction_Account_Type    enum ("BANK_ACCOUNT","WALLET","CASH"),
primary key      (TransactionID),
foreign key      (Transaction_UserID)
references        User (UserID)
);

desc Transaction;
