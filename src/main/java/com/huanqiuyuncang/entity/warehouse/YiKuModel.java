package com.huanqiuyuncang.entity.warehouse;

import java.util.List;

/**
 * Created by lzf on 2017/7/18.
 */
public class YiKuModel {

    private List<YiKuSaomiaoDTO> list;

    public YiKuModel(List<YiKuSaomiaoDTO> list) {
        this.list = list;
    }

    public YiKuModel() {
    }

    public List<YiKuSaomiaoDTO> getList() {
        return list;
    }

    public void setList(List<YiKuSaomiaoDTO> list) {
        this.list = list;
    }
}
