use master 
go
create database BluePumpkin
go
use BluePumpkin
go
create table Employee(
	EmployeeID varchar(30) primary key,
	FullName nvarchar(50),
	Gender bit,
	Address nvarchar(50),
	Email varchar(100),
	Phone varchar(20),
	DateOfBirth date	
)
go
select * from Employee
go
insert into Employee values('E01','Trung Thanh',1,'Ha Noi','thanhbt_C00411@fpt.aptech.ac.vn','0915382186','10/22/1986')
insert into Employee values('E02','Quang Phat',1,'Nam Dinh','phatvq_C00491@fpt.aptech.ac.vn','0987654321','05/23/1988')
insert into Employee values('E03','Van Son',1,'Hai Phong','sonpv_C00388@fpt.aptech.ac.vn','0908070605','06/06/1990')
go
create table Roles(
	RoleID int primary key identity(1,1),
	RoleName varchar(50),	
)
go
insert into Roles values('admin')
insert into Roles values('employee')
go
select * from Roles
go
create table Account(
	AccountID int primary key identity(1,1),
	UserName varchar(30) foreign key references Employee(EmployeeID) ON DELETE CASCADE ON Update CASCADE,
	PassWord varchar(50),	
	RoleID int foreign key references Roles(RoleID)
)
go
select * from Account
go
insert into Account values('E01','123456',1)
insert into Account values('E02','123456',2)
insert into Account values('E03','123456',2)
go
select e.FullName from Employee e inner join Account a on e.EmployeeID=a.UserName 
go
--Bảng về các loại hình sự kiện như meetings,games,competitions,etc...
create table EventType(
	EventTypeID varchar(30) primary key,
	EventTypeName varchar(50),
	DescriptionType ntext
)
go
insert into EventType values('ET01','Meeting','This is the first type event')
insert into EventType values('ET02','Competiton','This is the second type event')
insert into EventType values('ET03','Game','This is the third type event')
go
select * from EventType
go
--Bảng các chương trình dựa trên các loại hình sự kiện trên
create table Event(
	EventID varchar(30) primary key,
	EventTitle varchar(50),
	Image varchar(255),
	Description ntext,
	StartDate date,
	EndDate date,
	Status varchar(30),
	EventTypeID varchar(30) foreign key references EventType(EventTypeID) ON DELETE CASCADE ON Update CASCADE,
	numberEmployee int
)
go
insert into Event values('EV01','Event No1','event1.jpg','Meeting of delegates or representatives.','01/01/2013','01/05/2013','Incoming','ET01',3)
insert into Event values('EV02','Event No2','event2.jpg','The industry is generally regulated under the tourism sector.','02/06/2013','02/09/2013','Incoming','ET01',3)
insert into Event values('EV03','Event No3','event3.jpg','A ceremony may only be performed by a person with certain authority. ','03/12/2012','03/20/2012','Ended','ET02',3)
insert into Event values('EV04','Event No4','event4.jpg','Meeting and reaching higher quality of services','11/17/2013','11/11/2013','Incoming','ET02',3)
insert into Event values('EV05','Event No5','event5.jpg','Competition can have both beneficial and detrimental effects.','05/01/2012','05/08/2012','Ended','ET03',3)
insert into Event values('EV06','Event No6','event6.jpg','Experts have also questioned the constructiveness of competition in profitability.','06/06/2013','06/12/2013','Incoming','ET03',3)
insert into Event values('EV07','Event No7','event7.jpg','Companies also compete for financing on the capital markets.','08/13/2013','08/19/2013','Incoming','ET02',3)
insert into Event values('EV08','Event No8','event8.jpg','Competition law has also been sold as good medicine to provide better public services.','07/22/2013','07/26/2013','Incoming','ET01',3)
insert into Event values('EV09','Event No9','event9.jpg','Competitive sports are governed by codified rules agreed upon by the participants.','09/09/2013','09/16/2013','Incoming','ET03',3)
insert into Event values('EV10','Event No10','event10.jpg','Critics of competition as a motivating factor in education systems.','10/18/2013','10/22/2013','Incoming','ET03',3)
go
select * from Event
go
--Bảng thông tin giải đáp các thắc mắc 
create table FAQ(
	FAQID int primary key identity(1,1),
	Question ntext,
	Answer ntext,	
)
go
select * from FAQ
go
--Bảng đăng ký tham gia các sự kiện,ngày đăng ký cũng như trạng thái việc được chấp nhận hay từ chối tham gia
create table RegisterEvent(
	RegisterID int primary key identity(1,1),
	RegisterDate datetime,
	IsAccept bit,
	EmployeeID varchar(30) foreign key references Employee(EmployeeID) ON DELETE CASCADE ON Update CASCADE,
	EventID varchar(30) foreign key references Event(EventID) ON DELETE CASCADE ON Update CASCADE
)
go
select * from RegisterEvent
go
--Bảng thông tin về giải thưởng của các sự kiện diễn ra 
create table Prizes(
	PrizeID int primary key identity(1,1),
	PrizeName varchar(30),
	Weight int,
	Description ntext,
	numberOfPrize int,
	EventID varchar(30) foreign key references Event(EventID) ON DELETE CASCADE ON Update CASCADE
)
go
select * from Prizes
go
insert into Prizes (PrizeName,Weight,Description,numberOfPrize,EventID) values('first prize',1,'Laptop VaiO',1,'EV09')
insert into Prizes (PrizeName,Weight,Description,numberOfPrize,EventID) values('second prize',2,'Dell',2,'EV09')
insert into Prizes (PrizeName,Weight,Description,numberOfPrize,EventID) values('third prize',3,'Iphone 5',3,'EV09')
insert into Prizes (PrizeName,Weight,Description,numberOfPrize,EventID) values('consolation prize',4,'Galaxy S4',4,'EV09')
go
create table Winners
(
	Id int primary key identity,
	EmployeesName varchar(50),
	EmployeesId varchar(30),
	IsWin bit,
	PrizeID int foreign key references Prizes(PrizeID) ON DELETE CASCADE ON Update CASCADE,
	
)
go
--Bảng chi tiết danh sách cần liên hệ
create table Request(
	RequestID uniqueidentifier default newid() primary key,
	EmployeeID varchar(30) foreign key references Employee(EmployeeID),
	Subject varchar(50),
	Message varchar(max)
)
go
select * from Request
go

create table Response(
	RequestID uniqueidentifier primary key,
	Content ntext,
	foreign key(RequestID) references Request(RequestID)
)
go
select * from Response
select * from Event
select * from RegisterEvent
go
insert into RegisterEvent values(GETDATE(),1,'E01','EV03')
insert into RegisterEvent values(GETDATE(),1,'E02','EV03')
insert into RegisterEvent values(GETDATE(),1,'E03','EV03')
insert into RegisterEvent values(GETDATE(),1,'E01','EV05')
insert into RegisterEvent values(GETDATE(),1,'E02','EV05')
insert into RegisterEvent values(GETDATE(),1,'E03','EV05')
insert into RegisterEvent values(GETDATE(),1,'E01','EV09')
insert into RegisterEvent values(GETDATE(),1,'E02','EV09')
select * from Winners
select * from Prizes
go
insert into Prizes (PrizeName,Weight,Description,numberOfPrize,EventID) values('first prize',1,'Laptop VaiO',1,'EV03')
insert into Prizes (PrizeName,Weight,Description,numberOfPrize,EventID) values('second prize',2,'Dell',2,'EV03')
insert into Prizes (PrizeName,Weight,Description,numberOfPrize,EventID) values('third prize',3,'Iphone 5',3,'EV03')
insert into Prizes (PrizeName,Weight,Description,numberOfPrize,EventID) values('consolation prize',4,'Galaxy S4',4,'EV03')
insert into Prizes (PrizeName,Weight,Description,numberOfPrize,EventID) values('first prize',1,'Laptop VaiO',1,'EV05')
insert into Prizes (PrizeName,Weight,Description,numberOfPrize,EventID) values('second prize',2,'Dell',2,'EV05')
insert into Prizes (PrizeName,Weight,Description,numberOfPrize,EventID) values('third prize',3,'Iphone 5',3,'EV05')
insert into Prizes (PrizeName,Weight,Description,numberOfPrize,EventID) values('consolation prize',4,'Galaxy S4',4,'EV05')
insert into Winners values ('Trung Thanh','E01',1,5)
insert into Winners values ('Quang Phat','E02',0,6)
insert into Winners values ('Van Son','E03',0,7)

insert into Winners values ('Trung Thanh','E01',1,9)
insert into Winners values ('Quang Phat','E02',0,10)
insert into Winners values ('Van Son','E03',0,11)

update Event set Status = 'Oncoming' where EventID = 'EV09'
update Event set Status = 'Oncoming' where EventID = 'EV08'