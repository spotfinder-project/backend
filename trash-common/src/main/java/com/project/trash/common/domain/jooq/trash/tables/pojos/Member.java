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
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    private final ULong mbrSeq;
    private final String mbrEmail;
    private final String mbrNm;
    private final String mbrGndr;
    private final String mbrBrdt;
    private final String mbrSclId;
    private final String mbrSclTyp;
    private final String mbrVldYn;
    private final LocalDateTime creDtm;
    private final LocalDateTime updDtm;

    public Member(Member value) {
        this.mbrSeq = value.mbrSeq;
        this.mbrEmail = value.mbrEmail;
        this.mbrNm = value.mbrNm;
        this.mbrGndr = value.mbrGndr;
        this.mbrBrdt = value.mbrBrdt;
        this.mbrSclId = value.mbrSclId;
        this.mbrSclTyp = value.mbrSclTyp;
        this.mbrVldYn = value.mbrVldYn;
        this.creDtm = value.creDtm;
        this.updDtm = value.updDtm;
    }

    public Member(
        ULong mbrSeq,
        String mbrEmail,
        String mbrNm,
        String mbrGndr,
        String mbrBrdt,
        String mbrSclId,
        String mbrSclTyp,
        String mbrVldYn,
        LocalDateTime creDtm,
        LocalDateTime updDtm
    ) {
        this.mbrSeq = mbrSeq;
        this.mbrEmail = mbrEmail;
        this.mbrNm = mbrNm;
        this.mbrGndr = mbrGndr;
        this.mbrBrdt = mbrBrdt;
        this.mbrSclId = mbrSclId;
        this.mbrSclTyp = mbrSclTyp;
        this.mbrVldYn = mbrVldYn;
        this.creDtm = creDtm;
        this.updDtm = updDtm;
    }

    /**
     * Getter for <code>trash.MEMBER.MBR_SEQ</code>. 회원 일련번호
     */
    public ULong getMbrSeq() {
        return this.mbrSeq;
    }

    /**
     * Getter for <code>trash.MEMBER.MBR_EMAIL</code>. 이메일
     */
    public String getMbrEmail() {
        return this.mbrEmail;
    }

    /**
     * Getter for <code>trash.MEMBER.MBR_NM</code>. 이름
     */
    public String getMbrNm() {
        return this.mbrNm;
    }

    /**
     * Getter for <code>trash.MEMBER.MBR_GNDR</code>. 성별
     */
    public String getMbrGndr() {
        return this.mbrGndr;
    }

    /**
     * Getter for <code>trash.MEMBER.MBR_BRDT</code>. 생년월일
     */
    public String getMbrBrdt() {
        return this.mbrBrdt;
    }

    /**
     * Getter for <code>trash.MEMBER.MBR_SCL_ID</code>. 소셜 ID
     */
    public String getMbrSclId() {
        return this.mbrSclId;
    }

    /**
     * Getter for <code>trash.MEMBER.MBR_SCL_TYP</code>. 소셜 타입
     */
    public String getMbrSclTyp() {
        return this.mbrSclTyp;
    }

    /**
     * Getter for <code>trash.MEMBER.MBR_VLD_YN</code>. 유효여부
     */
    public String getMbrVldYn() {
        return this.mbrVldYn;
    }

    /**
     * Getter for <code>trash.MEMBER.CRE_DTM</code>. 등록일시
     */
    public LocalDateTime getCreDtm() {
        return this.creDtm;
    }

    /**
     * Getter for <code>trash.MEMBER.UPD_DTM</code>. 수정일시
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
        final Member other = (Member) obj;
        if (this.mbrSeq == null) {
            if (other.mbrSeq != null)
                return false;
        }
        else if (!this.mbrSeq.equals(other.mbrSeq))
            return false;
        if (this.mbrEmail == null) {
            if (other.mbrEmail != null)
                return false;
        }
        else if (!this.mbrEmail.equals(other.mbrEmail))
            return false;
        if (this.mbrNm == null) {
            if (other.mbrNm != null)
                return false;
        }
        else if (!this.mbrNm.equals(other.mbrNm))
            return false;
        if (this.mbrGndr == null) {
            if (other.mbrGndr != null)
                return false;
        }
        else if (!this.mbrGndr.equals(other.mbrGndr))
            return false;
        if (this.mbrBrdt == null) {
            if (other.mbrBrdt != null)
                return false;
        }
        else if (!this.mbrBrdt.equals(other.mbrBrdt))
            return false;
        if (this.mbrSclId == null) {
            if (other.mbrSclId != null)
                return false;
        }
        else if (!this.mbrSclId.equals(other.mbrSclId))
            return false;
        if (this.mbrSclTyp == null) {
            if (other.mbrSclTyp != null)
                return false;
        }
        else if (!this.mbrSclTyp.equals(other.mbrSclTyp))
            return false;
        if (this.mbrVldYn == null) {
            if (other.mbrVldYn != null)
                return false;
        }
        else if (!this.mbrVldYn.equals(other.mbrVldYn))
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
        result = prime * result + ((this.mbrSeq == null) ? 0 : this.mbrSeq.hashCode());
        result = prime * result + ((this.mbrEmail == null) ? 0 : this.mbrEmail.hashCode());
        result = prime * result + ((this.mbrNm == null) ? 0 : this.mbrNm.hashCode());
        result = prime * result + ((this.mbrGndr == null) ? 0 : this.mbrGndr.hashCode());
        result = prime * result + ((this.mbrBrdt == null) ? 0 : this.mbrBrdt.hashCode());
        result = prime * result + ((this.mbrSclId == null) ? 0 : this.mbrSclId.hashCode());
        result = prime * result + ((this.mbrSclTyp == null) ? 0 : this.mbrSclTyp.hashCode());
        result = prime * result + ((this.mbrVldYn == null) ? 0 : this.mbrVldYn.hashCode());
        result = prime * result + ((this.creDtm == null) ? 0 : this.creDtm.hashCode());
        result = prime * result + ((this.updDtm == null) ? 0 : this.updDtm.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Member (");

        sb.append(mbrSeq);
        sb.append(", ").append(mbrEmail);
        sb.append(", ").append(mbrNm);
        sb.append(", ").append(mbrGndr);
        sb.append(", ").append(mbrBrdt);
        sb.append(", ").append(mbrSclId);
        sb.append(", ").append(mbrSclTyp);
        sb.append(", ").append(mbrVldYn);
        sb.append(", ").append(creDtm);
        sb.append(", ").append(updDtm);

        sb.append(")");
        return sb.toString();
    }
}