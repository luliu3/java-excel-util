package git.luliu.excel.model;

/**
 * @author luliu3 on 2016/8/18.
 */
public class Speaker {

    private Integer workId;
    private Integer speakerId;
    private Integer categoryId;
    private String speakerName;
    private String speakerGender;
    private String speakerCategory;
    private String speakerStyle;
    private String workName;
    private String workUrl;

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(Integer speakerId) {
        this.speakerId = speakerId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public String getSpeakerGender() {
        return speakerGender;
    }

    public void setSpeakerGender(String speakerGender) {
        this.speakerGender = speakerGender;
    }

    public String getSpeakerCategory() {
        return speakerCategory;
    }

    public void setSpeakerCategory(String speakerCategory) {
        this.speakerCategory = speakerCategory;
    }

    public String getSpeakerStyle() {
        return speakerStyle;
    }

    public void setSpeakerStyle(String speakerStyle) {
        this.speakerStyle = speakerStyle;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkUrl() {
        return workUrl;
    }

    public void setWorkUrl(String workUrl) {
        this.workUrl = workUrl;
    }

    @Override
    public String toString() {

        // 一般情况下使用
        return "{" +
                "\"speaker_id\":" + speakerId +
                ", \"speaker_name\": \"" + speakerName + '"' +
                ", \"speaker_gender\": \"" + speakerGender + '"' +
                ", \"speaker_category\": \"" + speakerCategory + '"' +
                ", \"work_name\": \"" + workName + '"' +
                ", \"work_url\": \"" + workUrl + '"' +
                '}';

        // 主播信息是使用
        /*return "{" +
                "\"speaker_id\":" + speakerId +
                ", \"speaker_name\": \"" + speakerName + '"' +
                ", \"speaker_gender\": \"" + speakerGender + '"' +
                ", \"speaker_style\": \"" + speakerStyle + '"' +
                '}';*/

        /*return "{" +
                "\"speaker_id\":" + speakerId +
                ", \"category_id\": " + categoryId +
                ", \"speaker_name\": \"" + speakerName + '"' +
                ", \"speaker_gender\": \"" + speakerGender + '"' +
                ", \"speaker_category\": \"" + speakerCategory + '"' +
                ", \"speaker_style\": \"" + speakerStyle + '"' +
                ", \"work_id\": " + workId +
                ", \"work_name\": \"" + workName + '"' +
                ", \"work_url\": \"" + workUrl + '"' +
                '}';*/
    }

}
