package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.RdfClass;

/**
 * An event happening at a certain time and location, such as a concert, lecture, or festival. Ticketing information may
 * be added via the [[offers]] property.
 * Repeated events may be structured as separate Event objects.
 *
 * @author Kingen
 */
@RdfClass(equivalentClass = {"http://purl.org/dc/dcmitype/Event"})
public interface Event extends Thing {
}