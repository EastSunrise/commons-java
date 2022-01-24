package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.SupersededBy;

/**
 * A taxi.
 *
 * @author Kingen
 */
@SupersededBy(TaxiService.class)
public interface Taxi extends Service {
}