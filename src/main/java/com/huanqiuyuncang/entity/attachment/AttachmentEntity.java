package com.huanqiuyuncang.entity.attachment;

import java.util.Date;

public class AttachmentEntity {
    private String id;

    private Date created;

    private Date modified;

    private String contentType;

    private Date dateCreated;

    private Date lastUpdated;

    private String name;

    private String newFilename;

    private String newBriefname;

    private Long size;

    private String thumbnailFilename;

    private Long thumbnailSize;

    private String url;
    private String thumbnailUrl;
    private String deleteUrl;
    private String deleteType;



    public AttachmentEntity(String id, Date created, Date modified, String contentType, Date dateCreated, Date lastUpdated, String name, String newFilename, String newBriefname, Long size, String thumbnailFilename, Long thumbnailSize) {
        this.id = id;
        this.created = created;
        this.modified = modified;
        this.contentType = contentType;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.name = name;
        this.newFilename = newFilename;
        this.newBriefname = newBriefname;
        this.size = size;
        this.thumbnailFilename = thumbnailFilename;
        this.thumbnailSize = thumbnailSize;
    }

    public AttachmentEntity() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNewFilename() {
        return newFilename;
    }

    public void setNewFilename(String newFilename) {
        this.newFilename = newFilename == null ? null : newFilename.trim();
    }

    public String getNewBriefname() {
        return newBriefname;
    }

    public void setNewBriefname(String newBriefname) {
        this.newBriefname = newBriefname == null ? null : newBriefname.trim();
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getThumbnailFilename() {
        return thumbnailFilename;
    }

    public void setThumbnailFilename(String thumbnailFilename) {
        this.thumbnailFilename = thumbnailFilename == null ? null : thumbnailFilename.trim();
    }

    public Long getThumbnailSize() {
        return thumbnailSize;
    }

    public void setThumbnailSize(Long thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDeleteUrl() {
        return deleteUrl;
    }

    public void setDeleteUrl(String deleteUrl) {
        this.deleteUrl = deleteUrl;
    }

    public String getDeleteType() {
        return deleteType;
    }

    public void setDeleteType(String deleteType) {
        this.deleteType = deleteType;
    }
}