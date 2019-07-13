package com.example.team6.Model;

public class Job {
    private String companyname,jobdescription,role,skillsrequired,website;
    public Job()
    {

    }

    public Job(String companyname, String jobdescription, String role, String skillsrequired, String website) {
        this.companyname = companyname;
        this.jobdescription = jobdescription;
        this.role = role;
        this.skillsrequired = skillsrequired;
        this.website=website;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getJobdescription() {
        return jobdescription;
    }

    public void setJobdescription(String jobdescription) {
        this.jobdescription = jobdescription;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSkillsrequired() {
        return skillsrequired;
    }

    public void setSkillsrequired(String skillsrequired) {
        this.skillsrequired = skillsrequired;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
