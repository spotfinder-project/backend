/*
 * This file is generated by jOOQ.
 */
package trash.tables;


import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.types.ULong;

import trash.Keys;
import trash.Spotfinder;
import trash.tables.records.FacilityApprovalHistoryRecord;


/**
 * 시설물 승인 이력 테이블
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class FacilityApprovalHistory extends TableImpl<FacilityApprovalHistoryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>spotfinder.FACILITY_APPROVAL_HISTORY</code>
     */
    public static final FacilityApprovalHistory FACILITY_APPROVAL_HISTORY = new FacilityApprovalHistory();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FacilityApprovalHistoryRecord> getRecordType() {
        return FacilityApprovalHistoryRecord.class;
    }

    /**
     * The column
     * <code>spotfinder.FACILITY_APPROVAL_HISTORY.FCLTY_APRV_HST_ID</code>. 시설물
     * 승인 이력 ID
     */
    public final TableField<FacilityApprovalHistoryRecord, ULong> FCLTY_APRV_HST_ID = createField(DSL.name("FCLTY_APRV_HST_ID"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "시설물 승인 이력 ID");

    /**
     * The column <code>spotfinder.FACILITY_APPROVAL_HISTORY.FCLTY_ID</code>.
     * 시설물 ID
     */
    public final TableField<FacilityApprovalHistoryRecord, ULong> FCLTY_ID = createField(DSL.name("FCLTY_ID"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "시설물 ID");

    /**
     * The column
     * <code>spotfinder.FACILITY_APPROVAL_HISTORY.FCLTY_APRV_STA</code>. 승인 상태
     */
    public final TableField<FacilityApprovalHistoryRecord, String> FCLTY_APRV_STA = createField(DSL.name("FCLTY_APRV_STA"), SQLDataType.CHAR(1).nullable(false), this, "승인 상태");

    /**
     * The column <code>spotfinder.FACILITY_APPROVAL_HISTORY.CRE_DTM</code>.
     */
    public final TableField<FacilityApprovalHistoryRecord, LocalDateTime> CRE_DTM = createField(DSL.name("CRE_DTM"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "");

    private FacilityApprovalHistory(Name alias, Table<FacilityApprovalHistoryRecord> aliased) {
        this(alias, aliased, null);
    }

    private FacilityApprovalHistory(Name alias, Table<FacilityApprovalHistoryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("시설물 승인 이력 테이블"), TableOptions.table());
    }

    /**
     * Create an aliased <code>spotfinder.FACILITY_APPROVAL_HISTORY</code> table
     * reference
     */
    public FacilityApprovalHistory(String alias) {
        this(DSL.name(alias), FACILITY_APPROVAL_HISTORY);
    }

    /**
     * Create an aliased <code>spotfinder.FACILITY_APPROVAL_HISTORY</code> table
     * reference
     */
    public FacilityApprovalHistory(Name alias) {
        this(alias, FACILITY_APPROVAL_HISTORY);
    }

    /**
     * Create a <code>spotfinder.FACILITY_APPROVAL_HISTORY</code> table
     * reference
     */
    public FacilityApprovalHistory() {
        this(DSL.name("FACILITY_APPROVAL_HISTORY"), null);
    }

    public <O extends Record> FacilityApprovalHistory(Table<O> child, ForeignKey<O, FacilityApprovalHistoryRecord> key) {
        super(child, key, FACILITY_APPROVAL_HISTORY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Spotfinder.SPOTFINDER;
    }

    @Override
    public Identity<FacilityApprovalHistoryRecord, ULong> getIdentity() {
        return (Identity<FacilityApprovalHistoryRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<FacilityApprovalHistoryRecord> getPrimaryKey() {
        return Keys.KEY_FACILITY_APPROVAL_HISTORY_PRIMARY;
    }

    @Override
    public FacilityApprovalHistory as(String alias) {
        return new FacilityApprovalHistory(DSL.name(alias), this);
    }

    @Override
    public FacilityApprovalHistory as(Name alias) {
        return new FacilityApprovalHistory(alias, this);
    }

    @Override
    public FacilityApprovalHistory as(Table<?> alias) {
        return new FacilityApprovalHistory(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public FacilityApprovalHistory rename(String name) {
        return new FacilityApprovalHistory(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public FacilityApprovalHistory rename(Name name) {
        return new FacilityApprovalHistory(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public FacilityApprovalHistory rename(Table<?> name) {
        return new FacilityApprovalHistory(name.getQualifiedName(), null);
    }
}