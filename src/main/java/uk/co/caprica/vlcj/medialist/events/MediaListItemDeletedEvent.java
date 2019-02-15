/*
 * This file is part of VLCJ.
 *
 * VLCJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VLCJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VLCJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2009-2019 Caprica Software Limited.
 */

package uk.co.caprica.vlcj.medialist.events;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.binding.internal.libvlc_event_t;
import uk.co.caprica.vlcj.binding.internal.libvlc_instance_t;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.binding.internal.media_list_item_deleted;
import uk.co.caprica.vlcj.medialist.MediaList;
import uk.co.caprica.vlcj.medialist.MediaListEventListener;

/**
 * Encapsulation of a media list item deleted event.
 */
final class MediaListItemDeletedEvent extends MediaListEvent {

    /**
     * Native media instance that was deleted.
     */
    private final libvlc_media_t item;

    /**
     * Index from which the item was deleted.
     */
    private final int index;

    /**
     * Create a media list event.
     *
     * @param libvlc native library
     * @param libvlcInstance native library instance
     * @param mediaList media list the event relates to
     * @param event native event
     */
    MediaListItemDeletedEvent(LibVlc libvlc, libvlc_instance_t libvlcInstance, MediaList mediaList, libvlc_event_t event) {
        super(libvlc, libvlcInstance, mediaList);

        media_list_item_deleted itemDeletedEvent = ((media_list_item_deleted) event.u.getTypedValue(media_list_item_deleted.class));

        this.item = itemDeletedEvent.item;
        this.index         = itemDeletedEvent.index;
    }

    @Override
    public void notify(MediaListEventListener listener) {
        listener.mediaListItemDeleted(component, temporaryMediaRef(item), index);
    }

}
