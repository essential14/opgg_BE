create sequence BOARD_SEQ
/

create sequence COMMENTS_SEQ
/

create sequence FILE_SEQ
/

create sequence BOARD_BNO_SEQ
/

create table MEMBER
(
    ID       VARCHAR2(50)  not null
        primary key,
    NAME     VARCHAR2(30)  not null,
    BIRTHDAY DATE          not null,
    PASSWORD VARCHAR2(250) not null
)
    /

create table FILES
(
    FNO         NUMBER        not null
        primary key,
    STORED_FILE VARCHAR2(200) not null,
    ORG_FILE    VARCHAR2(200) not null,
    GROUP_FILE  VARCHAR2(64)
)
    /

create trigger FILE_BEFORE_INSERT
    before insert
    on FILES
    for each row
    BEGIN
        :new.fno := file_seq.nextval;
END;
/

create table COMMENTS
(
    CNO          NUMBER               not null
        primary key,
    BNO          NUMBER               not null,
    PARENT_CNO   NUMBER,
    CONTENT      VARCHAR2(4000)       not null,
    ID           VARCHAR2(30),
    DEPTH        NUMBER               not null,
    CREATED_DATE DATE default SYSDATE not null,
    ORDER_CNO    NUMBER,
    GROUP_CNO    NUMBER
)
    /

create trigger TRG_COMMENTS_AUTOINCREMENT
    before insert
    on COMMENTS
    for each row
BEGIN
    SELECT comments_seq.NEXTVAL
    INTO :new.cno
    FROM dual;
END;
/

create table BOARD
(
    BNO          NUMBER not null
        primary key,
    TITLE        VARCHAR2(255),
    ID           VARCHAR2(100),
    CONTENT      CLOB,
    VIEWCOUNT    NUMBER default 0,
    CREATED_DATE DATE   default sysdate,
    UPDATED_DATE DATE   default sysdate,
    GROUP_FILE   NUMBER,
    FNO          NUMBER
)
    /

create trigger BOARD_BNO_TRIGGER
    before insert
    on BOARD
    for each row
    BEGIN
        :new.bno := board_bno_seq.NEXTVAL;
END;
/