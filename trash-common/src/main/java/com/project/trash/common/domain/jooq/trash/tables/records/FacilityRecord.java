/*
 * This file is generated by jOOQ.
 */
package trash.tables.records;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;

import trash.tables.Facility;


/**
 * 시설물 테이블
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class FacilityRecord extends UpdatableRecordImpl<FacilityRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>spotfinder.FACILITY.FCLTY_SEQ</code>. 시설물 일련번호
     */
    public FacilityRecord setFcltySeq(ULong value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.FACILITY.FCLTY_SEQ</code>. 시설물 일련번호
     */
    public ULong getFcltySeq() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>spotfinder.FACILITY.FCLTY_NM</code>. 시설물명
     */
    public FacilityRecord setFcltyNm(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.FACILITY.FCLTY_NM</code>. 시설물명
     */
    public String getFcltyNm() {
        return (String) get(1);
    }

    /**
     * Setter for <code>spotfinder.FACILITY.FCLTY_TYP</code>. 시설물 종류
     */
    public FacilityRecord setFcltyTyp(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.FACILITY.FCLTY_TYP</code>. 시설물 종류
     */
    public String getFcltyTyp() {
        return (String) get(2);
    }

    /**
     * Setter for <code>spotfinder.FACILITY.FCLTY_LCTN</code>. 위치
     */
    public FacilityRecord setFcltyLctn(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.FACILITY.FCLTY_LCTN</code>. 위치
     */
    public String getFcltyLctn() {
        return (String) get(3);
    }

    /**
     * Setter for <code>spotfinder.FACILITY.FCLTY_DTL_LCTN</code>. 상세 위치
     */
    public FacilityRecord setFcltyDtlLctn(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.FACILITY.FCLTY_DTL_LCTN</code>. 상세 위치
     */
    public String getFcltyDtlLctn() {
        return (String) get(4);
    }

    /**
     * Setter for <code>spotfinder.FACILITY.FCLTY_LTTD</code>. 위도
     */
    public FacilityRecord setFcltyLttd(BigDecimal value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.FACILITY.FCLTY_LTTD</code>. 위도
     */
    public BigDecimal getFcltyLttd() {
        return (BigDecimal) get(5);
    }

    /**
     * Setter for <code>spotfinder.FACILITY.FCLTY_LNGT</code>. 경도
     */
    public FacilityRecord setFcltyLngt(BigDecimal value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.FACILITY.FCLTY_LNGT</code>. 경도
     */
    public BigDecimal getFcltyLngt() {
        return (BigDecimal) get(6);
    }

    /**
     * Setter for <code>spotfinder.FACILITY.FCLTY_INFO</code>. 정보
     */
    public FacilityRecord setFcltyInfo(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.FACILITY.FCLTY_INFO</code>. 정보
     */
    public String getFcltyInfo() {
        return (String) get(7);
    }

    /**
     * Setter for <code>spotfinder.FACILITY.FCLTY_DPR_NM</code>. 관리 부서명
     */
    public FacilityRecord setFcltyDprNm(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.FACILITY.FCLTY_DPR_NM</code>. 관리 부서명
     */
    public String getFcltyDprNm() {
        return (String) get(8);
    }

    /**
     * Setter for <code>spotfinder.FACILITY.FCLTY_DPR_TLPH_NMBR</code>. 관리 부서
     * 전화번호
     */
    public FacilityRecord setFcltyDprTlphNmbr(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.FACILITY.FCLTY_DPR_TLPH_NMBR</code>. 관리 부서
     * 전화번호
     */
    public String getFcltyDprTlphNmbr() {
        return (String) get(9);
    }

    /**
     * Setter for <code>spotfinder.FACILITY.FCLTY_APRV_YN</code>. 승인여부
     */
    public FacilityRecord setFcltyAprvYn(String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.FACILITY.FCLTY_APRV_YN</code>. 승인여부
     */
    public String getFcltyAprvYn() {
        return (String) get(10);
    }

    /**
     * Setter for <code>spotfinder.FACILITY.MBR_SEQ</code>. 회원 일련번호
     */
    public FacilityRecord setMbrSeq(ULong value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.FACILITY.MBR_SEQ</code>. 회원 일련번호
     */
    public ULong getMbrSeq() {
        return (ULong) get(11);
    }

    /**
     * Setter for <code>spotfinder.FACILITY.CRE_DTM</code>. 등록일시
     */
    public FacilityRecord setCreDtm(LocalDateTime value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.FACILITY.CRE_DTM</code>. 등록일시
     */
    public LocalDateTime getCreDtm() {
        return (LocalDateTime) get(12);
    }

    /**
     * Setter for <code>spotfinder.FACILITY.UPD_DTM</code>. 수정일시
     */
    public FacilityRecord setUpdDtm(LocalDateTime value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.FACILITY.UPD_DTM</code>. 수정일시
     */
    public LocalDateTime getUpdDtm() {
        return (LocalDateTime) get(13);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FacilityRecord
     */
    public FacilityRecord() {
        super(Facility.FACILITY);
    }

    /**
     * Create a detached, initialised FacilityRecord
     */
    public FacilityRecord(ULong fcltySeq, String fcltyNm, String fcltyTyp, String fcltyLctn, String fcltyDtlLctn, BigDecimal fcltyLttd, BigDecimal fcltyLngt, String fcltyInfo, String fcltyDprNm, String fcltyDprTlphNmbr, String fcltyAprvYn, ULong mbrSeq, LocalDateTime creDtm, LocalDateTime updDtm) {
        super(Facility.FACILITY);

        setFcltySeq(fcltySeq);
        setFcltyNm(fcltyNm);
        setFcltyTyp(fcltyTyp);
        setFcltyLctn(fcltyLctn);
        setFcltyDtlLctn(fcltyDtlLctn);
        setFcltyLttd(fcltyLttd);
        setFcltyLngt(fcltyLngt);
        setFcltyInfo(fcltyInfo);
        setFcltyDprNm(fcltyDprNm);
        setFcltyDprTlphNmbr(fcltyDprTlphNmbr);
        setFcltyAprvYn(fcltyAprvYn);
        setMbrSeq(mbrSeq);
        setCreDtm(creDtm);
        setUpdDtm(updDtm);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised FacilityRecord
     */
    public FacilityRecord(trash.tables.pojos.Facility value) {
        super(Facility.FACILITY);

        if (value != null) {
            setFcltySeq(value.getFcltySeq());
            setFcltyNm(value.getFcltyNm());
            setFcltyTyp(value.getFcltyTyp());
            setFcltyLctn(value.getFcltyLctn());
            setFcltyDtlLctn(value.getFcltyDtlLctn());
            setFcltyLttd(value.getFcltyLttd());
            setFcltyLngt(value.getFcltyLngt());
            setFcltyInfo(value.getFcltyInfo());
            setFcltyDprNm(value.getFcltyDprNm());
            setFcltyDprTlphNmbr(value.getFcltyDprTlphNmbr());
            setFcltyAprvYn(value.getFcltyAprvYn());
            setMbrSeq(value.getMbrSeq());
            setCreDtm(value.getCreDtm());
            setUpdDtm(value.getUpdDtm());
            resetChangedOnNotNull();
        }
    }
}