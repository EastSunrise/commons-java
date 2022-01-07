package cn.wsg.commons.web.mybatis;

import cn.wsg.commons.lang.EnumUtilExt;
import cn.wsg.commons.lang.function.IntCodeSupplier;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The common {@link TypeHandler} for enums that are stored as <em>unique</em> integer codes
 * (returned by {@link IntCodeSupplier#getIntCode()}) in the database.
 *
 * @author Kingen
 * @see IntCodeSupplier
 * @see EnumUtilExt#valueOfIntCode(Class, int)
 */
public class IntCodeEnumTypeHandler<E extends Enum<E> & IntCodeSupplier> extends BaseTypeHandler<E> {

    private final Class<E> type;

    public IntCodeEnumTypeHandler(Class<E> type) {
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E e, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, e.getIntCode());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return code == 0 && rs.wasNull() ? null : EnumUtilExt.valueOfIntCode(type, code);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return code == 0 && rs.wasNull() ? null : EnumUtilExt.valueOfIntCode(type, code);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return code == 0 && cs.wasNull() ? null : EnumUtilExt.valueOfIntCode(type, code);
    }
}
