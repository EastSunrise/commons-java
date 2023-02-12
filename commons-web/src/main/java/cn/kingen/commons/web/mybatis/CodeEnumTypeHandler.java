package cn.kingen.commons.web.mybatis;

import cn.kingen.commons.lang.function.CodeSupplier;
import cn.kingen.commons.lang.util.EnumUtilExt;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * The common {@link TypeHandler} for enums that are stored as <em>unique</em> codes (returned by
 * {@link CodeSupplier#getCode()}) in the database.
 *
 * @author Kingen
 * @see CodeSupplier
 * @see EnumUtilExt#valueOfCode(Class, String)
 */
public class CodeEnumTypeHandler<E extends Enum<E> & CodeSupplier> extends BaseTypeHandler<E> {

    private final Class<E> type;

    public CodeEnumTypeHandler(Class<E> type) {
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String code = rs.getString(columnName);
        return code == null ? null : EnumUtilExt.valueOfCode(type, code);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String code = rs.getString(columnIndex);
        return code == null ? null : EnumUtilExt.valueOfCode(type, code);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String code = cs.getString(columnIndex);
        return code == null ? null : EnumUtilExt.valueOfCode(type, code);
    }
}
