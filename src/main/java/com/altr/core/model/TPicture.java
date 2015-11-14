package com.altr.core.model;

import javax.persistence.*;

@Entity
@Table(name="t_pictures")
public class TPicture
{
    @Id
    @Column(name="picture_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
