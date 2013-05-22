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
create table Roles(
	RoleID int primary key identity(1,1),
	RoleName varchar(50),	
)
go
create table Account(
	AccountID int primary key identity(1,1),
	UserName varchar(30) foreign key references Employee(EmployeeID) ON DELETE CASCADE ON Update CASCADE,
	PassWord varchar(50),	
	RoleID int foreign key references Roles(RoleID)
)
go
--Bảng về các loại hình sự kiện như meetings,games,competitions,etc...
create table EventType(
	EventTypeID varchar(30) primary key,
	EventTypeName varchar(50),
	DescriptionType ntext
)
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
	EventTypeID varchar(30) foreign key references EventType(EventTypeID) ON DELETE CASCADE ON Update CASCADE
)
go
--Bảng thông tin giải đáp các thắc mắc 
create table FAQ(
	FAQID int primary key identity(1,1),
	Question ntext,
	Answer ntext,	
)
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
--Bảng thông tin về giải thưởng của các sự kiện diễn ra 
create table Prizes(
	PrizeID int primary key identity(1,1),
	PrizeName varchar(30),
	EventID varchar(30) foreign key references Event(EventID) ON DELETE CASCADE ON Update CASCADE
)
go
create table PrizesDetail(
	PrizesDetailID int primary key identity(1,1),
	EmployeeID varchar(30) foreign key references Employee(EmployeeID) ON DELETE CASCADE ON Update CASCADE,
	PrizeID int foreign key references Prizes(PrizeID) ON DELETE CASCADE ON Update CASCADE
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

create table Response(
	RequestID uniqueidentifier primary key,
	Content ntext,
	foreign key(RequestID) references Request(RequestID)
)
