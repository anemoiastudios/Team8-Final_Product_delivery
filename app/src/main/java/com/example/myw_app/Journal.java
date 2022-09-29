package com.example.myw_app;

public class Journal {
    private String journalTitle;
    private String journalDescription;

    public Journal(String journalTitle, String journalDescription) {
        this.journalTitle = journalTitle;
        this.journalDescription = journalDescription;
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

    public String getJournalDescription() {
        return journalDescription;
    }

    public void setJournalDescription(String journalDescription) {
        this.journalDescription = journalDescription;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "journalTitle='" + journalTitle + '\'' +
                ", journalDescription='" + journalDescription + '\'' +
                '}';
    }
}
