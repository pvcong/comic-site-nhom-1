package vn.group.utils;

public class CommanderUtils {
    private Integer limit = 4;
    private Integer offset = 0;
    private Integer maxResult;
    private Integer size = 0;
    private Integer page = 0;
    public CommanderUtils() {
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
