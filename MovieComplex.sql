CREATE DATABASE MoviesComplex
GO

USE MoviesComplex
GO

CREATE TABLE Actor
(
	IDActor int CONSTRAINT PK_Actor PRIMARY KEY IDENTITY,
	FirstNameActor nvarchar(200),
	LastNameActor nvarchar(200)
)
GO

CREATE TABLE Director
(
	IDDirector int CONSTRAINT PK_Director PRIMARY KEY IDENTITY,
	FirstNameDirector nvarchar(200),
	LastNameDirector nvarchar(200)
)
GO

CREATE TABLE Movie
(
	IDMovie int CONSTRAINT PK_Movie PRIMARY KEY IDENTITY,
	Title nvarchar(150),
	PublishedDate nvarchar(60),
	Description nvarchar(1000),
	OriginalTitle nvarchar(150),
	Duration nvarchar(50),
	Genre nvarchar(300),
	PicturePath nvarchar(100),
	DirectorID int CONSTRAINT FK_Movie_Director FOREIGN KEY REFERENCES Director(IDDirector)
)
GO

CREATE TABLE MovieActor
(
	IDMovieActor int CONSTRAINT PK_MovieActor PRIMARY KEY IDENTITY,
	MovieID int CONSTRAINT FK_MovieActor_Movie FOREIGN KEY REFERENCES Movie(IDMovie),
	ActorID int CONSTRAINT FK_MovieActor_Actor FOREIGN KEY REFERENCES Actor(IDActor)
)
GO

CREATE TABLE FavoriteActor
(
	IDFavoriteActor int CONSTRAINT PK_FavoriteActor PRIMARY KEY IDENTITY,
	ActorID int CONSTRAINT FK_FavoriteActor_Actor FOREIGN KEY REFERENCES Actor(IDActor) NOT NULL
)
GO

CREATE TABLE FavoriteDirector
(
	IDFavoriteDirector int CONSTRAINT PK_FavoriteDirector PRIMARY KEY IDENTITY,
	DirectorID int CONSTRAINT FK_FavoriteDirector_Director FOREIGN KEY REFERENCES Director(IDDirector) NOT NULL
)
GO

CREATE PROCEDURE AddFavoriteActor
	@ActorID int
AS
BEGIN
	INSERT INTO FavoriteActor(ActorID)
	VALUES(@ActorID)
END
GO

CREATE PROCEDURE AddFavoriteDirector
	@DirectorID int
AS
BEGIN
	INSERT INTO FavoriteDirector(DirectorID)
	VALUES(@DirectorID)
END
GO


CREATE PROCEDURE GetFavoriteActors
AS
BEGIN
	SELECT a.IDActor, a.FirstNameActor, a.LastNameActor
	FROM FavoriteActor AS fa
	INNER JOIN Actor AS a
		ON a.IDActor = fa.ActorID
END
GO

CREATE PROCEDURE GetFavoriteDirectors
AS
BEGIN
	SELECT d.IDDirector, d.FirstNameDirector, d.LastNameDirector
	FROM FavoriteDirector AS fd
	INNER JOIN Director AS d
		ON d.IDDirector = fd.DirectorID
END
GO

CREATE PROCEDURE RemoveFromFavoriteDirectors
	@DirectorID int
AS
BEGIN
	DELETE FROM FavoriteDirector
	WHERE FavoriteDirector.DirectorID = @DirectorID
END 
GO

CREATE PROCEDURE RemoveFromFavoriteActors
	@ActorID int
AS
BEGIN
	DELETE FROM FavoriteActor
	WHERE FavoriteActor.ActorID = @ActorID
END 
GO

CREATE PROCEDURE CreateDirector
	@FirstNameDirector nvarchar(200),
	@LastNameDirector nvarchar(200),
	@IDDirector int OUTPUT
AS
BEGIN
	IF EXISTS (SELECT *
		FROM Director AS d
		WHERE d.FirstNameDirector = @FirstNameDirector AND d.LastNameDirector = @LastNameDirector
	)
		BEGIN
			SELECT @IDDirector = d.IDDirector
			FROM Director AS d
			WHERE d.FirstNameDirector = @FirstNameDirector AND d.LastNameDirector = @LastNameDirector
		END
	ELSE
		BEGIN
			INSERT INTO Director(FirstNameDirector, LastNameDirector)
			VALUES(@FirstNameDirector, @LastNameDirector)
			SET @IDDirector = SCOPE_IDENTITY()
		END
END
GO

CREATE PROCEDURE GetActorByName
	@FirstNameActor nvarchar(200),
	@LastNameActor nvarchar(200)
AS
BEGIN
	SELECT *
	FROM Actor
	WHERE FirstNameActor = @FirstNameActor AND LastNameActor = @LastNameActor
END 
GO

CREATE PROCEDURE GetDirectorByName
	@FirstNameDirector nvarchar(200),
	@LastNameDirector nvarchar(200)
AS
BEGIN
	SELECT *
	FROM Director
	WHERE FirstNameDirector = @FirstNameDirector AND LastNameDirector = @LastNameDirector
END
GO

CREATE PROCEDURE UpdateMovie
	@IDMovie int,
	@Title nvarchar(150),
	@PublishedDate nvarchar(60),
	@Description nvarchar(1000),
	@OriginalTitle nvarchar(150),
	@Duration nvarchar(50),
	@Genre nvarchar(300),
	@PicturePath nvarchar(100),
	@DirectorID int
AS
BEGIN
	UPDATE Movie SET
		Title = @Title,
		PublishedDate = @PublishedDate,
		Description = @Description,
		OriginalTitle = @OriginalTitle,
		Duration = @Duration,
		Genre = @Genre,
		PicturePath = @PicturePath,
		DirectorID = @DirectorID
	WHERE IDMovie = @IDMovie
END
GO


CREATE PROCEDURE EditActor
	@IDActor int,
	@FirstNameActor nvarchar(200),
	@LastNameActor nvarchar(200)
AS
BEGIN
	UPDATE Actor SET
		FirstNameActor = @FirstNameActor,
		LastNameActor = @LastNameActor
		WHERE IDActor = @IDActor
END 
GO

CREATE PROCEDURE EditDirector
	@IDDirector int,
	@FirstNameDirector nvarchar(200),
	@LastNameDirector nvarchar(200)
AS
BEGIN
	UPDATE Director SET
		FirstNameDirector = @FirstNameDirector,
		LastNameDirector = @LastNameDirector
		WHERE IDDirector = @IDDirector
END 
GO

CREATE PROCEDURE CreateActor
	@FirstNameActor nvarchar(200),
	@LastNameActor nvarchar(200),
	@IDActor int OUTPUT
AS
BEGIN
	IF EXISTS (SELECT *
		FROM Actor AS a
		WHERE a.FirstNameActor = @FirstNameActor AND a.LastNameActor = @LastNameActor
	)
		BEGIN
			SELECT @IDActor = a.IDActor
			FROM Actor AS a
			WHERE a.FirstNameActor = @FirstNameActor AND a.LastNameActor = @LastNameActor
		END
	ELSE
		BEGIN
			INSERT INTO Actor(FirstNameActor, LastNameActor)
			VALUES(@FirstNameActor, @LastNameActor)
			SET @IDActor = SCOPE_IDENTITY()
		END
END
GO

CREATE PROCEDURE CreateMovie
	@Title nvarchar(150),
	@PublishedDate nvarchar(60),
	@Description nvarchar(1000),
	@OriginalTitle nvarchar(150),
	@Duration nvarchar(50),
	@Genre nvarchar(300),
	@PicturePath nvarchar(100),
	@DirectorID int,
	@IDMovie int OUTPUT
AS
BEGIN
	INSERT INTO Movie(Title, PublishedDate, Description, OriginalTitle, Duration, Genre, PicturePath, DirectorID)
	VALUES(@Title, @PublishedDate, @Description, @OriginalTitle, @Duration, @Genre, @PicturePath, @DirectorID)
	SET @IDMovie = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE CreateMovieActor
	@MovieID int,
	@ActorID int,
	@IDMovieActor int OUTPUT
AS
BEGIN
	INSERT INTO MovieActor(MovieID, ActorID)
	VALUES(@MovieID, @ActorID)
	SET @IDMovieActor = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE GetMovies
AS
BEGIN
	SELECT m.IDMovie, m.Title, m.PublishedDate, m.Description, m.OriginalTitle, m.Duration, m.Genre,
	   m.PicturePath, m.DirectorID, d.FirstNameDirector, d.LastNameDirector, am.ActorID, a.FirstNameActor, a.LastNameActor
	FROM Movie AS m
	FULL JOIN DIRECTOR AS d
		ON m.DirectorID = d.IDDirector
	FULL JOIN MovieActor AS am
		ON am.MovieID = m.IDMovie
	FULL JOIN Actor AS a
		ON a.IDActor = am.ActorID
	WHERE m.IDMovie IS NOT NULL
END
GO

CREATE PROCEDURE GetMovie
	@IDMovie int
AS
BEGIN
	SELECT m.IDMovie, m.Title, m.PublishedDate, m.Description, m.OriginalTitle, m.Duration, m.Genre,
	   m.PicturePath, m.DirectorID, d.FirstNameDirector, d.LastNameDirector, am.ActorID, a.FirstNameActor, a.LastNameActor
	FROM Movie AS m
	FULL JOIN DIRECTOR AS d
		ON m.DirectorID = d.IDDirector
	FULL JOIN MovieActor AS am
		ON am.MovieID = m.IDMovie
	FULL JOIN Actor AS a
		ON a.IDActor = am.ActorID
	WHERE IDMovie = @IDMovie
END
GO

CREATE PROCEDURE DeleteMovies
AS
BEGIN
	DELETE FROM MovieActor
	DELETE FROM FavoriteActor
	DELETE FROM Actor
	DELETE FROM Movie
	DELETE FROM FavoriteDirector
	DELETE FROM Director
END
GO

CREATE PROCEDURE DeleteMovie
	@IDMovie int
AS
BEGIN
	DELETE FROM MovieActor WHERE MovieActor.MovieID = @IDMovie
	DELETE FROM Movie WHERE Movie.IDMovie = @IDMovie
END
GO

CREATE PROCEDURE DeleteActorFromMovieActor
	@ActorID int
AS
BEGIN
	DELETE FROM MovieActor WHERE MovieActor.ActorID = @ActorID
END
GO

CREATE PROCEDURE GetActors
AS
BEGIN
	SELECT *
	FROM Actor
END
GO

CREATE PROCEDURE GetDirectors
AS
BEGIN
	SELECT *
	FROM Director
END
GO

/*User table --------------------------------------------------------------------------------------*/

CREATE TABLE UserInfo
(
	IDUserInfo int CONSTRAINT PK_UserInfo PRIMARY KEY IDENTITY,
	Username nvarchar(200) CONSTRAINT UQ_UserInfo_Username UNIQUE NOT NULL,
	UserPassword nvarchar(64) NOT NULL,
	IsAdmin bit CONSTRAINT DF_UserInfo_IsAdmin DEFAULT(0)
)
GO

CREATE PROCEDURE CreateUser
	@Username nvarchar(200),
	@UserPassword nvarchar(64),
	@IDUserInfo int OUTPUT
AS
BEGIN
	INSERT INTO UserInfo(Username, UserPassword, IsAdmin)
	VALUES(@Username, @UserPassword, 0)
	SET @IDUserInfo = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE GetUser
	@Username nvarchar(200)
AS
BEGIN
	SELECT *
	FROM UserInfo
	WHERE Username = @Username
END
GO

CREATE PROCEDURE GetUsers
AS
BEGIN
	SELECT *
	FROM UserInfo
	WHERE IsAdmin = 0
END
GO

CREATE PROCEDURE MakeUserAdmin
	@IDUserInfo int
AS
BEGIN
	UPDATE UserInfo
	SET IsAdmin = 1
	WHERE IDUserInfo = @IDUserInfo
END
GO

INSERT INTO UserInfo(Username, UserPassword, IsAdmin)
VALUES('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 1)
GO

CREATE PROCEDURE DeleteUser
	@IDUserInfo int
AS
BEGIN
	DELETE
	FROM UserInfo
	WHERE IDUserInfo = @IDUserInfo
END
GO

