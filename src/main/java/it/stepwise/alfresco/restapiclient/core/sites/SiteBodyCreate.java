package it.stepwise.alfresco.restapiclient.core.sites;

import it.stepwise.alfresco.restapiclient.InputBody;

public class SiteBodyCreate extends InputBody {
    String id;
    String title;
    String description;
    String visibility;

    public SiteBodyCreate(String id, String title, String description, String visibility) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "SiteBodyCreate{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", visibility='" + visibility + '\'' +
                '}';
    }
}
