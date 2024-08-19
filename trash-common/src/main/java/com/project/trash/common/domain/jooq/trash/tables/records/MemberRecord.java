/*
 * This file is generated by jOOQ.
 */
package trash.tables.records;


import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;

import trash.tables.Member;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class MemberRecord extends UpdatableRecordImpl<MemberRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>trash.MEMBER.MBR_SEQ</code>. 회원 일련번호
     */
    public MemberRecord setMbrSeq(ULong value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>trash.MEMBER.MBR_SEQ</code>. 회원 일련번호
     */
    public ULong getMbrSeq() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>trash.MEMBER.MBR_EMAIL</code>. 이메일
     */
    public MemberRecord setMbrEmail(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>trash.MEMBER.MBR_EMAIL</code>. 이메일
     */
    public String getMbrEmail() {
        return (String) get(1);
    }

    /**
     * Setter for <code>trash.MEMBER.MBR_NM</code>. 이름
     */
    public MemberRecord setMbrNm(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>trash.MEMBER.MBR_NM</code>. 이름
     */
    public String getMbrNm() {
        return (String) get(2);
    }

    /**
     * Setter for <code>trash.MEMBER.MBR_NCK_NM</code>. 닉네임
     */
    public MemberRecord setMbrNckNm(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>trash.MEMBER.MBR_NCK_NM</code>. 닉네임
     */
    public String getMbrNckNm() {
        return (String) get(3);
    }

    /**
     * Setter for <code>trash.MEMBER.MBR_GNDR</code>. 성별
     */
    public MemberRecord setMbrGndr(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>trash.MEMBER.MBR_GNDR</code>. 성별
     */
    public String getMbrGndr() {
        return (String) get(4);
    }

    /**
     * Setter for <code>trash.MEMBER.MBR_BRDT</code>. 생년월일
     */
    public MemberRecord setMbrBrdt(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>trash.MEMBER.MBR_BRDT</code>. 생년월일
     */
    public String getMbrBrdt() {
        return (String) get(5);
    }

    /**
     * Setter for <code>trash.MEMBER.MBR_SCL_ID</code>. 소셜 ID
     */
    public MemberRecord setMbrSclId(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>trash.MEMBER.MBR_SCL_ID</code>. 소셜 ID
     */
    public String getMbrSclId() {
        return (String) get(6);
    }

    /**
     * Setter for <code>trash.MEMBER.MBR_SCL_TYP</code>. 소셜 타입
     */
    public MemberRecord setMbrSclTyp(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>trash.MEMBER.MBR_SCL_TYP</code>. 소셜 타입
     */
    public String getMbrSclTyp() {
        return (String) get(7);
    }

    /**
     * Setter for <code>trash.MEMBER.MBR_VLD_YN</code>. 유효여부
     */
    public MemberRecord setMbrVldYn(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>trash.MEMBER.MBR_VLD_YN</code>. 유효여부
     */
    public String getMbrVldYn() {
        return (String) get(8);
    }

    /**
     * Setter for <code>trash.MEMBER.CRE_DTM</code>. 등록일시
     */
    public MemberRecord setCreDtm(LocalDateTime value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>trash.MEMBER.CRE_DTM</code>. 등록일시
     */
    public LocalDateTime getCreDtm() {
        return (LocalDateTime) get(9);
    }

    /**
     * Setter for <code>trash.MEMBER.UPD_DTM</code>. 수정일시
     */
    public MemberRecord setUpdDtm(LocalDateTime value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>trash.MEMBER.UPD_DTM</code>. 수정일시
     */
    public LocalDateTime getUpdDtm() {
        return (LocalDateTime) get(10);
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
     * Create a detached MemberRecord
     */
    public MemberRecord() {
        super(Member.MEMBER);
    }

    /**
     * Create a detached, initialised MemberRecord
     */
    public MemberRecord(ULong mbrSeq, String mbrEmail, String mbrNm, String mbrNckNm, String mbrGndr, String mbrBrdt, String mbrSclId, String mbrSclTyp, String mbrVldYn, LocalDateTime creDtm, LocalDateTime updDtm) {
        super(Member.MEMBER);

        setMbrSeq(mbrSeq);
        setMbrEmail(mbrEmail);
        setMbrNm(mbrNm);
        setMbrNckNm(mbrNckNm);
        setMbrGndr(mbrGndr);
        setMbrBrdt(mbrBrdt);
        setMbrSclId(mbrSclId);
        setMbrSclTyp(mbrSclTyp);
        setMbrVldYn(mbrVldYn);
        setCreDtm(creDtm);
        setUpdDtm(updDtm);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised MemberRecord
     */
    public MemberRecord(trash.tables.pojos.Member value) {
        super(Member.MEMBER);

        if (value != null) {
            setMbrSeq(value.getMbrSeq());
            setMbrEmail(value.getMbrEmail());
            setMbrNm(value.getMbrNm());
            setMbrNckNm(value.getMbrNckNm());
            setMbrGndr(value.getMbrGndr());
            setMbrBrdt(value.getMbrBrdt());
            setMbrSclId(value.getMbrSclId());
            setMbrSclTyp(value.getMbrSclTyp());
            setMbrVldYn(value.getMbrVldYn());
            setCreDtm(value.getCreDtm());
            setUpdDtm(value.getUpdDtm());
            resetChangedOnNotNull();
        }
    }
}
