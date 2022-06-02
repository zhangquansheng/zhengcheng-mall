package com.zhengcheng.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.data.elasticsearch.repository.ElasticsearchIndex;
import com.zhengcheng.data.elasticsearch.repository.ElasticsearchTemplate;
import com.zhengcheng.mall.document.HouseDocument;
import com.zhengcheng.mall.service.HouseDocumentService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * HouseDocumentServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2021/6/15 19:31
 */
@Slf4j
@Service
public class HouseDocumentServiceImpl implements HouseDocumentService {

    @Autowired
    private ElasticsearchIndex elasticsearchIndex;
    @Autowired
    private ElasticsearchTemplate<HouseDocument> elasticsearchTemplate;

    @Override
    public void createIndex() throws IOException {
        elasticsearchIndex.create(HouseDocument.class);
    }

    @Override
    public void save(HouseDocument houseDocument) throws IOException {
        elasticsearchTemplate.save(houseDocument);
    }

    @Override
    public void saveAll(List<HouseDocument> houseDocuments) throws IOException {
        elasticsearchTemplate.batchSave(houseDocuments);
    }

    @Override
    public List<HouseDocument> findList(String lpArea, String name, String wyEnterprise) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.sort(new FieldSortBuilder("recordNum").order(SortOrder.DESC));
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (StrUtil.isNotBlank(name)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("name", name));
        }

        if (StrUtil.isNotBlank(lpArea)) {
            TermQueryBuilder termQueryBuilder = new TermQueryBuilder("lpArea", lpArea);
            boolQueryBuilder.must(termQueryBuilder);
        }

        if (StrUtil.isNotBlank(wyEnterprise)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("wyEnterprise", wyEnterprise));
        }
        sourceBuilder.query(boolQueryBuilder);

        // Highlighting
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        // 设置前缀
        highlightBuilder.preTags("<span class='layui-bg-red'>");
        // 设置后缀
        highlightBuilder.postTags("</span>");
        HighlightBuilder.Field highlightName = new HighlightBuilder.Field("name");
        highlightBuilder.field(highlightName);
        sourceBuilder.highlighter(highlightBuilder);

        try {
            PageInfo<HouseDocument> docPageInfo =
                    elasticsearchTemplate.page(sourceBuilder, new PageCommand(), HouseDocument.class);
//            pageInfo.setTotal(docPageInfo.getTotal());
//            pageInfo.setList(houseAssembler.docToDTOs(docPageInfo.getList()));
//            return pageInfo;
            return docPageInfo.getRecords();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return Lists.newArrayList();
    }
}
