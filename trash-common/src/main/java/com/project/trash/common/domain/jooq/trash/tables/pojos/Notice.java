/*
 * This file is generated by jOOQ.
 */
package trash.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;

import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    private final ULong ntcSeq;
    private final String ntcTtl;
    private final String ntcCtt;
    private final String ntcVldYn;
    private final LocalDateTime creDtm;
    private final LocalDateTime updDtm;

    public Notice(Notice value) {
        this.ntcSeq = value.ntcSeq;
        this.ntcTtl = value.ntcTtl;
        this.ntcCtt = value.ntcCtt;
        this.ntcVldYn = value.ntcVldYn;
        this.creDtm = value.creDtm;
        this.updDtm = value.updDtm;
    }

    public Notice(
        ULong ntcSeq,
        String ntcTtl,
        String ntcCtt,
        String ntcVldYn,
        LocalDateTime creDtm,
        LocalDateTime updDtm
    ) {
        this.ntcSeq = ntcSeq;
        this.ntcTtl = ntcTtl;
        this.ntcCtt = ntcCtt;
        this.ntcVldYn = ntcVldYn;
        this.creDtm = creDtm;
        this.updDtm = updDtm;
    }

    /**
     * Getter for <code>spotfinder.NOTICE.NTC_SEQ</code>. 공지 일련번호
     */
    public ULong getNtcSeq() {
        return this.ntcSeq;
    }

    /**
     * Getter for <code>spotfinder.NOTICE.NTC_TTL</code>. 공지 제목
     */
    public String getNtcTtl() {
        return this.ntcTtl;
    }

    /**
     * Getter for <code>spotfinder.NOTICE.NTC_CTT</code>. 공지 내용
     */
    public String getNtcCtt() {
        return this.ntcCtt;
    }

    /**
     * Getter for <code>spotfinder.NOTICE.NTC_VLD_YN</code>. 유효여부
     */
    public String getNtcVldYn() {
        return this.ntcVldYn;
    }

    /**
     * Getter for <code>spotfinder.NOTICE.CRE_DTM</code>. 등록일시
     */
    public LocalDateTime getCreDtm() {
        return this.creDtm;
    }

    /**
     * Getter for <code>spotfinder.NOTICE.UPD_DTM</code>. 수정일시
     */
    public LocalDateTime getUpdDtm() {
        return this.updDtm;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Notice other = (Notice) obj;
        if (this.ntcSeq == null) {
            if (other.ntcSeq != null)
                return false;
        }
        else if (!this.ntcSeq.equals(other.ntcSeq))
            return false;
        if (this.ntcTtl == null) {
            if (other.ntcTtl != null)
                return false;
        }
        else if (!this.ntcTtl.equals(other.ntcTtl))
            return false;
        if (this.ntcCtt == null) {
            if (other.ntcCtt != null)
                return false;
        }
        else if (!this.ntcCtt.equals(other.ntcCtt))
            return false;
        if (this.ntcVldYn == null) {
            if (other.ntcVldYn != null)
                return false;
        }
        else if (!this.ntcVldYn.equals(other.ntcVldYn))
            return false;
        if (this.creDtm == null) {
            if (other.creDtm != null)
                return false;
        }
        else if (!this.creDtm.equals(other.creDtm))
            return false;
        if (this.updDtm == null) {
            if (other.updDtm != null)
                return false;
        }
        else if (!this.updDtm.equals(other.updDtm))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.ntcSeq == null) ? 0 : this.ntcSeq.hashCode());
        result = prime * result + ((this.ntcTtl == null) ? 0 : this.ntcTtl.hashCode());
        result = prime * result + ((this.ntcCtt == null) ? 0 : this.ntcCtt.hashCode());
        result = prime * result + ((this.ntcVldYn == null) ? 0 : this.ntcVldYn.hashCode());
        result = prime * result + ((this.creDtm == null) ? 0 : this.creDtm.hashCode());
        result = prime * result + ((this.updDtm == null) ? 0 : this.updDtm.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Notice (");

        sb.append(ntcSeq);
        sb.append(", ").append(ntcTtl);
        sb.append(", ").append(ntcCtt);
        sb.append(", ").append(ntcVldYn);
        sb.append(", ").append(creDtm);
        sb.append(", ").append(updDtm);

        sb.append(")");
        return sb.toString();
    }
}