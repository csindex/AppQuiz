package com.example.appquiz.newfeature.bodypartsfeature.data;

public class BodyPart2 {
    String bodyPartID;
    int bodyPartImageResource;
    String bodyPartName;
    String bodyPartDescription;

    public BodyPart2() {
    }

    public BodyPart2(String bodyPartID, int bodyPartImageResource, String bodyPartName, String bodyPartDescription) {
        this.bodyPartID = bodyPartID;
        this.bodyPartImageResource = bodyPartImageResource;
        this.bodyPartName = bodyPartName;
        this.bodyPartDescription = bodyPartDescription;
    }

    public String getBodyPartID() {
        return bodyPartID;
    }

    public void setBodyPartID(String bodyPartID) {
        this.bodyPartID = bodyPartID;
    }

    public int getBodyPartImageResource() {
        return bodyPartImageResource;
    }

    public void setBodyPartImageResource(int bodyPartImageResource) {
        this.bodyPartImageResource = bodyPartImageResource;
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
