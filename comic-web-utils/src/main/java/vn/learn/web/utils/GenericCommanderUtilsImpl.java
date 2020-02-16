package vn.learn.web.utils;

import org.springframework.stereotype.Component;
import vn.group.dto.UserDTO;

import java.util.Map;

@Component
public class GenericCommanderUtilsImpl<T> implements GenericCommanderUtils<T>{
    private Integer limit = 4;
    private Integer offset = 0;
    private Integer maxResult;
    private Integer size = 0;
    private Map<String,String> sortProperties;
    private Map<String,String> properties;
    private Integer page = 0;
    private T objectDTO;

    public T getObjectDTO() {
        return objectDTO;
    }

    public void setObjectDTO(T objectDTO) {
        this.objectDTO = objectDTO;
    }

    public GenericCommanderUtilsImpl() {
    }

    public Map<String, String> getSortProperties() {
        return sortProperties;
    }

    public void setSortProperties(Map<String, String> sortProperties) {
        this.sortProperties = sortProperties;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(Integer maxResult) {
        this.maxResult = maxResult;
    }
    public int getOffset(){
         intitFirstItem();
         return offset;
    }
    public void intitFirstItem(){
        offset = limit * (page - 1);

    }

}
