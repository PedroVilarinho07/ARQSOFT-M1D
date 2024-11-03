CREATE TABLE book
(
    book_id       BIGINT       NOT NULL,
    isbn          VARCHAR(13)  NOT NULL,
    title         VARCHAR(128) NOT NULL,
    genre         VARCHAR(255) NULL,
    `description` VARCHAR(4096) NULL,
    photo_uri     VARCHAR(1024) NULL,
    CONSTRAINT pk_book PRIMARY KEY (book_id)
);

CREATE TABLE book_authors
(
    author_id VARCHAR(255) NOT NULL,
    book_id   BIGINT       NOT NULL
);

ALTER TABLE book
    ADD CONSTRAINT uc_book_isbn UNIQUE (isbn);

ALTER TABLE book_authors
    ADD CONSTRAINT fk_booaut_on_author_data_model FOREIGN KEY (author_id) REFERENCES author (author_number);

ALTER TABLE book_authors
    ADD CONSTRAINT fk_booaut_on_book_data_model FOREIGN KEY (book_id) REFERENCES book (book_id);
ALTER TABLE user_authorities
    DROP CONSTRAINT FKg3ms6twsd5wemq30u2bvhj7if
GO

ALTER TABLE book
    ADD genre varchar(255)
GO

DROP TABLE user_authorities
GO

DECLARE @sql [nvarchar](MAX)
SELECT @sql = N'ALTER TABLE book DROP CONSTRAINT ' + QUOTENAME([df].[name])
FROM [sys].[columns] AS [c]
         INNER JOIN [sys].[default_constraints] AS [df] ON [df].[object_id] = [c].[default_object_id]
WHERE [c].[object_id] = OBJECT_ID(N'book')
  AND [c].[name] = N'genre_id'
EXEC sp_executesql @sql
GO

ALTER TABLE book
    DROP COLUMN genre_id
GO

DROP SEQUENCE forbidden_name_seq
GO

DROP SEQUENCE genre_seq
GO

DROP SEQUENCE photo_seq
GO

DROP SEQUENCE reader_details_seq
GO

DROP SEQUENCE t_user_seq
GO