package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * An agent orders a (not yet released) object/product/service to be delivered/sent.
 *
 * @author Kingen
 */
@Source({"https://github.com/schemaorg/schemaorg/issues/1125"})
public interface PreOrderAction extends TradeAction {
}