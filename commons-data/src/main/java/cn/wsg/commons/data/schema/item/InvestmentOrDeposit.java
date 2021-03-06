package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A type of financial product that typically requires the client to transfer funds to a financial service in return for
 * potential beneficial financial return.
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#FIBO"})
public interface InvestmentOrDeposit extends FinancialProduct {
}