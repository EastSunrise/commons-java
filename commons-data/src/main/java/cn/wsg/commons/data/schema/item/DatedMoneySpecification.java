package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.SupersededBy;

/**
 * A DatedMoneySpecification represents monetary values with optional start and end dates. For example, this could
 * represent an employee's salary over a
 * specific period of time. __Note:__ This type has been superseded by [[MonetaryAmount]] use of that type is
 * recommended
 *
 * @author Kingen
 */
@SupersededBy(MonetaryAmount.class)
public interface DatedMoneySpecification extends StructuredValue {
}