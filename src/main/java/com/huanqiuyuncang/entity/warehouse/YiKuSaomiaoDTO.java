package com.huanqiuyuncang.entity.warehouse;

/**
 * Created by lzf on 2017/7/13.
 */
public class YiKuSaomiaoDTO {

    private String shangpintiaoma;

    private String kuwei;

    private String shuliang;

    public YiKuSaomiaoDTO() {
    }

    public YiKuSaomiaoDTO(String shangpintiaoma, String kuwei, String shuliang) {
        this.shangpintiaoma = shangpintiaoma;
        this.kuwei = kuwei;
        this.shuliang = shuliang;
    }

    public String getShangpintiaoma() {
        return shangpintiaoma;
    }

    public void setShangpintiaoma(String shangpintiaoma) {
        this.shangpintiaoma = shangpintiaoma;
    }

    public String getKuwei() {
        return kuwei;
    }

    public void setKuwei(String kuwei) {
        this.kuwei = kuwei;
    }

    public String getShuliang() {
        return shuliang;
    }

    public void setShuliang(String shuliang) {
        this.shuliang = shuliang;
    }
}
