package cn.xanderye.entity;

/**
 * @author XanderYe
 * @description:
 * @date 2021/5/2 9:31
 */
public class Progress {

    private Long id;

    private String name;

    private Integer percent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", progress=" + percent +
                '}';
    }
}
