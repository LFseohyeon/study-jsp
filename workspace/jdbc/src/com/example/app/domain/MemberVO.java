package com.example.app.domain;

public class MemberVO { //필드
   private Long memberId;
   private String memberIdentification;
   private String memberPassword;
   
   public MemberVO() {;} //기본생성자

   public Long getMemberId() { //게터세터 
      return memberId;
   }

   public void setMemberId(Long memberId) {
      this.memberId = memberId;
   }

   public String getMemberIdentification() {
      return memberIdentification;
   }

   public void setMemberIdentification(String memberIdentification) {
      this.memberIdentification = memberIdentification;
   }

   public String getMemberPassword() {
      return memberPassword;
   }

   public void setMemberPassword(String memberPassword) {
      this.memberPassword = memberPassword;
   }

   @Override
   public String toString() { //toString 
      return "MemberVO [memberId=" + memberId + ", memberIdentification=" + memberIdentification + ", memberPassword="
            + memberPassword + "]";
   }
 
   @Override //이거... 머임??
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      MemberVO other = (MemberVO) obj;
      if (memberId == null) {
         if (other.memberId != null)
            return false;
      } else if (!memberId.equals(other.memberId))
         return false;
      return true;
   }
}