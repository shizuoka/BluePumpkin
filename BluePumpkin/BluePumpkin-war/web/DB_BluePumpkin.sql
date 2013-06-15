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
	DateOfBirth date,
	CreateDate date	
)
go
select * from Employee where MONTH(DateOfBirth) = MONTH(GETDATE()) and DAY(DateOfBirth) = DAY(GETDATE())
go
insert into Employee values('E000000001','Trung Thanh',1,'Ha Noi','thanhbt_C00411@fpt.aptech.ac.vn','0915382186','10/22/1986', GETDATE())
insert into Employee values('E000000002','Quang Phat',1,'Nam Dinh','phatvq_C00491@fpt.aptech.ac.vn','0987654321','05/23/1988', GETDATE())
insert into Employee values('E000000003','Van Son',1,'Hai Phong','sonpv_C00388@fpt.aptech.ac.vn','0908070605','06/15/1990', GETDATE())
insert into Employee values('E000000004','Ha An',1,'Ha Noi','anlh_C00400@fpt.aptech.ac.vn','0912883769','08/10/1991', GETDATE())
insert into Employee values('E000000005','Hoang Viet',1,'Ha Noi','vietvh_C00347@fpt.aptech.ac.vn','0934789012','06/16/1991', GETDATE())
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
insert into Account values('E000000001','e10adc3949ba59abbe56e057f20f883e',2)
insert into Account values('E000000002','e10adc3949ba59abbe56e057f20f883e',2)
insert into Account values('E000000003','e10adc3949ba59abbe56e057f20f883e',2)
insert into Account values('E000000004','e10adc3949ba59abbe56e057f20f883e',2)
insert into Account values('E000000005','e10adc3949ba59abbe56e057f20f883e',1)
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
insert into EventType values('ET02','Competition','This is the second type event')
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
	numberEmployee int,
	CreateDate date
)
go
-- dml
-- constraints
-- functions
create function GenerateEmployeeID()
returns varchar(10)
as
	begin
		declare @maxId varchar(10)
		declare @validId varchar(10)
		set @validId = 'E000000001'
		set @maxId = (select top 1 EmployeeID from Employee order by EmployeeID desc)
		if(@maxId is not null)
			begin				
				declare @calcId varchar(25)
				select @calcId = '0000000000' + convert(varchar, convert(int, substring(@maxId, 2, len(@maxId))) + 1)
				select @validId = 'E' + substring(@calcId, len(@calcId) - 8, len(@calcId))
			end
		return @validId
	end
go
create function GenerateEventID()
returns varchar(10)
as
	begin
		declare @maxId varchar(10)
		declare @validId varchar(10)
		set @validId = 'V000000001'
		set @maxId = (select top 1 EventID from Event order by EventID desc)
		if(@maxId is not null)
			begin				
				declare @calcId varchar(25)
				select @calcId = '0000000000' + convert(varchar, convert(int, substring(@maxId, 2, len(@maxId))) + 1)
				select @validId = 'V' + substring(@calcId, len(@calcId) - 8, len(@calcId))
			end
		return @validId
	end
go
select * from Event
go
insert into Event values('V000000001','Event No1','event1.jpg','Meeting of delegates or representatives.','01/01/2013','01/05/2013','Ended','ET01',5, GETDATE())
insert into Event values('V000000002','Event No2','event2.jpg','The industry is generally regulated under the tourism sector.','07/06/2013','07/12/2013','Incoming','ET01',5, GETDATE())
insert into Event values('V000000003','Event No3','event3.jpg','A ceremony may only be performed by a person with certain authority. ','06/14/2012','06/20/2012','Ended','ET02',5, GETDATE())
insert into Event values('V000000004','Event No4','event4.jpg','Meeting and reaching higher quality of services','06/15/2013','06/19/2013','Oncoming','ET02',5, GETDATE())
insert into Event values('V000000005','Event No5','event5.jpg','Competition can have both beneficial and detrimental effects.','07/01/2012','07/08/2012','Incoming','ET03',5, GETDATE())
insert into Event values('V000000006','Event No6','event6.jpg','Experts have also questioned the constructiveness of competition in profitability.','06/06/2013','06/12/2013','Ended','ET03',3, GETDATE())
insert into Event values('V000000007','Event No7','event7.jpg','Companies also compete for financing on the capital markets.','04/13/2013','04/19/2013','Ended','ET02',5, GETDATE())
insert into Event values('V000000008','Event No8','event8.jpg','Competition law has also been sold as good medicine to provide better public services.','07/22/2013','07/26/2013','Incoming','ET01',5, GETDATE())
insert into Event values('V000000009','Event No9','event9.jpg','Competitive sports are governed by codified rules agreed upon by the participants.','06/17/2011','06/21/2011','Ended','ET03',5, GETDATE())
insert into Event values('V000000010','Event No10','event10.jpg','Critics of competition as a motivating factor in education systems.','10/18/2012','10/22/2012','Ended','ET03',5, GETDATE())

insert into Event values('V000000011','Event No11','event11.jpg','competition in most countries is often limited or restricted.','07/05/2013','07/11/2013','Incoming','ET01',5, GETDATE())
insert into Event values('V000000012','Event No12','event12.jpg','This form of competition thus pitted a brand against.','06/16/2013','06/22/2013','Oncoming','ET01',5, GETDATE())
insert into Event values('V000000013','Event No13','event13.jpg','Most businesses also encourage competition . ','03/11/2012','03/18/2012','Ended','ET02',5, GETDATE())
insert into Event values('V000000014','Event No14','event14.jpg','The sales representative with the highest sales ','11/13/2013','11/21/2013','Incoming','ET02',5, GETDATE())
insert into Event values('V000000015','Event No15','event15.jpg','It should also be noted that business and economic .','05/01/2012','05/08/2012','Ended','ET03',5, GETDATE())
insert into Event values('V000000016','Event No16','event16.jpg','Experts have also questioned the constructiveness of competition in profitability.','08/06/2013','08/12/2013','Incoming','ET03',5, GETDATE())
insert into Event values('V000000017','Event No17','event17.jpg','Depending on the respective economic policy.','06/18/2013','06/23/2013','Oncoming','ET02',5, GETDATE())
insert into Event values('V000000018','Event No18','event18.jpg','Competition often is subject to legal restrictions.','07/22/2011','07/26/2011','Ended','ET01',5, GETDATE())
insert into Event values('V000000019','Event No19','event19.jpg','A practice is anti-competitive .','09/09/2012','09/16/2012','Ended','ET03',5, GETDATE())
insert into Event values('V000000020','Event No20','event20.jpg','Competition is the rivalry among sellers trying to achieve.','12/18/2013','12/22/2013','Incoming','ET03',5, GETDATE())

insert into Event values('V000000021','Event No21','event21.jpg','Compared to what the price would be .','09/01/2013','09/08/2013','Incoming','ET01',5, GETDATE())
insert into Event values('V000000022','Event No22','event1.jpg','Effective competition in the marketplace.','06/14/2013','06/19/2013','Oncoming','ET01',5, GETDATE())
insert into Event values('V000000023','Event No23','event2.jpg','Government in order to prevent or reduce. ','01/12/2012','01/20/2012','Ended','ET02',5, GETDATE())
insert into Event values('V000000024','Event No24','event3.jpg','The idea was first introduced by','11/17/2013','11/11/2013','Incoming','ET02',5, GETDATE())
insert into Event values('V000000025','Event No25','event4.jpg','Competition can have both beneficial and detrimental effects.','05/01/2012','05/08/2012','Ended','ET03',5, GETDATE())
insert into Event values('V000000026','Event No26','event5.jpg','Consumers greater selection and better.','06/06/2012','06/12/2012','Ended','ET03',5, GETDATE())
insert into Event values('V000000027','Event No27','event6.jpg','Companies also compete for financing on the capital markets.','08/13/2013','08/19/2013','Incoming','ET02',5, GETDATE())
insert into Event values('V000000028','Event No28','event7.jpg','Competition law has also been sold as good medicine to provide better public services.','06/14/2013','06/26/2013','Oncoming','ET01',5, GETDATE())
insert into Event values('V000000029','Event No29','event8.jpg','Competitive sports are governed by codified rules agreed upon by the participants.','09/09/2013','09/16/2013','Incoming','ET03',5, GETDATE())
insert into Event values('V000000030','Event No30','event9.jpg','Critics of competition as a motivating factor in education systems.','10/18/2012','10/22/2012','Ended','ET03',5, GETDATE())
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
insert into FAQ values('How to participate in the events?','ok')
insert into FAQ values('What if I am unable to login into the site?','good')
insert into FAQ values('What if I am unable to send the participation request for the event?','excellent')
insert into FAQ values('How can one be intimated with the upcoming events ?','normal')
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
-- indexies
-- sp, trigger
go
alter table RegisterEvent
	add constraint UQ_EmployeeID_EventID unique(EmployeeID, EventID)
	go
--Bảng thông tin về giải thưởng của các sự kiện diễn ra 
create table Prizes(
	PrizeID int primary key identity(1,1),
	PrizeName varchar(30),	
	Description ntext,
	numberOfPrize int,
	IsWin bit,
	EventID varchar(30) foreign key references Event(EventID) ON DELETE CASCADE ON Update CASCADE
)
go
select * from Prizes
go
insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('first prize','Laptop VaiO',1,1,'V000000009')
insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('second prize','Dell',2,0,'V000000009')
insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('third prize','Iphone 5',3,0,'V000000009')
insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('consolation prize','Galaxy S4',4,0,'V000000009')

insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('first prize','Iphone 5S',1,1,'V000000003')
insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('second prize','LG Optimus',2,0,'V000000003')
insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('third prize','Suzuky',3,0,'V000000003')
insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('consolation prize','Yamaha',4,0,'V000000003')

insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('first prize','VaiO S',1,1,'V000000005')
insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('second prize','Acer',2,0,'V000000005')
insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('third prize','Asus',3,0,'V000000005')
insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('consolation prize','Sony',4,0,'V000000005')
go
create table Winners
(
	Id int primary key identity,
	EmployeesName varchar(50),
	EmployeesId varchar(30),
	IsWin bit,
	PrizeID int foreign key references Prizes(PrizeID) ON DELETE CASCADE ON Update CASCADE
)
go

create table Comments
(
	CommentId int identity primary key,
	EmployeeID varchar(30) foreign key references Employee(EmployeeID),
	EventID varchar(30)foreign key references Event(EventID),
	Title nvarchar(100) not null,
	Content ntext not null,
	CreateDate datetime
)
go
--Bảng chi tiết danh sách cần liên hệ
go
select * from Event
select * from RegisterEvent
go
insert into RegisterEvent values(GETDATE(),1,'E000000001','V000000003')
insert into RegisterEvent values(GETDATE(),1,'E000000002','V000000003')
insert into RegisterEvent values(GETDATE(),1,'E000000003','V000000003')
insert into RegisterEvent values(GETDATE(),1,'E000000004','V000000003')
insert into RegisterEvent values(GETDATE(),1,'E000000005','V000000003')

insert into RegisterEvent values(GETDATE(),1,'E000000001','V000000005')
insert into RegisterEvent values(GETDATE(),1,'E000000002','V000000005')
insert into RegisterEvent values(GETDATE(),1,'E000000003','V000000005')

insert into RegisterEvent values(GETDATE(),1,'E000000001','V000000009')
insert into RegisterEvent values(GETDATE(),1,'E000000002','V000000009')
insert into RegisterEvent values(GETDATE(),1,'E000000003','V000000009')
insert into RegisterEvent values(GETDATE(),1,'E000000004','V000000009')
select * from Winners
select * from Prizes
go
insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('first prize','5000$',1,1,'V000000013')
insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('second prize','3000$',2,0,'V000000013')
insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('third prize','1000$',3,0,'V000000013')
insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('consolation prize','500$',4,0,'V000000013')
insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('first prize','4000$',1,1,'V000000015')
insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('second prize','2500$',2,0,'V000000015')
insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('third prize','1250$',3,0,'V000000015')
insert into Prizes (PrizeName,Description,numberOfPrize,IsWin,EventID) values('consolation prize','350$',4,0,'V000000015')

insert into Winners values ('Trung Thanh','E000000001',1,5)
insert into Winners values ('Quang Phat','E000000002',0,6)
insert into Winners values ('Van Son','E000000003',0,7)

insert into Winners values ('Trung Thanh','E000000001',1,9)
insert into Winners values ('Quang Phat','E000000002',0,10)
insert into Winners values ('Van Son','E000000003',0,11)
GO
update Event set Status = 'Oncoming' where EventID = 'V000000009'
update Event set Status = 'Oncoming' where EventID = 'V000000010'
Go
create trigger AddAccountTrigger on Account
for insert
as
begin
	update Event
	set numberEmployee = numberEmployee + 1
	where Status in ('Oncoming', 'Incoming')
end
go
select * from Event order by CreateDate DESC
