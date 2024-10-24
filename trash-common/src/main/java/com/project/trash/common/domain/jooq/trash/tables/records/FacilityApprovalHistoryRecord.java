/*
 * This file is generated by jOOQ.
 */
package trash.tables.records;


import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;

import trash.tables.FacilityApprovalHistory;


/**
 * 시설물 승인 이력 테이블
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class FacilityApprovalHistoryRecord extends UpdatableRecordImpl<FacilityApprovalHistoryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for
     * <code>spotfinder.FACILITY_APPROVAL_HISTORY.FCLTY_APRV_HST_ID</code>. 시설물
     * 승인 이력 ID
     */
    public FacilityApprovalHistoryRecord setFcltyAprvHstId(ULong value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for
     * <code>spotfinder.FACILITY_APPROVAL_HISTORY.FCLTY_APRV_HST_ID</code>. 시설물
     * 승인 이력 ID
     */
    public ULong getFcltyAprvHstId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>spotfinder.FACILITY_APPROVAL_HISTORY.FCLTY_ID</code>.
     * 시설물 ID
     */
    public FacilityApprovalHistoryRecord setFcltyId(ULong value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.FACILITY_APPROVAL_HISTORY.FCLTY_ID</code>.
     * 시설물 ID
     */
    public ULong getFcltyId() {
        return (ULong) get(1);
    }

    /**
     * Setter for
     * <code>spotfinder.FACILITY_APPROVAL_HISTORY.FCLTY_APRV_STA</code>. 승인 상태
     */
    public FacilityApprovalHistoryRecord setFcltyAprvSta(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for
     * <code>spotfinder.FACILITY_APPROVAL_HISTORY.FCLTY_APRV_STA</code>. 승인 상태
     */
    public String getFcltyAprvSta() {
        return (String) get(2);
    }

    /**
     * Setter for <code>spotfinder.FACILITY_APPROVAL_HISTORY.CRE_DTM</code>.
     */
    public FacilityApprovalHistoryRecord setCreDtm(LocalDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.FACILITY_APPROVAL_HISTORY.CRE_DTM</code>.
     */
    public LocalDateTime getCreDtm() {
        return (LocalDateTime) get(3);
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
     * Create a detached FacilityApprovalHistoryRecord
     */
    public FacilityApprovalHistoryRecord() {
        super(FacilityApprovalHistory.FACILITY_APPROVAL_HISTORY);
    }

    /**
     * Create a detached, initialised FacilityApprovalHistoryRecord
     */
    public FacilityApprovalHistoryRecord(ULong fcltyAprvHstId, ULong fcltyId, String fcltyAprvSta, LocalDateTime creDtm) {
        super(FacilityApprovalHistory.FACILITY_APPROVAL_HISTORY);

        setFcltyAprvHstId(fcltyAprvHstId);
        setFcltyId(fcltyId);
        setFcltyAprvSta(fcltyAprvSta);
        setCreDtm(creDtm);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised FacilityApprovalHistoryRecord
     */
    public FacilityApprovalHistoryRecord(trash.tables.pojos.FacilityApprovalHistory value) {
        super(FacilityApprovalHistory.FACILITY_APPROVAL_HISTORY);

        if (value != null) {
            setFcltyAprvHstId(value.getFcltyAprvHstId());
            setFcltyId(value.getFcltyId());
            setFcltyAprvSta(value.getFcltyAprvSta());
            setCreDtm(value.getCreDtm());
            resetChangedOnNotNull();
        }
    }
}
