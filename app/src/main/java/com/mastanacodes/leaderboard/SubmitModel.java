package com.mastanacodes.leaderboard;

public class SubmitModel {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String linkToProject;

    public SubmitModel(String firstName, String lastName, String emailAddress, String linkToProject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.linkToProject = linkToProject;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLinkToProject() {
        return linkToProject;
    }

    public void setLinkToProject(String linkToProject) {
        this.linkToProject = linkToProject;
    }
}
