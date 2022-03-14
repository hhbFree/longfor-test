package longfor.test.excel.pojo;

import lombok.ToString;

import java.util.Objects;

@ToString
public class Category {

    private String name;

    private Integer id;

    private String parentName;

    private Integer parentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(getName(), category.getName()) &&
                Objects.equals(getId(), category.getId()) &&
                Objects.equals(getParentName(), category.getParentName()) &&
                Objects.equals(getParentId(), category.getParentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId(), getParentName(), getParentId());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
