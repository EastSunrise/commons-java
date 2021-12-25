package cn.wsg.commons.web.mybatis;

import cn.wsg.commons.lang.EnumUtilExt;
import cn.wsg.commons.lang.Region;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * TypeHandler for {@link Region} stored as 2-alpha codes.
 *
 * @author Kingen
 */
public class RegionTypeHandler extends BaseTypeHandler<Region> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Region region, JdbcType jdbcType)
        throws SQLException {
        ps.setString(i, region.getAlpha2());
    }

    @Override
    public Region getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String alpha2 = rs.getString(columnName);
        return alpha2 == null ? null
            : EnumUtilExt.valueOfKey(Region.class, alpha2, Region::getAlpha2);
    }

    @Override
    public Region getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String alpha2 = rs.getString(columnIndex);
        return alpha2 == null ? null
            : EnumUtilExt.valueOfKey(Region.class, alpha2, Region::getAlpha2);
    }

    @Override
    public Region getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String alpha2 = cs.getString(columnIndex);
        return alpha2 == null ? null
            : EnumUtilExt.valueOfKey(Region.class, alpha2, Region::getAlpha2);
    }
}
