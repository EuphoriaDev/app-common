package ru.euphoria.commons.vk.model;

import java.io.Serializable;

import ru.euphoria.commons.json.JsonObject;

/**
 * Describes a note object from VK.
 *
 * @since 1.1
 */
public class VKNote implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Note ID, positive number */
    public int id;

    /** Note owner ID. */
    public int user_id;

    /** Note title. */
    public String title;

    /** Note text. */
    public String text;

    /** Date (in Unix time) when the note was created. */
    public long date;

    /** Number of comments. */
    public int comments;

    /** Number of read comments (only if owner_id is the current user). */
    public int read_comments;

    /** Note URL. */
    public String view_url;

    /**
     * Creates a new note model with fields from json source
     *
     * @param source the json source to parse
     */
    public VKNote(JsonObject source) {
        this.id = source.optInt("id", source.optInt("nid"));
        this.user_id = source.optInt("user_id", source.optInt("owner_id"));
        this.title = source.optString("title");
        this.text = source.optString("text");
        this.date = source.optLong("date");
        this.comments = source.optInt("comments", source.optInt("ncom"));
        this.read_comments = source.optInt("read_comments");
        this.view_url = source.optString("view_url");
    }

    @Override
    public String toString() {
        return title;
    }
}