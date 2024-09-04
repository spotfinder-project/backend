/*
 * This file is generated by jOOQ.
 */
package trash.tables.records;


import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;

import trash.tables.Token;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class TokenRecord extends UpdatableRecordImpl<TokenRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>spotfinder.TOKEN.MBR_ID</code>. 회원 ID
     */
    public TokenRecord setMbrId(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.TOKEN.MBR_ID</code>. 회원 ID
     */
    public String getMbrId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>spotfinder.TOKEN.TKN_ACS</code>. 엑세스 토큰
     */
    public TokenRecord setTknAcs(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.TOKEN.TKN_ACS</code>. 엑세스 토큰
     */
    public String getTknAcs() {
        return (String) get(1);
    }

    /**
     * Setter for <code>spotfinder.TOKEN.TKN_RFRS</code>. 리프레시 토큰
     */
    public TokenRecord setTknRfrs(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.TOKEN.TKN_RFRS</code>. 리프레시 토큰
     */
    public String getTknRfrs() {
        return (String) get(2);
    }

    /**
     * Setter for <code>spotfinder.TOKEN.CRE_DTM</code>. 등록일시
     */
    public TokenRecord setCreDtm(LocalDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.TOKEN.CRE_DTM</code>. 등록일시
     */
    public LocalDateTime getCreDtm() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>spotfinder.TOKEN.UPD_DTM</code>. 수정일시
     */
    public TokenRecord setUpdDtm(LocalDateTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>spotfinder.TOKEN.UPD_DTM</code>. 수정일시
     */
    public LocalDateTime getUpdDtm() {
        return (LocalDateTime) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TokenRecord
     */
    public TokenRecord() {
        super(Token.TOKEN);
    }

    /**
     * Create a detached, initialised TokenRecord
     */
    public TokenRecord(String mbrId, String tknAcs, String tknRfrs, LocalDateTime creDtm, LocalDateTime updDtm) {
        super(Token.TOKEN);

        setMbrId(mbrId);
        setTknAcs(tknAcs);
        setTknRfrs(tknRfrs);
        setCreDtm(creDtm);
        setUpdDtm(updDtm);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised TokenRecord
     */
    public TokenRecord(trash.tables.pojos.Token value) {
        super(Token.TOKEN);

        if (value != null) {
            setMbrId(value.getMbrId());
            setTknAcs(value.getTknAcs());
            setTknRfrs(value.getTknRfrs());
            setCreDtm(value.getCreDtm());
            setUpdDtm(value.getUpdDtm());
            resetChangedOnNotNull();
        }
    }
}
