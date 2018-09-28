package com.xtf.aggregatepay;

public interface Consts {
     String SYS_COMMON_ERR_CODE="fail";
     String SYS_COMMON_SUCCESS_CODE="success";
     String SYS_COMMON_FAIL_CODE="fail";


     enum PicType{
         LICENSE(0,"营业执照照片","licenseId"),PICPEOBLE(1,"手持身份证照片","picPeobleId"),BANKCARD(2,"银行卡照片","bankCardId"),CARD(3,"身份证正面照片","cardId"),BACKCARD(4,"身份证背面照片","backCarId"),POWER(5,"非法人结算授权书照片","PowerId"),
         PROTOCOLPHOTO(6,"商户协议照片","protocolPhotoId"),MAINPHOTO(7,"门头照","mainPhotoId"),ORGPHOTO(8,"组织机构代码证照片","orgPhotoId");
         private int val;
         private String name;
         private String str;

         private PicType(int val,String name,String str){
             this.val=val;
             this.name=name;
             this.str=str;
         }

         public String getStr(){
             return this.str;
         }
         public int getVal(){
             return this.val;
         }
     }

     enum STATUS{
         NORMAL("0"),STOP("1");
         private String val;
         private STATUS(String val){
             this.val=val;
         }
         public String getVal(){
             return this.val;
         }
     }

     enum SETTLEWAY{
         T1,Ts
     }

     enum INCOMETYPE{
         normal
     }

     enum AGENT{
         agentNum,agentKey
     }
     enum MERTYPE{
         personal,business
     }
     enum ACCTYPE{
         TO_PUBLIC,TO_PRIVATE
     }

     enum MER_STATUS{
         SHZ,SHTG,DDSH,SHJJ
     }

     enum TRADE_TYPE{
         QUREY("0","查询"),XXBSCAN("1","线下被扫"),XXZSCAN("3","线下主扫"),REFUND("4","退款"),GZZZ("5","公众账号");

         private String val;
         private String name;

         private TRADE_TYPE(String val,String name){
             this.val=val;
             this.name=name;
         }

         public String getVal(){
             return this.val;
         }
     }

     enum BIZ_TYPE{
         WECHATPAY("微信支付"),ALIPAY("支付宝"),QQPAY("QQ支付");
         private String name;
         private BIZ_TYPE(String name){
             this.name=name;
         }

         public String getName(){
             return this.name;
         }
     }

     enum TRADE_STATUS{
         SUCCESS("success","交易成功"),SCAN_PAY_FAILD("scan_pay_faild","交易失败"),PROCESSING("processing","交易处理中"),CLOSEFAILURE("closefailure","交易已关闭"),REFUND_SUCCESS("refund_success","退款成功");

         private String key;
         private String val;

         private TRADE_STATUS(String key,String val){
             this.key=key;
             this.val=val;
         }

         public String getKey(){
             return this.key;
         }

         public String getVal(){
             return  this.val;
         }
     }

     enum SETTLE_STATUS {
         COMMITTED("committed","已提交"),COMMITFAIL("commitfail","提交失败"),REMITSUCCESS("remitsuccess","已结算"),INIT("init","未结算");
         private String key;
         private String val;

         private SETTLE_STATUS(String key,String val){
             this.key=key;
             this.val=val;
         }

         public String getKey(){
             return this.key;
         }

         public String getVal(){
             return  this.val;
         }
     }
}
