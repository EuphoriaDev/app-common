package ru.euphoria.commons.vk.model;

import java.io.Serializable;

import ru.euphoria.commons.json.JsonObject;

/**
 * Describes a Wiki page.
 */
public class VKPage implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Wiki page ID. */
    public int id;

    /** ID of the group the wiki page belongs to. */
    public int group_id;

    /** ID of the page creator. */
    public int creator_id;

    /** Wiki page name. */
    public String title;

    /** Text of the wiki page. */
    public String source;

    /** Whether a user can edit page text (false — cannot, true — can). */
    public boolean current_user_can_edit;

    /** Whether a user can edit page access permissions (false — cannot, true — can). */
    public boolean current_user_can_edit_access;

    /** Who can view the wiki page (0 — only community managers, 1 — only community members, 2 — all users). */
    public int who_can_view;

    /** Who can edit the wiki page (0 — only community managers, 1 — only community members, 2 — all users). */
    public int who_can_edit;

    /** ID of the last user who edited the page. */
    public int editor_id;

    /** Date of the last change. */
    public long edited;

    /** Page creation date. */
    public long created;

    /** Title of the parent page for navigation, if any. */
    public String parent;

    /** Title of the second parent page for navigation, if any. */
    public String parent2;

	public VKPage(JsonObject source) {
        this.id = source.optInt("id");
        this.group_id = source.optInt("group_id");
        this.creator_id = source.optInt("creator_id");
        this.title = source.optString("title");
        this.source = source.optString("source");
        this.current_user_can_edit = source.optInt("current_user_can_edit") == 1;
        this.current_user_can_edit_access = source.optInt("current_user_can_edit_access") == 1;
        this.who_can_view = source.optInt("who_can_view");
        this.who_can_edit = source.optInt("who_can_edit");
        this.editor_id = source.optInt("editor_id");
        this.edited = source.optLong("edited");
        this.created = source.optLong("created");
        this.parent = source.optString("parent");
        this.parent2 = source.optString("parent2");
	}

    public CharSequence toAttachmentString() {
        return new StringBuilder("page").append(group_id).append('_').append(id);
    }

    @Override
    public String toString() {
        return title;
    }
}