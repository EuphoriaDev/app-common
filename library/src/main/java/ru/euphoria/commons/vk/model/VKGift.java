package ru.euphoria.commons.vk.model;

import java.io.Serializable;

import ru.euphoria.commons.json.JsonObject;

/**
 * Describes a gift object from VK.
 *
 * @since 1.1
 */
public class VKGift implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID of the user who sent the gift (or 0 if the gift is anonymous). */
    public int from_id;

    /** Gift ID. */
    public long id;

    /** Text of the message attached to the gift. */
    public String message;

    /** Time to send gift in unix time format. */
    public long date;

    /** URL of the gift image with 48x48px size. */
    public String thumb_48;

    /** URL of the gift image with 96x96px size. */
    public String thumb_96;

    /** URL of the gift image with 256x256px. */
    public String thumb_256;

    /** Privacy settings (for current user only). */
    public int privacy;

    /**
     * Creates a new gift model with fields from json source.
     *
     * @param source the json source to parse
     */
    public VKGift(JsonObject source) {
        this.id = source.optLong("id");
        this.from_id = source.optInt("from_id");
        this.date = source.optLong("date");
        this.message = source.optString("message");

        JsonObject gift = source.has("gift") ? source.optJsonObject("gift")
                : source;

        this.thumb_48  = gift.optString("thumb_48");
        this.thumb_96  = gift.optString("thumb_96");
        this.thumb_256 = gift.optString("thumb_256");

    }

}