package models;

public class CourseCustomization {
    private int customizationID;
    private String courseID;
    private Integer contentBlockID; // Nullable
    private Integer activityID;     // Nullable
    private boolean isHidden;
    private String addedByRole;
    private boolean isOriginalContent;
    private int displayOrder;
    private String createdByUserID;

    // Constructors
    public CourseCustomization() {}

    public CourseCustomization(int customizationID, String courseID, Integer contentBlockID, Integer activityID,
                                boolean isHidden, String addedByRole, boolean isOriginalContent,
                                int displayOrder, String createdByUserID) {
        this.customizationID = customizationID;
        this.courseID = courseID;
        this.contentBlockID = contentBlockID;
        this.activityID = activityID;
        this.isHidden = isHidden;
        this.addedByRole = addedByRole;
        this.isOriginalContent = isOriginalContent;
        this.displayOrder = displayOrder;
        this.createdByUserID = createdByUserID;
    }

    // Getters and Setters
    public int getCustomizationID() {
        return customizationID;
    }

    public void setCustomizationID(int customizationID) {
        this.customizationID = customizationID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public Integer getContentBlockID() {
        return contentBlockID;
    }

    public void setContentBlockID(Integer contentBlockID) {
        this.contentBlockID = contentBlockID;
    }

    public Integer getActivityID() {
        return activityID;
    }

    public void setActivityID(Integer activityID) {
        this.activityID = activityID;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public String getAddedByRole() {
        return addedByRole;
    }

    public void setAddedByRole(String addedByRole) {
        this.addedByRole = addedByRole;
    }

    public boolean isOriginalContent() {
        return isOriginalContent;
    }

    public void setOriginalContent(boolean isOriginalContent) {
        this.isOriginalContent = isOriginalContent;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getCreatedByUserID() {
        return createdByUserID;
    }

    public void setCreatedByUserID(String createdByUserID) {
        this.createdByUserID = createdByUserID;
    }
}
