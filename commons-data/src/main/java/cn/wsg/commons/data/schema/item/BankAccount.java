package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A product or service offered by a bank whereby one may deposit, withdraw or transfer money and in some cases be paid
 * interest.
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#FIBO"})
public interface BankAccount extends FinancialProduct {
}