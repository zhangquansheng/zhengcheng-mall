package com.zhengcheng.mall.service;


import com.zhengcheng.mall.document.HouseDocument;

import java.io.IOException;
import java.util.List;

/**
 * HouseDocumentService
 *
 * @author quansheng1.zhang
 * @since 2021/6/15 19:30
 */
public interface HouseDocumentService {

    void createIndex() throws IOException;

    void save(HouseDocument houseDocument) throws IOException;

    void saveAll(List<HouseDocument> houseDocuments) throws IOException;

    List<HouseDocument> findList(String lpArea, String name, String wyEnterprise);
}
