package com.Panlantic.Secondproject.entity;


import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.text.SimpleDateFormat;


@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String status;
    private String description;
    private SimpleDateFormat datecreate;
    private SimpleDateFormat datechange;
    private String responsible;
    private String autor;

    public Task() {
    }

    public Task(Integer id, String name, String status, String description,SimpleDateFormat datecreate, SimpleDateFormat datechange, String responsible, String autor) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;
        this.datecreate = datecreate;
        this.datechange =  datechange;
        this.responsible =  responsible;
        this.autor =  autor;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name= name;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status= status;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description= description;
    }
    public SimpleDateFormat getDatecreate(SimpleDateFormat datecreate) {
        return datecreate;
    }
    public void setDatecreate(SimpleDateFormat datecreate) {
        this.datecreate = datecreate;
    }
    public SimpleDateFormat getDatechange(SimpleDateFormat datechange) {
        return datechange;
    }
    public void setDatechange(SimpleDateFormat datechange) {
        this.datechange = datechange;
    }
    public String getResponsible() {
        return responsible;
    }
    public void setResponsible(String responsible) {
        this.responsible= responsible;
    }
    public String getAutor(String autor) {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor= autor;
    }

}
