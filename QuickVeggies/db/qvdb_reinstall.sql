USE [master]
GO
drop database qvdb
go
/****** Object:  Database [qvdb]    Script Date: 7/11/2017 10:46:04 AM ******/
CREATE DATABASE [qvdb]
 CONTAINMENT = NONE
GO
ALTER DATABASE [qvdb] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [qvdb].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [qvdb] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [qvdb] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [qvdb] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [qvdb] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [qvdb] SET ARITHABORT OFF 
GO
ALTER DATABASE [qvdb] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [qvdb] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [qvdb] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [qvdb] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [qvdb] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [qvdb] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [qvdb] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [qvdb] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [qvdb] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [qvdb] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [qvdb] SET  ENABLE_BROKER 
GO
ALTER DATABASE [qvdb] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [qvdb] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [qvdb] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [qvdb] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [qvdb] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [qvdb] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [qvdb] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [qvdb] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [qvdb] SET  MULTI_USER 
GO
ALTER DATABASE [qvdb] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [qvdb] SET DB_CHAINING OFF 
GO
ALTER DATABASE [qvdb] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [qvdb] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [qvdb]
GO
/****** Object:  Table [dbo].[accountEntries]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[accountEntries](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[parentId] [int] NULL,
	[accountName] [varchar](160) NULL,
	[transIdCol] [varchar](100) NULL,
	[dateCol] [varchar](100) NULL,
	[chqnoCol] [varchar](50) NULL,
	[descriptionCol] [varchar](200) NULL,
	[withdrawalCol] [real] NULL,
	[depositCol] [real] NULL,
	[balanceCol] [real] NULL,
	[status] [int] NULL,
	[payee] [varchar](160) NULL,
	[expense] [varchar](100) NULL,
	[comment] [varchar](160) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[accountEntryPayments]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[accountEntryPayments](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[account_entry_id] [int] NOT NULL,
	[payment_table] [varchar](50) NOT NULL,
	[payment_id] [int] NOT NULL,
 CONSTRAINT [PK_accountEntryPayments] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [IX_accountEntryPayments_Payment] UNIQUE NONCLUSTERED 
(
	[payment_id] ASC,
	[payment_table] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[accounts]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[accounts](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[acc_name] [varchar](100) NULL,
	[acc_type] [int] NULL,
	[acc_number] [varchar](50) NULL,
	[bank_name] [varchar](100) NULL,
	[balance] [real] NULL,
	[initBalance] [real] NULL,
	[phone] [varchar](20) NULL,
	[description] [varchar](200) NULL,
	[lastupdated] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[arrival]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[arrival](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[type] [varchar](10) NULL,
	[date] [varchar](20) NULL,
	[fruit] [varchar](20) NULL,
	[challan] [varchar](45) NULL,
	[supplier] [varchar](45) NULL,
	[totalQuantity] [varchar](10) NULL,
	[fullCase] [varchar](10) NULL,
	[halfCase] [varchar](10) NULL,
	[fwagent] [varchar](45) NULL,
	[truck] [varchar](20) NULL,
	[driver] [varchar](20) NULL,
	[gross] [varchar](10) NULL,
	[charges] [varchar](10) NULL,
	[net] [varchar](10) NULL,
	[remarks] [varchar](60) NULL,
	[amanat] [varchar](10) NULL,
	[dealID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[auditLog]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[auditLog](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userId] [varchar](100) NULL,
	[eventTime] [datetime] NULL,
	[eventDetail] [varchar](300) NULL,
	[eventObject] [varchar](100) NULL,
	[eventObjectId] [int] NULL,
	[oldValues] [varchar](max) NULL,
	[newValues] [varchar](max) NULL,
	[name] [varchar](256) NULL,
	[amount] [int] NULL,
	[date] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[boxSizes]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[boxSizes](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[buyerDeals]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[buyerDeals](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[buyerTitle] [varchar](45) NULL,
	[dealDate] [varchar](20) NULL,
	[buyerRate] [varchar](10) NULL,
	[buyerPay] [varchar](10) NULL,
	[boxes] [varchar](10) NULL,
	[amountReceived] [varchar](20) NULL,
	[aggregatedAmount] [varchar](20) NULL,
	[fruit] [varchar](100) NULL,
	[boxSizeType] [varchar](100) NULL,
	[qualityType] [varchar](100) NULL,
	[dealID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[buyerExpenseInfo]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[buyerExpenseInfo](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](100) NOT NULL,
	[type] [varchar](100) NOT NULL,
	[defaultAmount] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[buyers1]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[buyers1](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](64) NULL,
	[firstName] [varchar](64) NULL,
	[lastName] [varchar](64) NULL,
	[company] [varchar](64) NULL,
	[proprietor] [varchar](64) NULL,
	[mobile] [varchar](64) NULL,
	[mobile2] [varchar](64) NULL,
	[email] [varchar](64) NULL,
	[shopno] [varchar](64) NULL,
	[city] [varchar](64) NULL,
	[email2] [varchar](64) NULL,
	[parentCompany] [varchar](64) NULL,
	[creditPeriod] [varchar](64) NULL,
	[paymentMethod] [int] NULL,
	[buyerType] [varchar](10) NULL,
	[photo] [image] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[charges]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[charges](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[chargeName] [varchar](100) NULL,
	[value] [varchar](100) NULL,
	[chargeType] [varchar](15) NULL,
	[chargeRate] [varchar](15) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [id_unique] UNIQUE NONCLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[companyInfo]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[companyInfo](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](100) NULL,
	[address] [varchar](300) NULL,
	[website] [varchar](100) NULL,
	[phone] [varchar](100) NULL,
	[email] [varchar](100) NULL,
	[industryType] [varchar](100) NULL,
	[password] [varchar](64) NULL,
	[founderId] [varchar](10) NOT NULL,
	[foundationDate] [varchar](10) NOT NULL,
	[logo] [image] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[dealCharges]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[dealCharges](
	[chargeID] [int] NOT NULL,
	[dealID] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[expenditures]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[expenditures](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[companyId] [int] NOT NULL,
	[amount] [varchar](20) NULL,
	[date] [varchar](50) NULL,
	[comment] [varchar](200) NULL,
	[billto] [varchar](100) NULL,
	[type] [varchar](100) NULL,
	[receipt] [image] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[expenditureType]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[expenditureType](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[expenseInfo]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[expenseInfo](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](100) NOT NULL,
	[type] [varchar](100) NOT NULL,
	[defaultAmount] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[fruitBoxSizes]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[fruitBoxSizes](
	[fruit_id] [int] NOT NULL,
	[boxSize_id] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[fruitQuality]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[fruitQuality](
	[fruit_id] [int] NOT NULL,
	[quality_id] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[fruits]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[fruits](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ladaanBijakSaleDeals]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ladaanBijakSaleDeals](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[dealDate] [varchar](20) NULL,
	[buyerRate] [varchar](10) NULL,
	[boxes] [varchar](10) NULL,
	[comission] [varchar](10) NULL,
	[freight] [varchar](10) NULL,
	[totalAmount] [varchar](20) NULL,
	[aggregatedAmount] [varchar](20) NULL,
	[dealID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[last_expenses]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[last_expenses](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[category] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[partyMoney]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[partyMoney](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](150) NOT NULL,
	[partyType] [varchar](100) NOT NULL,
	[date] [varchar](100) NOT NULL,
	[paymentMode] [varchar](100) NOT NULL,
	[paid] [varchar](100) NOT NULL,
	[received] [varchar](100) NOT NULL,
	[bankName] [varchar](100) NULL,
	[chequeNo] [varchar](100) NULL,
	[depositDate] [varchar](100) NULL,
	[isAdvanced] [varchar](10) NOT NULL,
	[receipt] [image] NULL,
	[description] [varchar](200) NULL,
	[externalId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[qualities]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[qualities](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[storageBuyerDeals]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[storageBuyerDeals](
	[buyerDealLineId] [int] NOT NULL,
	[strorageDealLineId] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[supplier]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[supplier](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[company] [varchar](45) NULL,
	[proprietor] [varchar](45) NULL,
	[date] [varchar](45) NULL,
	[cases] [varchar](45) NULL,
	[gross] [varchar](45) NULL,
	[net] [varchar](45) NULL,
	[fw] [varchar](45) NULL,
	[grower] [varchar](45) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[supplierDeals]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[supplierDeals](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[supplierTitle] [varchar](45) NULL,
	[date] [varchar](20) NULL,
	[supplierRate] [varchar](10) NULL,
	[net] [varchar](10) NULL,
	[cases] [varchar](10) NULL,
	[agent] [varchar](10) NULL,
	[amountReceived] [varchar](20) NULL,
	[fruit] [varchar](100) NULL,
	[boxSizeType] [varchar](100) NULL,
	[qualityType] [varchar](100) NULL,
	[dealID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[suppliers1]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[suppliers1](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](64) NULL,
	[firstName] [varchar](64) NULL,
	[lastName] [varchar](64) NULL,
	[company] [varchar](128) NULL,
	[proprietor] [varchar](128) NULL,
	[mobile] [varchar](128) NULL,
	[mobile2] [varchar](128) NULL,
	[email] [varchar](128) NULL,
	[village] [varchar](128) NULL,
	[po] [varchar](128) NULL,
	[tehsil] [varchar](128) NULL,
	[ac] [varchar](128) NULL,
	[bank] [varchar](128) NULL,
	[ifsc] [varchar](128) NULL,
	[photo] [image] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[temp_accountEntries]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[temp_accountEntries](
	[id] [int] NOT NULL,
	[accountName] [varchar](160) NULL,
	[transIdCol] [varchar](100) NULL,
	[dateCol] [varchar](100) NULL,
	[chqnoCol] [varchar](50) NULL,
	[descriptionCol] [varchar](200) NULL,
	[withdrawalCol] [real] NULL,
	[depositCol] [real] NULL,
	[balanceCol] [real] NULL,
	[status] [int] NULL,
	[payee] [varchar](160) NULL,
	[expense] [varchar](100) NULL,
	[comment] [varchar](160) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[temp_accounts]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[temp_accounts](
	[id] [int] NOT NULL,
	[acc_name] [varchar](100) NULL,
	[acc_type] [int] NULL,
	[acc_number] [varchar](50) NULL,
	[bank_name] [varchar](100) NULL,
	[balance] [real] NULL,
	[initBalance] [real] NULL,
	[phone] [varchar](20) NULL,
	[description] [varchar](200) NULL,
	[lastupdated] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[temp_arrival]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[temp_arrival](
	[id] [int] NOT NULL,
	[type] [varchar](10) NULL,
	[date] [varchar](20) NULL,
	[fruit] [varchar](20) NULL,
	[challan] [varchar](45) NULL,
	[supplier] [varchar](45) NULL,
	[totalQuantity] [varchar](10) NULL,
	[fullCase] [varchar](10) NULL,
	[halfCase] [varchar](10) NULL,
	[fwagent] [varchar](45) NULL,
	[truck] [varchar](20) NULL,
	[driver] [varchar](20) NULL,
	[gross] [varchar](10) NULL,
	[charges] [varchar](10) NULL,
	[net] [varchar](10) NULL,
	[remarks] [varchar](60) NULL,
	[amanat] [varchar](10) NULL,
	[dealID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[temp_buyerDeals]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[temp_buyerDeals](
	[id] [int] NOT NULL,
	[buyerTitle] [varchar](45) NULL,
	[dealDate] [varchar](20) NULL,
	[buyerRate] [varchar](10) NULL,
	[buyerPay] [varchar](10) NULL,
	[boxes] [varchar](10) NULL,
	[amountReceived] [varchar](20) NULL,
	[aggregatedAmount] [varchar](20) NULL,
	[fruit] [varchar](100) NULL,
	[boxSizeType] [varchar](100) NULL,
	[qualityType] [varchar](100) NULL,
	[dealID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[temp_buyers1]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[temp_buyers1](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](64) NULL,
	[firstName] [varchar](64) NULL,
	[lastName] [varchar](64) NULL,
	[company] [varchar](64) NULL,
	[proprietor] [varchar](64) NULL,
	[mobile] [varchar](64) NULL,
	[mobile2] [varchar](64) NULL,
	[email] [varchar](64) NULL,
	[shopno] [varchar](64) NULL,
	[city] [varchar](64) NULL,
	[email2] [varchar](64) NULL,
	[parentCompany] [varchar](64) NULL,
	[creditPeriod] [varchar](64) NULL,
	[paymentMethod] [int] NULL,
	[buyerType] [varchar](10) NULL,
	[photo] [image] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[temp_expenditures]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[temp_expenditures](
	[id] [int] NOT NULL,
	[amount] [varchar](20) NULL,
	[date] [varchar](50) NULL,
	[comment] [varchar](200) NULL,
	[billto] [varchar](100) NULL,
	[type] [varchar](100) NULL,
	[receipt] [image] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[temp_expenses]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[temp_expenses](
	[id] [int] NOT NULL,
	[amount] [varchar](10) NULL,
	[date] [varchar](20) NULL,
	[comment] [varchar](20) NULL,
	[billto] [varchar](10) NULL,
	[type] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[temp_last_expenses]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[temp_last_expenses](
	[id] [int] NOT NULL,
	[category] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[temp_supplierDeals]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[temp_supplierDeals](
	[id] [int] NOT NULL,
	[supplierTitle] [varchar](45) NULL,
	[date] [varchar](20) NULL,
	[supplierRate] [varchar](10) NULL,
	[net] [varchar](10) NULL,
	[cases] [varchar](10) NULL,
	[agent] [varchar](10) NULL,
	[amountReceived] [varchar](20) NULL,
	[fruit] [varchar](100) NULL,
	[boxSizeType] [varchar](100) NULL,
	[qualityType] [varchar](100) NULL,
	[dealID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[temp_suppliers1]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[temp_suppliers1](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](64) NULL,
	[firstName] [varchar](64) NULL,
	[lastName] [varchar](64) NULL,
	[company] [varchar](128) NULL,
	[proprietor] [varchar](128) NULL,
	[mobile] [varchar](128) NULL,
	[mobile2] [varchar](128) NULL,
	[email] [varchar](128) NULL,
	[village] [varchar](128) NULL,
	[po] [varchar](128) NULL,
	[tehsil] [varchar](128) NULL,
	[ac] [varchar](128) NULL,
	[bank] [varchar](128) NULL,
	[ifsc] [varchar](128) NULL,
	[photo] [image] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[templates]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[templates](
	[accountName] [varchar](160) NULL,
	[dateCol] [int] NULL,
	[chqnoCol] [int] NULL,
	[descriptionCol] [int] NULL,
	[withdrawalCol] [int] NULL,
	[depositCol] [int] NULL,
	[balanceCol] [int] NULL,
	[transIdCol] [int] NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[users]    Script Date: 7/11/2017 10:46:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[users](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](32) NULL,
	[pass] [varchar](256) NULL,
	[email] [varchar](64) NULL,
	[companyId] [int] NULL,
	[role] [varchar](5) NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[accountant]    Script Date: 10/24/17 8:13 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[accountants](
	[id] [int] NOT NULL,
	[name] [varchar](32) NOT NULL,
	[pass] [varchar](256) NOT NULL,
	[companyId] [int] NOT NULL, /* [role] [varchar](5) NULL,*/
        [sinceDate] [varchar](10) NOT NULL
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[accountEntries] ADD  DEFAULT (NULL) FOR [accountName]
GO
ALTER TABLE [dbo].[accountEntries] ADD  DEFAULT (NULL) FOR [transIdCol]
GO
ALTER TABLE [dbo].[accountEntries] ADD  DEFAULT (NULL) FOR [dateCol]
GO
ALTER TABLE [dbo].[accountEntries] ADD  DEFAULT (NULL) FOR [chqnoCol]
GO
ALTER TABLE [dbo].[accountEntries] ADD  DEFAULT (NULL) FOR [descriptionCol]
GO
ALTER TABLE [dbo].[accountEntries] ADD  DEFAULT (NULL) FOR [withdrawalCol]
GO
ALTER TABLE [dbo].[accountEntries] ADD  DEFAULT (NULL) FOR [depositCol]
GO
ALTER TABLE [dbo].[accountEntries] ADD  DEFAULT (NULL) FOR [balanceCol]
GO
ALTER TABLE [dbo].[accountEntries] ADD  DEFAULT (NULL) FOR [status]
GO
ALTER TABLE [dbo].[accountEntries] ADD  DEFAULT (NULL) FOR [payee]
GO
ALTER TABLE [dbo].[accountEntries] ADD  DEFAULT (NULL) FOR [expense]
GO
ALTER TABLE [dbo].[accountEntries] ADD  DEFAULT (NULL) FOR [comment]
GO
ALTER TABLE [dbo].[accounts] ADD  DEFAULT (NULL) FOR [acc_name]
GO
ALTER TABLE [dbo].[accounts] ADD  DEFAULT (NULL) FOR [acc_type]
GO
ALTER TABLE [dbo].[accounts] ADD  DEFAULT (NULL) FOR [acc_number]
GO
ALTER TABLE [dbo].[accounts] ADD  DEFAULT (NULL) FOR [bank_name]
GO
ALTER TABLE [dbo].[accounts] ADD  DEFAULT (NULL) FOR [balance]
GO
ALTER TABLE [dbo].[accounts] ADD  DEFAULT (NULL) FOR [initBalance]
GO
ALTER TABLE [dbo].[accounts] ADD  DEFAULT (NULL) FOR [phone]
GO
ALTER TABLE [dbo].[accounts] ADD  DEFAULT (NULL) FOR [description]
GO
ALTER TABLE [dbo].[accounts] ADD  DEFAULT (NULL) FOR [lastupdated]
GO
ALTER TABLE [dbo].[arrival] ADD  DEFAULT (NULL) FOR [type]
GO
ALTER TABLE [dbo].[arrival] ADD  DEFAULT (NULL) FOR [date]
GO
ALTER TABLE [dbo].[arrival] ADD  DEFAULT (NULL) FOR [fruit]
GO
ALTER TABLE [dbo].[arrival] ADD  DEFAULT (NULL) FOR [supplier]
GO
ALTER TABLE [dbo].[arrival] ADD  DEFAULT (NULL) FOR [totalQuantity]
GO
ALTER TABLE [dbo].[arrival] ADD  DEFAULT (NULL) FOR [fullCase]
GO
ALTER TABLE [dbo].[arrival] ADD  DEFAULT (NULL) FOR [halfCase]
GO
ALTER TABLE [dbo].[arrival] ADD  DEFAULT (NULL) FOR [fwagent]
GO
ALTER TABLE [dbo].[arrival] ADD  DEFAULT (NULL) FOR [truck]
GO
ALTER TABLE [dbo].[arrival] ADD  DEFAULT (NULL) FOR [driver]
GO
ALTER TABLE [dbo].[arrival] ADD  DEFAULT (NULL) FOR [gross]
GO
ALTER TABLE [dbo].[arrival] ADD  DEFAULT (NULL) FOR [charges]
GO
ALTER TABLE [dbo].[arrival] ADD  DEFAULT (NULL) FOR [net]
GO
ALTER TABLE [dbo].[arrival] ADD  DEFAULT (NULL) FOR [remarks]
GO
ALTER TABLE [dbo].[arrival] ADD  DEFAULT (NULL) FOR [amanat]
GO
ALTER TABLE [dbo].[arrival] ADD  DEFAULT (NULL) FOR [dealID]
GO
ALTER TABLE [dbo].[auditLog] ADD  DEFAULT (NULL) FOR [userId]
GO
ALTER TABLE [dbo].[auditLog] ADD  DEFAULT (sysdatetime()) FOR [eventTime]
GO
ALTER TABLE [dbo].[auditLog] ADD  DEFAULT (NULL) FOR [eventDetail]
GO
ALTER TABLE [dbo].[auditLog] ADD  DEFAULT (NULL) FOR [eventObject]
GO
ALTER TABLE [dbo].[auditLog] ADD  DEFAULT (NULL) FOR [eventObjectId]
GO
ALTER TABLE [dbo].[buyerDeals] ADD  DEFAULT (NULL) FOR [buyerTitle]
GO
ALTER TABLE [dbo].[buyerDeals] ADD  DEFAULT (NULL) FOR [dealDate]
GO
ALTER TABLE [dbo].[buyerDeals] ADD  DEFAULT (NULL) FOR [buyerRate]
GO
ALTER TABLE [dbo].[buyerDeals] ADD  DEFAULT (NULL) FOR [buyerPay]
GO
ALTER TABLE [dbo].[buyerDeals] ADD  DEFAULT (NULL) FOR [boxes]
GO
ALTER TABLE [dbo].[buyerDeals] ADD  DEFAULT (NULL) FOR [amountReceived]
GO
ALTER TABLE [dbo].[buyerDeals] ADD  DEFAULT (NULL) FOR [aggregatedAmount]
GO
ALTER TABLE [dbo].[buyerDeals] ADD  DEFAULT (NULL) FOR [fruit]
GO
ALTER TABLE [dbo].[buyerDeals] ADD  DEFAULT (NULL) FOR [boxSizeType]
GO
ALTER TABLE [dbo].[buyerDeals] ADD  DEFAULT (NULL) FOR [qualityType]
GO
ALTER TABLE [dbo].[buyerDeals] ADD  DEFAULT (NULL) FOR [dealID]
GO
ALTER TABLE [dbo].[buyerExpenseInfo] ADD  DEFAULT (NULL) FOR [defaultAmount]
GO
ALTER TABLE [dbo].[buyers1] ADD  DEFAULT (NULL) FOR [title]
GO
ALTER TABLE [dbo].[buyers1] ADD  DEFAULT (NULL) FOR [firstName]
GO
ALTER TABLE [dbo].[buyers1] ADD  DEFAULT (NULL) FOR [lastName]
GO
ALTER TABLE [dbo].[buyers1] ADD  DEFAULT (NULL) FOR [company]
GO
ALTER TABLE [dbo].[buyers1] ADD  DEFAULT (NULL) FOR [proprietor]
GO
ALTER TABLE [dbo].[buyers1] ADD  DEFAULT (NULL) FOR [mobile]
GO
ALTER TABLE [dbo].[buyers1] ADD  DEFAULT (NULL) FOR [mobile2]
GO
ALTER TABLE [dbo].[buyers1] ADD  DEFAULT (NULL) FOR [email]
GO
ALTER TABLE [dbo].[buyers1] ADD  DEFAULT (NULL) FOR [shopno]
GO
ALTER TABLE [dbo].[buyers1] ADD  DEFAULT (NULL) FOR [city]
GO
ALTER TABLE [dbo].[buyers1] ADD  DEFAULT (NULL) FOR [email2]
GO
ALTER TABLE [dbo].[buyers1] ADD  DEFAULT (NULL) FOR [parentCompany]
GO
ALTER TABLE [dbo].[buyers1] ADD  DEFAULT (NULL) FOR [creditPeriod]
GO
ALTER TABLE [dbo].[buyers1] ADD  DEFAULT (NULL) FOR [paymentMethod]
GO
ALTER TABLE [dbo].[buyers1] ADD  DEFAULT (NULL) FOR [buyerType]
GO
ALTER TABLE [dbo].[buyers1] ADD  DEFAULT (NULL) FOR [photo]
GO
ALTER TABLE [dbo].[charges] ADD  DEFAULT (NULL) FOR [chargeName]
GO
ALTER TABLE [dbo].[charges] ADD  DEFAULT (NULL) FOR [value]
GO
ALTER TABLE [dbo].[charges] ADD  DEFAULT ('%') FOR [chargeType]
GO
ALTER TABLE [dbo].[charges] ADD  DEFAULT ('0') FOR [chargeRate]
GO
ALTER TABLE [dbo].[companyInfo] ADD  DEFAULT (NULL) FOR [name]
GO
ALTER TABLE [dbo].[companyInfo] ADD  DEFAULT (NULL) FOR [address]
GO
ALTER TABLE [dbo].[companyInfo] ADD  DEFAULT (NULL) FOR [website]
GO
ALTER TABLE [dbo].[companyInfo] ADD  DEFAULT (NULL) FOR [phone]
GO
ALTER TABLE [dbo].[companyInfo] ADD  DEFAULT (NULL) FOR [email]
GO
ALTER TABLE [dbo].[companyInfo] ADD  DEFAULT (NULL) FOR [industryType]
GO
ALTER TABLE [dbo].[companyInfo] ADD  DEFAULT (NULL) FOR [password]
GO
ALTER TABLE [dbo].[companyInfo] ADD  DEFAULT (NULL) FOR [logo]
GO
ALTER TABLE [dbo].[expenditures] ADD  DEFAULT (NULL) FOR [amount]
GO
ALTER TABLE [dbo].[expenditures] ADD  DEFAULT (NULL) FOR [date]
GO
ALTER TABLE [dbo].[expenditures] ADD  DEFAULT (NULL) FOR [comment]
GO
ALTER TABLE [dbo].[expenditures] ADD  DEFAULT (NULL) FOR [billto]
GO
ALTER TABLE [dbo].[expenditures] ADD  DEFAULT (NULL) FOR [type]
GO
ALTER TABLE [dbo].[expenditures] ADD  DEFAULT (NULL) FOR [receipt]
GO
ALTER TABLE [dbo].[expenseInfo] ADD  DEFAULT (NULL) FOR [defaultAmount]
GO
ALTER TABLE [dbo].[ladaanBijakSaleDeals] ADD  DEFAULT (NULL) FOR [dealDate]
GO
ALTER TABLE [dbo].[ladaanBijakSaleDeals] ADD  DEFAULT (NULL) FOR [buyerRate]
GO
ALTER TABLE [dbo].[ladaanBijakSaleDeals] ADD  DEFAULT (NULL) FOR [boxes]
GO
ALTER TABLE [dbo].[ladaanBijakSaleDeals] ADD  DEFAULT (NULL) FOR [comission]
GO
ALTER TABLE [dbo].[ladaanBijakSaleDeals] ADD  DEFAULT (NULL) FOR [freight]
GO
ALTER TABLE [dbo].[ladaanBijakSaleDeals] ADD  DEFAULT (NULL) FOR [totalAmount]
GO
ALTER TABLE [dbo].[ladaanBijakSaleDeals] ADD  DEFAULT (NULL) FOR [aggregatedAmount]
GO
ALTER TABLE [dbo].[ladaanBijakSaleDeals] ADD  DEFAULT (NULL) FOR [dealID]
GO
ALTER TABLE [dbo].[last_expenses] ADD  DEFAULT (NULL) FOR [category]
GO
ALTER TABLE [dbo].[partyMoney] ADD  DEFAULT ('0') FOR [paid]
GO
ALTER TABLE [dbo].[partyMoney] ADD  DEFAULT ('0') FOR [received]
GO
ALTER TABLE [dbo].[partyMoney] ADD  DEFAULT (NULL) FOR [bankName]
GO
ALTER TABLE [dbo].[partyMoney] ADD  DEFAULT (NULL) FOR [chequeNo]
GO
ALTER TABLE [dbo].[partyMoney] ADD  DEFAULT (NULL) FOR [depositDate]
GO
ALTER TABLE [dbo].[partyMoney] ADD  DEFAULT ('false') FOR [isAdvanced]
GO
ALTER TABLE [dbo].[partyMoney] ADD  DEFAULT (NULL) FOR [receipt]
GO
ALTER TABLE [dbo].[partyMoney] ADD  DEFAULT (NULL) FOR [description]
GO
ALTER TABLE [dbo].[supplier] ADD  DEFAULT (NULL) FOR [company]
GO
ALTER TABLE [dbo].[supplier] ADD  DEFAULT (NULL) FOR [proprietor]
GO
ALTER TABLE [dbo].[supplier] ADD  DEFAULT (NULL) FOR [date]
GO
ALTER TABLE [dbo].[supplier] ADD  DEFAULT (NULL) FOR [cases]
GO
ALTER TABLE [dbo].[supplier] ADD  DEFAULT (NULL) FOR [gross]
GO
ALTER TABLE [dbo].[supplier] ADD  DEFAULT (NULL) FOR [net]
GO
ALTER TABLE [dbo].[supplier] ADD  DEFAULT (NULL) FOR [fw]
GO
ALTER TABLE [dbo].[supplier] ADD  DEFAULT (NULL) FOR [grower]
GO
ALTER TABLE [dbo].[supplierDeals] ADD  DEFAULT (NULL) FOR [supplierTitle]
GO
ALTER TABLE [dbo].[supplierDeals] ADD  DEFAULT (NULL) FOR [date]
GO
ALTER TABLE [dbo].[supplierDeals] ADD  DEFAULT (NULL) FOR [supplierRate]
GO
ALTER TABLE [dbo].[supplierDeals] ADD  DEFAULT (NULL) FOR [net]
GO
ALTER TABLE [dbo].[supplierDeals] ADD  DEFAULT (NULL) FOR [cases]
GO
ALTER TABLE [dbo].[supplierDeals] ADD  DEFAULT (NULL) FOR [agent]
GO
ALTER TABLE [dbo].[supplierDeals] ADD  DEFAULT (NULL) FOR [amountReceived]
GO
ALTER TABLE [dbo].[supplierDeals] ADD  DEFAULT (NULL) FOR [fruit]
GO
ALTER TABLE [dbo].[supplierDeals] ADD  DEFAULT (NULL) FOR [boxSizeType]
GO
ALTER TABLE [dbo].[supplierDeals] ADD  DEFAULT (NULL) FOR [qualityType]
GO
ALTER TABLE [dbo].[supplierDeals] ADD  DEFAULT (NULL) FOR [dealID]
GO
ALTER TABLE [dbo].[suppliers1] ADD  DEFAULT (NULL) FOR [title]
GO
ALTER TABLE [dbo].[suppliers1] ADD  DEFAULT (NULL) FOR [firstName]
GO
ALTER TABLE [dbo].[suppliers1] ADD  DEFAULT (NULL) FOR [lastName]
GO
ALTER TABLE [dbo].[suppliers1] ADD  DEFAULT (NULL) FOR [company]
GO
ALTER TABLE [dbo].[suppliers1] ADD  DEFAULT (NULL) FOR [proprietor]
GO
ALTER TABLE [dbo].[suppliers1] ADD  DEFAULT (NULL) FOR [mobile]
GO
ALTER TABLE [dbo].[suppliers1] ADD  DEFAULT (NULL) FOR [mobile2]
GO
ALTER TABLE [dbo].[suppliers1] ADD  DEFAULT (NULL) FOR [email]
GO
ALTER TABLE [dbo].[suppliers1] ADD  DEFAULT (NULL) FOR [village]
GO
ALTER TABLE [dbo].[suppliers1] ADD  DEFAULT (NULL) FOR [po]
GO
ALTER TABLE [dbo].[suppliers1] ADD  DEFAULT (NULL) FOR [tehsil]
GO
ALTER TABLE [dbo].[suppliers1] ADD  DEFAULT (NULL) FOR [ac]
GO
ALTER TABLE [dbo].[suppliers1] ADD  DEFAULT (NULL) FOR [bank]
GO
ALTER TABLE [dbo].[suppliers1] ADD  DEFAULT (NULL) FOR [ifsc]
GO
ALTER TABLE [dbo].[suppliers1] ADD  DEFAULT (NULL) FOR [photo]
GO
ALTER TABLE [dbo].[temp_accountEntries] ADD  DEFAULT (NULL) FOR [accountName]
GO
ALTER TABLE [dbo].[temp_accountEntries] ADD  DEFAULT (NULL) FOR [transIdCol]
GO
ALTER TABLE [dbo].[temp_accountEntries] ADD  DEFAULT (NULL) FOR [dateCol]
GO
ALTER TABLE [dbo].[temp_accountEntries] ADD  DEFAULT (NULL) FOR [chqnoCol]
GO
ALTER TABLE [dbo].[temp_accountEntries] ADD  DEFAULT (NULL) FOR [descriptionCol]
GO
ALTER TABLE [dbo].[temp_accountEntries] ADD  DEFAULT (NULL) FOR [withdrawalCol]
GO
ALTER TABLE [dbo].[temp_accountEntries] ADD  DEFAULT (NULL) FOR [depositCol]
GO
ALTER TABLE [dbo].[temp_accountEntries] ADD  DEFAULT (NULL) FOR [balanceCol]
GO
ALTER TABLE [dbo].[temp_accountEntries] ADD  DEFAULT (NULL) FOR [status]
GO
ALTER TABLE [dbo].[temp_accountEntries] ADD  DEFAULT (NULL) FOR [payee]
GO
ALTER TABLE [dbo].[temp_accountEntries] ADD  DEFAULT (NULL) FOR [expense]
GO
ALTER TABLE [dbo].[temp_accountEntries] ADD  DEFAULT (NULL) FOR [comment]
GO
ALTER TABLE [dbo].[temp_accounts] ADD  DEFAULT (NULL) FOR [acc_name]
GO
ALTER TABLE [dbo].[temp_accounts] ADD  DEFAULT (NULL) FOR [acc_type]
GO
ALTER TABLE [dbo].[temp_accounts] ADD  DEFAULT (NULL) FOR [acc_number]
GO
ALTER TABLE [dbo].[temp_accounts] ADD  DEFAULT (NULL) FOR [bank_name]
GO
ALTER TABLE [dbo].[temp_accounts] ADD  DEFAULT (NULL) FOR [balance]
GO
ALTER TABLE [dbo].[temp_accounts] ADD  DEFAULT (NULL) FOR [initBalance]
GO
ALTER TABLE [dbo].[temp_accounts] ADD  DEFAULT (NULL) FOR [phone]
GO
ALTER TABLE [dbo].[temp_accounts] ADD  DEFAULT (NULL) FOR [description]
GO
ALTER TABLE [dbo].[temp_accounts] ADD  DEFAULT (NULL) FOR [lastupdated]
GO
ALTER TABLE [dbo].[temp_arrival] ADD  DEFAULT (NULL) FOR [type]
GO
ALTER TABLE [dbo].[temp_arrival] ADD  DEFAULT (NULL) FOR [date]
GO
ALTER TABLE [dbo].[temp_arrival] ADD  DEFAULT (NULL) FOR [fruit]
GO
ALTER TABLE [dbo].[temp_arrival] ADD  DEFAULT (NULL) FOR [supplier]
GO
ALTER TABLE [dbo].[temp_arrival] ADD  DEFAULT (NULL) FOR [totalQuantity]
GO
ALTER TABLE [dbo].[temp_arrival] ADD  DEFAULT (NULL) FOR [fullCase]
GO
ALTER TABLE [dbo].[temp_arrival] ADD  DEFAULT (NULL) FOR [halfCase]
GO
ALTER TABLE [dbo].[temp_arrival] ADD  DEFAULT (NULL) FOR [fwagent]
GO
ALTER TABLE [dbo].[temp_arrival] ADD  DEFAULT (NULL) FOR [truck]
GO
ALTER TABLE [dbo].[temp_arrival] ADD  DEFAULT (NULL) FOR [driver]
GO
ALTER TABLE [dbo].[temp_arrival] ADD  DEFAULT (NULL) FOR [gross]
GO
ALTER TABLE [dbo].[temp_arrival] ADD  DEFAULT (NULL) FOR [charges]
GO
ALTER TABLE [dbo].[temp_arrival] ADD  DEFAULT (NULL) FOR [net]
GO
ALTER TABLE [dbo].[temp_arrival] ADD  DEFAULT (NULL) FOR [remarks]
GO
ALTER TABLE [dbo].[temp_arrival] ADD  DEFAULT (NULL) FOR [amanat]
GO
ALTER TABLE [dbo].[temp_arrival] ADD  DEFAULT (NULL) FOR [dealID]
GO
ALTER TABLE [dbo].[temp_buyerDeals] ADD  DEFAULT (NULL) FOR [buyerTitle]
GO
ALTER TABLE [dbo].[temp_buyerDeals] ADD  DEFAULT (NULL) FOR [dealDate]
GO
ALTER TABLE [dbo].[temp_buyerDeals] ADD  DEFAULT (NULL) FOR [buyerRate]
GO
ALTER TABLE [dbo].[temp_buyerDeals] ADD  DEFAULT (NULL) FOR [buyerPay]
GO
ALTER TABLE [dbo].[temp_buyerDeals] ADD  DEFAULT (NULL) FOR [boxes]
GO
ALTER TABLE [dbo].[temp_buyerDeals] ADD  DEFAULT (NULL) FOR [amountReceived]
GO
ALTER TABLE [dbo].[temp_buyerDeals] ADD  DEFAULT (NULL) FOR [aggregatedAmount]
GO
ALTER TABLE [dbo].[temp_buyerDeals] ADD  DEFAULT (NULL) FOR [fruit]
GO
ALTER TABLE [dbo].[temp_buyerDeals] ADD  DEFAULT (NULL) FOR [boxSizeType]
GO
ALTER TABLE [dbo].[temp_buyerDeals] ADD  DEFAULT (NULL) FOR [qualityType]
GO
ALTER TABLE [dbo].[temp_buyerDeals] ADD  DEFAULT (NULL) FOR [dealID]
GO
ALTER TABLE [dbo].[temp_buyers1] ADD  DEFAULT (NULL) FOR [title]
GO
ALTER TABLE [dbo].[temp_buyers1] ADD  DEFAULT (NULL) FOR [firstName]
GO
ALTER TABLE [dbo].[temp_buyers1] ADD  DEFAULT (NULL) FOR [lastName]
GO
ALTER TABLE [dbo].[temp_buyers1] ADD  DEFAULT (NULL) FOR [company]
GO
ALTER TABLE [dbo].[temp_buyers1] ADD  DEFAULT (NULL) FOR [proprietor]
GO
ALTER TABLE [dbo].[temp_buyers1] ADD  DEFAULT (NULL) FOR [mobile]
GO
ALTER TABLE [dbo].[temp_buyers1] ADD  DEFAULT (NULL) FOR [mobile2]
GO
ALTER TABLE [dbo].[temp_buyers1] ADD  DEFAULT (NULL) FOR [email]
GO
ALTER TABLE [dbo].[temp_buyers1] ADD  DEFAULT (NULL) FOR [shopno]
GO
ALTER TABLE [dbo].[temp_buyers1] ADD  DEFAULT (NULL) FOR [city]
GO
ALTER TABLE [dbo].[temp_buyers1] ADD  DEFAULT (NULL) FOR [email2]
GO
ALTER TABLE [dbo].[temp_buyers1] ADD  DEFAULT (NULL) FOR [parentCompany]
GO
ALTER TABLE [dbo].[temp_buyers1] ADD  DEFAULT (NULL) FOR [creditPeriod]
GO
ALTER TABLE [dbo].[temp_buyers1] ADD  DEFAULT (NULL) FOR [paymentMethod]
GO
ALTER TABLE [dbo].[temp_buyers1] ADD  DEFAULT (NULL) FOR [buyerType]
GO
ALTER TABLE [dbo].[temp_buyers1] ADD  DEFAULT (NULL) FOR [photo]
GO
ALTER TABLE [dbo].[temp_expenditures] ADD  DEFAULT (NULL) FOR [amount]
GO
ALTER TABLE [dbo].[temp_expenditures] ADD  DEFAULT (NULL) FOR [date]
GO
ALTER TABLE [dbo].[temp_expenditures] ADD  DEFAULT (NULL) FOR [comment]
GO
ALTER TABLE [dbo].[temp_expenditures] ADD  DEFAULT (NULL) FOR [billto]
GO
ALTER TABLE [dbo].[temp_expenditures] ADD  DEFAULT (NULL) FOR [type]
GO
ALTER TABLE [dbo].[temp_expenditures] ADD  DEFAULT (NULL) FOR [receipt]
GO
ALTER TABLE [dbo].[temp_expenses] ADD  DEFAULT (NULL) FOR [amount]
GO
ALTER TABLE [dbo].[temp_expenses] ADD  DEFAULT (NULL) FOR [date]
GO
ALTER TABLE [dbo].[temp_expenses] ADD  DEFAULT (NULL) FOR [comment]
GO
ALTER TABLE [dbo].[temp_expenses] ADD  DEFAULT (NULL) FOR [billto]
GO
ALTER TABLE [dbo].[temp_expenses] ADD  DEFAULT (NULL) FOR [type]
GO
ALTER TABLE [dbo].[temp_last_expenses] ADD  DEFAULT (NULL) FOR [category]
GO
ALTER TABLE [dbo].[temp_supplierDeals] ADD  DEFAULT (NULL) FOR [supplierTitle]
GO
ALTER TABLE [dbo].[temp_supplierDeals] ADD  DEFAULT (NULL) FOR [date]
GO
ALTER TABLE [dbo].[temp_supplierDeals] ADD  DEFAULT (NULL) FOR [supplierRate]
GO
ALTER TABLE [dbo].[temp_supplierDeals] ADD  DEFAULT (NULL) FOR [net]
GO
ALTER TABLE [dbo].[temp_supplierDeals] ADD  DEFAULT (NULL) FOR [cases]
GO
ALTER TABLE [dbo].[temp_supplierDeals] ADD  DEFAULT (NULL) FOR [agent]
GO
ALTER TABLE [dbo].[temp_supplierDeals] ADD  DEFAULT (NULL) FOR [amountReceived]
GO
ALTER TABLE [dbo].[temp_supplierDeals] ADD  DEFAULT (NULL) FOR [fruit]
GO
ALTER TABLE [dbo].[temp_supplierDeals] ADD  DEFAULT (NULL) FOR [boxSizeType]
GO
ALTER TABLE [dbo].[temp_supplierDeals] ADD  DEFAULT (NULL) FOR [qualityType]
GO
ALTER TABLE [dbo].[temp_supplierDeals] ADD  DEFAULT (NULL) FOR [dealID]
GO
ALTER TABLE [dbo].[temp_suppliers1] ADD  DEFAULT (NULL) FOR [title]
GO
ALTER TABLE [dbo].[temp_suppliers1] ADD  DEFAULT (NULL) FOR [firstName]
GO
ALTER TABLE [dbo].[temp_suppliers1] ADD  DEFAULT (NULL) FOR [lastName]
GO
ALTER TABLE [dbo].[temp_suppliers1] ADD  DEFAULT (NULL) FOR [company]
GO
ALTER TABLE [dbo].[temp_suppliers1] ADD  DEFAULT (NULL) FOR [proprietor]
GO
ALTER TABLE [dbo].[temp_suppliers1] ADD  DEFAULT (NULL) FOR [mobile]
GO
ALTER TABLE [dbo].[temp_suppliers1] ADD  DEFAULT (NULL) FOR [mobile2]
GO
ALTER TABLE [dbo].[temp_suppliers1] ADD  DEFAULT (NULL) FOR [email]
GO
ALTER TABLE [dbo].[temp_suppliers1] ADD  DEFAULT (NULL) FOR [village]
GO
ALTER TABLE [dbo].[temp_suppliers1] ADD  DEFAULT (NULL) FOR [po]
GO
ALTER TABLE [dbo].[temp_suppliers1] ADD  DEFAULT (NULL) FOR [tehsil]
GO
ALTER TABLE [dbo].[temp_suppliers1] ADD  DEFAULT (NULL) FOR [ac]
GO
ALTER TABLE [dbo].[temp_suppliers1] ADD  DEFAULT (NULL) FOR [bank]
GO
ALTER TABLE [dbo].[temp_suppliers1] ADD  DEFAULT (NULL) FOR [ifsc]
GO
ALTER TABLE [dbo].[temp_suppliers1] ADD  DEFAULT (NULL) FOR [photo]
GO
ALTER TABLE [dbo].[templates] ADD  DEFAULT (NULL) FOR [accountName]
GO
ALTER TABLE [dbo].[templates] ADD  DEFAULT (NULL) FOR [dateCol]
GO
ALTER TABLE [dbo].[templates] ADD  DEFAULT (NULL) FOR [chqnoCol]
GO
ALTER TABLE [dbo].[templates] ADD  DEFAULT (NULL) FOR [descriptionCol]
GO
ALTER TABLE [dbo].[templates] ADD  DEFAULT (NULL) FOR [withdrawalCol]
GO
ALTER TABLE [dbo].[templates] ADD  DEFAULT (NULL) FOR [depositCol]
GO
ALTER TABLE [dbo].[templates] ADD  DEFAULT (NULL) FOR [balanceCol]
GO
ALTER TABLE [dbo].[templates] ADD  DEFAULT (NULL) FOR [transIdCol]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT (NULL) FOR [name]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT (NULL) FOR [pass]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT (NULL) FOR [email]
GO
ALTER TABLE [dbo].[users] ADD  DEFAULT (NULL) FOR [role]
GO
USE [master]
GO
ALTER DATABASE [qvdb] SET  READ_WRITE 
GO

TRUNCATE TABLE qvdb.dbo.buyers1;

INSERT INTO qvdb.dbo.buyers1 (title,firstName, lastName, company ,proprietor, mobile, mobile2, email, shopno,city,email2,parentCompany,creditPeriod,paymentMethod,buyerType) VALUES
('Cold Store','Cold Store', '',  '',  '',  '',  '',  '',  '','',  '',  '','', 1,  'regular'),
('Godown','Godown', '',  '',  '',  '',  '',  '',  '','',  '',  '','', 1,  'regular'),
( 'Cash Sale', 'Cash Sale', '',  '',  '',  '',  '',  '',  '','',  '',  '','', 1,  'regular'),
( 'Bank Sale', 'Bank Sale', '',  '',  '',  '',  '',  '',  '','',  '',  '','', 2,  'regular');
