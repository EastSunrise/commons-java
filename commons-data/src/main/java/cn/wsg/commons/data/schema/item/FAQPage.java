package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A [[FAQPage]] is a [[WebPage]] presenting one or more \"[Frequently asked questions](https://en.wikipedia
 * .org/wiki/FAQ)\"
 * (see also [[QAPage]]).
 *
 * @author Kingen
 */
@Source({"https://github.com/schemaorg/schemaorg/issues/1723"})
public interface FAQPage extends WebPage {
}