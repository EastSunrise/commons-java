package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A service to convert funds from one currency to another currency.
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#FIBO"})
public interface CurrencyConversionService extends FinancialProduct {
}