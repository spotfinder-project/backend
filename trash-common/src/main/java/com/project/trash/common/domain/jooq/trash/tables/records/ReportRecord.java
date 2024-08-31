/*
 * This file is generated by jOOQ.
 */
package trash.tables.records;


import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;

import trash.tables.Report;


/**
 * 신고 테이블
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class ReportRecord extends UpdatableRecordImpl<ReportRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>spotfinder.REPORT.RPT_SEQ</code>. 신고 일련번호
     */
    public ReportRecord setRptSeq(ULong value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.REPORT.RPT_SEQ</code>. 신고 일련번호
     */
    public ULong getRptSeq() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>spotfinder.REPORT.FCLTY_ID</code>. 시설물 ID
     */
    public ReportRecord setFcltyId(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.REPORT.FCLTY_ID</code>. 시설물 ID
     */
    public String getFcltyId() {
        return (String) get(1);
    }

    /**
     * Setter for <code>spotfinder.REPORT.MBR_SEQ</code>. 회원 일련번호
     */
    public ReportRecord setMbrSeq(ULong value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.REPORT.MBR_SEQ</code>. 회원 일련번호
     */
    public ULong getMbrSeq() {
        return (ULong) get(2);
    }

    /**
     * Setter for <code>spotfinder.REPORT.RPT_CTT</code>. 신고 내용
     */
    public ReportRecord setRptCtt(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.REPORT.RPT_CTT</code>. 신고 내용
     */
    public String getRptCtt() {
        return (String) get(3);
    }

    /**
     * Setter for <code>spotfinder.REPORT.RPT_ANS</code>. 답변
     */
    public ReportRecord setRptAns(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.REPORT.RPT_ANS</code>. 답변
     */
    public String getRptAns() {
        return (String) get(4);
    }

    /**
     * Setter for <code>spotfinder.REPORT.CRE_DTM</code>. 등록일시
     */
    public ReportRecord setCreDtm(LocalDateTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.REPORT.CRE_DTM</code>. 등록일시
     */
    public LocalDateTime getCreDtm() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>spotfinder.REPORT.UPD_DTM</code>. 수정일시
     */
    public ReportRecord setUpdDtm(LocalDateTime value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.REPORT.UPD_DTM</code>. 수정일시
     */
    public LocalDateTime getUpdDtm() {
        return (LocalDateTime) get(6);
    }

    /**
     * Setter for <code>spotfinder.REPORT.RPT_STT_YN</code>. 신고 처리상태
     */
    public ReportRecord setRptSttYn(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.REPORT.RPT_STT_YN</code>. 신고 처리상태
     */
    public String getRptSttYn() {
        return (String) get(7);
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
     * Create a detached ReportRecord
     */
    public ReportRecord() {
        super(Report.REPORT);
    }

    /**
     * Create a detached, initialised ReportRecord
     */
    public ReportRecord(ULong rptSeq, String fcltyId, ULong mbrSeq, String rptCtt, String rptAns, LocalDateTime creDtm, LocalDateTime updDtm, String rptSttYn) {
        super(Report.REPORT);

        setRptSeq(rptSeq);
        setFcltyId(fcltyId);
        setMbrSeq(mbrSeq);
        setRptCtt(rptCtt);
        setRptAns(rptAns);
        setCreDtm(creDtm);
        setUpdDtm(updDtm);
        setRptSttYn(rptSttYn);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised ReportRecord
     */
    public ReportRecord(trash.tables.pojos.Report value) {
        super(Report.REPORT);

        if (value != null) {
            setRptSeq(value.getRptSeq());
            setFcltyId(value.getFcltyId());
            setMbrSeq(value.getMbrSeq());
            setRptCtt(value.getRptCtt());
            setRptAns(value.getRptAns());
            setCreDtm(value.getCreDtm());
            setUpdDtm(value.getUpdDtm());
            setRptSttYn(value.getRptSttYn());
            resetChangedOnNotNull();
        }
    }
}
