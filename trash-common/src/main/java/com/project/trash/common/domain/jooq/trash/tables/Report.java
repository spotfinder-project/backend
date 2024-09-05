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
import trash.tables.records.ReportRecord;


/**
 * 신고 테이블
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Report extends TableImpl<ReportRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>spotfinder.REPORT</code>
     */
    public static final Report REPORT = new Report();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ReportRecord> getRecordType() {
        return ReportRecord.class;
    }

    /**
     * The column <code>spotfinder.REPORT.RPT_SEQ</code>. 신고 일련번호
     */
    public final TableField<ReportRecord, ULong> RPT_SEQ = createField(DSL.name("RPT_SEQ"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "신고 일련번호");

    /**
     * The column <code>spotfinder.REPORT.FCLTY_ID</code>. 시설물 ID
     */
    public final TableField<ReportRecord, String> FCLTY_ID = createField(DSL.name("FCLTY_ID"), SQLDataType.VARCHAR(30).nullable(false), this, "시설물 ID");

    /**
     * The column <code>spotfinder.REPORT.MBR_SEQ</code>. 회원 일련번호
     */
    public final TableField<ReportRecord, ULong> MBR_SEQ = createField(DSL.name("MBR_SEQ"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "회원 일련번호");

    /**
     * The column <code>spotfinder.REPORT.RPT_CTT</code>. 신고 내용
     */
    public final TableField<ReportRecord, String> RPT_CTT = createField(DSL.name("RPT_CTT"), SQLDataType.VARCHAR(255).nullable(false), this, "신고 내용");

    /**
     * The column <code>spotfinder.REPORT.RPT_ANS</code>. 답변
     */
    public final TableField<ReportRecord, String> RPT_ANS = createField(DSL.name("RPT_ANS"), SQLDataType.VARCHAR(255), this, "답변");

    /**
     * The column <code>spotfinder.REPORT.RPT_STT_YN</code>. 신고 처리상태
     */
    public final TableField<ReportRecord, String> RPT_STT_YN = createField(DSL.name("RPT_STT_YN"), SQLDataType.CHAR(1).nullable(false), this, "신고 처리상태");

    /**
     * The column <code>spotfinder.REPORT.CRE_DTM</code>. 등록일시
     */
    public final TableField<ReportRecord, LocalDateTime> CRE_DTM = createField(DSL.name("CRE_DTM"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "등록일시");

    /**
     * The column <code>spotfinder.REPORT.UPD_DTM</code>. 수정일시
     */
    public final TableField<ReportRecord, LocalDateTime> UPD_DTM = createField(DSL.name("UPD_DTM"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "수정일시");

    private Report(Name alias, Table<ReportRecord> aliased) {
        this(alias, aliased, null);
    }

    private Report(Name alias, Table<ReportRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("신고 테이블"), TableOptions.table());
    }

    /**
     * Create an aliased <code>spotfinder.REPORT</code> table reference
     */
    public Report(String alias) {
        this(DSL.name(alias), REPORT);
    }

    /**
     * Create an aliased <code>spotfinder.REPORT</code> table reference
     */
    public Report(Name alias) {
        this(alias, REPORT);
    }

    /**
     * Create a <code>spotfinder.REPORT</code> table reference
     */
    public Report() {
        this(DSL.name("REPORT"), null);
    }

    public <O extends Record> Report(Table<O> child, ForeignKey<O, ReportRecord> key) {
        super(child, key, REPORT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Spotfinder.SPOTFINDER;
    }

    @Override
    public Identity<ReportRecord, ULong> getIdentity() {
        return (Identity<ReportRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<ReportRecord> getPrimaryKey() {
        return Keys.KEY_REPORT_PRIMARY;
    }

    @Override
    public Report as(String alias) {
        return new Report(DSL.name(alias), this);
    }

    @Override
    public Report as(Name alias) {
        return new Report(alias, this);
    }

    @Override
    public Report as(Table<?> alias) {
        return new Report(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Report rename(String name) {
        return new Report(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Report rename(Name name) {
        return new Report(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Report rename(Table<?> name) {
        return new Report(name.getQualifiedName(), null);
    }
}