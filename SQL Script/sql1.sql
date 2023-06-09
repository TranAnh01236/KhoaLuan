USE [Quiz]
GO
/****** Object:  Table [dbo].[answers]    Script Date: 3/24/2023 8:08:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[answers](
	[id] [varchar](255) NOT NULL,
	[content] [nvarchar](max) NULL,
	[type] [int] NOT NULL,
	[question_id] [varchar](255) NULL,
	[audio] [nchar](255) NULL,
	[image] [nchar](255) NULL,
	[rightt] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[chapters]    Script Date: 3/24/2023 8:08:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[chapters](
	[id] [varchar](255) NOT NULL,
	[description] [nvarchar](max) NULL,
	[name] [nvarchar](255) NULL,
	[subject_id] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[exam_details]    Script Date: 3/24/2023 8:08:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[exam_details](
	[exam_id] [varchar](255) NOT NULL,
	[question_id] [varchar](255) NOT NULL,
	[scores] [float] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[exam_id] ASC,
	[question_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[exams]    Script Date: 3/24/2023 8:08:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[exams](
	[id] [varchar](255) NOT NULL,
	[description] [nvarchar](max) NULL,
	[duration] [int] NOT NULL,
	[name] [nvarchar](255) NULL,
	[type] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[grades]    Script Date: 3/24/2023 8:08:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[grades](
	[id] [varchar](255) NOT NULL,
	[description] [nvarchar](max) NULL,
	[name] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[lessons]    Script Date: 3/24/2023 8:08:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[lessons](
	[id] [varchar](255) NOT NULL,
	[description] [nvarchar](max) NULL,
	[name] [nvarchar](255) NULL,
	[chapter_id] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[questions]    Script Date: 3/24/2023 8:08:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[questions](
	[id] [varchar](255) NOT NULL,
	[audio] [nchar](255) NULL,
	[content] [nvarchar](max) NULL,
	[explain] [nvarchar](max) NULL,
	[image] [nchar](255) NULL,
	[type] [int] NOT NULL,
	[lesson_id] [varchar](255) NULL,
	[user_id] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[subjects]    Script Date: 3/24/2023 8:08:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[subjects](
	[id] [varchar](255) NOT NULL,
	[description] [nvarchar](max) NULL,
	[name] [nvarchar](255) NULL,
	[grade_id] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 3/24/2023 8:08:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[id] [varchar](255) NOT NULL,
	[account] [varchar](255) NOT NULL,
	[email] [varchar](255) NOT NULL,
	[first_name] [varchar](255) NOT NULL,
	[last_name] [varchar](255) NOT NULL,
	[password] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[chapters] ([id], [description], [name], [subject_id]) VALUES (N'CHA0001', N'', N'Chương 1: Các số đến 10, hình vuông, hình tròn, hình tam giác', N'SUB0001')
INSERT [dbo].[chapters] ([id], [description], [name], [subject_id]) VALUES (N'CHA0002', N'', N'Chương 2: Phép cộng, phép trừ trong phạm vi 10', N'SUB0001')
INSERT [dbo].[chapters] ([id], [description], [name], [subject_id]) VALUES (N'CHA0003', N'', N'Chương 4: Phép cộng, phép trừ trong phạm vi 100. Đo thời gian', N'SUB0001')
INSERT [dbo].[chapters] ([id], [description], [name], [subject_id]) VALUES (N'CHA0004', N'', N'Chương 4: Phép cộng, phép trừ trong phạm vi 100. Đo thời gian', N'SUB0001')
INSERT [dbo].[chapters] ([id], [description], [name], [subject_id]) VALUES (N'CHA0005', N'', N'Chương 1: Học vần', N'SUB0002')
INSERT [dbo].[chapters] ([id], [description], [name], [subject_id]) VALUES (N'CHA0006', N'', N'Chương 2: Nhà trường', N'SUB0002')
INSERT [dbo].[chapters] ([id], [description], [name], [subject_id]) VALUES (N'CHA0007', N'', N'Chương 3: Gia đình', N'SUB0002')
INSERT [dbo].[chapters] ([id], [description], [name], [subject_id]) VALUES (N'CHA0008', N'', N'Chương 4: Thiên nhiên - đất nước', N'SUB0002')
GO
INSERT [dbo].[grades] ([id], [description], [name]) VALUES (N'GRA0001', N'Trẻ thấy thú vị với bài tập thông qua câu hỏi sinh động, tăng tư duy logic qua các bài tập trắc nghiệm Toán và Tiếng Việt.', N'Lớp 1')
INSERT [dbo].[grades] ([id], [description], [name]) VALUES (N'GRA0002', N'Hào hứng với các bài tập trắc nghiệm Toán và Tiếng Việt giúp trẻ rèn tư duy với hình ảnh bắt mắt sinh động và trực quan.', N'Lớp 2')
INSERT [dbo].[grades] ([id], [description], [name]) VALUES (N'GRA0003', N'Mỗi ngày trẻ thấy thú vị với các bài tập trắc nghiệm Toán và Tiếng Việt giúp rèn tư duy logic.', N'Lớp 3')
INSERT [dbo].[grades] ([id], [description], [name]) VALUES (N'GRA0004', N'Bài tập lớp 4 sẽ dễ dàng hơn khi con tiếp cận bài tập Toán và Tiếng Việt sinh động và thú vị giúp con có tư duy mở.', N'Lớp 4')
INSERT [dbo].[grades] ([id], [description], [name]) VALUES (N'GRA0005', N'Học thông qua bài tập trắc nghiệm Toán và Tiếng Việt theo từng dạng để các con nhớ kiến thức lâu hơn.', N'Lớp 5')
INSERT [dbo].[grades] ([id], [description], [name]) VALUES (N'GRA0006', N'Làm tập trắc nghiệm theo từng dạng các môn Toán, Lý, Sinh, Anh, Sử, Địa với các câu hỏi từ dễ tới khó có lời giải chi tiết.', N'Lớp 6')
INSERT [dbo].[grades] ([id], [description], [name]) VALUES (N'GRA0007', N'Làm tập trắc nghiệm theo từng dạng các môn Toán, Lý, Sinh, Sử, Địa với các câu hỏi từ dễ tới khó có lời giải chi tiết.', N'Lớp 7')
INSERT [dbo].[grades] ([id], [description], [name]) VALUES (N'GRA0008', N'Luyện bài tập trắc nghiệm Toán, Lý, Hóa, Sinh, Sử, Địa theo từng dạng, cấp độ, có đáp án, lời giải chi tiết.', N'Lớp 8')
INSERT [dbo].[grades] ([id], [description], [name]) VALUES (N'GRA0009', N'Làm bài tập trắc nghiệm các môn Toán, Lý, Hóa, Sinh, Anh, Sử, Địa nắm chắc kiến thức lớp 9, tự tin thi vào 10.', N'Lớp 9')
INSERT [dbo].[grades] ([id], [description], [name]) VALUES (N'GRA0010', N'Làm bài tập trắc nghiệm có phương pháp và lời giải các môn Toán, Lý, Hóa, Sinh, Anh, Sử, Địa chắc kiến thức lớp 10.', N'Lớp 10')
INSERT [dbo].[grades] ([id], [description], [name]) VALUES (N'GRA0011', N'Làm bài tập trắc nghiệm theo mức độ tư duy các môn Toán, Lý, Hóa, Sinh, Anh, Sử, Địa có ngay đáp án và lời giải chi tiết.', N'Lớp 11')
INSERT [dbo].[grades] ([id], [description], [name]) VALUES (N'GRA0012', N'Luyện bài tập trắc nghiệm trực tuyến các môn Toán, Lý, Hóa, Sinh, Anh, Sử, Địa, GDCD theo hướng đánh giá năng lực.', N'Lớp 12')
GO
INSERT [dbo].[lessons] ([id], [description], [name], [chapter_id]) VALUES (N'LES0001', NULL, N'Các số 1, 2, 3', N'CHA0001')
INSERT [dbo].[lessons] ([id], [description], [name], [chapter_id]) VALUES (N'LES0002', NULL, N'Các số 1, 2, 3, 4, 5', N'CHA0001')
INSERT [dbo].[lessons] ([id], [description], [name], [chapter_id]) VALUES (N'LES0003', NULL, N'Bé hơn, lớn hơn, bằng. Dấu <; >; =', N'CHA0001')
INSERT [dbo].[lessons] ([id], [description], [name], [chapter_id]) VALUES (N'LES0004', NULL, N'Số 0', N'CHA0001')
INSERT [dbo].[lessons] ([id], [description], [name], [chapter_id]) VALUES (N'LES0005', NULL, N'Số 6', N'CHA0001')
INSERT [dbo].[lessons] ([id], [description], [name], [chapter_id]) VALUES (N'LES0006', NULL, N'Số 7', N'CHA0001')
INSERT [dbo].[lessons] ([id], [description], [name], [chapter_id]) VALUES (N'LES0007', NULL, N'Số 8', N'CHA0001')
INSERT [dbo].[lessons] ([id], [description], [name], [chapter_id]) VALUES (N'LES0008', NULL, N'Số 9', N'CHA0001')
INSERT [dbo].[lessons] ([id], [description], [name], [chapter_id]) VALUES (N'LES0009', NULL, N'Số 10', N'CHA0001')
INSERT [dbo].[lessons] ([id], [description], [name], [chapter_id]) VALUES (N'LES0010', NULL, N'Nhiều hơn, ít hơn', N'CHA0001')
GO
INSERT [dbo].[questions] ([id], [audio], [content], [explain], [image], [type], [lesson_id], [user_id]) VALUES (N'QUE0001', NULL, N'Đáp án nào có nhiều dấu cộng hơn', N'5 dấu cộng nhiều hơn 3 dấu cộng', NULL, 0, N'LES0010', N'USER0001')
GO
INSERT [dbo].[subjects] ([id], [description], [name], [grade_id]) VALUES (N'SUB0001', NULL, N'Toán', N'GRA0001')
INSERT [dbo].[subjects] ([id], [description], [name], [grade_id]) VALUES (N'SUB0002', NULL, N'Tiếng việt', N'GRA0001')
GO
INSERT [dbo].[users] ([id], [account], [email], [first_name], [last_name], [password]) VALUES (N'USER0001', N'trananh', N'trananh@gmail.com', N'Tran', N'Anh', N'12345')
GO
ALTER TABLE [dbo].[answers]  WITH CHECK ADD  CONSTRAINT [FK3erw1a3t0r78st8ty27x6v3g1] FOREIGN KEY([question_id])
REFERENCES [dbo].[questions] ([id])
GO
ALTER TABLE [dbo].[answers] CHECK CONSTRAINT [FK3erw1a3t0r78st8ty27x6v3g1]
GO
ALTER TABLE [dbo].[chapters]  WITH CHECK ADD  CONSTRAINT [FK3rm6snrkx0k8xyqn7017b0v41] FOREIGN KEY([subject_id])
REFERENCES [dbo].[subjects] ([id])
GO
ALTER TABLE [dbo].[chapters] CHECK CONSTRAINT [FK3rm6snrkx0k8xyqn7017b0v41]
GO
ALTER TABLE [dbo].[exam_details]  WITH CHECK ADD  CONSTRAINT [FKm1r5enan4xlodbhrj7jlnwyrb] FOREIGN KEY([question_id])
REFERENCES [dbo].[questions] ([id])
GO
ALTER TABLE [dbo].[exam_details] CHECK CONSTRAINT [FKm1r5enan4xlodbhrj7jlnwyrb]
GO
ALTER TABLE [dbo].[exam_details]  WITH CHECK ADD  CONSTRAINT [FKnoba0lfoktdjg7c3tsv5cud3y] FOREIGN KEY([exam_id])
REFERENCES [dbo].[exams] ([id])
GO
ALTER TABLE [dbo].[exam_details] CHECK CONSTRAINT [FKnoba0lfoktdjg7c3tsv5cud3y]
GO
ALTER TABLE [dbo].[lessons]  WITH CHECK ADD  CONSTRAINT [FKmb78vk1f2oljr16oj1hpo45ma] FOREIGN KEY([chapter_id])
REFERENCES [dbo].[chapters] ([id])
GO
ALTER TABLE [dbo].[lessons] CHECK CONSTRAINT [FKmb78vk1f2oljr16oj1hpo45ma]
GO
ALTER TABLE [dbo].[questions]  WITH CHECK ADD  CONSTRAINT [FKjoo8hp6d3gfwctr68dl2iaemj] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[questions] CHECK CONSTRAINT [FKjoo8hp6d3gfwctr68dl2iaemj]
GO
ALTER TABLE [dbo].[questions]  WITH CHECK ADD  CONSTRAINT [FKmoaj9c9k8ncsywujtcaujs6rt] FOREIGN KEY([lesson_id])
REFERENCES [dbo].[lessons] ([id])
GO
ALTER TABLE [dbo].[questions] CHECK CONSTRAINT [FKmoaj9c9k8ncsywujtcaujs6rt]
GO
ALTER TABLE [dbo].[subjects]  WITH CHECK ADD  CONSTRAINT [FKp5gpt39lxgg443ws2tnvnukpa] FOREIGN KEY([grade_id])
REFERENCES [dbo].[grades] ([id])
GO
ALTER TABLE [dbo].[subjects] CHECK CONSTRAINT [FKp5gpt39lxgg443ws2tnvnukpa]
GO
