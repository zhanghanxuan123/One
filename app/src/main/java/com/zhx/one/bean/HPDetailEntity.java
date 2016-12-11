package com.zhx.one.bean;

import java.io.Serializable;

/**
 * Author   :zhx
 * Create at 2016/11/30
 * Description: 首页文章详情
 */
public class HPDetailEntity implements Serializable {

    /**
     * res : 0
     * data : {"hpcontent_id":"1544","hp_title":"VOL.1515","author_id":"-1","hp_img_url":"http://image.wufazhuce.com/Fi9LinFbvjXTxbmFhX0SGv6EQTLH","hp_img_original_url":"http://image.wufazhuce.com/Fi9LinFbvjXTxbmFhX0SGv6EQTLH","hp_author":"许久未见 近来可好&十指 作品","ipad_url":"http://image.wufazhuce.com/Fi9LinFbvjXTxbmFhX0SGv6EQTLH","hp_content":"我想每个人都至少有这么一个挚友，你和他在人生的拐点遇到，惊叹于彼此的不同或者相似，有过不少平淡无奇却值得纪念的时光，任白云苍狗，风云变幻。 from 《触不可及》","hp_makettime":"2016-11-29 21:00:00","hide_flag":"0","last_update_date":"2016-11-29 21:14:07","web_url":"http://m.wufazhuce.com/one/1544","wb_img_url":"","image_authors":"十指","text_authors":"《触不可及》","image_from":"","text_from":"","content_bgcolor":"#FFFFFF","maketime":"2016-11-29 21:00:00","praisenum":13531,"sharenum":1151,"commentnum":0}
     */

    private int res;
    /**
     * hpcontent_id : 1544
     * hp_title : VOL.1515
     * author_id : -1
     * hp_img_url : http://image.wufazhuce.com/Fi9LinFbvjXTxbmFhX0SGv6EQTLH
     * hp_img_original_url : http://image.wufazhuce.com/Fi9LinFbvjXTxbmFhX0SGv6EQTLH
     * hp_author : 许久未见 近来可好&十指 作品
     * ipad_url : http://image.wufazhuce.com/Fi9LinFbvjXTxbmFhX0SGv6EQTLH
     * hp_content : 我想每个人都至少有这么一个挚友，你和他在人生的拐点遇到，惊叹于彼此的不同或者相似，有过不少平淡无奇却值得纪念的时光，任白云苍狗，风云变幻。 from 《触不可及》
     * hp_makettime : 2016-11-29 21:00:00
     * hide_flag : 0
     * last_update_date : 2016-11-29 21:14:07
     * web_url : http://m.wufazhuce.com/one/1544
     * wb_img_url :
     * image_authors : 十指
     * text_authors : 《触不可及》
     * image_from :
     * text_from :
     * content_bgcolor : #FFFFFF
     * maketime : 2016-11-29 21:00:00
     * praisenum : 13531
     * sharenum : 1151
     * commentnum : 0
     */

    private DataBean data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String hpcontent_id;
        private String hp_title;
        private String author_id;
        private String hp_img_url;
        private String hp_img_original_url;
        private String hp_author;
        private String ipad_url;
        private String hp_content;
        private String hp_makettime;
        private String hide_flag;
        private String last_update_date;
        private String web_url;
        private String wb_img_url;
        private String image_authors;
        private String text_authors;
        private String image_from;
        private String text_from;
        private String content_bgcolor;
        private String maketime;
        private int praisenum;
        private int sharenum;
        private int commentnum;

        public String getHpcontent_id() {
            return hpcontent_id;
        }

        public void setHpcontent_id(String hpcontent_id) {
            this.hpcontent_id = hpcontent_id;
        }

        public String getHp_title() {
            return hp_title;
        }

        public void setHp_title(String hp_title) {
            this.hp_title = hp_title;
        }

        public String getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(String author_id) {
            this.author_id = author_id;
        }

        public String getHp_img_url() {
            return hp_img_url;
        }

        public void setHp_img_url(String hp_img_url) {
            this.hp_img_url = hp_img_url;
        }

        public String getHp_img_original_url() {
            return hp_img_original_url;
        }

        public void setHp_img_original_url(String hp_img_original_url) {
            this.hp_img_original_url = hp_img_original_url;
        }

        public String getHp_author() {
            return hp_author;
        }

        public void setHp_author(String hp_author) {
            this.hp_author = hp_author;
        }

        public String getIpad_url() {
            return ipad_url;
        }

        public void setIpad_url(String ipad_url) {
            this.ipad_url = ipad_url;
        }

        public String getHp_content() {
            return hp_content;
        }

        public void setHp_content(String hp_content) {
            this.hp_content = hp_content;
        }

        public String getHp_makettime() {
            return hp_makettime;
        }

        public void setHp_makettime(String hp_makettime) {
            this.hp_makettime = hp_makettime;
        }

        public String getHide_flag() {
            return hide_flag;
        }

        public void setHide_flag(String hide_flag) {
            this.hide_flag = hide_flag;
        }

        public String getLast_update_date() {
            return last_update_date;
        }

        public void setLast_update_date(String last_update_date) {
            this.last_update_date = last_update_date;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

        public String getWb_img_url() {
            return wb_img_url;
        }

        public void setWb_img_url(String wb_img_url) {
            this.wb_img_url = wb_img_url;
        }

        public String getImage_authors() {
            return image_authors;
        }

        public void setImage_authors(String image_authors) {
            this.image_authors = image_authors;
        }

        public String getText_authors() {
            return text_authors;
        }

        public void setText_authors(String text_authors) {
            this.text_authors = text_authors;
        }

        public String getImage_from() {
            return image_from;
        }

        public void setImage_from(String image_from) {
            this.image_from = image_from;
        }

        public String getText_from() {
            return text_from;
        }

        public void setText_from(String text_from) {
            this.text_from = text_from;
        }

        public String getContent_bgcolor() {
            return content_bgcolor;
        }

        public void setContent_bgcolor(String content_bgcolor) {
            this.content_bgcolor = content_bgcolor;
        }

        public String getMaketime() {
            return maketime;
        }

        public void setMaketime(String maketime) {
            this.maketime = maketime;
        }

        public int getPraisenum() {
            return praisenum;
        }

        public void setPraisenum(int praisenum) {
            this.praisenum = praisenum;
        }

        public int getSharenum() {
            return sharenum;
        }

        public void setSharenum(int sharenum) {
            this.sharenum = sharenum;
        }

        public int getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(int commentnum) {
            this.commentnum = commentnum;
        }
    }
}
