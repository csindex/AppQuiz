package com.example.appquiz.newfeature.bodypartsfeature.data;

public class BodyPart {
    String bodyPartID;
    String bodyPartImageURL;
    String bodyPartName;
    String bodyPartDescription;

    public BodyPart() {
    }

    public BodyPart(String bodyPartID,String bodyPartImageURL, String bodyPartName, String bodyPartDescription) {
        this.bodyPartID = bodyPartID;
        this.bodyPartImageURL = bodyPartImageURL;
        this.bodyPartName = bodyPartName;
        this.bodyPartDescription = bodyPartDescription;
    }

    public String getBodyPartID() {
        return bodyPartID;
    }

    public void setBodyPartID(String bodyPartID) {
        this.bodyPartID = bodyPartID;
    }

    public String getBodyPartImageURL() {
        return bodyPartImageURL;
    }

    public void setBodyPartImageURL(String bodyPartImageURL) {
        this.bodyPartImageURL = bodyPartImageURL;
    }

    public String getBodyPartName() {
        return bodyPartName;
    }

    public void setBodyPartName(String bodyPartName) {
        this.bodyPartName = bodyPartName;
    }

    public String getBodyPartDescription() {
        return bodyPartDescription;
    }

    public void setBodyPartDescription(String bodyPartDescription) {
        this.bodyPartDescription = bodyPartDescription;
    }
}
