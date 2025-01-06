CREATE DATABASE IF NOT EXISTS `spotfinder`;

USE spotfinder;

create table if not exists ADMIN
(
    ADM_ID  varchar(20)  not null comment 'ID'
    primary key,
    ADM_PWD varchar(100) not null comment '비밀번호'
)
    comment '관리자 테이블';

create table if not exists FACILITY
(
    FCLTY_ID            bigint unsigned auto_increment comment '시설물 ID'
    primary key,
    FCLTY_NM            varchar(50)     not null comment '시설물명',
    FCLTY_TYP           char            not null comment '시설물 종류',
    FCLTY_LCTN          varchar(100)    not null comment '위치',
    FCLTY_DTL_LCTN      varchar(100)    null comment '상세 위치',
    FCLTY_LTTD          decimal(40, 30) not null comment '위도',
    FCLTY_LNGT          decimal(40, 30) not null comment '경도',
    FCLTY_INFO          varchar(255)    null comment '정보',
    FCLTY_DPR_NM        varchar(100)    null comment '관리 부서명',
    FCLTY_DPR_TLPH_NMBR varchar(100)    null comment '관리 부서 전화번호',
    FCLTY_APRV_STA      char            not null comment '승인 상태',
    MBR_ID              varchar(50)     null comment '회원 ID',
    CRE_DTM             datetime        not null comment '등록일시',
    UPD_DTM             datetime        not null comment '수정일시'
)
    comment '시설물 테이블';

create table if not exists FACILITY_APPROVAL_HISTORY
(
    FCLTY_APRV_HST_ID bigint unsigned auto_increment comment '시설물 승인 이력 ID'
    primary key,
    FCLTY_ID          bigint unsigned not null comment '시설물 ID',
    FCLTY_APRV_STA    char            not null comment '승인 상태',
    CRE_DTM           datetime        not null
)
    comment '시설물 승인 이력 테이블';

create table if not exists FACILITY_IMAGE
(
    FCLTY_IMG_ID   bigint unsigned auto_increment comment '이미지 ID'
    primary key,
    FCLTY_IMG_PATH varchar(255)    not null comment '이미지 경로',
    FCLTY_ID       bigint unsigned null comment '시설물 ID',
    CRE_DTM        datetime        not null comment '등록일시'
)
    comment '시설물 이미지 테이블';

create table if not exists MEMBER
(
    MBR_ID      bigint unsigned auto_increment comment '회원 ID'
    primary key,
    MBR_EMAIL   varchar(50)      not null comment '이메일',
    MBR_NCK_NM  varchar(20)      null comment '닉네임',
    MBR_GNDR    char             null comment '성별',
    MBR_SCL_ID  varchar(50)      not null comment '소셜 ID',
    MBR_SCL_TYP char             not null comment '소셜 타입',
    MBR_ARGM_YN char default 'N' not null comment '약관 동의 여부',
    MBR_VLD_YN  char             not null comment '유효여부',
    CRE_DTM     datetime         not null comment '등록일시',
    UPD_DTM     datetime         not null comment '수정일시'
);

create table if not exists NOTICE
(
    NTC_ID     bigint unsigned auto_increment comment '공지 ID'
    primary key,
    NTC_TTL    varchar(50)  not null comment '공지 제목',
    NTC_CTT    varchar(255) not null comment '공지 내용',
    NTC_VLD_YN char         not null comment '유효여부',
    CRE_DTM    datetime     not null comment '등록일시',
    UPD_DTM    datetime     not null comment '수정일시'
);

create table if not exists REPORT
(
    RPT_ID     bigint unsigned auto_increment comment '신고 ID'
    primary key,
    FCLTY_ID   bigint unsigned not null comment '시설물 ID',
    MBR_ID     bigint unsigned not null comment '회원 ID',
    RPT_CTT    varchar(255)    not null comment '신고 내용',
    RPT_ANS    varchar(255)    null comment '답변',
    RPT_STT_YN char            not null comment '신고 처리상태',
    CRE_DTM    datetime        not null comment '등록일시',
    UPD_DTM    datetime        not null comment '수정일시'
)
    comment '신고 테이블';

create table if not exists REVIEW
(
    RVW_ID   bigint unsigned auto_increment comment '리뷰 ID'
    primary key,
    RVW_CTT  varchar(100)    not null comment '리뷰 내용',
    MBR_ID   bigint unsigned not null comment '회원 ID',
    FCLTY_ID bigint unsigned not null comment '시설물 ID',
    CRE_DTM  datetime        not null comment '등록일시',
    UPD_DTM  datetime        not null comment '수정일시'
)
    comment '리뷰 테이블';

create table if not exists TOKEN
(
    MBR_ID   varchar(50)  not null comment '회원 ID'
    primary key,
    TKN_ACS  varchar(255) not null comment '엑세스 토큰',
    TKN_RFRS varchar(255) not null comment '리프레시 토큰',
    CRE_DTM  datetime     not null comment '등록일시',
    UPD_DTM  datetime     not null comment '수정일시'
);

